package com.tencent.qalsdk.sdk;

/* compiled from: LogToFileHelper */
class r implements Runnable {
    final /* synthetic */ q a;

    r(q qVar) {
        this.a = qVar;
    }

    public void run() {
        if (this.a.L != null && q.c && !this.a.C.get()) {
            new s(this, "QLogInitThread").start();
        }
    }
}
