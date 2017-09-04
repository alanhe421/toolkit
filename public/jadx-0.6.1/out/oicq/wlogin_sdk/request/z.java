package oicq.wlogin_sdk.request;

import java.util.List;
import oicq.wlogin_sdk.b.ac;
import oicq.wlogin_sdk.b.ag;
import oicq.wlogin_sdk.b.al;
import oicq.wlogin_sdk.b.am;
import oicq.wlogin_sdk.b.ao;
import oicq.wlogin_sdk.b.ap;
import oicq.wlogin_sdk.b.ar;
import oicq.wlogin_sdk.b.aw;
import oicq.wlogin_sdk.b.b;
import oicq.wlogin_sdk.b.ba;
import oicq.wlogin_sdk.b.bd;
import oicq.wlogin_sdk.b.bf;
import oicq.wlogin_sdk.b.bh;
import oicq.wlogin_sdk.b.bk;
import oicq.wlogin_sdk.b.bs;
import oicq.wlogin_sdk.b.by;
import oicq.wlogin_sdk.b.bz;
import oicq.wlogin_sdk.b.c;
import oicq.wlogin_sdk.b.cd;
import oicq.wlogin_sdk.b.ci;
import oicq.wlogin_sdk.b.cj;
import oicq.wlogin_sdk.b.cl;
import oicq.wlogin_sdk.b.cq;
import oicq.wlogin_sdk.b.cr;
import oicq.wlogin_sdk.b.d;
import oicq.wlogin_sdk.b.i;
import oicq.wlogin_sdk.b.j;
import oicq.wlogin_sdk.b.k;
import oicq.wlogin_sdk.b.l;
import oicq.wlogin_sdk.b.r;
import oicq.wlogin_sdk.b.u;
import oicq.wlogin_sdk.request.oicq_request.EncryptionMethod;
import oicq.wlogin_sdk.sharemem.WloginSigInfo;
import oicq.wlogin_sdk.tools.util;

/* compiled from: request_tgtgt_nopicsig */
public class z extends oicq_request {
    public z(u uVar) {
        this.t = 2064;
        this.u = 15;
        this.v = "wtlogin.exchange_emp";
        this.x = uVar;
        this.x.m = 0;
        this.y = EncryptionMethod.EM_ST;
    }

