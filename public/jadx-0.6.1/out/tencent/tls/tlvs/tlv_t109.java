package tencent.tls.tlvs;

public class tlv_t109 extends tlv_t {
    int _t109_body_len;

    public tlv_t109() {
        this._t109_body_len = 0;
        this._cmd = 265;
    }

    public byte[] get_tlv_109(byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[16];
        }
        this._t109_body_len = bArr.length;
        Object obj = new byte[this._t109_body_len];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        set_data(obj, this._t109_body_len);
        return get_buf();
    }
}
