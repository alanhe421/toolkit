package com.qq.reader.common.push;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.xiaomi.mipush.sdk.c;

public class XiaoMiShortTaskOfRegId extends ReaderShortTask {
    public static final String APP_ID = "2882303761517455450";
    public static final String APP_KEY = "5221745588450";

    public XiaoMiShortTaskOfRegId() {
        setFailedType(1);
        setPriority(3);
    }

    public void run() {
        super.run();
        c.a(ReaderApplication.getApplicationImp(), APP_ID, APP_KEY);
    }
}
