package tencent.tls.request;

public class TransReqContext {
    public byte[] _body;
    public int _subcmd = 0;
    public int _type = 0;
    public long _uin = 0;
    public Object listener = null;

    public void set_body(byte[] bArr) {
        if (bArr == null) {
            this._body = new byte[0];
        } else {
            this._body = bArr;
        }
    }

    public byte[] get_body() {
        return this._body;
    }

    public boolean is_register_req() {
        return this._type == 1;
    }

    public void set_register_req() {
        this._type = 1;
    }

    public int get_subcmd() {
        return this._subcmd;
    }

    public void set_subcmd(int i) {
        this._subcmd = i;
    }

    public long get_uin() {
        return this._uin;
    }

    public void set_uin(long j) {
        this._uin = j;
    }

    public TransReqContext(Object obj) {
        this.listener = obj;
    }
}
