package oicq.wlogin_sdk.b;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t146 */
public class aq extends b {
    public int a;
    public int i;
    public int j;

    public aq() {
        this.a = 0;
        this.i = 0;
        this.j = 0;
        this.h = ErrorCode.TEST_THROWABLE_IS_NULL;
    }

    public Boolean f() {
        if (this.f < 12) {
            return Boolean.valueOf(false);
        }
        int buf_to_int16 = util.buf_to_int16(this.g, this.e + 4);
        if (this.f < buf_to_int16 + 12) {
            return Boolean.valueOf(false);
        }
        this.a = buf_to_int16;
        buf_to_int16 = util.buf_to_int16(this.g, (this.e + 6) + this.a);
        if (this.f < (this.a + 12) + buf_to_int16) {
            return Boolean.valueOf(false);
        }
        this.i = buf_to_int16;
        buf_to_int16 = util.buf_to_int16(this.g, ((this.e + 10) + this.a) + this.i);
        if (this.f < ((this.a + 12) + this.i) + buf_to_int16) {
            return Boolean.valueOf(false);
        }
        this.j = buf_to_int16;
        return Boolean.valueOf(true);
    }

    public byte[] a() {
        Object obj = new byte[this.a];
        System.arraycopy(this.g, this.e + 6, obj, 0, this.a);
        return obj;
    }

    public byte[] g() {
        Object obj = new byte[this.i];
        System.arraycopy(this.g, (this.e + 8) + this.a, obj, 0, this.i);
        return obj;
    }

    public int h() {
        return util.buf_to_int16(this.g, ((this.e + 8) + this.a) + this.i);
    }

    public byte[] i() {
        Object obj = new byte[this.j];
        System.arraycopy(this.g, ((this.e + 12) + this.a) + this.i, obj, 0, this.j);
        return obj;
    }
}
