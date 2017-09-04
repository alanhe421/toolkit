package com.qq.reader.readengine.kernel;

/* compiled from: ZLTextPosition */
public abstract class h implements Comparable<h> {
    public abstract int a();

    public abstract int b();

    public abstract int c();

    public /* synthetic */ int compareTo(Object obj) {
        return b((h) obj);
    }

    public boolean a(h hVar) {
        return a() == hVar.a() && b() == hVar.b() && c() == hVar.c();
    }

    public int b(h hVar) {
        int a = a() - hVar.a();
        if (a != 0) {
            return a;
        }
        a = b() - hVar.b();
        return a == 0 ? c() - hVar.c() : a;
    }

    public int hashCode() {
        return ((a() << 16) + (b() << 8)) + c();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        if (a() == hVar.a() && b() == hVar.b() && c() == hVar.c()) {
            return true;
        }
        return false;
    }
}
