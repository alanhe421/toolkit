package de.innosystec.unrar.unpack.ppm;

import com.tencent.tinker.android.dx.instruction.Opcodes;
import de.innosystec.unrar.b.b;

/* compiled from: PPMContext */
public class c extends d {
    public static final int a = ((f + 2) + 4);
    public static final int[] b = new int[]{25, 14, 9, 7, 5, 5, 4, 4, 4, 3, 3, 3, 2, 2, 2, 2};
    private static final int f = Math.max(6, 6);
    private int g;
    private final a h;
    private final i i;
    private int j;
    private final i k = new i(null);
    private final i l = new i(null);
    private final i m = new i(null);
    private final i n = new i(null);
    private final i o = new i(null);
    private c p = null;
    private final int[] q = new int[256];

    public c(byte[] bArr) {
        super(bArr);
        this.i = new i(bArr);
        this.h = new a(bArr);
    }

    public c a(byte[] bArr) {
        this.c = bArr;
        this.d = 0;
        this.i.a(bArr);
        this.h.a(bArr);
        return this;
    }

    public a a() {
        return this.h;
    }

    public final int b() {
        if (this.c != null) {
            this.g = b.b(this.c, this.d) & 65535;
        }
        return this.g;
    }

    public final void a(int i) {
        this.g = 65535 & i;
        if (this.c != null) {
            b.a(this.c, this.d, (short) i);
        }
    }

    public i c() {
        return this.i;
    }

    public void a(j jVar) {
        this.i.a(jVar);
    }

    public int d() {
        if (this.c != null) {
            this.j = b.c(this.c, this.d + 8);
        }
        return this.j;
    }

    public void a(c cVar) {
        b(cVar.e());
    }

    public void b(int i) {
        this.j = i;
        if (this.c != null) {
            b.c(this.c, this.d + 8, i);
        }
    }

    public void c(int i) {
        super.c(i);
        this.i.c(i + 2);
        this.h.c(i + 2);
    }

    private c b(byte[] bArr) {
        if (this.p == null) {
            this.p = new c(null);
        }
        return this.p.a(bArr);
    }

    public int a(b bVar, i iVar, j jVar) {
        c b = b(bVar.a().j());
        b.c(bVar.a().e());
        if (b != null) {
            b.a(1);
            b.a(jVar);
            b.a(this);
            iVar.a(b);
        }
        return b.e();
    }

    public void a(b bVar) {
        int b = b();
        int b2 = b() - 1;
        i iVar = new i(bVar.r());
        i iVar2 = new i(bVar.r());
        i iVar3 = new i(bVar.r());
        iVar2.c(bVar.q().e());
        while (iVar2.e() != this.h.b()) {
            iVar3.c(iVar2.e() - 6);
            i.a(iVar2, iVar3);
            iVar2.d();
        }
        iVar3.c(this.h.b());
        iVar3.d(4);
        this.h.b(4);
        int a = this.h.a() - iVar2.b();
        int i = bVar.s() != 0 ? 1 : 0;
        iVar2.b((iVar2.b() + i) >>> 1);
        this.h.a(iVar2.b());
        do {
            iVar2.f();
            a -= iVar2.b();
            iVar2.b((iVar2.b() + i) >>> 1);
            this.h.b(iVar2.b());
            iVar3.c(iVar2.e() - 6);
            if (iVar2.b() > iVar3.b()) {
                iVar.c(iVar2.e());
                j jVar = new j();
                jVar.a(iVar);
                i iVar4 = new i(bVar.r());
                i iVar5 = new i(bVar.r());
                do {
                    iVar4.c(iVar.e() - 6);
                    iVar.a(iVar4);
                    iVar.d();
                    iVar5.c(iVar.e() - 6);
                    if (iVar.e() == this.h.b()) {
                        break;
                    }
                } while (jVar.b() > iVar5.b());
                iVar.a(jVar);
            }
            b2--;
        } while (b2 != 0);
        if (iVar2.b() == 0) {
            do {
                b2++;
                iVar2.d();
            } while (iVar2.b() == 0);
            i = a + b2;
            a(b() - b2);
            if (b() == 1) {
                j jVar2 = new j();
                iVar3.c(this.h.b());
                jVar2.a(iVar3);
                do {
                    jVar2.c(jVar2.b() >>> 1);
                    i >>>= 1;
                } while (i > 1);
                bVar.a().b(this.h.b(), (b + 1) >>> 1);
                this.i.a(jVar2);
                bVar.q().c(this.i.e());
                return;
            }
        }
        i = a;
        this.h.b(i - (i >>> 1));
        i = (b + 1) >>> 1;
        int b3 = (b() + 1) >>> 1;
        if (i != b3) {
            this.h.a_(bVar.a().a(this.h.b(), i, b3));
        }
        bVar.q().c(this.h.b());
    }

