package rx;

import rx.internal.util.g;

/* compiled from: Subscriber */
public abstract class e<T> implements b<T>, f {
    private static final Long a = Long.valueOf(Long.MIN_VALUE);
    private final g b;
    private final e<?> c;
    private c d;
    private long e;

    protected e() {
        this(null, false);
    }

    protected e(e<?> eVar) {
        this(eVar, true);
    }

    protected e(e<?> eVar, boolean z) {
        this.e = a.longValue();
        this.c = eVar;
        g gVar = (!z || eVar == null) ? new g() : eVar.b;
        this.b = gVar;
    }

    public final void a(f fVar) {
        this.b.a(fVar);
    }

    public final void unsubscribe() {
        this.b.unsubscribe();
    }

    public final boolean isUnsubscribed() {
        return this.b.isUnsubscribed();
    }

    public void a() {
    }

    protected final void a(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("number requested cannot be negative: " + j);
        }
        synchronized (this) {
            if (this.d != null) {
                c cVar = this.d;
                cVar.request(j);
                return;
            }
            b(j);
        }
    }

    private void b(long j) {
        if (this.e == a.longValue()) {
            this.e = j;
            return;
        }
        long j2 = this.e + j;
        if (j2 < 0) {
            this.e = Long.MAX_VALUE;
        } else {
            this.e = j2;
        }
    }

    public void a(c cVar) {
        Object obj = null;
        synchronized (this) {
            long j = this.e;
            this.d = cVar;
            if (this.c != null && j == a.longValue()) {
                obj = 1;
            }
        }
        if (obj != null) {
            this.c.a(this.d);
        } else if (j == a.longValue()) {
            this.d.request(Long.MAX_VALUE);
        } else {
            this.d.request(j);
        }
    }
}
