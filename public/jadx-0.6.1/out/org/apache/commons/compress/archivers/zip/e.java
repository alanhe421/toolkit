package org.apache.commons.compress.archivers.zip;

/* compiled from: GeneralPurposeBit */
public final class e {
    private boolean a = false;
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;

    public boolean a() {
        return this.b;
    }

    public boolean b() {
        return this.c;
    }

    public int hashCode() {
        int i = 1;
        int i2 = ((this.a ? 1 : 0) + (((this.d ? 1 : 0) + ((this.c ? 1 : 0) * 17)) * 13)) * 7;
        if (!this.b) {
            i = 0;
        }
        return (i2 + i) * 3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        if (eVar.c == this.c && eVar.d == this.d && eVar.a == this.a && eVar.b == this.b) {
            return true;
        }
        return false;
    }
}
