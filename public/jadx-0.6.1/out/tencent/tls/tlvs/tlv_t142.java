package tencent.tls.tlvs;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import tencent.tls.tools.util;

public class tlv_t142 extends tlv_t {
    int _t142_body_len;
    int _version;

    public tlv_t142() {
        this._version = 0;
        this._t142_body_len = 0;
        this._cmd = ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_02;
    }

    public byte[] get_tlv_142(byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        int limit_len = limit_len(bArr, 32);
        this._t142_body_len = limit_len + 4;
        Object obj = new byte[this._t142_body_len];
        util.int16_to_buf(obj, 0, this._version);
        util.int16_to_buf(obj, 2, limit_len);
        System.arraycopy(bArr, 0, obj, 4, limit_len);
        limit_len += 4;
        set_data(obj, obj.length);
        return get_buf();
    }
}
