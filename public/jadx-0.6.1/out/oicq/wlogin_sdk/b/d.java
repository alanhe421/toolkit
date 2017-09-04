package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t100 */
public class d extends b {
    int a;
    int i;
    int j;

    public d() {
        this.a = 1;
        this.i = 5;
        this.j = 22;
        this.h = 256;
    }

    public byte[] a(long j, long j2, int i, int i2) {
        byte[] bArr = new byte[this.j];
        util.int16_to_buf(bArr, 0, this.a);
        util.int32_to_buf(bArr, 2, this.i);
        util.int32_to_buf(bArr, 6, (int) j);
        util.int32_to_buf(bArr, 10, (int) j2);
        util.int32_to_buf(bArr, 14, i);
        util.int32_to_buf(bArr, 18, i2);
        b(this.h);
        c(bArr, this.j);
        e();
        return b();
    }
}
