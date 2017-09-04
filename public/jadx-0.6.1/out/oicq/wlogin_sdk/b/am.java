package oicq.wlogin_sdk.b;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t142 */
public class am extends b {
    int a;
    int i;

    public am() {
        this.a = 0;
        this.i = 0;
        this.h = ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_02;
    }

    public byte[] a(byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        int d = d(bArr, 32);
        this.i = d + 4;
        Object obj = new byte[this.i];
        util.int16_to_buf(obj, 0, this.a);
        util.int16_to_buf(obj, 2, d);
        System.arraycopy(bArr, 0, obj, 4, d);
        d += 4;
        b(this.h);
        c(obj, obj.length);
        e();
        return b();
    }
}
