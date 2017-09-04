package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.bumptech.glide.g.h;
import com.bumptech.glide.request.f;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/* compiled from: EngineJob */
class c implements a {
    private static final a a = new a();
    private static final Handler b = new Handler(Looper.getMainLooper(), new b());
    private final List<f> c;
    private final a d;
    private final d e;
    private final com.bumptech.glide.load.b f;
    private final ExecutorService g;
    private final ExecutorService h;
    private final boolean i;
    private boolean j;
    private i<?> k;
    private boolean l;
    private Exception m;
    private boolean n;
    private Set<f> o;
    private EngineRunnable p;
    private g<?> q;
    private volatile Future<?> r;

    /* compiled from: EngineJob */
    static class a {
        a() {
        }

        public <R> g<R> a(i<R> iVar, boolean z) {
            return new g(iVar, z);
        }
    }

    /* compiled from: EngineJob */
    private static class b implements Callback {
        private b() {
        }

        public boolean handleMessage(Message message) {
            if (1 != message.what && 2 != message.what) {
                return false;
            }
            c cVar = (c) message.obj;
            if (1 == message.what) {
                cVar.b();
            } else {
                cVar.c();
            }
            return true;
        }
    }

    public c(com.bumptech.glide.load.b bVar, ExecutorService executorService, ExecutorService executorService2, boolean z, d dVar) {
        this(bVar, executorService, executorService2, z, dVar, a);
    }

    public c(com.bumptech.glide.load.b bVar, ExecutorService executorService, ExecutorService executorService2, boolean z, d dVar, a aVar) {
        this.c = new ArrayList();
        this.f = bVar;
        this.g = executorService;
        this.h = executorService2;
        this.i = z;
        this.e = dVar;
        this.d = aVar;
    }

    public void a(EngineRunnable engineRunnable) {
        this.p = engineRunnable;
        this.r = this.g.submit(engineRunnable);
    }

    public void b(EngineRunnable engineRunnable) {
        this.r = this.h.submit(engineRunnable);
    }

    public void a(f fVar) {
        h.a();
        if (this.l) {
            fVar.a(this.q);
        } else if (this.n) {
            fVar.a(this.m);
        } else {
            this.c.add(fVar);
        }
    }

    public void b(f fVar) {
        h.a();
        if (this.l || this.n) {
            c(fVar);
            return;
        }
        this.c.remove(fVar);
        if (this.c.isEmpty()) {
            a();
        }
    }

    private void c(f fVar) {
        if (this.o == null) {
            this.o = new HashSet();
        }
        this.o.add(fVar);
    }

    private boolean d(f fVar) {
        return this.o != null && this.o.contains(fVar);
    }

    void a() {
        if (!this.n && !this.l && !this.j) {
            this.p.a();
            Future future = this.r;
            if (future != null) {
                future.cancel(true);
            }
            this.j = true;
            this.e.a(this, this.f);
        }
    }

    public void a(i<?> iVar) {
        this.k = iVar;
        b.obtainMessage(1, this).sendToTarget();
    }

    private void b() {
        if (this.j) {
            this.k.d();
        } else if (this.c.isEmpty()) {
            throw new IllegalStateException("Received a resource without any callbacks to notify");
        } else {
            this.q = this.d.a(this.k, this.i);
            this.l = true;
            this.q.e();
            this.e.a(this.f, this.q);
            for (f fVar : this.c) {
                if (!d(fVar)) {
                    this.q.e();
                    fVar.a(this.q);
                }
            }
            this.q.f();
        }
    }

    public void a(Exception exception) {
        this.m = exception;
        b.obtainMessage(2, this).sendToTarget();
    }

    private void c() {
        if (!this.j) {
            if (this.c.isEmpty()) {
                throw new IllegalStateException("Received an exception without any callbacks to notify");
            }
            this.n = true;
            this.e.a(this.f, null);
            for (f fVar : this.c) {
                if (!d(fVar)) {
                    fVar.a(this.m);
                }
            }
        }
    }
}
