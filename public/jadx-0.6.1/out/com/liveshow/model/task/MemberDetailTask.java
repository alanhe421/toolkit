package com.liveshow.model.task;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class MemberDetailTask extends ReaderProtocolJSONTask {
    public MemberDetailTask(c cVar, int i, String str) {
        super(cVar);
        this.mUrl = e.cV + "?platform=1" + "&roomId=" + i + "&userId=" + str;
    }
}
