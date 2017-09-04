package com.qq.reader.common.offline;

import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.d;

public class OfflineDownloadTask extends ReaderProtocolTask {
    public OfflineDownloadTask(d dVar) {
        super(dVar);
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }
}
