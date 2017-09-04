package format.epub.view;

import com.qq.reader.readengine.kernel.g;

/* compiled from: ZLTextElementArea */
public final class h extends k {
    public float a;
    public float b;
    public float c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;
    public float j;
    int k;
    boolean l;
    boolean m;
    v n;
    public g o;
    public boolean p = false;
    public long q = -1;
    g r;
    int s = 0;
    int t = 0;

    public h(g gVar, int i, boolean z, boolean z2, v vVar, g gVar2, float f, float f2, float f3, float f4, long j) {
        super(gVar.b(), gVar.c(), gVar.d());
        this.r = gVar;
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
        this.e = f3;
        this.f = f4;
        this.k = i;
        this.l = z;
        this.m = z2;
        this.n = vVar;
        this.o = gVar2;
        this.q = j;
    }

    public g d() {
        return this.r;
    }

    boolean a(float f, float f2) {
        return f2 >= this.i && f2 <= this.j && f >= this.g && f <= this.h;
    }

    public void a(int i) {
        this.s = i;
    }

    public int e() {
        return this.t;
    }

    public void b(int i) {
        this.t = i;
    }
}
