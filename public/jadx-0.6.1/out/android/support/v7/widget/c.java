package android.support.v7.widget;

import android.support.v4.view.ao;
import android.support.v4.view.ar;
import android.support.v4.view.z;
import android.support.v7.widget.RecyclerView.s;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: DefaultItemAnimator */
public class c extends i {
    private ArrayList<s> b = new ArrayList();
    private ArrayList<s> c = new ArrayList();
    private ArrayList<b> d = new ArrayList();
    private ArrayList<a> e = new ArrayList();
    private ArrayList<ArrayList<s>> f = new ArrayList();
    private ArrayList<ArrayList<b>> g = new ArrayList();
    private ArrayList<ArrayList<a>> h = new ArrayList();
    private ArrayList<s> i = new ArrayList();
    private ArrayList<s> j = new ArrayList();
    private ArrayList<s> k = new ArrayList();
    private ArrayList<s> l = new ArrayList();

    /* compiled from: DefaultItemAnimator */
    private static class c implements ar {
        private c() {
        }

        public void a(View view) {
        }

        public void b(View view) {
        }

        public void c(View view) {
        }
    }

    /* compiled from: DefaultItemAnimator */
    private static class a {
        public s a;
        public s b;
        public int c;
        public int d;
        public int e;
        public int f;

        private a(s sVar, s sVar2) {
            this.a = sVar;
            this.b = sVar2;
        }

        private a(s sVar, s sVar2, int i, int i2, int i3, int i4) {
            this(sVar, sVar2);
            this.c = i;
            this.d = i2;
            this.e = i3;
            this.f = i4;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.a + ", newHolder=" + this.b + ", fromX=" + this.c + ", fromY=" + this.d + ", toX=" + this.e + ", toY=" + this.f + '}';
        }
    }

    /* compiled from: DefaultItemAnimator */
    private static class b {
        public s a;
        public int b;
        public int c;
        public int d;
        public int e;

        private b(s sVar, int i, int i2, int i3, int i4) {
            this.a = sVar;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }
    }

