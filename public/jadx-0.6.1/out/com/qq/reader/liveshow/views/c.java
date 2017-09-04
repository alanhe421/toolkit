package com.qq.reader.liveshow.views;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.a.h;
import com.qq.reader.liveshow.c.a.c.a;
import com.qq.reader.liveshow.c.f;
import com.qq.reader.liveshow.c.i;
import com.qq.reader.liveshow.c.j;
import com.qq.reader.liveshow.model.filter.a.b;
import com.qq.reader.liveshow.model.im.viewdata.GiftItem;
import com.qq.reader.liveshow.utils.SxbLog;
import com.qq.reader.liveshow.utils.p;
import com.qq.reader.liveshow.utils.q;
import com.qq.reader.liveshow.views.customviews.FixedWebView;
import com.qq.reader.liveshow.views.customviews.MultiClickGiftLayout;

/* compiled from: LiveGiftProxy */
public class c implements Callback, a {
    LiveActivity a;
    f b;
    private final long c = 6500;
    private MultiClickGiftLayout d;
    private FixedWebView e;
    private View f;
    private ImageView g;
    private TextView h;
    private TextView i;
    private String j = "";
    private long k = 6500;
    private Handler l;
    private final int m = 10010;
    private final int n = 10011;
    private com.qq.reader.liveshow.model.im.message.a.c o;
    private boolean p = true;

    public c(LiveActivity liveActivity, j jVar) {
        this.a = liveActivity;
        this.b = (f) e();
        this.b.a(jVar);
        this.l = new Handler(this);
        d();
    }

    public void d() {
        this.d = (MultiClickGiftLayout) this.a.findViewById(e.multiGift_layout);
        this.d.setPositionCallback(new com.qq.reader.liveshow.b.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(b bVar) {
                this.a.b.a(bVar);
            }
        });
        this.f = this.a.findViewById(e.big_gift_sender_info);
        this.g = (ImageView) this.a.findViewById(e.big_gift_avatar_icon);
        this.h = (TextView) this.a.findViewById(e.big_gift_user_name);
        this.i = (TextView) this.a.findViewById(e.big_gift_content_text);
        this.e = (FixedWebView) this.a.findViewById(e.webview);
        this.e.setBackgroundColor(0);
        this.e.setTouchable(false);
        WebSettings settings = this.e.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccess(true);
            settings.setAllowContentAccess(true);
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        settings.setCacheMode(1);
        j();
        p.b(this.a);
        p.a(this.e);
    }

    public i e() {
        return new f(this);
    }

    public void f() {
    }

    public void g() {
    }

    public void h() {
        this.b.a();
        this.l.removeCallbacksAndMessages(null);
        if (this.e != null) {
            ((ViewGroup) this.e.getParent()).removeView(this.e);
            this.e.destroy();
            this.e = null;
        }
    }

    public void i() {
    }

    public void a(com.qq.reader.liveshow.model.im.message.a.c cVar) {
        this.d.a(cVar);
    }

    public void b(com.qq.reader.liveshow.model.im.message.a.c cVar) {
        this.o = cVar;
        if (this.l != null) {
            this.l.sendEmptyMessageDelayed(10011, 500);
        }
    }

    public void a() {
        if (com.qq.reader.liveshow.model.a.m() != null && com.qq.reader.liveshow.model.a.m().size() > 0) {
            for (GiftItem giftItem : com.qq.reader.liveshow.model.a.m()) {
                if (giftItem.mType == 2) {
                    this.j = giftItem.h5Url;
                    this.k = giftItem.showTime > 0 ? giftItem.showTime : 6500;
                    SxbLog.c("Proxy", "big gift id = " + giftItem.mId + "url is" + this.j);
                    this.p = true;
                    if (this.e != null) {
                        this.e.a(this.j);
                    }
                }
            }
            this.p = true;
            if (this.e != null) {
                this.e.a(this.j);
            }
        }
    }

    public int b() {
        return 2;
    }

    public int c() {
        return 1;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 10010:
                if (!(this.e == null || this.f == null || this.b == null)) {
                    this.e.setVisibility(8);
                    this.f.setVisibility(8);
                    this.b.b(this.o);
                    break;
                }
            case 10011:
                this.h.setText(this.o.b().getNickName());
                this.i.setText(this.a.getResources().getString(h.send_text, new Object[]{this.o.e()}));
                p.a(this.a, this.g, this.o.b().getAvatar(), this.o.b().getAuthorId() > 0);
                this.g.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        q.a((Activity) view.getContext(), this.a.o.b().getAuthorId(), this.a.o.b().getId(), this.a.o.b().getNickName(), this.a.o.b().getAvatar(), null, false);
                    }
                });
                if (this.e != null) {
                    this.e.setVisibility(0);
                    this.e.a(this.j);
                }
                if (this.l != null) {
                    this.l.sendEmptyMessageDelayed(10010, this.k + 500);
                    break;
                }
                break;
        }
        return true;
    }

    protected void j() {
        this.e.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                SxbLog.c("Proxy", "webview load url is" + str);
                if (str == null || !str.startsWith(com.tencent.qalsdk.core.c.d)) {
                    return false;
                }
                webView.loadUrl(str);
                return true;
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                SxbLog.c("Proxy", "webview error code is" + i + " url is" + str2);
                if (webView != null && this.a.f != null && this.a.b != null) {
                    webView.setVisibility(8);
                    this.a.f.setVisibility(8);
                    if (this.a.o != null) {
                        this.a.b.b(this.a.o);
                    }
                }
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            }

            public void onPageFinished(WebView webView, String str) {
                SxbLog.c("Proxy", "onPageFinished url is " + str + " " + this.a.p);
                if (this.a.p) {
                    this.a.p = false;
                } else {
                    this.a.f.setVisibility(0);
                }
            }
        });
    }
}
