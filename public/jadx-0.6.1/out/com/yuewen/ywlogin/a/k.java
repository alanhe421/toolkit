package com.yuewen.ywlogin.a;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class k {
    private static Object a = new Object();
    private static ExecutorService b;
    private static ExecutorService c;
    private static ExecutorService d;
    private static ExecutorService e;

    private static ExecutorService a() {
        ExecutorService executorService;
        synchronized (a) {
            if (c == null || c.isShutdown()) {
                c = Executors.newFixedThreadPool(3);
            }
            executorService = c;
        }
        return executorService;
    }

    private static ExecutorService b() {
        ExecutorService executorService;
        synchronized (a) {
            if (b == null || b.isShutdown()) {
                b = Executors.newCachedThreadPool();
            }
            executorService = b;
        }
        return executorService;
    }

    private static ExecutorService c() {
        ExecutorService executorService;
        synchronized (a) {
            if (d == null || d.isShutdown()) {
                d = Executors.newSingleThreadExecutor();
            }
            executorService = d;
        }
        return executorService;
    }

    private static ExecutorService d() {
        ExecutorService executorService;
        synchronized (a) {
            if (e == null || e.isShutdown()) {
                e = Executors.newFixedThreadPool(3);
            }
            executorService = e;
        }
        return executorService;
    }

    public static ExecutorService a(int i) {
        if (i == 0) {
            return b();
        }
        if (i == 1) {
            return a();
        }
        if (i == 3) {
            return d();
        }
        return c();
    }
}
