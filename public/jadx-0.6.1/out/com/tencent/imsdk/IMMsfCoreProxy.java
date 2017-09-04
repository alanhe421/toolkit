package com.tencent.imsdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.pay.http.APPluginErrorCode;
import com.qq.taf.jce.JceStruct;
import com.tencent.IMCoreUploadLogFileOpt;
import com.tencent.IMCoreWrapper;
import com.tencent.IMErrInfo;
import com.tencent.IMFunc;
import com.tencent.TIMCallBack;
import com.tencent.TIMLogLevel;
import com.tencent.TIMLogListener;
import com.tencent.TIMManager;
import com.tencent.TIMNetworkStatus;
import com.tencent.TIMOfflinePushSettings;
import com.tencent.TIMOfflinePushToken;
import com.tencent.TIMUser;
import com.tencent.TIMValueCallBack;
import com.tencent.av.sdk.AVError;
import com.tencent.imcore.IMCore;
import com.tencent.imcore.IMCoreUser;
import com.tencent.imcore.QrEventType;
import com.tencent.imsdk.util.QualityReportHelper;
import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.mobileqq.pb.PBUInt32Field;
import com.tencent.openqq.IMCmdListener;
import com.tencent.openqq.IMPushListener;
import com.tencent.openqq.protocol.imsdk.log_report.ReqBody;
import com.tencent.openqq.protocol.imsdk.stat_get_pushsound;
import com.tencent.openqq.protocol.imsdk.stat_set_pushsound;
import com.tencent.openqq.protocol.imsdk.stat_settoken;
import com.tencent.qalsdk.QALCallBack;
import com.tencent.qalsdk.QALSDKManager;
import com.tencent.qalsdk.QALValueCallBack;
import com.tencent.statistics.BeaconUtil;
import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import tencent.tls.platform.TLSExchangeTicketListener;
import tencent.tls.platform.TLSHelper;
import tencent.tls.platform.TLSLoginHelper;

public class IMMsfCoreProxy {
    private static String backupStorePath = (Environment.getExternalStorageDirectory().getPath() + "/imsdk/files");
    static IMMsfCoreProxy coreProxy = new IMMsfCoreProxy();
    public static Handler mainHandler = new Handler(Looper.getMainLooper());
    private static final String tag = "imsdk.IMMsfCoreProxy";
    private String accountType = "0";
    private boolean buglyDebugMode = true;
    private boolean buglyInited = false;
    private Context context = null;
    private long count = 0;
    private int env = 0;
    public volatile boolean inited = false;
    private TIMLogLevel logCbLevel = TIMLogLevel.WARN;
    private TIMLogListener logListener = null;
    private int mode = 1;
    public AtomicInteger msgSeq = new AtomicInteger(1000);
    private ConcurrentHashMap<String, IMMsfUserInfo> mutiUserMap = new ConcurrentHashMap();
    private TIMNetworkStatus networkStatus = TIMNetworkStatus.TIM_NETWORK_STATUS_DISCONNECTED;
    private boolean noCrashReport = false;
    private IMMsfUserInfo onlineMsfUser = null;
    private ExecutorService pool = Executors.newFixedThreadPool(10);
    public Random random = new Random();
    private long reqTimeout = 0;
    private int sdkAppId = 0;
    private String sdkType = "openim";
    private long ttotal = 0;

    private IMMsfCoreProxy() {
    }

    public static void errorOnMainThread(TIMCallBack tIMCallBack, int i, String str) {
        mainHandler.post(new ab(tIMCallBack, i, str));
    }

    public static IMMsfCoreProxy get() {
        return coreProxy;
    }

    private void initBugly(TIMUser tIMUser) {
        if (!this.buglyInited && !this.noCrashReport) {
            try {
                Class cls = Class.forName("com.tencent.bugly.imsdk.crashreport.CrashReport");
                Method method = cls.getMethod("setSdkExtraData", new Class[]{Context.class, String.class, String.class});
                if (method != null) {
                    method.invoke(null, new Object[]{this.context, BaseConstants.BUGLY_APP_ID, TIMManager.getInstance().getVersion()});
                }
                method = cls.getMethod("initCrashReport", new Class[]{Context.class, String.class, Boolean.TYPE});
                if (method != null) {
                    method.invoke(null, new Object[]{this.context, String.valueOf(this.sdkAppId), Boolean.valueOf(this.buglyDebugMode)});
                    this.buglyInited = true;
                    QLog.i(tag, 1, "initIMCore imsdk bugly succ");
                }
                Method method2 = cls.getMethod("setUserId", new Class[]{String.class});
                if (method2 != null) {
                    method2.invoke(null, new Object[]{String.valueOf(this.sdkAppId) + "_" + tIMUser.getAccountType() + "_" + tIMUser.getIdentifier()});
                }
            } catch (Throwable th) {
                QLog.e(tag, 1, "enable crashreport failed|" + th.getLocalizedMessage());
            }
        }
    }

