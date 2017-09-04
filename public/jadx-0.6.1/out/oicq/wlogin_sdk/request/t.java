package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.b.ac;
import oicq.wlogin_sdk.b.ag;
import oicq.wlogin_sdk.b.aw;
import oicq.wlogin_sdk.b.b;
import oicq.wlogin_sdk.b.ca;
import oicq.wlogin_sdk.b.cr;
import oicq.wlogin_sdk.b.d;
import oicq.wlogin_sdk.b.j;
import oicq.wlogin_sdk.b.k;
import oicq.wlogin_sdk.b.r;
import oicq.wlogin_sdk.b.u;
import oicq.wlogin_sdk.tools.util;

/* compiled from: request_getuin */
public class t extends oicq_request {
    public t(u uVar) {
        this.t = 2064;
        this.u = 4;
        this.v = "wtlogin.name2uin";
        this.x = uVar;
        this.x.m = 0;
    }

    public byte[] a(long j, long j2, int i, int i2, byte[] bArr, int i3, int i4, int i5, int i6, int i7, int i8, long[] jArr, byte[] bArr2, byte[] bArr3) {
        d dVar = new d();
        r rVar = new r();
        j jVar = new j();
        k kVar = new k();
        ac acVar = new ac();
        ag agVar = new ag();
        u uVar = new u();
        aw awVar = new aw();
        cr crVar = new cr();
        b bVar = new b(283);
        ca caVar = new ca();
        byte[] bArr4 = new byte[0];
        bArr4 = new byte[0];
        Object a = dVar.a(j, j2, i, i2);
        Object a2 = rVar.a(bArr);
        Object a3 = jVar.a(i3, i4, i5, i6);
        Object a4 = kVar.a(bArr2);
        Object a5 = uVar.a(i7, i8, jArr);
        Object a6 = awVar.a(this.x.i);
        Object a7 = crVar.a(0, u.u, 0);
        Object a8 = caVar.a(l.I);
        bVar.b(new byte[]{(byte) 2}, 1);
        Object a9 = acVar.a(util.get_os_type(), util.get_os_version(), u.D, u.C, new byte[0], u.F);
        Object a10 = agVar.a(u.T, u.U, u.V, u.Y, u.I, u.A, u.P);
        Object obj = new byte[((((((((((a.length + a2.length) + a3.length) + a4.length) + a9.length) + a10.length) + a5.length) + a6.length) + a7.length) + a8.length) + bVar.b().length)];
        System.arraycopy(a, 0, obj, 0, a.length);
        int length = 0 + a.length;
        System.arraycopy(a2, 0, obj, length, a2.length);
        length += a2.length;
        System.arraycopy(a3, 0, obj, length, a3.length);
        length += a3.length;
        System.arraycopy(a6, 0, obj, length, a6.length);
        length += a6.length;
        System.arraycopy(a7, 0, obj, length, a7.length);
        length += a7.length;
        int i9 = 5;
        if (bArr2 != null && bArr2.length > 0) {
            System.arraycopy(a4, 0, obj, length, a4.length);
            length += a4.length;
            i9 = 6;
        }
        System.arraycopy(a9, 0, obj, length, a9.length);
        length += a9.length;
        System.arraycopy(a10, 0, obj, length, a10.length);
        length += a10.length;
        System.arraycopy(a5, 0, obj, length, a5.length);
        length += a5.length;
        i9 += 3;
        System.arraycopy(a8, 0, obj, length, a8.length);
        length += a8.length;
        i9++;
        System.arraycopy(bVar.b(), 0, obj, length, bVar.b().length);
        length += bVar.b().length;
        length = i9 + 1;
        return b((byte[]) obj, this.u, length);
    }

    public int a(long j, long j2, int i, int i2, byte[] bArr, int i3, int i4, int i5, int i6, int i7, int i8, long[] jArr, WUserSigInfo wUserSigInfo) {
        int i9 = u.w;
        this.x.g = new String(bArr);
        int i10 = 0;
        while (true) {
            int i11 = i9;
            a(this.i, this.t, this.j, 0, this.m, this.n, i11, this.p, a(j, j2, i9, i2, bArr, i3, i4, i5, i6, i7, i8, jArr, u.aa, u.A));
            String valueOf = String.valueOf(this.x.f);
            int a = a(valueOf, false, wUserSigInfo);
            if (a != 0) {
                return a;
            }
            int b = b();
            util.LOGI("retry num:" + i10 + " ret:" + b + " uin: " + valueOf, this.x.g);
            if (b != 180) {
                return b;
            }
            a = i10 + 1;
            if (i10 >= 1) {
                return b;
            }
            i10 = a;
        }
    }
}
