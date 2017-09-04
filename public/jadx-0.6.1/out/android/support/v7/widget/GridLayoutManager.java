package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.a.c;
import android.support.v7.widget.RecyclerView.l;
import android.support.v7.widget.RecyclerView.p;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.Arrays;

public class GridLayoutManager extends LinearLayoutManager {
    static final int a = MeasureSpec.makeMeasureSpec(0, 0);
    boolean b;
    int c;
    int[] d;
    View[] e;
    final SparseIntArray f;
    final SparseIntArray g;
    a h;
    final Rect i;

    public static class LayoutParams extends android.support.v7.widget.RecyclerView.LayoutParams {
        private int e = -1;
        private int f = 0;

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

        public int a() {
            return this.e;
        }

        public int b() {
            return this.f;
        }
    }

    public static abstract class a {
        final SparseIntArray a;
        private boolean b;

        public abstract int a(int i);

        public void a() {
            this.a.clear();
        }

        int a(int i, int i2) {
            if (!this.b) {
                return b(i, i2);
            }
            int i3 = this.a.get(i, -1);
            if (i3 != -1) {
                return i3;
            }
            i3 = b(i, i2);
            this.a.put(i, i3);
            return i3;
        }

        public int b(int i, int i2) {
            int a = a(i);
            if (a == i2) {
                return 0;
            }
            int b;
            int a2;
            int i3;
            if (this.b && this.a.size() > 0) {
                b = b(i);
                if (b >= 0) {
                    a2 = this.a.get(b) + a(b);
                    b++;
                    i3 = b;
                    while (i3 < i) {
                        b = a(i3);
                        a2 += b;
                        if (a2 == i2) {
                            b = 0;
                        } else if (a2 <= i2) {
                            b = a2;
                        }
                        i3++;
                        a2 = b;
                    }
                    if (a2 + a > i2) {
                        return a2;
                    }
                    return 0;
                }
            }
            b = 0;
            a2 = 0;
            i3 = b;
            while (i3 < i) {
                b = a(i3);
                a2 += b;
                if (a2 == i2) {
                    b = 0;
                } else if (a2 <= i2) {
                    b = a2;
                }
                i3++;
                a2 = b;
            }
            if (a2 + a > i2) {
                return 0;
            }
            return a2;
        }

        int b(int i) {
            int i2 = 0;
            int size = this.a.size() - 1;
            while (i2 <= size) {
                int i3 = (i2 + size) >>> 1;
                if (this.a.keyAt(i3) < i) {
                    i2 = i3 + 1;
                } else {
                    size = i3 - 1;
                }
            }
            size = i2 - 1;
            if (size < 0 || size >= this.a.size()) {
                return -1;
            }
            return this.a.keyAt(size);
        }

        public int c(int i, int i2) {
            int a = a(i);
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (i3 < i) {
                int a2 = a(i3);
                i5 += a2;
                if (i5 == i2) {
                    i4++;
                    a2 = 0;
                } else if (i5 > i2) {
                    i4++;
                } else {
                    a2 = i5;
                }
                i3++;
                i5 = a2;
            }
            if (i5 + a > i2) {
                return i4 + 1;
            }
            return i4;
        }
    }

    public int a(l lVar, p pVar) {
        if (this.j == 0) {
            return this.c;
        }
        if (pVar.e() < 1) {
            return 0;
        }
        return a(lVar, pVar, pVar.e() - 1);
    }

    public int b(l lVar, p pVar) {
        if (this.j == 1) {
            return this.c;
        }
        if (pVar.e() < 1) {
            return 0;
        }
        return a(lVar, pVar, pVar.e() - 1);
    }

