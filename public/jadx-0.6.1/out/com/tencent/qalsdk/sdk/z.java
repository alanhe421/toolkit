package com.tencent.qalsdk.sdk;

import android.content.ComponentName;
import android.content.Intent;
import android.os.RemoteException;
import com.tencent.qalsdk.QALSDKManager;
import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.IMsfServiceCallbacker;
import com.tencent.qalsdk.base.remote.MsfServiceBindInfo;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.core.NetConnInfoCenter;
import com.tencent.qalsdk.util.QLog;

/* compiled from: MsfServiceProxy */
class z extends aj {
    private static final String p = "MsfServiceProxy";
    ac a;
    protected volatile boolean b = false;
    IMsfServiceCallbacker c = new aa(this);

    public z(String str) {
        super(str);
    }

    public void a(ac acVar) {
        this.a = acVar;
        acVar.d = this.n;
    }

    protected void a(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        c(toServiceMsg, fromServiceMsg);
    }

    void a() {
        try {
            ComponentName componentName = new ComponentName(QALSDKManager.getInstance().getContext().getPackageName(), this.a.d);
            Intent intent = new Intent();
            intent.setComponent(componentName);
            intent.putExtra(v.c, this.a.e);
            QALSDKManager.getInstance().getContext().startService(intent);
            QLog.d(p, 1, "start service finish");
        } catch (Throwable e) {
            QLog.d(p, 1, " " + e, e);
        }
    }

