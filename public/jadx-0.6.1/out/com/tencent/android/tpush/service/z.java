package com.tencent.android.tpush.service;

/* compiled from: ProGuard */
class z implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ ab b;
    final /* synthetic */ XGWatchdog c;

    z(XGWatchdog xGWatchdog, String str, ab abVar) {
        this.c = xGWatchdog;
        this.a = str;
        this.b = abVar;
    }

    public void run() {
        try {
            String access$000 = XGWatchdog.access$000(this.c, this.a);
            if (this.b != null) {
                this.b.a(access$000);
            }
        } catch (Throwable th) {
        }
    }
}
