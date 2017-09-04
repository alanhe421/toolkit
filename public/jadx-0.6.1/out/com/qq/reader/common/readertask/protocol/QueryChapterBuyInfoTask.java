package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;

public class QueryChapterBuyInfoTask extends ReaderProtocolJSONTask {
    public QueryChapterBuyInfoTask(String str) {
        this.mUrl = e.ap + "bid=" + str;
    }
}
