package okio;

/* compiled from: Segment */
final class m {
    final byte[] a;
    int b;
    int c;
    boolean d;
    boolean e;
    m f;
    m g;

    m() {
        this.a = new byte[8192];
        this.e = true;
        this.d = false;
    }

    m(m mVar) {
        this(mVar.a, mVar.b, mVar.c);
        mVar.d = true;
    }

    m(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.b = i;
        this.c = i2;
        this.e = false;
        this.d = true;
    }

    public m a() {
        m mVar = this.f != this ? this.f : null;
        this.g.f = this.f;
        this.f.g = this.g;
        this.f = null;
        this.g = null;
        return mVar;
    }

    public m a(m mVar) {
        mVar.g = this;
        mVar.f = this.f;
        this.f.g = mVar;
        this.f = mVar;
        return mVar;
    }

    public m a(int i) {
        if (i <= 0 || i > this.c - this.b) {
            throw new IllegalArgumentException();
        }
        m mVar;
        if (i >= 1024) {
            mVar = new m(this);
        } else {
            mVar = n.a();
            System.arraycopy(this.a, this.b, mVar.a, 0, i);
        }
        mVar.c = mVar.b + i;
        this.b += i;
        this.g.a(mVar);
        return mVar;
    }

    public void b() {
        if (this.g == this) {
            throw new IllegalStateException();
        } else if (this.g.e) {
            int i = this.c - this.b;
            if (i <= (this.g.d ? 0 : this.g.b) + (8192 - this.g.c)) {
                a(this.g, i);
                a();
                n.a(this);
            }
        }
    }

    public void a(m mVar, int i) {
        if (mVar.e) {
            if (mVar.c + i > 8192) {
                if (mVar.d) {
                    throw new IllegalArgumentException();
                } else if ((mVar.c + i) - mVar.b > 8192) {
                    throw new IllegalArgumentException();
                } else {
                    System.arraycopy(mVar.a, mVar.b, mVar.a, 0, mVar.c - mVar.b);
                    mVar.c -= mVar.b;
                    mVar.b = 0;
                }
            }
            System.arraycopy(this.a, this.b, mVar.a, mVar.c, i);
            mVar.c += i;
            this.b += i;
            return;
        }
        throw new IllegalArgumentException();
    }
}
