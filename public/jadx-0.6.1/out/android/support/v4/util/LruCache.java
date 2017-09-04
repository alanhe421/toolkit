package android.support.v4.util;

public class LruCache<K, V> {
    private int a;
    private int b;
    private int c;

    public final synchronized String toString() {
        String format;
        int i = 0;
        synchronized (this) {
            int i2 = this.b + this.c;
            if (i2 != 0) {
                i = (this.b * 100) / i2;
            }
            format = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(i)});
        }
        return format;
    }
}
