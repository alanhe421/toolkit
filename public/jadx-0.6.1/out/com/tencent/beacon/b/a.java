package com.tencent.beacon.b;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import com.tencent.android.tpush.common.Constants;
import com.tencent.beacon.event.i;

/* compiled from: ProGuard */
public final class a implements Runnable {
    private final Context a;
    private Object b;
    private boolean c = false;
    private int d = 0;

    public a(Context context) {
        this.a = context;
        this.a.getSystemService(Constants.FLAG_ACTIVITY_NAME);
        b.g(this.a);
    }

    public final void run() {
        if (this.d == 0) {
            com.tencent.beacon.e.a.a("Activity Monitor Task was started.", new Object[0]);
        }
        int i = this.d;
        this.d = i + 1;
        if (i < 10) {
            if (b.b) {
                new i(this.a).b();
                this.d = 10;
            } else {
                if (Integer.valueOf(VERSION.SDK).intValue() >= 18 || this.b != null || this.c) {
                    a();
                } else {
                    new Handler(this.a.getMainLooper()).post(new Runnable(this) {
                        private /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public final void run() {
                            this.a.a();
                        }
                    });
                    this.c = true;
                }
                c.a().a(this, 5000);
            }
        }
        if (this.d == 10) {
            com.tencent.beacon.e.a.a("Activity Monitor Task was exited.", new Object[0]);
        }
    }

    private void a() {
        if (this.b == null) {
            this.b = com.tencent.beacon.net.a.a("android.app.ActivityThread", "currentActivityThread", new Class[0], new Object[0]);
        }
        if (this.b != null) {
            int a = com.tencent.beacon.net.a.a("android.app.ActivityThread", this.b, "mNumVisibleActivities");
            if (a > 0) {
                com.tencent.beacon.e.a.a("mNumVisibleActivities equals " + a, new Object[0]);
                b.b = true;
            }
        }
    }
}
