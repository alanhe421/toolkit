package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;

public class GetSignInfoTask extends ReaderProtocolJSONTask {
    public GetSignInfoTask(c cVar) {
        super(cVar);
        this.mUrl = e.a + "querycheckininfo";
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }
}
