package format.epub.view;

import com.qq.reader.module.readpage.j;
import com.qq.reader.readengine.kernel.g;
import com.qq.reader.readengine.kernel.h;

/* compiled from: ZLTextSelectionModel */
public final class u {
    private c a = null;
    private final j b;
    private boolean c = true;
    private final a d = new a();
    private final a e = new a();

    /* compiled from: ZLTextSelectionModel */
    private static final class a {
        final b a;
        final b b;

        private a() {
            this.a = new b();
            this.b = new b();
        }

        boolean a(a aVar) {
            return this.a.b(aVar.a) < 0;
        }
    }

    /* compiled from: ZLTextSelectionModel */
    public static final class b extends h {
        g a = new g();

        public g d() {
            return this.a;
        }

        public void a(g gVar) {
            this.a.a(gVar);
        }

        void a(b bVar) {
            this.a.a(bVar.d());
        }

        public final int a() {
            return this.a.b();
        }

        public final int b() {
            return this.a.c();
        }

        public final int c() {
            return this.a.d();
        }
    }

    /* compiled from: ZLTextSelectionModel */
    public class c {
        public b a = new b();
        public b b = new b();
        final /* synthetic */ u c;

        c(u uVar, b bVar, b bVar2) {
            this.c = uVar;
            this.a.a(bVar);
            this.b.a(bVar2);
        }
    }

    public u(j jVar) {
        this.b = jVar;
    }

    public c a() {
        return this.a;
    }

    public boolean a(int i, int i2) {
        if (this.b.k.b().isEmpty() || this.b.k.b().size() < 1) {
            return false;
        }
        this.c = false;
        this.a = a(this.d, this.e, i, i2);
        return true;
    }

    private c b() {
        return this.e.a(this.d) ? new c(this, this.e.b, this.d.a) : new c(this, this.d.b, this.e.a);
    }

