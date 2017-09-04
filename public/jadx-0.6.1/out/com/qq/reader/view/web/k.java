package com.qq.reader.view.web;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.offline.b;
import com.qq.reader.common.utils.aj;
import com.qq.reader.common.utils.ao.e;
import com.qq.reader.common.web.js.JSAdv;
import com.qq.reader.common.web.js.JSContent;
import com.qq.reader.common.web.js.JSOfflineInterface;
import com.qq.reader.common.web.js.a.c;
import com.qq.reader.view.BaseDialog;
import com.qq.reader.view.FixedWebView;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;

/* compiled from: PopNetWebDialog */
public class k extends BaseDialog {
    public int a = 0;
    private FixedWebView b;
    private ImageView c;
    private Activity d;
    private c e = null;
    private final a i = new a(this);

    /* compiled from: PopNetWebDialog */
    private class a extends Handler {
        final /* synthetic */ k a;

        public a(k kVar) {
            this.a = kVar;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 65539:
                    this.a.dismiss();
                    return;
                case 90004:
                    b bVar = (b) message.obj;
                    this.a.b.a("javascript:" + bVar.a() + "(" + bVar.b() + ")");
                    return;
                default:
                    return;
            }
        }
    }

    public k(Activity activity, int i) {
        this.d = activity;
        this.a = i;
        if (this.f == null) {
            a(activity, null, R.layout.pop_web_dialog, true, true, true);
            LayoutParams attributes = this.f.getWindow().getAttributes();
            LayoutParams attributes2 = activity.getWindow().getAttributes();
            attributes.width = attributes2.width;
            attributes.height = attributes2.height;
            this.f.getWindow().setAttributes(attributes);
            aj.a(this.f, true);
            this.f.setCanceledOnTouchOutside(true);
            this.f.setCancelable(true);
            this.c = (ImageView) this.f.findViewById(R.id.pop_webview_close);
            this.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ k a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.f.cancel();
                }
            });
            this.b = (FixedWebView) this.f.findViewById(R.id.pop_webview);
            this.b.setBackgroundColor(0);
            switch (i) {
                case 0:
                    this.b.setVisibility(0);
                    break;
            }
            g();
            i();
            try {
                h();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        this.f.getWindow().addFlags(2);
    }

    public void a(final String str, WeakReferenceHandler weakReferenceHandler) {
        this.b.post(new Runnable(this) {
            final /* synthetic */ k b;

            public void run() {
                this.b.b.b(str);
            }
        });
        Message obtainMessage = weakReferenceHandler.obtainMessage();
        obtainMessage.what = 65541;
        weakReferenceHandler.sendMessage(obtainMessage);
    }

    public void f_() {
        if (this.d != null && !this.d.isFinishing()) {
            h();
            super.f_();
        }
    }

    public void dismiss() {
        this.e.a();
        if (!this.d.isFinishing()) {
            super.dismiss();
        }
    }

    public void cancel() {
        this.e.a();
        if (!this.d.isFinishing()) {
            super.cancel();
        }
    }

    private void h() {
        this.e = new c();
        this.e.b(this.b);
        this.b.getSettings().setJavaScriptEnabled(true);
        e.a(this.d);
        this.e.a(this.b);
        this.e.a(new JSContent(this.d), "JSContent");
        this.e.a(new JSAdv(this.i), "JSAdv");
        this.e.a(new JSOfflineInterface(this.d.getApplicationContext(), this.i, "JS_PopNetWebDialog"), "mclient");
    }

    protected void g() {
        this.b.setWebChromeClient(new WebChromeClient(this) {
            final /* synthetic */ k a;

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

    private void i() {
        this.b.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                i.a("event_A149", null, ReaderApplication.getApplicationImp());
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.startsWith("about")) {
                    return false;
                }
                try {
                    if (this.a.e.a(webView, str)) {
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
}
