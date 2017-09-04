package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.sdk.QbSdk.PreInitCallback;
import com.tencent.smtt.sdk.QbSdk.WebviewInitType;

final class e implements QbSdk$a {
    final /* synthetic */ WebviewInitType a;
    final /* synthetic */ Context b;
    final /* synthetic */ PreInitCallback c;

    e(WebviewInitType webviewInitType, Context context, PreInitCallback preInitCallback) {
        this.a = webviewInitType;
        this.b = context;
        this.c = preInitCallback;
    }

    public void a() {
        if (this.a == WebviewInitType.FIRSTUSE_AND_PRELOAD) {
            QbSdk.preInit(this.b, this.c, Boolean.valueOf(false));
        }
    }
}
