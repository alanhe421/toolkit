package com.sijla.c;

import android.os.Handler;
import android.os.Looper;

public class c {
    public static void a(Runnable runnable) {
        b.a(runnable);
    }

    public static void a(Runnable runnable, long j) {
        new Handler(Looper.getMainLooper()).postDelayed(runnable, j);
    }
}
