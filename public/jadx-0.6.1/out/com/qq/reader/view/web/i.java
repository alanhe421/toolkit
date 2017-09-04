package com.qq.reader.view.web;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import com.bumptech.glide.request.e;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.offline.b;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.t;
import com.qq.reader.common.web.js.JSAddToBookShelf;
import com.qq.reader.common.web.js.JSAdv;
import com.qq.reader.common.web.js.JSContent;
import com.qq.reader.common.web.js.JSDetail;
import com.qq.reader.common.web.js.JSLogin;
import com.qq.reader.common.web.js.JSOfflineInterface;
import com.qq.reader.common.web.js.JSReadOnline;
import com.qq.reader.common.web.js.JSSns;
import com.qq.reader.common.web.js.JSToast;
import com.qq.reader.common.web.js.a.c;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.view.BaseDialog;
import com.qq.reader.view.RoundImageView;
import com.qq.reader.view.RoundWebView;
import com.qq.reader.view.r;
import com.tencent.feedback.proguard.R;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import com.tencent.util.WeakReferenceHandler;
import java.util.HashMap;
import java.util.Map;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: PopNativeDialog */
public class i extends BaseDialog implements e {
    protected OnCancelListener a;
    protected RoundWebView b;
    protected RoundImageView c;
    protected ViewGroup d;
    protected Activity e;
    protected int i;
    protected final a j = new a(this);
    private OnDismissListener k;
    private OnCancelListener l;
    private String m;
    private boolean n;
    private c o = null;
    private long p;
    private String q = "";

    /* compiled from: PopNativeDialog */
    private class a extends Handler {
        final /* synthetic */ i a;

