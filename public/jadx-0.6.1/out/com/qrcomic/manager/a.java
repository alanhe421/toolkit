package com.qrcomic.manager;

import com.qrcomic.util.g;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: QRComicBarrageManager */
public class a implements d {
    private static final String b = a.class.getSimpleName();
    public AtomicLong a = new AtomicLong(0);
    private AtomicLong c = new AtomicLong(0);
    private AtomicLong d = new AtomicLong(0);

    public a() {
        this.c.set(0);
        this.d.set(0);
    }

    public void a() {
        if (g.a()) {
            g.b(b, g.d, "activity onDestroy clearCache");
        }
    }
}
