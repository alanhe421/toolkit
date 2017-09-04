package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t125 */
public class ad extends b {
    int a;
    int i;

    public ad() {
        this.a = 0;
        this.i = 0;
        this.h = 293;
    }

    public Boolean f() {
        if (this.f < 2) {
            return Boolean.valueOf(false);
        }
        this.a = util.buf_to_int16(this.g, this.e);
        if (this.f < (this.a + 2) + 2) {
            return Boolean.valueOf(false);
        }
        this.i = util.buf_to_int16(this.g, (this.e + 2) + this.a);
        return Boolean.valueOf(true);
    }

    public byte[] a() {
        Object obj = new byte[this.a];
        System.arraycopy(this.g, this.e + 2, obj, 0, obj.length);
        return obj;
    }

    public byte[] g() {
        Object obj = new byte[this.i];
        System.arraycopy(this.g, ((this.e + 2) + this.a) + 2, obj, 0, obj.length);
        return obj;
    }
}
