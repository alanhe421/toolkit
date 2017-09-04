package tencent.tls.tlvs;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import tencent.tls.tools.util;

public class tlv_t147 extends tlv_t {
    public tlv_t147() {
        this._cmd = ErrorCode.TEST_THROWABLE_ISNOT_NULL;
    }

    public byte[] get_tlv_147(long j, byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        int limit_len = limit_len(bArr, 32);
        int limit_len2 = limit_len(bArr2, 32);
        Object obj = new byte[(((limit_len + 6) + 2) + limit_len2)];
        util.int64_to_buf32(obj, 0, j);
        util.int16_to_buf(obj, 4, limit_len);
        System.arraycopy(bArr, 0, obj, 6, limit_len);
        limit_len += 6;
        util.int16_to_buf(obj, limit_len, limit_len2);
        limit_len += 2;
        System.arraycopy(bArr2, 0, obj, limit_len, limit_len2);
        limit_len += limit_len2;
        set_data(obj, obj.length);
        return get_buf();
    }
}
