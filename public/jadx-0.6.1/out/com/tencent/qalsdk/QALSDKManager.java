package com.tencent.qalsdk;

import android.content.Context;
import android.os.Build.VERSION;
import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.core.NetConnInfoCenter;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qalsdk.sdk.e;
import com.tencent.qalsdk.util.ALog;
import com.tencent.qalsdk.util.GuestHelper;
import com.tencent.qalsdk.util.QLog;
import java.util.concurrent.ConcurrentHashMap;
import qalsdk.an;
import tencent.tls.platform.TLSHelper;
import tencent.tls.tools.I18nMsg;

public class QALSDKManager {
    private static int LANG = I18nMsg.ZH_CN;
    private static String appVer = "1.0";
    public static boolean bConsoleLog = true;
    private static int country = 86;
    public static String gateip = null;
    static QALSDKManager inst = null;
    public static int svrport = 0;
    private static final String tag = "QALSDKManager";
    private boolean bAppSetGuestMode = false;
    private boolean bOnlyUseConn = false;
    private boolean bSupportGuestMode = true;
    private QALConnListener connListener = null;
    private Context context = null;
    public volatile boolean inited = false;
    private ConcurrentHashMap<String, QALOfflinePushListener> mCmd2OfflinePushCallBack = new ConcurrentHashMap();
    private j msfCore = j.a();
    private String processName;
    private int sdkAppId;
    private QALUserStatusListener userStatusListener = null;

    private QALSDKManager() {
    }

    public static QALSDKManager getInstance() {
        synchronized (QALSDKManager.class) {
            if (inst == null) {
                inst = new QALSDKManager();
            }
        }
        return inst;
    }

    public synchronized void setEnv(int i) {
        QLog.i(tag, "set server env:" + i);
        e.b().b(i);
    }

    public synchronized void setOutputLogLevel(int i) {
        QLog.i(tag, "setOutputLogLevel:" + i);
        e.b().a(i);
    }

    public synchronized void setNoGuestMode() {
        QLog.i(tag, "setNoGuestMode");
        if (!this.bAppSetGuestMode) {
            this.bSupportGuestMode = false;
        }
    }

    public synchronized void appSetGuestMode() {
        QLog.i(tag, "appSetGuestMode");
        this.bSupportGuestMode = true;
        this.bAppSetGuestMode = true;
    }

    public synchronized boolean getGuestMode() {
        return this.bSupportGuestMode;
    }

    public synchronized void setOnlyUseConn() {
        this.bOnlyUseConn = true;
    }

    public synchronized void setOffLinePushListener(String str, QALOfflinePushListener qALOfflinePushListener) {
        if (str != null) {
            if (str.length() != 0) {
                this.mCmd2OfflinePushCallBack.put(str, qALOfflinePushListener);
            }
        }
        QLog.e(tag, "cmd null:" + str);
    }

    synchronized ConcurrentHashMap<String, QALOfflinePushListener> getOffLinePushListener() {
        return this.mCmd2OfflinePushCallBack;
    }

    public synchronized void init(Context context, int i) {
        QLog.e(tag, 1, "init qal sdk,version:" + getSdkVersion());
        if (!this.inited) {
            this.context = context;
            QLog.init(context);
            ALog.init(context);
            this.sdkAppId = i;
            this.processName = MsfSdkUtils.getProcessName(context);
            if (this.bOnlyUseConn) {
                QLog.e(tag, 1, "set only use conn,tls not init");
            } else {
                initTLSSDK();
            }
            e.b().a(context);
            if (VERSION.SDK_INT >= 14) {
                an.a(context);
            }
            if (this.bSupportGuestMode) {
                GuestHelper.getInstance().init();
            }
            this.inited = true;
            QLog.e(tag, 1, this.processName + " init qal sdk done: ");
        }
    }

    private void initTLSSDK() {
        TLSHelper.getInstance().init(this.context, (long) this.sdkAppId).setLocalId(LANG);
        QLog.d(tag, 1, "init tls sdk done:" + this.sdkAppId);
    }

