package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;

public class BestReplyTask extends ReaderProtocolJSONTask {
    public BestReplyTask(String str, String str2, int i, int i2) {
        this.mUrl = e.bA + "bid=" + str + "&replyid=" + str2 + "&top=" + i + "&ctype=" + i2;
    }

    public boolean isNeedLogin() {
        return true;
    }
}
