package org.apache.commons.compress.archivers.a;

/* compiled from: ArArchiveEntry */
public class a implements org.apache.commons.compress.archivers.a {
    private final String a;
    private final long b;

    public long a() {
        return this.b;
    }

    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        if (this.a == null) {
            if (aVar.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(aVar.a)) {
            return true;
        } else {
            return false;
        }
    }
}
