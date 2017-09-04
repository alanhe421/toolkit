package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t1 */
public class c extends b {
    int a;
    int i;
    int j;
    int k;
    byte[] l;

    public c() {
        this.a = 4;
        this.i = 14;
        this.j = 1;
        this.k = 20;
        this.l = new byte[2];
        this.h = 1;
    }

    public Boolean f() {
        if (this.f < 20) {
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }

    public byte[] a(long j, byte[] bArr) {
        Object obj = new byte[this.k];
        util.int16_to_buf(obj, 0, this.j);
        util.int32_to_buf(obj, 2, util.get_rand_32());
        util.int32_to_buf(obj, 6, (int) j);
        util.int64_to_buf32(obj, 10, util.get_server_cur_time());
        System.arraycopy(bArr, 0, obj, 14, bArr.length);
        int length = bArr.length + 14;
        util.int16_to_buf(obj, length, 0);
        length += 2;
        b(this.h);
        c(obj, this.k);
        e();
        return b();
    }
}
