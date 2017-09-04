package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class QueryMediaBookInfoTask extends ReaderProtocolJSONTask {
    public QueryMediaBookInfoTask(c cVar, String str) {
        super(cVar);
        this.mUrl = String.format(e.bn, new Object[]{str});
    }
}
