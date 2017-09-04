package com.tencent;

import com.tencent.imsdk.IMMsfCoreProxy;

public class TIMUser {
    private String accountType = "";
    private String appIdAt3rd = "";
    private String identifier = "";
    private long tinyId = 0;

    public TIMUser(TIMUser tIMUser) {
        this.accountType = tIMUser.accountType;
        this.appIdAt3rd = tIMUser.appIdAt3rd;
        this.identifier = tIMUser.identifier;
        this.tinyId = tIMUser.tinyId;
    }

    public boolean equals(Object obj) {
        return obj == null ? false : toString().equals(((TIMUser) obj).toString());
    }

    @Deprecated
    public String getAccountType() {
        return this.accountType;
    }

    @Deprecated
    public String getAppIdAt3rd() {
        return this.appIdAt3rd;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public long getTinyId() {
        return this.tinyId;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public void parseFromString(String str) {
        if (str != null) {
            String[] split = str.split(":");
            this.accountType = split[0];
            this.appIdAt3rd = split[1];
            this.identifier = split[2];
        }
    }

    @Deprecated
    public void setAccountType(String str) {
        this.accountType = str;
    }

    @Deprecated
    public void setAppIdAt3rd(String str) {
        this.appIdAt3rd = str;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public void setTinyId(long j) {
        this.tinyId = j;
    }

    public String toString() {
        return String.valueOf(IMMsfCoreProxy.get().getSdkAppId()) + ":" + this.accountType + ":" + this.identifier + ":" + this.appIdAt3rd;
    }
}
