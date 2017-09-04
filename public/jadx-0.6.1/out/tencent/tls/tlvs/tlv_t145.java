package tencent.tls.tlvs;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;

public class tlv_t145 extends tlv_t {
    public tlv_t145() {
        this._cmd = ErrorCode.THROWABLE_INITX5CORE;
    }

    public byte[] get_tlv_145(byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        set_data(bArr, bArr.length);
        return get_buf();
    }
}
