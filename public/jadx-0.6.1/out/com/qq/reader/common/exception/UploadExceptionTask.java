package com.qq.reader.common.exception;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.d;
import com.tencent.connect.common.Constants;

public class UploadExceptionTask extends ReaderProtocolTask {
    private String mBody;

    public UploadExceptionTask(d dVar, String str) {
        super(dVar);
        this.mUrl = e.aS;
        this.mBody = str;
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }

    public String getRequestContent() {
        return this.mBody;
    }

    public String getContentType() {
        return "application/octet-stream";
    }
}
