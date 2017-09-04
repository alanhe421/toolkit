package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t185 */
public class bw extends b {
    public bw() {
        this.h = 389;
    }

    public byte[] a(int i) {
        byte[] bArr = new byte[2];
        util.int8_to_buf(bArr, 0, 1);
        util.int8_to_buf(bArr, 1, i);
        b(this.h);
        c(bArr, bArr.length);
        e();
        return b();
    }
}
