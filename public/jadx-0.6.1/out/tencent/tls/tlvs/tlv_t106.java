package tencent.tls.tlvs;

import tencent.tls.tools.cryptor;
import tencent.tls.tools.util;

public class tlv_t106 extends tlv_t {
    int _SSoVer;
    int _TGTGTVer;
    int _t106_body_len;

    public tlv_t106() {
        this._TGTGTVer = 4;
        this._SSoVer = 1;
        this._t106_body_len = 90;
        this._cmd = 262;
    }

    public byte[] get_tlv_106(long j, long j2, int i, byte[] bArr, byte[] bArr2, int i2, byte[] bArr3, long j3, byte[] bArr4, int i3, byte[] bArr5, int i4, byte[] bArr6, int i5) {
        Object obj;
        Object obj2;
        Object obj3;
        if (bArr == null) {
            bArr = new byte[0];
        }
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        if (bArr3 == null) {
            obj = new byte[0];
        }
        if (bArr4 == null) {
            obj2 = new byte[0];
        }
        if (bArr5 == null) {
            Object obj4 = new byte[0];
        }
        if (bArr6 == null) {
            obj3 = new byte[0];
        }
        this._t106_body_len += (obj3.length + 2) + 4;
        Object obj5 = new byte[this._t106_body_len];
        util.int16_to_buf(obj5, 0, this._TGTGTVer);
        util.int32_to_buf(obj5, 2, util.get_rand_32());
        util.int32_to_buf(obj5, 6, this._SSoVer);
        util.int64_to_buf32(obj5, 10, j);
        util.int32_to_buf(obj5, 14, i);
        System.arraycopy(bArr, 0, obj5, 18, bArr.length);
        int length = bArr.length + 18;
        System.arraycopy(bArr2, 0, obj5, length, bArr2.length);
        length += bArr2.length;
        util.int8_to_buf(obj5, length, i2);
        length++;
        System.arraycopy(obj, 0, obj5, length, obj.length);
        length += obj.length;
        System.arraycopy(obj2, 0, obj5, length, obj2.length);
        length += obj2.length;
        util.int32_to_buf(obj5, length, 0);
        length += 4;
        util.int8_to_buf(obj5, length, i3);
        length++;
        if (obj4.length == 0) {
            byte[] bArr7 = new byte[16];
            util.int32_to_buf(bArr7, 0, util.get_rand_32());
            util.int32_to_buf(bArr7, 4, util.get_rand_32());
            util.int32_to_buf(bArr7, 8, util.get_rand_32());
            util.int32_to_buf(bArr7, 12, util.get_rand_32());
            length += bArr7.length;
        } else {
            System.arraycopy(obj4, 0, obj5, length, obj4.length);
            length += obj4.length;
        }
        util.int64_to_buf32(obj5, length, j2);
        length += 4;
        util.int32_to_buf(obj5, length, i4);
        length += 4;
        util.int16_to_buf(obj5, length, obj3.length);
        length += 2;
        System.arraycopy(obj3, 0, obj5, length, obj3.length);
        length += obj3.length;
        util.int32_to_buf(obj5, length, i5);
        length += 4;
        byte[] encrypt = cryptor.encrypt(obj5, 0, obj5.length, util.getS2(obj, j3));
        this._t106_body_len = encrypt.length;
        set_data(encrypt, this._t106_body_len);
        return get_buf();
    }
}
