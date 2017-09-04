package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.v4.view.ak;
import android.support.v4.view.z;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import com.tencent.smtt.sdk.WebView;
import com.tencent.theme.SkinEngine;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.util.ArrayList;
import java.util.List;

public class DrawerLayout extends ViewGroup implements c {
    static final c a;
    private static final int[] b = new int[]{16842931};
    private static final boolean c = (VERSION.SDK_INT >= 19);
    private static final boolean d;
    private Drawable A;
    private CharSequence B;
    private CharSequence C;
    private Object D;
    private boolean E;
    private Drawable F;
    private Drawable G;
    private Drawable H;
    private Drawable I;
    private final ArrayList<View> J;
    private final b e;
    private float f;
    private int g;
    private int h;
    private float i;
    private Paint j;
    private final k k;
    private final k l;
    private final g m;
    private final g n;
    private int o;
    private boolean p;
    private boolean q;
    private int r;
    private int s;
    private boolean t;
    private boolean u;
    private f v;
    private float w;
    private float x;
    private Drawable y;
    private Drawable z;

    public static class LayoutParams extends MarginLayoutParams {
        public int a = 0;
        float b;
        boolean c;
        boolean d;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.b);
            this.a = obtainStyledAttributes.getInt(0, 0);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.a = layoutParams.a;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }

    protected static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public SavedState a(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] a(int i) {
                return new SavedState[i];
            }
        };
        int a = 0;
        int b = 0;
        int c = 0;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
        }
    }

    class a extends android.support.v4.view.a {
        final /* synthetic */ DrawerLayout b;
        private final Rect c = new Rect();

        a(DrawerLayout drawerLayout) {
            this.b = drawerLayout;
        }

        public void a(View view, android.support.v4.view.a.c cVar) {
            if (DrawerLayout.c) {
                super.a(view, cVar);
            } else {
                android.support.v4.view.a.c a = android.support.v4.view.a.c.a(cVar);
                super.a(view, a);
                cVar.a(view);
                ViewParent i = z.i(view);
                if (i instanceof View) {
                    cVar.c((View) i);
                }
                a(cVar, a);
                a.t();
                a(cVar, (ViewGroup) view);
            }
            cVar.b(DrawerLayout.class.getName());
            cVar.a(false);
            cVar.b(false);
            cVar.a(android.support.v4.view.a.c.a.a);
            cVar.a(android.support.v4.view.a.c.a.b);
        }

        public void d(View view, AccessibilityEvent accessibilityEvent) {
            super.d(view, accessibilityEvent);
            accessibilityEvent.setClassName(DrawerLayout.class.getName());
        }

        public boolean b(View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() != 32) {
                return super.b(view, accessibilityEvent);
            }
            List text = accessibilityEvent.getText();
            View a = this.b.k();
            if (a != null) {
                CharSequence a2 = this.b.a(this.b.e(a));
                if (a2 != null) {
                    text.add(a2);
                }
            }
            return true;
        }

        public boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (DrawerLayout.c || DrawerLayout.n(view)) {
                return super.a(viewGroup, view, accessibilityEvent);
            }
            return false;
        }

        private void a(android.support.v4.view.a.c cVar, ViewGroup viewGroup) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (DrawerLayout.n(childAt)) {
                    cVar.b(childAt);
                }
            }
        }

        private void a(android.support.v4.view.a.c cVar, android.support.v4.view.a.c cVar2) {
            Rect rect = this.c;
            cVar2.a(rect);
            cVar.b(rect);
            cVar2.c(rect);
            cVar.d(rect);
            cVar.c(cVar2.h());
            cVar.a(cVar2.p());
            cVar.b(cVar2.q());
            cVar.c(cVar2.s());
            cVar.h(cVar2.m());
            cVar.f(cVar2.k());
            cVar.a(cVar2.f());
            cVar.b(cVar2.g());
            cVar.d(cVar2.i());
            cVar.e(cVar2.j());
            cVar.g(cVar2.l());
            cVar.a(cVar2.b());
        }
    }

    final class b extends android.support.v4.view.a {
        final /* synthetic */ DrawerLayout b;

        b(DrawerLayout drawerLayout) {
            this.b = drawerLayout;
        }

        public void a(View view, android.support.v4.view.a.c cVar) {
            super.a(view, cVar);
            if (!DrawerLayout.n(view)) {
                cVar.c(null);
            }
        }
    }

    interface c {
        int a(Object obj);

        Drawable a(Context context);

        void a(View view);

        void a(View view, Object obj, int i);

        void a(MarginLayoutParams marginLayoutParams, Object obj, int i);
    }

    static class d implements c {
        d() {
        }

        public void a(View view) {
            b.a(view);
        }

        public void a(View view, Object obj, int i) {
            b.a(view, obj, i);
        }

        public void a(MarginLayoutParams marginLayoutParams, Object obj, int i) {
            b.a(marginLayoutParams, obj, i);
        }

        public int a(Object obj) {
            return b.a(obj);
        }

        public Drawable a(Context context) {
            return b.a(context);
        }
    }

    static class e implements c {
        e() {
        }

        public void a(View view) {
        }

        public void a(View view, Object obj, int i) {
        }

        public void a(MarginLayoutParams marginLayoutParams, Object obj, int i) {
        }

        public int a(Object obj) {
            return 0;
        }

        public Drawable a(Context context) {
            return null;
        }
    }

    public interface f {
        void a(int i);

        void a(View view);

        void a(View view, float f);

        void b(View view);
    }

    private class g extends android.support.v4.widget.k.a {
        final /* synthetic */ DrawerLayout a;
        private final int b;
        private k c;
        private final Runnable d = new Runnable(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.c();
            }
        };

        public g(DrawerLayout drawerLayout, int i) {
            this.a = drawerLayout;
            this.b = i;
        }

        public void a(k kVar) {
            this.c = kVar;
        }

        public void a() {
            this.a.removeCallbacks(this.d);
        }

        public boolean a(View view, int i) {
            return this.a.g(view) && this.a.a(view, this.b) && this.a.a(view) == 0;
        }

        public void a(int i) {
            this.a.a(this.b, i, this.c.c());
        }

        public void a(View view, int i, int i2, int i3, int i4) {
            float f;
            int width = view.getWidth();
            if (this.a.a(view, 3)) {
                f = ((float) (width + i)) / ((float) width);
            } else {
                f = ((float) (this.a.getWidth() - i)) / ((float) width);
            }
            this.a.b(view, f);
            view.setVisibility(f == 0.0f ? 4 : 0);
            this.a.invalidate();
        }

        public void b(View view, int i) {
            ((LayoutParams) view.getLayoutParams()).c = false;
            b();
        }

        private void b() {
            int i = 3;
            if (this.b == 3) {
                i = 5;
            }
            View b = this.a.b(i);
            if (b != null) {
                this.a.i(b);
            }
        }

        public void a(View view, float f, float f2) {
            int i;
            float d = this.a.d(view);
            int width = view.getWidth();
            if (this.a.a(view, 3)) {
                i = (f > 0.0f || (f == 0.0f && d > 0.5f)) ? 0 : -width;
            } else {
                i = this.a.getWidth();
                if (f < 0.0f || (f == 0.0f && d > 0.5f)) {
                    i -= width;
                }
            }
            this.c.a(i, view.getTop());
            this.a.invalidate();
        }

        public void a(int i, int i2) {
            this.a.postDelayed(this.d, 160);
        }

        private void c() {
            View view;
            int i;
            int i2 = 0;
            int b = this.c.b();
            boolean z = this.b == 3;
            if (z) {
                View b2 = this.a.b(3);
                if (b2 != null) {
                    i2 = -b2.getWidth();
                }
                i2 += b;
                view = b2;
                i = i2;
            } else {
                i2 = this.a.getWidth() - b;
                view = this.a.b(5);
                i = i2;
            }
            if (view == null) {
                return;
            }
            if (((z && view.getLeft() < i) || (!z && view.getLeft() > i)) && this.a.a(view) == 0) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                this.c.a(view, i, view.getTop());
                layoutParams.c = true;
                this.a.invalidate();
                b();
                this.a.c();
            }
        }

        public boolean b(int i) {
            return false;
        }

        public void b(int i, int i2) {
            View b;
            if ((i & 1) == 1) {
                b = this.a.b(3);
            } else {
                b = this.a.b(5);
            }
            if (b != null && this.a.a(b) == 0) {
                this.c.a(b, i2);
            }
        }

        public int a(View view) {
            return this.a.g(view) ? view.getWidth() : 0;
        }

        public int a(View view, int i, int i2) {
            if (this.a.a(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i, 0));
            }
            int width = this.a.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i, width));
        }

        public int b(View view, int i, int i2) {
            return view.getTop();
        }
    }

    static {
        boolean z = true;
        if (VERSION.SDK_INT < 21) {
            z = false;
        }
        d = z;
        if (VERSION.SDK_INT >= 21) {
            a = new d();
        } else {
            a = new e();
        }
    }

    public DrawerLayout(Context context) {
        this(context, null);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new b(this);
        this.h = -1728053248;
        this.j = new Paint();
        this.q = true;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        setDescendantFocusability(262144);
        float f = getResources().getDisplayMetrics().density;
        this.g = (int) ((64.0f * f) + 0.5f);
        float f2 = 400.0f * f;
        this.m = new g(this, 3);
        this.n = new g(this, 5);
        this.k = k.a((ViewGroup) this, 1.0f, this.m);
        this.k.a(1);
        this.k.a(f2);
        this.m.a(this.k);
        this.l = k.a((ViewGroup) this, 1.0f, this.n);
        this.l.a(2);
        this.l.a(f2);
        this.n.a(this.l);
        setFocusableInTouchMode(true);
        z.c((View) this, 1);
        z.a((View) this, new a(this));
        ak.a(this, false);
        if (z.s(this)) {
            a.a((View) this);
            this.y = a.a(context);
        }
        this.f = f * 10.0f;
        this.J = new ArrayList();
    }

    public void setDrawerElevation(float f) {
        this.f = f;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (g(childAt)) {
                z.h(childAt, this.f);
            }
        }
    }

    public float getDrawerElevation() {
        if (d) {
            return this.f;
        }
        return 0.0f;
    }

    public void setChildInsets(Object obj, boolean z) {
        this.D = obj;
        this.E = z;
        boolean z2 = !z && getBackground() == null;
        setWillNotDraw(z2);
        requestLayout();
    }

    public void setDrawerShadow(Drawable drawable, int i) {
        if (!d) {
            if ((i & 8388611) == 8388611) {
                this.F = drawable;
            } else if ((i & 8388613) == 8388613) {
                this.G = drawable;
            } else if ((i & 3) == 3) {
                this.H = drawable;
            } else if ((i & 5) == 5) {
                this.I = drawable;
            } else {
                return;
            }
            f();
            invalidate();
        }
    }

    public void setDrawerShadow(int i, int i2) {
        setDrawerShadow(getResources().getDrawable(i), i2);
    }

    public void setScrimColor(int i) {
        this.h = i;
        invalidate();
    }

    public void setDrawerListener(f fVar) {
        this.v = fVar;
    }

    public void setDrawerLockMode(int i) {
        setDrawerLockMode(i, 3);
        setDrawerLockMode(i, 5);
    }

    public void setDrawerLockMode(int i, int i2) {
        int a = android.support.v4.view.d.a(i2, z.h(this));
        if (a == 3) {
            this.r = i;
        } else if (a == 5) {
            this.s = i;
        }
        if (i != 0) {
            (a == 3 ? this.k : this.l).e();
        }
        View b;
        switch (i) {
            case 1:
                b = b(a);
                if (b != null) {
                    i(b);
                    return;
                }
                return;
            case 2:
                b = b(a);
                if (b != null) {
                    h(b);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void setDrawerLockMode(int i, View view) {
        if (g(view)) {
            setDrawerLockMode(i, ((LayoutParams) view.getLayoutParams()).a);
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a " + "drawer with appropriate layout_gravity");
    }

    public int a(View view) {
        int e = e(view);
        if (e == 3) {
            return this.r;
        }
        if (e == 5) {
            return this.s;
        }
        return 0;
    }

    public void setDrawerTitle(int i, CharSequence charSequence) {
        int a = android.support.v4.view.d.a(i, z.h(this));
        if (a == 3) {
            this.B = charSequence;
        } else if (a == 5) {
            this.C = charSequence;
        }
    }

    public CharSequence a(int i) {
        int a = android.support.v4.view.d.a(i, z.h(this));
        if (a == 3) {
            return this.B;
        }
        if (a == 5) {
            return this.C;
        }
        return null;
    }

    void a(int i, int i2, View view) {
        int i3 = 1;
        int a = this.k.a();
        int a2 = this.l.a();
        if (!(a == 1 || a2 == 1)) {
            i3 = (a == 2 || a2 == 2) ? 2 : 0;
        }
        if (view != null && i2 == 0) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (layoutParams.b == 0.0f) {
                b(view);
            } else if (layoutParams.b == 1.0f) {
                c(view);
            }
        }
        if (i3 != this.o) {
            this.o = i3;
            if (this.v != null) {
                this.v.a(i3);
            }
        }
    }

    void b(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams.d) {
            layoutParams.d = false;
            if (this.v != null) {
                this.v.b(view);
            }
            a(view, false);
            if (hasWindowFocus()) {
                View rootView = getRootView();
                if (rootView != null) {
                    rootView.sendAccessibilityEvent(32);
                }
            }
        }
    }

    void c(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.d) {
            layoutParams.d = true;
            if (this.v != null) {
                this.v.a(view);
            }
            a(view, true);
            if (hasWindowFocus()) {
                sendAccessibilityEvent(32);
            }
            view.requestFocus();
        }
    }

    private void a(View view, boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((z || g(childAt)) && !(z && childAt == view)) {
                z.c(childAt, 4);
            } else {
                z.c(childAt, 1);
            }
        }
    }

    void a(View view, float f) {
        if (this.v != null) {
            this.v.a(view, f);
        }
    }

    void b(View view, float f) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (f != layoutParams.b) {
            layoutParams.b = f;
            a(view, f);
        }
    }

    float d(View view) {
        return ((LayoutParams) view.getLayoutParams()).b;
    }

    int e(View view) {
        return android.support.v4.view.d.a(((LayoutParams) view.getLayoutParams()).a, z.h(this));
    }

    boolean a(View view, int i) {
        return (e(view) & i) == i;
    }

    View a() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (((LayoutParams) childAt.getLayoutParams()).d) {
                return childAt;
            }
        }
        return null;
    }

    View b(int i) {
        int a = android.support.v4.view.d.a(i, z.h(this)) & 7;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((e(childAt) & 7) == a) {
                return childAt;
            }
        }
        return null;
    }

    static String c(int i) {
        if ((i & 3) == 3) {
            return "LEFT";
        }
        if ((i & 5) == 5) {
            return "RIGHT";
        }
        return Integer.toHexString(i);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.q = true;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.q = true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onMeasure(int r14, int r15) {
        /*
        r13 = this;
        r1 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        r7 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r4 = 0;
        r12 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r3 = android.view.View.MeasureSpec.getMode(r14);
        r5 = android.view.View.MeasureSpec.getMode(r15);
        r2 = android.view.View.MeasureSpec.getSize(r14);
        r0 = android.view.View.MeasureSpec.getSize(r15);
        if (r3 != r12) goto L_0x001b;
    L_0x0019:
        if (r5 == r12) goto L_0x0056;
    L_0x001b:
        r6 = r13.isInEditMode();
        if (r6 == 0) goto L_0x0058;
    L_0x0021:
        if (r3 != r7) goto L_0x0050;
    L_0x0023:
        if (r5 != r7) goto L_0x0054;
    L_0x0025:
        r1 = r0;
    L_0x0026:
        r13.setMeasuredDimension(r2, r1);
        r0 = r13.D;
        if (r0 == 0) goto L_0x0061;
    L_0x002d:
        r0 = android.support.v4.view.z.s(r13);
        if (r0 == 0) goto L_0x0061;
    L_0x0033:
        r0 = 1;
        r3 = r0;
    L_0x0035:
        r6 = android.support.v4.view.z.h(r13);
        r7 = r13.getChildCount();
        r5 = r4;
    L_0x003e:
        if (r5 >= r7) goto L_0x0155;
    L_0x0040:
        r8 = r13.getChildAt(r5);
        r0 = r8.getVisibility();
        r9 = 8;
        if (r0 != r9) goto L_0x0063;
    L_0x004c:
        r0 = r5 + 1;
        r5 = r0;
        goto L_0x003e;
    L_0x0050:
        if (r3 != 0) goto L_0x0023;
    L_0x0052:
        r2 = r1;
        goto L_0x0023;
    L_0x0054:
        if (r5 == 0) goto L_0x0026;
    L_0x0056:
        r1 = r0;
        goto L_0x0026;
    L_0x0058:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "DrawerLayout must be measured with MeasureSpec.EXACTLY.";
        r0.<init>(r1);
        throw r0;
    L_0x0061:
        r3 = r4;
        goto L_0x0035;
    L_0x0063:
        r0 = r8.getLayoutParams();
        r0 = (android.support.v4.widget.DrawerLayout.LayoutParams) r0;
        if (r3 == 0) goto L_0x007e;
    L_0x006b:
        r9 = r0.a;
        r9 = android.support.v4.view.d.a(r9, r6);
        r10 = android.support.v4.view.z.s(r8);
        if (r10 == 0) goto L_0x009f;
    L_0x0077:
        r10 = a;
        r11 = r13.D;
        r10.a(r8, r11, r9);
    L_0x007e:
        r9 = r13.f(r8);
        if (r9 == 0) goto L_0x00a7;
    L_0x0084:
        r9 = r0.leftMargin;
        r9 = r2 - r9;
        r10 = r0.rightMargin;
        r9 = r9 - r10;
        r9 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r12);
        r10 = r0.topMargin;
        r10 = r1 - r10;
        r0 = r0.bottomMargin;
        r0 = r10 - r0;
        r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r12);
        r8.measure(r9, r0);
        goto L_0x004c;
    L_0x009f:
        r10 = a;
        r11 = r13.D;
        r10.a(r0, r11, r9);
        goto L_0x007e;
    L_0x00a7:
        r9 = r13.g(r8);
        if (r9 == 0) goto L_0x0122;
    L_0x00ad:
        r9 = d;
        if (r9 == 0) goto L_0x00c0;
    L_0x00b1:
        r9 = android.support.v4.view.z.r(r8);
        r10 = r13.f;
        r9 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1));
        if (r9 == 0) goto L_0x00c0;
    L_0x00bb:
        r9 = r13.f;
        android.support.v4.view.z.h(r8, r9);
    L_0x00c0:
        r9 = r13.e(r8);
        r9 = r9 & 7;
        r10 = r4 & r9;
        if (r10 == 0) goto L_0x0104;
    L_0x00ca:
        r0 = new java.lang.IllegalStateException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Child drawer has absolute gravity ";
        r1 = r1.append(r2);
        r2 = c(r9);
        r1 = r1.append(r2);
        r2 = " but this ";
        r1 = r1.append(r2);
        r2 = "DrawerLayout";
        r1 = r1.append(r2);
        r2 = " already has a ";
        r1 = r1.append(r2);
        r2 = "drawer view along that edge";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0104:
        r9 = r13.g;
        r10 = r0.leftMargin;
        r9 = r9 + r10;
        r10 = r0.rightMargin;
        r9 = r9 + r10;
        r10 = r0.width;
        r9 = getChildMeasureSpec(r14, r9, r10);
        r10 = r0.topMargin;
        r11 = r0.bottomMargin;
        r10 = r10 + r11;
        r0 = r0.height;
        r0 = getChildMeasureSpec(r15, r10, r0);
        r8.measure(r9, r0);
        goto L_0x004c;
    L_0x0122:
        r0 = new java.lang.IllegalStateException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Child ";
        r1 = r1.append(r2);
        r1 = r1.append(r8);
        r2 = " at index ";
        r1 = r1.append(r2);
        r1 = r1.append(r5);
        r2 = " does not have a valid layout_gravity - must be Gravity.LEFT, ";
        r1 = r1.append(r2);
        r2 = "Gravity.RIGHT or Gravity.NO_GRAVITY";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0155:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.DrawerLayout.onMeasure(int, int):void");
    }

    private void f() {
        if (!d) {
            this.z = g();
            this.A = h();
        }
    }

    private Drawable g() {
        int h = z.h(this);
        if (h == 0) {
            if (this.F != null) {
                a(this.F, h);
                return this.F;
            }
        } else if (this.G != null) {
            a(this.G, h);
            return this.G;
        }
        return this.H;
    }

    private Drawable h() {
        int h = z.h(this);
        if (h == 0) {
            if (this.G != null) {
                a(this.G, h);
                return this.G;
            }
        } else if (this.F != null) {
            a(this.F, h);
            return this.F;
        }
        return this.I;
    }

    private boolean a(Drawable drawable, int i) {
        if (drawable == null || !android.support.v4.b.a.a.a(drawable)) {
            return false;
        }
        android.support.v4.b.a.a.a(drawable, i);
        return true;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.p = true;
        int i5 = i3 - i;
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (f(childAt)) {
                    childAt.layout(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + childAt.getMeasuredWidth(), layoutParams.topMargin + childAt.getMeasuredHeight());
                } else {
                    int i7;
                    float f;
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (a(childAt, 3)) {
                        i7 = ((int) (((float) measuredWidth) * layoutParams.b)) + (-measuredWidth);
                        f = ((float) (measuredWidth + i7)) / ((float) measuredWidth);
                    } else {
                        i7 = i5 - ((int) (((float) measuredWidth) * layoutParams.b));
                        f = ((float) (i5 - i7)) / ((float) measuredWidth);
                    }
                    Object obj = f != layoutParams.b ? 1 : null;
                    int i8;
                    switch (layoutParams.a & 112) {
                        case 16:
                            int i9 = i4 - i2;
                            i8 = (i9 - measuredHeight) / 2;
                            if (i8 < layoutParams.topMargin) {
                                i8 = layoutParams.topMargin;
                            } else if (i8 + measuredHeight > i9 - layoutParams.bottomMargin) {
                                i8 = (i9 - layoutParams.bottomMargin) - measuredHeight;
                            }
                            childAt.layout(i7, i8, measuredWidth + i7, measuredHeight + i8);
                            break;
                        case Opcodes.APUT_CHAR /*80*/:
                            i8 = i4 - i2;
                            childAt.layout(i7, (i8 - layoutParams.bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i7, i8 - layoutParams.bottomMargin);
                            break;
                        default:
                            childAt.layout(i7, layoutParams.topMargin, measuredWidth + i7, measuredHeight + layoutParams.topMargin);
                            break;
                    }
                    if (obj != null) {
                        b(childAt, f);
                    }
                    int i10 = layoutParams.b > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i10) {
                        childAt.setVisibility(i10);
                    }
                }
            }
        }
        this.p = false;
        this.q = false;
    }

    public void requestLayout() {
        if (!this.p) {
            super.requestLayout();
        }
    }

    public void computeScroll() {
        int childCount = getChildCount();
        float f = 0.0f;
        for (int i = 0; i < childCount; i++) {
            f = Math.max(f, ((LayoutParams) getChildAt(i).getLayoutParams()).b);
        }
        this.i = f;
        if ((this.k.a(true) | this.l.a(true)) != 0) {
            z.d(this);
        }
    }

    private static boolean m(View view) {
        Drawable background = view.getBackground();
        if (background == null || background.getOpacity() != -1) {
            return false;
        }
        return true;
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.y = drawable;
        invalidate();
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.y;
    }

    public void setStatusBarBackground(int i) {
        this.y = i != 0 ? android.support.v4.content.a.a(getContext(), i) : null;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i) {
        this.y = new ColorDrawable(i);
        invalidate();
    }

    public void onRtlPropertiesChanged(int i) {
        f();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.E && this.y != null) {
            int a = a.a(this.D);
            if (a > 0) {
                this.y.setBounds(0, 0, getWidth(), a);
                this.y.draw(canvas);
            }
        }
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        int i;
        int height = getHeight();
        boolean f = f(view);
        int i2 = 0;
        int width = getWidth();
        int save = canvas.save();
        if (f) {
            int childCount = getChildCount();
            int i3 = 0;
            while (i3 < childCount) {
                View childAt = getChildAt(i3);
                if (childAt != view && childAt.getVisibility() == 0 && m(childAt) && g(childAt)) {
                    if (childAt.getHeight() < height) {
                        i = width;
                    } else if (a(childAt, 3)) {
                        i = childAt.getRight();
                        if (i <= i2) {
                            i = i2;
                        }
                        i2 = i;
                        i = width;
                    } else {
                        i = childAt.getLeft();
                        if (i < width) {
                        }
                    }
                    i3++;
                    width = i;
                }
                i = width;
                i3++;
                width = i;
            }
            canvas.clipRect(i2, 0, width, getHeight());
        }
        i = width;
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(save);
        if (this.i > 0.0f && f) {
            this.j.setColor((((int) (((float) ((this.h & WebView.NIGHT_MODE_COLOR) >>> 24)) * this.i)) << 24) | (this.h & SkinEngine.TYPE_FILE));
            canvas.drawRect((float) i2, 0.0f, (float) i, (float) getHeight(), this.j);
        } else if (this.z != null && a(view, 3)) {
            i = this.z.getIntrinsicWidth();
            i2 = view.getRight();
            r2 = Math.max(0.0f, Math.min(((float) i2) / ((float) this.k.b()), 1.0f));
            this.z.setBounds(i2, view.getTop(), i + i2, view.getBottom());
            this.z.setAlpha((int) (255.0f * r2));
            this.z.draw(canvas);
        } else if (this.A != null && a(view, 5)) {
            i = this.A.getIntrinsicWidth();
            i2 = view.getLeft();
            r2 = Math.max(0.0f, Math.min(((float) (getWidth() - i2)) / ((float) this.l.b()), 1.0f));
            this.A.setBounds(i2 - i, view.getTop(), i2, view.getBottom());
            this.A.setAlpha((int) (255.0f * r2));
            this.A.draw(canvas);
        }
        return drawChild;
    }

    boolean f(View view) {
        return ((LayoutParams) view.getLayoutParams()).a == 0;
    }

    boolean g(View view) {
        return (android.support.v4.view.d.a(((LayoutParams) view.getLayoutParams()).a, z.h(view)) & 7) != 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r8) {
        /*
        r7 = this;
        r1 = 1;
        r2 = 0;
        r0 = android.support.v4.view.n.a(r8);
        r3 = r7.k;
        r3 = r3.a(r8);
        r4 = r7.l;
        r4 = r4.a(r8);
        r3 = r3 | r4;
        switch(r0) {
            case 0: goto L_0x0027;
            case 1: goto L_0x0065;
            case 2: goto L_0x0050;
            case 3: goto L_0x0065;
            default: goto L_0x0016;
        };
    L_0x0016:
        r0 = r2;
    L_0x0017:
        if (r3 != 0) goto L_0x0025;
    L_0x0019:
        if (r0 != 0) goto L_0x0025;
    L_0x001b:
        r0 = r7.i();
        if (r0 != 0) goto L_0x0025;
    L_0x0021:
        r0 = r7.u;
        if (r0 == 0) goto L_0x0026;
    L_0x0025:
        r2 = r1;
    L_0x0026:
        return r2;
    L_0x0027:
        r0 = r8.getX();
        r4 = r8.getY();
        r7.w = r0;
        r7.x = r4;
        r5 = r7.i;
        r6 = 0;
        r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1));
        if (r5 <= 0) goto L_0x006d;
    L_0x003a:
        r5 = r7.k;
        r0 = (int) r0;
        r4 = (int) r4;
        r0 = r5.d(r0, r4);
        if (r0 == 0) goto L_0x006d;
    L_0x0044:
        r0 = r7.f(r0);
        if (r0 == 0) goto L_0x006d;
    L_0x004a:
        r0 = r1;
    L_0x004b:
        r7.t = r2;
        r7.u = r2;
        goto L_0x0017;
    L_0x0050:
        r0 = r7.k;
        r4 = 3;
        r0 = r0.d(r4);
        if (r0 == 0) goto L_0x0016;
    L_0x0059:
        r0 = r7.m;
        r0.a();
        r0 = r7.n;
        r0.a();
        r0 = r2;
        goto L_0x0017;
    L_0x0065:
        r7.a(r1);
        r7.t = r2;
        r7.u = r2;
        goto L_0x0016;
    L_0x006d:
        r0 = r2;
        goto L_0x004b;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.DrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.k.b(motionEvent);
        this.l.b(motionEvent);
        float x;
        float y;
        switch (motionEvent.getAction() & 255) {
            case 0:
                x = motionEvent.getX();
                y = motionEvent.getY();
                this.w = x;
                this.x = y;
                this.t = false;
                this.u = false;
                break;
            case 1:
                boolean z;
                x = motionEvent.getX();
                y = motionEvent.getY();
                View d = this.k.d((int) x, (int) y);
                if (d != null && f(d)) {
                    x -= this.w;
                    y -= this.x;
                    int d2 = this.k.d();
                    if ((x * x) + (y * y) < ((float) (d2 * d2))) {
                        View a = a();
                        if (a != null) {
                            z = a(a) == 2;
                            a(z);
                            this.t = false;
                            break;
                        }
                    }
                }
                z = true;
                a(z);
                this.t = false;
            case 3:
                a(true);
                this.t = false;
                this.u = false;
                break;
        }
        return true;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        this.t = z;
        if (z) {
            a(true);
        }
    }

    public void b() {
        a(false);
    }

    void a(boolean z) {
        int childCount = getChildCount();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (g(childAt) && (!z || layoutParams.c)) {
                int width = childAt.getWidth();
                if (a(childAt, 3)) {
                    i |= this.k.a(childAt, -width, childAt.getTop());
                } else {
                    i |= this.l.a(childAt, getWidth(), childAt.getTop());
                }
                layoutParams.c = false;
            }
        }
        this.m.a();
        this.n.a();
        if (i != 0) {
            invalidate();
        }
    }

    public void h(View view) {
        if (g(view)) {
            if (this.q) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                layoutParams.b = 1.0f;
                layoutParams.d = true;
                a(view, true);
            } else if (a(view, 3)) {
                this.k.a(view, 0, view.getTop());
            } else {
                this.l.a(view, getWidth() - view.getWidth(), view.getTop());
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    public void d(int i) {
        View b = b(i);
        if (b == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + c(i));
        }
        h(b);
    }

    public void i(View view) {
        if (g(view)) {
            if (this.q) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                layoutParams.b = 0.0f;
                layoutParams.d = false;
            } else if (a(view, 3)) {
                this.k.a(view, -view.getWidth(), view.getTop());
            } else {
                this.l.a(view, getWidth(), view.getTop());
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    public boolean j(View view) {
        if (g(view)) {
            return ((LayoutParams) view.getLayoutParams()).d;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public boolean e(int i) {
        View b = b(i);
        if (b != null) {
            return j(b);
        }
        return false;
    }

    public boolean k(View view) {
        if (g(view)) {
            return ((LayoutParams) view.getLayoutParams()).b > 0.0f;
        } else {
            throw new IllegalArgumentException("View " + view + " is not a drawer");
        }
    }

    private boolean i() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (((LayoutParams) getChildAt(i).getLayoutParams()).c) {
                return true;
            }
        }
        return false;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        return layoutParams instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        int i3 = 0;
        if (getDescendantFocusability() != 393216) {
            int i4;
            int childCount = getChildCount();
            int i5 = 0;
            for (i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (!g(childAt)) {
                    this.J.add(childAt);
                } else if (j(childAt)) {
                    i5 = 1;
                    childAt.addFocusables(arrayList, i, i2);
                }
            }
            if (i5 == 0) {
                i4 = this.J.size();
                while (i3 < i4) {
                    View view = (View) this.J.get(i3);
                    if (view.getVisibility() == 0) {
                        view.addFocusables(arrayList, i, i2);
                    }
                    i3++;
                }
            }
            this.J.clear();
        }
    }

    private boolean j() {
        return k() != null;
    }

    private View k() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (g(childAt) && k(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    void c() {
        int i = 0;
        if (!this.u) {
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            int childCount = getChildCount();
            while (i < childCount) {
                getChildAt(i).dispatchTouchEvent(obtain);
                i++;
            }
            obtain.recycle();
            this.u = true;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !j()) {
            return super.onKeyDown(i, keyEvent);
        }
        android.support.v4.view.f.b(keyEvent);
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        View k = k();
        if (k != null && a(k) == 0) {
            b();
        }
        return k != null;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.a != 0) {
            View b = b(savedState.a);
            if (b != null) {
                h(b);
            }
        }
        setDrawerLockMode(savedState.b, 3);
        setDrawerLockMode(savedState.c, 5);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        View a = a();
        if (a != null) {
            savedState.a = ((LayoutParams) a.getLayoutParams()).a;
        }
        savedState.b = this.r;
        savedState.c = this.s;
        return savedState;
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (a() != null || g(view)) {
            z.c(view, 4);
        } else {
            z.c(view, 1);
        }
        if (!c) {
            z.a(view, this.e);
        }
    }

    private static boolean n(View view) {
        return (z.e(view) == 4 || z.e(view) == 2) ? false : true;
    }
}
