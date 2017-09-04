package com.qq.reader.cservice.download.audio;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class AuthCheckTask extends ReaderProtocolJSONTask {
    public AuthCheckTask(long j, c cVar) {
        super(cVar);
        this.mUrl = e.ao + "bid=" + j;
    }
}
