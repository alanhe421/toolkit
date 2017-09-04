package com.qq.reader.filebrowser.view;

import com.qq.reader.filebrowser.b;
import java.io.File;
import java.text.Collator;
import java.util.Locale;

/* compiled from: IconifiedText */
public class a implements Comparable<a> {
    public File a = null;
    private String b;
    private Collator c = Collator.getInstance(Locale.US);
    private String d = "";
    private String e = "";
    private String f = "";
    private int g;
    private boolean h = false;
    private int i;
    private String j;

    public /* synthetic */ int compareTo(Object obj) {
        return a((a) obj);
    }

    public String a() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public a(String str, int i, String str2, String str3) {
        this.g = i;
        this.e = str;
        this.j = b.a(this.e);
        this.d = str2;
        this.f = str3;
    }

    public File b() {
        return this.a;
    }

    public void a(File file) {
        this.a = file;
    }

    public void a(int i) {
        this.i = i;
    }

    public int c() {
        return this.i;
    }

    public String d() {
        return this.e;
    }

    public void b(int i) {
        this.g = i;
    }

    public int e() {
        return this.g;
    }

    public String f() {
        return this.d;
    }

    public String toString() {
        return this.e;
    }

    public String g() {
        return this.f;
    }

    public int a(a aVar) {
        if (this.e != null) {
            return this.c.compare(this.j, aVar.h());
        }
        throw new IllegalArgumentException();
    }

    public String h() {
        return this.j;
    }
}
