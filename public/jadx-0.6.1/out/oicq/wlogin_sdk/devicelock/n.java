package oicq.wlogin_sdk.devicelock;

/* compiled from: TLV_VerifySmsCode */
public class n extends e {
    public n() {
        this._type = 7;
    }

    public byte[] a(byte[] bArr) {
        if (bArr == null) {
            return new byte[0];
        }
        byte[] bArr2 = new byte[(bArr.length + 2)];
        fill_head();
        fill_body(bArr2, bArr2.length);
        put_block(bArr, this._head_len);
        return get_buf();
    }
}
