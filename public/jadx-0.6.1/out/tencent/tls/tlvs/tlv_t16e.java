package tencent.tls.tlvs;

public class tlv_t16e extends tlv_t {
    int _t16e_body_len;

    public tlv_t16e() {
        this._t16e_body_len = 0;
        this._cmd = 366;
    }

    public byte[] get_tlv_16e(byte[] bArr) {
        int i = 64;
        if (bArr == null) {
            bArr = new byte[0];
        }
        if (bArr.length < 64) {
            i = bArr.length;
        }
        this._t16e_body_len = i;
        Object obj = new byte[this._t16e_body_len];
        System.arraycopy(bArr, 0, obj, 0, this._t16e_body_len);
        set_data(obj, this._t16e_body_len);
        return get_buf();
    }
}
