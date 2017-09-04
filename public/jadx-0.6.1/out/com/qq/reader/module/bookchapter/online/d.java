package com.qq.reader.module.bookchapter.online;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;

/* compiled from: OnlineBook */
public class d {
    private int A;
    private String B;
    private String C;
    private String D;
    private int E;
    private String F;
    private String G;
    private String H;
    private int I;
    private String J;
    private String K;
    private int L;
    private String M;
    private String N;
    private String O;
    private String P;
    private long Q;
    private String R;
    private String S;
    private String T;
    private String U;
    private boolean V;
    private int W;
    private String X;
    private int Y;
    private int Z;
    private long a = -1;
    private long aa;
    private String ab;
    private String ac;
    private int ad;
    private String ae;
    private String af;
    private int ag;
    private int ah;
    private SparseArray<Integer> ai = new SparseArray();
    private String b;
    private String c;
    private String d;
    private int e;
    private int f = 100;
    private String g;
    private long h;
    private int i;
    private int j;
    private String k;
    private int l;
    private long m;
    private List<Object> n;
    private int o;
    private List<com.qq.reader.common.charge.voucher.a.d> p = new ArrayList();
    private String q;
    private int r;
    private List<OnlineChapter> s;
    private List<j> t;
    private int u;
    private int v;
    private int w;
    private boolean x;
    private int y;
    private String z;

    public List<j> a() {
        return this.t;
    }

    public void a(List<j> list) {
        this.t = list;
    }

    public List<OnlineChapter> b() {
        return this.s;
    }

    public void b(List<OnlineChapter> list) {
        this.s = list;
    }

    public void a(SparseArray<Integer> sparseArray) {
        this.ai = sparseArray;
    }

    public SparseArray<Integer> c() {
        return this.ai;
    }

    public void c(List<OnlineChapter> list) {
        for (OnlineChapter onlineChapter : list) {
            this.s.add(onlineChapter);
            this.ai.append((int) onlineChapter.getUUID(), Integer.valueOf(onlineChapter.getChapterId()));
        }
    }

    public String d() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String e() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public int f() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public long g() {
        return this.a;
    }

    public void a(long j) {
        this.a = j;
    }

    public int h() {
        return this.r;
    }

    public void b(int i) {
        this.r = i;
    }

    public long i() {
        return this.h;
    }

    public void b(long j) {
        this.h = j;
    }

    public int j() {
        return this.i;
    }

    public void c(int i) {
        this.i = i;
    }

    public int k() {
        return this.j;
    }

    public void d(int i) {
        this.j = i;
    }

    public String l() {
        return this.k;
    }

    public void c(String str) {
        this.k = str;
    }

    public List<Object> m() {
        return this.n;
    }

    public void d(String str) {
        this.O = str;
    }

    public void e(String str) {
        this.P = str;
    }

    public void f(String str) {
        this.R = str;
    }

    public String n() {
        return this.S;
    }

    public void g(String str) {
        this.S = str;
    }

    public void h(String str) {
        this.N = str;
    }

    public long o() {
        return this.Q;
    }

    public void c(long j) {
        this.Q = j;
    }

    public void i(String str) {
        this.T = str;
    }

    public int p() {
        return this.W;
    }

    public void e(int i) {
        this.W = i;
    }

    public void j(String str) {
        this.U = str;
    }

    public void a(boolean z) {
        this.V = z;
    }

    public boolean q() {
        return this.V;
    }

    public int r() {
        return this.Y;
    }

    public void f(int i) {
        this.Y = i;
    }

    public int s() {
        return this.Z;
    }

    public void g(int i) {
        this.Z = i;
    }

    public long t() {
        return this.aa;
    }

    public void d(long j) {
        this.aa = j;
    }

    public void k(String str) {
        this.ab = str;
    }

    public void l(String str) {
        this.ac = str;
    }

    public String u() {
        return this.ac;
    }

    public boolean v() {
        return "19200".equals(this.ac);
    }

    public void a(ArrayList<com.qq.reader.common.charge.voucher.a.d> arrayList) {
        this.p.clear();
        this.p.addAll(arrayList);
    }

    public void h(int i) {
        this.f = i;
    }

    public void i(int i) {
        this.ad = i;
    }

    public int w() {
        return this.ad;
    }

    public int x() {
        return this.f;
    }

    public int y() {
        return this.o;
    }

    public void j(int i) {
        this.u = i;
    }

    public int z() {
        return this.v;
    }

    public void k(int i) {
        this.v = i;
    }

    public void l(int i) {
        this.w = i;
    }

    public int A() {
        return this.w;
    }

    public String B() {
        return this.g;
    }

    public void m(String str) {
        this.g = str;
    }

    public int C() {
        return this.y;
    }

    public void m(int i) {
        this.y = i;
    }

    public String D() {
        return this.z;
    }

    public void n(String str) {
        this.z = str;
    }

    public int E() {
        return this.A;
    }

    public void n(int i) {
        this.A = i;
    }

    public String F() {
        return this.B;
    }

    public void o(String str) {
        this.B = str;
    }

    public void p(String str) {
        this.C = str;
    }

    public String G() {
        return this.c;
    }

    public void q(String str) {
        this.c = str;
    }

    public void r(String str) {
        this.D = str;
    }

    public void o(int i) {
        this.E = i;
    }

    public void s(String str) {
        try {
            this.F = str.split(" ")[0];
        } catch (Exception e) {
            e.printStackTrace();
            this.F = str;
        }
    }

    public void t(String str) {
        this.G = str;
    }

    public void u(String str) {
        this.H = str;
    }

    public int H() {
        return this.I;
    }

    public void p(int i) {
        this.I = i;
    }

    public String I() {
        return this.J;
    }

    public void v(String str) {
        this.J = str;
    }

    public void w(String str) {
        this.K = str;
    }

    public String J() {
        return this.K;
    }

    public int K() {
        return this.L;
    }

    public void q(int i) {
        this.L = i;
    }

    public String L() {
        return this.M;
    }

    public void x(String str) {
        this.M = str;
    }

    public List<com.qq.reader.common.charge.voucher.a.d> M() {
        return this.p;
    }

    public String N() {
        return this.q;
    }

    public void y(String str) {
        this.q = str;
    }

    public boolean O() {
        return this.x;
    }

    public void b(boolean z) {
        this.x = z;
    }

    public int P() {
        return this.l;
    }

    public void r(int i) {
        this.l = i;
    }

    public long Q() {
        return this.m;
    }

    public void e(long j) {
        this.m = j;
    }

    public void z(String str) {
        this.ae = str;
    }

    public String R() {
        return this.ae;
    }

    public void A(String str) {
        this.af = str;
    }

    public String S() {
        return this.af;
    }

    public void B(String str) {
        this.X = str;
    }

    public String T() {
        return this.X;
    }

    public void s(int i) {
        this.ag = i;
    }

    public int U() {
        return this.ag;
    }

    public void t(int i) {
        this.ah = i;
    }

    public int V() {
        return this.ah;
    }
}
