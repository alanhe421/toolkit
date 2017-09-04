package rx.d;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: RxJavaPlugins */
public class d {
    static final a a = new a() {
    };
    private static final d b = new d();
    private final AtomicReference<a> c = new AtomicReference();
    private final AtomicReference<b> d = new AtomicReference();
    private final AtomicReference<e> e = new AtomicReference();

    public static d a() {
        return b;
    }

    d() {
    }

    public a b() {
        if (this.c.get() == null) {
            Object a = a(a.class);
            if (a == null) {
                this.c.compareAndSet(null, a);
            } else {
                this.c.compareAndSet(null, (a) a);
            }
        }
        return (a) this.c.get();
    }

    public b c() {
        if (this.d.get() == null) {
            Object a = a(b.class);
            if (a == null) {
                this.d.compareAndSet(null, c.a());
            } else {
                this.d.compareAndSet(null, (b) a);
            }
        }
        return (b) this.d.get();
    }

    private static Object a(Class<?> cls) {
        String simpleName = cls.getSimpleName();
        String property = System.getProperty("rxjava.plugin." + simpleName + ".implementation");
        if (property == null) {
            return null;
        }
        try {
            return Class.forName(property).asSubclass(cls).newInstance();
        } catch (ClassCastException e) {
            throw new RuntimeException(simpleName + " implementation is not an instance of " + simpleName + ": " + property);
        } catch (Throwable e2) {
            throw new RuntimeException(simpleName + " implementation class not found: " + property, e2);
        } catch (Throwable e22) {
            throw new RuntimeException(simpleName + " implementation not able to be instantiated: " + property, e22);
        } catch (Throwable e222) {
            throw new RuntimeException(simpleName + " implementation not able to be accessed: " + property, e222);
        }
    }

    public e d() {
        if (this.e.get() == null) {
            Object a = a(e.class);
            if (a == null) {
                this.e.compareAndSet(null, e.d());
            } else {
                this.e.compareAndSet(null, (e) a);
            }
        }
        return (e) this.e.get();
    }
}
