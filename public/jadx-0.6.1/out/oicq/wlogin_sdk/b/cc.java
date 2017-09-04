package oicq.wlogin_sdk.b;

/* compiled from: tlv_t193 */
public class cc extends b {
    public cc() {
        this.h = 403;
    }

    public byte[] a(byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        b(this.h);
        c(bArr, bArr.length);
        e();
        return b();
    }
}
