package com.qq.reader.view.web;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.qq.reader.common.offline.b;
import com.qq.reader.common.utils.ao.e;
import com.qq.reader.common.web.js.JSAddToBookShelf;
import com.qq.reader.common.web.js.JSAdv;
import com.qq.reader.common.web.js.JSContent;
import com.qq.reader.common.web.js.JSDetail;
import com.qq.reader.common.web.js.JSLogin;
import com.qq.reader.common.web.js.JSOfflineInterface;
import com.qq.reader.common.web.js.JSReadOnline;
import com.qq.reader.common.web.js.JSToast;
import com.qq.reader.common.web.js.a.c;
import com.qq.reader.view.BaseDialog;
import com.qq.reader.view.FixedWebView;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;

/* compiled from: BaseWebDialog */
public abstract class a extends BaseDialog implements e {
    protected FixedWebView a;
    protected String b = "BaseWebDialog";
    protected Activity c;
    private OnDismissListener d;
    private OnCancelListener e;
    private c i = null;
    private String j;
    private WeakReferenceHandler k = new WeakReferenceHandler(new Callback(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 65539:
                    this.a.dismiss();
                    break;
                case 90004:
                    b bVar = (b) message.obj;
                    this.a.a.a("javascript:" + bVar.a() + "(" + bVar.b() + ")");
                    break;
            }
            return true;
        }
    });

    protected abstract void g();

    public a(Activity activity) {
        this.c = activity;
        g();
        h();
    }

    protected void h() {
        this.a = (FixedWebView) this.f.findViewById(R.id.webview);
        k();
        l();
        m();
        try {
            n();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        com.qq.reader.common.offline.c.a(this.c).a(this.k, this.b);
        this.f.findViewById(R.id.close_btn).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.dismiss();
            }
        });
        this.f.setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onDismiss(DialogInterface dialogInterface) {
                if (this.a.d != null) {
                    this.a.d.onDismiss(dialogInterface);
                }
                if (this.a.a != null) {
                    this.a.a.destroy();
                }
            }
        });
        this.f.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                if (this.a.e != null) {
                    this.a.e.onCancel(dialogInterface);
                }
            }
        });
    }

    public void a(final String str) {
        this.a.post(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                this.b.a.b(str);
            }
        });
    }

    public void f_() {
        try {
            if (!this.c.isFinishing()) {
                this.f.show();
            }
        } catch (Throwable th) {
            com.qq.reader.common.monitor.debug.c.e("BaseWebDialog show", th.getMessage());
        }
    }

    public void dismiss() {
        super.dismiss();
    }

    public final void cancel() {
        this.i.a();
        com.qq.reader.common.offline.c.a(this.c).a(this.b);
        if (!this.c.isFinishing()) {
            super.cancel();
        }
    }

    public void setDestUrl(String str) {
    }

    public void reload() {
        if (this.a != null && this.c != null && !this.c.isFinishing()) {
            if (TextUtils.isEmpty(this.j)) {
                this.a.reload();
                return;
            }
            this.a.b(this.j);
            this.j = null;
        }
    }

    protected Callback i() {
        return null;
    }

    protected com.qq.reader.common.login.a j() {
        return new com.qq.reader.common.login.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (i == 1) {
                    this.a.reload();
                }
            }
        };
    }

    public void k() {
        WebSettings settings = this.a.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccess(true);
            settings.setAllowContentAccess(true);
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
    }

    protected void l() {
        this.a.setWebChromeClient(new WebChromeClient(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onProgressChanged(WebView webView, int i) {
            }

            public void onReceivedTitle(WebView webView, String str) {
                super.onReceivedTitle(webView, str);
            }
        });
    }

    protected void m() {
        this.a.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                com.qq.reader.common.monitor.debug.c.d("zzz", str + "");
                if (str.startsWith("about")) {
                    return false;
                }
                try {
                    if (this.a.i.a(webView, str)) {
                        return true;
                    }
                } catch (Exception e) {
                }
                if (com.qq.reader.qurl.c.b(str)) {
                    try {
                        com.qq.reader.qurl.c.a(this.a.b(), str);
                        return true;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return true;
                    }
                }
                webView.loadUrl(str);
                return true;
            }
        });
    }

    protected void n() {
        this.i = new c();
        this.i.b(this.a);
        this.a.getSettings().setJavaScriptEnabled(true);
        e.a(this.c);
        this.i.a(this.a);
        this.i.a(new JSContent(this.c), "JSContent");
        com.qq.reader.common.web.js.a.a.b jSAdv = new JSAdv(this.k);
        jSAdv.setCallback(i());
        this.i.a(jSAdv, "JSAdv");
        this.i.a(new JSReadOnline(this.c), "readonline");
        this.i.a(new JSDetail(this.c), "JSDetail");
        this.i.a(new JSToast(this.c), "JSToast");
        this.i.a(new JSAddToBookShelf(this.c), "JSAddToShelf");
        this.i.a(new JSOfflineInterface(this.c, this.k, this.b), "mclient");
        jSAdv = new JSLogin(this.c);
        jSAdv.setLoginListener(this);
        jSAdv.setNextLoginTask(j());
        this.i.a(jSAdv, "readerlogin");
    }
}
