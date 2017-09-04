package com.qrcomic.a;

import android.text.TextUtils;

/* compiled from: QRAbsTask */
public abstract class d implements b, Comparable<d>, Runnable {
    private b a = this;
    private int b = 2;
    private String c;
    private int d = 1;

    public /* synthetic */ int compareTo(Object obj) {
        return a((d) obj);
    }

    public int a(d dVar) {
        if (dVar == null) {
            return 1;
        }
        if (e() == dVar.e()) {
            return 0;
        }
        if (e() >= dVar.e()) {
            return -1;
        }
        return 1;
    }

    public int e() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public void a(b bVar) {
        if (bVar != null) {
            this.a = bVar;
        }
    }

    public b f() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return super.equals(obj);
        }
        boolean z;
        if (TextUtils.isEmpty(this.c) || TextUtils.isEmpty(((d) obj).c)) {
            z = false;
        } else {
            z = this.c.equals(((d) obj).c);
        }
        if (((d) obj).b == this.b && r0) {
            return true;
        }
        return false;
    }

    public void b(int i) {
        this.d = i;
    }

    public int g() {
        return this.d;
    }

    public void a() {
    }

    public void b() {
    }

    public void c() {
    }

    public void d() {
    }
}