    public void a(l lVar, p pVar, View view, c cVar) {
        android.view.ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            int a = a(lVar, pVar, layoutParams2.e());
            if (this.j == 0) {
                int a2 = layoutParams2.a();
                int b = layoutParams2.b();
                boolean z = this.c > 1 && layoutParams2.b() == this.c;
                cVar.c(c.l.a(a2, b, a, 1, z, false));
                return;
            }
            int a3 = layoutParams2.a();
            int b2 = layoutParams2.b();
            boolean z2 = this.c > 1 && layoutParams2.b() == this.c;
            cVar.c(c.l.a(a, 1, a3, b2, z2, false));
            return;
        }
        super.a(view, cVar);
    }

    public void c(l lVar, p pVar) {
        if (pVar.a()) {
            C();
        }
        super.c(lVar, pVar);
        B();
        if (!pVar.a()) {
            this.b = false;
        }
    }

    private void B() {
        this.f.clear();
        this.g.clear();
    }

    private void C() {
        int r = r();
        for (int i = 0; i < r; i++) {
            LayoutParams layoutParams = (LayoutParams) f(i).getLayoutParams();
            int e = layoutParams.e();
            this.f.put(e, layoutParams.b());
            this.g.put(e, layoutParams.a());
        }
    }

    public void a(RecyclerView recyclerView, int i, int i2) {
        this.h.a();
    }

    public void a(RecyclerView recyclerView) {
        this.h.a();
    }

    public void b(RecyclerView recyclerView, int i, int i2) {
        this.h.a();
    }

    public void a(RecyclerView recyclerView, int i, int i2, Object obj) {
        this.h.a();
    }

    public void a(RecyclerView recyclerView, int i, int i2, int i3) {
        this.h.a();
    }

    public android.support.v7.widget.RecyclerView.LayoutParams a() {
        return new LayoutParams(-2, -2);
    }

    public android.support.v7.widget.RecyclerView.LayoutParams a(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    public android.support.v7.widget.RecyclerView.LayoutParams a(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    public boolean a(android.support.v7.widget.RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    private void D() {
        int s;
        if (f() == 1) {
            s = (s() - w()) - u();
        } else {
            s = (t() - x()) - v();
        }
        j(s);
    }

    private void j(int i) {
        int i2 = 0;
        if (!(this.d != null && this.d.length == this.c + 1 && this.d[this.d.length - 1] == i)) {
            this.d = new int[(this.c + 1)];
        }
        this.d[0] = 0;
        int i3 = i / this.c;
        int i4 = i % this.c;
        int i5 = 0;
        for (int i6 = 1; i6 <= this.c; i6++) {
            int i7 = i2 + i4;
            if (i7 <= 0 || this.c - i7 >= i4) {
                i2 = i7;
                i7 = i3;
            } else {
                int i8 = i3 + 1;
                i2 = i7 - this.c;
                i7 = i8;
            }
            i5 += i7;
            this.d[i6] = i5;
        }
    }

    void a(l lVar, p pVar, a aVar) {
        super.a(lVar, pVar, aVar);
        D();
        if (pVar.e() > 0 && !pVar.a()) {
            b(lVar, pVar, aVar);
        }
        if (this.e == null || this.e.length != this.c) {
            this.e = new View[this.c];
        }
    }

    private void b(l lVar, p pVar, a aVar) {
        int b = b(lVar, pVar, aVar.a);
        while (b > 0 && aVar.a > 0) {
            aVar.a--;
            b = b(lVar, pVar, aVar.a);
        }
    }

    View a(l lVar, p pVar, int i, int i2, int i3) {
        View view = null;
        h();
        int c = this.k.c();
        int d = this.k.d();
        int i4 = i2 > i ? 1 : -1;
        View view2 = null;
        while (i != i2) {
            View view3;
            View f = f(i);
            int d2 = d(f);
            if (d2 >= 0 && d2 < i3) {
                if (b(lVar, pVar, d2) != 0) {
                    view3 = view;
                    f = view2;
                } else if (((android.support.v7.widget.RecyclerView.LayoutParams) f.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view3 = view;
                    }
                } else if (this.k.a(f) < d && this.k.b(f) >= c) {
                    return f;
                } else {
                    if (view == null) {
                        view3 = f;
                        f = view2;
                    }
                }
                i += i4;
                view = view3;
                view2 = f;
            }
            view3 = view;
            f = view2;
            i += i4;
            view = view3;
            view2 = f;
        }
        if (view == null) {
            view = view2;
        }
        return view;
    }

    private int a(l lVar, p pVar, int i) {
        if (!pVar.a()) {
            return this.h.c(i, this.c);
        }
        int b = lVar.b(i);
        if (b != -1) {
            return this.h.c(b, this.c);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i);
        return 0;
    }

    private int b(l lVar, p pVar, int i) {
        if (!pVar.a()) {
            return this.h.a(i, this.c);
        }
        int i2 = this.g.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        i2 = lVar.b(i);
        if (i2 != -1) {
            return this.h.a(i2, this.c);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 0;
    }

    private int c(l lVar, p pVar, int i) {
        if (!pVar.a()) {
            return this.h.a(i);
        }
        int i2 = this.f.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        i2 = lVar.b(i);
        if (i2 != -1) {
            return this.h.a(i2);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 1;
    }

    void a(l lVar, p pVar, c cVar, b bVar) {
        boolean z = cVar.e == 1;
        int i = 0;
        int i2 = 0;
        int i3 = this.c;
        if (!z) {
            i3 = b(lVar, pVar, cVar.d) + c(lVar, pVar, cVar.d);
        }
        while (i < this.c && cVar.a(pVar) && i3 > 0) {
            int i4 = cVar.d;
            int c = c(lVar, pVar, i4);
            if (c <= this.c) {
                i3 -= c;
                if (i3 >= 0) {
                    View a = cVar.a(lVar);
                    if (a == null) {
                        break;
                    }
                    i2 += c;
                    this.e[i] = a;
                    i++;
                } else {
                    break;
                }
            }
            throw new IllegalArgumentException("Item at position " + i4 + " requires " + c + " spans but GridLayoutManager has only " + this.c + " spans.");
        }
        if (i == 0) {
            bVar.b = true;
            return;
        }
        int makeMeasureSpec;
        a(lVar, pVar, i, i2, z);
        c = 0;
        i4 = 0;
        while (c < i) {
            View view = this.e[c];
            if (cVar.k == null) {
                if (z) {
                    b(view);
                } else {
                    b(view, 0);
                }
            } else if (z) {
                a(view);
            } else {
                a(view, 0);
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.d[layoutParams.e + layoutParams.f] - this.d[layoutParams.e], 1073741824);
            if (this.j == 1) {
                a(view, makeMeasureSpec, k(layoutParams.height), false);
            } else {
                a(view, k(layoutParams.width), makeMeasureSpec, false);
            }
            i3 = this.k.c(view);
            if (i3 <= i4) {
                i3 = i4;
            }
            c++;
            i4 = i3;
        }
        i2 = k(i4);
        for (c = 0; c < i; c++) {
            View view2 = this.e[c];
            if (this.k.c(view2) != i4) {
                layoutParams = (LayoutParams) view2.getLayoutParams();
                i3 = MeasureSpec.makeMeasureSpec(this.d[layoutParams.e + layoutParams.f] - this.d[layoutParams.e], 1073741824);
                if (this.j == 1) {
                    a(view2, i3, i2, true);
                } else {
                    a(view2, i2, i3, true);
                }
            }
        }
        bVar.a = i4;
        i3 = 0;
        if (this.j == 1) {
            if (cVar.f == -1) {
                i3 = cVar.b;
                i4 = i3 - i4;
                c = 0;
                i2 = 0;
            } else {
                c = cVar.b;
                i3 = c + i4;
                i4 = c;
                c = 0;
                i2 = 0;
            }
        } else if (cVar.f == -1) {
            i2 = cVar.b;
            c = i2;
            i2 -= i4;
            i4 = 0;
        } else {
            i2 = cVar.b;
            c = i4 + i2;
            i4 = 0;
        }
        int i5 = 0;
        makeMeasureSpec = i2;
        i2 = c;
        c = i4;
        i4 = i3;
        while (i5 < i) {
            int d;
            int i6;
            view2 = this.e[i5];
            layoutParams = (LayoutParams) view2.getLayoutParams();
            if (this.j == 1) {
                makeMeasureSpec = this.d[layoutParams.e] + u();
                d = this.k.d(view2) + makeMeasureSpec;
                i6 = makeMeasureSpec;
            } else {
                c = this.d[layoutParams.e] + v();
                i4 = this.k.d(view2) + c;
                d = i2;
                i6 = makeMeasureSpec;
            }
            a(view2, i6 + layoutParams.leftMargin, c + layoutParams.topMargin, d - layoutParams.rightMargin, i4 - layoutParams.bottomMargin);
            if (layoutParams.c() || layoutParams.d()) {
                bVar.c = true;
            }
            bVar.d |= view2.isFocusable();
            i5++;
            i2 = d;
            makeMeasureSpec = i6;
        }
        Arrays.fill(this.e, null);
    }

    private int k(int i) {
        if (i < 0) {
            return a;
        }
        return MeasureSpec.makeMeasureSpec(i, 1073741824);
    }

    private void a(View view, int i, int i2, boolean z) {
        a(view, this.i);
        android.support.v7.widget.RecyclerView.LayoutParams layoutParams = (android.support.v7.widget.RecyclerView.LayoutParams) view.getLayoutParams();
        if (z || this.j == 1) {
            i = a(i, layoutParams.leftMargin + this.i.left, layoutParams.rightMargin + this.i.right);
        }
        if (z || this.j == 0) {
            i2 = a(i2, layoutParams.topMargin + this.i.top, layoutParams.bottomMargin + this.i.bottom);
        }
        view.measure(i, i2);
    }

    private int a(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            return MeasureSpec.makeMeasureSpec((MeasureSpec.getSize(i) - i2) - i3, mode);
        }
        return i;
    }

    private void a(l lVar, p pVar, int i, int i2, boolean z) {
        int i3;
        int i4;
        int i5;
        int i6;
        if (z) {
            i3 = 1;
            i4 = 0;
        } else {
            i4 = i - 1;
            i3 = -1;
            i = -1;
        }
        if (this.j == 1 && g()) {
            i5 = this.c - 1;
            i6 = -1;
        } else {
            i5 = 0;
            i6 = 1;
        }
        int i7 = i5;
        for (i5 = i4; i5 != i; i5 += i3) {
            View view = this.e[i5];
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.f = c(lVar, pVar, d(view));
            if (i6 != -1 || layoutParams.f <= 1) {
                layoutParams.e = i7;
            } else {
                layoutParams.e = i7 - (layoutParams.f - 1);
            }
            i7 += layoutParams.f * i6;
        }
    }

    public boolean b() {
        return this.o == null && !this.b;
    }
}
