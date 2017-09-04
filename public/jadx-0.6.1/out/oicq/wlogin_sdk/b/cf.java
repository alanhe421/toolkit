package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t199 */
public class cf extends b {
    byte[] a;
    byte[] i;

    public cf() {
        this.a = new byte[0];
        this.i = new byte[0];
        this.h = 409;
    }

    public Boolean f() {
        if (this.f < 2) {
            return Boolean.valueOf(false);
        }
        int buf_to_int16 = util.buf_to_int16(this.g, this.e + 0);
        if (this.f < buf_to_int16 + 2) {
            return Boolean.valueOf(false);
        }
        this.a = new byte[buf_to_int16];
        System.arraycopy(this.g, this.e + 2, this.a, 0, buf_to_int16);
        buf_to_int16 += 2;
        if (this.f < buf_to_int16 + 2) {
            return Boolean.valueOf(false);
        }
        int buf_to_int162 = util.buf_to_int16(this.g, this.e + buf_to_int16);
        buf_to_int16 += 2;
        if (this.f < buf_to_int16 + buf_to_int162) {
            return Boolean.valueOf(false);
        }
        this.i = new byte[buf_to_int162];
        System.arraycopy(this.g, this.e + buf_to_int16, this.i, 0, buf_to_int162);
        buf_to_int16 += buf_to_int162;
        return Boolean.valueOf(true);
    }

    public byte[] a() {
        return this.a;
    }

    public byte[] g() {
        return this.i;
    }
}
