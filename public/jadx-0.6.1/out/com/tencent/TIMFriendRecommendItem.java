package com.tencent;

import java.util.Map;

public class TIMFriendRecommendItem {
    private long addTime;
    private String identifier;
    private Map<String, byte[]> tags;

    public long getAddTime() {
        return this.addTime;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public Map<String, byte[]> getTags() {
        return this.tags;
    }

    void setAddTime(long j) {
        this.addTime = j;
    }

    void setIdentifier(String str) {
        this.identifier = str;
    }

    void setTags(Map<String, byte[]> map) {
        this.tags = map;
    }
}
