package oicq.wlogin_sdk.devicelock;

/* compiled from: TLV_DevGuideInfo */
public class h extends e {
    public byte[] a;

    public h() {
        this.a = new byte[0];
        this._type = 18;
    }

    public void parse() {
        this.a = get_data();
    }
}