    private int a(b bVar, i iVar) {
        c b = b(bVar.a().j());
        b.c(d());
        return ((bVar.o()[b.b() - 1] + (0 + bVar.j())) + (bVar.k() + (bVar.n()[iVar.a()] * 2))) + ((bVar.i() >>> 26) & 32);
    }

    public int a(int i, int i2, int i3) {
        return ((1 << (i2 - i3)) + i) >>> i2;
    }

    public void b(b bVar) {
        int i = 0;
        i a = this.k.a(bVar.r());
        a.c(this.i.e());
        bVar.h(bVar.n()[bVar.q().a()]);
        int b = a.b() - 1;
        int a2 = a(bVar, a);
        int i2 = bVar.l()[b][a2];
        if (bVar.m().a(14) < ((long) i2)) {
            bVar.q().c(a.e());
            if (a.b() < 128) {
                i = 1;
            }
            a.d(i);
            bVar.m().a().b(0);
            bVar.m().a().a((long) i2);
            bVar.l()[b][a2] = ((i2 + 128) - a(i2, 7, 2)) & 65535;
            bVar.d(1);
            bVar.g(1);
            return;
        }
        bVar.m().a().b((long) i2);
        i2 = (i2 - a(i2, 7, 2)) & 65535;
        bVar.l()[b][a2] = i2;
        bVar.m().a().a(16384);
        bVar.e(b[i2 >>> 10]);
        bVar.c(1);
        bVar.g()[a.a()] = bVar.f();
        bVar.d(0);
        bVar.q().c(0);
    }

    public void a(b bVar, int i) {
        bVar.q().c(i);
        bVar.q().d(4);
        this.h.b(4);
        i a = this.m.a(bVar.r());
        i a2 = this.n.a(bVar.r());
        a.c(i);
        a2.c(i - 6);
        if (a.b() > a2.b()) {
            i.a(a, a2);
            bVar.q().c(a2.e());
            if (a2.b() > Opcodes.NOT_INT) {
                a(bVar);
            }
        }
    }

    public boolean c(b bVar) {
        int b;
        int i = 0;
        int b2 = b() - bVar.h();
        h c = c(bVar, b2);
        e m = bVar.m();
        i a = this.k.a(bVar.r());
        i a2 = this.l.a(bVar.r());
        a.c(this.h.b() - 6);
        int i2 = b2;
        int i3 = 0;
        b2 = 0;
        while (true) {
            a.f();
            if (bVar.g()[a.a()] != bVar.f()) {
                b = a.b() + i3;
                i3 = b2 + 1;
                this.q[b2] = a.e();
                b2 = i2 - 1;
                if (b2 == 0) {
                    break;
                }
                i2 = b2;
                b2 = i3;
                i3 = b;
            }
        }
        m.a().a(b);
        long b3 = (long) m.b();
        if (b3 >= m.a().c()) {
            return false;
        }
        a.c(this.q[0]);
        if (b3 < ((long) b)) {
            b2 = 0;
            while (true) {
                b2 += a.b();
                if (((long) b2) > b3) {
                    break;
                }
                i++;
                a.c(this.q[i]);
            }
            m.a().a((long) b2);
            m.a().b((long) (b2 - a.b()));
            c.b();
            b(bVar, a.e());
        } else {
            m.a().b((long) b);
            m.a().a(m.a().c());
            i = b() - bVar.h();
            b2 = -1;
            do {
                b2++;
                a2.c(this.q[b2]);
                bVar.g()[a2.a()] = bVar.f();
                i--;
            } while (i != 0);
            c.d((int) m.a().c());
            bVar.c(b());
        }
        return true;
    }

