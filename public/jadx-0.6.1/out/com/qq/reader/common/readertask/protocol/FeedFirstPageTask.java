package com.qq.reader.common.readertask.protocol;

import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class FeedFirstPageTask extends ReaderProtocolJSONTask {
    public FeedFirstPageTask(c cVar) {
        super(cVar);
        this.mUrl = e.dd + "?interest=" + d.aS(ReaderApplication.getApplicationImp());
    }
}
