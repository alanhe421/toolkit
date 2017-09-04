package com.tencent.beacon.d;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: ProGuard */
public final class a<T> {
    private Lock a = new ReentrantLock();
    private Condition b = this.a.newCondition();
    private volatile T c;

    public final void a(T t) {
        this.a.lock();
        try {
            this.c = t;
            if (t != null) {
                this.b.signal();
            }
            this.a.unlock();
        } catch (Throwable th) {
            this.a.unlock();
        }
    }

    public final T a() throws InterruptedException {
        this.a.lock();
        while (this.c == null) {
            try {
                this.b.await();
            } finally {
                this.a.unlock();
            }
        }
        T t = this.c;
        return t;
    }

    public final T b() {
        return this.c;
    }
}
