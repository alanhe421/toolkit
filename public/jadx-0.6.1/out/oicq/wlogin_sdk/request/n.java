package oicq.wlogin_sdk.request;

import java.util.List;
import oicq.wlogin_sdk.b.ac;
import oicq.wlogin_sdk.b.ag;
import oicq.wlogin_sdk.b.al;
import oicq.wlogin_sdk.b.am;
import oicq.wlogin_sdk.b.an;
import oicq.wlogin_sdk.b.ao;
import oicq.wlogin_sdk.b.ap;
import oicq.wlogin_sdk.b.ar;
import oicq.wlogin_sdk.b.aw;
import oicq.wlogin_sdk.b.bf;
import oicq.wlogin_sdk.b.bh;
import oicq.wlogin_sdk.b.bk;
import oicq.wlogin_sdk.b.bs;
import oicq.wlogin_sdk.b.by;
import oicq.wlogin_sdk.b.bz;
import oicq.wlogin_sdk.b.cd;
import oicq.wlogin_sdk.b.ci;
import oicq.wlogin_sdk.b.cj;
import oicq.wlogin_sdk.b.cq;
import oicq.wlogin_sdk.b.cr;
import oicq.wlogin_sdk.b.d;
import oicq.wlogin_sdk.b.k;
import oicq.wlogin_sdk.b.l;
import oicq.wlogin_sdk.b.m;
import oicq.wlogin_sdk.b.r;
import oicq.wlogin_sdk.b.u;
import oicq.wlogin_sdk.tools.util;

/* compiled from: request_change_sig */
public class n extends oicq_request {
    public n(u uVar) {
        this.t = 2064;
        this.u = 10;
        this.v = "wtlogin.exchange_emp";
        this.x = uVar;
        this.x.m = 0;
    }

