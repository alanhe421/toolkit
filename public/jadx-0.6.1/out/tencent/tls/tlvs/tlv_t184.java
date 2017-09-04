package tencent.tls.tlvs;

import tencent.tls.tools.MD5;
import tencent.tls.tools.util;

public class tlv_t184 extends tlv_t {
    byte[] _mS2;

    public tlv_t184() {
        this._mS2 = new byte[0];
        this._cmd = 388;
    }

    public boolean verify() {
        if (this._body_len < 16) {
            return false;
        }
        this._mS2 = new byte[16];
        System.arraycopy(this._buf, this._head_len, this._mS2, 0, 16);
        return true;
    }

    public byte[] getMS2() {
        return this._mS2;
    }

    public byte[] get_tlv_184(long j, String str) {
        Object toMD5Byte = MD5.toMD5Byte(str);
        int length = toMD5Byte.length;
        Object obj = new byte[(length + 8)];
        System.arraycopy(toMD5Byte, 0, obj, 0, length);
        int i = 0 + length;
        util.int64_to_buf(obj, i, j);
        i += 8;
        byte[] toMD5Byte2 = MD5.toMD5Byte(obj);
        set_data(toMD5Byte2, toMD5Byte2.length);
        return get_buf();
    }
}
