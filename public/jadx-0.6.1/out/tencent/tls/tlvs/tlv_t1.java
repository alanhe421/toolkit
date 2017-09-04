package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t1 extends tlv_t {
    byte[] IP_KEY;
    int _ip_len;
    int _ip_pos;
    int _ip_ver;
    int _t1_body_len;

    public tlv_t1() {
        this._ip_len = 4;
        this._ip_pos = 14;
        this._ip_ver = 1;
        this._t1_body_len = 24;
        this.IP_KEY = new byte[2];
        this._cmd = 1;
    }

    public boolean verify() {
        return this._body_len >= 24;
    }

    public byte[] get_ip() {
        Object obj = new byte[this._ip_len];
        System.arraycopy(this._buf, this._ip_pos, obj, 0, this._ip_len);
        return obj;
    }

    public byte[] get_tlv_1(long j, byte[] bArr) {
        Object obj = new byte[this._t1_body_len];
        util.int16_to_buf(obj, 0, this._ip_ver);
        util.int32_to_buf(obj, 2, util.get_rand_32());
        util.int64_to_buf(obj, 6, j);
        util.int64_to_buf32(obj, 14, util.get_server_cur_time());
        System.arraycopy(bArr, 0, obj, 18, bArr.length);
        int length = bArr.length + 18;
        util.int16_to_buf(obj, length, 0);
        length += 2;
        set_data(obj, this._t1_body_len);
        return get_buf();
    }
}
