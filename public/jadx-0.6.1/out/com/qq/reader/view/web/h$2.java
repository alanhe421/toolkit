package com.qq.reader.view.web;

/* compiled from: OpenMonthlyDialog */
class h$2 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ h b;

    h$2(h hVar, String str) {
        this.b = hVar;
        this.a = str;
    }

    public void run() {
        h.a(this.b).loadUrl(this.a);
    }
}
