package com.qq.reader.common.utils;

import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import java.io.File;

class Utility$3 extends ReaderIOTask {
    final /* synthetic */ File val$f;

    Utility$3(File file) {
        this.val$f = file;
    }

    public void run() {
        ao.c(this.val$f);
    }
}
