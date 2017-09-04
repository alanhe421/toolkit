package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t2 */
public class cg extends b {
    int a;
    int i;

    public cg() {
        this.a = 0;
        this.i = 0;
        this.h = 2;
    }

    public byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        this.a = (bArr.length + 6) + bArr2.length;
        Object obj = new byte[this.a];
        util.int16_to_buf(obj, 0, this.i);
        util.int16_to_buf(obj, 2, bArr.length);
        System.arraycopy(bArr, 0, obj, 4, bArr.length);
        int length = bArr.length + 4;
        util.int16_to_buf(obj, length, bArr2.length);
        length += 2;
        System.arraycopy(bArr2, 0, obj, length, bArr2.length);
        length += bArr2.length;
        b(this.h);
        c(obj, this.a);
        e();
        return b();
    }
}
