package com.tencent.qalhttp;

/* compiled from: QALHttpHelper */
class c implements Runnable {
    final /* synthetic */ QALHttpValueCallBack a;
    final /* synthetic */ int b;
    final /* synthetic */ String c;
    final /* synthetic */ QALHttpHelper d;

    c(QALHttpHelper qALHttpHelper, QALHttpValueCallBack qALHttpValueCallBack, int i, String str) {
        this.d = qALHttpHelper;
        this.a = qALHttpValueCallBack;
        this.b = i;
        this.c = str;
    }

    public void run() {
        this.a.onFailed(this.b, this.c);
    }
}
