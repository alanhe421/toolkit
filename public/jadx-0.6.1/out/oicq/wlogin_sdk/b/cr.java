package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t8 */
public class cr extends b {
    int a;

    public cr() {
        this.a = 0;
        this.h = 8;
    }

    public byte[] a(int i, int i2, int i3) {
        this.a = 8;
        byte[] bArr = new byte[this.a];
        util.int16_to_buf(bArr, 0, i);
        util.int32_to_buf(bArr, 2, i2);
        util.int16_to_buf(bArr, 6, i3);
        b(this.h);
        c(bArr, this.a);
        e();
        return b();
    }
}
