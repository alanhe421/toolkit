package oicq.wlogin_sdk.a;

import android.os.Build.VERSION;
import oicq.wlogin_sdk.request.u;
import oicq.wlogin_sdk.tools.util;

/* compiled from: reg_request_submit_mobile */
public class h extends c {
    public h() {
        this.b = 10;
        this.e = 2;
    }

    public byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int i2, int i3, long j, long j2, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, long j3, byte[] bArr8, byte[] bArr9, byte[] bArr10) {
        Object obj;
        Object obj2;
        String str = VERSION.RELEASE;
        if (bArr7 == null) {
            obj = new byte[0];
        }
        if (bArr10 == null) {
            obj2 = new byte[0];
        }
        this.d = ((((((((((((((((((((((((bArr.length + 1) + 1) + 1) + str.length()) + 1) + bArr2.length) + 1) + bArr3.length) + 1) + 1) + 1) + 4) + 4) + 1) + bArr5.length) + 1) + bArr6.length) + 1) + (obj.length + 2)) + 6) + 10) + (bArr8.length + 2)) + (bArr9.length + 2)) + (obj2.length + 5)) + 6;
        Object obj3 = new byte[this.d];
        util.int8_to_buf(obj3, 0, bArr.length);
        System.arraycopy(bArr, 0, obj3, 1, bArr.length);
        int length = bArr.length + 1;
        util.int8_to_buf(obj3, length, this.c);
        length++;
        util.int8_to_buf(obj3, length, str.length());
        length++;
        System.arraycopy(str.getBytes(), 0, obj3, length, str.length());
        length += str.length();
        util.int8_to_buf(obj3, length, bArr2.length);
        length++;
        System.arraycopy(bArr2, 0, obj3, length, bArr2.length);
        length += bArr2.length;
        util.int8_to_buf(obj3, length, bArr3.length);
        length++;
        System.arraycopy(bArr3, 0, obj3, length, bArr3.length);
        length += bArr3.length;
        util.int8_to_buf(obj3, length, i);
        length++;
        util.int8_to_buf(obj3, length, i2);
        length++;
        util.int8_to_buf(obj3, length, i3);
        length++;
        util.int64_to_buf32(obj3, length, j);
        length += 4;
        if (bArr4 == null || bArr4.length != 4) {
            util.int32_to_buf(obj3, length, 0);
        } else {
            System.arraycopy(bArr4, 0, obj3, length, 4);
        }
        length += 4;
        util.int8_to_buf(obj3, length, bArr5.length);
        length++;
        System.arraycopy(bArr5, 0, obj3, length, bArr5.length);
        length += bArr5.length;
        util.int8_to_buf(obj3, length, bArr6.length);
        length++;
        System.arraycopy(bArr6, 0, obj3, length, bArr6.length);
        length += bArr6.length;
        util.int8_to_buf(obj3, length, 7);
        length++;
        util.int8_to_buf(obj3, length, 1);
        length++;
        util.int8_to_buf(obj3, length, obj.length);
        length++;
        System.arraycopy(obj, 0, obj3, length, obj.length);
        length += obj.length;
        util.int8_to_buf(obj3, length, 2);
        length++;
        util.int8_to_buf(obj3, length, 8);
        length++;
        util.int64_to_buf(obj3, length, j3);
        length += 8;
        util.int8_to_buf(obj3, length, 3);
        length++;
        util.int8_to_buf(obj3, length, bArr8.length);
        length++;
        System.arraycopy(bArr8, 0, obj3, length, bArr8.length);
        length += bArr8.length;
        util.int8_to_buf(obj3, length, 4);
        length++;
        util.int8_to_buf(obj3, length, bArr9.length);
        length++;
        System.arraycopy(bArr9, 0, obj3, length, bArr9.length);
        length += bArr9.length;
        util.int8_to_buf(obj3, length, 13);
        length++;
        util.int8_to_buf(obj3, length, 4);
        length++;
        util.int64_to_buf32(obj3, length, j2);
        length += 4;
        int i4 = length + 1;
        util.int8_to_buf(obj3, length, 6);
        int i5 = i4 + 1;
        util.int8_to_buf(obj3, i4, obj2.length + 3);
        i4 = i5 + 1;
        util.int8_to_buf(obj3, i5, obj2.length == 0 ? 1 : 2);
        util.int16_to_buf(obj3, i4, obj2.length);
        length = i4 + 2;
        System.arraycopy(obj2, 0, obj3, length, obj2.length);
        length += obj2.length;
        i4 = length + 1;
        util.int8_to_buf(obj3, length, 7);
        i5 = i4 + 1;
        util.int8_to_buf(obj3, i4, 4);
        length = 1;
        if (true == u.ag) {
            length = 3;
        }
        util.int32_to_buf(obj3, i5, length);
        length = i5 + 4;
        return a(obj3);
    }
}
