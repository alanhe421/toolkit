package com.qq.reader.common.download.task;

import android.content.Context;
import com.qq.reader.common.conn.http.b;
import com.qq.reader.common.download.task.state.TaskActionEnum;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ab;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.r;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import qalsdk.n;

/* compiled from: DownloadWorker */
public abstract class c extends o {
    private static volatile int g;
    protected f a;
    protected int b = 0;
    protected long c = 0;
    protected long d = 0;
    protected Context e;
    private final int h;
    private boolean i = false;
    private Thread j;
    private final Object k;
    private RandomAccessFile l;

    protected abstract void a();

    protected abstract void a(f fVar);

    protected abstract boolean b();

    public /* synthetic */ g e() {
        return d();
    }

    public c(k kVar, g gVar, Object obj, Context context) {
        super(kVar, gVar);
        this.a = (f) gVar;
        int i = g;
        g = i + 1;
        this.h = i;
        this.k = obj;
        this.e = context;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r12 = this;
        r1 = 0;
        r2 = 0;
        r11 = 0;
        r10 = 100;
        r0 = java.lang.Thread.currentThread();
        r12.j = r0;
        r0 = r12.f;
        r3 = r12.a;
        r4 = com.qq.reader.common.download.task.state.TaskActionEnum.Start;
        r0.a(r3, r4);
        r0 = 0;
        r12.a();	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r3 = r12.i();	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r12.l = r3;	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r3 = r12.a;	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r4 = r3.getSize();	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r6 = 0;
        r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r3 <= 0) goto L_0x0066;
    L_0x002a:
        r4 = r12.c;	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r3 = r12.a;	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r6 = r3.getSize();	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r3 < 0) goto L_0x0066;
    L_0x0036:
        r2 = r12.a;	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r3 = 100;
        r2.setProgress(r3);	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r12.a(r0);	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r0 = r12.a;	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r12.a(r0);	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r12.a(r1);
        r2 = r12.a;
        monitor-enter(r2);
        r0 = r12.a;	 Catch:{ all -> 0x0060 }
        r0.notifyAll();	 Catch:{ all -> 0x0060 }
        monitor-exit(r2);	 Catch:{ all -> 0x0060 }
        r2 = r12.k;
        monitor-enter(r2);
        r0 = r12.k;	 Catch:{ all -> 0x0063 }
        r0.notifyAll();	 Catch:{ all -> 0x0063 }
        monitor-exit(r2);	 Catch:{ all -> 0x0063 }
        if (r1 == 0) goto L_0x005f;
    L_0x005c:
        r1.disconnect();
    L_0x005f:
        return;
    L_0x0060:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0060 }
        throw r0;
    L_0x0063:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0063 }
        throw r0;
    L_0x0066:
        r0 = r12.c();	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        if (r0 == 0) goto L_0x0075;
    L_0x006c:
        r0 = r12.f;	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r3 = r12.a;	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r4 = com.qq.reader.common.download.task.state.TaskActionEnum.Deactivate;	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r0.a(r3, r4);	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
    L_0x0075:
        r0 = r12.b();	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        if (r0 != 0) goto L_0x009c;
    L_0x007b:
        r12.a(r1);
        r2 = r12.a;
        monitor-enter(r2);
        r0 = r12.a;	 Catch:{ all -> 0x0096 }
        r0.notifyAll();	 Catch:{ all -> 0x0096 }
        monitor-exit(r2);	 Catch:{ all -> 0x0096 }
        r2 = r12.k;
        monitor-enter(r2);
        r0 = r12.k;	 Catch:{ all -> 0x0099 }
        r0.notifyAll();	 Catch:{ all -> 0x0099 }
        monitor-exit(r2);	 Catch:{ all -> 0x0099 }
        if (r1 == 0) goto L_0x005f;
    L_0x0092:
        r1.disconnect();
        goto L_0x005f;
    L_0x0096:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0096 }
        throw r0;
    L_0x0099:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0099 }
        throw r0;
    L_0x009c:
        r0 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r3 = r12.a;	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r3 = r3.getObjectURI();	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r0.<init>(r3);	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r3 = r12.a(r0);	 Catch:{ MalformedURLException -> 0x02f7, IOException -> 0x01fe, Exception -> 0x0247, all -> 0x0298 }
        r1 = r3.getInputStream();	 Catch:{ MalformedURLException -> 0x02fb, IOException -> 0x02e3, Exception -> 0x02cf, all -> 0x02bc }
        if (r1 != 0) goto L_0x00d2;
    L_0x00b1:
        r12.a(r1);
        r1 = r12.a;
        monitor-enter(r1);
        r0 = r12.a;	 Catch:{ all -> 0x00cc }
        r0.notifyAll();	 Catch:{ all -> 0x00cc }
        monitor-exit(r1);	 Catch:{ all -> 0x00cc }
        r1 = r12.k;
        monitor-enter(r1);
        r0 = r12.k;	 Catch:{ all -> 0x00cf }
        r0.notifyAll();	 Catch:{ all -> 0x00cf }
        monitor-exit(r1);	 Catch:{ all -> 0x00cf }
        if (r3 == 0) goto L_0x005f;
    L_0x00c8:
        r3.disconnect();
        goto L_0x005f;
    L_0x00cc:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x00cc }
        throw r0;
    L_0x00cf:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x00cf }
        throw r0;
    L_0x00d2:
        r0 = 20480; // 0x5000 float:2.8699E-41 double:1.01185E-319;
        r5 = new byte[r0];	 Catch:{ MalformedURLException -> 0x0300, IOException -> 0x02e7, Exception -> 0x02d3, all -> 0x02bf }
        r4 = r1;
        r1 = r2;
    L_0x00d8:
        r0 = r12.c();	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        if (r0 != 0) goto L_0x00f9;
    L_0x00de:
        r0 = r12.g();	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        if (r0 != 0) goto L_0x00f9;
    L_0x00e4:
        r0 = r12.j;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r0 = r0.isInterrupted();	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        if (r0 != 0) goto L_0x00f9;
    L_0x00ec:
        if (r4 == 0) goto L_0x00f9;
    L_0x00ee:
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ IOException -> 0x030d, MalformedURLException -> 0x017d, Exception -> 0x02d7 }
        r0 = r4.read(r5);	 Catch:{ IOException -> 0x030d, MalformedURLException -> 0x017d, Exception -> 0x02d7 }
        r1 = -1;
        if (r0 != r1) goto L_0x012a;
    L_0x00f9:
        r0 = r12.f;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r0 = r0.k();	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        if (r0 == 0) goto L_0x010e;
    L_0x0101:
        r0 = r12.b;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        if (r0 >= r10) goto L_0x010e;
    L_0x0105:
        r0 = r12.f;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r1 = r12.a;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r2 = com.qq.reader.common.download.task.state.TaskActionEnum.Deactivate;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r0.a(r1, r2);	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
    L_0x010e:
        r12.a(r4);
        r1 = r12.a;
        monitor-enter(r1);
        r0 = r12.a;	 Catch:{ all -> 0x01f2 }
        r0.notifyAll();	 Catch:{ all -> 0x01f2 }
        monitor-exit(r1);	 Catch:{ all -> 0x01f2 }
        r1 = r12.k;
        monitor-enter(r1);
        r0 = r12.k;	 Catch:{ all -> 0x01f5 }
        r0.notifyAll();	 Catch:{ all -> 0x01f5 }
        monitor-exit(r1);	 Catch:{ all -> 0x01f5 }
        if (r3 == 0) goto L_0x005f;
    L_0x0125:
        r3.disconnect();
        goto L_0x005f;
    L_0x012a:
        r8 = java.lang.System.currentTimeMillis();	 Catch:{ IOException -> 0x0177, MalformedURLException -> 0x017d, Exception -> 0x02d7 }
        r6 = r8 - r6;
        r1 = (float) r6;	 Catch:{ IOException -> 0x0177, MalformedURLException -> 0x017d, Exception -> 0x02d7 }
        r6 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1));
        if (r6 <= 0) goto L_0x0170;
    L_0x0135:
        r6 = r12.a;	 Catch:{ IOException -> 0x0177, MalformedURLException -> 0x017d, Exception -> 0x02d7 }
        r7 = (float) r0;	 Catch:{ IOException -> 0x0177, MalformedURLException -> 0x017d, Exception -> 0x02d7 }
        r1 = r7 / r1;
        r6.setDownloadSpeed(r1);	 Catch:{ IOException -> 0x0177, MalformedURLException -> 0x017d, Exception -> 0x02d7 }
    L_0x013d:
        r1 = r12.l;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r6 = 0;
        r1.write(r5, r6, r0);	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r0 = r12.l;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r0 = r0.length();	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r12.c = r0;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r6 = 100;
        r6 = r6 * r0;
        r8 = r12.d;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r6 = r6 / r8;
        r6 = (int) r6;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r12.b = r6;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r6 = r12.a;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r6.setCurrentSize(r0);	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r0 = r12.a;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r1 = r12.b;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r0.setProgress(r1);	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r0 = r12.b;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        if (r0 >= r10) goto L_0x01e6;
    L_0x0164:
        r0 = r12.f;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r1 = r12.a;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r6 = com.qq.reader.common.download.task.state.TaskActionEnum.Receive;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r0.a(r1, r6);	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r1 = r2;
        goto L_0x00d8;
    L_0x0170:
        r1 = r12.a;	 Catch:{ IOException -> 0x0177, MalformedURLException -> 0x017d, Exception -> 0x02d7 }
        r6 = 0;
        r1.setDownloadSpeed(r6);	 Catch:{ IOException -> 0x0177, MalformedURLException -> 0x017d, Exception -> 0x02d7 }
        goto L_0x013d;
    L_0x0177:
        r0 = move-exception;
        r1 = r2;
    L_0x0179:
        r6 = 1;
        if (r1 < r6) goto L_0x01c0;
    L_0x017c:
        throw r0;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
    L_0x017d:
        r0 = move-exception;
        r1 = r3;
        r2 = r4;
    L_0x0180:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x02cb }
        r3.<init>();	 Catch:{ all -> 0x02cb }
        r4 = r12.h();	 Catch:{ all -> 0x02cb }
        r3 = r3.append(r4);	 Catch:{ all -> 0x02cb }
        r4 = "run";
        r3 = r3.append(r4);	 Catch:{ all -> 0x02cb }
        r3 = r3.toString();	 Catch:{ all -> 0x02cb }
        r4 = r12.a;	 Catch:{ all -> 0x02cb }
        r4 = r4.toString();	 Catch:{ all -> 0x02cb }
        com.qq.reader.common.monitor.f.a(r3, r4, r0);	 Catch:{ all -> 0x02cb }
        r12.a(r0);	 Catch:{ all -> 0x02cb }
        r12.a(r2);
        r2 = r12.a;
        monitor-enter(r2);
        r0 = r12.a;	 Catch:{ all -> 0x01f8 }
        r0.notifyAll();	 Catch:{ all -> 0x01f8 }
        monitor-exit(r2);	 Catch:{ all -> 0x01f8 }
        r2 = r12.k;
        monitor-enter(r2);
        r0 = r12.k;	 Catch:{ all -> 0x01fb }
        r0.notifyAll();	 Catch:{ all -> 0x01fb }
        monitor-exit(r2);	 Catch:{ all -> 0x01fb }
        if (r1 == 0) goto L_0x005f;
    L_0x01bb:
        r1.disconnect();
        goto L_0x005f;
    L_0x01c0:
        r0 = r1 + 1;
        r4.close();	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r3.disconnect();	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r1 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r6 = r12.a;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r6 = r6.getObjectURI();	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r1.<init>(r6);	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r1 = r12.a(r1);	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r3 = r1.getInputStream();	 Catch:{ MalformedURLException -> 0x0305, IOException -> 0x02ee, Exception -> 0x02da, all -> 0x02c4 }
        r4 = r12.a;	 Catch:{ MalformedURLException -> 0x0309, IOException -> 0x02f2, Exception -> 0x02de, all -> 0x02c7 }
        r6 = 0;
        r4.setDownloadSpeed(r6);	 Catch:{ MalformedURLException -> 0x0309, IOException -> 0x02f2, Exception -> 0x02de, all -> 0x02c7 }
        r4 = r3;
        r3 = r1;
        r1 = r0;
        goto L_0x00d8;
    L_0x01e6:
        r0 = r12.b;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        if (r0 != r10) goto L_0x0310;
    L_0x01ea:
        r0 = r12.a;	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r12.a(r0);	 Catch:{ MalformedURLException -> 0x017d, IOException -> 0x02eb, Exception -> 0x02d7 }
        r1 = r2;
        goto L_0x00d8;
    L_0x01f2:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x01f2 }
        throw r0;
    L_0x01f5:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x01f5 }
        throw r0;
    L_0x01f8:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x01f8 }
        throw r0;
    L_0x01fb:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x01fb }
        throw r0;
    L_0x01fe:
        r0 = move-exception;
        r3 = r1;
        r4 = r1;
    L_0x0201:
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x02c2 }
        r1.<init>();	 Catch:{ all -> 0x02c2 }
        r2 = r12.h();	 Catch:{ all -> 0x02c2 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x02c2 }
        r2 = "run";
        r1 = r1.append(r2);	 Catch:{ all -> 0x02c2 }
        r1 = r1.toString();	 Catch:{ all -> 0x02c2 }
        r2 = r12.a;	 Catch:{ all -> 0x02c2 }
        r2 = r2.toString();	 Catch:{ all -> 0x02c2 }
        com.qq.reader.common.monitor.f.a(r1, r2, r0);	 Catch:{ all -> 0x02c2 }
        r12.a(r0);	 Catch:{ all -> 0x02c2 }
        r12.a(r4);
        r1 = r12.a;
        monitor-enter(r1);
        r0 = r12.a;	 Catch:{ all -> 0x0241 }
        r0.notifyAll();	 Catch:{ all -> 0x0241 }
        monitor-exit(r1);	 Catch:{ all -> 0x0241 }
        r1 = r12.k;
        monitor-enter(r1);
        r0 = r12.k;	 Catch:{ all -> 0x0244 }
        r0.notifyAll();	 Catch:{ all -> 0x0244 }
        monitor-exit(r1);	 Catch:{ all -> 0x0244 }
        if (r3 == 0) goto L_0x005f;
    L_0x023c:
        r3.disconnect();
        goto L_0x005f;
    L_0x0241:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0241 }
        throw r0;
    L_0x0244:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0244 }
        throw r0;
    L_0x0247:
        r0 = move-exception;
        r3 = r1;
        r4 = r1;
    L_0x024a:
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x02c2 }
        r1.<init>();	 Catch:{ all -> 0x02c2 }
        r2 = r12.h();	 Catch:{ all -> 0x02c2 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x02c2 }
        r2 = "run";
        r1 = r1.append(r2);	 Catch:{ all -> 0x02c2 }
        r1 = r1.toString();	 Catch:{ all -> 0x02c2 }
        r2 = "";
        com.qq.reader.common.monitor.f.a(r1, r2, r0);	 Catch:{ all -> 0x02c2 }
        r0 = r0 instanceof java.lang.InterruptedException;	 Catch:{ all -> 0x02c2 }
        if (r0 == 0) goto L_0x0276;
    L_0x026c:
        r0 = r12.h();	 Catch:{ all -> 0x02c2 }
        r1 = "Thread pool shutdown caught, worker is interrupted.";
        com.qq.reader.common.monitor.f.a(r0, r1);	 Catch:{ all -> 0x02c2 }
    L_0x0276:
        r12.a(r4);
        r1 = r12.a;
        monitor-enter(r1);
        r0 = r12.a;	 Catch:{ all -> 0x0292 }
        r0.notifyAll();	 Catch:{ all -> 0x0292 }
        monitor-exit(r1);	 Catch:{ all -> 0x0292 }
        r1 = r12.k;
        monitor-enter(r1);
        r0 = r12.k;	 Catch:{ all -> 0x0295 }
        r0.notifyAll();	 Catch:{ all -> 0x0295 }
        monitor-exit(r1);	 Catch:{ all -> 0x0295 }
        if (r3 == 0) goto L_0x005f;
    L_0x028d:
        r3.disconnect();
        goto L_0x005f;
    L_0x0292:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0292 }
        throw r0;
    L_0x0295:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0295 }
        throw r0;
    L_0x0298:
        r0 = move-exception;
        r3 = r1;
        r4 = r1;
    L_0x029b:
        r12.a(r4);
        r1 = r12.a;
        monitor-enter(r1);
        r2 = r12.a;	 Catch:{ all -> 0x02b6 }
        r2.notifyAll();	 Catch:{ all -> 0x02b6 }
        monitor-exit(r1);	 Catch:{ all -> 0x02b6 }
        r1 = r12.k;
        monitor-enter(r1);
        r2 = r12.k;	 Catch:{ all -> 0x02b9 }
        r2.notifyAll();	 Catch:{ all -> 0x02b9 }
        monitor-exit(r1);	 Catch:{ all -> 0x02b9 }
        if (r3 == 0) goto L_0x02b5;
    L_0x02b2:
        r3.disconnect();
    L_0x02b5:
        throw r0;
    L_0x02b6:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x02b6 }
        throw r0;
    L_0x02b9:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x02b9 }
        throw r0;
    L_0x02bc:
        r0 = move-exception;
        r4 = r1;
        goto L_0x029b;
    L_0x02bf:
        r0 = move-exception;
        r4 = r1;
        goto L_0x029b;
    L_0x02c2:
        r0 = move-exception;
        goto L_0x029b;
    L_0x02c4:
        r0 = move-exception;
        r3 = r1;
        goto L_0x029b;
    L_0x02c7:
        r0 = move-exception;
        r4 = r3;
        r3 = r1;
        goto L_0x029b;
    L_0x02cb:
        r0 = move-exception;
        r3 = r1;
        r4 = r2;
        goto L_0x029b;
    L_0x02cf:
        r0 = move-exception;
        r4 = r1;
        goto L_0x024a;
    L_0x02d3:
        r0 = move-exception;
        r4 = r1;
        goto L_0x024a;
    L_0x02d7:
        r0 = move-exception;
        goto L_0x024a;
    L_0x02da:
        r0 = move-exception;
        r3 = r1;
        goto L_0x024a;
    L_0x02de:
        r0 = move-exception;
        r4 = r3;
        r3 = r1;
        goto L_0x024a;
    L_0x02e3:
        r0 = move-exception;
        r4 = r1;
        goto L_0x0201;
    L_0x02e7:
        r0 = move-exception;
        r4 = r1;
        goto L_0x0201;
    L_0x02eb:
        r0 = move-exception;
        goto L_0x0201;
    L_0x02ee:
        r0 = move-exception;
        r3 = r1;
        goto L_0x0201;
    L_0x02f2:
        r0 = move-exception;
        r4 = r3;
        r3 = r1;
        goto L_0x0201;
    L_0x02f7:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0180;
    L_0x02fb:
        r0 = move-exception;
        r2 = r1;
        r1 = r3;
        goto L_0x0180;
    L_0x0300:
        r0 = move-exception;
        r2 = r1;
        r1 = r3;
        goto L_0x0180;
    L_0x0305:
        r0 = move-exception;
        r2 = r4;
        goto L_0x0180;
    L_0x0309:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0180;
    L_0x030d:
        r0 = move-exception;
        goto L_0x0179;
    L_0x0310:
        r1 = r2;
        goto L_0x00d8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.download.task.c.run():void");
    }

    private void a(InputStream inputStream) {
        if (!this.i) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    f.a("DownloadWorker", e.getMessage());
                }
            }
            try {
                if (this.l != null) {
                    this.l.close();
                    this.l = null;
                    this.i = true;
                }
            } catch (IOException e2) {
                f.a("DownloadWorker", e2.getMessage());
            } finally {
                this.l = null;
            }
        }
    }

    private void a(Exception exception) {
        f.a(h() + "markFailReason", "", exception);
        if (exception instanceof MalformedURLException) {
            this.a.setStatusCode(f.LOADER_ERROR);
            this.a.setFailReason("Object URI: " + this.a.getObjectURI() + " is malformed.");
        } else if (exception instanceof IOException) {
            synchronized (this) {
                try {
                    f.e(h() + "markFailReason", "waiting for phone state change signal");
                    wait(1000);
                } catch (Exception e) {
                    f.a("Thread: " + Thread.currentThread().getName() + ", DownloadWorker", "markFailReason", e);
                }
            }
            if (c()) {
                f.e(h() + "markFailReason", "phone state change signal is caught");
                this.a.setStatusCode(f.LOSS_OF_SERVICE);
                this.f.a(this.a, TaskActionEnum.Deactivate);
                return;
            }
            if (exception instanceof SocketTimeoutException) {
                this.a.setStatusCode(f.LOADER_ERROR);
                this.a.setFailReason("Connection Timeout");
            } else if (r.c(this.d - this.c)) {
                this.a.setStatusCode(f.LOADER_ERROR);
            } else {
                this.a.setStatusCode(f.INSUFFICIENT_MEMORY);
            }
            this.a.setFailReason(exception.getMessage());
        } else if (exception instanceof FileNotFoundException) {
            this.a.setFailReason("File: " + this.a.getTempFilePath() + " cannot be accessed.");
        }
        this.f.a(this.a, TaskActionEnum.Err);
    }

    private RandomAccessFile i() throws IOException {
        File file = new File(this.a.getTempFilePath());
        if (file.getParentFile() == null) {
            f.a(h() + "prepareRandomAccessFile", "file's directory is invalid: " + this.a.getDownloadDirectory());
            throw new IOException("file's directory is invalid: " + this.a.getDownloadDirectory());
        } else if (!ab.a(file.getParentFile())) {
            throw new IOException("cannot create directory:" + file.getParent());
        } else if (file.getParentFile().isDirectory()) {
            if (!file.exists()) {
                try {
                    if (!file.createNewFile()) {
                        f.a(h() + "prepareRandomAccessFile", "Failed to create new file:" + file.getName());
                    }
                } catch (IOException e) {
                    f.a(h() + "prepareRandomAccessFile", e.getMessage());
                    throw new IOException("cannot create file:" + file.getAbsolutePath());
                }
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.a.getTempFilePath(), "rw");
            this.c = randomAccessFile.length();
            randomAccessFile.seek(this.c);
            return randomAccessFile;
        } else {
            f.a(h() + "prepareRandomAccessFile", "file's directory is a file, not a directory: " + this.a.getDownloadDirectory());
            throw new IOException("file's directory is a file, not a directory: " + this.a.getDownloadDirectory());
        }
    }

    private HttpURLConnection a(URL url) throws IOException {
        HttpURLConnection a = b.a(url, this.e);
        if (a == null) {
            throw new IOException("Connection cannot be established to : " + url.toString());
        }
        a.setConnectTimeout(n.f);
        a.setReadTimeout(n.f);
        if (this.c > 0) {
            if (this.c < this.a.getSize()) {
                f.e(h() + "prepareConnection", " try to resume as current size !=0, currentsize:" + this.c);
                a.setRequestProperty("Range", "bytes=" + String.valueOf(this.c) + "-");
            } else {
                f.e(h() + "prepareConnection", " currentsize " + this.c + ">= task.getSize" + this.a.getSize());
                ao.a(new File(this.a.getTempFilePath()));
            }
        }
        int responseCode = a.getResponseCode();
        switch (responseCode) {
            case 200:
                String contentType = a.getContentType();
                if (contentType == null || (contentType.indexOf("text/vnd.wap.wml") == -1 && contentType.indexOf("application/vnd.wap.wmlc") == -1)) {
                    if (this.c > 0 && a.getHeaderField("Content-Range") == null) {
                        f.e(h() + "prepareConnection", " unsupported resume. start download again");
                        j();
                    }
                    this.d = ((long) a.getContentLength()) + this.c;
                    this.a.setSize(this.d);
                    return a;
                }
                if (a != null) {
                    a.disconnect();
                }
                return a(url);
            case 206:
                this.d = ((long) a.getContentLength()) + this.c;
                this.a.setSize(this.d);
                return a;
            case 301:
            case 302:
                if (a != null) {
                    a.disconnect();
                }
                return a(url);
            default:
                throw new IOException("HTTP Response Code: " + responseCode);
        }
    }

    private void j() throws IOException {
        if (this.l != null) {
            this.l.close();
        }
        if (ao.a(new File(this.a.getTempFilePath()))) {
            this.l = i();
            this.c = 0;
            f.a("DownloadWorker.prepareConnection", "Server use \"Accept_Ranges:none\" to response client that server does not support resumable downloading");
            return;
        }
        throw new IOException("File cannot be deleted: " + this.a.getTempFilePath());
    }

    protected boolean c() {
        return this.f.k();
    }

    public f d() {
        return this.a;
    }
}
