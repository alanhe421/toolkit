package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class ReaderConfigTask extends ReaderProtocolJSONTask {
    public ReaderConfigTask(String str, c cVar) {
        super(cVar);
        setFailedType(1);
        this.mUrl = e.cn + "?patchversion=" + str;
    }
}
