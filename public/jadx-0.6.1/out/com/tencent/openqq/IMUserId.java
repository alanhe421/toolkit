package com.tencent.openqq;

public class IMUserId {
    private String sUserAppId;
    private long tinyId;
    private String uidType;
    private long uin;
    private int userAppId;
    private String userId;

    public IMUserId(IMUserId iMUserId) {
        this.uidType = iMUserId.uidType;
        this.userAppId = iMUserId.userAppId;
        this.userId = iMUserId.userId;
        this.tinyId = iMUserId.tinyId;
        this.uin = iMUserId.uin;
    }

    public boolean equals(Object obj) {
        return obj == null ? false : toString().equals(((IMUserId) obj).toString());
    }

    public int getOpenAppId() {
        return this.userAppId;
    }

    public String getOpenId() {
        return this.userId;
    }

    public long getTinyId() {
        return this.tinyId;
    }

    public String getUidType() {
        return this.uidType;
    }

    public long getUin() {
        return this.uin;
    }

    public int getUserAppId() {
        return this.userAppId;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getsUserAppId() {
        return this.sUserAppId;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public void parseFromString(String str) {
        if (str != null) {
            String[] split = str.split(":");
            this.uidType = split[0];
            this.userAppId = Integer.parseInt(split[1]);
            this.sUserAppId = split[1];
            this.userId = split[2];
        }
    }

    public void setOpenAppId(int i) {
        this.userAppId = i;
    }

    public void setOpenId(String str) {
        this.userId = str;
    }

    public void setTinyId(long j) {
        this.tinyId = j;
    }

    public void setUidType(String str) {
        this.uidType = str;
    }

    public void setUin(long j) {
        this.uin = j;
    }

    public void setUserAppId(int i) {
        this.userAppId = i;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setsUserAppId(String str) {
        this.sUserAppId = str;
    }

    public String toString() {
        return this.uidType + ":" + this.sUserAppId + ":" + this.userId;
    }
}
