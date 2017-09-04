package format.epub.common.a;

import format.epub.common.c.a.e;
import format.epub.common.c.a.g;
import format.epub.common.text.model.a.d;
import format.epub.common.text.model.i;
import format.epub.common.text.model.n;
import format.epub.common.text.model.o;
import format.epub.common.utils.j;
import java.nio.charset.CharsetDecoder;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BookReader */
public class b {
    public final a a;
    private o b = null;
    private boolean c = false;
    private boolean d = false;
    private char[] e = new char[4096];
    private int f;
    private StringBuilder g = new StringBuilder();
    private byte[] h = new byte[20];
    private int i;
    private byte j;
    private String k = "";
    private boolean l = false;
    private boolean m = false;
    private c n;
    private CharsetDecoder o;
    private Map<String, g> p = new HashMap();
    private Map<String, e> q = new HashMap();
    private byte[] r = new byte[4];

    public b(a aVar) {
        this.a = aVar;
        this.n = aVar.c;
    }

    private final void i() {
        if (this.f > 0) {
            this.b.a(this.e, 0, this.f);
            this.f = 0;
            if (this.o != null) {
                this.o.reset();
            }
        }
    }

    public final void a(byte b, boolean z) {
        if (this.c) {
            i();
            this.b.a(b, z);
        }
        if (!z && this.k.length() != 0 && b == this.j) {
            this.k = "";
        }
    }

    public final void a(n nVar, int i) {
        if (this.c) {
            i();
            this.b.a(nVar, i);
        }
    }

    public final void a(boolean z) {
        if (this.c) {
            i();
            this.b.a(z);
        }
    }

    public final void b(boolean z) {
        i();
        this.b.a(z);
    }

    public void a(d dVar) {
        if (this.b != null) {
            this.m = true;
            b();
            a();
            this.b.a(dVar);
            b();
        }
    }

    public final void a(byte b) {
        byte[] bArr = this.h;
        if (bArr.length == this.i) {
            bArr = j.a(bArr, this.i, this.i << 1);
            this.h = bArr;
        }
        int i = this.i;
        this.i = i + 1;
        bArr[i] = b;
    }

    public final void a() {
        b((byte) 0);
    }

    public final void b(byte b) {
        b();
        o oVar = this.b;
        if (oVar != null) {
            oVar.a(b);
            byte[] bArr = this.h;
            int i = this.i;
            for (int i2 = 0; i2 < i; i2++) {
                oVar.a(bArr[i2], true);
            }
            if (this.k.length() != 0) {
                oVar.a(this.j, d(this.j), this.k);
            }
            this.c = true;
        }
    }

    public final void b() {
        if (this.c) {
            i();
            this.c = false;
            this.d = false;
        }
    }

    private final void c(byte b) {
        o oVar = this.b;
        if (oVar != null && this.m) {
            int b2 = oVar.b();
            if (b2 > 0 && oVar.a(b2 - 1).b() != b) {
                oVar.a(b);
                this.m = false;
            }
        }
    }

    public final void c() {
        c((byte) 4);
    }

    public final void d() {
        c((byte) 6);
    }

    public final void e() {
        if (!(this.b == null || this.b == this.a.b)) {
            this.b.e();
        }
        this.b = (o) this.a.b;
    }

    public final void a(char[] cArr) {
        a(cArr, 0, cArr.length, false);
    }

    public final void a(char[] cArr, int i, int i2, boolean z) {
        if (this.c && i2 != 0) {
            if (!(this.l || this.m)) {
                while (i2 > 0 && Character.isWhitespace(cArr[i])) {
                    i2--;
                    i++;
                }
                if (i2 == 0) {
                    return;
                }
            }
            this.d = true;
            if (z && this.f == 0 && !this.l) {
                this.b.a(cArr, i, i2);
            } else {
                int i3 = this.f;
                int i4 = i3 + i2;
                if (this.e.length < i4) {
                    this.e = j.a(this.e, i3, i4);
                }
                System.arraycopy(cArr, i, this.e, i3, i2);
                this.f = i4;
                if (this.l) {
                    a(this.e, i3, i2);
                }
            }
            if (!this.l) {
                this.m = true;
            }
        }
    }

    private static byte d(byte b) {
        return b == (byte) 37 ? (byte) 2 : (byte) 1;
    }

    public final void a(byte b, String str) {
        if (this.c) {
            i();
            this.b.a(b, d(b), str);
        }
        this.j = b;
        this.k = str;
    }

    public final void a(String str) {
        i iVar = this.b;
        if (iVar != null) {
            int b = iVar.b();
            if (this.c) {
                b--;
            }
            this.a.a(str, iVar, b);
        }
    }

    public final void b(char[] cArr) {
        a(cArr, 0, cArr.length);
    }

    public final void a(char[] cArr, int i, int i2) {
        if (i2 != 0 && this.n != null) {
            this.g.append(cArr, i, i2);
        }
    }

    public final void a(int i) {
        a(this.a.b, i);
    }

    public final void a(i iVar, int i) {
        i iVar2 = this.b;
        if (iVar2 == iVar) {
            if (i == -1) {
                i = iVar2.b();
            }
            c cVar = this.n;
            if (cVar.d <= 0) {
                this.g.delete(0, this.g.length());
            } else if (this.g.length() > 0) {
                cVar.a(this.g.toString());
                this.g.delete(0, this.g.length());
            } else if (cVar.a() == null) {
                cVar.a("...");
            }
            c cVar2 = new c(cVar);
            cVar2.a(this.b, i);
            this.n = cVar2;
        }
    }

    public final void b(int i) {
        if (this.n != null) {
            this.n.a(i);
        }
    }

    public final void f() {
        c cVar = this.n;
        if (cVar.d == 0) {
            this.g.delete(0, this.g.length());
            return;
        }
        if (this.g.length() > 0) {
            cVar.a(this.g.toString());
            this.g.delete(0, this.g.length());
        } else if (cVar.a() == null) {
            cVar.a("...");
        }
        this.n = (c) cVar.c;
    }

    public final boolean g() {
        return this.c;
    }

    public boolean h() {
        return this.d;
    }

    public final void a(String str, short s) {
        o oVar = this.b;
        if (oVar != null) {
            this.m = true;
            if (this.c) {
                i();
                oVar.a(str, s);
                return;
            }
            b((byte) 0);
            oVar.a((byte) 10, true);
            oVar.a(str, s);
            oVar.a((byte) 10, false);
            b();
        }
    }

    public final void b(String str, short s) {
        o oVar = this.b;
        if (oVar != null) {
            this.m = true;
            if (this.c) {
                i();
                oVar.a(str, s);
                return;
            }
            b((byte) 0);
            oVar.a((byte) 38, true);
            oVar.a(str, s);
            oVar.a((byte) 38, false);
            b();
        }
    }

    public final void a(String str, format.epub.common.image.b bVar) {
        this.a.a(str, bVar);
    }

    public final void a(short s) {
        if (this.c) {
            this.b.a(s);
        }
    }
}
