package com.tencent.android.tpush;

import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;

/* compiled from: ProGuard */
final class d implements XGIOperateCallback {
    d() {
    }

    public void onSuccess(Object obj, int i) {
        a.d(Constants.MSDK_TAG, "xg register push onSuccess. token:" + obj);
    }

    public void onFail(Object obj, int i, String str) {
        a.h(Constants.MSDK_TAG, "xg register push onFail. token:" + obj + ", errCode:" + i + ",msg:" + str);
    }
}
