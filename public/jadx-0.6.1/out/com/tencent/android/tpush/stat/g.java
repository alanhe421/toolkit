package com.tencent.android.tpush.stat;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

/* compiled from: ProGuard */
class g extends DefaultConnectionKeepAliveStrategy {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        long keepAliveDuration = super.getKeepAliveDuration(httpResponse, httpContext);
        if (keepAliveDuration == -1) {
            return 30000;
        }
        return keepAliveDuration;
    }
}
