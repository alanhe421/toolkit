package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t17c */
public class bp extends b {
    int a;

    public bp() {
        this.a = 0;
        this.h = 380;
    }

    public byte[] a(byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        this.a = bArr.length + 2;
        Object obj = new byte[this.a];
        util.int16_to_buf(obj, 0, bArr.length);
        System.arraycopy(bArr, 0, obj, 2, bArr.length);
        int length = bArr.length + 2;
        b(this.h);
        c(obj, this.a);
        e();
        return b();
    }
}
