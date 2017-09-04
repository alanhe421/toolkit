package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.c.a;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.utils.ao;
import com.tencent.qalsdk.sdk.v;
import java.util.HashMap;

public class QueryChargeListTask extends ReaderProtocolJSONTask {
    public QueryChargeListTask(int i) {
        this.mUrl = e.aU + "?channel=" + ao.h(getContext()) + "&prevalue=" + i;
    }

    public HashMap<String, String> getBasicHeader() {
        this.mHeaders.put("resolution", a.bU + v.n + a.bT);
        return this.mHeaders;
    }
}
