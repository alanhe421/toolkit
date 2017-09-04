package com.tencent.qalsdk.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.dynamicload.Lib.DLConstants;
import com.tencent.mobileqq.pb.InvalidProtocolBufferMicroException;
import com.tencent.qalsdk.QALCallBack;
import com.tencent.qalsdk.QALInitHelper;
import com.tencent.qalsdk.QALPushListener;
import com.tencent.qalsdk.QALSDKManager;
import com.tencent.qalsdk.QALUserStatusListener;
import com.tencent.qalsdk.QALValueCallBack;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.IBaseActionListener;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.core.l;
import com.tencent.qalsdk.core.m;
import com.tencent.qalsdk.core.o;
import com.tencent.qalsdk.im_open.stat_reg.RspBody;
import com.tencent.qalsdk.util.GuestHelper;
import com.tencent.qalsdk.util.QLog;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import oicq.wlogin_sdk.tools.util;
import qalsdk.am;
import qalsdk.aw;
import qalsdk.ax;
import tencent.tls.platform.TLSLoginHelper;

/* compiled from: CoreWrapper */
public class e {
    private static String D = null;
    private static String E = null;
    private static String F = null;
    private static int G = 0;
    private static e J = new e();
    private static Handler K = new Handler(Looper.getMainLooper());
    static final String b = "appTimeoutReq";
    static ac e = ac.a();
    public static final String f = "com.tencent.qalsdk.service.QalService";
    private static final String l = "CoreWrapper";
    private int A = 1;
    private int B = -1;
    private int C = 0;
    private ConcurrentHashMap<String, QALPushListener> H = new ConcurrentHashMap();
    private long I = com.tencent.qalsdk.base.a.ap;
    private com.tencent.qalsdk.core.a L;
    private boolean M = false;
    AtomicInteger a = new AtomicInteger();
    public volatile boolean c = false;
    volatile boolean d = false;
    y g;
    public Random h = new Random();
    public AtomicInteger i = new AtomicInteger(1000);
    l j;
    aw k = new n(this);
    private String m;
    private ConcurrentHashMap<String, String> n = new ConcurrentHashMap();
    private ConcurrentHashMap<String, String> o = new ConcurrentHashMap();
    private ConcurrentHashMap<String, Integer> p = new ConcurrentHashMap();
    private String q = null;
    private String r = null;
    private String s = null;
    private String t = "openim";
    private volatile boolean u = false;
    private volatile boolean v = false;
    private volatile boolean w = false;
    private String x = null;
    private String y = null;
    private Context z = null;

    /* compiled from: CoreWrapper */
    public enum a {
        UNREGISTER,
        REGISTING,
        REGISTERD;

        public static a a(String str) {
            return (a) Enum.valueOf(a.class, str);
        }

        public static a[] a() {
            return (a[]) d.clone();
        }
    }

    public Context a() {
        return this.z;
    }

    private e() {
    }

    public static e b() {
        return J;
    }

    public synchronized long c() {
        return this.I;
    }

    public synchronized int d() {
        return this.B;
    }

    public synchronized void a(String str) {
        this.q = str;
    }

    public synchronized String e() {
        return this.q;
    }

    public synchronized String f() {
        return this.r;
    }

    public synchronized void b(String str) {
        this.s = str;
    }

    public synchronized String g() {
        return this.s;
    }

    public synchronized ConcurrentHashMap<String, String> h() {
        return this.n;
    }

    public synchronized void a(long j) {
        this.I = j;
    }

    public synchronized String i() {
        return D;
    }

    public synchronized String j() {
        return F;
    }

    public synchronized String k() {
        return E;
    }

    public synchronized int l() {
        return G;
    }

    public synchronized void a(String str, String str2, String str3, int i) {
        D = str;
        F = str2;
        E = str3;
        G = i;
    }

    public synchronized void a(String str, QALPushListener qALPushListener) {
        Log.d(l, "add push" + str);
        this.H.put(str, qALPushListener);
    }

    public synchronized void c(String str) {
        this.t = str;
    }

    public synchronized String m() {
        return this.t;
    }

    private String d(String str) {
        return (String) this.o.get(str);
    }

