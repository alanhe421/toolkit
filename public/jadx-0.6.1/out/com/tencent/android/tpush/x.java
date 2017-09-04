package com.tencent.android.tpush;

import android.content.Context;

/* compiled from: ProGuard */
final class x implements XGIOperateCallback {
    final /* synthetic */ Context a;

    x(Context context) {
        this.a = context;
    }

    public void onSuccess(Object obj, int i) {
        XGPushManager.a(this.a);
    }

    public void onFail(Object obj, int i, String str) {
        XGPushManager.a(this.a);
    }
}
