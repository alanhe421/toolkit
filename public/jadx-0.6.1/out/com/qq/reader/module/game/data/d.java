package com.qq.reader.module.game.data;

import android.graphics.Color;

/* compiled from: GameMainDialogEvent */
public class d {
    private String a;
    private String b;
    private String c;
    private c d;

    public d(String str, String str2, String str3, c cVar) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = cVar;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return Color.parseColor(this.c);
    }

    public c d() {
        return this.d;
    }
}