    private c a(a aVar, a aVar2, int i, int i2) {
        ZLTextElementAreaArrayList b = this.b.k.b();
        if (b.isEmpty()) {
            return null;
        }
        int size = b.size();
        h hVar = null;
        int i3 = 0;
        float f = ((h) b.get(0)).c;
        float f2 = ((h) b.get(size - 1)).d;
        if (((float) i2) <= f || ((float) i2) >= f2) {
            return null;
        }
        float f3;
        h hVar2;
        while (i3 < size) {
            int i4;
            int i5;
            int i6;
            g gVar;
            h hVar3 = (h) b.get(i3);
            if (hVar3.c > ((float) i2) || hVar3.d < ((float) i2) || hVar3.a > ((float) i) || hVar3.b < ((float) i)) {
                i3++;
            } else {
                if (hVar3.o instanceof y) {
                    hVar = hVar3;
                }
                if (hVar == null) {
                    i4 = -1;
                    i5 = 0;
                    while (i5 < size) {
                        hVar3 = (h) b.get(i5);
                        if (hVar3.c < ((float) i2)) {
                            if (i4 == -1 && hVar3.d > ((float) i2)) {
                                f3 = (((h) b.get(i5)).c - ((h) b.get(i4)).d) / 2.0f;
                                break;
                            }
                            i6 = i4;
                        } else {
                            i6 = i5;
                        }
                        i5++;
                        i4 = i6;
                    }
                    f3 = 0.0f;
                    if (f3 != 0.0f) {
                        i5 = 0;
                        while (i5 < size) {
                            hVar3 = (h) b.get(i5);
                            if (hVar3.c - f3 <= ((float) i2) || hVar3.d + f3 < ((float) i2) || hVar3.a > ((float) i) || hVar3.b < ((float) i)) {
                                i5++;
                            } else {
                                if (hVar3.o instanceof y) {
                                    i3 = i5;
                                    hVar2 = hVar3;
                                    if (hVar2 == null) {
                                        gVar = hVar2.o;
                                        if ((gVar instanceof y) || ((y) gVar).c() <= ' ' || ((y) gVar).c() >= '') {
                                            hVar3 = hVar2;
                                        } else {
                                            char c;
                                            int i7 = i3 - 1;
                                            h hVar4 = hVar2;
                                            while (i7 >= 0) {
                                                hVar3 = (h) b.get(i7);
                                                gVar = hVar3.o;
                                                if (!(gVar instanceof y)) {
                                                    break;
                                                }
                                                c = ((y) gVar).c();
                                                if (c <= ' ' || c >= '' || hVar3.p) {
                                                    break;
                                                }
                                                i7--;
                                                hVar4 = hVar3;
                                            }
                                            i7 = i3 + 1;
                                            while (i7 < size) {
                                                hVar3 = (h) b.get(i7);
                                                gVar = hVar3.o;
                                                if (!(gVar instanceof y)) {
                                                    break;
                                                }
                                                c = ((y) gVar).c();
                                                if (c <= ' ' || c >= '') {
                                                    break;
                                                } else if (hVar3.p) {
                                                    hVar2 = hVar4;
                                                    break;
                                                } else {
                                                    i7++;
                                                    hVar2 = hVar3;
                                                }
                                            }
                                            hVar3 = hVar2;
                                            hVar2 = hVar4;
                                        }
                                    } else {
                                        hVar3 = null;
                                        hVar2 = null;
                                    }
                                    if (hVar2 != null || hVar3 == null) {
                                        return null;
                                    }
                                    aVar.b.a.a(hVar2.d());
                                    aVar.a.a.a(aVar.b.a);
                                    aVar2.b.a.a(hVar3.d());
                                    aVar2.a.a.a(aVar2.b.a);
                                    return b();
                                }
                                i3 = i5;
                                hVar2 = hVar;
                                if (hVar2 == null) {
                                    hVar3 = null;
                                    hVar2 = null;
                                } else {
                                    gVar = hVar2.o;
                                    if (gVar instanceof y) {
                                    }
                                    hVar3 = hVar2;
                                }
                                if (hVar2 != null) {
                                }
                                return null;
                            }
                        }
                        i3 = i5;
                        hVar2 = hVar;
                        if (hVar2 == null) {
                            gVar = hVar2.o;
                            if (gVar instanceof y) {
                            }
                            hVar3 = hVar2;
                        } else {
                            hVar3 = null;
                            hVar2 = null;
                        }
                        if (hVar2 != null) {
                        }
                        return null;
                    }
                }
                hVar2 = hVar;
                if (hVar2 == null) {
                    hVar3 = null;
                    hVar2 = null;
                } else {
                    gVar = hVar2.o;
                    if (gVar instanceof y) {
                    }
                    hVar3 = hVar2;
                }
                if (hVar2 != null) {
                }
                return null;
            }
        }
        if (hVar == null) {
            i4 = -1;
            i5 = 0;
            while (i5 < size) {
                hVar3 = (h) b.get(i5);
                if (hVar3.c < ((float) i2)) {
                    if (i4 == -1) {
                    }
                    i6 = i4;
                } else {
                    i6 = i5;
                }
                i5++;
                i4 = i6;
            }
            f3 = 0.0f;
            if (f3 != 0.0f) {
                i5 = 0;
                while (i5 < size) {
                    hVar3 = (h) b.get(i5);
                    if (hVar3.c - f3 <= ((float) i2)) {
                    }
                    i5++;
                }
                i3 = i5;
                hVar2 = hVar;
                if (hVar2 == null) {
                    gVar = hVar2.o;
                    if (gVar instanceof y) {
                    }
                    hVar3 = hVar2;
                } else {
                    hVar3 = null;
                    hVar2 = null;
                }
                if (hVar2 != null) {
                }
                return null;
            }
        }
        hVar2 = hVar;
        if (hVar2 == null) {
            hVar3 = null;
            hVar2 = null;
        } else {
            gVar = hVar2.o;
            if (gVar instanceof y) {
            }
            hVar3 = hVar2;
        }
        if (hVar2 != null) {
        }
        return null;
    }

    public h a(int i, float f, float f2) {
        h hVar = null;
        int i2 = 0;
        ZLTextElementAreaArrayList b = this.b.k.b();
        if (b.isEmpty()) {
            return null;
        }
        h hVar2;
        int size = b.size();
        int i3 = 0;
        while (i3 < size) {
            hVar2 = (h) b.get(i3);
            if (i != 0 || hVar2.a != f || f2 != hVar2.c) {
                if (i == 1 && hVar2.b == f && f2 == hVar2.d) {
                    hVar = hVar2;
                    break;
                }
                i3++;
            } else {
                hVar = hVar2;
                break;
            }
        }
        if (hVar == null) {
            for (i3 = 0; i3 < size; i3++) {
                hVar2 = (h) b.get(i3);
                if (f2 >= hVar2.c && f2 <= hVar2.d && hVar2.b > f) {
                    hVar = hVar2;
                    break;
                }
            }
        }
        if (hVar == null) {
            if (i != 0) {
                for (i2 = size - 1; i2 > 0; i2--) {
                    hVar2 = (h) b.get(i2);
                    if (f2 >= hVar2.c && f >= hVar2.b) {
                        break;
                    }
                }
            } else {
                while (i2 < size) {
                    hVar2 = (h) b.get(i2);
                    if (f2 < hVar2.d) {
                        break;
                    }
                    i2++;
                }
            }
        }
        hVar2 = hVar;
        if (hVar2 == null) {
            return hVar2;
        }
        b bVar = new b();
        bVar.a(hVar2.d());
        if (i == 0) {
            this.a.a = bVar;
            return hVar2;
        }
        this.a.b = bVar;
        return hVar2;
    }
}
