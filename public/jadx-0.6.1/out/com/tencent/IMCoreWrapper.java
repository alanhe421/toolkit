package com.tencent;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.tencent.av.config.ConfigBaseParser;
import com.tencent.imcore.ILogMsgCallback;
import com.tencent.imcore.IMCore;
import com.tencent.imcore.IMCoreUser;
import com.tencent.imcore.internalJNI;
import com.tencent.imsdk.BaseConstants;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.IMMsfUserInfo;
import com.tencent.imsdk.QLog;
import com.tencent.qalsdk.QALSDKManager;
import com.tencent.tesla.soload.SoLoadCore;
import java.io.File;
import java.util.Map;
import tencent.tls.platform.TLSLoginHelper;

public class IMCoreWrapper {
    static IMCoreWrapper inst = new IMCoreWrapper();
    private static final String tag = "imsdk.IMCoreWrapper";
    private String fileCachePath = "";
    private boolean isLogInited = false;
    private boolean isLogPrintEnabled = true;
    private String logFileName = "imsdk";
    private TIMLogLevel logLevel = TIMLogLevel.DEBUG;
    private String logPath = "";
    private String picCachePath = "";
    private boolean ready = false;
    private String soLibPath = "";
    private String videoCachePath = "";

    private IMCoreWrapper() {
    }

    public static IMCoreWrapper get() {
        return inst;
    }

    public String getFileCachePath() {
        return this.fileCachePath;
    }

    public String getGateWayIp() {
        String gatewayIp = QALSDKManager.getInstance().getGatewayIp();
        return gatewayIp != null ? gatewayIp : "";
    }

    public boolean getIsLogPrintEnabled() {
        return this.isLogPrintEnabled;
    }

    public String getLogFileName() {
        return this.logFileName;
    }

    public TIMLogLevel getLogLevel() {
        return this.logLevel;
    }

    public String getLogPath() {
        return this.logPath;
    }

    public String getPicCachePath() {
        return this.picCachePath;
    }

    public String getVideoCachePath() {
        return this.videoCachePath;
    }

