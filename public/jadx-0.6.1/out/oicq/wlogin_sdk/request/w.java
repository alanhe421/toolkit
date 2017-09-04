package oicq.wlogin_sdk.request;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.ArrayList;
import oicq.wlogin_sdk.b.am;
import oicq.wlogin_sdk.b.ap;
import oicq.wlogin_sdk.b.aw;
import oicq.wlogin_sdk.b.cr;
import oicq.wlogin_sdk.b.d;
import oicq.wlogin_sdk.b.k;
import oicq.wlogin_sdk.b.l;
import oicq.wlogin_sdk.b.r;
import oicq.wlogin_sdk.b.u;
import oicq.wlogin_sdk.tools.util;

/* compiled from: request_smslogin_check */
public class w extends oicq_request {
    public w(u uVar) {
        this.t = 2064;
        this.u = 17;
        this.v = "wtlogin.login";
        this.x = uVar;
        this.x.m = 0;
    }

    public byte[] a(long j, long j2, int i, int i2, byte[] bArr, byte[] bArr2, int i3, int i4, long[] jArr) {
        int i5;
        int i6 = 0;
        int[] iArr = new int[]{256, 264, 265, 8, ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_02, ErrorCode.THROWABLE_INITX5CORE, 340, 274, 278};
        ArrayList arrayList = new ArrayList();
        int length = iArr.length;
        int i7 = 0;
        int i8 = 0;
        while (i8 < length) {
            int length2;
            int i9;
            Object obj = new byte[0];
            switch (iArr[i8]) {
                case 8:
                    obj = new cr().a(0, u.u, 0);
                    break;
                case 256:
                    obj = new d().a(j, j2, i2, i);
                    break;
                case 264:
                    if (bArr != null && bArr.length > 0) {
                        obj = new k().a(bArr);
                        break;
                    }
                case 265:
                    if (u.M != null && u.M.length > 0) {
                        obj = new l().a(u.M);
                        break;
                    }
                case 274:
                    obj = new r().a(bArr2);
                    break;
                case 278:
                    obj = new u().a(i3, i4, jArr);
                    break;
                case ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_02 /*322*/:
                    obj = new am().a(u.E);
                    break;
                case ErrorCode.THROWABLE_INITX5CORE /*325*/:
                    obj = new ap().a(u.A);
                    break;
                case 340:
                    obj = new aw().a(this.x.i);
                    break;
            }
            if (obj.length > 4) {
                i5 = i6 + 1;
                length2 = obj.length + i7;
                arrayList.add(obj);
                i9 = length2;
                length2 = i5;
            } else {
                i9 = i7;
                length2 = i6;
            }
            i8++;
            i7 = i9;
            i6 = length2;
        }
        Object obj2 = new byte[i7];
        i5 = 0;
        for (length2 = 0; length2 < i6; length2++) {
            byte[] bArr3 = (byte[]) arrayList.get(length2);
            System.arraycopy(bArr3, 0, obj2, i5, bArr3.length);
            i5 += bArr3.length;
        }
        return b((byte[]) obj2, this.u, i6);
    }

    public int a(long j, long j2, int i, byte[] bArr, String str, int i2, int i3, long[] jArr, WUserSigInfo wUserSigInfo) {
        int i4 = u.w;
        int i5 = 0;
        while (true) {
            int i6 = i4;
            a(this.i, this.t, this.j, this.x.f, this.m, this.n, i6, this.p, a(j, j2, i, i4, bArr, str.getBytes(), i2, i3, jArr));
            int a = a(String.valueOf(this.x.f), false, wUserSigInfo);
            if (a != 0) {
                return a;
            }
            int b = b();
            util.LOGI("retry num:" + i5 + " ret:" + b, "" + this.x.f);
            if (b != 180) {
                return b;
            }
            a = i5 + 1;
            if (i5 >= 1) {
                return b;
            }
            i5 = a;
        }
    }
}
