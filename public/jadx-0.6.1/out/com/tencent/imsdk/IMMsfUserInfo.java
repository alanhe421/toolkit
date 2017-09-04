package com.tencent.imsdk;

import com.dynamicload.Lib.DLConstants;
import com.tencent.TIMUser;
import com.tencent.TIMValueCallBack;
import com.tencent.openqq.IMPushListener;
import java.util.concurrent.ConcurrentHashMap;

public class IMMsfUserInfo {
    private static final String tag = "IMMsfUserInfo";
    ConcurrentHashMap<String, TIMValueCallBack<byte[]>> cmd2PushCallBack = new ConcurrentHashMap();
    ConcurrentHashMap<String, IMPushListener> cmd2PushListener = new ConcurrentHashMap();
    private boolean isLoggedIn = false;
    private boolean isSigExpire = false;
    private long tinyid = 0;
    private TIMUser user = new TIMUser();

    public ConcurrentHashMap<String, TIMValueCallBack<byte[]>> getCmd2PushCallBack() {
        return this.cmd2PushCallBack;
    }

    public ConcurrentHashMap<String, IMPushListener> getCmd2PushListener() {
        return this.cmd2PushListener;
    }

    public long getTinyid() {
        return this.tinyid;
    }

    public String getUidType() {
        return this.user != null ? this.user.getAccountType() : "";
    }

    public TIMUser getUser() {
        return this.user;
    }

    public String getUserId() {
        return this.user != null ? this.user.getIdentifier() : "";
    }

    public String getsUerAppId() {
        return this.user != null ? this.user.getAppIdAt3rd() : "";
    }

    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    public boolean isSigExpire() {
        return this.isSigExpire;
    }

    public void setIsLoggedIn(boolean z) {
        QLog.d(tag, 1, "set login flag to: " + z + DLConstants.DEPENDENCY_PACKAGE_DIV + this.user.getIdentifier());
        this.isLoggedIn = z;
    }

    public void setIsSigExpire(boolean z) {
        this.isSigExpire = z;
    }

    public void setPushCallBack(String str, TIMValueCallBack<byte[]> tIMValueCallBack) {
        this.cmd2PushCallBack.put(str, tIMValueCallBack);
    }

    public void setPushListener(String str, IMPushListener iMPushListener) {
        this.cmd2PushListener.put(str, iMPushListener);
    }

    public void setTinyid(long j) {
        this.tinyid = j;
    }

    public void setUser(TIMUser tIMUser) {
        this.user = tIMUser;
    }
}
