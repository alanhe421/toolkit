package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class QidianUserInfoTask extends ReaderProtocolJSONTask {
    public QidianUserInfoTask(c cVar) {
        super(cVar);
        this.mUrl = e.d + "/getNickIcon";
    }
}
