package oicq.wlogin_sdk.devicelock;

public class TLV_SppKey extends e {
    public byte[] SppKey;

    public TLV_SppKey() {
        this.SppKey = new byte[0];
        this._type = 11;
    }

    public void parse() {
        this.SppKey = get_data();
    }
}
