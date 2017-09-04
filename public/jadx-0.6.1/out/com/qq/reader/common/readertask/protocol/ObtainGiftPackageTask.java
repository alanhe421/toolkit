package com.qq.reader.common.readertask.protocol;

import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;

public class ObtainGiftPackageTask extends ReaderProtocolJSONTask {
    public static final int OBTAIN_ALREADY = 1;
    public static final int OBTAIN_FAILED = -1;
    public static final int OBTAIN_NET_ERROR = -2;
    public static final int OBTAIN_SUCCESS = 0;

    public ObtainGiftPackageTask() {
        this.mUrl = d.cg(ReaderApplication.getApplicationImp());
    }
}
