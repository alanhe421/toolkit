package com.qq.reader.module.comic.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* compiled from: ComicStoreEntranceInfo */
public class r {
    @SerializedName("adList")
    private List<a> a;

    /* compiled from: ComicStoreEntranceInfo */
    public static class a {
        @SerializedName("id")
        private int a;
        @SerializedName("title")
        private String b;
        @SerializedName("actionUrl")
        private String c;
        @SerializedName("imageUrl")
        private String d;

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
            return this.d;
        }
    }

    public List<a> a() {
        return this.a;
    }

    public int b() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }
}