    public byte[] a(long j, long j2, long j3, int i, int i2, byte[] bArr, int i3, int i4, long[] jArr, byte[] bArr2, byte[] bArr3, List<String> list) {
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
        int i8;
        int i9 = this.u;
        d dVar = new d();
        m mVar = new m();
        u uVar = new u();
        k kVar = new k();
        l lVar = new l();
        ac acVar = new ac();
        ag agVar = new ag();
        an anVar = new an();
        r rVar = new r();
        ao aoVar = new ao();
        ap apVar = new ap();
        ar arVar = new ar();
        am amVar = new am();
        aw awVar = new aw();
        bs bsVar = new bs();
        al alVar = new al();
        cr crVar = new cr();
        cq cqVar = new cq();
        bf bfVar = new bf();
        bh bhVar = new bh();
        bk bkVar = new bk();
        by byVar = new by();
        bz bzVar = new bz();
        cd cdVar = new cd();
        ci ciVar = new ci();
        cj cjVar = new cj();
        Object a = dVar.a(j2, j3, i, i2);
        Object a2 = mVar.a(bArr);
        Object a3 = uVar.a(i3, i4, jArr);
        Object a4 = apVar.a(u.A);
        Object a5 = amVar.a(u.E);
        Object a6 = awVar.a(this.x.i);
        Object a7 = bsVar.a(j2, i, j, 0);
        Object b = alVar.b(u.C, u.D, u.F);
        Object a8 = crVar.a(0, u.u, 0);
        Object a9 = arVar.a(j2, u.G, u.H);
        Object a10 = bkVar.a(util.BUILD_TIME, util.SDK_VERSION);
        Object obj9 = new byte[0];
        byte[] bArr4 = new byte[0];
        bArr4 = new byte[0];
        bArr4 = new byte[0];
        Object obj10 = new byte[0];
        Object obj11 = new byte[0];
        bArr4 = new byte[0];
        Object obj12 = new byte[0];
        byte[] bArr5 = new byte[0];
        Object obj13 = new byte[0];
        Object obj14 = new byte[0];
        Object obj15 = new byte[0];
        Object obj16 = new byte[0];
        Object obj17 = new byte[0];
        Object obj18 = new byte[0];
        if (bArr2 == null || bArr2.length <= 0) {
            obj = obj9;
            i5 = 10;
        } else {
            obj = kVar.a(bArr2);
            i5 = 11;
        }
        if (list == null || list.size() <= 0) {
            obj2 = obj12;
            i6 = i5;
        } else {
            obj2 = cqVar.a(list);
            i6 = i5 + 1;
        }
        if (this.x.r == null || this.x.r.length <= 0) {
            obj3 = obj13;
        } else {
            i6++;
            obj3 = bhVar.a(this.x.r);
        }
        if (u.N == null || u.N.length <= 0) {
            obj4 = obj14;
        } else {
            i6++;
            obj4 = byVar.a(u.N);
        }
        if (u.O == null || u.O.length <= 0) {
            obj5 = obj15;
        } else {
            i6++;
            obj5 = bzVar.a(u.O);
        }
        if (u.L == null || u.L.length <= 0) {
            obj6 = obj16;
        } else {
            i6++;
            obj6 = cdVar.a(u.L);
        }
        if (l.J == null || l.J.length <= 0) {
            obj7 = obj17;
        } else {
            i6++;
            obj7 = ciVar.a(l.J, l.K, "qq".getBytes(), l.L);
        }
        if (u.R == null || u.R.length <= 0) {
            obj8 = obj18;
            i7 = i6;
        } else {
            obj8 = cjVar.a(u.R, u.S);
            i7 = i6 + 1;
        }
        obj14 = aoVar.a(lVar.a(u.M), acVar.a(util.get_os_type(), util.get_os_version(), u.D, u.C, new byte[0], u.F), agVar.a(u.T, u.U, u.V, u.Y, u.I, u.A, u.P), bfVar.a(u.I), this.x.b);
        int length;
        int length2;
        if (bArr3 == null || bArr3.length <= 0) {
            i5 = i7 + 2;
            if (this.x.g == null || util.check_uin_account(this.x.g).booleanValue()) {
                obj12 = obj11;
            } else {
                obj12 = rVar.a(this.x.g.getBytes());
                i5++;
            }
            obj13 = new byte[(((((((((((((((((((((a.length + a2.length) + a3.length) + obj.length) + obj14.length) + obj10.length) + obj12.length) + a4.length) + a5.length) + a6.length) + a7.length) + b.length) + a8.length) + obj2.length) + a9.length) + obj3.length) + a10.length) + obj4.length) + obj5.length) + obj6.length) + obj7.length) + obj8.length)];
            System.arraycopy(a, 0, obj13, 0, a.length);
            length = 0 + a.length;
            System.arraycopy(a2, 0, obj13, length, a2.length);
            length += a2.length;
            System.arraycopy(a3, 0, obj13, length, a3.length);
            length += a3.length;
            System.arraycopy(obj, 0, obj13, length, obj.length);
            length += obj.length;
            System.arraycopy(obj14, 0, obj13, length, obj14.length);
            length2 = obj14.length + length;
            System.arraycopy(obj10, 0, obj13, length2, obj10.length);
            length2 += obj10.length;
            System.arraycopy(obj12, 0, obj13, length2, obj12.length);
            i6 = obj12.length + length2;
            System.arraycopy(a4, 0, obj13, i6, a4.length);
            i6 += a4.length;
            System.arraycopy(a5, 0, obj13, i6, a5.length);
            i6 += a5.length;
            System.arraycopy(a6, 0, obj13, i6, a6.length);
            i6 += a6.length;
            System.arraycopy(a7, 0, obj13, i6, a7.length);
            i6 += a7.length;
            System.arraycopy(b, 0, obj13, i6, b.length);
            i6 += b.length;
            System.arraycopy(a8, 0, obj13, i6, a8.length);
            i6 += a8.length;
            System.arraycopy(obj2, 0, obj13, i6, obj2.length);
            i6 += obj2.length;
            System.arraycopy(a9, 0, obj13, i6, a9.length);
            i6 += a9.length;
            System.arraycopy(obj3, 0, obj13, i6, obj3.length);
            i6 += obj3.length;
            System.arraycopy(a10, 0, obj13, i6, a10.length);
            i6 += a10.length;
            System.arraycopy(obj4, 0, obj13, i6, obj4.length);
            i6 += obj4.length;
            System.arraycopy(obj5, 0, obj13, i6, obj5.length);
            i6 += obj5.length;
            System.arraycopy(obj6, 0, obj13, i6, obj6.length);
            i6 += obj6.length;
            System.arraycopy(obj7, 0, obj13, i6, obj7.length);
            i6 += obj7.length;
            System.arraycopy(obj8, 0, obj13, i6, obj8.length);
            i6 += obj8.length;
            obj12 = obj13;
            i8 = i5;
            i5 = i9;
        } else {
            i6 = i7 + 1;
            obj16 = anVar.a(bArr3);
            i8 = i6 + 1;
            i5 = 11;
            bArr4 = new byte[(((((((((((((((((((a.length + a2.length) + a3.length) + obj.length) + obj14.length) + obj16.length) + a5.length) + a6.length) + a7.length) + b.length) + a8.length) + obj2.length) + a9.length) + obj3.length) + a10.length) + obj4.length) + obj5.length) + obj6.length) + obj7.length) + obj8.length)];
            System.arraycopy(a, 0, bArr4, 0, a.length);
            length = 0 + a.length;
            System.arraycopy(a2, 0, bArr4, length, a2.length);
            length += a2.length;
            System.arraycopy(a3, 0, bArr4, length, a3.length);
            length += a3.length;
            System.arraycopy(obj, 0, bArr4, length, obj.length);
            length += obj.length;
            System.arraycopy(obj14, 0, bArr4, length, obj14.length);
            length2 = obj14.length + length;
            System.arraycopy(obj16, 0, bArr4, length2, obj16.length);
            length2 += obj16.length;
            System.arraycopy(a5, 0, bArr4, length2, a5.length);
            length2 += a5.length;
            System.arraycopy(a6, 0, bArr4, length2, a6.length);
            length2 += a6.length;
            System.arraycopy(a7, 0, bArr4, length2, a7.length);
            length2 += a7.length;
            System.arraycopy(b, 0, bArr4, length2, b.length);
            length2 += b.length;
            System.arraycopy(a8, 0, bArr4, length2, a8.length);
            length2 += a8.length;
            System.arraycopy(obj2, 0, bArr4, length2, obj2.length);
            length2 += obj2.length;
            System.arraycopy(a9, 0, bArr4, length2, a9.length);
            length2 += a9.length;
            System.arraycopy(obj3, 0, bArr4, length2, obj3.length);
            length2 += obj3.length;
            System.arraycopy(a10, 0, bArr4, length2, a10.length);
            length2 += a10.length;
            System.arraycopy(obj4, 0, bArr4, length2, obj4.length);
            length2 += obj4.length;
            System.arraycopy(obj5, 0, bArr4, length2, obj5.length);
            length2 += obj5.length;
            System.arraycopy(obj6, 0, bArr4, length2, obj6.length);
            length2 += obj6.length;
            System.arraycopy(obj7, 0, bArr4, length2, obj7.length);
            length2 += obj7.length;
            System.arraycopy(obj8, 0, bArr4, length2, obj8.length);
            length2 += obj8.length;
        }
        return b(bArr4, i5, i8);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(long r28, long r30, long r32, int r34, int r35, byte[] r36, int r37, int r38, long[] r39, byte[] r40, oicq.wlogin_sdk.request.WUserSigInfo r41) {
        /*
        r27 = this;
        r2 = "start request_change_sig";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "";
        r3 = r3.append(r4);
        r0 = r28;
        r3 = r3.append(r0);
        r3 = r3.toString();
        oicq.wlogin_sdk.tools.util.LOGI(r2, r3);
        r10 = oicq.wlogin_sdk.request.u.w;
        r2 = -33554433; // 0xfffffffffdffffff float:-4.2535293E37 double:NaN;
        r11 = r35 & r2;
        r0 = r27;
        r2 = r0.x;
        r2 = r2.h;
        r2 = oicq.wlogin_sdk.request.u.b(r2);
        r2._main_sigmap = r11;
        r2 = 0;
    L_0x0030:
        r0 = r27;
        r0 = r0.i;
        r20 = r0;
        r0 = r27;
        r0 = r0.t;
        r23 = r0;
        r0 = r27;
        r0 = r0.j;
        r24 = r0;
        r0 = r27;
        r0 = r0.m;
        r25 = r0;
        r0 = r27;
        r0 = r0.n;
        r19 = r0;
        r0 = r27;
        r0 = r0.p;
        r21 = r0;
        r16 = oicq.wlogin_sdk.request.u.aa;
        r0 = r41;
        r0 = r0._domains;
        r18 = r0;
        r3 = r27;
        r4 = r28;
        r6 = r30;
        r8 = r32;
        r12 = r36;
        r13 = r37;
        r14 = r38;
        r15 = r39;
        r17 = r40;
        r22 = r3.a(r4, r6, r8, r10, r11, r12, r13, r14, r15, r16, r17, r18);
        r12 = r27;
        r13 = r20;
        r14 = r23;
        r15 = r24;
        r16 = r28;
        r18 = r25;
        r20 = r10;
        r12.a(r13, r14, r15, r16, r18, r19, r20, r21, r22);
        r0 = r27;
        r3 = r0.x;
        r4 = r3.f;
        r3 = java.lang.String.valueOf(r4);
        r4 = 0;
        r0 = r27;
        r1 = r41;
        r3 = r0.a(r3, r4, r1);
        if (r3 == 0) goto L_0x00c7;
    L_0x0098:
        r2 = r3;
    L_0x0099:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "end request_change_sig for user ret ";
        r3 = r3.append(r4);
        r3 = r3.append(r2);
        r3 = r3.toString();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "";
        r4 = r4.append(r5);
        r0 = r28;
        r4 = r4.append(r0);
        r4 = r4.toString();
        oicq.wlogin_sdk.tools.util.LOGI(r3, r4);
        return r2;
    L_0x00c7:
        r4 = r27.b();
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r5 = "retry num:";
        r3 = r3.append(r5);
        r3 = r3.append(r2);
        r5 = " ret:";
        r3 = r3.append(r5);
        r3 = r3.append(r4);
        r3 = r3.toString();
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "";
        r5 = r5.append(r6);
        r0 = r28;
        r5 = r5.append(r0);
        r5 = r5.toString();
        oicq.wlogin_sdk.tools.util.LOGI(r3, r5);
        r3 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
        if (r4 == r3) goto L_0x0109;
    L_0x0107:
        r2 = r4;
        goto L_0x0099;
    L_0x0109:
        r3 = r2 + 1;
        r5 = 1;
        if (r2 < r5) goto L_0x0110;
    L_0x010e:
        r2 = r4;
        goto L_0x0099;
    L_0x0110:
        r2 = r3;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: oicq.wlogin_sdk.request.n.a(long, long, long, int, int, byte[], int, int, long[], byte[], oicq.wlogin_sdk.request.WUserSigInfo):int");
    }
}
