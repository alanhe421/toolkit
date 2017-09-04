package oicq.wlogin_sdk.b;

/* compiled from: tlv_t192 */
public class cb extends b {
    byte[] a;

    public cb() {
        this.a = new byte[0];
        this.h = 402;
    }

    public Boolean f() {
        this.a = new byte[this.f];
        System.arraycopy(this.g, this.e, this.a, 0, this.f);
        return Boolean.valueOf(true);
    }

    public String a() {
        return new String(this.a);
    }
}
