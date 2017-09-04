package rx.f;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import rx.f;

/* compiled from: SerialSubscription */
public final class d implements f {
    static final AtomicReferenceFieldUpdater<d, a> b = AtomicReferenceFieldUpdater.newUpdater(d.class, a.class, "a");
    volatile a a = new a(false, e.a());

    /* compiled from: SerialSubscription */
    private static final class a {
        final boolean a;
        final f b;

        a(boolean z, f fVar) {
            this.a = z;
            this.b = fVar;
        }

        a a() {
            return new a(true, this.b);
        }

        a a(f fVar) {
            return new a(this.a, fVar);
        }
    }

    public boolean isUnsubscribed() {
        return this.a.a;
    }

    public void unsubscribe() {
        a aVar;
        do {
            aVar = this.a;
            if (!aVar.a) {
            } else {
                return;
            }
        } while (!b.compareAndSet(this, aVar, aVar.a()));
        aVar.b.unsubscribe();
    }

    public void a(f fVar) {
        if (fVar == null) {
            throw new IllegalArgumentException("Subscription can not be null");
        }
        a aVar;
        do {
            aVar = this.a;
            if (aVar.a) {
                fVar.unsubscribe();
                return;
            }
        } while (!b.compareAndSet(this, aVar, aVar.a(fVar)));
        aVar.b.unsubscribe();
    }
}
