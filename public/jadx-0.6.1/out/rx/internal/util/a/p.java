package rx.internal.util.a;

/* compiled from: SpmcArrayQueue */
abstract class p<E> extends r<E> {
    protected static final long d = ae.a(p.class, "consumerIndex");
    private volatile long consumerIndex;

    public p(int i) {
        super(i);
    }

    protected final long a() {
        return this.consumerIndex;
    }

    protected final boolean b(long j, long j2) {
        return ae.a.compareAndSwapLong(this, d, j, j2);
    }
}
