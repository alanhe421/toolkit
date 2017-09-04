package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class GetBookUserScoreTask extends ReaderProtocolJSONTask {
    public GetBookUserScoreTask(long j, c cVar) {
        super(cVar);
        this.mUrl = e.cg + "?bid=" + j;
    }
}
