package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.b.ax;
import oicq.wlogin_sdk.b.az;
import oicq.wlogin_sdk.b.cr;
import oicq.wlogin_sdk.b.g;
import oicq.wlogin_sdk.b.h;
import oicq.wlogin_sdk.b.u;
import oicq.wlogin_sdk.tools.ErrMsg;
import oicq.wlogin_sdk.tools.util;

/* compiled from: request_flushimage */
public class r extends oicq_request {
    public r(u uVar) {
        this.t = 2064;
        this.u = 3;
        this.v = "wtlogin.login";
        this.x = uVar;
        this.x.m = 0;
    }

    public byte[] a(byte[] bArr, int i, int i2, long[] jArr) {
        cr crVar = new cr();
        g gVar = new g();
        u uVar = new u();
        Object a = crVar.a(0, u.u, 0);
        Object a2 = gVar.a(bArr);
        Object a3 = uVar.a(i, i2, jArr);
        Object obj = new byte[((a.length + a2.length) + a3.length)];
        System.arraycopy(a, 0, obj, 0, a.length);
        int length = a.length + 0;
        System.arraycopy(a2, 0, obj, length, a2.length);
        length += a2.length;
        System.arraycopy(a3, 0, obj, length, a3.length);
        length += a3.length;
        return b((byte[]) obj, this.u, 3);
    }

    public int d(byte[] bArr, int i, int i2) {
        g gVar = new g();
        h hVar = new h();
        az azVar = new az();
        ax axVar = new ax();
        async_context b = u.b(this.x.h);
        int c = c(bArr, i + 2);
        util.LOGD(getClass().getName(), "type=" + c);
        int i3 = i + 5;
        int c2;
        switch (c) {
            case 2:
                c2 = gVar.c(bArr, i3, this.c - i3);
                if (c2 < 0) {
                    return c2;
                }
                b._t104 = gVar;
                c2 = hVar.c(bArr, i3, this.c - i3);
                if (c2 < 0) {
                    return c2;
                }
                b._t105 = hVar;
                if (azVar.c(bArr, i3, this.c - i3) >= 0) {
                    b._t165 = azVar;
                } else {
                    b._t165 = new az();
                }
                a((ErrMsg) null);
                return c;
            case 180:
                c2 = axVar.c(bArr, i3, (this.c - i3) - 1);
                if (c2 < 0) {
                    return c2;
                }
                a(axVar);
                c(bArr, i3, (this.c - i3) - 1);
                return c;
            default:
                c(bArr, i3, (this.c - i3) - 1);
                return c;
        }
    }

    public int a(int i, int i2, long[] jArr, WUserSigInfo wUserSigInfo) {
        g gVar;
        int i3 = u.w;
        g gVar2 = u.b(this.x.h)._t104;
        if (gVar2 == null) {
            gVar = new g();
        } else {
            gVar = gVar2;
        }
        int i4 = 0;
        while (true) {
            a(this.i, this.t, this.j, this.x.f, this.m, this.n, i3, this.p, a(gVar.c(), i, i2, jArr));
            int a = a(String.valueOf(this.x.f), false, wUserSigInfo);
            if (a != 0) {
                return a;
            }
            int b = b();
            util.LOGI("retry num:" + i4 + " ret:" + b, "" + this.x.f);
            if (b != 180) {
                return b;
            }
            a = i4 + 1;
            if (i4 >= 1) {
                return b;
            }
            i4 = a;
        }
    }
}
