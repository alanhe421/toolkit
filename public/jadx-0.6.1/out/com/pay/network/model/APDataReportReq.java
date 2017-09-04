package com.pay.network.model;

import android.text.TextUtils;
import com.pay.http.APHttpReqPost;
import com.pay.http.APPluginUrlConf;
import com.tencent.midas.data.APPluginDataInterface;

public class APDataReportReq extends APHttpReqPost {
    public APDataReportReq() {
        if (!TextUtils.isEmpty(APPluginDataInterface.singleton().getOfferId())) {
            setUrl("", String.format("/v1/r/%s/log_data", new Object[]{APPluginDataInterface.singleton().getOfferId()}), String.format("/v1/r/%s/log_data", new Object[]{r0}), String.format(APPluginUrlConf.AP_LOGREPORT_FCG, new Object[]{r0}));
        }
    }

    public void startService(String str) {
        if (!str.equals("") && !TextUtils.isEmpty(APPluginDataInterface.singleton().getOfferId())) {
            this.httpParam.reqParam.clear();
            this.httpParam.reqParam.put(str, "");
            startRequest();
        }
    }
}