        public a(i iVar) {
            this.a = iVar;
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

    public void b(OnCancelListener onCancelListener) {
        this.a = onCancelListener;
    }

    public void a(r rVar) {
        this.k = rVar;
    }

    public void a(OnCancelListener onCancelListener) {
        this.l = onCancelListener;
    }

    public i(Activity activity, final int i, int i2) {
        this.e = activity;
        this.i = i;
        if (this.f == null) {
            a(activity, null, R.layout.nativeadv_window, 0, false);
            this.f.setCanceledOnTouchOutside(false);
            this.f.setCancelable(true);
            this.b = (RoundWebView) this.f.findViewById(R.id.advwebview);
            this.b.setBackgroundColor(0);
            this.c = (RoundImageView) this.f.findViewById(R.id.adv_img);
            this.d = (ViewGroup) this.f.findViewById(R.id.adv_mask_container);
            a(this.c);
            m();
            this.f.findViewById(R.id.close_btn).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ i b;

                public void onClick(View view) {
                    if (i != 1) {
                        this.b.b.destroy();
                    }
                    this.b.dismiss();
                    this.b.g();
                }
            });
            switch (i) {
                case 0:
                    i();
                    break;
                case 1:
                    b(i2);
                    break;
                case 2:
                    h();
                    break;
            }
            n();
            o();
            try {
                k();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            com.qq.reader.common.offline.c.a(this.e).a(this.j, "WEBDIALOG");
            this.f.setOnDismissListener(new r(this) {
                final /* synthetic */ i a;

                {
                    this.a = r1;
                }

                public t a() {
                    return this.a.c();
                }

                public void onDismiss(DialogInterface dialogInterface) {
                    this.a.j();
                    if (this.a.k != null) {
                        this.a.k.onDismiss(dialogInterface);
                    }
                    if (this.a.b != null) {
                        this.a.b.destroy();
                    }
                }
            });
            this.f.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ i a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    if (this.a.l != null) {
                        this.a.l.onCancel(dialogInterface);
                    }
                }
            });
        }
        this.f.getWindow().addFlags(2);
    }

    private void a(RoundImageView roundImageView) {
        if (roundImageView != null) {
            t.a((ImageView) roundImageView);
            this.n = d.n;
        }
    }

    protected void g() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, String.valueOf(this.p));
        com.qq.reader.common.monitor.i.a("event_B219", hashMap, e());
    }

    protected void h() {
        this.b.setRadius((float) ao.a(5.0f));
        this.b.setVisibility(0);
        this.c.setVisibility(8);
    }

    protected void b(int i) {
        this.b.setVisibility(8);
        this.c.setVisibility(0);
        this.c = (RoundImageView) this.f.findViewById(R.id.adv_img);
        this.c.setRadius((float) ao.a(2.0f));
        if (i == 17) {
            this.f.setCanceledOnTouchOutside(true);
        } else {
            this.f.setCanceledOnTouchOutside(false);
        }
    }

    protected void i() {
        this.b.setVisibility(0);
        this.b.setRadius((float) ao.a(5.0f));
        this.c.setVisibility(8);
    }

    public void a(com.qq.reader.cservice.adv.a aVar, WeakReferenceHandler weakReferenceHandler) {
        String str;
        com.qq.reader.cservice.adv.b.a = true;
        if (aVar.q() == 2) {
            str = "file://" + com.qq.reader.cservice.adv.b.a(aVar) + "index.html";
        } else {
            str = aVar.h();
        }
        this.b.post(new Runnable(this) {
            final /* synthetic */ i b;

            public void run() {
                this.b.b.b(str);
            }
        });
        Message obtainMessage = weakReferenceHandler.obtainMessage();
        obtainMessage.obj = aVar;
        obtainMessage.what = 65538;
        weakReferenceHandler.sendMessage(obtainMessage);
    }

    private void a(com.qq.reader.cservice.adv.a aVar) {
        j.a((int) Opcodes.NOT_LONG, 0);
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, String.valueOf(aVar.d()));
        com.qq.reader.common.monitor.i.a("event_C108", hashMap, ReaderApplication.getApplicationImp());
        StatisticsManager.a().a("event_C108", hashMap);
        String h = aVar.h();
        if (com.qq.reader.qurl.c.a(h)) {
            try {
                com.qq.reader.qurl.c.a(this.e, h);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(this.e, WebBrowserForContents.class);
            intent.setFlags(SigType.WLOGIN_QRPUSH);
            intent.putExtra("com.qq.reader.WebContent", h);
            this.e.startActivity(intent);
        }
        if (this.f.isShowing() && !this.e.isFinishing()) {
            this.f.dismiss();
        }
    }

    public void b(final com.qq.reader.cservice.adv.a aVar, final WeakReferenceHandler weakReferenceHandler) {
        com.qq.reader.cservice.adv.b.a = true;
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, String.valueOf(aVar.d()));
        com.qq.reader.common.monitor.i.a("event_A161", hashMap, ReaderApplication.getApplicationImp());
        StatisticsManager.a().a("event_A161", hashMap);
        if (this.i == 1) {
            String g = aVar.g();
            this.p = aVar.d();
            com.qq.reader.common.imageloader.c.a(e()).a(g, this.c, com.qq.reader.common.imageloader.a.a().d((int) e().getResources().getDimension(R.dimen.game_adv_dialog_img_width), (int) e().getResources().getDimension(R.dimen.game_adv_dialog_img_height)), new e<String, com.bumptech.glide.load.resource.a.b>(this) {
                final /* synthetic */ i c;

                public boolean a(Exception exception, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, String.valueOf(aVar.d()));
                    com.qq.reader.common.monitor.i.a("event_A257", hashMap, ReaderApplication.getApplicationImp());
                    com.qq.reader.cservice.adv.b.a = false;
                    if (this.c.a != null) {
                        this.c.a.onCancel(this.c.f);
                    }
                    return false;
                }

                public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, com.bumptech.glide.request.b.j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                    this.c.c.setVisibility(0);
                    this.c.c.setOnClickListener(new 1(this));
                    Message obtainMessage = weakReferenceHandler.obtainMessage();
                    obtainMessage.obj = aVar;
                    obtainMessage.what = 65538;
                    weakReferenceHandler.sendMessage(obtainMessage);
                    com.qq.reader.cservice.adv.b.a = false;
                    return false;
                }
            });
        } else if (this.i == 0) {
            a(aVar, weakReferenceHandler);
        } else if (this.i == 2) {
            a(aVar, weakReferenceHandler);
        }
    }

    public void f_() {
        try {
            if (this.e != null && !this.e.isFinishing()) {
                k();
                com.qq.reader.common.offline.c.a(this.e).a(this.j, "WEBDIALOG");
                if (this.e != null && !this.e.isFinishing() && !com.qq.reader.cservice.adv.b.b && !com.qq.reader.module.rookie.presenter.a.a().e()) {
                    p();
                    try {
                        this.f.show();
                        com.qq.reader.cservice.adv.b.b = true;
                        if (this.i == 1) {
                            Map hashMap = new HashMap();
                            hashMap.put(s.ORIGIN, String.valueOf(this.p));
                            com.qq.reader.common.monitor.i.a("event_B218", hashMap, e());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        j();
                        if (this.a != null) {
                            this.a.onCancel(this.f);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            com.qq.reader.common.monitor.debug.c.e("PopNativeDailog", e2.getMessage());
        }
    }

    public final void dismiss() {
        try {
            this.o.a();
            com.qq.reader.common.offline.c.a(this.e).a("WEBDIALOG");
            if (!this.e.isFinishing()) {
                super.dismiss();
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("PopNativeDialog", e.getMessage());
        }
    }

    protected void j() {
        com.qq.reader.cservice.adv.b.b = false;
        com.qq.reader.common.offline.c.a(this.e).a("WEBDIALOG");
        com.qq.reader.common.monitor.debug.c.d("ROOKIE", "adv dialog dismiss");
    }

    public final void cancel() {
        this.o.a();
        com.qq.reader.common.offline.c.a(this.e).a("WEBDIALOG");
        if (!this.e.isFinishing()) {
            super.cancel();
        }
    }

    public void setDestUrl(String str) {
        this.m = str;
    }

    public void reload() {
        if (this.b != null && this.e != null && !this.e.isFinishing()) {
            if (TextUtils.isEmpty(this.m)) {
                this.b.reload();
                return;
            }
            this.b.b(this.m);
            this.m = null;
        }
    }

    protected void k() {
        this.o = new c();
        this.o.b(this.b);
        this.b.getSettings().setJavaScriptEnabled(true);
        ao.e.a(this.e);
        this.o.a(this.b);
        this.o.a(new JSContent(this.e), "JSContent");
        com.qq.reader.common.web.js.a.a.b jSAdv = new JSAdv(this.j);
        jSAdv.setCallback(l());
        this.o.a(jSAdv, "JSAdv");
        this.o.a(new JSReadOnline(this.e), "readonline");
        this.o.a(new JSDetail(this.e), "JSDetail");
        this.o.a(new JSToast(this.e), "JSToast");
        this.o.a(new JSAddToBookShelf(this.e), "JSAddToShelf");
        this.o.a(new JSOfflineInterface(this.e, this.j, "WEBDIALOG"), "mclient");
        this.o.a(new JSSns(this.e), "JSSns");
        jSAdv = new JSLogin(this.e);
        jSAdv.setLoginListener(this);
        jSAdv.setNextLoginTask(q());
        this.o.a(jSAdv, "readerlogin");
    }

    protected Callback l() {
        return null;
    }

    public void m() {
        WebSettings settings = this.b.getSettings();
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

    protected void n() {
        this.b.setWebChromeClient(new WebChromeClient(this) {
            final /* synthetic */ i a;

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

    protected void a(WebView webView, String str) {
    }

    protected void a(WebView webView, String str, Bitmap bitmap) {
        webView.loadUrl(this.q);
    }

    protected void o() {
        this.b.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                this.a.a(webView, str);
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                this.a.a(webView, str, bitmap);
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
                    if (this.a.o.a(webView, str)) {
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

    protected void p() {
        if (this.i != 1) {
            c().a((int) R.id.adv_mask_container);
            c().b();
        } else if (d.n != this.n) {
            a(this.c);
        }
    }

    protected com.qq.reader.common.login.a q() {
        return new com.qq.reader.common.login.a(this) {
            final /* synthetic */ i a;

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
}
