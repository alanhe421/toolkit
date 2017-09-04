package com.qq.reader.view.web;

import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* compiled from: OpenMonthlyDialog */
class h$3 extends WebChromeClient {
    final /* synthetic */ h a;

    h$3(h hVar) {
        this.a = hVar;
    }

    public void onProgressChanged(WebView webView, int i) {
    }

    public void onReceivedTitle(WebView webView, String str) {
        super.onReceivedTitle(webView, str);
        if (h.a(this.a) != null && !TextUtils.isEmpty(h.a(this.a).getTitle())) {
            h.b(this.a).setText(h.a(this.a).getTitle());
        }
    }
}
