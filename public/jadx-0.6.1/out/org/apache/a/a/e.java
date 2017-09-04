package org.apache.a.a;

import java.util.Vector;
import java.util.zip.ZipEntry;

/* compiled from: ZipEntry */
public class e extends ZipEntry implements Cloneable {
    private int a = 0;
    private int b = 0;
    private long c = 0;
    private Vector d = null;
    private String e = null;

    protected e() {
        super("");
    }

    public Object clone() {
        e eVar = (e) super.clone();
        eVar.d = this.d != null ? (Vector) this.d.clone() : null;
        eVar.a(a());
        eVar.a(b());
        eVar.a(d());
        return eVar;
    }

    public int a() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    public long b() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public int c() {
        return this.b;
    }

    protected void b(int i) {
        this.b = i;
    }

    public void a(f[] fVarArr) {
        this.d = new Vector();
        for (Object addElement : fVarArr) {
            this.d.addElement(addElement);
        }
        e();
    }

    public f[] d() {
        if (this.d == null) {
            return new f[0];
        }
        f[] fVarArr = new f[this.d.size()];
        this.d.copyInto(fVarArr);
        return fVarArr;
    }

    public void setExtra(byte[] bArr) throws RuntimeException {
        try {
            a(b.a(bArr));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected void e() {
        super.setExtra(b.a(d()));
    }

    public byte[] f() {
        return b.b(d());
    }

    public String getName() {
        return this.e == null ? super.getName() : this.e;
    }

    public boolean isDirectory() {
        return getName().endsWith("/");
    }

    protected void a(String str) {
        this.e = str;
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean equals(Object obj) {
        return this == obj;
    }
}
