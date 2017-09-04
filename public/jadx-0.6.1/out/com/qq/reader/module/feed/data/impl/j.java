package com.qq.reader.module.feed.data.impl;

import android.text.TextUtils;
import com.dynamicload.Lib.DLConstants;

/* compiled from: StatFeedItem */
public class j {
    private String a;
    private String b;
    private int c;
    private String d = "";

    public void a(String str) {
        this.a = str;
    }

    public int a() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.d = str;
    }

    public String c() {
        return this.d;
    }

    public String toString() {
        String str = this.a + ":" + this.c;
        if (!TextUtils.isEmpty(this.b)) {
            str = str + DLConstants.DEPENDENCY_PACKAGE_DIV + this.b;
        }
        return str + DLConstants.DEPENDENCY_PACKAGE_DIV + this.d;
    }
}
