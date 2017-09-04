package oicq.wlogin_sdk.quicklogin;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import oicq.wlogin_sdk.tools.util;

/* compiled from: QuickLoginWebViewLoader */
final class j extends WebViewClient {
    final /* synthetic */ Activity a;
    final /* synthetic */ WebView b;

    j(Activity activity, WebView webView) {
        this.a = activity;
        this.b = webView;
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        util.LOGI("ssl error " + sslError.getPrimaryError());
        if (true == QuickLoginWebViewLoader.sslErrorProcessed) {
            sslErrorHandler.proceed();
            return;
        }
        Builder builder = new Builder(this.a);
        builder.setMessage("页面证书错误(" + sslError.getPrimaryError() + ")，是否继续？");
        builder.setPositiveButton("继续", new k(this, sslErrorHandler));
        builder.setNegativeButton("取消", new l(this, sslErrorHandler));
        try {
            builder.create().show();
        } catch (Exception e) {
        }
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str == null) {
            return false;
        }
        try {
            webView.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            return true;
        } catch (Exception e) {
            StringBuilder append = new StringBuilder().append("load url ");
            if (str == null) {
                str = "";
            }
            util.LOGI(append.append(str).append(" failed ").append(e.getMessage()).toString(), "");
            return true;
        }
    }

    public void onPageFinished(WebView webView, String str) {
        if (VERSION.SDK_INT >= 24) {
            this.b.loadUrl("javascript:function wtCB(uin, sig) { WTLogin.ptloginCallBack(uin, sig); }");
        }
    }
}
