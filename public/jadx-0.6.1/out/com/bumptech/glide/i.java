package com.bumptech.glide;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.manager.g;
import com.bumptech.glide.manager.h;
import com.bumptech.glide.manager.l;
import com.bumptech.glide.manager.m;
import java.io.InputStream;

/* compiled from: RequestManager */
public class i implements h {
    private final Context a;
    private final g b;
    private final l c;
    private final m d;
    private final g e;
    private final d f;
    private a g;

    /* compiled from: RequestManager */
    public interface a {
        <T> void a(e<T, ?, ?, ?> eVar);
    }

    /* compiled from: RequestManager */
    public final class b<A, T> {
        final /* synthetic */ i a;
        private final com.bumptech.glide.load.b.l<A, T> b;
        private final Class<T> c;

        /* compiled from: RequestManager */
        public final class a {
            final /* synthetic */ b a;
            private final A b;
            private final Class<A> c;
            private final boolean d = true;

            a(b bVar, A a) {
                this.a = bVar;
                this.b = a;
                this.c = i.b((Object) a);
            }

            public <Z> f<A, T, Z> a(Class<Z> cls) {
                f<A, T, Z> fVar = (f) this.a.a.f.a(new f(this.a.a.a, this.a.a.e, this.c, this.a.b, this.a.c, cls, this.a.a.d, this.a.a.b, this.a.a.f));
                if (this.d) {
                    fVar.b(this.b);
                }
                return fVar;
            }
        }

        b(i iVar, com.bumptech.glide.load.b.l<A, T> lVar, Class<T> cls) {
            this.a = iVar;
            this.b = lVar;
            this.c = cls;
        }

        public a a(A a) {
            return new a(this, a);
        }
    }

    /* compiled from: RequestManager */
    public final class c<T> {
        final /* synthetic */ i a;
        private final com.bumptech.glide.load.b.l<T, InputStream> b;

        c(i iVar, com.bumptech.glide.load.b.l<T, InputStream> lVar) {
            this.a = iVar;
            this.b = lVar;
        }

        public d<T> a(Class<T> cls) {
            return (d) this.a.f.a(new d(cls, this.b, null, this.a.a, this.a.e, this.a.d, this.a.b, this.a.f));
        }

        public d<T> a(T t) {
            return (d) a(i.b((Object) t)).a((Object) t);
        }
    }

    /* compiled from: RequestManager */
    class d {
        final /* synthetic */ i a;

        d(i iVar) {
            this.a = iVar;
        }

        public <A, X extends e<A, ?, ?, ?>> X a(X x) {
            if (this.a.g != null) {
                this.a.g.a(x);
            }
            return x;
        }
    }

    /* compiled from: RequestManager */
    private static class e implements com.bumptech.glide.manager.c.a {
        private final m a;

        public e(m mVar) {
            this.a = mVar;
        }

        public void a(boolean z) {
            if (z) {
                this.a.d();
            }
        }
    }

    public i(Context context, g gVar, l lVar) {
        this(context, gVar, lVar, new m(), new com.bumptech.glide.manager.d());
    }

    i(Context context, final g gVar, l lVar, m mVar, com.bumptech.glide.manager.d dVar) {
        this.a = context.getApplicationContext();
        this.b = gVar;
        this.c = lVar;
        this.d = mVar;
        this.e = g.a(context);
        this.f = new d(this);
        h a = dVar.a(context, new e(mVar));
        if (com.bumptech.glide.g.h.d()) {
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ i b;

                public void run() {
                    gVar.a(this.b);
                }
            });
        } else {
            gVar.a(this);
        }
        gVar.a(a);
    }

    public void a(int i) {
        this.e.a(i);
    }

    public void a() {
        this.e.i();
    }

    public void b() {
        com.bumptech.glide.g.h.a();
        this.d.a();
    }

    public void c() {
        com.bumptech.glide.g.h.a();
        this.d.b();
    }

    public void d() {
        c();
    }

    public void e() {
        b();
    }

    public void f() {
        this.d.c();
    }

    public <A, T> b<A, T> a(com.bumptech.glide.load.b.l<A, T> lVar, Class<T> cls) {
        return new b(this, lVar, cls);
    }

    public <T> c<T> a(com.bumptech.glide.load.b.b.d<T> dVar) {
        return new c(this, dVar);
    }

    public d<String> a(String str) {
        return (d) g().a((Object) str);
    }

    public d<String> g() {
        return a(String.class);
    }

    private <T> d<T> a(Class<T> cls) {
        com.bumptech.glide.load.b.l a = g.a((Class) cls, this.a);
        com.bumptech.glide.load.b.l b = g.b((Class) cls, this.a);
        if (cls != null && a == null && b == null) {
            throw new IllegalArgumentException("Unknown type " + cls + ". You must provide a Model of a type for" + " which there is a registered ModelLoader, if you are using a custom model, you must first call" + " Glide#register with a ModelLoaderFactory for your custom model class");
        }
        return (d) this.f.a(new d(cls, a, b, this.a, this.e, this.d, this.b, this.f));
    }

    private static <T> Class<T> b(T t) {
        return t != null ? t.getClass() : null;
    }
}
