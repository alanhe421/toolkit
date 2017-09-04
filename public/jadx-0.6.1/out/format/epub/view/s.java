package format.epub.view;

import format.epub.common.image.b;
import format.epub.common.image.c;
import format.epub.common.text.model.d;
import format.epub.common.text.model.g;
import format.epub.common.text.model.i;
import format.epub.common.text.model.j;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ZLTextParagraphCursor */
public final class s {
    private static final char[] e = new char[]{' '};
    public final int a;
    public final i b;
    private final ArrayList<g> c = new ArrayList();
    private boolean d;

    /* compiled from: ZLTextParagraphCursor */
    private static final class a {
        private final j a;
        private final ArrayList<g> b;
        private int c;
        private int d;
        private int e;
        private final List<g> f;
        private boolean g;

        private a(j jVar, List<g> list, int i, ArrayList<g> arrayList) {
            this.a = jVar;
            this.b = arrayList;
            this.f = list;
            g gVar = new g(i, 0, 0);
            int i2 = 0;
            while (i2 < this.f.size() && ((g) this.f.get(i2)).a(gVar) < 0) {
                i2++;
            }
            this.d = i2;
            this.e = this.d;
            while (this.e != this.f.size() && ((g) this.f.get(this.e)).a == i) {
                this.e++;
            }
            this.c = 0;
        }

        void a() {
            ArrayList arrayList = this.b;
            format.epub.common.text.model.j.a a = this.a.a();
            while (a.l()) {
                a.m();
                switch (a.a()) {
                    case (byte) 1:
                        a(a.b(), a.c(), a.d());
                        break;
                    case (byte) 2:
                        d i = a.i();
                        b a2 = i.a();
                        if (a2 == null) {
                            break;
                        }
                        c a3 = format.epub.common.image.d.a().a(a2);
                        if (a3 == null) {
                            break;
                        }
                        p pVar = new p(i.a, a3, a2.j_(), i.c, i.f, i.d, i.e);
                        pVar.m = i.g;
                        pVar.n = i.h;
                        pVar.o = i.i;
                        pVar.a(i.b());
                        pVar.a(i.c());
                        pVar.a(i.d());
                        pVar.a(i.e());
                        if (pVar.d()) {
                            this.g = true;
                        }
                        arrayList.add(pVar);
                        break;
                    case (byte) 3:
                        if (a.f()) {
                            byte g = a.g();
                            if (g != (byte) 0) {
                                arrayList.add(new n(a.e(), g, a.h()));
                                break;
                            }
                        }
                        arrayList.add(f.a(a.e(), a.f()));
                        break;
                    case (byte) 4:
                        break;
                    case (byte) 5:
                        arrayList.add(j.a(a.k()));
                        break;
                    case (byte) 6:
                    case (byte) 9:
                        arrayList.add(new x(a.j()));
                        break;
                    case (byte) 7:
                        arrayList.add(g.f);
                        break;
                    case (byte) 13:
                        arrayList.add(g.g);
                        break;
                    default:
                        break;
                }
            }
        }

        private void a(char[] cArr, int i, int i2) {
            if (i2 != 0) {
                g gVar = g.c;
                ArrayList arrayList = this.b;
                int i3 = 0;
                int i4 = 0;
                Object obj = null;
                char c = '\u0000';
                while (i3 < i2) {
                    char c2 = cArr[i + i3];
                    if (Character.isSpace(c2)) {
                        if (i3 > 0 && obj == null) {
                            a(cArr, i + i4, i3 - i4, this.c + i4);
                        }
                        obj = 1;
                    } else {
                        switch (obj) {
                            case null:
                                if (i3 > 0 && i3 != i4 && (r4 != '-' || ((c2 < 'A' || c2 > 'Z') && (c2 < 'a' || c2 > 'z')))) {
                                    a(cArr, i + i4, i3 - i4, i4 + this.c);
                                    i4 = i3;
                                    break;
                                }
                            case 1:
                                arrayList.add(gVar);
                                i4 = i3;
                                break;
                        }
                        obj = null;
                    }
                    i3++;
                    c = c2;
                }
                switch (obj) {
                    case null:
                        a(cArr, i + i4, i2 - i4, i4 + this.c);
                        break;
                    case 1:
                        arrayList.add(gVar);
                        break;
                }
                this.c += i2;
            }
        }

        private final void a(char[] cArr, int i, int i2, int i3) {
            y yVar = new y(cArr, i, i2, i3);
            for (int i4 = this.d; i4 < this.e; i4++) {
                g gVar = (g) this.f.get(i4);
                if (gVar.b < i3 + i2 && gVar.b + gVar.c > i3) {
                    yVar.a(gVar.b - i3, gVar.c);
                }
            }
            this.b.add(yVar);
        }
    }

    private s(i iVar, int i) {
        this.b = iVar;
        this.a = Math.min(i, this.b.b() - 1);
        a();
    }

    public static s a(i iVar, int i) {
        s a = t.a(iVar, i);
        if (a != null) {
            return a;
        }
        a = new s(iVar, i);
        t.a(iVar, i, a);
        return a;
    }

    void a() {
        j a = this.b.a(this.a);
        switch (a.b()) {
            case (byte) 0:
                a aVar = new a(a, this.b.c(), this.a, this.c);
                aVar.a();
                if (aVar.g) {
                    this.d = true;
                    return;
                }
                return;
            case (byte) 1:
                this.c.add(new y(e, 0, 1, 0));
                return;
            default:
                return;
        }
    }

    public boolean b() {
        if (this.c == null && this.c.size() == 0) {
            return true;
        }
        return false;
    }

    public int a(int i) {
        int i2 = 0;
        if (this.c == null && i > this.c.size()) {
            return 0;
        }
        int i3 = 0;
        while (i2 < this.c.size()) {
            if (this.c.get(i2) instanceof y) {
                i3++;
            }
            if (i2 >= i) {
                break;
            }
            i2++;
        }
        return i3;
    }

    public int b(int i) {
        int i2 = 0;
        if (this.c == null && i > this.c.size()) {
            return 0;
        }
        while (i2 < this.c.size()) {
            if (this.c.get(i2) instanceof y) {
                i--;
                if (i <= 0) {
                    if (i2 == this.c.size() - 1) {
                        return -1;
                    }
                    return i2;
                }
            }
            i2++;
        }
        return -1;
    }

    public boolean c() {
        return this.a == 0;
    }

    public boolean d() {
        return this.a + 1 >= this.b.b();
    }

    public boolean e() {
        return this.b.a(this.a).b() == (byte) 4;
    }

    public int f() {
        return this.c.size();
    }

    public s g() {
        return c() ? null : a(this.b, this.a - 1);
    }

    public s h() {
        return d() ? null : a(this.b, this.a + 1);
    }

    public g c(int i) {
        try {
            return (g) this.c.get(i);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public j i() {
        return this.b.a(this.a);
    }

    public String toString() {
        return "ZLTextParagraphCursor [" + this.a + " (0.." + this.c.size() + ")]";
    }

    public boolean j() {
        return this.d;
    }
}