    private void logReport(String str, String str2, int i, String str3) {
        if (!TextUtils.isEmpty(str2)) {
            ReqBody reqBody = new ReqBody();
            reqBody.bytes_log_id.set(ByteStringMicro.copyFromUtf8(str2));
            if (i != 0) {
                reqBody.uint32_err_code.set(i);
                reqBody.bytes_err_msg.set(ByteStringMicro.copyFromUtf8(str3));
            }
            request(str, "open_logs.report", reqBody.toByteArray(), new ag(this));
        }
    }

    private void saveOfflinePushSettingsToLocal(String str, TIMOfflinePushSettings tIMOfflinePushSettings) {
        if (this.context == null || tIMOfflinePushSettings == null) {
            QLog.e(tag, 1, "save offlinePushSettings failed");
            return;
        }
        Editor edit = this.context.getSharedPreferences(BaseConstants.OFFLINE_PUSH_SETTINGS_FILE, 0).edit();
        edit.putBoolean(str + BaseConstants.OFFLINE_IS_ENABLED_TAG, tIMOfflinePushSettings.isEnabled());
        edit.putString(str + BaseConstants.OFFLINE_C2C_SOUND_TAG, tIMOfflinePushSettings.getC2cMsgRemindSound() != null ? tIMOfflinePushSettings.getC2cMsgRemindSound().toString() : "");
        edit.putString(str + BaseConstants.OFFLINE_GRP_SOUND_TAG, tIMOfflinePushSettings.getGroupMsgRemindSound() != null ? tIMOfflinePushSettings.getGroupMsgRemindSound().toString() : "");
        edit.apply();
    }

    public void disableCrashReport() {
        this.noCrashReport = true;
    }

