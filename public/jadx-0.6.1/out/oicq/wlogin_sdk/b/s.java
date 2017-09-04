package oicq.wlogin_sdk.b;

import com.tencent.openqq.protocol.imsdk.im_common;
import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t113 */
public class s extends b {
    public s() {
        this.h = im_common.WPA_PAIPAI;
    }

    public long a() {
        return ((long) util.buf_to_int32(this.g, this.e)) & 4294967295L;
    }
}
