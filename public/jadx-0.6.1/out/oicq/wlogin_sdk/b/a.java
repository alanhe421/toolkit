package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: RegTLV */
public class a extends b {
    int a = 2;

    public a(int i) {
        super(i);
    }

    public void a(byte[] bArr, int i) {
        if (this.a + i > this.b) {
            this.b = (this.a + i) + 128;
            Object obj = new byte[this.b];
            System.arraycopy(this.g, 0, obj, 0, this.a);
            this.g = obj;
        }
        this.c = this.a + i;
        System.arraycopy(bArr, 0, this.g, this.a, i);
        this.f = i;
        util.int8_to_buf(this.g, 0, this.h);
        util.int8_to_buf(this.g, 1, this.f);
    }

    public byte[] a() {
        return b();
    }

    public void a(int i) {
        byte[] bArr = new byte[4];
        util.int32_to_buf(bArr, 0, i);
        a(bArr, bArr.length);
    }

    public void a(byte b) {
        byte[] bArr = new byte[]{b};
        a(bArr, bArr.length);
    }
}
