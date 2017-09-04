package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.MD5;
import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t184 */
public class bv extends b {
    byte[] a;

    public bv() {
        this.a = new byte[0];
        this.h = 388;
    }

    public Boolean f() {
        if (this.f < 16) {
            return Boolean.valueOf(false);
        }
        this.a = new byte[16];
        System.arraycopy(this.g, this.e, this.a, 0, 16);
        return Boolean.valueOf(true);
    }

    public byte[] a(long j, String str) {
        Object toMD5Byte = MD5.toMD5Byte(str);
        int length = toMD5Byte.length;
        Object obj = new byte[(length + 8)];
        System.arraycopy(toMD5Byte, 0, obj, 0, length);
        int i = 0 + length;
        util.int64_to_buf(obj, i, j);
        i += 8;
        byte[] toMD5Byte2 = MD5.toMD5Byte(obj);
        b(this.h);
        c(toMD5Byte2, toMD5Byte2.length);
        e();
        return b();
    }
}
