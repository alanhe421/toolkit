package com.tencent.beacon.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.beacon.e.a;
import com.tencent.beacon.event.o;

/* compiled from: ProGuard */
class k$a extends BroadcastReceiver {
    private String a;
    private /* synthetic */ k b;

    private k$a(k kVar) {
        this.b = kVar;
        this.a = null;
    }

    public final void onReceive(Context context, Intent intent) {
        this.a = intent.getAction();
        o d = o.d();
        if ("android.intent.action.SCREEN_ON".equals(this.a)) {
            a.b("on screen", new Object[0]);
            if (!(d == null || d.c == null)) {
                d.c.a(true);
            }
            c.a().a(this.b.a);
        } else if ("android.intent.action.SCREEN_OFF".equals(this.a)) {
            a.b("off screen", new Object[0]);
            if (d != null && d.c != null) {
                d.c.a(false);
            }
        }
    }
}
