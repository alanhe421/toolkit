package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t18 */
public class bs extends b {
    int a;
    int i;
    int j;

    public bs() {
        this.a = 22;
        this.i = 1;
        this.j = 1536;
        this.h = 24;
    }

    public byte[] a(long j, int i, long j2, int i2) {
        byte[] bArr = new byte[this.a];
        util.int16_to_buf(bArr, 0, this.i);
        util.int32_to_buf(bArr, 2, this.j);
        util.int32_to_buf(bArr, 6, (int) j);
        util.int32_to_buf(bArr, 10, i);
        util.int32_to_buf(bArr, 14, (int) j2);
        util.int16_to_buf(bArr, 18, i2);
        util.int16_to_buf(bArr, 20, 0);
        b(this.h);
        c(bArr, this.a);
        e();
        return b();
    }
}
