package oicq.wlogin_sdk.b;

import com.tencent.openqq.protocol.imsdk.im_common;
import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t202 */
public class cj extends b {
    public cj() {
        this.h = im_common.GRP_HRTX;
    }

    public byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            bArr = new byte[16];
        }
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        int d = d(bArr, 16);
        int d2 = d(bArr2, 32);
        Object obj = new byte[(((d + 2) + 2) + d2)];
        util.int16_to_buf(obj, 0, d);
        System.arraycopy(bArr, 0, obj, 2, d);
        d += 2;
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
