package org.apache.commons.compress.archivers.zip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.a;

/* compiled from: ZipArchiveEntry */
public class n extends ZipEntry implements a {
    private static final byte[] a = new byte[0];
    private int b;
    private long c;
    private int d;
    private int e;
    private long f;
    private LinkedHashMap<ZipShort, r> g;
    private k h;
    private String i;
    private byte[] j;
    private e k;

    public n(String str) {
        super(str);
        this.b = -1;
        this.c = -1;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = new e();
        a(str);
    }

    protected n() {
        this("");
    }

    public Object clone() {
        n nVar = (n) super.clone();
        nVar.a(a());
        nVar.a(b());
        nVar.a(a(true));
        return nVar;
    }

    public int getMethod() {
        return this.b;
    }

    public void setMethod(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("ZIP compression method can not be negative: " + i);
        }
        this.b = i;
    }

    public int a() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public long b() {
        return this.f;
    }

    public void a(long j) {
        this.f = j;
    }

    public int c() {
        return this.e;
    }

    public void a(r[] rVarArr) {
        this.g = new LinkedHashMap();
        for (r rVar : rVarArr) {
            if (rVar instanceof k) {
                this.h = (k) rVar;
            } else {
                this.g.put(rVar.getHeaderId(), rVar);
            }
        }
        d();
    }

    public r[] a(boolean z) {
        if (this.g != null) {
            List arrayList = new ArrayList(this.g.values());
            if (z && this.h != null) {
                arrayList.add(this.h);
            }
            return (r[]) arrayList.toArray(new r[0]);
        } else if (!z || this.h == null) {
            return new r[0];
        } else {
            return new r[]{this.h};
        }
    }

    public void a(r rVar) {
        if (rVar instanceof k) {
            this.h = (k) rVar;
        } else {
            if (this.g == null) {
                this.g = new LinkedHashMap();
            }
            this.g.put(rVar.getHeaderId(), rVar);
        }
        d();
    }

    public r a(ZipShort zipShort) {
        if (this.g != null) {
            return (r) this.g.get(zipShort);
        }
        return null;
    }

    public void setExtra(byte[] bArr) throws RuntimeException {
        try {
            a(c.a(bArr, true, c.a.c), true);
        } catch (Throwable e) {
            throw new RuntimeException("Error parsing extra fields for entry: " + getName() + " - " + e.getMessage(), e);
        }
    }

    protected void d() {
        super.setExtra(c.a(a(true)));
    }

    public byte[] e() {
        byte[] extra = getExtra();
        return extra != null ? extra : a;
    }

    public byte[] f() {
        return c.b(a(true));
    }

    public String getName() {
        return this.i == null ? super.getName() : this.i;
    }

    public boolean isDirectory() {
        return getName().endsWith("/");
    }

    protected void a(String str) {
        if (str != null && c() == 0 && str.indexOf("/") == -1) {
            str = str.replace('\\', '/');
        }
        this.i = str;
    }

    public long getSize() {
        return this.c;
    }

    public void setSize(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("invalid entry size");
        }
        this.c = j;
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public e g() {
        return this.k;
    }

    private void a(r[] rVarArr, boolean z) throws ZipException {
        if (this.g == null) {
            a(rVarArr);
            return;
        }
        for (r rVar : rVarArr) {
            r rVar2;
            if (rVar instanceof k) {
                rVar2 = this.h;
            } else {
                rVar2 = a(rVar.getHeaderId());
            }
            if (rVar2 == null) {
                a(rVar);
            } else if (z) {
                r4 = rVar.getLocalFileDataData();
                rVar2.parseFromLocalFileData(r4, 0, r4.length);
            } else {
                r4 = rVar.getCentralDirectoryData();
                rVar2.parseFromCentralDirectoryData(r4, 0, r4.length);
            }
        }
        d();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        boolean z;
        n nVar = (n) obj;
        String name = getName();
        String name2 = nVar.getName();
        if (name == null) {
            if (name2 != null) {
                return false;
            }
        } else if (!name.equals(name2)) {
            return false;
        }
        name2 = getComment();
        Object comment = nVar.getComment();
        if (name2 == null) {
            name2 = "";
        }
        if (comment == null) {
            comment = "";
        }
        if (getTime() == nVar.getTime() && r3.equals(r0) && a() == nVar.a() && c() == nVar.c() && b() == nVar.b() && getMethod() == nVar.getMethod() && getSize() == nVar.getSize() && getCrc() == nVar.getCrc() && getCompressedSize() == nVar.getCompressedSize() && Arrays.equals(f(), nVar.f()) && Arrays.equals(e(), nVar.e()) && this.k.equals(nVar.k)) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }
}
