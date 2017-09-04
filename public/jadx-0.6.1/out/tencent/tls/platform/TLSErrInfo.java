package tencent.tls.platform;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import tencent.tls.tools.I18nMsg;
import tencent.tls.tools.I18nMsg.MSG_TYPE;

public class TLSErrInfo implements Parcelable {
    public static final int ACCOUNT_FREQ_LIMIT = 3;
    public static final int ACCOUNT_INVALID_APPID = 11;
    public static final int ACCOUNT_INVALID_MOBILE = 4;
    public static final int ACCOUNT_INVALID_OPERATION = 13;
    public static final int ACCOUNT_INVALID_TOKEN = 7;
    public static final int ACCOUNT_JUST_FAILED = 5;
    public static final int ACCOUNT_NOT_EXIST = 1;
    public static final int ACCOUNT_OK = 0;
    public static final int ACCOUNT_REGISTERED = 2;
    public static final int ACCOUNT_REGISTER_TOOMANY = 10;
    public static final int ACCOUNT_RESET_TOOMANY = 6;
    public static final int ACCOUNT_SESSION_NOTFOUND = 9;
    public static final int ACCOUNT_SESSION_OPERATE_TOOMANY = 16;
    public static final int ACCOUNT_SESSION_VERIFY_TOOMANY = 17;
    public static final int ACCOUNT_SMSCODE_EXPIRED = 15;
    public static final int ACCOUNT_SMSCODE_INVALID = 14;
    public static final int ACCOUNT_SMSCODE_NOTALLOW = 8;
    public static final int ACCOUNT_WRONG_OPERATION = 12;
    protected static final Creator<TLSErrInfo> CREATOR = new 1();
    public static final int DECRYPT_FAILED = -1002;
    public static final int INPUT_INVALID = -1017;
    public static final int LOGIN_ID_INVALID = -1008;
    public static final int LOGIN_KEY_INVALID = -1014;
    public static final int LOGIN_NEED_IMGPIC = 2;
    public static final int LOGIN_NO_ACCOUNT = 229;
    public static final int LOGIN_NO_ID = -1003;
    public static final int LOGIN_NO_KEY = -1004;
    public static final int LOGIN_OK = 0;
    public static final int LOGIN_STORAGE_ERR = -1022;
    public static final int LOGIN_SYS_ERR = -1012;
    public static final int LOGIN_TLV_INVALID = -1005;
    public static final int LOGIN_WRONG_PWD = 1;
    public static final int LOGIN_WRONG_SMSCODE = 216;
    public static final int NO_NETWORK_ERROR = -1023;
    public static final int PENDING = -1001;
    public static final int PK_LEN = -1009;
    public static final int TIMEOUT = -1000;
    public int ErrCode;
    public String ExtraMsg;
    public String Msg;
    public String Title;

    public TLSErrInfo() {
        this.ExtraMsg = "";
        this.ErrCode = 0;
        this.Title = I18nMsg.getMsg(MSG_TYPE.MSG_0);
        this.Msg = I18nMsg.getMsg(MSG_TYPE.MSG_1);
    }

    public TLSErrInfo(int i, String str, String str2, String str3) {
        this.ExtraMsg = "";
        this.ErrCode = i;
        this.Title = str;
        this.Msg = str2;
        this.ExtraMsg = str3;
    }

    public TLSErrInfo(int i, String str, String str2) {
        this.ExtraMsg = "";
        this.ErrCode = i;
        if (str != null) {
            this.Title = str;
        }
        if (str2 != null) {
            this.Msg = str2;
        }
    }

    private TLSErrInfo(Parcel parcel) {
        this.ExtraMsg = "";
        readFromParcel(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.ErrCode);
        parcel.writeString(this.Title);
        parcel.writeString(this.Msg);
        parcel.writeString(this.ExtraMsg);
    }

    public void readFromParcel(Parcel parcel) {
        this.ErrCode = parcel.readInt();
        this.Title = parcel.readString();
        this.Msg = parcel.readString();
        this.ExtraMsg = parcel.readString();
    }
}
