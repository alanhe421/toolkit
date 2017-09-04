package oicq.wlogin_sdk.request;

import android.content.Context;
import com.tencent.openqq.protocol.imsdk.im_common;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.ArrayList;
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
import oicq.wlogin_sdk.b.bw;
import oicq.wlogin_sdk.b.by;
import oicq.wlogin_sdk.b.bz;
import oicq.wlogin_sdk.b.c;
import oicq.wlogin_sdk.b.ca;
import oicq.wlogin_sdk.b.cd;
import oicq.wlogin_sdk.b.ci;
import oicq.wlogin_sdk.b.cj;
import oicq.wlogin_sdk.b.cl;
import oicq.wlogin_sdk.b.cq;
import oicq.wlogin_sdk.b.cr;
import oicq.wlogin_sdk.b.d;
import oicq.wlogin_sdk.b.g;
import oicq.wlogin_sdk.b.i;
import oicq.wlogin_sdk.b.j;
import oicq.wlogin_sdk.b.k;
import oicq.wlogin_sdk.b.r;
import oicq.wlogin_sdk.b.u;
import oicq.wlogin_sdk.sharemem.WloginSigInfo;
import oicq.wlogin_sdk.tools.util;

/* compiled from: request_TGTGT */
public class l extends oicq_request {
    public static boolean I = false;
    public static byte[] J = new byte[0];
    public static byte[] K = new byte[0];
    public static byte[] L = new byte[0];

    public l(u uVar, Context context) {
        this.t = 2064;
        this.u = 9;
        this.v = "wtlogin.login";
        this.x = uVar;
        this.x.m = 0;
        this.a = context;
    }

    public byte[] a(long j, long j2, int i, long j3, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i3, byte[] bArr5, byte[] bArr6, int i4, int i5, long[] jArr, int i6, long j4, int i7, int i8, int i9, int i10, byte[] bArr7, byte[] bArr8, byte[] bArr9, List<String> list, WUserSigInfo wUserSigInfo) {
        int[] iArr = new int[30];
        iArr = new int[]{24, 1, 262, 278, 256, 263, 264, 260, ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_02, 274, ErrorCode.ERROR_SDKENGINE_CANLOADTBS, ErrorCode.THROWABLE_INITX5CORE, ErrorCode.TEST_THROWABLE_ISNOT_NULL, 358, 362, 340, ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01, 8, 1297, 370, 389, 1024, 391, 392, 404, 401, im_common.GRP_CONFERENCE, im_common.GRP_HRTX, 375, 1302};
        async_context b = u.b(this.x.h);
        ArrayList arrayList = new ArrayList();
        int i11 = 0;
        int length = iArr.length;
        int i12 = 0;
        while (i12 < length) {
            int length2;
            Object obj = null;
            byte[] bArr10;
            switch (iArr[i12]) {
                case 1:
                    obj = new c().a(j3, bArr);
                    break;
                case 8:
                    obj = new cr().a(0, u.u, 0);
                    break;
                case 24:
                    obj = new bs().a(j, i, j3, i2);
                    break;
                case 256:
                    obj = new d().a(j, j4, i, i6);
                    break;
                case 260:
                    if (!(bArr8 == null || bArr8.length == 0)) {
                        obj = new g().a(bArr8);
                        break;
                    }
                case 262:
                    i iVar = new i();
                    if (bArr5 != null && bArr5.length > 0) {
                        iVar.b(bArr5, bArr5.length);
                        obj = iVar.b();
                        break;
                    }
                    obj = iVar.a(j, j2, i, j3, bArr2, bArr, 1, bArr3, b._msalt, this.x.g.getBytes(), bArr4, u.U, u.A, i3);
                    break;
                    break;
                case 263:
                    obj = new j().a(i7, i8, i9, i10);
                    break;
                case 264:
                    if (!(bArr7 == null || bArr7.length == 0)) {
                        obj = new k().a(bArr7);
                        break;
                    }
                case 274:
                    if (!(this.x.g == null || util.check_uin_account(this.x.g).booleanValue())) {
                        obj = new r().a(this.x.g.getBytes());
                        break;
                    }
                case 278:
                    obj = new u().a(i4, i5, jArr);
                    break;
                case ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01 /*321*/:
                    obj = new al().b(u.C, u.D, u.F);
                    break;
                case ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_02 /*322*/:
                    obj = new am().a(bArr9);
                    break;
                case ErrorCode.ERROR_SDKENGINE_CANLOADTBS /*324*/:
                    byte[] a = new oicq.wlogin_sdk.b.l().a(u.M);
                    byte[] a2 = new ac().a(util.get_os_type(), util.get_os_version(), u.D, u.C, new byte[0], u.F);
                    bArr10 = a;
                    byte[] bArr11 = a2;
                    obj = new ao().a(bArr10, bArr11, new ag().a(u.T, u.U, u.V, u.Y, u.I, u.A, u.P), new bf().a(u.I), b._tgtgt_key);
                    break;
                case ErrorCode.THROWABLE_INITX5CORE /*325*/:
                    obj = new ap().a(u.A);
                    break;
                case ErrorCode.TEST_THROWABLE_ISNOT_NULL /*327*/:
                    obj = new ar().a(j, u.G, u.H);
                    break;
                case 340:
                    obj = new aw().a(this.x.i);
                    break;
                case 358:
                    if ((i4 & 128) != 0) {
                        obj = new ba().a(u.x);
                        break;
                    }
                    break;
                case 362:
                    if (!(bArr6 == null || bArr6.length == 0)) {
                        obj = new bd().a(bArr6);
                        break;
                    }
                case 370:
                    if (!(this.x.r == null || this.x.r.length == 0)) {
                        obj = new bh().a(this.x.r);
                        break;
                    }
                case 375:
                    obj = new bk().a(util.BUILD_TIME, util.SDK_VERSION);
                    break;
                case 389:
                    if (i3 == 3) {
                        obj = new bw().a(1);
                        break;
                    }
                    break;
                case 391:
                    if (!(u.N == null || u.N.length == 0)) {
                        obj = new by().a(u.N);
                        break;
                    }
                case 392:
                    if (!(u.O == null || u.O.length == 0)) {
                        obj = new bz().a(u.O);
                        break;
                    }
                case 401:
                    obj = new ca().a(I);
                    break;
                case 404:
                    if (!(u.L == null || u.L.length == 0)) {
                        obj = new cd().a(u.L);
                        break;
                    }
                case im_common.GRP_CONFERENCE /*513*/:
                    if (!(J == null || J.length == 0)) {
                        obj = new ci().a(J, K, "qq".getBytes(), L);
                        break;
                    }
                case im_common.GRP_HRTX /*514*/:
                    if (!(u.R == null || u.R.length == 0)) {
                        obj = new cj().a(u.R, u.S);
                        break;
                    }
                case 1024:
                    WloginSigInfo a3 = this.x.a(j3, j);
                    if (!(a3 == null || a3._G == null || a3._G.length == 0 || a3._dpwd == null || a3._dpwd.length == 0 || a3._randseed == null || a3._randseed.length == 0)) {
                        obj = new cl().a(a3._G, j3, u.A, a3._dpwd, j, j2, a3._randseed);
                        break;
                    }
                case 1297:
                    if (!(list == null || list.size() == 0)) {
                        obj = new cq().a(list);
                        break;
                    }
                case 1302:
                    b bVar = new b(1302);
                    bArr10 = new byte[4];
                    util.int32_to_buf(bArr10, 0, u.af);
                    bVar.b(bArr10, 4);
                    obj = bVar.b();
                    break;
            }
            if (obj != null) {
                arrayList.add(obj);
                length2 = obj.length + i11;
            } else {
                length2 = i11;
            }
            i12++;
            i11 = length2;
        }
        Object obj2 = new byte[i11];
        int size = arrayList.size();
        int i13 = 0;
        for (int i14 = 0; i14 < size; i14++) {
            byte[] bArr12 = (byte[]) arrayList.get(i14);
            System.arraycopy(bArr12, 0, obj2, i13, bArr12.length);
            i13 += bArr12.length;
        }
        return b((byte[]) obj2, this.u, size);
    }

