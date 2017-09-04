package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t153 */
public class av extends b {
    public av() {
        this.h = 339;
    }

    public byte[] a(int i) {
        byte[] bArr = new byte[2];
        util.int16_to_buf(bArr, 0, i);
        b(this.h);
        c(bArr, bArr.length);
        e();
        return b();
    }
}
