package com.yw.game.sdk.login.util.network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.connect.common.Constants;
import com.tencent.qalsdk.sdk.v;
import com.yw.game.sdk.login.util.a;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import tencent.tls.tools.util.APNName;

/* compiled from: HttpUtils */
public class c {
    private static Uri a = Uri.parse("content://telephony/carriers/preferapn");
    private static final HostnameVerifier b = new HostnameVerifier() {
        public boolean verify(String str, SSLSession sSLSession) {
            return str.equalsIgnoreCase(a.a());
        }
    };

    private static String a(String str, String str2) {
        if (str == null) {
            return "";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "UTF-8";
        }
        try {
            return URLEncoder.encode(str, str2).replace("+", "%20").replace(v.n, "%2A").replace("%7E", "~").replace("#", "%23");
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String a(String str) {
        return a(str, null);
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    private static int a(byte[] bArr) {
        return (bArr[0] << 8) | (bArr[1] & 255);
    }

    private static InetSocketAddress b(Context context) {
        Exception e;
        Throwable th;
        Cursor query;
        try {
            query = context.getContentResolver().query(a, null, null, null, null);
            if (query != null) {
                try {
                    query.moveToFirst();
                    String string = query.getString(query.getColumnIndex("proxy"));
                    if (string != null) {
                        string.toLowerCase();
                    }
                    String string2 = query.getString(query.getColumnIndex("port"));
                    if (string2 == null) {
                        if (query != null) {
                            query.close();
                        }
                        return null;
                    }
                    int parseInt;
                    try {
                        parseInt = Integer.parseInt(string2);
                    } catch (Exception e2) {
                        parseInt = -1;
                    }
                    String string3 = query.getString(query.getColumnIndex("apn"));
                    if (string3 != null) {
                        string3.toLowerCase();
                    }
                    query.close();
                    if (string3 == null || !string3.startsWith(APNName.NAME_CTWAP)) {
                        if (string3 != null) {
                            if (string3.startsWith(APNName.NAME_CMWAP)) {
                                if (string == null || string.length() == 0 || r0 <= 0) {
                                    string = "10.0.0.172";
                                    parseInt = 80;
                                }
                            }
                        }
                        if (string3 == null || !string3.startsWith(APNName.NAME_UNIWAP)) {
                            if (string3 == null || !string3.startsWith(APNName.NAME_3GWAP)) {
                                if (string == null || string.trim().length() <= 0 || r0 <= 0) {
                                    if (query != null) {
                                        query.close();
                                    }
                                    return null;
                                }
                            } else if (string == null || string.length() == 0 || r0 <= 0) {
                                string = "10.0.0.172";
                                parseInt = 80;
                            }
                        } else if (string == null || string.length() == 0 || r0 <= 0) {
                            string = "10.0.0.172";
                            parseInt = 80;
                        }
                    } else if (string == null || string.length() == 0 || r0 <= 0) {
                        string = "10.0.0.200";
                        parseInt = 80;
                    }
                    InetSocketAddress createUnresolved = InetSocketAddress.createUnresolved(string, parseInt);
                    if (query == null) {
                        return createUnresolved;
                    }
                    query.close();
                    return createUnresolved;
                } catch (Exception e3) {
                    e = e3;
                    try {
                        Log.e("getProxyByApn", e.toString());
                        if (query != null) {
                            query.close();
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Exception e4) {
            e = e4;
            query = null;
            Log.e("getProxyByApn", e.toString());
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public static InetSocketAddress a(Context context) {
        String host;
        int port;
        InetSocketAddress inetSocketAddress;
        String defaultHost = Proxy.getDefaultHost();
        int defaultPort = Proxy.getDefaultPort();
        if (context != null) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() != 0) {
                    return null;
                }
                if (activeNetworkInfo.getExtraInfo() != null) {
                    String toLowerCase = activeNetworkInfo.getExtraInfo().toLowerCase();
                    if (toLowerCase.startsWith("epc.tmobile.com") || toLowerCase.startsWith("ctnet") || toLowerCase.startsWith("cmnet") || toLowerCase.startsWith("uninet") || toLowerCase.startsWith("3gnet")) {
                        return null;
                    }
                }
                host = Proxy.getHost(context);
                port = Proxy.getPort(context);
                if (host == null || host.trim().length() == 0 || port <= 0) {
                    host = Proxy.getDefaultHost();
                    port = Proxy.getDefaultPort();
                    if (host == null || host.trim().length() == 0 || port <= 0) {
                        return b(context);
                    }
                }
                if (host != null || host.trim().length() <= 0) {
                    inetSocketAddress = null;
                } else {
                    inetSocketAddress = InetSocketAddress.createUnresolved(host, port);
                }
                return inetSocketAddress;
            }
        }
        port = defaultPort;
        host = defaultHost;
        if (host != null) {
        }
        inetSocketAddress = null;
        return inetSocketAddress;
    }

    private static InputStream c(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = httpURLConnection.getInputStream();
        if (httpURLConnection.getContentEncoding() == null || !Arrays.toString(httpURLConnection.getContentEncoding().getBytes()).contains("gzip")) {
            return inputStream;
        }
        InputStream bufferedInputStream = new BufferedInputStream(inputStream);
        bufferedInputStream.mark(2);
        byte[] bArr = new byte[2];
        int read = bufferedInputStream.read(bArr);
        bufferedInputStream.reset();
        int a = a(bArr);
        if (read == -1 || a != 8075) {
            return bufferedInputStream;
        }
        return new GZIPInputStream(bufferedInputStream);
    }

    public static void a() {
        b();
        HttpsURLConnection.setDefaultHostnameVerifier(b);
    }

    @SuppressLint({"TrustAllX509TrustManager"})
    private static void b() {
        TrustManager[] trustManagerArr = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }
        }};
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, trustManagerArr, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(HttpURLConnection httpURLConnection, List<HashMap<String, String>> list) {
        if (list != null && list.size() > 0) {
            for (Map entrySet : list) {
                for (Entry entry : entrySet.entrySet()) {
                    String str = (String) entry.getValue();
                    if (str != null) {
                        httpURLConnection.setRequestProperty((String) entry.getKey(), str);
                    }
                }
            }
        }
    }

    public static boolean a(Http http) {
        if (http == null) {
            return false;
        }
        String requestMothod = http.getRequestMothod();
        if (Constants.HTTP_POST.equals(requestMothod) || "PUT".equals(requestMothod)) {
            return true;
        }
        return false;
    }

    public static String a(String str, Http http) {
        if (!(str.contains("http://") || str.contains("https://"))) {
            str = "http://" + str;
        }
        if (a(http)) {
            return str;
        }
        ArrayList params = http.getParams();
        if (params == null || params.size() <= 0) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = params.iterator();
        while (it.hasNext()) {
            Map map = (Map) it.next();
            if (stringBuilder.length() != 0) {
                stringBuilder.append("&");
            }
            for (Entry entry : map.entrySet()) {
                stringBuilder.append(a((String) entry.getKey()));
                stringBuilder.append("=");
                stringBuilder.append(a((String) entry.getValue()));
            }
        }
        if (str.contains("?")) {
            return str + "&" + stringBuilder.toString();
        }
        return str + "?" + stringBuilder.toString();
    }

    public static void a(HttpURLConnection httpURLConnection, Http http) throws IOException {
        Throwable th;
        if (http != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setChunkedStreamingMode(0);
            Closeable closeable = null;
            try {
                Closeable bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                try {
                    PrintWriter printWriter = new PrintWriter(bufferedOutputStream);
                    ArrayList params = http.getParams();
                    if (params != null && params.size() > 0) {
                        Iterator it = params.iterator();
                        boolean z = true;
                        while (it.hasNext()) {
                            Map map = (Map) it.next();
                            if (!z) {
                                printWriter.append('&');
                            }
                            boolean z2 = z;
                            for (Entry entry : map.entrySet()) {
                                CharSequence a = a((String) entry.getKey());
                                CharSequence a2 = a((String) entry.getValue());
                                if (com.yw.game.sdk.login.util.c.a) {
                                    com.yw.game.sdk.login.util.c.a("encodedName:" + a + " encodedValue:" + a2);
                                }
                                printWriter.append(a).append('=').append(a2);
                                z2 = false;
                            }
                            z = z2;
                        }
                    }
                    printWriter.flush();
                    a(bufferedOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    closeable = bufferedOutputStream;
                    a(closeable);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                a(closeable);
                throw th;
            }
        }
    }

    public static f a(HttpURLConnection httpURLConnection) throws IOException {
        return new f(httpURLConnection.getContentType(), (long) httpURLConnection.getContentLength(), new BufferedInputStream(b(httpURLConnection)), httpURLConnection);
    }

    public static InputStream b(HttpURLConnection httpURLConnection) {
        try {
            return c(httpURLConnection);
        } catch (IOException e) {
            e.printStackTrace();
            com.yw.game.sdk.login.util.c.a("IOException", e.toString());
            return httpURLConnection.getErrorStream();
        }
    }
}
