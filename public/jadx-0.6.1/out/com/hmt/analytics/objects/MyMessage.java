package com.hmt.analytics.objects;

public class MyMessage {
    private boolean a;
    private String b;

    public boolean isFlag() {
        return this.a;
    }

    public void setFlag(boolean z) {
        this.a = z;
    }

    public String getMsg() {
        return this.b;
    }

    public void setMsg(String str) {
        this.b = str;
    }
}
