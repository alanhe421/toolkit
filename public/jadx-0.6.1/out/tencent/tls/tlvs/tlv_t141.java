package tencent.tls.tlvs;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import tencent.tls.tools.util;

public class tlv_t141 extends tlv_t {
    int _version;

    public tlv_t141() {
        this._version = 1;
        this._cmd = ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01;
    }

    public byte[] get_tlv_141(byte[] bArr, int i, byte[] bArr2) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        int length = (((bArr.length + 4) + 2) + 2) + bArr2.length;
        Object obj = new byte[length];
        util.int16_to_buf(obj, 0, this._version);
        util.int16_to_buf(obj, 2, bArr.length);
        System.arraycopy(bArr, 0, obj, 4, bArr.length);
        int length2 = bArr.length + 4;
        util.int16_to_buf(obj, length2, i);
        length2 += 2;
        util.int16_to_buf(obj, length2, bArr2.length);
        length2 += 2;
        System.arraycopy(bArr2, 0, obj, length2, bArr2.length);
        length2 += bArr2.length;
        set_data(obj, length);
        return get_buf();
    }
}
