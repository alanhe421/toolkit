package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.b.ac;
import oicq.wlogin_sdk.b.ag;
import oicq.wlogin_sdk.b.al;
import oicq.wlogin_sdk.b.am;
import oicq.wlogin_sdk.b.ao;
import oicq.wlogin_sdk.b.ap;
import oicq.wlogin_sdk.b.ar;
import oicq.wlogin_sdk.b.as;
import oicq.wlogin_sdk.b.av;
import oicq.wlogin_sdk.b.bd;
import oicq.wlogin_sdk.b.bf;
import oicq.wlogin_sdk.b.bh;
import oicq.wlogin_sdk.b.bk;
import oicq.wlogin_sdk.b.bs;
import oicq.wlogin_sdk.b.by;
import oicq.wlogin_sdk.b.bz;
import oicq.wlogin_sdk.b.cd;
import oicq.wlogin_sdk.b.cj;
import oicq.wlogin_sdk.b.cr;
import oicq.wlogin_sdk.b.d;
import oicq.wlogin_sdk.b.i;
import oicq.wlogin_sdk.b.k;
import oicq.wlogin_sdk.b.l;
import oicq.wlogin_sdk.b.r;
import oicq.wlogin_sdk.b.u;
import oicq.wlogin_sdk.tools.util;

/* compiled from: request_change_a1 */
public class m extends oicq_request {
    public m(u uVar) {
        this.t = 2064;
        this.u = 13;
        this.v = "wtlogin.login";
        this.x = uVar;
        this.x.m = 0;
    }

