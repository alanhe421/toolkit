package com.sijla.e;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class a {
    private Context a;
    private a b = new a();
    private b c;

    public interface b {
        void a();

        void a(Intent intent);

        void b();

        void c();

        void d();

        void e();
    }

    private class a extends BroadcastReceiver {
        final /* synthetic */ a a;
        private String b;

        private a(a aVar) {
            this.a = aVar;
            this.b = null;
        }

        public void onReceive(Context context, Intent intent) {
            try {
                this.b = intent.getAction();
                if ("android.intent.action.SCREEN_ON".equals(this.b)) {
                    this.a.c.a();
                } else if ("android.intent.action.SCREEN_OFF".equals(this.b)) {
                    this.a.c.b();
                } else if ("android.intent.action.USER_PRESENT".equals(this.b)) {
                    this.a.c.e();
                } else if ("android.intent.action.ACTION_POWER_CONNECTED".equals(this.b)) {
                    this.a.c.c();
                } else if ("android.intent.action.ACTION_POWER_DISCONNECTED".equals(this.b)) {
                    this.a.c.d();
                } else if ("android.intent.action.BATTERY_CHANGED".equals(this.b)) {
                    this.a.c.a(intent);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public a(Context context) {
        this.a = context;
    }

    public void a(b bVar) {
        this.c = bVar;
        b();
    }

    public void a() {
        this.a.unregisterReceiver(this.b);
    }

    private void b() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        this.a.registerReceiver(this.b, intentFilter);
    }
}
