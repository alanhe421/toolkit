package com.tencent.openqq;

import android.content.Context;
import com.tencent.imsdk.IMMsfCoreProxy;

class IMSdkBase {
    protected static IMMsfCoreProxy msfCoreProxy = IMMsfCoreProxy.get();
    static IMSdkBase sdkbase = new IMSdkBase();
    private static final String tag = "openqq.IMSdkBase";

    protected IMSdkBase() {
    }

    static IMSdkBase get() {
        return sdkbase;
    }

    public void init(Context context) {
        msfCoreProxy.init(context);
    }

    public void setEnv(int i) {
        msfCoreProxy.setEnv(i);
    }

    public void setSdkType(String str) {
        msfCoreProxy.setSdkType(str);
    }
}
