package com.tencent.mid.a;

import com.tencent.mid.util.Util;
import com.tencent.mid.util.f;
import com.tencent.mid.util.h;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import qalsdk.n;

public class a {
    private HttpHost a;
    private DefaultHttpClient b;
    private String c;
    private Map<String, String> d;
    private f e;
    private int f;

    public a(String str, Map<String, String> map) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = n.f;
        this.e = Util.getLogger();
        this.a = Util.getHttpProxy();
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, this.f);
        HttpConnectionParams.setSoTimeout(basicHttpParams, this.f);
        this.b = new DefaultHttpClient(basicHttpParams);
        this.e.b("proxy==" + (this.a == null ? "null" : this.a.getHostName()));
        if (this.a != null) {
            this.b.getParams().setParameter("http.route.default-proxy", this.a);
        }
        if (this.a != null && this.a.getHostName().equals("10.0.0.200")) {
            this.b.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("ctwap@mycdma.cn", "vnet.mobi"));
        }
        Logger.getLogger("org.apache.http.wire").setLevel(Level.FINEST);
        Logger.getLogger("org.apache.http.headers").setLevel(Level.FINEST);
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "debug");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "debug");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.headers", "debug");
        this.b.setKeepAliveStrategy(new b(this));
        this.c = str;
        this.d = map;
    }

    private String b() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!(this.d == null || this.d.size() == 0)) {
            int i = 0;
            for (Entry entry : this.d.entrySet()) {
                int i2 = i + 1;
                stringBuilder.append(i == 0 ? "?" : "&");
                stringBuilder.append((String) entry.getKey());
                stringBuilder.append("=");
                stringBuilder.append((String) entry.getValue());
                i = i2;
            }
        }
        return stringBuilder.toString();
    }

    public d a(String str, byte[] bArr, String str2, int i) {
        String a = a(str);
        this.e.b("[" + a + "]Send request(" + bArr.length + "bytes):" + bArr);
        Object httpPost = new HttpPost(a);
        httpPost.setHeader("Connection", "Keep-Alive");
        httpPost.removeHeaders("Cache-Control");
        httpPost.removeHeaders("User-Agent");
        if (this.a != null) {
            httpPost.addHeader("X-Online-Host", this.c);
            httpPost.addHeader("Accept", "*/*");
            httpPost.addHeader("Content-Type", "json");
        } else {
            this.b.getParams().removeParameter("http.route.default-proxy");
        }
        if (this.a == null) {
            httpPost.addHeader("Content-Encoding", str2);
        } else {
            httpPost.addHeader("X-Content-Encoding", str2);
        }
        httpPost.setEntity(new ByteArrayEntity(bArr));
        HttpResponse execute = this.b.execute(httpPost);
        HttpEntity entity = execute.getEntity();
        int statusCode = execute.getStatusLine().getStatusCode();
        this.e.b("recv response status code:" + statusCode + ", content length:" + entity.getContentLength());
        byte[] toByteArray = EntityUtils.toByteArray(entity);
        a = "";
        Header firstHeader = execute.getFirstHeader("Content-Encoding");
        if (firstHeader != null) {
            if (firstHeader.getValue().toUpperCase().contains("AES")) {
                a = new String(c.a(c.a()).a(i).b(toByteArray), "UTF-8");
            }
            if (firstHeader.getValue().toUpperCase().contains("RSA")) {
                a = h.b(toByteArray);
            }
            if (firstHeader.getValue().toUpperCase().contains("IDENTITY")) {
                a = new String(toByteArray, "UTF-8");
            }
        }
        this.e.b("recv response status code:" + statusCode + ", content :" + a);
        return new d(statusCode, a);
    }

    public String a(String str) {
        return this.c + str + b();
    }

    public void a() {
        if (this.b != null) {
            this.b.getConnectionManager().shutdown();
            this.b = null;
            this.c = null;
            this.d = null;
            this.a = null;
        }
    }
}
