package com.bumptech.glide.request;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.bumptech.glide.request.a.c;
import com.bumptech.glide.request.b.h;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: RequestFutureTarget */
public class d<T, R> implements a<R>, Runnable {
    private static final a a = new a();
    private final Handler b;
    private final int c;
    private final int d;
    private final boolean e;
    private final a f;
    private R g;
    private b h;
    private boolean i;
    private Exception j;
    private boolean k;
    private boolean l;

    /* compiled from: RequestFutureTarget */
    static class a {
        a() {
        }

        public void a(Object obj, long j) throws InterruptedException {
            obj.wait(j);
        }

        public void a(Object obj) {
            obj.notifyAll();
        }
    }

    public d(Handler handler, int i, int i2) {
        this(handler, i, i2, true, a);
    }

    d(Handler handler, int i, int i2, boolean z, a aVar) {
        this.b = handler;
        this.c = i;
        this.d = i2;
        this.e = z;
        this.f = aVar;
    }

    public synchronized boolean cancel(boolean z) {
        boolean z2 = true;
        synchronized (this) {
            if (!this.i) {
                if (isDone()) {
                    z2 = false;
                }
                if (z2) {
                    this.i = true;
                    if (z) {
                        b();
                    }
                    this.f.a(this);
                }
            }
        }
        return z2;
    }

    public synchronized boolean isCancelled() {
        return this.i;
    }

    public synchronized boolean isDone() {
        boolean z;
        z = this.i || this.k;
        return z;
    }

    public R get() throws InterruptedException, ExecutionException {
        try {
            return a(null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    public R get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return a(Long.valueOf(timeUnit.toMillis(j)));
    }

    public void a(h hVar) {
        hVar.a(this.c, this.d);
    }

    public void a(b bVar) {
        this.h = bVar;
    }

    public b a() {
        return this.h;
    }

    public void a(Drawable drawable) {
    }

    public void b(Drawable drawable) {
    }

    public synchronized void a(Exception exception, Drawable drawable) {
        this.l = true;
        this.j = exception;
        this.f.a(this);
    }

    public synchronized void a(R r, c<? super R> cVar) {
        this.k = true;
        this.g = r;
        this.f.a(this);
    }

    private synchronized R a(Long l) throws ExecutionException, InterruptedException, TimeoutException {
        R r;
        if (this.e) {
            com.bumptech.glide.g.h.b();
        }
        if (this.i) {
            throw new CancellationException();
        } else if (this.l) {
            throw new ExecutionException(this.j);
        } else if (this.k) {
            r = this.g;
        } else {
            if (l == null) {
                this.f.a(this, 0);
            } else if (l.longValue() > 0) {
                this.f.a(this, l.longValue());
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            } else if (this.l) {
                throw new ExecutionException(this.j);
            } else if (this.i) {
                throw new CancellationException();
            } else if (this.k) {
                r = this.g;
            } else {
                throw new TimeoutException();
            }
        }
        return r;
    }

    public void run() {
        if (this.h != null) {
            this.h.d();
            cancel(false);
        }
    }

    public void b() {
        this.b.post(this);
    }

    public void d() {
    }

    public void e() {
    }

    public void f() {
    }
}
