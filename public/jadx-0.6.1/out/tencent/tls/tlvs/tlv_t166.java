package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t166 extends tlv_t {
    public tlv_t166() {
        this._cmd = 358;
    }

    public byte[] get_tlv_166(int i) {
        byte[] bArr = new byte[1];
        util.int8_to_buf(bArr, 0, i);
        set_data(bArr, bArr.length);
        return get_buf();
    }
}
