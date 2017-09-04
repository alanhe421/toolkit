package org.greenrobot.greendao.rx;

import java.util.concurrent.Callable;
import org.greenrobot.greendao.annotation.apihint.Internal;
import rx.a;
import rx.b.c;

@Internal
class RxUtils {
    RxUtils() {
    }

    @Internal
    static <T> a<T> fromCallable(final Callable<T> callable) {
        return a.a(new c<a<T>>() {
            public a<T> call() {
                try {
                    return a.a(callable.call());
                } catch (Throwable e) {
                    return a.a(e);
                }
            }
        });
    }
}
