package com.qq.reader.liveshow.model;

import com.google.gson.annotations.SerializedName;

/* compiled from: ServerUser */
public class e {
    @SerializedName("code")
    public int a;
    @SerializedName("isVip")
    public boolean b;
    @SerializedName("sig")
    public String c;
    @SerializedName("identifier")
    public String d;
    @SerializedName("level")
    public int e;
    @SerializedName("ext")
    public a f;
    @SerializedName("hostUid")
    public String g;
    @SerializedName("title")
    public String h;

    /* compiled from: ServerUser */
    public class a {
        @SerializedName("vipLevel")
        public int a;
        @SerializedName("authorId")
        public long b;
        @SerializedName("authorAvatar")
        public String c;
        @SerializedName("authorName")
        public String d;
    }

    public String toString() {
        return "ServerUser{code=" + this.a + ", isVip=" + this.b + ", sig='" + this.c + '\'' + ", identifier='" + this.d + '\'' + ", level=" + this.e + ", ext=" + this.f + ", hostUid='" + this.g + '\'' + ", title='" + this.h + '\'' + '}';
    }
}
