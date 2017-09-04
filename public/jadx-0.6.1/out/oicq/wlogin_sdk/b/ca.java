package oicq.wlogin_sdk.b;

/* compiled from: tlv_t191 */
public class ca extends b {
    public ca() {
        this.h = 401;
    }

    public byte[] a(boolean z) {
        byte[] bArr = new byte[1];
        bArr[0] = (byte) (z ? 1 : 0);
        b(this.h);
        c(bArr, 1);
        e();
        return b();
    }
}
