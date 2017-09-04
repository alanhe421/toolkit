package rx.internal.util.a;

/* compiled from: MpmcArrayQueue */
abstract class j<E> extends l<E> {
    private static final long e = ae.a(j.class, "consumerIndex");
    private volatile long consumerIndex;

    public j(int i) {
        super(i);
    }

    protected final long a() {
        return this.consumerIndex;
    }

    protected final boolean b(long j, long j2) {
        return ae.a.compareAndSwapLong(this, e, j, j2);
    }
}
