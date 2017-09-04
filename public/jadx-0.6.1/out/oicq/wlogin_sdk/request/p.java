package oicq.wlogin_sdk.request;

import com.tencent.qalsdk.im_open.http;
import java.util.ArrayList;
import oicq.wlogin_sdk.b.b;
import oicq.wlogin_sdk.b.bj;
import oicq.wlogin_sdk.b.bp;
import oicq.wlogin_sdk.b.cm;
import oicq.wlogin_sdk.b.cn;
import oicq.wlogin_sdk.b.co;
import oicq.wlogin_sdk.b.cr;
import oicq.wlogin_sdk.b.g;
import oicq.wlogin_sdk.b.u;
import oicq.wlogin_sdk.tools.util;

/* compiled from: request_checksms */
public class p extends oicq_request {
    public p(u uVar) {
        this.t = 2064;
        this.u = 7;
        this.v = "wtlogin.login";
        this.x = uVar;
        this.x.m = 0;
    }

    public byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int i2, long[] jArr, byte[] bArr4) {
        int[] iArr = new int[]{8, 260, 278, 372, 380, 1025, http.Request_Timeout};
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        for (int i4 : iArr) {
            Object obj = null;
            switch (i4) {
                case 8:
                    obj = new cr().a(0, u.u, 0);
                    break;
                case 260:
                    obj = new g().a(bArr);
                    break;
                case 278:
                    obj = new u().a(i, i2, jArr);
                    break;
                case 372:
                    obj = new bj().a(bArr3);
                    break;
                case 380:
                    obj = new bp().a(bArr2);
                    break;
                case http.Request_Timeout /*408*/:
                    b bVar = new b(http.Request_Timeout);
                    bVar.b(new byte[]{(byte) s.I}, 1);
                    obj = bVar.b();
                    break;
                case 1025:
                    obj = new cm().a(bArr4);
                    break;
            }
            if (obj != null) {
                arrayList.add(obj);
                i3 += obj.length;
            }
        }
        int size = arrayList.size();
        Object obj2 = new byte[i3];
        int i5 = 0;
        for (i3 = 0; i3 < size; i3++) {
            byte[] bArr5 = (byte[]) arrayList.get(i3);
            System.arraycopy(bArr5, 0, obj2, i5, bArr5.length);
            i5 += bArr5.length;
        }
        return b((byte[]) obj2, this.u, size);
    }

    public int a(byte[] bArr, int i, int i2, long[] jArr, WUserSigInfo wUserSigInfo) {
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
            a(this.i, this.t, this.j, this.x.f, this.m, this.n, i3, this.p, a(gVar.c(), bArr, bjVar.c(), i, i2, jArr, b._G));
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
