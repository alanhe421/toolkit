package com.qq.reader.common.push;

import com.qq.reader.common.readertask.ordinal.ReaderShortTask;

public class XiaoMiShortTaskOfUnAccount extends ReaderShortTask {
    public XiaoMiShortTaskOfUnAccount() {
        setFailedType(1);
        setPriority(3);
    }

    public void run() {
        super.run();
        b.d();
    }
}
