package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class FeedColumnCheckNeedGeneTask extends ReaderProtocolJSONTask {
    public FeedColumnCheckNeedGeneTask(c cVar) {
        super(cVar);
        this.mUrl = e.M;
    }
}
