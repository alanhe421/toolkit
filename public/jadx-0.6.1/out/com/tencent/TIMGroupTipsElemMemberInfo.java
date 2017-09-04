package com.tencent;

public class TIMGroupTipsElemMemberInfo {
    private String identifier;
    private long shutupTime;

    public String getIdentifier() {
        return this.identifier;
    }

    public long getShutupTime() {
        return this.shutupTime;
    }

    void setIdentifier(String str) {
        this.identifier = str;
    }

    void setShutupTime(long j) {
        this.shutupTime = j;
    }
}
