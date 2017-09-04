package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t154 */
public class aw extends b {
    public aw() {
        this.h = 340;
    }

    public byte[] a(int i) {
        byte[] bArr = new byte[4];
        util.int32_to_buf(bArr, 0, i);
        b(this.h);
        c(bArr, bArr.length);
        e();
        return b();
    }
}
