package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t2 extends tlv_t {
    int _sigVer;
    int _t2_body_len;

    public tlv_t2() {
        this._t2_body_len = 0;
        this._sigVer = 0;
        this._cmd = 2;
    }

    public byte[] get_tlv_2(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        this._t2_body_len = (bArr.length + 6) + bArr2.length;
        Object obj = new byte[this._t2_body_len];
        util.int16_to_buf(obj, 0, this._sigVer);
        util.int16_to_buf(obj, 2, bArr.length);
        System.arraycopy(bArr, 0, obj, 4, bArr.length);
        int length = bArr.length + 4;
        util.int16_to_buf(obj, length, bArr2.length);
        length += 2;
        System.arraycopy(bArr2, 0, obj, length, bArr2.length);
        length += bArr2.length;
        set_data(obj, this._t2_body_len);
        return get_buf();
    }
}
