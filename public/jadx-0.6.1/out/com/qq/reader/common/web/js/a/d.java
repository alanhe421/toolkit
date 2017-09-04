package com.qq.reader.common.web.js.a;

import com.qq.reader.common.web.js.a.b.b;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

/* compiled from: WebBrowserJsExH5 */
public class d {
    public b a;

    public d() {
        this.a = null;
        this.a = new b();
    }

    public void a(WebView webView) {
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
        webView.removeJavascriptInterface("accessibility");
        webView.removeJavascriptInterface("accessibilityTraversal");
    }

    public void a(b bVar, String str) {
        if (this.a == null) {
            this.a = new b();
        }
        this.a.a(bVar, str);
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
