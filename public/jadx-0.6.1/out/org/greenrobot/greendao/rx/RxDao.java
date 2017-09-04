package org.greenrobot.greendao.rx;

import java.util.List;
import java.util.concurrent.Callable;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.annotation.apihint.Experimental;
import rx.a;
import rx.d;

@Experimental
public class RxDao<T, K> extends RxBase {
    private final AbstractDao<T, K> dao;

    @Experimental
    public /* bridge */ /* synthetic */ d getScheduler() {
        return super.getScheduler();
    }

    @Experimental
    public RxDao(AbstractDao<T, K> abstractDao) {
        this(abstractDao, null);
    }

    @Experimental
    public RxDao(AbstractDao<T, K> abstractDao, d dVar) {
        super(dVar);
        this.dao = abstractDao;
    }

    @Experimental
    public a<List<T>> loadAll() {
        return wrap(new Callable<List<T>>() {
            public List<T> call() throws Exception {
                return RxDao.this.dao.loadAll();
            }
        });
    }

    @Experimental
    public a<T> load(final K k) {
        return wrap(new Callable<T>() {
            public T call() throws Exception {
                return RxDao.this.dao.load(k);
            }
        });
    }

    @Experimental
    public a<T> refresh(final T t) {
        return wrap(new Callable<T>() {
            public T call() throws Exception {
                RxDao.this.dao.refresh(t);
                return t;
            }
        });
    }

    @Experimental
    public a<T> insert(final T t) {
        return wrap(new Callable<T>() {
            public T call() throws Exception {
                RxDao.this.dao.insert(t);
                return t;
            }
        });
    }

    @Experimental
    public a<Iterable<T>> insertInTx(final Iterable<T> iterable) {
        return wrap(new Callable<Iterable<T>>() {
            public Iterable<T> call() throws Exception {
                RxDao.this.dao.insertInTx(iterable);
                return iterable;
            }
        });
    }

    @Experimental
    public a<Object[]> insertInTx(final T... tArr) {
        return wrap(new Callable<Object[]>() {
            public Object[] call() throws Exception {
                RxDao.this.dao.insertInTx(tArr);
                return tArr;
            }
        });
    }

    @Experimental
    public a<T> insertOrReplace(final T t) {
        return wrap(new Callable<T>() {
            public T call() throws Exception {
                RxDao.this.dao.insertOrReplace(t);
                return t;
            }
        });
    }

    @Experimental
    public a<Iterable<T>> insertOrReplaceInTx(final Iterable<T> iterable) {
        return wrap(new Callable<Iterable<T>>() {
            public Iterable<T> call() throws Exception {
                RxDao.this.dao.insertOrReplaceInTx(iterable);
                return iterable;
            }
        });
    }

    @Experimental
    public a<Object[]> insertOrReplaceInTx(final T... tArr) {
        return wrap(new Callable<Object[]>() {
            public Object[] call() throws Exception {
                RxDao.this.dao.insertOrReplaceInTx(tArr);
                return tArr;
            }
        });
    }

    @Experimental
    public a<T> save(final T t) {
        return wrap(new Callable<T>() {
            public T call() throws Exception {
                RxDao.this.dao.save(t);
                return t;
            }
        });
    }

    @Experimental
    public a<Iterable<T>> saveInTx(final Iterable<T> iterable) {
        return wrap(new Callable<Iterable<T>>() {
            public Iterable<T> call() throws Exception {
                RxDao.this.dao.saveInTx(iterable);
                return iterable;
            }
        });
    }

    @Experimental
    public a<Object[]> saveInTx(final T... tArr) {
        return wrap(new Callable<Object[]>() {
            public Object[] call() throws Exception {
                RxDao.this.dao.saveInTx(tArr);
                return tArr;
            }
        });
    }

    @Experimental
    public a<T> update(final T t) {
        return wrap(new Callable<T>() {
            public T call() throws Exception {
                RxDao.this.dao.update(t);
                return t;
            }
        });
    }

    @Experimental
    public a<Iterable<T>> updateInTx(final Iterable<T> iterable) {
        return wrap(new Callable<Iterable<T>>() {
            public Iterable<T> call() throws Exception {
                RxDao.this.dao.updateInTx(iterable);
                return iterable;
            }
        });
    }

    @Experimental
    public a<Object[]> updateInTx(final T... tArr) {
        return wrap(new Callable<Object[]>() {
            public Object[] call() throws Exception {
                RxDao.this.dao.updateInTx(tArr);
                return tArr;
            }
        });
    }

    @Experimental
    public a<Void> delete(final T t) {
        return wrap(new Callable<Void>() {
            public Void call() throws Exception {
                RxDao.this.dao.delete(t);
                return null;
            }
        });
    }

    @Experimental
    public a<Void> deleteByKey(final K k) {
        return wrap(new Callable<Void>() {
            public Void call() throws Exception {
                RxDao.this.dao.deleteByKey(k);
                return null;
            }
        });
    }

    @Experimental
    public a<Void> deleteAll() {
        return wrap(new Callable<Void>() {
            public Void call() throws Exception {
                RxDao.this.dao.deleteAll();
                return null;
            }
        });
    }

    @Experimental
    public a<Void> deleteInTx(final Iterable<T> iterable) {
        return wrap(new Callable<Void>() {
            public Void call() throws Exception {
                RxDao.this.dao.deleteInTx(iterable);
                return null;
            }
        });
    }

    @Experimental
    public a<Void> deleteInTx(final T... tArr) {
        return wrap(new Callable<Void>() {
            public Void call() throws Exception {
                RxDao.this.dao.deleteInTx(tArr);
                return null;
            }
        });
    }

    @Experimental
    public a<Void> deleteByKeyInTx(final Iterable<K> iterable) {
        return wrap(new Callable<Void>() {
            public Void call() throws Exception {
                RxDao.this.dao.deleteByKeyInTx(iterable);
                return null;
            }
        });
    }

    @Experimental
    public a<Void> deleteByKeyInTx(final K... kArr) {
        return wrap(new Callable<Void>() {
            public Void call() throws Exception {
                RxDao.this.dao.deleteByKeyInTx(kArr);
                return null;
            }
        });
    }

    @Experimental
    public a<Long> count() {
        return wrap(new Callable<Long>() {
            public Long call() throws Exception {
                return Long.valueOf(RxDao.this.dao.count());
            }
        });
    }

    @Experimental
    public AbstractDao<T, K> getDao() {
        return this.dao;
    }
}