    public void initFileCachePath(Context context) {
        String packageName;
        try {
            packageName = context.getPackageName();
        } catch (Exception e) {
            packageName = ConfigBaseParser.DEFAULT_VALUE;
        }
        this.fileCachePath = Environment.getExternalStorageDirectory().getPath() + "/tencent/imsdkfilecache/" + packageName.replace(".", "/") + "/";
        File file = new File(this.fileCachePath);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File delete : listFiles) {
                        delete.delete();
                    }
                }
            }
        } else if (!file.mkdirs()) {
            QLog.d(tag, 1, "create imsdkfilecache folder failed");
        }
    }

    public void initIMCore(Context context, String str, TIMCallBack tIMCallBack) {
        QLog.i(tag, 1, "welcome to imsdk, version: " + TIMManager.getInstanceById(str).getVersion());
        this.ready = false;
        try {
            com.tencent.imcore.Context context2 = new com.tencent.imcore.Context();
            context2.setBid((long) QALSDKManager.getInstance().getQalAppId());
            context2.setLogPath(this.logPath);
            context2.setPicCachePath(this.picCachePath);
            context2.setIsLogPrintEnabled(this.isLogPrintEnabled);
            long serverTimeDiff = TIMManager.getInstanceById(str).getServerTimeDiff();
            QLog.i(tag, 1, "servertimediff: " + serverTimeDiff);
            context2.setSvr_time_diff(serverTimeDiff);
            IMCore.get().setContext(context2);
            IMMsfUserInfo msfUserInfo = IMMsfCoreProxy.get().getMsfUserInfo(str);
            if (msfUserInfo == null) {
                QLog.e(tag, 1, "Login|4-InitIMCore|Fail| init failed, user not found:" + str);
                IMMsfCoreProxy.errorOnMainThread(tIMCallBack, 6014, "current user not login. id: " + str);
                return;
            }
            Map sSOTicket = TLSLoginHelper.getInstance().getSSOTicket(msfUserInfo.getUser().getIdentifier());
            IMCoreUserConfig userConfig = TIMManager.getInstanceById(str).getUserConfig();
            userConfig.setNotifyCallback(new IMCoreNotify(str));
            if (IMCore.get().initUser(IMMsfCoreProxy.get().getSdkAppId(), msfUserInfo.getUidType(), String.valueOf(msfUserInfo.getsUerAppId()), msfUserInfo.getUserId(), String.valueOf(msfUserInfo.getTinyid()), (byte[]) sSOTicket.get("A2"), getGateWayIp(), context.getFilesDir().toString(), userConfig.convertTo(str), new be(this, tIMCallBack, str)) == 0) {
                IMMsfCoreProxy.errorOnMainThread(tIMCallBack, BaseConstants.ERR_DATABASE_OPERATE_FAILED, "local database operation failed!");
                return;
            }
            IMCoreUser user = IMCore.get().getUser(msfUserInfo.getUserId());
            TIMManager.getInstanceById(str).setCoreUser(user);
            TIMManager.getInstanceById(str).getCoreUser().getFileTranser().setCachePath(TIMManager.getInstance().getFileCachePath());
            user.setAvInviteCallBack(new IMCoreAvInviteCallBack(str));
            user.setGroupUpdateCallback(new ar(str));
            user.setGroupTipsEventCallback(new IMCoreGroupEventCallback(str));
        } catch (Throwable th) {
            QLog.e(tag, 1, "Login|4-InitIMCore|Fail|init failed, exception: " + th.getLocalizedMessage());
            this.ready = false;
            IMMsfCoreProxy.errorOnMainThread(tIMCallBack, BaseConstants.ERR_INIT_CORE_FAIL, th.getLocalizedMessage());
        }
    }

    public void initLog(Context context, TIMLogLevel tIMLogLevel, TIMLogListener tIMLogListener) {
        ILogMsgCallback iLogMsgCallback = null;
        if (tIMLogListener != null) {
            iLogMsgCallback = new IMCoreLogMsgCallback(tIMLogListener);
        }
        IMCore.get().lOGGERINIT(this.logPath + this.logFileName, this.logLevel.getDescr(), this.isLogPrintEnabled, iLogMsgCallback);
        IMCore.get().lOGGERSETLOGCBLEVEL(tIMLogLevel.getDescr());
        QALSDKManager.getInstance().setOutputLogLevel(this.logLevel.getIntLevel());
        setLogInited();
    }

    public void initLogPath(Context context) {
        String packageName;
        try {
            packageName = context.getPackageName();
        } catch (Exception e) {
            packageName = ConfigBaseParser.DEFAULT_VALUE;
        }
        if (TextUtils.isEmpty(this.logPath)) {
            this.logPath = Environment.getExternalStorageDirectory().getPath() + "/tencent/imsdklogs/" + packageName.replace(".", "/") + "/";
        }
        File file = new File(this.logPath);
        if (!file.exists() && !file.mkdirs()) {
            QLog.d(tag, 1, "create imsdklogs folder failed");
        }
    }

    public void initLogSettings(boolean z, String str) {
        this.isLogPrintEnabled = z;
        if (str != null) {
            this.logPath = str;
            if (str.charAt(str.length() - 1) != '/') {
                this.logPath += "/";
            }
        }
    }

    public void initPicCachePath(Context context) {
        String packageName;
        try {
            packageName = context.getPackageName();
        } catch (Exception e) {
            packageName = ConfigBaseParser.DEFAULT_VALUE;
        }
        this.picCachePath = Environment.getExternalStorageDirectory().getPath() + "/tencent/imsdkpiccache/" + packageName.replace(".", "/") + "/";
        File file = new File(this.picCachePath);
        if (!file.exists() && !file.mkdirs()) {
            QLog.d(tag, 1, "create imsdkpiccache folder failed");
        }
    }

    public void initVideoCachePath(Context context) {
        String packageName;
        try {
            packageName = context.getPackageName();
        } catch (Exception e) {
            packageName = ConfigBaseParser.DEFAULT_VALUE;
        }
        this.videoCachePath = Environment.getExternalStorageDirectory().getPath() + "/tencent/imsdkvideocache/" + packageName.replace(".", "/") + "/";
        File file = new File(this.videoCachePath);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File delete : listFiles) {
                        delete.delete();
                    }
                }
            }
        } else if (!file.mkdirs()) {
            QLog.d(tag, 1, "create imsdkvideocache folder failed");
        }
    }

    public boolean isLogInited() {
        return this.isLogInited;
    }

    public boolean isLogPrintEnabled() {
        return this.isLogPrintEnabled;
    }

    public boolean isReady() {
        return this.ready;
    }

    public boolean loadLib() {
        return loadLib(false);
    }

    public boolean loadLib(boolean z) {
        QLog.i(tag, 1, "LoadLibrary|1-Begin|succ");
        try {
            if (TextUtils.isEmpty(this.soLibPath)) {
                System.loadLibrary("_imcore_jni_gyp");
                QLog.i(tag, 1, "LoadLibrary|2-Loading|system load succ");
            } else {
                System.load(this.soLibPath + "lib_imcore_jni_gyp.so");
                QLog.i(tag, 1, "LoadLibrary|2-Loading|system load succ, from [" + this.soLibPath + "]");
            }
        } catch (Throwable th) {
            QLog.e(tag, 1, "LoadLibrary|2-Loading|system load failed" + IMFunc.getExceptionInfo(th));
            long loadSo = (long) SoLoadCore.loadSo(IMMsfCoreProxy.get().getContext().getApplicationContext(), "_imcore_jni_gyp");
            if ((2 & loadSo) == 0 && (262144 & loadSo) == 0) {
                QLog.e(tag, 1, "LoadLibrary|2-Loading|manual load failed, ret = " + loadSo + "|setMode 0");
                TIMManager.getInstance().setMode(0);
                return false;
            }
            QLog.i(tag, 1, "LoadLibrary|2-Loading|manual load succ");
        }
        try {
            internalJNI.new_IGetMsgs();
            internalJNI.new_ISendMsg();
            internalJNI.new_INotify();
            internalJNI.IMCore_get();
            internalJNI.new_Context();
            internalJNI.new_IGroupNotifyCallback();
            internalJNI.new_IEnv();
            internalJNI.new_SdkReportItem();
            QLog.i(tag, 1, "LoadLibrary|3-MethodTest|succ");
            QLog.i(tag, 1, "LoadLibrary|4-End|succ");
            return true;
        } catch (Throwable th2) {
            QLog.e(tag, 1, IMFunc.getExceptionInfo(th2));
            QLog.i(tag, 1, "LoadLibrary|3-MethodTest|failed, setMode 0");
            TIMManager.getInstance().setMode(0);
            return false;
        }
    }

    public void setIsLogPrintEnabled(boolean z) {
        this.isLogPrintEnabled = z;
    }

    public void setLogInited() {
        this.isLogInited = true;
    }

    public void setLogLevel(TIMLogLevel tIMLogLevel) {
        this.logLevel = tIMLogLevel;
    }

    public void setReady(boolean z) {
        this.ready = z;
    }

    public void setSoLibPath(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.soLibPath = str;
            if (this.soLibPath.charAt(this.soLibPath.length() - 1) != '/') {
                this.soLibPath += "/";
            }
        }
    }
}
