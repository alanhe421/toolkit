package com.qq.reader.module.comic.activity;

import com.google.gson.annotations.SerializedName;

public class NativeBookStoreComicSectionPayActivity$a {
    @SerializedName("cid")
    String a;
    @SerializedName("sid")
    String b;
    @SerializedName("price")
    int c;
    @SerializedName("disPrice")
    int d;
    @SerializedName("buyType")
    int e;
    @SerializedName("disMsg")
    String f;
    @SerializedName("visible")
    boolean g;

    public int a() {
        return Math.min(this.c, this.d);
    }
}
