package org.greenrobot.greendao.rx;

import java.util.concurrent.Callable;
import org.greenrobot.greendao.annotation.apihint.Experimental;
import org.greenrobot.greendao.annotation.apihint.Internal;
import rx.a;
import rx.d;

@Internal
class RxBase {
    protected final d scheduler;

    RxBase() {
        this.scheduler = null;
    }

    @Experimental
    RxBase(d dVar) {
        this.scheduler = dVar;
    }

    @Experimental
    public d getScheduler() {
        return this.scheduler;
    }

    protected <R> a<R> wrap(Callable<R> callable) {
        return wrap(RxUtils.fromCallable(callable));
    }

    protected <R> a<R> wrap(a<R> aVar) {
        if (this.scheduler != null) {
            return aVar.b(this.scheduler);
        }
        return aVar;
    }
}
