package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.e;
import android.support.v7.widget.RecyclerView.e.c;
import android.support.v7.widget.RecyclerView.s;
import android.view.View;

/* compiled from: SimpleItemAnimator */
public abstract class i extends e {
    boolean a = true;

    public abstract boolean a(s sVar);

    public abstract boolean a(s sVar, int i, int i2, int i3, int i4);

    public abstract boolean a(s sVar, s sVar2, int i, int i2, int i3, int i4);

    public abstract boolean b(s sVar);

    public boolean g(s sVar) {
        return !this.a || sVar.n();
    }

    public boolean a(s sVar, c cVar, c cVar2) {
        int i = cVar.a;
        int i2 = cVar.b;
        View view = sVar.a;
        int left = cVar2 == null ? view.getLeft() : cVar2.a;
        int top = cVar2 == null ? view.getTop() : cVar2.b;
        if (sVar.q() || (i == left && i2 == top)) {
            return a(sVar);
        }
        view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
        return a(sVar, i, i2, left, top);
    }

    public boolean b(s sVar, c cVar, c cVar2) {
        if (cVar == null || (cVar.a == cVar2.a && cVar.b == cVar2.b)) {
            return b(sVar);
        }
        return a(sVar, cVar.a, cVar.b, cVar2.a, cVar2.b);
    }

    public boolean c(s sVar, c cVar, c cVar2) {
        if (cVar.a == cVar2.a && cVar.b == cVar2.b) {
            i(sVar);
            return false;
        }
        return a(sVar, cVar.a, cVar.b, cVar2.a, cVar2.b);
    }

    public boolean a(s sVar, s sVar2, c cVar, c cVar2) {
        int i;
        int i2;
        int i3 = cVar.a;
        int i4 = cVar.b;
        if (sVar2.c()) {
            i = cVar.a;
            i2 = cVar.b;
        } else {
            i = cVar2.a;
            i2 = cVar2.b;
        }
        return a(sVar, sVar2, i3, i4, i, i2);
    }

    public final void h(s sVar) {
        o(sVar);
        e(sVar);
    }

    public final void i(s sVar) {
        s(sVar);
        e(sVar);
    }

    public final void j(s sVar) {
        q(sVar);
        e(sVar);
    }

    public final void a(s sVar, boolean z) {
        d(sVar, z);
        e(sVar);
    }

    public final void k(s sVar) {
        n(sVar);
    }

    public final void l(s sVar) {
        r(sVar);
    }

    public final void m(s sVar) {
        p(sVar);
    }

    public final void b(s sVar, boolean z) {
        c(sVar, z);
    }

    public void n(s sVar) {
    }

    public void o(s sVar) {
    }

    public void p(s sVar) {
    }

    public void q(s sVar) {
    }

    public void r(s sVar) {
    }

    public void s(s sVar) {
    }

    public void c(s sVar, boolean z) {
    }

    public void d(s sVar, boolean z) {
    }
}
