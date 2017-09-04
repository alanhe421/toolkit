package tencent.tls.tlvs;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import tencent.tls.tools.util;

public class tlv_t146 extends tlv_t {
    public int _errorinfo_len;
    public int _msg_len;
    public int _title_len;
    public String errMsg;
    public String errTitle;
    public int errType;
    public String extraErrMsg;
    int ver;

    public tlv_t146() {
        this._title_len = 0;
        this._msg_len = 0;
        this._errorinfo_len = 0;
        this.ver = 0;
        this.errType = 0;
        this.errTitle = "";
        this.errMsg = "";
        this.extraErrMsg = "";
        this._cmd = ErrorCode.TEST_THROWABLE_IS_NULL;
    }

    public boolean verify() {
        if (this._body_len < 4) {
            return false;
        }
        this.ver = util.buf_to_int16(this._buf, this._head_len);
        Object obj;
        if (this.ver == 0) {
            this.errType = util.buf_to_int16(this._buf, this._head_len + 2);
            int buf_to_int16 = util.buf_to_int16(this._buf, this._head_len + 4);
            this._title_len = buf_to_int16;
            if (this._body_len < buf_to_int16 + 12) {
                return false;
            }
            Object obj2 = new byte[buf_to_int16];
            System.arraycopy(this._buf, this._head_len + 6, obj2, 0, buf_to_int16);
            this.errTitle = new String(obj2);
            buf_to_int16 = util.buf_to_int16(this._buf, (this._head_len + 6) + this._title_len);
            this._msg_len = buf_to_int16;
            if (this._body_len < buf_to_int16 + (this._title_len + 12)) {
                return false;
            }
            obj = new byte[this._msg_len];
            System.arraycopy(this._buf, (this._head_len + 8) + this._title_len, obj, 0, this._msg_len);
            this.errMsg = new String(obj);
            buf_to_int16 = util.buf_to_int16(this._buf, ((this._head_len + 10) + this._title_len) + this._msg_len);
            this._errorinfo_len = buf_to_int16;
            if (this._body_len < buf_to_int16 + ((this._title_len + 12) + this._msg_len)) {
                return false;
            }
            obj = new byte[this._errorinfo_len];
            System.arraycopy(this._buf, ((this._head_len + 12) + this._title_len) + this._msg_len, obj, 0, this._errorinfo_len);
            this.extraErrMsg = new String(obj);
        } else if (this.ver == 1) {
            this.errType = util.buf_to_int32(this._buf, this._head_len + 2);
            this._msg_len = util.buf_to_int16(this._buf, (this._head_len + 2) + 4);
            obj = new byte[this._msg_len];
            System.arraycopy(this._buf, (this._head_len + 2) + 4, obj, 0, this._msg_len);
            this.errMsg = new String(obj);
            return true;
        }
        return true;
    }
}
