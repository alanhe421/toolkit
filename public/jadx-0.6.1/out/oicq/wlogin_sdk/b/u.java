package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t116 */
public class u extends b {
    int a;
    int i;

    public u() {
        this.a = 0;
        this.i = 0;
        this.h = 278;
    }

    public byte[] a(int i, int i2, long[] jArr) {
        int i3 = 0;
        if (jArr == null) {
            jArr = new long[0];
        }
        this.a = (jArr.length * 4) + 10;
        byte[] bArr = new byte[this.a];
        util.int8_to_buf(bArr, 0, this.i);
        util.int32_to_buf(bArr, 1, i);
        util.int32_to_buf(bArr, 5, i2);
        util.int8_to_buf(bArr, 9, jArr.length);
        int i4 = 10;
        while (i3 < jArr.length) {
            util.int32_to_buf(bArr, i4, (int) jArr[i3]);
            i4 += 4;
            i3++;
        }
        b(this.h);
        c(bArr, this.a);
        e();
        return b();
    }
}
