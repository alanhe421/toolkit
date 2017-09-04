package android.support.v7.widget;

import android.support.v4.util.e;
import android.support.v7.widget.RecyclerView.e.c;
import android.support.v7.widget.RecyclerView.s;

/* compiled from: ViewInfoStore */
class j {
    final android.support.v4.util.a<s, a> a = new android.support.v4.util.a();
    final e<s> b = new e();

    /* compiled from: ViewInfoStore */
    interface b {
        void a(s sVar);

        void a(s sVar, c cVar, c cVar2);

        void b(s sVar, c cVar, c cVar2);

        void c(s sVar, c cVar, c cVar2);
    }

    /* compiled from: ViewInfoStore */
    static class a {
        static android.support.v4.util.g.a<a> d = new android.support.v4.util.g.b(20);
        int a;
        c b;
        c c;

        private a() {
        }

        static a a() {
            a aVar = (a) d.a();
            return aVar == null ? new a() : aVar;
        }

        static void a(a aVar) {
            aVar.a = 0;
            aVar.b = null;
            aVar.c = null;
            d.a(aVar);
        }

        static void b() {
            do {
            } while (d.a() != null);
        }
    }

    j() {
    }

    void a() {
        this.a.clear();
        this.b.c();
    }

    void a(s sVar, c cVar) {
        a aVar = (a) this.a.get(sVar);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(sVar, aVar);
        }
        aVar.b = cVar;
        aVar.a |= 4;
    }

    c a(s sVar) {
        int a = this.a.a((Object) sVar);
        if (a < 0) {
            return null;
        }
        a aVar = (a) this.a.c(a);
        if (aVar == null || (aVar.a & 4) == 0) {
            return null;
        }
        aVar.a &= -5;
        c cVar = aVar.b;
        if (aVar.a == 0) {
            this.a.d(a);
            a.a(aVar);
        }
        return cVar;
    }

    void a(long j, s sVar) {
        this.b.b(j, sVar);
    }

    void b(s sVar, c cVar) {
        a aVar = (a) this.a.get(sVar);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(sVar, aVar);
        }
        aVar.a |= 2;
        aVar.b = cVar;
    }

    boolean b(s sVar) {
        a aVar = (a) this.a.get(sVar);
        return (aVar == null || (aVar.a & 4) == 0) ? false : true;
    }

    s a(long j) {
        return (s) this.b.a(j);
    }

    void c(s sVar, c cVar) {
        a aVar = (a) this.a.get(sVar);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(sVar, aVar);
        }
        aVar.c = cVar;
        aVar.a |= 8;
    }

    void c(s sVar) {
        a aVar = (a) this.a.get(sVar);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(sVar, aVar);
        }
        r0.a |= 1;
    }

    void d(s sVar) {
        a aVar = (a) this.a.get(sVar);
        if (aVar != null) {
            aVar.a &= -2;
        }
    }

    void a(b bVar) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            s sVar = (s) this.a.b(size);
            a aVar = (a) this.a.d(size);
            if ((aVar.a & 3) == 3) {
                bVar.a(sVar);
            } else if ((aVar.a & 1) != 0) {
                bVar.a(sVar, aVar.b, aVar.c);
            } else if ((aVar.a & 14) == 14) {
                bVar.b(sVar, aVar.b, aVar.c);
            } else if ((aVar.a & 12) == 12) {
                bVar.c(sVar, aVar.b, aVar.c);
            } else if ((aVar.a & 4) != 0) {
                bVar.a(sVar, aVar.b, null);
            } else if ((aVar.a & 8) != 0) {
                bVar.b(sVar, aVar.b, aVar.c);
            } else if ((aVar.a & 2) != 0) {
            }
            a.a(aVar);
        }
    }

    void e(s sVar) {
        for (int b = this.b.b() - 1; b >= 0; b--) {
            if (sVar == this.b.c(b)) {
                this.b.a(b);
                break;
            }
        }
        a aVar = (a) this.a.remove(sVar);
        if (aVar != null) {
            a.a(aVar);
        }
    }

    void b() {
        a.b();
    }
}
