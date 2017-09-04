package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.a.a;
import rx.b.b;
import rx.b.c;
import rx.b.d;
import rx.e;
import rx.f;

public final class OnSubscribeUsing<T, Resource> implements a<T> {
    private final c<Resource> a;
    private final d<? super Resource, ? extends rx.a<? extends T>> b;
    private final b<? super Resource> c;
    private final boolean d;

    private static final class DisposeAction<Resource> extends AtomicBoolean implements rx.b.a, f {
        private static final long serialVersionUID = 4262875056400218316L;
        private b<? super Resource> dispose;
        private Resource resource;

        private DisposeAction(b<? super Resource> bVar, Resource resource) {
            this.dispose = bVar;
            this.resource = resource;
            lazySet(false);
        }

        public void call() {
            if (compareAndSet(false, true)) {
                try {
                    this.dispose.call(this.resource);
                } finally {
                    this.resource = null;
                    this.dispose = null;
                }
            }
        }

        public boolean isUnsubscribed() {
            return get();
        }

        public void unsubscribe() {
            call();
        }
    }

    public /* synthetic */ void call(Object obj) {
        a((e) obj);
    }

    public void a(e<? super T> eVar) {
        rx.b.a disposeAction;
        try {
            Object call = this.a.call();
            disposeAction = new DisposeAction(this.c, call);
            eVar.a((f) disposeAction);
            rx.a aVar = (rx.a) this.b.call(call);
            if (this.d) {
                aVar = aVar.a(disposeAction);
            }
            aVar.a(rx.c.d.a(eVar));
        } catch (Throwable th) {
            eVar.onError(th);
        }
    }

    private Throwable a(rx.b.a aVar) {
        Throwable th = null;
        if (this.d) {
            try {
                aVar.call();
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return th;
    }
}
