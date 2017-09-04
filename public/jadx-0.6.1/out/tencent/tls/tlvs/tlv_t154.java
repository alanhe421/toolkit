package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t154 extends tlv_t {
    public tlv_t154() {
        this._cmd = 340;
    }

    public byte[] get_tlv_154(int i) {
        byte[] bArr = new byte[4];
        util.int32_to_buf(bArr, 0, i);
        set_data(bArr, bArr.length);
        return get_buf();
    }
}
