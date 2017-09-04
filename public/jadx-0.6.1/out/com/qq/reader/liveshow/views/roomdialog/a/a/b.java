package com.qq.reader.liveshow.views.roomdialog.a.a;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.a.g;
import com.qq.reader.liveshow.b.n;
import com.qq.reader.liveshow.utils.SxbLog;
import com.qq.reader.liveshow.utils.p;
import com.qq.reader.liveshow.views.roomdialog.LiveEnterRoomDialog.a;

/* compiled from: LiveNotStartHelper */
public class b extends a {
    private WebView d = ((WebView) this.g.findViewById(e.webview));
    private View e;
    private a f;
    private View g;

    public b(final a aVar, Activity activity, ViewGroup viewGroup) {
        super(aVar, activity, viewGroup);
        this.g = LayoutInflater.from(activity).inflate(g.live_not_start_frame, viewGroup, false);
        this.f = aVar;
        this.d.getSettings().setCacheMode(2);
        this.d.getSettings().setJavaScriptEnabled(true);
        a(this.d);
        this.d.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        p.b(this.b);
        p.a(this.d);
        this.d.setBackgroundColor(Color.parseColor("#434343"));
        this.d.setLayerType(1, null);
        this.e = this.g.findViewById(e.live_not_start_btn_cancel);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b b;

            public void onClick(View view) {
                aVar.a(7, null);
            }
        });
    }

    private void a(WebView webView) {
        webView.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return motionEvent.getAction() == 2;
            }
        });
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str == null) {
                    return true;
                }
                if (!str.startsWith("uniteqqreader://")) {
                    return super.shouldOverrideUrlLoading(webView, str);
                }
                if (str.startsWith("uniteqqreader://nativepage/client/liveshow?room_num=")) {
                    try {
                        Integer valueOf = Integer.valueOf(str.replace("uniteqqreader://nativepage/client/liveshow?room_num=", ""));
                        if (this.a.f == null || this.a.b == null) {
                            return true;
                        }
                        com.qq.reader.liveshow.model.a.e(valueOf.intValue());
                        this.a.b.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass3 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                SxbLog.e("WEB", "START NEXT STEP");
                                this.a.a.f.a(2, null);
                            }
                        });
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return true;
                    }
                }
                try {
                    n.a().d().a(this.a.b, str);
                    return true;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return true;
                }
            }
        });
    }

    public void b() {
        super.b();
        this.c.addView(this.g);
        this.d.loadUrl(n.a().e().a(com.qq.reader.liveshow.model.a.i()));
    }

    public void d() {
        super.d();
        if (this.f != null) {
            this.f.a(7, null);
        }
    }

    public void c() {
        super.c();
    }

    public void e() {
        super.e();
        this.c.removeAllViews();
        this.d.loadUrl("about:blank");
        this.d.stopLoading();
        this.d.setWebChromeClient(null);
        this.d.setWebViewClient(null);
        this.d.destroy();
        this.d = null;
    }
}
