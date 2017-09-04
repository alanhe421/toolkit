package com.qq.reader.liveshow.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.a.g;
import com.qq.reader.liveshow.utils.p;
import com.qq.reader.liveshow.views.customviews.ProgressBar;
import com.tencent.open.SocialConstants;

public class WebActivity extends Activity {
    protected WebSettings a;
    private RelativeLayout b;
    private ProgressBar c;
    private WebView d;
    private View e;
    private TextView f;
    private View g;
    private boolean h;
    private boolean i;
    private String j;
    private WebChromeClient k;

    private class a extends WebChromeClient {
        final /* synthetic */ WebActivity a;

        private a(WebActivity webActivity) {
            this.a = webActivity;
        }

        public void onProgressChanged(WebView webView, int i) {
            this.a.c.setProgress((double) i);
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

    public static void a(Activity activity, String str, String str2) {
        Intent intent = new Intent(activity, WebActivity.class);
        intent.putExtra(SocialConstants.PARAM_URL, str);
        intent.putExtra("title", str2);
        activity.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(g.activity_web);
        b();
        c();
        b(this.d);
        c(this.d);
        a(this.d);
        d();
        this.j = getIntent().getStringExtra(SocialConstants.PARAM_URL);
        a(this.j, getIntent().getStringExtra("title"));
    }

    private void b() {
        this.b = (RelativeLayout) findViewById(e.content);
        this.c = (ProgressBar) findViewById(e.webprogress);
        this.d = (WebView) findViewById(e.webview);
        this.e = findViewById(e.profile_header_left_back);
        this.f = (TextView) findViewById(e.profile_header_title);
        this.g = findViewById(e.common_titler);
    }

    private void c() {
        try {
            this.a = this.d.getSettings();
            this.a.setRenderPriority(RenderPriority.HIGH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(String str, String str2) {
        this.d.loadUrl(str);
        if (this.d.getVisibility() == 0) {
            this.d.setVisibility(4);
        }
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WebActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        if (str2 != null) {
            this.f.setText(str2);
            this.g.setVisibility(0);
            return;
        }
        this.g.setVisibility(8);
    }

    protected void a(WebView webView) {
        this.k = new a();
        webView.setWebChromeClient(this.k);
    }

    private void b(WebView webView) {
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setCacheMode(2);
        webView.getSettings().setJavaScriptEnabled(true);
        p.b((Context) this);
        p.a(this.d);
    }

    private void c(WebView webView) {
        webView.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ WebActivity a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                this.a.i = true;
                if (!this.a.a(str)) {
                    this.a.j = str;
                }
                if (this.a.c.getVisibility() == 8) {
                    this.a.c.setVisibility(0);
                }
            }

            public void onLoadResource(WebView webView, String str) {
                super.onLoadResource(webView, str);
                if (webView.getVisibility() == 4) {
                    webView.setVisibility(0);
                }
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                if (webView.getVisibility() == 0) {
                    webView.setVisibility(4);
                }
                this.a.i = false;
                if (this.a.h) {
                    this.a.h = false;
                    return;
                }
                this.a.h = true;
                webView.loadUrl(str2);
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                if (this.a.i) {
                    this.a.a(this.a.a(str));
                }
            }
        });
    }

    private boolean a(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("file:///");
    }

    private void a(boolean z) {
        if (this.c.getVisibility() == 0) {
            this.c.setVisibility(8);
        }
    }

    private void d() {
        WebSettings settings = this.d.getSettings();
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

    protected void onDestroy() {
        if (this.d != null) {
            ((ViewGroup) this.d.getParent()).removeView(this.d);
            this.d.destroy();
            this.d = null;
        }
        super.onDestroy();
    }
}
