package com.qq.reader.module.audio.loader;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;

public class QueryAudioChapterBuyInfoTask extends ReaderProtocolJSONTask {
    public QueryAudioChapterBuyInfoTask(String str) {
        this.mUrl = e.aq + "bid=" + str;
    }
}
