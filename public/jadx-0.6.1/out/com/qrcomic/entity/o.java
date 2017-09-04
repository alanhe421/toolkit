package com.qrcomic.entity;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

/* compiled from: ServerResponse */
public class o<T> {
    public static int a = 0;
    public static String b = "本地数据";
    @SerializedName("code")
    public int c;
    @SerializedName("message")
    public String d;
    @SerializedName("data")
    public T e;

    /* compiled from: ServerResponse */
    public static class a {
        @SerializedName("comicId")
        public String a;
        @SerializedName("sectionId")
        public String b;
        @SerializedName("picId")
        public String c;
        @SerializedName("imgUrl")
        public String d;
        @SerializedName("headers")
        public Map<String, String> e;

        public String toString() {
            return "RealImageData{comicId='" + this.a + '\'' + ", sectionId='" + this.b + '\'' + ", picId='" + this.c + '\'' + ", imgUrl='" + this.d + '\'' + '}';
        }
    }
}
