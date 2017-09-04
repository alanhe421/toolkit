package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t105 extends tlv_t {
    int _en_pos;
    int _enlen;
    int _pic_pos;
    int _piclen;

    public tlv_t105() {
        this._piclen = 0;
        this._enlen = 0;
        this._pic_pos = 0;
        this._en_pos = 0;
        this._cmd = 261;
    }

    public boolean verify() {
        if (this._body_len < 2) {
            return false;
        }
        this._enlen = util.buf_to_int16(this._buf, this._head_len);
        if (this._body_len < (this._enlen + 2) + 2) {
            return false;
        }
        this._piclen = util.buf_to_int16(this._buf, (this._head_len + 2) + this._enlen);
        if (this._body_len < ((this._enlen + 2) + 2) + this._piclen) {
            return false;
        }
        this._en_pos = this._head_len + 2;
        this._pic_pos = ((this._enlen + 2) + 2) + this._head_len;
        return true;
    }

    public byte[] get_pic() {
        Object obj = new byte[this._piclen];
        if (this._piclen > 0) {
            System.arraycopy(this._buf, this._pic_pos, obj, 0, this._piclen);
        }
        return obj;
    }

    public byte[] get_sign() {
        Object obj = new byte[this._enlen];
        if (this._enlen > 0) {
            System.arraycopy(this._buf, this._en_pos, obj, 0, this._enlen);
        }
        return obj;
    }
}
