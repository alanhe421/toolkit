package com.qq.reader.common.web.js.a;

import android.webkit.WebSettings;
import android.webkit.WebView;
import com.qq.reader.common.web.js.a.a.b;

/* compiled from: WebBrowserJsEx */
public class c {
    public a a;

    public c() {
        this.a = null;
        this.a = new a();
    }

    public void a(WebView webView) {
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
        webView.removeJavascriptInterface("accessibility");
        webView.removeJavascriptInterface("accessibilityTraversal");
    }

    public void a(b bVar, String str) {
        this.a.a(bVar, str);
    }

    public void a(String str) {
        this.a.a(str);
    }

    public void a() {
        this.a.a(null);
        this.a = null;
    }

    public boolean a(WebView webView, String str) {
        try {
            if (this.a.a(webView, str)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void b(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setUserAgentString(new StringBuilder(settings.getUserAgentString()).append("V1_AND_SQ_5.0.1").append(" QQReader ").toString());
    }
}
