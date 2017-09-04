package com.qrcomic.widget.reader;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import b.a.a.a.a.a.c;
import com.qq.reader.module.question.card.AudioQuestionQuizCard;
import com.qrcomic.activity.reader.QRComicReadingVerticalActivity;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.manager.b;
import com.qrcomic.util.g;
import com.qrcomic.util.k;

public class QRComicPagerLoading extends LinearLayout implements OnClickListener {
    protected GestureDetector a;
    protected TextView b;
    protected QRComicReadingVerticalActivity c;
    Runnable d;
    com.qrcomic.a.g.a e;
    SimpleOnGestureListener f;
    public a g;
    private TextView h;
    private int i;
    private int j;
    private String k;
    private long l;
    private Handler m;
    private int n;
    private String[] o;

    public interface a {
        void c(View view);
    }

    static /* synthetic */ int a(QRComicPagerLoading qRComicPagerLoading) {
        int i = qRComicPagerLoading.n + 1;
        qRComicPagerLoading.n = i;
        return i;
    }

    public QRComicPagerLoading(Context context) {
        super(context);
        this.k = "";
        this.l = 500;
        this.n = -1;
        this.o = new String[]{"图片加载中.   ", "图片加载中..  ", "图片加载中... "};
        this.d = new Runnable(this) {
            final /* synthetic */ QRComicPagerLoading a;

            {
                this.a = r1;
            }

            public void run() {
                if (QRComicPagerLoading.a(this.a) >= AudioQuestionQuizCard.MAX_BOOKCOIN_COUNT) {
                    this.a.n = 0;
                }
                this.a.h.setText(this.a.o[Math.abs(this.a.n) % this.a.o.length]);
                if ("图片加载中".equals(this.a.k) && this.a.d() && this.a.b() && this.a.m != null) {
                    this.a.m.postDelayed(this, this.a.l);
                }
                if (g.a()) {
                    g.a("VipComicPagerLoading", g.d, "mIndex = " + this.a.n + ", view = " + toString());
                }
            }
        };
        this.e = new com.qrcomic.a.g.a(this) {
            final /* synthetic */ QRComicPagerLoading a;

            {
                this.a = r1;
            }

            protected void a() {
                if (this.a.m != null) {
                    this.a.m.removeCallbacks(this.a.d);
                }
                if (g.a()) {
                    g.a("VipComicPagerLoading", g.d, "onRunningBackground");
                }
            }

            protected void e() {
                this.a.c();
                if (g.a()) {
                    g.a("VipComicPagerLoading", g.d, "onRunningForeground");
                }
            }

            protected void k() {
                this.a.k = "";
                if (this.a.h != null) {
                    this.a.h.setText("");
                }
                if (this.a.m != null) {
                    this.a.m.removeCallbacks(this.a.d);
                    this.a.m = null;
                }
                if (b.a().b() != null) {
                    b.a().b().e().deleteObserver(this);
                }
                if (g.a()) {
                    g.a("VipComicPagerLoading", g.d, "onReaderDestroy");
                }
            }
        };
        this.f = new SimpleOnGestureListener(this) {
            final /* synthetic */ QRComicPagerLoading a;

            {
                this.a = r1;
            }

            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (this.a.c != null) {
                    this.a.c.w();
                }
                if (this.a.c != null) {
                    this.a.c.x();
                }
                return true;
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (this.a.c != null) {
                    this.a.c.w();
                }
                return true;
            }
        };
        a();
    }

    public QRComicPagerLoading(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = "";
        this.l = 500;
        this.n = -1;
        this.o = new String[]{"图片加载中.   ", "图片加载中..  ", "图片加载中... "};
        this.d = /* anonymous class already generated */;
        this.e = /* anonymous class already generated */;
        this.f = /* anonymous class already generated */;
        a();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.a.onTouchEvent(motionEvent);
        return true;
    }

    public void setAttachedActivity(QRComicReadingVerticalActivity qRComicReadingVerticalActivity) {
        this.c = qRComicReadingVerticalActivity;
        this.c.a(true);
        this.c.a.e().addObserver(this.e);
    }

    public void a() {
        setOrientation(1);
        setBackgroundColor(getResources().getColor(b.a.a.a.a.a.b.reading_loading_color_bg));
        setGravity(17);
        this.a = new GestureDetector(getContext(), this.f);
        this.b = new TextView(getContext());
        this.b.setGravity(17);
        this.b.setTextColor(getResources().getColor(b.a.a.a.a.a.b.reading_loading_color_text_main));
        this.b.setTextSize(1, 20.0f);
        addView(this.b);
        this.h = new TextView(getContext());
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = (int) getResources().getDimension(c.loading_text_offset);
        this.h.setTextColor(getResources().getColor(b.a.a.a.a.a.b.reading_loading_color_text_sub));
        this.h.setTextSize(1, 14.0f);
        this.h.setOnClickListener(this);
        addView(this.h, layoutParams);
        this.i = getResources().getDisplayMetrics().widthPixels;
        this.j = getResources().getDisplayMetrics().heightPixels;
    }

    public void a(ComicSectionPicInfo comicSectionPicInfo) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
        int i = comicSectionPicInfo.width;
        float f = ((float) this.i) / ((float) i);
        layoutParams.height = (int) (((float) comicSectionPicInfo.height) * f);
        layoutParams.width = (int) (((float) i) * f);
        layoutParams.addRule(13);
        setLayoutParams(layoutParams);
    }

    public void setText(String str) {
        if (this.b != null) {
            this.b.setText(str);
        }
    }

    public void setTextSize(int i) {
        if (this.b != null) {
            this.b.setTextSize(0, (float) i);
        }
    }

    public void setSubTextVisible(int i) {
        if (this.h != null && this.h.getVisibility() != i) {
            this.h.setVisibility(i);
            a(this.h, i);
        }
    }

    private boolean b() {
        Object tag = getTag();
        if (this.c == null || !(tag instanceof QRComicReadingVerticalActivity.b)) {
            return false;
        }
        if (Math.abs(this.c.aX - ((QRComicReadingVerticalActivity.b) tag).f) <= 1) {
            return true;
        }
        return false;
    }

    public void setSubText(String str) {
        if (this.h != null) {
            if (str == null) {
                str = "";
            }
            if (!str.equals(this.k)) {
                this.k = str;
                if (!"图片加载中".equals(this.k)) {
                    if (this.m != null) {
                        this.m.removeCallbacks(this.d);
                    }
                    CharSequence spannableStringBuilder;
                    if ("加载失败, 点击重试".equals(str)) {
                        spannableStringBuilder = new SpannableStringBuilder("加载失败, 点击重试");
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(-13395457), "加载失败,".length(), "加载失败, 点击重试".length(), 17);
                        this.h.setText(spannableStringBuilder);
                    } else if ("付费失败, 重新购买".equals(str)) {
                        spannableStringBuilder = new SpannableStringBuilder("付费失败, 重新购买");
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(-13395457), "付费失败,".length(), "付费失败, 重新购买".length(), 17);
                        this.h.setText(spannableStringBuilder);
                    } else {
                        this.h.setText(this.k);
                    }
                } else if (d() && b()) {
                    if (this.m == null) {
                        this.m = new k(Looper.getMainLooper(), null);
                    }
                    this.m.removeCallbacks(this.d);
                    int i = this.n + 1;
                    this.n = i;
                    if (i >= AudioQuestionQuizCard.MAX_BOOKCOIN_COUNT) {
                        this.n = 0;
                    }
                    this.h.setText(this.o[Math.abs(this.n) % this.o.length]);
                    this.m.postDelayed(this.d, this.l);
                } else {
                    this.h.setText(this.o[2]);
                    if (this.m != null) {
                        this.m.removeCallbacks(this.d);
                    }
                }
            }
        }
    }

    private void c() {
        if ("图片加载中".equals(this.k) && d() && b()) {
            if (this.m == null) {
                this.m = new k(Looper.getMainLooper(), null);
            }
            this.m.removeCallbacks(this.d);
            int i = this.n + 1;
            this.n = i;
            if (i >= AudioQuestionQuizCard.MAX_BOOKCOIN_COUNT) {
                this.n = 0;
            }
            this.h.setText(this.o[Math.abs(this.n) % this.o.length]);
            this.m.postDelayed(this.d, this.l);
        } else if (this.m != null) {
            this.m.removeCallbacks(this.d);
        }
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        a(view, i);
    }

    private void a(View view, int i) {
        if (i != 0) {
            return;
        }
        if ((equals(view) || view.equals(this.h)) && d()) {
            c();
        }
    }

    private boolean d() {
        return getVisibility() == 0 && this.h != null && this.h.getVisibility() == 0;
    }

    public void onClick(View view) {
        if ((this.g != null && "加载失败, 点击重试".equals(this.k)) || "付费失败, 重新购买".equals(this.k)) {
            this.g.c(this);
        }
    }

    public void setOnClickForSubText(a aVar) {
        this.g = aVar;
    }
}
