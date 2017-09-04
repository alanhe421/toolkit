package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t150 extends tlv_t {
    public int _other_len;

    public tlv_t150() {
        this._other_len = 0;
        this._cmd = 336;
    }

    public boolean verify() {
        if (this._body_len < 7) {
            return false;
        }
        int buf_to_int16 = util.buf_to_int16(this._buf, this._head_len + 5);
        if (this._body_len < buf_to_int16 + 7) {
            return false;
        }
        this._other_len = buf_to_int16;
        return true;
    }

    public int get_bitmap() {
        return util.buf_to_int32(this._buf, this._head_len);
    }

    public byte get_network() {
        return (byte) (util.buf_to_int8(this._buf, this._head_len + 4) & 255);
    }
}
