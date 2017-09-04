package oicq.wlogin_sdk.b;

/* compiled from: tlv_t112 */
public class r extends b {
    int a;

    public r() {
        this.a = 0;
        this.h = 274;
    }

    public byte[] a(byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        this.a = bArr.length;
        Object obj = new byte[this.a];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        int length = bArr.length + 0;
        b(this.h);
        c(obj, this.a);
        e();
        return b();
    }
}
