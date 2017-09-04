package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;

public class DelReplyTask extends ReaderProtocolJSONTask {
    public DelReplyTask(String str, String str2, int i) {
        this.mUrl = e.bC + "bid=" + str + "&replyid=" + str2 + "&ctype=" + i;
    }

    public boolean isNeedLogin() {
        return true;
    }
}
