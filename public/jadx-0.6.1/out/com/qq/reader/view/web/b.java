package com.qq.reader.view.web;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.web.c;
import com.qq.reader.common.web.js.JSContent;
import com.qq.reader.common.web.js.JSDownLoad;
import com.qq.reader.common.web.js.JSGoToWeb;
import com.qq.reader.common.web.js.JSLogin;
import com.qq.reader.common.web.js.JSOfflineInterface;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.common.web.js.JSReload;
import com.qq.reader.common.web.js.JSSendSMS;
import com.qq.reader.common.web.js.JSToast;
import com.qq.reader.view.BaseDialog;
import com.qq.reader.view.FixedWebView;
import com.qq.reader.view.h;
import com.tencent.feedback.proguard.R;
import com.tencent.mobileqq.openpay.constants.OpenConstants;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: CommonBuyDialog */
public class b extends BaseDialog implements c, h {
    private FixedWebView a;
    private Button b;
    private String c;
    private View d;
    private Activity e;
    private String i = null;
    private int j = -1;
    private boolean k;
    private com.qq.reader.common.web.js.a.c l = null;
    private String m = "";
    private final a n = new a();
    private boolean o = false;

    /* compiled from: CommonBuyDialog */
    private class a extends Handler {
        final /* synthetic */ b a;

        private a(b bVar) {
            this.a = bVar;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 90004:
                    com.qq.reader.common.offline.b bVar = (com.qq.reader.common.offline.b) message.obj;
                    this.a.a.a("javascript:" + bVar.a() + "(" + bVar.b() + ")");
                    return;
                default:
                    return;
            }
        }
    }

    public b(Activity activity, String str, String str2) {
        this.c = str;
        this.e = activity;
        if (this.f == null) {
            a(activity, null, R.layout.dialog_common_buy, 0, false);
            this.f.a(this);
            this.f.setCanceledOnTouchOutside(false);
            this.b = (Button) this.f.findViewById(R.id.dialog_common_buy_close);
            this.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.f.cancel();
                }
            });
            this.a = (FixedWebView) this.f.findViewById(R.id.dialog_common_buy_webview);
            this.a.setScrollBarStyle(SigType.WLOGIN_DA2);
            g();
            m();
            h();
            this.a.removeJavascriptInterface("searchBoxJavaBridge_");
            this.a.removeJavascriptInterface("accessibility");
            this.a.removeJavascriptInterface("accessibilityTraversal");
            this.d = this.f.findViewById(R.id.dialog_common_buy_load);
        }
    }

    public void d() {
        this.l.a();
        com.qq.reader.common.offline.c.a(this.e).a("COMMONBUY");
    }

    public void a(String str, int i) {
        this.c = str;
        this.j = i;
        this.d.setVisibility(0);
        this.a.clearView();
        this.a.setVisibility(8);
        l();
        com.qq.reader.common.offline.c.a(this.e).a(this.n, "COMMONBUY");
        this.a.post(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a.b(this.a.c);
                this.a.m = this.a.c;
            }
        });
        super.f_();
    }

    public void retry() {
        this.a.post(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.a.copyBackForwardList().getCurrentItem().getUrl().contains("/web_error.html") && this.a.m != null && this.a.m.trim().length() > 0) {
                    this.a.a.b(this.a.m);
                }
            }
        });
    }

    public void g() {
        if (this.a.getSettings().getUseWideViewPort()) {
            this.a.setInitialScale(25);
        }
    }

    private void l() {
        this.l = new com.qq.reader.common.web.js.a.c();
        this.l.b(this.a);
        this.a.getSettings().setJavaScriptEnabled(true);
        this.l.a(this.a);
        this.l.a(new JSDownLoad(this.e, true), "downloadbook");
        this.l.a(new JSLogin(this.e), "readerlogin");
        this.l.a(new JSContent(this.e), "JSContent");
        this.l.a(new JSSendSMS(this.e), "sendvip");
        this.l.a(new JSPay(this.e, this.a), OpenConstants.API_NAME_PAY);
        this.l.a(new JSToast(this.e), "JSToast");
        this.l.a(new JSReload(this.e, this), "JSReload");
        this.l.a(new JSGoToWeb(this.e), "JSGoToWeb");
        this.l.a(new JSOfflineInterface(this.e, this.n, "COMMONBUY"), "mclient");
    }

    protected void h() {
        this.a.setWebChromeClient(new WebChromeClient(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onProgressChanged(WebView webView, int i) {
            }

            public void onReceivedTitle(WebView webView, String str) {
            }
        });
    }

    private void m() {
        this.a.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onPageFinished(WebView webView, String str) {
                this.a.d.setVisibility(8);
                this.a.a.setVisibility(0);
                this.a.a.requestFocus();
                super.onPageFinished(webView, str);
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                if (!(str == null || str.contains("/web_error.html"))) {
                    this.a.m = str;
                }
                super.onPageStarted(webView, str, bitmap);
            }

            public void onLoadResource(WebView webView, String str) {
                super.onLoadResource(webView, str);
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                if (this.a.o) {
                    webView.loadUrl(e.a(1));
                    return;
                }
                webView.loadUrl(str2);
                this.a.o = true;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                String str2 = null;
                if (str.startsWith("about")) {
                    return false;
                }
                if (this.a.l.a(webView, str)) {
                    return true;
                }
                if (com.qq.reader.qurl.c.b(str)) {
                    try {
                        com.qq.reader.qurl.c.a(this.a.b(), str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                if (str == null || !str.toLowerCase().startsWith("sms:")) {
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
                        intent.setData(Uri.parse("sms:111111"));
                        intent.putExtra("sms_body", str2);
                        intent.putExtra("address", substring);
                        intent.setType("vnd.android-dir/mms-sms");
                    }
                    this.a.e.startActivity(intent);
                }
                return true;
            }
        });
    }

    public boolean i() {
        if (!this.a.canGoBack()) {
            return false;
        }
        String url = this.a.copyBackForwardList().getCurrentItem().getUrl();
        if (url.equals(this.c) || url.equals("file:///android_asset/bookstore/web_error.html")) {
            return false;
        }
        return true;
    }

    public void j() {
        this.a.goBack();
        this.a.invalidate();
    }

    public void a(MotionEvent motionEvent) {
    }

    public boolean a(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (i()) {
                j();
                return true;
            }
        } else if (i == 82) {
            return true;
        }
        return false;
    }

    public boolean k() {
        return this.k;
    }

    public void a(boolean z) {
        this.k = z;
    }
}