    public void setSDKAppID(int i) {
        this.sdkAppId = i;
    }

    public synchronized void setAppEnv(String str, String str2) {
        e.b().a(str, str2);
    }

    public void sendMsg(String str, String str2, byte[] bArr, QALValueCallBack qALValueCallBack) {
        if (bArr != null) {
            sendMsg(str, str2, bArr, e.b().c(bArr.length), qALValueCallBack);
            return;
        }
        sendMsg(str, str2, bArr, a.ap, qALValueCallBack);
    }

    public void sendMsg(String str, String str2, byte[] bArr, long j, QALValueCallBack qALValueCallBack) {
        String str3;
        QLog.d(tag, "sendmsg:" + str + ":" + str2 + ":" + j);
        if (str == null || str.length() == 0) {
            str3 = "0";
        } else {
            str3 = str;
        }
        if (this.inited) {
            long j2;
            if (j < 2000 || j > 120000) {
                j2 = a.ap;
            } else {
                j2 = j;
            }
            e.b().a(str3, str2, bArr, qALValueCallBack, j2);
            return;
        }
        QLog.e(tag, "qalsdk should init first!");
    }

    public synchronized QALUserStatusListener getUserStatusListener() {
        return this.userStatusListener;
    }

    public synchronized void setUserStatusListener(QALUserStatusListener qALUserStatusListener) {
        this.userStatusListener = qALUserStatusListener;
    }

    public synchronized void setConnectionListener(QALConnListener qALConnListener) {
        this.connListener = qALConnListener;
    }

    public synchronized QALConnListener getConnectionListener() {
        return this.connListener;
    }

    public Context getContext() {
        return this.context;
    }

    public void bindID(String str, QALCallBack qALCallBack) {
        QLog.i(tag, "begin bindID:" + str);
        e.b().b(str, qALCallBack);
    }

    public void unBindID(String str, QALCallBack qALCallBack) {
        QLog.i(tag, "unBindID:" + str);
        if (!e.b().h().containsKey(str)) {
            qALCallBack.onError(-2, "user is not registerd");
            QLog.e(tag, "user is not registerd");
        } else if (str.equals(e.b().e())) {
            qALCallBack.onError(-2, "guest user can't unbind");
            QLog.e(tag, "guest user can't unbind");
        } else {
            e.b().a(str, qALCallBack);
        }
    }

    public void addPushListener(String str, QALPushListener qALPushListener) {
        if (str == null || str.length() == 0) {
            QLog.e(tag, "cmd null:" + str);
            return;
        }
        QLog.i(tag, 1, "setPushCallBack: " + str);
        e.b().a(str, qALPushListener);
    }

    public void setTIMLogListener(QALLogListener qALLogListener) {
        QLog.setSdkLogListener(qALLogListener);
    }

    public void setReqTimeout(long j) {
        e.b().a(j);
    }

    public void login(String str, String str2, String str3, QALCallBack qALCallBack) {
        if (TLSHelper.getInstance() == null) {
            QLog.e(tag, 1, "tls login null");
            return;
        }
        TLSHelper.getInstance().TLSExchangeTicket((long) this.sdkAppId, str, str3, new b(this, str, qALCallBack));
    }

    public int getSdkAppId() {
        return this.sdkAppId;
    }

    public int getQalAppId() {
        return a.bm;
    }

    public String getGuestIdentifier() {
        return e.b().e();
    }

    public String getSdkVersion() {
        return "1.4.1.104461";
    }

    public String getGatewayIp() {
        return e.b().j();
    }

    public long getServetTimeSecondInterv() {
        return NetConnInfoCenter.servetTimeSecondInterv;
    }

    public void setAppQuit() {
        e.b().o();
    }

    public int getServerEnv() {
        return e.b().d();
    }

    public void stopQALService() {
        e.b().r();
    }
}
