package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t107 extends tlv_t {
    int _t107_body_len;

    public tlv_t107() {
        this._t107_body_len = 6;
        this._cmd = 263;
    }

    public byte[] get_tlv_107(int i, int i2, int i3, int i4) {
        byte[] bArr = new byte[this._t107_body_len];
        util.int16_to_buf(bArr, 0, i);
        util.int8_to_buf(bArr, 2, i2);
        util.int16_to_buf(bArr, 3, i3);
        util.int8_to_buf(bArr, 5, i4);
        set_data(bArr, this._t107_body_len);
        return get_buf();
    }
}
