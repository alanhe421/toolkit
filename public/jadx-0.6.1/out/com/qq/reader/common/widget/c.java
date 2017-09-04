package com.qq.reader.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import com.tencent.smtt.sdk.WebView;
import java.util.ArrayList;

/* compiled from: MaterialProgressDrawable */
class c extends Drawable implements Animatable {
    private static final Interpolator b = new LinearInterpolator();
    private static final Interpolator c = new a();
    private static final Interpolator d = new c();
    private static final Interpolator e = new AccelerateDecelerateInterpolator();
    boolean a;
    private final int[] f = new int[]{WebView.NIGHT_MODE_COLOR};
    private final ArrayList<Animation> g = new ArrayList();
    private final b h;
    private float i;
    private Resources j;
    private View k;
    private Animation l;
    private float m;
    private double n;
    private double o;
    private final Callback p = new Callback(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void invalidateDrawable(Drawable drawable) {
            this.a.invalidateSelf();
        }

        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            this.a.scheduleSelf(runnable, j);
        }

        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            this.a.unscheduleSelf(runnable);
        }
    };

    /* compiled from: MaterialProgressDrawable */
    private static class a extends AccelerateDecelerateInterpolator {
        private a() {
        }

        public float getInterpolation(float f) {
            return super.getInterpolation(Math.max(0.0f, (f - 0.5f) * 2.0f));
        }
    }

    /* compiled from: MaterialProgressDrawable */
    private static class b {
        private final RectF a = new RectF();
        private final Paint b = new Paint();
        private final Paint c = new Paint();
        private final Callback d;
        private float e = 0.0f;
        private float f = 0.0f;
        private float g = 0.0f;
        private float h = 5.0f;
        private float i = 2.5f;
        private int[] j;
        private int k;
        private float l;
        private float m;
        private float n;
        private boolean o;
        private Path p;
        private float q;
        private double r;
        private int s;
        private int t;
        private int u;
        private final Paint v = new Paint();
        private int w;

        public b(Callback callback) {
            this.d = callback;
            this.b.setStrokeCap(Cap.SQUARE);
            this.b.setAntiAlias(true);
            this.b.setStyle(Style.STROKE);
            this.c.setStyle(Style.FILL);
            this.c.setAntiAlias(true);
        }

        public void a(int i) {
            this.w = i;
        }

        public void a(float f, float f2) {
            this.s = (int) f;
            this.t = (int) f2;
        }

        public void a(Canvas canvas, Rect rect) {
            RectF rectF = this.a;
            rectF.set(rect);
            rectF.inset(this.i, this.i);
            float f = (this.e + this.g) * 360.0f;
            float f2 = ((this.f + this.g) * 360.0f) - f;
            this.b.setColor(this.j[this.k]);
            canvas.drawArc(rectF, f, f2, false, this.b);
            a(canvas, f, f2, rect);
            if (this.u < 255) {
                this.v.setColor(this.w);
                this.v.setAlpha(255 - this.u);
                canvas.drawCircle(rect.exactCenterX(), rect.exactCenterY(), (float) (rect.width() / 2), this.v);
            }
        }

        private void a(Canvas canvas, float f, float f2, Rect rect) {
            if (this.o) {
                if (this.p == null) {
                    this.p = new Path();
                    this.p.setFillType(FillType.EVEN_ODD);
                } else {
                    this.p.reset();
                }
                float f3 = ((float) (((int) this.i) / 2)) * this.q;
                float cos = (float) ((this.r * Math.cos(0.0d)) + ((double) rect.exactCenterX()));
                float sin = (float) ((this.r * Math.sin(0.0d)) + ((double) rect.exactCenterY()));
                this.p.moveTo(0.0f, 0.0f);
                this.p.lineTo(((float) this.s) * this.q, 0.0f);
                this.p.lineTo((((float) this.s) * this.q) / 2.0f, ((float) this.t) * this.q);
                this.p.offset(cos - f3, sin);
                this.p.close();
                this.c.setColor(this.j[this.k]);
                canvas.rotate((f + f2) - 5.0f, rect.exactCenterX(), rect.exactCenterY());
                canvas.drawPath(this.p, this.c);
            }
        }

        public void a(int[] iArr) {
            this.j = iArr;
            b(0);
        }

        public void b(int i) {
            this.k = i;
        }

        public void a() {
            this.k = (this.k + 1) % this.j.length;
        }

        public void a(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
            l();
        }

        public void c(int i) {
            this.u = i;
        }

        public int b() {
            return this.u;
        }

        public void a(float f) {
            this.h = f;
            this.b.setStrokeWidth(f);
            l();
        }

        public float c() {
            return this.h;
        }

        public void b(float f) {
            this.e = f;
            l();
        }

        public float d() {
            return this.e;
        }

        public float e() {
            return this.l;
        }

        public float f() {
            return this.m;
        }

        public void c(float f) {
            this.f = f;
            l();
        }

        public float g() {
            return this.f;
        }

        public void d(float f) {
            this.g = f;
            l();
        }

        public void a(int i, int i2) {
            float min = (float) Math.min(i, i2);
            if (this.r <= 0.0d || min < 0.0f) {
                min = (float) Math.ceil((double) (this.h / 2.0f));
            } else {
                min = (float) (((double) (min / 2.0f)) - this.r);
            }
            this.i = min;
        }

        public void a(double d) {
            this.r = d;
        }

        public double h() {
            return this.r;
        }

        public void a(boolean z) {
            if (this.o != z) {
                this.o = z;
                l();
            }
        }

        public void e(float f) {
            if (f != this.q) {
                this.q = f;
                l();
            }
        }

        public float i() {
            return this.n;
        }

        public void j() {
            this.l = this.e;
            this.m = this.f;
            this.n = this.g;
        }

        public void k() {
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = 0.0f;
            b(0.0f);
            c(0.0f);
            d(0.0f);
        }

        private void l() {
            this.d.invalidateDrawable(null);
        }
    }

    /* compiled from: MaterialProgressDrawable */
    private static class c extends AccelerateDecelerateInterpolator {
        private c() {
        }

        public float getInterpolation(float f) {
            return super.getInterpolation(Math.min(1.0f, 2.0f * f));
        }
    }

    public c(Context context, View view) {
        this.k = view;
        this.j = context.getResources();
        this.h = new b(this.p);
        this.h.a(this.f);
        a(1);
        c();
    }

    private void a(double d, double d2, double d3, double d4, float f, float f2) {
        b bVar = this.h;
        float f3 = this.j.getDisplayMetrics().density;
        this.n = ((double) f3) * d;
        this.o = ((double) f3) * d2;
        bVar.a(((float) d4) * f3);
        bVar.a(((double) f3) * d3);
        bVar.b(0);
        bVar.a(f * f3, f3 * f2);
        bVar.a((int) this.n, (int) this.o);
    }

    public void a(int i) {
        if (i == 0) {
            a(56.0d, 56.0d, 12.5d, 3.0d, 12.0f, 6.0f);
        } else {
            a(40.0d, 40.0d, 8.75d, 2.5d, 10.0f, 5.0f);
        }
    }

    public void a(boolean z) {
        this.h.a(z);
    }

    public void a(float f) {
        this.h.e(f);
    }

    public void a(float f, float f2) {
        this.h.b(f);
        this.h.c(f2);
    }

    public void b(float f) {
        this.h.d(f);
    }

    public void b(int i) {
        this.h.a(i);
    }

    public void a(int... iArr) {
        this.h.a(iArr);
        this.h.b(0);
    }

    public int getIntrinsicHeight() {
        return (int) this.o;
    }

    public int getIntrinsicWidth() {
        return (int) this.n;
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int save = canvas.save();
        canvas.rotate(this.i, bounds.exactCenterX(), bounds.exactCenterY());
        this.h.a(canvas, bounds);
        canvas.restoreToCount(save);
    }

    public void setAlpha(int i) {
        this.h.c(i);
    }

    public int getAlpha() {
        return this.h.b();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.h.a(colorFilter);
    }

    void c(float f) {
        this.i = f;
        invalidateSelf();
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isRunning() {
        ArrayList arrayList = this.g;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Animation animation = (Animation) arrayList.get(i);
            if (animation.hasStarted() && !animation.hasEnded()) {
                return true;
            }
        }
        return false;
    }

    public void start() {
        this.l.reset();
        this.h.j();
        if (this.h.g() != this.h.d()) {
            this.a = true;
            this.l.setDuration(666);
            this.k.startAnimation(this.l);
            return;
        }
        this.h.b(0);
        this.h.k();
        this.l.setDuration(1333);
        this.k.startAnimation(this.l);
    }

    public void stop() {
        this.k.clearAnimation();
        c(0.0f);
        this.h.a(false);
        this.h.b(0);
        this.h.k();
    }

    private void a(float f, b bVar) {
        float floor = (float) (Math.floor((double) (bVar.i() / 0.8f)) + 1.0d);
        bVar.b(bVar.e() + ((bVar.f() - bVar.e()) * f));
        bVar.d(((floor - bVar.i()) * f) + bVar.i());
    }

    private void c() {
        final b bVar = this.h;
        Animation anonymousClass1 = new Animation(this) {
            final /* synthetic */ c b;

            public void applyTransformation(float f, Transformation transformation) {
                if (this.b.a) {
                    this.b.a(f, bVar);
                    return;
                }
                float toRadians = (float) Math.toRadians(((double) bVar.c()) / (6.283185307179586d * bVar.h()));
                float f2 = bVar.f();
                float e = bVar.e();
                float i = bVar.i();
                bVar.c(((0.8f - toRadians) * c.d.getInterpolation(f)) + f2);
                bVar.b((c.c.getInterpolation(f) * 0.8f) + e);
                bVar.d((0.25f * f) + i);
                this.b.c((144.0f * f) + (720.0f * (this.b.m / 5.0f)));
            }
        };
        anonymousClass1.setRepeatCount(-1);
        anonymousClass1.setRepeatMode(1);
        anonymousClass1.setInterpolator(b);
        anonymousClass1.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ c b;

            public void onAnimationStart(Animation animation) {
                this.b.m = 0.0f;
            }

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
                bVar.j();
                bVar.a();
                bVar.b(bVar.g());
                if (this.b.a) {
                    this.b.a = false;
                    animation.setDuration(1333);
                    bVar.a(false);
                    return;
                }
                this.b.m = (this.b.m + 1.0f) % 5.0f;
            }
        });
        this.l = anonymousClass1;
    }
}
