package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class RentBookTask extends ReaderProtocolJSONTask {
    public RentBookTask(c cVar, long j) {
        super(cVar);
        this.mUrl = e.h + "rentBook?bid=" + j;
    }
}
