package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t400 */
public class cl extends b {
    int a;
    int i;

    public cl() {
        this.a = 1;
        this.i = 0;
        this.h = 1024;
    }

    public byte[] a(byte[] bArr, long j, byte[] bArr2, byte[] bArr3, long j2, long j3, byte[] bArr4) {
        Object obj;
        if (bArr == null) {
            bArr = new byte[16];
        }
        if (bArr2 == null) {
            bArr2 = new byte[16];
        }
        if (bArr3 == null) {
            bArr3 = new byte[16];
        }
        if (bArr4 == null) {
            obj = new byte[8];
        }
        this.i = (((((bArr2.length + 10) + bArr3.length) + 4) + 4) + 4) + obj.length;
        Object obj2 = new byte[this.i];
        util.int16_to_buf(obj2, 0, this.a);
        util.int64_to_buf(obj2, 2, j);
        System.arraycopy(bArr2, 0, obj2, 10, bArr2.length);
        int length = bArr2.length + 10;
        System.arraycopy(bArr3, 0, obj2, length, bArr3.length);
        length += bArr3.length;
        util.int32_to_buf(obj2, length, (int) j2);
        length += 4;
        util.int32_to_buf(obj2, length, (int) j3);
        length += 4;
        util.int64_to_buf32(obj2, length, util.get_server_cur_time());
        length += 4;
        System.arraycopy(obj, 0, obj2, length, obj.length);
        length += obj.length;
        byte[] encrypt = cryptor.encrypt(obj2, 0, obj2.length, bArr);
        this.i = encrypt.length;
        b(this.h);
        c(encrypt, encrypt.length);
        e();
        return b();
    }
}
