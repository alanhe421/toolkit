package com.qq.reader.module.bookchapter.online;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: ChapterBatDownloadAdapter */
class c {
    private String a;
    private List<b> b = Collections.synchronizedList(new ArrayList());
    private boolean c = false;
    private boolean d = true;

    public c(String str) {
        this.a = str;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public boolean a() {
        return this.d;
    }

    public void b() {
        this.c = !this.c;
    }

    public boolean c() {
        return this.c;
    }

    public String d() {
        return this.a;
    }

    public void a(b bVar) {
        this.b.add(bVar);
    }

    public int e() {
        return this.b.size();
    }

    public b a(int i) {
        return (b) this.b.get(i);
    }
}
