package com.qrcomic.e;

import android.os.Looper;
import android.os.SystemClock;
import com.qrcomic.a.h;
import com.qrcomic.a.j;
import com.qrcomic.d.b;
import com.qrcomic.util.g;
import java.util.HashMap;
import java.util.Map;

/* compiled from: QRBusinessHandler */
public abstract class a<E> {
    private static b b = new b(Looper.getMainLooper());
    private static b c = j.b();
    private static final String f = com.qrcomic.a.a.class.getName();
    private static final boolean g = h.c;
    public final h a;
    private Map<Long, com.qrcomic.a.a> d = new HashMap();
    private Map<Long, com.qrcomic.a.a> e = new HashMap();

    protected abstract Class<? extends com.qrcomic.a.a> a();

    protected a(h hVar) {
        this.a = hVar;
    }

    public final void a(int i, boolean z, Object obj) {
        a(i, z, obj, false);
    }

    public final void a(int i, boolean z, Object obj, boolean z2) {
        synchronized (this.a.f) {
            for (com.qrcomic.a.a aVar : this.a.f) {
                if (a() != null && a().isAssignableFrom(aVar.getClass())) {
                    a(i, z, obj, z2, aVar, b);
                }
            }
        }
        synchronized (this.a.g) {
            for (com.qrcomic.a.a aVar2 : this.a.g) {
                if (a() != null && a().isAssignableFrom(aVar2.getClass())) {
                    a(i, z, obj, z2, aVar2, c);
                }
            }
        }
    }

    private void a(int i, boolean z, Object obj, boolean z2, com.qrcomic.a.a aVar, b bVar) {
        final b bVar2 = bVar;
        final com.qrcomic.a.a aVar2 = aVar;
        final int i2 = i;
        final boolean z3 = z;
        final Object obj2 = obj;
        Runnable anonymousClass1 = new Runnable(this) {
            final /* synthetic */ a f;

            public void run() {
                long j = 0;
                if (a.g && bVar2 == a.b) {
                    j = SystemClock.uptimeMillis();
                }
                aVar2.a(i2, z3, obj2);
                if (a.g && bVar2 == a.b) {
                    j = SystemClock.uptimeMillis() - j;
                    if (j > 50) {
                        new HashMap().put("observer", aVar2.getClass().getName());
                        if (g.a()) {
                            g.a("TIME_OUT", g.d, "Observer time cost > 50 time = " + j + " observer = " + aVar2.getClass().getName());
                        }
                    }
                }
            }
        };
        if (z2) {
            bVar.b(anonymousClass1);
        } else {
            bVar.a(anonymousClass1);
        }
    }
}
