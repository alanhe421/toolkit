package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class ProjectStateTask extends ReaderProtocolJSONTask {
    public ProjectStateTask(c cVar, int i, String str) {
        super(cVar);
        if (i == 10) {
            this.mUrl = e.S + "id=" + str;
        }
    }
}
