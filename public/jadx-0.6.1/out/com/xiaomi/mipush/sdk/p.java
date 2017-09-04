package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.push.service.k;
import com.xiaomi.xmpush.thrift.f;

final class p implements Runnable {
    final /* synthetic */ Context a;

    p(Context context) {
        this.a = context;
    }

    public void run() {
        if (g.d(this.a)) {
            Object a = g.b(k.a(this.a).a(f.AggregationSdkMonitorDepth.a(), 4));
            if (!TextUtils.isEmpty(a)) {
                e.a(this.a, "monitor_upload", "call_stack", 1, a);
                g.e(this.a);
            }
        }
    }
}
