package com.tencent.upload.b.a;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.qalsdk.core.c;
import com.tencent.upload.log.b;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultHttpRoutePlanner;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

final class a {
    private static final a a = new a();

    public static final class a {
        public a() {
            TimeUnit timeUnit = TimeUnit.SECONDS;
        }
    }

    public static HttpResponse a(Context context, String str, HttpEntity httpEntity) {
        HttpClient a = a(null);
        String replace = str.trim().replace(" ", "");
        int indexOf = replace.indexOf(35);
        if (indexOf > 0) {
            replace = replace.substring(0, indexOf);
        }
        String authority = new URL(replace).getAuthority();
        HttpUriRequest httpPost = new HttpPost(replace);
        httpPost.addHeader("Host", authority);
        httpPost.addHeader("x-online-host", authority);
        Object obj = "qua@123";
        if (!TextUtils.isEmpty(obj)) {
            httpPost.addHeader("Q-UA", obj);
        }
        if (httpEntity instanceof ByteArrayEntity) {
            httpPost.addHeader("Content-Type", "application/octet-stream");
        }
        httpPost.setEntity(httpEntity);
        return a.execute(httpPost);
    }

    private static HttpClient a(a aVar) {
        a aVar2 = a;
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, false);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 20000);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 45000);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(basicHttpParams, "UserAgent");
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        try {
            schemeRegistry.register(new Scheme(c.d, PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        } catch (Throwable th) {
            b.c("HttpUtils", "", th);
        }
        ClientConnectionManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        HttpClient defaultHttpClient = new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
        defaultHttpClient.setRoutePlanner(new DefaultHttpRoutePlanner(threadSafeClientConnManager.getSchemeRegistry()));
        return defaultHttpClient;
    }
}
