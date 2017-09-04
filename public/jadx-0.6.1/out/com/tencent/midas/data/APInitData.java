package com.tencent.midas.data;

public class APInitData {
    private static APInitData a = null;
    private long b;
    private String c;

    private APInitData() {
        this.b = 0;
        this.c = "";
        this.b = 0;
        this.c = "";
    }

    public static void init() {
        a = new APInitData();
    }

    public static APInitData singleton() {
        if (a == null) {
            a = new APInitData();
        }
        return a;
    }

    public String getInitGUID() {
        return this.c;
    }

    public long getInitInterfaceTime() {
        return this.b;
    }

    public void setInitGUID(String str) {
        this.c = str;
    }

    public void setInitInterfaceTime(long j) {
        this.b = j;
    }
}
