package tencent.tls.tlvs;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import tencent.tls.tools.util;

public class tlv_t149 extends tlv_t {
    public int _content_len;
    public int _otherinfo_len;
    public int _title_len;

    public tlv_t149() {
        this._title_len = 0;
        this._content_len = 0;
        this._otherinfo_len = 0;
        this._cmd = ErrorCode.ERROR_GETSTRINGARRAY_JARFILE;
    }

    public boolean verify() {
        if (this._body_len < 8) {
            return false;
        }
        int buf_to_int16 = util.buf_to_int16(this._buf, this._head_len + 2);
        if (this._body_len < buf_to_int16 + 8) {
            return false;
        }
        this._title_len = buf_to_int16;
        buf_to_int16 = util.buf_to_int16(this._buf, ((this._head_len + 2) + 2) + this._title_len);
        if (this._body_len < (this._title_len + 8) + buf_to_int16) {
            return false;
        }
        this._content_len = buf_to_int16;
        buf_to_int16 = util.buf_to_int16(this._buf, ((((this._head_len + 2) + 2) + this._title_len) + 2) + this._content_len);
        if (this._body_len < ((this._title_len + 8) + this._content_len) + buf_to_int16) {
            return false;
        }
        this._otherinfo_len = buf_to_int16;
        return true;
    }

    public int get_type() {
        return util.buf_to_int16(this._buf, this._head_len);
    }

    public String get_title() {
        Object obj = new byte[this._title_len];
        System.arraycopy(this._buf, (this._head_len + 2) + 2, obj, 0, this._title_len);
        return new String(obj);
    }

    public String get_content() {
        Object obj = new byte[this._content_len];
        System.arraycopy(this._buf, (((this._head_len + 2) + 2) + this._title_len) + 2, obj, 0, this._content_len);
        return new String(obj);
    }

    public String get_otherinfo() {
        Object obj = new byte[this._otherinfo_len];
        System.arraycopy(this._buf, (((((this._head_len + 2) + 2) + this._title_len) + 2) + this._content_len) + 2, obj, 0, this._otherinfo_len);
        return new String(obj);
    }
}
