package rx.e;

import rx.internal.schedulers.a;

/* compiled from: Schedulers */
public final class d {
    private static final d d = new d();
    private final rx.d a;
    private final rx.d b;
    private final rx.d c;

    private d() {
        rx.d a = rx.d.d.a().d().a();
        if (a != null) {
            this.a = a;
        } else {
            this.a = new a();
        }
        a = rx.d.d.a().d().b();
        if (a != null) {
            this.b = a;
        } else {
            this.b = new a();
        }
        a = rx.d.d.a().d().c();
        if (a != null) {
            this.c = a;
        } else {
            this.c = c.c();
        }
    }

    public static rx.d a() {
        return d.a;
    }

    public static rx.d b() {
        return d.b;
    }
}
