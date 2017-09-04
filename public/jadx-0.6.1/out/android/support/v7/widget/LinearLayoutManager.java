package android.support.v7.widget;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.l;
import android.support.v7.widget.RecyclerView.p;
import android.support.v7.widget.RecyclerView.s;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.util.List;

public class LinearLayoutManager extends LayoutManager {
    private c a;
    private boolean b;
    private boolean c = false;
    private boolean d = false;
    private boolean e = true;
    private boolean f;
    int j;
    f k;
    boolean l = false;
    int m = -1;
    int n = Integer.MIN_VALUE;
    SavedState o = null;
    final a p = new a(this);

    public static class SavedState implements Parcelable {
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
        int a;
        int b;
        boolean c;

        SavedState(Parcel parcel) {
            boolean z = true;
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            if (parcel.readInt() != 1) {
                z = false;
            }
            this.c = z;
        }

        public SavedState(SavedState savedState) {
            this.a = savedState.a;
            this.b = savedState.b;
            this.c = savedState.c;
        }

        boolean a() {
            return this.a >= 0;
        }

        void b() {
            this.a = -1;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c ? 1 : 0);
        }
    }

    class a {
        int a;
        int b;
        boolean c;
        final /* synthetic */ LinearLayoutManager d;

        a(LinearLayoutManager linearLayoutManager) {
            this.d = linearLayoutManager;
        }

        void a() {
            this.a = -1;
            this.b = Integer.MIN_VALUE;
            this.c = false;
        }

        void b() {
            this.b = this.c ? this.d.k.d() : this.d.k.c();
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.a + ", mCoordinate=" + this.b + ", mLayoutFromEnd=" + this.c + '}';
        }

        private boolean a(View view, p pVar) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            return !layoutParams.c() && layoutParams.e() >= 0 && layoutParams.e() < pVar.e();
        }

        public void a(View view) {
            int b = this.d.k.b();
            if (b >= 0) {
                b(view);
                return;
            }
            this.a = this.d.d(view);
            int c;
            if (this.c) {
                b = (this.d.k.d() - b) - this.d.k.b(view);
                this.b = this.d.k.d() - b;
                if (b > 0) {
                    c = this.b - this.d.k.c(view);
                    int c2 = this.d.k.c();
                    c -= c2 + Math.min(this.d.k.a(view) - c2, 0);
                    if (c < 0) {
                        this.b = Math.min(b, -c) + this.b;
                        return;
                    }
                    return;
                }
                return;
            }
            c = this.d.k.a(view);
            c2 = c - this.d.k.c();
            this.b = c;
            if (c2 > 0) {
                b = (this.d.k.d() - Math.min(0, (this.d.k.d() - b) - this.d.k.b(view))) - (c + this.d.k.c(view));
                if (b < 0) {
                    this.b -= Math.min(c2, -b);
                }
            }
        }

        public void b(View view) {
            if (this.c) {
                this.b = this.d.k.b(view) + this.d.k.b();
            } else {
                this.b = this.d.k.a(view);
            }
            this.a = this.d.d(view);
        }
    }

    protected static class b {
        public int a;
        public boolean b;
        public boolean c;
        public boolean d;

        protected b() {
        }

        void a() {
            this.a = 0;
            this.b = false;
            this.c = false;
            this.d = false;
        }
    }

    static class c {
        boolean a = true;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h = 0;
        boolean i = false;
        int j;
        List<s> k = null;

        c() {
        }

        boolean a(p pVar) {
            return this.d >= 0 && this.d < pVar.e();
        }

        View a(l lVar) {
            if (this.k != null) {
                return b();
            }
            View c = lVar.c(this.d);
            this.d += this.e;
            return c;
        }

        private View b() {
            int size = this.k.size();
            for (int i = 0; i < size; i++) {
                View view = ((s) this.k.get(i)).a;
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                if (!layoutParams.c() && this.d == layoutParams.e()) {
                    a(view);
                    return view;
                }
            }
            return null;
        }

        public void a() {
            a(null);
        }

        public void a(View view) {
            View b = b(view);
            if (b == null) {
                this.d = -1;
            } else {
                this.d = ((LayoutParams) b.getLayoutParams()).e();
            }
        }

        public View b(View view) {
            int size = this.k.size();
            View view2 = null;
            int i = Integer.MAX_VALUE;
            int i2 = 0;
            while (i2 < size) {
                int i3;
                View view3;
                View view4 = ((s) this.k.get(i2)).a;
                LayoutParams layoutParams = (LayoutParams) view4.getLayoutParams();
                if (view4 != view) {
                    if (layoutParams.c()) {
                        i3 = i;
                        view3 = view2;
                    } else {
                        i3 = (layoutParams.e() - this.d) * this.e;
                        if (i3 < 0) {
                            i3 = i;
                            view3 = view2;
                        } else if (i3 < i) {
                            if (i3 == 0) {
                                return view4;
                            }
                            view3 = view4;
                        }
                    }
                    i2++;
                    view2 = view3;
                    i = i3;
                }
                i3 = i;
                view3 = view2;
                i2++;
                view2 = view3;
                i = i3;
            }
            return view2;
        }
    }

    public LinearLayoutManager(Context context, int i, boolean z) {
        a(i);
        a(z);
    }

    public LayoutParams a() {
        return new LayoutParams(-2, -2);
    }

    public void a(RecyclerView recyclerView, l lVar) {
        super.a(recyclerView, lVar);
        if (this.f) {
            c(lVar);
            lVar.a();
        }
    }

    public void a(AccessibilityEvent accessibilityEvent) {
        super.a(accessibilityEvent);
        if (r() > 0) {
            android.support.v4.view.a.l a = android.support.v4.view.a.a.a(accessibilityEvent);
            a.b(j());
            a.c(k());
        }
    }

    public Parcelable c() {
        if (this.o != null) {
            return new SavedState(this.o);
        }
        Parcelable savedState = new SavedState();
        if (r() > 0) {
            h();
            boolean z = this.b ^ this.l;
            savedState.c = z;
            View D;
            if (z) {
                D = D();
                savedState.b = this.k.d() - this.k.b(D);
                savedState.a = d(D);
                return savedState;
            }
            D = C();
            savedState.a = d(D);
            savedState.b = this.k.a(D) - this.k.c();
            return savedState;
        }
        savedState.b();
        return savedState;
    }

    public void a(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.o = (SavedState) parcelable;
            l();
        }
    }

    public boolean d() {
        return this.j == 0;
    }

    public boolean e() {
        return this.j == 1;
    }

    public int f() {
        return this.j;
    }

    public void a(int i) {
        if (i == 0 || i == 1) {
            a(null);
            if (i != this.j) {
                this.j = i;
                this.k = null;
                l();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("invalid orientation:" + i);
    }

    private void B() {
        boolean z = true;
        if (this.j == 1 || !g()) {
            this.l = this.c;
            return;
        }
        if (this.c) {
            z = false;
        }
        this.l = z;
    }

    public void a(boolean z) {
        a(null);
        if (z != this.c) {
            this.c = z;
            l();
        }
    }

    public View b(int i) {
        int r = r();
        if (r == 0) {
            return null;
        }
        int d = i - d(f(0));
        if (d >= 0 && d < r) {
            View f = f(d);
            if (d(f) == i) {
                return f;
            }
        }
        return super.b(i);
    }

    protected int a(p pVar) {
        if (pVar.d()) {
            return this.k.f();
        }
        return 0;
    }

    public void c(l lVar, p pVar) {
        if (!(this.o == null && this.m == -1) && pVar.e() == 0) {
            c(lVar);
            return;
        }
        int i;
        int d;
        if (this.o != null && this.o.a()) {
            this.m = this.o.a;
        }
        h();
        this.a.a = false;
        B();
        this.p.a();
        this.p.c = this.l ^ this.d;
        b(lVar, pVar, this.p);
        int a = a(pVar);
        if (this.a.j >= 0) {
            i = 0;
        } else {
            i = a;
            a = 0;
        }
        i += this.k.c();
        a += this.k.g();
        if (!(!pVar.a() || this.m == -1 || this.n == Integer.MIN_VALUE)) {
            View b = b(this.m);
            if (b != null) {
                if (this.l) {
                    d = (this.k.d() - this.k.b(b)) - this.n;
                } else {
                    d = this.n - (this.k.a(b) - this.k.c());
                }
                if (d > 0) {
                    i += d;
                } else {
                    a -= d;
                }
            }
        }
        a(lVar, pVar, this.p);
        a(lVar);
        this.a.i = pVar.a();
        int i2;
        if (this.p.c) {
            b(this.p);
            this.a.h = i;
            a(lVar, this.a, pVar, false);
            i = this.a.b;
            i2 = this.a.d;
            if (this.a.c > 0) {
                a += this.a.c;
            }
            a(this.p);
            this.a.h = a;
            c cVar = this.a;
            cVar.d += this.a.e;
            a(lVar, this.a, pVar, false);
            d = this.a.b;
            if (this.a.c > 0) {
                a = this.a.c;
                d(i2, i);
                this.a.h = a;
                a(lVar, this.a, pVar, false);
                a = this.a.b;
            } else {
                a = i;
            }
            i = a;
            a = d;
        } else {
            a(this.p);
            this.a.h = a;
            a(lVar, this.a, pVar, false);
            a = this.a.b;
            d = this.a.d;
            if (this.a.c > 0) {
                i += this.a.c;
            }
            b(this.p);
            this.a.h = i;
            c cVar2 = this.a;
            cVar2.d += this.a.e;
            a(lVar, this.a, pVar, false);
            i = this.a.b;
            if (this.a.c > 0) {
                i2 = this.a.c;
                c(d, a);
                this.a.h = i2;
                a(lVar, this.a, pVar, false);
                a = this.a.b;
            }
        }
        if (r() > 0) {
            int b2;
            if ((this.l ^ this.d) != 0) {
                d = a(a, lVar, pVar, true);
                i += d;
                a += d;
                b2 = b(i, lVar, pVar, false);
                i += b2;
                a += b2;
            } else {
                d = b(i, lVar, pVar, true);
                i += d;
                a += d;
                b2 = a(a, lVar, pVar, false);
                i += b2;
                a += b2;
            }
        }
        b(lVar, pVar, i, a);
        if (!pVar.a()) {
            this.m = -1;
            this.n = Integer.MIN_VALUE;
            this.k.a();
        }
        this.b = this.d;
        this.o = null;
    }

    void a(l lVar, p pVar, a aVar) {
    }

    private void b(l lVar, p pVar, int i, int i2) {
        if (pVar.b() && r() != 0 && !pVar.a() && b()) {
            int i3 = 0;
            int i4 = 0;
            List b = lVar.b();
            int size = b.size();
            int d = d(f(0));
            int i5 = 0;
            while (i5 < size) {
                int i6;
                int i7;
                s sVar = (s) b.get(i5);
                if (sVar.q()) {
                    i6 = i4;
                    i7 = i3;
                } else {
                    if (((sVar.d() < d) != this.l ? -1 : 1) == -1) {
                        i7 = this.k.c(sVar.a) + i3;
                        i6 = i4;
                    } else {
                        i6 = this.k.c(sVar.a) + i4;
                        i7 = i3;
                    }
                }
                i5++;
                i3 = i7;
                i4 = i6;
            }
            this.a.k = b;
            if (i3 > 0) {
                d(d(C()), i);
                this.a.h = i3;
                this.a.c = 0;
                this.a.a();
                a(lVar, this.a, pVar, false);
            }
            if (i4 > 0) {
                c(d(D()), i2);
                this.a.h = i4;
                this.a.c = 0;
                this.a.a();
                a(lVar, this.a, pVar, false);
            }
            this.a.k = null;
        }
    }

    private void b(l lVar, p pVar, a aVar) {
        if (!a(pVar, aVar) && !c(lVar, pVar, aVar)) {
            aVar.b();
            aVar.a = this.d ? pVar.e() - 1 : 0;
        }
    }

    private boolean c(l lVar, p pVar, a aVar) {
        boolean z = false;
        if (r() == 0) {
            return false;
        }
        View y = y();
        if (y != null && aVar.a(y, pVar)) {
            aVar.a(y);
            return true;
        } else if (this.b != this.d) {
            return false;
        } else {
            y = aVar.c ? f(lVar, pVar) : g(lVar, pVar);
            if (y == null) {
                return false;
            }
            aVar.b(y);
            if (!pVar.a() && b()) {
                if (this.k.a(y) >= this.k.d() || this.k.b(y) < this.k.c()) {
                    z = true;
                }
                if (z) {
                    aVar.b = aVar.c ? this.k.d() : this.k.c();
                }
            }
            return true;
        }
    }

    private boolean a(p pVar, a aVar) {
        boolean z = false;
        if (pVar.a() || this.m == -1) {
            return false;
        }
        if (this.m < 0 || this.m >= pVar.e()) {
            this.m = -1;
            this.n = Integer.MIN_VALUE;
            return false;
        }
        aVar.a = this.m;
        if (this.o != null && this.o.a()) {
            aVar.c = this.o.c;
            if (aVar.c) {
                aVar.b = this.k.d() - this.o.b;
                return true;
            }
            aVar.b = this.k.c() + this.o.b;
            return true;
        } else if (this.n == Integer.MIN_VALUE) {
            View b = b(this.m);
            if (b == null) {
                if (r() > 0) {
                    boolean z2;
                    if (this.m < d(f(0))) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (z2 == this.l) {
                        z = true;
                    }
                    aVar.c = z;
                }
                aVar.b();
                return true;
            } else if (this.k.c(b) > this.k.f()) {
                aVar.b();
                return true;
            } else if (this.k.a(b) - this.k.c() < 0) {
                aVar.b = this.k.c();
                aVar.c = false;
                return true;
            } else if (this.k.d() - this.k.b(b) < 0) {
                aVar.b = this.k.d();
                aVar.c = true;
                return true;
            } else {
                aVar.b = aVar.c ? this.k.b(b) + this.k.b() : this.k.a(b);
                return true;
            }
        } else {
            aVar.c = this.l;
            if (this.l) {
                aVar.b = this.k.d() - this.n;
                return true;
            }
            aVar.b = this.k.c() + this.n;
            return true;
        }
    }

    private int a(int i, l lVar, p pVar, boolean z) {
        int d = this.k.d() - i;
        if (d <= 0) {
            return 0;
        }
        d = -c(-d, lVar, pVar);
        int i2 = i + d;
        if (!z) {
            return d;
        }
        i2 = this.k.d() - i2;
        if (i2 <= 0) {
            return d;
        }
        this.k.a(i2);
        return d + i2;
    }

    private int b(int i, l lVar, p pVar, boolean z) {
        int c = i - this.k.c();
        if (c <= 0) {
            return 0;
        }
        c = -c(c, lVar, pVar);
        int i2 = i + c;
        if (!z) {
            return c;
        }
        i2 -= this.k.c();
        if (i2 <= 0) {
            return c;
        }
        this.k.a(-i2);
        return c - i2;
    }

    private void a(a aVar) {
        c(aVar.a, aVar.b);
    }

    private void c(int i, int i2) {
        this.a.c = this.k.d() - i2;
        this.a.e = this.l ? -1 : 1;
        this.a.d = i;
        this.a.f = 1;
        this.a.b = i2;
        this.a.g = Integer.MIN_VALUE;
    }

    private void b(a aVar) {
        d(aVar.a, aVar.b);
    }

    private void d(int i, int i2) {
        this.a.c = i2 - this.k.c();
        this.a.d = i;
        this.a.e = this.l ? 1 : -1;
        this.a.f = -1;
        this.a.b = i2;
        this.a.g = Integer.MIN_VALUE;
    }

    protected boolean g() {
        return p() == 1;
    }

    void h() {
        if (this.a == null) {
            this.a = i();
        }
        if (this.k == null) {
            this.k = f.a(this, this.j);
        }
    }

    c i() {
        return new c();
    }

    public void c(int i) {
        this.m = i;
        this.n = Integer.MIN_VALUE;
        if (this.o != null) {
            this.o.b();
        }
        l();
    }

    public int a(int i, l lVar, p pVar) {
        if (this.j == 1) {
            return 0;
        }
        return c(i, lVar, pVar);
    }

    public int b(int i, l lVar, p pVar) {
        if (this.j == 0) {
            return 0;
        }
        return c(i, lVar, pVar);
    }

    public int b(p pVar) {
        return h(pVar);
    }

    public int c(p pVar) {
        return h(pVar);
    }

    public int d(p pVar) {
        return i(pVar);
    }

    public int e(p pVar) {
        return i(pVar);
    }

    public int f(p pVar) {
        return j(pVar);
    }

    public int g(p pVar) {
        return j(pVar);
    }

    private int h(p pVar) {
        boolean z = false;
        if (r() == 0) {
            return 0;
        }
        h();
        f fVar = this.k;
        View a = a(!this.e, true);
        if (!this.e) {
            z = true;
        }
        return h.a(pVar, fVar, a, b(z, true), this, this.e, this.l);
    }

    private int i(p pVar) {
        boolean z = false;
        if (r() == 0) {
            return 0;
        }
        h();
        f fVar = this.k;
        View a = a(!this.e, true);
        if (!this.e) {
            z = true;
        }
        return h.a(pVar, fVar, a, b(z, true), this, this.e);
    }

    private int j(p pVar) {
        boolean z = false;
        if (r() == 0) {
            return 0;
        }
        h();
        f fVar = this.k;
        View a = a(!this.e, true);
        if (!this.e) {
            z = true;
        }
        return h.b(pVar, fVar, a, b(z, true), this, this.e);
    }

    private void a(int i, int i2, boolean z, p pVar) {
        int i3 = -1;
        int i4 = 1;
        this.a.h = a(pVar);
        this.a.f = i;
        View D;
        c cVar;
        if (i == 1) {
            c cVar2 = this.a;
            cVar2.h += this.k.g();
            D = D();
            cVar = this.a;
            if (!this.l) {
                i3 = 1;
            }
            cVar.e = i3;
            this.a.d = d(D) + this.a.e;
            this.a.b = this.k.b(D);
            i3 = this.k.b(D) - this.k.d();
        } else {
            D = C();
            cVar = this.a;
            cVar.h += this.k.c();
            cVar = this.a;
            if (!this.l) {
                i4 = -1;
            }
            cVar.e = i4;
            this.a.d = d(D) + this.a.e;
            this.a.b = this.k.a(D);
            i3 = (-this.k.a(D)) + this.k.c();
        }
        this.a.c = i2;
        if (z) {
            c cVar3 = this.a;
            cVar3.c -= i3;
        }
        this.a.g = i3;
    }

    int c(int i, l lVar, p pVar) {
        if (r() == 0 || i == 0) {
            return 0;
        }
        this.a.a = true;
        h();
        int i2 = i > 0 ? 1 : -1;
        int abs = Math.abs(i);
        a(i2, abs, true, pVar);
        int a = this.a.g + a(lVar, this.a, pVar, false);
        if (a < 0) {
            return 0;
        }
        if (abs > a) {
            i = i2 * a;
        }
        this.k.a(-i);
        this.a.j = i;
        return i;
    }

    public void a(String str) {
        if (this.o == null) {
            super.a(str);
        }
    }

    private void a(l lVar, int i, int i2) {
        if (i != i2) {
            if (i2 > i) {
                for (int i3 = i2 - 1; i3 >= i; i3--) {
                    a(i3, lVar);
                }
                return;
            }
            while (i > i2) {
                a(i, lVar);
                i--;
            }
        }
    }

    private void a(l lVar, int i) {
        if (i >= 0) {
            int r = r();
            int i2;
            if (this.l) {
                for (i2 = r - 1; i2 >= 0; i2--) {
                    if (this.k.b(f(i2)) > i) {
                        a(lVar, r - 1, i2);
                        return;
                    }
                }
                return;
            }
            for (i2 = 0; i2 < r; i2++) {
                if (this.k.b(f(i2)) > i) {
                    a(lVar, 0, i2);
                    return;
                }
            }
        }
    }

    private void b(l lVar, int i) {
        int r = r();
        if (i >= 0) {
            int e = this.k.e() - i;
            int i2;
            if (this.l) {
                for (i2 = 0; i2 < r; i2++) {
                    if (this.k.a(f(i2)) < e) {
                        a(lVar, 0, i2);
                        return;
                    }
                }
                return;
            }
            for (i2 = r - 1; i2 >= 0; i2--) {
                if (this.k.a(f(i2)) < e) {
                    a(lVar, r - 1, i2);
                    return;
                }
            }
        }
    }

    private void a(l lVar, c cVar) {
        if (!cVar.a) {
            return;
        }
        if (cVar.f == -1) {
            b(lVar, cVar.g);
        } else {
            a(lVar, cVar.g);
        }
    }

    int a(l lVar, c cVar, p pVar, boolean z) {
        int i = cVar.c;
        if (cVar.g != Integer.MIN_VALUE) {
            if (cVar.c < 0) {
                cVar.g += cVar.c;
            }
            a(lVar, cVar);
        }
        int i2 = cVar.c + cVar.h;
        b bVar = new b();
        while (i2 > 0 && cVar.a(pVar)) {
            bVar.a();
            a(lVar, pVar, cVar, bVar);
            if (!bVar.b) {
                cVar.b += bVar.a * cVar.f;
                if (!(bVar.c && this.a.k == null && pVar.a())) {
                    cVar.c -= bVar.a;
                    i2 -= bVar.a;
                }
                if (cVar.g != Integer.MIN_VALUE) {
                    cVar.g += bVar.a;
                    if (cVar.c < 0) {
                        cVar.g += cVar.c;
                    }
                    a(lVar, cVar);
                }
                if (z && bVar.d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - cVar.c;
    }

    void a(l lVar, p pVar, c cVar, b bVar) {
        View a = cVar.a(lVar);
        if (a == null) {
            bVar.b = true;
            return;
        }
        int s;
        int d;
        int i;
        int i2;
        LayoutParams layoutParams = (LayoutParams) a.getLayoutParams();
        if (cVar.k == null) {
            if (this.l == (cVar.f == -1)) {
                b(a);
            } else {
                b(a, 0);
            }
        } else {
            boolean z;
            boolean z2 = this.l;
            if (cVar.f == -1) {
                z = true;
            } else {
                z = false;
            }
            if (z2 == z) {
                a(a);
            } else {
                a(a, 0);
            }
        }
        a(a, 0, 0);
        bVar.a = this.k.c(a);
        if (this.j == 1) {
            if (g()) {
                s = s() - w();
                d = s - this.k.d(a);
            } else {
                d = u();
                s = this.k.d(a) + d;
            }
            if (cVar.f == -1) {
                i = cVar.b - bVar.a;
                i2 = s;
                s = cVar.b;
            } else {
                i = cVar.b;
                i2 = s;
                s = cVar.b + bVar.a;
            }
        } else {
            i = v();
            s = this.k.d(a) + i;
            if (cVar.f == -1) {
                d = cVar.b - bVar.a;
                i2 = cVar.b;
            } else {
                d = cVar.b;
                i2 = cVar.b + bVar.a;
            }
        }
        a(a, d + layoutParams.leftMargin, i + layoutParams.topMargin, i2 - layoutParams.rightMargin, s - layoutParams.bottomMargin);
        if (layoutParams.c() || layoutParams.d()) {
            bVar.c = true;
        }
        bVar.d = a.isFocusable();
    }

    private int j(int i) {
        int i2 = 1;
        int i3 = Integer.MIN_VALUE;
        switch (i) {
            case 1:
                return -1;
            case 2:
                return 1;
            case 17:
                if (this.j != 0) {
                    return Integer.MIN_VALUE;
                }
                return -1;
            case 33:
                if (this.j != 1) {
                    return Integer.MIN_VALUE;
                }
                return -1;
            case 66:
                if (this.j != 0) {
                    i2 = Integer.MIN_VALUE;
                }
                return i2;
            case Opcodes.INT_TO_FLOAT /*130*/:
                if (this.j == 1) {
                    i3 = 1;
                }
                return i3;
            default:
                return Integer.MIN_VALUE;
        }
    }

    private View C() {
        return f(this.l ? r() - 1 : 0);
    }

    private View D() {
        return f(this.l ? 0 : r() - 1);
    }

    private View a(boolean z, boolean z2) {
        if (this.l) {
            return a(r() - 1, -1, z, z2);
        }
        return a(0, r(), z, z2);
    }

    private View b(boolean z, boolean z2) {
        if (this.l) {
            return a(0, r(), z, z2);
        }
        return a(r() - 1, -1, z, z2);
    }

    private View f(l lVar, p pVar) {
        return this.l ? h(lVar, pVar) : i(lVar, pVar);
    }

    private View g(l lVar, p pVar) {
        return this.l ? i(lVar, pVar) : h(lVar, pVar);
    }

    private View h(l lVar, p pVar) {
        return a(lVar, pVar, 0, r(), pVar.e());
    }

    private View i(l lVar, p pVar) {
        return a(lVar, pVar, r() - 1, -1, pVar.e());
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
                if (((LayoutParams) f.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view3 = view;
                        i += i4;
                        view = view3;
                        view2 = f;
                    }
                } else if (this.k.a(f) < d && this.k.b(f) >= c) {
                    return f;
                } else {
                    if (view == null) {
                        view3 = f;
                        f = view2;
                        i += i4;
                        view = view3;
                        view2 = f;
                    }
                }
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

    public int j() {
        View a = a(0, r(), false, true);
        return a == null ? -1 : d(a);
    }

    public int k() {
        View a = a(r() - 1, -1, false, true);
        if (a == null) {
            return -1;
        }
        return d(a);
    }

    View a(int i, int i2, boolean z, boolean z2) {
        h();
        int c = this.k.c();
        int d = this.k.d();
        int i3 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View f = f(i);
            int a = this.k.a(f);
            int b = this.k.b(f);
            if (a < d && b > c) {
                if (!z) {
                    return f;
                }
                if (a >= c && b <= d) {
                    return f;
                }
                if (z2 && view == null) {
                    i += i3;
                    view = f;
                }
            }
            f = view;
            i += i3;
            view = f;
        }
        return view;
    }

    public View a(View view, int i, l lVar, p pVar) {
        B();
        if (r() == 0) {
            return null;
        }
        int j = j(i);
        if (j == Integer.MIN_VALUE) {
            return null;
        }
        View g;
        h();
        if (j == -1) {
            g = g(lVar, pVar);
        } else {
            g = f(lVar, pVar);
        }
        if (g == null) {
            return null;
        }
        View C;
        h();
        a(j, (int) (0.33f * ((float) this.k.f())), false, pVar);
        this.a.g = Integer.MIN_VALUE;
        this.a.a = false;
        a(lVar, this.a, pVar, true);
        if (j == -1) {
            C = C();
        } else {
            C = D();
        }
        if (C == g || !C.isFocusable()) {
            return null;
        }
        return C;
    }

    public boolean b() {
        return this.o == null && this.b == this.d;
    }
}
