package com.qrcomic.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* compiled from: QRComicBuyInfo */
public class l extends j {
    @SerializedName("comicId")
    public String a;
    @SerializedName("isAutoBuy")
    public int b;
    @SerializedName("isBuyBook")
    public int c;
    @SerializedName("buyJson")
    public String d;
    @SerializedName("qrSectionBuyStatusList")
    public List<n> e;
    public boolean f;
    private Long g;
    private String h;

    public l(Long l, String str, String str2, int i, int i2, String str3) {
        this.g = l;
        this.h = str;
        this.a = str2;
        this.b = i;
        this.c = i2;
        this.d = str3;
    }

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public int b() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public List<n> c() {
        return this.e;
    }

    public Long d() {
        return this.g;
    }

    public void a(long j) {
        this.g = Long.valueOf(j);
    }

    public String e() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public String f() {
        return this.h;
    }

    public void c(String str) {
        this.h = str;
    }

    public void a(Long l) {
        this.g = l;
    }

    public int g() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public boolean h() {
        return this.c == 1;
    }

    public void i() {
        this.c = 1;
    }

    public String toString() {
        return "QRComicBuyInfo{_id=" + this.g + ", uid='" + this.h + '\'' + ", comicId='" + this.a + '\'' + ", isAutoBuy=" + this.b + ", buyTheBook=" + this.c + ", buyJson='" + this.d + '\'' + ", sectionBuyStatusList=" + this.e + '}';
    }
}
