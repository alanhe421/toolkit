package com.qq.reader.module.comic.entity;

import com.google.gson.annotations.SerializedName;
import com.qq.reader.common.utils.ao;

/* compiled from: ComicSingleBookInfo */
public class q {
    @SerializedName("bid")
    private long a;
    @SerializedName("name")
    private String b;
    @SerializedName("author")
    private String c;
    @SerializedName("intro")
    private String d;
    @SerializedName("category")
    private String e;
    @SerializedName("desc")
    private String f;
    @SerializedName("sortValue")
    private int g;
    @SerializedName("sortType")
    private String h;
    @SerializedName("bookCover")
    private String i;
    @SerializedName("qurl")
    private String j;

    public long a() {
        return this.a;
    }

    public String a(int i, int i2) {
        return ao.a(this.a, i, i2);
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public int f() {
        return this.g;
    }

    public String g() {
        return this.h;
    }

    public String h() {
        return this.f;
    }

    public String i() {
        return this.i;
    }

    public String j() {
        return this.j;
    }
}
