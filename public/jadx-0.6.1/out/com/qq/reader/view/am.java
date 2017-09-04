package com.qq.reader.view;

import android.os.Build.VERSION;
import android.os.Handler;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import com.qq.reader.activity.SplashActivity;
import com.qq.reader.activity.b;
import com.qq.reader.common.d.a;
import com.tencent.feedback.proguard.R;

/* compiled from: SpalshWebUI */
public class am implements b {
    private SplashActivity a;
    private FixedWebView b;

    public int a() {
        return R.layout.splash_web;
    }

    public void a(SplashActivity splashActivity, Handler handler) {
        this.a = splashActivity;
    }

    public void b() {
        this.b = (FixedWebView) this.a.findViewById(R.id.webview);
        a(this.b);
        this.b.b("file:///android_asset/splash.html");
    }

    public void c() {
        b();
    }

    private void a(FixedWebView fixedWebView) {
        WebSettings settings = fixedWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccess(true);
            settings.setAllowContentAccess(true);
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
    }

    public long d() {
        return 3000;
    }

    public void e() {
        ((ViewGroup) this.b.getParent()).removeView(this.b);
        this.b.stopLoading();
        this.b.destroy();
        this.b = null;
        a.a(this.a);
    }
}
