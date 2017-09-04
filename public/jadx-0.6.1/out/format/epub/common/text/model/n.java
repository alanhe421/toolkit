package format.epub.common.text.model;

import android.text.TextUtils;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.qq.reader.common.utils.ao;
import format.epub.common.c.a.g.b;
import format.epub.common.image.c;
import format.epub.options.ZLBoolean3;
import java.util.List;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: ZLTextStyleEntry */
public class n {
    private int A;
    public short a;
    public int b;
    public a[] c;
    public byte d;
    public byte e;
    public format.epub.common.c.a.g.a[] f;
    private byte g;
    private byte h;
    private String i;
    private String j;
    private byte k;
    private int l;
    private int m;
    private String n;
    private boolean o;
    private int p;
    private byte q;
    private byte r;
    private String s;
    private String t;
    private String u;
    private c v;
    private b w;
    private int x;
    private int y;
    private String z;

    /* compiled from: ZLTextStyleEntry */
    public static class a {
        public short a;
        public byte b;

        public a(short s, byte b) {
            this.a = s;
            this.b = b;
        }

        public String toString() {
            return this.a + "." + this.b;
        }
    }

    public boolean a() {
        return this.o;
    }

    public void a(boolean z) {
        this.o = z;
    }

    static boolean a(int i, int i2) {
        return ((1 << i2) & i) != 0;
    }

    public boolean a(int i) {
        return a(this.b, i);
    }

    public int b() {
        return this.p;
    }

    public String c() {
        return this.s;
    }

    public void a(String str) {
        this.s = str;
    }

    public String d() {
        return this.t;
    }

    public void b(String str) {
        this.t = str;
    }

    public void b(int i) {
        this.p |= i;
    }

    public byte e() {
        return this.q;
    }

    public void a(byte b) {
        this.b |= SigType.WLOGIN_PAYTOKEN;
        this.q = b;
    }

    public byte f() {
        return this.r;
    }

    public void b(byte b) {
        this.b |= SigType.WLOGIN_QRPUSH;
        this.r = b;
    }

    public n(short s) {
        this.a = (short) 0;
        this.c = new a[12];
        this.k = c.a;
        this.f = new format.epub.common.c.a.g.a[4];
        this.a = s;
    }

    public n() {
        this.a = (short) 0;
        this.c = new a[12];
        this.k = c.a;
        this.f = new format.epub.common.c.a.g.a[4];
    }

    public void a(int i, short s, byte b) {
        this.b |= 1 << i;
        this.c[i] = new a(s, b);
    }

    private static int a(h hVar, int i, int i2) {
        switch (i2) {
            case 5:
            case 6:
            case 7:
            case 8:
            case 18:
            case 20:
                return hVar.c;
            case 9:
            case 10:
                return i;
            default:
                return hVar.b;
        }
    }

    public final int a(int i, h hVar, int i2) {
        return a(this.c[i], hVar, i2, i);
    }

    public static int a(a aVar, h hVar, int i, int i2) {
        switch (aVar.b) {
            case (byte) 1:
                return (aVar.a * hVar.a) / 72;
            case (byte) 2:
                return ((aVar.a * i) + 50) / 100;
            case (byte) 3:
                return ((aVar.a * hVar.d) + 50) / 100;
            case (byte) 4:
                return (((aVar.a * i) / 2) + 50) / 100;
            case (byte) 5:
                return ((aVar.a * a(hVar, i, i2)) + 50) / 100;
            default:
                return ao.a((float) aVar.a);
        }
    }

    public void c(byte b) {
        this.b |= 4096;
        this.h = b;
    }

    public byte g() {
        return this.h;
    }

    public void c(String str) {
        this.b |= 8192;
        this.i = str;
    }

    public String h() {
        return this.i;
    }

    public void d(String str) {
        this.b |= 32768;
        this.j = str;
    }

    public String i() {
        return this.j;
    }

    public void a(byte b, byte b2) {
        this.b |= 16384;
        this.d = b;
        this.e = b2;
    }

