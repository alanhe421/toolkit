package tencent.tls.tlvs;

import com.tencent.openqq.protocol.imsdk.im_common;
import tencent.tls.tools.util;

public class tlv_t113 extends tlv_t {
    public tlv_t113() {
        this._cmd = im_common.WPA_PAIPAI;
    }

    public long get_uin() {
        return util.buf_to_int64(this._buf, this._head_len) & -1;
    }
}
