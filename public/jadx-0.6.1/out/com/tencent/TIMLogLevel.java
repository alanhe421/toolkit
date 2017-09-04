package com.tencent;

public enum TIMLogLevel {
    OFF("OFF"),
    ERROR("ERROR"),
    WARN("WARN"),
    INFO("INFO"),
    DEBUG("DEBUG");
    
    private String descr;

    private TIMLogLevel(String str) {
        this.descr = "INFO";
        this.descr = str;
    }

    public final String getDescr() {
        return this.descr;
    }

    final int getIntLevel() {
        return this.descr.equals("OFF") ? 1 : this.descr.equals("ERROR") ? 2 : this.descr.equals("WARN") ? 3 : this.descr.equals("DEBUG") ? 5 : 4;
    }
}
