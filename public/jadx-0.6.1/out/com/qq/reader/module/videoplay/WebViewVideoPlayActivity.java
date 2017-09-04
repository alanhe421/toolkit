package com.qq.reader.module.videoplay;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.a.c;
import com.qq.reader.view.ProgressBar;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;

public class WebViewVideoPlayActivity extends Activity {
    protected WebSettings a;
    public c b = null;
    private RelativeLayout c;
    private ProgressBar d;
    private View e;
    private View f;
    private WebView g;
    private boolean h;
    private boolean i;
    private String j;
    private WebChromeClient k;

    private class a extends WebChromeClient {
        final /* synthetic */ WebViewVideoPlayActivity a;

        private a(WebViewVideoPlayActivity webViewVideoPlayActivity) {
            this.a = webViewVideoPlayActivity;
        }

        public void onProgressChanged(WebView webView, int i) {
            this.a.d.setProgress((double) i);
        }

        public void onHideCustomView() {
            super.onHideCustomView();
            this.a.a();
        }

        public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
            super.onShowCustomView(view, customViewCallback);
            this.a.a();
        }

        public void onShowCustomView(View view, int i, CustomViewCallback customViewCallback) {
            super.onShowCustomView(view, i, customViewCallback);
            this.a.a();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_web_view_video_play);
        b();
        c();
        b(this.g);
        c(this.g);
        a(this.g);
        d();
        this.j = getIntent().getStringExtra(SocialConstants.PARAM_URL);
        a(this.j);
    }

    private void b() {
        this.c = (RelativeLayout) findViewById(R.id.h5game_content);
        this.d = (ProgressBar) findViewById(R.id.webprogress);
        this.e = findViewById(R.id.loading_layout);
        this.f = findViewById(R.id.loading_failed_layout);
        this.g = (WebView) findViewById(R.id.h5game_webview);
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WebViewVideoPlayActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.f.setVisibility(8);
                this.a.e.setVisibility(0);
                this.a.a(this.a.j);
            }
        });
    }

    private void c() {
        try {
            this.a = this.g.getSettings();
            ao.a(this, this.a);
            this.a.setRenderPriority(RenderPriority.HIGH);
            this.a.setJavaScriptEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(String str) {
        this.g.loadUrl(str);
        if (this.e.getVisibility() == 8) {
            this.e.setVisibility(0);
        }
        if (this.g.getVisibility() == 0) {
            this.g.setVisibility(4);
        }
    }

    protected void a(WebView webView) {
        this.k = new a();
        webView.setWebChromeClient(this.k);
    }

    private void b(WebView webView) {
        this.b = new c();
        this.b.b(webView);
        this.b.a(webView);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setCacheMode(2);
    }

    private void c(WebView webView) {
        webView.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ WebViewVideoPlayActivity a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (this.a.b == null || !this.a.b.a(webView, str)) {
                    webView.loadUrl(str);
                }
                return true;
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                this.a.i = true;
                if (!this.a.b(str)) {
                    this.a.j = str;
                }
                if (this.a.d.getVisibility() == 8) {
                    this.a.d.setVisibility(0);
                }
            }

            public void onLoadResource(WebView webView, String str) {
                super.onLoadResource(webView, str);
                if (this.a.e.getVisibility() == 0) {
                    this.a.e.setVisibility(8);
                }
                if (this.a.g.getVisibility() == 4) {
                    this.a.g.setVisibility(0);
                }
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                if (this.a.g.getVisibility() == 0) {
                    this.a.g.setVisibility(4);
                }
                this.a.i = false;
                if (this.a.h) {
                    this.a.h = false;
                    webView.loadUrl("file:///android_asset/empty.html");
                    return;
                }
                this.a.h = true;
                this.a.g.loadUrl(str2);
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                if (this.a.i) {
                    this.a.a(this.a.b(str));
                }
            }
        });
    }

    private boolean b(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("file:///");
    }

    private void a(boolean z) {
        if (z) {
            this.f.setVisibility(0);
        } else {
            this.f.setVisibility(8);
        }
        if (this.d.getVisibility() == 0) {
            this.d.setVisibility(8);
        }
        if (this.e.getVisibility() == 0) {
            this.e.setVisibility(8);
        }
    }

    private void d() {
        WebSettings settings = this.g.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
    }

    public void a() {
        if (getRequestedOrientation() != 0) {
            setRequestedOrientation(0);
        } else {
            setRequestedOrientation(1);
        }
        int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
        if (VERSION.SDK_INT >= 14) {
            systemUiVisibility ^= 2;
        }
        if (VERSION.SDK_INT >= 16) {
            systemUiVisibility ^= 4;
        }
        if (VERSION.SDK_INT >= 19) {
            systemUiVisibility ^= 4096;
        }
        getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
    }
}
