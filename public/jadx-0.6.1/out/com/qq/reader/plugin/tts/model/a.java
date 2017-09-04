package com.qq.reader.plugin.tts.model;

import com.qq.reader.readengine.kernel.g;

/* compiled from: BufferHolder */
public abstract class a {
    private String a;
    private g b;
    private String c;
    private int d;

    public a(String str, g gVar, String str2) {
        this.a = str;
        this.b = gVar;
        this.c = str2;
    }

    public void a(int i) {
        this.d = i;
    }

    public String a() {
        return this.a;
    }

    public g b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }
}
