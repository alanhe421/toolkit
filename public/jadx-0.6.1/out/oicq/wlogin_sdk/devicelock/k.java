package oicq.wlogin_sdk.devicelock;

import oicq.wlogin_sdk.tools.util;

/* compiled from: TLV_MbMobileInfo */
public class k extends e {
    public byte[] a;
    public byte[] b;
    public int c;
    public int d;
    public int e;

    public k() {
        this.a = new byte[0];
        this.b = new byte[0];
        this._type = 4;
    }

    public void parse() {
        int i = this._head_len;
        int buf_to_int16 = util.buf_to_int16(this._buf, i);
        i += 2;
        this.a = new byte[buf_to_int16];
        System.arraycopy(this._buf, i, this.a, 0, buf_to_int16);
        i += buf_to_int16;
        buf_to_int16 = util.buf_to_int16(this._buf, i);
        i += 2;
        this.b = new byte[buf_to_int16];
        System.arraycopy(this._buf, i, this.b, 0, buf_to_int16);
        i += buf_to_int16;
        this.c = util.buf_to_int32(this._buf, i);
        i += 4;
        this.d = util.buf_to_int16(this._buf, i);
        i += 2;
        this.e = util.buf_to_int16(this._buf, i);
        i += 2;
    }
}
