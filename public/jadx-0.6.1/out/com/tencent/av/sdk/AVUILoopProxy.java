package com.tencent.av.sdk;

import android.os.Handler;
import android.os.Looper;

public class AVUILoopProxy {
    public static void postTaskToMainLooper(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
