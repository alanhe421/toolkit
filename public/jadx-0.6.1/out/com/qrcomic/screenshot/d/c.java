package com.qrcomic.screenshot.d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* compiled from: QRComicHomeWatcher */
public class c {
    private Context a;
    private IntentFilter b = new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS");
    private b c;
    private a d;

    /* compiled from: QRComicHomeWatcher */
    public interface b {
        void a();

        void b();
    }

    /* compiled from: QRComicHomeWatcher */
    class a extends BroadcastReceiver {
        final String a = "reason";
        final String b = "globalactions";
        final String c = "recentapps";
        final String d = "homekey";
        final /* synthetic */ c e;

        a(c cVar) {
            this.e = cVar;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                String stringExtra = intent.getStringExtra("reason");
                if (stringExtra != null && this.e.c != null) {
                    if (stringExtra.equals("homekey")) {
                        this.e.c.a();
                    } else if (stringExtra.equals("recentapps")) {
                        this.e.c.b();
                    }
                }
            }
        }
    }

    public c(Context context) {
        this.a = context;
    }

    public void a(b bVar) {
        this.c = bVar;
        this.d = new a(this);
    }

    public void a() {
        if (this.d != null) {
            this.a.registerReceiver(this.d, this.b);
        }
    }

    public void b() {
        if (this.d != null) {
            this.a.unregisterReceiver(this.d);
        }
    }
}
