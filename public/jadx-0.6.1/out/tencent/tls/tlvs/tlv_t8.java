package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t8 extends tlv_t {
    int _t8_body_len;

    public tlv_t8() {
        this._t8_body_len = 0;
        this._cmd = 8;
    }

    public byte[] get_tlv_8(int i, int i2, int i3) {
        this._t8_body_len = 8;
        byte[] bArr = new byte[this._t8_body_len];
        util.int16_to_buf(bArr, 0, i);
        util.int32_to_buf(bArr, 2, i2);
        util.int16_to_buf(bArr, 6, i3);
        set_data(bArr, this._t8_body_len);
        return get_buf();
    }
}
