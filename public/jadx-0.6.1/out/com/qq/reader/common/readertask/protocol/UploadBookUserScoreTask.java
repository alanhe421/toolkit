package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class UploadBookUserScoreTask extends ReaderProtocolJSONTask {
    public UploadBookUserScoreTask(long j, float f, c cVar) {
        super(cVar);
        this.mUrl = e.cf + "?bid=" + j + "&score=" + f;
    }
}
