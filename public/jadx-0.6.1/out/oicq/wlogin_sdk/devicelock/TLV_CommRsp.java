package oicq.wlogin_sdk.devicelock;

import oicq.wlogin_sdk.tools.util;

public class TLV_CommRsp extends e {
    public byte[] ErrInfo;
    public int ErrInfoType;
    public byte[] ErrMsg;
    public byte[] ErrTitle;
    public int Reason;
    public int RetCode;

    public TLV_CommRsp() {
        this.RetCode = -1;
        this.Reason = 0;
        this.ErrTitle = new byte[0];
        this.ErrMsg = new byte[0];
        this.ErrInfo = new byte[0];
        this._type = 1;
    }

    public void parse() {
        int i = this._head_len;
        this.RetCode = util.buf_to_int32(this._buf, i);
        i += 4;
        this.Reason = util.buf_to_int32(this._buf, i);
        i += 4;
        int buf_to_int16 = util.buf_to_int16(this._buf, i);
        i += 2;
        this.ErrTitle = new byte[buf_to_int16];
        System.arraycopy(this._buf, i, this.ErrTitle, 0, buf_to_int16);
        i += buf_to_int16;
        buf_to_int16 = util.buf_to_int16(this._buf, i);
        i += 2;
        this.ErrMsg = new byte[buf_to_int16];
        System.arraycopy(this._buf, i, this.ErrMsg, 0, buf_to_int16);
        i += buf_to_int16;
        this.ErrInfoType = util.buf_to_int16(this._buf, i);
        i += 2;
        buf_to_int16 = util.buf_to_int16(this._buf, i);
        i += 2;
        this.ErrInfo = new byte[buf_to_int16];
        System.arraycopy(this._buf, i, this.ErrInfo, 0, buf_to_int16);
        i += buf_to_int16;
    }
}
