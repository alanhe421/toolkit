package com.qrcomic.d;

import android.os.Message;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: MqrMessage */
public class c {
    static boolean e = true;
    private static final Object f = new Object();
    private static c g;
    private static int h = 0;
    private static AtomicInteger i = new AtomicInteger(0);
    long a;
    Message b;
    a c;
    c d;
    private int j;

    private c() {
        if (e) {
            this.j = i.incrementAndGet();
        }
    }

    public static c a() {
        synchronized (f) {
            if (g != null) {
                c cVar = g;
                g = cVar.d;
                cVar.d = null;
                if (e) {
                    cVar.j = i.incrementAndGet();
                }
                h--;
                return cVar;
            }
            return new c();
        }
    }

    public static c a(Message message) {
        c a = a();
        a.b = message;
        return a;
    }

    public void b() {
        if (this.b != null) {
            this.b.recycle();
        }
        this.a = 0;
        this.b = null;
        this.c = null;
        synchronized (f) {
            if (h < 10) {
                this.d = g;
                g = this;
                h++;
            }
        }
    }

    public String toString() {
        return "MqrMessage@" + this.j;
    }
}
