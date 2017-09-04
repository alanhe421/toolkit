package com.qrcomic.manager;

import com.qrcomic.a.h;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: QRComicPluginManager */
public class b {
    private static b a;
    private static AtomicBoolean c = new AtomicBoolean(false);
    private h b;

    private b() {
    }

    public static b a() {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b();
                }
            }
        }
        return a;
    }

    public void a(h hVar) {
        if (!c.getAndSet(true) || this.b == null) {
            this.b = hVar;
        }
    }

    public h b() {
        return this.b;
    }

    public void a(String str, int i) {
        try {
            this.b.f().d().a(this.b.b(), str, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
