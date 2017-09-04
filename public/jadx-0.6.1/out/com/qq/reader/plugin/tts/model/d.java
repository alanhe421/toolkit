package com.qq.reader.plugin.tts.model;

import com.qq.reader.readengine.kernel.g;

/* compiled from: TtsInputHolder */
public class d {
    public static final String[] a = new String[]{"", "您已经听完该书", "本章需要登录，请登录", "网络不畅，章节获取失败，请联网重试", "本章需要购买，请先购买", "本章需要开通会员，请先开通会员"};
    private int b;
    private String c = "";
    private g d;
    private g e;
    private boolean f;
    private int g;

    public int a() {
        return this.b;
    }

    public void a(String str, g gVar, g gVar2) {
        this.c = str;
        this.d = gVar;
        this.e = gVar2;
        this.b = 0;
    }

    public void a(int i) {
        this.b = i;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public void b(int i) {
        this.g = i;
    }

    public boolean b() {
        return this.f;
    }

    public int c() {
        return this.g;
    }

    public g d() {
        return this.d;
    }

    public g e() {
        return this.e;
    }

    public String f() {
        return this.c;
    }
}
