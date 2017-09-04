package com.tencent.midas.jsbridge;

import android.graphics.Bitmap;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebView;

public interface IAPWebViewCallback {
    boolean WebChromeClientJsAlert(WebView webView, String str, String str2, JsResult jsResult);

    boolean WebChromeClientJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult);

    void WebViewClientPageFinished(WebView webView, String str);

    void WebViewClientPageStarted(WebView webView, String str, Bitmap bitmap);

    void WebViewClientReceivedError(WebView webView, int i, String str, String str2);
}
