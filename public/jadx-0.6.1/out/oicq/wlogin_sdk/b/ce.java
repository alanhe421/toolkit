package oicq.wlogin_sdk.b;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t196 */
public class ce extends b {
    int a;
    byte[] i;
    byte[] j;

    public ce() {
        this.a = 0;
        this.i = new byte[0];
        this.j = new byte[0];
        this.h = ErrorCode.INFO_CAN_LOAD_TBS;
    }

    public Boolean f() {
        if (this.f < 5) {
            return Boolean.valueOf(false);
        }
        int i = this.e;
        this.a = util.buf_to_int8(this.g, i);
        i++;
        int buf_to_int16 = util.buf_to_int16(this.g, i);
        i += 2;
        if ((i - this.e) + buf_to_int16 > this.f) {
            return Boolean.valueOf(false);
        }
        this.i = new byte[buf_to_int16];
        System.arraycopy(this.g, i, this.i, 0, buf_to_int16);
        i += buf_to_int16;
        buf_to_int16 = util.buf_to_int16(this.g, i);
        i += 2;
        if ((i - this.e) + buf_to_int16 > this.f) {
            return Boolean.valueOf(false);
        }
        this.j = new byte[buf_to_int16];
        System.arraycopy(this.g, i, this.j, 0, buf_to_int16);
        i += buf_to_int16;
        return Boolean.valueOf(true);
    }

    public int a() {
        return this.a;
    }

    public String g() {
        return new String(this.i);
    }

    public String h() {
        return new String(this.j);
    }
}
