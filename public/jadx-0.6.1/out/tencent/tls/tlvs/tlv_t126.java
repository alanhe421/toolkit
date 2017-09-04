package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t126 extends tlv_t {
    int _random_len;

    public tlv_t126() {
        this._random_len = 0;
        this._cmd = 294;
    }

    public boolean verify() {
        if (this._body_len < 2 || this._body_len < 4) {
            return false;
        }
        this._random_len = util.buf_to_int16(this._buf, this._head_len + 2);
        if ((this._random_len + 2) + 2 <= this._body_len) {
            return true;
        }
        return false;
    }

    public byte[] get_random() {
        Object obj = new byte[this._random_len];
        System.arraycopy(this._buf, (this._head_len + 2) + 2, obj, 0, obj.length);
        return obj;
    }
}