    public int a(long j, long j2, long j3, int i, byte[] bArr, byte[] bArr2, byte[] bArr3, int i2, int i3, int i4, long[] jArr, int i5, long j4, int i6, int i7, int i8, int i9, byte[] bArr4, WUserSigInfo wUserSigInfo) {
        g gVar;
        int i10 = u.w;
        async_context b = u.b(this.x.h);
        b._tgtgt_key = util.get_rand_16byte(u.B);
        byte[] bArr5 = b._tgtgt_key;
        g gVar2 = b._t104;
        if (gVar2 == null) {
            gVar = new g();
        } else {
            gVar = gVar2;
        }
        int i11 = 0;
        while (true) {
            a(j3, a(j, j2, i10, j3, i, bArr, bArr2, bArr3, bArr5, i2, null, null, i3, i4, jArr, i5, j4, i6, i7, i8, i9, bArr4, gVar.c(), u.E, wUserSigInfo._domains, wUserSigInfo), this.y);
            int a = a(String.valueOf(this.x.f), false, wUserSigInfo);
            if (a != 0) {
                return a;
            }
            int b2 = b();
            util.LOGI("retry num:" + i11 + " ret:" + b2, "" + j3);
            if (b2 != 180) {
                return b2;
            }
            a = i11 + 1;
            if (i11 >= 1) {
                return b2;
            }
            i11 = a;
        }
    }

    public int a(long j, long j2, long j3, int i, byte[] bArr, byte[] bArr2, byte[] bArr3, int i2, int i3, long[] jArr, int i4, long j4, int i5, int i6, int i7, int i8, byte[] bArr4, WUserSigInfo wUserSigInfo) {
        g gVar;
        int i9 = u.w;
        g gVar2 = u.b(this.x.h)._t104;
        if (gVar2 == null) {
            gVar = new g();
        } else {
            gVar = gVar2;
        }
        byte[] c = c(bArr2);
        if (c == null) {
            return -1014;
        }
        int i10 = 0;
        while (true) {
            byte[] a = a(j, j2, i9, j3, i, bArr, null, null, null, 0, c, bArr3, i2, i3, jArr, i4, j4, i5, i6, i7, i8, bArr4, gVar.c(), u.E, wUserSigInfo._domains, wUserSigInfo);
            a(this.i, this.t, this.j, j3, this.m, this.n, i9, this.p, a);
            int a2 = a(String.valueOf(this.x.f), false, wUserSigInfo);
            if (a2 != 0) {
                return a2;
            }
            int b = b();
            util.LOGI("retry num:" + i10 + " ret:" + b, "" + j3);
            if (b != 180) {
                return b;
            }
            a2 = i10 + 1;
            if (i10 >= 1) {
                return b;
            }
            i10 = a2;
        }
    }
}
