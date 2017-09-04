package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t126 */
public class ae extends b {
    int a;

    public ae() {
        this.a = 0;
        this.h = 294;
    }

    public Boolean f() {
        if (this.f < 2) {
            return Boolean.valueOf(false);
        }
        if (this.f < 4) {
            return Boolean.valueOf(false);
        }
        this.a = util.buf_to_int16(this.g, this.e + 2);
        if ((this.a + 2) + 2 > this.f) {
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }

    public byte[] a() {
        Object obj = new byte[this.a];
        System.arraycopy(this.g, (this.e + 2) + 2, obj, 0, obj.length);
        return obj;
    }
}
