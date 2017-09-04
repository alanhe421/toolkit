package com.qq.reader.module.question.loader;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.utils.networkUtil.e;
import com.tencent.connect.common.Constants;

public class RecordGetDownloadUrlTask extends ReaderProtocolJSONTask {
    public static final String TASKNAME = "RecordGetDownloadUrlTask";

    public RecordGetDownloadUrlTask(c cVar, String str) {
        super(cVar);
        int i = 0;
        if (e.a(ReaderApplication.getApplicationImp())) {
            if (e.a()) {
                i = 1;
            } else {
                i = 3;
            }
        }
        this.mUrl = com.qq.reader.appconfig.e.co + "nativepage/authortalk/getdownloadurl?qid=" + str + "&network=" + i;
    }

    public String getRequestMethod() {
        return Constants.HTTP_GET;
    }
}
