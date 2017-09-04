package com.qq.reader.common.readertask.protocol;

import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.tencent.connect.common.Constants;
import java.util.HashMap;

public class MSMessageUserTagTask extends ReaderProtocolJSONTask {
    public static final String MESSAGE_STREAM_GET_USERTAG_URL = "http://bookcs.android.reader.qq.com/concept/usertag?";

    public MSMessageUserTagTask() {
        this.mUrl = MESSAGE_STREAM_GET_USERTAG_URL;
    }

    public HashMap<String, String> getBasicHeader() {
        return this.mHeaders;
    }

    public String getRequestMethod() {
        return Constants.HTTP_GET;
    }
}
