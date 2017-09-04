package com.qq.reader.module.qmessage.data;

import com.qq.reader.module.bookstore.qnative.c.a;

/* compiled from: MessagePackage */
public class c {
    public String a;
    private long b;
    private d c;
    private int d;
    private int e;

    public c(long j, int i, a aVar) {
        this.b = 0;
        this.c = null;
        this.d = 1;
        this.e = 0;
        this.a = "";
        this.c = new d();
        this.b = j;
        this.e = i;
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
        return this.e;
    }

    public int d() {
        return this.d;
    }

    public void a(int i) {
        if (1 == i || 2 == i) {
            this.d = i;
        }
    }

    public int e() {
        return this.c.e().size();
    }

    public d f() {
        return this.c;
    }

    public String g() {
        return this.a;
    }
}