    public void a() {
        int i;
        int i2;
        int i3;
        int i4 = !this.b.isEmpty() ? 1 : 0;
        if (this.d.isEmpty()) {
            i = 0;
        } else {
            i = 1;
        }
        if (this.e.isEmpty()) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        if (this.c.isEmpty()) {
            i3 = 0;
        } else {
            i3 = 1;
        }
        if (i4 != 0 || i != 0 || i3 != 0 || i2 != 0) {
            final ArrayList arrayList;
            Runnable anonymousClass1;
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                t((s) it.next());
            }
            this.b.clear();
            if (i != 0) {
                arrayList = new ArrayList();
                arrayList.addAll(this.d);
                this.g.add(arrayList);
                this.d.clear();
                anonymousClass1 = new Runnable(this) {
                    final /* synthetic */ c b;

                    public void run() {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            b bVar = (b) it.next();
                            this.b.b(bVar.a, bVar.b, bVar.c, bVar.d, bVar.e);
                        }
                        arrayList.clear();
                        this.b.g.remove(arrayList);
                    }
                };
                if (i4 != 0) {
                    z.a(((b) arrayList.get(0)).a.a, anonymousClass1, f());
                } else {
                    anonymousClass1.run();
                }
            }
            if (i2 != 0) {
                arrayList = new ArrayList();
                arrayList.addAll(this.e);
                this.h.add(arrayList);
                this.e.clear();
                anonymousClass1 = new Runnable(this) {
                    final /* synthetic */ c b;

                    public void run() {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            this.b.a((a) it.next());
                        }
                        arrayList.clear();
                        this.b.h.remove(arrayList);
                    }
                };
                if (i4 != 0) {
                    z.a(((a) arrayList.get(0)).a.a, anonymousClass1, f());
                } else {
                    anonymousClass1.run();
                }
            }
            if (i3 != 0) {
                final ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(this.c);
                this.f.add(arrayList2);
                this.c.clear();
                Runnable anonymousClass3 = new Runnable(this) {
                    final /* synthetic */ c b;

                    public void run() {
                        Iterator it = arrayList2.iterator();
                        while (it.hasNext()) {
                            this.b.u((s) it.next());
                        }
                        arrayList2.clear();
                        this.b.f.remove(arrayList2);
                    }
                };
                if (i4 == 0 && i == 0 && i2 == 0) {
                    anonymousClass3.run();
                    return;
                }
                long d;
                long g;
                long f = i4 != 0 ? f() : 0;
                if (i != 0) {
                    d = d();
                } else {
                    d = 0;
                }
                if (i2 != 0) {
                    g = g();
                } else {
                    g = 0;
                }
                z.a(((s) arrayList2.get(0)).a, anonymousClass3, f + Math.max(d, g));
            }
        }
    }

    public boolean a(s sVar) {
        v(sVar);
        this.b.add(sVar);
        return true;
    }

    private void t(final s sVar) {
        final ao p = z.p(sVar.a);
        this.k.add(sVar);
        p.a(f()).a(0.0f).a(new c(this) {
            final /* synthetic */ c c;

            public void a(View view) {
                this.c.k(sVar);
            }

            public void b(View view) {
                p.a(null);
                z.c(view, 1.0f);
                this.c.h(sVar);
                this.c.k.remove(sVar);
                this.c.j();
            }
        }).b();
    }

    public boolean b(s sVar) {
        v(sVar);
        z.c(sVar.a, 0.0f);
        this.c.add(sVar);
        return true;
    }

    private void u(final s sVar) {
        final ao p = z.p(sVar.a);
        this.i.add(sVar);
        p.a(1.0f).a(e()).a(new c(this) {
            final /* synthetic */ c c;

            public void a(View view) {
                this.c.m(sVar);
            }

            public void c(View view) {
                z.c(view, 1.0f);
            }

            public void b(View view) {
                p.a(null);
                this.c.j(sVar);
                this.c.i.remove(sVar);
                this.c.j();
            }
        }).b();
    }

    public boolean a(s sVar, int i, int i2, int i3, int i4) {
        View view = sVar.a;
        int l = (int) (((float) i) + z.l(sVar.a));
        int m = (int) (((float) i2) + z.m(sVar.a));
        v(sVar);
        int i5 = i3 - l;
        int i6 = i4 - m;
        if (i5 == 0 && i6 == 0) {
            i(sVar);
            return false;
        }
        if (i5 != 0) {
            z.a(view, (float) (-i5));
        }
        if (i6 != 0) {
            z.b(view, (float) (-i6));
        }
        this.d.add(new b(sVar, l, m, i3, i4));
        return true;
    }

    private void b(s sVar, int i, int i2, int i3, int i4) {
        View view = sVar.a;
        final int i5 = i3 - i;
        final int i6 = i4 - i2;
        if (i5 != 0) {
            z.p(view).b(0.0f);
        }
        if (i6 != 0) {
            z.p(view).c(0.0f);
        }
        final ao p = z.p(view);
        this.j.add(sVar);
        final s sVar2 = sVar;
        p.a(d()).a(new c(this) {
            final /* synthetic */ c e;

            public void a(View view) {
                this.e.l(sVar2);
            }

            public void c(View view) {
                if (i5 != 0) {
                    z.a(view, 0.0f);
                }
                if (i6 != 0) {
                    z.b(view, 0.0f);
                }
            }

            public void b(View view) {
                p.a(null);
                this.e.i(sVar2);
                this.e.j.remove(sVar2);
                this.e.j();
            }
        }).b();
    }

    public boolean a(s sVar, s sVar2, int i, int i2, int i3, int i4) {
        if (sVar == sVar2) {
            return a(sVar, i, i2, i3, i4);
        }
        float l = z.l(sVar.a);
        float m = z.m(sVar.a);
        float f = z.f(sVar.a);
        v(sVar);
        int i5 = (int) (((float) (i3 - i)) - l);
        int i6 = (int) (((float) (i4 - i2)) - m);
        z.a(sVar.a, l);
        z.b(sVar.a, m);
        z.c(sVar.a, f);
        if (sVar2 != null) {
            v(sVar2);
            z.a(sVar2.a, (float) (-i5));
            z.b(sVar2.a, (float) (-i6));
            z.c(sVar2.a, 0.0f);
        }
        this.e.add(new a(sVar, sVar2, i, i2, i3, i4));
        return true;
    }

    private void a(final a aVar) {
        View view = null;
        s sVar = aVar.a;
        View view2 = sVar == null ? null : sVar.a;
        s sVar2 = aVar.b;
        if (sVar2 != null) {
            view = sVar2.a;
        }
        if (view2 != null) {
            final ao a = z.p(view2).a(g());
            this.l.add(aVar.a);
            a.b((float) (aVar.e - aVar.c));
            a.c((float) (aVar.f - aVar.d));
            a.a(0.0f).a(new c(this) {
                final /* synthetic */ c c;

                public void a(View view) {
                    this.c.b(aVar.a, true);
                }

                public void b(View view) {
                    a.a(null);
                    z.c(view, 1.0f);
                    z.a(view, 0.0f);
                    z.b(view, 0.0f);
                    this.c.a(aVar.a, true);
                    this.c.l.remove(aVar.a);
                    this.c.j();
                }
            }).b();
        }
        if (view != null) {
            a = z.p(view);
            this.l.add(aVar.b);
            a.b(0.0f).c(0.0f).a(g()).a(1.0f).a(new c(this) {
                final /* synthetic */ c d;

                public void a(View view) {
                    this.d.b(aVar.b, false);
                }

                public void b(View view) {
                    a.a(null);
                    z.c(view, 1.0f);
                    z.a(view, 0.0f);
                    z.b(view, 0.0f);
                    this.d.a(aVar.b, false);
                    this.d.l.remove(aVar.b);
                    this.d.j();
                }
            }).b();
        }
    }

    private void a(List<a> list, s sVar) {
        for (int size = list.size() - 1; size >= 0; size--) {
            a aVar = (a) list.get(size);
            if (a(aVar, sVar) && aVar.a == null && aVar.b == null) {
                list.remove(aVar);
            }
        }
    }

    private void b(a aVar) {
        if (aVar.a != null) {
            a(aVar, aVar.a);
        }
        if (aVar.b != null) {
            a(aVar, aVar.b);
        }
    }

    private boolean a(a aVar, s sVar) {
        boolean z = false;
        if (aVar.b == sVar) {
            aVar.b = null;
        } else if (aVar.a != sVar) {
            return false;
        } else {
            aVar.a = null;
            z = true;
        }
        z.c(sVar.a, 1.0f);
        z.a(sVar.a, 0.0f);
        z.b(sVar.a, 0.0f);
        a(sVar, z);
        return true;
    }

    public void c(s sVar) {
        int size;
        View view = sVar.a;
        z.p(view).a();
        for (size = this.d.size() - 1; size >= 0; size--) {
            if (((b) this.d.get(size)).a == sVar) {
                z.b(view, 0.0f);
                z.a(view, 0.0f);
                i(sVar);
                this.d.remove(size);
            }
        }
        a(this.e, sVar);
        if (this.b.remove(sVar)) {
            z.c(view, 1.0f);
            h(sVar);
        }
        if (this.c.remove(sVar)) {
            z.c(view, 1.0f);
            j(sVar);
        }
        for (size = this.h.size() - 1; size >= 0; size--) {
            List list = (ArrayList) this.h.get(size);
            a(list, sVar);
            if (list.isEmpty()) {
                this.h.remove(size);
            }
        }
        for (int size2 = this.g.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList = (ArrayList) this.g.get(size2);
            int size3 = arrayList.size() - 1;
            while (size3 >= 0) {
                if (((b) arrayList.get(size3)).a == sVar) {
                    z.b(view, 0.0f);
                    z.a(view, 0.0f);
                    i(sVar);
                    arrayList.remove(size3);
                    if (arrayList.isEmpty()) {
                        this.g.remove(size2);
                    }
                } else {
                    size3--;
                }
            }
        }
        for (size = this.f.size() - 1; size >= 0; size--) {
            arrayList = (ArrayList) this.f.get(size);
            if (arrayList.remove(sVar)) {
                z.c(view, 1.0f);
                j(sVar);
                if (arrayList.isEmpty()) {
                    this.f.remove(size);
                }
            }
        }
        if (this.k.remove(sVar)) {
        }
        if (this.i.remove(sVar)) {
        }
        if (this.l.remove(sVar)) {
        }
        if (this.j.remove(sVar)) {
            j();
        } else {
            j();
        }
    }

    private void v(s sVar) {
        android.support.v4.a.a.a(sVar.a);
        c(sVar);
    }

    public boolean b() {
        return (this.c.isEmpty() && this.e.isEmpty() && this.d.isEmpty() && this.b.isEmpty() && this.j.isEmpty() && this.k.isEmpty() && this.i.isEmpty() && this.l.isEmpty() && this.g.isEmpty() && this.f.isEmpty() && this.h.isEmpty()) ? false : true;
    }

    private void j() {
        if (!b()) {
            h();
        }
    }

    public void c() {
        int size;
        for (size = this.d.size() - 1; size >= 0; size--) {
            b bVar = (b) this.d.get(size);
            View view = bVar.a.a;
            z.b(view, 0.0f);
            z.a(view, 0.0f);
            i(bVar.a);
            this.d.remove(size);
        }
        for (size = this.b.size() - 1; size >= 0; size--) {
            h((s) this.b.get(size));
            this.b.remove(size);
        }
        for (size = this.c.size() - 1; size >= 0; size--) {
            s sVar = (s) this.c.get(size);
            z.c(sVar.a, 1.0f);
            j(sVar);
            this.c.remove(size);
        }
        for (size = this.e.size() - 1; size >= 0; size--) {
            b((a) this.e.get(size));
        }
        this.e.clear();
        if (b()) {
            int size2;
            ArrayList arrayList;
            int size3;
            for (size2 = this.g.size() - 1; size2 >= 0; size2--) {
                arrayList = (ArrayList) this.g.get(size2);
                for (size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    b bVar2 = (b) arrayList.get(size3);
                    View view2 = bVar2.a.a;
                    z.b(view2, 0.0f);
                    z.a(view2, 0.0f);
                    i(bVar2.a);
                    arrayList.remove(size3);
                    if (arrayList.isEmpty()) {
                        this.g.remove(arrayList);
                    }
                }
            }
            for (size2 = this.f.size() - 1; size2 >= 0; size2--) {
                arrayList = (ArrayList) this.f.get(size2);
                for (size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    s sVar2 = (s) arrayList.get(size3);
                    z.c(sVar2.a, 1.0f);
                    j(sVar2);
                    arrayList.remove(size3);
                    if (arrayList.isEmpty()) {
                        this.f.remove(arrayList);
                    }
                }
            }
            for (size2 = this.h.size() - 1; size2 >= 0; size2--) {
                arrayList = (ArrayList) this.h.get(size2);
                for (size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    b((a) arrayList.get(size3));
                    if (arrayList.isEmpty()) {
                        this.h.remove(arrayList);
                    }
                }
            }
            a(this.k);
            a(this.j);
            a(this.i);
            a(this.l);
            h();
        }
    }

    void a(List<s> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            z.p(((s) list.get(size)).a).a();
        }
    }
}
