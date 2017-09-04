package rx.e;

import java.util.concurrent.TimeUnit;
import rx.d;
import rx.f;
import rx.f.e;

/* compiled from: ImmediateScheduler */
public final class b extends d {
    private static final b a = new b();

    /* compiled from: ImmediateScheduler */
    private class a extends rx.d.a implements f {
        final rx.f.a a;
        final /* synthetic */ b b;

        private a(b bVar) {
            this.b = bVar;
            this.a = new rx.f.a();
        }

        public f a(rx.b.a aVar, long j, TimeUnit timeUnit) {
            return a(new e(aVar, this, this.b.b() + timeUnit.toMillis(j)));
        }

        public f a(rx.b.a aVar) {
            aVar.call();
            return e.b();
        }

        public void unsubscribe() {
            this.a.unsubscribe();
        }

        public boolean isUnsubscribed() {
            return this.a.isUnsubscribed();
        }
    }

    b() {
    }

    public rx.d.a a() {
        return new a();
    }
}
