package com.tencent.xplatform;

import android.os.Handler;
import android.os.Looper;

public class MainThreadHelp {
    public static native void nativeProcessTask(long j);

    public static void postTask(final long j) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                MainThreadHelp.nativeProcessTask(j);
            }
        });
    }

    public static void postRunnable(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
