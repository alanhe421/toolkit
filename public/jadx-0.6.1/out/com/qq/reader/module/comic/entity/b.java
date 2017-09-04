package com.qq.reader.module.comic.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* compiled from: ComicColumnInfo */
public class b {
    @SerializedName("cid")
    private int a;
    @SerializedName("title")
    private String b;
    @SerializedName("subTitle")
    private String c;
    @SerializedName("columnIcon")
    private String d;
    @SerializedName("pushName")
    private String e;
    @SerializedName("totalBooks")
    private int f;
    @SerializedName("bookList")
    private List<q> g;
    @SerializedName("qurl")
    private String h;

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.d;
    }

    public List<q> f() {
        return this.g;
    }

    public int g() {
        if (this.g == null || this.g.isEmpty()) {
            return 0;
        }
        return this.g.size();
    }

    public int h() {
        return this.f;
    }

    public String i() {
        return this.h;
    }
}
