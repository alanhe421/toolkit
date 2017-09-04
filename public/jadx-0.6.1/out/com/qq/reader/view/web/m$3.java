package com.qq.reader.view.web;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* compiled from: PopWebDialog */
class m$3 extends WebViewClient {
    final /* synthetic */ m a;

    m$3(m mVar) {
        this.a = mVar;
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str.startsWith("about")) {
            return false;
        }
        try {
            if (m.i(this.a).a(webView, str)) {
                return true;
            }
        } catch (Exception e) {
        }
        webView.loadUrl(str);
        return true;
    }
}
