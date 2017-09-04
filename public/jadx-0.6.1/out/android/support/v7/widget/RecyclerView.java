package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.v4.view.ai;
import android.support.v4.view.w;
import android.support.v4.view.x;
import android.support.v4.view.z;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import com.pay.http.APPluginErrorCode;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerView extends ViewGroup implements android.support.v4.view.r, w {
    private static final Interpolator ao = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
        }
    };
    private static final boolean i;
    private static final Class<?>[] j = new Class[]{Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE};
    private boolean A;
    private boolean B;
    private int C;
    private boolean D;
    private final boolean E;
    private final AccessibilityManager F;
    private List<h> G;
    private boolean H;
    private int I;
    private android.support.v4.widget.d J;
    private android.support.v4.widget.d K;
    private android.support.v4.widget.d L;
    private android.support.v4.widget.d M;
    private int N;
    private int O;
    private VelocityTracker P;
    private int Q;
    private int R;
    private int S;
    private int T;
    private int U;
    private final int V;
    private final int W;
    final l a;
    private float aa;
    private final r ab;
    private j ac;
    private List<j> ad;
    private b ae;
    private boolean af;
    private g ag;
    private d ah;
    private final int[] ai;
    private final android.support.v4.view.s aj;
    private final int[] ak;
    private final int[] al;
    private final int[] am;
    private Runnable an;
    private final b ap;
    a b;
    b c;
    final j d;
    e e;
    final p f;
    boolean g;
    boolean h;
    private final n k;
    private SavedState l;
    private boolean m;
    private final Runnable n;
    private final Rect o;
    private a p;
    private LayoutManager q;
    private m r;
    private final ArrayList<g> s;
    private final ArrayList<i> t;
    private i u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    public static class LayoutParams extends MarginLayoutParams {
        s a;
        final Rect b = new Rect();
        boolean c = true;
        boolean d = false;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
        }

        public boolean c() {
            return this.a.q();
        }

        public boolean d() {
            return this.a.x();
        }

        public int e() {
            return this.a.d();
        }
    }

    public static abstract class LayoutManager {
        private boolean a = false;
        private boolean b = false;
        b q;
        RecyclerView r;
        o s;

        public static class Properties {
        }

        public abstract LayoutParams a();

        void b(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.r = null;
                this.q = null;
                return;
            }
            this.r = recyclerView;
            this.q = recyclerView.c;
        }

        public void l() {
            if (this.r != null) {
                this.r.requestLayout();
            }
        }

        public void a(String str) {
            if (this.r != null) {
                this.r.a(str);
            }
        }

        public boolean b() {
            return false;
        }

        void c(RecyclerView recyclerView) {
            this.b = true;
            d(recyclerView);
        }

        void b(RecyclerView recyclerView, l lVar) {
            this.b = false;
            a(recyclerView, lVar);
        }

        public boolean m() {
            return this.b;
        }

        public void a(Runnable runnable) {
            if (this.r != null) {
                z.a(this.r, runnable);
            }
        }

        public boolean b(Runnable runnable) {
            if (this.r != null) {
                return this.r.removeCallbacks(runnable);
            }
            return false;
        }

        public void d(RecyclerView recyclerView) {
        }

        @Deprecated
        public void e(RecyclerView recyclerView) {
        }

        public void a(RecyclerView recyclerView, l lVar) {
            e(recyclerView);
        }

        public boolean n() {
            return this.r != null && this.r.m;
        }

        public void c(l lVar, p pVar) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public boolean a(LayoutParams layoutParams) {
            return layoutParams != null;
        }

        public LayoutParams a(android.view.ViewGroup.LayoutParams layoutParams) {
            if (layoutParams instanceof LayoutParams) {
                return new LayoutParams((LayoutParams) layoutParams);
            }
            if (layoutParams instanceof MarginLayoutParams) {
                return new LayoutParams((MarginLayoutParams) layoutParams);
            }
            return new LayoutParams(layoutParams);
        }

        public LayoutParams a(Context context, AttributeSet attributeSet) {
            return new LayoutParams(context, attributeSet);
        }

        public int a(int i, l lVar, p pVar) {
            return 0;
        }

        public int b(int i, l lVar, p pVar) {
            return 0;
        }

        public boolean d() {
            return false;
        }

        public boolean e() {
            return false;
        }

        public void c(int i) {
        }

        public boolean o() {
            return this.s != null && this.s.c();
        }

        public int p() {
            return z.h(this.r);
        }

        public void a(View view) {
            a(view, -1);
        }

        public void a(View view, int i) {
            a(view, i, true);
        }

        public void b(View view) {
            b(view, -1);
        }

        public void b(View view, int i) {
            a(view, i, false);
        }

        private void a(View view, int i, boolean z) {
            s b = RecyclerView.b(view);
            if (z || b.q()) {
                this.r.d.c(b);
            } else {
                this.r.d.d(b);
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (b.k() || b.i()) {
                if (b.i()) {
                    b.j();
                } else {
                    b.l();
                }
                this.q.a(view, i, view.getLayoutParams(), false);
            } else if (view.getParent() == this.r) {
                int b2 = this.q.b(view);
                if (i == -1) {
                    i = this.q.b();
                }
                if (b2 == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.r.indexOfChild(view));
                } else if (b2 != i) {
                    this.r.q.a(b2, i);
                }
            } else {
                this.q.a(view, i, false);
                layoutParams.c = true;
                if (this.s != null && this.s.c()) {
                    this.s.b(view);
                }
            }
            if (layoutParams.d) {
                b.a.invalidate();
                layoutParams.d = false;
            }
        }

        public void c(View view) {
            this.q.a(view);
        }

        public void d(int i) {
            if (f(i) != null) {
                this.q.a(i);
            }
        }

        public int q() {
            return -1;
        }

        public int d(View view) {
            return ((LayoutParams) view.getLayoutParams()).e();
        }

        public View b(int i) {
            int r = r();
            for (int i2 = 0; i2 < r; i2++) {
                View f = f(i2);
                s b = RecyclerView.b(f);
                if (b != null && b.d() == i && !b.c() && (this.r.f.a() || !b.q())) {
                    return f;
                }
            }
            return null;
        }

        public void e(int i) {
            a(i, f(i));
        }

        private void a(int i, View view) {
            this.q.d(i);
        }

        public void a(View view, int i, LayoutParams layoutParams) {
            s b = RecyclerView.b(view);
            if (b.q()) {
                this.r.d.c(b);
            } else {
                this.r.d.d(b);
            }
            this.q.a(view, i, layoutParams, b.q());
        }

        public void c(View view, int i) {
            a(view, i, (LayoutParams) view.getLayoutParams());
        }

        public void a(int i, int i2) {
            View f = f(i);
            if (f == null) {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i);
            }
            e(i);
            c(f, i2);
        }

        public void a(View view, l lVar) {
            c(view);
            lVar.a(view);
        }

        public void a(int i, l lVar) {
            View f = f(i);
            d(i);
            lVar.a(f);
        }

        public int r() {
            return this.q != null ? this.q.b() : 0;
        }

        public View f(int i) {
            return this.q != null ? this.q.b(i) : null;
        }

        public int s() {
            return this.r != null ? this.r.getWidth() : 0;
        }

        public int t() {
            return this.r != null ? this.r.getHeight() : 0;
        }

        public int u() {
            return this.r != null ? this.r.getPaddingLeft() : 0;
        }

        public int v() {
            return this.r != null ? this.r.getPaddingTop() : 0;
        }

        public int w() {
            return this.r != null ? this.r.getPaddingRight() : 0;
        }

        public int x() {
            return this.r != null ? this.r.getPaddingBottom() : 0;
        }

        public View y() {
            if (this.r == null) {
                return null;
            }
            View focusedChild = this.r.getFocusedChild();
            if (focusedChild == null || this.q.c(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public void g(int i) {
            if (this.r != null) {
                this.r.b(i);
            }
        }

        public void h(int i) {
            if (this.r != null) {
                this.r.a(i);
            }
        }

        public void a(l lVar) {
            for (int r = r() - 1; r >= 0; r--) {
                a(lVar, r, f(r));
            }
        }

        private void a(l lVar, int i, View view) {
            s b = RecyclerView.b(view);
            if (!b.c()) {
                if (!b.n() || b.q() || this.r.p.b()) {
                    e(i);
                    lVar.c(view);
                    return;
                }
                d(i);
                lVar.b(b);
            }
        }

        void b(l lVar) {
            int d = lVar.d();
            for (int i = d - 1; i >= 0; i--) {
                View e = lVar.e(i);
                s b = RecyclerView.b(e);
                if (!b.c()) {
                    b.a(false);
                    if (b.r()) {
                        this.r.removeDetachedView(e, false);
                    }
                    if (this.r.e != null) {
                        this.r.e.c(b);
                    }
                    b.a(true);
                    lVar.b(e);
                }
            }
            lVar.e();
            if (d > 0) {
                this.r.invalidate();
            }
        }

        public void a(View view, int i, int i2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect f = this.r.f(view);
            view.measure(a(s(), ((f.left + f.right) + i) + (((u() + w()) + layoutParams.leftMargin) + layoutParams.rightMargin), layoutParams.width, d()), a(t(), ((f.bottom + f.top) + i2) + (((v() + x()) + layoutParams.topMargin) + layoutParams.bottomMargin), layoutParams.height, e()));
        }

        public static int a(int i, int i2, int i3, boolean z) {
            int i4 = 1073741824;
            int max = Math.max(0, i - i2);
            if (z) {
                if (i3 < 0) {
                    i4 = 0;
                    i3 = 0;
                }
            } else if (i3 < 0) {
                if (i3 == -1) {
                    i3 = max;
                } else if (i3 == -2) {
                    i4 = Integer.MIN_VALUE;
                    i3 = max;
                } else {
                    i4 = 0;
                    i3 = 0;
                }
            }
            return MeasureSpec.makeMeasureSpec(i3, i4);
        }

        public int e(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).b;
            return rect.right + (view.getMeasuredWidth() + rect.left);
        }

        public int f(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).b;
            return rect.bottom + (view.getMeasuredHeight() + rect.top);
        }

        public void a(View view, int i, int i2, int i3, int i4) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).b;
            view.layout(rect.left + i, rect.top + i2, i3 - rect.right, i4 - rect.bottom);
        }

        public int g(View view) {
            return view.getLeft() - m(view);
        }

        public int h(View view) {
            return view.getTop() - k(view);
        }

        public int i(View view) {
            return view.getRight() + n(view);
        }

        public int j(View view) {
            return view.getBottom() + l(view);
        }

        public void a(View view, Rect rect) {
            if (this.r == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(this.r.f(view));
            }
        }

        public int k(View view) {
            return ((LayoutParams) view.getLayoutParams()).b.top;
        }

        public int l(View view) {
            return ((LayoutParams) view.getLayoutParams()).b.bottom;
        }

        public int m(View view) {
            return ((LayoutParams) view.getLayoutParams()).b.left;
        }

        public int n(View view) {
            return ((LayoutParams) view.getLayoutParams()).b.right;
        }

        public View a(View view, int i, l lVar, p pVar) {
            return null;
        }

        public View d(View view, int i) {
            return null;
        }

        public boolean a(RecyclerView recyclerView, View view, Rect rect, boolean z) {
            int u = u();
            int v = v();
            int s = s() - w();
            int t = t() - x();
            int left = view.getLeft() + rect.left;
            int top = view.getTop() + rect.top;
            int width = left + rect.width();
            int height = top + rect.height();
            int min = Math.min(0, left - u);
            int min2 = Math.min(0, top - v);
            int max = Math.max(0, width - s);
            t = Math.max(0, height - t);
            if (p() == 1) {
                if (max == 0) {
                    max = Math.max(min, width - s);
                }
                min = max;
            } else {
                min = min != 0 ? min : Math.min(left - u, max);
            }
            max = min2 != 0 ? min2 : Math.min(top - v, t);
            if (min == 0 && max == 0) {
                return false;
            }
            if (z) {
                recyclerView.scrollBy(min, max);
            } else {
                recyclerView.a(min, max);
            }
            return true;
        }

        @Deprecated
        public boolean a(RecyclerView recyclerView, View view, View view2) {
            return o() || recyclerView.j();
        }

        public boolean a(RecyclerView recyclerView, p pVar, View view, View view2) {
            return a(recyclerView, view, view2);
        }

        public void a(a aVar, a aVar2) {
        }

        public boolean a(RecyclerView recyclerView, ArrayList<View> arrayList, int i, int i2) {
            return false;
        }

        public void a(RecyclerView recyclerView) {
        }

        public void a(RecyclerView recyclerView, int i, int i2) {
        }

        public void b(RecyclerView recyclerView, int i, int i2) {
        }

        public void c(RecyclerView recyclerView, int i, int i2) {
        }

        public void a(RecyclerView recyclerView, int i, int i2, Object obj) {
            c(recyclerView, i, i2);
        }

        public void a(RecyclerView recyclerView, int i, int i2, int i3) {
        }

        public int d(p pVar) {
            return 0;
        }

        public int b(p pVar) {
            return 0;
        }

        public int f(p pVar) {
            return 0;
        }

        public int e(p pVar) {
            return 0;
        }

        public int c(p pVar) {
            return 0;
        }

        public int g(p pVar) {
            return 0;
        }

        public void a(l lVar, p pVar, int i, int i2) {
            this.r.i(i, i2);
        }

        public void b(int i, int i2) {
            this.r.setMeasuredDimension(i, i2);
        }

        public Parcelable c() {
            return null;
        }

        public void a(Parcelable parcelable) {
        }

        void z() {
            if (this.s != null) {
                this.s.a();
            }
        }

        private void a(o oVar) {
            if (this.s == oVar) {
                this.s = null;
            }
        }

        public void i(int i) {
        }

        public void c(l lVar) {
            for (int r = r() - 1; r >= 0; r--) {
                if (!RecyclerView.b(f(r)).c()) {
                    a(r, lVar);
                }
            }
        }

        void a(android.support.v4.view.a.c cVar) {
            a(this.r.a, this.r.f, cVar);
        }

        public void a(l lVar, p pVar, android.support.v4.view.a.c cVar) {
            if (z.b(this.r, -1) || z.a(this.r, -1)) {
                cVar.a(8192);
                cVar.i(true);
            }
            if (z.b(this.r, 1) || z.a(this.r, 1)) {
                cVar.a(4096);
                cVar.i(true);
            }
            cVar.b(android.support.v4.view.a.c.k.a(a(lVar, pVar), b(lVar, pVar), e(lVar, pVar), d(lVar, pVar)));
        }

        public void a(AccessibilityEvent accessibilityEvent) {
            a(this.r.a, this.r.f, accessibilityEvent);
        }

        public void a(l lVar, p pVar, AccessibilityEvent accessibilityEvent) {
            boolean z = true;
            android.support.v4.view.a.l a = android.support.v4.view.a.a.a(accessibilityEvent);
            if (this.r != null && a != null) {
                if (!(z.b(this.r, 1) || z.b(this.r, -1) || z.a(this.r, -1) || z.a(this.r, 1))) {
                    z = false;
                }
                a.a(z);
                if (this.r.p != null) {
                    a.a(this.r.p.a());
                }
            }
        }

        void a(View view, android.support.v4.view.a.c cVar) {
            s b = RecyclerView.b(view);
            if (b != null && !b.q() && !this.q.c(b.a)) {
                a(this.r.a, this.r.f, view, cVar);
            }
        }

        public void a(l lVar, p pVar, View view, android.support.v4.view.a.c cVar) {
            int d;
            int d2 = e() ? d(view) : 0;
            if (d()) {
                d = d(view);
            } else {
                d = 0;
            }
            cVar.c(android.support.v4.view.a.c.l.a(d2, 1, d, 1, false, false));
        }

        public void A() {
            this.a = true;
        }

        public int d(l lVar, p pVar) {
            return 0;
        }

        public int a(l lVar, p pVar) {
            if (this.r == null || this.r.p == null || !e()) {
                return 1;
            }
            return this.r.p.a();
        }

        public int b(l lVar, p pVar) {
            if (this.r == null || this.r.p == null || !d()) {
                return 1;
            }
            return this.r.p.a();
        }

        public boolean e(l lVar, p pVar) {
            return false;
        }

        boolean a(int i, Bundle bundle) {
            return a(this.r.a, this.r.f, i, bundle);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a(android.support.v7.widget.RecyclerView.l r7, android.support.v7.widget.RecyclerView.p r8, int r9, android.os.Bundle r10) {
            /*
            r6 = this;
            r4 = -1;
            r2 = 1;
            r1 = 0;
            r0 = r6.r;
            if (r0 != 0) goto L_0x0008;
        L_0x0007:
            return r1;
        L_0x0008:
            switch(r9) {
                case 4096: goto L_0x004a;
                case 8192: goto L_0x0018;
                default: goto L_0x000b;
            };
        L_0x000b:
            r0 = r1;
            r3 = r1;
        L_0x000d:
            if (r3 != 0) goto L_0x0011;
        L_0x000f:
            if (r0 == 0) goto L_0x0007;
        L_0x0011:
            r1 = r6.r;
            r1.scrollBy(r0, r3);
            r1 = r2;
            goto L_0x0007;
        L_0x0018:
            r0 = r6.r;
            r0 = android.support.v4.view.z.b(r0, r4);
            if (r0 == 0) goto L_0x007f;
        L_0x0020:
            r0 = r6.t();
            r3 = r6.v();
            r0 = r0 - r3;
            r3 = r6.x();
            r0 = r0 - r3;
            r0 = -r0;
        L_0x002f:
            r3 = r6.r;
            r3 = android.support.v4.view.z.a(r3, r4);
            if (r3 == 0) goto L_0x007a;
        L_0x0037:
            r3 = r6.s();
            r4 = r6.u();
            r3 = r3 - r4;
            r4 = r6.w();
            r3 = r3 - r4;
            r3 = -r3;
            r5 = r3;
            r3 = r0;
            r0 = r5;
            goto L_0x000d;
        L_0x004a:
            r0 = r6.r;
            r0 = android.support.v4.view.z.b(r0, r2);
            if (r0 == 0) goto L_0x007d;
        L_0x0052:
            r0 = r6.t();
            r3 = r6.v();
            r0 = r0 - r3;
            r3 = r6.x();
            r0 = r0 - r3;
        L_0x0060:
            r3 = r6.r;
            r3 = android.support.v4.view.z.a(r3, r2);
            if (r3 == 0) goto L_0x007a;
        L_0x0068:
            r3 = r6.s();
            r4 = r6.u();
            r3 = r3 - r4;
            r4 = r6.w();
            r3 = r3 - r4;
            r5 = r3;
            r3 = r0;
            r0 = r5;
            goto L_0x000d;
        L_0x007a:
            r3 = r0;
            r0 = r1;
            goto L_0x000d;
        L_0x007d:
            r0 = r1;
            goto L_0x0060;
        L_0x007f:
            r0 = r1;
            goto L_0x002f;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.LayoutManager.a(android.support.v7.widget.RecyclerView$l, android.support.v7.widget.RecyclerView$p, int, android.os.Bundle):boolean");
        }

        boolean a(View view, int i, Bundle bundle) {
            return a(this.r.a, this.r.f, view, i, bundle);
        }

        public boolean a(l lVar, p pVar, View view, int i, Bundle bundle) {
            return false;
        }
    }

    public static class SavedState extends BaseSavedState {
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
        Parcelable a;

        SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readParcelable(LayoutManager.class.getClassLoader());
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.a, 0);
        }

        private void a(SavedState savedState) {
            this.a = savedState.a;
        }
    }

    public static abstract class a<VH extends s> {
        private final b a = new b();
        private boolean b = false;

        public abstract int a();

        public abstract VH a(ViewGroup viewGroup, int i);

        public abstract void a(VH vh, int i);

        public void a(VH vh, int i, List<Object> list) {
            a((s) vh, i);
        }

        public final VH b(ViewGroup viewGroup, int i) {
            android.support.v4.os.h.a("RV CreateView");
            VH a = a(viewGroup, i);
            a.e = i;
            android.support.v4.os.h.a();
            return a;
        }

        public final void b(VH vh, int i) {
            vh.b = i;
            if (b()) {
                vh.d = b(i);
            }
            vh.a(1, 519);
            android.support.v4.os.h.a("RV OnBindView");
            a(vh, i, vh.u());
            vh.t();
            android.support.v4.os.h.a();
        }

        public int a(int i) {
            return 0;
        }

        public long b(int i) {
            return -1;
        }

        public final boolean b() {
            return this.b;
        }

        public void a(VH vh) {
        }

        public boolean b(VH vh) {
            return false;
        }

        public void c(VH vh) {
        }

        public void d(VH vh) {
        }

        public void a(c cVar) {
            this.a.registerObserver(cVar);
        }

        public void b(c cVar) {
            this.a.unregisterObserver(cVar);
        }

        public void a(RecyclerView recyclerView) {
        }

        public void b(RecyclerView recyclerView) {
        }

        public final void c() {
            this.a.a();
        }
    }

    static class b extends Observable<c> {
        b() {
        }

        public void a() {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).a();
            }
        }
    }

    public static abstract class c {
        public void a() {
        }
    }

    public interface d {
        int a(int i, int i2);
    }

    public static abstract class e {
        private b a = null;
        private ArrayList<a> b = new ArrayList();
        private long c = 120;
        private long d = 120;
        private long e = 250;
        private long f = 250;

        public interface a {
            void a();
        }

        interface b {
            void a(s sVar);
        }

        public static class c {
            public int a;
            public int b;
            public int c;
            public int d;

            public c a(s sVar) {
                return a(sVar, 0);
            }

            public c a(s sVar, int i) {
                View view = sVar.a;
                this.a = view.getLeft();
                this.b = view.getTop();
                this.c = view.getRight();
                this.d = view.getBottom();
                return this;
            }
        }

        public abstract void a();

        public abstract boolean a(s sVar, c cVar, c cVar2);

        public abstract boolean a(s sVar, s sVar2, c cVar, c cVar2);

        public abstract boolean b();

        public abstract boolean b(s sVar, c cVar, c cVar2);

        public abstract void c();

        public abstract void c(s sVar);

        public abstract boolean c(s sVar, c cVar, c cVar2);

        public long d() {
            return this.e;
        }

        public long e() {
            return this.c;
        }

        public long f() {
            return this.d;
        }

        public long g() {
            return this.f;
        }

        void a(b bVar) {
            this.a = bVar;
        }

        public c a(p pVar, s sVar, int i, List<Object> list) {
            return i().a(sVar);
        }

        public c a(p pVar, s sVar) {
            return i().a(sVar);
        }

        static int d(s sVar) {
            int f = sVar.l & 14;
            if (sVar.n()) {
                return 4;
            }
            if ((f & 4) != 0) {
                return f;
            }
            int f2 = sVar.f();
            int e = sVar.e();
            if (f2 == -1 || e == -1 || f2 == e) {
                return f;
            }
            return f | 2048;
        }

        public final void e(s sVar) {
            f(sVar);
            if (this.a != null) {
                this.a.a(sVar);
            }
        }

        public void f(s sVar) {
        }

        public boolean g(s sVar) {
            return true;
        }

        public final void h() {
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                ((a) this.b.get(i)).a();
            }
            this.b.clear();
        }

        public c i() {
            return new c();
        }
    }

    private class f implements b {
        final /* synthetic */ RecyclerView a;

        private f(RecyclerView recyclerView) {
            this.a = recyclerView;
        }

        public void a(s sVar) {
            sVar.a(true);
            if (sVar.g != null && sVar.h == null) {
                sVar.g = null;
            }
            sVar.h = null;
            if (!sVar.B() && !this.a.g(sVar.a) && sVar.r()) {
                this.a.removeDetachedView(sVar.a, false);
            }
        }
    }

    public static abstract class g {
        public void a(Canvas canvas, RecyclerView recyclerView, p pVar) {
            a(canvas, recyclerView);
        }

        @Deprecated
        public void a(Canvas canvas, RecyclerView recyclerView) {
        }

        public void b(Canvas canvas, RecyclerView recyclerView, p pVar) {
            b(canvas, recyclerView);
        }

        @Deprecated
        public void b(Canvas canvas, RecyclerView recyclerView) {
        }

        @Deprecated
        public void a(Rect rect, int i, RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }

        public void a(Rect rect, View view, RecyclerView recyclerView, p pVar) {
            a(rect, ((LayoutParams) view.getLayoutParams()).e(), recyclerView);
        }
    }

    public interface h {
        void a(View view);

        void b(View view);
    }

    public interface i {
        void a(boolean z);

        boolean a(RecyclerView recyclerView, MotionEvent motionEvent);

        void b(RecyclerView recyclerView, MotionEvent motionEvent);
    }

    public static abstract class j {
        public void a(RecyclerView recyclerView, int i) {
        }

        public void a(RecyclerView recyclerView, int i, int i2) {
        }
    }

    public static class k {
        private SparseArray<ArrayList<s>> a = new SparseArray();
        private SparseIntArray b = new SparseIntArray();
        private int c = 0;

        public void a() {
            this.a.clear();
        }

        public s a(int i) {
            ArrayList arrayList = (ArrayList) this.a.get(i);
            if (arrayList == null || arrayList.isEmpty()) {
                return null;
            }
            int size = arrayList.size() - 1;
            s sVar = (s) arrayList.get(size);
            arrayList.remove(size);
            return sVar;
        }

        public void a(s sVar) {
            int h = sVar.h();
            ArrayList b = b(h);
            if (this.b.get(h) > b.size()) {
                sVar.v();
                b.add(sVar);
            }
        }

        void a(a aVar) {
            this.c++;
        }

        void b() {
            this.c--;
        }

        void a(a aVar, a aVar2, boolean z) {
            if (aVar != null) {
                b();
            }
            if (!z && this.c == 0) {
                a();
            }
            if (aVar2 != null) {
                a(aVar2);
            }
        }

        private ArrayList<s> b(int i) {
            ArrayList<s> arrayList = (ArrayList) this.a.get(i);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.a.put(i, arrayList);
                if (this.b.indexOfKey(i) < 0) {
                    this.b.put(i, 5);
                }
            }
            return arrayList;
        }
    }

    public final class l {
        final ArrayList<s> a = new ArrayList();
        final ArrayList<s> b = new ArrayList();
        final /* synthetic */ RecyclerView c;
        private ArrayList<s> d = null;
        private final List<s> e = Collections.unmodifiableList(this.a);
        private int f = 2;
        private k g;
        private q h;

        public l(RecyclerView recyclerView) {
            this.c = recyclerView;
        }

        public void a() {
            this.a.clear();
            c();
        }

        public void a(int i) {
            this.f = i;
            for (int size = this.b.size() - 1; size >= 0 && this.b.size() > i; size--) {
                d(size);
            }
        }

        public List<s> b() {
            return this.e;
        }

        boolean a(s sVar) {
            if (sVar.q()) {
                return true;
            }
            if (sVar.b < 0 || sVar.b >= this.c.p.a()) {
                throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + sVar);
            } else if (!this.c.f.a() && this.c.p.a(sVar.b) != sVar.h()) {
                return false;
            } else {
                if (!this.c.p.b() || sVar.g() == this.c.p.b(sVar.b)) {
                    return true;
                }
                return false;
            }
        }

        public int b(int i) {
            if (i >= 0 && i < this.c.f.e()) {
                return !this.c.f.a() ? i : this.c.b.b(i);
            } else {
                throw new IndexOutOfBoundsException("invalid position " + i + ". State " + "item count is " + this.c.f.e());
            }
        }

        public View c(int i) {
            return a(i, false);
        }

        View a(int i, boolean z) {
            boolean z2 = true;
            if (i < 0 || i >= this.c.f.e()) {
                throw new IndexOutOfBoundsException("Invalid item position " + i + "(" + i + "). Item count:" + this.c.f.e());
            }
            s f;
            boolean z3;
            s sVar;
            boolean z4;
            int b;
            boolean z5;
            int b2;
            android.view.ViewGroup.LayoutParams layoutParams;
            LayoutParams layoutParams2;
            if (this.c.f.a()) {
                f = f(i);
                s sVar2 = f;
                z3 = f != null;
                sVar = sVar2;
            } else {
                sVar = null;
                z3 = false;
            }
            if (sVar == null) {
                sVar = a(i, -1, z);
                if (sVar != null) {
                    if (a(sVar)) {
                        z4 = true;
                    } else {
                        if (!z) {
                            sVar.b(4);
                            if (sVar.i()) {
                                this.c.removeDetachedView(sVar.a, false);
                                sVar.j();
                            } else if (sVar.k()) {
                                sVar.l();
                            }
                            b(sVar);
                        }
                        sVar = null;
                        z4 = z3;
                    }
                    if (sVar == null) {
                        b = this.c.b.b(i);
                        if (b >= 0 || b >= this.c.p.a()) {
                            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + i + "(offset:" + b + ")." + "state:" + this.c.f.e());
                        }
                        int a = this.c.p.a(b);
                        if (this.c.p.b()) {
                            sVar = a(this.c.p.b(b), a, z);
                            if (sVar != null) {
                                sVar.b = b;
                                z4 = true;
                            }
                        }
                        if (sVar == null && this.h != null) {
                            View a2 = this.h.a(this, i, a);
                            if (a2 != null) {
                                sVar = this.c.a(a2);
                                if (sVar == null) {
                                    throw new IllegalArgumentException("getViewForPositionAndType returned a view which does not have a ViewHolder");
                                } else if (sVar.c()) {
                                    throw new IllegalArgumentException("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view.");
                                }
                            }
                        }
                        if (sVar == null) {
                            sVar = f().a(a);
                            if (sVar != null) {
                                sVar.v();
                                if (RecyclerView.i) {
                                    f(sVar);
                                }
                            }
                        }
                        if (sVar == null) {
                            f = this.c.p.b(this.c, a);
                            z5 = z4;
                            if (z5 && !this.c.f.a() && f.a(8192)) {
                                f.a(0, 8192);
                                if (this.c.f.h) {
                                    this.c.a(f, this.c.e.a(this.c.f, f, e.d(f) | 4096, f.u()));
                                }
                            }
                            int i2;
                            if (!this.c.f.a() && f.p()) {
                                f.f = i;
                                i2 = 0;
                            } else if (f.p() || f.o() || f.n()) {
                                b2 = this.c.b.b(i);
                                f.k = this.c;
                                this.c.p.b(f, b2);
                                d(f.a);
                                if (this.c.f.a()) {
                                    f.f = i;
                                }
                                z4 = true;
                            } else {
                                i2 = 0;
                            }
                            layoutParams = f.a.getLayoutParams();
                            if (layoutParams != null) {
                                layoutParams2 = (LayoutParams) this.c.generateDefaultLayoutParams();
                                f.a.setLayoutParams(layoutParams2);
                            } else if (this.c.checkLayoutParams(layoutParams)) {
                                layoutParams2 = (LayoutParams) layoutParams;
                            } else {
                                layoutParams2 = (LayoutParams) this.c.generateLayoutParams(layoutParams);
                                f.a.setLayoutParams(layoutParams2);
                            }
                            layoutParams2.a = f;
                            if (!z5 || r3 == 0) {
                                z2 = false;
                            }
                            layoutParams2.d = z2;
                            return f.a;
                        }
                    }
                    f = sVar;
                    z5 = z4;
                    f.a(0, 8192);
                    if (this.c.f.h) {
                        this.c.a(f, this.c.e.a(this.c.f, f, e.d(f) | 4096, f.u()));
                    }
                    if (!this.c.f.a()) {
                    }
                    if (f.p()) {
                    }
                    b2 = this.c.b.b(i);
                    f.k = this.c;
                    this.c.p.b(f, b2);
                    d(f.a);
                    if (this.c.f.a()) {
                        f.f = i;
                    }
                    z4 = true;
                    layoutParams = f.a.getLayoutParams();
                    if (layoutParams != null) {
                        layoutParams2 = (LayoutParams) this.c.generateDefaultLayoutParams();
                        f.a.setLayoutParams(layoutParams2);
                    } else if (this.c.checkLayoutParams(layoutParams)) {
                        layoutParams2 = (LayoutParams) layoutParams;
                    } else {
                        layoutParams2 = (LayoutParams) this.c.generateLayoutParams(layoutParams);
                        f.a.setLayoutParams(layoutParams2);
                    }
                    layoutParams2.a = f;
                    z2 = false;
                    layoutParams2.d = z2;
                    return f.a;
                }
            }
            z4 = z3;
            if (sVar == null) {
                b = this.c.b.b(i);
                if (b >= 0) {
                }
                throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + i + "(offset:" + b + ")." + "state:" + this.c.f.e());
            }
            f = sVar;
            z5 = z4;
            f.a(0, 8192);
            if (this.c.f.h) {
                this.c.a(f, this.c.e.a(this.c.f, f, e.d(f) | 4096, f.u()));
            }
            if (!this.c.f.a()) {
            }
            if (f.p()) {
            }
            b2 = this.c.b.b(i);
            f.k = this.c;
            this.c.p.b(f, b2);
            d(f.a);
            if (this.c.f.a()) {
                f.f = i;
            }
            z4 = true;
            layoutParams = f.a.getLayoutParams();
            if (layoutParams != null) {
                layoutParams2 = (LayoutParams) this.c.generateDefaultLayoutParams();
                f.a.setLayoutParams(layoutParams2);
            } else if (this.c.checkLayoutParams(layoutParams)) {
                layoutParams2 = (LayoutParams) this.c.generateLayoutParams(layoutParams);
                f.a.setLayoutParams(layoutParams2);
            } else {
                layoutParams2 = (LayoutParams) layoutParams;
            }
            layoutParams2.a = f;
            z2 = false;
            layoutParams2.d = z2;
            return f.a;
        }

        private void d(View view) {
            if (this.c.i()) {
                if (z.e(view) == 0) {
                    z.c(view, 1);
                }
                if (!z.b(view)) {
                    z.a(view, this.c.ag.b());
                }
            }
        }

        private void f(s sVar) {
            if (sVar.a instanceof ViewGroup) {
                a((ViewGroup) sVar.a, false);
            }
        }

        private void a(ViewGroup viewGroup, boolean z) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    a((ViewGroup) childAt, true);
                }
            }
            if (!z) {
                return;
            }
            if (viewGroup.getVisibility() == 4) {
                viewGroup.setVisibility(0);
                viewGroup.setVisibility(4);
                return;
            }
            int visibility = viewGroup.getVisibility();
            viewGroup.setVisibility(4);
            viewGroup.setVisibility(visibility);
        }

        public void a(View view) {
            s b = RecyclerView.b(view);
            if (b.r()) {
                this.c.removeDetachedView(view, false);
            }
            if (b.i()) {
                b.j();
            } else if (b.k()) {
                b.l();
            }
            b(b);
        }

        void c() {
            for (int size = this.b.size() - 1; size >= 0; size--) {
                d(size);
            }
            this.b.clear();
        }

        void d(int i) {
            c((s) this.b.get(i));
            this.b.remove(i);
        }

        void b(s sVar) {
            boolean z = true;
            int i = 0;
            if (sVar.i() || sVar.a.getParent() != null) {
                StringBuilder append = new StringBuilder().append("Scrapped or attached views may not be recycled. isScrap:").append(sVar.i()).append(" isAttached:");
                if (sVar.a.getParent() == null) {
                    z = false;
                }
                throw new IllegalArgumentException(append.append(z).toString());
            } else if (sVar.r()) {
                throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + sVar);
            } else if (sVar.c()) {
                throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.");
            } else {
                int i2;
                boolean c = sVar.C();
                boolean z2 = this.c.p != null && c && this.c.p.b(sVar);
                if (z2 || sVar.w()) {
                    if (!sVar.a(14)) {
                        int size = this.b.size();
                        if (size == this.f && size > 0) {
                            d(0);
                        }
                        if (size < this.f) {
                            this.b.add(sVar);
                            z2 = true;
                            if (z2) {
                                c(sVar);
                                i = 1;
                                i2 = z2;
                            } else {
                                z = z2;
                            }
                        }
                    }
                    z2 = false;
                    if (z2) {
                        z = z2;
                    } else {
                        c(sVar);
                        i = 1;
                        i2 = z2;
                    }
                } else {
                    i2 = 0;
                }
                this.c.d.e(sVar);
                if (i2 == 0 && r1 == 0 && c) {
                    sVar.k = null;
                }
            }
        }

        void c(s sVar) {
            z.a(sVar.a, null);
            e(sVar);
            sVar.k = null;
            f().a(sVar);
        }

        void b(View view) {
            s b = RecyclerView.b(view);
            b.o = null;
            b.p = false;
            b.l();
            b(b);
        }

        void c(View view) {
            s b = RecyclerView.b(view);
            if (b.x() && !b.n() && !this.c.c(b)) {
                if (this.d == null) {
                    this.d = new ArrayList();
                }
                b.a(this, true);
                this.d.add(b);
            } else if (!b.n() || b.q() || this.c.p.b()) {
                b.a(this, false);
                this.a.add(b);
            } else {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
            }
        }

        void d(s sVar) {
            if (sVar.p) {
                this.d.remove(sVar);
            } else {
                this.a.remove(sVar);
            }
            sVar.o = null;
            sVar.p = false;
            sVar.l();
        }

        int d() {
            return this.a.size();
        }

        View e(int i) {
            return ((s) this.a.get(i)).a;
        }

        void e() {
            this.a.clear();
            if (this.d != null) {
                this.d.clear();
            }
        }

        s f(int i) {
            int i2 = 0;
            if (this.d != null) {
                int size = this.d.size();
                if (size != 0) {
                    s sVar;
                    int i3 = 0;
                    while (i3 < size) {
                        sVar = (s) this.d.get(i3);
                        if (sVar.k() || sVar.d() != i) {
                            i3++;
                        } else {
                            sVar.b(32);
                            return sVar;
                        }
                    }
                    if (this.c.p.b()) {
                        int b = this.c.b.b(i);
                        if (b > 0 && b < this.c.p.a()) {
                            long b2 = this.c.p.b(b);
                            while (i2 < size) {
                                sVar = (s) this.d.get(i2);
                                if (sVar.k() || sVar.g() != b2) {
                                    i2++;
                                } else {
                                    sVar.b(32);
                                    return sVar;
                                }
                            }
                        }
                    }
                    return null;
                }
            }
            return null;
        }

        s a(int i, int i2, boolean z) {
            View a;
            int i3 = 0;
            int size = this.a.size();
            int i4 = 0;
            while (i4 < size) {
                s sVar = (s) this.a.get(i4);
                if (sVar.k() || sVar.d() != i || sVar.n() || (!this.c.f.g && sVar.q())) {
                    i4++;
                } else if (i2 == -1 || sVar.h() == i2) {
                    sVar.b(32);
                    return sVar;
                } else {
                    Log.e("RecyclerView", "Scrap view for position " + i + " isn't dirty but has" + " wrong view type! (found " + sVar.h() + " but expected " + i2 + ")");
                    if (!z) {
                        a = this.c.c.a(i, i2);
                        if (a != null) {
                            sVar = RecyclerView.b(a);
                            this.c.c.e(a);
                            i3 = this.c.c.b(a);
                            if (i3 != -1) {
                                throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + sVar);
                            }
                            this.c.c.d(i3);
                            c(a);
                            sVar.b(8224);
                            return sVar;
                        }
                    }
                    i4 = this.b.size();
                    while (i3 < i4) {
                        sVar = (s) this.b.get(i3);
                        if (sVar.n() || sVar.d() != i) {
                            i3++;
                        } else if (z) {
                            return sVar;
                        } else {
                            this.b.remove(i3);
                            return sVar;
                        }
                    }
                    return null;
                }
            }
            if (z) {
                a = this.c.c.a(i, i2);
                if (a != null) {
                    sVar = RecyclerView.b(a);
                    this.c.c.e(a);
                    i3 = this.c.c.b(a);
                    if (i3 != -1) {
                        this.c.c.d(i3);
                        c(a);
                        sVar.b(8224);
                        return sVar;
                    }
                    throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + sVar);
                }
            }
            i4 = this.b.size();
            while (i3 < i4) {
                sVar = (s) this.b.get(i3);
                if (sVar.n()) {
                }
                i3++;
            }
            return null;
        }

        s a(long j, int i, boolean z) {
            int size;
            for (size = this.a.size() - 1; size >= 0; size--) {
                s sVar = (s) this.a.get(size);
                if (sVar.g() == j && !sVar.k()) {
                    if (i == sVar.h()) {
                        sVar.b(32);
                        if (!sVar.q() || this.c.f.a()) {
                            return sVar;
                        }
                        sVar.a(2, 14);
                        return sVar;
                    } else if (!z) {
                        this.a.remove(size);
                        this.c.removeDetachedView(sVar.a, false);
                        b(sVar.a);
                    }
                }
            }
            for (size = this.b.size() - 1; size >= 0; size--) {
                sVar = (s) this.b.get(size);
                if (sVar.g() == j) {
                    if (i == sVar.h()) {
                        if (z) {
                            return sVar;
                        }
                        this.b.remove(size);
                        return sVar;
                    } else if (!z) {
                        d(size);
                    }
                }
            }
            return null;
        }

        void e(s sVar) {
            if (this.c.r != null) {
                this.c.r.a(sVar);
            }
            if (this.c.p != null) {
                this.c.p.a(sVar);
            }
            if (this.c.f != null) {
                this.c.d.e(sVar);
            }
        }

        void a(a aVar, a aVar2, boolean z) {
            a();
            f().a(aVar, aVar2, z);
        }

        void a(int i, int i2) {
            int i3;
            int i4;
            int i5;
            if (i < i2) {
                i3 = -1;
                i4 = i2;
                i5 = i;
            } else {
                i3 = 1;
                i4 = i;
                i5 = i2;
            }
            int size = this.b.size();
            for (int i6 = 0; i6 < size; i6++) {
                s sVar = (s) this.b.get(i6);
                if (sVar != null && sVar.b >= r3 && sVar.b <= r2) {
                    if (sVar.b == i) {
                        sVar.a(i2 - i, false);
                    } else {
                        sVar.a(i3, false);
                    }
                }
            }
        }

        void b(int i, int i2) {
            int size = this.b.size();
            for (int i3 = 0; i3 < size; i3++) {
                s sVar = (s) this.b.get(i3);
                if (sVar != null && sVar.d() >= i) {
                    sVar.a(i2, true);
                }
            }
        }

        void b(int i, int i2, boolean z) {
            int i3 = i + i2;
            for (int size = this.b.size() - 1; size >= 0; size--) {
                s sVar = (s) this.b.get(size);
                if (sVar != null) {
                    if (sVar.d() >= i3) {
                        sVar.a(-i2, z);
                    } else if (sVar.d() >= i) {
                        sVar.b(8);
                        d(size);
                    }
                }
            }
        }

        void a(q qVar) {
            this.h = qVar;
        }

        void a(k kVar) {
            if (this.g != null) {
                this.g.b();
            }
            this.g = kVar;
            if (kVar != null) {
                this.g.a(this.c.getAdapter());
            }
        }

        k f() {
            if (this.g == null) {
                this.g = new k();
            }
            return this.g;
        }

        void c(int i, int i2) {
            int i3 = i + i2;
            for (int size = this.b.size() - 1; size >= 0; size--) {
                s sVar = (s) this.b.get(size);
                if (sVar != null) {
                    int d = sVar.d();
                    if (d >= i && d < i3) {
                        sVar.b(2);
                        d(size);
                    }
                }
            }
        }

        void g() {
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                s sVar = (s) this.b.get(i);
                if (sVar != null) {
                    sVar.b(512);
                }
            }
        }

        void h() {
            if (this.c.p == null || !this.c.p.b()) {
                c();
                return;
            }
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                s sVar = (s) this.b.get(i);
                if (sVar != null) {
                    sVar.b(6);
                    sVar.a(null);
                }
            }
        }

        void i() {
            int i;
            int i2 = 0;
            int size = this.b.size();
            for (i = 0; i < size; i++) {
                ((s) this.b.get(i)).a();
            }
            size = this.a.size();
            for (i = 0; i < size; i++) {
                ((s) this.a.get(i)).a();
            }
            if (this.d != null) {
                i = this.d.size();
                while (i2 < i) {
                    ((s) this.d.get(i2)).a();
                    i2++;
                }
            }
        }

        void j() {
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                LayoutParams layoutParams = (LayoutParams) ((s) this.b.get(i)).a.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.c = true;
                }
            }
        }
    }

    public interface m {
        void a(s sVar);
    }

    private class n extends c {
        final /* synthetic */ RecyclerView a;

        private n(RecyclerView recyclerView) {
            this.a = recyclerView;
        }

        public void a() {
            this.a.a(null);
            if (this.a.p.b()) {
                this.a.f.f = true;
                this.a.F();
            } else {
                this.a.f.f = true;
                this.a.F();
            }
            if (!this.a.b.d()) {
                this.a.requestLayout();
            }
        }
    }

    public static abstract class o {
        private int a;
        private RecyclerView b;
        private LayoutManager c;
        private boolean d;
        private boolean e;
        private View f;
        private final a g;

        public static class a {
            private int a;
            private int b;
            private int c;
            private int d;
            private Interpolator e;
            private boolean f;
            private int g;

            boolean a() {
                return this.d >= 0;
            }

            private void a(RecyclerView recyclerView) {
                if (this.d >= 0) {
                    int i = this.d;
                    this.d = -1;
                    recyclerView.e(i);
                    this.f = false;
                } else if (this.f) {
                    b();
                    if (this.e != null) {
                        recyclerView.ab.a(this.a, this.b, this.c, this.e);
                    } else if (this.c == Integer.MIN_VALUE) {
                        recyclerView.ab.b(this.a, this.b);
                    } else {
                        recyclerView.ab.a(this.a, this.b, this.c);
                    }
                    this.g++;
                    if (this.g > 10) {
                        Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.f = false;
                } else {
                    this.g = 0;
                }
            }

            private void b() {
                if (this.e != null && this.c < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                } else if (this.c < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }
        }

        protected abstract void a(int i, int i2, p pVar, a aVar);

        protected abstract void a(View view, p pVar, a aVar);

        protected abstract void e();

        public void a(int i) {
            this.a = i;
        }

        protected final void a() {
            if (this.e) {
                e();
                this.b.f.b = -1;
                this.f = null;
                this.a = -1;
                this.d = false;
                this.e = false;
                this.c.a(this);
                this.c = null;
                this.b = null;
            }
        }

        public boolean b() {
            return this.d;
        }

        public boolean c() {
            return this.e;
        }

        public int d() {
            return this.a;
        }

        private void a(int i, int i2) {
            RecyclerView recyclerView = this.b;
            if (!this.e || this.a == -1 || recyclerView == null) {
                a();
            }
            this.d = false;
            if (this.f != null) {
                if (a(this.f) == this.a) {
                    a(this.f, recyclerView.f, this.g);
                    this.g.a(recyclerView);
                    a();
                } else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.f = null;
                }
            }
            if (this.e) {
                a(i, i2, recyclerView.f, this.g);
                boolean a = this.g.a();
                this.g.a(recyclerView);
                if (!a) {
                    return;
                }
                if (this.e) {
                    this.d = true;
                    recyclerView.ab.a();
                    return;
                }
                a();
            }
        }

        public int a(View view) {
            return this.b.c(view);
        }

        protected void b(View view) {
            if (a(view) == d()) {
                this.f = view;
            }
        }
    }

    public static class p {
        int a = 0;
        private int b = -1;
        private SparseArray<Object> c;
        private int d = 0;
        private int e = 0;
        private boolean f = false;
        private boolean g = false;
        private boolean h = false;
        private boolean i = false;
        private boolean j = false;

        static /* synthetic */ int a(p pVar, int i) {
            int i2 = pVar.e + i;
            pVar.e = i2;
            return i2;
        }

        public boolean a() {
            return this.g;
        }

        public boolean b() {
            return this.i;
        }

        public int c() {
            return this.b;
        }

        public boolean d() {
            return this.b != -1;
        }

        public int e() {
            return this.g ? this.d - this.e : this.a;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.b + ", mData=" + this.c + ", mItemCount=" + this.a + ", mPreviousLayoutItemCount=" + this.d + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.e + ", mStructureChanged=" + this.f + ", mInPreLayout=" + this.g + ", mRunSimpleAnimations=" + this.h + ", mRunPredictiveAnimations=" + this.i + '}';
        }
    }

    public static abstract class q {
        public abstract View a(l lVar, int i, int i2);
    }

    private class r implements Runnable {
        final /* synthetic */ RecyclerView a;
        private int b;
        private int c;
        private android.support.v4.widget.h d;
        private Interpolator e = RecyclerView.ao;
        private boolean f = false;
        private boolean g = false;

        public r(RecyclerView recyclerView) {
            this.a = recyclerView;
            this.d = android.support.v4.widget.h.a(recyclerView.getContext(), RecyclerView.ao);
        }

        public void run() {
            c();
            this.a.t();
            android.support.v4.widget.h hVar = this.d;
            o oVar = this.a.q.s;
            if (hVar.g()) {
                int e;
                int i;
                int f;
                int i2;
                Object obj;
                Object obj2;
                int b = hVar.b();
                int c = hVar.c();
                int i3 = b - this.b;
                int i4 = c - this.c;
                int i5 = 0;
                int i6 = 0;
                this.b = b;
                this.c = c;
                int i7 = 0;
                int i8 = 0;
                if (this.a.p != null) {
                    this.a.b();
                    this.a.z();
                    android.support.v4.os.h.a("RV Scroll");
                    if (i3 != 0) {
                        i5 = this.a.q.a(i3, this.a.a, this.a.f);
                        i7 = i3 - i5;
                    }
                    if (i4 != 0) {
                        i6 = this.a.q.b(i4, this.a.a, this.a.f);
                        i8 = i4 - i6;
                    }
                    android.support.v4.os.h.a();
                    this.a.G();
                    this.a.A();
                    this.a.a(false);
                    if (!(oVar == null || oVar.b() || !oVar.c())) {
                        e = this.a.f.e();
                        if (e == 0) {
                            oVar.a();
                            i = i7;
                            i7 = i6;
                            i6 = i;
                        } else if (oVar.d() >= e) {
                            oVar.a(e - 1);
                            oVar.a(i3 - i7, i4 - i8);
                            i = i7;
                            i7 = i6;
                            i6 = i;
                        } else {
                            oVar.a(i3 - i7, i4 - i8);
                        }
                        if (!this.a.s.isEmpty()) {
                            this.a.invalidate();
                        }
                        if (z.a(this.a) != 2) {
                            this.a.h(i3, i4);
                        }
                        if (!(i6 == 0 && i8 == 0)) {
                            f = (int) hVar.f();
                            if (i6 == b) {
                                e = i6 >= 0 ? -f : i6 <= 0 ? f : 0;
                                i2 = e;
                            } else {
                                i2 = 0;
                            }
                            if (i8 != c) {
                                f = 0;
                            } else if (i8 < 0) {
                                f = -f;
                            } else if (i8 <= 0) {
                                f = 0;
                            }
                            if (z.a(this.a) != 2) {
                                this.a.c(i2, f);
                            }
                            if ((i2 != 0 || i6 == b || hVar.d() == 0) && (f != 0 || i8 == c || hVar.e() == 0)) {
                                hVar.h();
                            }
                        }
                        if (!(i5 == 0 && i7 == 0)) {
                            this.a.g(i5, i7);
                        }
                        if (!this.a.awakenScrollBars()) {
                            this.a.invalidate();
                        }
                        obj = (i4 == 0 && this.a.q.e() && i7 == i4) ? 1 : null;
                        obj2 = (i3 == 0 && this.a.q.d() && i5 == i3) ? 1 : null;
                        obj2 = ((i3 == 0 || i4 != 0) && obj2 == null && obj == null) ? null : 1;
                        if (!hVar.a() || obj2 == null) {
                            this.a.setScrollState(0);
                        } else {
                            a();
                        }
                    }
                }
                i = i7;
                i7 = i6;
                i6 = i;
                if (this.a.s.isEmpty()) {
                    this.a.invalidate();
                }
                if (z.a(this.a) != 2) {
                    this.a.h(i3, i4);
                }
                f = (int) hVar.f();
                if (i6 == b) {
                    i2 = 0;
                } else {
                    if (i6 >= 0) {
                        if (i6 <= 0) {
                        }
                    }
                    i2 = e;
                }
                if (i8 != c) {
                    f = 0;
                } else if (i8 < 0) {
                    f = -f;
                } else if (i8 <= 0) {
                    f = 0;
                }
                if (z.a(this.a) != 2) {
                    this.a.c(i2, f);
                }
                hVar.h();
                this.a.g(i5, i7);
                if (this.a.awakenScrollBars()) {
                    this.a.invalidate();
                }
                if (i4 == 0) {
                }
                if (i3 == 0) {
                }
                if (i3 == 0) {
                }
                if (hVar.a()) {
                }
                this.a.setScrollState(0);
            }
            if (oVar != null) {
                if (oVar.b()) {
                    oVar.a(0, 0);
                }
                if (!this.g) {
                    oVar.a();
                }
            }
            d();
        }

        private void c() {
            this.g = false;
            this.f = true;
        }

        private void d() {
            this.f = false;
            if (this.g) {
                a();
            }
        }

        void a() {
            if (this.f) {
                this.g = true;
                return;
            }
            this.a.removeCallbacks(this);
            z.a(this.a, (Runnable) this);
        }

        public void a(int i, int i2) {
            this.a.setScrollState(2);
            this.c = 0;
            this.b = 0;
            this.d.a(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            a();
        }

        public void b(int i, int i2) {
            a(i, i2, 0, 0);
        }

        public void a(int i, int i2, int i3, int i4) {
            a(i, i2, b(i, i2, i3, i4));
        }

        private float a(float f) {
            return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
        }

        private int b(int i, int i2, int i3, int i4) {
            int round;
            int abs = Math.abs(i);
            int abs2 = Math.abs(i2);
            Object obj = abs > abs2 ? 1 : null;
            int sqrt = (int) Math.sqrt((double) ((i3 * i3) + (i4 * i4)));
            int sqrt2 = (int) Math.sqrt((double) ((i * i) + (i2 * i2)));
            int width = obj != null ? this.a.getWidth() : this.a.getHeight();
            int i5 = width / 2;
            float a = (a(Math.min(1.0f, (((float) sqrt2) * 1.0f) / ((float) width))) * ((float) i5)) + ((float) i5);
            if (sqrt > 0) {
                round = Math.round(1000.0f * Math.abs(a / ((float) sqrt))) * 4;
            } else {
                if (obj != null) {
                    round = abs;
                } else {
                    round = abs2;
                }
                round = (int) (((((float) round) / ((float) width)) + 1.0f) * 300.0f);
            }
            return Math.min(round, APPluginErrorCode.ERROR_APP_SYSTEM);
        }

        public void a(int i, int i2, int i3) {
            a(i, i2, i3, RecyclerView.ao);
        }

        public void a(int i, int i2, int i3, Interpolator interpolator) {
            if (this.e != interpolator) {
                this.e = interpolator;
                this.d = android.support.v4.widget.h.a(this.a.getContext(), interpolator);
            }
            this.a.setScrollState(2);
            this.c = 0;
            this.b = 0;
            this.d.a(0, 0, i, i2, i3);
            a();
        }

        public void b() {
            this.a.removeCallbacks(this);
            this.d.h();
        }
    }

    public static abstract class s {
        private static final List<Object> m = Collections.EMPTY_LIST;
        public final View a;
        int b = -1;
        int c = -1;
        long d = -1;
        int e = -1;
        int f = -1;
        s g = null;
        s h = null;
        List<Object> i = null;
        List<Object> j = null;
        RecyclerView k;
        private int l;
        private int n = 0;
        private l o = null;
        private boolean p = false;
        private int q = 0;

        public s(View view) {
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.a = view;
        }

        void a(int i, int i2, boolean z) {
            b(8);
            a(i2, z);
            this.b = i;
        }

        void a(int i, boolean z) {
            if (this.c == -1) {
                this.c = this.b;
            }
            if (this.f == -1) {
                this.f = this.b;
            }
            if (z) {
                this.f += i;
            }
            this.b += i;
            if (this.a.getLayoutParams() != null) {
                ((LayoutParams) this.a.getLayoutParams()).c = true;
            }
        }

        void a() {
            this.c = -1;
            this.f = -1;
        }

        void b() {
            if (this.c == -1) {
                this.c = this.b;
            }
        }

        boolean c() {
            return (this.l & 128) != 0;
        }

        public final int d() {
            return this.f == -1 ? this.b : this.f;
        }

        public final int e() {
            if (this.k == null) {
                return -1;
            }
            return this.k.d(this);
        }

        public final int f() {
            return this.c;
        }

        public final long g() {
            return this.d;
        }

        public final int h() {
            return this.e;
        }

        boolean i() {
            return this.o != null;
        }

        void j() {
            this.o.d(this);
        }

        boolean k() {
            return (this.l & 32) != 0;
        }

        void l() {
            this.l &= -33;
        }

        void m() {
            this.l &= -257;
        }

        void a(l lVar, boolean z) {
            this.o = lVar;
            this.p = z;
        }

        boolean n() {
            return (this.l & 4) != 0;
        }

        boolean o() {
            return (this.l & 2) != 0;
        }

        boolean p() {
            return (this.l & 1) != 0;
        }

        boolean q() {
            return (this.l & 8) != 0;
        }

        boolean a(int i) {
            return (this.l & i) != 0;
        }

        boolean r() {
            return (this.l & 256) != 0;
        }

        boolean s() {
            return (this.l & 512) != 0 || n();
        }

        void a(int i, int i2) {
            this.l = (this.l & (i2 ^ -1)) | (i & i2);
        }

        void b(int i) {
            this.l |= i;
        }

        void a(Object obj) {
            if (obj == null) {
                b(1024);
            } else if ((this.l & 1024) == 0) {
                y();
                this.i.add(obj);
            }
        }

        private void y() {
            if (this.i == null) {
                this.i = new ArrayList();
                this.j = Collections.unmodifiableList(this.i);
            }
        }

        void t() {
            if (this.i != null) {
                this.i.clear();
            }
            this.l &= -1025;
        }

        List<Object> u() {
            if ((this.l & 1024) != 0) {
                return m;
            }
            if (this.i == null || this.i.size() == 0) {
                return m;
            }
            return this.j;
        }

        void v() {
            this.l = 0;
            this.b = -1;
            this.c = -1;
            this.d = -1;
            this.f = -1;
            this.n = 0;
            this.g = null;
            this.h = null;
            t();
            this.q = 0;
        }

        private void z() {
            this.q = z.e(this.a);
            z.c(this.a, 4);
        }

        private void A() {
            z.c(this.a, this.q);
            this.q = 0;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("ViewHolder{" + Integer.toHexString(hashCode()) + " position=" + this.b + " id=" + this.d + ", oldPos=" + this.c + ", pLpos:" + this.f);
            if (i()) {
                stringBuilder.append(" scrap ").append(this.p ? "[changeScrap]" : "[attachedScrap]");
            }
            if (n()) {
                stringBuilder.append(" invalid");
            }
            if (!p()) {
                stringBuilder.append(" unbound");
            }
            if (o()) {
                stringBuilder.append(" update");
            }
            if (q()) {
                stringBuilder.append(" removed");
            }
            if (c()) {
                stringBuilder.append(" ignored");
            }
            if (r()) {
                stringBuilder.append(" tmpDetached");
            }
            if (!w()) {
                stringBuilder.append(" not recyclable(" + this.n + ")");
            }
            if (s()) {
                stringBuilder.append(" undefined adapter position");
            }
            if (this.a.getParent() == null) {
                stringBuilder.append(" no parent");
            }
            stringBuilder.append("}");
            return stringBuilder.toString();
        }

        public final void a(boolean z) {
            this.n = z ? this.n - 1 : this.n + 1;
            if (this.n < 0) {
                this.n = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            } else if (!z && this.n == 1) {
                this.l |= 16;
            } else if (z && this.n == 0) {
                this.l &= -17;
            }
        }

        public final boolean w() {
            return (this.l & 16) == 0 && !z.c(this.a);
        }

        private boolean B() {
            return (this.l & 16) != 0;
        }

        private boolean C() {
            return (this.l & 16) == 0 && z.c(this.a);
        }

        boolean x() {
            return (this.l & 2) != 0;
        }
    }

    static {
        boolean z;
        if (VERSION.SDK_INT == 18 || VERSION.SDK_INT == 19 || VERSION.SDK_INT == 20) {
            z = true;
        } else {
            z = false;
        }
        i = z;
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i) {
        boolean z;
        super(context, attributeSet, i);
        this.k = new n();
        this.a = new l(this);
        this.d = new j();
        this.n = new Runnable(this) {
            final /* synthetic */ RecyclerView a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.x && !this.a.isLayoutRequested()) {
                    if (this.a.A) {
                        this.a.z = true;
                    } else {
                        this.a.t();
                    }
                }
            }
        };
        this.o = new Rect();
        this.s = new ArrayList();
        this.t = new ArrayList();
        this.H = false;
        this.I = 0;
        this.e = new c();
        this.N = 0;
        this.O = -1;
        this.aa = Float.MIN_VALUE;
        this.ab = new r(this);
        this.f = new p();
        this.g = false;
        this.h = false;
        this.ae = new f();
        this.af = false;
        this.ai = new int[2];
        this.ak = new int[2];
        this.al = new int[2];
        this.am = new int[2];
        this.an = new Runnable(this) {
            final /* synthetic */ RecyclerView a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.e != null) {
                    this.a.e.a();
                }
                this.a.af = false;
            }
        };
        this.ap = new b(this) {
            final /* synthetic */ RecyclerView a;

            {
                this.a = r1;
            }

            public void a(s sVar, c cVar, c cVar2) {
                this.a.a.d(sVar);
                this.a.b(sVar, cVar, cVar2);
            }

            public void b(s sVar, c cVar, c cVar2) {
                this.a.a(sVar, cVar, cVar2);
            }

            public void c(s sVar, c cVar, c cVar2) {
                sVar.a(false);
                if (this.a.H) {
                    if (this.a.e.a(sVar, sVar, cVar, cVar2)) {
                        this.a.C();
                    }
                } else if (this.a.e.c(sVar, cVar, cVar2)) {
                    this.a.C();
                }
            }

            public void a(s sVar) {
                this.a.q.a(sVar.a, this.a.a);
            }
        };
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        this.E = VERSION.SDK_INT >= 16;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.U = viewConfiguration.getScaledTouchSlop();
        this.V = viewConfiguration.getScaledMinimumFlingVelocity();
        this.W = viewConfiguration.getScaledMaximumFlingVelocity();
        if (z.a(this) == 2) {
            z = true;
        } else {
            z = false;
        }
        setWillNotDraw(z);
        this.e.a(this.ae);
        a();
        s();
        if (z.e(this) == 0) {
            z.c((View) this, 1);
        }
        this.F = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new g(this));
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, android.support.v7.a.a.a.RecyclerView, i, 0);
            String string = obtainStyledAttributes.getString(android.support.v7.a.a.a.RecyclerView_layoutManager);
            obtainStyledAttributes.recycle();
            a(context, string, attributeSet, i, 0);
        }
        this.aj = new android.support.v4.view.s(this);
        setNestedScrollingEnabled(true);
    }

    public g getCompatAccessibilityDelegate() {
        return this.ag;
    }

    public void setAccessibilityDelegateCompat(g gVar) {
        this.ag = gVar;
        z.a((View) this, this.ag);
    }

    private void a(Context context, String str, AttributeSet attributeSet, int i, int i2) {
        if (str != null) {
            String trim = str.trim();
            if (trim.length() != 0) {
                String a = a(context, trim);
                try {
                    ClassLoader classLoader;
                    Object[] objArr;
                    Constructor constructor;
                    if (isInEditMode()) {
                        classLoader = getClass().getClassLoader();
                    } else {
                        classLoader = context.getClassLoader();
                    }
                    Class asSubclass = classLoader.loadClass(a).asSubclass(LayoutManager.class);
                    try {
                        objArr = new Object[]{context, attributeSet, Integer.valueOf(i), Integer.valueOf(i2)};
                        constructor = asSubclass.getConstructor(j);
                    } catch (Throwable e) {
                        constructor = asSubclass.getConstructor(new Class[0]);
                        objArr = null;
                    }
                    constructor.setAccessible(true);
                    setLayoutManager((LayoutManager) constructor.newInstance(objArr));
                } catch (Throwable e2) {
                    e2.initCause(e);
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + a, e2);
                } catch (Throwable e3) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + a, e3);
                } catch (Throwable e32) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + a, e32);
                } catch (Throwable e322) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + a, e322);
                } catch (Throwable e3222) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + a, e3222);
                } catch (Throwable e32222) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + a, e32222);
                }
            }
        }
    }

    private String a(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        }
        return !str.contains(".") ? RecyclerView.class.getPackage().getName() + '.' + str : str;
    }

    private void s() {
        this.c = new b(new b(this) {
            final /* synthetic */ RecyclerView a;

            {
                this.a = r1;
            }

            public int a() {
                return this.a.getChildCount();
            }

            public void a(View view, int i) {
                this.a.addView(view, i);
                this.a.i(view);
            }

            public int a(View view) {
                return this.a.indexOfChild(view);
            }

            public void a(int i) {
                View childAt = this.a.getChildAt(i);
                if (childAt != null) {
                    this.a.h(childAt);
                }
                this.a.removeViewAt(i);
            }

            public View b(int i) {
                return this.a.getChildAt(i);
            }

            public void b() {
                int a = a();
                for (int i = 0; i < a; i++) {
                    this.a.h(b(i));
                }
                this.a.removeAllViews();
            }

            public s b(View view) {
                return RecyclerView.b(view);
            }

            public void a(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
                s b = RecyclerView.b(view);
                if (b != null) {
                    if (b.r() || b.c()) {
                        b.m();
                    } else {
                        throw new IllegalArgumentException("Called attach on a child which is not detached: " + b);
                    }
                }
                this.a.attachViewToParent(view, i, layoutParams);
            }

            public void c(int i) {
                View b = b(i);
                if (b != null) {
                    s b2 = RecyclerView.b(b);
                    if (b2 != null) {
                        if (!b2.r() || b2.c()) {
                            b2.b(256);
                        } else {
                            throw new IllegalArgumentException("called detach on an already detached child " + b2);
                        }
                    }
                }
                this.a.detachViewFromParent(i);
            }

            public void c(View view) {
                s b = RecyclerView.b(view);
                if (b != null) {
                    b.z();
                }
            }

            public void d(View view) {
                s b = RecyclerView.b(view);
                if (b != null) {
                    b.A();
                }
            }
        });
    }

    void a() {
        this.b = new a(new a(this) {
            final /* synthetic */ RecyclerView a;

            {
                this.a = r1;
            }

            public s a(int i) {
                s a = this.a.a(i, true);
                if (a == null || this.a.c.c(a.a)) {
                    return null;
                }
                return a;
            }

            public void a(int i, int i2) {
                this.a.a(i, i2, true);
                this.a.g = true;
                p.a(this.a.f, i2);
            }

            public void b(int i, int i2) {
                this.a.a(i, i2, false);
                this.a.g = true;
            }

            public void a(int i, int i2, Object obj) {
                this.a.a(i, i2, obj);
                this.a.h = true;
            }

            public void a(b bVar) {
                c(bVar);
            }

            void c(b bVar) {
                switch (bVar.a) {
                    case 1:
                        this.a.q.a(this.a, bVar.b, bVar.d);
                        return;
                    case 2:
                        this.a.q.b(this.a, bVar.b, bVar.d);
                        return;
                    case 4:
                        this.a.q.a(this.a, bVar.b, bVar.d, bVar.c);
                        return;
                    case 8:
                        this.a.q.a(this.a, bVar.b, bVar.d, 1);
                        return;
                    default:
                        return;
                }
            }

            public void b(b bVar) {
                c(bVar);
            }

            public void c(int i, int i2) {
                this.a.e(i, i2);
                this.a.g = true;
            }

            public void d(int i, int i2) {
                this.a.d(i, i2);
                this.a.g = true;
            }
        });
    }

    public void setHasFixedSize(boolean z) {
        this.w = z;
    }

    public void setClipToPadding(boolean z) {
        if (z != this.m) {
            h();
        }
        this.m = z;
        super.setClipToPadding(z);
        if (this.x) {
            requestLayout();
        }
    }

    public void setScrollingTouchSlop(int i) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        switch (i) {
            case 0:
                break;
            case 1:
                this.U = ai.a(viewConfiguration);
                return;
            default:
                Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i + "; using default value");
                break;
        }
        this.U = viewConfiguration.getScaledTouchSlop();
    }

    public void setAdapter(a aVar) {
        setLayoutFrozen(false);
        a(aVar, false, true);
        requestLayout();
    }

    private void a(a aVar, boolean z, boolean z2) {
        if (this.p != null) {
            this.p.b(this.k);
            this.p.b(this);
        }
        if (!z || z2) {
            if (this.e != null) {
                this.e.c();
            }
            if (this.q != null) {
                this.q.c(this.a);
                this.q.b(this.a);
            }
            this.a.a();
        }
        this.b.a();
        a aVar2 = this.p;
        this.p = aVar;
        if (aVar != null) {
            aVar.a(this.k);
            aVar.a(this);
        }
        if (this.q != null) {
            this.q.a(aVar2, this.p);
        }
        this.a.a(aVar2, this.p, z);
        this.f.f = true;
        o();
    }

    public a getAdapter() {
        return this.p;
    }

    public void setRecyclerListener(m mVar) {
        this.r = mVar;
    }

    public int getBaseline() {
        if (this.q != null) {
            return this.q.q();
        }
        return super.getBaseline();
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        if (layoutManager != this.q) {
            if (this.q != null) {
                if (this.v) {
                    this.q.b(this, this.a);
                }
                this.q.b(null);
            }
            this.a.a();
            this.c.a();
            this.q = layoutManager;
            if (layoutManager != null) {
                if (layoutManager.r != null) {
                    throw new IllegalArgumentException("LayoutManager " + layoutManager + " is already attached to a RecyclerView: " + layoutManager.r);
                }
                this.q.b(this);
                if (this.v) {
                    this.q.c(this);
                }
            }
            requestLayout();
        }
    }

    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.l != null) {
            savedState.a(this.l);
        } else if (this.q != null) {
            savedState.a = this.q.c();
        } else {
            savedState.a = null;
        }
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        this.l = (SavedState) parcelable;
        super.onRestoreInstanceState(this.l.getSuperState());
        if (this.q != null && this.l.a != null) {
            this.q.a(this.l.a);
        }
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    private void b(s sVar) {
        View view = sVar.a;
        boolean z = view.getParent() == this;
        this.a.d(a(view));
        if (sVar.r()) {
            this.c.a(view, -1, view.getLayoutParams(), true);
        } else if (z) {
            this.c.d(view);
        } else {
            this.c.a(view, true);
        }
    }

    private boolean g(View view) {
        b();
        boolean f = this.c.f(view);
        if (f) {
            s b = b(view);
            this.a.d(b);
            this.a.b(b);
        }
        a(false);
        return f;
    }

    public LayoutManager getLayoutManager() {
        return this.q;
    }

    public k getRecycledViewPool() {
        return this.a.f();
    }

    public void setRecycledViewPool(k kVar) {
        this.a.a(kVar);
    }

    public void setViewCacheExtension(q qVar) {
        this.a.a(qVar);
    }

    public void setItemViewCacheSize(int i) {
        this.a.a(i);
    }

    public int getScrollState() {
        return this.N;
    }

    private void setScrollState(int i) {
        if (i != this.N) {
            this.N = i;
            if (i != 2) {
                v();
            }
            d(i);
        }
    }

    public void a(g gVar, int i) {
        if (this.q != null) {
            this.q.a("Cannot add item decoration during a scroll  or layout");
        }
        if (this.s.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i < 0) {
            this.s.add(gVar);
        } else {
            this.s.add(i, gVar);
        }
        l();
        requestLayout();
    }

    public void a(g gVar) {
        a(gVar, -1);
    }

    public void setChildDrawingOrderCallback(d dVar) {
        if (dVar != this.ah) {
            this.ah = dVar;
            setChildrenDrawingOrderEnabled(this.ah != null);
        }
    }

    @Deprecated
    public void setOnScrollListener(j jVar) {
        this.ac = jVar;
    }

    private void e(int i) {
        if (this.q != null) {
            this.q.c(i);
            awakenScrollBars();
        }
    }

    public void scrollTo(int i, int i2) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollBy(int i, int i2) {
        if (this.q == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.A) {
            boolean d = this.q.d();
            boolean e = this.q.e();
            if (d || e) {
                if (!d) {
                    i = 0;
                }
                if (!e) {
                    i2 = 0;
                }
                a(i, i2, null);
            }
        }
    }

    private void t() {
        if (!this.x) {
            return;
        }
        if (this.H) {
            android.support.v4.os.h.a("RV FullInvalidate");
            k();
            android.support.v4.os.h.a();
        } else if (!this.b.d()) {
        } else {
            if (this.b.a(4) && !this.b.a(11)) {
                android.support.v4.os.h.a("RV PartialInvalidate");
                b();
                this.b.b();
                if (!this.z) {
                    if (u()) {
                        k();
                    } else {
                        this.b.c();
                    }
                }
                a(true);
                android.support.v4.os.h.a();
            } else if (this.b.d()) {
                android.support.v4.os.h.a("RV FullInvalidate");
                k();
                android.support.v4.os.h.a();
            }
        }
    }

    private boolean u() {
        int b = this.c.b();
        for (int i = 0; i < b; i++) {
            s b2 = b(this.c.b(i));
            if (b2 != null && !b2.c() && b2.x()) {
                return true;
            }
        }
        return false;
    }

    boolean a(int i, int i2, MotionEvent motionEvent) {
        int a;
        int i3;
        int i4;
        int i5;
        t();
        if (this.p != null) {
            int b;
            b();
            z();
            android.support.v4.os.h.a("RV Scroll");
            if (i != 0) {
                a = this.q.a(i, this.a, this.f);
                i3 = i - a;
            } else {
                a = 0;
                i3 = 0;
            }
            if (i2 != 0) {
                b = this.q.b(i2, this.a, this.f);
                i4 = i2 - b;
            } else {
                b = 0;
                i4 = 0;
            }
            android.support.v4.os.h.a();
            G();
            A();
            a(false);
            i5 = i4;
            i4 = a;
            a = b;
        } else {
            a = 0;
            i4 = 0;
            i5 = 0;
            i3 = 0;
        }
        if (!this.s.isEmpty()) {
            invalidate();
        }
        if (dispatchNestedScroll(i4, a, i3, i5, this.ak)) {
            this.S -= this.ak[0];
            this.T -= this.ak[1];
            if (motionEvent != null) {
                motionEvent.offsetLocation((float) this.ak[0], (float) this.ak[1]);
            }
            int[] iArr = this.am;
            iArr[0] = iArr[0] + this.ak[0];
            iArr = this.am;
            iArr[1] = iArr[1] + this.ak[1];
        } else if (z.a(this) != 2) {
            if (motionEvent != null) {
                a(motionEvent.getX(), (float) i3, motionEvent.getY(), (float) i5);
            }
            h(i, i2);
        }
        if (!(i4 == 0 && a == 0)) {
            g(i4, a);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        if (i4 == 0 && a == 0) {
            return false;
        }
        return true;
    }

    public int computeHorizontalScrollOffset() {
        return this.q.d() ? this.q.b(this.f) : 0;
    }

    public int computeHorizontalScrollExtent() {
        return this.q.d() ? this.q.d(this.f) : 0;
    }

    public int computeHorizontalScrollRange() {
        return this.q.d() ? this.q.f(this.f) : 0;
    }

    public int computeVerticalScrollOffset() {
        return this.q.e() ? this.q.c(this.f) : 0;
    }

    public int computeVerticalScrollExtent() {
        return this.q.e() ? this.q.e(this.f) : 0;
    }

    public int computeVerticalScrollRange() {
        return this.q.e() ? this.q.g(this.f) : 0;
    }

    void b() {
        if (!this.y) {
            this.y = true;
            if (!this.A) {
                this.z = false;
            }
        }
    }

    void a(boolean z) {
        if (this.y) {
            if (!(!z || !this.z || this.A || this.q == null || this.p == null)) {
                k();
            }
            this.y = false;
            if (!this.A) {
                this.z = false;
            }
        }
    }

    public void setLayoutFrozen(boolean z) {
        if (z != this.A) {
            a("Do not setLayoutFrozen in layout or scroll");
            if (z) {
                long uptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
                this.A = z;
                this.B = true;
                c();
                return;
            }
            this.A = z;
            if (!(!this.z || this.q == null || this.p == null)) {
                requestLayout();
            }
            this.z = false;
        }
    }

    public void a(int i, int i2) {
        int i3 = 0;
        if (this.q == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.A) {
            if (!this.q.d()) {
                i = 0;
            }
            if (this.q.e()) {
                i3 = i2;
            }
            if (i != 0 || i3 != 0) {
                this.ab.b(i, i3);
            }
        }
    }

    public boolean b(int i, int i2) {
        if (this.q == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        } else if (this.A) {
            return false;
        } else {
            boolean d = this.q.d();
            boolean e = this.q.e();
            if (!d || Math.abs(i) < this.V) {
                i = 0;
            }
            if (!e || Math.abs(i2) < this.V) {
                i2 = 0;
            }
            if ((i == 0 && i2 == 0) || dispatchNestedPreFling((float) i, (float) i2)) {
                return false;
            }
            d = d || e;
            dispatchNestedFling((float) i, (float) i2, d);
            if (!d) {
                return false;
            }
            this.ab.a(Math.max(-this.W, Math.min(i, this.W)), Math.max(-this.W, Math.min(i2, this.W)));
            return true;
        }
    }

    public void c() {
        setScrollState(0);
        v();
    }

    private void v() {
        this.ab.b();
        if (this.q != null) {
            this.q.z();
        }
    }

    public int getMinFlingVelocity() {
        return this.V;
    }

    public int getMaxFlingVelocity() {
        return this.W;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(float r8, float r9, float r10, float r11) {
        /*
        r7 = this;
        r6 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r0 = 1;
        r5 = 0;
        r1 = 0;
        r2 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1));
        if (r2 >= 0) goto L_0x0050;
    L_0x0009:
        r7.d();
        r2 = r7.J;
        r3 = -r9;
        r4 = r7.getWidth();
        r4 = (float) r4;
        r3 = r3 / r4;
        r4 = r7.getHeight();
        r4 = (float) r4;
        r4 = r10 / r4;
        r4 = r6 - r4;
        r2 = r2.a(r3, r4);
        if (r2 == 0) goto L_0x0025;
    L_0x0024:
        r1 = r0;
    L_0x0025:
        r2 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1));
        if (r2 >= 0) goto L_0x006f;
    L_0x0029:
        r7.f();
        r2 = r7.K;
        r3 = -r11;
        r4 = r7.getHeight();
        r4 = (float) r4;
        r3 = r3 / r4;
        r4 = r7.getWidth();
        r4 = (float) r4;
        r4 = r8 / r4;
        r2 = r2.a(r3, r4);
        if (r2 == 0) goto L_0x008e;
    L_0x0042:
        if (r0 != 0) goto L_0x004c;
    L_0x0044:
        r0 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1));
        if (r0 != 0) goto L_0x004c;
    L_0x0048:
        r0 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1));
        if (r0 == 0) goto L_0x004f;
    L_0x004c:
        android.support.v4.view.z.d(r7);
    L_0x004f:
        return;
    L_0x0050:
        r2 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1));
        if (r2 <= 0) goto L_0x0025;
    L_0x0054:
        r7.e();
        r2 = r7.L;
        r3 = r7.getWidth();
        r3 = (float) r3;
        r3 = r9 / r3;
        r4 = r7.getHeight();
        r4 = (float) r4;
        r4 = r10 / r4;
        r2 = r2.a(r3, r4);
        if (r2 == 0) goto L_0x0025;
    L_0x006d:
        r1 = r0;
        goto L_0x0025;
    L_0x006f:
        r2 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1));
        if (r2 <= 0) goto L_0x008e;
    L_0x0073:
        r7.g();
        r2 = r7.M;
        r3 = r7.getHeight();
        r3 = (float) r3;
        r3 = r11 / r3;
        r4 = r7.getWidth();
        r4 = (float) r4;
        r4 = r8 / r4;
        r4 = r6 - r4;
        r2 = r2.a(r3, r4);
        if (r2 != 0) goto L_0x0042;
    L_0x008e:
        r0 = r1;
        goto L_0x0042;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.a(float, float, float, float):void");
    }

    private void w() {
        int i = 0;
        if (this.J != null) {
            i = this.J.c();
        }
        if (this.K != null) {
            i |= this.K.c();
        }
        if (this.L != null) {
            i |= this.L.c();
        }
        if (this.M != null) {
            i |= this.M.c();
        }
        if (i != 0) {
            z.d(this);
        }
    }

    private void h(int i, int i2) {
        int i3 = 0;
        if (!(this.J == null || this.J.a() || i <= 0)) {
            i3 = this.J.c();
        }
        if (!(this.L == null || this.L.a() || i >= 0)) {
            i3 |= this.L.c();
        }
        if (!(this.K == null || this.K.a() || i2 <= 0)) {
            i3 |= this.K.c();
        }
        if (!(this.M == null || this.M.a() || i2 >= 0)) {
            i3 |= this.M.c();
        }
        if (i3 != 0) {
            z.d(this);
        }
    }

    void c(int i, int i2) {
        if (i < 0) {
            d();
            this.J.a(-i);
        } else if (i > 0) {
            e();
            this.L.a(i);
        }
        if (i2 < 0) {
            f();
            this.K.a(-i2);
        } else if (i2 > 0) {
            g();
            this.M.a(i2);
        }
        if (i != 0 || i2 != 0) {
            z.d(this);
        }
    }

    void d() {
        if (this.J == null) {
            this.J = new android.support.v4.widget.d(getContext());
            if (this.m) {
                this.J.a((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                this.J.a(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    void e() {
        if (this.L == null) {
            this.L = new android.support.v4.widget.d(getContext());
            if (this.m) {
                this.L.a((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                this.L.a(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    void f() {
        if (this.K == null) {
            this.K = new android.support.v4.widget.d(getContext());
            if (this.m) {
                this.K.a((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                this.K.a(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    void g() {
        if (this.M == null) {
            this.M = new android.support.v4.widget.d(getContext());
            if (this.m) {
                this.M.a((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                this.M.a(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    void h() {
        this.M = null;
        this.K = null;
        this.L = null;
        this.J = null;
    }

    public View focusSearch(View view, int i) {
        View d = this.q.d(view, i);
        if (d != null) {
            return d;
        }
        d = FocusFinder.getInstance().findNextFocus(this, view, i);
        if (!(d != null || this.p == null || this.q == null || j() || this.A)) {
            b();
            d = this.q.a(view, i, this.a, this.f);
            a(false);
        }
        return d == null ? super.focusSearch(view, i) : d;
    }

    public void requestChildFocus(View view, View view2) {
        if (!(this.q.a(this, this.f, view, view2) || view2 == null)) {
            Rect rect;
            boolean z;
            this.o.set(0, 0, view2.getWidth(), view2.getHeight());
            android.view.ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            if (layoutParams instanceof LayoutParams) {
                LayoutParams layoutParams2 = (LayoutParams) layoutParams;
                if (!layoutParams2.c) {
                    Rect rect2 = layoutParams2.b;
                    rect = this.o;
                    rect.left -= rect2.left;
                    rect = this.o;
                    rect.right += rect2.right;
                    rect = this.o;
                    rect.top -= rect2.top;
                    rect = this.o;
                    rect.bottom = rect2.bottom + rect.bottom;
                }
            }
            offsetDescendantRectToMyCoords(view2, this.o);
            offsetRectIntoDescendantCoords(view, this.o);
            rect = this.o;
            if (this.x) {
                z = false;
            } else {
                z = true;
            }
            requestChildRectangleOnScreen(view, rect, z);
        }
        super.requestChildFocus(view, view2);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.q.a(this, view, rect, z);
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        if (this.q == null || !this.q.a(this, (ArrayList) arrayList, i, i2)) {
            super.addFocusables(arrayList, i, i2);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.I = 0;
        this.v = true;
        this.x = false;
        if (this.q != null) {
            this.q.c(this);
        }
        this.af = false;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.e != null) {
            this.e.c();
        }
        this.x = false;
        c();
        this.v = false;
        if (this.q != null) {
            this.q.b(this, this.a);
        }
        removeCallbacks(this.an);
        this.d.b();
    }

    public boolean isAttachedToWindow() {
        return this.v;
    }

    void a(String str) {
        if (!j()) {
            return;
        }
        if (str == null) {
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling");
        }
        throw new IllegalStateException(str);
    }

    private boolean a(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 3 || action == 0) {
            this.u = null;
        }
        int size = this.t.size();
        int i = 0;
        while (i < size) {
            i iVar = (i) this.t.get(i);
            if (!iVar.a(this, motionEvent) || action == 3) {
                i++;
            } else {
                this.u = iVar;
                return true;
            }
        }
        return false;
    }

    private boolean b(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (this.u != null) {
            if (action == 0) {
                this.u = null;
            } else {
                this.u.b(this, motionEvent);
                if (action == 3 || action == 1) {
                    this.u = null;
                }
                return true;
            }
        }
        if (action != 0) {
            int size = this.t.size();
            for (int i = 0; i < size; i++) {
                i iVar = (i) this.t.get(i);
                if (iVar.a(this, motionEvent)) {
                    this.u = iVar;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int i = -1;
        boolean z = true;
        if (this.A) {
            return false;
        }
        if (a(motionEvent)) {
            y();
            return true;
        } else if (this.q == null) {
            return false;
        } else {
            boolean d = this.q.d();
            boolean e = this.q.e();
            if (this.P == null) {
                this.P = VelocityTracker.obtain();
            }
            this.P.addMovement(motionEvent);
            int a = android.support.v4.view.n.a(motionEvent);
            int b = android.support.v4.view.n.b(motionEvent);
            int i2;
            switch (a) {
                case 0:
                    if (this.B) {
                        this.B = false;
                    }
                    this.O = android.support.v4.view.n.b(motionEvent, 0);
                    i = (int) (motionEvent.getX() + 0.5f);
                    this.S = i;
                    this.Q = i;
                    i = (int) (motionEvent.getY() + 0.5f);
                    this.T = i;
                    this.R = i;
                    if (this.N == 2) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        setScrollState(1);
                    }
                    int[] iArr = this.am;
                    this.am[1] = 0;
                    iArr[0] = 0;
                    if (d) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    if (e) {
                        i2 |= 2;
                    }
                    startNestedScroll(i2);
                    break;
                case 1:
                    this.P.clear();
                    stopNestedScroll();
                    break;
                case 2:
                    a = android.support.v4.view.n.a(motionEvent, this.O);
                    if (a >= 0) {
                        b = (int) (android.support.v4.view.n.c(motionEvent, a) + 0.5f);
                        a = (int) (android.support.v4.view.n.d(motionEvent, a) + 0.5f);
                        if (this.N != 1) {
                            b -= this.Q;
                            a -= this.R;
                            if (!d || Math.abs(b) <= this.U) {
                                d = false;
                            } else {
                                this.S = ((b < 0 ? -1 : 1) * this.U) + this.Q;
                                d = true;
                            }
                            if (e && Math.abs(a) > this.U) {
                                i2 = this.R;
                                int i3 = this.U;
                                if (a >= 0) {
                                    i = 1;
                                }
                                this.T = i2 + (i * i3);
                                d = true;
                            }
                            if (d) {
                                setScrollState(1);
                                break;
                            }
                        }
                    }
                    Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.O + " not found. Did any MotionEvents get skipped?");
                    return false;
                    break;
                case 3:
                    y();
                    break;
                case 5:
                    this.O = android.support.v4.view.n.b(motionEvent, b);
                    i2 = (int) (android.support.v4.view.n.c(motionEvent, b) + 0.5f);
                    this.S = i2;
                    this.Q = i2;
                    i2 = (int) (android.support.v4.view.n.d(motionEvent, b) + 0.5f);
                    this.T = i2;
                    this.R = i2;
                    break;
                case 6:
                    c(motionEvent);
                    break;
            }
            if (this.N != 1) {
                z = false;
            }
            return z;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        int size = this.t.size();
        for (int i = 0; i < size; i++) {
            ((i) this.t.get(i)).a(z);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.A || this.B) {
            return false;
        }
        if (b(motionEvent)) {
            y();
            return true;
        } else if (this.q == null) {
            return false;
        } else {
            boolean d = this.q.d();
            boolean e = this.q.e();
            if (this.P == null) {
                this.P = VelocityTracker.obtain();
            }
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            int a = android.support.v4.view.n.a(motionEvent);
            int b = android.support.v4.view.n.b(motionEvent);
            if (a == 0) {
                int[] iArr = this.am;
                this.am[1] = 0;
                iArr[0] = 0;
            }
            obtain.offsetLocation((float) this.am[0], (float) this.am[1]);
            switch (a) {
                case 0:
                    this.O = android.support.v4.view.n.b(motionEvent, 0);
                    a = (int) (motionEvent.getX() + 0.5f);
                    this.S = a;
                    this.Q = a;
                    a = (int) (motionEvent.getY() + 0.5f);
                    this.T = a;
                    this.R = a;
                    if (d) {
                        a = 1;
                    } else {
                        a = 0;
                    }
                    if (e) {
                        a |= 2;
                    }
                    startNestedScroll(a);
                    break;
                case 1:
                    this.P.addMovement(obtain);
                    this.P.computeCurrentVelocity(1000, (float) this.W);
                    float f = d ? -x.a(this.P, this.O) : 0.0f;
                    float f2;
                    if (e) {
                        f2 = -x.b(this.P, this.O);
                    } else {
                        f2 = 0.0f;
                    }
                    if ((f == 0.0f && r0 == 0.0f) || !b((int) f, (int) r0)) {
                        setScrollState(0);
                    }
                    x();
                    z = true;
                    break;
                case 2:
                    a = android.support.v4.view.n.a(motionEvent, this.O);
                    if (a >= 0) {
                        int c = (int) (android.support.v4.view.n.c(motionEvent, a) + 0.5f);
                        int d2 = (int) (android.support.v4.view.n.d(motionEvent, a) + 0.5f);
                        int i = this.S - c;
                        a = this.T - d2;
                        if (dispatchNestedPreScroll(i, a, this.al, this.ak)) {
                            i -= this.al[0];
                            a -= this.al[1];
                            obtain.offsetLocation((float) this.ak[0], (float) this.ak[1]);
                            int[] iArr2 = this.am;
                            iArr2[0] = iArr2[0] + this.ak[0];
                            iArr2 = this.am;
                            iArr2[1] = iArr2[1] + this.ak[1];
                        }
                        if (this.N != 1) {
                            boolean z2;
                            if (!d || Math.abs(i) <= this.U) {
                                z2 = false;
                            } else {
                                if (i > 0) {
                                    i -= this.U;
                                } else {
                                    i += this.U;
                                }
                                z2 = true;
                            }
                            if (e && Math.abs(a) > this.U) {
                                if (a > 0) {
                                    a -= this.U;
                                } else {
                                    a += this.U;
                                }
                                z2 = true;
                            }
                            if (z2) {
                                setScrollState(1);
                            }
                        }
                        if (this.N == 1) {
                            this.S = c - this.ak[0];
                            this.T = d2 - this.ak[1];
                            if (!d) {
                                i = 0;
                            }
                            if (!e) {
                                a = 0;
                            }
                            if (a(i, a, obtain)) {
                                getParent().requestDisallowInterceptTouchEvent(true);
                                break;
                            }
                        }
                    }
                    Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.O + " not found. Did any MotionEvents get skipped?");
                    return false;
                    break;
                case 3:
                    y();
                    break;
                case 5:
                    this.O = android.support.v4.view.n.b(motionEvent, b);
                    a = (int) (android.support.v4.view.n.c(motionEvent, b) + 0.5f);
                    this.S = a;
                    this.Q = a;
                    a = (int) (android.support.v4.view.n.d(motionEvent, b) + 0.5f);
                    this.T = a;
                    this.R = a;
                    break;
                case 6:
                    c(motionEvent);
                    break;
            }
            if (!z) {
                this.P.addMovement(obtain);
            }
            obtain.recycle();
            return true;
        }
    }

    private void x() {
        if (this.P != null) {
            this.P.clear();
        }
        stopNestedScroll();
        w();
    }

    private void y() {
        x();
        setScrollState(0);
    }

    private void c(MotionEvent motionEvent) {
        int b = android.support.v4.view.n.b(motionEvent);
        if (android.support.v4.view.n.b(motionEvent, b) == this.O) {
            b = b == 0 ? 1 : 0;
            this.O = android.support.v4.view.n.b(motionEvent, b);
            int c = (int) (android.support.v4.view.n.c(motionEvent, b) + 0.5f);
            this.S = c;
            this.Q = c;
            b = (int) (android.support.v4.view.n.d(motionEvent, b) + 0.5f);
            this.T = b;
            this.R = b;
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (!(this.q == null || this.A || (android.support.v4.view.n.d(motionEvent) & 2) == 0 || motionEvent.getAction() != 8)) {
            float f;
            float e;
            if (this.q.e()) {
                f = -android.support.v4.view.n.e(motionEvent, 9);
            } else {
                f = 0.0f;
            }
            if (this.q.d()) {
                e = android.support.v4.view.n.e(motionEvent, 10);
            } else {
                e = 0.0f;
            }
            if (!(f == 0.0f && e == 0.0f)) {
                float scrollFactor = getScrollFactor();
                a((int) (e * scrollFactor), (int) (f * scrollFactor), motionEvent);
            }
        }
        return false;
    }

    private float getScrollFactor() {
        if (this.aa == Float.MIN_VALUE) {
            TypedValue typedValue = new TypedValue();
            if (!getContext().getTheme().resolveAttribute(16842829, typedValue, true)) {
                return 0.0f;
            }
            this.aa = typedValue.getDimension(getContext().getResources().getDisplayMetrics());
        }
        return this.aa;
    }

    protected void onMeasure(int i, int i2) {
        if (this.D) {
            b();
            E();
            if (this.f.i) {
                this.f.g = true;
            } else {
                this.b.e();
                this.f.g = false;
            }
            this.D = false;
            a(false);
        }
        if (this.p != null) {
            this.f.a = this.p.a();
        } else {
            this.f.a = 0;
        }
        if (this.q == null) {
            i(i, i2);
        } else {
            this.q.a(this.a, this.f, i, i2);
        }
        this.f.g = false;
    }

    private void i(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        switch (mode) {
            case Integer.MIN_VALUE:
            case 1073741824:
                break;
            default:
                size = z.n(this);
                break;
        }
        switch (mode2) {
            case Integer.MIN_VALUE:
            case 1073741824:
                break;
            default:
                size2 = z.o(this);
                break;
        }
        setMeasuredDimension(size, size2);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3 || i2 != i4) {
            h();
        }
    }

    public void setItemAnimator(e eVar) {
        if (this.e != null) {
            this.e.c();
            this.e.a(null);
        }
        this.e = eVar;
        if (this.e != null) {
            this.e.a(this.ae);
        }
    }

    private void z() {
        this.I++;
    }

    private void A() {
        this.I--;
        if (this.I < 1) {
            this.I = 0;
            B();
        }
    }

    boolean i() {
        return this.F != null && this.F.isEnabled();
    }

    private void B() {
        int i = this.C;
        this.C = 0;
        if (i != 0 && i()) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain();
            obtain.setEventType(2048);
            android.support.v4.view.a.a.a(obtain, i);
            sendAccessibilityEventUnchecked(obtain);
        }
    }

    public boolean j() {
        return this.I > 0;
    }

    boolean a(AccessibilityEvent accessibilityEvent) {
        int i = 0;
        if (!j()) {
            return false;
        }
        int b;
        if (accessibilityEvent != null) {
            b = android.support.v4.view.a.a.b(accessibilityEvent);
        } else {
            b = 0;
        }
        if (b != 0) {
            i = b;
        }
        this.C = i | this.C;
        return true;
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (!a(accessibilityEvent)) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        }
    }

    public e getItemAnimator() {
        return this.e;
    }

    private void C() {
        if (!this.af && this.v) {
            z.a((View) this, this.an);
            this.af = true;
        }
    }

    private boolean D() {
        return this.e != null && this.q.b();
    }

    private void E() {
        boolean z;
        boolean z2 = true;
        if (this.H) {
            this.b.a();
            o();
            this.q.a(this);
        }
        if (this.e == null || !this.q.b()) {
            this.b.e();
        } else {
            this.b.b();
        }
        boolean z3;
        if (this.g || this.h) {
            z3 = true;
        } else {
            z3 = false;
        }
        p pVar = this.f;
        if (!this.x || this.e == null || (!(this.H || r0 || this.q.a) || (this.H && !this.p.b()))) {
            z = false;
        } else {
            z = true;
        }
        pVar.h = z;
        p pVar2 = this.f;
        if (!(this.f.h && r0 && !this.H && D())) {
            z2 = false;
        }
        pVar2.i = z2;
    }

    void k() {
        if (this.p == null) {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
        } else if (this.q == null) {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
        } else {
            int b;
            int i;
            s b2;
            this.d.a();
            b();
            z();
            E();
            p pVar = this.f;
            boolean z = this.f.h && this.h;
            pVar.j = z;
            this.h = false;
            this.g = false;
            this.f.g = this.f.i;
            this.f.a = this.p.a();
            a(this.ai);
            if (this.f.h) {
                b = this.c.b();
                for (i = 0; i < b; i++) {
                    b2 = b(this.c.b(i));
                    if (!b2.c() && (!b2.n() || this.p.b())) {
                        this.d.a(b2, this.e.a(this.f, b2, e.d(b2), b2.u()));
                        if (!(!this.f.j || !b2.x() || b2.q() || b2.c() || b2.n())) {
                            this.d.a(a(b2), b2);
                        }
                    }
                }
            }
            if (this.f.i) {
                m();
                z = this.f.f;
                this.f.f = false;
                this.q.c(this.a, this.f);
                this.f.f = z;
                for (i = 0; i < this.c.b(); i++) {
                    b2 = b(this.c.b(i));
                    if (!(b2.c() || this.d.b(b2))) {
                        b = e.d(b2);
                        boolean a = b2.a(8192);
                        if (!a) {
                            b |= 4096;
                        }
                        c a2 = this.e.a(this.f, b2, b, b2.u());
                        if (a) {
                            a(b2, a2);
                        } else {
                            this.d.b(b2, a2);
                        }
                    }
                }
                n();
                this.b.c();
            } else {
                n();
            }
            this.f.a = this.p.a();
            this.f.e = 0;
            this.f.g = false;
            this.q.c(this.a, this.f);
            this.f.f = false;
            this.l = null;
            pVar = this.f;
            z = this.f.h && this.e != null;
            pVar.h = z;
            if (this.f.h) {
                b = this.c.b();
                for (i = 0; i < b; i++) {
                    b2 = b(this.c.b(i));
                    if (!b2.c()) {
                        long a3 = a(b2);
                        c a4 = this.e.a(this.f, b2);
                        s a5 = this.d.a(a3);
                        if (a5 == null || a5.c()) {
                            this.d.c(b2, a4);
                        } else {
                            a(a5, b2, this.d.a(a5), a4);
                        }
                    }
                }
                this.d.a(this.ap);
            }
            a(false);
            this.q.b(this.a);
            this.f.d = this.f.a;
            this.H = false;
            this.f.h = false;
            this.f.i = false;
            A();
            this.q.a = false;
            if (this.a.d != null) {
                this.a.d.clear();
            }
            this.d.a();
            if (j(this.ai[0], this.ai[1])) {
                g(0, 0);
            }
        }
    }

    private void a(s sVar, c cVar) {
        sVar.a(0, 8192);
        if (this.f.j && sVar.x() && !sVar.q() && !sVar.c()) {
            this.d.a(a(sVar), sVar);
        }
        this.d.a(sVar, cVar);
    }

    private void a(int[] iArr) {
        int b = this.c.b();
        if (b == 0) {
            iArr[0] = 0;
            iArr[1] = 0;
            return;
        }
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        int i3 = 0;
        while (i3 < b) {
            int i4;
            s b2 = b(this.c.b(i3));
            if (b2.c()) {
                i4 = i;
            } else {
                i4 = b2.d();
                if (i4 < i) {
                    i = i4;
                }
                if (i4 > i2) {
                    i2 = i4;
                    i4 = i;
                } else {
                    i4 = i;
                }
            }
            i3++;
            i = i4;
        }
        iArr[0] = i;
        iArr[1] = i2;
    }

    private boolean j(int i, int i2) {
        int b = this.c.b();
        if (b != 0) {
            for (int i3 = 0; i3 < b; i3++) {
                s b2 = b(this.c.b(i3));
                if (!b2.c()) {
                    int d = b2.d();
                    if (d < i || d > i2) {
                        return true;
                    }
                }
            }
            return false;
        } else if (i == 0 && i2 == 0) {
            return false;
        } else {
            return true;
        }
    }

    protected void removeDetachedView(View view, boolean z) {
        s b = b(view);
        if (b != null) {
            if (b.r()) {
                b.m();
            } else if (!b.c()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + b);
            }
        }
        h(view);
        super.removeDetachedView(view, z);
    }

    long a(s sVar) {
        return this.p.b() ? sVar.g() : (long) sVar.b;
    }

    private void a(s sVar, c cVar, c cVar2) {
        sVar.a(false);
        if (this.e.b(sVar, cVar, cVar2)) {
            C();
        }
    }

    private void b(s sVar, c cVar, c cVar2) {
        b(sVar);
        sVar.a(false);
        if (this.e.a(sVar, cVar, cVar2)) {
            C();
        }
    }

    private void a(s sVar, s sVar2, c cVar, c cVar2) {
        sVar.a(false);
        if (sVar != sVar2) {
            sVar.g = sVar2;
            b(sVar);
            this.a.d(sVar);
            sVar2.a(false);
            sVar2.h = sVar;
        }
        if (this.e.a(sVar, sVar2, cVar, cVar2)) {
            C();
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        b();
        android.support.v4.os.h.a("RV OnLayout");
        k();
        android.support.v4.os.h.a();
        a(false);
        this.x = true;
    }

    public void requestLayout() {
        if (this.y || this.A) {
            this.z = true;
        } else {
            super.requestLayout();
        }
    }

    void l() {
        int c = this.c.c();
        for (int i = 0; i < c; i++) {
            ((LayoutParams) this.c.c(i).getLayoutParams()).c = true;
        }
        this.a.j();
    }

    public void draw(Canvas canvas) {
        int i;
        int i2;
        int i3 = 1;
        int i4 = 0;
        super.draw(canvas);
        int size = this.s.size();
        for (i = 0; i < size; i++) {
            ((g) this.s.get(i)).b(canvas, this, this.f);
        }
        if (this.J == null || this.J.a()) {
            i2 = 0;
        } else {
            i = canvas.save();
            i2 = this.m ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((float) (i2 + (-getHeight())), 0.0f);
            if (this.J == null || !this.J.a(canvas)) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            canvas.restoreToCount(i);
        }
        if (!(this.K == null || this.K.a())) {
            size = canvas.save();
            if (this.m) {
                canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            }
            if (this.K == null || !this.K.a(canvas)) {
                i = 0;
            } else {
                i = 1;
            }
            i2 |= i;
            canvas.restoreToCount(size);
        }
        if (!(this.L == null || this.L.a())) {
            size = canvas.save();
            int width = getWidth();
            if (this.m) {
                i = getPaddingTop();
            } else {
                i = 0;
            }
            canvas.rotate(90.0f);
            canvas.translate((float) (-i), (float) (-width));
            if (this.L == null || !this.L.a(canvas)) {
                i = 0;
            } else {
                i = 1;
            }
            i2 |= i;
            canvas.restoreToCount(size);
        }
        if (!(this.M == null || this.M.a())) {
            i = canvas.save();
            canvas.rotate(180.0f);
            if (this.m) {
                canvas.translate((float) ((-getWidth()) + getPaddingRight()), (float) ((-getHeight()) + getPaddingBottom()));
            } else {
                canvas.translate((float) (-getWidth()), (float) (-getHeight()));
            }
            if (this.M != null && this.M.a(canvas)) {
                i4 = 1;
            }
            i2 |= i4;
            canvas.restoreToCount(i);
        }
        if (i2 != 0 || this.e == null || this.s.size() <= 0 || !this.e.b()) {
            i3 = i2;
        }
        if (i3 != 0) {
            z.d(this);
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.s.size();
        for (int i = 0; i < size; i++) {
            ((g) this.s.get(i)).a(canvas, this, this.f);
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && this.q.a((LayoutParams) layoutParams);
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        if (this.q != null) {
            return this.q.a();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager");
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        if (this.q != null) {
            return this.q.a(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager");
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (this.q != null) {
            return this.q.a(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager");
    }

    void m() {
        int c = this.c.c();
        for (int i = 0; i < c; i++) {
            s b = b(this.c.c(i));
            if (!b.c()) {
                b.b();
            }
        }
    }

    void n() {
        int c = this.c.c();
        for (int i = 0; i < c; i++) {
            s b = b(this.c.c(i));
            if (!b.c()) {
                b.a();
            }
        }
        this.a.i();
    }

    void d(int i, int i2) {
        int i3;
        int c = this.c.c();
        int i4;
        int i5;
        if (i < i2) {
            i3 = -1;
            i4 = i2;
            i5 = i;
        } else {
            i3 = 1;
            i4 = i;
            i5 = i2;
        }
        for (int i6 = 0; i6 < c; i6++) {
            s b = b(this.c.c(i6));
            if (b != null && b.b >= r3 && b.b <= r2) {
                if (b.b == i) {
                    b.a(i2 - i, false);
                } else {
                    b.a(i3, false);
                }
                this.f.f = true;
            }
        }
        this.a.a(i, i2);
        requestLayout();
    }

    void e(int i, int i2) {
        int c = this.c.c();
        for (int i3 = 0; i3 < c; i3++) {
            s b = b(this.c.c(i3));
            if (!(b == null || b.c() || b.b < i)) {
                b.a(i2, false);
                this.f.f = true;
            }
        }
        this.a.b(i, i2);
        requestLayout();
    }

    void a(int i, int i2, boolean z) {
        int i3 = i + i2;
        int c = this.c.c();
        for (int i4 = 0; i4 < c; i4++) {
            s b = b(this.c.c(i4));
            if (!(b == null || b.c())) {
                if (b.b >= i3) {
                    b.a(-i2, z);
                    this.f.f = true;
                } else if (b.b >= i) {
                    b.a(i - 1, -i2, z);
                    this.f.f = true;
                }
            }
        }
        this.a.b(i, i2, z);
        requestLayout();
    }

    void a(int i, int i2, Object obj) {
        int c = this.c.c();
        int i3 = i + i2;
        for (int i4 = 0; i4 < c; i4++) {
            View c2 = this.c.c(i4);
            s b = b(c2);
            if (b != null && !b.c() && b.b >= i && b.b < i3) {
                b.b(2);
                b.a(obj);
                ((LayoutParams) c2.getLayoutParams()).c = true;
            }
        }
        this.a.c(i, i2);
    }

    private boolean c(s sVar) {
        return this.e == null || this.e.g(sVar);
    }

    private void F() {
        if (!this.H) {
            this.H = true;
            int c = this.c.c();
            for (int i = 0; i < c; i++) {
                s b = b(this.c.c(i));
                if (!(b == null || b.c())) {
                    b.b(512);
                }
            }
            this.a.g();
        }
    }

    void o() {
        int c = this.c.c();
        for (int i = 0; i < c; i++) {
            s b = b(this.c.c(i));
            if (!(b == null || b.c())) {
                b.b(6);
            }
        }
        l();
        this.a.h();
    }

    public s a(View view) {
        Object parent = view.getParent();
        if (parent == null || parent == this) {
            return b(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    static s b(View view) {
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).a;
    }

    public int c(View view) {
        s b = b(view);
        return b != null ? b.d() : -1;
    }

    s a(int i, boolean z) {
        int c = this.c.c();
        for (int i2 = 0; i2 < c; i2++) {
            s b = b(this.c.c(i2));
            if (!(b == null || b.q())) {
                if (z) {
                    if (b.b == i) {
                        return b;
                    }
                } else if (b.d() == i) {
                    return b;
                }
            }
        }
        return null;
    }

    public boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(canvas, view, j);
    }

    public void a(int i) {
        int b = this.c.b();
        for (int i2 = 0; i2 < b; i2++) {
            this.c.b(i2).offsetTopAndBottom(i);
        }
    }

    public void d(View view) {
    }

    public void e(View view) {
    }

    public void b(int i) {
        int b = this.c.b();
        for (int i2 = 0; i2 < b; i2++) {
            this.c.b(i2).offsetLeftAndRight(i);
        }
    }

    Rect f(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.c) {
            return layoutParams.b;
        }
        Rect rect = layoutParams.b;
        rect.set(0, 0, 0, 0);
        int size = this.s.size();
        for (int i = 0; i < size; i++) {
            this.o.set(0, 0, 0, 0);
            ((g) this.s.get(i)).a(this.o, view, this, this.f);
            rect.left += this.o.left;
            rect.top += this.o.top;
            rect.right += this.o.right;
            rect.bottom += this.o.bottom;
        }
        layoutParams.c = false;
        return rect;
    }

    public void f(int i, int i2) {
    }

    void g(int i, int i2) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        f(i, i2);
        if (this.ac != null) {
            this.ac.a(this, i, i2);
        }
        if (this.ad != null) {
            for (scrollY = this.ad.size() - 1; scrollY >= 0; scrollY--) {
                ((j) this.ad.get(scrollY)).a(this, i, i2);
            }
        }
    }

    public void c(int i) {
    }

    void d(int i) {
        if (this.q != null) {
            this.q.i(i);
        }
        c(i);
        if (this.ac != null) {
            this.ac.a(this, i);
        }
        if (this.ad != null) {
            for (int size = this.ad.size() - 1; size >= 0; size--) {
                ((j) this.ad.get(size)).a(this, i);
            }
        }
    }

    public boolean p() {
        return !this.x || this.H || this.b.d();
    }

    private void G() {
        int b = this.c.b();
        for (int i = 0; i < b; i++) {
            View b2 = this.c.b(i);
            s a = a(b2);
            if (!(a == null || a.h == null)) {
                View view = a.h.a;
                int left = b2.getLeft();
                int top = b2.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    private void h(View view) {
        s b = b(view);
        e(view);
        if (!(this.p == null || b == null)) {
            this.p.d(b);
        }
        if (this.G != null) {
            for (int size = this.G.size() - 1; size >= 0; size--) {
                ((h) this.G.get(size)).b(view);
            }
        }
    }

    private void i(View view) {
        s b = b(view);
        d(view);
        if (!(this.p == null || b == null)) {
            this.p.c(b);
        }
        if (this.G != null) {
            for (int size = this.G.size() - 1; size >= 0; size--) {
                ((h) this.G.get(size)).a(view);
            }
        }
    }

    private int d(s sVar) {
        if (sVar.a(524) || !sVar.p()) {
            return -1;
        }
        return this.b.c(sVar.b);
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.aj.a(z);
    }

    public boolean isNestedScrollingEnabled() {
        return this.aj.a();
    }

    public boolean startNestedScroll(int i) {
        return this.aj.a(i);
    }

    public void stopNestedScroll() {
        this.aj.c();
    }

    public boolean hasNestedScrollingParent() {
        return this.aj.b();
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.aj.a(i, i2, i3, i4, iArr);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.aj.a(i, i2, iArr, iArr2);
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.aj.a(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.aj.a(f, f2);
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (this.ah == null) {
            return super.getChildDrawingOrder(i, i2);
        }
        return this.ah.a(i, i2);
    }
}
