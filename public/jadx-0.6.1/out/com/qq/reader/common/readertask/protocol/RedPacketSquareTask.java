package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e.a;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class RedPacketSquareTask extends ReaderProtocolJSONTask {
    public RedPacketSquareTask(c cVar, int i, long j) {
        super(cVar);
        this.mUrl = a.a + "?op=" + i + "&rid=" + j;
    }
}
