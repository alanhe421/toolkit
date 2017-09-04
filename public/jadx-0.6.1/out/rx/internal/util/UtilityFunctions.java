package rx.internal.util;

import rx.b.c;
import rx.b.d;
import rx.b.e;
import rx.b.f;
import rx.b.g;

public final class UtilityFunctions {
    private static final a a = new a();

    static class AnonymousClass1 implements d<T, T> {
        public T call(T t) {
            return t;
        }
    }

    private enum AlwaysFalse implements d<Object, Boolean> {
        INSTANCE;

        public Boolean call(Object obj) {
            return Boolean.valueOf(false);
        }
    }

    private enum AlwaysTrue implements d<Object, Boolean> {
        INSTANCE;

        public Boolean call(Object obj) {
            return Boolean.valueOf(true);
        }
    }

    private static final class a<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, R> implements c<R>, d<T0, R>, e<T0, T1, R>, f<T0, T1, T2, R>, g<R> {
        private a() {
        }

        public R call() {
            return null;
        }

        public R call(T0 t0) {
            return null;
        }

        public R a(T0 t0, T1 t1) {
            return null;
        }

        public R a(T0 t0, T1 t1, T2 t2) {
            return null;
        }

        public R a(Object... objArr) {
            return null;
        }
    }
}
