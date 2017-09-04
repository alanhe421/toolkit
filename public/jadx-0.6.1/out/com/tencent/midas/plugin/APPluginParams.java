package com.tencent.midas.plugin;

import android.app.Dialog;
import android.content.Intent;
import com.tencent.android.tpush.common.Constants;

public class APPluginParams {
    int a = 0;
    public String mApkFilePath;
    public String mConponentName;
    public Dialog mDialog;
    public boolean mDialogDismissBySDK = true;
    public Intent mIntent;
    public String mPluginName;
    public String mProgressTips;
    public Class<?> mProxyActivityClass;
    public int mRequestCode = -1;
    public int mTimeOut = Constants.ERRORCODE_UNKNOWN;

    public APPluginParams(int i) {
        this.a = i;
    }
}
