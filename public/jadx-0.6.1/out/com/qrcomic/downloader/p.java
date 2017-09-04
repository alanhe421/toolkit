package com.qrcomic.downloader;

import android.os.HandlerThread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: QRComicSectionPreloadThread */
public class p extends HandlerThread {
    private final Lock a = new ReentrantLock();
    private final Condition b = this.a.newCondition();
    private AtomicInteger c = new AtomicInteger(0);

    public p() {
        super("QRComicSectionPreloadThread");
        this.c.set(0);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r10 = this;
        r1 = 0;
        r0 = r10.c;
        r0.set(r1);
    L_0x0006:
        r0 = r10.a;	 Catch:{ Exception -> 0x001f }
        r0.lock();	 Catch:{ Exception -> 0x001f }
        r7 = com.qrcomic.downloader.d.b();	 Catch:{ Exception -> 0x001f }
    L_0x000f:
        r0 = r10.a();	 Catch:{ Exception -> 0x001f }
        if (r0 == 0) goto L_0x0029;
    L_0x0015:
        r0 = r10.b;	 Catch:{ Exception -> 0x001f }
        r2 = 3;
        r1 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ Exception -> 0x001f }
        r0.await(r2, r1);	 Catch:{ Exception -> 0x001f }
        goto L_0x000f;
    L_0x001f:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00b2 }
        r0 = r10.a;
        r0.unlock();
        goto L_0x0006;
    L_0x0029:
        r0 = r7.c;	 Catch:{ Exception -> 0x001f }
        r5 = r0.poll();	 Catch:{ Exception -> 0x001f }
        r5 = (com.qrcomic.downloader.u) r5;	 Catch:{ Exception -> 0x001f }
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x001f }
        r5.v = r0;	 Catch:{ Exception -> 0x001f }
        r0 = r7.c;	 Catch:{ Exception -> 0x001f }
        r8 = r0.size();	 Catch:{ Exception -> 0x001f }
        r0 = r5.f;	 Catch:{ Exception -> 0x001f }
        r1 = r0.a;	 Catch:{ Exception -> 0x001f }
        r0 = r5.f;	 Catch:{ Exception -> 0x001f }
        r2 = r0.b;	 Catch:{ Exception -> 0x001f }
        r0 = r5.f;	 Catch:{ Exception -> 0x001f }
        r9 = r0.f;	 Catch:{ Exception -> 0x001f }
        r0 = r5.m();	 Catch:{ Exception -> 0x001f }
        if (r0 == 0) goto L_0x0096;
    L_0x004f:
        r5.q();	 Catch:{ Exception -> 0x001f }
    L_0x0052:
        r0 = 2;
        r2 = 1;
        r7.a(r0, r2, r5);	 Catch:{ Exception -> 0x001f }
        r0 = com.qrcomic.util.g.a();	 Catch:{ Exception -> 0x001f }
        if (r0 == 0) goto L_0x008f;
    L_0x005d:
        r0 = "QRComicSectionPreloadThread";
        r2 = com.qrcomic.util.g.d;	 Catch:{ Exception -> 0x001f }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x001f }
        r3.<init>();	 Catch:{ Exception -> 0x001f }
        r4 = "send request query comic picInfo comicId=";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x001f }
        r1 = r3.append(r1);	 Catch:{ Exception -> 0x001f }
        r3 = ",sectionIndex=";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x001f }
        r1 = r1.append(r9);	 Catch:{ Exception -> 0x001f }
        r3 = ",preloadQueueSize=";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x001f }
        r1 = r1.append(r8);	 Catch:{ Exception -> 0x001f }
        r1 = r1.toString();	 Catch:{ Exception -> 0x001f }
        com.qrcomic.util.g.b(r0, r2, r1);	 Catch:{ Exception -> 0x001f }
    L_0x008f:
        r0 = r10.a;
        r0.unlock();
        goto L_0x0006;
    L_0x0096:
        r0 = r5.j;	 Catch:{ Exception -> 0x001f }
        r3 = 1;
        r5.a(r0, r3);	 Catch:{ Exception -> 0x001f }
        r0 = com.qrcomic.manager.b.a();	 Catch:{ Exception -> 0x001f }
        r0 = r0.b();	 Catch:{ Exception -> 0x001f }
        r3 = 1;
        r0 = r0.a(r3);	 Catch:{ Exception -> 0x001f }
        r0 = (com.qrcomic.manager.QRComicManager) r0;	 Catch:{ Exception -> 0x001f }
        r3 = 0;
        r4 = 0;
        r6 = 0;
        r0.a(r1, r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x001f }
        goto L_0x0052;
    L_0x00b2:
        r0 = move-exception;
        r1 = r10.a;
        r1.unlock();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qrcomic.downloader.p.run():void");
    }

    public boolean a() {
        d b = d.b();
        if (b.c.isEmpty()) {
            return true;
        }
        if (b.e != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(b.e.values());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar instanceof u) {
                    u uVar = (u) aVar;
                    int b2 = uVar.g.b();
                    if ((b2 == 3 || b2 == 4) && !uVar.o()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
