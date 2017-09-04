package android.support.v4.util;

/* compiled from: Pools */
public final class g {

    /* compiled from: Pools */
    public interface a<T> {
        T a();

        boolean a(T t);
    }

    /* compiled from: Pools */
    public static class b<T> implements a<T> {
        private final Object[] a;
        private int b;

        public b(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.a = new Object[i];
        }

        public T a() {
            if (this.b <= 0) {
                return null;
            }
            int i = this.b - 1;
            T t = this.a[i];
            this.a[i] = null;
            this.b--;
            return t;
        }

        public boolean a(T t) {
            if (b(t)) {
                throw new IllegalStateException("Already in the pool!");
            } else if (this.b >= this.a.length) {
                return false;
            } else {
                this.a[this.b] = t;
                this.b++;
                return true;
            }
        }

        private boolean b(T t) {
            for (int i = 0; i < this.b; i++) {
                if (this.a[i] == t) {
                    return true;
                }
            }
            return false;
        }
    }
}
