package oicq.wlogin_sdk.request;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.ArrayList;
import oicq.wlogin_sdk.b.am;
import oicq.wlogin_sdk.b.ap;
import oicq.wlogin_sdk.b.aw;
import oicq.wlogin_sdk.b.b;
import oicq.wlogin_sdk.b.bu;
import oicq.wlogin_sdk.b.bv;
import oicq.wlogin_sdk.b.cr;
import oicq.wlogin_sdk.b.d;
import oicq.wlogin_sdk.b.k;
import oicq.wlogin_sdk.b.l;
import oicq.wlogin_sdk.b.u;
import oicq.wlogin_sdk.tools.util;

/* compiled from: VerifyPTSig */
public class c extends oicq_request {
    private String I;

    public c(u uVar, String str) {
        this.t = 2064;
        this.u = 22;
        this.v = "wtlogin.login";
        this.x = uVar;
        this.x.m = 0;
        this.I = str;
    }

    public byte[] a(int i, int i2) {
        int i3;
        int length;
        int i4 = 0;
        int[] iArr = new int[]{8, 256, 264, 265, 278, ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_02, ErrorCode.THROWABLE_INITX5CORE, 340, 387, 388, 1312};
        ArrayList arrayList = new ArrayList();
        int length2 = iArr.length;
        int i5 = 0;
        async_context b = u.b(this.x.h);
        int i6 = 0;
        while (i6 < length2) {
            int i7;
            Object obj = new byte[0];
            switch (iArr[i6]) {
                case 8:
                    obj = new cr().a(0, u.u, 0);
                    break;
                case 256:
                    obj = new d().a(b._appid, b._sub_appid, 0, b._main_sigmap);
                    break;
                case 264:
                    if (!(u.aa == null || u.aa.length == 0)) {
                        k kVar = new k();
                        kVar.b(u.aa, u.aa.length);
                        obj = kVar.b();
                        break;
                    }
                case 265:
                    obj = new l().a(u.M);
                    break;
                case 278:
                    obj = new u().a(i, i2, b._sub_appid_list);
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
                case 387:
                    obj = new bu().a(b._msalt);
                    break;
                case 388:
                    obj = new bv().a(b._msalt, b._mpasswd);
                    break;
                case 1312:
                    b bVar = new b(1312);
                    byte[] string_to_buf = util.string_to_buf(this.I);
                    bVar.b(string_to_buf, string_to_buf.length);
                    obj = bVar.b();
                    break;
            }
            if (obj.length > 4) {
                i3 = i4 + 1;
                length = obj.length + i5;
                arrayList.add(obj);
                i7 = length;
                length = i3;
            } else {
                i7 = i5;
                length = i4;
            }
            i6++;
            i5 = i7;
            i4 = length;
        }
        Object obj2 = new byte[i5];
        i3 = 0;
        for (length = 0; length < i4; length++) {
            byte[] bArr = (byte[]) arrayList.get(length);
            System.arraycopy(bArr, 0, obj2, i3, bArr.length);
            i3 += bArr.length;
        }
        return b((byte[]) obj2, this.u, i4);
    }

    public int a(int i, int i2, WUserSigInfo wUserSigInfo) {
        int i3 = 0;
        while (true) {
            a(this.x.f, a(i, i2), this.y);
            int a = a(String.valueOf(this.x.f), false, wUserSigInfo);
            if (a != 0) {
                return a;
            }
            int b = b();
            util.LOGI("retry num:" + i3 + " ret:" + b, "" + this.x.f);
            if (b != 180) {
                return b;
            }
            a = i3 + 1;
            if (i3 >= 1) {
                return b;
            }
            i3 = a;
        }
    }
}
