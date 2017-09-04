package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t177 */
public class bk extends b {
    int a;

    public bk() {
        this.a = 0;
        this.h = 375;
    }

    public byte[] a(long j, String str) {
        Object obj = new byte[0];
        if (str != null) {
            obj = str.getBytes();
        }
        this.a = obj.length + 7;
        Object obj2 = new byte[this.a];
        util.int8_to_buf(obj2, 0, 1);
        util.int64_to_buf32(obj2, 1, j);
        util.int16_to_buf(obj2, 5, obj.length);
        System.arraycopy(obj, 0, obj2, 7, obj.length);
        int length = obj.length + 7;
        b(this.h);
        c(obj2, this.a);
        e();
        return b();
    }
}
