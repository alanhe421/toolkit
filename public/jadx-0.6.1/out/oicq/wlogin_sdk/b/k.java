package oicq.wlogin_sdk.b;

/* compiled from: tlv_t108 */
public class k extends b {
    int a;

    public k() {
        this.a = 0;
        this.h = 264;
    }

    public byte[] a(byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
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
