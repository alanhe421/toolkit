package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t167 */
public class bb extends b {
    public int a;

    public bb() {
        this.a = 0;
        this.h = 359;
    }

    public Boolean f() {
        if (this.f < 4) {
            return Boolean.valueOf(false);
        }
        int buf_to_int16 = util.buf_to_int16(this.g, (this.e + 1) + 1);
        if (this.f < buf_to_int16 + 4) {
            return Boolean.valueOf(false);
        }
        this.a = buf_to_int16;
        return Boolean.valueOf(true);
    }

    public byte[] a() {
        Object obj = new byte[1];
        System.arraycopy(this.g, this.e, obj, 0, 1);
        return obj;
    }

    public byte[] g() {
        Object obj = new byte[1];
        System.arraycopy(this.g, this.e + 1, obj, 0, 1);
        return obj;
    }

    public byte[] h() {
        Object obj = new byte[this.a];
        System.arraycopy(this.g, ((this.e + 1) + 1) + 2, obj, 0, this.a);
        return obj;
    }
}
