package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t177 extends tlv_t {
    int _t177_body_len;

    public tlv_t177() {
        this._t177_body_len = 0;
        this._cmd = 375;
    }

    public byte[] get_tlv_177(long j, String str) {
        Object obj = new byte[0];
        if (str != null) {
            obj = str.getBytes();
        }
        this._t177_body_len = obj.length + 7;
        Object obj2 = new byte[this._t177_body_len];
        util.int8_to_buf(obj2, 0, 1);
        util.int64_to_buf32(obj2, 1, j);
        util.int16_to_buf(obj2, 5, obj.length);
        System.arraycopy(obj, 0, obj2, 7, obj.length);
        int length = obj.length + 7;
        set_data(obj2, this._t177_body_len);
        return get_buf();
    }
}
