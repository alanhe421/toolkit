package com.qq.reader.module.comic.entity;

import com.google.gson.annotations.SerializedName;

/* compiled from: ComicDetailHeaderItem */
public class h {
    @SerializedName("sectionCount")
    public int a;
    @SerializedName("priceDesc")
    public String b;
    @SerializedName("isVisiblePrice")
    boolean c;
    @SerializedName("cid")
    private String d;
    @SerializedName("title")
    private String e;
    @SerializedName("catName")
    private String f;
    @SerializedName("author")
    private String g;
    @SerializedName("headUrl")
    private String h;
    @SerializedName("buyType")
    private int i;
    @SerializedName("isPay")
    private boolean j;
    @SerializedName("price")
    private int k;
    @SerializedName("status")
    private int l;
    @SerializedName("disInfo")
    private a m;
    @SerializedName("monthInfo")
    private c n;
    @SerializedName("guide")
    private b o;
    @SerializedName("firstSid")
    private String p;

    /* compiled from: ComicDetailHeaderItem */
    public static class a {
        @SerializedName("discount")
        private int a;
        @SerializedName("disEnd")
        private long b;

        public int a() {
            return this.a;
        }

        public long b() {
            return this.b;
        }
    }

    /* compiled from: ComicDetailHeaderItem */
    public static class b {
        @SerializedName("desc")
        private String a;
        @SerializedName("url")
        private String b;

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }
    }

    /* compiled from: ComicDetailHeaderItem */
    public static class c {
        @SerializedName("monthly")
        private int a;

        public boolean a() {
            return this.a == 1 || this.a == 3;
        }
    }

    public String a() {
        return this.d;
    }

    public String b() {
        return this.e;
    }

    public String c() {
        return this.f;
    }

    public String d() {
        return this.g;
    }

    public String e() {
        return this.h;
    }

    public String f() {
        return this.b;
    }

    public int g() {
        return this.a;
    }

    public String h() {
        return this.p;
    }

    public String i() {
        if (this.i == 2) {
            return "本";
        }
        return "话";
    }

    public boolean j() {
        return this.j;
    }

    public int k() {
        return this.k;
    }

    public a l() {
        return this.m;
    }

    public int m() {
        return this.l;
    }

    public boolean n() {
        return this.c;
    }

    public c o() {
        return this.n;
    }

    public b p() {
        return this.o;
    }
}
