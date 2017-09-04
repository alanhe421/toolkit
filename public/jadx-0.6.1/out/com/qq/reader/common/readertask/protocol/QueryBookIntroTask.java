package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class QueryBookIntroTask extends ReaderProtocolJSONTask {
    public QueryBookIntroTask(c cVar, String str) {
        super(cVar);
        this.mUrl = String.format(e.bm, new Object[]{str, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)});
    }

    public QueryBookIntroTask(c cVar, String str, long j, long j2, long j3) {
        super(cVar);
        this.mUrl = String.format(e.bm, new Object[]{str, Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)});
    }
}
