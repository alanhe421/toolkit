package com.yuewen.ywlogin;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.yuewen.ywlogin.a.c;
import com.yuewen.ywlogin.b.i;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

class j extends WebViewClient {
    final /* synthetic */ YWBrowserActivity a;

    j(YWBrowserActivity yWBrowserActivity) {
        this.a = yWBrowserActivity;
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        Builder builder = new Builder(this.a.a.getContext());
        builder.setMessage(c.notification_error_ssl_cert_invalid);
        builder.setPositiveButton("继续", new k(this, sslErrorHandler));
        builder.setNegativeButton("取消", new l(this, sslErrorHandler));
        builder.create().show();
    }

    public void onPageFinished(WebView webView, String str) {
        this.a.e.setVisibility(8);
        Map a = d.a(CookieManager.getInstance().getCookie(str));
        String str2 = (String) a.get("ywkey");
        String str3 = (String) a.get("ywguid");
        CharSequence charSequence = "";
        long j = 0;
        for (String str4 : a.keySet()) {
            String str42;
            if ("alk".equals(str42)) {
                charSequence = (String) a.get(str42);
            }
            if ("alkts".equals(str42)) {
                str42 = (String) a.get("key");
                j = str42 == null ? 0 : Long.valueOf(str42).longValue();
            }
        }
        if (!TextUtils.isEmpty(charSequence)) {
            try {
                charSequence = URLDecoder.decode(charSequence, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        Log.d("YWLoginSDK", "YWBrowserActivity  autoLoginSessionKey:" + charSequence + "  ;autoLoginExpiredTime:" + j);
        if (!TextUtils.isEmpty(charSequence) && j > 0) {
            i.a().a("YWLogin_AutoLoginSessionKey", charSequence);
            i.a().a("YWLogin_AutoLoginExpiredTime", Long.valueOf(j));
        }
        if (!(TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3))) {
            Intent intent = new Intent();
            intent.putExtra("ywKey", str2);
            intent.putExtra("ywGuid", str3);
            this.a.setResult(-1, intent);
            this.a.finish();
        }
        super.onPageFinished(webView, str);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.a.e.setVisibility(0);
        super.onPageStarted(webView, str, bitmap);
    }
}
