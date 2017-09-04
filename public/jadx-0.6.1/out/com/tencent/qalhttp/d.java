package com.tencent.qalhttp;

/* compiled from: QALHttpHelper */
class d implements Runnable {
    final /* synthetic */ QALHttpValueCallBack a;
    final /* synthetic */ QALHttpResponse b;
    final /* synthetic */ QALHttpHelper c;

    d(QALHttpHelper qALHttpHelper, QALHttpValueCallBack qALHttpValueCallBack, QALHttpResponse qALHttpResponse) {
        this.c = qALHttpHelper;
        this.a = qALHttpValueCallBack;
        this.b = qALHttpResponse;
    }

    public void run() {
        this.a.onFinished(this.b);
    }
}
