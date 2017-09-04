package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t17a */
public class bn extends b {
    public bn() {
        this.h = 378;
    }

    public byte[] a(long j) {
        byte[] bArr = new byte[4];
        util.int64_to_buf32(bArr, 0, j);
        b(this.h);
        c(bArr, 4);
        e();
        return b();
    }
}
