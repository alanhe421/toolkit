package oicq.wlogin_sdk.b;

/* compiled from: tlv_t130 */
public class ah extends b {
    public ah() {
        this.h = 304;
    }

    public Boolean f() {
        if (this.f < 14) {
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }

    public byte[] a() {
        Object obj = new byte[4];
        System.arraycopy(this.g, this.e + 2, obj, 0, obj.length);
        return obj;
    }

    public byte[] g() {
        Object obj = new byte[4];
        System.arraycopy(this.g, (this.e + 2) + 4, obj, 0, obj.length);
        return obj;
    }
}
