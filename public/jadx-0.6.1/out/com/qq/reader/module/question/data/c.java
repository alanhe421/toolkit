package com.qq.reader.module.question.data;

import com.qq.reader.module.question.a.a;

/* compiled from: FamousAuthorSayPackage */
public class c {
    public String a;
    private long b;
    private a c;
    private int d;

    public c(long j, int i, com.qq.reader.module.bookstore.qnative.c.a aVar) {
        this.b = 0;
        this.c = null;
        this.d = 0;
        this.a = "";
        this.c = new a();
        this.b = j;
        this.d = i;
        this.c.a(aVar);
    }

    public void a(boolean z) {
        this.c.a(z);
    }

    public boolean a() {
        return this.c.a();
    }

    public void a(long j) {
        this.b = j;
    }

    public long b() {
        if (this.b < 0) {
            return 0;
        }
        return this.b;
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.c.g().size();
    }

    public a e() {
        return this.c;
    }

    public String f() {
        return this.a;
    }

    public boolean g() {
        return this.c.c();
    }
}
