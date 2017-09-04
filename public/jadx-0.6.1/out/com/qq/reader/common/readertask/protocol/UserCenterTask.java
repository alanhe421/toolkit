package com.qq.reader.common.readertask.protocol;

import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class UserCenterTask extends ReaderProtocolJSONTask {
    public UserCenterTask(c cVar, String str) {
        super(cVar);
        this.mUrl = "http://common.reader.bookcs.3g.qq.com/v6_0/common/nativepage/user/profile?uin=" + d.R(ReaderApplication.getApplicationImp()) + "&userId=" + str + "&platform=" + "android";
    }
}
