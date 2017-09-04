package com.tencent.android.tpush.service;

import com.tencent.android.tpush.a.a;

/* compiled from: ProGuard */
class aa implements Runnable {
    final /* synthetic */ XGWatchdog a;

    aa(XGWatchdog xGWatchdog) {
        this.a = xGWatchdog;
    }

    public void run() {
        try {
            String access$000 = XGWatchdog.access$000(this.a, "ver:");
            Integer valueOf = Integer.valueOf(0);
            if (access$000 != null) {
                try {
                    valueOf = Integer.valueOf(access$000);
                } catch (NumberFormatException e) {
                }
            }
            if (valueOf.intValue() <= 2) {
                XGWatchdog.access$000(this.a, "exit:");
                XGWatchdog.access$000(this.a, "exit1:");
                XGWatchdog.access$000(this.a, "exit2:");
                Thread.sleep(5000);
                XGWatchdog.access$100(this.a);
            }
            this.a.isStarted = true;
        } catch (Throwable th) {
            a.h(XGWatchdog.TAG, "jniStartWatchdog error:" + th.getMessage());
        }
    }
}
