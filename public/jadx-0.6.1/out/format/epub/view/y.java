package format.epub.view;

import format.epub.paint.ZLPaintContext;

/* compiled from: ZLTextWord */
public final class y extends g {
    public final char[] a;
    public final int b;
    public final int h;
    private float i = -1.0f;
    private a j;
    private int k;
    private boolean l;

    /* compiled from: ZLTextWord */
    class a {
        public final int a;
        public final int b;
        final /* synthetic */ y c;
        private a d;

        private a(y yVar, int i, int i2) {
            this.c = yVar;
            this.a = i;
            this.b = i2;
            this.d = null;
        }

        public a a() {
            return this.d;
        }

        private void a(a aVar) {
            this.d = aVar;
        }
    }

    public y(char[] cArr, int i, int i2, int i3) {
        this.a = cArr;
        this.b = i;
        this.h = i2;
        this.k = i3;
    }

    public boolean a() {
        for (int i = this.b; i < this.b + this.h; i++) {
            if (Character.isLetterOrDigit(this.a[i])) {
                return true;
            }
        }
        return false;
    }

    public a b() {
        return this.j;
    }

    public void a(int i, int i2) {
        a aVar = this.j;
        a aVar2 = new a(i, i2);
        if (aVar == null || aVar.a > i) {
            aVar2.a(aVar);
            this.j = aVar2;
            return;
        }
        while (aVar.a() != null && aVar.a().a < i) {
            aVar = aVar.a();
        }
        aVar2.a(aVar.a());
        aVar.a(aVar2);
    }

    public float a(ZLPaintContext zLPaintContext) {
        float f = this.i;
        if (f != -1.0f) {
            return f;
        }
        f = zLPaintContext.a(this.a, this.b, this.h);
        this.i = f;
        return f;
    }

    public String toString() {
        return new String(this.a, this.b, this.h);
    }

    public char c() {
        return this.a[this.b];
    }

    public boolean d() {
        return this.l;
    }

    public void a(boolean z) {
        this.l = z;
    }
}
