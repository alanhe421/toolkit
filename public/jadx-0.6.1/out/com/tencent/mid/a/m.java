package com.tencent.mid.a;

import android.os.Handler;
import android.os.HandlerThread;

public class m {
    private static m a = null;
    private Handler b = null;

    private m() {
        HandlerThread handlerThread = new HandlerThread("MidWorkThread");
        handlerThread.start();
        this.b = new Handler(handlerThread.getLooper());
    }

    public static m a() {
        if (a == null) {
            synchronized (m.class) {
                if (a == null) {
                    a = new m();
                }
            }
        }
        return a;
    }

    public void a(Runnable runnable) {
        if (this.b != null) {
            this.b.post(runnable);
        }
    }
}
