package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t127 extends tlv_t {
    int _t127_body_len;
    int _version;

    public tlv_t127() {
        this._t127_body_len = 0;
        this._version = 0;
        this._cmd = 295;
    }

    public byte[] get_tlv_127(byte[] bArr, byte[] bArr2) {
        this._t127_body_len = ((bArr.length + 4) + 2) + bArr2.length;
        Object obj = new byte[this._t127_body_len];
        util.int16_to_buf(obj, 0, this._version);
        util.int16_to_buf(obj, 2, bArr.length);
        System.arraycopy(bArr, 0, obj, 4, bArr.length);
        int length = bArr.length + 4;
        util.int16_to_buf(obj, length, bArr2.length);
        length += 2;
        System.arraycopy(bArr2, 0, obj, length, bArr2.length);
        length += bArr2.length;
        set_data(obj, obj.length);
        return get_buf();
    }
}
