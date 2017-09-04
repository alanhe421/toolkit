package oicq.wlogin_sdk.b;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t149 */
public class at extends b {
    public int a;
    public int i;
    public int j;

    public at() {
        this.a = 0;
        this.i = 0;
        this.j = 0;
        this.h = ErrorCode.ERROR_GETSTRINGARRAY_JARFILE;
    }

    public Boolean f() {
        if (this.f < 8) {
            return Boolean.valueOf(false);
        }
        int buf_to_int16 = util.buf_to_int16(this.g, this.e + 2);
        if (this.f < buf_to_int16 + 8) {
            return Boolean.valueOf(false);
        }
        this.a = buf_to_int16;
        buf_to_int16 = util.buf_to_int16(this.g, ((this.e + 2) + 2) + this.a);
        if (this.f < (this.a + 8) + buf_to_int16) {
            return Boolean.valueOf(false);
        }
        this.i = buf_to_int16;
        buf_to_int16 = util.buf_to_int16(this.g, ((((this.e + 2) + 2) + this.a) + 2) + this.i);
        if (this.f < ((this.a + 8) + this.i) + buf_to_int16) {
            return Boolean.valueOf(false);
        }
        this.j = buf_to_int16;
        return Boolean.valueOf(true);
    }

    public int a() {
        return util.buf_to_int16(this.g, this.e);
    }

    public byte[] g() {
        Object obj = new byte[this.a];
        System.arraycopy(this.g, (this.e + 2) + 2, obj, 0, this.a);
        return obj;
    }

    public byte[] h() {
        Object obj = new byte[this.i];
        System.arraycopy(this.g, (((this.e + 2) + 2) + this.a) + 2, obj, 0, this.i);
        return obj;
    }

    public byte[] i() {
        Object obj = new byte[this.j];
        System.arraycopy(this.g, (((((this.e + 2) + 2) + this.a) + 2) + this.i) + 2, obj, 0, this.j);
        return obj;
    }
}
