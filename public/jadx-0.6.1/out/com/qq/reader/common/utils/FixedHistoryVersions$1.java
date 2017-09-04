package com.qq.reader.common.utils;

import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;

class FixedHistoryVersions$1 extends ReaderIOTask {
    FixedHistoryVersions$1() {
    }

    public void run() {
        super.run();
        try {
            aa.a();
        } catch (Exception e) {
            c.e("splash", e.getMessage());
        }
    }
}
