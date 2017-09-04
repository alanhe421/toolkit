package com.qq.reader.common.push;

import com.qq.reader.common.readertask.ordinal.ReaderShortTask;

public class XiaoMiShortTaskOfAccount extends ReaderShortTask {
    public XiaoMiShortTaskOfAccount() {
        setFailedType(1);
        setPriority(3);
    }

    public void run() {
        super.run();
        b.c();
    }
}
