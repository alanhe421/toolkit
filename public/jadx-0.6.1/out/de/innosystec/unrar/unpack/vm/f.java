package de.innosystec.unrar.unpack.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/* compiled from: VMPreparedProgram */
public class f {
    private List<d> a;
    private List<d> b;
    private int c;
    private Vector<Byte> d;
    private Vector<Byte> e;
    private int[] f;
    private int g;
    private int h;

    public f() {
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.d = new Vector();
        this.e = new Vector();
        this.f = new int[7];
        this.b = null;
    }

    public List<d> a() {
        return this.b;
    }

    public void a(List<d> list) {
        this.b = list;
    }

    public List<d> b() {
        return this.a;
    }

    public int c() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public int d() {
        return this.g;
    }

    public void b(int i) {
        this.g = i;
    }

    public int e() {
        return this.h;
    }

    public void c(int i) {
        this.h = i;
    }

    public Vector<Byte> f() {
        return this.d;
    }

    public int[] g() {
        return this.f;
    }

    public Vector<Byte> h() {
        return this.e;
    }

    public void a(Vector<Byte> vector) {
        this.e = vector;
    }
}