    private boolean b(FromServiceMsg fromServiceMsg) {
        try {
            String serviceCmd = fromServiceMsg.getServiceCmd();
            QLog.d(l, 1, "[CoreWrapper handlePushMsg] recv push cmd: " + serviceCmd + ":msf cmd:" + fromServiceMsg.getMsfCommand() + ":" + fromServiceMsg.getRequestSsoSeq());
            if (fromServiceMsg.getMsfCommand() == MsfCommand.onNetNeedSignon) {
                QLog.i(l, m.h() + " wifi need wath");
                QALSDKManager.getInstance().getConnectionListener().onWifiNeedAuth((String) fromServiceMsg.getAttribute("signonurl"));
            } else if (serviceCmd.equals(com.tencent.qalsdk.base.a.ad)) {
                if (QALSDKManager.getInstance().getConnectionListener() != null) {
                    QALSDKManager.getInstance().getConnectionListener().onConnected();
                }
            } else if (serviceCmd.equals(com.tencent.qalsdk.base.a.Z)) {
                if (QALSDKManager.getInstance().getConnectionListener() != null) {
                    QALSDKManager.getInstance().getConnectionListener().onDisconnected(0, "");
                }
            } else if (serviceCmd.equals(com.tencent.qalsdk.base.a.aa)) {
                a((String) fromServiceMsg.getAttribute(v.J), (String) fromServiceMsg.getAttribute(v.M), (String) fromServiceMsg.getAttribute(v.L), ((Integer) fromServiceMsg.getAttribute(v.K)).intValue());
            } else if (serviceCmd.equals(com.tencent.qalsdk.base.a.cj)) {
                this.B = ((Integer) fromServiceMsg.getAttribute(v.N)).intValue();
                QLog.i(l, "recv service serverEnv:" + this.B);
            } else if (serviceCmd.equals(com.tencent.qalsdk.base.a.cq)) {
                QALUserStatusListener userStatusListener = QALSDKManager.getInstance().getUserStatusListener();
                if (userStatusListener != null) {
                    QLog.i(l, fromServiceMsg.getUin() + " forceoffline, getUserStatusListener cb");
                    userStatusListener.onForceOffline((String) this.o.get(fromServiceMsg.getUin()));
                }
            } else if (fromServiceMsg.getMsfCommand() == MsfCommand.registerPush) {
                a(null, fromServiceMsg, null);
            } else if (this.H.containsKey(serviceCmd)) {
                QALPushListener qALPushListener = (QALPushListener) this.H.get(serviceCmd);
                if (!a(fromServiceMsg, qALPushListener)) {
                    qALPushListener.onSuccess(d(fromServiceMsg.getUin()), a(fromServiceMsg));
                }
            } else {
                QLog.d(l, 2, "push come,no callback:" + fromServiceMsg.getServiceCmd());
            }
        } catch (Throwable e) {
            QLog.d(l, 1, "handle push msg error " + e, e);
        }
        return true;
    }

