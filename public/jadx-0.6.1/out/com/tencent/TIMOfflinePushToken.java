package com.tencent;

public class TIMOfflinePushToken {
    private long bussid;
    private String token;

    public long getBussid() {
        return this.bussid;
    }

    public String getToken() {
        return this.token;
    }

    public void setBussid(long j) {
        this.bussid = j;
    }

    public void setToken(String str) {
        this.token = str;
    }
}
