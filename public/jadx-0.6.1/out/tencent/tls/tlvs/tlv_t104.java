package tencent.tls.tlvs;

public class tlv_t104 extends tlv_t {
    int _t104_body_len;

    public tlv_t104() {
        this._t104_body_len = 0;
        this._cmd = 260;
    }

    public byte[] get_tlv_104(byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        this._t104_body_len = bArr.length;
        Object obj = new byte[this._t104_body_len];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        set_data(obj, this._t104_body_len);
        return get_buf();
    }
}
