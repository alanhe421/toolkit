package oicq.wlogin_sdk.code2d;

import java.util.ArrayList;
import oicq.wlogin_sdk.request.oicq_request;
import oicq.wlogin_sdk.tools.util;

/* compiled from: query_result */
public class d extends b {
    public d() {
        this._cmd = 18;
    }

    public byte[] a(long j, long j2, byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[((((((bArr.length + 8) + 8) + 1) + 2) + bArr2.length) + 2)];
        util.int64_to_buf32(bArr3, 2, j2);
        int fill_staff = fill_staff(bArr3, bArr, 6);
        util.int64_to_buf(bArr3, fill_staff, j);
        fill_staff += 8;
        util.int8_to_buf(bArr3, fill_staff, 8);
        fill_staff++;
        return get_request(j, true, bArr3);
    }

    public int a(byte[] bArr) {
        byte[] bArr2 = null;
        Object obj = get_response(bArr, 0);
        if (obj == null || obj.length < 8) {
            return -1009;
        }
        _status.h = (long) util.buf_to_int32(obj, 2);
        _status.b = util.buf_to_int8(obj, 6);
        if (_status.b != 0) {
            return _status.b;
        }
        _status.a = util.buf_to_int64(obj, 7);
        _status.c = (long) util.buf_to_int32(obj, 15);
        _status.e = new ArrayList();
        int buf_to_int16 = util.buf_to_int16(obj, 19);
        int i = 21;
        byte[] bArr3 = null;
        byte[] bArr4 = null;
        for (int i2 = 0; i2 < buf_to_int16; i2++) {
            int buf_to_int162 = util.buf_to_int16(obj, i);
            i += 2;
            int buf_to_int163 = util.buf_to_int16(obj, i);
            i += 2;
            switch (buf_to_int162) {
                case 24:
                    bArr4 = new byte[buf_to_int163];
                    System.arraycopy(obj, i, bArr4, 0, buf_to_int163);
                    i += buf_to_int163;
                    break;
                case 25:
                    bArr2 = new byte[buf_to_int163];
                    System.arraycopy(obj, i, bArr2, 0, buf_to_int163);
                    i += buf_to_int163;
                    break;
                case 30:
                    bArr3 = new byte[buf_to_int163];
                    System.arraycopy(obj, i, bArr3, 0, buf_to_int163);
                    i += buf_to_int163;
                    break;
                default:
                    Object obj2 = new byte[(buf_to_int163 + 4)];
                    System.arraycopy(obj, i - 4, obj2, 0, obj2.length);
                    _status.e.add(obj2);
                    i += buf_to_int163;
                    break;
            }
        }
        if (bArr4 == null || bArr3 == null || bArr2 == null) {
            return -1009;
        }
        c.q = oicq_request.b(bArr4, bArr3);
        c.r = bArr2;
        return _status.b;
    }
}
