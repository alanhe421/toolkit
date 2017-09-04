package com.qq.reader.common.readertask.protocol;

import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class SearchTask extends ReaderProtocolJSONTask {
    public SearchTask(c cVar, String str, int i) {
        super(cVar);
        this.mUrl = "http://bookcs.android.reader.qq.com/5_1/search?key=" + str + "&pagestamp=" + i;
    }
}
