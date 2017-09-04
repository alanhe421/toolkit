package com.liveshow.model.task;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class AuthorDetailTask extends ReaderProtocolJSONTask {
    public AuthorDetailTask(c cVar, int i, long j, String str) {
        super(cVar);
        this.mUrl = e.cW + "?platform=1" + "&roomId=" + i + "&authorId=" + j + "&userId=" + str;
    }
}
