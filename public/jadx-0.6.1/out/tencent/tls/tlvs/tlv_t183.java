package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t183 extends tlv_t {
    long _msalt;

    public tlv_t183() {
        this._msalt = 0;
        this._cmd = 387;
    }

    public boolean verify() {
        if (this._body_len < 8) {
            return false;
        }
        this._msalt = util.buf_to_int64(this._buf, this._head_len);
        return true;
    }

    public long getMsalt() {
        return this._msalt;
    }

    public byte[] get_tlv_183(long j) {
        byte[] bArr = new byte[8];
        util.int64_to_buf(bArr, 0, j);
        set_data(bArr, bArr.length);
        return get_buf();
    }
}
