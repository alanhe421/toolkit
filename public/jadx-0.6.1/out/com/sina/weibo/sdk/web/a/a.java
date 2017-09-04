package com.sina.weibo.sdk.web.a;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import com.sina.weibo.sdk.auth.d;
import com.sina.weibo.sdk.auth.e;
import com.sina.weibo.sdk.b.k;
import com.sina.weibo.sdk.web.b;
import com.sina.weibo.sdk.web.c;

/* compiled from: AuthWebViewClient */
public class a extends b {
    private Context c;
    private boolean d = false;

    public a(b bVar, Context context, com.sina.weibo.sdk.web.b.b bVar2) {
        super(bVar, bVar2);
        this.c = context;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.b != null) {
            this.b.a(webView, str);
        }
        return a(str);
    }

    private boolean a(String str) {
        if (str.startsWith("sms:")) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.putExtra("address", str.replace("sms:", ""));
            intent.setType("vnd.android-dir/mms-sms");
            this.c.startActivity(intent);
            return true;
        } else if (!str.startsWith("sinaweibo://browser/close")) {
            return false;
        } else {
            if (this.a.c() == null || TextUtils.isEmpty(this.a.c().a())) {
                return true;
            }
            String a = this.a.c().a();
            c a2 = c.a();
            if (a2.a(a) != null) {
                a2.a(a).a();
            }
            a2.b(a);
            return true;
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.b != null) {
            this.b.a(webView, str, bitmap);
        }
        if (!str.startsWith(this.a.c().d().b()) || this.d) {
            super.onPageStarted(webView, str, bitmap);
            return;
        }
        this.d = true;
        b(str);
        webView.stopLoading();
        if (this.b != null) {
            this.b.a();
        }
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        if (this.b != null) {
            this.b.b(webView, str);
        }
    }

    @RequiresApi(api = 23)
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        if (this.b != null) {
            this.b.a(webView, webResourceError.getErrorCode(), webResourceError.getDescription().toString(), webResourceRequest.getUrl().toString());
        }
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        if (this.b != null) {
            this.b.a(webView, i, str, str2);
        }
    }

    private void b(String str) {
        Bundle a = k.a(str);
        String string = a.getString("error");
        String string2 = a.getString("error_code");
        String string3 = a.getString("error_description");
        d dVar = null;
        if (!(this.a.c() == null || TextUtils.isEmpty(this.a.c().a()))) {
            String a2 = this.a.c().a();
            c a3 = c.a();
            dVar = a3.a(a2);
            a3.b(a2);
        }
        if (string == null && string2 == null) {
            if (dVar != null) {
                com.sina.weibo.sdk.auth.b a4 = com.sina.weibo.sdk.auth.b.a(a);
                com.sina.weibo.sdk.auth.a.a(this.c, a4);
                dVar.a(a4);
            }
        } else if (dVar != null) {
            dVar.a(new e(string2, string3));
        }
    }

    public void a() {
        super.a();
        if (this.a.c() != null && !TextUtils.isEmpty(this.a.c().a())) {
            String a = this.a.c().a();
            c a2 = c.a();
            if (a2.a(a) != null) {
                a2.a(a).a();
            }
            a2.b(a);
        }
    }

    public boolean b() {
        a();
        if (this.b != null) {
            this.b.a();
        }
        return true;
    }
}
