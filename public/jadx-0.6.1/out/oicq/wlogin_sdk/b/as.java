package oicq.wlogin_sdk.b;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t148 */
public class as extends b {
    public as() {
        this.h = ErrorCode.THROWABLE_INITTESRUNTIMEENVIRONMENT;
    }

    public byte[] a(byte[] bArr, long j, long j2, long j3, byte[] bArr2, byte[] bArr3) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        if (bArr3 == null) {
            bArr3 = new byte[0];
        }
        int d = d(bArr, 32);
        int d2 = d(bArr2, 32);
        int d3 = d(bArr3, 32);
        Object obj = new byte[((((((((d + 2) + 4) + 4) + 4) + 2) + d2) + 2) + d3)];
        util.int16_to_buf(obj, 0, d);
        System.arraycopy(bArr, 0, obj, 2, d);
        d += 2;
        util.int64_to_buf32(obj, d, j);
        d += 4;
        util.int64_to_buf32(obj, d, j2);
        d += 4;
        util.int64_to_buf32(obj, d, j3);
        d += 4;
        util.int16_to_buf(obj, d, d2);
        d += 2;
        System.arraycopy(bArr2, 0, obj, d, d2);
        d += d2;
        util.int16_to_buf(obj, d, d3);
        d += 2;
        System.arraycopy(bArr3, 0, obj, d, d3);
        d += d3;
        b(this.h);
        c(obj, obj.length);
        e();
        return b();
    }
}
