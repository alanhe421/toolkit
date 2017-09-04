package com.hmt.analytics.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtils {
    private static ExecutorService a = Executors.newSingleThreadExecutor();

    private ThreadPoolUtils() {
    }

    public static ExecutorService getSingleInstance() {
        return a;
    }
}
