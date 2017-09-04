package com.qrcomic.widget.reader;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.OverScroller;
import android.widget.Scroller;
import com.qrcomic.activity.reader.QRComicReadingVerticalActivity;
import com.qrcomic.entity.ComicSectionPicInfo;

public class QRComicTouchImageView extends ImageView {
    private float A;
    private float B;
    private float[] C;
    private ScaleGestureDetector D;
    private GestureDetector E;
    private OnDoubleTapListener F = null;
    private OnTouchListener G;
    private final long H = 500;
    private long I = 0;
    private boolean J = false;
    float a;
    float b;
    boolean c;
    SimpleOnGestureListener d = new SimpleOnGestureListener(this) {
        final /* synthetic */ QRComicTouchImageView a;

        {
            this.a = r1;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            this.a.J = true;
            return false;
        }
    };
    private float e;
    private Context f;
    private QRComicReadingVerticalActivity g;
    private d h;
    private ScaleType i;
    private boolean j;
    private boolean k;
    private h l;
    private a m;
    private int n;
    private int o;
    private int p;
    private int q;
    private float r;
    private float s;
    private float t;
    private float u;
    private Matrix v;
    private Matrix w;
    private int x;
    private float y;
    private float z;

    static /* synthetic */ class AnonymousClass2 {
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
                a[ScaleType.FIT_XY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    @TargetApi(9)
    private class a {
        Scroller a;
        OverScroller b;
        boolean c;
        final /* synthetic */ QRComicTouchImageView d;

        public a(QRComicTouchImageView qRComicTouchImageView, Context context) {
            this.d = qRComicTouchImageView;
            if (VERSION.SDK_INT < 9) {
                this.c = true;
                this.a = new Scroller(context);
                return;
            }
            this.c = false;
            this.b = new OverScroller(context);
        }

        public void a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (this.c) {
                this.a.fling(i, i2, i3, i4, i5, i6, i7, i8);
            } else {
                this.b.fling(i, i2, i3, i4, i5, i6, i7, i8);
            }
        }

        public void a(boolean z) {
            if (this.c) {
                this.a.forceFinished(z);
            } else {
                this.b.forceFinished(z);
            }
        }

        public boolean a() {
            if (this.c) {
                return this.a.isFinished();
            }
            return this.b.isFinished();
        }

        public boolean b() {
            if (this.c) {
                return this.a.computeScrollOffset();
            }
            this.b.computeScrollOffset();
            return this.b.computeScrollOffset();
        }

        public int c() {
            if (this.c) {
                return this.a.getCurrX();
            }
            return this.b.getCurrX();
        }

        public int d() {
            if (this.c) {
                return this.a.getCurrY();
            }
            return this.b.getCurrY();
        }
    }

    private class b implements Runnable {
        final /* synthetic */ QRComicTouchImageView a;
        private long b;
        private float c;
        private float d;
        private float e;
        private float f;
        private boolean g;
        private AccelerateDecelerateInterpolator h = new AccelerateDecelerateInterpolator();

        b(QRComicTouchImageView qRComicTouchImageView, float f, float f2, float f3, boolean z) {
            this.a = qRComicTouchImageView;
            qRComicTouchImageView.setState(4);
            this.b = System.currentTimeMillis();
            this.c = qRComicTouchImageView.e;
            this.d = f;
            this.g = z;
            this.e = f2;
            this.f = f3;
        }

        public void run() {
            float a = a();
            this.a.a(a(a), this.e, this.f, this.g);
            if (this.d == this.a.y && !this.a.c) {
                this.a.f();
            }
            this.a.setImageMatrix(this.a.v);
            if (a < 1.0f) {
                this.a.a((Runnable) this);
            } else {
                this.a.setState(0);
            }
        }

        private float a() {
            return this.h.getInterpolation(Math.min(1.0f, ((float) (System.currentTimeMillis() - this.b)) / 350.0f));
        }

        private double a(float f) {
            return ((double) (this.c + ((this.d - this.c) * f))) / ((double) this.a.e);
        }
    }

    private class c implements Runnable {
        final /* synthetic */ QRComicTouchImageView a;
        private long b;
        private float c;
        private float d;
        private float e;
        private float f;
        private boolean g;
        private AccelerateDecelerateInterpolator h = new AccelerateDecelerateInterpolator();
        private PointF i;
        private PointF j;

        c(QRComicTouchImageView qRComicTouchImageView, float f, float f2, float f3, boolean z) {
            this.a = qRComicTouchImageView;
            qRComicTouchImageView.setState(4);
            this.b = System.currentTimeMillis();
            this.c = qRComicTouchImageView.e;
            this.d = f;
            this.g = z;
            PointF a = qRComicTouchImageView.a(f2, f3, false);
            this.e = a.x;
            this.f = a.y;
            this.i = qRComicTouchImageView.a(this.e, this.f);
            this.j = new PointF((float) (qRComicTouchImageView.n / 2), (float) (qRComicTouchImageView.o / 2));
        }

        public void run() {
            float a = a();
            this.a.a(b(a), this.e, this.f, this.g);
            a(a);
            this.a.f();
            this.a.setImageMatrix(this.a.v);
            if (a < 1.0f) {
                this.a.a((Runnable) this);
            } else {
                this.a.setState(0);
            }
        }

        private void a(float f) {
            float f2 = this.i.x + ((this.j.x - this.i.x) * f);
            float f3 = this.i.y + ((this.j.y - this.i.y) * f);
            PointF a = this.a.a(this.e, this.f);
            this.a.v.postTranslate(f2 - a.x, f3 - a.y);
        }

        private float a() {
            return this.h.getInterpolation(Math.min(1.0f, ((float) (System.currentTimeMillis() - this.b)) / 500.0f));
        }

        private double b(float f) {
            return ((double) (this.c + ((this.d - this.c) * f))) / ((double) this.a.e);
        }
    }

    private class d implements Runnable {
        a a;
        int b;
        int c;
        final /* synthetic */ QRComicTouchImageView d;

        d(QRComicTouchImageView qRComicTouchImageView, int i, int i2) {
            int g;
            int i3;
            int i4;
            int i5;
            this.d = qRComicTouchImageView;
            qRComicTouchImageView.setState(3);
            this.a = new a(qRComicTouchImageView, qRComicTouchImageView.f);
            qRComicTouchImageView.v.getValues(qRComicTouchImageView.C);
            int i6 = (int) qRComicTouchImageView.C[2];
            int i7 = (int) qRComicTouchImageView.C[5];
            if (qRComicTouchImageView.getImageWidth() > ((float) qRComicTouchImageView.n)) {
                g = qRComicTouchImageView.n - ((int) qRComicTouchImageView.getImageWidth());
                i3 = 0;
            } else {
                i3 = i6;
                g = i6;
            }
            if (qRComicTouchImageView.getImageHeight() > ((float) qRComicTouchImageView.o)) {
                i4 = qRComicTouchImageView.o - ((int) qRComicTouchImageView.getImageHeight());
                i5 = 0;
            } else {
                i5 = i7;
                i4 = i7;
            }
            this.a.a(i6, i7, i, i2, g, i3, i4, i5);
            this.b = i6;
            this.c = i7;
        }

        public void a() {
            if (this.a != null) {
                this.d.setState(0);
                this.a.a(true);
            }
        }

        public void run() {
            if (this.a.a()) {
                this.a = null;
            } else if (this.a.b()) {
                int c = this.a.c();
                int d = this.a.d();
                int i = c - this.b;
                int i2 = d - this.c;
                this.b = c;
                this.c = d;
                this.d.v.postTranslate((float) i, (float) i2);
                this.d.e();
                this.d.setImageMatrix(this.d.v);
                this.d.a((Runnable) this);
            }
        }
    }

    private class e extends SimpleOnGestureListener {
        final /* synthetic */ QRComicTouchImageView a;

        private e(QRComicTouchImageView qRComicTouchImageView) {
            this.a = qRComicTouchImageView;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            int i = this.a.g.at;
            int i2 = this.a.g.as;
            int i3 = (int) (((float) i) * 0.117f);
            int i4 = (int) (((float) i2) * 0.21f);
            int i5 = this.a.g.aX;
            ComicSectionPicInfo s;
            if (!this.a.g.l() && (motionEvent.getX() < ((float) i4) || (((float) i2) - motionEvent.getX() > ((float) i4) && motionEvent.getY() < ((float) i3)))) {
                if (this.a.g.Z.C - 1 >= 0 || this.a.g.Z.E - 1 >= 0) {
                    s = this.a.getPicInfo();
                    if (s == null || s.index != 0) {
                        this.a.g.al.setCurrentItem(i5 - 1);
                    } else {
                        if (this.a.g.Z.d((com.qrcomic.entity.f) this.a.g.Z.w.get(this.a.g.Z.E - 1))) {
                            this.a.g.al.setCurrentItem(i5 - 1);
                        } else {
                            this.a.g.al.a = true;
                            this.a.g.al.getPageChangeListener().a();
                        }
                    }
                } else {
                    this.a.g.K();
                }
                a("1");
            } else if (!this.a.g.l() && (((float) i2) - motionEvent.getX() < ((float) i4) || (motionEvent.getX() > ((float) i4) && ((float) i) - motionEvent.getY() < ((float) i3)))) {
                if (this.a.g.Z.E + 1 < this.a.g.Z.u.size() || this.a.g.Z.C + 1 < this.a.g.Z.o.e || !(this.a.g.Z.L == null || this.a.g.Z.K)) {
                    s = this.a.getPicInfo();
                    if (s == null || s.index != this.a.g.Z.r.size() - 1) {
                        this.a.g.al.setCurrentItem(i5 + 1);
                    } else {
                        if (this.a.g.Z.d((com.qrcomic.entity.f) this.a.g.Z.w.get(this.a.g.Z.E + 1))) {
                            this.a.g.al.setCurrentItem(i5 + 1);
                        } else {
                            this.a.g.al.a = true;
                            this.a.g.al.getPageChangeListener().b();
                        }
                    }
                } else {
                    this.a.g.L();
                }
                a("2");
            } else if (this.a.m != null) {
                this.a.m.a();
            }
            this.a.J = false;
            return this.a.performClick();
        }

        private void a(String str) {
            com.qrcomic.activity.reader.a aVar = this.a.g.Z;
            if (aVar != null && aVar.o == null) {
            }
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (this.a.m != null) {
                this.a.m.b(motionEvent);
            }
            this.a.performClick();
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (this.a.c && (Math.abs(motionEvent.getX() - motionEvent2.getX()) > 120.0f || Math.abs(motionEvent.getY() - motionEvent2.getY()) > 120.0f)) {
                this.a.c = false;
            }
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (this.a.h != null) {
                this.a.h.a();
            }
            this.a.h = new d(this.a, (int) f, (int) f2);
            this.a.a(this.a.h);
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            this.a.J = false;
            if (this.a.g()) {
                return true;
            }
            if (this.a.m != null) {
                this.a.m.c();
            }
            if (System.currentTimeMillis() - this.a.I < 500) {
                return true;
            }
            boolean onDoubleTap;
            this.a.I = System.currentTimeMillis();
            if (this.a.F != null) {
                onDoubleTap = this.a.F.onDoubleTap(motionEvent);
            } else {
                onDoubleTap = false;
            }
            if (this.a.x == 0) {
                float o = this.a.e == this.a.y ? this.a.z : this.a.y;
                if (o == this.a.z) {
                    this.a.a = motionEvent.getX();
                    this.a.b = motionEvent.getY();
                    this.a.a(new b(this.a, o, this.a.a, this.a.b, false));
                    this.a.c = true;
                } else if (this.a.c) {
                    this.a.a(new b(this.a, o, this.a.a, this.a.b, false));
                } else {
                    this.a.a(new b(this.a, o, (float) (this.a.n / 2), (float) (this.a.o / 2), false));
                }
                onDoubleTap = true;
            }
            return onDoubleTap;
        }

        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            if (System.currentTimeMillis() - this.a.I < 500) {
                return true;
            }
            this.a.I = System.currentTimeMillis();
            if (this.a.F != null) {
                return this.a.F.onDoubleTapEvent(motionEvent);
            }
            return false;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            this.a.J = true;
            return super.onSingleTapUp(motionEvent);
        }
    }

