package com.tencent.feedback.proguard;

import android.util.SparseArray;

/* compiled from: RQDSRC */
public final class w {
    public static boolean a = false;
    private SparseArray<a> b;
    private String c;
    private int d;
    private int e;
    private String f;
    private String g;
    private boolean h;
    private boolean i;

    /* compiled from: RQDSRC */
    public static class a {
        private String a = "http://monitor.uu.qq.com/analytics/rqdsync";
        private boolean b = false;
        private boolean c = true;
        private boolean d = false;

        public a(int i) {
        }

        public final synchronized String a() {
            return this.a;
        }

        public final synchronized boolean b() {
            return this.b;
        }

        public final synchronized void a(boolean z) {
            this.b = z;
        }

        public final synchronized boolean c() {
            return this.c;
        }

        public final synchronized boolean d() {
            return false;
        }
    }

    public w() {
        this.b = null;
        this.c = "http://monitor.uu.qq.com/analytics/rqdsync";
        this.d = -1;
        this.e = 6;
        this.f = "";
        this.g = "";
        this.h = true;
        this.i = false;
        this.b = new SparseArray(5);
        this.b.append(3, new a(3));
        this.b = this.b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("S(@L@L").append("@)");
        this.g = stringBuilder.toString();
        stringBuilder.setLength(0);
        stringBuilder.append("*^@K#K").append("@!");
        this.f = stringBuilder.toString();
    }

    public final synchronized String a() {
        return this.c;
    }

    public final synchronized void a(String str) {
        this.c = str;
    }

    public final synchronized int b() {
        return this.d;
    }

    public final synchronized void a(int i) {
        this.d = i;
    }

    public final synchronized int c() {
        return this.e;
    }

    public final synchronized void b(int i) {
        if (i > 0) {
            this.e = i;
        }
    }

    public final synchronized String d() {
        return this.f;
    }

    public final synchronized String e() {
        return this.g;
    }

    public final synchronized boolean f() {
        return this.h;
    }

    public final synchronized void a(boolean z) {
        this.h = z;
    }

    public final synchronized a c(int i) {
        a aVar;
        if (this.b != null) {
            aVar = (a) this.b.get(i);
        } else {
            aVar = null;
        }
        return aVar;
    }

    public final synchronized boolean g() {
        return this.i;
    }

    public final synchronized void b(boolean z) {
        this.i = z;
    }
}
