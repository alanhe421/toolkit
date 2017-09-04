package oicq.wlogin_sdk.request;

import java.io.Serializable;

public class WloginLastLoginInfo implements Serializable {
    private static final long serialVersionUID = 1;
    public String mAccount;
    public long mUin;

    public WloginLastLoginInfo(String str, long j) {
        this.mAccount = new String();
        this.mUin = 0;
        this.mAccount = str;
        this.mUin = j;
    }

    public WloginLastLoginInfo() {
        this.mAccount = new String();
        this.mUin = 0;
        this.mAccount = "";
    }
}
