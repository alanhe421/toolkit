package tencent.tls.tlvs;

public class tlv_t130 extends tlv_t {
    public tlv_t130() {
        this._cmd = 304;
    }

    public boolean verify() {
        return this._body_len >= 14;
    }

    public byte[] get_time() {
        Object obj = new byte[4];
        System.arraycopy(this._buf, this._head_len + 2, obj, 0, obj.length);
        return obj;
    }

    public byte[] get_ipaddr() {
        Object obj = new byte[4];
        System.arraycopy(this._buf, (this._head_len + 2) + 4, obj, 0, obj.length);
        return obj;
    }
}
