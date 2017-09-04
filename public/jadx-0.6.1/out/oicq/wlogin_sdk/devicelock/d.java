package oicq.wlogin_sdk.devicelock;

import oicq.wlogin_sdk.devicelock.DevlockBase.a;
import oicq.wlogin_sdk.tools.util;

/* compiled from: DevlockSendSMS */
public class d extends DevlockBase {
    public d() {
        this._msgType = a.b;
    }

    public byte[] a(long j, long j2, long j3) {
        TLV_QuerySig tLV_QuerySig = rst.querySig;
        l lVar = new l();
        lVar.a(j3);
        int i = tLV_QuerySig.get_size();
        int i2 = lVar.get_size();
        if (i == 0 || i2 == 0) {
            return new byte[0];
        }
        Object obj = new byte[((i + 2) + i2)];
        util.int16_to_buf(obj, 0, 2);
        System.arraycopy(tLV_QuerySig.get_buf(), 0, obj, 2, i);
        System.arraycopy(lVar.get_buf(), 0, obj, i + 2, i2);
        return _get_request(j, j2, obj);
    }
}
