package com.tencent.beacon.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import com.tencent.beacon.b.b.c;
import com.tencent.beacon.e.a;
import com.tencent.beacon.event.o;

/* compiled from: ProGuard */
public final class e extends BroadcastReceiver implements Runnable {
    protected Context a;
    private boolean b = false;
    private Runnable c = new Runnable(this) {
        private /* synthetic */ e a;

        {
            this.a = r1;
        }

        public final void run() {
            if (this.a.a != null) {
                h.c(this.a.a);
            }
        }
    };
    private Runnable d = new Runnable() {
        public final void run() {
            a.b(" db events to up on netConnectChange", new Object[0]);
            try {
                o.d(true);
            } catch (Throwable th) {
                a.a(th);
            }
        }
    };

    public final void onReceive(Context context, Intent intent) {
        this.a = context;
        c.a().a((Runnable) this);
    }

    public final void run() {
        try {
            if (this.a == null) {
                a.c(" onReceive context is null!", new Object[0]);
                return;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) this.a.getSystemService("connectivity");
            if (connectivityManager == null) {
                a.c(" onReceive ConnectivityManager is null!", new Object[0]);
                return;
            }
            State state;
            State state2;
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
            if (networkInfo != null) {
                state = networkInfo.getState();
            } else {
                state = null;
            }
            if (networkInfo2 != null) {
                state2 = networkInfo2.getState();
            } else {
                state2 = null;
            }
            if (!(state == null && state2 == null)) {
                c a = c.a(this.a);
                if (!(a.a() || o.d().k() == 0 || a.j() == 2)) {
                    c.a().a(a.d());
                }
            }
            if (state != null || state2 != null) {
                if (State.CONNECTED == state2 || State.CONNECTED == state) {
                    c.a().a(this.d);
                    c.a().a(this.c);
                }
            }
        } catch (Throwable e) {
            a.a(e);
        }
    }

    public final void a(Context context) {
        if (context == null) {
            a.c(" Context is null!", new Object[0]);
        } else if (!this.b) {
            this.b = true;
            context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }
}
