package com.tencent.qalsdk.sdk;

import com.dynamicload.Lib.DLConstants;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.util.QLog;
import qalsdk.aw;

/* compiled from: MsfServiceSub */
class ad {
    private String a;
    private Object b = new Object();
    protected int c;
    protected String d;
    protected String e;
    protected final ae<w> f = new ae();
    aw g;

    ad() {
    }

    public void a(String str, int i, String str2, aw awVar) {
        this.c = i;
        c(str2);
        this.e = str;
        this.g = awVar;
    }

    public boolean a(w wVar) {
        boolean z = true;
        if (MsfSdkUtils.isPrivilegeCMD(wVar.b.getServiceCmd())) {
            this.f.a((Object) wVar);
            QLog.d("MsfServiceSub", "serviceSub add first:" + System.currentTimeMillis() + DLConstants.DEPENDENCY_PACKAGE_DIV + wVar.b.getServiceCmd() + DLConstants.DEPENDENCY_PACKAGE_DIV + wVar.b.getRequestSsoSeq());
        } else {
            z = this.f.add(wVar);
            QLog.d("MsfServiceSub", "serviceSub add last:" + System.currentTimeMillis() + DLConstants.DEPENDENCY_PACKAGE_DIV + wVar.b.getServiceCmd() + DLConstants.DEPENDENCY_PACKAGE_DIV + wVar.b.getRequestSsoSeq());
        }
        synchronized (this.b) {
            this.b.notify();
        }
        return z;
    }

    public w n() {
        return (w) this.f.poll();
    }

    public boolean a(FromServiceMsg fromServiceMsg) {
        boolean add = this.f.add(new w(null, fromServiceMsg));
        if (fromServiceMsg == null || fromServiceMsg.getServiceCmd() == null || fromServiceMsg.getServiceCmd().equals("SharpSvr.s2c")) {
        }
        synchronized (this.b) {
            this.b.notify();
        }
        return add;
    }

    public void m() {
        synchronized (this.b) {
            try {
                if (this.f.size() == 0) {
                    this.b.wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }

    public aw l() {
        return this.g;
    }

    public String k() {
        return this.a;
    }

    public void c(String str) {
        this.a = str;
    }

    public ae<w> j() {
        return this.f;
    }
}
