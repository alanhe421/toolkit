package rx.internal.util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import rx.d.a;
import rx.e.d;
import rx.internal.util.a.ae;
import rx.internal.util.a.i;

/* compiled from: ObjectPool */
public abstract class b<T> {
    private Queue<T> a;
    private final int b;
    private a c;

    protected abstract T b();

    public b() {
        this(0, 0, 67);
    }

    private b(final int i, final int i2, long j) {
        this.b = i2;
        a(i);
        this.c = d.a().a();
        this.c.a(new rx.b.a(this) {
            final /* synthetic */ b c;

            public void call() {
                int i = 0;
                int size = this.c.a.size();
                if (size < i) {
                    size = i2 - size;
                    while (i < size) {
                        this.c.a.add(this.c.b());
                        i++;
                    }
                } else if (size > i2) {
                    size -= i2;
                    while (i < size) {
                        this.c.a.poll();
                        i++;
                    }
                }
            }
        }, j, j, TimeUnit.SECONDS);
    }

    public T a() {
        T poll = this.a.poll();
        if (poll == null) {
            return b();
        }
        return poll;
    }

    public void a(T t) {
        if (t != null) {
            this.a.offer(t);
        }
    }

    private void a(int i) {
        if (ae.a()) {
            this.a = new i(Math.max(this.b, 1024));
        } else {
            this.a = new ConcurrentLinkedQueue();
        }
        for (int i2 = 0; i2 < i; i2++) {
            this.a.add(b());
        }
    }
}
