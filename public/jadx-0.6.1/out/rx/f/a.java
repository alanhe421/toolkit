package rx.f;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import rx.f;

/* compiled from: BooleanSubscription */
public final class a implements f {
    static final AtomicIntegerFieldUpdater<a> b = AtomicIntegerFieldUpdater.newUpdater(a.class, "a");
    volatile int a;
    private final rx.b.a c;

    public a() {
        this.c = null;
    }

    private a(rx.b.a aVar) {
        this.c = aVar;
    }

    public static a a() {
        return new a();
    }

    public static a a(rx.b.a aVar) {
        return new a(aVar);
    }

    public boolean isUnsubscribed() {
        return this.a != 0;
    }

    public final void unsubscribe() {
        if (b.compareAndSet(this, 0, 1) && this.c != null) {
            this.c.call();
        }
    }
}
