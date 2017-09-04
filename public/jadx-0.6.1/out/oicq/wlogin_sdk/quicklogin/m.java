package oicq.wlogin_sdk.quicklogin;

import android.os.Build.VERSION;
import android.webkit.WebView;

/* compiled from: QuickLoginWebViewLoader */
final class m implements Runnable {
    final /* synthetic */ WebView a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;

    m(WebView webView, String str, String str2) {
        this.a = webView;
        this.b = str;
        this.c = str2;
    }

    public void run() {
        if (VERSION.SDK_INT >= 19) {
            this.a.loadUrl(this.b);
            this.a.evaluateJavascript(this.c, null);
            return;
        }
        this.a.loadUrl(this.c);
        this.a.loadUrl(this.b);
    }
}