    boolean b() {
        boolean bindService;
        Throwable e;
        try {
            ComponentName componentName = new ComponentName(QALSDKManager.getInstance().getContext().getPackageName(), this.a.d);
            Intent intent = new Intent();
            intent.putExtra(v.c, this.a.e);
            intent.setComponent(componentName);
            bindService = QALSDKManager.getInstance().getContext().bindService(intent, this.o, 1);
            try {
                QLog.d(p, 1, "threadID:" + Thread.currentThread().getId() + ", threadName: " + Thread.currentThread().getName() + " bind " + this.a.d + " service finished " + bindService);
            } catch (Exception e2) {
                e = e2;
                QLog.d(p, 1, " " + e, e);
                return bindService;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            bindService = false;
            e = th;
            QLog.d(p, 1, " " + e, e);
            return bindService;
        }
        return bindService;
    }

    public boolean c() {
        try {
            ComponentName componentName = new ComponentName(QALSDKManager.getInstance().getContext().getPackageName(), this.a.d);
            Intent intent = new Intent();
            intent.setComponent(componentName);
            intent.putExtra(v.c, this.a.e);
            boolean stopService = QALSDKManager.getInstance().getContext().stopService(intent);
            QLog.i(p, 2, " stopService service finished: " + stopService);
            return stopService;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected int a(ToServiceMsg toServiceMsg) throws RemoteException {
        if (toServiceMsg == null) {
            return -1;
        }
        toServiceMsg.setAppId(this.a.c);
        toServiceMsg.getAttributes().put(v.a, Long.valueOf(System.currentTimeMillis()));
        toServiceMsg.getAttributes().put(v.c, this.a.e);
        if (QLog.isColorLevel()) {
            QLog.d(p, 2, " send req to msfService:" + toServiceMsg);
        }
        return this.d.sendToServiceMsg(toServiceMsg);
    }

    protected void d() {
        int f = f();
        if (QLog.isColorLevel()) {
            QLog.d(p, 2, " registerMsfService result:" + f);
        }
        Thread abVar = new ab(this);
        abVar.setName("handleWaitSendProxyMsgThread");
        try {
            abVar.start();
        } catch (Throwable th) {
            QLog.i(p, 1, "error: " + th, th);
            e();
        }
    }

    public void e() {
        while (!f.isEmpty()) {
            ToServiceMsg toServiceMsg = (ToServiceMsg) f.poll();
            if (toServiceMsg != null) {
                try {
                    a(toServiceMsg);
                } catch (Exception e) {
                    c(toServiceMsg, a(toServiceMsg, toServiceMsg.getServiceName() + "sendMsgToServiceFailed，" + e.toString()));
                }
            }
        }
    }

    public int f() {
        ToServiceMsg toServiceMsg = new ToServiceMsg(this.a.d, "0", a.V);
        toServiceMsg.setMsfCommand(MsfCommand.registerMsfService);
        toServiceMsg.getAttributes().put(v.o, new MsfServiceBindInfo(this.a.c, this.a.e, this.a.k(), this.c));
        toServiceMsg.setNeedCallback(false);
        this.b = true;
        return c(toServiceMsg);
    }

    public int a(Boolean bool) {
        ToServiceMsg toServiceMsg = new ToServiceMsg(this.a.d, "0", a.U);
        toServiceMsg.setMsfCommand(MsfCommand.unRegisterMsfService);
        toServiceMsg.extraData.putBoolean(v.b, bool.booleanValue());
        this.b = false;
        return c(toServiceMsg);
    }

    public void g() {
        super.g();
        this.c = null;
    }

    public boolean b(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        boolean booleanValue;
        int businessFailCode = fromServiceMsg.getBusinessFailCode();
        if (fromServiceMsg.getAttributes().containsKey(a.aR)) {
            booleanValue = ((Boolean) fromServiceMsg.getAttribute(a.aR)).booleanValue();
        } else {
            booleanValue = false;
        }
        switch (businessFailCode) {
            case 2001:
                QLog.d(p, 1, "BaseConstants.CODE_NO_LOGIN " + fromServiceMsg.hashCode());
                this.a.g.a(toServiceMsg, fromServiceMsg, booleanValue);
                return true;
            case a.n /*2009*/:
                this.a.g.e(toServiceMsg, fromServiceMsg, booleanValue);
                return true;
            case a.o /*2011*/:
                this.a.g.b(toServiceMsg, fromServiceMsg, booleanValue);
                return true;
            case a.p /*2012*/:
                this.a.g.c(toServiceMsg, fromServiceMsg, booleanValue);
                return true;
            case a.q /*2013*/:
                this.a.g.d(toServiceMsg, fromServiceMsg, booleanValue);
                return true;
            case a.r /*2014*/:
                this.a.g.a(booleanValue);
                return true;
            default:
                return false;
        }
    }

    private void a(FromServiceMsg fromServiceMsg) {
        if (QLog.isColorLevel()) {
            QLog.d(p, 2, " onRecvPushResp " + fromServiceMsg);
        }
        if (!b(null, fromServiceMsg)) {
            if (fromServiceMsg.getMsfCommand() == MsfCommand.pushSetConfig) {
                NetConnInfoCenter.socketConnState = ((Integer) fromServiceMsg.getAttribute(a.aU)).intValue();
                NetConnInfoCenter.servetTimeSecondInterv = ((Long) fromServiceMsg.getAttribute(a.aV)).longValue();
                QLog.d(p, "server interval time:" + NetConnInfoCenter.servetTimeSecondInterv);
            } else if (this.b) {
                QLog.d(p, 2, "onRecvServicePushResp  SsoCmd:" + fromServiceMsg.getServiceCmd() + " ssoSeq:" + fromServiceMsg.getRequestSsoSeq());
                this.a.a(fromServiceMsg);
            } else if (QLog.isColorLevel()) {
                QLog.d(p, 2, " close msfServiceConn. push msg is droped." + fromServiceMsg);
            }
        }
    }

    public void b(ToServiceMsg toServiceMsg) {
        g.remove(Integer.valueOf(toServiceMsg.getAppSeq()));
    }

    private void d(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        ToServiceMsg toServiceMsg2;
        if (toServiceMsg.isHttpReq()) {
            toServiceMsg2 = (ToServiceMsg) g.get(Integer.valueOf(toServiceMsg.getAppSeq()));
        } else {
            toServiceMsg2 = (ToServiceMsg) g.remove(Integer.valueOf(toServiceMsg.getAppSeq()));
        }
        if (toServiceMsg2 != null) {
            QLog.d(p, "onReceiveResp SsoCmd:" + toServiceMsg.getServiceCmd() + " ssoSeq:" + toServiceMsg.getRequestSsoSeq());
            if (!b(toServiceMsg, fromServiceMsg)) {
                if (this.b) {
                    if (QLog.isColorLevel()) {
                        QLog.d(p, 2, "add queue req:" + toServiceMsg + " from:" + fromServiceMsg);
                    }
                    this.a.a(new w(toServiceMsg, fromServiceMsg));
                    return;
                } else if (QLog.isColorLevel()) {
                    QLog.d(p, 2, " close msfServiceConn. msg is droped." + toServiceMsg.getRequestSsoSeq() + " " + fromServiceMsg);
                    return;
                } else {
                    return;
                }
            }
            return;
        }
        QLog.d(p, 2, " found timeout resp to:" + toServiceMsg + " from:" + fromServiceMsg);
    }

    protected void c(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        if (QLog.isColorLevel()) {
            QLog.d(p, 2, "add fail queue req:" + toServiceMsg + " from:" + fromServiceMsg);
        }
        this.a.a(new w(toServiceMsg, fromServiceMsg));
    }
}
