package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.v4.view.a.l;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewPager extends ViewGroup {
    private static final int[] a = new int[]{16842931};
    private static final h ai = new h();
    private static final Comparator<b> c = new Comparator<b>() {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((b) obj, (b) obj2);
        }

        public int a(b bVar, b bVar2) {
            return bVar.b - bVar2.b;
        }
    };
    private static final Interpolator d = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
        }
    };
    private boolean A;
    private boolean B;
    private int C;
    private int D;
    private int E;
    private float F;
    private float G;
    private float H;
    private float I;
    private int J = -1;
    private VelocityTracker K;
    private int L;
    private int M;
    private int N;
    private int O;
    private boolean P;
    private long Q;
    private android.support.v4.widget.d R;
    private android.support.v4.widget.d S;
    private boolean T = true;
    private boolean U = false;
    private boolean V;
    private int W;
    private List<e> aa;
    private e ab;
    private e ac;
    private d ad;
    private f ae;
    private Method af;
    private int ag;
    private ArrayList<View> ah;
    private final Runnable aj = new Runnable(this) {
        final /* synthetic */ ViewPager a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.setScrollState(0);
            this.a.c();
        }
    };
    private int ak = 0;
    private int b;
    private final ArrayList<b> e = new ArrayList();
    private final b f = new b();
    private final Rect g = new Rect();
    private PagerAdapter h;
    private int i;
    private int j = -1;
    private Parcelable k = null;
    private ClassLoader l = null;
    private Scroller m;
    private g n;
    private int o;
    private Drawable p;
    private int q;
    private int r;
    private float s = -3.4028235E38f;
    private float t = Float.MAX_VALUE;
    private int u;
    private int v;
    private boolean w;
    private boolean x;
    private boolean y;
    private int z = 1;

    interface a {
    }

    interface d {
        void a(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2);
    }

    public interface e {
        void onPageScrollStateChanged(int i);

        void onPageScrolled(int i, float f, int i2);

        void onPageSelected(int i);
    }

    public static class LayoutParams extends android.view.ViewGroup.LayoutParams {
        public boolean a;
        public int b;
        float c = 0.0f;
        boolean d;
        int e;
        int f;

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.a);
            this.b = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }
    }

    public static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = android.support.v4.os.d.a(new android.support.v4.os.e<SavedState>() {
            public /* synthetic */ Object a(Parcel parcel, ClassLoader classLoader) {
                return b(parcel, classLoader);
            }

            public /* synthetic */ Object[] a(int i) {
                return b(i);
            }

            public SavedState b(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState[] b(int i) {
                return new SavedState[i];
            }
        });
        int a;
        Parcelable b;
        ClassLoader c;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeParcelable(this.b, i);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.a + "}";
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel);
            if (classLoader == null) {
                classLoader = getClass().getClassLoader();
            }
            this.a = parcel.readInt();
            this.b = parcel.readParcelable(classLoader);
            this.c = classLoader;
        }
    }

    static class b {
        Object a;
        int b;
        boolean c;
        float d;
        float e;

        b() {
        }
    }

    class c extends a {
        final /* synthetic */ ViewPager b;

        c(ViewPager viewPager) {
            this.b = viewPager;
        }

        public void d(View view, AccessibilityEvent accessibilityEvent) {
            super.d(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            l a = l.a();
            a.a(b());
            if (accessibilityEvent.getEventType() == 4096 && this.b.h != null) {
                a.a(this.b.h.a());
                a.b(this.b.i);
                a.c(this.b.i);
            }
        }

        public void a(View view, android.support.v4.view.a.c cVar) {
            super.a(view, cVar);
            cVar.b(ViewPager.class.getName());
            cVar.i(b());
            if (this.b.canScrollHorizontally(1)) {
                cVar.a(4096);
            }
            if (this.b.canScrollHorizontally(-1)) {
                cVar.a(8192);
            }
        }

        public boolean a(View view, int i, Bundle bundle) {
            if (super.a(view, i, bundle)) {
                return true;
            }
            switch (i) {
                case 4096:
                    if (!this.b.canScrollHorizontally(1)) {
                        return false;
                    }
                    this.b.setCurrentItem(this.b.i + 1);
                    return true;
                case 8192:
                    if (!this.b.canScrollHorizontally(-1)) {
                        return false;
                    }
                    this.b.setCurrentItem(this.b.i - 1);
                    return true;
                default:
                    return false;
            }
        }

        private boolean b() {
            return this.b.h != null && this.b.h.a() > 1;
        }
    }

    public interface f {
        void a(View view, float f);
    }

    private class g extends DataSetObserver {
        final /* synthetic */ ViewPager a;

        private g(ViewPager viewPager) {
            this.a = viewPager;
        }

        public void onChanged() {
            this.a.b();
        }

        public void onInvalidated() {
            this.a.b();
        }
    }

    static class h implements Comparator<View> {
        h() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((View) obj, (View) obj2);
        }

        public int a(View view, View view2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
            if (layoutParams.a != layoutParams2.a) {
                return layoutParams.a ? 1 : -1;
            } else {
                return layoutParams.e - layoutParams2.e;
            }
        }
    }

    public ViewPager(Context context) {
        super(context);
        a();
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    void a() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.m = new Scroller(context, d);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.E = ai.a(viewConfiguration);
        this.L = (int) (400.0f * f);
        this.M = viewConfiguration.getScaledMaximumFlingVelocity();
        this.R = new android.support.v4.widget.d(context);
        this.S = new android.support.v4.widget.d(context);
        this.N = (int) (25.0f * f);
        this.O = (int) (2.0f * f);
        this.C = (int) (16.0f * f);
        z.a((View) this, new c(this));
        if (z.e(this) == 0) {
            z.c((View) this, 1);
        }
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.aj);
        super.onDetachedFromWindow();
    }

    private void setScrollState(int i) {
        if (this.ak != i) {
            this.ak = i;
            if (this.ae != null) {
                b(i != 0);
            }
            f(i);
        }
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        if (this.h != null) {
            this.h.b(this.n);
            this.h.a((ViewGroup) this);
            for (int i = 0; i < this.e.size(); i++) {
                b bVar = (b) this.e.get(i);
                this.h.a((ViewGroup) this, bVar.b, bVar.a);
            }
            this.h.b((ViewGroup) this);
            this.e.clear();
            j();
            this.i = 0;
            scrollTo(0, 0);
        }
        PagerAdapter pagerAdapter2 = this.h;
        this.h = pagerAdapter;
        this.b = 0;
        if (this.h != null) {
            if (this.n == null) {
                this.n = new g();
            }
            this.h.a(this.n);
            this.y = false;
            boolean z = this.T;
            this.T = true;
            this.b = this.h.a();
            if (this.j >= 0) {
                this.h.a(this.k, this.l);
                a(this.j, false, true);
                this.j = -1;
                this.k = null;
                this.l = null;
            } else if (z) {
                requestLayout();
            } else {
                c();
            }
        }
        if (this.ad != null && pagerAdapter2 != pagerAdapter) {
            this.ad.a(pagerAdapter2, pagerAdapter);
        }
    }

    private void j() {
        int i = 0;
        while (i < getChildCount()) {
            if (!((LayoutParams) getChildAt(i).getLayoutParams()).a) {
                removeViewAt(i);
                i--;
            }
            i++;
        }
    }

    public PagerAdapter getAdapter() {
        return this.h;
    }

    void setOnAdapterChangeListener(d dVar) {
        this.ad = dVar;
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public void setCurrentItem(int i) {
        boolean z;
        this.y = false;
        if (this.T) {
            z = false;
        } else {
            z = true;
        }
        a(i, z, false);
    }

    public void setCurrentItem(int i, boolean z) {
        this.y = false;
        a(i, z, false);
    }

    public int getCurrentItem() {
        return this.i;
    }

    void a(int i, boolean z, boolean z2) {
        a(i, z, z2, 0);
    }

    void a(int i, boolean z, boolean z2, int i2) {
        boolean z3 = false;
        if (this.h == null || this.h.a() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (z2 || this.i != i || this.e.size() == 0) {
            if (i < 0) {
                i = 0;
            } else if (i >= this.h.a()) {
                i = this.h.a() - 1;
            }
            int i3 = this.z;
            if (i > this.i + i3 || i < this.i - i3) {
                for (int i4 = 0; i4 < this.e.size(); i4++) {
                    ((b) this.e.get(i4)).c = true;
                }
            }
            if (this.i != i) {
                z3 = true;
            }
            if (this.T) {
                this.i = i;
                if (z3) {
                    e(i);
                }
                requestLayout();
                return;
            }
            a(i);
            a(i, z, i2, z3);
        } else {
            setScrollingCacheEnabled(false);
        }
    }

    private void a(int i, boolean z, int i2, boolean z2) {
        int max;
        b b = b(i);
        if (b != null) {
            max = (int) (Math.max(this.s, Math.min(b.e, this.t)) * ((float) getClientWidth()));
        } else {
            max = 0;
        }
        if (z) {
            a(max, 0, i2);
            if (z2) {
                e(i);
                return;
            }
            return;
        }
        if (z2) {
            e(i);
        }
        a(false);
        scrollTo(max, 0);
        d(max);
    }

    @Deprecated
    public void setOnPageChangeListener(e eVar) {
        this.ab = eVar;
    }

    public void a(e eVar) {
        if (this.aa == null) {
            this.aa = new ArrayList();
        }
        this.aa.add(eVar);
    }

    public void setPageTransformer(boolean z, f fVar) {
        int i = 1;
        if (VERSION.SDK_INT >= 11) {
            boolean z2;
            boolean z3 = fVar != null;
            if (this.ae != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            int i2 = z3 != z2 ? 1 : 0;
            this.ae = fVar;
            setChildrenDrawingOrderEnabledCompat(z3);
            if (z3) {
                if (z) {
                    i = 2;
                }
                this.ag = i;
            } else {
                this.ag = 0;
            }
            if (i2 != 0) {
                c();
            }
        }
    }

    void setChildrenDrawingOrderEnabledCompat(boolean z) {
        if (VERSION.SDK_INT >= 7) {
            if (this.af == null) {
                try {
                    this.af = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[]{Boolean.TYPE});
                } catch (Throwable e) {
                    Log.e("ViewPager", "Can't find setChildrenDrawingOrderEnabled", e);
                }
            }
            try {
                this.af.invoke(this, new Object[]{Boolean.valueOf(z)});
            } catch (Throwable e2) {
                Log.e("ViewPager", "Error changing children drawing order", e2);
            }
        }
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (this.ag == 2) {
            i2 = (i - 1) - i2;
        }
        return ((LayoutParams) ((View) this.ah.get(i2)).getLayoutParams()).f;
    }

    e b(e eVar) {
        e eVar2 = this.ac;
        this.ac = eVar;
        return eVar2;
    }

    public int getOffscreenPageLimit() {
        return this.z;
    }

    public void setOffscreenPageLimit(int i) {
        if (i < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i + " too small; defaulting to " + 1);
            i = 1;
        }
        if (i != this.z) {
            this.z = i;
            c();
        }
    }

    public void setPageMargin(int i) {
        int i2 = this.o;
        this.o = i;
        int width = getWidth();
        a(width, width, i, i2);
        requestLayout();
    }

    public int getPageMargin() {
        return this.o;
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.p = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setPageMarginDrawable(int i) {
        setPageMarginDrawable(getContext().getResources().getDrawable(i));
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.p;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.p;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    float a(float f) {
        return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
    }

    void a(int i, int i2, int i3) {
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i4 = i - scrollX;
        int i5 = i2 - scrollY;
        if (i4 == 0 && i5 == 0) {
            a(false);
            c();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i6 = clientWidth / 2;
        float a = (((float) i6) * a(Math.min(1.0f, (((float) Math.abs(i4)) * 1.0f) / ((float) clientWidth)))) + ((float) i6);
        int abs = Math.abs(i3);
        if (abs > 0) {
            clientWidth = Math.round(1000.0f * Math.abs(a / ((float) abs))) * 4;
        } else {
            clientWidth = (int) (((((float) Math.abs(i4)) / ((((float) clientWidth) * this.h.b(this.i)) + ((float) this.o))) + 1.0f) * 100.0f);
        }
        this.m.startScroll(scrollX, scrollY, i4, i5, Math.min(clientWidth, ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE));
        z.d(this);
    }

    b a(int i, int i2) {
        b bVar = new b();
        bVar.b = i;
        bVar.a = this.h.a((ViewGroup) this, i);
        bVar.d = this.h.b(i);
        if (i2 < 0 || i2 >= this.e.size()) {
            this.e.add(bVar);
        } else {
            this.e.add(i2, bVar);
        }
        return bVar;
    }

    void b() {
        int i;
        int a = this.h.a();
        this.b = a;
        boolean z = this.e.size() < (this.z * 2) + 1 && this.e.size() < a;
        boolean z2 = false;
        int i2 = this.i;
        boolean z3 = z;
        int i3 = 0;
        while (i3 < this.e.size()) {
            int i4;
            boolean z4;
            boolean z5;
            b bVar = (b) this.e.get(i3);
            int a2 = this.h.a(bVar.a);
            if (a2 == -1) {
                i4 = i3;
                z4 = z2;
                i = i2;
                z5 = z3;
            } else if (a2 == -2) {
                this.e.remove(i3);
                i3--;
                if (!z2) {
                    this.h.a((ViewGroup) this);
                    z2 = true;
                }
                this.h.a((ViewGroup) this, bVar.b, bVar.a);
                if (this.i == bVar.b) {
                    i4 = i3;
                    z4 = z2;
                    i = Math.max(0, Math.min(this.i, a - 1));
                    z5 = true;
                } else {
                    i4 = i3;
                    z4 = z2;
                    i = i2;
                    z5 = true;
                }
            } else if (bVar.b != a2) {
                if (bVar.b == this.i) {
                    i2 = a2;
                }
                bVar.b = a2;
                i4 = i3;
                z4 = z2;
                i = i2;
                z5 = true;
            } else {
                i4 = i3;
                z4 = z2;
                i = i2;
                z5 = z3;
            }
            z3 = z5;
            i2 = i;
            z2 = z4;
            i3 = i4 + 1;
        }
        if (z2) {
            this.h.b((ViewGroup) this);
        }
        Collections.sort(this.e, c);
        if (z3) {
            i = getChildCount();
            for (i3 = 0; i3 < i; i3++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i3).getLayoutParams();
                if (!layoutParams.a) {
                    layoutParams.c = 0.0f;
                }
            }
            a(i2, false, true);
            requestLayout();
        }
    }

    void c() {
        a(this.i);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void a(int r19) {
        /*
        r18 = this;
        r3 = 0;
        r2 = 2;
        r0 = r18;
        r4 = r0.i;
        r0 = r19;
        if (r4 == r0) goto L_0x0344;
    L_0x000a:
        r0 = r18;
        r2 = r0.i;
        r0 = r19;
        if (r2 >= r0) goto L_0x0030;
    L_0x0012:
        r2 = 66;
    L_0x0014:
        r0 = r18;
        r3 = r0.i;
        r0 = r18;
        r3 = r0.b(r3);
        r0 = r19;
        r1 = r18;
        r1.i = r0;
        r4 = r3;
        r3 = r2;
    L_0x0026:
        r0 = r18;
        r2 = r0.h;
        if (r2 != 0) goto L_0x0033;
    L_0x002c:
        r18.k();
    L_0x002f:
        return;
    L_0x0030:
        r2 = 17;
        goto L_0x0014;
    L_0x0033:
        r0 = r18;
        r2 = r0.y;
        if (r2 == 0) goto L_0x003d;
    L_0x0039:
        r18.k();
        goto L_0x002f;
    L_0x003d:
        r2 = r18.getWindowToken();
        if (r2 == 0) goto L_0x002f;
    L_0x0043:
        r0 = r18;
        r2 = r0.h;
        r0 = r18;
        r2.a(r0);
        r0 = r18;
        r2 = r0.z;
        r5 = 0;
        r0 = r18;
        r6 = r0.i;
        r6 = r6 - r2;
        r11 = java.lang.Math.max(r5, r6);
        r0 = r18;
        r5 = r0.h;
        r12 = r5.a();
        r5 = r12 + -1;
        r0 = r18;
        r6 = r0.i;
        r2 = r2 + r6;
        r13 = java.lang.Math.min(r5, r2);
        r0 = r18;
        r2 = r0.b;
        if (r12 == r2) goto L_0x00df;
    L_0x0073:
        r2 = r18.getResources();	 Catch:{ NotFoundException -> 0x00d5 }
        r3 = r18.getId();	 Catch:{ NotFoundException -> 0x00d5 }
        r2 = r2.getResourceName(r3);	 Catch:{ NotFoundException -> 0x00d5 }
    L_0x007f:
        r3 = new java.lang.IllegalStateException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: ";
        r4 = r4.append(r5);
        r0 = r18;
        r5 = r0.b;
        r4 = r4.append(r5);
        r5 = ", found: ";
        r4 = r4.append(r5);
        r4 = r4.append(r12);
        r5 = " Pager id: ";
        r4 = r4.append(r5);
        r2 = r4.append(r2);
        r4 = " Pager class: ";
        r2 = r2.append(r4);
        r4 = r18.getClass();
        r2 = r2.append(r4);
        r4 = " Problematic adapter: ";
        r2 = r2.append(r4);
        r0 = r18;
        r4 = r0.h;
        r4 = r4.getClass();
        r2 = r2.append(r4);
        r2 = r2.toString();
        r3.<init>(r2);
        throw r3;
    L_0x00d5:
        r2 = move-exception;
        r2 = r18.getId();
        r2 = java.lang.Integer.toHexString(r2);
        goto L_0x007f;
    L_0x00df:
        r6 = 0;
        r2 = 0;
        r5 = r2;
    L_0x00e2:
        r0 = r18;
        r2 = r0.e;
        r2 = r2.size();
        if (r5 >= r2) goto L_0x0341;
    L_0x00ec:
        r0 = r18;
        r2 = r0.e;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.ViewPager.b) r2;
        r7 = r2.b;
        r0 = r18;
        r8 = r0.i;
        if (r7 < r8) goto L_0x01d4;
    L_0x00fe:
        r7 = r2.b;
        r0 = r18;
        r8 = r0.i;
        if (r7 != r8) goto L_0x0341;
    L_0x0106:
        if (r2 != 0) goto L_0x033e;
    L_0x0108:
        if (r12 <= 0) goto L_0x033e;
    L_0x010a:
        r0 = r18;
        r2 = r0.i;
        r0 = r18;
        r2 = r0.a(r2, r5);
        r10 = r2;
    L_0x0115:
        if (r10 == 0) goto L_0x0185;
    L_0x0117:
        r9 = 0;
        r8 = r5 + -1;
        if (r8 < 0) goto L_0x01d9;
    L_0x011c:
        r0 = r18;
        r2 = r0.e;
        r2 = r2.get(r8);
        r2 = (android.support.v4.view.ViewPager.b) r2;
    L_0x0126:
        r14 = r18.getClientWidth();
        if (r14 > 0) goto L_0x01dc;
    L_0x012c:
        r6 = 0;
    L_0x012d:
        r0 = r18;
        r7 = r0.i;
        r7 = r7 + -1;
        r16 = r7;
        r7 = r9;
        r9 = r16;
        r17 = r8;
        r8 = r5;
        r5 = r17;
    L_0x013d:
        if (r9 < 0) goto L_0x0147;
    L_0x013f:
        r15 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1));
        if (r15 < 0) goto L_0x021b;
    L_0x0143:
        if (r9 >= r11) goto L_0x021b;
    L_0x0145:
        if (r2 != 0) goto L_0x01eb;
    L_0x0147:
        r6 = r10.d;
        r9 = r8 + 1;
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x0180;
    L_0x0151:
        r0 = r18;
        r2 = r0.e;
        r2 = r2.size();
        if (r9 >= r2) goto L_0x0251;
    L_0x015b:
        r0 = r18;
        r2 = r0.e;
        r2 = r2.get(r9);
        r2 = (android.support.v4.view.ViewPager.b) r2;
        r7 = r2;
    L_0x0166:
        if (r14 > 0) goto L_0x0254;
    L_0x0168:
        r2 = 0;
        r5 = r2;
    L_0x016a:
        r0 = r18;
        r2 = r0.i;
        r2 = r2 + 1;
        r16 = r2;
        r2 = r7;
        r7 = r9;
        r9 = r16;
    L_0x0176:
        if (r9 >= r12) goto L_0x0180;
    L_0x0178:
        r11 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1));
        if (r11 < 0) goto L_0x029f;
    L_0x017c:
        if (r9 <= r13) goto L_0x029f;
    L_0x017e:
        if (r2 != 0) goto L_0x0261;
    L_0x0180:
        r0 = r18;
        r0.a(r10, r8, r4);
    L_0x0185:
        r0 = r18;
        r4 = r0.h;
        r0 = r18;
        r5 = r0.i;
        if (r10 == 0) goto L_0x02ed;
    L_0x018f:
        r2 = r10.a;
    L_0x0191:
        r0 = r18;
        r4.b(r0, r5, r2);
        r0 = r18;
        r2 = r0.h;
        r0 = r18;
        r2.b(r0);
        r5 = r18.getChildCount();
        r2 = 0;
        r4 = r2;
    L_0x01a5:
        if (r4 >= r5) goto L_0x02f0;
    L_0x01a7:
        r0 = r18;
        r6 = r0.getChildAt(r4);
        r2 = r6.getLayoutParams();
        r2 = (android.support.v4.view.ViewPager.LayoutParams) r2;
        r2.f = r4;
        r7 = r2.a;
        if (r7 != 0) goto L_0x01d0;
    L_0x01b9:
        r7 = r2.c;
        r8 = 0;
        r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1));
        if (r7 != 0) goto L_0x01d0;
    L_0x01c0:
        r0 = r18;
        r6 = r0.a(r6);
        if (r6 == 0) goto L_0x01d0;
    L_0x01c8:
        r7 = r6.d;
        r2.c = r7;
        r6 = r6.b;
        r2.e = r6;
    L_0x01d0:
        r2 = r4 + 1;
        r4 = r2;
        goto L_0x01a5;
    L_0x01d4:
        r2 = r5 + 1;
        r5 = r2;
        goto L_0x00e2;
    L_0x01d9:
        r2 = 0;
        goto L_0x0126;
    L_0x01dc:
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r7 = r10.d;
        r6 = r6 - r7;
        r7 = r18.getPaddingLeft();
        r7 = (float) r7;
        r15 = (float) r14;
        r7 = r7 / r15;
        r6 = r6 + r7;
        goto L_0x012d;
    L_0x01eb:
        r15 = r2.b;
        if (r9 != r15) goto L_0x0215;
    L_0x01ef:
        r15 = r2.c;
        if (r15 != 0) goto L_0x0215;
    L_0x01f3:
        r0 = r18;
        r15 = r0.e;
        r15.remove(r5);
        r0 = r18;
        r15 = r0.h;
        r2 = r2.a;
        r0 = r18;
        r15.a(r0, r9, r2);
        r5 = r5 + -1;
        r8 = r8 + -1;
        if (r5 < 0) goto L_0x0219;
    L_0x020b:
        r0 = r18;
        r2 = r0.e;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.ViewPager.b) r2;
    L_0x0215:
        r9 = r9 + -1;
        goto L_0x013d;
    L_0x0219:
        r2 = 0;
        goto L_0x0215;
    L_0x021b:
        if (r2 == 0) goto L_0x0235;
    L_0x021d:
        r15 = r2.b;
        if (r9 != r15) goto L_0x0235;
    L_0x0221:
        r2 = r2.d;
        r7 = r7 + r2;
        r5 = r5 + -1;
        if (r5 < 0) goto L_0x0233;
    L_0x0228:
        r0 = r18;
        r2 = r0.e;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.ViewPager.b) r2;
        goto L_0x0215;
    L_0x0233:
        r2 = 0;
        goto L_0x0215;
    L_0x0235:
        r2 = r5 + 1;
        r0 = r18;
        r2 = r0.a(r9, r2);
        r2 = r2.d;
        r7 = r7 + r2;
        r8 = r8 + 1;
        if (r5 < 0) goto L_0x024f;
    L_0x0244:
        r0 = r18;
        r2 = r0.e;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.ViewPager.b) r2;
        goto L_0x0215;
    L_0x024f:
        r2 = 0;
        goto L_0x0215;
    L_0x0251:
        r7 = 0;
        goto L_0x0166;
    L_0x0254:
        r2 = r18.getPaddingRight();
        r2 = (float) r2;
        r5 = (float) r14;
        r2 = r2 / r5;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 + r5;
        r5 = r2;
        goto L_0x016a;
    L_0x0261:
        r11 = r2.b;
        if (r9 != r11) goto L_0x0337;
    L_0x0265:
        r11 = r2.c;
        if (r11 != 0) goto L_0x0337;
    L_0x0269:
        r0 = r18;
        r11 = r0.e;
        r11.remove(r7);
        r0 = r18;
        r11 = r0.h;
        r2 = r2.a;
        r0 = r18;
        r11.a(r0, r9, r2);
        r0 = r18;
        r2 = r0.e;
        r2 = r2.size();
        if (r7 >= r2) goto L_0x029d;
    L_0x0285:
        r0 = r18;
        r2 = r0.e;
        r2 = r2.get(r7);
        r2 = (android.support.v4.view.ViewPager.b) r2;
    L_0x028f:
        r16 = r6;
        r6 = r2;
        r2 = r16;
    L_0x0294:
        r9 = r9 + 1;
        r16 = r2;
        r2 = r6;
        r6 = r16;
        goto L_0x0176;
    L_0x029d:
        r2 = 0;
        goto L_0x028f;
    L_0x029f:
        if (r2 == 0) goto L_0x02c6;
    L_0x02a1:
        r11 = r2.b;
        if (r9 != r11) goto L_0x02c6;
    L_0x02a5:
        r2 = r2.d;
        r6 = r6 + r2;
        r7 = r7 + 1;
        r0 = r18;
        r2 = r0.e;
        r2 = r2.size();
        if (r7 >= r2) goto L_0x02c4;
    L_0x02b4:
        r0 = r18;
        r2 = r0.e;
        r2 = r2.get(r7);
        r2 = (android.support.v4.view.ViewPager.b) r2;
    L_0x02be:
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x0294;
    L_0x02c4:
        r2 = 0;
        goto L_0x02be;
    L_0x02c6:
        r0 = r18;
        r2 = r0.a(r9, r7);
        r7 = r7 + 1;
        r2 = r2.d;
        r6 = r6 + r2;
        r0 = r18;
        r2 = r0.e;
        r2 = r2.size();
        if (r7 >= r2) goto L_0x02eb;
    L_0x02db:
        r0 = r18;
        r2 = r0.e;
        r2 = r2.get(r7);
        r2 = (android.support.v4.view.ViewPager.b) r2;
    L_0x02e5:
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x0294;
    L_0x02eb:
        r2 = 0;
        goto L_0x02e5;
    L_0x02ed:
        r2 = 0;
        goto L_0x0191;
    L_0x02f0:
        r18.k();
        r2 = r18.hasFocus();
        if (r2 == 0) goto L_0x002f;
    L_0x02f9:
        r2 = r18.findFocus();
        if (r2 == 0) goto L_0x0335;
    L_0x02ff:
        r0 = r18;
        r2 = r0.b(r2);
    L_0x0305:
        if (r2 == 0) goto L_0x030f;
    L_0x0307:
        r2 = r2.b;
        r0 = r18;
        r4 = r0.i;
        if (r2 == r4) goto L_0x002f;
    L_0x030f:
        r2 = 0;
    L_0x0310:
        r4 = r18.getChildCount();
        if (r2 >= r4) goto L_0x002f;
    L_0x0316:
        r0 = r18;
        r4 = r0.getChildAt(r2);
        r0 = r18;
        r5 = r0.a(r4);
        if (r5 == 0) goto L_0x0332;
    L_0x0324:
        r5 = r5.b;
        r0 = r18;
        r6 = r0.i;
        if (r5 != r6) goto L_0x0332;
    L_0x032c:
        r4 = r4.requestFocus(r3);
        if (r4 != 0) goto L_0x002f;
    L_0x0332:
        r2 = r2 + 1;
        goto L_0x0310;
    L_0x0335:
        r2 = 0;
        goto L_0x0305;
    L_0x0337:
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x0294;
    L_0x033e:
        r10 = r2;
        goto L_0x0115;
    L_0x0341:
        r2 = r6;
        goto L_0x0106;
    L_0x0344:
        r4 = r3;
        r3 = r2;
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.a(int):void");
    }

    private void k() {
        if (this.ag != 0) {
            if (this.ah == null) {
                this.ah = new ArrayList();
            } else {
                this.ah.clear();
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.ah.add(getChildAt(i));
            }
            Collections.sort(this.ah, ai);
        }
    }

    private void a(b bVar, int i, b bVar2) {
        float f;
        float f2;
        int i2;
        b bVar3;
        int i3;
        int a = this.h.a();
        int clientWidth = getClientWidth();
        if (clientWidth > 0) {
            f = ((float) this.o) / ((float) clientWidth);
        } else {
            f = 0.0f;
        }
        if (bVar2 != null) {
            clientWidth = bVar2.b;
            int i4;
            if (clientWidth < bVar.b) {
                f2 = (bVar2.e + bVar2.d) + f;
                i4 = clientWidth + 1;
                i2 = 0;
                while (i4 <= bVar.b && i2 < this.e.size()) {
                    bVar3 = (b) this.e.get(i2);
                    while (i4 > bVar3.b && i2 < this.e.size() - 1) {
                        i2++;
                        bVar3 = (b) this.e.get(i2);
                    }
                    while (i4 < bVar3.b) {
                        f2 += this.h.b(i4) + f;
                        i4++;
                    }
                    bVar3.e = f2;
                    f2 += bVar3.d + f;
                    i4++;
                }
            } else if (clientWidth > bVar.b) {
                i2 = this.e.size() - 1;
                f2 = bVar2.e;
                i4 = clientWidth - 1;
                while (i4 >= bVar.b && i2 >= 0) {
                    bVar3 = (b) this.e.get(i2);
                    while (i4 < bVar3.b && i2 > 0) {
                        i2--;
                        bVar3 = (b) this.e.get(i2);
                    }
                    while (i4 > bVar3.b) {
                        f2 -= this.h.b(i4) + f;
                        i4--;
                    }
                    f2 -= bVar3.d + f;
                    bVar3.e = f2;
                    i4--;
                }
            }
        }
        int size = this.e.size();
        float f3 = bVar.e;
        i2 = bVar.b - 1;
        this.s = bVar.b == 0 ? bVar.e : -3.4028235E38f;
        this.t = bVar.b == a + -1 ? (bVar.e + bVar.d) - 1.0f : Float.MAX_VALUE;
        for (i3 = i - 1; i3 >= 0; i3--) {
            bVar3 = (b) this.e.get(i3);
            f2 = f3;
            while (i2 > bVar3.b) {
                f2 -= this.h.b(i2) + f;
                i2--;
            }
            f3 = f2 - (bVar3.d + f);
            bVar3.e = f3;
            if (bVar3.b == 0) {
                this.s = f3;
            }
            i2--;
        }
        f3 = (bVar.e + bVar.d) + f;
        i2 = bVar.b + 1;
        for (i3 = i + 1; i3 < size; i3++) {
            bVar3 = (b) this.e.get(i3);
            f2 = f3;
            while (i2 < bVar3.b) {
                f2 = (this.h.b(i2) + f) + f2;
                i2++;
            }
            if (bVar3.b == a - 1) {
                this.t = (bVar3.d + f2) - 1.0f;
            }
            bVar3.e = f2;
            f3 = f2 + (bVar3.d + f);
            i2++;
        }
        this.U = false;
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.i;
        if (this.h != null) {
            savedState.b = this.h.b();
        }
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            if (this.h != null) {
                this.h.a(savedState.b, savedState.c);
                a(savedState.a, false, true);
                return;
            }
            this.j = savedState.a;
            this.k = savedState.b;
            this.l = savedState.c;
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
        android.view.ViewGroup.LayoutParams layoutParams2;
        if (checkLayoutParams(layoutParams)) {
            layoutParams2 = layoutParams;
        } else {
            layoutParams2 = generateLayoutParams(layoutParams);
        }
        LayoutParams layoutParams3 = (LayoutParams) layoutParams2;
        layoutParams3.a |= view instanceof a;
        if (!this.w) {
            super.addView(view, i, layoutParams2);
        } else if (layoutParams3 == null || !layoutParams3.a) {
            layoutParams3.d = true;
            addViewInLayout(view, i, layoutParams2);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    public void removeView(View view) {
        if (this.w) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    b a(View view) {
        for (int i = 0; i < this.e.size(); i++) {
            b bVar = (b) this.e.get(i);
            if (this.h.a(view, bVar.a)) {
                return bVar;
            }
        }
        return null;
    }

    b b(View view) {
        while (true) {
            ViewPager parent = view.getParent();
            if (parent == this) {
                return a(view);
            }
            if (parent != null && (parent instanceof View)) {
                view = parent;
            }
        }
        return null;
    }

    b b(int i) {
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            b bVar = (b) this.e.get(i2);
            if (bVar.b == i) {
                return bVar;
            }
        }
        return null;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.T = true;
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
        int measuredWidth = getMeasuredWidth();
        this.D = Math.min(measuredWidth / 10, this.C);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            LayoutParams layoutParams;
            int i5;
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams != null && layoutParams.a) {
                    int i6 = layoutParams.b & 7;
                    int i7 = layoutParams.b & 112;
                    i3 = Integer.MIN_VALUE;
                    i5 = Integer.MIN_VALUE;
                    Object obj = (i7 == 48 || i7 == 80) ? 1 : null;
                    Object obj2 = (i6 == 3 || i6 == 5) ? 1 : null;
                    if (obj != null) {
                        i3 = 1073741824;
                    } else if (obj2 != null) {
                        i5 = 1073741824;
                    }
                    if (layoutParams.width != -2) {
                        i7 = 1073741824;
                        i3 = layoutParams.width != -1 ? layoutParams.width : paddingLeft;
                    } else {
                        i7 = i3;
                        i3 = paddingLeft;
                    }
                    if (layoutParams.height != -2) {
                        i5 = 1073741824;
                        if (layoutParams.height != -1) {
                            measuredWidth = layoutParams.height;
                            childAt.measure(MeasureSpec.makeMeasureSpec(i3, i7), MeasureSpec.makeMeasureSpec(measuredWidth, i5));
                            if (obj != null) {
                                measuredHeight -= childAt.getMeasuredHeight();
                            } else if (obj2 != null) {
                                paddingLeft -= childAt.getMeasuredWidth();
                            }
                        }
                    }
                    measuredWidth = measuredHeight;
                    childAt.measure(MeasureSpec.makeMeasureSpec(i3, i7), MeasureSpec.makeMeasureSpec(measuredWidth, i5));
                    if (obj != null) {
                        measuredHeight -= childAt.getMeasuredHeight();
                    } else if (obj2 != null) {
                        paddingLeft -= childAt.getMeasuredWidth();
                    }
                }
            }
        }
        this.u = MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824);
        this.v = MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.w = true;
        c();
        this.w = false;
        i3 = getChildCount();
        for (i5 = 0; i5 < i3; i5++) {
            View childAt2 = getChildAt(i5);
            if (childAt2.getVisibility() != 8) {
                layoutParams = (LayoutParams) childAt2.getLayoutParams();
                if (layoutParams == null || !layoutParams.a) {
                    childAt2.measure(MeasureSpec.makeMeasureSpec((int) (layoutParams.c * ((float) paddingLeft)), 1073741824), this.v);
                }
            }
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            a(i, i3, this.o, this.o);
        }
    }

    private void a(int i, int i2, int i3, int i4) {
        if (i2 <= 0 || this.e.isEmpty()) {
            b b = b(this.i);
            int min = (int) ((b != null ? Math.min(b.e, this.t) : 0.0f) * ((float) ((i - getPaddingLeft()) - getPaddingRight())));
            if (min != getScrollX()) {
                a(false);
                scrollTo(min, getScrollY());
                return;
            }
            return;
        }
        int paddingLeft = (int) (((float) (((i - getPaddingLeft()) - getPaddingRight()) + i3)) * (((float) getScrollX()) / ((float) (((i2 - getPaddingLeft()) - getPaddingRight()) + i4))));
        scrollTo(paddingLeft, getScrollY());
        if (!this.m.isFinished()) {
            this.m.startScroll(paddingLeft, 0, (int) (b(this.i).e * ((float) i)), 0, this.m.getDuration() - this.m.timePassed());
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        LayoutParams layoutParams;
        int max;
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int i7 = 0;
        int i8 = 0;
        while (i8 < childCount) {
            int measuredWidth;
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.a) {
                    int i9 = layoutParams.b & 112;
                    switch (layoutParams.b & 7) {
                        case 1:
                            max = Math.max((i5 - childAt.getMeasuredWidth()) / 2, paddingLeft);
                            break;
                        case 3:
                            max = paddingLeft;
                            paddingLeft = childAt.getMeasuredWidth() + paddingLeft;
                            break;
                        case 5:
                            measuredWidth = (i5 - paddingRight) - childAt.getMeasuredWidth();
                            paddingRight += childAt.getMeasuredWidth();
                            max = measuredWidth;
                            break;
                        default:
                            max = paddingLeft;
                            break;
                    }
                    int i10;
                    switch (i9) {
                        case 16:
                            measuredWidth = Math.max((i6 - childAt.getMeasuredHeight()) / 2, paddingTop);
                            i10 = paddingBottom;
                            paddingBottom = paddingTop;
                            paddingTop = i10;
                            break;
                        case 48:
                            measuredWidth = childAt.getMeasuredHeight() + paddingTop;
                            i10 = paddingTop;
                            paddingTop = paddingBottom;
                            paddingBottom = measuredWidth;
                            measuredWidth = i10;
                            break;
                        case Opcodes.APUT_CHAR /*80*/:
                            measuredWidth = (i6 - paddingBottom) - childAt.getMeasuredHeight();
                            i10 = paddingBottom + childAt.getMeasuredHeight();
                            paddingBottom = paddingTop;
                            paddingTop = i10;
                            break;
                        default:
                            measuredWidth = paddingTop;
                            i10 = paddingBottom;
                            paddingBottom = paddingTop;
                            paddingTop = i10;
                            break;
                    }
                    max += scrollX;
                    childAt.layout(max, measuredWidth, childAt.getMeasuredWidth() + max, childAt.getMeasuredHeight() + measuredWidth);
                    measuredWidth = i7 + 1;
                    i7 = paddingBottom;
                    paddingBottom = paddingTop;
                    paddingTop = paddingRight;
                    paddingRight = paddingLeft;
                    i8++;
                    paddingLeft = paddingRight;
                    paddingRight = paddingTop;
                    paddingTop = i7;
                    i7 = measuredWidth;
                }
            }
            measuredWidth = i7;
            i7 = paddingTop;
            paddingTop = paddingRight;
            paddingRight = paddingLeft;
            i8++;
            paddingLeft = paddingRight;
            paddingRight = paddingTop;
            paddingTop = i7;
            i7 = measuredWidth;
        }
        max = (i5 - paddingLeft) - paddingRight;
        for (paddingRight = 0; paddingRight < childCount; paddingRight++) {
            View childAt2 = getChildAt(paddingRight);
            if (childAt2.getVisibility() != 8) {
                layoutParams = (LayoutParams) childAt2.getLayoutParams();
                if (!layoutParams.a) {
                    b a = a(childAt2);
                    if (a != null) {
                        i5 = ((int) (a.e * ((float) max))) + paddingLeft;
                        if (layoutParams.d) {
                            layoutParams.d = false;
                            childAt2.measure(MeasureSpec.makeMeasureSpec((int) (layoutParams.c * ((float) max)), 1073741824), MeasureSpec.makeMeasureSpec((i6 - paddingTop) - paddingBottom, 1073741824));
                        }
                        childAt2.layout(i5, paddingTop, childAt2.getMeasuredWidth() + i5, childAt2.getMeasuredHeight() + paddingTop);
                    }
                }
            }
        }
        this.q = paddingTop;
        this.r = i6 - paddingBottom;
        this.W = i7;
        if (this.T) {
            a(this.i, false, 0, false);
        }
        this.T = false;
    }

    public void computeScroll() {
        if (this.m.isFinished() || !this.m.computeScrollOffset()) {
            a(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.m.getCurrX();
        int currY = this.m.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
            if (!d(currX)) {
                this.m.abortAnimation();
                scrollTo(0, currY);
            }
        }
        z.d(this);
    }

    private boolean d(int i) {
        if (this.e.size() == 0) {
            this.V = false;
            a(0, 0.0f, 0);
            if (this.V) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        b m = m();
        int clientWidth = getClientWidth();
        int i2 = this.o + clientWidth;
        float f = ((float) this.o) / ((float) clientWidth);
        int i3 = m.b;
        float f2 = ((((float) i) / ((float) clientWidth)) - m.e) / (m.d + f);
        clientWidth = (int) (((float) i2) * f2);
        this.V = false;
        a(i3, f2, clientWidth);
        if (this.V) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    protected void a(int i, float f, int i2) {
        int paddingLeft;
        int paddingRight;
        int i3;
        if (this.W > 0) {
            int scrollX = getScrollX();
            paddingLeft = getPaddingLeft();
            paddingRight = getPaddingRight();
            int width = getWidth();
            int childCount = getChildCount();
            i3 = 0;
            while (i3 < childCount) {
                int i4;
                View childAt = getChildAt(i3);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.a) {
                    int max;
                    switch (layoutParams.b & 7) {
                        case 1:
                            max = Math.max((width - childAt.getMeasuredWidth()) / 2, paddingLeft);
                            i4 = paddingRight;
                            paddingRight = paddingLeft;
                            paddingLeft = i4;
                            break;
                        case 3:
                            max = childAt.getWidth() + paddingLeft;
                            i4 = paddingLeft;
                            paddingLeft = paddingRight;
                            paddingRight = max;
                            max = i4;
                            break;
                        case 5:
                            max = (width - paddingRight) - childAt.getMeasuredWidth();
                            i4 = paddingRight + childAt.getMeasuredWidth();
                            paddingRight = paddingLeft;
                            paddingLeft = i4;
                            break;
                        default:
                            max = paddingLeft;
                            i4 = paddingRight;
                            paddingRight = paddingLeft;
                            paddingLeft = i4;
                            break;
                    }
                    max = (max + scrollX) - childAt.getLeft();
                    if (max != 0) {
                        childAt.offsetLeftAndRight(max);
                    }
                } else {
                    i4 = paddingRight;
                    paddingRight = paddingLeft;
                    paddingLeft = i4;
                }
                i3++;
                i4 = paddingLeft;
                paddingLeft = paddingRight;
                paddingRight = i4;
            }
        }
        b(i, f, i2);
        if (this.ae != null) {
            paddingRight = getScrollX();
            i3 = getChildCount();
            for (paddingLeft = 0; paddingLeft < i3; paddingLeft++) {
                View childAt2 = getChildAt(paddingLeft);
                if (!((LayoutParams) childAt2.getLayoutParams()).a) {
                    this.ae.a(childAt2, ((float) (childAt2.getLeft() - paddingRight)) / ((float) getClientWidth()));
                }
            }
        }
        this.V = true;
    }

    private void b(int i, float f, int i2) {
        if (this.ab != null) {
            this.ab.onPageScrolled(i, f, i2);
        }
        if (this.aa != null) {
            int size = this.aa.size();
            for (int i3 = 0; i3 < size; i3++) {
                e eVar = (e) this.aa.get(i3);
                if (eVar != null) {
                    eVar.onPageScrolled(i, f, i2);
                }
            }
        }
        if (this.ac != null) {
            this.ac.onPageScrolled(i, f, i2);
        }
    }

    private void e(int i) {
        if (this.ab != null) {
            this.ab.onPageSelected(i);
        }
        if (this.aa != null) {
            int size = this.aa.size();
            for (int i2 = 0; i2 < size; i2++) {
                e eVar = (e) this.aa.get(i2);
                if (eVar != null) {
                    eVar.onPageSelected(i);
                }
            }
        }
        if (this.ac != null) {
            this.ac.onPageSelected(i);
        }
    }

    private void f(int i) {
        if (this.ab != null) {
            this.ab.onPageScrollStateChanged(i);
        }
        if (this.aa != null) {
            int size = this.aa.size();
            for (int i2 = 0; i2 < size; i2++) {
                e eVar = (e) this.aa.get(i2);
                if (eVar != null) {
                    eVar.onPageScrollStateChanged(i);
                }
            }
        }
        if (this.ac != null) {
            this.ac.onPageScrollStateChanged(i);
        }
    }

    private void a(boolean z) {
        int scrollX;
        boolean z2 = this.ak == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            this.m.abortAnimation();
            scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.m.getCurrX();
            int currY = this.m.getCurrY();
            if (!(scrollX == currX && scrollY == currY)) {
                scrollTo(currX, currY);
                if (currX != scrollX) {
                    d(currX);
                }
            }
        }
        this.y = false;
        boolean z3 = z2;
        for (scrollX = 0; scrollX < this.e.size(); scrollX++) {
            b bVar = (b) this.e.get(scrollX);
            if (bVar.c) {
                bVar.c = false;
                z3 = true;
            }
        }
        if (!z3) {
            return;
        }
        if (z) {
            z.a((View) this, this.aj);
        } else {
            this.aj.run();
        }
    }

    private boolean a(float f, float f2) {
        return (f < ((float) this.D) && f2 > 0.0f) || (f > ((float) (getWidth() - this.D)) && f2 < 0.0f);
    }

    private void b(boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            int i2;
            if (z) {
                i2 = 2;
            } else {
                i2 = 0;
            }
            z.a(getChildAt(i), i2, null);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            l();
            return false;
        }
        if (action != 0) {
            if (this.A) {
                return true;
            }
            if (this.B) {
                return false;
            }
        }
        switch (action) {
            case 0:
                float x = motionEvent.getX();
                this.H = x;
                this.F = x;
                x = motionEvent.getY();
                this.I = x;
                this.G = x;
                this.J = n.b(motionEvent, 0);
                this.B = false;
                this.m.computeScrollOffset();
                if (this.ak == 2 && Math.abs(this.m.getFinalX() - this.m.getCurrX()) > this.O) {
                    this.m.abortAnimation();
                    this.y = false;
                    c();
                    this.A = true;
                    c(true);
                    setScrollState(1);
                    break;
                }
                a(false);
                this.A = false;
                break;
            case 2:
                action = this.J;
                if (action != -1) {
                    action = n.a(motionEvent, action);
                    float c = n.c(motionEvent, action);
                    float f = c - this.F;
                    float abs = Math.abs(f);
                    float d = n.d(motionEvent, action);
                    float abs2 = Math.abs(d - this.I);
                    if (f == 0.0f || a(this.F, f) || !a(this, false, (int) f, (int) c, (int) d)) {
                        if (abs > ((float) this.E) && 0.5f * abs > abs2) {
                            this.A = true;
                            c(true);
                            setScrollState(1);
                            this.F = f > 0.0f ? this.H + ((float) this.E) : this.H - ((float) this.E);
                            this.G = d;
                            setScrollingCacheEnabled(true);
                        } else if (abs2 > ((float) this.E)) {
                            this.B = true;
                        }
                        if (this.A && c(c)) {
                            z.d(this);
                            break;
                        }
                    }
                    this.F = c;
                    this.G = d;
                    this.B = true;
                    return false;
                }
                break;
            case 6:
                a(motionEvent);
                break;
        }
        if (this.K == null) {
            this.K = VelocityTracker.obtain();
        }
        this.K.addMovement(motionEvent);
        return this.A;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.P) {
            return true;
        }
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        if (this.h == null || this.h.a() == 0) {
            return false;
        }
        if (this.K == null) {
            this.K = VelocityTracker.obtain();
        }
        this.K.addMovement(motionEvent);
        float x;
        int a;
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.m.abortAnimation();
                this.y = false;
                c();
                x = motionEvent.getX();
                this.H = x;
                this.F = x;
                x = motionEvent.getY();
                this.I = x;
                this.G = x;
                this.J = n.b(motionEvent, 0);
                break;
            case 1:
                if (this.A) {
                    VelocityTracker velocityTracker = this.K;
                    velocityTracker.computeCurrentVelocity(1000, (float) this.M);
                    a = (int) x.a(velocityTracker, this.J);
                    this.y = true;
                    int clientWidth = getClientWidth();
                    int scrollX = getScrollX();
                    b m = m();
                    a(a(m.b, ((((float) scrollX) / ((float) clientWidth)) - m.e) / m.d, a, (int) (n.c(motionEvent, n.a(motionEvent, this.J)) - this.H)), true, true, a);
                    z = l();
                    break;
                }
                break;
            case 2:
                if (!this.A) {
                    a = n.a(motionEvent, this.J);
                    if (a == -1) {
                        z = l();
                        break;
                    }
                    float c = n.c(motionEvent, a);
                    float abs = Math.abs(c - this.F);
                    float d = n.d(motionEvent, a);
                    x = Math.abs(d - this.G);
                    if (abs > ((float) this.E) && abs > x) {
                        this.A = true;
                        c(true);
                        if (c - this.H > 0.0f) {
                            x = this.H + ((float) this.E);
                        } else {
                            x = this.H - ((float) this.E);
                        }
                        this.F = x;
                        this.G = d;
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
                if (this.A) {
                    z = false | c(n.c(motionEvent, n.a(motionEvent, this.J)));
                    break;
                }
                break;
            case 3:
                if (this.A) {
                    a(this.i, true, 0, false);
                    z = l();
                    break;
                }
                break;
            case 5:
                a = n.b(motionEvent);
                this.F = n.c(motionEvent, a);
                this.J = n.b(motionEvent, a);
                break;
            case 6:
                a(motionEvent);
                this.F = n.c(motionEvent, n.a(motionEvent, this.J));
                break;
        }
        if (z) {
            z.d(this);
        }
        return true;
    }

    private boolean l() {
        this.J = -1;
        n();
        return this.R.c() | this.S.c();
    }

    private void c(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private boolean c(float f) {
        boolean z;
        float f2;
        boolean z2 = true;
        boolean z3 = false;
        float f3 = this.F - f;
        this.F = f;
        float scrollX = ((float) getScrollX()) + f3;
        int clientWidth = getClientWidth();
        float f4 = ((float) clientWidth) * this.s;
        float f5 = ((float) clientWidth) * this.t;
        b bVar = (b) this.e.get(0);
        b bVar2 = (b) this.e.get(this.e.size() - 1);
        if (bVar.b != 0) {
            f4 = bVar.e * ((float) clientWidth);
            z = false;
        } else {
            z = true;
        }
        if (bVar2.b != this.h.a() - 1) {
            f2 = bVar2.e * ((float) clientWidth);
            z2 = false;
        } else {
            f2 = f5;
        }
        if (scrollX < f4) {
            if (z) {
                z3 = this.R.a(Math.abs(f4 - scrollX) / ((float) clientWidth));
            }
        } else if (scrollX > f2) {
            if (z2) {
                z3 = this.S.a(Math.abs(scrollX - f2) / ((float) clientWidth));
            }
            f4 = f2;
        } else {
            f4 = scrollX;
        }
        this.F += f4 - ((float) ((int) f4));
        scrollTo((int) f4, getScrollY());
        d((int) f4);
        return z3;
    }

    private b m() {
        float f;
        int clientWidth = getClientWidth();
        float scrollX = clientWidth > 0 ? ((float) getScrollX()) / ((float) clientWidth) : 0.0f;
        if (clientWidth > 0) {
            f = ((float) this.o) / ((float) clientWidth);
        } else {
            f = 0.0f;
        }
        float f2 = 0.0f;
        float f3 = 0.0f;
        int i = -1;
        int i2 = 0;
        Object obj = 1;
        b bVar = null;
        while (i2 < this.e.size()) {
            int i3;
            b bVar2;
            b bVar3 = (b) this.e.get(i2);
            b bVar4;
            if (obj != null || bVar3.b == i + 1) {
                bVar4 = bVar3;
                i3 = i2;
                bVar2 = bVar4;
            } else {
                bVar3 = this.f;
                bVar3.e = (f2 + f3) + f;
                bVar3.b = i + 1;
                bVar3.d = this.h.b(bVar3.b);
                bVar4 = bVar3;
                i3 = i2 - 1;
                bVar2 = bVar4;
            }
            f2 = bVar2.e;
            f3 = (bVar2.d + f2) + f;
            if (obj == null && scrollX < f2) {
                return bVar;
            }
            if (scrollX < f3 || i3 == this.e.size() - 1) {
                return bVar2;
            }
            f3 = f2;
            i = bVar2.b;
            obj = null;
            f2 = bVar2.d;
            bVar = bVar2;
            i2 = i3 + 1;
        }
        return bVar;
    }

    private int a(int i, float f, int i2, int i3) {
        if (Math.abs(i3) <= this.N || Math.abs(i2) <= this.L) {
            i = (int) ((i >= this.i ? 0.4f : 0.6f) + (((float) i) + f));
        } else if (i2 <= 0) {
            i++;
        }
        if (this.e.size() <= 0) {
            return i;
        }
        return Math.max(((b) this.e.get(0)).b, Math.min(i, ((b) this.e.get(this.e.size() - 1)).b));
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int i = 0;
        int a = z.a(this);
        if (a == 0 || (a == 1 && this.h != null && this.h.a() > 1)) {
            int height;
            int width;
            if (!this.R.a()) {
                a = canvas.save();
                height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((float) ((-height) + getPaddingTop()), this.s * ((float) width));
                this.R.a(height, width);
                i = 0 | this.R.a(canvas);
                canvas.restoreToCount(a);
            }
            if (!this.S.a()) {
                a = canvas.save();
                height = getWidth();
                width = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate((float) (-getPaddingTop()), (-(this.t + 1.0f)) * ((float) height));
                this.S.a(width, height);
                i |= this.S.a(canvas);
                canvas.restoreToCount(a);
            }
        } else {
            this.R.b();
            this.S.b();
        }
        if (i != 0) {
            z.d(this);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.o > 0 && this.p != null && this.e.size() > 0 && this.h != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            float f = ((float) this.o) / ((float) width);
            b bVar = (b) this.e.get(0);
            float f2 = bVar.e;
            int size = this.e.size();
            int i = bVar.b;
            int i2 = ((b) this.e.get(size - 1)).b;
            int i3 = 0;
            int i4 = i;
            while (i4 < i2) {
                float f3;
                while (i4 > bVar.b && i3 < size) {
                    i3++;
                    bVar = (b) this.e.get(i3);
                }
                if (i4 == bVar.b) {
                    f3 = (bVar.e + bVar.d) * ((float) width);
                    f2 = (bVar.e + bVar.d) + f;
                } else {
                    float b = this.h.b(i4);
                    f3 = (f2 + b) * ((float) width);
                    f2 += b + f;
                }
                if (((float) this.o) + f3 > ((float) scrollX)) {
                    this.p.setBounds((int) f3, this.q, (int) ((((float) this.o) + f3) + 0.5f), this.r);
                    this.p.draw(canvas);
                }
                if (f3 <= ((float) (scrollX + width))) {
                    i4++;
                } else {
                    return;
                }
            }
        }
    }

    public boolean d() {
        if (this.A) {
            return false;
        }
        this.P = true;
        setScrollState(1);
        this.F = 0.0f;
        this.H = 0.0f;
        if (this.K == null) {
            this.K = VelocityTracker.obtain();
        } else {
            this.K.clear();
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, 0.0f, 0.0f, 0);
        this.K.addMovement(obtain);
        obtain.recycle();
        this.Q = uptimeMillis;
        return true;
    }

    public void e() {
        if (this.P) {
            VelocityTracker velocityTracker = this.K;
            velocityTracker.computeCurrentVelocity(1000, (float) this.M);
            int a = (int) x.a(velocityTracker, this.J);
            this.y = true;
            int clientWidth = getClientWidth();
            int scrollX = getScrollX();
            b m = m();
            a(a(m.b, ((((float) scrollX) / ((float) clientWidth)) - m.e) / m.d, a, (int) (this.F - this.H)), true, true, a);
            n();
            this.P = false;
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public void b(float f) {
        if (this.P) {
            float f2;
            float f3;
            this.F += f;
            float scrollX = ((float) getScrollX()) - f;
            int clientWidth = getClientWidth();
            float f4 = ((float) clientWidth) * this.s;
            float f5 = ((float) clientWidth) * this.t;
            b bVar = (b) this.e.get(0);
            b bVar2 = (b) this.e.get(this.e.size() - 1);
            if (bVar.b != 0) {
                f2 = bVar.e * ((float) clientWidth);
            } else {
                f2 = f4;
            }
            if (bVar2.b != this.h.a() - 1) {
                f3 = bVar2.e * ((float) clientWidth);
            } else {
                f3 = f5;
            }
            if (scrollX >= f2) {
                if (scrollX > f3) {
                    f2 = f3;
                } else {
                    f2 = scrollX;
                }
            }
            this.F += f2 - ((float) ((int) f2));
            scrollTo((int) f2, getScrollY());
            d((int) f2);
            MotionEvent obtain = MotionEvent.obtain(this.Q, SystemClock.uptimeMillis(), 2, this.F, 0.0f, 0);
            this.K.addMovement(obtain);
            obtain.recycle();
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public boolean f() {
        return this.P;
    }

    private void a(MotionEvent motionEvent) {
        int b = n.b(motionEvent);
        if (n.b(motionEvent, b) == this.J) {
            b = b == 0 ? 1 : 0;
            this.F = n.c(motionEvent, b);
            this.J = n.b(motionEvent, b);
            if (this.K != null) {
                this.K.clear();
            }
        }
    }

    private void n() {
        this.A = false;
        this.B = false;
        if (this.K != null) {
            this.K.recycle();
            this.K = null;
        }
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.x != z) {
            this.x = z;
        }
    }

    public boolean canScrollHorizontally(int i) {
        boolean z = true;
        if (this.h == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        if (i < 0) {
            if (scrollX <= ((int) (((float) clientWidth) * this.s))) {
                z = false;
            }
            return z;
        } else if (i <= 0) {
            return false;
        } else {
            if (scrollX >= ((int) (((float) clientWidth) * this.t))) {
                z = false;
            }
            return z;
        }
    }

    protected boolean a(View view, boolean z, int i, int i2, int i3) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (i2 + scrollX >= childAt.getLeft() && i2 + scrollX < childAt.getRight() && i3 + scrollY >= childAt.getTop() && i3 + scrollY < childAt.getBottom()) {
                    if (a(childAt, true, i, (i2 + scrollX) - childAt.getLeft(), (i3 + scrollY) - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        if (z && z.a(view, -i)) {
            return true;
        }
        return false;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || a(keyEvent);
    }

    public boolean a(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) {
            return false;
        }
        switch (keyEvent.getKeyCode()) {
            case 21:
                return c(17);
            case 22:
                return c(66);
            case 61:
                if (VERSION.SDK_INT < 11) {
                    return false;
                }
                if (f.a(keyEvent)) {
                    return c(2);
                }
                if (f.a(keyEvent, 1)) {
                    return c(1);
                }
                return false;
            default:
                return false;
        }
    }

    public boolean c(int i) {
        View view;
        boolean g;
        View findFocus = findFocus();
        if (findFocus == this) {
            view = null;
        } else {
            if (findFocus != null) {
                Object obj;
                for (ViewPager parent = findFocus.getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
                    if (parent == this) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(findFocus.getClass().getSimpleName());
                    for (ViewParent parent2 = findFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                        stringBuilder.append(" => ").append(parent2.getClass().getSimpleName());
                    }
                    Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + stringBuilder.toString());
                    view = null;
                }
            }
            view = findFocus;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, view, i);
        if (findNextFocus == null || findNextFocus == view) {
            if (i == 17 || i == 1) {
                g = g();
            } else {
                if (i == 66 || i == 2) {
                    g = h();
                }
                g = false;
            }
        } else if (i == 17) {
            g = (view == null || a(this.g, findNextFocus).left < a(this.g, view).left) ? findNextFocus.requestFocus() : g();
        } else {
            if (i == 66) {
                g = (view == null || a(this.g, findNextFocus).left > a(this.g, view).left) ? findNextFocus.requestFocus() : h();
            }
            g = false;
        }
        if (g) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        }
        return g;
    }

    private Rect a(Rect rect, View view) {
        Rect rect2;
        if (rect == null) {
            rect2 = new Rect();
        } else {
            rect2 = rect;
        }
        if (view == null) {
            rect2.set(0, 0, 0, 0);
            return rect2;
        }
        rect2.left = view.getLeft();
        rect2.right = view.getRight();
        rect2.top = view.getTop();
        rect2.bottom = view.getBottom();
        ViewPager parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = parent;
            rect2.left += viewGroup.getLeft();
            rect2.right += viewGroup.getRight();
            rect2.top += viewGroup.getTop();
            rect2.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect2;
    }

    boolean g() {
        if (this.i <= 0) {
            return false;
        }
        setCurrentItem(this.i - 1, true);
        return true;
    }

    boolean h() {
        if (this.h == null || this.i >= this.h.a() - 1) {
            return false;
        }
        setCurrentItem(this.i + 1, true);
        return true;
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() == 0) {
                    b a = a(childAt);
                    if (a != null && a.b == this.i) {
                        childAt.addFocusables(arrayList, i, i2);
                    }
                }
            }
        }
        if ((descendantFocusability == 262144 && size != arrayList.size()) || !isFocusable()) {
            return;
        }
        if (((i2 & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) && arrayList != null) {
            arrayList.add(this);
        }
    }

    public void addTouchables(ArrayList<View> arrayList) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                b a = a(childAt);
                if (a != null && a.b == this.i) {
                    childAt.addTouchables(arrayList);
                }
            }
        }
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        int i3 = -1;
        int childCount = getChildCount();
        if ((i & 2) != 0) {
            i3 = 1;
            i2 = 0;
        } else {
            i2 = childCount - 1;
            childCount = -1;
        }
        while (i2 != childCount) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0) {
                b a = a(childAt);
                if (a != null && a.b == this.i && childAt.requestFocus(i, rect)) {
                    return true;
                }
            }
            i2 += i3;
        }
        return false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                b a = a(childAt);
                if (a != null && a.b == this.i && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }
}
