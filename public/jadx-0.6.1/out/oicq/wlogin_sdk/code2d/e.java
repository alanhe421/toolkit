package oicq.wlogin_sdk.code2d;

import java.util.ArrayList;
import oicq.wlogin_sdk.tools.util;

/* compiled from: verify_code */
public class e extends b {
    public e() {
        this._cmd = 19;
    }

    public byte[] a(long j, long j2, boolean z, byte[] bArr, int[] iArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i, byte[] bArr5) {
        Object obj;
        if (bArr == null) {
            bArr = new byte[0];
        }
        if (iArr == null) {
            iArr = new int[0];
        }
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        if (bArr3 == null) {
            bArr3 = new byte[0];
        }
        if (bArr4 == null) {
            bArr4 = new byte[0];
        }
        if (bArr5 == null) {
            Object obj2 = new byte[0];
        }
        if (obj2 == null || obj2.length <= 0) {
            obj = new byte[((((((((((((bArr.length + 16) + 2) + bArr2.length) + 16) + 1) + 2) + 1) + 2) + (iArr.length * 2)) + 2) + 4) + bArr4.length)];
        } else {
            obj = new byte[((((((((((((((bArr.length + 16) + 2) + bArr2.length) + 16) + 1) + 2) + 1) + 2) + (iArr.length * 2)) + 2) + 4) + bArr4.length) + 4) + obj2.length)];
        }
        util.int64_to_buf32(obj, 2, j2);
        util.int64_to_buf(obj, 6, j);
        util.int16_to_buf(obj, 14, bArr.length);
        System.arraycopy(bArr, 0, obj, 16, bArr.length);
        int length = bArr.length + 16;
        util.int16_to_buf(obj, length, bArr2.length);
        length += 2;
        System.arraycopy(bArr2, 0, obj, length, bArr2.length);
        length += bArr2.length;
        if (bArr3 == null || bArr3.length != 16) {
            length += 16;
        } else {
            System.arraycopy(bArr3, 0, obj, length, 16);
            length += 16;
        }
        util.int8_to_buf(obj, length, 1);
        length++;
        util.int16_to_buf(obj, length, i);
        length += 2;
        util.int8_to_buf(obj, length, 3);
        length++;
        util.int16_to_buf(obj, length, iArr.length);
        int i2 = length + 2;
        for (int int16_to_buf : iArr) {
            util.int16_to_buf(obj, i2, int16_to_buf);
            i2 += 2;
        }
        length = 1;
        if (obj2 != null && obj2.length > 0) {
            length = 2;
        }
        util.int16_to_buf(obj, i2, length);
        length = i2 + 2;
        util.int16_to_buf(obj, length, 9);
        length += 2;
        util.int16_to_buf(obj, length, bArr4.length);
        length += 2;
        System.arraycopy(bArr4, 0, obj, length, bArr4.length);
        length += bArr4.length;
        if (obj2 != null && obj2.length > 0) {
            util.int16_to_buf(obj, length, 12);
            length += 2;
            util.int16_to_buf(obj, length, obj2.length);
            length += 2;
            System.arraycopy(obj2, 0, obj, length, obj2.length);
            length += obj2.length;
        }
        return get_request(j, z, obj);
    }

    public int a(byte[] bArr) {
        Object obj = get_response(bArr, 0);
        if (obj == null || obj.length < 11) {
            return -1009;
        }
        _status.a = util.buf_to_int64(obj, 2);
        _status.b = util.buf_to_int8(obj, 10) & 255;
        if (_status.b != 0) {
            int buf_to_int16 = util.buf_to_int16(obj, 11);
            _status.f = new byte[buf_to_int16];
            System.arraycopy(obj, 13, _status.f, 0, buf_to_int16);
            buf_to_int16 += 13;
            return _status.b;
        }
        _status.c = ((long) util.buf_to_int32(obj, 11)) & 4294967295L;
        buf_to_int16 = util.buf_to_int16(obj, 15);
        _status.d = new byte[buf_to_int16];
        System.arraycopy(obj, 17, _status.d, 0, buf_to_int16);
        buf_to_int16 += 17;
        int buf_to_int162 = util.buf_to_int16(obj, buf_to_int16);
        buf_to_int16 += 2;
        _status.g = new byte[0];
        _status.e = new ArrayList(buf_to_int162);
        int i = buf_to_int16;
        for (int i2 = 0; i2 < buf_to_int162; i2++) {
            buf_to_int16 = util.buf_to_int16(obj, i);
            int buf_to_int163 = util.buf_to_int16(obj, i + 2);
            if (buf_to_int16 == 13) {
                _status.g = new byte[buf_to_int163];
                System.arraycopy(obj, i + 4, _status.g, 0, buf_to_int163);
            } else if (buf_to_int16 == 26) {
                c.s = util.buf_to_int8(obj, i + 4) == 1;
            } else {
                Object obj2 = new byte[(buf_to_int163 + 4)];
                System.arraycopy(obj, i, obj2, 0, buf_to_int163 + 4);
                _status.e.add(obj2);
            }
            i += buf_to_int163 + 4;
        }
        return _status.b;
    }
}
