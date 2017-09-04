package com.tencent.smtt.sdk;

import android.content.Context;

public class WebViewDatabase {
    private static WebViewDatabase a;
    private Context b;

    protected WebViewDatabase(Context context) {
        this.b = context;
    }

    private static synchronized WebViewDatabase a(Context context) {
        WebViewDatabase webViewDatabase;
        synchronized (WebViewDatabase.class) {
            if (a == null) {
                a = new WebViewDatabase(context);
            }
            webViewDatabase = a;
        }
        return webViewDatabase;
    }

    public static WebViewDatabase getInstance(Context context) {
        return a(context);
    }

    public void clearFormData() {
        aq a = aq.a();
        if (a == null || !a.b()) {
            android.webkit.WebViewDatabase.getInstance(this.b).clearFormData();
        } else {
            a.c().g(this.b);
        }
    }

    public void clearHttpAuthUsernamePassword() {
        aq a = aq.a();
        if (a == null || !a.b()) {
            android.webkit.WebViewDatabase.getInstance(this.b).clearHttpAuthUsernamePassword();
        } else {
            a.c().e(this.b);
        }
    }

    @Deprecated
    public void clearUsernamePassword() {
        aq a = aq.a();
        if (a == null || !a.b()) {
            android.webkit.WebViewDatabase.getInstance(this.b).clearUsernamePassword();
        } else {
            a.c().c(this.b);
        }
    }

    public boolean hasFormData() {
        aq a = aq.a();
        return (a == null || !a.b()) ? android.webkit.WebViewDatabase.getInstance(this.b).hasFormData() : a.c().f(this.b);
    }

    public boolean hasHttpAuthUsernamePassword() {
        aq a = aq.a();
        return (a == null || !a.b()) ? android.webkit.WebViewDatabase.getInstance(this.b).hasHttpAuthUsernamePassword() : a.c().d(this.b);
    }

    @Deprecated
    public boolean hasUsernamePassword() {
        aq a = aq.a();
        return (a == null || !a.b()) ? android.webkit.WebViewDatabase.getInstance(this.b).hasUsernamePassword() : a.c().b(this.b);
    }
}
