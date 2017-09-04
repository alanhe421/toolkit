package com.qrcomic.widget.reader;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.z;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.Scroller;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.tencent.smtt.sdk.WebView;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QRComicScrollReaderListView extends AdapterView<ListAdapter> {
    private static final Interpolator ao = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
        }
    };
    private int A;
    private int B;
    private f C;
    private int D;
    private VelocityTracker E;
    private GestureDetector F;
    private boolean G;
    private e H;
    private int I;
    private BaseAdapter J;
    private int K;
    private int L;
    private int M;
    private Drawable N;
    private int O;
    private int P;
    private DataSetObserver Q;
    private boolean R;
    private boolean S;
    private View T;
    private int U;
    private Integer V;
    private boolean W;
    public a a;
    private int aa;
    private boolean ab;
    private boolean ac;
    private boolean ad;
    private boolean ae;
    private boolean af;
    private boolean ag;
    private c ah;
    private c ai;
    private d aj;
    private b ak;
    private a al;
    private List<Queue<View>> am;
    private float an;
    private boolean ap;
    private boolean aq;
    public int b;
    public boolean c;
    public int d;
    OnDoubleTapListener e;
    SimpleOnGestureListener f;
    private final int g;
    private int h;
    private int i;
    private int j;
    private PointF k;
    private PointF l;
    private Matrix m;
    private Matrix n;
    private Matrix o;
    private Matrix p;
    private PointF q;
    private float[] r;
    private float[] s;
    private float[] t;
    private long u;
    private int v;
    private float w;
    private float x;
    private int y;
    private int z;

    public interface c {
        void a();

        void a(ComicSectionPicInfo comicSectionPicInfo);

        void b();
    }

    private class a implements Runnable {
        final /* synthetic */ QRComicScrollReaderListView a;
        private final Scroller b;
        private int c;
        private int d;
        private long e;
        private int f;
        private int g;
        private int h;
        private int i;
        private boolean j;

        private a(QRComicScrollReaderListView qRComicScrollReaderListView, Context context) {
            this.a = qRComicScrollReaderListView;
            this.g = 0;
            this.h = 0;
            this.i = 0;
            this.j = false;
            this.b = new Scroller(context, QRComicScrollReaderListView.ao);
        }

        public int a() {
            return this.f;
        }

        public boolean b() {
            return !this.j;
        }

        @TargetApi(11)
        public void a(float f) {
            if (VERSION.SDK_INT >= 11) {
                this.b.setFriction(f);
            }
        }

        void c() {
            this.a.removeCallbacks(this);
            z.a(this.a, this);
        }

        public void a(int i, int i2) {
            int max = Math.max(-this.a.z, Math.min(i, this.a.z));
            int max2 = Math.max(-this.a.z, Math.min(i2, this.a.z));
            this.c = 0;
            this.d = 0;
            this.e = System.currentTimeMillis();
            this.j = true;
            this.b.fling(0, 0, max, max2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            c();
            if (this.a.ak != null) {
                this.a.ak.f();
            }
        }

        public void a(int i) {
            if (!this.j) {
                this.c = 0;
                this.d = 0;
                this.e = System.currentTimeMillis();
                this.j = true;
                this.i = Math.round(((float) Math.abs(i)) / this.a.r[4]);
                this.h = -this.i;
                this.g = 0;
                this.b.startScroll(0, 0, 0, i, 250);
                c();
                if (this.a.ak != null) {
                    this.a.ak.f();
                }
                if (this.a.ah != null && this.a.ah.a != null) {
                    this.a.ah.a.a();
                }
            } else if (com.qrcomic.util.g.a()) {
                com.qrcomic.util.g.b("FlingTracker", com.qrcomic.util.g.d, "ScrollListView is scrolling, hold on...");
            }
        }

        public void run() {
            int i = 0;
            boolean computeScrollOffset = this.b.computeScrollOffset();
            int currY = this.b.getCurrY();
            int i2 = currY - this.d;
            if (i2 > 0) {
                i2 = Math.min(this.a.getHeight() - 1, i2);
            } else {
                i2 = Math.max(-(this.a.getHeight() - 1), i2);
            }
            int currX = this.b.getCurrX();
            int i3 = this.c - currX;
            if (this.c == Integer.MAX_VALUE) {
                i3 = 0;
            }
            this.a.m.postTranslate((float) i3, 0.0f);
            this.a.b(this.a.m, this.a.r);
            if (computeScrollOffset) {
                float f = ((float) i2) / this.a.r[4];
                if (f >= 0.0f) {
                    i3 = Math.round(f);
                } else {
                    i3 = -Math.round(-f);
                }
                if ((i3 >= 0 || this.g > this.h || this.h == 0) && (i3 <= 0 || this.g < this.i || this.i == 0)) {
                    i = i3;
                }
                this.g += i;
                QRComicScrollReaderListView.a(this.a, i);
                this.a.b(f);
                this.a.c(f);
                this.d = currY;
                this.c = currX;
                if (i2 != 0) {
                    this.a.l();
                }
                c();
                b(i2);
                if (this.a.ak != null) {
                    this.a.ak.g();
                    return;
                }
                return;
            }
            this.h = 0;
            this.i = 0;
            this.g = 0;
            d();
        }

        private void b(int i) {
            long currentTimeMillis = System.currentTimeMillis();
            long currentTimeMillis2 = System.currentTimeMillis() - this.e;
            if (currentTimeMillis2 == 0) {
                this.f = 0;
            } else {
                this.f = (int) ((((double) i) * 1.0d) / ((double) currentTimeMillis2));
            }
            this.e = currentTimeMillis;
        }

        public void d() {
            this.j = false;
            this.a.removeCallbacks(this);
            this.b.abortAnimation();
            this.f = 0;
            if (this.a.ak != null) {
                this.a.ak.h();
            }
        }
    }

    public interface b {
        void f();

        void g();

        void h();
    }

    public interface d {
        void a(int i, int i2, int i3, int i4);

        void a(Matrix matrix);

        void a(View view, int i, int i2, int i3, int i4);

        void b(int i);

        void b(View view);

        void i();
    }

    public interface e {
        void a(int i);
    }

    public interface f {
        void a();
    }

    class g extends DataSetObserver {
        final /* synthetic */ QRComicScrollReaderListView a;

        g(QRComicScrollReaderListView qRComicScrollReaderListView) {
            this.a = qRComicScrollReaderListView;
        }

        public void onChanged() {
            this.a.R = true;
            this.a.G = false;
            this.a.e();
            this.a.invalidate();
            this.a.requestLayout();
        }

        public void onInvalidated() {
            this.a.G = false;
            this.a.f();
            this.a.g();
            this.a.invalidate();
            this.a.requestLayout();
        }
    }

    static /* synthetic */ int a(QRComicScrollReaderListView qRComicScrollReaderListView, int i) {
        int i2 = qRComicScrollReaderListView.L + i;
        qRComicScrollReaderListView.L = i2;
        return i2;
    }

    public QRComicScrollReaderListView(Context context) {
        this(context, null);
    }

    public QRComicScrollReaderListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = 200;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = new PointF();
        this.l = new PointF();
        this.r = new float[9];
        this.s = new float[9];
        this.t = new float[9];
        this.u = -1;
        this.x = 1.0f;
        this.C = null;
        this.D = 0;
        this.G = false;
        this.H = null;
        this.I = 4097;
        this.M = Integer.MAX_VALUE;
        this.N = null;
        this.O = 0;
        this.R = false;
        this.S = false;
        this.T = null;
        this.W = true;
        this.c = true;
        this.ab = false;
        this.ac = false;
        this.ad = false;
        this.ae = true;
        this.am = new ArrayList();
        this.an = 0.0f;
        this.e = new OnDoubleTapListener(this) {
            final /* synthetic */ QRComicScrollReaderListView a;

            {
                this.a = r1;
            }

            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                int b = this.a.ah.b();
                int i = (int) (this.a.ah.k() ? ((float) b) * 0.21f : ((float) b) * 0.224f);
                if (this.a.ah.j() || motionEvent.getY() >= ((float) i)) {
                    if (this.a.ah.j() || ((float) b) - motionEvent.getY() >= ((float) i)) {
                        if (this.a.al != null && this.a.ae) {
                            this.a.al.a();
                        }
                        this.a.ae = true;
                    } else if (!this.a.aq) {
                        this.a.a(true);
                        this.a.ah.a("2");
                    }
                } else if (!this.a.aq) {
                    this.a.a(false);
                    this.a.ah.a("1");
                }
                this.a.ap = false;
                return false;
            }

            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (this.a.x <= 1.0f) {
                    this.a.p.setScale(2.0f, 2.0f, motionEvent.getX(), motionEvent.getY());
                } else {
                    this.a.p.reset();
                }
                this.a.u = System.currentTimeMillis();
                this.a.invalidate();
                this.a.ap = false;
                return false;
            }

            public boolean onDoubleTapEvent(MotionEvent motionEvent) {
                return false;
            }
        };
        this.ap = false;
        this.aq = false;
        this.f = new SimpleOnGestureListener(this) {
            final /* synthetic */ QRComicScrollReaderListView a;

            {
                this.a = r1;
            }

            public void onLongPress(MotionEvent motionEvent) {
                if (this.a.al != null) {
                    this.a.al.b(motionEvent);
                }
            }

            public boolean onSingleTapUp(MotionEvent motionEvent) {
                if (this.a.ae) {
                    this.a.ap = true;
                }
                return false;
            }

            public boolean onDown(MotionEvent motionEvent) {
                QRComicScrollReaderListView qRComicScrollReaderListView = this.a;
                boolean z = (this.a.a == null || this.a.a.b()) ? false : true;
                qRComicScrollReaderListView.aq = z;
                return super.onDown(motionEvent);
            }
        };
        setFocusableInTouchMode(true);
        setWillNotDraw(false);
        setAlwaysDrawnWithCacheEnabled(false);
        d();
    }

    private void d() {
        this.a = new a(getContext());
        this.a.a(0.02f);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.v = viewConfiguration.getScaledTouchSlop();
        this.z = viewConfiguration.getScaledMaximumFlingVelocity();
        this.y = viewConfiguration.getScaledMinimumFlingVelocity();
        this.A = -1;
        this.B = -1;
        this.K = 0;
        this.L = 0;
        this.P = 0;
        this.M = Integer.MAX_VALUE;
        setBackgroundColor(WebView.NIGHT_MODE_COLOR);
        this.m = new Matrix();
        this.n = new Matrix();
        this.p = new Matrix();
        this.o = new Matrix();
        this.q = new PointF();
        this.F = new GestureDetector(null, this.f);
        this.F.setOnDoubleTapListener(this.e);
        setFocusable(true);
        setCurrentScrollState(4097);
        if (this.Q == null) {
            this.Q = new g(this);
        }
    }

    public void a(c cVar) {
        this.ah = cVar;
    }

    public void setDivider(Drawable drawable) {
        this.N = drawable;
        if (drawable != null) {
            setDividerHeight(drawable.getIntrinsicHeight());
        } else {
            setDividerHeight(0);
        }
    }

    public void setDividerHeight(int i) {
        this.O = i;
        requestLayout();
        invalidate();
    }

    private void e() {
        if (this.J != null) {
            int i = this.b;
            View c = c(this.b);
            if (c != null) {
                ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) this.J.getItem(this.b);
                ComicSectionPicInfo comicSectionPicInfo2 = ((com.qrcomic.widget.reader.c.c) c.getTag()).d;
                int i2 = this.b - this.A;
                int i3 = this.b - this.B;
                if (comicSectionPicInfo != comicSectionPicInfo2) {
                    int i4;
                    int count = this.J.getCount();
                    for (i4 = 0; i4 < count; i4++) {
                        if (((ComicSectionPicInfo) this.J.getItem(i4)) == comicSectionPicInfo2) {
                            this.b = i4;
                            break;
                        }
                    }
                    i = this.b - i;
                    for (i4 = 0; i4 < i; i4++) {
                        this.K = (((ComicSectionPicInfo) this.J.getItem(i4)).dstHeight + this.O) + this.K;
                    }
                    this.L = this.K;
                    this.A = this.b - i2;
                    this.B = this.b - i3;
                    b(i);
                }
            }
        }
    }

    private void b(int i) {
        this.d += i;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getTag() != null) {
                com.qrcomic.widget.reader.c.c cVar = (com.qrcomic.widget.reader.c.c) childAt.getTag();
                cVar.c += i;
            }
        }
    }

    private void f() {
        if (this.T != null) {
            this.T.setPressed(false);
            this.T = null;
            refreshDrawableState();
        }
        setPressed(false);
    }

    private void g() {
        d();
        removeAllViewsInLayout();
        requestLayout();
    }

    public void setOnScrollStateChangedListener(e eVar) {
        this.H = eVar;
    }

    public void setOnComicPageChangeListener(c cVar) {
        this.ai = cVar;
    }

    public void setOnComicFlingListener(b bVar) {
        this.ak = bVar;
    }

    public void setOnComicTouchListener(a aVar) {
        this.al = aVar;
    }

    public void setOnLayoutListener(d dVar) {
        this.aj = dVar;
    }

    private void setCurrentScrollState(int i) {
        if (!(this.I == i || this.H == null)) {
            this.H.a(i);
        }
        this.I = i;
    }

    public void setTouchEventEnabled(boolean z) {
        this.ac = !z;
    }

    public void setRunningOutOfDataListener(f fVar, int i) {
        this.C = fVar;
        this.D = i;
    }

    private void h() {
        if (this.C != null && this.J != null && this.J.getCount() - (this.B + 1) < this.D && !this.G) {
            this.G = true;
            this.C.a();
        }
    }

    private View c(int i) {
        if (i < this.A || i > this.B) {
            return null;
        }
        return getChildAt(i - this.A);
    }

    private void d(int i) {
        this.am.clear();
        for (int i2 = 0; i2 < i; i2++) {
            this.am.add(new LinkedList());
        }
    }

    private View e(int i) {
        int itemViewType = this.J.getItemViewType(i);
        if (f(itemViewType)) {
            return (View) ((Queue) this.am.get(itemViewType)).poll();
        }
        return null;
    }

    private void a(int i, View view) {
        int itemViewType = this.J.getItemViewType(i);
        if (f(itemViewType)) {
            ((Queue) this.am.get(itemViewType)).offer(view);
        }
    }

    private boolean f(int i) {
        return i < this.am.size();
    }

    public BaseAdapter getAdapter() {
        return this.J;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.J != null) {
            this.J.unregisterDataSetObserver(this.Q);
        }
        if (listAdapter != null) {
            this.G = false;
            this.J = (BaseAdapter) listAdapter;
            this.J.registerDataSetObserver(this.Q);
            d(this.J.getViewTypeCount());
        } else {
            this.J = null;
        }
        g();
    }

    public View getSelectedView() {
        return c(this.b);
    }

    public void setSelection(int i) {
        setSelectionFromTop(i, 0);
    }

    public void setSelectionFromTop(int i, int i2) {
        int i3 = 0;
        if (this.J != null) {
            int count = this.J.getCount();
            if (i >= 0 && i < count) {
                int i4;
                ComicSectionPicInfo comicSectionPicInfo;
                int i5 = 0;
                for (i4 = 0; i4 < this.b; i4++) {
                    comicSectionPicInfo = (ComicSectionPicInfo) this.J.getItem(i4);
                    if (comicSectionPicInfo.dstHeight <= 0) {
                        comicSectionPicInfo.dstHeight = a(i4);
                    }
                    i5 += comicSectionPicInfo.dstHeight + this.O;
                }
                if (i5 >= 0) {
                    this.b = i;
                    comicSectionPicInfo = (ComicSectionPicInfo) this.J.getItem(i);
                    i4 = comicSectionPicInfo.dstHeight;
                    if (i4 <= 0) {
                        i4 = a(i);
                        comicSectionPicInfo.dstHeight = i4;
                    }
                    int i6 = i4;
                    i4 = this.ah.b();
                    if (this.b != count - 1 || i5 + i6 <= i4 || i6 >= i4) {
                        this.K = i5;
                        this.L = i5 - i2;
                        if (this.b > 0) {
                            this.B = i - 2;
                            this.K -= ((ComicSectionPicInfo) this.J.getItem(this.b - 1)).dstHeight + this.O;
                        } else {
                            this.B = i - 1;
                        }
                    } else {
                        i6 = i4 - i6;
                        this.L = i5 - i6;
                        i4 = i6;
                        i6 = i;
                        while (i4 > 0 && i6 > 0) {
                            i = i6 - 1;
                            i4 -= ((ComicSectionPicInfo) this.J.getItem(i)).dstHeight + this.O;
                            i6 = i;
                        }
                        if (i6 > 0) {
                            i5 = this.L + i4;
                            i4 = i6 - 1;
                            if (i4 >= 0) {
                                i3 = this.O + ((ComicSectionPicInfo) this.J.getItem(i4)).dstHeight;
                            }
                            this.K = i5 - i3;
                            i6 = i4;
                        } else {
                            this.K = i4 + this.L;
                        }
                        this.B = i6 - 1;
                    }
                    this.S = true;
                    requestLayout();
                    if (!this.a.b()) {
                        this.a.d();
                    }
                }
            }
        }
    }

    public void requestLayout() {
        if (!this.ad) {
            super.requestLayout();
        }
    }

    private boolean g(int i) {
        return i == this.J.getCount() + -1;
    }

    private void h(int i) {
        View topmostChild = getTopmostChild();
        while (topmostChild != null && (topmostChild.getBottom() + getSubTopmostChildHeight()) + i <= 0) {
            int measuredHeight;
            int i2 = this.P;
            if (g(this.A)) {
                measuredHeight = topmostChild.getMeasuredHeight();
            } else {
                measuredHeight = this.O + topmostChild.getMeasuredHeight();
            }
            this.P = measuredHeight + i2;
            if (this.aj != null) {
                this.aj.b(topmostChild);
            }
            a(this.A, topmostChild);
            removeViewInLayout(topmostChild);
            this.A++;
            topmostChild = getTopmostChild();
        }
        View bottommostChild = getBottommostChild();
        while (bottommostChild != null && (bottommostChild.getTop() + i) - getSubBottommostChildHeight() >= getHeight()) {
            if (this.aj != null) {
                this.aj.b(bottommostChild);
            }
            a(this.B, bottommostChild);
            removeViewInLayout(bottommostChild);
            this.B--;
            bottommostChild = getBottommostChild();
        }
    }

    private void i(int i) {
        int top;
        int bottom;
        int i2 = 0;
        View bottommostChild = getBottommostChild();
        if (bottommostChild != null) {
            top = bottommostChild.getTop();
        } else {
            top = 0;
        }
        a(top, bottommostChild != null ? bottommostChild.getMeasuredHeight() : 0, i);
        View topmostChild = getTopmostChild();
        if (topmostChild != null) {
            bottom = topmostChild.getBottom();
        } else {
            bottom = 0;
        }
        if (topmostChild != null) {
            i2 = topmostChild.getMeasuredHeight();
        }
        b(bottom, i2, i);
    }

    private void a(int i, int i2, int i3) {
        while (i + i3 < getHeight() && this.B + 1 < this.J.getCount()) {
            this.B++;
            View view = this.J.getView(this.B, e(this.B), this);
            a(view, -1);
            if (this.A < 0) {
                this.A = this.B;
                i = 0;
            } else {
                i += (this.B == 0 ? 0 : this.O) + i2;
            }
            i2 = view.getMeasuredHeight();
            h();
        }
    }

    private void b(int i, int i2, int i3) {
        int i4 = i2;
        while (i + i3 > 0 && this.A >= 1) {
            this.A--;
            View view = this.J.getView(this.A, e(this.A), this);
            a(view, 0);
            if (this.A != 0) {
                i4 += this.O;
            }
            i -= i4;
            i4 = view.getMeasuredHeight();
            this.P -= view.getMeasuredHeight() + this.O;
        }
    }

    private void a(View view, int i) {
        addViewInLayout(view, i, b(view), true);
        a(view);
    }

    private void a(View view) {
        int makeMeasureSpec;
        LayoutParams b = b(view);
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(this.U, getPaddingLeft() + getPaddingRight(), b.width);
        if (b.height > 0) {
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(b.height, 1073741824);
        } else {
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, makeMeasureSpec);
    }

    private LayoutParams b(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            return new LayoutParams(-1, -2);
        }
        return layoutParams;
    }

    private void j(int i) {
        this.c = true;
        int childCount = getChildCount();
        if (childCount > 0) {
            this.P += i;
            int i2 = 0;
            int i3 = this.P;
            while (i2 < childCount) {
                Object obj;
                View childAt = getChildAt(i2);
                c(childAt);
                int paddingLeft = getPaddingLeft();
                int paddingTop = i3 + getPaddingTop();
                int measuredWidth = paddingLeft + childAt.getMeasuredWidth();
                int measuredHeight = paddingTop + childAt.getMeasuredHeight();
                com.qrcomic.widget.reader.c.c cVar = (com.qrcomic.widget.reader.c.c) childAt.getTag();
                if (cVar.e || childAt.isLayoutRequested()) {
                    obj = 1;
                } else {
                    obj = null;
                }
                int i4 = cVar.c;
                if (this.aj != null) {
                    this.aj.a(childAt, paddingLeft, paddingTop, measuredWidth, measuredHeight);
                }
                this.ah.a(((com.qrcomic.widget.reader.c.c) childAt.getTag()).d, paddingTop, measuredHeight, i4, i);
                if (paddingTop < this.aa && measuredHeight >= this.aa) {
                    double d = this.ah.c() == 1 ? 0.66d : 0.2d;
                    if (((double) (this.aa - paddingTop)) < d * ((double) (((double) childAt.getMeasuredHeight()) * d <= ((double) this.aa) ? childAt.getMeasuredHeight() : this.aa))) {
                        if (i4 > 0) {
                            i4--;
                        } else {
                            i4 = 0;
                        }
                    }
                    if (i4 != this.b) {
                        if (this.ai != null) {
                            ComicSectionPicInfo a = this.ah.a(i4);
                            if (a != null) {
                                this.ai.a(a);
                            }
                        }
                        this.b = i4;
                    }
                }
                if (obj != null) {
                    childAt.layout(paddingLeft, paddingTop, measuredWidth, measuredHeight);
                }
                i2++;
                i3 += childAt.getMeasuredHeight() + this.O;
            }
            if (this.aj != null) {
                this.aj.i();
            }
        }
    }

    private void c(View view) {
        if (view.isLayoutRequested()) {
            LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LayoutParams(-1, -2);
            }
            int childMeasureSpec = ViewGroup.getChildMeasureSpec(this.U, 0, layoutParams.width);
            int i = layoutParams.height;
            if (i > 0) {
                i = MeasureSpec.makeMeasureSpec(i, 1073741824);
            } else {
                i = MeasureSpec.makeMeasureSpec(0, 0);
            }
            try {
                view.measure(childMeasureSpec, i);
            } catch (StringIndexOutOfBoundsException e) {
            } catch (Exception e2) {
            }
        }
    }

    private boolean i() {
        if (!g(this.B)) {
            return false;
        }
        View bottommostChild = getBottommostChild();
        if (bottommostChild == null) {
            return false;
        }
        int i = this.M;
        int bottom = (bottommostChild.getBottom() - getPaddingTop()) - this.ah.b();
        if (bottom < 0) {
            bottom = 0;
        }
        this.M = bottom + this.K;
        if (this.M < 0) {
            this.M = 0;
        }
        if (this.M != i) {
            return true;
        }
        return false;
    }

    private void j() {
        if (!this.ad) {
            this.ad = true;
        }
    }

    private void k() {
        if (this.ad) {
            this.ad = false;
        }
    }

    private void k(int i) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).offsetTopAndBottom(i);
        }
    }

    private void l() {
        if (this.J != null && getChildCount() != 0) {
            j();
            i();
            if (this.L < 0) {
                this.L = 0;
            } else if (this.L >= this.M) {
                this.L = this.M;
            }
            int i = this.K - this.L;
            k(i);
            h(0);
            i(0);
            j(i);
            if (this.aj != null) {
                this.aj.b(i);
            }
            this.K = this.L;
            k();
            if (i()) {
                requestLayout();
            }
            invalidate();
        }
    }

    public int getCurrentVelocity() {
        return this.a.a();
    }

    private int getRenderHeight() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    private View getTopmostChild() {
        return getChildAt(0);
    }

    private View getBottommostChild() {
        return getChildAt(getChildCount() - 1);
    }

    private int getSubTopmostChildHeight() {
        if (getChildCount() > 1) {
            return getChildAt(1).getMeasuredHeight();
        }
        return 0;
    }

    private int getSubBottommostChildHeight() {
        if (getChildCount() > 1) {
            return getChildAt(getChildCount() - 2).getMeasuredHeight();
        }
        return 0;
    }

    private void m() {
        if (this.E == null) {
            this.E = VelocityTracker.obtain();
        }
    }

    private float a(MotionEvent motionEvent) {
        try {
            float x = motionEvent.getX(0) - motionEvent.getX(1);
            float y = motionEvent.getY(0) - motionEvent.getY(1);
            return (float) Math.sqrt((double) ((x * x) + (y * y)));
        } catch (Exception e) {
            return 0.0f;
        }
    }

    private PointF b(MotionEvent motionEvent) {
        PointF pointF = new PointF();
        pointF.set((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f, (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f);
        return pointF;
    }

    private void a(Matrix matrix, float[] fArr) {
        if (getTopmostChild() != null) {
            matrix.getValues(fArr);
            int top = getTopmostChild().getTop();
            int bottom = getBottommostChild().getBottom();
            RectF rectF = new RectF(0.0f, (float) top, (float) getWidth(), (float) bottom);
            matrix.mapRect(rectF);
            if (((float) top) + (fArr[5] / fArr[4]) >= 0.0f) {
                fArr[5] = ((float) (-top)) * fArr[4];
            }
            if (bottom >= getHeight() - getPaddingBottom()) {
                top = (bottom - getPaddingTop()) - getRenderHeight();
                if (((float) bottom) - rectF.bottom >= ((float) top)) {
                    fArr[5] = ((((float) bottom) - rectF.bottom) - ((float) top)) + fArr[5];
                }
            }
            matrix.setValues(fArr);
            float width = rectF.width() < ((float) getWidth()) ? ((((float) getWidth()) - rectF.width()) / 2.0f) - rectF.left : rectF.left > 0.0f ? -rectF.left : rectF.right < ((float) getWidth()) ? ((float) getWidth()) - rectF.right : 0.0f;
            matrix.postTranslate(width, 0.0f);
        }
    }

    private float a(float f) {
        this.m.getValues(this.r);
        float f2 = this.r[0] * f;
        if (f2 < 0.5f) {
            return 0.5f / this.r[0];
        }
        if (f2 > 2.0f) {
            return 2.0f / this.r[0];
        }
        return f;
    }

    private void b(Matrix matrix, float[] fArr) {
        matrix.getValues(fArr);
        if (fArr[2] > 0.0f) {
            fArr[2] = 0.0f;
        }
        if (fArr[2] < ((float) (-getWidth())) * (fArr[0] - 1.0f)) {
            fArr[2] = ((float) (-getWidth())) * (fArr[0] - 1.0f);
        }
        matrix.setValues(fArr);
    }

    private void a(Canvas canvas) {
        float f = 0.0f;
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.u < 200) {
            this.m.getValues(this.r);
            this.p.getValues(this.s);
            float f2 = ((float) (currentTimeMillis - this.u)) / 200.0f;
            if (f2 >= 0.0f) {
                if (f2 > 1.0f) {
                    f = 1.0f;
                } else {
                    f = f2;
                }
            }
            for (int i = 0; i < 9; i++) {
                this.t[i] = this.r[i] + ((this.s[i] - this.r[i]) * f);
            }
            this.o.setValues(this.t);
            canvas.setMatrix(this.o);
            if (this.aj != null) {
                this.aj.a(this.o);
            }
            invalidate();
            return;
        }
        this.u = -1;
        if (this.aj != null) {
            this.aj.a(this.p);
        }
        canvas.setMatrix(this.p);
        this.m.set(this.p);
        this.m.getValues(this.r);
        this.x = this.r[4];
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(canvas, view, j);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.an = motionEvent.getY();
            this.ae = true;
            if (this.al != null && this.al.a(motionEvent)) {
                return true;
            }
            this.k.set(motionEvent.getX(), motionEvent.getY());
            this.l.set(motionEvent.getX(), motionEvent.getY());
            this.h = 1;
            this.n.set(this.m);
            this.ab = false;
            if (!(this.a == null || this.a.b())) {
                ViewParent parent = getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                this.ae = false;
                this.a.d();
            }
            setCurrentScrollState(4097);
        } else if (action == 2 && Math.abs(motionEvent.getY() - this.an) > ((float) this.v)) {
            return true;
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.ac) {
            return false;
        }
        m();
        this.E.addMovement(motionEvent);
        this.F.onTouchEvent(motionEvent);
        int xVelocity;
        int yVelocity;
        switch (motionEvent.getAction() & 255) {
            case 1:
                if (this.h == 2) {
                    if (this.x < 1.0f) {
                        this.u = System.currentTimeMillis();
                        this.p.reset();
                    } else {
                        b(this.m, this.r);
                    }
                    invalidate();
                } else {
                    this.h = 0;
                    this.E.computeCurrentVelocity(1000, (float) this.z);
                    xVelocity = (int) this.E.getXVelocity();
                    yVelocity = (int) this.E.getYVelocity();
                    if (Math.abs(yVelocity) > this.y) {
                        setCurrentScrollState(4099);
                        this.a.a(-xVelocity, -yVelocity);
                    } else {
                        setCurrentScrollState(4097);
                    }
                }
                if (this.E != null) {
                    this.E.recycle();
                    this.E = null;
                }
                if (this.al != null) {
                    this.al.a(this.l.y, motionEvent.getY());
                    break;
                }
                break;
            case 2:
                xVelocity = (int) (this.k.y - motionEvent.getY());
                yVelocity = (int) (this.k.x - motionEvent.getX());
                if (this.al != null) {
                    this.al.a(yVelocity, xVelocity);
                }
                if (this.h != 1) {
                    if (this.h == 2) {
                        float a = a(motionEvent);
                        if (a > 10.0f) {
                            this.m.set(this.n);
                            a = a(a / this.w);
                            this.m.preScale(a, a, (float) (getWidth() / 2), this.q.y);
                            a(this.m, this.r);
                            invalidate();
                            break;
                        }
                    }
                }
                if (Math.abs(motionEvent.getY() - this.l.y) > ((float) this.v)) {
                    float f = ((float) xVelocity) / this.r[4];
                    if (xVelocity != 0) {
                        setCurrentScrollState(4098);
                        f();
                        this.L = (int) (((float) this.L) + f);
                    }
                    b(f);
                    c(f);
                    l();
                }
                this.m.postTranslate((float) (-yVelocity), 0.0f);
                b(this.m, this.r);
                invalidate();
                this.k.set(motionEvent.getX(), motionEvent.getY());
                break;
                break;
            case 3:
            case 6:
                if (this.h == 2 && this.x < 1.0f) {
                    this.u = System.currentTimeMillis();
                    this.p.reset();
                    invalidate();
                }
                this.h = 0;
                if (this.E != null) {
                    this.E.recycle();
                    this.E = null;
                    break;
                }
                break;
            case 5:
                this.E.addMovement(motionEvent);
                this.w = a(motionEvent);
                if (this.w > 10.0f) {
                    this.n.set(this.m);
                    this.q = b(motionEvent);
                    this.h = 2;
                }
                if (this.al != null) {
                    this.al.c(motionEvent);
                    break;
                }
                break;
        }
        return true;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.u > 0) {
            a(canvas);
            return;
        }
        this.m.getValues(this.r);
        canvas.setMatrix(this.m);
        if (this.aj != null) {
            this.aj.a(this.m);
        }
        this.x = this.r[4];
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        j();
        if (this.W) {
            this.aa = getHeight();
            this.W = false;
        }
        if (this.J != null) {
            invalidate();
            if (this.R) {
                this.M = Integer.MAX_VALUE;
                this.R = false;
            }
            if (this.S) {
                this.A = -1;
                this.P = 0;
                removeAllViewsInLayout();
                this.S = false;
            }
            if (this.V != null) {
                this.L = this.V.intValue();
                this.V = null;
            }
            if (this.L < 0) {
                this.L = 0;
                setCurrentScrollState(4097);
            } else if (this.L >= this.M) {
                this.L = this.M;
                setCurrentScrollState(4097);
            }
            int i5 = this.K - this.L;
            h(i5);
            i(i5);
            j(i5);
            if (this.aj != null) {
                this.aj.b(i5);
            }
            this.K = this.L;
            k();
            if (i()) {
                onLayout(z, i, i2, i3, i4);
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(getDefaultSize(-1, i), getDefaultSize(-1, i2));
        this.U = i;
    }

    public void removeAllViewsInLayout() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (this.aj != null) {
                this.aj.b(getChildAt(i));
            }
        }
        super.removeAllViewsInLayout();
    }

    private int getCanvasHeight() {
        int i = 0;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (i2 < childCount - 1) {
                i += childAt.getMeasuredHeight() + this.O;
            } else {
                i += childAt.getMeasuredHeight();
            }
        }
        return i;
    }

    public boolean a() {
        return this.af;
    }

    public boolean b() {
        return this.ag;
    }

    private void n() {
        if (this.i != 1) {
            this.i = 1;
            if (this.ai != null) {
                this.ai.a();
            }
            this.a.d();
        }
    }

    private void o() {
        if (this.j != 1) {
            this.j = 1;
            if (this.ai != null) {
                this.ai.b();
            }
        }
    }

    private void b(float f) {
        if (this.L < 0) {
            if (this.x > 1.0f) {
                this.m.preTranslate(0.0f, -f);
                this.m.getValues(this.r);
                if (this.r[5] > 0.0f) {
                    this.r[5] = 0.0f;
                    this.m.setValues(this.r);
                    n();
                    this.af = true;
                    return;
                }
                this.i = 0;
                this.af = false;
                return;
            }
            n();
            this.a.d();
            this.af = true;
        } else if (this.L != 0) {
            this.i = 0;
            this.af = false;
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (this.aj != null) {
            this.aj.a(i, i2, i3, i4);
        }
    }

    private void c(float f) {
        if (this.L > this.M) {
            if (this.x > 1.0f) {
                this.m.preTranslate(0.0f, -f);
                this.m.getValues(this.r);
                int canvasHeight = (int) (((float) getCanvasHeight()) * this.r[4]);
                if (getHeight() - ((int) ((((float) this.P) * this.r[4]) + this.r[5])) > canvasHeight) {
                    this.r[5] = ((float) (getHeight() - canvasHeight)) - (((float) this.P) * this.r[4]);
                    this.m.setValues(this.r);
                    o();
                    this.ag = true;
                    return;
                }
                this.j = 0;
                this.ag = false;
                return;
            }
            o();
            this.ag = true;
        } else if (this.L != this.M) {
            this.j = 0;
            this.ag = false;
        }
    }

    public int a(int i) {
        View c = c(i);
        if (c == null) {
            return 0;
        }
        return Math.round(((float) c.getHeight()) * this.r[4]);
    }

    public int getCurrentYOffset() {
        View selectedView = getSelectedView();
        if (selectedView == null) {
            return 0;
        }
        return Math.round((((float) selectedView.getTop()) * this.r[4]) + this.r[5]);
    }

    public void a(boolean z) {
        if (this.a != null) {
            this.a.a(b(z));
        }
    }

    private int b(boolean z) {
        int i = 1;
        int b = this.ah.b();
        View selectedView = getSelectedView();
        if (selectedView == null) {
            return 0;
        }
        int round = Math.round(((float) selectedView.getHeight()) * this.r[4]);
        int round2 = Math.round((((float) selectedView.getTop()) * this.r[4]) + this.r[5]);
        int round3 = Math.round(((float) this.O) * this.r[4]);
        if (z) {
            if (this.b + 1 < this.J.getCount()) {
                i = 0;
            }
            if (round2 <= 0) {
                round2 += round;
                if (round2 >= b * 2) {
                    round2 = b;
                } else if (round2 > b) {
                    round2 -= b;
                } else if (i == 0) {
                    round2 += round3;
                }
            } else if (round2 + round <= b) {
                round2 = (round2 + round) + round3;
            }
            if (round2 <= this.v) {
                round2 += round;
            }
        } else {
            if (this.b > 0) {
                i = 0;
            }
            if (round2 > 0) {
                round2 = (b - round2) + round3;
            } else if (round2 == 0) {
                round2 = i != 0 ? 0 : b + round3;
            } else {
                round2 = -round2;
                if (round2 > b) {
                    round2 = b;
                }
            }
            if (round2 <= this.v) {
                round2 += b;
            }
        }
        if (!z) {
            round2 = -round2;
        }
        return round2;
    }
}
