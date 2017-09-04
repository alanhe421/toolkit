package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t182 */
public class bt extends b {
    int a;
    int i;

    public bt() {
        this.a = 0;
        this.i = 0;
        this.h = 386;
    }

    public Boolean f() {
        if (this.f < 5) {
            return Boolean.valueOf(false);
        }
        this.a = util.buf_to_int16(this.g, this.e + 1);
        this.i = util.buf_to_int16(this.g, (this.e + 1) + 2);
        return Boolean.valueOf(true);
    }

    public int a() {
        return this.a;
    }

    public int g() {
        return this.i;
    }
}
