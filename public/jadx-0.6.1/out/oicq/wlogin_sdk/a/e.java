package oicq.wlogin_sdk.a;

import oicq.wlogin_sdk.tools.util;

/* compiled from: reg_request_query_account_available */
public class e extends c {
    public e() {
        this.b = 7;
    }

    public byte[] a(int i, byte[] bArr, long j) {
        this.d = (bArr.length + 2) + 4;
        Object obj = new byte[this.d];
        util.int8_to_buf(obj, 0, i);
        util.int8_to_buf(obj, 1, bArr.length);
        System.arraycopy(bArr, 0, obj, 2, bArr.length);
        int length = bArr.length + 2;
        util.int64_to_buf32(obj, length, j);
        length += 4;
        return a(obj);
    }
}
