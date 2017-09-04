package oicq.wlogin_sdk.devicelock;

import oicq.wlogin_sdk.devicelock.DevlockBase.a;
import oicq.wlogin_sdk.tools.util;

/* compiled from: DevlockVerifySMS */
public class f extends DevlockBase {
    public f() {
        this._msgType = a.c;
    }

    public byte[] a(long j, long j2, long j3, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7) {
        TLV_QuerySig tLV_QuerySig = rst.querySig;
        if (tLV_QuerySig.get_size() == 0) {
            tLV_QuerySig.set_data(new byte[0], 0);
        }
        Object a = new g().a(j2, j3, bArr, bArr2, bArr3, bArr4, bArr5, bArr6);
        if (a == null || a.length == 0) {
            return new byte[0];
        }
        Object obj = null;
        if (bArr7 != null && bArr7.length > 0) {
            obj = new n().a(bArr7);
        } else if (rst.sppKey != null && rst.sppKey.get_data_len() > 0) {
            obj = rst.sppKey.get_buf();
        }
        if (obj == null || obj.length == 0) {
            return new byte[0];
        }
        int i = tLV_QuerySig.get_size();
        int length = a.length;
        int length2 = obj.length;
        Object obj2 = new byte[(((i + 2) + length) + length2)];
        util.int16_to_buf(obj2, 0, 3);
        System.arraycopy(tLV_QuerySig.get_buf(), 0, obj2, 2, i);
        int i2 = i + 2;
        System.arraycopy(a, 0, obj2, i2, length);
        int i3 = i2 + length;
        System.arraycopy(obj, 0, obj2, i3, length2);
        int i4 = i3 + length2;
        return _get_request(j, j2, obj2);
    }
}
