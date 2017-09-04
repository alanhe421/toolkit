package com.qq.reader.view;

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
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.a.l;
import android.support.v4.view.ai;
import android.support.v4.view.n;
import android.support.v4.view.x;
import android.support.v4.view.z;
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

public class VerticalViewPager extends ViewGroup {
    private static final int[] a = new int[]{16842931};
    private static final f ag = new f();
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
    private android.support.v4.widget.d Q;
    private android.support.v4.widget.d R;
    private boolean S = true;
    private boolean T = false;
    private boolean U;
    private int V;
    private android.support.v4.view.ViewPager.e W;
    private android.support.v4.view.ViewPager.e aa;
    private d ab;
    private android.support.v4.view.ViewPager.f ac;
    private Method ad;
    private int ae;
    private ArrayList<View> af;
    private final Runnable ah = new Runnable(this) {
        final /* synthetic */ VerticalViewPager a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.setScrollState(0);
            this.a.e();
        }
    };
    private int ai = 0;
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
    private e n;
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
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, VerticalViewPager.a);
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

    interface a {
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

    class c extends android.support.v4.view.a {
        final /* synthetic */ VerticalViewPager b;

        c(VerticalViewPager verticalViewPager) {
            this.b = verticalViewPager;
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
            if (this.b.c(1)) {
                cVar.a(4096);
            }
            if (this.b.c(-1)) {
                cVar.a(8192);
            }
        }

        public boolean a(View view, int i, Bundle bundle) {
            if (super.a(view, i, bundle)) {
                return true;
            }
            switch (i) {
                case 4096:
                    if (!this.b.c(1)) {
                        return false;
                    }
                    this.b.setCurrentItem(this.b.i + 1);
                    return true;
                case 8192:
                    if (!this.b.c(-1)) {
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

    interface d {
        void a(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2);
    }

    private class e extends DataSetObserver {
        final /* synthetic */ VerticalViewPager a;

        private e(VerticalViewPager verticalViewPager) {
            this.a = verticalViewPager;
        }

        public void onChanged() {
            this.a.d();
        }

        public void onInvalidated() {
            this.a.d();
        }
    }

    static class f implements Comparator<View> {
        f() {
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

    public VerticalViewPager(Context context) {
        super(context);
        c();
    }

    public VerticalViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c();
    }

    void c() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.m = new Scroller(context, d);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context.getApplicationContext());
        float f = context.getResources().getDisplayMetrics().density;
        this.E = ai.a(viewConfiguration);
        this.L = (int) (400.0f * f);
        this.M = viewConfiguration.getScaledMaximumFlingVelocity();
        this.Q = new android.support.v4.widget.d(context);
        this.R = new android.support.v4.widget.d(context);
        this.N = (int) (25.0f * f);
        this.O = (int) (2.0f * f);
        this.C = (int) (16.0f * f);
        z.a(this, new c(this));
        if (z.e(this) == 0) {
            z.c(this, 1);
        }
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.ah);
        super.onDetachedFromWindow();
    }

    private void setScrollState(int i) {
        if (this.ai != i) {
            this.ai = i;
            if (this.ac != null) {
                b(i != 0);
            }
            if (this.W != null) {
                this.W.onPageScrollStateChanged(i);
            }
        }
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        if (this.h != null) {
            this.h.b(this.n);
            this.h.a(this);
            for (int i = 0; i < this.e.size(); i++) {
                b bVar = (b) this.e.get(i);
                this.h.a(this, bVar.b, bVar.a);
            }
            this.h.b(this);
            this.e.clear();
            a();
            this.i = 0;
            scrollTo(0, 0);
        }
        PagerAdapter pagerAdapter2 = this.h;
        this.h = pagerAdapter;
        this.b = 0;
        if (this.h != null) {
            if (this.n == null) {
                this.n = new e();
            }
            this.h.a(this.n);
            this.y = false;
            boolean z = this.S;
            this.S = true;
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
                e();
            }
        }
        if (this.ab != null && pagerAdapter2 != pagerAdapter) {
            this.ab.a(pagerAdapter2, pagerAdapter);
        }
    }

    private void a() {
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
        this.ab = dVar;
    }

    private int getClientHeight() {
        return (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
    }

    public void setCurrentItem(int i) {
        boolean z;
        this.y = false;
        if (this.S) {
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
            if (this.S) {
                this.i = i;
                if (z3 && this.W != null) {
                    this.W.onPageSelected(i);
                }
                if (z3 && this.aa != null) {
                    this.aa.onPageSelected(i);
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
            max = (int) (Math.max(this.s, Math.min(b.e, this.t)) * ((float) getClientHeight()));
        } else {
            max = 0;
        }
        if (z) {
            a(0, max, i2);
            if (z2 && this.W != null) {
                this.W.onPageSelected(i);
            }
            if (z2 && this.aa != null) {
                this.aa.onPageSelected(i);
                return;
            }
            return;
        }
        if (z2 && this.W != null) {
            this.W.onPageSelected(i);
        }
        if (z2 && this.aa != null) {
            this.aa.onPageSelected(i);
        }
        a(false);
        scrollTo(0, max);
        e(max);
    }

    public void setOnPageChangeListener(android.support.v4.view.ViewPager.e eVar) {
        this.W = eVar;
    }

    public void setPageTransformer(boolean z, android.support.v4.view.ViewPager.f fVar) {
        int i = 1;
        if (VERSION.SDK_INT >= 11) {
            boolean z2;
            boolean z3 = fVar != null;
            if (this.ac != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            int i2 = z3 != z2 ? 1 : 0;
            this.ac = fVar;
            setChildrenDrawingOrderEnabledCompat(z3);
            if (z3) {
                if (z) {
                    i = 2;
                }
                this.ae = i;
            } else {
                this.ae = 0;
            }
            if (i2 != 0) {
                e();
            }
        }
    }

    void setChildrenDrawingOrderEnabledCompat(boolean z) {
        if (VERSION.SDK_INT >= 7) {
            if (this.ad == null) {
                try {
                    this.ad = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[]{Boolean.TYPE});
                } catch (Throwable e) {
                    Log.e("ViewPager", "Can't find setChildrenDrawingOrderEnabled", e);
                }
            }
            try {
                this.ad.invoke(this, new Object[]{Boolean.valueOf(z)});
            } catch (Throwable e2) {
                Log.e("ViewPager", "Error changing children drawing order", e2);
            }
        }
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (this.ae == 2) {
            i2 = (i - 1) - i2;
        }
        return ((LayoutParams) ((View) this.af.get(i2)).getLayoutParams()).f;
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
            e();
        }
    }

    public void setPageMargin(int i) {
        int i2 = this.o;
        this.o = i;
        int height = getHeight();
        a(height, height, i, i2);
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
            e();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientHeight = getClientHeight();
        int i6 = clientHeight / 2;
        float a = (((float) i6) * a(Math.min(1.0f, (((float) Math.abs(i4)) * 1.0f) / ((float) clientHeight)))) + ((float) i6);
        int abs = Math.abs(i3);
        if (abs > 0) {
            clientHeight = Math.round(1000.0f * Math.abs(a / ((float) abs))) * 4;
        } else {
            clientHeight = (int) (((((float) Math.abs(i4)) / ((((float) clientHeight) * this.h.b(this.i)) + ((float) this.o))) + 1.0f) * 100.0f);
        }
        this.m.startScroll(scrollX, scrollY, i4, i5, Math.min(clientHeight, ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE));
        z.d(this);
    }

    b a(int i, int i2) {
        b bVar = new b();
        bVar.b = i;
        bVar.a = this.h.a(this, i);
        bVar.d = this.h.b(i);
        if (i2 < 0 || i2 >= this.e.size()) {
            this.e.add(bVar);
        } else {
            this.e.add(i2, bVar);
        }
        return bVar;
    }

    void d() {
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
                    this.h.a(this);
                    z2 = true;
                }
                this.h.a(this, bVar.b, bVar.a);
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
            this.h.b(this);
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

    void e() {
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
        r2 = 130; // 0x82 float:1.82E-43 double:6.4E-322;
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
        r18.b();
    L_0x002f:
        return;
    L_0x0030:
        r2 = 33;
        goto L_0x0014;
    L_0x0033:
        r0 = r18;
        r2 = r0.y;
        if (r2 == 0) goto L_0x003d;
    L_0x0039:
        r18.b();
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
        r2 = (com.qq.reader.view.VerticalViewPager.b) r2;
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
        r2 = (com.qq.reader.view.VerticalViewPager.b) r2;
    L_0x0126:
        r14 = r18.getClientHeight();
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
        r2 = (com.qq.reader.view.VerticalViewPager.b) r2;
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
        r2 = (com.qq.reader.view.VerticalViewPager.LayoutParams) r2;
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
        r2 = (com.qq.reader.view.VerticalViewPager.b) r2;
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
        r2 = (com.qq.reader.view.VerticalViewPager.b) r2;
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
        r2 = (com.qq.reader.view.VerticalViewPager.b) r2;
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
        r2 = (com.qq.reader.view.VerticalViewPager.b) r2;
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
        r2 = (com.qq.reader.view.VerticalViewPager.b) r2;
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
        r2 = (com.qq.reader.view.VerticalViewPager.b) r2;
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
        r18.b();
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
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.view.VerticalViewPager.a(int):void");
    }

    private void b() {
        if (this.ae != 0) {
            if (this.af == null) {
                this.af = new ArrayList();
            } else {
                this.af.clear();
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.af.add(getChildAt(i));
            }
            Collections.sort(this.af, ag);
        }
    }

    private void a(b bVar, int i, b bVar2) {
        float f;
        float f2;
        int i2;
        b bVar3;
        int i3;
        int a = this.h.a();
        int clientHeight = getClientHeight();
        if (clientHeight > 0) {
            f = ((float) this.o) / ((float) clientHeight);
        } else {
            f = 0.0f;
        }
        if (bVar2 != null) {
            clientHeight = bVar2.b;
            int i4;
            if (clientHeight < bVar.b) {
                f2 = (bVar2.e + bVar2.d) + f;
                i4 = clientHeight + 1;
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
            } else if (clientHeight > bVar.b) {
                i2 = this.e.size() - 1;
                f2 = bVar2.e;
                i4 = clientHeight - 1;
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
        this.T = false;
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
            VerticalViewPager parent = view.getParent();
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
        this.S = true;
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
        int measuredHeight = getMeasuredHeight();
        this.D = Math.min(measuredHeight / 10, this.C);
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        int paddingTop = (measuredHeight - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            LayoutParams layoutParams;
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams != null && layoutParams.a) {
                    int i6 = layoutParams.b & 7;
                    int i7 = layoutParams.b & 112;
                    i3 = Integer.MIN_VALUE;
                    i4 = Integer.MIN_VALUE;
                    Object obj = (i7 == 48 || i7 == 80) ? 1 : null;
                    Object obj2 = (i6 == 3 || i6 == 5) ? 1 : null;
                    if (obj != null) {
                        i3 = 1073741824;
                    } else if (obj2 != null) {
                        i4 = 1073741824;
                    }
                    if (layoutParams.width != -2) {
                        i7 = 1073741824;
                        i3 = layoutParams.width != -1 ? layoutParams.width : measuredWidth;
                    } else {
                        i7 = i3;
                        i3 = measuredWidth;
                    }
                    if (layoutParams.height != -2) {
                        i4 = 1073741824;
                        if (layoutParams.height != -1) {
                            measuredHeight = layoutParams.height;
                            childAt.measure(MeasureSpec.makeMeasureSpec(i3, i7), MeasureSpec.makeMeasureSpec(measuredHeight, i4));
                            if (obj != null) {
                                paddingTop -= childAt.getMeasuredHeight();
                            } else if (obj2 != null) {
                                measuredWidth -= childAt.getMeasuredWidth();
                            }
                        }
                    }
                    measuredHeight = paddingTop;
                    childAt.measure(MeasureSpec.makeMeasureSpec(i3, i7), MeasureSpec.makeMeasureSpec(measuredHeight, i4));
                    if (obj != null) {
                        paddingTop -= childAt.getMeasuredHeight();
                    } else if (obj2 != null) {
                        measuredWidth -= childAt.getMeasuredWidth();
                    }
                }
            }
        }
        this.u = MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        this.v = MeasureSpec.makeMeasureSpec(paddingTop, 1073741824);
        this.w = true;
        e();
        this.w = false;
        i3 = getChildCount();
        for (i4 = 0; i4 < i3; i4++) {
            View childAt2 = getChildAt(i4);
            if (childAt2.getVisibility() != 8) {
                layoutParams = (LayoutParams) childAt2.getLayoutParams();
                if (layoutParams == null || !layoutParams.a) {
                    childAt2.measure(this.u, MeasureSpec.makeMeasureSpec((int) (layoutParams.c * ((float) paddingTop)), 1073741824));
                }
            }
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i2 != i4) {
            a(i2, i4, this.o, this.o);
        }
    }

    private void a(int i, int i2, int i3, int i4) {
        if (i2 <= 0 || this.e.isEmpty()) {
            b b = b(this.i);
            int min = (int) ((b != null ? Math.min(b.e, this.t) : 0.0f) * ((float) ((i - getPaddingTop()) - getPaddingBottom())));
            if (min != getScrollY()) {
                a(false);
                scrollTo(getScrollX(), min);
                return;
            }
            return;
        }
        int paddingTop = (int) (((float) (((i - getPaddingTop()) - getPaddingBottom()) + i3)) * (((float) getScrollY()) / ((float) (((i2 - getPaddingTop()) - getPaddingBottom()) + i4))));
        scrollTo(getScrollX(), paddingTop);
        if (!this.m.isFinished()) {
            this.m.startScroll(0, paddingTop, 0, (int) (b(this.i).e * ((float) i)), this.m.getDuration() - this.m.timePassed());
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
        int scrollY = getScrollY();
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
                    measuredWidth += scrollY;
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
        max = (i6 - paddingTop) - paddingBottom;
        for (paddingBottom = 0; paddingBottom < childCount; paddingBottom++) {
            View childAt2 = getChildAt(paddingBottom);
            if (childAt2.getVisibility() != 8) {
                layoutParams = (LayoutParams) childAt2.getLayoutParams();
                if (!layoutParams.a) {
                    b a = a(childAt2);
                    if (a != null) {
                        i6 = ((int) (a.e * ((float) max))) + paddingTop;
                        if (layoutParams.d) {
                            layoutParams.d = false;
                            childAt2.measure(MeasureSpec.makeMeasureSpec((i5 - paddingLeft) - paddingRight, 1073741824), MeasureSpec.makeMeasureSpec((int) (layoutParams.c * ((float) max)), 1073741824));
                        }
                        childAt2.layout(paddingLeft, i6, childAt2.getMeasuredWidth() + paddingLeft, childAt2.getMeasuredHeight() + i6);
                    }
                }
            }
        }
        this.q = paddingLeft;
        this.r = i5 - paddingRight;
        this.V = i7;
        if (this.S) {
            a(this.i, false, 0, false);
        }
        this.S = false;
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
            if (!e(currY)) {
                this.m.abortAnimation();
                scrollTo(currX, 0);
            }
        }
        z.d(this);
    }

    private boolean e(int i) {
        if (this.e.size() == 0) {
            this.U = false;
            a(0, 0.0f, 0);
            if (this.U) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        b i2 = i();
        int clientHeight = getClientHeight();
        int i3 = this.o + clientHeight;
        float f = ((float) this.o) / ((float) clientHeight);
        int i4 = i2.b;
        float f2 = ((((float) i) / ((float) clientHeight)) - i2.e) / (i2.d + f);
        clientHeight = (int) (((float) i3) * f2);
        this.U = false;
        a(i4, f2, clientHeight);
        if (this.U) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    protected void a(int i, float f, int i2) {
        int paddingTop;
        int paddingBottom;
        int i3;
        if (this.V > 0) {
            int scrollY = getScrollY();
            paddingTop = getPaddingTop();
            paddingBottom = getPaddingBottom();
            int height = getHeight();
            int childCount = getChildCount();
            i3 = 0;
            while (i3 < childCount) {
                int i4;
                View childAt = getChildAt(i3);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.a) {
                    int max;
                    switch (layoutParams.b & 112) {
                        case 16:
                            max = Math.max((height - childAt.getMeasuredHeight()) / 2, paddingTop);
                            i4 = paddingBottom;
                            paddingBottom = paddingTop;
                            paddingTop = i4;
                            break;
                        case 48:
                            max = childAt.getHeight() + paddingTop;
                            i4 = paddingTop;
                            paddingTop = paddingBottom;
                            paddingBottom = max;
                            max = i4;
                            break;
                        case Opcodes.APUT_CHAR /*80*/:
                            max = (height - paddingBottom) - childAt.getMeasuredHeight();
                            i4 = paddingBottom + childAt.getMeasuredHeight();
                            paddingBottom = paddingTop;
                            paddingTop = i4;
                            break;
                        default:
                            max = paddingTop;
                            i4 = paddingBottom;
                            paddingBottom = paddingTop;
                            paddingTop = i4;
                            break;
                    }
                    max = (max + scrollY) - childAt.getTop();
                    if (max != 0) {
                        childAt.offsetTopAndBottom(max);
                    }
                } else {
                    i4 = paddingBottom;
                    paddingBottom = paddingTop;
                    paddingTop = i4;
                }
                i3++;
                i4 = paddingTop;
                paddingTop = paddingBottom;
                paddingBottom = i4;
            }
        }
        if (this.W != null) {
            this.W.onPageScrolled(i, f, i2);
        }
        if (this.aa != null) {
            this.aa.onPageScrolled(i, f, i2);
        }
        if (this.ac != null) {
            paddingBottom = getScrollY();
            i3 = getChildCount();
            for (paddingTop = 0; paddingTop < i3; paddingTop++) {
                View childAt2 = getChildAt(paddingTop);
                if (!((LayoutParams) childAt2.getLayoutParams()).a) {
                    this.ac.a(childAt2, ((float) (childAt2.getTop() - paddingBottom)) / ((float) getClientHeight()));
                }
            }
        }
        this.U = true;
    }

    private void a(boolean z) {
        int scrollX;
        boolean z2 = this.ai == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            this.m.abortAnimation();
            scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.m.getCurrX();
            int currY = this.m.getCurrY();
            if (!(scrollX == currX && scrollY == currY)) {
                scrollTo(currX, currY);
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
            z.a(this, this.ah);
        } else {
            this.ah.run();
        }
    }

    private boolean a(float f, float f2) {
        return (f < ((float) this.D) && f2 > 0.0f) || (f > ((float) (getHeight() - this.D)) && f2 < 0.0f);
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
            this.A = false;
            this.B = false;
            this.J = -1;
            if (this.K == null) {
                return false;
            }
            this.K.recycle();
            this.K = null;
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
                if (this.ai == 2 && Math.abs(this.m.getFinalY() - this.m.getCurrY()) > this.O) {
                    this.m.abortAnimation();
                    this.y = false;
                    e();
                    this.A = true;
                    c(true);
                    setScrollState(1);
                    break;
                }
                a(false);
                this.A = false;
                break;
                break;
            case 2:
                action = this.J;
                if (action != -1) {
                    action = n.a(motionEvent, action);
                    float d = n.d(motionEvent, action);
                    float f = d - this.G;
                    float abs = Math.abs(f);
                    float c = n.c(motionEvent, action);
                    float abs2 = Math.abs(c - this.H);
                    if (f == 0.0f || a(this.G, f) || !a(this, false, (int) f, (int) c, (int) d)) {
                        if (abs > ((float) this.E) && 0.5f * abs > abs2) {
                            this.A = true;
                            c(true);
                            setScrollState(1);
                            this.G = f > 0.0f ? this.I + ((float) this.E) : this.I - ((float) this.E);
                            this.F = c;
                            setScrollingCacheEnabled(true);
                        } else if (abs2 > ((float) this.E)) {
                            this.B = true;
                        }
                        if (this.A && b(d)) {
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
        int b;
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.m.abortAnimation();
                this.y = false;
                e();
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
                    b = (int) x.b(velocityTracker, this.J);
                    this.y = true;
                    int clientHeight = getClientHeight();
                    int scrollY = getScrollY();
                    b i = i();
                    a(a(i.b, ((((float) scrollY) / ((float) clientHeight)) - i.e) / i.d, b, (int) (n.d(motionEvent, n.a(motionEvent, this.J)) - this.I)), true, true, b);
                    this.J = -1;
                    j();
                    z = this.R.c() | this.Q.c();
                    break;
                }
                break;
            case 2:
                if (!this.A) {
                    b = n.a(motionEvent, this.J);
                    float d = n.d(motionEvent, b);
                    float abs = Math.abs(d - this.G);
                    float c = n.c(motionEvent, b);
                    x = Math.abs(c - this.F);
                    if (abs > ((float) this.E) && abs > x) {
                        this.A = true;
                        c(true);
                        if (d - this.I > 0.0f) {
                            x = this.I + ((float) this.E);
                        } else {
                            x = this.I - ((float) this.E);
                        }
                        this.G = x;
                        this.F = c;
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
                if (this.A) {
                    z = false | b(n.d(motionEvent, n.a(motionEvent, this.J)));
                    break;
                }
                break;
            case 3:
                if (this.A) {
                    a(this.i, true, 0, false);
                    this.J = -1;
                    j();
                    z = this.R.c() | this.Q.c();
                    break;
                }
                break;
            case 5:
                b = n.b(motionEvent);
                this.G = n.d(motionEvent, b);
                this.J = n.b(motionEvent, b);
                break;
            case 6:
                a(motionEvent);
                this.G = n.d(motionEvent, n.a(motionEvent, this.J));
                break;
        }
        if (z) {
            z.d(this);
        }
        return true;
    }

    private void c(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private boolean b(float f) {
        boolean z;
        float f2;
        boolean z2 = true;
        boolean z3 = false;
        float f3 = this.G - f;
        this.G = f;
        float scrollY = ((float) getScrollY()) + f3;
        int clientHeight = getClientHeight();
        float f4 = ((float) clientHeight) * this.s;
        float f5 = ((float) clientHeight) * this.t;
        b bVar = (b) this.e.get(0);
        b bVar2 = (b) this.e.get(this.e.size() - 1);
        if (bVar.b != 0) {
            f4 = bVar.e * ((float) clientHeight);
            z = false;
        } else {
            z = true;
        }
        if (bVar2.b != this.h.a() - 1) {
            f2 = bVar2.e * ((float) clientHeight);
            z2 = false;
        } else {
            f2 = f5;
        }
        if (scrollY < f4) {
            if (z) {
                z3 = this.Q.a(Math.abs(f4 - scrollY) / ((float) clientHeight));
            }
        } else if (scrollY > f2) {
            if (z2) {
                z3 = this.R.a(Math.abs(scrollY - f2) / ((float) clientHeight));
            }
            f4 = f2;
        } else {
            f4 = scrollY;
        }
        this.F += f4 - ((float) ((int) f4));
        scrollTo(getScrollX(), (int) f4);
        e((int) f4);
        return z3;
    }

    private b i() {
        float f;
        int clientHeight = getClientHeight();
        float scrollY = clientHeight > 0 ? ((float) getScrollY()) / ((float) clientHeight) : 0.0f;
        if (clientHeight > 0) {
            f = ((float) this.o) / ((float) clientHeight);
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
            if (obj == null && scrollY < f2) {
                return bVar;
            }
            if (scrollY < f3 || i3 == this.e.size() - 1) {
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
            if (!this.Q.a()) {
                a = canvas.save();
                height = getHeight();
                width = (getWidth() - getPaddingLeft()) - getPaddingRight();
                canvas.translate((float) getPaddingLeft(), this.s * ((float) height));
                this.Q.a(width, height);
                i = 0 | this.Q.a(canvas);
                canvas.restoreToCount(a);
            }
            if (!this.R.a()) {
                a = canvas.save();
                height = getHeight();
                width = (getWidth() - getPaddingLeft()) - getPaddingRight();
                canvas.rotate(180.0f);
                canvas.translate((float) ((-width) - getPaddingLeft()), (-(this.t + 1.0f)) * ((float) height));
                this.R.a(width, height);
                i |= this.R.a(canvas);
                canvas.restoreToCount(a);
            }
        } else {
            this.Q.b();
            this.R.b();
        }
        if (i != 0) {
            z.d(this);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.o > 0 && this.p != null && this.e.size() > 0 && this.h != null) {
            int scrollY = getScrollY();
            int height = getHeight();
            float f = ((float) this.o) / ((float) height);
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
                    f3 = (bVar.e + bVar.d) * ((float) height);
                    f2 = (bVar.e + bVar.d) + f;
                } else {
                    float b = this.h.b(i4);
                    f3 = (f2 + b) * ((float) height);
                    f2 += b + f;
                }
                if (((float) this.o) + f3 > ((float) scrollY)) {
                    this.p.setBounds(this.q, (int) f3, this.r, (int) ((((float) this.o) + f3) + 0.5f));
                    this.p.draw(canvas);
                }
                if (f3 <= ((float) (scrollY + height))) {
                    i4++;
                } else {
                    return;
                }
            }
        }
    }

    private void a(MotionEvent motionEvent) {
        int b = n.b(motionEvent);
        if (n.b(motionEvent, b) == this.J) {
            b = b == 0 ? 1 : 0;
            this.G = n.d(motionEvent, b);
            this.J = n.b(motionEvent, b);
            if (this.K != null) {
                this.K.clear();
            }
        }
    }

    private void j() {
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

    public boolean c(int i) {
        boolean z = true;
        if (this.h == null) {
            return false;
        }
        int clientHeight = getClientHeight();
        int scrollY = getScrollY();
        if (i < 0) {
            if (scrollY <= ((int) (((float) clientHeight) * this.s))) {
                z = false;
            }
            return z;
        } else if (i <= 0) {
            return false;
        } else {
            if (scrollY >= ((int) (((float) clientHeight) * this.t))) {
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
                if (i3 + scrollY >= childAt.getTop() && i3 + scrollY < childAt.getBottom() && i2 + scrollX >= childAt.getLeft() && i2 + scrollX < childAt.getRight()) {
                    if (a(childAt, true, i, (i2 + scrollX) - childAt.getLeft(), (i3 + scrollY) - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        if (z && z.b(view, -i)) {
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
                return d(17);
            case 22:
                return d(66);
            case 61:
                if (VERSION.SDK_INT < 11) {
                    return false;
                }
                if (android.support.v4.view.f.a(keyEvent)) {
                    return d(2);
                }
                if (android.support.v4.view.f.a(keyEvent, 1)) {
                    return d(1);
                }
                return false;
            default:
                return false;
        }
    }

    public boolean d(int i) {
        View view;
        boolean f;
        View findFocus = findFocus();
        if (findFocus == this) {
            view = null;
        } else {
            if (findFocus != null) {
                Object obj;
                for (VerticalViewPager parent = findFocus.getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
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
            if (i == 33 || i == 1) {
                f = f();
            } else {
                if (i == Opcodes.INT_TO_FLOAT || i == 2) {
                    f = g();
                }
                f = false;
            }
        } else if (i == 33) {
            f = (view == null || a(this.g, findNextFocus).top < a(this.g, view).top) ? findNextFocus.requestFocus() : f();
        } else {
            if (i == Opcodes.INT_TO_FLOAT) {
                f = (view == null || a(this.g, findNextFocus).bottom > a(this.g, view).bottom) ? findNextFocus.requestFocus() : g();
            }
            f = false;
        }
        if (f) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        }
        return f;
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
        VerticalViewPager parent = view.getParent();
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

    boolean f() {
        if (this.i <= 0) {
            return false;
        }
        setCurrentItem(this.i - 1, true);
        return true;
    }

    boolean g() {
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
