package com.qq.reader.view.web;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.qq.reader.common.monitor.f;
import com.qq.reader.qurl.c;

/* compiled from: OpenMonthlyDialog */
class h$4 extends WebViewClient {
    final /* synthetic */ h a;

    h$4(h hVar) {
        this.a = hVar;
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        if (h.a(this.a) != null && !TextUtils.isEmpty(h.a(this.a).getTitle())) {
            h.b(this.a).setText(h.a(this.a).getTitle());
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        f.d("openmonth", "shouldOverrideUrlLoading " + str);
        if (str.startsWith("about")) {
            return false;
        }
        try {
            if (this.a.a.a(webView, str)) {
                return true;
            }
        } catch (Exception e) {
        }
        if (c.b(str)) {
            try {
                c.a(this.a.b(), str);
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return true;
            }
        }
        webView.loadUrl(str);
        return true;
    }
}
