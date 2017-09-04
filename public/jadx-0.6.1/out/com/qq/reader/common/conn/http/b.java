package com.qq.reader.common.conn.http;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.tencent.connect.common.Constants;
import com.tencent.qalsdk.im_open.http;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import tencent.tls.tools.util.APNName;

/* compiled from: Http */
public class b {
    public static String a = "ctnet";
    public static String b = APNName.NAME_CTWAP;
    public static String c = "epc.tmobile.com";
    public static String d = "cmnet";
    public static String e = APNName.NAME_CMWAP;
    public static String f = "uninet";
    public static String g = APNName.NAME_UNIWAP;
    public static String h = "3gnet";
    public static String i = APNName.NAME_3GWAP;
    private static Uri j = Uri.parse("content://telephony/carriers/preferapn");

    /* compiled from: Http */
    private static class a extends SSLSocketFactory {
        SSLContext a = SSLContext.getInstance("TLS");

        public a(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(keyStore);
            AnonymousClass1 anonymousClass1 = new X509TrustManager(this) {
                public X509Certificate a;
                final /* synthetic */ a b;

                {
                    this.b = r1;
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                    c.a("Http", "X509TrustManager checkServerTrusted ");
                    ReaderApplication.getInstance();
                    if (com.qq.reader.appconfig.a.b(ReaderApplication.getApplicationContext())) {
                        IllegalArgumentException illegalArgumentException;
                        if (x509CertificateArr == null) {
                            illegalArgumentException = new IllegalArgumentException("chain is null");
                        }
                        if (x509CertificateArr.length <= 0) {
                            illegalArgumentException = new IllegalArgumentException("chain is empty");
                        }
                        this.a = this.a == null ? a() : this.a;
                        c.a("Http", "X509TrustManager checkServerTrusted " + this.a.getPublicKey() + "  ");
                        for (X509Certificate x509Certificate : x509CertificateArr) {
                            x509Certificate.checkValidity();
                            try {
                                x509Certificate.verify(this.a.getPublicKey());
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            } catch (InvalidKeyException e2) {
                                e2.printStackTrace();
                            } catch (NoSuchProviderException e3) {
                                e3.printStackTrace();
                            } catch (SignatureException e4) {
                                e4.printStackTrace();
                            }
                        }
                        c.a("Http", "X509TrustManager checkServerTrusted finish ");
                    }
                }

                private X509Certificate a() {
                    try {
                        ReaderApplication.getInstance();
                        return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(ReaderApplication.getApplicationContext().getAssets().open("server.cer"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (CertificateException e2) {
                        e2.printStackTrace();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    return null;
                }
            };
            this.a.init(null, new TrustManager[]{anonymousClass1}, null);
        }

        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
            return this.a.getSocketFactory().createSocket(socket, str, i, z);
        }

        public Socket createSocket() throws IOException {
            return this.a.getSocketFactory().createSocket();
        }
    }

    public static InputStream a(String str, byte[] bArr, String str2, HashMap<String, String> hashMap, String str3, Context context) throws HttpResponseException, IOException, Exception {
        return b(str, bArr, str2, hashMap, str3, context).getEntity().getContent();
    }

    public static HttpResponse b(String str, byte[] bArr, String str2, HashMap<String, String> hashMap, String str3, Context context) throws HttpResponseException, IOException, Exception {
        return a(str, bArr, false, str2, hashMap, str3, context);
    }

    public static HttpResponse a(String str, byte[] bArr, boolean z, String str2, HashMap<String, String> hashMap, String str3, Context context) throws HttpResponseException, IOException, Exception {
        return a(a(str, bArr, z, str2, (HashMap) hashMap, str3), context);
    }

    private static HttpResponse a(HttpRequestBase httpRequestBase, Context context) throws HttpResponseException, IOException, Exception {
        HttpResponse b;
        Object obj = null;
        int i = 0;
        while (true) {
            b = b(httpRequestBase, context);
            int statusCode = b.getStatusLine().getStatusCode();
            if (statusCode != 302 && statusCode != 301) {
                if (statusCode == 200) {
                    Header contentType = b.getEntity().getContentType();
                    if (contentType == null) {
                        break;
                    }
                    String value = contentType.getValue();
                    if (value == null || (value.indexOf("text/vnd.wap.wml") == -1 && value.indexOf("application/vnd.wap.wmlc") == -1)) {
                        break;
                    }
                    i++;
                    if (i < 3) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        throw new HttpResponseException(statusCode);
                    }
                }
                throw new HttpResponseException(statusCode);
            }
            i++;
            if (i < 3) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            } else {
                throw new HttpResponseException(statusCode);
            }
        }
        return b;
    }

