package com.qq.reader.cservice.cloud;

import java.util.ArrayList;
import java.util.List;

/* compiled from: CloudSynTaskResult */
public class f {
    int a;
    long b;
    long c;
    long d;
    int e;
    int f;
    long g;
    String h = "";
    String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private int n;
    private int o = 0;
    private int p = 0;
    private String q = "";
    private int r = 0;
    private int s = 0;
    private ArrayList<g> t = new ArrayList();

    public f(int i, long j, int i2, long j2, long j3, int i3, long j4, String str) {
        this.a = i;
        this.b = j;
        this.e = i2;
        this.c = j2;
        this.g = j3;
        this.f = i3;
        this.d = j4;
        this.i = str;
    }

    public f(int i, long j, int i2, long j2) {
        this.a = i;
        this.b = j;
        this.e = i2;
        this.d = j2;
    }

    public void a(String str) {
        this.h = str;
    }

    public String a() {
        return this.h;
    }

    public int b() {
        return this.a;
    }

    public void a(long j) {
        this.c = j;
    }

    public long c() {
        return this.c;
    }

    public long d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public int f() {
        return this.f;
    }

    public long g() {
        return this.g;
    }

    public void b(String str) {
        this.j = str;
    }

    public void c(String str) {
        this.k = str;
    }

    public String h() {
        return this.i;
    }

    public void d(String str) {
        this.l = str;
    }

    public void e(String str) {
        this.m = str;
    }

    public void a(int i) {
        this.n = i;
    }

    public void b(int i) {
        this.p = i;
    }

    public String i() {
        return this.q;
    }

    public void f(String str) {
        this.q = str;
    }

    public void c(int i) {
        this.r = i;
    }

    public void d(int i) {
        this.s = i;
    }

    public void e(int i) {
        this.o = i;
    }

    public g j() {
        if (this.t == null || this.t.size() <= 0) {
            return null;
        }
        return (g) this.t.get(0);
    }

    public void a(g gVar) {
        if (this.t == null) {
            this.t = new ArrayList();
        } else {
            this.t.clear();
        }
        this.t.add(gVar);
    }

    public void a(List<g> list) {
        if (this.t == null) {
            this.t = new ArrayList();
        } else {
            this.t.clear();
        }
        this.t.addAll(list);
    }
}
