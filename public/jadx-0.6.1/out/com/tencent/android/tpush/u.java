package com.tencent.android.tpush;

import com.tencent.android.tpush.a.a;

/* compiled from: ProGuard */
final class u implements XGIOperateCallback {
    u() {
    }

    public void onSuccess(Object obj, int i) {
        a.e(XGPushManager.a, "UnRegisterPush push succeed with token = " + obj + " flag = " + i);
    }

    public void onFail(Object obj, int i, String str) {
        a.i(XGPushManager.a, "UnRegisterPush push failed with token = " + obj + " , errCode = " + i + " , msg = " + str);
    }
}
