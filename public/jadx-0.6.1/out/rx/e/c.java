package rx.e;

import rx.d;
import rx.d.a;
import rx.internal.schedulers.b;
import rx.internal.util.e;

/* compiled from: NewThreadScheduler */
public final class c extends d {
    private static final e a = new e("RxNewThreadScheduler-");
    private static final c b = new c();

    static c c() {
        return b;
    }

    private c() {
    }

    public a a() {
        return new b(a);
    }
}
