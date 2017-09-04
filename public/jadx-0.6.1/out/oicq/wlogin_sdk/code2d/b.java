package oicq.wlogin_sdk.code2d;

import oicq.wlogin_sdk.request.u;
import oicq.wlogin_sdk.tools.util;

/* compiled from: code2d_base */
public class b {
    public static int _seq = 0;
    public static c _status = new c();
    public static int _version = 50;
    public int _cmd = 0;
    public int _head_len = 43;
    public int _role = 114;
    public int _sub_cmd = 0;

    public int get_cmd() {
        return this._cmd;
    }

    protected byte[] get_request(long j, boolean z, byte[] bArr) {
        Object obj = new byte[((this._head_len + bArr.length) + 1)];
        util.int8_to_buf(obj, 0, 2);
        util.int16_to_buf(obj, 1, obj.length);
        util.int16_to_buf(obj, 3, this._cmd);
        util.int8_to_buf(obj, 26, 3);
        if (z) {
            util.int16_to_buf(obj, 27, 0);
        } else {
            util.int16_to_buf(obj, 27, 1);
        }
        util.int16_to_buf(obj, 29, _version);
        int i = _seq;
        _seq = i + 1;
        util.int32_to_buf(obj, 31, i);
        util.int64_to_buf(obj, 35, j);
        System.arraycopy(bArr, 0, obj, 43, bArr.length);
        int length = bArr.length + 43;
        util.int8_to_buf(obj, length, 3);
        length++;
        return obj;
    }

    protected byte[] get_response(byte[] bArr, int i) {
        if (bArr.length <= this._head_len) {
            return null;
        }
        byte[] bArr2 = new byte[(bArr.length - this._head_len)];
        System.arraycopy(bArr, this._head_len, bArr2, 0, bArr2.length);
        return bArr2;
    }

    protected int fill_staff(byte[] bArr, byte[] bArr2, int i) {
        util.int16_to_buf(bArr, i, bArr2.length);
        int i2 = i + 2;
        System.arraycopy(bArr2, 0, bArr, i2, bArr2.length);
        return i2 + bArr2.length;
    }

    protected byte[] getAppInfo(long j, long j2) {
        Object obj = new byte[(28 + (((((u.E.length + 2) + 2) + u.G.length) + 2) + u.H.length))];
        util.int64_to_buf32(obj, 0, 5);
        util.int64_to_buf32(obj, 4, j);
        util.int64_to_buf32(obj, 8, j2);
        System.arraycopy(u.A, 0, obj, 12, u.A.length);
        fill_staff(obj, u.H, fill_staff(obj, u.G, fill_staff(obj, u.E, u.A.length + 12)));
        return obj;
    }
}
