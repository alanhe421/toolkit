package com.qrcomic.screenshot.d;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.qrcomic.a.g;
import com.qrcomic.util.f;

/* compiled from: AppNetWatcher */
public class a {
    private a a;

    /* compiled from: AppNetWatcher */
    private static class a extends BroadcastReceiver {
        private g a;
        private boolean b;
        private String c;

        public a(g gVar, boolean z) {
            this.b = z;
            this.a = gVar;
        }

        public void onReceive(Context context, Intent intent) {
            if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                return;
            }
            if (f.a(context)) {
                if (!(this.b || this.a == null)) {
                    this.a.c();
                }
                this.b = true;
                int d = f.d(context);
                if (d == 1) {
                    if (this.a != null) {
                        this.a.e();
                    }
                } else if (this.a != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("apnType", d);
                    this.a.a(bundle);
                }
                String e = f.e(context);
                if (!(e == null || !e.equals(this.c) || this.a == null)) {
                    this.a.f();
                }
                this.c = e;
                return;
            }
            if (this.a != null) {
                this.a.d();
            }
            this.b = false;
        }
    }

    public void a(Activity activity, g gVar) {
        if (this.a == null) {
            this.a = new a(gVar, f.a((Context) activity));
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        if (activity != null) {
            activity.registerReceiver(this.a, intentFilter);
        }
    }

    public void a(Activity activity) {
        if (activity != null) {
            activity.unregisterReceiver(this.a);
        }
    }
}
