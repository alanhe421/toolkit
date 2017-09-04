package org.apache.commons.compress.archivers.dump;

/* compiled from: DumpArchiveSummary */
public class c {
    private long a;
    private long b;
    private int c;
    private String d;
    private int e;
    private String f;
    private String g;
    private String h;
    private int i;
    private int j;
    private int k;

    c(byte[] bArr) {
        this.a = ((long) d.b(bArr, 4)) * 1000;
        this.b = ((long) d.b(bArr, 8)) * 1000;
        this.c = d.b(bArr, 12);
        this.d = new String(bArr, 676, 16).trim();
        this.e = d.b(bArr, 692);
        this.f = new String(bArr, 696, 64).trim();
        this.g = new String(bArr, 760, 64).trim();
        this.h = new String(bArr, 824, 64).trim();
        this.i = d.b(bArr, 888);
        this.j = d.b(bArr, 892);
        this.k = d.b(bArr, 896);
    }

    public String a() {
        return this.g;
    }

    public String b() {
        return this.h;
    }

    public int c() {
        return this.k;
    }

    public boolean d() {
        return (this.i & 128) == 128;
    }

    public int hashCode() {
        int i = 17;
        if (this.d != null) {
            i = this.d.hashCode();
        }
        i = (int) (((long) i) + (31 * this.a));
        if (this.h != null) {
            i = (this.h.hashCode() * 31) + 17;
        }
        if (this.g != null) {
            return (this.g.hashCode() * 31) + 17;
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        c cVar = (c) obj;
        if (this.a != cVar.a) {
            return false;
        }
        if (b() == null || !b().equals(cVar.b())) {
            return false;
        }
        if (a() == null || !a().equals(cVar.a())) {
            return false;
        }
        return true;
    }
}
