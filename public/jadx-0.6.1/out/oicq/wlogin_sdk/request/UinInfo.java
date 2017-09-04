package oicq.wlogin_sdk.request;

import java.io.Serializable;

public class UinInfo implements Serializable {
    private static final long serialVersionUID = 1;
    public boolean _hasPassword;
    public Long _uin;
    private int pwdState = 0;

    public UinInfo(Long l, boolean z) {
        this._uin = l;
        this._hasPassword = z;
        this.pwdState = z ? 1 : 2;
    }

    public boolean getHasPassword() {
        boolean z = true;
        if (!this._hasPassword && this.pwdState > 1) {
            z = false;
        }
        this._hasPassword = z;
        return this._hasPassword;
    }
}