    private byte[] a(long j, int i, long j2, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, int i3, int i4, long[] jArr, int i5, long j3, int i6, int i7, int i8, int i9, int i10, byte[] bArr4, long j4, byte[] bArr5, List<String> list) {
        Object obj;
        int i11;
        Object a;
        int i12;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        int i13;
        Object obj8;
        Object obj9;
        int i14;
        bs bsVar = new bs();
        c cVar = new c();
        i iVar = new i();
        u uVar = new u();
        d dVar = new d();
        j jVar = new j();
        k kVar = new k();
        l lVar = new l();
        ac acVar = new ac();
        ag agVar = new ag();
        am amVar = new am();
        r rVar = new r();
        ao aoVar = new ao();
        ap apVar = new ap();
        ar arVar = new ar();
        ba baVar = new ba();
        bd bdVar = new bd();
        al alVar = new al();
        cr crVar = new cr();
        aw awVar = new aw();
        cq cqVar = new cq();
        bf bfVar = new bf();
        bh bhVar = new bh();
        bk bkVar = new bk();
        cl clVar = new cl();
        by byVar = new by();
        bz bzVar = new bz();
        cd cdVar = new cd();
        ci ciVar = new ci();
        cj cjVar = new cj();
        b bVar = new b(1302);
        WloginSigInfo a2 = this.x.a(j2, j4);
        Object a3 = bsVar.a(j, i, j2, i2);
        Object a4 = cVar.a(j2, bArr);
        iVar.b(bArr2, bArr2.length);
        Object b = iVar.b();
        util.LOGD("req2 a1:", util.buf_to_string(b));
        Object a5 = dVar.a(j, j3, i, i5);
        Object a6 = jVar.a(i7, i8, i9, i10);
        Object a7 = uVar.a(i3, i4, jArr);
        Object a8 = apVar.a(u.A);
        Object a9 = awVar.a(this.x.i);
        Object b2 = alVar.b(u.C, u.D, u.F);
        Object a10 = crVar.a(0, u.u, 0);
        Object a11 = arVar.a(j4, u.G, u.H);
        Object a12 = bkVar.a(util.BUILD_TIME, util.SDK_VERSION);
        Object obj10 = new byte[0];
        byte[] bArr6 = new byte[0];
        bArr6 = new byte[0];
        bArr6 = new byte[0];
        bArr6 = new byte[0];
        Object obj11 = new byte[0];
        bArr6 = new byte[0];
        Object obj12 = new byte[0];
        Object obj13 = new byte[0];
        Object obj14 = new byte[0];
        byte[] bArr7 = new byte[0];
        Object obj15 = new byte[0];
        Object obj16 = new byte[0];
        Object obj17 = new byte[0];
        Object obj18 = new byte[0];
        Object obj19 = new byte[0];
        Object obj20 = new byte[0];
        Object obj21 = new byte[0];
        if (bArr4 == null || bArr4.length <= 0) {
            obj = obj10;
            i11 = 12;
        } else {
            obj = kVar.a(bArr4);
            i11 = 13;
        }
        if ((i3 & 128) != 0) {
            a = baVar.a(u.x);
            i12 = i11 + 1;
        } else {
            a = obj12;
            i12 = i11;
        }
        if (this.x.r == null || this.x.r.length <= 0) {
            obj2 = obj15;
        } else {
            i12++;
            obj2 = bhVar.a(this.x.r);
        }
        if (u.N == null || u.N.length <= 0) {
            obj3 = obj17;
        } else {
            i12++;
            obj3 = byVar.a(u.N);
        }
        if (u.O == null || u.O.length <= 0) {
            obj4 = obj18;
        } else {
            i12++;
            obj4 = bzVar.a(u.O);
        }
        if (u.L == null || u.L.length <= 0) {
            obj5 = obj19;
        } else {
            i12++;
            obj5 = cdVar.a(u.L);
        }
        if (l.J == null || l.J.length <= 0) {
            obj6 = obj20;
        } else {
            i12++;
            obj6 = ciVar.a(l.J, l.K, "qq".getBytes(), l.L);
        }
        if (u.R == null || u.R.length <= 0) {
            obj7 = obj21;
            i13 = i12;
        } else {
            obj7 = cjVar.a(u.R, u.S);
            i13 = i12 + 1;
        }
        Object a13 = aoVar.a(lVar.a(u.M), acVar.a(util.get_os_type(), util.get_os_version(), u.D, u.C, new byte[0], u.F), agVar.a(u.T, u.U, u.V, u.Y, u.I, u.A, u.P), bfVar.a(u.I), u.b(this.x.h)._tgtgt_key);
        i12 = i13 + 1;
        Object a14 = amVar.a(bArr5);
        i11 = i12 + 1;
        if (this.x.g == null || util.check_uin_account(this.x.g).booleanValue()) {
            obj8 = obj11;
        } else {
            i11++;
            obj8 = rVar.a(this.x.g.getBytes());
        }
        if (bArr3 == null || bArr3.length <= 0) {
            obj9 = obj13;
            i14 = i11;
        } else {
            i14 = i11 + 1;
            obj9 = bdVar.a(bArr3);
        }
        if (list == null || list.size() <= 0) {
            obj11 = obj14;
        } else {
            i14++;
            obj11 = cqVar.a(list);
        }
        if (a2 == null || a2._G == null || a2._G.length <= 0 || a2._dpwd == null || a2._dpwd.length <= 0 || a2._randseed == null || a2._randseed.length <= 0) {
            util.LOGI("request_tgtgt_nopicsig req without DA1", "" + j2);
            obj12 = obj16;
            i11 = i14;
        } else {
            obj12 = clVar.a(a2._G, j2, u.A, a2._dpwd, j4, 1, a2._randseed);
            i11 = i14 + 1;
        }
        bArr7 = new byte[4];
        util.int32_to_buf(bArr7, 0, u.af);
        bVar.b(bArr7, 4);
        obj15 = bVar.b();
        i11++;
        obj17 = new byte[((((((((((((((((((((((((((a3.length + a4.length) + b.length) + a7.length) + a5.length) + a6.length) + obj.length) + a13.length) + a14.length) + obj8.length) + a8.length) + a.length) + obj9.length) + a9.length) + b2.length) + a10.length) + obj11.length) + a11.length) + obj2.length) + a12.length) + obj12.length) + obj3.length) + obj4.length) + obj5.length) + obj6.length) + obj7.length) + obj15.length)];
        System.arraycopy(a3, 0, obj17, 0, a3.length);
        int length = 0 + a3.length;
        System.arraycopy(a4, 0, obj17, length, a4.length);
        length += a4.length;
        System.arraycopy(b, 0, obj17, length, b.length);
        length += b.length;
        System.arraycopy(a7, 0, obj17, length, a7.length);
        length += a7.length;
        System.arraycopy(a5, 0, obj17, length, a5.length);
        length += a5.length;
        System.arraycopy(a6, 0, obj17, length, a6.length);
        length += a6.length;
        System.arraycopy(obj, 0, obj17, length, obj.length);
        length += obj.length;
        System.arraycopy(a13, 0, obj17, length, a13.length);
        length += a13.length;
        System.arraycopy(a14, 0, obj17, length, a14.length);
        length += a14.length;
        System.arraycopy(obj8, 0, obj17, length, obj8.length);
        length += obj8.length;
        System.arraycopy(a8, 0, obj17, length, a8.length);
        length += a8.length;
        System.arraycopy(a, 0, obj17, length, a.length);
        length += a.length;
        System.arraycopy(obj9, 0, obj17, length, obj9.length);
        length += obj9.length;
        System.arraycopy(a9, 0, obj17, length, a9.length);
        length += a9.length;
        System.arraycopy(b2, 0, obj17, length, b2.length);
        length += b2.length;
        System.arraycopy(a10, 0, obj17, length, a10.length);
        length += a10.length;
        System.arraycopy(obj11, 0, obj17, length, obj11.length);
        length += obj11.length;
        System.arraycopy(a11, 0, obj17, length, a11.length);
        length += a11.length;
        System.arraycopy(obj2, 0, obj17, length, obj2.length);
        length += obj2.length;
        System.arraycopy(a12, 0, obj17, length, a12.length);
        length += a12.length;
        System.arraycopy(obj12, 0, obj17, length, obj12.length);
        i12 = obj12.length + length;
        System.arraycopy(obj3, 0, obj17, i12, obj3.length);
        i12 += obj3.length;
        System.arraycopy(obj4, 0, obj17, i12, obj4.length);
        i12 += obj4.length;
        System.arraycopy(obj5, 0, obj17, i12, obj5.length);
        i12 += obj5.length;
        System.arraycopy(obj6, 0, obj17, i12, obj6.length);
        i12 += obj6.length;
        System.arraycopy(obj7, 0, obj17, i12, obj7.length);
        i12 += obj7.length;
        System.arraycopy(obj15, 0, obj17, i12, obj15.length);
        i12 += obj15.length;
        return a(a((byte[]) obj17, this.u, i11), this.y, this.A, this.B);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(long r33, int r35, long r36, int r38, byte[] r39, byte[] r40, byte[] r41, int r42, int r43, long[] r44, int r45, long r46, int r48, int r49, int r50, int r51, int r52, byte[] r53, long r54, oicq.wlogin_sdk.request.WUserSigInfo r56) {
        /*
        r32 = this;
        r4 = "start request_tgtgt_nopicsig";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "";
        r5 = r5.append(r6);
        r0 = r36;
        r5 = r5.append(r0);
        r5 = r5.toString();
        oicq.wlogin_sdk.tools.util.LOGI(r4, r5);
        r7 = oicq.wlogin_sdk.request.u.w;
        r0 = r32;
        r1 = r40;
        r12 = r0.c(r1);
        if (r12 != 0) goto L_0x002b;
    L_0x0028:
        r4 = -1014; // 0xfffffffffffffc0a float:NaN double:NaN;
    L_0x002a:
        return r4;
    L_0x002b:
        r4 = 0;
        r30 = r4;
    L_0x002e:
        r0 = r32;
        r4 = r0.y;
        r5 = oicq.wlogin_sdk.request.oicq_request.EncryptionMethod.EM_ST;
        if (r4 != r5) goto L_0x007a;
    L_0x0036:
        r0 = r32;
        r4 = r0.B;
        if (r4 == 0) goto L_0x0050;
    L_0x003c:
        r0 = r32;
        r4 = r0.B;
        r4 = r4.length;
        if (r4 == 0) goto L_0x0050;
    L_0x0043:
        r0 = r32;
        r4 = r0.A;
        if (r4 == 0) goto L_0x0050;
    L_0x0049:
        r0 = r32;
        r4 = r0.A;
        r4 = r4.length;
        if (r4 != 0) goto L_0x007a;
    L_0x0050:
        r4 = oicq.wlogin_sdk.request.oicq_request.EncryptionMethod.EM_ECDH;
        r0 = r32;
        r0.y = r4;
        r4 = oicq.wlogin_sdk.request.u.al;
        r5 = 2553232; // 0x26f590 float:3.57784E-39 double:1.261464E-317;
        r4.attr_api(r5);
        r4 = "using wt st encrypt body but no st key";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "";
        r5 = r5.append(r6);
        r0 = r36;
        r5 = r5.append(r0);
        r5 = r5.toString();
        oicq.wlogin_sdk.tools.util.LOGI(r4, r5);
    L_0x007a:
        r28 = oicq.wlogin_sdk.request.u.E;
        r0 = r56;
        r0 = r0._domains;
        r29 = r0;
        r4 = r32;
        r5 = r33;
        r8 = r36;
        r10 = r38;
        r11 = r39;
        r13 = r41;
        r14 = r42;
        r15 = r43;
        r16 = r44;
        r17 = r45;
        r18 = r46;
        r20 = r7;
        r21 = r49;
        r22 = r50;
        r23 = r51;
        r24 = r52;
        r25 = r53;
        r26 = r54;
        r4 = r4.a(r5, r7, r8, r10, r11, r12, r13, r14, r15, r16, r17, r18, r20, r21, r22, r23, r24, r25, r26, r28, r29);
        r0 = r32;
        r5 = r0.y;
        r0 = r32;
        r1 = r36;
        r0.a(r1, r4, r5);
        r0 = r32;
        r4 = r0.x;
        r4 = r4.f;
        r4 = java.lang.String.valueOf(r4);
        r5 = 0;
        r0 = r32;
        r1 = r56;
        r4 = r0.a(r4, r5, r1);
        if (r4 == 0) goto L_0x00f9;
    L_0x00ca:
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "end request_tgtgt_nopicsig ret ";
        r5 = r5.append(r6);
        r5 = r5.append(r4);
        r5 = r5.toString();
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "";
        r6 = r6.append(r7);
        r0 = r36;
        r6 = r6.append(r0);
        r6 = r6.toString();
        oicq.wlogin_sdk.tools.util.LOGI(r5, r6);
        goto L_0x002a;
    L_0x00f9:
        r5 = r32.b();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r6 = "retry num:";
        r4 = r4.append(r6);
        r0 = r30;
        r4 = r4.append(r0);
        r6 = " ret:";
        r4 = r4.append(r6);
        r4 = r4.append(r5);
        r4 = r4.toString();
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r8 = "";
        r6 = r6.append(r8);
        r0 = r36;
        r6 = r6.append(r0);
        r6 = r6.toString();
        oicq.wlogin_sdk.tools.util.LOGI(r4, r6);
        r4 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
        if (r5 == r4) goto L_0x013d;
    L_0x013b:
        r4 = r5;
        goto L_0x00ca;
    L_0x013d:
        r4 = r30 + 1;
        r6 = 1;
        r0 = r30;
        if (r0 < r6) goto L_0x0146;
    L_0x0144:
        r4 = r5;
        goto L_0x00ca;
    L_0x0146:
        r30 = r4;
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: oicq.wlogin_sdk.request.z.a(long, int, long, int, byte[], byte[], byte[], int, int, long[], int, long, int, int, int, int, int, byte[], long, oicq.wlogin_sdk.request.WUserSigInfo):int");
    }
}
