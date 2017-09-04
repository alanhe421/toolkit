package rx.internal.util.a;

/* compiled from: MpmcArrayQueue */
public class i<E> extends j<E> {
    public i(int i) {
        super(Math.max(2, i));
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        long j = this.b + 1;
        long[] jArr = this.d;
        long j2 = Long.MAX_VALUE;
        while (true) {
            long b = b();
            long d = d(b);
            long a = a(jArr, d) - b;
            if (a == 0) {
                if (c(b, 1 + b)) {
                    a(a(b), (Object) e);
                    a(jArr, d, 1 + b);
                    return true;
                }
            } else if (a < 0 && b - j <= r4) {
                d = b - j;
                j2 = a();
                if (d <= j2) {
                    return false;
                }
            }
            j2 = j2;
        }
    }

    public E poll() {
        long[] jArr = this.d;
        long j = -1;
        while (true) {
            long a = a();
            long d = d(a);
            long a2 = a(jArr, d) - (1 + a);
            if (a2 == 0) {
                if (b(a, 1 + a)) {
                    j = a(a);
                    E b = b(j);
                    a(j, null);
                    a(jArr, d, (this.b + a) + 1);
                    return b;
                }
            } else if (a2 < 0 && a >= r4) {
                j = b();
                if (a == j) {
                    return null;
                }
            }
            j = j;
        }
    }

    public E peek() {
        E b;
        long a;
        do {
            a = a();
            b = b(a(a));
            if (b != null) {
                break;
            }
        } while (a != b());
        return b;
    }

    public int size() {
        long a = a();
        while (true) {
            long b = b();
            long a2 = a();
            if (a == a2) {
                return (int) (b - a2);
            }
            a = a2;
        }
    }

    public boolean isEmpty() {
        return a() == b();
    }
}
