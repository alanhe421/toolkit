package oicq.wlogin_sdk.devicelock;

import oicq.wlogin_sdk.tools.util;

/* compiled from: DevlockClose */
public class b extends DevlockBase {
    public b() {
        this._msgType = 12;
    }

    public byte[] a(long j, long j2, long j3, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
        Object a = new g().a(j2, j3, bArr, bArr2, bArr3, bArr4, bArr5, bArr6);
        if (a == null || a.length == 0) {
            return new byte[0];
        }
        Object obj = new byte[(a.length + 2)];
        util.int16_to_buf(obj, 0, 1);
        System.arraycopy(a, 0, obj, 2, a.length);
        return _get_request(j, j2, obj);
    }
}
