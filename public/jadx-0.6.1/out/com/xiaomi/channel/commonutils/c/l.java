package com.xiaomi.channel.commonutils.c;

import android.os.Looper;
import com.xiaomi.channel.commonutils.b.c;

public class l {
    public static void a() {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            throw new RuntimeException("can't do this on ui thread");
        }
    }

    public static void a(boolean z) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread() && z) {
            throw new RuntimeException("can't do this on ui thread when debug_switch:" + z);
        } else if (Looper.getMainLooper().getThread() == Thread.currentThread() && !z) {
            c.a("can't do this on ui thread when debug_switch:" + z);
        }
    }
}
