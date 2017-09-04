package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.b.cm;
import oicq.wlogin_sdk.b.cn;
import oicq.wlogin_sdk.b.co;
import oicq.wlogin_sdk.b.cr;
import oicq.wlogin_sdk.b.g;
import oicq.wlogin_sdk.b.u;
import oicq.wlogin_sdk.tools.util;

/* compiled from: request_devlock_G */
public class q extends oicq_request {
    public q(u uVar) {
        this.t = 2064;
        this.u = 20;
        this.v = "wtlogin.login";
        this.x = uVar;
        this.x.m = 0;
    }

    public byte[] a(byte[] bArr, int i, int i2, long[] jArr, byte[] bArr2) {
        cr crVar = new cr();
        g gVar = new g();
        u uVar = new u();
        cm cmVar = new cm();
        Object a = crVar.a(0, u.u, 0);
        Object a2 = gVar.a(bArr);
        Object a3 = uVar.a(i, i2, jArr);
        Object a4 = cmVar.a(bArr2);
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

    public int a(int i, int i2, long[] jArr, WUserSigInfo wUserSigInfo) {
        g gVar;
        int i3 = u.w;
        async_context b = u.b(this.x.h);
        g gVar2 = b._t104;
        if (gVar2 == null) {
            gVar = new g();
        } else {
            gVar = gVar2;
        }
        cn cnVar = b._t402;
        if (cnVar == null) {
            cnVar = new cn();
        }
        co coVar = b._t403;
        if (coVar == null) {
            coVar = new co();
        }
        b._dpwd = util.get_mpasswd().getBytes();
        b._G = c(u.A, b._dpwd, cnVar.c());
        if (cnVar.d() > 0 && r3.d() > 0) {
            b._sec_guid_flag = true;
        }
        int i4 = 0;
        while (true) {
            a(this.i, this.t, this.j, this.x.f, this.m, this.n, i3, this.p, a(gVar.c(), i, i2, jArr, b._G));
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
