package oicq.wlogin_sdk.b;

/* compiled from: tlv_t10a */
public class m extends b {
    public m() {
        this.h = 266;
    }

    public byte[] a(byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        Object obj = new byte[bArr.length];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        b(this.h);
        c(obj, obj.length);
        e();
        return b();
    }
}
