package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t107 */
public class j extends b {
    int a;

    public j() {
        this.a = 6;
        this.h = 263;
    }

    public byte[] a(int i, int i2, int i3, int i4) {
        byte[] bArr = new byte[this.a];
        util.int16_to_buf(bArr, 0, i);
        util.int8_to_buf(bArr, 2, i2);
        util.int16_to_buf(bArr, 3, i3);
        util.int8_to_buf(bArr, 5, i4);
        b(this.h);
        c(bArr, this.a);
        e();
        return b();
    }
}
