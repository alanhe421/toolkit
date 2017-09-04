package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t186 */
public class bx extends b {
    private int a;

    public bx() {
        this.h = 390;
    }

    public Boolean f() {
        if (this.f < 2) {
            return Boolean.valueOf(false);
        }
        this.a = util.buf_to_int8(this.g, this.e + 1);
        return Boolean.valueOf(true);
    }

    public boolean a() {
        return this.a == 1;
    }
}
