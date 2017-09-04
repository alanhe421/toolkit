package rx.internal.util.a;

import java.util.Iterator;

/* compiled from: ConcurrentCircularArrayQueue */
public abstract class f<E> extends g<E> {
    protected static final int a = Integer.getInteger("sparse.shift", 0).intValue();
    private static final long d = ((long) (ae.a.arrayBaseOffset(Object[].class) + (32 << (e - a))));
    private static final int e;
    protected final long b;
    protected final E[] c;

    static {
        int arrayIndexScale = ae.a.arrayIndexScale(Object[].class);
        if (4 == arrayIndexScale) {
            e = a + 2;
        } else if (8 == arrayIndexScale) {
            e = a + 3;
        } else {
            throw new IllegalStateException("Unknown pointer size");
        }
    }

    public f(int i) {
        int a = n.a(i);
        this.b = (long) (a - 1);
        this.c = new Object[((a << a) + 64)];
    }

    protected final long a(long j) {
        return a(j, this.b);
    }

    protected final long a(long j, long j2) {
        return d + ((j & j2) << e);
    }

    protected final void a(long j, E e) {
        a(this.c, j, e);
    }

    protected final void a(E[] eArr, long j, E e) {
        ae.a.putObject(eArr, j, e);
    }

    protected final void b(E[] eArr, long j, E e) {
        ae.a.putOrderedObject(eArr, j, e);
    }

    protected final E b(long j) {
        return a(this.c, j);
    }

    protected final E a(E[] eArr, long j) {
        return ae.a.getObject(eArr, j);
    }

    protected final E c(long j) {
        return b(this.c, j);
    }

    protected final E b(E[] eArr, long j) {
        return ae.a.getObjectVolatile(eArr, j);
    }

    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }
}
