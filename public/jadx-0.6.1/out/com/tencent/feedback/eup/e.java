package com.tencent.feedback.eup;

import com.tencent.av.config.ConfigBaseParser;
import com.tencent.feedback.common.PlugInInfo;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* compiled from: RQDSRC */
public class e implements Serializable {
    private long A = -1;
    private long B = -1;
    private long C = -1;
    private long D = -1;
    private String E;
    private byte[] F;
    private Map<String, PlugInInfo> G;
    private String H;
    private boolean I;
    private String J;
    private String K = "";
    private String L = "";
    private String M = ConfigBaseParser.DEFAULT_VALUE;
    private String N = "";
    private String O = "";
    private String P = "";
    private final Map<String, String> Q = new HashMap();
    private long R = -1;
    public int a = -1;
    public int b = -1;
    public Map<String, String> c = null;
    public Map<String, String> d = null;
    private long e = -1;
    private byte f = (byte) 0;
    private boolean g = false;
    private boolean h = false;
    private int i = 0;
    private String j = "";
    private String k;
    private String l;
    private String m;
    private String n;
    private long o;
    private String p;
    private int q;
    private byte[] r;
    private String s;
    private String t;
    private String u;
    private String v;
    private long w = -1;
    private long x = -1;
    private long y = -1;
    private long z = -1;

    public e() {
        try {
            this.H = UUID.randomUUID().toString();
        } catch (Throwable th) {
            if (!(com.tencent.feedback.common.e.a(th) || com.tencent.feedback.common.e.a(th))) {
                th.printStackTrace();
            }
            this.H = ConfigBaseParser.DEFAULT_VALUE;
        }
    }

    public final long a() {
        return this.e;
    }

    public final void a(long j) {
        this.e = j;
    }

    public final boolean b() {
        return this.f == (byte) 0;
    }

    public final boolean c() {
        return this.g;
    }

    public final void a(boolean z) {
        this.g = true;
    }

    public final boolean d() {
        return this.f == (byte) 2;
    }

    public final String e() {
        return this.k;
    }

    public final void a(String str) {
        this.k = str;
    }

    public final String f() {
        return this.l;
    }

    public final void b(String str) {
        this.l = str;
    }

    public final String g() {
        return this.m;
    }

    public final void c(String str) {
        this.m = str;
    }

    public final String h() {
        return this.n;
    }

    public final void d(String str) {
        this.n = str;
    }

    public final long i() {
        return this.o;
    }

    public final void b(long j) {
        this.o = j;
    }

    public final void a(float f) {
    }

    public final int j() {
        return this.i;
    }

    public final void a(int i) {
        this.i = i;
    }

    public final String k() {
        return this.j;
    }

    public final void e(String str) {
        this.j = str;
    }

    public final String l() {
        return this.p;
    }

    public final void f(String str) {
        this.p = str;
    }

    public final int m() {
        return this.q;
    }

    public final void b(int i) {
        this.q = i;
    }

    public final byte[] n() {
        return this.r;
    }

    public final void a(byte[] bArr) {
        this.r = bArr;
    }

    public final String o() {
        return this.s;
    }

    public final void g(String str) {
        this.s = str;
    }

    public final String p() {
        return this.t;
    }

    public final void h(String str) {
        this.t = str;
    }

    public final String q() {
        return this.u;
    }

    public final void i(String str) {
        this.u = str;
    }

    public final String r() {
        return this.v;
    }

    public final void j(String str) {
        this.v = str;
    }

    public final String s() {
        return this.E;
    }

    public final void k(String str) {
        this.E = str;
    }

    public final byte[] t() {
        return this.F;
    }

    public final void b(byte[] bArr) {
        this.F = bArr;
    }

    public final Map<String, PlugInInfo> u() {
        return this.G;
    }

    public final void a(Map<String, PlugInInfo> map) {
        this.G = map;
    }

    public final String v() {
        return this.H;
    }

    public final void l(String str) {
        this.H = str;
    }

    public final boolean w() {
        return this.I;
    }

    public final void b(boolean z) {
        this.I = true;
    }

    public final boolean x() {
        return this.f == (byte) 3;
    }

    public final String y() {
        return this.J;
    }

    public final void m(String str) {
        this.J = str;
    }

    public final String z() {
        return this.K;
    }

    public final void n(String str) {
        this.K = str;
    }

    public final String A() {
        return this.L;
    }

    public final void o(String str) {
        this.L = str;
    }

    public final String B() {
        return this.M;
    }

    public final void p(String str) {
        this.M = str;
    }

    public final Map<String, String> C() {
        return this.Q;
    }

    public final String D() {
        return this.N;
    }

    public final void q(String str) {
        this.N = str;
    }

    public final boolean E() {
        return this.h;
    }

    public final void c(boolean z) {
        this.h = true;
    }

    public final long F() {
        return this.w;
    }

    public final void c(long j) {
        this.w = j;
    }

    public final long G() {
        return this.x;
    }

    public final void d(long j) {
        this.x = j;
    }

    public final long H() {
        return this.y;
    }

    public final void e(long j) {
        this.y = j;
    }

    public final long I() {
        return this.z;
    }

    public final void f(long j) {
        this.z = j;
    }

    public final long J() {
        return this.A;
    }

    public final void g(long j) {
        this.A = j;
    }

    public final long K() {
        return this.B;
    }

    public final void h(long j) {
        this.B = j;
    }

    public final long L() {
        return this.C;
    }

    public final void i(long j) {
        this.C = j;
    }

    public final long M() {
        return this.D;
    }

    public final void j(long j) {
        this.D = j;
    }

    public final String N() {
        return this.O;
    }

    public final void r(String str) {
        this.O = str;
    }

    public final String O() {
        return this.P;
    }

    public final void s(String str) {
        this.P = str;
    }

    public final byte P() {
        return this.f;
    }

    public final void a(byte b) {
        this.f = b;
    }

    public final long Q() {
        return this.R;
    }

    public final void k(long j) {
        this.R = j;
    }

    public final int R() {
        return this.a;
    }

    public final void c(int i) {
        this.a = i;
    }

    public final int S() {
        return this.b;
    }

    public final void d(int i) {
        this.b = i;
    }

    public final Map<String, String> T() {
        return this.c;
    }

    public final void b(Map<String, String> map) {
        this.c = map;
    }

    public final Map<String, String> U() {
        return this.d;
    }

    public final void c(Map<String, String> map) {
        this.d = map;
    }
}
