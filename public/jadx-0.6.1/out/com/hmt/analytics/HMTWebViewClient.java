package com.hmt.analytics;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HMTWebViewClient extends WebViewClient {
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        HMTAgent.onReqUrl(str);
        super.onPageStarted(webView, str, bitmap);
    }
}