    public void a(byte b, boolean z) {
        this.b |= 16384;
        this.d = (byte) (this.d | b);
        if (z) {
            this.e = (byte) (this.e | b);
        } else {
            this.e = (byte) (this.e & (b ^ -1));
        }
    }

    public ZLBoolean3 d(byte b) {
        if ((this.d & b) == 0) {
            return ZLBoolean3.B3_UNDEFINED;
        }
        return (this.e & b) == 0 ? ZLBoolean3.B3_FALSE : ZLBoolean3.B3_TRUE;
    }

    public final void c(int i) {
        this.b |= 65536;
        this.l = i;
    }

    public final int j() {
        return this.l;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("StyleEntry[");
        stringBuilder.append("features: ").append(this.b).append(VoiceWakeuperAidl.PARAMS_SEPARATE);
        if (a(5)) {
            stringBuilder.append("space-before: ").append(this.c[5]).append(VoiceWakeuperAidl.PARAMS_SEPARATE);
        }
        if (a(6)) {
            stringBuilder.append("space-after: ").append(this.c[6]).append(VoiceWakeuperAidl.PARAMS_SEPARATE);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public byte k() {
        return this.g;
    }

    public void e(byte b) {
        this.g = b;
    }

    public int l() {
        return this.m;
    }

    public void d(int i) {
        this.m = i;
    }

    public final n m() {
        n nVar = new n((short) 0);
        nVar.e(this.g);
        nVar.b = this.b;
        for (int i = 0; i < 12; i++) {
            nVar.c[i] = this.c[i];
        }
        nVar.y = this.y;
        nVar.h = this.h;
        nVar.d = this.d;
        nVar.e = this.e;
        nVar.i = this.i;
        nVar.j = this.j;
        nVar.n = this.n;
        nVar.q = this.q;
        nVar.r = this.r;
        nVar.u = this.u;
        nVar.l = this.l;
        nVar.w = this.w;
        nVar.x = this.x;
        nVar.s = this.s;
        nVar.o = this.o;
        nVar.p = this.p;
        System.arraycopy(this.f, 0, nVar.f, 0, 4);
        return nVar;
    }

    public final n n() {
        n nVar = new n((short) 0);
        nVar.A = 1;
        nVar.e(this.g);
        nVar.b = this.b;
        nVar.y = this.y;
        for (int i = 0; i < 12; i++) {
            nVar.c[i] = this.c[i];
        }
        nVar.h = this.h;
        nVar.d = this.d;
        nVar.e = this.e;
        nVar.i = this.i;
        nVar.j = this.j;
        nVar.n = this.n;
        nVar.q = this.q;
        nVar.r = this.r;
        nVar.u = this.u;
        nVar.w = this.w;
        nVar.l = this.l;
        nVar.x = this.x;
        System.arraycopy(this.f, 0, nVar.f, 0, 4);
        return nVar;
    }

    public static n a(n nVar, n nVar2) {
        int i = 0;
        n m = nVar.m();
        if (nVar.A == 1) {
            m.A = 1;
        }
        if (nVar2.A == 1) {
            m.A = 1;
        }
        m.b |= nVar2.b;
        for (int i2 = 0; i2 < 12; i2++) {
            if (nVar2.c[i2] != null) {
                m.c[i2] = nVar2.c[i2];
            }
        }
        if (nVar2.h != (byte) 0) {
            m.h = nVar2.h;
        }
        if (nVar2.d != (byte) 0) {
            m.d = nVar2.d;
        }
        if (nVar2.e != (byte) 0) {
            m.e = nVar2.e;
        }
        if (nVar2.i != null) {
            m.i = nVar2.i;
        }
        if (nVar2.j != null) {
            m.j = nVar2.j;
        }
        if (nVar2.n != null) {
            m.n = nVar2.n;
        }
        if (nVar2.q != (byte) 0) {
            m.q = nVar2.q;
        }
        if (nVar2.r != (byte) 0) {
            m.r = nVar2.r;
        }
        if (nVar2.u != null) {
            m.u = nVar2.u;
        }
        if (nVar2.w != null) {
            m.w = nVar2.w;
        }
        if (nVar2.l != 0) {
            m.l = nVar2.l;
        }
        if (nVar2.x != 0) {
            m.x = nVar2.x;
        }
        while (i < 4) {
            if (nVar2.f[i] != null && nVar2.f[i].a()) {
                m.f[i] = nVar2.f[i];
            }
            i++;
        }
        if (nVar2.o) {
            m.o = true;
        }
        if (!TextUtils.isEmpty(nVar2.s)) {
            m.s = nVar2.s;
        }
        if (nVar2.p != 0) {
            m.p = nVar2.p;
        }
        return m;
    }

    public static n a(List<n> list) {
        if (list == null) {
            return null;
        }
        int size = list.size();
        if (size <= 0) {
            return null;
        }
        n nVar = (n) list.get(0);
        for (int i = 1; i < size; i++) {
            nVar = a(nVar, (n) list.get(i));
        }
        return nVar;
    }

    public void a(b bVar) {
        this.b |= SigType.WLOGIN_DA2;
        this.w = bVar;
    }

    public b o() {
        return this.w;
    }

    public void a(format.epub.common.c.a.g.a[] aVarArr) {
        System.arraycopy(aVarArr, 0, this.f, 0, 4);
        format.epub.common.c.a.g.a aVar = aVarArr[0];
        format.epub.common.c.a.g.a aVar2 = aVarArr[1];
        format.epub.common.c.a.g.a aVar3 = aVarArr[2];
        format.epub.common.c.a.g.a aVar4 = aVarArr[3];
        if (aVar.a()) {
            this.b |= 262144;
        }
        if (aVar2.a()) {
            this.b |= 524288;
        }
        if (aVar3.a()) {
            this.b |= 1048576;
        }
        if (aVar4.a()) {
            this.b |= 2097152;
        }
    }

    public void e(int i) {
        this.A = i;
    }

    public int p() {
        return this.A;
    }

    public String q() {
        return this.n;
    }

    public void e(String str) {
        this.b |= SigType.WLOGIN_LHSIG;
        this.n = str;
    }

    public String r() {
        return this.u;
    }

    public void f(String str) {
        this.b |= SigType.WLOGIN_PF;
        this.u = str;
    }

    public format.epub.common.c.a.g.a[] s() {
        return this.f;
    }

    public int b(int i, h hVar, int i2) {
        format.epub.common.c.a.g.a aVar = null;
        if (i == 18) {
            aVar = this.f[0];
        } else if (i == 19) {
            aVar = this.f[1];
        } else if (i == 20) {
            aVar = this.f[2];
        } else if (i == 21) {
            aVar = this.f[3];
        }
        return a(new a(aVar.b, aVar.c), hVar, i2, i);
    }

    public int c(int i, h hVar, int i2) {
        format.epub.common.c.a.g.a aVar = null;
        if (i == 18) {
            aVar = this.f[0];
        } else if (i == 19) {
            aVar = this.f[1];
        } else if (i == 20) {
            aVar = this.f[2];
        } else if (i == 21) {
            aVar = this.f[3];
        }
        return a(new a(aVar.d, aVar.e), hVar, i2, i);
    }

    public byte f(int i) {
        format.epub.common.c.a.g.a aVar = null;
        if (i == 18) {
            aVar = this.f[0];
        } else if (i == 19) {
            aVar = this.f[1];
        } else if (i == 20) {
            aVar = this.f[2];
        } else if (i == 21) {
            aVar = this.f[3];
        }
        return aVar.a;
    }

    public c t() {
        return this.v;
    }

    public void a(c cVar) {
        this.v = cVar;
    }

    public int u() {
        return this.x;
    }

    public void g(int i) {
        this.x = i;
    }

    public int v() {
        return this.y;
    }

    public void h(int i) {
        this.y = i;
    }

    public String w() {
        return this.z;
    }

    public void g(String str) {
        this.z = str;
    }
}
