package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.b;
import okhttp3.internal.b.g;
import okhttp3.internal.b.j;
import okhttp3.internal.e.e;

/* compiled from: RealCall */
final class v implements e {
    final u a;
    final j b;
    final w c;
    final boolean d;
    private boolean e;

    /* compiled from: RealCall */
    final class a extends b {
        final /* synthetic */ v a;
        private final f c;

        a(v vVar, f fVar) {
            this.a = vVar;
            super("OkHttp %s", vVar.f());
            this.c = fVar;
        }

        String a() {
            return this.a.c.a().f();
        }

        protected void b() {
            IOException e;
            Object obj = 1;
            Object obj2 = null;
            try {
                y g = this.a.g();
                if (this.a.b.b()) {
                    try {
                        this.c.a(this.a, new IOException("Canceled"));
                    } catch (IOException e2) {
                        e = e2;
                        if (obj == null) {
                            this.c.a(this.a, e);
                        } else {
                            try {
                                e.b().a(4, "Callback failure for " + this.a.e(), (Throwable) e);
                            } catch (Throwable th) {
                                this.a.a.s().b(this);
                            }
                        }
                        this.a.a.s().b(this);
                    }
                }
                this.c.a(this.a, g);
                this.a.a.s().b(this);
            } catch (IOException e3) {
                e = e3;
                obj = obj2;
                if (obj == null) {
                    e.b().a(4, "Callback failure for " + this.a.e(), (Throwable) e);
                } else {
                    this.c.a(this.a, e);
                }
                this.a.a.s().b(this);
            }
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return d();
    }

    v(u uVar, w wVar, boolean z) {
        this.a = uVar;
        this.c = wVar;
        this.d = z;
        this.b = new j(uVar, z);
    }

    public y a() throws IOException {
        synchronized (this) {
            if (this.e) {
                throw new IllegalStateException("Already Executed");
            }
            this.e = true;
        }
        h();
        try {
            this.a.s().a(this);
            y g = g();
            if (g != null) {
                return g;
            }
            throw new IOException("Canceled");
        } finally {
            this.a.s().b(this);
        }
    }

    private void h() {
        this.b.a(e.b().a("response.body().close()"));
    }

    public void a(f fVar) {
        synchronized (this) {
            if (this.e) {
                throw new IllegalStateException("Already Executed");
            }
            this.e = true;
        }
        h();
        this.a.s().a(new a(this, fVar));
    }

    public void b() {
        this.b.a();
    }

    public boolean c() {
        return this.b.b();
    }

    public v d() {
        return new v(this.a, this.c, this.d);
    }

    String e() {
        return (c() ? "canceled " : "") + (this.d ? "web socket" : "call") + " to " + f();
    }

    String f() {
        return this.c.a().m();
    }

    y g() throws IOException {
        List arrayList = new ArrayList();
        arrayList.addAll(this.a.v());
        arrayList.add(this.b);
        arrayList.add(new okhttp3.internal.b.a(this.a.f()));
        arrayList.add(new okhttp3.internal.a.a(this.a.g()));
        arrayList.add(new okhttp3.internal.connection.a(this.a));
        if (!this.d) {
            arrayList.addAll(this.a.w());
        }
        arrayList.add(new okhttp3.internal.b.b(this.d));
        return new g(arrayList, null, null, null, 0, this.c).a(this.c);
    }
}
