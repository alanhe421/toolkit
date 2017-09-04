package oicq.wlogin_sdk.devicelock;

import oicq.wlogin_sdk.tools.util;

/* compiled from: TLV_DevSetupInfo */
public class i extends e {
    public int a;
    public int b;
    public int c;
    public byte[] d;
    public byte[] e;
    public byte[] f;
    public byte[] g;

    public i() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = new byte[0];
        this.e = new byte[0];
        this.f = new byte[0];
        this.g = new byte[0];
        this._type = 3;
    }

    public void parse() {
        int i = this._head_len;
        this.a = util.buf_to_int32(this._buf, i);
        i += 4;
        this.b = util.buf_to_int8(this._buf, i);
        i++;
        int buf_to_int16 = util.buf_to_int16(this._buf, i);
        i += 2;
        this.d = new byte[buf_to_int16];
        System.arraycopy(this._buf, i, this.d, 0, buf_to_int16);
        i += buf_to_int16;
        buf_to_int16 = util.buf_to_int16(this._buf, i);
        i += 2;
        this.e = new byte[buf_to_int16];
        System.arraycopy(this._buf, i, this.e, 0, buf_to_int16);
        i += buf_to_int16;
        buf_to_int16 = util.buf_to_int16(this._buf, i);
        i += 2;
        this.f = new byte[buf_to_int16];
        System.arraycopy(this._buf, i, this.f, 0, buf_to_int16);
        i += buf_to_int16;
        this.c = util.buf_to_int16(this._buf, i);
        i += 2;
        buf_to_int16 = util.buf_to_int16(this._buf, i);
        i += 2;
        this.g = new byte[buf_to_int16];
        System.arraycopy(this._buf, i, this.g, 0, buf_to_int16);
        i += buf_to_int16;
    }
}
