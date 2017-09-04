package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t11d */
public class y extends b {
    int a;

    public y() {
        this.a = 0;
        this.h = 285;
    }

    public Boolean f() {
        if (this.f < 22) {
            return Boolean.valueOf(false);
        }
        this.a = util.buf_to_int16(this.g, (this.e + 4) + 16);
        this.a &= 65535;
        if (this.f < this.a + 22) {
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }

    public long a() {
        return ((long) util.buf_to_int32(this.g, this.e)) & 4294967295L;
    }

    public byte[] g() {
        Object obj = new byte[16];
        System.arraycopy(this.g, this.e + 4, obj, 0, obj.length);
        return obj;
    }

    public byte[] h() {
        Object obj = new byte[this.a];
        System.arraycopy(this.g, ((this.e + 4) + 16) + 2, obj, 0, obj.length);
        return obj;
    }
}
