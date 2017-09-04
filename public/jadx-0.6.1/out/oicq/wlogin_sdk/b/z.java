package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t11f */
public class z extends b {
    public z() {
        this.h = 287;
    }

    public Boolean f() {
        if (this.f < 8) {
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }

    public int a() {
        return util.buf_to_int32(this.g, this.e + 4);
    }
}
