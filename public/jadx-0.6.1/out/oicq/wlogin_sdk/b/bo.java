package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t17b */
public class bo extends b {
    public bo() {
        this.h = 379;
    }

    public Boolean f() {
        if (this.f < 4) {
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }

    public int a() {
        if (this.f < 4) {
            return 0;
        }
        return util.buf_to_int16(this.g, this.e);
    }

    public int g() {
        if (this.f < 4) {
            return 0;
        }
        return util.buf_to_int16(this.g, this.e + 2);
    }
}
