package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t502 extends tlv_t {
    public tlv_t502() {
        this._cmd = 1282;
    }

    public byte[] get_tlv_502(int i) {
        byte[] bArr = new byte[4];
        util.int32_to_buf(bArr, 0, i);
        set_data(bArr, bArr.length);
        return get_buf();
    }
}
