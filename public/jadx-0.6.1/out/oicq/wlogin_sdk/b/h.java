package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t105 */
public class h extends b {
    int a;
    int i;
    int j;
    int k;

    public h() {
        this.a = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.h = 261;
    }

    public Boolean f() {
        if (this.f < 2) {
            return Boolean.valueOf(false);
        }
        this.i = util.buf_to_int16(this.g, this.e);
        if (this.f < (this.i + 2) + 2) {
            return Boolean.valueOf(false);
        }
        this.a = util.buf_to_int16(this.g, (this.e + 2) + this.i);
        if (this.f < ((this.i + 2) + 2) + this.a) {
            return Boolean.valueOf(false);
        }
        this.k = this.e + 2;
        this.j = ((this.i + 2) + 2) + this.e;
        return Boolean.valueOf(true);
    }

    public byte[] a() {
        Object obj = new byte[this.a];
        if (this.a > 0) {
            System.arraycopy(this.g, this.j, obj, 0, this.a);
        }
        return obj;
    }

    public byte[] g() {
        Object obj = new byte[this.i];
        if (this.i > 0) {
            System.arraycopy(this.g, this.k, obj, 0, this.i);
        }
        return obj;
    }
}
