package com.qq.reader.common.readertask.protocol;

import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class GetRedpacketInfoTask extends ReaderProtocolJSONTask {
    public GetRedpacketInfoTask(String str, c cVar) {
        super(cVar);
        this.mUrl = str;
    }
}
