package com.liveshow.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* compiled from: AuthorDetail */
public class a {
    @SerializedName("label")
    public int a;
    @SerializedName("categoryName")
    public String b;
    @SerializedName("isFocus")
    public boolean c;
    @SerializedName("content")
    public String d;
    @SerializedName("totalWords")
    public b e;
    @SerializedName("fansCount")
    public a f;
    @SerializedName("userLiveLevel")
    public int g;
    @SerializedName("ownerLiveLevel")
    public int h;
    @SerializedName("shield")
    public int i;
    @SerializedName("books")
    public List<e> j;

    /* compiled from: AuthorDetail */
    public static class a {
        @SerializedName("unit")
        public String a;
        @SerializedName("count")
        public String b;
    }

    /* compiled from: AuthorDetail */
    public static class b {
        @SerializedName("unit")
        public String a;
        @SerializedName("count")
        public String b;
    }
}
