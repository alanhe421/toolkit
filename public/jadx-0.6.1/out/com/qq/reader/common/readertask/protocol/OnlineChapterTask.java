package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class OnlineChapterTask extends ReaderProtocolJSONTask {
    public OnlineChapterTask(c cVar, String str, long j, long j2) {
        super(cVar);
        this.mUrl = String.format(e.bl, new Object[]{str, Integer.valueOf(-1), Integer.valueOf(0), Long.valueOf(j), Long.valueOf(j2)});
    }
}
