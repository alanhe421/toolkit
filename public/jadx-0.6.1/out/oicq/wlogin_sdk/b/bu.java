package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t183 */
public class bu extends b {
    long a;

    public bu() {
        this.a = 0;
        this.h = 387;
    }

    public Boolean f() {
        if (this.f < 8) {
            return Boolean.valueOf(false);
        }
        this.a = util.buf_to_int64(this.g, this.e);
        return Boolean.valueOf(true);
    }

    public long a() {
        return this.a;
    }

    public byte[] a(long j) {
        byte[] bArr = new byte[8];
        util.int64_to_buf(bArr, 0, j);
        b(this.h);
        c(bArr, bArr.length);
        e();
        return b();
    }
}
