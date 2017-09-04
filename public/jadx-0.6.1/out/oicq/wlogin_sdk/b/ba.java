package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t166 */
public class ba extends b {
    public ba() {
        this.h = 358;
    }

    public byte[] a(int i) {
        byte[] bArr = new byte[1];
        util.int8_to_buf(bArr, 0, i);
        b(this.h);
        c(bArr, bArr.length);
        e();
        return b();
    }
}
