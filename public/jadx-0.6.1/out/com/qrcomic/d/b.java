package com.qrcomic.d;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.SystemClock;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: MqrHandler */
public class b implements a {
    public static AtomicInteger a = new AtomicInteger(0);
    public static e b;
    private static boolean j = false;
    private static boolean k = false;
    boolean c;
    boolean d;
    Callback e;
    MessageQueue f;
    d g;
    final Looper h;
    private a i;

    /* compiled from: MqrHandler */
    private static class a extends Handler {
        b a;

        public a(Looper looper, Callback callback) {
            super(looper, callback);
        }

        public void dispatchMessage(Message message) {
            this.a.a(message);
        }
    }

    public static boolean a() {
        return true;
    }

    public b() {
        this(Looper.myLooper(), null);
    }

    public b(Looper looper) {
        this(looper, null);
    }

    public b(Looper looper, Callback callback) {
        this(looper, callback, false);
    }

    public b(Looper looper, Callback callback, boolean z) {
        this.c = false;
        this.d = false;
        this.h = looper;
        if (this.h == null) {
            throw new RuntimeException("Can't create handler inside thread that has not called Looper.prepare()");
        }
        this.e = callback;
        try {
            this.f = looper.getQueue();
            if (this.f == null || this.h != Looper.getMainLooper() || !a() || z) {
                if (!(this.h == Looper.getMainLooper() || b == null)) {
                    this.d = b.a(looper.getThread());
                }
                this.i = new a(looper, callback);
                this.i.a = this;
            }
            this.c = true;
            a.incrementAndGet();
            this.g = d.a();
            this.i = new a(looper, callback);
            this.i.a = this;
        } catch (Throwable th) {
            this.c = false;
        }
    }

    public void b(Message message) {
    }

    public void a(Message message) {
        if (this.d) {
            b.a();
        }
        if (message.getCallback() != null) {
            d(message);
        } else if (this.e == null || !this.e.handleMessage(message)) {
            b(message);
        }
    }

    public final boolean a(Runnable runnable) {
        return a(c(runnable), 0);
    }

    public final boolean b(Runnable runnable) {
        return c(c(runnable));
    }

    public final boolean a(Message message, long j) {
        if (j < 0) {
            j = 0;
        }
        return b(message, SystemClock.uptimeMillis() + j);
    }

    public boolean b(Message message, long j) {
        if (!this.c) {
            return this.i.sendMessageAtTime(message, j);
        }
        d dVar = this.g;
        if (dVar != null) {
            c a = c.a(message);
            a.c = this;
            return dVar.a(a, j);
        }
        throw new RuntimeException(this + " sendMessageAtTime() called with no mSubQueue");
    }

    public final boolean c(Message message) {
        if (!this.c) {
            return this.i.sendMessageAtFrontOfQueue(message);
        }
        d dVar = this.g;
        if (dVar != null) {
            c a = c.a(message);
            a.c = this;
            return dVar.a(a, 0);
        }
        throw new RuntimeException(this + " sendMessageAtTime() called with no mSubQueue");
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("Handler (");
        stringBuffer.append(getClass().getName());
        stringBuffer.append(") {");
        stringBuffer.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    private final Message c(Runnable runnable) {
        return Message.obtain(this.i, runnable);
    }

    private final void d(Message message) {
        message.getCallback().run();
    }
}
