package com.qq.reader.common.download.task;

import com.qq.reader.common.monitor.f;

/* compiled from: TaskWorker */
public abstract class o implements Runnable {
    private g a;
    private volatile boolean b = false;
    protected k f;

    public o(k kVar, g gVar) {
        this.a = gVar;
        this.f = kVar;
    }

    public void f() {
        this.b = true;
        f.e(h() + "pause", "paused switch is set, waiting for pause this thread.");
    }

    public boolean g() {
        return this.b;
    }

    public g e() {
        return this.a;
    }

    protected String h() {
        return "Thread = " + Thread.currentThread().getName() + " " + getClass().getSimpleName() + ".";
    }
}
