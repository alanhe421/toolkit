package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t116 extends tlv_t {
    int _t116_body_len;
    int _ver;

    public tlv_t116() {
        this._t116_body_len = 0;
        this._ver = 0;
        this._cmd = 278;
    }

    public byte[] get_tlv_116(int i, int i2, long[] jArr) {
        int i3 = 0;
        if (jArr == null) {
            jArr = new long[0];
        }
        this._t116_body_len = (jArr.length * 4) + 10;
        byte[] bArr = new byte[this._t116_body_len];
        util.int8_to_buf(bArr, 0, this._ver);
        util.int32_to_buf(bArr, 1, i);
        util.int32_to_buf(bArr, 5, i2);
        util.int8_to_buf(bArr, 9, jArr.length);
        int i4 = 10;
        while (i3 < jArr.length) {
            util.int32_to_buf(bArr, i4, (int) jArr[i3]);
            i4 += 4;
            i3++;
        }
        set_data(bArr, this._t116_body_len);
        return get_buf();
    }
}
