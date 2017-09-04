package com.tencent.smtt.sdk;

import android.os.Build.VERSION;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.tencent.smtt.utils.r;
import java.util.Map;

public class CookieManager {
    private static CookieManager a;

    private CookieManager() {
    }

    public static synchronized CookieManager getInstance() {
        CookieManager cookieManager;
        synchronized (CookieManager.class) {
            if (a == null) {
                a = new CookieManager();
            }
            cookieManager = a;
        }
        return cookieManager;
    }

    public boolean acceptCookie() {
        aq a = aq.a();
        return (a == null || !a.b()) ? android.webkit.CookieManager.getInstance().acceptCookie() : a.c().d();
    }

    public synchronized boolean acceptThirdPartyCookies(WebView webView) {
        boolean booleanValue;
        aq a = aq.a();
        Object invokeStaticMethod;
        if (a != null && a.b()) {
            invokeStaticMethod = a.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_acceptThirdPartyCookies", new Class[]{Object.class}, webView.getView());
            booleanValue = invokeStaticMethod != null ? ((Boolean) invokeStaticMethod).booleanValue() : true;
        } else if (VERSION.SDK_INT < 21) {
            booleanValue = true;
        } else {
            invokeStaticMethod = r.a(android.webkit.CookieManager.getInstance(), "acceptThirdPartyCookies", new Class[]{WebView.class}, webView.getView());
            booleanValue = invokeStaticMethod != null ? ((Boolean) invokeStaticMethod).booleanValue() : false;
        }
        return booleanValue;
    }

    public void flush() {
        aq a = aq.a();
        if (a != null && a.b()) {
            a.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_flush", new Class[0], new Object[0]);
        } else if (VERSION.SDK_INT >= 21) {
            r.a(android.webkit.CookieManager.getInstance(), "flush", new Class[0], new Object[0]);
        }
    }

    public String getCookie(String str) {
        aq a = aq.a();
        return (a == null || !a.b()) ? android.webkit.CookieManager.getInstance().getCookie(str) : a.c().a(str);
    }

    public boolean hasCookies() {
        aq a = aq.a();
        return (a == null || !a.b()) ? android.webkit.CookieManager.getInstance().hasCookies() : a.c().h();
    }

    public void removeAllCookie() {
        aq a = aq.a();
        if (a == null || !a.b()) {
            android.webkit.CookieManager.getInstance().removeAllCookie();
        } else {
            a.c().e();
        }
    }

    public void removeAllCookies(ValueCallback<Boolean> valueCallback) {
        aq a = aq.a();
        if (a != null && a.b()) {
            a.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeAllCookies", new Class[]{ValueCallback.class}, valueCallback);
        } else if (VERSION.SDK_INT >= 21) {
            r.a(android.webkit.CookieManager.getInstance(), "removeAllCookies", new Class[]{ValueCallback.class}, valueCallback);
        }
    }

    public void removeExpiredCookie() {
        aq a = aq.a();
        if (a == null || !a.b()) {
            android.webkit.CookieManager.getInstance().removeExpiredCookie();
        } else {
            a.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeExpiredCookie", new Class[0], new Object[0]);
        }
    }

    public void removeSessionCookie() {
        aq a = aq.a();
        if (a == null || !a.b()) {
            android.webkit.CookieManager.getInstance().removeSessionCookie();
        } else {
            a.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookie", new Class[0], new Object[0]);
        }
    }

    public void removeSessionCookies(ValueCallback<Boolean> valueCallback) {
        aq a = aq.a();
        if (a != null && a.b()) {
            a.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookies", new Class[]{ValueCallback.class}, valueCallback);
        } else if (VERSION.SDK_INT >= 21) {
            r.a(android.webkit.CookieManager.getInstance(), "removeSessionCookies", new Class[]{ValueCallback.class}, valueCallback);
        }
    }

    public synchronized void setAcceptCookie(boolean z) {
        aq a = aq.a();
        if (a == null || !a.b()) {
            android.webkit.CookieManager.getInstance().setAcceptCookie(z);
        } else {
            a.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptCookie", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public synchronized void setAcceptThirdPartyCookies(WebView webView, boolean z) {
        aq a = aq.a();
        if (a != null && a.b()) {
            a.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptThirdPartyCookies", new Class[]{Object.class, Boolean.TYPE}, webView.getView(), Boolean.valueOf(z));
        } else if (VERSION.SDK_INT >= 21) {
            r.a(android.webkit.CookieManager.getInstance(), "setAcceptThirdPartyCookies", new Class[]{WebView.class, Boolean.TYPE}, webView.getView(), Boolean.valueOf(z));
        }
    }

    public void setCookie(String str, String str2) {
        aq a = aq.a();
        if (a == null || !a.b()) {
            android.webkit.CookieManager.getInstance().setCookie(str, str2);
            return;
        }
        a.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class}, str, str2);
    }

    public void setCookie(String str, String str2, ValueCallback<Boolean> valueCallback) {
        aq a = aq.a();
        if (a != null && a.b()) {
            a.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class, ValueCallback.class}, str, str2, valueCallback);
        } else if (VERSION.SDK_INT >= 21) {
            r.a(android.webkit.CookieManager.getInstance(), "setCookie", new Class[]{String.class, String.class, ValueCallback.class}, str, str2, valueCallback);
        }
    }

    public void setCookies(Map<String, String[]> map) {
        aq a = aq.a();
        boolean a2 = (a == null || !a.b()) ? false : a.c().a((Map) map);
        if (!a2) {
            for (String str : map.keySet()) {
                for (String cookie : (String[]) map.get(str)) {
                    setCookie(str, cookie);
                }
            }
        }
    }
}
