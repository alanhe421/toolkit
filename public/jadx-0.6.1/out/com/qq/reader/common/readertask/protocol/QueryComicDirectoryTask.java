package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class QueryComicDirectoryTask extends ReaderProtocolJSONTask {
    public QueryComicDirectoryTask(c cVar, String str, long j, int i, int i2) {
        super(cVar);
        this.mUrl = e.dn + "?comicId=" + str + "&lastUpdateTime=" + j + "&lastCount=" + i + "&wd=" + i2;
    }
}
