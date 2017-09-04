package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t11f extends tlv_t {
    public tlv_t11f() {
        this._cmd = 287;
    }

    public boolean verify() {
        return this._body_len >= 8;
    }

    public int get_chg_time() {
        return util.buf_to_int32(this._buf, this._head_len);
    }

    public int get_tk_pri() {
        return util.buf_to_int32(this._buf, this._head_len + 4);
    }
}
