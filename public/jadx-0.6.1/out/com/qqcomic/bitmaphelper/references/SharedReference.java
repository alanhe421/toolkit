package com.qqcomic.bitmaphelper.references;

public class SharedReference<T> {
    private T a;
    private int b = 1;
    private final d<T> c;

    public static class NullReferenceException extends RuntimeException {
        public NullReferenceException() {
            super("Null shared reference");
        }
    }

    public SharedReference(T t, d<T> dVar) {
        this.a = c.a((Object) t);
        this.c = (d) c.a((Object) dVar);
    }

    public synchronized T a() {
        return this.a;
    }

    public synchronized boolean b() {
        return this.b > 0;
    }

    public static boolean a(SharedReference<?> sharedReference) {
        return sharedReference != null && sharedReference.b();
    }

    public synchronized void c() {
        f();
        this.b++;
    }

    public void d() {
        if (e() == 0) {
            this.c.a(this.a);
            synchronized (this) {
                this.a = null;
            }
        }
    }

    private synchronized int e() {
        f();
        c.a(this.b > 0);
        this.b--;
        return this.b;
    }

    private void f() {
        if (!a(this)) {
            throw new NullReferenceException();
        }
    }
}
