package oicq.wlogin_sdk.b;

import com.tencent.openqq.protocol.imsdk.im_common;
import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t201 */
public class ci extends b {
    public ci() {
        this.h = im_common.GRP_CONFERENCE;
    }

    public byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        if (bArr4 == null) {
            bArr4 = new byte[0];
        }
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        if (bArr == null) {
            bArr = new byte[0];
        }
        int length = ((((((bArr.length + 2) + 2) + bArr2.length) + 2) + bArr3.length) + 2) + bArr4.length;
        Object obj = new byte[length];
        util.int16_to_buf(obj, 0, bArr.length);
        System.arraycopy(bArr, 0, obj, 2, bArr.length);
        int length2 = bArr.length + 2;
        util.int16_to_buf(obj, length2, bArr2.length);
        length2 += 2;
        System.arraycopy(bArr2, 0, obj, length2, bArr2.length);
        length2 += bArr2.length;
        util.int16_to_buf(obj, length2, bArr3.length);
        length2 += 2;
        System.arraycopy(bArr3, 0, obj, length2, bArr3.length);
        length2 += bArr3.length;
        util.int16_to_buf(obj, length2, bArr4.length);
        length2 += 2;
        System.arraycopy(bArr4, 0, obj, length2, bArr4.length);
        length2 += bArr4.length;
        b(this.h);
        c(obj, length);
        e();
        return b();
    }
}
