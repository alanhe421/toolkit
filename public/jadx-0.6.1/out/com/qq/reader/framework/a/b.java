package com.qq.reader.framework.a;

import com.qq.reader.common.db.handle.t;
import java.util.List;

/* compiled from: NoteInfo */
public class b {
    private long a = 0;
    private int b = 0;
    private int c = 0;
    private int d;
    private String e = "";
    private String f = "";
    private long g = 0;
    private int h = 0;
    private List<com.qq.reader.readengine.model.b> i;

    public long a() {
        return this.a;
    }

    public void a(long j) {
        this.a = j;
    }

    public int b() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public void b(int i) {
        this.d = i;
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public List<com.qq.reader.readengine.model.b> e() {
        return this.i;
    }

    public void a(List<com.qq.reader.readengine.model.b> list) {
        this.i = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this.a != ((b) obj).a) {
            return false;
        }
        return true;
    }

    public String f() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public long g() {
        return this.g;
    }

    public void b(long j) {
        this.g = j;
    }

    public String h() {
        return this.f;
    }

    public void b(String str) {
        this.f = str;
    }

    public int i() {
        return this.h;
    }

    public void d(int i) {
        this.h = i;
    }

    public boolean a(t tVar) {
        if (!(tVar == null || e() == null || e().size() <= 0)) {
            for (com.qq.reader.readengine.model.b bVar : e()) {
                long a = tVar.a(this.f, bVar);
                if (a < 0) {
                    return false;
                }
                bVar.c(a);
            }
        }
        return true;
    }
}