    public void b(b bVar, int i) {
        i a = this.o.a(bVar.r());
        a.c(i);
        bVar.q().c(i);
        bVar.q().d(4);
        this.h.b(4);
        if (a.b() > Opcodes.NOT_INT) {
            a(bVar);
        }
        bVar.b(1);
        bVar.f(bVar.e());
    }

    private h c(b bVar, int i) {
        int i2 = 1;
        int b = b();
        if (b != 256) {
            int i3;
            c b2 = b(bVar.r());
            b2.c(d());
            int i4 = bVar.p()[i - 1];
            int i5 = 0 + (i < b2.b() - b ? 1 : 0);
            if (this.h.a() < b * 11) {
                i3 = 1;
            } else {
                i3 = 0;
            }
            i3 = (i3 * 2) + i5;
            if (bVar.h() <= i) {
                i2 = 0;
            }
            h hVar = bVar.c()[i4][(i3 + (i2 * 4)) + bVar.k()];
            bVar.m().a().c((long) hVar.a());
            return hVar;
        }
        hVar = bVar.d();
        bVar.m().a().c(1);
        return hVar;
    }

    public boolean d(b bVar) {
        int i = 0;
        e m = bVar.m();
        m.a().c((long) this.h.a());
        i iVar = new i(bVar.r());
        iVar.c(this.h.b());
        long b = (long) m.b();
        if (b >= m.a().c()) {
            return false;
        }
        int b2 = iVar.b();
        if (b < ((long) b2)) {
            m.a().a((long) b2);
            if (((long) (b2 * 2)) > m.a().c()) {
                i = 1;
            }
            bVar.d(i);
            bVar.g(bVar.j());
            i = b2 + 4;
            bVar.q().c(iVar.e());
            bVar.q().b(i);
            this.h.b(4);
            if (i > Opcodes.NOT_INT) {
                a(bVar);
            }
            m.a().b(0);
            return true;
        } else if (bVar.q().e() == 0) {
            return false;
        } else {
            bVar.d(0);
            int b3 = b();
            int i2 = b3 - 1;
            do {
                b2 += iVar.f().b();
                if (((long) b2) > b) {
                    m.a().b((long) (b2 - iVar.b()));
                    m.a().a((long) b2);
                    a(bVar, iVar.e());
                    return true;
                }
                i2--;
            } while (i2 != 0);
            bVar.h(bVar.n()[bVar.q().a()]);
            m.a().b((long) b2);
            bVar.g()[iVar.a()] = bVar.f();
            bVar.c(b3);
            b2 = b3 - 1;
            bVar.q().c(0);
            i = b2;
            do {
                bVar.g()[iVar.d().a()] = bVar.f();
                i--;
            } while (i != 0);
            m.a().a(m.a().c());
            return true;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PPMContext[");
        stringBuilder.append("\n  pos=");
        stringBuilder.append(this.d);
        stringBuilder.append("\n  size=");
        stringBuilder.append(a);
        stringBuilder.append("\n  numStats=");
        stringBuilder.append(b());
        stringBuilder.append("\n  Suffix=");
        stringBuilder.append(d());
        stringBuilder.append("\n  freqData=");
        stringBuilder.append(this.h);
        stringBuilder.append("\n  oneState=");
        stringBuilder.append(this.i);
        stringBuilder.append("\n]");
        return stringBuilder.toString();
    }
}
