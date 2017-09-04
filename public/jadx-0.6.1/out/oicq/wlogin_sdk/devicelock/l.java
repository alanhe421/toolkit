package oicq.wlogin_sdk.devicelock;

/* compiled from: TLV_SmsConfig */
public class l extends e {
    public int a;

    public l() {
        this.a = 0;
        this._type = 5;
    }

    public byte[] a(long j) {
        fill_head();
        fill_body(new byte[4], 4);
        put_int32(j, this._head_len);
        return get_buf();
    }
}
