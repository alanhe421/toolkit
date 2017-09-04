package com.xiaomi.mipush.sdk;

import android.database.ContentObserver;
import android.os.Handler;
import com.xiaomi.channel.commonutils.d.d;
import com.xiaomi.push.service.q;

class j extends ContentObserver {
    final /* synthetic */ al a;

    j(al alVar, Handler handler) {
        this.a = alVar;
        super(handler);
    }

    public void onChange(boolean z) {
        this.a.l = Integer.valueOf(q.a(this.a.c).b());
        if (this.a.l.intValue() != 0) {
            this.a.c.getContentResolver().unregisterContentObserver(this);
            if (d.d(this.a.c)) {
                this.a.d();
            }
        }
    }
}
