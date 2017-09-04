package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t124 */
public class ac extends b {
    int a;

    public ac() {
        this.a = 0;
        this.h = 292;
    }

    public byte[] a(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        if (bArr3 == null) {
            bArr3 = new byte[0];
        }
        if (bArr4 == null) {
            bArr4 = new byte[0];
        }
        if (bArr5 == null) {
            bArr5 = new byte[0];
        }
        int d = d(bArr, 16);
        int d2 = d(bArr2, 16);
        int d3 = d(bArr3, 16);
        int d4 = d(bArr4, 32);
        int d5 = d(bArr5, 16);
        this.a = (((((((((d + 2) + 2) + d2) + 2) + 2) + d3) + 2) + d4) + 2) + d5;
        Object obj = new byte[this.a];
        util.int16_to_buf(obj, 0, d);
        System.arraycopy(bArr, 0, obj, 2, d);
        d += 2;
        util.int16_to_buf(obj, d, d2);
        d += 2;
        System.arraycopy(bArr2, 0, obj, d, d2);
        d += d2;
        util.int16_to_buf(obj, d, i);
        d += 2;
        util.int16_to_buf(obj, d, d3);
        d += 2;
        System.arraycopy(bArr3, 0, obj, d, d3);
        d += d3;
        util.int16_to_buf(obj, d, d4);
        d += 2;
        System.arraycopy(bArr4, 0, obj, d, d4);
        d += d4;
        util.int16_to_buf(obj, d, d5);
        d += 2;
        System.arraycopy(bArr5, 0, obj, d, d5);
        d += d5;
        b(this.h);
        c(obj, this.a);
        e();
        return b();
    }
}
