package com.tencent.midas.comm;

import android.content.Context;

public class APLogInfo {
    public static final String INNER_LOG_TAG = "TencentPayInner";
    public static final int LOG_VERSION = 3;
    private Context context = null;
    private boolean logEnable = true;
    private String logTag = "TencentPay";

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isLogEnable() {
        return this.logEnable;
    }

    public void setLogEnable(boolean z) {
        this.logEnable = z;
    }

    public String getLogTag() {
        return this.logTag;
    }

    public void setLogTag(String str) {
        this.logTag = str;
    }
}
