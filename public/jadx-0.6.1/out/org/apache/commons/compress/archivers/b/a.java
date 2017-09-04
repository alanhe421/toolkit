package org.apache.commons.compress.archivers.b;

/* compiled from: CpioArchiveEntry */
public class a implements org.apache.commons.compress.archivers.a {
    private final short a;
    private final int b;
    private long c;
    private long d;
    private String e;

    private void e() {
        if ((this.a & 3) == 0) {
            throw new UnsupportedOperationException();
        }
    }

    public long a() {
        e();
        return this.c;
    }

    public long b() {
        return this.d;
    }

    public short c() {
        return this.a;
    }

    public int d() {
        if (this.b == 0) {
            return 0;
        }
        int i = (int) (this.d % ((long) this.b));
        if (i > 0) {
            return this.b - i;
        }
        return 0;
    }

    public int hashCode() {
        return (this.e == null ? 0 : this.e.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        if (this.e == null) {
            if (aVar.e != null) {
                return false;
            }
            return true;
        } else if (this.e.equals(aVar.e)) {
            return true;
        } else {
            return false;
        }
    }
}
