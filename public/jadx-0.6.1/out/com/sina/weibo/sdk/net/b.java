package com.sina.weibo.sdk.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/* compiled from: ConnectionFactory */
public class b {
    public static HttpURLConnection a(String str, Context context) {
        if (TextUtils.isEmpty(str) || !(str.startsWith("http://") || str.startsWith("https://"))) {
            throw new RuntimeException("非法url请求");
        }
        HttpURLConnection httpURLConnection;
        try {
            Proxy proxy;
            URL url = new URL(str);
            Pair a = NetStateManager.a();
            if (a != null) {
                proxy = new Proxy(Type.HTTP, new InetSocketAddress((String) a.first, ((Integer) a.second).intValue()));
            } else {
                proxy = null;
            }
            if (str.startsWith("http://")) {
                httpURLConnection = proxy == null ? (HttpURLConnection) url.openConnection() : (HttpURLConnection) url.openConnection(proxy);
                httpURLConnection.setUseCaches(false);
                try {
                    httpURLConnection.setRequestMethod(Constants.HTTP_POST);
                } catch (ProtocolException e) {
                }
                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.setRequestProperty("Charset", "UTF-8");
                httpURLConnection.setReadTimeout(20000);
                httpURLConnection.setConnectTimeout(25000);
                return httpURLConnection;
            }
            HttpURLConnection httpURLConnection2;
            if (proxy == null) {
                httpURLConnection2 = (HttpsURLConnection) url.openConnection();
            } else {
                httpURLConnection2 = (HttpsURLConnection) url.openConnection(proxy);
            }
            try {
                ((HttpsURLConnection) httpURLConnection2).setSSLSocketFactory(c.a(context));
                httpURLConnection = httpURLConnection2;
            } catch (MalformedURLException e2) {
                httpURLConnection = httpURLConnection2;
            } catch (IOException e3) {
                httpURLConnection = httpURLConnection2;
            }
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod(Constants.HTTP_POST);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setReadTimeout(20000);
            httpURLConnection.setConnectTimeout(25000);
            return httpURLConnection;
        } catch (MalformedURLException e4) {
            httpURLConnection = null;
        } catch (IOException e5) {
            httpURLConnection = null;
        }
    }
}
