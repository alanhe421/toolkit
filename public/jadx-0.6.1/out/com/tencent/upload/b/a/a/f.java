package com.tencent.upload.b.a.a;

public abstract class f extends e {
    private String b;
    private String c;
    private String d;
    private String e;

    public f(String str, String str2, String str3, String str4) {
        if (str == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
    }

    public final String b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }

    public final String d() {
        return this.d;
    }

    public final String e() {
        return this.e;
    }
}