    private static HttpResponse b(HttpRequestBase httpRequestBase, Context context) throws Exception {
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 20000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 25000);
        KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
        instance.load(null, null);
        final String host = httpRequestBase.getURI().getHost();
        SocketFactory aVar = new a(instance);
        aVar.setHostnameVerifier(new X509HostnameVerifier() {
            public boolean verify(String str, SSLSession sSLSession) {
                if (str.equalsIgnoreCase(host)) {
                    return true;
                }
                return false;
            }

            public void verify(String str, SSLSocket sSLSocket) throws IOException {
            }

            public void verify(String str, X509Certificate x509Certificate) throws SSLException {
            }

            public void verify(String str, String[] strArr, String[] strArr2) throws SSLException {
            }
        });
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(com.tencent.qalsdk.core.c.d, PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", aVar, 443));
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        defaultHttpClient.getParams().setParameter("http.protocol.handle-redirects", Boolean.valueOf(false));
        InetSocketAddress a = a(context);
        if (a != null) {
            defaultHttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(a.getHostName(), a.getPort()));
        }
        return defaultHttpClient.execute(httpRequestBase);
    }

    private static HttpRequestBase a(String str, byte[] bArr, boolean z, String str2, HashMap<String, String> hashMap, String str3) throws IOException {
        HttpRequestBase httpRequestBase;
        ByteArrayEntity byteArrayEntity = null;
        if (str2.equals(Constants.HTTP_POST)) {
            HttpRequestBase httpPost = new HttpPost(str);
            if (bArr != null) {
                HttpEntity httpEntity;
                if (z) {
                    byte[] b = ao.b(bArr);
                    if (b != null) {
                        byteArrayEntity = new ByteArrayEntity(b);
                    } else {
                        i.a("event_commall_post_null", null, ReaderApplication.getApplicationContext());
                    }
                    httpEntity = byteArrayEntity;
                } else {
                    Object byteArrayEntity2 = new ByteArrayEntity(bArr);
                }
                if (httpEntity != null) {
                    if (str3 != null) {
                        httpEntity.setContentType(str3);
                    }
                    ((HttpPost) httpPost).setEntity(httpEntity);
                }
            }
            httpRequestBase = httpPost;
        } else {
            if (bArr != null) {
                str = str + "?" + new String(bArr);
            }
            httpRequestBase = new HttpGet(str);
        }
        if (hashMap != null) {
            for (Entry entry : hashMap.entrySet()) {
                httpRequestBase.addHeader(new BasicHeader((String) entry.getKey(), (String) entry.getValue()));
            }
        }
        return httpRequestBase;
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
                    if (toLowerCase.startsWith(c) || toLowerCase.startsWith(a) || toLowerCase.startsWith(d) || toLowerCase.startsWith(f) || toLowerCase.startsWith(h)) {
                        return null;
                    }
                }
                host = Proxy.getHost(context);
                port = Proxy.getPort(context);
                if (host == null || host.trim().length() == 0 || port <= 0) {
                    host = Proxy.getDefaultHost();
                    port = Proxy.getDefaultPort();
                    if (host == null || host.trim().length() == 0 || port <= 0) {
                        return c(context);
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

    public static HttpURLConnection a(String str, BasicHeader[] basicHeaderArr, Context context) throws IOException {
        return a(str, basicHeaderArr, context, true);
    }

    private static HttpURLConnection a(String str, BasicHeader[] basicHeaderArr, Context context, boolean z) throws IOException {
        HttpURLConnection a = a(new URL(str), context);
        a.setConnectTimeout(20000);
        a.setReadTimeout(25000);
        if (com.qq.reader.common.c.a.y) {
            a.setRequestProperty("Connection", "Close");
        }
        if (basicHeaderArr != null) {
            for (BasicHeader basicHeader : basicHeaderArr) {
                a.setRequestProperty(basicHeader.getName(), basicHeader.getValue());
            }
        }
        int responseCode = a.getResponseCode();
        switch (responseCode) {
            case 200:
            case 206:
                String contentType = a.getContentType();
                if (!(contentType == null || (contentType.indexOf("text/vnd.wap.wml") == -1 && contentType.indexOf("application/vnd.wap.wmlc") == -1))) {
                    if (a != null) {
                        a.disconnect();
                    }
                    if (z) {
                        a = a(str, basicHeaderArr, context, false);
                        break;
                    }
                    throw new HttpErrorException(1003, "HTTP Response Code: " + responseCode + " wapCotentType : " + contentType);
                }
            case 301:
            case 302:
            case 307:
                if (a != null) {
                    a.disconnect();
                }
                if (z) {
                    a = a(str, basicHeaderArr, context, false);
                    break;
                }
                throw new HttpErrorException(1004, "HTTP Response Code: " + responseCode);
            case 400:
                throw new HttpErrorException(1000, "HTTP Response Code: " + responseCode);
            case 404:
                throw new HttpErrorException(404, "HTTP Response Code: " + responseCode);
            case http.Requested_Range_Not_Satisfiable /*416*/:
                throw new RangeException();
            default:
                throw new IOException("HTTP Response Code: " + responseCode);
        }
        f.e("Http", "prepareConnection.Finish to prepare connection");
        return a;
    }

    public static HttpURLConnection a(URL url, Context context) throws IOException {
        SocketAddress a = a(context);
        if (a == null) {
            return (HttpURLConnection) url.openConnection();
        }
        return (HttpURLConnection) url.openConnection(new java.net.Proxy(Type.HTTP, a));
    }

    public static byte[] a(String str, Context context) throws IOException {
        HttpURLConnection a = a(new URL(str), context);
        a.setConnectTimeout(20000);
        a.setReadTimeout(25000);
        InputStream inputStream = null;
        try {
            inputStream = a.getInputStream();
            byte[] a2 = a(a.getContentLength(), inputStream);
            if (inputStream != null) {
                inputStream.close();
            }
            return a2;
        } catch (IOException e) {
            throw e;
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public static byte[] a(int i, InputStream inputStream) throws IOException {
        Object obj = new byte[i];
        Object obj2 = new byte[5120];
        int i2 = 0;
        while (true) {
            int read = inputStream.read(obj2);
            if (read == -1) {
                return obj;
            }
            System.arraycopy(obj2, 0, obj, i2, read);
            i2 += read;
        }
    }

    public static String a(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return stringBuilder.toString();
                }
                stringBuilder.append(readLine);
            } catch (IOException e) {
                throw e;
            }
        }
    }

    public static String b(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read == -1) {
                return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    private static InetSocketAddress c(Context context) {
        Exception e;
        Throwable th;
        Cursor query;
        try {
            query = context.getContentResolver().query(j, null, null, null, null);
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
                if (string3 == null || !string3.startsWith(b)) {
                    if (string3 != null) {
                        if (string3.startsWith(e)) {
                            if (string == null || string.length() == 0 || Integer.valueOf(r0).intValue() <= 0) {
                                string = "10.0.0.172";
                                parseInt = 80;
                            }
                        }
                    }
                    if (string3 == null || !string3.startsWith(g)) {
                        if (string3 == null || !string3.startsWith(i)) {
                            if (string == null || string.trim().length() <= 0 || r0 <= 0) {
                                if (query != null) {
                                    query.close();
                                }
                                return null;
                            }
                        } else if (string == null || string.length() == 0 || Integer.valueOf(r0).intValue() <= 0) {
                            string = "10.0.0.172";
                            parseInt = 80;
                        }
                    } else if (string == null || string.length() == 0 || Integer.valueOf(r0).intValue() <= 0) {
                        string = "10.0.0.172";
                        parseInt = 80;
                    }
                } else if (string == null || string.length() == 0 || Integer.valueOf(r0).intValue() <= 0) {
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
                    f.a("getProxyByApn", e.toString());
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
        } catch (Exception e4) {
            e = e4;
            query = null;
            f.a("getProxyByApn", e.toString());
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

    public static String b(Context context) {
        Cursor query;
        Throwable th;
        String str = "";
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return str;
        }
        String str2;
        if (activeNetworkInfo.getType() == 1) {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            str2 = "";
            if (connectionInfo != null) {
                str2 = connectionInfo.getSSID();
            }
            return "wifi_" + str2;
        }
        if (activeNetworkInfo.getExtraInfo() != null) {
            str2 = activeNetworkInfo.getExtraInfo().toLowerCase();
        } else {
            str2 = str;
        }
        if (str2 != null) {
            return str2;
        }
        try {
            query = context.getContentResolver().query(j, null, null, null, null);
            try {
                query.moveToFirst();
                str2 = query.getString(query.getColumnIndex("apn"));
                if (str2 != null) {
                    str2.toLowerCase();
                }
                if (query == null) {
                    return str2;
                }
                try {
                    query.close();
                    return str2;
                } catch (Throwable th2) {
                    return str2;
                }
            } catch (Exception e) {
                try {
                    str2 = "";
                    if (query != null) {
                        return str2;
                    }
                    try {
                        query.close();
                        return str2;
                    } catch (Throwable th3) {
                        return str2;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable th5) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e2) {
            query = null;
            str2 = "";
            if (query != null) {
                return str2;
            }
            query.close();
            return str2;
        } catch (Throwable th6) {
            th = th6;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }
}
