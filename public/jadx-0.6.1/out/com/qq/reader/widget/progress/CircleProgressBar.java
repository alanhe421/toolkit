package com.qq.reader.widget.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.qq.reader.c.b;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ao;
import com.tencent.openqq.protocol.imsdk.im_common;
import com.tencent.smtt.sdk.WebView;

public class CircleProgressBar extends View {
    private static final String f = CircleProgressBar.class.getSimpleName();
    protected int a = 360;
    protected Paint b;
    protected Bitmap c;
    protected Canvas d;
    ValueAnimator e;
    private int g = 0;
    private int h = ao.a(2.0f);
    private int i;
    private int j;
    private Xfermode k = new PorterDuffXfermode(Mode.CLEAR);
    private Xfermode l = new PorterDuffXfermode(Mode.SRC_OVER);
    private Shader m;
    private int n = -1;
    private Drawable o;
    private int p = Color.parseColor("#5085b8");
    private int q;
    private int r;
    private RectF s = new RectF();
    private RectF t = new RectF();
    private RectF u = new RectF();
    private boolean v = true;
    private int w;
    private int x;
    private int y;

    public interface a {
        void a();
    }

    public CircleProgressBar(Context context) {
        super(context);
        a(context, null);
    }

    protected void a(Context context, AttributeSet attributeSet) {
        this.b = new Paint();
        this.b.setAntiAlias(true);
        this.b.setStyle(Style.FILL);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.CircleProgressBar);
        if (obtainStyledAttributes != null) {
            try {
                this.a = (int) obtainStyledAttributes.getFloat(0, (float) this.a);
                this.h = obtainStyledAttributes.getDimensionPixelSize(1, this.h);
                this.n = obtainStyledAttributes.getResourceId(2, this.n);
                this.p = obtainStyledAttributes.getResourceId(3, this.p);
                this.o = obtainStyledAttributes.getDrawable(4);
                this.g = (int) obtainStyledAttributes.getFloat(5, 0.0f);
                this.w = obtainStyledAttributes.getResourceId(3, 0);
                this.x = obtainStyledAttributes.getResourceId(2, 0);
                obtainStyledAttributes.recycle();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getMaxProgress() {
        if (this.a != 0) {
            return this.a;
        }
        c.e(f, "max progress is zero , It is wrong !");
        return 1;
    }

    public CircleProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i > 0 && i2 > 0) {
            this.c = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
            this.d = new Canvas(this.c);
        }
    }

