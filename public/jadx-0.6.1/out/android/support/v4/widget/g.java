package android.support.v4.widget;

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
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import com.tencent.smtt.sdk.WebView;
import java.util.ArrayList;

/* compiled from: MaterialProgressDrawable */
class g extends Drawable implements Animatable {
    private static final Interpolator b = new LinearInterpolator();
    private static final Interpolator c = new android.support.v4.view.b.a();
    boolean a;
    private final int[] d = new int[]{WebView.NIGHT_MODE_COLOR};
    private final ArrayList<Animation> e = new ArrayList();
    private final a f;
    private float g;
    private Resources h;
    private View i;
    private Animation j;
    private float k;
    private double l;
    private double m;
    private final Callback n = new Callback(this) {
        final /* synthetic */ g a;

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
    private static class a {
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
        private final Paint v = new Paint(1);
        private int w;
        private int x;

        public a(Callback callback) {
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
            this.b.setColor(this.x);
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
                this.c.setColor(this.x);
                canvas.rotate((f + f2) - 5.0f, rect.exactCenterX(), rect.exactCenterY());
                canvas.drawPath(this.p, this.c);
            }
        }

        public void a(int[] iArr) {
            this.j = iArr;
            c(0);
        }

        public void b(int i) {
            this.x = i;
        }

        public void c(int i) {
            this.k = i;
            this.x = this.j[this.k];
        }

        public int a() {
            return this.j[n()];
        }

        private int n() {
            return (this.k + 1) % this.j.length;
        }

        public void b() {
            c(n());
        }

        public void a(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
            o();
        }

        public void d(int i) {
            this.u = i;
        }

        public int c() {
            return this.u;
        }

        public void a(float f) {
            this.h = f;
            this.b.setStrokeWidth(f);
            o();
        }

        public float d() {
            return this.h;
        }

        public void b(float f) {
            this.e = f;
            o();
        }

        public float e() {
            return this.e;
        }

        public float f() {
            return this.l;
        }

        public float g() {
            return this.m;
        }

        public int h() {
            return this.j[this.k];
        }

        public void c(float f) {
            this.f = f;
            o();
        }

        public float i() {
            return this.f;
        }

        public void d(float f) {
            this.g = f;
            o();
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

        public double j() {
            return this.r;
        }

        public void a(boolean z) {
            if (this.o != z) {
                this.o = z;
                o();
            }
        }

        public void e(float f) {
            if (f != this.q) {
                this.q = f;
                o();
            }
        }

        public float k() {
            return this.n;
        }

        public void l() {
            this.l = this.e;
            this.m = this.f;
            this.n = this.g;
        }

        public void m() {
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = 0.0f;
            b(0.0f);
            c(0.0f);
            d(0.0f);
        }

        private void o() {
            this.d.invalidateDrawable(null);
        }
    }

    public g(Context context, View view) {
        this.i = view;
        this.h = context.getResources();
        this.f = new a(this.n);
        this.f.a(this.d);
        a(1);
        b();
    }

    private void a(double d, double d2, double d3, double d4, float f, float f2) {
        a aVar = this.f;
        float f3 = this.h.getDisplayMetrics().density;
        this.l = ((double) f3) * d;
        this.m = ((double) f3) * d2;
        aVar.a(((float) d4) * f3);
        aVar.a(((double) f3) * d3);
        aVar.c(0);
        aVar.a(f * f3, f3 * f2);
        aVar.a((int) this.l, (int) this.m);
    }

    public void a(int i) {
        if (i == 0) {
            a(56.0d, 56.0d, 12.5d, 3.0d, 12.0f, 6.0f);
        } else {
            a(40.0d, 40.0d, 8.75d, 2.5d, 10.0f, 5.0f);
        }
    }

    public void a(boolean z) {
        this.f.a(z);
    }

    public void a(float f) {
        this.f.e(f);
    }

    public void a(float f, float f2) {
        this.f.b(f);
        this.f.c(f2);
    }

    public void b(float f) {
        this.f.d(f);
    }

    public void b(int i) {
        this.f.a(i);
    }

    public void a(int... iArr) {
        this.f.a(iArr);
        this.f.c(0);
    }

    public int getIntrinsicHeight() {
        return (int) this.m;
    }

    public int getIntrinsicWidth() {
        return (int) this.l;
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int save = canvas.save();
        canvas.rotate(this.g, bounds.exactCenterX(), bounds.exactCenterY());
        this.f.a(canvas, bounds);
        canvas.restoreToCount(save);
    }

    public void setAlpha(int i) {
        this.f.d(i);
    }

    public int getAlpha() {
        return this.f.c();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f.a(colorFilter);
    }

    void c(float f) {
        this.g = f;
        invalidateSelf();
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isRunning() {
        ArrayList arrayList = this.e;
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
        this.j.reset();
        this.f.l();
        if (this.f.i() != this.f.e()) {
            this.a = true;
            this.j.setDuration(666);
            this.i.startAnimation(this.j);
            return;
        }
        this.f.c(0);
        this.f.m();
        this.j.setDuration(1332);
        this.i.startAnimation(this.j);
    }

    public void stop() {
        this.i.clearAnimation();
        c(0.0f);
        this.f.a(false);
        this.f.c(0);
        this.f.m();
    }

    private float a(a aVar) {
        return (float) Math.toRadians(((double) aVar.d()) / (6.283185307179586d * aVar.j()));
    }

    private int a(float f, int i, int i2) {
        int intValue = Integer.valueOf(i).intValue();
        int i3 = (intValue >> 24) & 255;
        int i4 = (intValue >> 16) & 255;
        int i5 = (intValue >> 8) & 255;
        intValue &= 255;
        int intValue2 = Integer.valueOf(i2).intValue();
        return (intValue + ((int) (((float) ((intValue2 & 255) - intValue)) * f))) | ((((i3 + ((int) (((float) (((intValue2 >> 24) & 255) - i3)) * f))) << 24) | ((i4 + ((int) (((float) (((intValue2 >> 16) & 255) - i4)) * f))) << 16)) | ((((int) (((float) (((intValue2 >> 8) & 255) - i5)) * f)) + i5) << 8));
    }

    private void a(float f, a aVar) {
        if (f > 0.75f) {
            aVar.b(a((f - 0.75f) / 0.25f, aVar.h(), aVar.a()));
        }
    }

    private void b(float f, a aVar) {
        a(f, aVar);
        float floor = (float) (Math.floor((double) (aVar.k() / 0.8f)) + 1.0d);
        float a = a(aVar);
        aVar.b((((aVar.g() - a) - aVar.f()) * f) + aVar.f());
        aVar.c(aVar.g());
        aVar.d(((floor - aVar.k()) * f) + aVar.k());
    }

    private void b() {
        final a aVar = this.f;
        Animation anonymousClass1 = new Animation(this) {
            final /* synthetic */ g b;

            public void applyTransformation(float f, Transformation transformation) {
                if (this.b.a) {
                    this.b.b(f, aVar);
                    return;
                }
                float a = this.b.a(aVar);
                float g = aVar.g();
                float f2 = aVar.f();
                float k = aVar.k();
                this.b.a(f, aVar);
                if (f <= 0.5f) {
                    float f3 = 0.8f - a;
                    aVar.b(f2 + (g.c.getInterpolation(f / 0.5f) * f3));
                }
                if (f > 0.5f) {
                    aVar.c(((0.8f - a) * g.c.getInterpolation((f - 0.5f) / 0.5f)) + g);
                }
                aVar.d((0.25f * f) + k);
                this.b.c((216.0f * f) + (1080.0f * (this.b.k / 5.0f)));
            }
        };
        anonymousClass1.setRepeatCount(-1);
        anonymousClass1.setRepeatMode(1);
        anonymousClass1.setInterpolator(b);
        anonymousClass1.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ g b;

            public void onAnimationStart(Animation animation) {
                this.b.k = 0.0f;
            }

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
                aVar.l();
                aVar.b();
                aVar.b(aVar.i());
                if (this.b.a) {
                    this.b.a = false;
                    animation.setDuration(1332);
                    aVar.a(false);
                    return;
                }
                this.b.k = (this.b.k + 1.0f) % 5.0f;
            }
        });
        this.j = anonymousClass1;
    }
}
