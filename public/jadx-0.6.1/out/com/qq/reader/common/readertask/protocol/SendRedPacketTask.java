package com.qq.reader.common.readertask.protocol;

import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class SendRedPacketTask extends ReaderProtocolJSONTask {
    public SendRedPacketTask(String str, c cVar) {
        super(cVar);
        this.mUrl = str;
    }

    public void addParam(String str, String str2) {
        addHeader(str, str2);
    }
}
