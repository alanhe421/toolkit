package com.tencent.imsdk;

final class bp implements Runnable {
    bp() {
    }

    public final void run() {
        if (QLogImpl.sContext != null && QLogImpl.isLogToFile && !QLogImpl.isInitLogFileDone.get()) {
            new bq(this, "QLogInitThread").start();
        }
    }
}
