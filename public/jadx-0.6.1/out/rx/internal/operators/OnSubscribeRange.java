package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.a.a;
import rx.c;
import rx.e;

public final class OnSubscribeRange implements a<Integer> {
    private final int a;
    private final int b;

    private static final class RangeProducer extends AtomicLong implements c {
        private static final long serialVersionUID = 4114392207069098388L;
        private final int end;
        private long index;
        private final e<? super Integer> o;

        private RangeProducer(e<? super Integer> eVar, int i, int i2) {
            this.o = eVar;
            this.index = (long) i;
            this.end = i2;
        }

        public void request(long j) {
            if (get() != Long.MAX_VALUE) {
                if (j == Long.MAX_VALUE && compareAndSet(0, Long.MAX_VALUE)) {
                    fastpath();
                } else if (j > 0 && a.a(this, j) == 0) {
                    slowpath(j);
                }
            }
        }

        void slowpath(long j) {
            long j2 = this.index;
            while (true) {
                long j3 = (((long) this.end) - j2) + 1;
                long min = Math.min(j3, j);
                Object obj = j3 <= j ? 1 : null;
                j3 = min + j2;
                e eVar = this.o;
                while (j2 != j3) {
                    if (!eVar.isUnsubscribed()) {
                        eVar.onNext(Integer.valueOf((int) j2));
                        j2++;
                    } else {
                        return;
                    }
                }
                if (obj != null) {
                    break;
                }
                this.index = j3;
                j = addAndGet(-min);
                if (j != 0) {
                    j2 = j3;
                } else {
                    return;
                }
            }
            if (!eVar.isUnsubscribed()) {
                eVar.onCompleted();
            }
        }

        void fastpath() {
            long j = ((long) this.end) + 1;
            e eVar = this.o;
            long j2 = this.index;
            while (j2 != j) {
                if (!eVar.isUnsubscribed()) {
                    eVar.onNext(Integer.valueOf((int) j2));
                    j2++;
                } else {
                    return;
                }
            }
            if (!eVar.isUnsubscribed()) {
                eVar.onCompleted();
            }
        }
    }

    public /* synthetic */ void call(Object obj) {
        a((e) obj);
    }

    public void a(e<? super Integer> eVar) {
        eVar.a(new RangeProducer(eVar, this.a, this.b));
    }
}
