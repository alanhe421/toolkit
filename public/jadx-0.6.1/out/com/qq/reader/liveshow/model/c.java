package com.qq.reader.liveshow.model;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.qq.reader.liveshow.utils.SxbLog;

/* compiled from: MySelfInfo */
public class c {
    private static final String a = c.class.getSimpleName();
    private static boolean g = false;
    private static c s = new c();
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private boolean h;
    private int i;
    private int j;
    private boolean k;
    private long l;
    private int m;
    private boolean n;
    private String o;
    private int p;
    private int q = -1;
    private boolean r = true;

    public static c a() {
        return s;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public String d() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public String e() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public int f() {
        return this.q;
    }

    public void a(int i) {
        this.q = i;
        SxbLog.e("MINFO", "set my room num " + i);
    }

    public boolean g() {
        return this.k;
    }

    public void a(boolean z) {
        this.k = z;
    }

    public void a(Context context) {
        Editor edit = context.getSharedPreferences("user_info", 0).edit();
        edit.clear();
        edit.commit();
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        g = false;
        this.h = false;
        this.i = 6;
        this.j = 0;
        this.k = false;
        this.l = 0;
        this.m = 0;
        this.p = 0;
        this.q = -1;
        this.r = true;
        this.n = false;
        this.o = "";
    }

    public int h() {
        return this.p;
    }

    public void b(int i) {
        this.p = i;
    }

    public boolean i() {
        return this.p == 1;
    }

    public boolean j() {
        return g;
    }

    public void b(boolean z) {
        g = z;
    }

    public void c(boolean z) {
        this.r = z;
    }

    public boolean k() {
        return this.r;
    }

    public void c(int i) {
        this.i = i;
    }

    public int l() {
        if (this.i == 0) {
            this.i = 6;
        }
        return this.i;
    }

    public void a(long j) {
        this.l = j;
    }

    public long m() {
        return this.l;
    }

    public int n() {
        return this.m;
    }

    public void d(int i) {
        this.m = i;
    }

    public void e(int i) {
        this.j = i;
    }

    public String o() {
        return this.o;
    }

    public void e(String str) {
        this.o = str;
    }

    public boolean p() {
        return this.n;
    }

    public void d(boolean z) {
        this.n = z;
    }
}
