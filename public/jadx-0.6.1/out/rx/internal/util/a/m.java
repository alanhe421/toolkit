package rx.internal.util.a;

/* compiled from: MpmcArrayQueue */
abstract class m<E> extends k<E> {
    private static final long e = ae.a(m.class, "producerIndex");
    private volatile long producerIndex;

    public m(int i) {
        super(i);
    }

    protected final long b() {
        return this.producerIndex;
    }

    protected final boolean c(long j, long j2) {
        return ae.a.compareAndSwapLong(this, e, j, j2);
    }
}
