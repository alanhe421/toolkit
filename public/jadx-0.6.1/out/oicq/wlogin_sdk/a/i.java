package oicq.wlogin_sdk.a;

import oicq.wlogin_sdk.tools.util;

/* compiled from: reg_request_submit_msg_chk */
public class i extends c {
    public i() {
        this.b = 5;
    }

    public byte[] b(byte[] bArr, byte[] bArr2) {
        Object a = a(bArr, bArr2);
        this.d = ((bArr.length + 1) + 1) + a.length;
        Object obj = new byte[this.d];
        util.int8_to_buf(obj, 0, bArr.length);
        System.arraycopy(bArr, 0, obj, 1, bArr.length);
        int length = bArr.length + 1;
        util.int8_to_buf(obj, length, a.length);
        length++;
        System.arraycopy(a, 0, obj, length, a.length);
        int length2 = a.length + length;
        return a(obj);
    }
}
