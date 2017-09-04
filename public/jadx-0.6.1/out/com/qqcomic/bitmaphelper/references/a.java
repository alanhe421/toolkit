package com.qqcomic.bitmaphelper.references;

import java.io.Closeable;
import java.io.IOException;

/* compiled from: CloseableReference */
public class a<T> implements Closeable, Cloneable {
    private static final String a = a.class.getName();
    private static final d<Closeable> b = new d<Closeable>() {
        public void a(Closeable closeable) {
            try {
                b.a(closeable, true);
            } catch (IOException e) {
            }
        }
    };
    private boolean c = false;
    private final SharedReference<T> d;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return b();
    }

    private a(SharedReference<T> sharedReference) {
        this.d = (SharedReference) c.a((Object) sharedReference);
        sharedReference.c();
    }

    private a(T t, d<T> dVar) {
        this.d = new SharedReference(t, dVar);
    }

    public static <T> a<T> a(T t, d<T> dVar) {
        if (t == null) {
            return null;
        }
        return new a(t, dVar);
    }

    public void close() {
        synchronized (this) {
            if (this.c) {
                return;
            }
            this.c = true;
            this.d.d();
        }
    }

    public synchronized T a() {
        c.b(!this.c);
        return this.d.a();
    }

    public synchronized a<T> b() {
        c.b(d());
        return new a(this.d);
    }

    public synchronized a<T> c() {
        return d() ? new a(this.d) : null;
    }

    public synchronized boolean d() {
        return !this.c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void finalize() throws java.lang.Throwable {
        /*
        r4 = this;
        monitor-enter(r4);	 Catch:{ all -> 0x006b }
        r0 = r4.c;	 Catch:{ all -> 0x0068 }
        if (r0 == 0) goto L_0x000a;
    L_0x0005:
        monitor-exit(r4);	 Catch:{ all -> 0x0068 }
        super.finalize();
    L_0x0009:
        return;
    L_0x000a:
        monitor-exit(r4);	 Catch:{ all -> 0x0068 }
        r0 = com.qrcomic.util.g.a();	 Catch:{ all -> 0x006b }
        if (r0 == 0) goto L_0x0061;
    L_0x0011:
        r0 = a;	 Catch:{ all -> 0x006b }
        r1 = com.qrcomic.util.g.d;	 Catch:{ all -> 0x006b }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x006b }
        r2.<init>();	 Catch:{ all -> 0x006b }
        r3 = "Finalized without closing: ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x006b }
        r3 = java.lang.System.identityHashCode(r4);	 Catch:{ all -> 0x006b }
        r2 = r2.append(r3);	 Catch:{ all -> 0x006b }
        r3 = " ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x006b }
        r3 = r4.d;	 Catch:{ all -> 0x006b }
        r3 = java.lang.System.identityHashCode(r3);	 Catch:{ all -> 0x006b }
        r2 = r2.append(r3);	 Catch:{ all -> 0x006b }
        r3 = " (type = ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x006b }
        r3 = r4.d;	 Catch:{ all -> 0x006b }
        r3 = r3.a();	 Catch:{ all -> 0x006b }
        r3 = r3.getClass();	 Catch:{ all -> 0x006b }
        r3 = r3.getSimpleName();	 Catch:{ all -> 0x006b }
        r2 = r2.append(r3);	 Catch:{ all -> 0x006b }
        r3 = ")";
        r2 = r2.append(r3);	 Catch:{ all -> 0x006b }
        r2 = r2.toString();	 Catch:{ all -> 0x006b }
        com.qrcomic.util.g.a(r0, r1, r2);	 Catch:{ all -> 0x006b }
    L_0x0061:
        r4.close();	 Catch:{ all -> 0x006b }
        super.finalize();
        goto L_0x0009;
    L_0x0068:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0068 }
        throw r0;	 Catch:{ all -> 0x006b }
    L_0x006b:
        r0 = move-exception;
        super.finalize();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qqcomic.bitmaphelper.references.a.finalize():void");
    }
}
