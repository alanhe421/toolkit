package com.tencent;

import com.tencent.imcore.IStatusCallback;
import com.tencent.imcore.UserStatusVec;
import com.tencent.imsdk.IMMsfCoreProxy;
import java.util.ArrayList;
import java.util.List;

final class gy extends IStatusCallback {
    TIMValueCallBack<List<TIMUserDefinedStatus>> a;

    gy(TIMManager tIMManager, TIMValueCallBack<List<TIMUserDefinedStatus>> tIMValueCallBack) {
        swigReleaseOwnership();
        this.a = tIMValueCallBack;
    }

    public final void done(UserStatusVec userStatusVec) {
        if (this.a != null) {
            List arrayList = new ArrayList();
            if (!(userStatusVec == null || userStatusVec.empty())) {
                for (int i = 0; ((long) i) < userStatusVec.size(); i++) {
                    if (userStatusVec.get(i) != null) {
                        arrayList.add(new TIMUserDefinedStatus(userStatusVec.get(i)));
                    }
                }
            }
            IMMsfCoreProxy.mainHandler.post(new gz(this, arrayList));
        }
        swigTakeOwnership();
    }

    public final void fail(int i, String str) {
        if (this.a != null) {
            IMMsfCoreProxy.mainHandler.post(new ha(this, i, str));
        }
        swigTakeOwnership();
    }
}
