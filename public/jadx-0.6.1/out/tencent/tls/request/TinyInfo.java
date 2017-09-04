package tencent.tls.request;

import java.io.Serializable;

public class TinyInfo implements Serializable {
    public static long GUEST_MAGIC = -1;
    private static final long serialVersionUID = 1;
    public int _acc_type;
    public long _msalt;
    public long _tinyid;
    public String _userid;
    public UserType userType = UserType.USER_TYPE_NORMAL;

    public enum UserType {
        USER_TYPE_NORMAL,
        USER_TYPE_GUEST,
        USER_TYPE_SSO_GUEST
    }

    public TinyInfo(int i, String str, long j, UserType userType) {
        this._acc_type = i;
        this._userid = str;
        this._tinyid = j;
        this.userType = userType;
    }
}
