package com.tencent.beacon.upload;

import android.content.Context;
import android.net.Proxy;
import com.pay.http.APPluginErrorCode;
import com.tencent.beacon.b.b.e;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Locale;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import qalsdk.n;

/* compiled from: ProGuard */
public abstract class f {
    private static f a = null;

    /* compiled from: ProGuard */
    public static class a extends f {
        private Context a;

        public a(Context context) {
            this.a = context;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final byte[] a(java.lang.String r23, byte[] r24, com.tencent.beacon.upload.e r25, com.tencent.beacon.upload.a r26) throws java.lang.Exception {
            /*
            r22 = this;
            if (r23 != 0) goto L_0x000d;
        L_0x0002:
            r2 = "no destUrl!";
            r3 = 0;
            r3 = new java.lang.Object[r3];
            com.tencent.beacon.e.a.d(r2, r3);
            r2 = 0;
        L_0x000c:
            return r2;
        L_0x000d:
            r10 = 0;
            r8 = 0;
            r7 = 0;
            if (r24 != 0) goto L_0x004b;
        L_0x0012:
            r4 = 0;
        L_0x0014:
            r2 = "start req: %s sz:%d";
            r3 = 2;
            r3 = new java.lang.Object[r3];
            r6 = 0;
            r3[r6] = r23;
            r6 = 1;
            r9 = java.lang.Long.valueOf(r4);
            r3[r6] = r9;
            com.tencent.beacon.e.a.h(r2, r3);
            r3 = 0;
            r6 = "";
            r2 = "";
            r9 = r6;
            r12 = r3;
            r13 = r7;
            r3 = r23;
            r21 = r8;
            r8 = r2;
            r2 = r21;
        L_0x0038:
            r7 = r2 + 1;
            r6 = 3;
            if (r2 >= r6) goto L_0x0222;
        L_0x003d:
            r2 = 2;
            if (r13 >= r2) goto L_0x0222;
        L_0x0040:
            if (r12 == 0) goto L_0x0050;
        L_0x0042:
            r2 = "net redirect";
            r3 = new java.lang.Exception;
            r3.<init>(r2);
            throw r3;
        L_0x004b:
            r0 = r24;
            r2 = r0.length;
            r4 = (long) r2;
            goto L_0x0014;
        L_0x0050:
            r2 = 1;
            if (r7 <= r2) goto L_0x007e;
        L_0x0053:
            r2 = new java.lang.StringBuilder;
            r6 = "try time:";
            r2.<init>(r6);
            r2 = r2.append(r7);
            r2 = r2.toString();
            r6 = 0;
            r6 = new java.lang.Object[r6];
            com.tencent.beacon.e.a.h(r2, r6);
            r2 = 2;
            if (r7 != r2) goto L_0x0079;
        L_0x006c:
            r2 = r26.c();
            r6 = 2;
            if (r2 != r6) goto L_0x0079;
        L_0x0073:
            r2 = 0;
            r0 = r26;
            r0.b(r2);
        L_0x0079:
            r14 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
            java.lang.Thread.sleep(r14);	 Catch:{ InterruptedException -> 0x011e }
        L_0x007e:
            r0 = r22;
            r2 = r0.a;
            r6 = com.tencent.beacon.b.f.p(r2);
            if (r25 == 0) goto L_0x008d;
        L_0x0088:
            r2 = r25;
            r2.a(r3, r4, r6, r7);
        L_0x008d:
            r2 = new java.util.Date;
            r2.<init>();
            r14 = r2.getTime();
            r0 = r24;
            r2 = a(r3, r0, r6, r7);	 Catch:{ Exception -> 0x0124 }
            r6 = r9;
            r21 = r8;
            r8 = r2;
            r2 = r21;
        L_0x00a2:
            r9 = new java.util.Date;
            r9.<init>();
            r10 = r9.getTime();
            r14 = r10 - r14;
            if (r8 == 0) goto L_0x0213;
        L_0x00af:
            r0 = r8.a;
            r16 = r0;
            r9 = r16.getEntity();
            r10 = 0;
            if (r9 == 0) goto L_0x00c7;
        L_0x00bb:
            r10 = r9.getContentLength();
            r18 = 0;
            r9 = (r10 > r18 ? 1 : (r10 == r18 ? 0 : -1));
            if (r9 >= 0) goto L_0x00c7;
        L_0x00c5:
            r10 = 0;
        L_0x00c7:
            r9 = r16.getStatusLine();
            r17 = r9.getStatusCode();
            r9 = "response code:%d ";
            r18 = 1;
            r0 = r18;
            r0 = new java.lang.Object[r0];
            r18 = r0;
            r19 = 0;
            r20 = java.lang.Integer.valueOf(r17);
            r18[r19] = r20;
            r0 = r18;
            com.tencent.beacon.e.a.h(r9, r0);
            r9 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
            r0 = r17;
            if (r0 != r9) goto L_0x0189;
        L_0x00ed:
            r2 = "encrypt-status";
            r0 = r16;
            r2 = r0.containsHeader(r2);
            if (r2 == 0) goto L_0x016e;
        L_0x00f8:
            r2 = new java.lang.StringBuilder;
            r3 = "svr encry failed: ";
            r2.<init>(r3);
            r3 = "encrypt-status";
            r0 = r16;
            r3 = r0.getFirstHeader(r3);
            r3 = r3.getValue();
            r2 = r2.append(r3);
            r2 = r2.toString();
            r3 = 0;
            r3 = new java.lang.Object[r3];
            com.tencent.beacon.e.a.d(r2, r3);
            r2 = 0;
            goto L_0x000c;
        L_0x011e:
            r2 = move-exception;
            com.tencent.beacon.e.a.a(r2);
            goto L_0x007e;
        L_0x0124:
            r2 = move-exception;
            r2 = r2.toString();
            r6 = r8.equals(r2);
            if (r6 == 0) goto L_0x014a;
        L_0x012f:
            r6 = new java.lang.StringBuilder;
            r6.<init>();
            r6 = r6.append(r9);
            r6 = r6.append(r7);
            r8 = ":same ";
            r6 = r6.append(r8);
            r6 = r6.toString();
        L_0x0147:
            r8 = r10;
            goto L_0x00a2;
        L_0x014a:
            r6 = new java.lang.StringBuilder;
            r6.<init>();
            r6 = r6.append(r9);
            r6 = r6.append(r7);
            r8 = ":";
            r6 = r6.append(r8);
            r6 = r6.append(r2);
            r8 = " ";
            r6 = r6.append(r8);
            r6 = r6.toString();
            goto L_0x0147;
        L_0x016e:
            r2 = r8.b;
            r0 = r22;
            r1 = r16;
            r4 = r0.a(r1, r2);
            if (r25 == 0) goto L_0x0183;
        L_0x017a:
            if (r4 != 0) goto L_0x0186;
        L_0x017c:
            r2 = 0;
        L_0x017e:
            r0 = r25;
            r0.a(r2, r14);
        L_0x0183:
            r2 = r4;
            goto L_0x000c;
        L_0x0186:
            r2 = r4.length;
            r2 = (long) r2;
            goto L_0x017e;
        L_0x0189:
            r9 = 301; // 0x12d float:4.22E-43 double:1.487E-321;
            r0 = r17;
            if (r0 == r9) goto L_0x01a1;
        L_0x018f:
            r9 = 302; // 0x12e float:4.23E-43 double:1.49E-321;
            r0 = r17;
            if (r0 == r9) goto L_0x01a1;
        L_0x0195:
            r9 = 303; // 0x12f float:4.25E-43 double:1.497E-321;
            r0 = r17;
            if (r0 == r9) goto L_0x01a1;
        L_0x019b:
            r9 = 307; // 0x133 float:4.3E-43 double:1.517E-321;
            r0 = r17;
            if (r0 != r9) goto L_0x01db;
        L_0x01a1:
            r9 = 1;
        L_0x01a2:
            if (r9 == 0) goto L_0x022c;
        L_0x01a4:
            r3 = 1;
            r7 = "Location";
            r0 = r16;
            r12 = r0.getFirstHeader(r7);
            if (r12 != 0) goto L_0x01dd;
        L_0x01b0:
            r2 = new java.lang.StringBuilder;
            r3 = "redirect code:";
            r2.<init>(r3);
            r0 = r17;
            r2 = r2.append(r0);
            r3 = " Location is null! return";
            r2 = r2.append(r3);
            r2 = r2.toString();
            r3 = 0;
            r3 = new java.lang.Object[r3];
            com.tencent.beacon.e.a.d(r2, r3);
            r2 = r8.b;	 Catch:{ Exception -> 0x022a }
            if (r2 == 0) goto L_0x01d8;
        L_0x01d3:
            r2 = r8.b;	 Catch:{ Exception -> 0x022a }
            r2.abort();	 Catch:{ Exception -> 0x022a }
        L_0x01d8:
            r2 = 0;
            goto L_0x000c;
        L_0x01db:
            r9 = 0;
            goto L_0x01a2;
        L_0x01dd:
            r7 = r13 + 1;
            r9 = 0;
            r23 = r12.getValue();
            r12 = "redirect code:%d , to:%s";
            r13 = 2;
            r13 = new java.lang.Object[r13];
            r16 = 0;
            r17 = java.lang.Integer.valueOf(r17);
            r13[r16] = r17;
            r16 = 1;
            r13[r16] = r23;
            com.tencent.beacon.e.a.h(r12, r13);
            r12 = r8.b;	 Catch:{ Exception -> 0x0228 }
            if (r12 == 0) goto L_0x0202;
        L_0x01fd:
            r12 = r8.b;	 Catch:{ Exception -> 0x0228 }
            r12.abort();	 Catch:{ Exception -> 0x0228 }
        L_0x0202:
            if (r25 == 0) goto L_0x0209;
        L_0x0204:
            r0 = r25;
            r0.a(r10, r14);
        L_0x0209:
            r12 = r3;
            r13 = r7;
            r10 = r8;
            r8 = r2;
            r3 = r23;
            r2 = r9;
            r9 = r6;
            goto L_0x0038;
        L_0x0213:
            if (r25 == 0) goto L_0x021c;
        L_0x0215:
            r10 = 0;
            r0 = r25;
            r0.a(r10, r14);
        L_0x021c:
            r9 = r6;
            r10 = r8;
            r8 = r2;
            r2 = r7;
            goto L_0x0038;
        L_0x0222:
            r2 = new java.lang.Exception;
            r2.<init>(r9);
            throw r2;
        L_0x0228:
            r12 = move-exception;
            goto L_0x0202;
        L_0x022a:
            r2 = move-exception;
            goto L_0x01d8;
        L_0x022c:
            r9 = r7;
            r23 = r3;
            r3 = r12;
            r7 = r13;
            goto L_0x0202;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.beacon.upload.f.a.a(java.lang.String, byte[], com.tencent.beacon.upload.e, com.tencent.beacon.upload.a):byte[]");
        }

        private static b a(String str, byte[] bArr, String str2, int i) throws Exception {
            Throwable th;
            b bVar = null;
            if (str == null) {
                com.tencent.beacon.e.a.d("no destUrl!", new Object[0]);
            } else {
                HttpEntity byteArrayEntity;
                HttpPost httpPost;
                if (bArr != null) {
                    try {
                        byteArrayEntity = new ByteArrayEntity(bArr);
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        httpPost = null;
                        th = th3;
                        com.tencent.beacon.e.a.a(th);
                        com.tencent.beacon.e.a.d("execute error:%s", th.toString());
                        if (httpPost != null) {
                            httpPost.abort();
                        }
                        throw new Exception(th.toString());
                    }
                }
                Object byteArrayEntity2 = new ByteArrayEntity("".getBytes());
                HttpClient a = a(str2, i);
                if (a == null) {
                    com.tencent.beacon.e.a.d("no httpClient!", new Object[0]);
                } else {
                    httpPost = new HttpPost(str);
                    try {
                        httpPost.setHeader("wup_version", "3.0");
                        httpPost.setHeader("TYPE_COMPRESS", String.valueOf(2));
                        httpPost.setHeader("encr_type", "rsapost");
                        e a2 = e.a();
                        if (a2 != null) {
                            httpPost.setHeader("bea_key", a2.o());
                        }
                        httpPost.setEntity(byteArrayEntity);
                        HttpResponse execute = a.execute(httpPost, new BasicHttpContext());
                        com.tencent.beacon.e.a.h("execute request:\n %s", ((HttpRequest) r0.getAttribute("http.request")).getRequestLine().toString());
                        bVar = new b(execute, httpPost);
                    } catch (Throwable th4) {
                        th = th4;
                        com.tencent.beacon.e.a.a(th);
                        com.tencent.beacon.e.a.d("execute error:%s", th.toString());
                        if (httpPost != null) {
                            httpPost.abort();
                        }
                        throw new Exception(th.toString());
                    }
                }
            }
            return bVar;
        }

        private byte[] a(HttpResponse httpResponse, HttpPost httpPost) {
            BufferedInputStream bufferedInputStream;
            Throwable th;
            Throwable th2;
            byte[] bArr = null;
            if (httpResponse != null) {
                if (httpResponse.getStatusLine().getStatusCode() != 200) {
                    StatusLine statusLine = httpResponse.getStatusLine();
                    com.tencent.beacon.e.a.c("request failure code:%d , line:%s ", Integer.valueOf(r1), statusLine);
                } else {
                    if (httpResponse.containsHeader("session_id") && httpResponse.containsHeader("max_time")) {
                        e.a().a(this.a, httpResponse.getFirstHeader("session_id").getValue(), httpResponse.getFirstHeader("max_time").getValue());
                    }
                    HttpEntity entity = httpResponse.getEntity();
                    if (entity == null) {
                        com.tencent.beacon.e.a.d("no response datas!", new Object[0]);
                    } else {
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
                                    com.tencent.beacon.e.a.a(th3);
                                }
                                if (httpPost != null) {
                                    httpPost.abort();
                                }
                            } catch (Throwable th4) {
                                th3 = th4;
                                try {
                                    com.tencent.beacon.e.a.a(th3);
                                    com.tencent.beacon.e.a.d("read error %s", th3.toString());
                                    if (bufferedInputStream != null) {
                                        try {
                                            bufferedInputStream.close();
                                        } catch (Throwable th32) {
                                            com.tencent.beacon.e.a.a(th32);
                                        }
                                    }
                                    if (httpPost != null) {
                                        httpPost.abort();
                                    }
                                    return bArr;
                                } catch (Throwable th5) {
                                    th2 = th5;
                                    if (bufferedInputStream != null) {
                                        try {
                                            bufferedInputStream.close();
                                        } catch (Throwable th322) {
                                            com.tencent.beacon.e.a.a(th322);
                                        }
                                    }
                                    if (httpPost != null) {
                                        httpPost.abort();
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
                            if (httpPost != null) {
                                httpPost.abort();
                            }
                            throw th2;
                        }
                    }
                }
            }
            return bArr;
        }

        private static HttpClient a(String str, int i) {
            try {
                HttpParams basicHttpParams = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(basicHttpParams, n.f);
                HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
                HttpConnectionParams.setSocketBufferSize(basicHttpParams, APPluginErrorCode.ERROR_APP_SYSTEM);
                basicHttpParams.setBooleanParameter("http.protocol.handle-redirects", false);
                HttpClient defaultHttpClient = new DefaultHttpClient(basicHttpParams);
                HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
                defaultHttpClient.setHttpRequestRetryHandler(new HttpRequestRetryHandler() {
                    public final boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
                        if (i >= 3) {
                            return false;
                        }
                        if (iOException instanceof NoHttpResponseException) {
                            return true;
                        }
                        if (iOException instanceof ClientProtocolException) {
                            return true;
                        }
                        return false;
                    }
                });
                if (str == null || !str.toLowerCase(Locale.US).contains("wap") || i == 2) {
                    if (str != null) {
                        com.tencent.beacon.e.a.a("Don't use proxy: %s, try time: %s", str, Integer.valueOf(i));
                    }
                    defaultHttpClient.getParams().removeParameter("http.route.default-proxy");
                    return defaultHttpClient;
                }
                com.tencent.beacon.e.a.a("use proxy: %s, try time: %s", str, Integer.valueOf(i));
                defaultHttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(Proxy.getDefaultHost(), Proxy.getDefaultPort()));
                return defaultHttpClient;
            } catch (Throwable th) {
                com.tencent.beacon.e.a.a(th);
                com.tencent.beacon.e.a.d("httpclient error!", new Object[0]);
                return null;
            }
        }
    }

    public abstract byte[] a(String str, byte[] bArr, e eVar, a aVar) throws Exception;

    public static synchronized f a(Context context) {
        f fVar;
        synchronized (f.class) {
            if (a == null && context != null) {
                a = new a(context);
            }
            fVar = a;
        }
        return fVar;
    }
}
