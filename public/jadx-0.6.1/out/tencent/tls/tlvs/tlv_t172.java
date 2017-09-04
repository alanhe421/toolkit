package tencent.tls.tlvs;

public class tlv_t172 extends tlv_t {
    public tlv_t172() {
        this._cmd = 370;
    }

    public byte[] get_tlv_172(byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        set_data(bArr, bArr.length);
        return get_buf();
    }
}
