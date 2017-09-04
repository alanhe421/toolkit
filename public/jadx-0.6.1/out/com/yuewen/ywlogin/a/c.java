package com.yuewen.ywlogin.a;

import java.io.IOException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;

public class c {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.yuewen.ywlogin.a.j a(okhttp3.y r8) {
        /*
        r0 = android.os.Looper.myLooper();
        r1 = android.os.Looper.getMainLooper();
        if (r0 != r1) goto L_0x0013;
    L_0x000a:
        r0 = new java.lang.RuntimeException;
        r1 = "can not get http body in main thread";
        r0.<init>(r1);
        throw r0;
    L_0x0013:
        r5 = 0;
        r6 = 0;
        r0 = r8.c();	 Catch:{ IOException -> 0x0053 }
        if (r0 == 0) goto L_0x0024;
    L_0x001c:
        r0 = r8.g();	 Catch:{ IOException -> 0x0053 }
        r5 = r0.e();	 Catch:{ IOException -> 0x0053 }
    L_0x0024:
        r0 = r8.g();	 Catch:{ Exception -> 0x004e }
        r0.close();	 Catch:{ Exception -> 0x004e }
    L_0x002b:
        r0 = 0;
        r1 = r8.j();
        if (r1 == 0) goto L_0x0039;
    L_0x0032:
        r1 = r8.i();
        if (r1 != 0) goto L_0x0039;
    L_0x0038:
        r0 = 1;
    L_0x0039:
        r1 = r8.i();
        if (r1 == 0) goto L_0x0072;
    L_0x003f:
        r4 = 2;
    L_0x0040:
        r1 = new com.yuewen.ywlogin.a.j;
        r2 = r8.c();
        r3 = r8.b();
        r1.<init>(r2, r3, r4, r5, r6);
        return r1;
    L_0x004e:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x002b;
    L_0x0053:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0064 }
        r0 = r8.g();	 Catch:{ Exception -> 0x005f }
        r0.close();	 Catch:{ Exception -> 0x005f }
        goto L_0x002b;
    L_0x005f:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x002b;
    L_0x0064:
        r0 = move-exception;
        r1 = r8.g();	 Catch:{ Exception -> 0x006d }
        r1.close();	 Catch:{ Exception -> 0x006d }
    L_0x006c:
        throw r0;
    L_0x006d:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x006c;
    L_0x0072:
        r4 = r0;
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yuewen.ywlogin.a.c.a(okhttp3.y):com.yuewen.ywlogin.a.j");
    }

    public static j a(IOException iOException) {
        if (iOException instanceof SSLHandshakeException) {
            return new j(false, -20052);
        }
        if (iOException instanceof SSLKeyException) {
            return new j(false, -20053);
        }
        if (iOException instanceof SSLPeerUnverifiedException) {
            return new j(false, -20054);
        }
        if (iOException instanceof SSLProtocolException) {
            return new j(false, -20055);
        }
        if (iOException instanceof SocketTimeoutException) {
            return new j(false, -20000);
        }
        if (iOException instanceof UnknownHostException) {
            return new j(false, -20008);
        }
        if (iOException instanceof ConnectException) {
            return new j(false, -20056);
        }
        if (iOException instanceof BindException) {
            return new j(false, -20057);
        }
        if (iOException instanceof NoRouteToHostException) {
            return new j(false, -20058);
        }
        if (iOException instanceof PortUnreachableException) {
            return new j(false, -20059);
        }
        if (iOException instanceof SocketException) {
            return new j(false, -20060);
        }
        if (iOException instanceof HttpRetryException) {
            return new j(false, -20061);
        }
        if (iOException instanceof ProtocolException) {
            return new j(false, -20062);
        }
        return new j(false, -20051);
    }
}
