package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class SkinListNetTask extends ReaderProtocolJSONTask {
    public SkinListNetTask(c cVar, String str) {
        super(cVar);
        this.mUrl = e.bx + str;
    }
}
