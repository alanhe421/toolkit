package oicq.wlogin_sdk.b;

/* compiled from: tlv_t401 */
public class cm extends b {
    public cm() {
        this.h = 1025;
    }

    public byte[] a(byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[16];
        }
        Object obj = new byte[bArr.length];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        int length = bArr.length + 0;
        b(this.h);
        c(obj, obj.length);
        e();
        return b();
    }
}
