package com.qq.reader.module.qmessage.data;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;

public class MessageDelTask extends ReaderProtocolJSONTask {
    public MessageDelTask(long j) {
        this.mUrl = buildUrl(j);
    }

    private String buildUrl(long j) {
        StringBuilder stringBuilder = new StringBuilder(e.h);
        stringBuilder.append("nativepage/message/del?").append("mid=").append(j);
        return stringBuilder.toString();
    }

    public boolean isResponseGzip() {
        return false;
    }
}
