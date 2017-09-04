package com.qq.reader.common.offline;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.d;

public class OfflineCheckVersionTask extends ReaderProtocolTask {
    public OfflineCheckVersionTask(d dVar) {
        super(dVar);
        this.mUrl = e.c + "/version.txt";
    }
}
