package com.qq.reader.module.question.loader;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;

public class RecordUploadTask extends ReaderProtocolJSONTask {
    public static final String TASKNAME = "RecordUploadTask";

    public RecordUploadTask(c cVar, long j, String str) {
        super(cVar);
        this.mUrl = e.co + "nativepage/authortalk/applyupload?filetype=amr" + "&filesize=" + j + "&filesha=" + str;
    }

    public String getRequestMethod() {
        return Constants.HTTP_GET;
    }

    public boolean isNeedLogin() {
        return true;
    }
}
