package oicq.wlogin_sdk.b;

/* compiled from: tlv_t188 */
public class bz extends b {
    int a;

    public bz() {
        this.a = 0;
        this.h = 392;
    }

    public byte[] a(byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[16];
        }
        this.a = bArr.length;
        Object obj = new byte[this.a];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        b(this.h);
        c(obj, this.a);
        e();
        return b();
    }
}
