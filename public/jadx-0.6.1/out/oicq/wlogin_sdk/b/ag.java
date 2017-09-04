package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t128 */
public class ag extends b {
    int a;

    public ag() {
        this.a = 0;
        this.h = 296;
    }

    public byte[] a(int i, int i2, int i3, int i4, byte[] bArr, byte[] bArr2, byte[] bArr3) {
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
        int d2 = d(bArr2, 16);
        int d3 = d(bArr3, 16);
        this.a = ((((d + 11) + 2) + d2) + 2) + d3;
        Object obj = new byte[this.a];
        util.int16_to_buf(obj, 0, 0);
        util.int8_to_buf(obj, 2, i);
        util.int8_to_buf(obj, 3, i2);
        util.int8_to_buf(obj, 4, i3);
        util.int32_to_buf(obj, 5, i4);
        util.int16_to_buf(obj, 9, d);
        System.arraycopy(bArr, 0, obj, 11, d);
        d += 11;
        util.int16_to_buf(obj, d, d2);
        d += 2;
        System.arraycopy(bArr2, 0, obj, d, d2);
        d += d2;
        util.int16_to_buf(obj, d, d3);
        d += 2;
        System.arraycopy(bArr3, 0, obj, d, d3);
        d += d3;
        b(this.h);
        c(obj, this.a);
        e();
        return b();
    }
}
