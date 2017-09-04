package rx.b;

/* compiled from: Functions */
public final class h {
    public static <T0, T1, R> g<R> a(final e<? super T0, ? super T1, ? extends R> eVar) {
        return new g<R>() {
            public R a(Object... objArr) {
                if (objArr.length == 2) {
                    return eVar.a(objArr[0], objArr[1]);
                }
                throw new RuntimeException("Func2 expecting 2 arguments.");
            }
        };
    }

    public static <T0, T1, T2, R> g<R> a(final f<? super T0, ? super T1, ? super T2, ? extends R> fVar) {
        return new g<R>() {
            public R a(Object... objArr) {
                if (objArr.length == 3) {
                    return fVar.a(objArr[0], objArr[1], objArr[2]);
                }
                throw new RuntimeException("Func3 expecting 3 arguments.");
            }
        };
    }
}
