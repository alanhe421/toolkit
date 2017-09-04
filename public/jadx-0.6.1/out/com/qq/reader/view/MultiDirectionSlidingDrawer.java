package com.qq.reader.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class MultiDirectionSlidingDrawer extends ViewGroup {
    private boolean A;
    private boolean B;
    private boolean C;
    private final int D;
    private final int E;
    private int F;
    private int G;
    private int H;
    private final int I;
    private final int a;
    private final int b;
    private View c;
    private View d;
    private final Rect e;
    private final Rect f;
    private boolean g;
    private boolean h;
    private VelocityTracker i;
    private boolean j;
    private boolean k;
    private boolean l;
    private int m;
    private int n;
    private int o;
    private int p;
    private c q;
    private b r;
    private d s;
    private final Handler t;
    private float u;
    private float v;
    private float w;
    private long x;
    private long y;
    private int z;

    private class a implements OnClickListener {
        final /* synthetic */ MultiDirectionSlidingDrawer a;

        private a(MultiDirectionSlidingDrawer multiDirectionSlidingDrawer) {
            this.a = multiDirectionSlidingDrawer;
        }

        public void onClick(View view) {
            if (!this.a.h) {
                if (this.a.C) {
                    this.a.b();
                } else {
                    this.a.a();
                }
            }
        }
    }

    public interface b {
        void a();
    }

    public interface c {
        void a();
    }

    public interface d {
        void a();

        void b();
    }

    private class e extends Handler {
        final /* synthetic */ MultiDirectionSlidingDrawer a;

        private e(MultiDirectionSlidingDrawer multiDirectionSlidingDrawer) {
            this.a = multiDirectionSlidingDrawer;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1000:
                    this.a.g();
                    return;
                default:
                    return;
            }
        }
    }

    public MultiDirectionSlidingDrawer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MultiDirectionSlidingDrawer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new Rect();
        this.f = new Rect();
        this.t = new e();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.qq.reader.c.b.MultiDirectionSlidingDrawer, i, 0);
        int i2 = obtainStyledAttributes.getInt(0, 1);
        boolean z = i2 == 1 || i2 == 3;
        this.k = z;
        this.m = (int) obtainStyledAttributes.getDimension(3, 0.0f);
        this.n = (int) obtainStyledAttributes.getDimension(4, 0.0f);
        this.B = obtainStyledAttributes.getBoolean(5, true);
        this.C = obtainStyledAttributes.getBoolean(6, true);
        if (i2 == 3 || i2 == 2) {
            z = true;
        } else {
            z = false;
        }
        this.j = z;
        int resourceId = obtainStyledAttributes.getResourceId(1, 0);
        if (resourceId == 0) {
            throw new IllegalArgumentException("The handle attribute is required and must refer to a valid child.");
        }
        int resourceId2 = obtainStyledAttributes.getResourceId(2, 0);
        if (resourceId2 == 0) {
            throw new IllegalArgumentException("The content attribute is required and must refer to a valid child.");
        } else if (resourceId == resourceId2) {
            throw new IllegalArgumentException("The content and handle attributes must refer to different children.");
        } else {
            this.a = resourceId;
            this.b = resourceId2;
            float f = getResources().getDisplayMetrics().density;
            this.D = (int) ((6.0f * f) + 0.5f);
            this.E = (int) ((100.0f * f) + 0.5f);
            this.F = (int) ((150.0f * f) + 0.5f);
            this.G = (int) ((200.0f * f) + 0.5f);
            this.H = (int) ((2000.0f * f) + 0.5f);
            this.I = (int) ((f * 1000.0f) + 0.5f);
            if (this.j) {
                this.H = -this.H;
                this.G = -this.G;
                this.F = -this.F;
            }
            obtainStyledAttributes.recycle();
            setAlwaysDrawnWithCacheEnabled(false);
        }
    }

    protected void onFinishInflate() {
        this.c = findViewById(this.a);
        if (this.c == null) {
            throw new IllegalArgumentException("The handle attribute is must refer to an existing child.");
        }
        this.c.setOnClickListener(new a());
        this.d = findViewById(this.b);
        if (this.d == null) {
            throw new IllegalArgumentException("The content attribute is must refer to an existing child.");
        }
        this.d.setVisibility(8);
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (mode == 0 || mode2 == 0) {
            throw new RuntimeException("SlidingDrawer cannot have UNSPECIFIED dimensions");
        }
        View view = this.c;
        measureChild(view, i, i2);
        if (this.k) {
            this.d.measure(MeasureSpec.makeMeasureSpec(size, 1073741824), MeasureSpec.makeMeasureSpec((size2 - view.getMeasuredHeight()) - this.n, 1073741824));
        } else {
            this.d.measure(MeasureSpec.makeMeasureSpec((size - view.getMeasuredWidth()) - this.n, 1073741824), MeasureSpec.makeMeasureSpec(size2, 1073741824));
        }
        setMeasuredDimension(size, size2);
    }

    protected void dispatchDraw(Canvas canvas) {
        float f = 0.0f;
        long drawingTime = getDrawingTime();
        View view = this.c;
        boolean z = this.k;
        drawChild(canvas, view, drawingTime);
        if (this.g || this.A) {
            Bitmap drawingCache = this.d.getDrawingCache();
            if (drawingCache == null) {
                canvas.save();
                if (this.j) {
                    canvas.translate(z ? 0.0f : (float) ((view.getLeft() - this.n) - this.d.getMeasuredWidth()), z ? (float) ((view.getTop() - this.n) - this.d.getMeasuredHeight()) : 0.0f);
                } else {
                    float left = z ? 0.0f : (float) (view.getLeft() - this.n);
                    if (z) {
                        f = (float) (view.getTop() - this.n);
                    }
                    canvas.translate(left, f);
                }
                drawChild(canvas, this.d, drawingTime);
                canvas.restore();
            } else if (!z) {
                canvas.drawBitmap(drawingCache, this.j ? (float) (view.getLeft() - drawingCache.getWidth()) : (float) view.getRight(), 0.0f, null);
            } else if (this.j) {
                canvas.drawBitmap(drawingCache, 0.0f, (float) ((view.getTop() - (getBottom() - getTop())) + this.o), null);
            } else {
                canvas.drawBitmap(drawingCache, 0.0f, (float) view.getBottom(), null);
            }
            invalidate();
        } else if (this.l) {
            drawChild(canvas, this.d, drawingTime);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (!this.g) {
            int i5;
            int i6 = i3 - i;
            int i7 = i4 - i2;
            View view = this.c;
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            View view2 = this.d;
            if (this.k) {
                i5 = i6 - measuredWidth;
                if (this.j) {
                    i6 = this.l ? (i7 - this.m) - measuredHeight : this.n;
                    view2.layout(0, this.n, view2.getMeasuredWidth(), this.n + view2.getMeasuredHeight());
                } else {
                    i6 = this.l ? this.n : (i7 - measuredHeight) + this.m;
                    view2.layout(0, this.n + measuredHeight, view2.getMeasuredWidth(), (this.n + measuredHeight) + view2.getMeasuredHeight());
                }
            } else {
                i5 = (i7 - measuredHeight) / 2;
                int i8;
                if (this.j) {
                    i6 = this.l ? (i6 - this.m) - measuredWidth : this.n;
                    view2.layout(this.n, 0, this.n + view2.getMeasuredWidth(), view2.getMeasuredHeight());
                    i8 = i5;
                    i5 = i6;
                    i6 = i8;
                } else {
                    i6 = this.l ? this.n : (i6 - measuredWidth) + this.m;
                    view2.layout(this.n + measuredWidth, 0, (this.n + measuredWidth) + view2.getMeasuredWidth(), view2.getMeasuredHeight());
                    i8 = i5;
                    i5 = i6;
                    i6 = i8;
                }
            }
            view.layout(i5, i6, i5 + measuredWidth, i6 + measuredHeight);
            this.o = view.getHeight();
            this.p = view.getWidth();
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.h) {
            return false;
        }
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        Rect rect = this.e;
        View view = this.c;
        view.getHitRect(rect);
        if (!this.g && !rect.contains((int) x, (int) y)) {
            return false;
        }
        if (action == 0) {
            this.g = true;
            view.setPressed(true);
            e();
            if (this.s != null) {
                this.s.a();
            }
            int top;
            if (this.k) {
                top = this.c.getTop();
                this.z = ((int) y) - top;
                c(top);
            } else {
                top = this.c.getLeft();
                this.z = ((int) x) - top;
                c(top);
            }
            this.i.addMovement(motionEvent);
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.h) {
            return true;
        }
        if (this.g) {
            this.i.addMovement(motionEvent);
            switch (motionEvent.getAction()) {
                case 1:
                case 3:
                    boolean z2;
                    float f;
                    boolean z3;
                    VelocityTracker velocityTracker = this.i;
                    velocityTracker.computeCurrentVelocity(this.I);
                    float yVelocity = velocityTracker.getYVelocity();
                    float xVelocity = velocityTracker.getXVelocity();
                    boolean z4 = this.k;
                    boolean z5;
                    if (z4) {
                        z2 = yVelocity < 0.0f;
                        if (xVelocity < 0.0f) {
                            xVelocity = -xVelocity;
                        }
                        if ((!this.j && r0 > ((float) this.F)) || (this.j && r0 < ((float) this.F))) {
                            z5 = z2;
                            f = yVelocity;
                            yVelocity = (float) this.F;
                            z3 = z5;
                        }
                        z5 = z2;
                        f = yVelocity;
                        yVelocity = xVelocity;
                        z3 = z5;
                    } else {
                        z2 = xVelocity < 0.0f;
                        if (yVelocity < 0.0f) {
                            yVelocity = -yVelocity;
                        }
                        if ((!this.j && r1 > ((float) this.F)) || (this.j && r1 < ((float) this.F))) {
                            z5 = z2;
                            f = (float) this.F;
                            yVelocity = xVelocity;
                            z3 = z5;
                        }
                        z5 = z2;
                        f = yVelocity;
                        yVelocity = xVelocity;
                        z3 = z5;
                    }
                    yVelocity = (float) Math.hypot((double) yVelocity, (double) f);
                    if (z3) {
                        xVelocity = -yVelocity;
                    } else {
                        xVelocity = yVelocity;
                    }
                    int top = this.c.getTop();
                    int left = this.c.getLeft();
                    int bottom = this.c.getBottom();
                    int right = this.c.getRight();
                    if (Math.abs(xVelocity) >= ((float) this.E)) {
                        if (!z4) {
                            top = left;
                        }
                        a(top, xVelocity, false);
                        break;
                    }
                    boolean z6;
                    boolean z7;
                    boolean z8;
                    if (this.j) {
                        z6 = this.l && getBottom() - bottom < this.D + this.m;
                        if (this.l || top >= (this.n + this.o) - this.D) {
                            z2 = false;
                        } else {
                            z2 = true;
                        }
                        if (!this.l || getRight() - right >= this.D + this.m) {
                            z7 = false;
                        } else {
                            z7 = true;
                        }
                        if (this.l || left <= (this.n + this.p) + this.D) {
                            z8 = false;
                        } else {
                            z8 = true;
                        }
                    } else {
                        if (!this.l || top >= this.D + this.n) {
                            z6 = false;
                        } else {
                            z6 = true;
                        }
                        if (this.l || top <= (((this.m + getBottom()) - getTop()) - this.o) - this.D) {
                            z2 = false;
                        } else {
                            z2 = true;
                        }
                        if (!this.l || left >= this.D + this.n) {
                            z7 = false;
                        } else {
                            z7 = true;
                        }
                        if (this.l || left <= (((this.m + getRight()) - getLeft()) - this.p) - this.D) {
                            z8 = false;
                        } else {
                            z8 = true;
                        }
                    }
                    if (z4 ? r1 || r2 : r5 || r6) {
                        if (!this.B) {
                            if (!z4) {
                                top = left;
                            }
                            a(top, xVelocity, false);
                            break;
                        }
                        playSoundEffect(0);
                        if (!this.l) {
                            if (!z4) {
                                top = left;
                            }
                            b(top);
                            break;
                        }
                        int i;
                        if (z4) {
                            i = top;
                        } else {
                            i = left;
                        }
                        a(i);
                        break;
                    }
                    if (!z4) {
                        top = left;
                    }
                    a(top, xVelocity, false);
                    break;
                    break;
                case 2:
                    d(((int) (this.k ? motionEvent.getY() : motionEvent.getX())) - this.z);
                    break;
            }
        }
        if (this.g || this.A || super.onTouchEvent(motionEvent)) {
            z = true;
        }
        return z;
    }

    private void a(int i) {
        c(i);
        a(i, (float) this.H, true);
    }

    private void b(int i) {
        c(i);
        a(i, (float) (-this.H), true);
    }

    private void a(int i, float f, boolean z) {
        boolean z2 = false;
        this.w = (float) i;
        this.v = f;
        boolean z3;
        if (this.l) {
            int bottom = this.k ? getBottom() : getRight();
            int i2 = this.k ? this.o : this.p;
            boolean z4 = this.j ? f < ((float) this.G) : f > ((float) this.G);
            if (this.j) {
                z3 = (bottom - (i + i2)) + this.m > i2;
            } else {
                z3 = i > (this.k ? this.o : this.p) + this.n;
            }
            if (this.j) {
                if (f < ((float) (-this.G))) {
                    z2 = true;
                }
            } else if (f > ((float) (-this.G))) {
                z2 = true;
            }
            if (z || r2 || (r0 && r4)) {
                this.u = (float) this.H;
                if (this.j) {
                    if (f > 0.0f) {
                        this.v = 0.0f;
                    }
                } else if (f < 0.0f) {
                    this.v = 0.0f;
                }
            } else {
                this.u = (float) (-this.H);
                if (this.j) {
                    if (f < 0.0f) {
                        this.v = 0.0f;
                    }
                } else if (f > 0.0f) {
                    this.v = 0.0f;
                }
            }
        } else {
            z3 = this.j ? f < ((float) this.G) : f > ((float) this.G);
            boolean z5;
            if (this.j) {
                z5 = i < (this.k ? getHeight() : getWidth()) / 2;
            } else {
                z5 = i > (this.k ? getHeight() : getWidth()) / 2;
            }
            if (this.j) {
                if (f < ((float) (-this.G))) {
                    z2 = true;
                }
            } else if (f > ((float) (-this.G))) {
                z2 = true;
            }
            if (z || !(r0 || (r1 && r4))) {
                this.u = (float) (-this.H);
                if (this.j) {
                    if (f < 0.0f) {
                        this.v = 0.0f;
                    }
                } else if (f > 0.0f) {
                    this.v = 0.0f;
                }
            } else {
                this.u = (float) this.H;
                if (this.j) {
                    if (f > 0.0f) {
                        this.v = 0.0f;
                    }
                } else if (f < 0.0f) {
                    this.v = 0.0f;
                }
            }
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        this.x = uptimeMillis;
        this.y = uptimeMillis + 16;
        this.A = true;
        this.t.removeMessages(1000);
        this.t.sendMessageAtTime(this.t.obtainMessage(1000), this.y);
        f();
    }

    private void c(int i) {
        this.g = true;
        this.i = VelocityTracker.obtain();
        if (!this.l) {
            this.u = (float) this.H;
            this.v = (float) this.G;
            if (this.j) {
                this.w = (float) this.n;
            } else {
                this.w = (float) ((this.k ? getHeight() - this.o : getWidth() - this.p) + this.m);
            }
            d((int) this.w);
            this.A = true;
            this.t.removeMessages(1000);
            long uptimeMillis = SystemClock.uptimeMillis();
            this.x = uptimeMillis;
            this.y = uptimeMillis + 16;
            this.A = true;
            return;
        }
        if (this.A) {
            this.A = false;
            this.t.removeMessages(1000);
        }
        d(i);
    }

    private void d(int i) {
        View view = this.c;
        int top;
        int i2;
        Rect rect;
        Rect rect2;
        if (this.k) {
            if (i == -10001) {
                if (this.j) {
                    view.offsetTopAndBottom(((this.m + getBottom()) - getTop()) - this.o);
                } else {
                    view.offsetTopAndBottom(this.n - view.getTop());
                }
                invalidate();
            } else if (i == -10002) {
                if (this.j) {
                    view.offsetTopAndBottom(this.n - view.getTop());
                } else {
                    view.offsetTopAndBottom((((this.m + getBottom()) - getTop()) - this.o) - view.getTop());
                }
                invalidate();
            } else {
                top = view.getTop();
                i2 = i - top;
                if (i < this.n) {
                    i2 = this.n - top;
                } else if (i2 > (((this.m + getBottom()) - getTop()) - this.o) - top) {
                    i2 = (((this.m + getBottom()) - getTop()) - this.o) - top;
                }
                view.offsetTopAndBottom(i2);
                rect = this.e;
                rect2 = this.f;
                view.getHitRect(rect);
                rect2.set(rect);
                rect2.union(rect.left, rect.top - i2, rect.right, rect.bottom - i2);
                rect2.union(0, rect.bottom - i2, getWidth(), (rect.bottom - i2) + this.d.getHeight());
                invalidate(rect2);
            }
        } else if (i == -10001) {
            if (this.j) {
                view.offsetLeftAndRight(((this.m + getRight()) - getLeft()) - this.p);
            } else {
                view.offsetLeftAndRight(this.n - view.getLeft());
            }
            invalidate();
        } else if (i == -10002) {
            if (this.j) {
                view.offsetLeftAndRight(this.n - view.getLeft());
            } else {
                view.offsetLeftAndRight((((this.m + getRight()) - getLeft()) - this.p) - view.getLeft());
            }
            invalidate();
        } else {
            top = view.getLeft();
            i2 = i - top;
            if (i < this.n) {
                i2 = this.n - top;
            } else if (i2 > (((this.m + getRight()) - getLeft()) - this.p) - top) {
                i2 = (((this.m + getRight()) - getLeft()) - this.p) - top;
            }
            view.offsetLeftAndRight(i2);
            rect = this.e;
            rect2 = this.f;
            view.getHitRect(rect);
            rect2.set(rect);
            rect2.union(rect.left - i2, rect.top, rect.right - i2, rect.bottom);
            rect2.union(rect.right - i2, 0, (rect.right - i2) + this.d.getWidth(), getHeight());
            invalidate(rect2);
        }
    }

    private void e() {
        if (!this.A) {
            View view = this.d;
            if (view.isLayoutRequested()) {
                int i;
                if (this.k) {
                    i = this.o;
                    view.measure(MeasureSpec.makeMeasureSpec(getRight() - getLeft(), 1073741824), MeasureSpec.makeMeasureSpec(((getBottom() - getTop()) - i) - this.n, 1073741824));
                    if (this.j) {
                        view.layout(0, this.n, view.getMeasuredWidth(), this.n + view.getMeasuredHeight());
                    } else {
                        view.layout(0, this.n + i, view.getMeasuredWidth(), (i + this.n) + view.getMeasuredHeight());
                    }
                } else {
                    i = this.c.getWidth();
                    view.measure(MeasureSpec.makeMeasureSpec(((getRight() - getLeft()) - i) - this.n, 1073741824), MeasureSpec.makeMeasureSpec(getBottom() - getTop(), 1073741824));
                    if (this.j) {
                        view.layout(this.n, 0, this.n + view.getMeasuredWidth(), view.getMeasuredHeight());
                    } else {
                        view.layout(this.n + i, 0, (i + this.n) + view.getMeasuredWidth(), view.getMeasuredHeight());
                    }
                }
            }
            view.getViewTreeObserver().dispatchOnPreDraw();
            view.buildDrawingCache();
            view.setVisibility(8);
        }
    }

    private void f() {
        this.c.setPressed(false);
        this.g = false;
        if (this.s != null) {
            this.s.b();
        }
        if (this.i != null) {
            this.i.recycle();
            this.i = null;
        }
    }

    private void g() {
        if (this.A) {
            h();
            if (!this.j) {
                if (this.w >= ((float) (((this.k ? getHeight() : getWidth()) + this.m) - 1))) {
                    this.A = false;
                    i();
                } else if (this.w < ((float) this.n)) {
                    this.A = false;
                    j();
                } else {
                    d((int) this.w);
                    this.y += 16;
                    this.t.sendMessageAtTime(this.t.obtainMessage(1000), this.y);
                }
            } else if (this.w < ((float) this.n)) {
                this.A = false;
                i();
            } else {
                if (this.w >= ((float) (((this.k ? getHeight() : getWidth()) + this.n) - 1))) {
                    this.A = false;
                    j();
                    return;
                }
                d((int) this.w);
                this.y += 16;
                this.t.sendMessageAtTime(this.t.obtainMessage(1000), this.y);
            }
        }
    }

    private void h() {
        long uptimeMillis = SystemClock.uptimeMillis();
        float f = ((float) (uptimeMillis - this.x)) / 1000.0f;
        float f2 = this.w;
        float f3 = this.v;
        float f4 = this.j ? this.u : this.u;
        this.w = (f2 + (f3 * f)) + (((0.5f * f4) * f) * f);
        this.v = (f4 * f) + f3;
        this.x = uptimeMillis;
    }

    public void a() {
        if (this.l) {
            i();
        } else {
            j();
        }
        invalidate();
        requestLayout();
    }

    public void b() {
        if (this.l) {
            c();
        } else {
            d();
        }
    }

    public void c() {
        e();
        d dVar = this.s;
        if (dVar != null) {
            dVar.a();
        }
        a(this.k ? this.c.getTop() : this.c.getLeft());
        if (dVar != null) {
            dVar.b();
        }
    }

    public void d() {
        e();
        d dVar = this.s;
        if (dVar != null) {
            dVar.a();
        }
        b(this.k ? this.c.getTop() : this.c.getLeft());
        sendAccessibilityEvent(32);
        if (dVar != null) {
            dVar.b();
        }
    }

    private void i() {
        d(-10002);
        this.d.setVisibility(8);
        this.d.destroyDrawingCache();
        if (this.l) {
            this.l = false;
            if (this.r != null) {
                this.r.a();
            }
        }
    }

    private void j() {
        d(-10001);
        this.d.setVisibility(0);
        if (!this.l) {
            this.l = true;
            if (this.q != null) {
                this.q.a();
            }
        }
    }

    public void setOnDrawerOpenListener(c cVar) {
        this.q = cVar;
    }

    public void setOnDrawerCloseListener(b bVar) {
        this.r = bVar;
    }

    public void setOnDrawerScrollListener(d dVar) {
        this.s = dVar;
    }

    public View getHandle() {
        return this.c;
    }

    public View getContent() {
        return this.d;
    }
}
