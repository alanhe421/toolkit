package com.qq.reader.common.monitor;

import com.qq.reader.common.readertask.ordinal.ReaderIOTask;

class ServerLog$1 extends ReaderIOTask {
    final /* synthetic */ String val$log;

    ServerLog$1(String str) {
        this.val$log = str;
    }

    public String getTaskName() {
        return "debuglog";
    }

    public void run() {
        super.run();
        j.c(this.val$log);
    }
}
