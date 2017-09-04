package qalsdk;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.d;
import com.tencent.qalsdk.core.b;
import com.tencent.qalsdk.sdk.ag;
import com.tencent.qalsdk.sdk.c;
import com.tencent.qalsdk.service.QalService;

/* compiled from: AppPushInfo */
public final class ah extends JceStruct {
    static com.tencent.qalsdk.sdk.ah p;
    static ag q;
    static c r;
    public int a = 0;
    public String b = "";
    public long c = 0;
    public long d = 0;
    public long e = 0;
    public long f = 0;
    public long g = 0;
    public long h = 0;
    public String i = "";
    public String j = "";
    public com.tencent.qalsdk.sdk.ah k = null;
    public ag l = null;
    public c m = null;
    public String n = "";
    public byte o = (byte) 0;

    public ah(int i, String str, long j, long j2, long j3, long j4, long j5, long j6, String str2, String str3, com.tencent.qalsdk.sdk.ah ahVar, ag agVar, c cVar, String str4, byte b) {
        this.a = i;
        this.b = str;
        this.c = j;
        this.d = j2;
        this.e = j3;
        this.f = j4;
        this.g = j5;
        this.h = j6;
        this.i = str2;
        this.j = str3;
        this.k = ahVar;
        this.l = agVar;
        this.m = cVar;
        this.n = str4;
        this.o = b;
    }

    public ah(String str) {
        this.b = str;
        this.n = "" + b.b(QalService.context);
    }

    public void writeTo(d dVar) {
        dVar.a(this.a, 1);
        dVar.a(this.b, 2);
        dVar.a(this.c, 3);
        dVar.a(this.d, 4);
        dVar.a(this.e, 5);
        dVar.a(this.f, 6);
        dVar.a(this.g, 7);
        dVar.a(this.h, 8);
        if (this.i != null) {
            dVar.a(this.i, 9);
        }
        if (this.j != null) {
            dVar.a(this.j, 10);
        }
        if (this.k != null) {
            dVar.a(this.k, 11);
        }
        if (this.l != null) {
            dVar.a(this.l, 12);
        }
        if (this.m != null) {
            dVar.a(this.m, 13);
        }
        if (this.n != null) {
            dVar.a(this.n, 14);
        }
        dVar.b(this.o, 15);
    }

    public void readFrom(com.qq.taf.jce.c cVar) {
        this.a = cVar.a(this.a, 1, true);
        this.b = cVar.a(2, true);
        this.c = cVar.a(this.c, 3, true);
        this.d = cVar.a(this.d, 4, true);
        this.e = cVar.a(this.e, 5, false);
        this.f = cVar.a(this.f, 6, false);
        this.g = cVar.a(this.g, 7, false);
        this.h = cVar.a(this.h, 8, false);
        this.i = cVar.a(9, false);
        this.j = cVar.a(10, false);
        if (p == null) {
            p = new com.tencent.qalsdk.sdk.ah();
        }
        this.k = (com.tencent.qalsdk.sdk.ah) cVar.a(p, 11, false);
        if (q == null) {
            q = new ag();
        }
        this.l = (ag) cVar.a(q, 12, false);
        if (r == null) {
            r = new c();
        }
        this.m = (c) cVar.a(r, 13, false);
        this.n = cVar.a(14, false);
        this.o = cVar.a(this.o, 15, false);
    }
}
