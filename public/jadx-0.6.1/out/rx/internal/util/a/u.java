package rx.internal.util.a;

/* compiled from: SpmcArrayQueue */
abstract class u<E> extends q<E> {
    protected static final long e = ae.a(u.class, "producerIndex");
    private volatile long producerIndex;

    protected final long b() {
        return this.producerIndex;
    }

    protected final void d(long j) {
        ae.a.putOrderedLong(this, e, j);
    }

    public u(int i) {
        super(i);
    }
}
