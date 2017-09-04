package com.hmt.analytics.util;

public class HMTInfo {
    private Integer a;
    private String b;
    private String c;

    public HMTInfo(String str, String str2) {
        this.b = str;
        this.c = str2;
    }

    public HMTInfo(Integer num, String str, String str2) {
        this.a = num;
        this.b = str;
        this.c = str2;
    }

    public Integer getId() {
        return this.a;
    }

    public void setId(Integer num) {
        this.a = num;
    }

    public String getType() {
        return this.b;
    }

    public void setType(String str) {
        this.b = str;
    }

    public String getInfo() {
        return this.c;
    }

    public void setInfo(String str) {
        this.c = str;
    }
}
