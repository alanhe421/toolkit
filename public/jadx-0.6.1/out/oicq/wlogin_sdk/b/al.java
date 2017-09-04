package oicq.wlogin_sdk.b;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t141 */
public class al extends b {
    int a;

    public al() {
        this.a = 1;
        this.h = ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01;
    }

    public byte[] b(byte[] bArr, int i, byte[] bArr2) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        int length = (((bArr.length + 4) + 2) + 2) + bArr2.length;
        Object obj = new byte[length];
        util.int16_to_buf(obj, 0, this.a);
        util.int16_to_buf(obj, 2, bArr.length);
        System.arraycopy(bArr, 0, obj, 4, bArr.length);
        int length2 = bArr.length + 4;
        util.int16_to_buf(obj, length2, i);
        length2 += 2;
        util.int16_to_buf(obj, length2, bArr2.length);
        length2 += 2;
        System.arraycopy(bArr2, 0, obj, length2, bArr2.length);
        length2 += bArr2.length;
        b(this.h);
        c(obj, length);
        e();
        return b();
    }
}
