package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.MD5;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t106 */
public class i extends b {
    int a;
    int i;
    int j;

    public i() {
        this.a = 4;
        this.i = 5;
        this.j = 98;
        this.h = 262;
    }

    public byte[] a(long j, long j2, int i, long j3, byte[] bArr, byte[] bArr2, int i2, byte[] bArr3, long j4, byte[] bArr4, byte[] bArr5, int i3, byte[] bArr6, int i4) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        if (bArr == null) {
            obj = new byte[0];
        }
        if (bArr2 == null) {
            obj2 = new byte[0];
        }
        if (bArr3 == null) {
            obj3 = new byte[0];
        }
        if (bArr5 == null) {
            obj4 = new byte[0];
        }
        if (bArr6 == null) {
            Object obj6 = new byte[0];
        }
        if (bArr4 == null) {
            obj5 = new byte[0];
        }
        this.j += obj5.length + 2;
        Object obj7 = new byte[this.j];
        util.int16_to_buf(obj7, 0, this.a);
        util.int32_to_buf(obj7, 2, util.get_rand_32());
        util.int32_to_buf(obj7, 6, this.i);
        util.int32_to_buf(obj7, 10, (int) j);
        util.int32_to_buf(obj7, 14, i);
        util.int64_to_buf(obj7, 18, j3 == 0 ? j4 : j3);
        System.arraycopy(obj, 0, obj7, 26, obj.length);
        int length = obj.length + 26;
        System.arraycopy(obj2, 0, obj7, length, obj2.length);
        length += obj2.length;
        util.int8_to_buf(obj7, length, i2);
        length++;
        System.arraycopy(obj3, 0, obj7, length, obj3.length);
        length += obj3.length;
        System.arraycopy(obj4, 0, obj7, length, obj4.length);
        length += obj4.length;
        util.int32_to_buf(obj7, length, 0);
        length += 4;
        util.int8_to_buf(obj7, length, i3);
        length++;
        if (obj6.length == 0) {
            byte[] bArr7 = new byte[16];
            util.int32_to_buf(bArr7, 0, util.get_rand_32());
            util.int32_to_buf(bArr7, 4, util.get_rand_32());
            util.int32_to_buf(bArr7, 8, util.get_rand_32());
            util.int32_to_buf(bArr7, 12, util.get_rand_32());
            length += bArr7.length;
        } else {
            System.arraycopy(obj6, 0, obj7, length, obj6.length);
            length += obj6.length;
        }
        util.int64_to_buf32(obj7, length, j2);
        length += 4;
        util.int32_to_buf(obj7, length, i4);
        length += 4;
        util.int16_to_buf(obj7, length, obj5.length);
        System.arraycopy(obj5, 0, obj7, length + 2, obj5.length);
        byte[] bArr8 = new byte[24];
        System.arraycopy(obj3, 0, bArr8, 0, obj3.length);
        if (j4 == 0) {
            util.int64_to_buf(bArr8, 16, j3);
        } else {
            util.int64_to_buf(bArr8, 16, j4);
        }
        bArr8 = cryptor.encrypt(obj7, 0, obj7.length, MD5.toMD5Byte(bArr8));
        this.j = bArr8.length;
        b(this.h);
        c(bArr8, this.j);
        e();
        return b();
    }
}
