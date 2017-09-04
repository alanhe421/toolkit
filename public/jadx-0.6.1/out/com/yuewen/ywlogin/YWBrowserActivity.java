package com.yuewen.ywlogin;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.yuewen.ywlogin.a.a;
import com.yuewen.ywlogin.a.b;

public class YWBrowserActivity extends Activity {
    private WebView a;
    private View b;
    private TextView c;
    private String d;
    private ProgressBar e;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(b.ywlogin_activity_browser);
        this.a = (WebView) findViewById(a.mWebViewLayout);
        this.b = findViewById(a.btnBack);
        this.c = (TextView) findViewById(a.mTitle);
        this.e = (ProgressBar) findViewById(a.browser_progress);
        this.e.setMax(100);
        this.e.setVisibility(8);
        this.d = getIntent().getStringExtra("Url");
        Log.e("YWLoginSDK", "The Url of WebLogin is : " + this.d);
        a();
        this.b.setOnClickListener(new h(this));
    }

    private void a() {
        this.a.setWebChromeClient(new i(this));
        this.a.setWebViewClient(new j(this));
        this.a.setScrollBarStyle(0);
        this.a.requestFocusFromTouch();
        try {
            WebSettings settings = this.a.getSettings();
            if (settings != null) {
                settings.setJavaScriptEnabled(true);
                settings.setJavaScriptCanOpenWindowsAutomatically(true);
                settings.setDomStorageEnabled(true);
                settings.setDatabaseEnabled(true);
                settings.setAppCacheEnabled(true);
                settings.setUseWideViewPort(true);
                settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
                settings.setLoadWithOverviewMode(true);
                this.a.loadUrl(this.d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        a((Context) this);
        a(this.a);
    }

    private void a(WebView webView) {
        if (VERSION.SDK_INT >= 11) {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.a.canGoBack()) {
            this.a.goBack();
        } else {
            setResult(0);
            finish();
        }
        return true;
    }

    protected void onDestroy() {
        a((Context) this);
        super.onDestroy();
    }

    public static void a(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        instance.removeSessionCookie();
        if (VERSION.SDK_INT < 21) {
            CookieSyncManager.getInstance().sync();
        } else {
            instance.flush();
        }
    }
}
