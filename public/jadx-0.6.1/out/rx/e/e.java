package rx.e;

import rx.b.a;
import rx.d;

/* compiled from: SleepingAction */
class e implements a {
    private final a a;
    private final d.a b;
    private final long c;

    public e(a aVar, d.a aVar2, long j) {
        this.a = aVar;
        this.b = aVar2;
        this.c = j;
    }

    public void call() {
        if (!this.b.isUnsubscribed()) {
            if (this.c > this.b.a()) {
                long a = this.c - this.b.a();
                if (a > 0) {
                    try {
                        Thread.sleep(a);
                    } catch (Throwable e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                }
            }
            if (!this.b.isUnsubscribed()) {
                this.a.call();
            }
        }
    }
}
