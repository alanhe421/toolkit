package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.a.c;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.l;
import android.support.v7.widget.RecyclerView.p;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class StaggeredGridLayoutManager extends LayoutManager {
    private boolean A;
    private final Runnable B;
    f a;
    f b;
    boolean c;
    int d;
    int e;
    LazySpanLookup f;
    private int g;
    private b[] h;
    private int i;
    private int j;
    private d k;
    private boolean l;
    private BitSet m;
    private int n;
    private boolean o;
    private boolean p;
    private SavedState t;
    private int u;
    private int v;
    private int w;
    private final Rect x;
    private final a y;
    private boolean z;

    public static class LayoutParams extends android.support.v7.widget.RecyclerView.LayoutParams {
        b e;
        boolean f;

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

        public final int a() {
            if (this.e == null) {
                return -1;
            }
            return this.e.d;
        }
    }

    static class LazySpanLookup {
        int[] a;
        List<FullSpanItem> b;

        static class FullSpanItem implements Parcelable {
            public static final Creator<FullSpanItem> CREATOR = new Creator<FullSpanItem>() {
                public /* synthetic */ Object createFromParcel(Parcel parcel) {
                    return a(parcel);
                }

                public /* synthetic */ Object[] newArray(int i) {
                    return a(i);
                }

                public FullSpanItem a(Parcel parcel) {
                    return new FullSpanItem(parcel);
                }

                public FullSpanItem[] a(int i) {
                    return new FullSpanItem[i];
                }
            };
            int a;
            int b;
            int[] c;
            boolean d;

            public FullSpanItem(Parcel parcel) {
                boolean z = true;
                this.a = parcel.readInt();
                this.b = parcel.readInt();
                if (parcel.readInt() != 1) {
                    z = false;
                }
                this.d = z;
                int readInt = parcel.readInt();
                if (readInt > 0) {
                    this.c = new int[readInt];
                    parcel.readIntArray(this.c);
                }
            }

            int a(int i) {
                return this.c == null ? 0 : this.c[i];
            }

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.a);
                parcel.writeInt(this.b);
                parcel.writeInt(this.d ? 1 : 0);
                if (this.c == null || this.c.length <= 0) {
                    parcel.writeInt(0);
                    return;
                }
                parcel.writeInt(this.c.length);
                parcel.writeIntArray(this.c);
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.a + ", mGapDir=" + this.b + ", mHasUnwantedGapAfter=" + this.d + ", mGapPerSpan=" + Arrays.toString(this.c) + '}';
            }
        }

        int a(int i) {
            if (this.b != null) {
                for (int size = this.b.size() - 1; size >= 0; size--) {
                    if (((FullSpanItem) this.b.get(size)).a >= i) {
                        this.b.remove(size);
                    }
                }
            }
            return b(i);
        }

        int b(int i) {
            if (this.a == null || i >= this.a.length) {
                return -1;
            }
            int g = g(i);
            if (g == -1) {
                Arrays.fill(this.a, i, this.a.length, -1);
                return this.a.length;
            }
            Arrays.fill(this.a, i, g + 1, -1);
            return g + 1;
        }

        int c(int i) {
            if (this.a == null || i >= this.a.length) {
                return -1;
            }
            return this.a[i];
        }

        void a(int i, b bVar) {
            e(i);
            this.a[i] = bVar.d;
        }

        int d(int i) {
            int length = this.a.length;
            while (length <= i) {
                length *= 2;
            }
            return length;
        }

        void e(int i) {
            if (this.a == null) {
                this.a = new int[(Math.max(i, 10) + 1)];
                Arrays.fill(this.a, -1);
            } else if (i >= this.a.length) {
                Object obj = this.a;
                this.a = new int[d(i)];
                System.arraycopy(obj, 0, this.a, 0, obj.length);
                Arrays.fill(this.a, obj.length, this.a.length, -1);
            }
        }

        void a() {
            if (this.a != null) {
                Arrays.fill(this.a, -1);
            }
            this.b = null;
        }

        void a(int i, int i2) {
            if (this.a != null && i < this.a.length) {
                e(i + i2);
                System.arraycopy(this.a, i + i2, this.a, i, (this.a.length - i) - i2);
                Arrays.fill(this.a, this.a.length - i2, this.a.length, -1);
                c(i, i2);
            }
        }

        private void c(int i, int i2) {
            if (this.b != null) {
                int i3 = i + i2;
                for (int size = this.b.size() - 1; size >= 0; size--) {
                    FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(size);
                    if (fullSpanItem.a >= i) {
                        if (fullSpanItem.a < i3) {
                            this.b.remove(size);
                        } else {
                            fullSpanItem.a -= i2;
                        }
                    }
                }
            }
        }

        void b(int i, int i2) {
            if (this.a != null && i < this.a.length) {
                e(i + i2);
                System.arraycopy(this.a, i, this.a, i + i2, (this.a.length - i) - i2);
                Arrays.fill(this.a, i, i + i2, -1);
                d(i, i2);
            }
        }

        private void d(int i, int i2) {
            if (this.b != null) {
                for (int size = this.b.size() - 1; size >= 0; size--) {
                    FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(size);
                    if (fullSpanItem.a >= i) {
                        fullSpanItem.a += i2;
                    }
                }
            }
        }

        private int g(int i) {
            if (this.b == null) {
                return -1;
            }
            FullSpanItem f = f(i);
            if (f != null) {
                this.b.remove(f);
            }
            int size = this.b.size();
            int i2 = 0;
            while (i2 < size) {
                if (((FullSpanItem) this.b.get(i2)).a >= i) {
                    break;
                }
                i2++;
            }
            i2 = -1;
            if (i2 == -1) {
                return -1;
            }
            f = (FullSpanItem) this.b.get(i2);
            this.b.remove(i2);
            return f.a;
        }

        public void a(FullSpanItem fullSpanItem) {
            if (this.b == null) {
                this.b = new ArrayList();
            }
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                FullSpanItem fullSpanItem2 = (FullSpanItem) this.b.get(i);
                if (fullSpanItem2.a == fullSpanItem.a) {
                    this.b.remove(i);
                }
                if (fullSpanItem2.a >= fullSpanItem.a) {
                    this.b.add(i, fullSpanItem);
                    return;
                }
            }
            this.b.add(fullSpanItem);
        }

        public FullSpanItem f(int i) {
            if (this.b == null) {
                return null;
            }
            for (int size = this.b.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(size);
                if (fullSpanItem.a == i) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem a(int i, int i2, int i3, boolean z) {
            if (this.b == null) {
                return null;
            }
            int size = this.b.size();
            for (int i4 = 0; i4 < size; i4++) {
                FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(i4);
                if (fullSpanItem.a >= i2) {
                    return null;
                }
                if (fullSpanItem.a >= i) {
                    if (i3 == 0 || fullSpanItem.b == i3) {
                        return fullSpanItem;
                    }
                    if (z && fullSpanItem.d) {
                        return fullSpanItem;
                    }
                }
            }
            return null;
        }
    }

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
        int c;
        int[] d;
        int e;
        int[] f;
        List<FullSpanItem> g;
        boolean h;
        boolean i;
        boolean j;

        SavedState(Parcel parcel) {
            boolean z;
            boolean z2 = true;
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            if (this.c > 0) {
                this.d = new int[this.c];
                parcel.readIntArray(this.d);
            }
            this.e = parcel.readInt();
            if (this.e > 0) {
                this.f = new int[this.e];
                parcel.readIntArray(this.f);
            }
            this.h = parcel.readInt() == 1;
            if (parcel.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            this.i = z;
            if (parcel.readInt() != 1) {
                z2 = false;
            }
            this.j = z2;
            this.g = parcel.readArrayList(FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.c = savedState.c;
            this.a = savedState.a;
            this.b = savedState.b;
            this.d = savedState.d;
            this.e = savedState.e;
            this.f = savedState.f;
            this.h = savedState.h;
            this.i = savedState.i;
            this.j = savedState.j;
            this.g = savedState.g;
        }

        void a() {
            this.d = null;
            this.c = 0;
            this.e = 0;
            this.f = null;
            this.g = null;
        }

        void b() {
            this.d = null;
            this.c = 0;
            this.a = -1;
            this.b = -1;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int i2;
            int i3 = 1;
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            if (this.c > 0) {
                parcel.writeIntArray(this.d);
            }
            parcel.writeInt(this.e);
            if (this.e > 0) {
                parcel.writeIntArray(this.f);
            }
            if (this.h) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            parcel.writeInt(i2);
            if (this.i) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            parcel.writeInt(i2);
            if (!this.j) {
                i3 = 0;
            }
            parcel.writeInt(i3);
            parcel.writeList(this.g);
        }
    }

    private class a {
        int a;
        int b;
        boolean c;
        boolean d;
        final /* synthetic */ StaggeredGridLayoutManager e;

        void a() {
            this.a = -1;
            this.b = Integer.MIN_VALUE;
            this.c = false;
            this.d = false;
        }

        void b() {
            this.b = this.c ? this.e.a.d() : this.e.a.c();
        }

        void a(int i) {
            if (this.c) {
                this.b = this.e.a.d() - i;
            } else {
                this.b = this.e.a.c() + i;
            }
        }
    }

    class b {
        int a;
        int b;
        int c;
        final int d;
        final /* synthetic */ StaggeredGridLayoutManager e;
        private ArrayList<View> f;

        int a(int i) {
            if (this.a != Integer.MIN_VALUE) {
                return this.a;
            }
            if (this.f.size() == 0) {
                return i;
            }
            a();
            return this.a;
        }

        void a() {
            View view = (View) this.f.get(0);
            LayoutParams c = c(view);
            this.a = this.e.a.a(view);
            if (c.f) {
                FullSpanItem f = this.e.f.f(c.e());
                if (f != null && f.b == -1) {
                    this.a -= f.a(this.d);
                }
            }
        }

        int b() {
            if (this.a != Integer.MIN_VALUE) {
                return this.a;
            }
            a();
            return this.a;
        }

        int b(int i) {
            if (this.b != Integer.MIN_VALUE) {
                return this.b;
            }
            if (this.f.size() == 0) {
                return i;
            }
            c();
            return this.b;
        }

        void c() {
            View view = (View) this.f.get(this.f.size() - 1);
            LayoutParams c = c(view);
            this.b = this.e.a.b(view);
            if (c.f) {
                FullSpanItem f = this.e.f.f(c.e());
                if (f != null && f.b == 1) {
                    this.b = f.a(this.d) + this.b;
                }
            }
        }

        int d() {
            if (this.b != Integer.MIN_VALUE) {
                return this.b;
            }
            c();
            return this.b;
        }

        void a(View view) {
            LayoutParams c = c(view);
            c.e = this;
            this.f.add(0, view);
            this.a = Integer.MIN_VALUE;
            if (this.f.size() == 1) {
                this.b = Integer.MIN_VALUE;
            }
            if (c.c() || c.d()) {
                this.c += this.e.a.c(view);
            }
        }

        void b(View view) {
            LayoutParams c = c(view);
            c.e = this;
            this.f.add(view);
            this.b = Integer.MIN_VALUE;
            if (this.f.size() == 1) {
                this.a = Integer.MIN_VALUE;
            }
            if (c.c() || c.d()) {
                this.c += this.e.a.c(view);
            }
        }

        void a(boolean z, int i) {
            int b;
            if (z) {
                b = b(Integer.MIN_VALUE);
            } else {
                b = a(Integer.MIN_VALUE);
            }
            e();
            if (b != Integer.MIN_VALUE) {
                if (z && b < this.e.a.d()) {
                    return;
                }
                if (z || b <= this.e.a.c()) {
                    if (i != Integer.MIN_VALUE) {
                        b += i;
                    }
                    this.b = b;
                    this.a = b;
                }
            }
        }

        void e() {
            this.f.clear();
            f();
            this.c = 0;
        }

        void f() {
            this.a = Integer.MIN_VALUE;
            this.b = Integer.MIN_VALUE;
        }

        void c(int i) {
            this.a = i;
            this.b = i;
        }

        void g() {
            int size = this.f.size();
            View view = (View) this.f.remove(size - 1);
            LayoutParams c = c(view);
            c.e = null;
            if (c.c() || c.d()) {
                this.c -= this.e.a.c(view);
            }
            if (size == 1) {
                this.a = Integer.MIN_VALUE;
            }
            this.b = Integer.MIN_VALUE;
        }

        void h() {
            View view = (View) this.f.remove(0);
            LayoutParams c = c(view);
            c.e = null;
            if (this.f.size() == 0) {
                this.b = Integer.MIN_VALUE;
            }
            if (c.c() || c.d()) {
                this.c -= this.e.a.c(view);
            }
            this.a = Integer.MIN_VALUE;
        }

        public int i() {
            return this.c;
        }

        LayoutParams c(View view) {
            return (LayoutParams) view.getLayoutParams();
        }

        void d(int i) {
            if (this.a != Integer.MIN_VALUE) {
                this.a += i;
            }
            if (this.b != Integer.MIN_VALUE) {
                this.b += i;
            }
        }
    }

    private boolean B() {
        if (r() == 0 || this.n == 0 || !m()) {
            return false;
        }
        int E;
        int F;
        if (this.c) {
            E = E();
            F = F();
        } else {
            E = F();
            F = E();
        }
        if (E == 0 && f() != null) {
            this.f.a();
            A();
            l();
            return true;
        } else if (!this.z) {
            return false;
        } else {
            int i = this.c ? -1 : 1;
            FullSpanItem a = this.f.a(E, F + 1, i, true);
            if (a == null) {
                this.z = false;
                this.f.a(F + 1);
                return false;
            }
            FullSpanItem a2 = this.f.a(E, a.a, i * -1, true);
            if (a2 == null) {
                this.f.a(a.a);
            } else {
                this.f.a(a2.a + 1);
            }
            A();
            l();
            return true;
        }
    }

    public void i(int i) {
        if (i == 0) {
            B();
        }
    }

    public void a(RecyclerView recyclerView, l lVar) {
        b(this.B);
        for (int i = 0; i < this.g; i++) {
            this.h[i].e();
        }
    }

    View f() {
        int i;
        int i2;
        int r = r() - 1;
        BitSet bitSet = new BitSet(this.g);
        bitSet.set(0, this.g, true);
        boolean z = (this.i == 1 && g()) ? true : true;
        if (this.c) {
            i = -1;
        } else {
            i = r + 1;
            r = 0;
        }
        if (r < i) {
            i2 = 1;
        } else {
            i2 = -1;
        }
        int i3 = r;
        while (i3 != i) {
            View f = f(i3);
            LayoutParams layoutParams = (LayoutParams) f.getLayoutParams();
            if (bitSet.get(layoutParams.e.d)) {
                if (a(layoutParams.e)) {
                    return f;
                }
                bitSet.clear(layoutParams.e.d);
            }
            if (!(layoutParams.f || i3 + i2 == i)) {
                boolean z2;
                View f2 = f(i3 + i2);
                int b;
                if (this.c) {
                    r = this.a.b(f);
                    b = this.a.b(f2);
                    if (r < b) {
                        return f;
                    }
                    if (r == b) {
                        z2 = true;
                    }
                    z2 = false;
                } else {
                    r = this.a.a(f);
                    b = this.a.a(f2);
                    if (r > b) {
                        return f;
                    }
                    if (r == b) {
                        z2 = true;
                    }
                    z2 = false;
                }
                if (z2) {
                    if (layoutParams.e.d - ((LayoutParams) f2.getLayoutParams()).e.d < 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (z2 != (z >= false)) {
                        return f;
                    }
                } else {
                    continue;
                }
            }
            i3 += i2;
        }
        return null;
    }

    private boolean a(b bVar) {
        if (this.c) {
            if (bVar.d() < this.a.d()) {
                return true;
            }
        } else if (bVar.b() > this.a.c()) {
            return true;
        }
        return false;
    }

    public void a(boolean z) {
        a(null);
        if (!(this.t == null || this.t.h == z)) {
            this.t.h = z;
        }
        this.l = z;
        l();
    }

    public void a(String str) {
        if (this.t == null) {
            super.a(str);
        }
    }

    private void C() {
        if (this.a == null) {
            this.a = f.a(this, this.i);
            this.b = f.a(this, 1 - this.i);
            this.k = new d();
        }
    }

    private void D() {
        boolean z = true;
        if (this.i == 1 || !g()) {
            this.c = this.l;
            return;
        }
        if (this.l) {
            z = false;
        }
        this.c = z;
    }

    boolean g() {
        return p() == 1;
    }

    public void c(l lVar, p pVar) {
        boolean z = false;
        C();
        a aVar = this.y;
        aVar.a();
        if (!(this.t == null && this.d == -1) && pVar.e() == 0) {
            c(lVar);
            return;
        }
        if (this.t != null) {
            a(aVar);
        } else {
            D();
            aVar.c = this.c;
        }
        a(pVar, aVar);
        if (this.t == null && !(aVar.c == this.o && g() == this.p)) {
            this.f.a();
            aVar.d = true;
        }
        if (r() > 0 && (this.t == null || this.t.c < 1)) {
            int i;
            if (aVar.d) {
                for (i = 0; i < this.g; i++) {
                    this.h[i].e();
                    if (aVar.b != Integer.MIN_VALUE) {
                        this.h[i].c(aVar.b);
                    }
                }
            } else {
                for (i = 0; i < this.g; i++) {
                    this.h[i].a(this.c, aVar.b);
                }
            }
        }
        a(lVar);
        this.z = false;
        h();
        a(aVar.a, pVar);
        if (aVar.c) {
            a(-1);
            a(lVar, this.k, pVar);
            a(1);
            this.k.b = aVar.a + this.k.c;
            a(lVar, this.k, pVar);
        } else {
            a(1);
            a(lVar, this.k, pVar);
            a(-1);
            this.k.b = aVar.a + this.k.c;
            a(lVar, this.k, pVar);
        }
        if (r() > 0) {
            if (this.c) {
                a(lVar, pVar, true);
                b(lVar, pVar, false);
            } else {
                b(lVar, pVar, true);
                a(lVar, pVar, false);
            }
        }
        if (!pVar.a()) {
            if (this.n != 0 && r() > 0 && (this.z || f() != null)) {
                z = true;
            }
            if (z) {
                b(this.B);
                a(this.B);
            }
            this.d = -1;
            this.e = Integer.MIN_VALUE;
        }
        this.o = aVar.c;
        this.p = g();
        this.t = null;
    }

    private void a(a aVar) {
        if (this.t.c > 0) {
            if (this.t.c == this.g) {
                for (int i = 0; i < this.g; i++) {
                    this.h[i].e();
                    int i2 = this.t.d[i];
                    if (i2 != Integer.MIN_VALUE) {
                        if (this.t.i) {
                            i2 += this.a.d();
                        } else {
                            i2 += this.a.c();
                        }
                    }
                    this.h[i].c(i2);
                }
            } else {
                this.t.a();
                this.t.a = this.t.b;
            }
        }
        this.p = this.t.j;
        a(this.t.h);
        D();
        if (this.t.a != -1) {
            this.d = this.t.a;
            aVar.c = this.t.i;
        } else {
            aVar.c = this.c;
        }
        if (this.t.e > 1) {
            this.f.a = this.t.f;
            this.f.b = this.t.g;
        }
    }

    void a(p pVar, a aVar) {
        if (!b(pVar, aVar) && !c(pVar, aVar)) {
            aVar.b();
            aVar.a = 0;
        }
    }

    private boolean c(p pVar, a aVar) {
        aVar.a = this.o ? s(pVar.e()) : r(pVar.e());
        aVar.b = Integer.MIN_VALUE;
        return true;
    }

    boolean b(p pVar, a aVar) {
        boolean z = false;
        if (pVar.a() || this.d == -1) {
            return false;
        }
        if (this.d < 0 || this.d >= pVar.e()) {
            this.d = -1;
            this.e = Integer.MIN_VALUE;
            return false;
        } else if (this.t == null || this.t.a == -1 || this.t.c < 1) {
            View b = b(this.d);
            if (b != null) {
                aVar.a = this.c ? E() : F();
                if (this.e != Integer.MIN_VALUE) {
                    if (aVar.c) {
                        aVar.b = (this.a.d() - this.e) - this.a.b(b);
                        return true;
                    }
                    aVar.b = (this.a.c() + this.e) - this.a.a(b);
                    return true;
                } else if (this.a.c(b) > this.a.f()) {
                    aVar.b = aVar.c ? this.a.d() : this.a.c();
                    return true;
                } else {
                    int a = this.a.a(b) - this.a.c();
                    if (a < 0) {
                        aVar.b = -a;
                        return true;
                    }
                    a = this.a.d() - this.a.b(b);
                    if (a < 0) {
                        aVar.b = a;
                        return true;
                    }
                    aVar.b = Integer.MIN_VALUE;
                    return true;
                }
            }
            aVar.a = this.d;
            if (this.e == Integer.MIN_VALUE) {
                if (q(aVar.a) == 1) {
                    z = true;
                }
                aVar.c = z;
                aVar.b();
            } else {
                aVar.a(this.e);
            }
            aVar.d = true;
            return true;
        } else {
            aVar.b = Integer.MIN_VALUE;
            aVar.a = this.d;
            return true;
        }
    }

    void h() {
        this.j = this.b.f() / this.g;
        this.u = MeasureSpec.makeMeasureSpec(this.b.f(), 1073741824);
        if (this.i == 1) {
            this.v = MeasureSpec.makeMeasureSpec(this.j, 1073741824);
            this.w = MeasureSpec.makeMeasureSpec(0, 0);
            return;
        }
        this.w = MeasureSpec.makeMeasureSpec(this.j, 1073741824);
        this.v = MeasureSpec.makeMeasureSpec(0, 0);
    }

    public boolean b() {
        return this.t == null;
    }

    public int b(p pVar) {
        return a(pVar);
    }

    private int a(p pVar) {
        boolean z = false;
        if (r() == 0) {
            return 0;
        }
        C();
        f fVar = this.a;
        View a = a(!this.A, true);
        if (!this.A) {
            z = true;
        }
        return h.a(pVar, fVar, a, b(z, true), this, this.A, this.c);
    }

    public int c(p pVar) {
        return a(pVar);
    }

    public int d(p pVar) {
        return h(pVar);
    }

    private int h(p pVar) {
        boolean z = false;
        if (r() == 0) {
            return 0;
        }
        C();
        f fVar = this.a;
        View a = a(!this.A, true);
        if (!this.A) {
            z = true;
        }
        return h.a(pVar, fVar, a, b(z, true), this, this.A);
    }

    public int e(p pVar) {
        return h(pVar);
    }

    public int f(p pVar) {
        return i(pVar);
    }

    private int i(p pVar) {
        boolean z = false;
        if (r() == 0) {
            return 0;
        }
        C();
        f fVar = this.a;
        View a = a(!this.A, true);
        if (!this.A) {
            z = true;
        }
        return h.b(pVar, fVar, a, b(z, true), this, this.A);
    }

    public int g(p pVar) {
        return i(pVar);
    }

    private void a(View view, LayoutParams layoutParams) {
        if (layoutParams.f) {
            if (this.i == 1) {
                b(view, this.u, c(layoutParams.height, this.w));
            } else {
                b(view, c(layoutParams.width, this.v), this.u);
            }
        } else if (this.i == 1) {
            b(view, this.v, c(layoutParams.height, this.w));
        } else {
            b(view, c(layoutParams.width, this.v), this.w);
        }
    }

    private int c(int i, int i2) {
        return i < 0 ? i2 : MeasureSpec.makeMeasureSpec(i, 1073741824);
    }

    private void b(View view, int i, int i2) {
        a(view, this.x);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        view.measure(a(i, layoutParams.leftMargin + this.x.left, layoutParams.rightMargin + this.x.right), a(i2, layoutParams.topMargin + this.x.top, layoutParams.bottomMargin + this.x.bottom));
    }

    private int a(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            return MeasureSpec.makeMeasureSpec(Math.max(0, (MeasureSpec.getSize(i) - i2) - i3), mode);
        }
        return i;
    }

    public void a(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.t = (SavedState) parcelable;
            l();
        }
    }

    public Parcelable c() {
        if (this.t != null) {
            return new SavedState(this.t);
        }
        SavedState savedState = new SavedState();
        savedState.h = this.l;
        savedState.i = this.o;
        savedState.j = this.p;
        if (this.f == null || this.f.a == null) {
            savedState.e = 0;
        } else {
            savedState.f = this.f.a;
            savedState.e = savedState.f.length;
            savedState.g = this.f.b;
        }
        if (r() > 0) {
            C();
            savedState.a = this.o ? E() : F();
            savedState.b = i();
            savedState.c = this.g;
            savedState.d = new int[this.g];
            for (int i = 0; i < this.g; i++) {
                int b;
                if (this.o) {
                    b = this.h[i].b(Integer.MIN_VALUE);
                    if (b != Integer.MIN_VALUE) {
                        b -= this.a.d();
                    }
                } else {
                    b = this.h[i].a(Integer.MIN_VALUE);
                    if (b != Integer.MIN_VALUE) {
                        b -= this.a.c();
                    }
                }
                savedState.d[i] = b;
            }
        } else {
            savedState.a = -1;
            savedState.b = -1;
            savedState.c = 0;
        }
        return savedState;
    }

    public void a(l lVar, p pVar, View view, c cVar) {
        android.view.ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (this.i == 0) {
                cVar.c(c.l.a(layoutParams2.a(), layoutParams2.f ? this.g : 1, -1, -1, layoutParams2.f, false));
                return;
            } else {
                cVar.c(c.l.a(-1, -1, layoutParams2.a(), layoutParams2.f ? this.g : 1, layoutParams2.f, false));
                return;
            }
        }
        super.a(view, cVar);
    }

    public void a(AccessibilityEvent accessibilityEvent) {
        super.a(accessibilityEvent);
        if (r() > 0) {
            android.support.v4.view.a.l a = android.support.v4.view.a.a.a(accessibilityEvent);
            View a2 = a(false, true);
            View b = b(false, true);
            if (a2 != null && b != null) {
                int d = d(a2);
                int d2 = d(b);
                if (d < d2) {
                    a.b(d);
                    a.c(d2);
                    return;
                }
                a.b(d2);
                a.c(d);
            }
        }
    }

    int i() {
        View b = this.c ? b(true, true) : a(true, true);
        return b == null ? -1 : d(b);
    }

    public int a(l lVar, p pVar) {
        if (this.i == 0) {
            return this.g;
        }
        return super.a(lVar, pVar);
    }

    public int b(l lVar, p pVar) {
        if (this.i == 1) {
            return this.g;
        }
        return super.b(lVar, pVar);
    }

    View a(boolean z, boolean z2) {
        C();
        int c = this.a.c();
        int d = this.a.d();
        int r = r();
        View view = null;
        for (int i = 0; i < r; i++) {
            View f = f(i);
            int a = this.a.a(f);
            if (this.a.b(f) > c && a < d) {
                if (a >= c || !z) {
                    return f;
                }
                if (z2 && view == null) {
                    view = f;
                }
            }
        }
        return view;
    }

    View b(boolean z, boolean z2) {
        C();
        int c = this.a.c();
        int d = this.a.d();
        View view = null;
        for (int r = r() - 1; r >= 0; r--) {
            View f = f(r);
            int a = this.a.a(f);
            int b = this.a.b(f);
            if (b > c && a < d) {
                if (b <= d || !z) {
                    return f;
                }
                if (z2 && view == null) {
                    view = f;
                }
            }
        }
        return view;
    }

    private void a(l lVar, p pVar, boolean z) {
        int d = this.a.d() - n(this.a.d());
        if (d > 0) {
            d -= -c(-d, lVar, pVar);
            if (z && d > 0) {
                this.a.a(d);
            }
        }
    }

    private void b(l lVar, p pVar, boolean z) {
        int m = m(this.a.c()) - this.a.c();
        if (m > 0) {
            m -= c(m, lVar, pVar);
            if (z && m > 0) {
                this.a.a(-m);
            }
        }
    }

    private void a(int i, p pVar) {
        int c;
        int i2 = 0;
        this.k.a = 0;
        this.k.b = i;
        if (o()) {
            c = pVar.c();
            if (c != -1) {
                if (this.c == (c < i)) {
                    c = this.a.f();
                } else {
                    i2 = this.a.f();
                    c = 0;
                }
                if (n()) {
                    this.k.f = c + this.a.e();
                    this.k.e = -i2;
                    return;
                }
                this.k.e = this.a.c() - i2;
                this.k.f = c + this.a.d();
            }
        }
        c = 0;
        if (n()) {
            this.k.f = c + this.a.e();
            this.k.e = -i2;
            return;
        }
        this.k.e = this.a.c() - i2;
        this.k.f = c + this.a.d();
    }

    private void a(int i) {
        int i2 = 1;
        this.k.d = i;
        d dVar = this.k;
        if (this.c != (i == -1)) {
            i2 = -1;
        }
        dVar.c = i2;
    }

    public void g(int i) {
        super.g(i);
        for (int i2 = 0; i2 < this.g; i2++) {
            this.h[i2].d(i);
        }
    }

    public void h(int i) {
        super.h(i);
        for (int i2 = 0; i2 < this.g; i2++) {
            this.h[i2].d(i);
        }
    }

    public void b(RecyclerView recyclerView, int i, int i2) {
        b(i, i2, 2);
    }

    public void a(RecyclerView recyclerView, int i, int i2) {
        b(i, i2, 1);
    }

    public void a(RecyclerView recyclerView) {
        this.f.a();
        l();
    }

    public void a(RecyclerView recyclerView, int i, int i2, int i3) {
        b(i, i2, 8);
    }

    public void a(RecyclerView recyclerView, int i, int i2, Object obj) {
        b(i, i2, 4);
    }

    private void b(int i, int i2, int i3) {
        int i4;
        int i5;
        int E = this.c ? E() : F();
        if (i3 != 8) {
            i4 = i + i2;
            i5 = i;
        } else if (i < i2) {
            i4 = i2 + 1;
            i5 = i;
        } else {
            i4 = i + 1;
            i5 = i2;
        }
        this.f.b(i5);
        switch (i3) {
            case 1:
                this.f.b(i, i2);
                break;
            case 2:
                this.f.a(i, i2);
                break;
            case 8:
                this.f.a(i, 1);
                this.f.b(i2, 1);
                break;
        }
        if (i4 > E) {
            if (i5 <= (this.c ? F() : E())) {
                l();
            }
        }
    }

    private int a(l lVar, d dVar, p pVar) {
        int i;
        int n;
        this.m.set(0, this.g, true);
        if (dVar.d == 1) {
            i = dVar.f + dVar.a;
        } else {
            i = dVar.e - dVar.a;
        }
        d(dVar.d, i);
        int d = this.c ? this.a.d() : this.a.c();
        Object obj = null;
        while (dVar.a(pVar) && !this.m.isEmpty()) {
            b bVar;
            int c;
            View a = dVar.a(lVar);
            LayoutParams layoutParams = (LayoutParams) a.getLayoutParams();
            int e = layoutParams.e();
            int c2 = this.f.c(e);
            Object obj2 = c2 == -1 ? 1 : null;
            if (obj2 != null) {
                b a2 = layoutParams.f ? this.h[0] : a(dVar);
                this.f.a(e, a2);
                bVar = a2;
            } else {
                bVar = this.h[c2];
            }
            layoutParams.e = bVar;
            if (dVar.d == 1) {
                b(a);
            } else {
                b(a, 0);
            }
            a(a, layoutParams);
            if (dVar.d == 1) {
                if (layoutParams.f) {
                    n = n(d);
                } else {
                    n = bVar.b(d);
                }
                c = n + this.a.c(a);
                if (obj2 == null || !layoutParams.f) {
                    c2 = n;
                } else {
                    FullSpanItem j = j(n);
                    j.b = -1;
                    j.a = e;
                    this.f.a(j);
                    c2 = n;
                }
            } else {
                if (layoutParams.f) {
                    n = m(d);
                } else {
                    n = bVar.a(d);
                }
                c2 = n - this.a.c(a);
                if (obj2 != null && layoutParams.f) {
                    FullSpanItem k = k(n);
                    k.b = 1;
                    k.a = e;
                    this.f.a(k);
                }
                c = n;
            }
            if (layoutParams.f && dVar.c == -1) {
                if (obj2 != null) {
                    this.z = true;
                } else {
                    obj = dVar.d == 1 ? !j() ? 1 : null : !k() ? 1 : null;
                    if (obj != null) {
                        FullSpanItem f = this.f.f(e);
                        if (f != null) {
                            f.d = true;
                        }
                        this.z = true;
                    }
                }
            }
            a(a, layoutParams, dVar);
            int c3 = layoutParams.f ? this.b.c() : this.b.c() + (bVar.d * this.j);
            e = c3 + this.b.c(a);
            if (this.i == 1) {
                b(a, c3, c2, e, c);
            } else {
                b(a, c2, c3, c, e);
            }
            if (layoutParams.f) {
                d(this.k.d, i);
            } else {
                a(bVar, this.k.d, i);
            }
            a(lVar, this.k);
            obj = 1;
        }
        if (obj == null) {
            a(lVar, this.k);
        }
        if (this.k.d == -1) {
            n = this.a.c() - m(this.a.c());
        } else {
            n = n(this.a.d()) - this.a.d();
        }
        return n > 0 ? Math.min(dVar.a, n) : 0;
    }

    private FullSpanItem j(int i) {
        FullSpanItem fullSpanItem = new FullSpanItem();
        fullSpanItem.c = new int[this.g];
        for (int i2 = 0; i2 < this.g; i2++) {
            fullSpanItem.c[i2] = i - this.h[i2].b(i);
        }
        return fullSpanItem;
    }

    private FullSpanItem k(int i) {
        FullSpanItem fullSpanItem = new FullSpanItem();
        fullSpanItem.c = new int[this.g];
        for (int i2 = 0; i2 < this.g; i2++) {
            fullSpanItem.c[i2] = this.h[i2].a(i) - i;
        }
        return fullSpanItem;
    }

    private void a(View view, LayoutParams layoutParams, d dVar) {
        if (dVar.d == 1) {
            if (layoutParams.f) {
                o(view);
            } else {
                layoutParams.e.b(view);
            }
        } else if (layoutParams.f) {
            p(view);
        } else {
            layoutParams.e.a(view);
        }
    }

    private void a(l lVar, d dVar) {
        if (dVar.a == 0) {
            if (dVar.d == -1) {
                b(lVar, dVar.f);
            } else {
                a(lVar, dVar.e);
            }
        } else if (dVar.d == -1) {
            r0 = dVar.e - l(dVar.e);
            if (r0 < 0) {
                r0 = dVar.f;
            } else {
                r0 = dVar.f - Math.min(r0, dVar.a);
            }
            b(lVar, r0);
        } else {
            r0 = o(dVar.f) - dVar.f;
            if (r0 < 0) {
                r0 = dVar.e;
            } else {
                r0 = Math.min(r0, dVar.a) + dVar.e;
            }
            a(lVar, r0);
        }
    }

    private void o(View view) {
        for (int i = this.g - 1; i >= 0; i--) {
            this.h[i].b(view);
        }
    }

    private void p(View view) {
        for (int i = this.g - 1; i >= 0; i--) {
            this.h[i].a(view);
        }
    }

    private void b(View view, int i, int i2, int i3, int i4) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        a(view, i + layoutParams.leftMargin, i2 + layoutParams.topMargin, i3 - layoutParams.rightMargin, i4 - layoutParams.bottomMargin);
    }

    private void d(int i, int i2) {
        for (int i3 = 0; i3 < this.g; i3++) {
            if (!this.h[i3].f.isEmpty()) {
                a(this.h[i3], i, i2);
            }
        }
    }

    private void a(b bVar, int i, int i2) {
        int i3 = bVar.i();
        if (i == -1) {
            if (i3 + bVar.b() <= i2) {
                this.m.set(bVar.d, false);
            }
        } else if (bVar.d() - i3 >= i2) {
            this.m.set(bVar.d, false);
        }
    }

    private int l(int i) {
        int a = this.h[0].a(i);
        for (int i2 = 1; i2 < this.g; i2++) {
            int a2 = this.h[i2].a(i);
            if (a2 > a) {
                a = a2;
            }
        }
        return a;
    }

    private int m(int i) {
        int a = this.h[0].a(i);
        for (int i2 = 1; i2 < this.g; i2++) {
            int a2 = this.h[i2].a(i);
            if (a2 < a) {
                a = a2;
            }
        }
        return a;
    }

    boolean j() {
        int b = this.h[0].b(Integer.MIN_VALUE);
        for (int i = 1; i < this.g; i++) {
            if (this.h[i].b(Integer.MIN_VALUE) != b) {
                return false;
            }
        }
        return true;
    }

    boolean k() {
        int a = this.h[0].a(Integer.MIN_VALUE);
        for (int i = 1; i < this.g; i++) {
            if (this.h[i].a(Integer.MIN_VALUE) != a) {
                return false;
            }
        }
        return true;
    }

    private int n(int i) {
        int b = this.h[0].b(i);
        for (int i2 = 1; i2 < this.g; i2++) {
            int b2 = this.h[i2].b(i);
            if (b2 > b) {
                b = b2;
            }
        }
        return b;
    }

    private int o(int i) {
        int b = this.h[0].b(i);
        for (int i2 = 1; i2 < this.g; i2++) {
            int b2 = this.h[i2].b(i);
            if (b2 < b) {
                b = b2;
            }
        }
        return b;
    }

    private void a(l lVar, int i) {
        while (r() > 0) {
            View f = f(0);
            if (this.a.b(f) <= i) {
                LayoutParams layoutParams = (LayoutParams) f.getLayoutParams();
                if (layoutParams.f) {
                    int i2 = 0;
                    while (i2 < this.g) {
                        if (this.h[i2].f.size() != 1) {
                            i2++;
                        } else {
                            return;
                        }
                    }
                    for (i2 = 0; i2 < this.g; i2++) {
                        this.h[i2].h();
                    }
                } else if (layoutParams.e.f.size() != 1) {
                    layoutParams.e.h();
                } else {
                    return;
                }
                a(f, lVar);
            } else {
                return;
            }
        }
    }

    private void b(l lVar, int i) {
        int r = r() - 1;
        while (r >= 0) {
            View f = f(r);
            if (this.a.a(f) >= i) {
                LayoutParams layoutParams = (LayoutParams) f.getLayoutParams();
                if (layoutParams.f) {
                    int i2 = 0;
                    while (i2 < this.g) {
                        if (this.h[i2].f.size() != 1) {
                            i2++;
                        } else {
                            return;
                        }
                    }
                    for (i2 = 0; i2 < this.g; i2++) {
                        this.h[i2].g();
                    }
                } else if (layoutParams.e.f.size() != 1) {
                    layoutParams.e.g();
                } else {
                    return;
                }
                a(f, lVar);
                r--;
            } else {
                return;
            }
        }
    }

    private boolean p(int i) {
        if (this.i == 0) {
            boolean z;
            if (i == -1) {
                z = true;
            } else {
                z = false;
            }
            if (z != this.c) {
                return true;
            }
            return false;
        }
        if (((i == -1) == this.c) != g()) {
            return false;
        }
        return true;
    }

    private b a(d dVar) {
        int i;
        int i2;
        b bVar = null;
        int i3 = -1;
        if (p(dVar.d)) {
            i = this.g - 1;
            i2 = -1;
        } else {
            i = 0;
            i2 = this.g;
            i3 = 1;
        }
        int c;
        int i4;
        b bVar2;
        int b;
        b bVar3;
        if (dVar.d == 1) {
            c = this.a.c();
            i4 = i;
            i = Integer.MAX_VALUE;
            while (i4 != i2) {
                bVar2 = this.h[i4];
                b = bVar2.b(c);
                if (b < i) {
                    bVar3 = bVar2;
                } else {
                    b = i;
                    bVar3 = bVar;
                }
                i4 += i3;
                bVar = bVar3;
                i = b;
            }
        } else {
            c = this.a.d();
            i4 = i;
            i = Integer.MIN_VALUE;
            while (i4 != i2) {
                bVar2 = this.h[i4];
                b = bVar2.a(c);
                if (b > i) {
                    bVar3 = bVar2;
                } else {
                    b = i;
                    bVar3 = bVar;
                }
                i4 += i3;
                bVar = bVar3;
                i = b;
            }
        }
        return bVar;
    }

    public boolean e() {
        return this.i == 1;
    }

    public boolean d() {
        return this.i == 0;
    }

    public int a(int i, l lVar, p pVar) {
        return c(i, lVar, pVar);
    }

    public int b(int i, l lVar, p pVar) {
        return c(i, lVar, pVar);
    }

    private int q(int i) {
        int i2 = -1;
        if (r() != 0) {
            if ((i < F()) == this.c) {
                i2 = 1;
            }
            return i2;
        } else if (this.c) {
            return 1;
        } else {
            return -1;
        }
    }

    public void c(int i) {
        if (!(this.t == null || this.t.a == i)) {
            this.t.b();
        }
        this.d = i;
        this.e = Integer.MIN_VALUE;
        l();
    }

    int c(int i, l lVar, p pVar) {
        int i2;
        int E;
        C();
        if (i > 0) {
            i2 = 1;
            E = E();
        } else {
            i2 = -1;
            E = F();
        }
        a(E, pVar);
        a(i2);
        this.k.b = E + this.k.c;
        E = Math.abs(i);
        this.k.a = E;
        i2 = a(lVar, this.k, pVar);
        if (E >= i2) {
            i = i < 0 ? -i2 : i2;
        }
        this.a.a(-i);
        this.o = this.c;
        return i;
    }

    private int E() {
        int r = r();
        return r == 0 ? 0 : d(f(r - 1));
    }

    private int F() {
        if (r() == 0) {
            return 0;
        }
        return d(f(0));
    }

    private int r(int i) {
        int r = r();
        for (int i2 = 0; i2 < r; i2++) {
            int d = d(f(i2));
            if (d >= 0 && d < i) {
                return d;
            }
        }
        return 0;
    }

    private int s(int i) {
        for (int r = r() - 1; r >= 0; r--) {
            int d = d(f(r));
            if (d >= 0 && d < i) {
                return d;
            }
        }
        return 0;
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
}
