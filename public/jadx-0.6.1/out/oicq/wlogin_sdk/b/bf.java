package oicq.wlogin_sdk.b;

/* compiled from: tlv_t16e */
public class bf extends b {
    int a;

    public bf() {
        this.a = 0;
        this.h = 366;
    }

    public byte[] a(byte[] bArr) {
        int i = 64;
        if (bArr == null) {
            bArr = new byte[0];
        }
        if (bArr.length < 64) {
            i = bArr.length;
        }
        this.a = i;
        Object obj = new byte[this.a];
        System.arraycopy(bArr, 0, obj, 0, this.a);
        b(this.h);
        c(obj, this.a);
        e();
        return b();
    }
}
