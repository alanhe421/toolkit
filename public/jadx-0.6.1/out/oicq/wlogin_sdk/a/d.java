package oicq.wlogin_sdk.a;

import oicq.wlogin_sdk.tools.MD5;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

/* compiled from: reg_request_get_account */
public class d extends c {
    public d() {
        this.b = 6;
    }

    public byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i, byte[] bArr5, byte[] bArr6, boolean z, byte[] bArr7, long j, byte[] bArr8, int i2) {
        Object b = b(bArr, bArr2, bArr3, bArr4, i, bArr5, bArr6, z, bArr7, j, bArr8, i2);
        this.d = ((bArr.length + 1) + 1) + b.length;
        Object obj = new byte[this.d];
        util.int8_to_buf(obj, 0, bArr.length);
        System.arraycopy(bArr, 0, obj, 1, bArr.length);
        int length = bArr.length + 1;
        util.int8_to_buf(obj, length, b.length);
        length++;
        System.arraycopy(b, 0, obj, length, b.length);
        length += b.length;
        return a(obj);
    }

    public byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i, byte[] bArr5, byte[] bArr6, boolean z, byte[] bArr7, long j, byte[] bArr8, int i2) {
        Object obj;
        byte[] bytes;
        if (bArr6 == null) {
            bArr6 = new byte[0];
        }
        if (bArr4 == null) {
            bArr4 = new byte[0];
        }
        if (bArr7 == null) {
            Object obj2 = new byte[0];
        }
        int i3 = 5;
        if (z) {
            i3 = 10;
            obj = new byte[(((((((((((((((((((((((((bArr.length + 1) + 1) + bArr3.length) + 1) + 1) + 2) + bArr5.length) + 2) + bArr6.length) + 2) + bArr4.length) + 2) + 1) + 2) + 1) + 2) + obj2.length) + 2) + bArr3.length) + 2) + 4) + 2) + bArr8.length) + 2) + 4)];
        } else {
            obj = new byte[(((((((((((((((bArr.length + 1) + 1) + bArr3.length) + 1) + 1) + 2) + bArr5.length) + 2) + bArr6.length) + 2) + bArr4.length) + 2) + 1) + 2) + 4)];
        }
        util.int8_to_buf(obj, 0, bArr.length);
        System.arraycopy(bArr, 0, obj, 1, bArr.length);
        int length = bArr.length + 1;
        util.int8_to_buf(obj, length, bArr3.length);
        length++;
        System.arraycopy(bArr3, 0, obj, length, bArr3.length);
        length += bArr3.length;
        util.int8_to_buf(obj, length, i);
        length++;
        util.int8_to_buf(obj, length, i3);
        i3 = length + 1;
        util.int8_to_buf(obj, i3, 1);
        i3++;
        util.int8_to_buf(obj, i3, bArr5.length);
        i3++;
        System.arraycopy(bArr5, 0, obj, i3, bArr5.length);
        i3 += bArr5.length;
        util.int8_to_buf(obj, i3, 5);
        i3++;
        util.int8_to_buf(obj, i3, bArr6.length);
        i3++;
        System.arraycopy(bArr6, 0, obj, i3, bArr6.length);
        i3 += bArr6.length;
        util.int8_to_buf(obj, i3, 6);
        i3++;
        util.int8_to_buf(obj, i3, bArr4.length);
        i3++;
        System.arraycopy(bArr4, 0, obj, i3, bArr4.length);
        i3 += bArr4.length;
        util.int8_to_buf(obj, i3, 8);
        i3++;
        util.int8_to_buf(obj, i3, 1);
        i3++;
        util.int8_to_buf(obj, i3, 1);
        i3++;
        util.int8_to_buf(obj, i3, 15);
        i3++;
        util.int8_to_buf(obj, i3, 4);
        i3++;
        util.int32_to_buf(obj, i3, i2);
        i3 += 4;
        if (z) {
            util.int8_to_buf(obj, i3, 9);
            i3++;
            util.int8_to_buf(obj, i3, 1);
            i3++;
            util.int8_to_buf(obj, i3, 1);
            i3++;
            util.int8_to_buf(obj, i3, 10);
            i3++;
            util.int8_to_buf(obj, i3, obj2.length);
            i3++;
            System.arraycopy(obj2, 0, obj, i3, obj2.length);
            i3 += obj2.length;
            util.int8_to_buf(obj, i3, 11);
            i3++;
            util.int8_to_buf(obj, i3, bArr3.length);
            i3++;
            System.arraycopy(bArr3, 0, obj, i3, bArr3.length);
            i3 += bArr3.length;
            util.int8_to_buf(obj, i3, 13);
            i3++;
            util.int8_to_buf(obj, i3, 4);
            i3++;
            util.int64_to_buf32(obj, i3, j);
            i3 += 4;
            util.int8_to_buf(obj, i3, 14);
            i3++;
            util.int8_to_buf(obj, i3, bArr8.length);
            i3++;
            System.arraycopy(bArr8, 0, obj, i3, bArr8.length);
            i3 += bArr8.length;
        }
        if (bArr2 == null || bArr2.length <= 0) {
            bytes = j.a.getBytes();
        } else {
            bytes = MD5.toMD5Byte(bArr2);
        }
        return cryptor.encrypt(obj, 0, obj.length, bytes);
    }
}
