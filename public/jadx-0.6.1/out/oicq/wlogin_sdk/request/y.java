package oicq.wlogin_sdk.request;

import java.util.ArrayList;
import oicq.wlogin_sdk.b.af;
import oicq.wlogin_sdk.b.bv;
import oicq.wlogin_sdk.b.cr;
import oicq.wlogin_sdk.b.u;
import oicq.wlogin_sdk.tools.util;

/* compiled from: request_smslogin_verify */
public class y extends oicq_request {
    public y(u uVar) {
        this.t = 2064;
        this.u = 18;
        this.v = "wtlogin.login";
        this.x = uVar;
        this.x.m = 0;
    }

    public byte[] a(byte[] bArr, int i, int i2, long[] jArr) {
        int i3 = 0;
        int[] iArr = new int[]{260, 8, 295, 388, 278};
        ArrayList arrayList = new ArrayList();
        int length = iArr.length;
        int i4 = 0;
        async_context b = u.b(this.x.h);
        int i5 = 0;
        while (i5 < length) {
            Object obj = new byte[0];
            switch (iArr[i5]) {
                case 8:
                    obj = new cr().a(0, u.u, 0);
                    break;
                case 260:
                    obj = b._t104.b();
                    break;
                case 278:
                    obj = new u().a(i, i2, jArr);
                    break;
                case 295:
                    obj = new af().a(bArr, b._t126.a());
                    break;
                case 388:
                    obj = new bv().a(b._msalt, b._mpasswd);
                    break;
            }
            if (obj.length > 4) {
                i3++;
                i4 += obj.length;
                arrayList.add(obj);
            }
            int i6 = i4;
            i5++;
            i3 = i3;
            i4 = i6;
        }
        Object obj2 = new byte[i4];
        i5 = 0;
        for (i4 = 0; i4 < i3; i4++) {
            byte[] bArr2 = (byte[]) arrayList.get(i4);
            System.arraycopy(bArr2, 0, obj2, i5, bArr2.length);
            i5 += bArr2.length;
        }
        return b((byte[]) obj2, this.u, i3);
    }

    public int a(String str, int i, int i2, long[] jArr, WUserSigInfo wUserSigInfo) {
        int i3 = u.w;
        int i4 = 0;
        while (true) {
            a(this.i, this.t, this.j, this.x.f, this.m, this.n, i3, this.p, a(str.getBytes(), i, i2, jArr));
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
