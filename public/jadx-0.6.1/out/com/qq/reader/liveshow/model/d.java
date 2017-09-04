package com.qq.reader.liveshow.model;

import com.google.gson.annotations.SerializedName;

/* compiled from: ResponseData */
public class d {

    /* compiled from: ResponseData */
    public static class a {
        @SerializedName("code")
        public int a;
    }

    /* compiled from: ResponseData */
    public static class b extends a {
        @SerializedName("balance")
        public int b;
    }

    /* compiled from: ResponseData */
    public static class c extends a {
        @SerializedName("hostIncome")
        public int b;
        @SerializedName("timeSpan")
        public int c;
        @SerializedName("admireCount")
        public int d;
    }

    /* compiled from: ResponseData */
    public static class d extends a {
    }

    /* compiled from: ResponseData */
    public static class e extends a {
    }

    /* compiled from: ResponseData */
    public static class f {
        @SerializedName("code")
        public int a = -1;
        @SerializedName("realCount")
        public int b = 0;
    }
}
