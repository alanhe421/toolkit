package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t11a */
public class w extends b {
    public int a;

    public w() {
        this.a = 0;
        this.h = 282;
    }

    public Boolean f() {
        if (this.f < 5) {
            return Boolean.valueOf(false);
        }
        int buf_to_int8 = util.buf_to_int8(this.g, ((this.e + 2) + 1) + 1);
        if (this.f < buf_to_int8 + 5) {
            return Boolean.valueOf(false);
        }
        this.a = buf_to_int8;
        return Boolean.valueOf(true);
    }

    public byte[] a() {
        Object obj = new byte[2];
        System.arraycopy(this.g, this.e, obj, 0, 2);
        return obj;
    }

    public byte[] g() {
        Object obj = new byte[1];
        System.arraycopy(this.g, this.e + 2, obj, 0, 1);
        return obj;
    }

    public byte[] h() {
        Object obj = new byte[1];
        System.arraycopy(this.g, (this.e + 2) + 1, obj, 0, 1);
        return obj;
    }

    public byte[] i() {
        Object obj = new byte[this.a];
        System.arraycopy(this.g, (((this.e + 2) + 1) + 1) + 1, obj, 0, this.a);
        return obj;
    }
}
