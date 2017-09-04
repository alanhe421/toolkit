package rx.internal.operators;

import rx.a.a;
import rx.b.c;
import rx.c.d;
import rx.e;

/* compiled from: OnSubscribeDefer */
public final class b<T> implements a<T> {
    final c<? extends rx.a<? extends T>> a;

    public /* synthetic */ void call(Object obj) {
        a((e) obj);
    }

    public b(c<? extends rx.a<? extends T>> cVar) {
        this.a = cVar;
    }

    public void a(e<? super T> eVar) {
        try {
            ((rx.a) this.a.call()).a(d.a(eVar));
        } catch (Throwable th) {
            eVar.onError(th);
        }
    }
}
