package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.a.b;
import rx.c;
import rx.e;
import rx.f;

public final class OperatorElementAt<T> implements b<T, T> {
    private final int a;
    private final boolean b;
    private final T c;

    static class InnerProducer extends AtomicBoolean implements c {
        private static final long serialVersionUID = 1;
        final c actual;

        public InnerProducer(c cVar) {
            this.actual = cVar;
        }

        public void request(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            } else if (j > 0 && compareAndSet(false, true)) {
                this.actual.request(Long.MAX_VALUE);
            }
        }
    }

    public /* synthetic */ Object call(Object obj) {
        return a((e) obj);
    }

    public e<? super T> a(final e<? super T> eVar) {
        f anonymousClass1 = new e<T>(this) {
            final /* synthetic */ OperatorElementAt b;
            private int c = 0;

            public void onNext(T t) {
                int i = this.c;
                this.c = i + 1;
                if (i == this.b.a) {
                    eVar.onNext(t);
                    eVar.onCompleted();
                    unsubscribe();
                }
            }

            public void onError(Throwable th) {
                eVar.onError(th);
            }

            public void onCompleted() {
                if (this.c > this.b.a) {
                    return;
                }
                if (this.b.b) {
                    eVar.onNext(this.b.c);
                    eVar.onCompleted();
                    return;
                }
                eVar.onError(new IndexOutOfBoundsException(this.b.a + " is out of bounds"));
            }

            public void a(c cVar) {
                eVar.a(new InnerProducer(cVar));
            }
        };
        eVar.a(anonymousClass1);
        return anonymousClass1;
    }
}
