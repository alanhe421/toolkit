package format.epub.common.utils;

/* compiled from: ZLColor */
public final class k {
    private final short a;
    private final short b;
    private final short c;
    private final short d;
    private final int e;

    public k(int i, int i2, int i3) {
        this.d = (short) 255;
        this.a = (short) (i & 255);
        this.b = (short) (i2 & 255);
        this.c = (short) (i3 & 255);
        this.e = (((this.d << 24) + (this.a << 16)) + (this.b << 8)) + this.c;
    }

    public k(int i) {
        this.d = (short) ((i >> 24) & 255);
        this.a = (short) ((i >> 16) & 255);
        this.b = (short) ((i >> 8) & 255);
        this.c = (short) (i & 255);
        this.e = (((this.d << 24) + (this.a << 16)) + (this.b << 8)) + this.c;
    }

    public int a() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        if (((k) obj).a() != a()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return a();
    }
}