    public byte[] a(long j, long j2, long j3, int i, int i2, byte[] bArr, byte[] bArr2, int i3, int i4, long[] jArr, byte[] bArr3, byte[] bArr4, long j4, long j5, long j6, byte[] bArr5, byte[] bArr6) {
        Object obj;
        int i5;
        Object obj2;
        int i6;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        int i7;
        i iVar = new i();
        ao aoVar = new ao();
        d dVar = new d();
        k kVar = new k();
        u uVar = new u();
        r rVar = new r();
        am amVar = new am();
        ap apVar = new ap();
        bd bdVar = new bd();
        bs bsVar = new bs();
        al alVar = new al();
        cr crVar = new cr();
        l lVar = new l();
        ac acVar = new ac();
        ag agVar = new ag();
        ar arVar = new ar();
        as asVar = new as();
        av avVar = new av();
        bf bfVar = new bf();
        bh bhVar = new bh();
        bk bkVar = new bk();
        by byVar = new by();
        bz bzVar = new bz();
        cd cdVar = new cd();
        cj cjVar = new cj();
        iVar.b(bArr, bArr.length);
        Object b = iVar.b();
        Object a = dVar.a(j5, j6, i, i2);
        Object a2 = uVar.a(i3, i4, jArr);
        Object a3 = amVar.a(u.E);
        Object a4 = apVar.a(u.A);
        Object a5 = bsVar.a(j2, i, j, 0);
        Object b2 = alVar.b(u.C, u.D, u.F);
        Object a6 = crVar.a(0, u.u, 0);
        Object a7 = arVar.a(j2, u.G, u.H);
        Object a8 = bkVar.a(util.BUILD_TIME, util.SDK_VERSION);
        Object obj9 = new byte[0];
        Object obj10 = new byte[0];
        byte[] bArr7 = new byte[0];
        bArr7 = new byte[0];
        bArr7 = new byte[0];
        bArr7 = new byte[0];
        byte[] bArr8 = new byte[0];
        bArr7 = new byte[0];
        bArr7 = new byte[0];
        Object obj11 = new byte[0];
        byte[] bArr9 = new byte[0];
        Object obj12 = new byte[0];
        Object obj13 = new byte[0];
        Object obj14 = new byte[0];
        Object obj15 = new byte[0];
        Object obj16 = new byte[0];
        if (bArr3 == null || bArr3.length <= 0) {
            obj = obj9;
            i5 = 10;
        } else {
            obj = kVar.a(bArr3);
            i5 = 11;
        }
        if (this.x.g == null || util.check_uin_account(this.x.g).booleanValue()) {
            obj2 = obj10;
            i6 = i5;
        } else {
            obj2 = rVar.a(this.x.g.getBytes());
            i6 = i5 + 1;
        }
        if (bArr2 == null || bArr2.length <= 0) {
            obj3 = obj11;
            i5 = i6;
        } else {
            obj3 = bdVar.a(bArr2);
            i5 = i6 + 1;
        }
        if (this.x.r == null || this.x.r.length <= 0) {
            obj4 = obj12;
        } else {
            i5++;
            obj4 = bhVar.a(this.x.r);
        }
        if (u.N == null || u.N.length <= 0) {
            obj5 = obj13;
        } else {
            i5++;
            obj5 = byVar.a(u.N);
        }
        if (u.O == null || u.O.length <= 0) {
            obj6 = obj14;
        } else {
            i5++;
            obj6 = bzVar.a(u.O);
        }
        if (u.L == null || u.L.length <= 0) {
            obj7 = obj15;
        } else {
            i5++;
            obj7 = cdVar.a(u.L);
        }
        if (u.R == null || u.R.length <= 0) {
            obj8 = obj16;
            i7 = i5;
        } else {
            obj8 = cjVar.a(u.R, u.S);
            i7 = i5 + 1;
        }
        obj9 = aoVar.a(lVar.a(u.M), acVar.a(util.get_os_type(), util.get_os_version(), u.D, u.C, new byte[0], u.F), agVar.a(u.T, u.U, u.V, 0, u.I, u.A, u.P), asVar.a(bArr4, j4, j5, j6, bArr5, bArr6), bArr8, avVar.a(u.Z), bfVar.a(u.I), u.b(this.x.h)._tgtgt_key);
        i6 = i7 + 1;
        obj11 = new byte[((((((((((((((((((b.length + a.length) + obj.length) + a2.length) + obj2.length) + a3.length) + a4.length) + obj9.length) + obj3.length) + a5.length) + b2.length) + a6.length) + a7.length) + obj4.length) + a8.length) + obj5.length) + obj6.length) + obj7.length) + obj8.length)];
        System.arraycopy(b, 0, obj11, 0, b.length);
        int length = 0 + b.length;
        System.arraycopy(a, 0, obj11, length, a.length);
        length += a.length;
        System.arraycopy(obj, 0, obj11, length, obj.length);
        length += obj.length;
        System.arraycopy(a2, 0, obj11, length, a2.length);
        length += a2.length;
        System.arraycopy(obj2, 0, obj11, length, obj2.length);
        length += obj2.length;
        System.arraycopy(a3, 0, obj11, length, a3.length);
        length += a3.length;
        System.arraycopy(a4, 0, obj11, length, a4.length);
        length += a4.length;
        System.arraycopy(obj9, 0, obj11, length, obj9.length);
        i5 = obj9.length + length;
        System.arraycopy(obj3, 0, obj11, i5, obj3.length);
        i5 += obj3.length;
        System.arraycopy(a5, 0, obj11, i5, a5.length);
        i5 += a5.length;
        System.arraycopy(b2, 0, obj11, i5, b2.length);
        i5 += b2.length;
        System.arraycopy(a6, 0, obj11, i5, a6.length);
        i5 += a6.length;
        System.arraycopy(a7, 0, obj11, i5, a7.length);
        i5 += a7.length;
        System.arraycopy(obj4, 0, obj11, i5, obj4.length);
        i5 += obj4.length;
        System.arraycopy(a8, 0, obj11, i5, a8.length);
        i5 += a8.length;
        System.arraycopy(obj5, 0, obj11, i5, obj5.length);
        i5 += obj5.length;
        System.arraycopy(obj6, 0, obj11, i5, obj6.length);
        i5 += obj6.length;
        System.arraycopy(obj7, 0, obj11, i5, obj7.length);
        i5 += obj7.length;
        System.arraycopy(obj8, 0, obj11, i5, obj8.length);
        i5 += obj8.length;
        return b((byte[]) obj11, this.u, i6);
    }

    public int a(long j, long j2, long j3, int i, int i2, byte[] bArr, byte[] bArr2, int i3, int i4, long[] jArr, byte[] bArr3, long j4, long j5, long j6, byte[] bArr4, byte[] bArr5, WUserSigInfo wUserSigInfo) {
        int i5 = u.w;
        byte[] c = c(bArr);
        if (c == null) {
            return -1014;
        }
        int i6 = 0;
        while (true) {
            long j7 = j;
            int i7 = i5;
            a(this.i, this.t, this.j, j7, this.m, this.n, i7, this.p, a(j, j2, j3, i5, i2, c, bArr2, i3, i4, jArr, u.aa, bArr3, j4, j5, j6, bArr4, bArr5));
            int a = a(String.valueOf(this.x.f), false, wUserSigInfo);
            if (a != 0) {
                return a;
            }
            int b = b();
            util.LOGI("retry num:" + i6 + " ret:" + b, "" + j);
            if (b != 180) {
                return b;
            }
            a = i6 + 1;
            if (i6 >= 1) {
                return b;
            }
            i6 = a;
        }
    }
}
