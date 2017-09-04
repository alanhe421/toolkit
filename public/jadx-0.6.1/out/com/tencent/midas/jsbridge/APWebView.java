package com.tencent.midas.jsbridge;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tencent.midas.api.APMidasPayAPI;
import com.tencent.midas.comm.APLog;
import java.lang.reflect.Method;

public class APWebView {
    private WebView a = null;
    private Activity b = null;
    private IAPWebViewCallback c = null;
    private WebChromeClient d = new WebChromeClient(this) {
        final /* synthetic */ APWebView a;

        {
            this.a = r1;
        }

        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            APLog.i("inner onJsAlert message", str2);
            if (APMidasPayAPI.h5PayHook(this.a.b, this.a.a, str, str2, jsResult) != 0) {
                return super.onJsAlert(webView, str, str2, jsResult);
            }
            this.a.c.WebChromeClientJsAlert(webView, str, str2, jsResult);
            jsResult.cancel();
            return true;
        }

        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            return this.a.c.WebChromeClientJsPrompt(webView, str, str2, str3, jsPromptResult);
        }
    };
    private WebViewClient e = new WebViewClient(this) {
        final /* synthetic */ APWebView a;

        {
            this.a = r1;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            APLog.i("APWebView url == ", str);
            this.a.a.setVisibility(0);
            APMidasPayAPI.InnerH5PayInit(this.a.b, this.a.a);
            this.a.c.WebViewClientPageFinished(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.a.c.WebViewClientPageStarted(webView, str, bitmap);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            this.a.c.WebViewClientReceivedError(webView, i, str, str2);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!(str.startsWith("http://unipay.sdk.android/?") || str.startsWith("wsj://"))) {
                webView.loadUrl(str);
            }
            return true;
        }
    };

    public APWebView(Activity activity, WebView webView, IAPWebViewCallback iAPWebViewCallback) {
        this.b = activity;
        this.a = webView;
        this.c = iAPWebViewCallback;
        a();
    }

    @SuppressLint({"NewApi"})
    private void a() {
        WebSettings settings = this.a.getSettings();
        settings.setJavaScriptEnabled(true);
        if (VERSION.SDK_INT < 19 || APMidasPayAPI.env.equals(APMidasPayAPI.ENV_TEST)) {
            settings.setDomStorageEnabled(true);
            settings.setAppCachePath(this.b.getApplicationContext().getDir("cache", 0).getPath());
            settings.setAllowFileAccess(true);
            settings.setAppCacheEnabled(true);
            settings.setCacheMode(-1);
            this.a.setScrollBarStyle(0);
            this.a.setWebChromeClient(this.d);
            this.a.setWebViewClient(this.e);
            b();
        } else {
            settings.setDomStorageEnabled(true);
            settings.setAppCachePath(this.b.getApplicationContext().getDir("cache", 0).getPath());
            settings.setAllowFileAccess(true);
            settings.setAppCacheEnabled(true);
            settings.setCacheMode(-1);
            this.a.setScrollBarStyle(0);
            this.a.setWebChromeClient(this.d);
            this.a.setWebViewClient(this.e);
            b();
        }
    }

    private void b() {
        try {
            Method method = this.a.getClass().getMethod("removeJavascriptInterface", new Class[]{String.class});
            if (method != null) {
                method.invoke(this.a, new Object[]{"searchBoxJavaBridge_"});
            }
        } catch (Exception e) {
            APLog.i("removeJavascriptInterface", e.toString());
        }
    }

    public WebView getWebView() {
        return this.a;
    }

    public void loadUrl(String str) {
        this.a.loadUrl(str);
    }
}
