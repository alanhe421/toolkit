package rx.internal.util.a;

/* compiled from: ConcurrentSequencedCircularArrayQueue */
public abstract class h<E> extends f<E> {
    private static final long e = ((long) (ae.a.arrayBaseOffset(long[].class) + (32 << (f - a))));
    private static final int f = (a + 3);
    protected final long[] d;

    static {
        if (8 == ae.a.arrayIndexScale(long[].class)) {
            return;
        }
        throw new IllegalStateException("Unexpected long[] element size");
    }

    public h(int i) {
        super(i);
        int i2 = (int) (this.b + 1);
        this.d = new long[((i2 << a) + 64)];
        for (long j = 0; j < ((long) i2); j++) {
            a(this.d, d(j), j);
        }
    }

    protected final long d(long j) {
        return e + ((this.b & j) << f);
    }

    protected final void a(long[] jArr, long j, long j2) {
        ae.a.putOrderedLong(jArr, j, j2);
    }

    protected final long a(long[] jArr, long j) {
        return ae.a.getLongVolatile(jArr, j);
    }
}
