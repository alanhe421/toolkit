package com.qq.reader.common.readertask.protocol.debug;

import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.d;

public class DebugOnlineReadTask extends ReaderProtocolTask {
    public DebugOnlineReadTask(d dVar) {
        super(dVar);
        this.mUrl = "http://dwn.3g.qq.com/DWN/monitors.txt";
    }
}
