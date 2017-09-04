package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.b.cc;
import oicq.wlogin_sdk.b.cg;
import oicq.wlogin_sdk.b.cr;
import oicq.wlogin_sdk.b.g;
import oicq.wlogin_sdk.b.h;
import oicq.wlogin_sdk.b.u;
import oicq.wlogin_sdk.tools.util;

/* compiled from: request_checkimage */
public class o extends oicq_request {
    public static boolean I = false;

    public o(u uVar) {
        this.t = 2064;
        this.u = 2;
        this.v = "wtlogin.login";
        this.x = uVar;
        this.x.m = 0;
    }

    public byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int i2, long[] jArr) {
        Object a;
        cg cgVar = new cg();
        cr crVar = new cr();
        g gVar = new g();
        u uVar = new u();
        cc ccVar = new cc();
        Object a2 = crVar.a(0, u.u, 0);
        Object a3 = gVar.a(bArr);
        Object a4 = uVar.a(i, i2, jArr);
        byte[] bArr4 = new byte[0];
        if (I) {
            a = ccVar.a(bArr2);
        } else {
            a = cgVar.a(bArr2, bArr3);
        }
        Object obj = new byte[(((a.length + a2.length) + a3.length) + a4.length)];
        System.arraycopy(a, 0, obj, 0, a.length);
        int length = a.length + 0;
        System.arraycopy(a2, 0, obj, length, a2.length);
        length += a2.length;
        System.arraycopy(a3, 0, obj, length, a3.length);
        length += a3.length;
        System.arraycopy(a4, 0, obj, length, a4.length);
        length += a4.length;
        return b((byte[]) obj, this.u, 4);
    }

    public int a(byte[] bArr, int i, int i2, long[] jArr, WUserSigInfo wUserSigInfo) {
        g gVar;
        h hVar;
        int i3 = u.w;
        async_context b = u.b(this.x.h);
        g gVar2 = b._t104;
        if (gVar2 == null) {
            gVar = new g();
        } else {
            gVar = gVar2;
        }
        h hVar2 = b._t105;
        if (hVar2 == null) {
            hVar = new h();
        } else {
            hVar = hVar2;
        }
        int i4 = 0;
        while (true) {
            a(this.i, this.t, this.j, this.x.f, this.m, this.n, i3, this.p, a(gVar.c(), bArr, hVar.g(), i, i2, jArr));
            int a = a(String.valueOf(this.x.f), false, wUserSigInfo);
            if (a != 0) {
                return a;
            }
            int b2 = b();
            util.LOGI("retry num:" + i4 + " ret:" + b2, "" + this.x.f);
            if (b2 != 180) {
                return b2;
            }
            a = i4 + 1;
            if (i4 >= 1) {
                return b2;
            }
            i4 = a;
        }
    }
}
