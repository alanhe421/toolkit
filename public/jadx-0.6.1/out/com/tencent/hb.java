package com.tencent;

import com.tencent.imcore.IStatusSetUserDefCallback;
import com.tencent.imsdk.IMMsfCoreProxy;

final class hb extends IStatusSetUserDefCallback {
    private TIMCallBack a;

    hb(TIMManager tIMManager, TIMCallBack tIMCallBack) {
        swigReleaseOwnership();
        this.a = tIMCallBack;
    }

    public final void done() {
        if (this.a != null) {
            IMMsfCoreProxy.mainHandler.post(new hc(this));
        }
        swigTakeOwnership();
    }

    public final void fail(int i, String str) {
        if (this.a != null) {
            IMMsfCoreProxy.mainHandler.post(new hd(this, i, str));
        }
        swigTakeOwnership();
    }
}
