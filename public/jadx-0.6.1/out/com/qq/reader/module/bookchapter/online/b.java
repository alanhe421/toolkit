package com.qq.reader.module.bookchapter.online;

import com.qq.reader.cservice.buy.chapter.d;

/* compiled from: ChapterBatDownloadAdapter */
class b {
    private d a;
    private boolean b = false;
    private boolean c = true;

    public b(d dVar) {
        this.a = dVar;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public boolean a() {
        return this.c;
    }

    public void b() {
        this.b = !this.b;
    }

    public boolean c() {
        return this.b;
    }

    public String d() {
        return this.a.b();
    }

    public float e() {
        return this.a.c();
    }

    public float f() {
        return this.a.d();
    }

    public int g() {
        return this.a.e();
    }

    public d h() {
        return this.a;
    }

    public boolean i() {
        return this.a.f();
    }

    public boolean j() {
        return this.a.i();
    }

    public boolean k() {
        return this.a.h();
    }
}