    private class f implements OnTouchListener {
        final /* synthetic */ QRComicTouchImageView a;
        private PointF b;

        private f(QRComicTouchImageView qRComicTouchImageView) {
            this.a = qRComicTouchImageView;
            this.b = new PointF();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTouch(android.view.View r8, android.view.MotionEvent r9) {
            /*
            r7 = this;
            r5 = 0;
            r4 = 0;
            r6 = 1;
            r0 = r7.a;
            r0 = r0.D;
            r0.onTouchEvent(r9);
            r0 = r7.a;
            r0 = r0.E;
            r0.onTouchEvent(r9);
            r0 = new android.graphics.PointF;
            r1 = r9.getX();
            r2 = r9.getY();
            r0.<init>(r1, r2);
            r1 = r9.getAction();
            r1 = r1 & 255;
            r2 = 2;
            if (r1 != r2) goto L_0x0089;
        L_0x002b:
            r1 = r0.x;
            r2 = r7.b;
            r2 = r2.x;
            r1 = r1 - r2;
            r1 = (int) r1;
            r2 = r0.y;
            r3 = r7.b;
            r3 = r3.y;
            r2 = r2 - r3;
            r2 = (int) r2;
            r3 = r7.a;
            r3 = r3.m;
            if (r3 == 0) goto L_0x004c;
        L_0x0043:
            r3 = r7.a;
            r3 = r3.m;
            r3.a(r1, r2);
        L_0x004c:
            r1 = r7.a;
            r1 = r1.x;
            if (r1 == 0) goto L_0x0065;
        L_0x0054:
            r1 = r7.a;
            r1 = r1.x;
            if (r1 == r6) goto L_0x0065;
        L_0x005c:
            r1 = r7.a;
            r1 = r1.x;
            r2 = 3;
            if (r1 != r2) goto L_0x006c;
        L_0x0065:
            r1 = r9.getAction();
            switch(r1) {
                case 0: goto L_0x00a4;
                case 1: goto L_0x013a;
                case 2: goto L_0x00e5;
                case 3: goto L_0x013a;
                case 4: goto L_0x006c;
                case 5: goto L_0x006c;
                case 6: goto L_0x0152;
                default: goto L_0x006c;
            };
        L_0x006c:
            r0 = r7.a;
            r1 = r7.a;
            r1 = r1.v;
            r0.setImageMatrix(r1);
            r0 = r7.a;
            r0 = r0.G;
            if (r0 == 0) goto L_0x0088;
        L_0x007f:
            r0 = r7.a;
            r0 = r0.G;
            r0.onTouch(r8, r9);
        L_0x0088:
            return r6;
        L_0x0089:
            r1 = r9.getAction();
            r1 = r1 & 255;
            r2 = 5;
            if (r1 != r2) goto L_0x004c;
        L_0x0092:
            r1 = r7.a;
            r1 = r1.m;
            if (r1 == 0) goto L_0x004c;
        L_0x009a:
            r1 = r7.a;
            r1 = r1.m;
            r1.c(r9);
            goto L_0x004c;
        L_0x00a4:
            r1 = r7.a;
            r1 = r1.m;
            if (r1 == 0) goto L_0x00b8;
        L_0x00ac:
            r1 = r7.a;
            r1 = r1.m;
            r1 = r1.a(r9);
            if (r1 != 0) goto L_0x0088;
        L_0x00b8:
            r1 = r7.a;
            r1 = r1.g;
            if (r1 == 0) goto L_0x00c9;
        L_0x00c0:
            r1 = r7.a;
            r1 = r1.g;
            r1.a(r6);
        L_0x00c9:
            r1 = r7.b;
            r1.set(r0);
            r0 = r7.a;
            r0 = r0.h;
            if (r0 == 0) goto L_0x00df;
        L_0x00d6:
            r0 = r7.a;
            r0 = r0.h;
            r0.a();
        L_0x00df:
            r0 = r7.a;
            r0.setState(r6);
            goto L_0x006c;
        L_0x00e5:
            r1 = r7.a;
            r1 = r1.x;
            if (r1 != r6) goto L_0x006c;
        L_0x00ed:
            r1 = r0.x;
            r2 = r7.b;
            r2 = r2.x;
            r1 = r1 - r2;
            r2 = r0.y;
            r3 = r7.b;
            r3 = r3.y;
            r2 = r2 - r3;
            r3 = r7.a;
            r4 = r7.a;
            r4 = r4.n;
            r4 = (float) r4;
            r5 = r7.a;
            r5 = r5.getImageWidth();
            r1 = r3.b(r1, r4, r5);
            r3 = r7.a;
            r4 = r7.a;
            r4 = r4.o;
            r4 = (float) r4;
            r5 = r7.a;
            r5 = r5.getImageHeight();
            r2 = r3.b(r2, r4, r5);
            r3 = r7.a;
            r3 = r3.v;
            r3.postTranslate(r1, r2);
            r1 = r7.a;
            r1.e();
            r1 = r7.b;
            r2 = r0.x;
            r0 = r0.y;
            r1.set(r2, r0);
            goto L_0x006c;
        L_0x013a:
            r0 = r7.a;
            r0 = r0.m;
            if (r0 == 0) goto L_0x014b;
        L_0x0142:
            r0 = r7.a;
            r0 = r0.m;
            r0.a(r4, r4);
        L_0x014b:
            r0 = r7.a;
            r0.setState(r5);
            goto L_0x006c;
        L_0x0152:
            r0 = r7.a;
            r0.setState(r5);
            goto L_0x006c;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qrcomic.widget.reader.QRComicTouchImageView.f.onTouch(android.view.View, android.view.MotionEvent):boolean");
        }
    }

    private class g extends SimpleOnScaleGestureListener {
        final /* synthetic */ QRComicTouchImageView a;

        private g(QRComicTouchImageView qRComicTouchImageView) {
            this.a = qRComicTouchImageView;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            if (!this.a.g()) {
                this.a.setState(2);
            }
            return true;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            if (!this.a.g()) {
                this.a.a((double) scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), true);
            }
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            int i = 0;
            if (!this.a.g()) {
                super.onScaleEnd(scaleGestureDetector);
                this.a.setState(0);
                float n = this.a.e;
                if (this.a.e > this.a.z) {
                    n = this.a.z;
                    i = 1;
                } else if (this.a.e < this.a.y) {
                    n = this.a.y;
                    i = 1;
                }
                if (i != 0) {
                    this.a.a(new c(this.a, n, (float) (this.a.n / 2), (float) (this.a.o / 2), true));
                }
                if (this.a.m != null) {
                    this.a.m.b();
                }
            }
        }
    }

