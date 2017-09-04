package tencent.tls.tlvs;

import tencent.tls.tools.util;

public class tlv_t124 extends tlv_t {
    int _t124_body_len;

    public tlv_t124() {
        this._t124_body_len = 0;
        this._cmd = 292;
    }

    public byte[] get_tlv_124(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        if (bArr3 == null) {
            bArr3 = new byte[0];
        }
        if (bArr4 == null) {
            bArr4 = new byte[0];
        }
        if (bArr5 == null) {
            bArr5 = new byte[0];
        }
        int limit_len = limit_len(bArr, 16);
        int limit_len2 = limit_len(bArr2, 16);
        int limit_len3 = limit_len(bArr3, 16);
        int limit_len4 = limit_len(bArr4, 32);
        int limit_len5 = limit_len(bArr5, 16);
        this._t124_body_len = (((((((((limit_len + 2) + 2) + limit_len2) + 2) + 2) + limit_len3) + 2) + limit_len4) + 2) + limit_len5;
        Object obj = new byte[this._t124_body_len];
        util.int16_to_buf(obj, 0, limit_len);
        System.arraycopy(bArr, 0, obj, 2, limit_len);
        limit_len += 2;
        util.int16_to_buf(obj, limit_len, limit_len2);
        limit_len += 2;
        System.arraycopy(bArr2, 0, obj, limit_len, limit_len2);
        limit_len += limit_len2;
        util.int16_to_buf(obj, limit_len, i);
        limit_len += 2;
        util.int16_to_buf(obj, limit_len, limit_len3);
        limit_len += 2;
        System.arraycopy(bArr3, 0, obj, limit_len, limit_len3);
        limit_len += limit_len3;
        util.int16_to_buf(obj, limit_len, limit_len4);
        limit_len += 2;
        System.arraycopy(bArr4, 0, obj, limit_len, limit_len4);
        limit_len += limit_len4;
        util.int16_to_buf(obj, limit_len, limit_len5);
        limit_len += 2;
        System.arraycopy(bArr5, 0, obj, limit_len, limit_len5);
        limit_len += limit_len5;
        set_data(obj, this._t124_body_len);
        return get_buf();
    }
}
