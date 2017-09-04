package com.qq.reader.module.feed;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v4.view.z;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.c;
import com.qq.reader.common.web.js.JSReload;
import com.qq.reader.common.web.js.JSSns;
import com.qq.reader.common.web.js.JSToast;
import com.qq.reader.cservice.adv.b;
import com.qq.reader.view.FixedWebView;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: BrandExpansionHelper */
public class a implements c {
    private ViewGroup a;
    private Handler b;
    private ViewGroup c;
    private ImageView d;
    private FixedWebView e;
    private com.qq.reader.common.web.js.a.c f;
    private com.qq.reader.cservice.adv.a g;
    private boolean h;
    private a i;
    private boolean j;
    private boolean k;
    private AnimatorSet l;
    private Runnable m = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.c.getVisibility() == 0) {
                this.a.l.start();
            }
        }
    };

    /* compiled from: BrandExpansionHelper */
    public interface a {
        void a();
    }

    public a(ViewGroup viewGroup, Handler handler) {
        this.a = viewGroup;
        this.b = handler;
    }

    public static boolean a() {
        return (b(Boolean.valueOf(false), "103484") == null && b(Boolean.valueOf(false), "103469") == null) ? false : true;
    }

    private static com.qq.reader.cservice.adv.a b(Boolean bool, String str) {
        List c = b.a(ReaderApplication.getApplicationImp().getApplicationContext()).c(str);
        if (c != null && c.size() > 0) {
            com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) c.get(0);
            if (aVar != null && aVar.l() == 1) {
                if (!bool.booleanValue()) {
                    return aVar;
                }
                aVar.a(0);
                b.a(ReaderApplication.getApplicationImp().getApplicationContext()).a(aVar, false);
                return aVar;
            }
        }
        return null;
    }

    public void b() {
        if (this.e != null) {
            this.e.onPause();
        }
    }

    public void c() {
        if (this.e != null) {
            this.e.onResume();
        }
    }

    public void a(boolean z) {
        if (z) {
            if (!this.j) {
                this.c.setVisibility(8);
                if (this.l != null) {
                    this.l.cancel();
                    this.d.removeCallbacks(this.m);
                }
            }
        } else if (this.h) {
            this.c.setVisibility(0);
            if (this.j) {
                this.d.setVisibility(8);
                if (this.e == null && ao.d(ReaderApplication.getApplicationImp()) && !this.k) {
                    this.k = true;
                    f();
                    this.e.setVisibility(0);
                    g();
                    return;
                }
                return;
            }
            i.a("event_D229", null, this.c.getContext());
            i();
            this.d.setVisibility(0);
        }
    }

    public void a(a aVar) {
        if (com.qq.reader.appconfig.b.a && VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        this.i = aVar;
        this.c = (ViewGroup) LayoutInflater.from(this.a.getContext()).inflate(R.layout.feed_brand_expansion, this.a, false);
        this.d = (ImageView) this.c.findViewById(R.id.brand_page_launch_btn);
        this.d.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public boolean onTouch(final View view, MotionEvent motionEvent) {
                d.e(view.getContext(), false);
                if (this.a.l != null) {
                    this.a.l.cancel();
                    this.a.d.removeCallbacks(this.a.m);
                }
                if (motionEvent.getAction() == 0) {
                    z.e(this.a.d, 0.0f);
                    if (this.a.e == null) {
                        try {
                            this.a.f();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (!ao.d(ReaderApplication.getApplicationImp())) {
                        af.a(view.getContext(), view.getContext().getString(R.string.net_not_available), 0).a();
                        return true;
                    } else if (this.a.e == null) {
                        return true;
                    } else {
                        this.a.e.setVisibility(0);
                        this.a.g();
                        return true;
                    }
                } else if (motionEvent.getAction() != 1) {
                    return false;
                } else {
                    view.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void run() {
                            z.e(this.b.a.d, view.getResources().getDimension(R.dimen.brand_expansion_entrance_image_margin_top));
                        }
                    });
                    g.a().a(new BrandExpansionHelper$1$2(this));
                    return true;
                }
            }
        });
        this.a.addView(this.c);
        g.a().a(new BrandExpansionHelper$2(this));
    }

    private void f() {
        this.e = new FixedWebView(this.c.getContext());
        this.e.setBackgroundColor(0);
        WebSettings settings = this.e.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccess(true);
            settings.setAllowContentAccess(true);
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
            if (VERSION.SDK_INT >= 17) {
                settings.setMediaPlaybackRequiresUserGesture(false);
            }
        }
        settings.setCacheMode(1);
        settings.setDomStorageEnabled(true);
        this.f = new com.qq.reader.common.web.js.a.c();
        this.f.b(this.e);
        this.f.a(new JSSns((Activity) this.a.getContext(), this), "JSSns");
        this.f.a(new JSReload(this.e.getContext(), this), "JSReload");
        this.f.a(new JSToast((Activity) this.a.getContext()), "JSToast");
        h();
        this.e.setVisibility(8);
        this.e.requestFocus();
        this.c.addView(this.e, -1, -1);
    }

    private void g() {
        this.e.b(this.g.h());
    }

    private void h() {
        this.e.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str == null) {
                    return false;
                }
                if (this.a.f.a(webView, str)) {
                    return true;
                }
                if (str.startsWith(com.tencent.qalsdk.core.c.d)) {
                    webView.loadUrl(str);
                    return true;
                } else if (!com.qq.reader.qurl.c.b(str)) {
                    return false;
                } else {
                    try {
                        com.qq.reader.qurl.c.a((Activity) webView.getContext(), str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                if (this.a.h) {
                    webView.loadUrl(e.a(1));
                } else {
                    this.a.d();
                }
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            }

            public void onPageFinished(WebView webView, String str) {
                if (!this.a.h) {
                    this.a.h = true;
                    this.a.b.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.d();
                            if (this.a.a.i != null) {
                                this.a.a.i.a();
                            }
                        }
                    });
                    com.qq.reader.appconfig.a.e.c(ReaderApplication.getApplicationImp(), this.a.g.h());
                }
            }
        });
    }

    public void d() {
        if (this.e != null) {
            this.c.removeView(this.e);
            this.e.removeAllViews();
            this.e.stopLoading();
            this.e.destroy();
            this.e = null;
            if (this.j && this.k) {
                b(Boolean.valueOf(true), "103484");
            }
        }
    }

    public boolean e() {
        if (this.e == null || this.e.getVisibility() != 0) {
            return false;
        }
        return true;
    }

    private void i() {
        if (d.l(this.c.getContext())) {
            if (this.l == null) {
                this.l = new AnimatorSet();
                float dimension = this.d.getResources().getDimension(R.dimen.brand_expansion_entrance_image_margin_top);
                float dimension2 = this.d.getResources().getDimension(R.dimen.brand_expansion_entrance_image_shake_delta);
                ObjectAnimator duration = ObjectAnimator.ofFloat(this.d, "y", new float[]{0.0f}).setDuration(500);
                duration.setInterpolator(new DecelerateInterpolator());
                ObjectAnimator duration2 = ObjectAnimator.ofFloat(this.d, "y", new float[]{dimension - dimension2}).setDuration(200);
                duration2.setInterpolator(new DecelerateInterpolator());
                ObjectAnimator duration3 = ObjectAnimator.ofFloat(this.d, "y", new float[]{dimension + dimension2}).setDuration(120);
                ObjectAnimator duration4 = ObjectAnimator.ofFloat(this.d, "y", new float[]{dimension - dimension2}).setDuration(120);
                ObjectAnimator duration5 = ObjectAnimator.ofFloat(this.d, "y", new float[]{dimension}).setDuration(120);
                List arrayList = new ArrayList();
                arrayList.add(duration);
                arrayList.add(duration2);
                arrayList.add(duration3);
                arrayList.add(duration4);
                arrayList.add(duration5);
                this.l.playSequentially(arrayList);
                this.l.addListener(new AnimatorListener(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void onAnimationStart(Animator animator) {
                    }

                    public void onAnimationEnd(Animator animator) {
                        this.a.d.removeCallbacks(this.a.m);
                        this.a.d.postDelayed(this.a.m, 1000);
                    }

                    public void onAnimationCancel(Animator animator) {
                    }

                    public void onAnimationRepeat(Animator animator) {
                    }
                });
            } else {
                this.l.cancel();
                this.d.removeCallbacks(this.m);
            }
            this.d.postDelayed(this.m, 500);
        }
    }

    public void retry() {
        g();
    }
}
