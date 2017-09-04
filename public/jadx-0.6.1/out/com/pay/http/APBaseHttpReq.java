package com.pay.http;

import android.util.Log;
import com.tencent.midas.api.APMidasPayAPI;
import com.tencent.midas.comm.APLog;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import org.apache.http.conn.util.InetAddressUtils;

public abstract class APBaseHttpReq extends Thread {
    private byte[] a;
    private boolean b = false;
    protected IAPHttpAns httpAns;
    public APBaseHttpParam httpParam = new APBaseHttpParam();
    protected HttpURLConnection httpURLConnection;

    private void a() {
        constructParam();
        this.httpParam.constructUrl();
        preCreateConnection();
        c();
        d();
        setHeader();
        setBody();
    }

    private void a(int i, int i2) {
        if (!this.httpParam.urlName.endsWith("log_data")) {
        }
    }

    private void a(int i, int i2, Exception exception, String str) {
        a(i, i2);
        APLog.i("APBaseHttpReq", getClass().getName() + " tryAgain reqTimes = " + this.httpParam.requestTimes + " tryTimes = " + this.httpParam.reTryTimes);
        try {
            if (this.httpParam.requestTimes < this.httpParam.reTryTimes) {
                this.httpParam.constructReTryUrl();
                b();
                return;
            }
            try {
                if (this.httpParam.reqType.equals("https://")) {
                    Throwable th = exception;
                    while (th != null) {
                        if ((th instanceof CertificateExpiredException) || (th instanceof CertificateNotYetValidException)) {
                            APLog.e("APBaseHttpReq", "您的设备系统时间不正确，请更改");
                            this.httpAns.onError(this, APPluginErrorCode.ERROR_NETWORK_HTTPSTIMES, str);
                            return;
                        }
                        th = th.getCause();
                    }
                }
                this.httpAns.onError(this, 1000, str);
                APLog.i("APBaseHttpReq", str);
                exception.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e2) {
        }
    }

    private void a(InputStream inputStream, OutputStream outputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
                return;
            }
        }
        if (outputStream != null) {
            outputStream.flush();
            outputStream.close();
        }
        this.httpURLConnection.disconnect();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b() {
        /*
        r9 = this;
        r6 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r3 = 0;
        r1 = 0;
        r8 = new java.io.ByteArrayOutputStream;
        r8.<init>();
        r0 = r9.httpParam;
        r4 = java.lang.System.currentTimeMillis();
        r0.begTime = r4;
        r9.a();
        r0 = r9.httpParam;	 Catch:{ Throwable -> 0x0090 }
        r0 = r0.sendType;	 Catch:{ Throwable -> 0x0090 }
        r2 = "POST";
        r0 = r0.equals(r2);	 Catch:{ Throwable -> 0x0090 }
        if (r0 == 0) goto L_0x003a;
    L_0x0021:
        r0 = new java.io.DataOutputStream;	 Catch:{ Throwable -> 0x0090 }
        r2 = r9.httpURLConnection;	 Catch:{ Throwable -> 0x0090 }
        r2 = r2.getOutputStream();	 Catch:{ Throwable -> 0x0090 }
        r0.<init>(r2);	 Catch:{ Throwable -> 0x0090 }
        r2 = r9.httpParam;	 Catch:{ Throwable -> 0x0090 }
        r2 = r2.urlParams;	 Catch:{ Throwable -> 0x0090 }
        r2 = r2.getBytes();	 Catch:{ Throwable -> 0x0090 }
        r0.write(r2);	 Catch:{ Throwable -> 0x0090 }
        r0.flush();	 Catch:{ Throwable -> 0x0090 }
    L_0x003a:
        r0 = r9.httpURLConnection;	 Catch:{ ConnectTimeoutException -> 0x0095, SocketTimeoutException -> 0x033b, IOException -> 0x021a, Exception -> 0x027b, all -> 0x02dc }
        r7 = r0.getInputStream();	 Catch:{ ConnectTimeoutException -> 0x0095, SocketTimeoutException -> 0x033b, IOException -> 0x021a, Exception -> 0x027b, all -> 0x02dc }
        r0 = r9.httpURLConnection;	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r0 = r0.getResponseCode();	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        if (r0 != r6) goto L_0x018b;
    L_0x0048:
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r2 = new byte[r0];	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r0 = r3;
    L_0x004d:
        r3 = r7.read(r2);	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        if (r3 <= 0) goto L_0x0126;
    L_0x0053:
        r1 = r9.b;	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        if (r1 == 0) goto L_0x0114;
    L_0x0057:
        r0 = java.lang.Thread.currentThread();	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r0.interrupt();	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r9.a(r7, r8);
        r0 = r9.httpParam;
        r0 = r0.reqType;
        r1 = "https://";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x008f;
    L_0x006e:
        r0 = "APBaseHttpReq";
        r1 = "finally https";
        com.tencent.midas.comm.APLog.e(r0, r1);
        r0 = "TLS";
        r0 = javax.net.ssl.SSLContext.getInstance(r0);	 Catch:{ Exception -> 0x00f3 }
        r1 = 0;
        r2 = 0;
        r3 = new java.security.SecureRandom;	 Catch:{ Exception -> 0x00f3 }
        r3.<init>();	 Catch:{ Exception -> 0x00f3 }
        r0.init(r1, r2, r3);	 Catch:{ Exception -> 0x00f3 }
        r0 = r0.getSocketFactory();	 Catch:{ Exception -> 0x00f3 }
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(r0);	 Catch:{ Exception -> 0x00f3 }
    L_0x008f:
        return;
    L_0x0090:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ ConnectTimeoutException -> 0x0095, SocketTimeoutException -> 0x033b, IOException -> 0x021a, Exception -> 0x027b, all -> 0x02dc }
        goto L_0x003a;
    L_0x0095:
        r0 = move-exception;
    L_0x0096:
        r2 = "网络连接超时,请检查网络";
        r9.a(r1, r8);	 Catch:{ all -> 0x0332 }
        r3 = -7;
        r4 = -1;
        r9.a(r3, r4, r0, r2);	 Catch:{ all -> 0x0332 }
        r9.a(r1, r8);
        r0 = r9.httpParam;
        r0 = r0.reqType;
        r1 = "https://";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x008f;
    L_0x00b1:
        r0 = "APBaseHttpReq";
        r1 = "finally https";
        com.tencent.midas.comm.APLog.e(r0, r1);
        r0 = "TLS";
        r0 = javax.net.ssl.SSLContext.getInstance(r0);	 Catch:{ Exception -> 0x00d3 }
        r1 = 0;
        r2 = 0;
        r3 = new java.security.SecureRandom;	 Catch:{ Exception -> 0x00d3 }
        r3.<init>();	 Catch:{ Exception -> 0x00d3 }
        r0.init(r1, r2, r3);	 Catch:{ Exception -> 0x00d3 }
        r0 = r0.getSocketFactory();	 Catch:{ Exception -> 0x00d3 }
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(r0);	 Catch:{ Exception -> 0x00d3 }
        goto L_0x008f;
    L_0x00d3:
        r0 = move-exception;
        r1 = "APBaseHttpReq";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "finally Exception";
        r2 = r2.append(r3);
        r0 = r0.toString();
        r0 = r2.append(r0);
        r0 = r0.toString();
        com.tencent.midas.comm.APLog.e(r1, r0);
        goto L_0x008f;
    L_0x00f3:
        r0 = move-exception;
        r1 = "APBaseHttpReq";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "finally Exception";
        r2 = r2.append(r3);
        r0 = r0.toString();
        r0 = r2.append(r0);
        r0 = r0.toString();
        com.tencent.midas.comm.APLog.e(r1, r0);
        goto L_0x008f;
    L_0x0114:
        r1 = 0;
        r8.write(r2, r1, r3);	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r0 = r0 + r3;
        r1 = r9.httpAns;	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r4 = (long) r0;	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r6 = r9;
        r1.onReceive(r2, r3, r4, r6);	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        goto L_0x004d;
    L_0x0122:
        r0 = move-exception;
        r1 = r7;
        goto L_0x0096;
    L_0x0126:
        r0 = r8.toByteArray();	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r9.a = r0;	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r0 = r9.httpAns;	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r0.onFinish(r9);	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r0 = 0;
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r9.a(r0, r1);	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
    L_0x0137:
        r9.a(r7, r8);
        r0 = r9.httpParam;
        r0 = r0.reqType;
        r1 = "https://";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x008f;
    L_0x0147:
        r0 = "APBaseHttpReq";
        r1 = "finally https";
        com.tencent.midas.comm.APLog.e(r0, r1);
        r0 = "TLS";
        r0 = javax.net.ssl.SSLContext.getInstance(r0);	 Catch:{ Exception -> 0x016a }
        r1 = 0;
        r2 = 0;
        r3 = new java.security.SecureRandom;	 Catch:{ Exception -> 0x016a }
        r3.<init>();	 Catch:{ Exception -> 0x016a }
        r0.init(r1, r2, r3);	 Catch:{ Exception -> 0x016a }
        r0 = r0.getSocketFactory();	 Catch:{ Exception -> 0x016a }
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(r0);	 Catch:{ Exception -> 0x016a }
        goto L_0x008f;
    L_0x016a:
        r0 = move-exception;
        r1 = "APBaseHttpReq";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "finally Exception";
        r2 = r2.append(r3);
        r0 = r0.toString();
        r0 = r2.append(r0);
        r0 = r0.toString();
        com.tencent.midas.comm.APLog.e(r1, r0);
        goto L_0x008f;
    L_0x018b:
        r0 = new java.lang.StringBuilder;	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r0.<init>();	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r1 = "网络错误(错误码";
        r0 = r0.append(r1);	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r1 = r9.httpURLConnection;	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r1 = r1.getResponseCode();	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r0 = r0.append(r1);	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r1 = ")";
        r0 = r0.append(r1);	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r0 = r0.toString();	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r1 = -10;
        r2 = r9.httpURLConnection;	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r2 = r2.getResponseCode();	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        r3 = 0;
        r9.a(r1, r2, r3, r0);	 Catch:{ ConnectTimeoutException -> 0x0122, SocketTimeoutException -> 0x01ba, IOException -> 0x0338, Exception -> 0x0335 }
        goto L_0x0137;
    L_0x01ba:
        r0 = move-exception;
    L_0x01bb:
        r1 = "网络响应超时,请检查网络";
        r9.a(r7, r8);	 Catch:{ all -> 0x0330 }
        r2 = -8;
        r3 = -1;
        r9.a(r2, r3, r0, r1);	 Catch:{ all -> 0x0330 }
        r9.a(r7, r8);
        r0 = r9.httpParam;
        r0 = r0.reqType;
        r1 = "https://";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x008f;
    L_0x01d6:
        r0 = "APBaseHttpReq";
        r1 = "finally https";
        com.tencent.midas.comm.APLog.e(r0, r1);
        r0 = "TLS";
        r0 = javax.net.ssl.SSLContext.getInstance(r0);	 Catch:{ Exception -> 0x01f9 }
        r1 = 0;
        r2 = 0;
        r3 = new java.security.SecureRandom;	 Catch:{ Exception -> 0x01f9 }
        r3.<init>();	 Catch:{ Exception -> 0x01f9 }
        r0.init(r1, r2, r3);	 Catch:{ Exception -> 0x01f9 }
        r0 = r0.getSocketFactory();	 Catch:{ Exception -> 0x01f9 }
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(r0);	 Catch:{ Exception -> 0x01f9 }
        goto L_0x008f;
    L_0x01f9:
        r0 = move-exception;
        r1 = "APBaseHttpReq";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "finally Exception";
        r2 = r2.append(r3);
        r0 = r0.toString();
        r0 = r2.append(r0);
        r0 = r0.toString();
        com.tencent.midas.comm.APLog.e(r1, r0);
        goto L_0x008f;
    L_0x021a:
        r0 = move-exception;
        r7 = r1;
    L_0x021c:
        r1 = "网络连接异常,请检查网络";
        r2 = com.pay.tool.APMidasTools.getErrorCodeFromException(r0);	 Catch:{ all -> 0x0330 }
        r3 = -1;
        r9.a(r2, r3, r0, r1);	 Catch:{ all -> 0x0330 }
        r9.a(r7, r8);
        r0 = r9.httpParam;
        r0 = r0.reqType;
        r1 = "https://";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x008f;
    L_0x0237:
        r0 = "APBaseHttpReq";
        r1 = "finally https";
        com.tencent.midas.comm.APLog.e(r0, r1);
        r0 = "TLS";
        r0 = javax.net.ssl.SSLContext.getInstance(r0);	 Catch:{ Exception -> 0x025a }
        r1 = 0;
        r2 = 0;
        r3 = new java.security.SecureRandom;	 Catch:{ Exception -> 0x025a }
        r3.<init>();	 Catch:{ Exception -> 0x025a }
        r0.init(r1, r2, r3);	 Catch:{ Exception -> 0x025a }
        r0 = r0.getSocketFactory();	 Catch:{ Exception -> 0x025a }
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(r0);	 Catch:{ Exception -> 0x025a }
        goto L_0x008f;
    L_0x025a:
        r0 = move-exception;
        r1 = "APBaseHttpReq";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "finally Exception";
        r2 = r2.append(r3);
        r0 = r0.toString();
        r0 = r2.append(r0);
        r0 = r0.toString();
        com.tencent.midas.comm.APLog.e(r1, r0);
        goto L_0x008f;
    L_0x027b:
        r0 = move-exception;
        r7 = r1;
    L_0x027d:
        r1 = "网络错误，请稍后再试";
        r9.a(r7, r8);	 Catch:{ all -> 0x0330 }
        r2 = -6;
        r3 = -1;
        r9.a(r2, r3, r0, r1);	 Catch:{ all -> 0x0330 }
        r9.a(r7, r8);
        r0 = r9.httpParam;
        r0 = r0.reqType;
        r1 = "https://";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x008f;
    L_0x0298:
        r0 = "APBaseHttpReq";
        r1 = "finally https";
        com.tencent.midas.comm.APLog.e(r0, r1);
        r0 = "TLS";
        r0 = javax.net.ssl.SSLContext.getInstance(r0);	 Catch:{ Exception -> 0x02bb }
        r1 = 0;
        r2 = 0;
        r3 = new java.security.SecureRandom;	 Catch:{ Exception -> 0x02bb }
        r3.<init>();	 Catch:{ Exception -> 0x02bb }
        r0.init(r1, r2, r3);	 Catch:{ Exception -> 0x02bb }
        r0 = r0.getSocketFactory();	 Catch:{ Exception -> 0x02bb }
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(r0);	 Catch:{ Exception -> 0x02bb }
        goto L_0x008f;
    L_0x02bb:
        r0 = move-exception;
        r1 = "APBaseHttpReq";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "finally Exception";
        r2 = r2.append(r3);
        r0 = r0.toString();
        r0 = r2.append(r0);
        r0 = r0.toString();
        com.tencent.midas.comm.APLog.e(r1, r0);
        goto L_0x008f;
    L_0x02dc:
        r0 = move-exception;
        r7 = r1;
    L_0x02de:
        r9.a(r7, r8);
        r1 = r9.httpParam;
        r1 = r1.reqType;
        r2 = "https://";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x030f;
    L_0x02ee:
        r1 = "APBaseHttpReq";
        r2 = "finally https";
        com.tencent.midas.comm.APLog.e(r1, r2);
        r1 = "TLS";
        r1 = javax.net.ssl.SSLContext.getInstance(r1);	 Catch:{ Exception -> 0x0310 }
        r2 = 0;
        r3 = 0;
        r4 = new java.security.SecureRandom;	 Catch:{ Exception -> 0x0310 }
        r4.<init>();	 Catch:{ Exception -> 0x0310 }
        r1.init(r2, r3, r4);	 Catch:{ Exception -> 0x0310 }
        r1 = r1.getSocketFactory();	 Catch:{ Exception -> 0x0310 }
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(r1);	 Catch:{ Exception -> 0x0310 }
    L_0x030f:
        throw r0;
    L_0x0310:
        r1 = move-exception;
        r2 = "APBaseHttpReq";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "finally Exception";
        r3 = r3.append(r4);
        r1 = r1.toString();
        r1 = r3.append(r1);
        r1 = r1.toString();
        com.tencent.midas.comm.APLog.e(r2, r1);
        goto L_0x030f;
    L_0x0330:
        r0 = move-exception;
        goto L_0x02de;
    L_0x0332:
        r0 = move-exception;
        r7 = r1;
        goto L_0x02de;
    L_0x0335:
        r0 = move-exception;
        goto L_0x027d;
    L_0x0338:
        r0 = move-exception;
        goto L_0x021c;
    L_0x033b:
        r0 = move-exception;
        r7 = r1;
        goto L_0x01bb;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pay.http.APBaseHttpReq.b():void");
    }

    private void c() {
        URL url;
        try {
            url = new URL(this.httpParam.url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            url = null;
        }
        if (APMidasPayAPI.env.equals(APMidasPayAPI.ENV_TESTING)) {
            APLog.i("APHttp Request", "URL = " + this.httpParam.url);
        } else {
            APLog.i("APHttp Request", "URL = " + this.httpParam.url + " HOST = " + this.httpParam.defaultDomain);
        }
        this.httpAns.onStart(this);
        try {
            this.httpURLConnection = (HttpURLConnection) url.openConnection();
            this.httpURLConnection.setConnectTimeout(this.httpParam.connectTimeout);
            this.httpURLConnection.setReadTimeout(this.httpParam.readTimeout);
            this.httpURLConnection.setRequestProperty("Host", this.httpParam.defaultDomain);
            this.httpURLConnection.setUseCaches(false);
        } catch (Exception e2) {
            APLog.i("createConnection", e2.toString());
        }
    }

    private void d() {
        try {
            if (this.httpURLConnection.getDoOutput()) {
                try {
                    this.httpURLConnection.getOutputStream().flush();
                    this.httpURLConnection.getOutputStream().close();
                } catch (Throwable th) {
                }
            }
        } catch (Exception e) {
            APLog.i("closeOutput", e.toString());
        }
    }

    public void constructParam() {
    }

    public byte[] getContent() {
        return this.a;
    }

    public IAPHttpAns getHttpAns() {
        return this.httpAns;
    }

    public boolean isIPAddress(String str) {
        return str != null && (InetAddressUtils.isIPv4Address(str) || InetAddressUtils.isIPv6Address(str));
    }

    protected void preCreateConnection() {
    }

    public void requestAgain() {
        b();
    }

    public void run() {
        b();
        super.run();
    }

    protected void setBody() {
    }

    public void setContent(byte[] bArr) {
        this.a = bArr;
    }

    protected void setHeader() {
    }

    public void setHttpAns(IAPHttpAns iAPHttpAns) {
        this.httpAns = iAPHttpAns;
    }

    protected void setUrl(String str, String str2, String str3, String str4) {
        this.httpParam.setUrl(str, str2, str3, str4);
    }

    public void startRequest() {
        start();
    }

    public void stopRequest() {
        Log.i("APBaseHttpReq", "stopRequest");
        this.b = true;
        this.httpAns.onStop(this);
    }
}
