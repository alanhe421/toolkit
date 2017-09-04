package com.tencent.smtt.sdk;

import android.os.Message;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase$WebViewTransport;

class i implements Runnable {
    final /* synthetic */ WebView$WebViewTransport a;
    final /* synthetic */ Message b;
    final /* synthetic */ h c;

    i(h hVar, WebView$WebViewTransport webView$WebViewTransport, Message message) {
        this.c = hVar;
        this.a = webView$WebViewTransport;
        this.b = message;
    }

    public void run() {
        WebView webView = this.a.getWebView();
        if (webView != null) {
            ((IX5WebViewBase$WebViewTransport) this.b.obj).setWebView(webView.b());
        }
        this.b.sendToTarget();
    }
}
