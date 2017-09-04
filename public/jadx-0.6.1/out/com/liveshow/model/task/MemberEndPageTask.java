package com.liveshow.model.task;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class MemberEndPageTask extends ReaderProtocolJSONTask {
    public MemberEndPageTask(c cVar, int i) {
        super(cVar);
        this.mUrl = e.cT + "?platform=1" + "&roomId=" + i;
    }
}
