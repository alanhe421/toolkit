package com.qq.reader.common.exception;

import android.text.format.DateFormat;

/* compiled from: ReaderException */
public class c {
    String a = "";
    String b = "";
    long c = 0;
    String d = "";
    String e = "";
    String f = "";

    public String a() {
        return this.a;
    }

    public c a(String str) {
        this.a = str;
        return this;
    }

    public String b() {
        return this.b;
    }

    public c b(String str) {
        this.b = str;
        return this;
    }

    public String c() {
        return DateFormat.format("yyyy:MM:dd kk:mm:ss", this.c).toString();
    }

    public c a(long j) {
        this.c = j;
        return this;
    }

    public String d() {
        return this.d;
    }

    public c c(String str) {
        this.d = str;
        return this;
    }

    public String e() {
        return this.e;
    }

    public c d(String str) {
        this.e = str;
        return this;
    }

    public String f() {
        return this.f;
    }

    public c e(String str) {
        if (str == null) {
            this.f = "";
        } else {
            this.f = str;
        }
        return this;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("mException = " + this.a);
        stringBuffer.append(" , mType = " + this.b);
        stringBuffer.append(" , mTime = " + c());
        stringBuffer.append(" , mUid = " + this.d);
        stringBuffer.append(" , mUrl = " + this.e);
        stringBuffer.append(" , mDownloadUrl = " + this.f);
        return stringBuffer.toString();
    }
}
