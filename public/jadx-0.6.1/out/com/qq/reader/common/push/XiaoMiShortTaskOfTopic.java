package com.qq.reader.common.push;

import com.qq.reader.common.readertask.ordinal.ReaderShortTask;

public class XiaoMiShortTaskOfTopic extends ReaderShortTask {
    public XiaoMiShortTaskOfTopic() {
        setFailedType(1);
        setPriority(3);
    }

    public void run() {
        super.run();
        b.e();
    }
}
