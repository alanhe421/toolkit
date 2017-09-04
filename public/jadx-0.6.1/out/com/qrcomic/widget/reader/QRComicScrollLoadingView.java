package com.qrcomic.widget.reader;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import b.a.a.a.a.a.i;
import com.qq.reader.module.question.card.AudioQuestionQuizCard;
import com.qrcomic.activity.reader.QRComicReadingBaseActivity;
import com.qrcomic.screenshot.a.b;
import com.qrcomic.screenshot.d.d;
import com.qrcomic.util.g;
import com.qrcomic.util.k;
import com.qrcomic.widget.reader.c.c;
import com.tencent.smtt.sdk.WebView;
import com.tencent.tinker.android.dx.instruction.Opcodes;

public class QRComicScrollLoadingView extends View {
    QRComicReadingBaseActivity a;
    Runnable b;
    com.qrcomic.a.g.a c;
    int d;
    int e;
    int f;
    int g;
    int h;
    Rect i;
    Rect j;
    PointF k;
    PointF l;
    public a m;
    private int n;
    private int o;
    private final TextPaint p;
    private CharSequence q;
    private final TextPaint r;
    private CharSequence s;
    private int t;
    private int u;
    private final TextPaint v;
    private long w;
    private Handler x;
    private int y;
    private String[] z;

    public interface a {
        void a(View view);
    }

    static /* synthetic */ int a(QRComicScrollLoadingView qRComicScrollLoadingView) {
        int i = qRComicScrollLoadingView.y + 1;
        qRComicScrollLoadingView.y = i;
        return i;
    }

    public QRComicScrollLoadingView(Context context) {
        this(context, null);
    }

