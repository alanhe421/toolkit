package oicq.wlogin_sdk.b;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t147 */
public class ar extends b {
    public ar() {
        this.h = ErrorCode.TEST_THROWABLE_ISNOT_NULL;
    }

    public byte[] a(long j, byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        int d = d(bArr, 32);
        int d2 = d(bArr2, 32);
        Object obj = new byte[(((d + 6) + 2) + d2)];
        util.int64_to_buf32(obj, 0, j);
        util.int16_to_buf(obj, 4, d);
        System.arraycopy(bArr, 0, obj, 6, d);
        d += 6;
        util.int16_to_buf(obj, d, d2);
        d += 2;
        System.arraycopy(bArr2, 0, obj, d, d2);
        d += d2;
        b(this.h);
        c(obj, obj.length);
        e();
        return b();
    }
}
