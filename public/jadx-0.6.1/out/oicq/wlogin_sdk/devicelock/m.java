package oicq.wlogin_sdk.devicelock;

import oicq.wlogin_sdk.tools.util;

/* compiled from: TLV_SmsInfo */
public class m extends e {
    public int a;
    public int b;

    public m() {
        this.a = 0;
        this.b = 0;
        this._type = 6;
    }

    public void parse() {
        int i = this._head_len;
        this.a = util.buf_to_int16(this._buf, i);
        this.b = util.buf_to_int16(this._buf, i + 2);
    }
}
