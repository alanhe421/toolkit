package com.yw.game.sdk.login.util.network;

import android.content.Context;
import android.os.Build.VERSION;
import com.yw.game.sdk.login.util.c;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketAddress;
import java.net.URL;

/* compiled from: HttpEngine */
public class a {
    private static boolean a;

    static {
        int i = VERSION.SDK_INT;
        boolean z = (i >= 14 && i < 16) || i < 8;
        a = z;
        c.a();
    }

    public static b a(Context context, Http http) throws IOException {
        HttpURLConnection httpURLConnection;
        String a = c.a(http.getUrlStr(), http);
        URL url = new URL(a);
        SocketAddress a2 = c.a(context);
        if (a2 != null) {
            httpURLConnection = (HttpURLConnection) url.openConnection(new Proxy(Type.HTTP, a2));
            httpURLConnection.setReadTimeout(http.readTimeout * 3);
            httpURLConnection.setConnectTimeout(http.connectTimeout * 3);
        } else {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(http.readTimeout);
            httpURLConnection.setConnectTimeout(http.connectTimeout);
        }
        if (a) {
            httpURLConnection.setRequestProperty("Connection", "Close");
        }
        httpURLConnection.setRequestMethod(http.getRequestMothod());
        httpURLConnection.setRequestProperty("accept", "*/*");
        httpURLConnection.setRequestProperty("connection", "Keep-Alive");
        httpURLConnection.setRequestProperty("charset", http.getCharset());
        httpURLConnection.setDoInput(true);
        c.a(httpURLConnection, http.getHeaders());
        if (c.a(http)) {
            c.a(httpURLConnection, http);
        }
        if (c.a) {
            c.a(a);
        }
        httpURLConnection.connect();
        return new b(http, httpURLConnection.getResponseCode(), httpURLConnection.getHeaderFields(), c.a(httpURLConnection));
    }
}