    private boolean a(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        try {
            boolean z;
            if (QLog.isColorLevel()) {
                QLog.d(l, 2, "[CoreWrapper handleRespMsg] recv resp msg:" + System.currentTimeMillis() + DLConstants.DEPENDENCY_PACKAGE_DIV + fromServiceMsg.getServiceCmd() + DLConstants.DEPENDENCY_PACKAGE_DIV + fromServiceMsg.getRequestSsoSeq());
            }
            int businessFailCode = fromServiceMsg.getBusinessFailCode();
            if (businessFailCode == -10001 || businessFailCode == -10003 || businessFailCode == -10004) {
                String str = (String) this.o.get(toServiceMsg.getUin());
                if (!(str == null || this.q == null || !str.equals(this.q))) {
                    if (businessFailCode == -10001) {
                        QLog.i(l, "handleRespMsg CODE_D2Expired:" + str);
                        this.r = null;
                        GuestHelper.getInstance().tlsRefreshID(str, true);
                        return true;
                    }
                    QLog.i(l, "handleRespMsg CODE_D2 invalid:" + str);
                    this.r = null;
                    GuestHelper.getInstance().generateAndLoginGuest();
                    return true;
                }
            }
            if (fromServiceMsg.getMsfCommand() == MsfCommand.registerPush) {
                QLog.d(l, 2, "handle server msg [register push] ");
                z = false;
            } else if (fromServiceMsg.getMsfCommand() == MsfCommand.unRegisterPush) {
                QLog.d(l, 2, "handle server msg [unregister push] ");
                z = false;
            } else if (fromServiceMsg.getMsfCommand() == MsfCommand.registerCmdCallback) {
                z = true;
            } else if (fromServiceMsg.getMsfCommand() == MsfCommand.resetCmdCallback) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return z;
            }
            if (toServiceMsg.getActionListener() == null) {
                return false;
            }
            K.post(new f(this, toServiceMsg, fromServiceMsg));
            return true;
        } catch (Throwable e) {
            QLog.d(l, 1, "handle resp msg error " + e, e);
            return true;
        }
    }

    public synchronized void a(Context context) {
        this.m = MsfSdkUtils.getProcessName(context);
        n();
        q();
        if (this.u) {
            v();
            this.u = false;
        }
        if (this.v) {
            u();
            this.v = false;
        }
        if (this.w) {
            d(this.C);
            this.w = false;
        }
        try {
            this.j = new l();
            if (this.j.a(context)) {
                this.M = true;
            } else {
                QLog.e(l, 1, "MsfStore init fail");
            }
        } catch (Exception e) {
            QLog.e(l, 1, "MsfStore init error: " + e);
        }
        this.L = new com.tencent.qalsdk.core.a();
        if (this.M) {
            this.L.a();
            for (String str : this.L.d().keySet()) {
                String str2 = ((a) this.L.d().get(str)).a;
                QLog.i(l, "store accout:" + str + ":" + str2);
                this.o.put(str, str2);
                this.n.put(str2, str);
            }
        }
    }

    private void a(ToServiceMsg toServiceMsg) {
        if (toServiceMsg != null) {
            toServiceMsg.setAppId(com.tencent.qalsdk.base.a.bm);
            toServiceMsg.setTimeout(this.I);
            toServiceMsg.setServiceName(f);
            MsfSdkUtils.addToMsgProcessName(this.m, toServiceMsg);
        }
    }

    public void n() {
        Thread gVar = new g(this);
        gVar.setName("handlerRespMsgThread");
        gVar.start();
    }

    public boolean a(FromServiceMsg fromServiceMsg, QALPushListener qALPushListener) {
        if (fromServiceMsg.isSuccess()) {
            return false;
        }
        qALPushListener.onError(d(fromServiceMsg.getUin()), 1001, fromServiceMsg.getBusinessFailMsg());
        return true;
    }

    public boolean a(FromServiceMsg fromServiceMsg, QALCallBack qALCallBack) {
        if (fromServiceMsg.isSuccess()) {
            return false;
        }
        qALCallBack.onError(1001, fromServiceMsg.getBusinessFailMsg());
        return true;
    }

    public boolean a(FromServiceMsg fromServiceMsg, QALValueCallBack qALValueCallBack) {
        if (fromServiceMsg.isSuccess()) {
            return false;
        }
        String businessFailMsg = fromServiceMsg.getBusinessFailMsg();
        if (qALValueCallBack != null) {
            qALValueCallBack.onError(fromServiceMsg.getResultCode(), businessFailMsg);
            return true;
        }
        QLog.e(l, 1, "checkError cb null:" + fromServiceMsg.getServiceCmd() + ":" + fromServiceMsg.getResultCode());
        return true;
    }

    public static byte[] a(FromServiceMsg fromServiceMsg) {
        Object wupBuffer = fromServiceMsg.getWupBuffer();
        if (wupBuffer.length - 4 < 0) {
            return null;
        }
        Object obj = new byte[(wupBuffer.length - 4)];
        System.arraycopy(wupBuffer, 4, obj, 0, wupBuffer.length - 4);
        return obj;
    }

    public void a(String str, String str2, byte[] bArr, QALValueCallBack qALValueCallBack, long j) {
        if (str != null) {
            String str3 = "0";
            if (!str.equals("0")) {
                String str4 = (String) this.n.get(str);
                if (str4 != null && str4.length() != 0) {
                    str3 = str4;
                } else if (qALValueCallBack != null) {
                    qALValueCallBack.onError(com.tencent.qalsdk.base.a.v, "invalid account:" + str);
                    return;
                } else {
                    return;
                }
            }
            if (!str3.equals("0") || str2.startsWith(util.FILE_DIR) || str2.equals("imopen_passthrough.callback_emp")) {
                if (qALValueCallBack != null) {
                    a(str3, str2, bArr, new i(this, qALValueCallBack), j, false);
                    return;
                }
                a(str3, str2, bArr, null, j, false);
            } else if (qALValueCallBack != null) {
                qALValueCallBack.onError(com.tencent.qalsdk.base.a.w, "id 0,but cmd:" + str2);
            }
        } else if (qALValueCallBack != null) {
            qALValueCallBack.onError(com.tencent.qalsdk.base.a.v, "account null");
        }
    }

    public void a(String str, String str2, byte[] bArr, IBaseActionListener iBaseActionListener, long j, boolean z) {
        ToServiceMsg toServiceMsg = new ToServiceMsg("", str, str2);
        toServiceMsg.putWupBuffer(o.b(bArr));
        toServiceMsg.setUinType(20);
        if (iBaseActionListener != null) {
            toServiceMsg.setActionListener(iBaseActionListener);
        } else {
            toServiceMsg.setNeedCallback(false);
        }
        if (z) {
            toServiceMsg.setIsHttpReq();
        }
        toServiceMsg.setAppId(com.tencent.qalsdk.base.a.bm);
        toServiceMsg.setTimeout(j);
        toServiceMsg.setServiceName(f);
        toServiceMsg.setAppSeq(MsfSdkUtils.getNextAppSeq());
        MsfSdkUtils.addToMsgProcessName(this.m, toServiceMsg);
        ac.a().b(toServiceMsg);
    }

    public void a(QALCallBack qALCallBack, a aVar) {
        ah ahVar = new ah();
        ahVar.d = (byte) 0;
        ahVar.e = (byte) 0;
        ahVar.c = 11;
        ArrayList arrayList = new ArrayList();
        arrayList.add(Long.valueOf(1));
        ahVar.b = arrayList;
        ahVar.f = System.currentTimeMillis() / 1000;
        ahVar.a = aVar.b;
        ToServiceMsg toServiceMsg = new ToServiceMsg("", ahVar.a, com.tencent.qalsdk.base.a.M);
        toServiceMsg.setMsfCommand(MsfCommand.registerPush);
        if (qALCallBack != null) {
            toServiceMsg.setActionListener(new k(this, aVar, qALCallBack));
        } else {
            toServiceMsg.setNeedCallback(false);
        }
        ai.a(toServiceMsg, ahVar);
        ai.a(toServiceMsg, aVar);
        a(toServiceMsg);
        toServiceMsg.setAppSeq(MsfSdkUtils.getNextAppSeq());
        QLog.i(l, "regPushNew |" + aVar.a + ":" + toServiceMsg.getAppSeq());
        ac.a().b(toServiceMsg);
        if (aVar.a.equals(this.q)) {
            QALInitHelper.instance().sendMsg();
        }
    }

    private void e(String str) {
        c cVar = new c();
        cVar.a = str;
        ArrayList arrayList = new ArrayList();
        for (String add : this.H.keySet()) {
            arrayList.add(add);
        }
        arrayList.add(com.tencent.qalsdk.base.a.cq);
        cVar.b = arrayList;
        ac.a().b(ac.a().a(cVar));
    }

    public void a(String str, QALCallBack qALCallBack) {
        String str2 = (String) this.n.get(str);
        if (str2 != null && str2.length() != 0) {
            ah ahVar = new ah();
            ahVar.d = (byte) 0;
            ahVar.e = (byte) 0;
            ArrayList arrayList = new ArrayList();
            arrayList.add(Long.valueOf(1));
            ahVar.b = arrayList;
            ahVar.f = 0;
            ahVar.a = str2;
            ToServiceMsg toServiceMsg = new ToServiceMsg("", ahVar.a, com.tencent.qalsdk.base.a.M);
            toServiceMsg.setMsfCommand(MsfCommand.unRegisterPush);
            if (qALCallBack != null) {
                toServiceMsg.setActionListener(new l(this, str, qALCallBack));
            } else {
                toServiceMsg.setNeedCallback(false);
            }
            ai.a(toServiceMsg, ahVar);
            a(toServiceMsg);
            toServiceMsg.setAppSeq(MsfSdkUtils.getNextAppSeq());
            ac.a().b(toServiceMsg);
        } else if (qALCallBack != null) {
            qALCallBack.onError(6014, "invalid account");
        }
    }

    public synchronized void a(String str, String str2) {
        this.x = str;
        this.y = str2;
        if (e.b) {
            u();
        } else {
            this.v = true;
        }
    }

    public void o() {
        for (String str : this.n.keySet()) {
            String str2 = (String) this.n.get(str2);
            if (str2 != null && str2.length() != 0) {
                ah ahVar = new ah();
                ahVar.d = (byte) 0;
                ahVar.e = (byte) 0;
                ahVar.c = 95;
                ArrayList arrayList = new ArrayList();
                arrayList.add(Long.valueOf(1));
                ahVar.b = arrayList;
                ahVar.f = System.currentTimeMillis() / 1000;
                ahVar.a = str2;
                ToServiceMsg toServiceMsg = new ToServiceMsg("", ahVar.a, com.tencent.qalsdk.base.a.M);
                toServiceMsg.setMsfCommand(MsfCommand.qal_setAppStatus);
                toServiceMsg.setNeedCallback(false);
                ai.a(toServiceMsg, ahVar);
                a(toServiceMsg);
                toServiceMsg.setAppSeq(MsfSdkUtils.getNextAppSeq());
                ac.a().b(toServiceMsg);
            } else {
                return;
            }
        }
    }

    private void u() {
        if (this.x != null && this.y != null) {
            ToServiceMsg toServiceMsg = new ToServiceMsg("", "0", com.tencent.qalsdk.base.a.ch);
            toServiceMsg.setMsfCommand(MsfCommand.qal_setAppEnv);
            toServiceMsg.setNeedCallback(false);
            toServiceMsg.addAttribute("appVersion", this.x);
            toServiceMsg.addAttribute("appChannel", this.y);
            a(toServiceMsg);
            toServiceMsg.setAppSeq(MsfSdkUtils.getNextAppSeq());
            ac.a().b(toServiceMsg);
        }
    }

    public void a(int i) {
        QLog.setOutputLogLevel(i);
        if (e.b) {
            v();
        } else {
            this.u = true;
        }
    }

    public void b(int i) {
        if (e.b) {
            d(i);
            return;
        }
        this.w = true;
        this.C = i;
    }

    private void d(int i) {
        ToServiceMsg toServiceMsg = new ToServiceMsg("", "0", com.tencent.qalsdk.base.a.cj);
        toServiceMsg.setMsfCommand(MsfCommand.qal_setServerEnv);
        toServiceMsg.setNeedCallback(false);
        toServiceMsg.addAttribute("serverEnv", Integer.valueOf(i));
        a(toServiceMsg);
        toServiceMsg.setAppSeq(MsfSdkUtils.getNextAppSeq());
        ac.a().b(toServiceMsg);
    }

    private void v() {
        QLog.d(l, "set service loglevel:" + QLog.getOutputLogLevel());
        ToServiceMsg toServiceMsg = new ToServiceMsg("", "0", com.tencent.qalsdk.base.a.ci);
        toServiceMsg.setMsfCommand(MsfCommand.qal_setLogLevel);
        toServiceMsg.setNeedCallback(false);
        toServiceMsg.addAttribute("logLevel", Integer.valueOf(QLog.getOutputLogLevel()));
        a(toServiceMsg);
        toServiceMsg.setAppSeq(MsfSdkUtils.getNextAppSeq());
        ac.a().b(toServiceMsg);
    }

    public void p() {
        ToServiceMsg toServiceMsg = new ToServiceMsg("", "0", com.tencent.qalsdk.base.a.cs);
        toServiceMsg.setMsfCommand(MsfCommand.qal_reportEvent);
        toServiceMsg.setNeedCallback(false);
        a(toServiceMsg);
        toServiceMsg.setAppSeq(MsfSdkUtils.getNextAppSeq());
        ac.a().b(toServiceMsg);
    }

    private void a(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg, QALCallBack qALCallBack) {
        String str = null;
        if (toServiceMsg != null) {
            str = (String) toServiceMsg.getAttribute("regPushReason");
        }
        QLog.d(l, 4, "pushReason:" + str + "cmd:" + fromServiceMsg.getMsfCommand() + ":" + fromServiceMsg.getServiceCmd());
        int intValue;
        if (str != null && str.equals(am.appRegister.toString()) && qALCallBack != null && !fromServiceMsg.isSuccess()) {
            if (fromServiceMsg.getResultCode() == 1002 && this.p.containsKey(fromServiceMsg.getUin())) {
                intValue = ((Integer) this.p.get(fromServiceMsg.getUin())).intValue() + 1;
                if (intValue <= 2) {
                    str = (String) this.o.get(fromServiceMsg.getUin());
                    QLog.i(l, " retry register:" + str + ":" + intValue);
                    a f = f(str);
                    if (!(f == null || f.b.equals("0"))) {
                        this.p.put(fromServiceMsg.getUin(), Integer.valueOf(intValue));
                        a(qALCallBack, f);
                        return;
                    }
                }
            }
            str = fromServiceMsg.getBusinessFailMsg();
            QLog.e(l, fromServiceMsg.getServiceCmd() + " failed:" + fromServiceMsg.getResultCode() + ":" + str + ":" + fromServiceMsg.getRequestSsoSeq());
            qALCallBack.onError(fromServiceMsg.getResultCode(), str);
        } else if (fromServiceMsg.isSuccess()) {
            byte[] a = a(fromServiceMsg);
            RspBody rspBody = new RspBody();
            if (a == null) {
                try {
                    QLog.i(l, "regsiter rspbody null,is already online. app cb ret ok ");
                    if (qALCallBack != null) {
                        qALCallBack.onSuccess();
                        return;
                    }
                    return;
                } catch (InvalidProtocolBufferMicroException e) {
                    e.printStackTrace();
                    if (qALCallBack != null) {
                        QLog.e(l, fromServiceMsg.getServiceCmd() + " failed:" + 6001 + ": parse rspbody failed");
                        qALCallBack.onError(6001, "reg push: parse rspbody failed");
                        return;
                    }
                    return;
                }
            }
            rspBody.mergeFrom(a);
            if (fromServiceMsg.getMsfCommand().equals(MsfCommand.registerPush)) {
                QLog.d(l, 4, "setRegisterStatus:" + a.REGISTERD);
                a((String) fromServiceMsg.getAttribute(v.J), (String) fromServiceMsg.getAttribute(v.M), (String) fromServiceMsg.getAttribute(v.L), ((Integer) fromServiceMsg.getAttribute(v.K)).intValue());
            } else if (fromServiceMsg.getMsfCommand() == MsfCommand.unRegisterPush) {
            }
            if (rspBody.enum_cmd_error_code.uint32_code.get() != 0) {
                int i = rspBody.enum_cmd_error_code.uint32_code.get();
                if (fromServiceMsg.getMsfCommand().equals(MsfCommand.registerPush) && i == 1002) {
                    i = com.tencent.qalsdk.base.a.b;
                    b().a(fromServiceMsg.getUin(), new m(this));
                }
                intValue = i;
                if (qALCallBack != null) {
                    QLog.e(l, fromServiceMsg.getServiceCmd() + " failed. result:" + intValue);
                    qALCallBack.onError(intValue, rspBody.enum_cmd_error_code.bytes_err_msg.get().toStringUtf8());
                } else if (toServiceMsg == null) {
                    QALUserStatusListener userStatusListener = QALSDKManager.getInstance().getUserStatusListener();
                    if (userStatusListener != null) {
                        userStatusListener.onRegisterFail((String) this.o.get(fromServiceMsg.getUin()), intValue, rspBody.enum_cmd_error_code.bytes_err_msg.get().toStringUtf8());
                    }
                    QLog.e(l, fromServiceMsg.getServiceCmd() + "failed. getUserStatusListener. result:" + intValue);
                }
            } else if (qALCallBack != null) {
                QLog.i(l, fromServiceMsg.getServiceCmd() + " succ,app cb return. " + fromServiceMsg.getMsfCommand());
                qALCallBack.onSuccess();
            } else {
                QALUserStatusListener userStatusListener2 = QALSDKManager.getInstance().getUserStatusListener();
                if (userStatusListener2 != null) {
                    str = (String) this.o.get(fromServiceMsg.getUin());
                    if (str != null) {
                        QLog.i(l, 4, "getUserStatusListener.onRegisterSucc: " + str);
                        userStatusListener2.onRegisterSucc(str);
                    }
                }
            }
        } else {
            QLog.e(l, "regsiter error:" + fromServiceMsg.getResultCode());
        }
    }

    private a f(String str) {
        String str2 = "0";
        byte[] bArr = null;
        byte[] bArr2 = null;
        byte[] bArr3 = null;
        QLog.d(l, 4, "getTicket:" + str);
        if (TLSLoginHelper.getInstance() == null) {
            QLog.e(l, 1, "tls sdk not init");
            return null;
        }
        Map sSOTicket = TLSLoginHelper.getInstance().getSSOTicket(str);
        for (String str3 : sSOTicket.keySet()) {
            byte[] bArr4;
            String str4;
            if ("tinyID".equals(str3) || "identifier".equals(str3)) {
                if ("tinyID".equals(str3)) {
                    str2 = sSOTicket.get(str3).toString();
                    QLog.i(l, 4, "user:" + str + String.format(" bind id:%s: %s", new Object[]{str3, sSOTicket.get(str3)}));
                    bArr4 = bArr3;
                    bArr3 = bArr2;
                    bArr2 = bArr;
                    str4 = str2;
                }
                bArr4 = bArr3;
                bArr3 = bArr2;
                bArr2 = bArr;
                str4 = str2;
            } else if ("A2".equals(str3)) {
                str4 = str2;
                r13 = bArr2;
                bArr2 = (byte[]) sSOTicket.get(str3);
                bArr4 = bArr3;
                bArr3 = r13;
            } else if ("D2".equals(str3)) {
                bArr2 = bArr;
                str4 = str2;
                r13 = bArr3;
                bArr3 = (byte[]) sSOTicket.get(str3);
                bArr4 = r13;
            } else {
                if ("D2Key".equals(str3)) {
                    bArr4 = (byte[]) sSOTicket.get(str3);
                    bArr3 = bArr2;
                    bArr2 = bArr;
                    str4 = str2;
                }
                bArr4 = bArr3;
                bArr3 = bArr2;
                bArr2 = bArr;
                str4 = str2;
            }
            str2 = str4;
            bArr = bArr2;
            bArr2 = bArr3;
            bArr3 = bArr4;
        }
        if (str.equals(this.q)) {
            this.r = str2;
        }
        this.o.put(str2, str);
        this.n.put(str, str2);
        b(str);
        a aVar = new a();
        aVar.a(str);
        aVar.b(str2);
        aVar.a(bArr);
        aVar.b(bArr2);
        aVar.c(bArr3);
        aVar.a(QALSDKManager.getInstance().getSdkAppId());
        return aVar;
    }

    public void b(String str, QALCallBack qALCallBack) {
        a f = f(str);
        if (f == null || f.b.equals("0")) {
            int i = com.tencent.qalsdk.base.a.g;
            if (f == null) {
                i = com.tencent.qalsdk.base.a.f;
            }
            QLog.e(l, "tls sdk not init or no user:" + str + ":" + i);
            qALCallBack.onError(i, "tls sdk not init or no user");
            return;
        }
        this.p.put(f.b, Integer.valueOf(0));
        a(qALCallBack, f);
    }

    public void q() {
        e.a(this.z, com.tencent.qalsdk.base.a.bm, "com.tencent.qalsdk.broadcast.qal", f, this.k, this.m);
        this.g = new y(new ax[]{null, null, null, null, null, null, null});
        e.c();
    }

    public void r() {
        try {
            e.h();
            e.i();
        } catch (Exception e) {
            QLog.i(l, "stop error:" + e.getMessage());
        }
    }

    public synchronized String s() {
        return this.m;
    }

    public long c(int i) {
        if (i > 13600) {
            return (long) (((double) 15000) + (((double) (i / 13600)) * 1.5d));
        }
        return com.tencent.qalsdk.base.a.ap;
    }
}
