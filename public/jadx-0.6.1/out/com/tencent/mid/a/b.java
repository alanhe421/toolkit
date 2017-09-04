package com.tencent.mid.a;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

class b extends DefaultConnectionKeepAliveStrategy {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        long keepAliveDuration = super.getKeepAliveDuration(httpResponse, httpContext);
        return keepAliveDuration == -1 ? 20000 : keepAliveDuration;
    }
}
