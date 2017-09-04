package oicq.wlogin_sdk.request;

import java.util.ArrayList;
import oicq.wlogin_sdk.b.cr;
import oicq.wlogin_sdk.b.u;
import oicq.wlogin_sdk.tools.util;

/* compiled from: request_smslogin_refresh */
public class x extends oicq_request {
    public x(u uVar) {
        this.t = 2064;
        this.u = 19;
        this.v = "wtlogin.login";
        this.x = uVar;
        this.x.m = 0;
    }

    public byte[] a(int i, int i2, long[] jArr) {
        int[] iArr = new int[]{260, 8, 278};
        ArrayList arrayList = new ArrayList();
        int length = iArr.length;
        async_context b = u.b(this.x.h);
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < length) {
            Object obj = new byte[0];
            switch (iArr[i3]) {
                case 8:
                    obj = new cr().a(0, u.u, 0);
                    break;
                case 260:
                    obj = b._t104.b();
                    break;
                case 278:
                    obj = new u().a(i, i2, jArr);
                    break;
            }
            if (obj.length > 4) {
                i5++;
                i4 += obj.length;
                arrayList.add(obj);
            }
            int i6 = i4;
            i3++;
            i5 = i5;
            i4 = i6;
        }
        Object obj2 = new byte[i4];
        i3 = 0;
        for (i4 = 0; i4 < i5; i4++) {
            byte[] bArr = (byte[]) arrayList.get(i4);
            System.arraycopy(bArr, 0, obj2, i3, bArr.length);
            i3 += bArr.length;
        }
        return b((byte[]) obj2, this.u, i5);
    }

    public int a(int i, int i2, long[] jArr, WUserSigInfo wUserSigInfo) {
        int i3 = u.w;
        int i4 = 0;
        while (true) {
            a(this.i, this.t, this.j, this.x.f, this.m, this.n, i3, this.p, a(i, i2, jArr));
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
