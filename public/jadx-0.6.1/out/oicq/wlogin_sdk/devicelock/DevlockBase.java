package oicq.wlogin_sdk.devicelock;

import oicq.wlogin_sdk.request.u;
import oicq.wlogin_sdk.tools.util;

public class DevlockBase {
    private static int _seq = 0;
    public static DevlockRst rst = new DevlockRst();
    public int Role = 505;
    private int _head_len = 110;
    protected int _msgType = 0;
    private int _version = 32;

    public static final class a {
        public static int a = 5;
        public static int b = 7;
        public static int c = 8;
    }

    public int get_msgType() {
        return this._msgType;
    }

    public byte[] pickup_TLV(byte[] bArr, int i) {
        if (bArr == null || bArr.length < i + 4) {
            return null;
        }
        int buf_to_int16 = util.buf_to_int16(bArr, i + 2) + 4;
        if (bArr.length < i + buf_to_int16) {
            return null;
        }
        byte[] bArr2 = new byte[buf_to_int16];
        System.arraycopy(bArr, i, bArr2, 0, buf_to_int16);
        return bArr2;
    }

    public byte[] _get_request(long j, long j2, byte[] bArr) {
        Object obj = new byte[(((this._head_len + 1) + bArr.length) + 1)];
        util.int8_to_buf(obj, 0, 2);
        util.int16_to_buf(obj, 1, obj.length);
        util.int64_to_buf32(obj, 3, j);
        util.int16_to_buf(obj, 9, this._msgType);
        util.int64_to_buf32(obj, 11, j2);
        int i = _seq;
        _seq = i + 1;
        util.int32_to_buf(obj, 27, i);
        util.int16_to_buf(obj, 39, u.u);
        util.int16_to_buf(obj, 43, this._version);
        util.int8_to_buf(obj, 45, 0);
        System.arraycopy(bArr, 0, obj, 111, bArr.length);
        int length = bArr.length + 111;
        util.int8_to_buf(obj, length, 3);
        length++;
        return obj;
    }

    public int parse_rsp(byte[] bArr) {
        if (bArr == null || bArr.length < this._head_len + 2) {
            return -1009;
        }
        Object obj = new byte[((bArr.length - this._head_len) - 2)];
        System.arraycopy(bArr, this._head_len + 1, obj, 0, obj.length);
        if (2 > obj.length) {
            return -1009;
        }
        int buf_to_int16 = util.buf_to_int16(obj, 0);
        int i = 2;
        int i2 = 0;
        for (int i3 = 0; i3 < buf_to_int16; i3++) {
            byte[] pickup_TLV = pickup_TLV(obj, i);
            if (pickup_TLV == null) {
                return -1009;
            }
            i += pickup_TLV.length;
            switch (util.buf_to_int16(pickup_TLV, 0)) {
                case 1:
                    i2 = rst.commRsp.set_buf(pickup_TLV, pickup_TLV.length);
                    break;
                case 3:
                    i2 = rst.devSetupInfo.set_buf(pickup_TLV, pickup_TLV.length);
                    break;
                case 4:
                    i2 = rst.mbMobileInfo.set_buf(pickup_TLV, pickup_TLV.length);
                    break;
                case 6:
                    i2 = rst.smsInfo.set_buf(pickup_TLV, pickup_TLV.length);
                    break;
                case 8:
                    i2 = rst.querySig.set_buf(pickup_TLV, pickup_TLV.length);
                    break;
                case 11:
                    i2 = rst.sppKey.set_buf(pickup_TLV, pickup_TLV.length);
                    break;
                case 14:
                    i2 = rst.mbGuideInfo.set_buf(pickup_TLV, pickup_TLV.length);
                    break;
                case 18:
                    i2 = rst.devGuideInfo.set_buf(pickup_TLV, pickup_TLV.length);
                    break;
                case 19:
                    i2 = rst.transferInfo.set_buf(pickup_TLV, pickup_TLV.length);
                    break;
                default:
                    i2 = 0;
                    break;
            }
            if (i2 != 0) {
                return i2;
            }
        }
        if (i2 == 0) {
            return rst.commRsp.RetCode;
        }
        return i2;
    }
}
