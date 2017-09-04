package com.qq.reader.widget.picbrowser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import com.qq.reader.common.monitor.f;

public class AnimationImageView extends View implements AnimationListener {
    private boolean A = false;
    private boolean B = true;
    private float C = 0.0f;
    private float D = 0.0f;
    private float E = 0.0f;
    private float F = 0.0f;
    private OnClickListener G;
    private a H;
    private Handler I = new Handler(this) {
        final /* synthetic */ AnimationImageView a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == 0) {
                if (this.a.G != null) {
                    this.a.G.onClick(this.a);
                }
            } else if (message.what == 1) {
                this.a.b();
            }
        }
    };
    private boolean J;
    private int K;
    private int L;
    private boolean M = false;
    private float N = -1.0f;
    private boolean O = false;
    private Bitmap a;
    private Drawable b;
    private Paint c = new Paint(1);
    private int d;
    private int e;
    private Animation f;
    private Transformation g;
    private float[] h;
    private boolean i = false;
    private boolean j = true;
    private int[] k;
    private boolean l = false;
    private Transformation m;
    private Animation n;
    private Drawable o;
    private Drawable p;
    private float q = 0.5f;
    private float r = 5.0f;
    private float s = 1.0f;
    private float t = 0.0f;
    private float u = 0.0f;
    private int v = 0;
    private int w = 0;
    private float x = -1.0f;
    private float y = -1.0f;
    private boolean z = false;

    public interface a {
    }

    public void setLongClickListener(a aVar) {
        this.H = aVar;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.G = onClickListener;
    }

    public AnimationImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public AnimationImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public AnimationImageView(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.c.setFilterBitmap(true);
        this.K = ViewConfiguration.get(getContext().getApplicationContext()).getScaledTouchSlop();
    }

    public void startAnimation(Animation animation) {
        a(animation, false);
    }

    public void a(Animation animation, boolean z) {
        this.f = animation;
        if (this.f != null) {
            this.f.start();
            this.g = new Transformation();
            this.h = new float[9];
            invalidate();
        }
        this.i = z;
    }

    public void a(int[] iArr) {
        this.j = true;
        this.k = iArr;
    }

    public void setOriPicPos(int[] iArr) {
        this.k = iArr;
        this.j = false;
    }

    private void a() {
        int i;
        if (this.k != null && this.k.length >= 4) {
            int height;
            r9 = new int[2];
            a.a(this, null, r9);
            r9[0] = 0;
            int[] iArr = this.k;
            float f = ((float) iArr[2]) / ((float) this.d);
            i = (int) (((float) iArr[3]) / f);
            if (this.a != null) {
                height = (this.a.getHeight() * this.d) / this.a.getWidth();
            } else if (this.b != null) {
                height = (this.b.getIntrinsicHeight() * this.d) / this.b.getIntrinsicWidth();
            } else {
                height = i;
            }
            Animation scaleAnimation = new ScaleAnimation(f, 1.0f, f, 1.0f, 0.0f, 0.0f);
            scaleAnimation.setInterpolator(new DecelerateInterpolator());
            scaleAnimation.setDuration(300);
            float f2 = (float) (iArr[0] - r9[0]);
            float f3 = (float) (iArr[1] - r9[1]);
            if (this.e > height) {
                f = (float) ((this.e - height) / 2);
            } else {
                f = 0.0f;
            }
            Animation translateAnimation = new TranslateAnimation(f2, 0.0f, f3, f);
            translateAnimation.initialize(this.d, this.e, this.d, this.e);
            translateAnimation.setInterpolator(new DecelerateInterpolator());
            translateAnimation.setDuration(300);
            Animation animationSet = new AnimationSet(true);
            animationSet.addAnimation(scaleAnimation);
            animationSet.addAnimation(translateAnimation);
            startAnimation(animationSet);
        } else if (this.a != null) {
            i = (this.a.getHeight() * this.d) / this.a.getWidth();
            this.w = this.e > i ? (this.e - i) / 2 : 0;
            invalidate();
        }
    }

    public boolean a(AnimationListener animationListener) {
        int i;
        if (this.k != null && this.k.length >= 4) {
            int height;
            r10 = new int[2];
            a.a(this, null, r10);
            r10[0] = 0;
            int[] iArr = this.k;
            float f = ((float) iArr[2]) / ((float) this.d);
            i = (int) (((float) iArr[3]) / f);
            if (this.a != null) {
                height = (this.a.getHeight() * this.d) / this.a.getWidth();
            } else if (this.b != null) {
                height = (this.b.getIntrinsicHeight() * this.d) / this.b.getIntrinsicWidth();
            } else {
                height = i;
            }
            Animation scaleAnimation = new ScaleAnimation(1.0f, f, 1.0f, f, 0.0f, 0.0f);
            scaleAnimation.setInterpolator(new DecelerateInterpolator());
            scaleAnimation.setDuration(300);
            float f2 = (float) (iArr[0] - r10[0]);
            if (this.e > height) {
                f = (float) ((this.e - height) / 2);
            } else {
                f = 0.0f;
            }
            Animation translateAnimation = new TranslateAnimation(0.0f, f2, f, (float) (iArr[1] - r10[1]));
            translateAnimation.initialize(this.d, this.e, this.d, this.e);
            translateAnimation.setInterpolator(new DecelerateInterpolator());
            translateAnimation.setDuration(300);
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.3f);
            alphaAnimation.setInterpolator(new LinearInterpolator());
            alphaAnimation.setStartOffset(150);
            alphaAnimation.setDuration(100);
            Animation animationSet = new AnimationSet(true);
            animationSet.addAnimation(scaleAnimation);
            animationSet.addAnimation(translateAnimation);
            animationSet.addAnimation(alphaAnimation);
            if (animationListener != null) {
                animationSet.setAnimationListener(animationListener);
            }
            startAnimation(animationSet);
            return true;
        } else if (this.a == null) {
            return false;
        } else {
            i = (this.a.getHeight() * this.d) / this.a.getWidth();
            this.w = this.e > i ? (this.e - i) / 2 : 0;
            invalidate();
            return false;
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.a = bitmap;
        this.B = true;
        this.b = null;
        invalidate();
    }

    public Bitmap getBitmap() {
        return this.a;
    }

    public Drawable getDrawable() {
        return this.b;
    }

    public void setImageDrawable(Drawable drawable) {
        this.b = drawable;
        if (this.b != null) {
            this.b.setBounds(0, 0, getWidth(), getHeight());
            this.b.setCallback(this);
            if (drawable.isStateful()) {
                drawable.setState(getDrawableState());
            }
            this.a = null;
            this.B = true;
        }
        invalidate();
    }

    public void sethScrollBar(Drawable drawable) {
        this.o = drawable;
    }

    public void setvScrollBar(Drawable drawable) {
        this.p = drawable;
    }

    public void invalidate(int i, int i2, int i3, int i4) {
        super.invalidate();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.d = i3 - i;
        this.e = i4 - i2;
        if (this.b != null) {
            this.b.setBounds(0, 0, i3 - i, i4 - i2);
        }
        if (this.j) {
            a();
            this.j = false;
        }
        a(0.0f, 0.0f);
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return this.b == drawable || super.verifyDrawable(drawable);
    }

    public void draw(Canvas canvas) {
        int i = 0;
        super.draw(canvas);
        int i2 = this.v;
        int i3 = this.w;
        if (this.a == null || !this.a.isRecycled()) {
            int width;
            int height;
            float f;
            if (this.a != null) {
                width = this.a.getWidth();
                height = this.a.getHeight();
            } else if (this.b != null) {
                width = this.b.getIntrinsicWidth();
                height = this.b.getIntrinsicHeight();
            } else {
                height = 0;
                width = 0;
            }
            float f2 = ((float) this.d) / ((float) (width > 0 ? width : this.d));
            int i4 = (int) ((((float) height) * f2) * this.s);
            int i5 = (int) ((((float) width) * f2) * this.s);
            if (i4 < this.e) {
                i4 = (this.e - i4) / 2;
            } else if (i4 < this.e || this.w <= 0) {
                i4 = i3;
            } else {
                this.w = 0;
                i4 = 0;
            }
            float f3 = this.s;
            int i6;
            if (this.f != null) {
                boolean transformation = this.f.getTransformation(System.currentTimeMillis(), this.g);
                this.g.getMatrix().getValues(this.h);
                this.c.setAlpha((int) (this.g.getAlpha() * 255.0f));
                float f4 = this.h[0];
                i2 = (int) this.h[2];
                i3 = (int) this.h[5];
                if (this.i) {
                    int i7 = (int) ((((float) height) * f2) * f4);
                    if (i7 < this.e) {
                        i3 = (this.e - i7) / 2;
                    }
                    width = (int) ((((float) width) * f2) * f4);
                    if (i2 > 0 || width <= this.d) {
                        i2 = transformation;
                        i6 = i3;
                        i3 = (this.d - width) / 2;
                        f = f4;
                        i4 = i6;
                    }
                }
                f = f4;
                i4 = i3;
                i3 = i2;
                boolean z = transformation;
            } else {
                if (i2 > 0 || i5 <= this.d) {
                    width = (this.d - i5) / 2;
                } else {
                    width = i2;
                }
                this.c.setAlpha(255);
                i2 = 0;
                i6 = width;
                f = f3;
                i3 = i6;
            }
            i5 = (int) (((float) this.d) * f);
            width = (int) ((((float) height) * f2) * f);
            canvas.rotate((float) (((((double) (this.t + this.u)) / 3.141592653589793d) * 180.0d) + 360.0d), this.E, this.F);
            if (this.a != null) {
                canvas.drawBitmap(this.a, new Rect(0, 0, this.a.getWidth(), this.a.getHeight()), new Rect(i3, i4, i5 + i3, width + i4), this.c);
            } else if (this.b != null) {
                this.b.setBounds(i3, i4, i5 + i3, width + i4);
                this.b.draw(canvas);
            }
            this.v = i3;
            this.w = i4;
            if (this.l && (i5 > this.d || width > this.e)) {
                if (this.n != null) {
                    i = this.n.getTransformation(System.currentTimeMillis(), this.m);
                    height = (int) (this.m.getAlpha() * 255.0f);
                } else {
                    height = 255;
                }
                if (i5 > this.d && this.o != null) {
                    this.o.setBounds(((-i3) * this.d) / i5, this.e - this.o.getIntrinsicHeight(), (((-i3) + this.d) * this.d) / i5, this.e);
                    this.o.setAlpha(height);
                    this.o.draw(canvas);
                }
                if (width > this.e && this.p != null) {
                    this.p.setBounds(this.d - this.p.getIntrinsicWidth(), ((-i4) * this.e) / width, this.d, (((-i4) + this.e) * this.e) / width);
                    this.p.setAlpha(height);
                    this.p.draw(canvas);
                }
                i2 |= i;
            }
            if (i2 != 0) {
                invalidate();
            } else {
                this.f = null;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.x = x;
                this.y = y;
                this.M = false;
                this.L = 1;
                break;
            case 1:
                this.J = false;
                if (!(this.M || this.L != 1 || this.G == null)) {
                    this.G.onClick(this);
                    break;
                }
            case 2:
                f.a("View", " MotionEvent.ACTION_MOVE->>pointer2");
                try {
                    if (this.L != 1) {
                        if (this.L == 2) {
                            x = motionEvent.getX(0) - motionEvent.getX(1);
                            y = motionEvent.getY(0) - motionEvent.getY(1);
                            x = (float) Math.sqrt((double) ((x * x) + (y * y)));
                            a((this.s * x) / this.N, (this.C - ((float) this.v)) / (((float) this.d) * this.s), (this.D - ((float) this.w)) / (((float) this.e) * this.s), false);
                            this.N = x;
                            break;
                        }
                    }
                    if ((!this.M && Math.abs(x - this.x) >= ((float) this.K)) || Math.abs(y - this.y) >= ((float) this.K)) {
                        this.M = true;
                    }
                    if (this.M) {
                        if (this.J) {
                            this.O = a(x - this.x, y - this.y);
                        }
                        this.J = true;
                        this.x = x;
                        this.y = y;
                        break;
                    }
                } catch (Exception e) {
                    f.a("ImageViewActivity", e.toString());
                    break;
                }
                break;
            case 5:
                try {
                    x = motionEvent.getX(0);
                    y = motionEvent.getX(1);
                    float y2 = motionEvent.getY(0);
                    float y3 = motionEvent.getY(1);
                    float f = x - y;
                    float f2 = y2 - y3;
                    this.N = (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
                    this.C = (x + y) / 2.0f;
                    this.D = (y2 + y3) / 2.0f;
                    this.L = 2;
                    break;
                } catch (Exception e2) {
                    f.a("ImageViewActivity", e2.toString());
                    break;
                }
            case 6:
                if (((double) this.s) < 1.0d) {
                    a(1.0f);
                }
                this.L = -1;
                break;
        }
        return true;
    }

    private void a(float f) {
        a(f, 0.5f, 0.5f);
    }

    private void a(float f, float f2, float f3) {
        a(f, f2, f3, true);
    }

    private void a(float f, float f2, float f3, boolean z) {
        float f4;
        if (f < this.q) {
            f4 = this.q;
        }
        if (f > this.r) {
            f = this.r;
        }
        f4 = ((this.s - f) * ((float) this.d)) * f2;
        float f5 = ((this.s - f) * ((float) this.e)) * f3;
        if (z) {
            Animation animationSet = new AnimationSet(true);
            Animation scaleAnimation = new ScaleAnimation(this.s, f, this.s, f);
            scaleAnimation.setDuration((long) ((int) (Math.abs(f - this.s) * 300.0f)));
            scaleAnimation.setInterpolator(new DecelerateInterpolator());
            animationSet.addAnimation(scaleAnimation);
            scaleAnimation = new TranslateAnimation((float) this.v, f4 + ((float) this.v), (float) this.w, f5 + ((float) this.w));
            scaleAnimation.initialize(this.d, this.e, this.d, this.e);
            scaleAnimation.setDuration((long) ((int) (Math.abs(f - this.s) * 300.0f)));
            scaleAnimation.setInterpolator(new DecelerateInterpolator());
            animationSet.addAnimation(scaleAnimation);
            a(animationSet, true);
            this.s = f;
            return;
        }
        this.v = (int) (f4 + ((float) this.v));
        this.w = (int) (((float) this.w) + f5);
        this.s = f;
        invalidate();
    }

    private void b() {
        this.n = new AlphaAnimation(1.0f, 0.0f);
        this.n.setDuration(800);
        this.n.start();
        this.n.setAnimationListener(this);
        if (this.m == null) {
            this.m = new Transformation();
        }
        invalidate();
    }

    public void onAnimationEnd(Animation animation) {
        this.l = false;
        this.n = null;
        invalidate();
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }

    private boolean a(float f, float f2) {
        int i;
        int i2;
        boolean z = true;
        float sqrt = (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
        if (f == 0.0f) {
            f = 1.0E-7f;
        }
        float atan = (float) Math.atan((double) (f2 / Math.abs(f)));
        if (f < 0.0f) {
            atan = (float) (3.141592653589793d - ((double) atan));
        }
        float cos = ((float) Math.cos((double) ((atan - this.t) - this.u))) * sqrt;
        float sin = ((float) Math.sin((double) ((atan - this.t) - this.u))) * sqrt;
        int i3 = this.d;
        if (this.a != null) {
            i = (int) (((float) this.d) * this.s);
            i3 = (int) ((((float) this.d) / ((float) this.a.getWidth())) * (((float) this.a.getHeight()) * this.s));
        } else if (this.b == null) {
            return false;
        } else {
            i = (int) (((float) this.d) * this.s);
            i3 = (int) ((((float) this.d) / ((float) this.b.getIntrinsicWidth())) * (((float) this.b.getIntrinsicHeight()) * this.s));
        }
        if (i > this.d) {
            if (((float) this.v) + cos > ((float) (this.d - i))) {
                this.v = (int) (((float) this.v) + cos);
                i = 0;
            } else {
                this.v = this.d - i;
                i = 1;
            }
            if (this.v > 0) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            i2 |= i;
            this.v = this.v > 0 ? 0 : this.v;
            i = i2;
        } else {
            this.v = (this.d - i) / 2;
            i = 1;
        }
        if (i3 > this.e) {
            if (((float) this.w) + sin > ((float) (this.e - i3))) {
                this.w = (int) (((float) this.w) + sin);
                i3 = 0;
            } else {
                this.w = this.e - i3;
                i3 = 1;
            }
            if (this.w > 0) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            i2 |= i3;
            this.w = this.w > 0 ? 0 : this.w;
            i3 = i2;
        } else {
            this.w = (this.e - i3) / 2;
            i3 = 1;
        }
        if (!(i == 0 || r0 == 0) || ((r0 != 0 && Math.abs(sin) > Math.abs(cos) * 2.0f) || (i != 0 && Math.abs(cos) > Math.abs(sin) * 2.0f))) {
            z = false;
        }
        invalidate();
        return z;
    }
}
