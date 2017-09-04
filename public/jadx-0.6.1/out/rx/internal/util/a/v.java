package rx.internal.util.a;

/* compiled from: SpmcArrayQueue */
abstract class v<E> extends t<E> {
    private volatile long f;

    public v(int i) {
        super(i);
    }

    protected final long c() {
        return this.f;
    }

    protected final void e(long j) {
        this.f = j;
    }
}
