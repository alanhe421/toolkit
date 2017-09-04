package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t179 */
public class bm extends b {
    public int a;

    public bm() {
        this.a = 0;
        this.h = 377;
    }

    public Boolean f() {
        if (this.f < 2) {
            return Boolean.valueOf(false);
        }
        int buf_to_int16 = util.buf_to_int16(this.g, this.e);
        if (this.f < buf_to_int16 + 2) {
            return Boolean.valueOf(false);
        }
        this.a = buf_to_int16;
        return Boolean.valueOf(true);
    }

    public byte[] a() {
        Object obj = new byte[this.a];
        System.arraycopy(this.g, this.e + 2, obj, 0, this.a);
        return obj;
    }
}
