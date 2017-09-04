package com.tencent;

import com.tencent.imcore.EnvRequestClosure;
import com.tencent.imcore.HttpMethod;
import java.net.HttpURLConnection;

final class ab implements Runnable {
    private /* synthetic */ HttpMethod a;
    private /* synthetic */ HttpURLConnection b;
    private /* synthetic */ byte[] c;
    private /* synthetic */ EnvRequestClosure d;

    ab(aa aaVar, HttpMethod httpMethod, HttpURLConnection httpURLConnection, byte[] bArr, EnvRequestClosure envRequestClosure) {
        this.a = httpMethod;
        this.b = httpURLConnection;
        this.c = bArr;
        this.d = envRequestClosure;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r5 = this;
        r0 = r5.a;	 Catch:{ Throwable -> 0x004f }
        r1 = com.tencent.imcore.HttpMethod.kPost;	 Catch:{ Throwable -> 0x004f }
        if (r0 != r1) goto L_0x002e;
    L_0x0006:
        r0 = r5.b;	 Catch:{ Throwable -> 0x004f }
        r1 = "POST";
        r0.setRequestMethod(r1);	 Catch:{ Throwable -> 0x004f }
        r0 = r5.b;	 Catch:{ Throwable -> 0x004f }
        r1 = 1;
        r0.setDoOutput(r1);	 Catch:{ Throwable -> 0x004f }
        r0 = r5.b;	 Catch:{ Throwable -> 0x004f }
        r1 = "Content-Length";
        r2 = r5.c;	 Catch:{ Throwable -> 0x004f }
        r2 = r2.length;	 Catch:{ Throwable -> 0x004f }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ Throwable -> 0x004f }
        r0.setRequestProperty(r1, r2);	 Catch:{ Throwable -> 0x004f }
        r0 = r5.b;	 Catch:{ Throwable -> 0x004f }
        r0 = r0.getOutputStream();	 Catch:{ Throwable -> 0x004f }
        r1 = r5.c;	 Catch:{ Throwable -> 0x004f }
        r0.write(r1);	 Catch:{ Throwable -> 0x004f }
    L_0x002e:
        r0 = new java.io.BufferedInputStream;	 Catch:{ Throwable -> 0x004f }
        r1 = r5.b;	 Catch:{ Throwable -> 0x004f }
        r1 = r1.getInputStream();	 Catch:{ Throwable -> 0x004f }
        r0.<init>(r1);	 Catch:{ Throwable -> 0x004f }
        r1 = new java.io.ByteArrayOutputStream;	 Catch:{ Throwable -> 0x004f }
        r2 = 10240; // 0x2800 float:1.4349E-41 double:5.059E-320;
        r1.<init>(r2);	 Catch:{ Throwable -> 0x004f }
        r2 = 10240; // 0x2800 float:1.4349E-41 double:5.059E-320;
        r2 = new byte[r2];	 Catch:{ Throwable -> 0x004f }
    L_0x0044:
        r3 = r0.read(r2);	 Catch:{ Throwable -> 0x004f }
        if (r3 < 0) goto L_0x0084;
    L_0x004a:
        r4 = 0;
        r1.write(r2, r4, r3);	 Catch:{ Throwable -> 0x004f }
        goto L_0x0044;
    L_0x004f:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00cf }
        r1 = com.tencent.aa.a;	 Catch:{ all -> 0x00cf }
        r2 = 1;
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cf }
        r4 = "httpRequest->error: ";
        r3.<init>(r4);	 Catch:{ all -> 0x00cf }
        r4 = com.tencent.IMFunc.getExceptionInfo(r0);	 Catch:{ all -> 0x00cf }
        r3 = r3.append(r4);	 Catch:{ all -> 0x00cf }
        r3 = r3.toString();	 Catch:{ all -> 0x00cf }
        com.tencent.imsdk.QLog.e(r1, r2, r3);	 Catch:{ all -> 0x00cf }
        r0 = r0.toString();	 Catch:{ all -> 0x00cf }
        r1 = r5.d;	 Catch:{ all -> 0x00cf }
        if (r1 == 0) goto L_0x007e;
    L_0x0077:
        r1 = r5.d;	 Catch:{ Throwable -> 0x00b4 }
        r2 = 6010; // 0x177a float:8.422E-42 double:2.9693E-320;
        r1.fail(r2, r0);	 Catch:{ Throwable -> 0x00b4 }
    L_0x007e:
        r0 = r5.b;
        r0.disconnect();
    L_0x0083:
        return;
    L_0x0084:
        r0 = r1.toByteArray();	 Catch:{ Throwable -> 0x004f }
        r1 = com.tencent.aa.a;	 Catch:{ Throwable -> 0x004f }
        r2 = 1;
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x004f }
        r4 = "httpRequest->success: ";
        r3.<init>(r4);	 Catch:{ Throwable -> 0x004f }
        r4 = new java.lang.String;	 Catch:{ Throwable -> 0x004f }
        r4.<init>(r0);	 Catch:{ Throwable -> 0x004f }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x004f }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x004f }
        com.tencent.imsdk.QLog.d(r1, r2, r3);	 Catch:{ Throwable -> 0x004f }
        r1 = r5.d;	 Catch:{ Throwable -> 0x004f }
        if (r1 == 0) goto L_0x00ae;
    L_0x00a9:
        r1 = r5.d;	 Catch:{ Throwable -> 0x004f }
        r1.done(r0);	 Catch:{ Throwable -> 0x004f }
    L_0x00ae:
        r0 = r5.b;
        r0.disconnect();
        goto L_0x0083;
    L_0x00b4:
        r0 = move-exception;
        r0 = com.tencent.IMFunc.getExceptionInfo(r0);	 Catch:{ all -> 0x00cf }
        r1 = com.tencent.aa.a;	 Catch:{ all -> 0x00cf }
        r2 = 1;
        com.tencent.imsdk.QLog.e(r1, r2, r0);	 Catch:{ all -> 0x00cf }
        r1 = com.tencent.TIMManager.getInstance();	 Catch:{ all -> 0x00cf }
        r1 = r1.getExceptionListener();	 Catch:{ all -> 0x00cf }
        if (r1 == 0) goto L_0x007e;
    L_0x00cb:
        r1.onException(r0);	 Catch:{ all -> 0x00cf }
        goto L_0x007e;
    L_0x00cf:
        r0 = move-exception;
        r1 = r5.b;
        r1.disconnect();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.ab.run():void");
    }
}
