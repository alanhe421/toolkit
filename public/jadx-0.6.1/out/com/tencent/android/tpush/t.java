package com.tencent.android.tpush;

import com.tencent.android.tpush.a.a;

/* compiled from: ProGuard */
final class t implements XGIOperateCallback {
    t() {
    }

    public void onSuccess(Object obj, int i) {
        a.e(XGPushManager.a, "XG register push success with token = " + obj);
    }

    public void onFail(Object obj, int i, String str) {
        a.i(XGPushManager.a, "XG register push failed with token = " + obj + ", errCode =  " + i + ", msg = " + str);
    }
}
