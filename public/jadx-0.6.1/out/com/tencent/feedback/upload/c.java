package com.tencent.feedback.upload;

import android.content.Context;
import android.net.Proxy;
import com.pay.http.APPluginErrorCode;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.common.e;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import qalsdk.n;

/* compiled from: RQDSRC */
public abstract class c {
    private static c a = null;

    /* compiled from: RQDSRC */
    public static class a extends c {
        private Context a;

        public a(Context context) {
            if (context != null) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    context = applicationContext;
                }
            }
            this.a = context;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final byte[] a(java.lang.String r11, byte[] r12, com.tencent.feedback.upload.b r13, java.util.Map<java.lang.String, java.lang.String> r14) {
            /*
            r10 = this;
            if (r11 != 0) goto L_0x000d;
        L_0x0002:
            r0 = "rqdp{  no destUrl!}";
            r1 = 0;
            r1 = new java.lang.Object[r1];
            com.tencent.feedback.common.e.d(r0, r1);
            r0 = 0;
        L_0x000c:
            return r0;
        L_0x000d:
            r4 = 0;
            r3 = 0;
            if (r12 != 0) goto L_0x0062;
        L_0x0011:
            r0 = 0;
        L_0x0013:
            r2 = "rqdp{  start req} %s rqdp{  sz:}%d";
            r5 = 2;
            r5 = new java.lang.Object[r5];
            r6 = 0;
            r5[r6] = r11;
            r6 = 1;
            r7 = java.lang.Long.valueOf(r0);
            r5[r6] = r7;
            com.tencent.feedback.common.e.b(r2, r5);
            r2 = 0;
        L_0x0027:
            r5 = r4 + 1;
            r6 = 3;
            if (r4 >= r6) goto L_0x010f;
        L_0x002c:
            r4 = 2;
            if (r3 >= r4) goto L_0x010f;
        L_0x002f:
            if (r2 == 0) goto L_0x0065;
        L_0x0031:
            r2 = 0;
        L_0x0032:
            r4 = r10.a;
            r4 = com.tencent.feedback.common.g.c(r4);
            if (r13 == 0) goto L_0x003d;
        L_0x003a:
            r13.a(r11, r0, r4);
        L_0x003d:
            r6 = r10.a(r11, r12, r4, r14);
            if (r6 == 0) goto L_0x0105;
        L_0x0043:
            r7 = r6.getEntity();
            r4 = r6.getStatusLine();
            r8 = r4.getStatusCode();
            r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
            if (r8 != r4) goto L_0x0092;
        L_0x0053:
            r2 = a(r6);
            if (r13 == 0) goto L_0x0060;
        L_0x0059:
            if (r2 != 0) goto L_0x008f;
        L_0x005b:
            r0 = 0;
        L_0x005d:
            r13.a(r0);
        L_0x0060:
            r0 = r2;
            goto L_0x000c;
        L_0x0062:
            r0 = r12.length;
            r0 = (long) r0;
            goto L_0x0013;
        L_0x0065:
            r4 = 1;
            if (r5 <= r4) goto L_0x0032;
        L_0x0068:
            r4 = new java.lang.StringBuilder;
            r6 = "rqdp{  try time} ";
            r4.<init>(r6);
            r4 = r4.append(r5);
            r4 = r4.toString();
            r6 = 0;
            r6 = new java.lang.Object[r6];
            com.tencent.feedback.common.e.b(r4, r6);
            r6 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
            java.lang.Thread.sleep(r6);	 Catch:{ InterruptedException -> 0x0084 }
            goto L_0x0032;
        L_0x0084:
            r4 = move-exception;
            r6 = com.tencent.feedback.common.e.a(r4);
            if (r6 != 0) goto L_0x0032;
        L_0x008b:
            r4.printStackTrace();
            goto L_0x0032;
        L_0x008f:
            r0 = r2.length;
            r0 = (long) r0;
            goto L_0x005d;
        L_0x0092:
            r4 = 301; // 0x12d float:4.22E-43 double:1.487E-321;
            if (r8 == r4) goto L_0x00a2;
        L_0x0096:
            r4 = 302; // 0x12e float:4.23E-43 double:1.49E-321;
            if (r8 == r4) goto L_0x00a2;
        L_0x009a:
            r4 = 303; // 0x12f float:4.25E-43 double:1.497E-321;
            if (r8 == r4) goto L_0x00a2;
        L_0x009e:
            r4 = 307; // 0x133 float:4.3E-43 double:1.517E-321;
            if (r8 != r4) goto L_0x00cf;
        L_0x00a2:
            r4 = 1;
        L_0x00a3:
            if (r4 == 0) goto L_0x0112;
        L_0x00a5:
            r4 = 1;
            r2 = "Location";
            r2 = r6.getFirstHeader(r2);
            if (r2 != 0) goto L_0x00d1;
        L_0x00af:
            r0 = new java.lang.StringBuilder;
            r1 = "rqdp{  redirect code:}";
            r0.<init>(r1);
            r0 = r0.append(r8);
            r1 = "rqdp{   Location is null! return}";
            r0 = r0.append(r1);
            r0 = r0.toString();
            r1 = 0;
            r1 = new java.lang.Object[r1];
            com.tencent.feedback.common.e.d(r0, r1);
            r0 = 0;
            goto L_0x000c;
        L_0x00cf:
            r4 = 0;
            goto L_0x00a3;
        L_0x00d1:
            r5 = r3 + 1;
            r6 = 0;
            r11 = r2.getValue();
            r2 = "rqdp{  redirect code:}%d rqdp{  , to:}%s";
            r3 = 2;
            r3 = new java.lang.Object[r3];
            r9 = 0;
            r8 = java.lang.Integer.valueOf(r8);
            r3[r9] = r8;
            r8 = 1;
            r3[r8] = r11;
            com.tencent.feedback.common.e.b(r2, r3);
        L_0x00eb:
            r2 = 0;
            if (r7 == 0) goto L_0x00fb;
        L_0x00ef:
            r2 = r7.getContentLength();
            r8 = 0;
            r7 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
            if (r7 >= 0) goto L_0x00fb;
        L_0x00f9:
            r2 = 0;
        L_0x00fb:
            if (r13 == 0) goto L_0x0100;
        L_0x00fd:
            r13.a(r2);
        L_0x0100:
            r2 = r4;
            r3 = r5;
            r4 = r6;
            goto L_0x0027;
        L_0x0105:
            if (r13 == 0) goto L_0x010c;
        L_0x0107:
            r6 = 0;
            r13.a(r6);
        L_0x010c:
            r4 = r5;
            goto L_0x0027;
        L_0x010f:
            r0 = 0;
            goto L_0x000c;
        L_0x0112:
            r4 = r2;
            r6 = r5;
            r5 = r3;
            goto L_0x00eb;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.feedback.upload.c.a.a(java.lang.String, byte[], com.tencent.feedback.upload.b, java.util.Map):byte[]");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private org.apache.http.HttpResponse a(java.lang.String r9, byte[] r10, java.lang.String r11, java.util.Map<java.lang.String, java.lang.String> r12) {
            /*
            r8 = this;
            r3 = 0;
            r2 = 0;
            if (r9 != 0) goto L_0x000e;
        L_0x0004:
            r1 = "rqdp{  no destUrl!}";
            r2 = new java.lang.Object[r2];
            com.tencent.feedback.common.e.d(r1, r2);
            r1 = r3;
        L_0x000d:
            return r1;
        L_0x000e:
            if (r10 == 0) goto L_0x0026;
        L_0x0010:
            r1 = new org.apache.http.entity.ByteArrayEntity;	 Catch:{ Throwable -> 0x0033, all -> 0x00e9 }
            r1.<init>(r10);	 Catch:{ Throwable -> 0x0033, all -> 0x00e9 }
        L_0x0015:
            r5 = r8.a(r11);	 Catch:{ Throwable -> 0x0033, all -> 0x00e9 }
            if (r5 != 0) goto L_0x0055;
        L_0x001b:
            r1 = "rqdp{  no httpClient!}";
            r2 = 0;
            r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0033, all -> 0x00e9 }
            com.tencent.feedback.common.e.d(r1, r2);	 Catch:{ Throwable -> 0x0033, all -> 0x00e9 }
            r1 = r3;
            goto L_0x000d;
        L_0x0026:
            r1 = new org.apache.http.entity.ByteArrayEntity;	 Catch:{ Throwable -> 0x0033, all -> 0x00e9 }
            r2 = "";
            r2 = r2.getBytes();	 Catch:{ Throwable -> 0x0033, all -> 0x00e9 }
            r1.<init>(r2);	 Catch:{ Throwable -> 0x0033, all -> 0x00e9 }
            goto L_0x0015;
        L_0x0033:
            r1 = move-exception;
            r2 = r1;
            r1 = r3;
        L_0x0036:
            r4 = com.tencent.feedback.common.e.a(r2);	 Catch:{ all -> 0x00f3 }
            if (r4 != 0) goto L_0x003f;
        L_0x003c:
            r2.printStackTrace();	 Catch:{ all -> 0x00f3 }
        L_0x003f:
            r4 = "rqdp{  execute error }%s";
            r5 = 1;
            r5 = new java.lang.Object[r5];	 Catch:{ all -> 0x00f3 }
            r6 = 0;
            r2 = r2.toString();	 Catch:{ all -> 0x00f3 }
            r5[r6] = r2;	 Catch:{ all -> 0x00f3 }
            com.tencent.feedback.common.e.d(r4, r5);	 Catch:{ all -> 0x00f3 }
            if (r3 == 0) goto L_0x000d;
        L_0x0051:
            r3.abort();
            goto L_0x000d;
        L_0x0055:
            r4 = new org.apache.http.client.methods.HttpPost;	 Catch:{ Throwable -> 0x0033, all -> 0x00e9 }
            r4.<init>(r9);	 Catch:{ Throwable -> 0x0033, all -> 0x00e9 }
            r2 = "wup_version";
            r6 = "3.0";
            r4.setHeader(r2, r6);	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r4.setEntity(r1);	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            if (r12 == 0) goto L_0x00bd;
        L_0x0068:
            r1 = r12.size();	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            if (r1 <= 0) goto L_0x00bd;
        L_0x006e:
            r1 = r12.entrySet();	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r6 = r1.iterator();	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
        L_0x0076:
            r1 = r6.hasNext();	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            if (r1 == 0) goto L_0x0099;
        L_0x007c:
            r1 = r6.next();	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r0 = r1;
            r0 = (java.util.Map.Entry) r0;	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r2 = r0;
            r1 = r2.getKey();	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r1 = (java.lang.String) r1;	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r2 = r2.getValue();	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r2 = (java.lang.String) r2;	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r4.setHeader(r1, r2);	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            goto L_0x0076;
        L_0x0094:
            r1 = move-exception;
            r2 = r1;
            r1 = r3;
            r3 = r4;
            goto L_0x0036;
        L_0x0099:
            r1 = "A37";
            r4.setHeader(r1, r11);	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r1 = "A38";
            r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r2.<init>();	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r6 = r8.a;	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            com.tencent.feedback.common.d.a(r6);	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r6 = r8.a;	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r6 = com.tencent.feedback.common.d.f(r6);	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r2 = r2.append(r6);	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r2 = r2.toString();	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r4.setHeader(r1, r2);	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
        L_0x00bd:
            r1 = new org.apache.http.protocol.BasicHttpContext;	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r1.<init>();	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r2 = r5.execute(r4, r1);	 Catch:{ Throwable -> 0x0094, all -> 0x00f1 }
            r3 = "http.request";
            r1 = r1.getAttribute(r3);	 Catch:{ Throwable -> 0x00f6, all -> 0x00f1 }
            r1 = (org.apache.http.HttpRequest) r1;	 Catch:{ Throwable -> 0x00f6, all -> 0x00f1 }
            r3 = "rqdp{  execute request:\n} %s";
            r5 = 1;
            r5 = new java.lang.Object[r5];	 Catch:{ Throwable -> 0x00f6, all -> 0x00f1 }
            r6 = 0;
            r1 = r1.getRequestLine();	 Catch:{ Throwable -> 0x00f6, all -> 0x00f1 }
            r1 = r1.toString();	 Catch:{ Throwable -> 0x00f6, all -> 0x00f1 }
            r5[r6] = r1;	 Catch:{ Throwable -> 0x00f6, all -> 0x00f1 }
            com.tencent.feedback.common.e.b(r3, r5);	 Catch:{ Throwable -> 0x00f6, all -> 0x00f1 }
            r4.abort();
            r1 = r2;
            goto L_0x000d;
        L_0x00e9:
            r1 = move-exception;
            r4 = r3;
        L_0x00eb:
            if (r4 == 0) goto L_0x00f0;
        L_0x00ed:
            r4.abort();
        L_0x00f0:
            throw r1;
        L_0x00f1:
            r1 = move-exception;
            goto L_0x00eb;
        L_0x00f3:
            r1 = move-exception;
            r4 = r3;
            goto L_0x00eb;
        L_0x00f6:
            r1 = move-exception;
            r3 = r4;
            r7 = r2;
            r2 = r1;
            r1 = r7;
            goto L_0x0036;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.feedback.upload.c.a.a(java.lang.String, byte[], java.lang.String, java.util.Map):org.apache.http.HttpResponse");
        }

        private static byte[] a(HttpResponse httpResponse) {
            Throwable th;
            Throwable th2;
            byte[] bArr = null;
            if (httpResponse != null) {
                if (httpResponse.getStatusLine().getStatusCode() != 200) {
                    StatusLine statusLine = httpResponse.getStatusLine();
                    e.c("rqdp{  request failure code:}%d rqdp{  , line:}%s ", Integer.valueOf(r1), statusLine);
                } else {
                    HttpEntity entity = httpResponse.getEntity();
                    if (entity == null) {
                        e.d("rqdp{  no response datas!}", new Object[0]);
                    } else {
                        BufferedInputStream bufferedInputStream;
                        try {
                            bufferedInputStream = new BufferedInputStream(new DataInputStream(entity.getContent()));
                            try {
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                while (true) {
                                    int read = bufferedInputStream.read();
                                    if (read == -1) {
                                        break;
                                    }
                                    byteArrayOutputStream.write(read);
                                }
                                byteArrayOutputStream.flush();
                                bArr = byteArrayOutputStream.toByteArray();
                                try {
                                    bufferedInputStream.close();
                                } catch (Throwable th3) {
                                    if (!e.a(th3)) {
                                        th3.printStackTrace();
                                    }
                                }
                            } catch (Throwable th4) {
                                th3 = th4;
                                try {
                                    if (!e.a(th3)) {
                                        th3.printStackTrace();
                                    }
                                    e.d("rqdp{  read error} %s", th3.toString());
                                    if (bufferedInputStream != null) {
                                        try {
                                            bufferedInputStream.close();
                                        } catch (Throwable th32) {
                                            if (!e.a(th32)) {
                                                th32.printStackTrace();
                                            }
                                        }
                                    }
                                    return bArr;
                                } catch (Throwable th5) {
                                    th2 = th5;
                                    if (bufferedInputStream != null) {
                                        try {
                                            bufferedInputStream.close();
                                        } catch (Throwable th322) {
                                            if (!e.a(th322)) {
                                                th322.printStackTrace();
                                            }
                                        }
                                    }
                                    throw th2;
                                }
                            }
                        } catch (Throwable th3222) {
                            bufferedInputStream = bArr;
                            th2 = th3222;
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            throw th2;
                        }
                    }
                }
            }
            return bArr;
        }

        private HttpClient a(String str) {
            try {
                HttpParams basicHttpParams = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(basicHttpParams, n.f);
                HttpConnectionParams.setSoTimeout(basicHttpParams, Constants.ERRORCODE_UNKNOWN);
                HttpConnectionParams.setSocketBufferSize(basicHttpParams, APPluginErrorCode.ERROR_APP_SYSTEM);
                basicHttpParams.setBooleanParameter("http.protocol.handle-redirects", false);
                HttpClient defaultHttpClient = new DefaultHttpClient(basicHttpParams);
                defaultHttpClient.setHttpRequestRetryHandler(new HttpRequestRetryHandler(this) {
                    public final boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
                        return false;
                    }
                });
                if (str == null || !str.toLowerCase(Locale.US).contains("wap")) {
                    defaultHttpClient.getParams().removeParameter("http.route.default-proxy");
                    return defaultHttpClient;
                }
                e.a("rqdp{  use proxy} %s", str);
                defaultHttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(Proxy.getDefaultHost(), Proxy.getDefaultPort()));
                return defaultHttpClient;
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                }
                e.d("rqdp{  httpclient error!}", new Object[0]);
                return null;
            }
        }
    }

    public abstract byte[] a(String str, byte[] bArr, b bVar, Map<String, String> map);

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                if (context != null) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                }
                a = new a(context);
            }
            cVar = a;
        }
        return cVar;
    }
}
