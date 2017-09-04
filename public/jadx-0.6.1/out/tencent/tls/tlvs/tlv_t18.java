package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t18 extends tlv_t {
    int _ping_version;
    int _sso_version;
    int _t18_body_len;

    public tlv_t18() {
        this._t18_body_len = 26;
        this._ping_version = 1;
        this._sso_version = 1536;
        this._cmd = 24;
    }

    public byte[] get_tlv_18(long j, int i, long j2, int i2) {
        byte[] bArr = new byte[this._t18_body_len];
        util.int16_to_buf(bArr, 0, this._ping_version);
        util.int32_to_buf(bArr, 2, this._sso_version);
        util.int32_to_buf(bArr, 6, (int) j);
        util.int32_to_buf(bArr, 10, i);
        util.int64_to_buf(bArr, 14, j2);
        util.int16_to_buf(bArr, 22, i2);
        util.int16_to_buf(bArr, 24, 0);
        set_data(bArr, this._t18_body_len);
        return get_buf();
    }
}