    protected void onDraw(Canvas canvas) {
        int i = 0;
        super.onDraw(canvas);
        try {
            this.i = getWidth();
            this.j = getHeight();
            this.q = getWidth() / 2;
            this.r = getHeight() / 2;
            this.s.set(0.0f, 0.0f, (float) this.i, (float) this.j);
            b();
            float centerX = this.t.centerX();
            float f = this.t.top;
            float centerX2 = (float) (((double) this.t.centerX()) + ((Math.sin(Math.toRadians(30.0d)) * ((double) this.t.width())) / 2.0d));
            float centerY = (float) (((double) this.t.centerY()) - ((Math.cos(Math.toRadians(30.0d)) * ((double) this.t.height())) / 2.0d));
            if (!(this.u.left == centerX && this.u.top == f && this.u.right == centerX2 && this.u.bottom == centerY)) {
                i = 1;
            }
            if ((i | a()) != 0) {
                this.u.set(centerX, f, centerX2, centerY);
                this.m = new LinearGradient(centerX2, centerY, centerX, f, new int[]{this.n, this.p}, null, TileMode.CLAMP);
            }
            if (this.d != null) {
                this.d.save();
                this.d.drawColor(0, Mode.CLEAR);
                a(this.d);
                d(this.d);
                c(this.d);
                e(this.d);
                b(this.d);
                f(this.d);
                this.d.restore();
                this.b.setXfermode(null);
                this.b.setShader(null);
                this.b.setColor(WebView.NIGHT_MODE_COLOR);
                canvas.drawBitmap(this.c, 0.0f, 0.0f, this.b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean a() {
        int color;
        boolean z = false;
        if (this.w != 0) {
            color = getResources().getColor(this.w);
            if (!(color == this.p || color == 0)) {
                this.p = color;
                z = true;
            }
        }
        if (this.x != 0) {
            color = getResources().getColor(this.x);
            if (!(color == this.n || color == 0)) {
                this.n = color;
                z = true;
            }
        }
        if (z) {
            c.e("TAG", "change color");
        }
        return z;
    }

    private void b() {
        if (this.j > this.i) {
            this.t.set(0.0f, (float) ((this.j - this.i) / 2), (float) this.i, (float) (this.j - ((this.j - this.i) / 2)));
        } else {
            this.t.set((float) ((this.i - this.j) / 2), 0.0f, (float) (this.i - ((this.i - this.j) / 2)), (float) this.j);
        }
    }

    public void setProgress(int i) {
        c.e("setProgress", i + "");
        if (i != this.g) {
            this.g = Math.min(i, getMaxProgress());
            invalidate();
        }
    }

    public void setMaxProgress(int i) {
        if (i == 0) {
            throw new IllegalArgumentException("perPro can not be 0");
        }
        this.a = i;
    }

    public void setProgressBarColor(int i) {
        if (this.n != i) {
            this.n = i;
            this.x = 0;
            c();
        }
    }

    private void c() {
        invalidate();
    }

    public void setCenterDrawable(Drawable drawable) {
        if (this.o != drawable) {
            this.o = drawable;
            c();
        }
    }

    public void setProgressBarBgColor(int i) {
        if (this.p != i) {
            this.p = i;
            this.w = 0;
            c();
        }
    }

    public void setProgressBarWidth(int i) {
        if (this.h != i) {
            this.h = i;
            c();
        }
    }

    public void setProgressEnable(boolean z) {
        if (this.v != z) {
            this.v = z;
            c();
        }
    }

    public void a(Canvas canvas) {
        this.b.setShader(null);
        this.b.setColor(this.p);
        this.b.setXfermode(this.l);
        canvas.drawCircle((float) this.q, (float) this.r, this.t.width() / 2.0f, this.b);
    }

    public void b(Canvas canvas) {
        this.b.setShader(null);
        this.b.setColor(0);
        this.b.setXfermode(this.k);
        float f = (float) this.q;
        float f2 = (float) this.r;
        float width = this.t.width() / 2.0f;
        int i = (this.g > getMaxProgress() || !this.v) ? -ao.a(1.0f) : this.h;
        canvas.drawCircle(f, f2, width - ((float) i), this.b);
    }

    public void c(Canvas canvas) {
        this.b.setColor(this.n);
        this.b.setShader(this.m);
        this.b.setXfermode(this.l);
        Canvas canvas2 = canvas;
        canvas2.drawArc(this.t, 270.0f, Math.min(30.0f, getRealRadius()), true, this.b);
    }

    private float getRealRadius() {
        return ((((float) this.g) % ((float) getMaxProgress())) * 360.0f) / ((float) getMaxProgress());
    }

    public void d(Canvas canvas) {
        this.b.setShader(null);
        this.b.setColor(this.n);
        this.b.setXfermode(this.l);
        Canvas canvas2 = canvas;
        canvas2.drawArc(this.t, getCenterProgressStartRadius(), getCenterProgressSweepRadius(), true, this.b);
    }

    private float getCenterProgressSweepRadius() {
        if (this.g <= 0 || this.g % getMaxProgress() != 0) {
            return ((float) ((((this.g % getMaxProgress()) * 360) / getMaxProgress()) + im_common.WPA_QZONE)) - getCenterProgressStartRadius();
        }
        return 360.0f;
    }

    private float getCenterProgressStartRadius() {
        return 270.0f;
    }

    public void e(Canvas canvas) {
        this.b.setShader(null);
        this.b.setColor(this.n);
        this.b.setXfermode(this.l);
        int maxProgress = (((this.g % getMaxProgress()) * 360) / getMaxProgress()) + im_common.WPA_QZONE;
        int width = (int) ((this.t.width() / 2.0f) - ((float) (this.h / 2)));
        int cos = (int) (((double) this.q) + (Math.cos(Math.toRadians((double) maxProgress)) * ((double) width)));
        width = (int) ((((double) width) * Math.sin(Math.toRadians((double) maxProgress))) + ((double) this.r));
        float realRadius = getRealRadius();
        if (realRadius < 30.0f) {
            realRadius *= 3.0f;
        } else {
            realRadius = 90.0f;
        }
        canvas.drawCircle((float) cos, (float) width, (float) Math.min(Math.ceil(((double) (((float) this.h) / 2.0f)) * Math.sin(Math.toRadians((double) realRadius))), (double) (((float) this.h) / 2.0f)), this.b);
    }

    public void f(Canvas canvas) {
        this.b.setShader(null);
        this.b.setColor(0);
        this.b.setXfermode(this.l);
        if (this.o != null) {
            int intrinsicWidth = this.o.getIntrinsicWidth();
            int intrinsicHeight = this.o.getIntrinsicHeight();
            this.o.setBounds(this.q - (intrinsicWidth / 2), this.r - (intrinsicHeight / 2), (intrinsicWidth / 2) + this.q, (intrinsicHeight / 2) + this.r);
            this.o.draw(canvas);
        }
    }

    float getSpeed() {
        return 1.0f;
    }

    public void setProgress4Animation(int i, final a aVar) {
        if (this.e != null && this.e.isRunning()) {
            this.e.removeAllListeners();
            this.e.end();
            setProgress(this.y);
        }
        this.y = i;
        c.e("setProgress4Animation", i + "");
        this.e = ValueAnimator.ofInt(new int[]{this.g, i});
        this.e.setDuration(2000);
        this.e.setInterpolator(new LinearInterpolator());
        this.e.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ CircleProgressBar a;

            {
                this.a = r1;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.a.setProgress(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        this.e.addListener(new AnimatorListener(this) {
            final /* synthetic */ CircleProgressBar b;

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                if (aVar != null) {
                    aVar.a();
                }
            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
        this.e.start();
    }

    public int getCurrentProgress() {
        return this.g;
    }

    protected void setCurrentProgress(int i) {
        this.g = i;
    }
}
