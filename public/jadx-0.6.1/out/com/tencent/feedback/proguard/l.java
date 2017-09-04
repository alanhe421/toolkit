package com.tencent.feedback.proguard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: RQDSRC */
public final class L extends j implements Cloneable {
    private static Map<String, String> A;
    private static Map<String, String> t;
    private static J u;
    private static I v;
    private static ArrayList<I> w;
    private static ArrayList<I> x;
    private static ArrayList<K> y;
    private static Map<String, String> z;
    public String a = "";
    public long b = 0;
    public String c = "";
    public String d = "";
    public String e = "";
    public String f = "";
    public String g = "";
    public Map<String, String> h = null;
    public String i = "";
    public int j = 0;
    public String k = "";
    public String l = "";
    public ArrayList<I> m = null;
    public ArrayList<I> n = null;
    public ArrayList<K> o = null;
    public Map<String, String> p = null;
    private J q = null;
    private I r = null;
    private Map<String, String> s = null;

    public final void a(i iVar) {
        iVar.a(this.a, 0);
        iVar.a(this.b, 1);
        iVar.a(this.c, 2);
        if (this.d != null) {
            iVar.a(this.d, 3);
        }
        if (this.e != null) {
            iVar.a(this.e, 4);
        }
        if (this.f != null) {
            iVar.a(this.f, 5);
        }
        if (this.g != null) {
            iVar.a(this.g, 6);
        }
        if (this.h != null) {
            iVar.a(this.h, 7);
        }
        if (this.i != null) {
            iVar.a(this.i, 8);
        }
        if (this.q != null) {
            iVar.a(this.q, 9);
        }
        iVar.a(this.j, 10);
        if (this.k != null) {
            iVar.a(this.k, 11);
        }
        if (this.l != null) {
            iVar.a(this.l, 12);
        }
        if (this.r != null) {
            iVar.a(this.r, 13);
        }
        if (this.m != null) {
            iVar.a(this.m, 14);
        }
        if (this.n != null) {
            iVar.a(this.n, 15);
        }
        if (this.o != null) {
            iVar.a(this.o, 16);
        }
        if (this.p != null) {
            iVar.a(this.p, 17);
        }
        if (this.s != null) {
            iVar.a(this.s, 18);
        }
    }

    public final void a(h hVar) {
        this.a = hVar.b(0, true);
        this.b = hVar.a(this.b, 1, true);
        this.c = hVar.b(2, true);
        this.d = hVar.b(3, false);
        this.e = hVar.b(4, false);
        this.f = hVar.b(5, false);
        this.g = hVar.b(6, false);
        if (t == null) {
            t = new HashMap();
            t.put("", "");
        }
        this.h = (Map) hVar.a(t, 7, false);
        this.i = hVar.b(8, false);
        if (u == null) {
            u = new J();
        }
        this.q = (J) hVar.a(u, 9, false);
        this.j = hVar.a(this.j, 10, false);
        this.k = hVar.b(11, false);
        this.l = hVar.b(12, false);
        if (v == null) {
            v = new I();
        }
        this.r = (I) hVar.a(v, 13, false);
        if (w == null) {
            w = new ArrayList();
            w.add(new I());
        }
        this.m = (ArrayList) hVar.a(w, 14, false);
        if (x == null) {
            x = new ArrayList();
            x.add(new I());
        }
        this.n = (ArrayList) hVar.a(x, 15, false);
        if (y == null) {
            y = new ArrayList();
            y.add(new K());
        }
        this.o = (ArrayList) hVar.a(y, 16, false);
        if (z == null) {
            z = new HashMap();
            z.put("", "");
        }
        this.p = (Map) hVar.a(z, 17, false);
        if (A == null) {
            A = new HashMap();
            A.put("", "");
        }
        this.s = (Map) hVar.a(A, 18, false);
    }

    public final void a(StringBuilder stringBuilder, int i) {
    }
}
