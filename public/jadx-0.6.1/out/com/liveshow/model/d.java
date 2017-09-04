package com.liveshow.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* compiled from: MemberLiveEndDetail */
public class d {
    @SerializedName("icon")
    public String a;
    @SerializedName("authorName")
    public String b;
    @SerializedName("authorId")
    public String c;
    @SerializedName("timeSpan")
    public String d;
    @SerializedName("watchCount")
    public b e;
    @SerializedName("admireCount")
    public a f;
    @SerializedName("isFocus")
    public boolean g;
    @SerializedName("books")
    public List<e> h;

    /* compiled from: MemberLiveEndDetail */
    public static class a {
        @SerializedName("unit")
        public String a;
        @SerializedName("count")
        public String b;
    }

    /* compiled from: MemberLiveEndDetail */
    public static class b {
        @SerializedName("unit")
        public String a;
        @SerializedName("count")
        public String b;
    }
}
