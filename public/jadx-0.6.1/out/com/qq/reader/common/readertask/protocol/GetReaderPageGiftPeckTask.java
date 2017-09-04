package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class GetReaderPageGiftPeckTask extends ReaderProtocolJSONTask {
    public static int TYPE_FEEDGOOGLECARD = 2;
    public static int TYPE_READERPAGE = 1;

    public GetReaderPageGiftPeckTask(int i, c cVar) {
        super(cVar);
        this.mUrl = e.i + "useractive/queryEvent" + "?etypes=" + i;
        f.a("mUrl", this.mUrl);
    }
}
