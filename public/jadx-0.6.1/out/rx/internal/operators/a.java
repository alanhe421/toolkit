package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/* compiled from: BackpressureUtils */
public final class a {
    public static <T> long a(AtomicLongFieldUpdater<T> atomicLongFieldUpdater, T t, long j) {
        long j2;
        long j3;
        do {
            j2 = atomicLongFieldUpdater.get(t);
            j3 = j2 + j;
            if (j3 < 0) {
                j3 = Long.MAX_VALUE;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(t, j2, j3));
        return j2;
    }

    public static long a(AtomicLong atomicLong, long j) {
        long j2;
        long j3;
        do {
            j2 = atomicLong.get();
            j3 = j2 + j;
            if (j3 < 0) {
                j3 = Long.MAX_VALUE;
            }
        } while (!atomicLong.compareAndSet(j2, j3));
        return j2;
    }
}
