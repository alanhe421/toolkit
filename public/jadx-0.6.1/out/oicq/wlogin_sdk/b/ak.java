package oicq.wlogin_sdk.b;

import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t138 */
public class ak extends b {
    public int a;

    public ak() {
        this.a = 0;
        this.h = ErrorCode.ERROR_TBSCORE_SHARE_DIR;
    }

    public Boolean f() {
        if (this.f < 4) {
            return Boolean.valueOf(false);
        }
        this.a = util.buf_to_int32(this.g, this.e);
        if (this.f < (this.a * 10) + 4) {
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }

    public int a() {
        for (int i = 0; i < this.a; i++) {
            if (util.buf_to_int16(this.g, (this.e + 4) + (i * 10)) == 266) {
                return util.buf_to_int32(this.g, ((i * 10) + (this.e + 4)) + 2);
            }
        }
        return 0;
    }

    public int g() {
        for (int i = 0; i < this.a; i++) {
            if (util.buf_to_int16(this.g, (this.e + 4) + (i * 10)) == 284) {
                return util.buf_to_int32(this.g, ((i * 10) + (this.e + 4)) + 2);
            }
        }
        return 0;
    }

    public int h() {
        for (int i = 0; i < this.a; i++) {
            if (util.buf_to_int16(this.g, (this.e + 4) + (i * 10)) == 288) {
                return util.buf_to_int32(this.g, ((i * 10) + (this.e + 4)) + 2);
            }
        }
        return 0;
    }

    public int i() {
        for (int i = 0; i < this.a; i++) {
            if (util.buf_to_int16(this.g, (this.e + 4) + (i * 10)) == 310) {
                return util.buf_to_int32(this.g, ((i * 10) + (this.e + 4)) + 2);
            }
        }
        return 0;
    }

    public int j() {
        for (int i = 0; i < this.a; i++) {
            if (util.buf_to_int16(this.g, (this.e + 4) + (i * 10)) == VoiceWakeuperAidl.RES_SPECIFIED) {
                return util.buf_to_int32(this.g, ((i * 10) + (this.e + 4)) + 2);
            }
        }
        return 0;
    }

    public int k() {
        for (int i = 0; i < this.a; i++) {
            if (util.buf_to_int16(this.g, (this.e + 4) + (i * 10)) == VoiceWakeuperAidl.RES_FROM_CLIENT) {
                return util.buf_to_int32(this.g, ((i * 10) + (this.e + 4)) + 2);
            }
        }
        return 0;
    }

    public int l() {
        for (int i = 0; i < this.a; i++) {
            if (util.buf_to_int16(this.g, (this.e + 4) + (i * 10)) == ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_03) {
                return util.buf_to_int32(this.g, ((i * 10) + (this.e + 4)) + 2);
            }
        }
        return 0;
    }

    public int m() {
        for (int i = 0; i < this.a; i++) {
            if (util.buf_to_int16(this.g, (this.e + 4) + (i * 10)) == 356) {
                return util.buf_to_int32(this.g, ((i * 10) + (this.e + 4)) + 2);
            }
        }
        return 0;
    }
}
