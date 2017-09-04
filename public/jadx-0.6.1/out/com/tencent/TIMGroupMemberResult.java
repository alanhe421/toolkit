package com.tencent;

public class TIMGroupMemberResult {
    private long result;
    private String user;

    public long getResult() {
        return this.result;
    }

    public String getUser() {
        return this.user;
    }

    void setResult(long j) {
        this.result = j;
    }

    void setUser(String str) {
        this.user = str;
    }
}
