package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t185 extends tlv_t {
    public tlv_t185() {
        this._cmd = 389;
    }

    public byte[] get_tlv_185(int i) {
        byte[] bArr = new byte[2];
        util.int8_to_buf(bArr, 0, 1);
        util.int8_to_buf(bArr, 1, i);
        set_data(bArr, bArr.length);
        return get_buf();
    }
}
