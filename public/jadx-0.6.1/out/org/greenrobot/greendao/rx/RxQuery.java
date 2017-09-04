package org.greenrobot.greendao.rx;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import org.greenrobot.greendao.annotation.apihint.Experimental;
import org.greenrobot.greendao.query.LazyList;
import org.greenrobot.greendao.query.Query;
import rx.a;
import rx.d;
import rx.e;

@Experimental
public class RxQuery<T> extends RxBase {
    private final Query<T> query;

    @Experimental
    public /* bridge */ /* synthetic */ d getScheduler() {
        return super.getScheduler();
    }

    public RxQuery(Query<T> query) {
        this.query = query;
    }

    public RxQuery(Query<T> query, d dVar) {
        super(dVar);
        this.query = query;
    }

    @Experimental
    public a<List<T>> list() {
        return wrap(new Callable<List<T>>() {
            public List<T> call() throws Exception {
                return RxQuery.this.query.forCurrentThread().list();
            }
        });
    }

    @Experimental
    public a<T> unique() {
        return wrap(new Callable<T>() {
            public T call() throws Exception {
                return RxQuery.this.query.forCurrentThread().unique();
            }
        });
    }

    public a<T> oneByOne() {
        return wrap(a.a(new a.a<T>() {
            public void call(e<? super T> eVar) {
                LazyList listLazyUncached;
                try {
                    listLazyUncached = RxQuery.this.query.forCurrentThread().listLazyUncached();
                    Iterator it = listLazyUncached.iterator();
                    while (it.hasNext()) {
                        Object next = it.next();
                        if (!eVar.isUnsubscribed()) {
                            eVar.onNext(next);
                        }
                    }
                    listLazyUncached.close();
                    if (!eVar.isUnsubscribed()) {
                        eVar.onCompleted();
                    }
                } catch (Throwable th) {
                    rx.exceptions.a.a(th);
                    eVar.onError(th);
                }
            }
        }));
    }
}
