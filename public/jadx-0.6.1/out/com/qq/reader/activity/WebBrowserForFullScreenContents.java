package com.qq.reader.activity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.c.a;
import com.qq.reader.common.utils.ao;
import com.qq.reader.view.FixedWebView;
import com.tencent.feedback.proguard.R;

public class WebBrowserForFullScreenContents extends WebBrowserForContents {
    private RelativeLayout j;
    private RelativeLayout s;
    private float t = 0.4f;
    private int u;
    private int v;
    private int w;
    private boolean x = true;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.j = (RelativeLayout) findViewById(R.id.common_titler);
        this.s = (RelativeLayout) findViewById(R.id.webview_layout);
        this.a.setVisibility(8);
        this.j.setBackgroundColor(this.v);
        A();
        B();
    }

    private void A() {
        try {
            a(this.b);
            a(this.e);
            a(this.f);
            a(this.b, 1);
            a(this.e, 4);
        } catch (Exception e) {
        }
    }

    private void a(View view, int i) {
        if (view.getLayoutParams() != null && (view.getLayoutParams() instanceof MarginLayoutParams)) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
            if (i == 1) {
                marginLayoutParams.leftMargin = ao.a(10.5f);
            } else if (i == 4) {
                marginLayoutParams.rightMargin = ao.a(10.5f);
            }
            view.setLayoutParams(marginLayoutParams);
        }
    }

    private void a(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = ao.a(36.0f);
        layoutParams.width = ao.a(36.0f);
        view.setLayoutParams(layoutParams);
        view.setBackgroundResource(R.drawable.common_title_transition_bg);
        view.setPadding(4, 4, 4, 4);
    }

    private void B() {
        this.v = ReaderApplication.getApplicationImp().getResources().getColor(R.color.translucent);
        this.w = ReaderApplication.getApplicationImp().getResources().getColor(R.color.fullscreen_webview_title_color);
        this.u = (int) (((float) a.bU) * this.t);
        try {
            this.k.setOnScrollChangedListener(new FixedWebView.a(this) {
                final /* synthetic */ WebBrowserForFullScreenContents a;

                {
                    this.a = r1;
                }

                public void a(WebView webView, int i, int i2, int i3, int i4) {
                    ObjectAnimator ofObject;
                    if (!this.a.x && (webView.getScrollY() < this.a.u || i2 < this.a.u)) {
                        this.a.x = true;
                        this.a.a.setVisibility(8);
                        ofObject = ObjectAnimator.ofObject(this.a.j, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(this.a.w), Integer.valueOf(this.a.v)});
                        ofObject.setDuration(1200);
                        ofObject.start();
                        this.a.b.setBackgroundResource(R.drawable.common_title_transition_bg);
                        this.a.e.setBackgroundResource(R.drawable.common_title_transition_bg);
                        this.a.f.setBackgroundResource(R.drawable.common_title_transition_bg);
                    } else if (!this.a.x) {
                    } else {
                        if (webView.getScrollY() >= this.a.u || i2 >= this.a.u) {
                            this.a.x = false;
                            this.a.a.setVisibility(0);
                            this.a.b.setBackgroundResource(0);
                            this.a.e.setBackgroundResource(0);
                            this.a.f.setBackgroundResource(0);
                            ofObject = ObjectAnimator.ofObject(this.a.j, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(this.a.v), Integer.valueOf(this.a.w)});
                            ofObject.setDuration(1200);
                            ofObject.start();
                        }
                    }
                }
            });
        } catch (Exception e) {
            this.j.setBackgroundColor(this.v);
            this.b.setBackgroundResource(R.drawable.common_title_transition_bg);
            this.e.setBackgroundResource(0);
            this.f.setBackgroundResource(0);
        }
    }

    protected void onResume() {
        super.onResume();
    }

    protected void d(String str) {
        super.d(str);
    }

    protected void c(WebView webView) {
        G();
    }

    protected void w() {
        G();
    }

    public void switchImmerseMode() {
        super.switchImmerseMode();
        G();
    }

    private void G() {
        if (VERSION.SDK_INT >= 19 && this.s != null) {
            this.s.setPadding(0, 0, 0, 0);
        }
    }
}
