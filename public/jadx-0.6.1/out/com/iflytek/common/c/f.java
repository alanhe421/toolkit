package com.iflytek.common.c;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class f implements ThreadFactory {
    private final AtomicInteger a = new AtomicInteger(1);

    f() {
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "CommonTask#" + this.a.getAndIncrement());
        thread.setPriority(5);
        return thread;
    }
}
