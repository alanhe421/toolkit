package oicq.wlogin_sdk.a;

import oicq.wlogin_sdk.tools.util;

/* compiled from: reg_request_resend_msg */
public class g extends c {
    public g() {
        this.b = 4;
    }

    public byte[] b(byte[] bArr, byte[] bArr2) {
        this.d = bArr.length + 1;
        Object obj = new byte[this.d];
        util.int8_to_buf(obj, 0, bArr.length);
        System.arraycopy(bArr, 0, obj, 1, bArr.length);
        int length = bArr.length + 1;
        return a(obj);
    }
}
