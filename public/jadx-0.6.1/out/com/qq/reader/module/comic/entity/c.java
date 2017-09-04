package com.qq.reader.module.comic.entity;

import com.google.gson.annotations.SerializedName;

/* compiled from: ComicColumnItemInfo */
public class c {
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
    @SerializedName("lastChapter")
    private int f;
    @SerializedName("isFinished")
    private boolean g;

    public long a() {
        return this.a;
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
        return this.f;
    }

    public boolean g() {
        return this.g;
    }
}
