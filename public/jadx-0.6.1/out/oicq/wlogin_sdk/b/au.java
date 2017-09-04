package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t150 */
public class au extends b {
    public int a;

    public au() {
        this.a = 0;
        this.h = 336;
    }

    public Boolean f() {
        if (this.f < 7) {
            return Boolean.valueOf(false);
        }
        int buf_to_int16 = util.buf_to_int16(this.g, this.e + 5);
        if (this.f < buf_to_int16 + 7) {
            return Boolean.valueOf(false);
        }
        this.a = buf_to_int16;
        return Boolean.valueOf(true);
    }

    public int a() {
        return util.buf_to_int32(this.g, this.e);
    }

    public byte g() {
        return (byte) (util.buf_to_int8(this.g, this.e + 4) & 255);
    }
}
