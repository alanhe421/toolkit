package rx.internal.util.a;

/* compiled from: SpscArrayQueue */
public final class w<E> extends ab<E> {
    public w(int i) {
        super(i);
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("null elements not allowed");
        }
        Object[] objArr = this.c;
        long j = this.producerIndex;
        long a = a(j);
        if (b(objArr, a) != null) {
            return false;
        }
        d(j + 1);
        b(objArr, a, e);
        return true;
    }

    public E poll() {
        long j = this.consumerIndex;
        long a = a(j);
        Object[] objArr = this.c;
        E b = b(objArr, a);
        if (b == null) {
            return null;
        }
        e(j + 1);
        b(objArr, a, null);
        return b;
    }

    public E peek() {
        return c(a(this.consumerIndex));
    }

    public int size() {
        long b = b();
        while (true) {
            long a = a();
            long b2 = b();
            if (b == b2) {
                return (int) (a - b2);
            }
            b = b2;
        }
    }

    private void d(long j) {
        ae.a.putOrderedLong(this, f, j);
    }

    private void e(long j) {
        ae.a.putOrderedLong(this, e, j);
    }

    private long a() {
        return ae.a.getLongVolatile(this, f);
    }

    private long b() {
        return ae.a.getLongVolatile(this, e);
    }
}
