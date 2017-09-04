package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;

public class LuckyDrawTask extends ReaderProtocolJSONTask {
    public LuckyDrawTask(c cVar) {
        super(cVar);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(e.a + "checkinlottery");
        this.mUrl = stringBuffer.toString();
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }
}
