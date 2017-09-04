package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t505 extends tlv_t {
    int expire;
    int reask;

    public tlv_t505() {
        this.reask = 0;
        this.expire = 0;
        this._cmd = 1285;
    }

    public boolean verify() {
        if (this._body_len < 4) {
            return false;
        }
        this.reask = util.buf_to_int16(this._buf, this._head_len);
        this.expire = util.buf_to_int16(this._buf, this._head_len + 2);
        return true;
    }

    public int getReask() {
        return this.reask;
    }

    public int getExpire() {
        return this.expire;
    }
}
