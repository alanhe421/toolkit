package com.yw.game.sdk.login.util.network;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: ThreadPool */
public class i {
    private static final Object a = new Object();
    private static ExecutorService b;

    private static ExecutorService c() {
        ExecutorService executorService;
        synchronized (a) {
            if (b == null || b.isShutdown()) {
                b = Executors.newCachedThreadPool();
            }
            executorService = b;
        }
        return executorService;
    }

    public static ExecutorService a() {
        return c();
    }

    public static void b() {
        if (b != null && !b.isShutdown()) {
            b.shutdown();
        }
    }
}
