package com.yuewen.ywlogin;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

class i extends WebChromeClient {
    final /* synthetic */ YWBrowserActivity a;

    i(YWBrowserActivity yWBrowserActivity) {
        this.a = yWBrowserActivity;
    }

    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        return super.onJsAlert(webView, str, str2, jsResult);
    }

    public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        return super.onJsConfirm(webView, str, str2, jsResult);
    }

    public void onProgressChanged(WebView webView, int i) {
        this.a.e.setProgress(i);
        if (i >= 100) {
            this.a.e.setVisibility(8);
        } else {
            this.a.e.setVisibility(0);
        }
        super.onProgressChanged(webView, i);
    }

    public void onReceivedTitle(WebView webView, String str) {
        if (str != null) {
            CharSequence substring;
            if (str.length() > 15) {
                substring = str.substring(0, 15);
            } else {
                Object obj = str;
            }
            this.a.c.setText(substring);
        }
        super.onReceivedTitle(webView, str);
    }
}
