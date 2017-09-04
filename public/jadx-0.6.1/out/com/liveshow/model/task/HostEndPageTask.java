package com.liveshow.model.task;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class HostEndPageTask extends ReaderProtocolJSONTask {
    public HostEndPageTask(c cVar, int i) {
        super(cVar);
        this.mUrl = e.cU + "?platform=1" + "&roomId=" + i;
    }
}
