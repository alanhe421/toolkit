package de.innosystec.unrar.unpack.ppm;

/* compiled from: Pointer */
public abstract class d {
    static final /* synthetic */ boolean e = (!d.class.desiredAssertionStatus());
    protected byte[] c;
    protected int d;

    public d(byte[] bArr) {
        this.c = bArr;
    }

    public int e() {
        if (e || this.c != null) {
            return this.d;
        }
        throw new AssertionError();
    }

    public void c(int i) {
        if (!e && this.c == null) {
            throw new AssertionError();
        } else if (e || (i >= 0 && i < this.c.length)) {
            this.d = i;
        } else {
            throw new AssertionError(i);
        }
    }
}
