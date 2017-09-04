package com.iflytek.cloud;

import com.iflytek.cloud.a.b.a.a;
import com.tencent.android.tpush.common.Constants;
import qalsdk.n;

public class SpeechError extends Exception {
    public static final int TIP_ERROR_ALREADY_EXIST = 66;
    public static final int TIP_ERROR_IVP_EXTRA_RGN_SOPPORT = 56;
    public static final int TIP_ERROR_IVP_GENERAL = 55;
    public static final int TIP_ERROR_IVP_MODEL_NOT_FOUND_IN_HBASE = 64;
    public static final int TIP_ERROR_IVP_MUCH_NOISE = 58;
    public static final int TIP_ERROR_IVP_NO_ENOUGH_AUDIO = 63;
    public static final int TIP_ERROR_IVP_TEXT_NOT_MATCH = 62;
    public static final int TIP_ERROR_IVP_TOO_LOW = 59;
    public static final int TIP_ERROR_IVP_TRUNCATED = 57;
    public static final int TIP_ERROR_IVP_UTTER_TOO_SHORT = 61;
    public static final int TIP_ERROR_IVP_ZERO_AUDIO = 60;
    public static final int TIP_ERROR_NOT_FOUND = 65;
    private static final long serialVersionUID = 4434424251478985596L;
    private int a;
    private String b;

    public SpeechError(int i) {
        int i2 = 11;
        this.a = 0;
        this.b = "";
        this.a = i;
        if (i >= 20001) {
            if (this.a < n.f) {
                if (this.a == 20001) {
                    i2 = 1;
                } else if (this.a == 20002) {
                    i2 = 2;
                } else if (this.a == 20003) {
                    i2 = 3;
                } else if (this.a == 20004) {
                    i2 = 5;
                } else if (this.a == 20005) {
                    i2 = 10;
                } else if (this.a == 20006) {
                    i2 = 9;
                } else if (this.a == 20007) {
                    i2 = 12;
                } else if (this.a != 20008) {
                    i2 = this.a == 20009 ? 13 : this.a == 20010 ? 14 : this.a == 20012 ? 7 : this.a == 21003 ? 28 : (this.a == 21002 || this.a == 21001) ? 29 : 30;
                }
            }
            i2 = 3;
        } else if (this.a != 10118) {
            if (10106 == this.a || 10107 == this.a || 10124 == this.a) {
                a.a("sdk errorcode", this.a + "");
                i2 = 7;
            } else if (this.a == Constants.CODE_NETWORK_HANDLER_NULL) {
                i2 = 32;
            } else if (this.a == 10111) {
                i2 = 28;
            } else if (this.a >= 10200 && this.a < Constants.CODE_STRATEGY_INIT) {
                i2 = 3;
            } else if (this.a == 10117 || this.a == 10101) {
                i2 = 16;
            } else if (this.a == 10113) {
                i2 = 17;
            } else if (this.a == 10116) {
                i2 = 65;
            } else if (this.a == 10121) {
                i2 = 66;
            } else if (this.a >= 10400 && this.a <= 10407) {
                i2 = 18;
            } else if (this.a >= 11000 && this.a < 11099) {
                i2 = this.a == 11005 ? 23 : this.a == 11006 ? 24 : 18;
            } else if (this.a == 10129) {
                i2 = 19;
            } else if (this.a == Constants.CODE_NETWORK_UNKNOWN_EXCEPTION) {
                i2 = 20;
            } else if (this.a == 10702) {
                i2 = 21;
            } else if (this.a >= 10500 && this.a < 10600) {
                i2 = 22;
            } else if (this.a >= 11200 && this.a <= 11250) {
                i2 = 25;
            } else if (this.a >= 14000 && this.a <= 14006) {
                i2 = 31;
            } else if (this.a >= 16000 && this.a <= 16006) {
                i2 = 31;
            } else if (11401 == this.a) {
                i2 = 35;
            } else if (11402 == this.a) {
                i2 = 36;
            } else if (11403 == this.a) {
                i2 = 37;
            } else if (11404 == this.a) {
                i2 = 38;
            } else if (11405 == this.a) {
                i2 = 39;
            } else if (11406 == this.a) {
                i2 = 40;
            } else if (11407 == this.a) {
                i2 = 41;
            } else {
                if (11408 == this.a) {
                    i2 = 42;
                }
                i2 = 3;
            }
        }
        switch (this.a) {
            case 11600:
                i2 = 55;
                break;
            case 11601:
                i2 = 56;
                break;
            case 11602:
                i2 = 57;
                break;
            case 11603:
                i2 = 58;
                break;
            case 11604:
                i2 = 59;
                break;
            case 11605:
                i2 = 60;
                break;
            case 11606:
                i2 = 61;
                break;
            case 11607:
                i2 = 62;
                break;
            case 11608:
                i2 = 63;
                break;
            case 11610:
                i2 = 64;
                break;
            case 11700:
                i2 = 43;
                break;
            case 11701:
                i2 = 44;
                break;
            case 11702:
                i2 = 45;
                break;
            case 11703:
                i2 = 46;
                break;
            case 11704:
                i2 = 47;
                break;
            case 11705:
                i2 = 48;
                break;
            case 11706:
                i2 = 49;
                break;
            case 11707:
                i2 = 50;
                break;
            case 11708:
                i2 = 51;
                break;
            case 11709:
                i2 = 52;
                break;
            case 11710:
                i2 = 53;
                break;
            case 11711:
                i2 = 54;
                break;
        }
        this.b = com.iflytek.cloud.c.a.a(i2);
    }

    public SpeechError(int i, String str) {
        this(i);
        if (!"wfr".equals(str)) {
            return;
        }
        if (10118 == i) {
            this.b = com.iflytek.cloud.c.a.a(33);
        } else if (10119 == i) {
            this.b = com.iflytek.cloud.c.a.a(34);
        }
    }

    public SpeechError(Exception exception) {
        this.a = 0;
        this.b = "";
        this.a = 20999;
        this.b = exception.toString();
    }

    public int getErrorCode() {
        return this.a;
    }

    public String getErrorDescription() {
        return this.b;
    }

    public String getHtmlDescription(boolean z) {
        String str = this.b + "...";
        if (!z) {
            return str;
        }
        return ((str + "<br>(") + com.iflytek.cloud.c.a.b(0) + ":") + this.a + ")";
    }

    public String getPlainDescription(boolean z) {
        String str = this.b;
        if (!z) {
            return str;
        }
        return ((str + ".") + "(" + com.iflytek.cloud.c.a.b(0) + ":") + this.a + ")";
    }

    public String toString() {
        return getPlainDescription(true);
    }
}
