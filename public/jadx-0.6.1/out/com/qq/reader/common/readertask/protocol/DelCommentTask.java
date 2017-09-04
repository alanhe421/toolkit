package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;

public class DelCommentTask extends ReaderProtocolJSONTask {
    public DelCommentTask(String str, String str2, int i) {
        this.mUrl = e.bI + "bid=" + str + "&commentid=" + str2 + "&ctype=" + i;
    }
}
