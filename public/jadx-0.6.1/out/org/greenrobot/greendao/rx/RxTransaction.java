package org.greenrobot.greendao.rx;

import java.util.concurrent.Callable;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.annotation.apihint.Experimental;
import rx.a;
import rx.d;

@Experimental
public class RxTransaction extends RxBase {
    private final AbstractDaoSession daoSession;

    @Experimental
    public /* bridge */ /* synthetic */ d getScheduler() {
        return super.getScheduler();
    }

    public RxTransaction(AbstractDaoSession abstractDaoSession) {
        this.daoSession = abstractDaoSession;
    }

    public RxTransaction(AbstractDaoSession abstractDaoSession, d dVar) {
        super(dVar);
        this.daoSession = abstractDaoSession;
    }

    @Experimental
    public a<Void> run(final Runnable runnable) {
        return wrap(new Callable<Void>() {
            public Void call() throws Exception {
                RxTransaction.this.daoSession.runInTx(runnable);
                return null;
            }
        });
    }

    @Experimental
    public <T> a<T> call(final Callable<T> callable) {
        return wrap(new Callable<T>() {
            public T call() throws Exception {
                return RxTransaction.this.daoSession.callInTx(callable);
            }
        });
    }

    @Experimental
    public AbstractDaoSession getDaoSession() {
        return this.daoSession;
    }
}
