package com.qq.reader.cservice.cloud;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class CloudSynUpdateBookTask extends ReaderProtocolJSONTask {
    public CloudSynUpdateBookTask(c cVar, long j, long j2, long j3, int i, int i2, int i3) {
        super(cVar);
        setTid(j3);
        this.mUrl = e.aZ + j + "&lasttime=" + j2 + "&tid=" + j3 + "&noteversion=" + i + "&notenum=" + i2 + "&resType=" + i3;
    }

    public boolean isResponseGzip() {
        return true;
    }

    protected boolean interuptNoConn() {
        return true;
    }
}
