package com.tencent.beacon.event;

import android.content.Context;
import com.tencent.beacon.b.b;
import com.tencent.beacon.b.c;
import com.tencent.beacon.b.f;
import com.tencent.beacon.net.a;
import com.tencent.beacon.upload.h;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ProGuard */
public final class i {
    private k a;
    private Context b;
    private final boolean c;
    private boolean d = false;
    private int e = 20000;
    private int f = 0;
    private Runnable g = new 1(this);

    public i(Context context) {
        this.b = context;
        this.c = b.j(this.b);
        this.d = b.b;
    }

    public i(Context context, boolean z) {
        this.b = context;
        this.c = z;
    }

    private Map c() {
        Map hashMap = new HashMap(4);
        f.a(this.b);
        hashMap.put("A33", f.l(this.b));
        if (this.c) {
            hashMap.put("A66", "F");
        } else {
            hashMap.put("A66", "B");
        }
        hashMap.put("A68", b.k(this.b));
        hashMap.put("A85", this.d ? "Y" : "N");
        return hashMap;
    }

    protected final void a() {
        if (f.o(this.b)) {
            h i = o.d().i();
            if (i != null) {
                List arrayList = new ArrayList(2);
                arrayList.add(this.a);
                o.d().d.b();
                i.a(new a(this.b, arrayList));
            }
            a(d() + 1);
            if (d() % 10 == 0) {
                c.a().a(108, this.g, 600000, (long) this.e);
                a(0);
            }
            if (this.d) {
                b.a(this.b, "active_user_date", a.d());
            }
        }
    }

    public final void a(boolean z) {
        int i = 0;
        String d = a.d();
        String b = b.b(this.b, "HEART_DENGTA", "");
        String b2 = b.b(this.b, "active_user_date", "");
        if (d.equals(b) || b2.equals(d)) {
            com.tencent.beacon.e.a.a("heartbeat has been uploaded today!", new Object[0]);
            return;
        }
        this.a = b.a(this.b, "rqd_heartbeat", c());
        if (z) {
            i = ((int) (Math.random() * 600.0d)) * 1000;
        }
        c.a().a(108, this.g, (long) i, (long) this.e);
    }

    public final void b() {
        if (a.d().equals(b.b(this.b, "active_user_date", ""))) {
            com.tencent.beacon.e.a.b("active user event has been uploaded today.", new Object[0]);
            return;
        }
        com.tencent.beacon.e.a.b("recover a heart beat for active user.", new Object[0]);
        if (UserAction.onUserAction("rqd_heartbeat", true, 0, 0, c(), true)) {
            b.a(this.b, "active_user_date", a.d());
        }
    }

    private synchronized int d() {
        return this.f;
    }

    private synchronized void a(int i) {
        this.f = i;
    }
}
