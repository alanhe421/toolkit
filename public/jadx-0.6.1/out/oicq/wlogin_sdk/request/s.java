package oicq.wlogin_sdk.request;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import oicq.wlogin_sdk.b.ax;
import oicq.wlogin_sdk.b.b;
import oicq.wlogin_sdk.b.bj;
import oicq.wlogin_sdk.b.bn;
import oicq.wlogin_sdk.b.bo;
import oicq.wlogin_sdk.b.cr;
import oicq.wlogin_sdk.b.g;
import oicq.wlogin_sdk.b.u;
import oicq.wlogin_sdk.tools.ErrMsg;
import oicq.wlogin_sdk.tools.util;

/* compiled from: request_flushsms */
public class s extends oicq_request {
    static int I = 0;

    public s(u uVar) {
        this.t = 2064;
        this.u = 8;
        this.v = "wtlogin.login";
        this.x = uVar;
        this.x.m = 0;
    }

    public byte[] a(byte[] bArr, long j, byte[] bArr2, int i, int i2, long[] jArr) {
        cr crVar = new cr();
        g gVar = new g();
        u uVar = new u();
        bj bjVar = new bj();
        bn bnVar = new bn();
        b bVar = new b(ErrorCode.INFO_CAN_NOT_LOAD_X5);
        Object a = crVar.a(0, u.u, 0);
        Object a2 = gVar.a(bArr);
        Object a3 = uVar.a(i, i2, jArr);
        Object a4 = bjVar.a(bArr2);
        Object a5 = bnVar.a(j);
        bVar.b(new byte[]{(byte) I}, 1);
        Object b = bVar.b();
        Object obj = new byte[(((((a.length + a2.length) + a3.length) + a4.length) + a5.length) + b.length)];
        System.arraycopy(a, 0, obj, 0, a.length);
        int length = a.length + 0;
        System.arraycopy(a2, 0, obj, length, a2.length);
        length += a2.length;
        System.arraycopy(a3, 0, obj, length, a3.length);
        length += a3.length;
        System.arraycopy(a4, 0, obj, length, a4.length);
        length += a4.length;
        System.arraycopy(a5, 0, obj, length, a5.length);
        length += a5.length;
        System.arraycopy(b, 0, obj, length, b.length);
        length += b.length;
        return b((byte[]) obj, this.u, 6);
    }

    public int d(byte[] bArr, int i, int i2) {
        g gVar = new g();
        bo boVar = new bo();
        ax axVar = new ax();
        async_context b = u.b(this.x.h);
        int c = c(bArr, i + 2);
        util.LOGD(getClass().getName(), "type=" + c);
        int i3 = i + 5;
        int c2;
        switch (c) {
            case 160:
                c2 = gVar.c(bArr, i3, this.c - i3);
                if (c2 < 0) {
                    return c2;
                }
                b._t104 = gVar;
                c2 = boVar.c(bArr, i3, this.c - i3);
                if (c2 < 0) {
                    return c2;
                }
                b._t17b = boVar;
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

    public int a(long j, int i, int i2, long[] jArr, WUserSigInfo wUserSigInfo) {
        g gVar;
        bj bjVar;
        int i3 = u.w;
        async_context b = u.b(this.x.h);
        g gVar2 = b._t104;
        if (gVar2 == null) {
            gVar = new g();
        } else {
            gVar = gVar2;
        }
        bj bjVar2 = b._t174;
        if (bjVar2 == null) {
            bjVar = new bj();
        } else {
            bjVar = bjVar2;
        }
        int i4 = 0;
        while (true) {
            a(this.i, this.t, this.j, this.x.f, this.m, this.n, i3, this.p, a(gVar.c(), j, bjVar.c(), i, i2, jArr));
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