    private class h {
        public float a;
        public float b;
        public float c;
        public ScaleType d;
        final /* synthetic */ QRComicTouchImageView e;

        public h(QRComicTouchImageView qRComicTouchImageView, float f, float f2, float f3, ScaleType scaleType) {
            this.e = qRComicTouchImageView;
            this.a = f;
            this.b = f2;
            this.c = f3;
            this.d = scaleType;
        }
    }

    public QRComicTouchImageView(Context context) {
        super(context);
        a(context);
    }

    public QRComicTouchImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public QRComicTouchImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        super.setClickable(true);
        this.f = context;
        this.D = new ScaleGestureDetector(context, new g());
        this.E = new GestureDetector(context, new e());
        this.v = new Matrix();
        this.w = new Matrix();
        this.C = new float[9];
        this.e = 1.0f;
        if (this.i == null) {
            this.i = ScaleType.FIT_CENTER;
        }
        this.y = 1.0f;
        this.z = 2.0f;
        this.A = 0.05f * this.y;
        this.B = 10.0f * this.z;
        setImageMatrix(this.v);
        setScaleType(ScaleType.MATRIX);
        setState(0);
        this.k = false;
        super.setOnTouchListener(new f());
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.G = onTouchListener;
    }

    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
        this.F = onDoubleTapListener;
    }

    public void setAttachedActivity(QRComicReadingVerticalActivity qRComicReadingVerticalActivity) {
        this.g = qRComicReadingVerticalActivity;
    }

    private void setState(int i) {
        this.x = i;
    }

    public void setOnComicTouchListener(a aVar) {
        this.m = aVar;
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        c();
        d();
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        c();
        d();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        c();
        d();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        c();
        d();
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType == ScaleType.FIT_START || scaleType == ScaleType.FIT_END) {
            throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
        } else if (scaleType == ScaleType.MATRIX) {
            super.setScaleType(ScaleType.MATRIX);
        } else {
            this.i = scaleType;
            if (this.k) {
                setZoom(this);
            }
        }
    }

    public ScaleType getScaleType() {
        return this.i;
    }

    public boolean a() {
        return this.e != 1.0f;
    }

    private void c() {
        if (this.v != null && this.o != 0 && this.n != 0) {
            this.v.getValues(this.C);
            this.w.setValues(this.C);
            this.u = this.s;
            this.t = this.r;
            this.q = this.o;
            this.p = this.n;
        }
    }

    private ComicSectionPicInfo getPicInfo() {
        com.qrcomic.activity.reader.a aVar = this.g.Z;
        if (aVar.r == null || aVar.C < 0 || aVar.C >= aVar.r.size()) {
            return null;
        }
        return (ComicSectionPicInfo) aVar.r.get(aVar.C);
    }

    private void d() {
        float f = 0.0f;
        Drawable drawable = getDrawable();
        if (drawable != null && drawable.getIntrinsicWidth() != 0 && drawable.getIntrinsicHeight() != 0 && this.v != null && this.w != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            float f2 = ((float) this.n) / ((float) intrinsicWidth);
            float f3 = ((float) this.o) / ((float) intrinsicHeight);
            switch (AnonymousClass2.a[this.i.ordinal()]) {
                case 1:
                    f2 = 1.0f;
                    f3 = 1.0f;
                    break;
                case 2:
                    f2 = Math.max(f2, f3);
                    f3 = f2;
                    break;
                case 3:
                    f2 = Math.min(1.0f, Math.min(f2, f2));
                    f3 = f2;
                    break;
                case 4:
                    f3 = f2;
                    break;
                case 5:
                    float f4 = f3;
                    f3 = f2;
                    f2 = f4;
                    break;
                default:
                    throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
            }
            float f5 = ((float) this.n) - (((float) intrinsicWidth) * f3);
            float f6 = ((float) this.o) - (((float) intrinsicHeight) * f2);
            this.r = ((float) this.n) - f5;
            this.s = ((float) this.o) - f6;
            if (a() || this.j) {
                if (this.u == 0.0f || this.u == 0.0f) {
                    c();
                }
                this.w.getValues(this.C);
                this.C[0] = (this.r / ((float) intrinsicWidth)) * this.e;
                this.C[4] = (this.s / ((float) intrinsicHeight)) * this.e;
                float f7 = this.C[2];
                float f8 = f6 > 0.0f ? f6 / 2.0f : 0.0f;
                a(2, f7, this.e * this.t, getImageWidth(), this.p, this.n, intrinsicWidth);
                a(5, f8, this.u * this.e, getImageHeight(), this.q, this.o, intrinsicHeight);
                this.v.setValues(this.C);
            } else {
                this.v.setScale(f3, f2);
                this.v.postTranslate(f5 / 2.0f, f6 > 0.0f ? f6 / 2.0f : 0.0f);
                this.e = 1.0f;
                StringBuilder append = new StringBuilder().append("Translate:[ X = ").append(f5).append(" , Y = ");
                if (f6 > 0.0f) {
                    f = f6 / 2.0f;
                }
                a(append.append(f).append(" ]").append(" \n").append("Scale:[ X = ").append(f3).append(" , ").append("Y = ").append(f2).append(" ]").toString());
            }
            e();
            setImageMatrix(this.v);
        }
    }

    private void a(int i, float f, float f2, float f3, int i2, int i3, int i4) {
        if (f3 < ((float) i3)) {
            this.C[i] = (((float) i3) - (((float) i4) * this.C[0])) * 0.5f;
            if (i == 5) {
                a("TRANS Y ---- A:" + this.C[i]);
            }
        } else if (f > 0.0f) {
            r0 = -((f3 - ((float) i3)) * 0.5f);
            this.C[i] = 0.0f;
            if (i == 5) {
                a("TRANS Y ---- B:" + this.C[i]);
            }
        } else {
            r0 = -((((Math.abs(f) + (((float) i2) * 0.5f)) / f2) * f3) - (((float) i3) * 0.5f));
            this.C[i] = 0.0f;
            if (i == 5) {
                a("TRANS Y ---- C:" + this.C[i]);
            }
        }
    }

    public float getMaxZoom() {
        return this.z;
    }

    public void setMaxZoom(float f) {
        this.z = f;
        this.B = 10.0f * this.z;
    }

    public float getMinZoom() {
        return this.y;
    }

    public float getCurrentZoom() {
        return this.e;
    }

    public void setMinZoom(float f) {
        this.y = f;
        this.A = 0.05f * this.y;
    }

    public void setZoom(float f) {
        setZoom(f, 0.5f, 0.5f);
    }

    public void setZoom(float f, float f2, float f3) {
        setZoom(f, f2, f3, this.i);
    }

    public void setZoom(float f, float f2, float f3, ScaleType scaleType) {
        if (this.k) {
            if (scaleType != this.i) {
                setScaleType(scaleType);
            }
            b();
            a((double) f, (float) (this.n / 2), (float) (this.o / 2), true);
            this.v.getValues(this.C);
            this.C[2] = -((getImageWidth() * f2) - (((float) this.n) * 0.5f));
            this.C[5] = -((getImageHeight() * f3) - (((float) this.o) * 0.5f));
            this.v.setValues(this.C);
            e();
            setImageMatrix(this.v);
            return;
        }
        this.l = new h(this, f, f2, f3, scaleType);
    }

    public void setZoom(QRComicTouchImageView qRComicTouchImageView) {
        PointF scrollPosition = qRComicTouchImageView.getScrollPosition();
        setZoom(qRComicTouchImageView.getCurrentZoom(), scrollPosition.x, scrollPosition.y, qRComicTouchImageView.getScaleType());
    }

    public PointF getScrollPosition() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        PointF a = a((float) (this.n / 2), (float) (this.o / 2), true);
        a.x /= (float) intrinsicWidth;
        a.y /= (float) intrinsicHeight;
        return a;
    }

    public void setScrollPosition(float f, float f2) {
        setZoom(this.e, f, f2);
    }

    private void e() {
        this.v.getValues(this.C);
        float f = this.C[2];
        float f2 = this.C[5];
        f = a(f, (float) this.n, getImageWidth());
        f2 = a(f2, (float) this.o, getImageHeight());
        if (!(f == 0.0f && f2 == 0.0f)) {
            this.v.postTranslate(f, f2);
        }
        a("fixTrans:[ X = " + f + " , Y = " + f2 + "]");
    }

    private void f() {
        e();
        this.v.getValues(this.C);
        if (getImageWidth() < ((float) this.n)) {
            this.C[2] = (((float) this.n) - getImageWidth()) / 2.0f;
        }
        if (getImageHeight() < ((float) this.o)) {
            this.C[5] = (((float) this.o) - getImageHeight()) / 2.0f;
        }
        this.v.setValues(this.C);
    }

    private float a(float f, float f2, float f3) {
        float f4;
        float f5;
        if (f3 <= f2) {
            f4 = f2 - f3;
            f5 = 0.0f;
        } else {
            f5 = f2 - f3;
            f4 = 0.0f;
        }
        if (f < f5) {
            return (-f) + f5;
        }
        if (f > f4) {
            return (-f) + f4;
        }
        return 0.0f;
    }

    public boolean canScrollHorizontally(int i) {
        this.v.getValues(this.C);
        float f = this.C[2];
        if (getImageWidth() < ((float) this.n)) {
            return false;
        }
        if (f >= -1.0f && i < 0) {
            return false;
        }
        if ((Math.abs(f) + ((float) this.n)) + 1.0f < getImageWidth() || i <= 0) {
            return true;
        }
        return false;
    }

    public void b() {
        this.e = 1.0f;
        d();
    }

    private void a(double d, float f, float f2, boolean z) {
        float f3;
        float f4;
        if (z) {
            f3 = this.A;
            f4 = this.B;
        } else {
            f3 = this.y;
            f4 = this.z;
        }
        float f5 = this.e;
        this.e = (float) (((double) this.e) * d);
        if (this.e > f4) {
            this.e = f4;
            d = (double) (f4 / f5);
        } else if (this.e < f3) {
            this.e = f3;
            d = (double) (f3 / f5);
        }
        this.v.postScale((float) d, (float) d, f, f2);
    }

    private int a(int i, int i2, int i3) {
        switch (i) {
            case Integer.MIN_VALUE:
                return Math.min(i3, i2);
            case 0:
                return i3;
            default:
                return i2;
        }
    }

    private float getImageWidth() {
        return this.r * this.e;
    }

    public float getImageHeight() {
        return this.s * this.e;
    }

    private float b(float f, float f2, float f3) {
        if (f3 <= f2) {
            return 0.0f;
        }
        return f;
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putFloat("saveScale", this.e);
        bundle.putFloat("matchViewHeight", this.s);
        bundle.putFloat("matchViewWidth", this.r);
        bundle.putInt("viewWidth", this.n);
        bundle.putInt("viewHeight", this.o);
        this.v.getValues(this.C);
        bundle.putFloatArray("matrix", this.C);
        bundle.putBoolean("imageRendered", this.j);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.e = bundle.getFloat("saveScale");
            this.C = bundle.getFloatArray("matrix");
            this.w.setValues(this.C);
            this.u = bundle.getFloat("matchViewHeight");
            this.t = bundle.getFloat("matchViewWidth");
            this.q = bundle.getInt("viewHeight");
            this.p = bundle.getInt("viewWidth");
            this.j = bundle.getBoolean("imageRendered");
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected void onMeasure(int i, int i2) {
        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0) {
            setMeasuredDimension(0, 0);
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i2);
        this.n = a(mode, size, intrinsicWidth);
        this.o = a(mode2, size2, intrinsicHeight);
        setMeasuredDimension(this.n, this.o);
        d();
    }

    protected void onDraw(Canvas canvas) {
        this.k = true;
        this.j = true;
        if (this.l != null) {
            setZoom(this.l.a, this.l.b, this.l.c, this.l.d);
            this.l = null;
        }
        try {
            super.onDraw(canvas);
        } catch (Exception e) {
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        c();
    }

    @TargetApi(16)
    private void a(Runnable runnable) {
        if (VERSION.SDK_INT >= 16) {
            postOnAnimation(runnable);
        } else {
            postDelayed(runnable, 16);
        }
    }

    private boolean g() {
        if (this.g == null || !this.g.l()) {
            return false;
        }
        this.g.w();
        return true;
    }

    private PointF a(float f, float f2, boolean z) {
        this.v.getValues(this.C);
        float intrinsicWidth = (float) getDrawable().getIntrinsicWidth();
        float intrinsicHeight = (float) getDrawable().getIntrinsicHeight();
        float f3 = this.C[2];
        float imageWidth = ((f - f3) * intrinsicWidth) / getImageWidth();
        f3 = ((f2 - this.C[5]) * intrinsicHeight) / getImageHeight();
        if (z) {
            imageWidth = Math.min(Math.max(imageWidth, 0.0f), intrinsicWidth);
            f3 = Math.min(Math.max(f3, 0.0f), intrinsicHeight);
        }
        return new PointF(imageWidth, f3);
    }

    private PointF a(float f, float f2) {
        this.v.getValues(this.C);
        float intrinsicWidth = f / ((float) getDrawable().getIntrinsicWidth());
        float intrinsicHeight = f2 / ((float) getDrawable().getIntrinsicHeight());
        return new PointF((intrinsicWidth * getImageWidth()) + this.C[2], (intrinsicHeight * getImageHeight()) + this.C[5]);
    }

    public Matrix getCurrentMatrix() {
        return this.v;
    }

    private void a(String str) {
        if (com.qrcomic.util.g.a()) {
            com.qrcomic.util.g.a(getClass().getSimpleName(), com.qrcomic.util.g.d, str);
        }
    }
}
