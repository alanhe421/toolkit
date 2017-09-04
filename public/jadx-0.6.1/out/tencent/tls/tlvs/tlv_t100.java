package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t100 extends tlv_t {
    int _db_buf_ver;
    int _sso_ver;
    int _t100_body_len;

    public tlv_t100() {
        this._db_buf_ver = 1;
        this._sso_ver = 1;
        this._t100_body_len = 22;
        this._cmd = 256;
    }

    public byte[] get_tlv_100(long j, long j2, int i, int i2) {
        byte[] bArr = new byte[this._t100_body_len];
        util.int16_to_buf(bArr, 0, this._db_buf_ver);
        util.int32_to_buf(bArr, 2, this._sso_ver);
        util.int32_to_buf(bArr, 6, (int) j);
        util.int32_to_buf(bArr, 10, (int) j2);
        util.int32_to_buf(bArr, 14, i);
        util.int32_to_buf(bArr, 18, i2);
        set_data(bArr, this._t100_body_len);
        return get_buf();
    }
}
