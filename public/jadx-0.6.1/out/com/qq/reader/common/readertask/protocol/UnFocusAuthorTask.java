package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class UnFocusAuthorTask extends ReaderProtocolJSONTask {
    public UnFocusAuthorTask(c cVar, String str) {
        super(cVar);
        this.mUrl = e.a + "unfocusManito?authorId=" + str + "&platform=3";
    }
}
