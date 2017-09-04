package tencent.tls.account;

import tencent.tls.tools.util;

public class TLSOpenAccountInfo {
    public String access_token;
    public String identifer;
    public int openAccountType;
    public String openappid;
    public String openid;
    public long sdkAppid;
    public OpenAccountStatus status = OpenAccountStatus.UNKNOWN;
    public int userAccountType;
    public String usersig;

    public enum OpenAccountStatus {
        UNKNOWN,
        UNUSED,
        USED_UNBINDED,
        USED_BINDED
    }

    public TLSOpenAccountInfo(int i, String str, String str2, String str3) {
        this.openAccountType = i;
        this.openappid = str;
        this.openid = str2;
        this.access_token = str3;
    }

    public boolean checkInvalid() {
        if (this.openAccountType < 1 || util.checkInvalid(this.openappid) || util.checkInvalid(this.openid) || util.checkInvalid(this.access_token)) {
            return true;
        }
        return false;
    }
}
