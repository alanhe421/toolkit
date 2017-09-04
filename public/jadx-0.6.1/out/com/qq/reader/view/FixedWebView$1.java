package com.qq.reader.view;

class FixedWebView$1 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ FixedWebView b;

    FixedWebView$1(FixedWebView fixedWebView, String str) {
        this.b = fixedWebView;
        this.a = str;
    }

    public void run() {
        if (this.a != null && this.a.length() != 0) {
            this.b.loadUrl(this.a);
        }
    }
}