    public QRComicScrollLoadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public QRComicScrollLoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.n = 0;
        this.o = 1;
        this.w = 500;
        this.y = -1;
        this.z = new String[]{".", "..", "..."};
        this.b = new Runnable(this) {
            final /* synthetic */ QRComicScrollLoadingView a;

            {
                this.a = r1;
            }

            public void run() {
                if (QRComicScrollLoadingView.a(this.a) >= AudioQuestionQuizCard.MAX_BOOKCOIN_COUNT) {
                    this.a.y = 0;
                }
                this.a.invalidate();
                if (g.a()) {
                    c cVar = (c) this.a.getTag();
                    if (cVar.d != null) {
                        g.a("VipComicScrollLoadingView", g.d, "mIndex = " + this.a.y + ", index = " + cVar.d.index);
                    }
                }
                if (this.a.d() && "图片加载中".equals(this.a.s) && this.a.c() && this.a.x != null) {
                    this.a.x.postDelayed(this, this.a.w);
                }
            }
        };
        this.c = new com.qrcomic.a.g.a(this) {
            final /* synthetic */ QRComicScrollLoadingView a;

            {
                this.a = r1;
            }

            protected void a() {
                if (this.a.x != null) {
                    this.a.x.removeCallbacks(this.a.b);
                }
                if (g.a()) {
                    g.a("VipComicScrollLoadingView", g.d, "onRunningBackground");
                }
            }

            protected void e() {
                this.a.a();
                if (g.a()) {
                    g.a("VipComicScrollLoadingView", g.d, "onRunningForeground");
                }
            }

            protected void k() {
                this.a.s = "";
                if (this.a.x != null) {
                    this.a.x.removeCallbacks(this.a.b);
                    this.a.x = null;
                }
                if (!(this.a.a == null || this.a.a.a == null)) {
                    this.a.a.a.e().deleteObserver(this);
                }
                if (g.a()) {
                    g.a("VipComicScrollLoadingView", g.d, "onReaderDestroy");
                }
            }
        };
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = new Rect();
        this.j = new Rect();
        this.k = new PointF();
        this.l = new PointF();
        Resources resources = getResources();
        this.q = "";
        this.p = new TextPaint(1);
        this.p.setTextAlign(Align.CENTER);
        this.p.density = resources.getDisplayMetrics().density;
        this.s = "";
        this.r = new TextPaint(1);
        this.r.setTextAlign(Align.CENTER);
        this.r.density = resources.getDisplayMetrics().density;
        this.v = new TextPaint(1);
        this.v.setTextAlign(Align.LEFT);
        this.v.setColor(-13395457);
        this.v.density = resources.getDisplayMetrics().density;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i.ScrollLoadingView);
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(i.ScrollLoadingView_mainTextSize, com.qrcomic.util.c.a.a(context, 30));
            this.p.setTextSize((float) dimensionPixelSize);
            this.p.setColor(obtainStyledAttributes.getColor(i.ScrollLoadingView_mainTextColor, WebView.NIGHT_MODE_COLOR));
            setMainText(obtainStyledAttributes.getString(i.ScrollLoadingView_mainText));
            int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(i.ScrollLoadingView_subTextSize, com.qrcomic.util.c.a.a(context, 18));
            this.r.setTextSize((float) dimensionPixelSize2);
            this.v.setTextSize((float) dimensionPixelSize2);
            setSubTextColor(obtainStyledAttributes.getColor(i.ScrollLoadingView_subTextColor, WebView.NIGHT_MODE_COLOR));
            setBackgroundColor(Color.parseColor("#d2d2d2"));
            this.u = dimensionPixelSize + obtainStyledAttributes.getDimensionPixelSize(i.ScrollLoadingView_textMargin, com.qrcomic.util.c.a.a(context, 5));
            obtainStyledAttributes.recycle();
        }
    }

    public void setAttachedActivity(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.a = qRComicReadingBaseActivity;
        if (this.a != null) {
            this.a.a.e().addObserver(this.c);
        }
    }

    public void setMainText(CharSequence charSequence) {
        a(this.n, charSequence);
    }

    public void setSubText(CharSequence charSequence) {
        a(this.o, charSequence);
    }

    private void a(int i, CharSequence charSequence) {
        Object obj = i == this.n ? this.q : this.s;
        if (charSequence == null) {
            charSequence = "";
        }
        if (!obj.equals(charSequence)) {
            if (charSequence.length() > Opcodes.MUL_FLOAT) {
                try {
                    charSequence = charSequence.subSequence(0, Opcodes.MUL_FLOAT);
                } catch (Exception e) {
                    if (g.a()) {
                        e.printStackTrace();
                    }
                }
            }
            if (i == this.n) {
                this.q = charSequence;
            } else {
                this.s = charSequence;
                a();
                e();
            }
            invalidate();
        }
    }

    public void a() {
        if (d() && "图片加载中".equals(this.s) && c()) {
            if (this.x == null) {
                this.x = new k(Looper.getMainLooper(), null);
            }
            this.x.removeCallbacks(this.b);
            int i = this.y + 1;
            this.y = i;
            if (i >= AudioQuestionQuizCard.MAX_BOOKCOIN_COUNT) {
                this.y = 0;
            }
            this.x.postDelayed(this.b, this.w);
        } else if (this.x != null) {
            this.x.removeCallbacks(this.b);
        }
    }

    private boolean c() {
        if (getTag() instanceof c) {
            c cVar = (c) getTag();
            if (cVar.d != null) {
                return cVar.d.isVisible;
            }
        }
        return false;
    }

    public void b() {
        if (this.x != null) {
            this.x.removeCallbacks(this.b);
        }
    }

    private boolean d() {
        return getVisibility() == 0;
    }

    protected void onVisibilityChanged(View view, int i) {
        if (equals(view) && i == 0) {
            a();
        }
    }

    private void e() {
        if ("加载失败, 点击重试".equals(this.s) || "付费失败, 重新购买".equals(this.s)) {
            this.r.getTextBounds("加载失败, 点击重试", 0, "加载失败, 点击重试".length(), this.i);
            this.j.set(this.f - (this.i.width() / 2), (this.g + this.u) - this.i.height(), this.f + (this.i.width() / 2), (this.g + this.u) + (this.i.height() / 2));
        }
    }

    public void setSubTextColor(int i) {
        if (i != this.t) {
            this.t = i;
            this.r.setColor(this.t);
            invalidate();
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.d = i;
        this.e = i2;
        this.f = this.d / 2;
        this.g = this.e / 2;
        this.h = this.f + Math.round(this.r.measureText("图片加载中") / 2.0f);
        e();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(this.q.toString(), (float) this.f, (float) this.g, this.p);
        if (!TextUtils.isEmpty(this.s)) {
            if ("图片加载中".equals(this.s)) {
                this.r.setTextAlign(Align.CENTER);
                canvas.drawText("图片加载中", (float) this.f, (float) (this.g + this.u), this.r);
                this.r.setTextAlign(Align.LEFT);
                canvas.drawText(this.z[Math.abs(this.y) % this.z.length], (float) this.h, (float) (this.g + this.u), this.r);
            } else if ("加载失败, 点击重试".equals(this.s) || "付费失败, 重新购买".equals(this.s)) {
                this.r.setTextAlign(Align.RIGHT);
                if ("加载失败, 点击重试".equals(this.s)) {
                    canvas.drawText("加载失败,", (float) this.f, (float) (this.g + this.u), this.r);
                    canvas.drawText(" 点击重试", (float) this.f, (float) (this.g + this.u), this.v);
                    return;
                }
                canvas.drawText("付费失败,", (float) this.f, (float) (this.g + this.u), this.r);
                canvas.drawText(" 重新购买", (float) this.f, (float) (this.g + this.u), this.v);
            } else {
                this.r.setTextAlign(Align.CENTER);
                canvas.drawText(this.s.toString(), (float) this.f, (float) (this.g + this.u), this.r);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if ((!"加载失败, 点击重试".equals(this.s) && !"付费失败, 重新购买".equals(this.s)) || !this.j.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.k.set(motionEvent.getX(), motionEvent.getY());
                break;
            case 1:
                this.l.set(motionEvent.getX(), motionEvent.getY());
                if (d.b(this.k, this.l) < ((float) b.a) && this.m != null) {
                    this.m.a(this);
                    break;
                }
        }
        return true;
    }

    public void setOnClickForSubText(a aVar) {
        this.m = aVar;
    }
}
