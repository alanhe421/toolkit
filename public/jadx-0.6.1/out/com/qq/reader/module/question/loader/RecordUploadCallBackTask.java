package com.qq.reader.module.question.loader;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;

public class RecordUploadCallBackTask extends ReaderProtocolJSONTask {
    public static final String TASKNAME = "RecordUploadTask";

    public RecordUploadCallBackTask(c cVar, String str, String str2, long j) {
        super(cVar);
        this.mUrl = e.co + "nativepage/authortalk/uploadsuccess?vid=" + str + "&qid=" + str2 + "&vtime=" + j;
    }

    public String getRequestMethod() {
        return Constants.HTTP_GET;
    }

    public boolean isNeedLogin() {
        return true;
    }
}
