package com.qq.reader.module.comic.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.OverScroller;
import android.widget.Scroller;
import com.tencent.qalsdk.im_open.http;

public class PhotoView extends ImageView {
    private boolean A;
    private boolean B;
    private float C;
    private float D;
    private float E = 1.0f;
    private int F;
    private int G;
    private float H;
    private float I;
    private RectF J = new RectF();
    private RectF K = new RectF();
    private RectF L = new RectF();
    private RectF M = new RectF();
    private RectF N = new RectF();
    private PointF O = new PointF();
    private PointF P = new PointF();
    private PointF Q = new PointF();
    private f R = new f(this);
    private RectF S;
    private RectInfo T;
    private long U;
    private Runnable V;
    private OnLongClickListener W;
    private int a;
    private a aa = new a(this) {
        final /* synthetic */ PhotoView a;

        {
            this.a = r1;
        }

        public void a(float f, float f2, float f3) {
            PhotoView.a(this.a, f);
            if (this.a.z) {
                PhotoView.b(this.a, f);
                this.a.i.postRotate(f, f2, f3);
            } else if (Math.abs(this.a.C) >= ((float) this.a.a)) {
                this.a.z = true;
                this.a.C = 0.0f;
            }
        }
    };
    private OnScaleGestureListener ab = new OnScaleGestureListener(this) {
        final /* synthetic */ PhotoView a;

        {
            this.a = r1;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float scaleFactor = scaleGestureDetector.getScaleFactor();
            if (Float.isNaN(scaleFactor) || Float.isInfinite(scaleFactor)) {
                return false;
            }
            PhotoView.d(this.a, scaleFactor);
            this.a.i.postScale(scaleFactor, scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            this.a.k();
            return true;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        }
    };
    private Runnable ac = new Runnable(this) {
        final /* synthetic */ PhotoView a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.o != null) {
                this.a.o.onClick(this.a);
            }
        }
    };
    private OnGestureListener ad = new SimpleOnGestureListener(this) {
        final /* synthetic */ PhotoView a;

        {
            this.a = r1;
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (this.a.W != null) {
                this.a.W.onLongClick(this.a);
            }
        }

        public boolean onDown(MotionEvent motionEvent) {
            this.a.t = false;
            this.a.q = false;
            this.a.z = false;
            this.a.removeCallbacks(this.a.ac);
            return false;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (this.a.q) {
                return false;
            }
            if ((!this.a.A && !this.a.B) || this.a.R.a) {
                return false;
            }
            float f3;
            float f4;
            if (((float) Math.round(this.a.L.left)) >= this.a.J.left || ((float) Math.round(this.a.L.right)) <= this.a.J.right) {
                f3 = 0.0f;
            } else {
                f3 = f;
            }
            if (((float) Math.round(this.a.L.top)) >= this.a.J.top || ((float) Math.round(this.a.L.bottom)) <= this.a.J.bottom) {
                f4 = 0.0f;
            } else {
                f4 = f2;
            }
            if (this.a.z || this.a.D % 90.0f != 0.0f) {
                float o = (float) (((int) (this.a.D / 90.0f)) * 90);
                float o2 = this.a.D % 90.0f;
                if (o2 > 45.0f) {
                    o += 90.0f;
                } else if (o2 < -45.0f) {
                    o -= 90.0f;
                }
                this.a.R.a((int) this.a.D, (int) o);
                this.a.D = o;
            }
            this.a.a(this.a.L);
            this.a.R.b(f3, f4);
            this.a.R.a();
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            float f3;
            if (this.a.R.a) {
                this.a.R.b();
            }
            if (this.a.a(f)) {
                if (f >= 0.0f || this.a.L.left - f <= this.a.J.left) {
                    f3 = f;
                } else {
                    f3 = this.a.L.left;
                }
                if (f3 > 0.0f && this.a.L.right - f3 < this.a.J.right) {
                    f3 = this.a.L.right - this.a.J.right;
                }
                this.a.i.postTranslate(-f3, 0.0f);
                PhotoView.f(this.a, f3);
            } else if (this.a.A || this.a.q || this.a.t) {
                this.a.m();
                if (!this.a.q) {
                    if (f < 0.0f && this.a.L.left - f > this.a.N.left) {
                        f = this.a.a(this.a.L.left - this.a.N.left, f);
                    }
                    if (f > 0.0f && this.a.L.right - f < this.a.N.right) {
                        f = this.a.a(this.a.L.right - this.a.N.right, f);
                    }
                }
                PhotoView.f(this.a, f);
                this.a.i.postTranslate(-f, 0.0f);
                this.a.t = true;
            }
            if (this.a.b(f2)) {
                if (f2 >= 0.0f || this.a.L.top - f2 <= this.a.J.top) {
                    f3 = f2;
                } else {
                    f3 = this.a.L.top;
                }
                if (f3 > 0.0f && this.a.L.bottom - f3 < this.a.J.bottom) {
                    f3 = this.a.L.bottom - this.a.J.bottom;
                }
                this.a.i.postTranslate(0.0f, -f3);
                PhotoView.g(this.a, f3);
            } else if (this.a.B || this.a.t || this.a.q) {
                this.a.m();
                if (!this.a.q) {
                    if (f2 < 0.0f && this.a.L.top - f2 > this.a.N.top) {
                        f2 = this.a.b(this.a.L.top - this.a.N.top, f2);
                    }
                    if (f2 > 0.0f && this.a.L.bottom - f2 < this.a.N.bottom) {
                        f2 = this.a.b(this.a.L.bottom - this.a.N.bottom, f2);
                    }
                }
                this.a.i.postTranslate(0.0f, -f2);
                PhotoView.g(this.a, f2);
                this.a.t = true;
            }
            this.a.k();
            return true;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            this.a.postDelayed(this.a.ac, 250);
            return false;
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            this.a.R.b();
            float width = this.a.L.left + (this.a.L.width() / 2.0f);
            float height = this.a.L.top + (this.a.L.height() / 2.0f);
            this.a.P.set(width, height);
            this.a.Q.set(width, height);
            this.a.F = 0;
            this.a.G = 0;
            if (this.a.y) {
                height = this.a.E;
                width = 1.0f;
            } else {
                height = this.a.E;
                width = this.a.c;
                this.a.P.set(motionEvent.getX(), motionEvent.getY());
            }
            this.a.k.reset();
            this.a.k.postTranslate(-this.a.K.left, -this.a.K.top);
            this.a.k.postTranslate(this.a.Q.x, this.a.Q.y);
            this.a.k.postTranslate(-this.a.H, -this.a.I);
            this.a.k.postRotate(this.a.D, this.a.Q.x, this.a.Q.y);
            this.a.k.postScale(width, width, this.a.P.x, this.a.P.y);
            this.a.k.postTranslate((float) this.a.F, (float) this.a.G);
            this.a.k.mapRect(this.a.M, this.a.K);
            this.a.a(this.a.M);
            this.a.y = !this.a.y;
            this.a.R.a(height, width);
            this.a.R.a();
            return false;
        }
    };
    private int b;
    private float c;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private int g = http.Internal_Server_Error;
    private Matrix h = new Matrix();
    private Matrix i = new Matrix();
    private Matrix j = new Matrix();
    private Matrix k = new Matrix();
    private b l;
    private GestureDetector m;
    private ScaleGestureDetector n;
    private OnClickListener o;
    private ScaleType p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u = false;
    private boolean v = false;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        static {
            try {
                a[ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[ScaleType.FIT_START.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[ScaleType.FIT_END.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public interface a {
        float a();
    }

    public class b implements a {
        final /* synthetic */ PhotoView a;

        public b(PhotoView photoView) {
            this.a = photoView;
        }

        public float a() {
            return this.a.L.bottom;
        }
    }

    private class c implements Interpolator {
        final /* synthetic */ PhotoView a;
        private Interpolator b;

        private c(PhotoView photoView) {
            this.a = photoView;
            this.b = new DecelerateInterpolator();
        }

        public void a(Interpolator interpolator) {
            this.b = interpolator;
        }

        public float getInterpolation(float f) {
            if (this.b != null) {
                return this.b.getInterpolation(f);
            }
            return f;
        }
    }

    public class d implements a {
        final /* synthetic */ PhotoView a;

        public d(PhotoView photoView) {
            this.a = photoView;
        }

        public float a() {
            return (this.a.L.top + this.a.L.bottom) / 2.0f;
        }
    }

    public class e implements a {
        final /* synthetic */ PhotoView a;

        public e(PhotoView photoView) {
            this.a = photoView;
        }

        public float a() {
            return this.a.L.top;
        }
    }

    private class f implements Runnable {
        boolean a;
        OverScroller b;
        OverScroller c;
        Scroller d;
        Scroller e;
        Scroller f;
        a g;
        int h;
        int i;
        int j;
        int k;
        RectF l = new RectF();
        c m = new c();
        final /* synthetic */ PhotoView n;

        f(PhotoView photoView) {
            this.n = photoView;
            Context context = photoView.getContext();
            this.b = new OverScroller(context, this.m);
            this.d = new Scroller(context, this.m);
            this.c = new OverScroller(context, this.m);
            this.e = new Scroller(context, this.m);
            this.f = new Scroller(context, this.m);
        }

        public void a(Interpolator interpolator) {
            this.m.a(interpolator);
        }

        void a(int i, int i2, int i3, int i4) {
            this.j = 0;
            this.k = 0;
            this.b.startScroll(0, 0, i3, i4, this.n.b);
        }

        void a(float f, float f2) {
            this.d.startScroll((int) (f * 10000.0f), 0, (int) ((f2 - f) * 10000.0f), 0, this.n.b);
        }

        void a(float f, float f2, float f3, float f4, int i, a aVar) {
            this.e.startScroll((int) (f * 10000.0f), (int) (f2 * 10000.0f), (int) (f3 * 10000.0f), (int) (10000.0f * f4), i);
            this.g = aVar;
        }

        void a(int i, int i2) {
            this.f.startScroll(i, 0, i2 - i, 0, this.n.b);
        }

        void a(int i, int i2, int i3) {
            this.f.startScroll(i, 0, i2 - i, 0, i3);
        }

        void b(float f, float f2) {
            int i;
            int i2;
            int i3;
            int i4;
            int i5;
            int i6;
            int i7 = 0;
            this.h = f < 0.0f ? Integer.MAX_VALUE : 0;
            int abs = (int) (f > 0.0f ? Math.abs(this.n.L.left) : this.n.L.right - this.n.J.right);
            if (f < 0.0f) {
                abs = Integer.MAX_VALUE - abs;
            }
            if (f < 0.0f) {
                i = abs;
            } else {
                i = 0;
            }
            if (f < 0.0f) {
                i2 = Integer.MAX_VALUE;
            } else {
                i2 = abs;
            }
            if (f < 0.0f) {
                i3 = Integer.MAX_VALUE - i;
            } else {
                i3 = abs;
            }
            if (f2 < 0.0f) {
                abs = Integer.MAX_VALUE;
            } else {
                abs = 0;
            }
            this.i = abs;
            abs = (int) (f2 > 0.0f ? Math.abs(this.n.L.top) : this.n.L.bottom - this.n.J.bottom);
            if (f2 < 0.0f) {
                abs = Integer.MAX_VALUE - abs;
            }
            if (f2 < 0.0f) {
                i4 = abs;
            } else {
                i4 = 0;
            }
            if (f2 < 0.0f) {
                i5 = Integer.MAX_VALUE;
            } else {
                i5 = abs;
            }
            if (f2 < 0.0f) {
                i6 = Integer.MAX_VALUE - i4;
            } else {
                i6 = abs;
            }
            if (f == 0.0f) {
                i2 = 0;
                i = 0;
            }
            if (f2 == 0.0f) {
                i5 = 0;
                i4 = 0;
            }
            OverScroller overScroller = this.c;
            int i8 = this.h;
            int i9 = this.i;
            int i10 = (int) f;
            int i11 = (int) f2;
            i3 = Math.abs(i3) < this.n.e * 2 ? 0 : this.n.e;
            if (Math.abs(i6) >= this.n.e * 2) {
                i7 = this.n.e;
            }
            overScroller.fling(i8, i9, i10, i11, i, i2, i4, i5, i3, i7);
        }

        void a() {
            this.a = true;
            d();
        }

        void b() {
            this.n.removeCallbacks(this);
            this.b.abortAnimation();
            this.d.abortAnimation();
            this.c.abortAnimation();
            this.f.abortAnimation();
            this.a = false;
        }

        public void run() {
            boolean z;
            boolean z2 = true;
            boolean z3 = false;
            if (this.d.computeScrollOffset()) {
                this.n.E = ((float) this.d.getCurrX()) / 10000.0f;
                z = false;
            } else {
                z = true;
            }
            if (this.b.computeScrollOffset()) {
                int currY = this.b.getCurrY() - this.k;
                PhotoView.c(this.n, this.b.getCurrX() - this.j);
                PhotoView.d(this.n, currY);
                this.j = this.b.getCurrX();
                this.k = this.b.getCurrY();
                z = false;
            }
            if (this.c.computeScrollOffset()) {
                int currX = this.c.getCurrX() - this.h;
                currY = this.c.getCurrY() - this.i;
                this.h = this.c.getCurrX();
                this.i = this.c.getCurrY();
                PhotoView.c(this.n, currX);
                PhotoView.d(this.n, currY);
                z = false;
            }
            if (this.f.computeScrollOffset()) {
                this.n.D = (float) this.f.getCurrX();
                z = false;
            }
            if (this.e.computeScrollOffset() || this.n.S != null) {
                float currX2 = ((float) this.e.getCurrX()) / 10000.0f;
                float currY2 = ((float) this.e.getCurrY()) / 10000.0f;
                this.n.k.setScale(currX2, currY2, (this.n.L.left + this.n.L.right) / 2.0f, this.g.a());
                this.n.k.mapRect(this.l, this.n.L);
                if (currX2 == 1.0f) {
                    this.l.left = this.n.J.left;
                    this.l.right = this.n.J.right;
                }
                if (currY2 == 1.0f) {
                    this.l.top = this.n.J.top;
                    this.l.bottom = this.n.J.bottom;
                }
                this.n.S = this.l;
            }
            if (z) {
                this.a = false;
                if (this.n.A) {
                    if (this.n.L.left > 0.0f) {
                        PhotoView.f(this.n, this.n.L.left);
                    } else if (this.n.L.right < this.n.J.width()) {
                        PhotoView.e(this.n, (int) (this.n.J.width() - this.n.L.right));
                    }
                    z3 = true;
                }
                if (!this.n.B) {
                    z2 = z3;
                } else if (this.n.L.top > 0.0f) {
                    PhotoView.g(this.n, this.n.L.top);
                } else if (this.n.L.bottom < this.n.J.height()) {
                    PhotoView.f(this.n, (int) (this.n.J.height() - this.n.L.bottom));
                }
                if (z2) {
                    c();
                }
                this.n.invalidate();
                if (this.n.V != null) {
                    this.n.V.run();
                    this.n.V = null;
                    return;
                }
                return;
            }
            c();
            d();
        }

        private void c() {
            this.n.i.reset();
            this.n.i.postTranslate(-this.n.K.left, -this.n.K.top);
            this.n.i.postTranslate(this.n.Q.x, this.n.Q.y);
            this.n.i.postTranslate(-this.n.H, -this.n.I);
            this.n.i.postRotate(this.n.D, this.n.Q.x, this.n.Q.y);
            this.n.i.postScale(this.n.E, this.n.E, this.n.P.x, this.n.P.y);
            this.n.i.postTranslate((float) this.n.F, (float) this.n.G);
            this.n.k();
        }

        private void d() {
            if (this.a) {
                this.n.post(this);
            }
        }
    }

    static /* synthetic */ float a(PhotoView photoView, float f) {
        float f2 = photoView.C + f;
        photoView.C = f2;
        return f2;
    }

    static /* synthetic */ float b(PhotoView photoView, float f) {
        float f2 = photoView.D + f;
        photoView.D = f2;
        return f2;
    }

    static /* synthetic */ int c(PhotoView photoView, int i) {
        int i2 = photoView.F + i;
        photoView.F = i2;
        return i2;
    }

    static /* synthetic */ float d(PhotoView photoView, float f) {
        float f2 = photoView.E * f;
        photoView.E = f2;
        return f2;
    }

    static /* synthetic */ int d(PhotoView photoView, int i) {
        int i2 = photoView.G + i;
        photoView.G = i2;
        return i2;
    }

    static /* synthetic */ int e(PhotoView photoView, int i) {
        int i2 = photoView.F - i;
        photoView.F = i2;
        return i2;
    }

    static /* synthetic */ int f(PhotoView photoView, float f) {
        int i = (int) (((float) photoView.F) - f);
        photoView.F = i;
        return i;
    }

    static /* synthetic */ int f(PhotoView photoView, int i) {
        int i2 = photoView.G - i;
        photoView.G = i2;
        return i2;
    }

    static /* synthetic */ int g(PhotoView photoView, float f) {
        int i = (int) (((float) photoView.G) - f);
        photoView.G = i;
        return i;
    }

    public PhotoView(Context context) {
        super(context);
        a();
    }

    public PhotoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public PhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        super.setScaleType(ScaleType.MATRIX);
        if (this.p == null) {
            this.p = ScaleType.CENTER_INSIDE;
        }
        this.l = new b(this.aa);
        this.m = new GestureDetector(getContext(), this.ad);
        this.n = new ScaleGestureDetector(getContext(), this.ab);
        float f = getResources().getDisplayMetrics().density;
        this.d = (int) (f * 30.0f);
        this.e = (int) (f * 30.0f);
        this.f = (int) (f * 140.0f);
        this.a = 35;
        this.b = 200;
        this.c = 2.5f;
    }

    public int getDefaultAnimaDuring() {
        return 200;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
        this.o = onClickListener;
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType != ScaleType.MATRIX && scaleType != this.p) {
            this.p = scaleType;
            if (this.w) {
                b();
            }
        }
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.W = onLongClickListener;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.R.a(interpolator);
    }

    public int getAnimaDuring() {
        return this.b;
    }

    public void setAnimaDuring(int i) {
        this.b = i;
    }

    public void setMaxScale(float f) {
        this.c = f;
    }

    public float getMaxScale() {
        return this.c;
    }

    public void setMaxAnimFromWaiteTime(int i) {
        this.g = i;
    }

    public void setImageResource(int i) {
        Drawable drawable = null;
        try {
            drawable = getResources().getDrawable(i);
        } catch (Exception e) {
        }
        setImageDrawable(drawable);
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (drawable == null) {
            this.r = false;
        } else if (a(drawable)) {
            if (!this.r) {
                this.r = true;
            }
            b();
        }
    }

    private boolean a(Drawable drawable) {
        if ((drawable.getIntrinsicHeight() <= 0 || drawable.getIntrinsicWidth() <= 0) && ((drawable.getMinimumWidth() <= 0 || drawable.getMinimumHeight() <= 0) && (drawable.getBounds().width() <= 0 || drawable.getBounds().height() <= 0))) {
            return false;
        }
        return true;
    }

    private static int b(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (intrinsicWidth <= 0) {
            intrinsicWidth = drawable.getMinimumWidth();
        }
        if (intrinsicWidth <= 0) {
            return drawable.getBounds().width();
        }
        return intrinsicWidth;
    }

    private static int c(Drawable drawable) {
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicHeight <= 0) {
            intrinsicHeight = drawable.getMinimumHeight();
        }
        if (intrinsicHeight <= 0) {
            return drawable.getBounds().height();
        }
        return intrinsicHeight;
    }

    private void b() {
        float f = 1.0f;
        if (this.r && this.s) {
            float f2;
            this.h.reset();
            this.i.reset();
            this.y = false;
            Drawable drawable = getDrawable();
            int width = getWidth();
            int height = getHeight();
            int b = b(drawable);
            int c = c(drawable);
            this.K.set(0.0f, 0.0f, (float) b, (float) c);
            int i = (width - b) / 2;
            int i2 = (height - c) / 2;
            if (b > width) {
                f2 = ((float) width) / ((float) b);
            } else {
                f2 = 1.0f;
            }
            if (c > height) {
                f = ((float) height) / ((float) c);
            }
            if (f2 >= f) {
                f2 = f;
            }
            this.h.reset();
            this.h.postTranslate((float) i, (float) i2);
            this.h.postScale(f2, f2, this.O.x, this.O.y);
            this.h.mapRect(this.K);
            this.H = this.K.width() / 2.0f;
            this.I = this.K.height() / 2.0f;
            this.P.set(this.O);
            this.Q.set(this.P);
            k();
            switch (AnonymousClass6.a[this.p.ordinal()]) {
                case 1:
                    c();
                    break;
                case 2:
                    d();
                    break;
                case 3:
                    e();
                    break;
                case 4:
                    f();
                    break;
                case 5:
                    g();
                    break;
                case 6:
                    h();
                    break;
                case 7:
                    i();
                    break;
            }
            this.w = true;
            if (this.T != null && System.currentTimeMillis() - this.U < ((long) this.g)) {
                a(this.T);
            }
            this.T = null;
        }
    }

    private void c() {
        if (this.r && this.s) {
            Drawable drawable = getDrawable();
            int b = b(drawable);
            int c = c(drawable);
            if (((float) b) > this.J.width() || ((float) c) > this.J.height()) {
                float width = ((float) b) / this.L.width();
                float height = ((float) c) / this.L.height();
                if (width <= height) {
                    width = height;
                }
                this.E = width;
                this.i.postScale(this.E, this.E, this.O.x, this.O.y);
                k();
                j();
            }
        }
    }

    private void d() {
        if (this.L.width() < this.J.width() || this.L.height() < this.J.height()) {
            float width = this.J.width() / this.L.width();
            float height = this.J.height() / this.L.height();
            if (width <= height) {
                width = height;
            }
            this.E = width;
            this.i.postScale(this.E, this.E, this.O.x, this.O.y);
            k();
            j();
        }
    }

    private void e() {
        if (this.L.width() > this.J.width() || this.L.height() > this.J.height()) {
            float width = this.J.width() / this.L.width();
            float height = this.J.height() / this.L.height();
            if (width >= height) {
                width = height;
            }
            this.E = width;
            this.i.postScale(this.E, this.E, this.O.x, this.O.y);
            k();
            j();
        }
    }

    private void f() {
        if (this.L.width() < this.J.width()) {
            this.E = this.J.width() / this.L.width();
            this.i.postScale(this.E, this.E, this.O.x, this.O.y);
            k();
            j();
        }
    }

    private void g() {
        f();
        float f = -this.L.top;
        this.i.postTranslate(0.0f, f);
        k();
        j();
        this.G = (int) (f + ((float) this.G));
    }

    private void h() {
        f();
        float f = this.J.bottom - this.L.bottom;
        this.G = (int) (((float) this.G) + f);
        this.i.postTranslate(0.0f, f);
        k();
        j();
    }

    private void i() {
        this.i.postScale(this.J.width() / this.L.width(), this.J.height() / this.L.height(), this.O.x, this.O.y);
        k();
        j();
    }

    private void j() {
        Drawable drawable = getDrawable();
        this.K.set(0.0f, 0.0f, (float) b(drawable), (float) c(drawable));
        this.h.set(this.j);
        this.h.mapRect(this.K);
        this.H = this.K.width() / 2.0f;
        this.I = this.K.height() / 2.0f;
        this.E = 1.0f;
        this.F = 0;
        this.G = 0;
        this.i.reset();
    }

    private void k() {
        boolean z;
        boolean z2 = true;
        this.j.set(this.h);
        this.j.postConcat(this.i);
        setImageMatrix(this.j);
        this.i.mapRect(this.L, this.K);
        if (this.L.width() > this.J.width()) {
            z = true;
        } else {
            z = false;
        }
        this.A = z;
        if (this.L.height() <= this.J.height()) {
            z2 = false;
        }
        this.B = z2;
    }

    protected void onMeasure(int i, int i2) {
        if (this.r) {
            Drawable drawable = getDrawable();
            int b = b(drawable);
            int c = c(drawable);
            int size = MeasureSpec.getSize(i);
            int size2 = MeasureSpec.getSize(i2);
            int mode = MeasureSpec.getMode(i);
            int mode2 = MeasureSpec.getMode(i2);
            LayoutParams layoutParams = getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LayoutParams(-2, -2);
            }
            if (layoutParams.width == -1) {
                if (mode == 0) {
                    size = b;
                }
            } else if (mode != 1073741824) {
                if (mode != Integer.MIN_VALUE) {
                    size = b;
                } else if (b <= size) {
                    size = b;
                }
            }
            if (layoutParams.height == -1) {
                if (mode2 == 0) {
                    size2 = c;
                }
            } else if (mode2 != 1073741824) {
                if (mode2 != Integer.MIN_VALUE) {
                    size2 = c;
                } else if (c <= size2) {
                    size2 = c;
                }
            }
            if (this.x && ((float) b) / ((float) c) != ((float) size) / ((float) size2)) {
                float f = ((float) size2) / ((float) c);
                float f2 = ((float) size) / ((float) b);
                if (f >= f2) {
                    f = f2;
                }
                if (layoutParams.width != -1) {
                    size = (int) (((float) b) * f);
                }
                if (layoutParams.height != -1) {
                    size2 = (int) (((float) c) * f);
                }
            }
            setMeasuredDimension(size, size2);
            return;
        }
        super.onMeasure(i, i2);
    }

    public void setAdjustViewBounds(boolean z) {
        super.setAdjustViewBounds(z);
        this.x = z;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.J.set(0.0f, 0.0f, (float) i, (float) i2);
        this.O.set((float) (i / 2), (float) (i2 / 2));
        if (!this.s) {
            this.s = true;
            b();
        }
    }

    public void draw(Canvas canvas) {
        if (this.S != null) {
            canvas.clipRect(this.S);
            this.S = null;
        }
        super.draw(canvas);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.u) {
            return super.dispatchTouchEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (motionEvent.getPointerCount() >= 2) {
            this.q = true;
        }
        this.m.onTouchEvent(motionEvent);
        if (this.v) {
            this.l.a(motionEvent);
        }
        this.n.onTouchEvent(motionEvent);
        if (actionMasked != 1 && actionMasked != 3) {
            return true;
        }
        l();
        return true;
    }

    private void l() {
        if (!this.R.a) {
            float f;
            float f2;
            if (this.z || this.D % 90.0f != 0.0f) {
                f = (float) (((int) (this.D / 90.0f)) * 90);
                f2 = this.D % 90.0f;
                if (f2 > 45.0f) {
                    f += 90.0f;
                } else if (f2 < -45.0f) {
                    f -= 90.0f;
                }
                this.R.a((int) this.D, (int) f);
                this.D = f;
            }
            f = this.E;
            if (this.E < 1.0f) {
                this.R.a(this.E, 1.0f);
                f = 1.0f;
            } else if (this.E > this.c) {
                f = this.c;
                this.R.a(this.E, this.c);
            }
            float width = this.L.left + (this.L.width() / 2.0f);
            f2 = this.L.top + (this.L.height() / 2.0f);
            this.P.set(width, f2);
            this.Q.set(width, f2);
            this.F = 0;
            this.G = 0;
            this.k.reset();
            this.k.postTranslate(-this.K.left, -this.K.top);
            this.k.postTranslate(width - this.H, f2 - this.I);
            this.k.postScale(f, f, width, f2);
            this.k.postRotate(this.D, width, f2);
            this.k.mapRect(this.M, this.K);
            a(this.M);
            this.R.a();
        }
    }

    private void a(RectF rectF) {
        int i;
        int i2 = 0;
        if (rectF.width() <= this.J.width()) {
            if (!c(rectF)) {
                i = -((int) (((this.J.width() - rectF.width()) / 2.0f) - rectF.left));
            }
            i = 0;
        } else if (rectF.left > this.J.left) {
            i = (int) (rectF.left - this.J.left);
        } else {
            if (rectF.right < this.J.right) {
                i = (int) (rectF.right - this.J.right);
            }
            i = 0;
        }
        if (rectF.height() <= this.J.height()) {
            if (!b(rectF)) {
                i2 = -((int) (((this.J.height() - rectF.height()) / 2.0f) - rectF.top));
            }
        } else if (rectF.top > this.J.top) {
            i2 = (int) (rectF.top - this.J.top);
        } else if (rectF.bottom < this.J.bottom) {
            i2 = (int) (rectF.bottom - this.J.bottom);
        }
        if (i != 0 || i2 != 0) {
            if (!this.R.c.isFinished()) {
                this.R.c.abortAnimation();
            }
            this.R.a(this.F, this.G, -i, -i2);
        }
    }

    private boolean b(RectF rectF) {
        return Math.abs(((float) Math.round(rectF.top)) - ((this.J.height() - rectF.height()) / 2.0f)) < 1.0f;
    }

    private boolean c(RectF rectF) {
        return Math.abs(((float) Math.round(rectF.left)) - ((this.J.width() - rectF.width()) / 2.0f)) < 1.0f;
    }

    private float a(float f, float f2) {
        return (Math.abs(Math.abs(f) - ((float) this.f)) / ((float) this.f)) * f2;
    }

    private float b(float f, float f2) {
        return (Math.abs(Math.abs(f) - ((float) this.f)) / ((float) this.f)) * f2;
    }

    private void a(RectF rectF, RectF rectF2, RectF rectF3) {
        float f = rectF.left > rectF2.left ? rectF.left : rectF2.left;
        float f2 = rectF.right < rectF2.right ? rectF.right : rectF2.right;
        if (f > f2) {
            rectF3.set(0.0f, 0.0f, 0.0f, 0.0f);
            return;
        }
        float f3 = rectF.top > rectF2.top ? rectF.top : rectF2.top;
        float f4 = rectF.bottom < rectF2.bottom ? rectF.bottom : rectF2.bottom;
        if (f3 > f4) {
            rectF3.set(0.0f, 0.0f, 0.0f, 0.0f);
        } else {
            rectF3.set(f, f3, f2, f4);
        }
    }

    private void m() {
        if (!this.t) {
            a(this.J, this.L, this.N);
        }
    }

    public boolean a(float f) {
        if (this.L.width() <= this.J.width()) {
            return false;
        }
        if (f < 0.0f && ((float) Math.round(this.L.left)) - f >= this.J.left) {
            return false;
        }
        if (f <= 0.0f || ((float) Math.round(this.L.right)) - f > this.J.right) {
            return true;
        }
        return false;
    }

    public boolean b(float f) {
        if (this.L.height() <= this.J.height()) {
            return false;
        }
        if (f < 0.0f && ((float) Math.round(this.L.top)) - f >= this.J.top) {
            return false;
        }
        if (f <= 0.0f || ((float) Math.round(this.L.bottom)) - f > this.J.bottom) {
            return true;
        }
        return false;
    }

    public boolean canScrollHorizontally(int i) {
        if (this.q) {
            return true;
        }
        return a((float) i);
    }

    public boolean canScrollVertically(int i) {
        if (this.q) {
            return true;
        }
        return b((float) i);
    }

    public RectInfo getInfo() {
        RectF rectF = new RectF();
        int[] iArr = new int[2];
        a((View) this, iArr);
        rectF.set(((float) iArr[0]) + this.L.left, ((float) iArr[1]) + this.L.top, ((float) iArr[0]) + this.L.right, ((float) iArr[1]) + this.L.bottom);
        return new RectInfo(rectF, this.L, this.J, this.K, this.O, this.E, this.D, this.p);
    }

    private static void a(View view, int[] iArr) {
        iArr[0] = iArr[0] + view.getLeft();
        iArr[1] = iArr[1] + view.getTop();
        ViewParent parent = view.getParent();
        while (parent instanceof View) {
            View view2 = (View) parent;
            if (view2.getId() != 16908290) {
                iArr[0] = iArr[0] - view2.getScrollX();
                iArr[1] = iArr[1] - view2.getScrollY();
                iArr[0] = iArr[0] + view2.getLeft();
                iArr[1] = iArr[1] + view2.getTop();
                parent = view2.getParent();
            } else {
                return;
            }
        }
        iArr[0] = (int) (((float) iArr[0]) + 0.5f);
        iArr[1] = (int) (((float) iArr[1]) + 0.5f);
    }

    private void n() {
        this.i.reset();
        k();
        this.E = 1.0f;
        this.F = 0;
        this.G = 0;
    }

    public void a(RectInfo rectInfo) {
        if (this.w) {
            n();
            RectInfo info = getInfo();
            float width = rectInfo.b.width() / info.b.width();
            float height = rectInfo.b.height() / info.b.height();
            if (width >= height) {
                width = height;
            }
            height = rectInfo.a.left + (rectInfo.a.width() / 2.0f);
            float height2 = rectInfo.a.top + (rectInfo.a.height() / 2.0f);
            float width2 = info.a.left + (info.a.width() / 2.0f);
            float height3 = (info.a.height() / 2.0f) + info.a.top;
            this.i.reset();
            this.i.postTranslate(height - width2, height2 - height3);
            this.i.postScale(width, width, height, height2);
            this.i.postRotate(rectInfo.g, height, height2);
            k();
            this.P.set(height, height2);
            this.Q.set(height, height2);
            this.R.a(0, 0, (int) (-(height - width2)), (int) (-(height2 - height3)));
            this.R.a(width, 1.0f);
            this.R.a((int) rectInfo.g, 0);
            if (rectInfo.c.width() < rectInfo.b.width() || rectInfo.c.height() < rectInfo.b.height()) {
                height = rectInfo.c.width() / rectInfo.b.width();
                height3 = rectInfo.c.height() / rectInfo.b.height();
                if (height > 1.0f) {
                    height = 1.0f;
                }
                if (height3 > 1.0f) {
                    height3 = 1.0f;
                }
                a eVar = rectInfo.h == ScaleType.FIT_START ? new e(this) : rectInfo.h == ScaleType.FIT_END ? new b(this) : new d(this);
                this.R.a(height, height3, 1.0f - height, 1.0f - height3, this.b / 3, eVar);
                this.k.setScale(height, height3, (this.L.left + this.L.right) / 2.0f, eVar.a());
                this.k.mapRect(this.R.l, this.L);
                this.S = this.R.l;
            }
            this.R.a();
            return;
        }
        this.T = rectInfo;
        this.U = System.currentTimeMillis();
    }

    public void a(RectInfo rectInfo, Runnable runnable) {
        if (this.w) {
            this.R.b();
            this.F = 0;
            this.G = 0;
            float width = rectInfo.a.left + (rectInfo.a.width() / 2.0f);
            float height = rectInfo.a.top + (rectInfo.a.height() / 2.0f);
            this.P.set(this.L.left + (this.L.width() / 2.0f), this.L.top + (this.L.height() / 2.0f));
            this.Q.set(this.P);
            this.i.postRotate(-this.D, this.P.x, this.P.y);
            this.i.mapRect(this.L, this.K);
            float width2 = rectInfo.b.width() / this.K.width();
            float height2 = rectInfo.b.height() / this.K.height();
            if (width2 <= height2) {
                width2 = height2;
            }
            this.i.postRotate(this.D, this.P.x, this.P.y);
            this.i.mapRect(this.L, this.K);
            this.D %= 360.0f;
            this.R.a(0, 0, (int) (width - this.P.x), (int) (height - this.P.y));
            this.R.a(this.E, width2);
            this.R.a((int) this.D, (int) rectInfo.g, (this.b * 2) / 3);
            if (rectInfo.c.width() < rectInfo.a.width() || rectInfo.c.height() < rectInfo.a.height()) {
                height2 = rectInfo.c.width() / rectInfo.a.width();
                width2 = rectInfo.c.height() / rectInfo.a.height();
                width = height2 > 1.0f ? 1.0f : height2;
                if (width2 > 1.0f) {
                    width2 = 1.0f;
                }
                a eVar = rectInfo.h == ScaleType.FIT_START ? new e(this) : rectInfo.h == ScaleType.FIT_END ? new b(this) : new d(this);
                postDelayed(new Runnable(this) {
                    final /* synthetic */ PhotoView d;

                    public void run() {
                        this.d.R.a(1.0f, 1.0f, -1.0f + width, -1.0f + width2, this.d.b / 2, eVar);
                    }
                }, (long) (this.b / 2));
            }
            this.V = runnable;
            this.R.a();
        }
    }
}
