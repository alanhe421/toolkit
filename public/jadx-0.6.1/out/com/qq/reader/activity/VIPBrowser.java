package com.qq.reader.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.common.web.js.JSSendSMS;
import com.qq.reader.common.web.js.a.c;
import com.qq.reader.view.ProgressBar;
import com.qq.reader.view.linearmenu.a;
import com.qq.reader.view.linearmenu.b;
import com.tencent.feedback.proguard.R;
import com.tencent.mobileqq.openpay.constants.OpenConstants;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class VIPBrowser extends ReaderBaseActivity {
    c a = null;
    private Context b;
    private ProgressBar c;
    private TextView d;
    private WebView e;
    private ImageView f;
    private b g;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = getApplicationContext();
        setContentView(R.layout.vipwebpage);
        final String stringExtra = getIntent().getStringExtra("com.qq.reader.webbrowser.url");
        int intExtra = getIntent().getIntExtra("com.qq.reader.webbrowser.title", -1);
        this.c = (ProgressBar) findViewById(R.id.viploadprogress);
        this.d = (TextView) findViewById(R.id.profile_header_title);
        if (intExtra != -1) {
            this.d.setText(intExtra);
        }
        this.e = (WebView) findViewById(R.id.vip_webview);
        this.e.setScrollBarStyle(SigType.WLOGIN_DA2);
        a();
        e();
        b();
        c();
        this.e.removeJavascriptInterface("searchBoxJavaBridge_");
        this.e.removeJavascriptInterface("accessibility");
        this.e.removeJavascriptInterface("accessibilityTraversal");
        this.e.post(new Runnable(this) {
            final /* synthetic */ VIPBrowser b;

            public void run() {
                this.b.e.loadUrl(stringExtra);
            }
        });
        this.f = (ImageView) findViewById(R.id.profile_header_left_back);
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VIPBrowser a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
    }

    protected void onDestroy() {
        this.a.a();
        super.onDestroy();
    }

    public void a() {
        if (this.e.getSettings().getUseWideViewPort()) {
            this.e.setInitialScale(25);
        }
    }

    private void e() {
        this.e.getSettings().setJavaScriptEnabled(true);
        this.a = new c();
        this.a.a(this.e);
        this.a.a(new JSSendSMS(this), "sendvip");
        this.a.a(new JSPay(this, this.e), OpenConstants.API_NAME_PAY);
    }

    protected void b() {
        this.e.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ VIPBrowser a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                String str2 = null;
                if (!this.a.a.a(webView, str)) {
                    if (com.qq.reader.qurl.c.b(str)) {
                        try {
                            com.qq.reader.qurl.c.a(this.a, str);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (str == null || !str.toLowerCase().startsWith("sms:")) {
                        webView.loadUrl(str);
                    } else {
                        String substring;
                        int indexOf = str.indexOf("?");
                        if (indexOf != -1) {
                            substring = str.substring(4, indexOf);
                            if (indexOf < str.length()) {
                                str2 = str.substring(indexOf + 6, str.length());
                            }
                        } else {
                            substring = null;
                        }
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                        if (!(substring == null || str2 == null)) {
                            intent.putExtra("sms_body", str2);
                            intent.putExtra("address", substring);
                            intent.setType("vnd.android-dir/mms-sms");
                        }
                        this.a.startActivity(intent);
                    }
                }
                return true;
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                webView.loadUrl("file:///android_asset/bookstore/error.html");
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                if (this.a.c.getVisibility() != 0) {
                    this.a.c.setVisibility(0);
                }
            }

            public void onPageFinished(WebView webView, String str) {
                if (this.a.c.getVisibility() != 4) {
                    this.a.c.setVisibility(4);
                    String charSequence = this.a.d.getText().toString();
                    if (charSequence == null || charSequence.trim().length() <= 0) {
                        this.a.d.setText(this.a.e.getTitle());
                    }
                }
            }
        });
    }

    protected void c() {
        this.e.setWebChromeClient(new WebChromeClient(this) {
            final /* synthetic */ VIPBrowser a;

            {
                this.a = r1;
            }

            public void onProgressChanged(WebView webView, int i) {
                this.a.c.setProgress((double) i);
            }

            public void onReceivedTitle(WebView webView, String str) {
                String charSequence = this.a.d.getText().toString();
                if (charSequence == null || charSequence.trim().length() <= 0) {
                    this.a.d.setText(this.a.e.getTitle());
                }
            }
        });
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        d().f_();
        return true;
    }

    public b d() {
        if (this.g == null) {
            this.g = new b(this);
            this.g.a(1, "刷新", null);
            this.g.a(new a.b(this) {
                final /* synthetic */ VIPBrowser a;

                {
                    this.a = r1;
                }

                public boolean a(int i, Bundle bundle) {
                    return this.a.a(i, bundle);
                }
            });
        }
        return this.g;
    }

    protected boolean a(int i, Bundle bundle) {
        switch (i) {
            case 1:
                this.e.reload();
                return true;
            default:
                return false;
        }
    }
}
