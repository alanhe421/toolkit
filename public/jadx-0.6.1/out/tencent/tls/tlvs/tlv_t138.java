package tencent.tls.tlvs;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import tencent.tls.tools.util;

public class tlv_t138 extends tlv_t {
    public int A2_Valid;
    public int D2_Valid;
    private int cnt;

    public tlv_t138() {
        this.A2_Valid = 0;
        this.D2_Valid = 0;
        this.cnt = 0;
        this._cmd = ErrorCode.ERROR_TBSCORE_SHARE_DIR;
    }

    public boolean verify() {
        int i = 0;
        if (this._body_len < 4) {
            return false;
        }
        this.cnt = util.buf_to_int32(this._buf, this._head_len);
        if (this._body_len >= (this.cnt * 10) + 4) {
            return false;
        }
        while (i < this.cnt) {
            int i2 = (this._head_len + 4) + (i * 10);
            switch (util.buf_to_int16(this._buf, i2)) {
                case 266:
                    this.A2_Valid = util.buf_to_int32(this._buf, i2 + 2);
                    break;
                case ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_03 /*323*/:
                    this.D2_Valid = util.buf_to_int32(this._buf, i2 + 2);
                    break;
                default:
                    break;
            }
            i++;
        }
        return true;
    }
}
