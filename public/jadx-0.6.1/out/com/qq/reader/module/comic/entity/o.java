package com.qq.reader.module.comic.entity;

import com.google.gson.annotations.SerializedName;
import com.qrcomic.util.j;

/* compiled from: ComicSectionInfo */
public class o {
    public int a;
    @SerializedName("cmid")
    private String b;
    @SerializedName("chid")
    private String c;
    @SerializedName("huaTitle")
    private String d;
    @SerializedName("huaSort")
    private int e;
    @SerializedName("huaPages")
    private int f;
    @SerializedName("amount")
    private String g;
    @SerializedName("size")
    private int h;
    @SerializedName("payType")
    private int i;
    private boolean j;

    public boolean a() {
        return this.j;
    }

    public void a(boolean z) {
        this.j = z;
    }

    public int b() {
        return this.e;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.c;
    }

    public String e() {
        return this.d;
    }

    public int f() {
        if (i()) {
            return 0;
        }
        return Integer.valueOf(this.g).intValue();
    }

    public long g() {
        return (long) this.h;
    }

    public String h() {
        return "话";
    }

    public boolean i() {
        return this.i == 2;
    }

    public int j() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    public int k() {
        return this.f;
    }

    public String l() {
        return j.a(this.b, this.c);
    }
}
