package oicq.wlogin_sdk.devicelock;

public class TLV_QuerySig extends e {
    public byte[] QuerySig;

    public TLV_QuerySig() {
        this.QuerySig = new byte[0];
        this._type = 8;
    }

    public void parse() {
        this.QuerySig = get_data();
    }
}