    public void downloadToBuff(List<String> list, TIMValueCallBack<byte[]> tIMValueCallBack, QualityReportHelper qualityReportHelper) {
        IMErrInfo iMErrInfo;
        if (list == null || list.isEmpty()) {
            iMErrInfo = new IMErrInfo(BaseConstants.ERR_INVALID_SDK_OBJECT, "urls is empty");
            tIMValueCallBack.onError(iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.init(iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.report();
        } else if (IMCoreWrapper.get().isReady()) {
            new Thread(new an(this, list, new Handler(Looper.getMainLooper()), tIMValueCallBack, qualityReportHelper)).start();
        } else {
            iMErrInfo = new IMErrInfo(6013, "sdk not initialized or not logged in.");
            tIMValueCallBack.onError(iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.init(iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.report();
        }
    }

    public void downloadToFile(List<String> list, String str, TIMCallBack tIMCallBack, QualityReportHelper qualityReportHelper) {
        IMErrInfo iMErrInfo;
        if (list == null || list.isEmpty()) {
            iMErrInfo = new IMErrInfo(BaseConstants.ERR_INVALID_SDK_OBJECT, "urls is empty");
            tIMCallBack.onError(iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.init(iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.report();
        } else if (TextUtils.isEmpty(str)) {
            iMErrInfo = new IMErrInfo(6017, "invalid path");
            tIMCallBack.onError(iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.init(iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.report();
        } else if (IMCoreWrapper.get().isReady()) {
            new Thread(new ai(this, list, tIMCallBack, qualityReportHelper, str)).start();
        } else {
            iMErrInfo = new IMErrInfo(6013, "sdk not initialized or not logged in.");
            tIMCallBack.onError(iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.init(iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.report();
        }
    }

    public void enableBuglyDebugMode(boolean z) {
        this.buglyDebugMode = z;
    }

    public IMMsfUserInfo getAnyOnLineMsfUserInfo() {
        if (this.onlineMsfUser != null && this.onlineMsfUser.isLoggedIn()) {
            return this.onlineMsfUser;
        }
        for (Entry value : this.mutiUserMap.entrySet()) {
            IMMsfUserInfo iMMsfUserInfo = (IMMsfUserInfo) value.getValue();
            if (iMMsfUserInfo.isLoggedIn()) {
                this.onlineMsfUser = iMMsfUserInfo;
                return this.onlineMsfUser;
            }
        }
        return this.onlineMsfUser;
    }

    public Context getContext() {
        return this.context;
    }

    public int getEnv() {
        return QALSDKManager.getInstance().getServerEnv();
    }

    public int getMode() {
        return this.mode;
    }

    public IMMsfUserInfo getMsfUserInfo(String str) {
        Object identification;
        if (TextUtils.isEmpty(str)) {
            identification = TIMManager.getInstance().getIdentification();
            QLog.w(tag, 1, "IMMsfCoreProxy|getMsfUserInfo empty, take identifer id:" + identification);
        }
        return this.mutiUserMap.containsKey(identification) ? (IMMsfUserInfo) this.mutiUserMap.get(identification) : null;
    }

    public TIMNetworkStatus getNetworkStatus() {
        return this.networkStatus;
    }

    public void getOfflinePushSettings(String str, TIMValueCallBack<TIMOfflinePushSettings> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (isLoggedIn(str)) {
                stat_get_pushsound.ReqBody reqBody = new stat_get_pushsound.ReqBody();
                reqBody.uint32_platform.set(2);
                reqBody.uint64_tinyid.set(get().getMsfUserInfo(str).getTinyid());
                get().request(str, "im_open_status.stat_get_pushsound", reqBody.toByteArray(), new af(this, tIMValueCallBack, str));
                return;
            }
            QLog.e(tag, 1, "getOfflinePushSettings failed, user not online: " + str);
            tIMValueCallBack.onError(6014, "current user not login. id: " + str);
        }
    }

    public boolean getOfflinePushSettingsFromLocal(Context context, String str, TIMOfflinePushSettings tIMOfflinePushSettings) {
        if (tIMOfflinePushSettings == null || context == null) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(BaseConstants.OFFLINE_PUSH_SETTINGS_FILE, 0);
        if (!sharedPreferences.contains(str + BaseConstants.OFFLINE_IS_ENABLED_TAG)) {
            return false;
        }
        tIMOfflinePushSettings.setEnabled(sharedPreferences.getBoolean(str + BaseConstants.OFFLINE_IS_ENABLED_TAG, false));
        Object string = sharedPreferences.getString(str + BaseConstants.OFFLINE_C2C_SOUND_TAG, "");
        if (!TextUtils.isEmpty(string)) {
            tIMOfflinePushSettings.setC2cMsgRemindSound(Uri.parse(string));
        }
        string = sharedPreferences.getString(str + BaseConstants.OFFLINE_GRP_SOUND_TAG, "");
        if (!TextUtils.isEmpty(string)) {
            tIMOfflinePushSettings.setGroupMsgRemindSound(Uri.parse(string));
        }
        return true;
    }

    public long getReqTimeout() {
        return this.reqTimeout;
    }

    public synchronized String getSaveRootPath() {
        String str;
        try {
            File filesDir = this.context.getFilesDir();
            if (filesDir == null) {
                filesDir = this.context.getCacheDir();
                if (filesDir == null) {
                    QLog.d(tag, 1, "load cache dir is null");
                    str = null;
                } else {
                    str = filesDir.getAbsolutePath();
                    int lastIndexOf = str.lastIndexOf(47);
                    if (lastIndexOf != -1) {
                        str = str.substring(0, lastIndexOf) + "/files/";
                    }
                }
            } else {
                str = filesDir.getAbsolutePath();
            }
            File file;
            if (str == null) {
                str = backupStorePath;
                file = new File(str);
                if (!(file.exists() || file.mkdirs())) {
                    QLog.d(tag, 1, "create backupStore folder failed");
                }
            } else {
                file = new File(str);
                if (!(file.exists() || file.mkdirs())) {
                    QLog.d(tag, 1, "create backupStore folder failed");
                }
                if (!(file.exists() && file.canWrite())) {
                    str = backupStorePath;
                    if (!new File(str).mkdirs()) {
                        QLog.d(tag, 1, "create backupStore folder failed");
                    }
                }
            }
            QLog.d(tag, 1, "load save root dir is " + str);
        } catch (Throwable th) {
            QLog.d(tag, 1, "getSaveRootPath error ", th);
            str = null;
        }
        return str;
    }

    public int getSdkAppId() {
        return this.sdkAppId;
    }

    public String getSdkType() {
        return this.sdkType;
    }

    public String getUidType() {
        return this.accountType;
    }

    public ConcurrentHashMap<String, TIMValueCallBack<byte[]>> getUserPushCallBack(String str) {
        return this.mutiUserMap.containsKey(str) ? ((IMMsfUserInfo) this.mutiUserMap.get(str)).getCmd2PushCallBack() : null;
    }

    public ConcurrentHashMap<String, IMPushListener> getUserPushListener(String str) {
        return this.mutiUserMap.containsKey(str) ? ((IMMsfUserInfo) this.mutiUserMap.get(str)).getCmd2PushListener() : null;
    }

    public synchronized boolean init(Context context) {
        boolean z = false;
        synchronized (this) {
            IMCoreWrapper.get().initLogPath(context);
            QLog.i(tag, 1, "try initIMCore msfCoreProxy|appid: " + QALSDKManager.getInstance().getQalAppId());
            if (!this.inited) {
                QLog.i(tag, 1, "initIMCore msfCoreProxy...");
                this.context = context.getApplicationContext();
                if (get().getMode() == 1) {
                    try {
                        if (IMCoreWrapper.get().loadLib()) {
                            if (VERSION.SDK_INT >= 23 && context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                                QLog.d(tag, 1, "no WRITE_EXTERNAL_STORAGE permission granted, won't log to file");
                                IMCoreWrapper.get().setLogLevel(TIMLogLevel.OFF);
                            }
                            IMCoreWrapper.get().initLog(context, this.logCbLevel, this.logListener);
                            IMCoreWrapper.get().initPicCachePath(context);
                            IMCoreWrapper.get().initVideoCachePath(context);
                            IMCoreWrapper.get().initFileCachePath(context);
                        }
                    } catch (Throwable th) {
                        setMode(0);
                        QLog.e(tag, 1, "setMode 0\n" + th.getLocalizedMessage());
                    }
                } else {
                    LogManager.init();
                    QLog.init(context);
                }
                initQalSdk();
                this.inited = true;
                BeaconUtil.Init(context);
                QLog.i(tag, 1, "initIMCore msfCoreProxy done: " + this);
                z = true;
            }
        }
        return z;
    }

    public synchronized boolean init(Context context, int i) {
        this.sdkAppId = i;
        return init(context);
    }

    public synchronized boolean init(Context context, int i, String str) {
        this.sdkAppId = i;
        this.accountType = str;
        return init(context);
    }

    public void initOfflinePushSettings(String str, TIMOfflinePushSettings tIMOfflinePushSettings) {
        int i = 2;
        if (!isLoggedIn(str)) {
            QLog.e(tag, 1, "initOfflinePushSettings failed, user not online");
        } else if (tIMOfflinePushSettings == null) {
            QLog.e(tag, 1, "initOfflinePushSettings failed, invalid param");
        } else {
            stat_set_pushsound.ReqBody reqBody = new stat_set_pushsound.ReqBody();
            reqBody.uint32_platform.set(2);
            reqBody.msg_config.setHasFlag(true);
            PBUInt32Field pBUInt32Field = reqBody.msg_config.uint32_openpush;
            if (tIMOfflinePushSettings.isEnabled()) {
                i = 1;
            }
            pBUInt32Field.set(i);
            try {
                String str2 = "";
                if (tIMOfflinePushSettings.getC2cMsgRemindSound() != null) {
                    str2 = tIMOfflinePushSettings.getC2cMsgRemindSound().toString();
                }
                reqBody.msg_config.bytes_c2c_sound.set(ByteStringMicro.copyFrom(str2.getBytes("utf-8")));
                str2 = "";
                if (tIMOfflinePushSettings.getGroupMsgRemindSound() != null) {
                    str2 = tIMOfflinePushSettings.getGroupMsgRemindSound().toString();
                }
                reqBody.msg_config.bytes_grp_sound.set(ByteStringMicro.copyFrom(str2.getBytes("utf-8")));
                request(str, "im_open_status.stat_set_pushsound", reqBody.toByteArray(), new ae(this, str, tIMOfflinePushSettings));
            } catch (Throwable th) {
                QLog.e(tag, 1, "initOfflinePushSettings failed, form req failed: " + th.getLocalizedMessage());
            }
        }
    }

    public void initQalSdk() {
        QALSDKManager.getInstance().setTIMLogListener(new as(this));
        QALSDKManager.getInstance().setConnectionListener(new at(this));
        QALSDKManager.getInstance().setUserStatusListener(new ay(this));
        QALSDKManager.getInstance().addPushListener("im_open_push.msg_push", new ba(this));
        QALSDKManager.getInstance().setNoGuestMode();
        QALSDKManager.getInstance().init(this.context, this.sdkAppId);
    }

    public boolean isLoggedIn(String str) {
        return this.mutiUserMap.containsKey(str) ? ((IMMsfUserInfo) this.mutiUserMap.get(str)).isLoggedIn() : false;
    }

    public boolean isOfflinePushEnabled(Context context, String str) {
        if (context == null) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(BaseConstants.OFFLINE_PUSH_SETTINGS_FILE, 0);
        return sharedPreferences.contains(new StringBuilder().append(str).append(BaseConstants.OFFLINE_IS_ENABLED_TAG).toString()) ? sharedPreferences.getBoolean(str + BaseConstants.OFFLINE_IS_ENABLED_TAG, false) : true;
    }

    public void logBugly(TIMLogLevel tIMLogLevel, String str, String str2) {
        if (!this.noCrashReport) {
            try {
                String str3;
                if (tIMLogLevel == TIMLogLevel.DEBUG) {
                    str3 = "d";
                } else if (tIMLogLevel == TIMLogLevel.INFO) {
                    str3 = "i";
                } else if (tIMLogLevel == TIMLogLevel.WARN) {
                    str3 = "w";
                } else if (tIMLogLevel == TIMLogLevel.ERROR) {
                    str3 = "e";
                } else {
                    return;
                }
                Class cls = Class.forName("com.tencent.bugly.imsdk.crashreport.BuglyLog");
                if (cls != null) {
                    cls.getMethod(str3, new Class[]{String.class, String.class}).invoke(null, new Object[]{str, str2});
                }
            } catch (Throwable th) {
                QLog.e(str, 1, "logBugly failed|" + th.getLocalizedMessage());
            }
        }
    }

    public void login(int i, TIMUser tIMUser, String str, TIMCallBack tIMCallBack) {
        if (tIMUser == null) {
            errorOnMainThread(tIMCallBack, 6017, "invalid TIMUser");
        } else if (tIMUser.getIdentifier() == null || tIMUser.getIdentifier().length() <= 0) {
            errorOnMainThread(tIMCallBack, 6017, "invalid Identifier");
        } else if (str == null || str.length() <= 0) {
            errorOnMainThread(tIMCallBack, 6017, "invalid userKey");
        } else {
            QualityReportHelper qualityReportHelper = new QualityReportHelper();
            QLog.i(tag, 1, "Login|1-Begin|Succ|identifer=" + tIMUser + ", sdkappid=" + i);
            QLog.i(tag, 1, "user sig: " + str);
            IMMsfUserInfo msfUserInfo = getMsfUserInfo(tIMUser.getIdentifier());
            if (msfUserInfo == null) {
                msfUserInfo = new IMMsfUserInfo();
                msfUserInfo.setUser(tIMUser);
                this.mutiUserMap.put(tIMUser.getIdentifier(), msfUserInfo);
            }
            this.sdkAppId = i;
            this.accountType = tIMUser.getAccountType();
            QALSDKManager.getInstance().setSDKAppID(i);
            initBugly(tIMUser);
            if (QALSDKManager.getInstance().inited) {
                QALCallBack biVar = new bi(this, tIMUser, tIMCallBack, qualityReportHelper, msfUserInfo);
                boolean needLogin = TLSLoginHelper.getInstance().needLogin(tIMUser.getIdentifier());
                QLog.e(tag, 1, "needLogin: " + needLogin);
                if (needLogin) {
                    TLSExchangeTicketListener bkVar = new bk(this, tIMUser, msfUserInfo, biVar, tIMCallBack, qualityReportHelper);
                    int i2 = 0;
                    try {
                        i2 = Integer.valueOf(this.accountType).intValue();
                    } catch (Exception e) {
                        QLog.e(tag, 1, "invalid accountType: " + this.accountType);
                    }
                    if (i2 <= 0 || i2 >= 100) {
                        TLSLoginHelper.getInstance().TLSExchangeTicket((long) i, tIMUser.getIdentifier(), str, bkVar);
                        return;
                    }
                    QLog.i(tag, 1, "3rd login:" + this.accountType + ":" + tIMUser.getAppIdAt3rd() + ":" + tIMUser.getIdentifier() + ":" + str);
                    TLSHelper.getInstance().setOpenAccountInfo(i2, tIMUser.getAppIdAt3rd(), tIMUser.getIdentifier(), str);
                    TLSHelper.getInstance().TLSOpenAccountLogin(bkVar);
                    return;
                }
                QLog.e(tag, 1, "tkt exist");
                try {
                    msfUserInfo.setTinyid(((Long) TLSLoginHelper.getInstance().getSSOTicket(tIMUser.getIdentifier()).get("tinyID")).longValue());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                QALSDKManager.getInstance().bindID(tIMUser.getIdentifier(), biVar);
                return;
            }
            IMErrInfo iMErrInfo = new IMErrInfo(6013, "qalsdk not initialized.");
            errorOnMainThread(tIMCallBack, iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.init(QrEventType.kEventLogin.swigValue(), iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.report();
        }
    }

    public void login(String str, String str2, TIMCallBack tIMCallBack) {
        if (this.sdkAppId == 0) {
            errorOnMainThread(tIMCallBack, 6013, "please initIMCore SDK with sdkAppId!!!");
            return;
        }
        TIMUser tIMUser = new TIMUser();
        tIMUser.setIdentifier(str);
        tIMUser.setAccountType("0");
        tIMUser.setAppIdAt3rd("0");
        login(this.sdkAppId, tIMUser, str2, tIMCallBack);
    }

    public void logout(String str) {
        logout(str, null);
    }

    public void logout(String str, TIMCallBack tIMCallBack) {
        QLog.i(tag, 1, "Logout|1-Begin|Succ, identifier: " + str);
        if (str != null) {
            QALSDKManager.getInstance().unBindID(str, new ac(this, tIMCallBack));
        }
        IMMsfUserInfo msfUserInfo = getMsfUserInfo(str);
        if (msfUserInfo == null) {
            QLog.e(tag, 1, "user logout error user not found: " + str);
            if (tIMCallBack != null) {
                tIMCallBack.onError(6014, "current user not login. id: " + str);
                QLog.e(tag, 1, "Logout|2-Callback|Succ|user not found");
                return;
            }
            return;
        }
        TIMUser user = msfUserInfo.getUser();
        QLog.i(tag, 1, "user logout: " + user);
        if (user != null && IMCoreWrapper.get().isReady()) {
            IMCoreUser coreUser = TIMManager.getInstanceById(user.getIdentifier()).getCoreUser();
            coreUser.cancelAllPicupTask();
            coreUser.getFriendShipMgr().clearAllData();
            if (this.mode == 1) {
                IMCore.get().unInitUser(user.getIdentifier());
            }
            if (getAnyOnLineMsfUserInfo() == null) {
                IMCoreWrapper.get().setReady(false);
            }
        }
        msfUserInfo.setTinyid(0);
        msfUserInfo.setIsLoggedIn(false);
        if (tIMCallBack != null) {
            if (str == null) {
                tIMCallBack.onSuccess();
                QLog.i(tag, 1, "Logout|2-Callback|Succ|logout succ");
            }
        } else if (str == null) {
            QLog.e(tag, 1, "Logout|2-Callback|Fail|user not set logout callback");
        }
    }

    public void performanceTest(String str) {
        byte[] bArr = new byte[]{(byte) 10, (byte) -99, (byte) 1, (byte) 10, (byte) -113, (byte) 1, (byte) 10, (byte) 30, (byte) 8, (byte) -76, (byte) 4, (byte) 16, (byte) 6, (byte) 24, (byte) -16, (byte) 80, (byte) 32, (byte) -87, (byte) -58, (byte) -100, (byte) 42, (byte) 40, (byte) -4, (byte) -121, (byte) -25, (byte) -72, (byte) 5, (byte) 48, (byte) -4, (byte) -121, (byte) -25, (byte) -72, (byte) 5, (byte) 64, (byte) -58, (byte) -59, (byte) -47, (byte) 2, (byte) 18, (byte) 99, (byte) 10, (byte) 19, (byte) 26, (byte) 17, (byte) 16, (byte) -79, (byte) -49, (byte) -118, Byte.MIN_VALUE, (byte) 2, (byte) 40, (byte) -3, (byte) -89, (byte) -55, (byte) -101, (byte) 5, (byte) 90, (byte) 3, (byte) 87, (byte) 101, (byte) 98, (byte) 34, (byte) 74, (byte) 8, (byte) -86, (byte) -62, (byte) -50, (byte) -84, (byte) -90, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, (byte) 2, (byte) 16, (byte) -11, (byte) -72, (byte) -102, (byte) -97, (byte) -90, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, (byte) 2, (byte) 24, (byte) 5, (byte) 32, (byte) 5, (byte) 42, (byte) 6, (byte) -25, (byte) -117, (byte) -105, (byte) -27, (byte) -72, (byte) -90, (byte) 56, (byte) -4, (byte) -55, (byte) -36, (byte) -120, (byte) 16, (byte) 66, (byte) 3, (byte) 66, (byte) 66, (byte) 67, (byte) 98, (byte) 14, (byte) 64, (byte) 84, (byte) 71, (byte) 83, (byte) 35, (byte) 50, (byte) 52, (byte) 72, (byte) 74, (byte) 79, (byte) 82, (byte) 65, (byte) 69, (byte) 77, (byte) 104, (byte) 0, (byte) 114, JceStruct.STRUCT_END, (byte) 16, (byte) -56, (byte) 1, (byte) 50, (byte) 6, (byte) 120, (byte) 107, (byte) 97, (byte) 122, (byte) 101, (byte) 114, (byte) 58, (byte) 0, (byte) 34, (byte) 8, (byte) 34, (byte) 2, (byte) 8, (byte) 0, (byte) 40, (byte) 0, (byte) 80, (byte) 2, (byte) 18, (byte) 9, (byte) 10, (byte) 7, (byte) 18, (byte) 5, (byte) 10, (byte) 3, (byte) 10, (byte) 1, (byte) 51, (byte) 18, (byte) 21, JceStruct.SIMPLE_LIST, (byte) 10, (byte) -21, (byte) 8, (byte) 25, (byte) 21, (byte) 52, (byte) -127, (byte) 0, (byte) 0, (byte) 24, (byte) -44, (byte) -1, (byte) -53, (byte) -43, (byte) -90, (byte) -95, (byte) -4, (byte) -71, (byte) -88, (byte) 1};
        long currentTimeMillis = System.currentTimeMillis();
        this.count++;
        int i = 0;
        while (i < 100) {
            IMCore.get().getUser(str).manualPush(bArr);
            i++;
        }
        currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
        Log.e("XIAO", "totoal cost: " + currentTimeMillis + "ms/" + i);
        Log.e("XIAO", "level: " + (currentTimeMillis / ((long) i)) + "mpm");
        this.ttotal = (currentTimeMillis / ((long) i)) + this.ttotal;
        Log.e("XIAO", "ave: " + (this.ttotal / this.count) + "mpm/" + this.count);
    }

    public void request(String str, String str2, byte[] bArr, TIMValueCallBack<byte[]> tIMValueCallBack) {
        request(str, str2, bArr, tIMValueCallBack, this.reqTimeout);
    }

    public void request(String str, String str2, byte[] bArr, TIMValueCallBack<byte[]> tIMValueCallBack, long j) {
        QALValueCallBack qALValueCallBack = null;
        if (tIMValueCallBack != null) {
            qALValueCallBack = new bf(this, tIMValueCallBack, str2);
        }
        String identification = TextUtils.isEmpty(str) ? TIMManager.getInstance().getIdentification() : str;
        QLog.d(tag, 1, "request user: " + str + "|cmd:" + str2 + "|req:" + IMFunc.byte2hex(bArr));
        QALSDKManager.getInstance().sendMsg(identification, str2, bArr, j, qALValueCallBack);
    }

    public void request(String str, String str2, byte[] bArr, IMCmdListener iMCmdListener) {
        TIMValueCallBack tIMValueCallBack = null;
        if (iMCmdListener != null) {
            tIMValueCallBack = new bh(this, iMCmdListener);
        }
        request(str, str2, bArr, tIMValueCallBack);
    }

    public void setAvSDKVersionToBugly(String str, String str2) {
        if (!this.noCrashReport) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                QLog.e(tag, 1, "setAvSDKVersionToBugly failed| invalid param");
                return;
            }
            try {
                Method method = Class.forName("com.tencent.bugly.imsdk.crashreport.CrashReport").getMethod("setSdkExtraData", new Class[]{Context.class, String.class, String.class});
                if (method != null) {
                    method.invoke(null, new Object[]{this.context, str, str2});
                }
            } catch (Throwable th) {
                QLog.e(tag, 1, "setAvSDKVersionToBugly failed|" + th.getLocalizedMessage());
            }
        }
    }

    public void setEnv(int i) {
        this.env = i;
        QALSDKManager.getInstance().setEnv(i);
    }

    public void setMode(int i) {
        this.mode = i;
    }

    public void setOfflinePushToken(String str, TIMOfflinePushToken tIMOfflinePushToken, TIMCallBack tIMCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper(QrEventType.kEventSetToken.swigValue());
        IMErrInfo iMErrInfo;
        if (!isLoggedIn(str)) {
            iMErrInfo = new IMErrInfo(6014, "current user not login. id: " + str);
            QLog.e(tag, 1, iMErrInfo.getMsg());
            qualityReportHelper.init(iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.report();
            if (tIMCallBack != null) {
                tIMCallBack.onError(iMErrInfo.getCode(), iMErrInfo.getMsg());
            }
        } else if (tIMOfflinePushToken == null || TextUtils.isEmpty(tIMOfflinePushToken.getToken()) || tIMOfflinePushToken.getBussid() == 0) {
            iMErrInfo = new IMErrInfo(6017, "setToken failed, busisid=0 or token is empty");
            QLog.e(tag, 1, iMErrInfo.getMsg());
            qualityReportHelper.init(iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.report();
            if (tIMCallBack != null) {
                tIMCallBack.onError(iMErrInfo.getCode(), iMErrInfo.getMsg());
            }
        } else {
            QLog.i(tag, 1, "setToken, token: " + tIMOfflinePushToken.getToken() + "|bussid: " + tIMOfflinePushToken.getBussid() + "|vendor: " + Build.MANUFACTURER);
            String str2 = Build.MANUFACTURER;
            int i = str2.toLowerCase(Locale.ENGLISH).contains("xiaomi") ? APPluginErrorCode.ERROR_APP_SYSTEM : str2.toLowerCase(Locale.ENGLISH).contains("huawei") ? 2001 : AVError.AV_ERR_TRY_NEW_ROOM_FAILED;
            stat_settoken.ReqBody reqBody = new stat_settoken.ReqBody();
            reqBody.uint32_busiid.set((int) tIMOfflinePushToken.getBussid());
            try {
                reqBody.bytes_token_id.set(ByteStringMicro.copyFrom(tIMOfflinePushToken.getToken().getBytes("utf-8")));
                reqBody.uint32_inst_type.set(i);
                reqBody.uint32_enter_version.set(getSdkAppId());
                reqBody.uint32_push_msg.set(1);
                request(str, "im_open_status.stat_settoken", reqBody.toByteArray(), new ad(this, tIMCallBack, qualityReportHelper));
            } catch (Throwable th) {
                iMErrInfo = new IMErrInfo(6002, "setToken failed, req serialize failed");
                QLog.e(tag, 1, iMErrInfo.getMsg());
                qualityReportHelper.init(iMErrInfo.getCode(), iMErrInfo.getMsg());
                qualityReportHelper.report();
                QLog.e(tag, 1, iMErrInfo.getMsg());
                if (tIMCallBack != null) {
                    tIMCallBack.onError(iMErrInfo.getCode(), iMErrInfo.getMsg());
                }
            }
        }
    }

    public void setReqTimeout(long j) {
        this.reqTimeout = j;
    }

    public void setSdkType(String str) {
        this.sdkType = str;
    }

    public void setTIMLogCbLevel(TIMLogLevel tIMLogLevel) {
        this.logCbLevel = tIMLogLevel;
    }

    public void setTIMLogListener(TIMLogListener tIMLogListener) {
        this.logListener = tIMLogListener;
        QLog.setSdkLogListener(tIMLogListener);
    }

    public void stopQALService() {
        QALSDKManager.getInstance().stopQALService();
    }

    public boolean uploadLogFile(String str, IMCoreUploadLogFileOpt iMCoreUploadLogFileOpt) {
        IMMsfUserInfo msfUserInfo = get().getMsfUserInfo(str);
        if (msfUserInfo == null) {
            QLog.e(tag, 1, "uploadLogFile->find user failed: " + str);
            return false;
        }
        String logId = iMCoreUploadLogFileOpt.getLogId();
        String filePath = iMCoreUploadLogFileOpt.getFilePath();
        String tag = iMCoreUploadLogFileOpt.getTag();
        Object relativePath = iMCoreUploadLogFileOpt.getRelativePath();
        long logSize = iMCoreUploadLogFileOpt.getLogSize() * BaseConstants.MEGA;
        if (logSize == 0) {
            QLog.e(tag, 1, "uploadLogFile->failed: invalid parameters, request size 0");
            get().logReport(str, logId, 6017, "invalid parameters, request size 0");
            return false;
        }
        try {
            String str2;
            RandomAccessFile randomAccessFile;
            String str3 = "sdklog";
            String str4 = "8whnoADaf5USGpmmio7JATVQWrj5BaSz";
            String str5 = "AKIDIsvZoS8DRMqxDPh0wQ5B3sF6SlCINL0G";
            String str6 = "web.file.myqcloud.com/files/v1";
            String str7 = "imsdk";
            String uuid = UUID.randomUUID().toString();
            if (TextUtils.isEmpty(logId)) {
                int lastIndexOf = filePath.lastIndexOf(46);
                str2 = tag + "_" + filePath.substring(lastIndexOf - 8, lastIndexOf) + "_" + msfUserInfo.getTinyid() + ".lz4";
            } else {
                str2 = logId + ".lz4";
            }
            tag = "/" + 10002868 + "/" + str3 + "/" + str7 + "/" + str2;
            str6 = "http://" + str6 + tag;
            if (TextUtils.isEmpty(relativePath)) {
                randomAccessFile = new RandomAccessFile(filePath, "r");
            } else {
                randomAccessFile = new RandomAccessFile(Environment.getExternalStorageDirectory().getPath() + "/" + relativePath, "r");
            }
            long length = randomAccessFile.length();
            if (length > logSize) {
                randomAccessFile.seek(length - logSize);
            } else {
                logSize = (long) ((int) length);
            }
            byte[] bArr = new byte[((int) logSize)];
            randomAccessFile.read(bArr);
            randomAccessFile.close();
            bArr = IMFunc.gzCompress(new String(bArr));
            Object paramBytes = IMFunc.getParamBytes(uuid, "sha", "sha", IMFunc.calcSHA(bArr));
            Object paramBytes2 = IMFunc.getParamBytes(uuid, "biz_attr", "biz_attr", "");
            Object paramBytes3 = IMFunc.getParamBytes(uuid, "filecontent", str2, bArr);
            Object paramBytes4 = IMFunc.getParamBytes(uuid, "op", "op", "upload");
            relativePath = ("--" + uuid + "--\r\n").getBytes();
            Object obj = new byte[((((paramBytes.length + paramBytes2.length) + paramBytes3.length) + paramBytes4.length) + relativePath.length)];
            System.arraycopy(paramBytes, 0, obj, 0, paramBytes.length);
            System.arraycopy(paramBytes2, 0, obj, paramBytes.length, paramBytes2.length);
            System.arraycopy(paramBytes3, 0, obj, paramBytes.length + paramBytes2.length, paramBytes3.length);
            System.arraycopy(paramBytes4, 0, obj, (paramBytes.length + paramBytes2.length) + paramBytes3.length, paramBytes4.length);
            int i = 0;
            Object obj2 = obj;
            System.arraycopy(relativePath, i, obj2, (paramBytes3.length + (paramBytes.length + paramBytes2.length)) + paramBytes4.length, relativePath.length);
            Map hashMap = new HashMap();
            hashMap.put("Authorization", IMFunc.appSignature(10002868, str5, str4, 60, tag, str3));
            hashMap.put("Content-Type", "multipart/form-data; boundary=" + uuid);
            QLog.i(tag, 1, "uploadLogFile->request: " + str6);
            IMFunc.httpPostReq(str6, obj, hashMap, new ah(this, str2, str, logId));
            return true;
        } catch (Throwable th) {
            QLog.e(tag, 1, "uploadLogFile->error: " + IMFunc.getExceptionInfo(th));
            get().logReport(str, logId, BaseConstants.ERR_IO_OPERATION_FAILED, IMFunc.getExceptionInfo(th));
            return false;
        }
    }
}
