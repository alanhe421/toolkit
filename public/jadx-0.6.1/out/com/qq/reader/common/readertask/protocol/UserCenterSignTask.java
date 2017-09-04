package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import java.net.URLEncoder;

public class UserCenterSignTask extends ReaderProtocolJSONTask {
    public UserCenterSignTask(c cVar, String str) {
        super(cVar);
        try {
            this.mUrl = e.bY + "signature=" + URLEncoder.encode(str, "utf-8");
        } catch (Exception e) {
        }
    }
}
