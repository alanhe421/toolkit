package com.qrcomic.downloader;

import android.annotation.TargetApi;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.text.TextUtils;
import com.qqcomic.bitmaphelper.CompactBitmapFactory;
import com.qqcomic.bitmaphelper.b;
import com.qrcomic.a.h;
import com.qrcomic.a.j;
import com.qrcomic.downloader.a.d;
import com.qrcomic.downloader.a.d.a;
import com.qrcomic.downloader.a.e;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.o;
import com.qrcomic.f.c;
import com.qrcomic.util.f;
import com.qrcomic.util.g;
import com.tencent.imsdk.BaseConstants;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: QRComicReadTask */
public class i extends h {
    d<a, b> c;
    public HashSet<WeakReference<j>> d = new HashSet();
    private ReentrantLock e = new ReentrantLock();
    private Condition j = this.e.newCondition();
    private long k;
    private boolean l = false;

    public void l() {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1439)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1461)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r5 = this;
        r0 = com.qrcomic.util.g.a();
        if (r0 == 0) goto L_0x0026;
    L_0x0006:
        r0 = "qqcomic.downloader.QRComicReadTask";
        r1 = com.qrcomic.util.g.d;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "QRComicReadTask cancelHttpReq ";
        r2 = r2.append(r3);
        r3 = r5.i();
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.qrcomic.util.g.b(r0, r1, r2);
    L_0x0026:
        r0 = r5.e;	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
        r0.lock();	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
        super.l();	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
        r0 = com.qrcomic.util.g.a();	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
        if (r0 == 0) goto L_0x003f;	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
    L_0x0034:
        r0 = "qqcomic.downloader.QRComicReadTask";	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
        r1 = com.qrcomic.util.g.d;	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
        r2 = "QRComicReadTask cancelHttpReq end";	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
        com.qrcomic.util.g.b(r0, r1, r2);	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
    L_0x003f:
        r0 = r5.j;
        r0.signalAll();
        r0 = com.qrcomic.util.g.a();
        if (r0 == 0) goto L_0x0068;
    L_0x004a:
        r0 = "qqcomic.downloader.QRComicReadTask";
        r1 = com.qrcomic.util.g.d;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = " UN_WAITING ON_RESPONSE ";
        r2 = r2.append(r3);
        r3 = r5.g;
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.qrcomic.util.g.b(r0, r1, r2);
    L_0x0068:
        r0 = r5.e;
        r0.unlock();
        r0 = com.qrcomic.util.g.a();
        if (r0 == 0) goto L_0x007e;
    L_0x0073:
        r0 = "qqcomic.downloader.QRComicReadTask";
        r1 = com.qrcomic.util.g.d;
        r2 = "QRComicReadTask cancelHttpReq notify signal";
        com.qrcomic.util.g.b(r0, r1, r2);
    L_0x007e:
        return;
    L_0x007f:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
        r1 = 102; // 0x66 float:1.43E-43 double:5.04E-322;	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
        r2.<init>();	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
        r3 = "cancelHttpReq exception msg=";	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
        r0 = r0.getMessage();	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
        r0 = r2.append(r0);	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
        r5.a(r1, r0);	 Catch:{ Exception -> 0x007f, all -> 0x00e0 }
        r0 = r5.j;
        r0.signalAll();
        r0 = com.qrcomic.util.g.a();
        if (r0 == 0) goto L_0x00c9;
    L_0x00ab:
        r0 = "qqcomic.downloader.QRComicReadTask";
        r1 = com.qrcomic.util.g.d;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = " UN_WAITING ON_RESPONSE ";
        r2 = r2.append(r3);
        r3 = r5.g;
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.qrcomic.util.g.b(r0, r1, r2);
    L_0x00c9:
        r0 = r5.e;
        r0.unlock();
        r0 = com.qrcomic.util.g.a();
        if (r0 == 0) goto L_0x007e;
    L_0x00d4:
        r0 = "qqcomic.downloader.QRComicReadTask";
        r1 = com.qrcomic.util.g.d;
        r2 = "QRComicReadTask cancelHttpReq notify signal";
        com.qrcomic.util.g.b(r0, r1, r2);
        goto L_0x007e;
    L_0x00e0:
        r0 = move-exception;
        r1 = r5.j;
        r1.signalAll();
        r1 = com.qrcomic.util.g.a();
        if (r1 == 0) goto L_0x010a;
    L_0x00ec:
        r1 = "qqcomic.downloader.QRComicReadTask";
        r2 = com.qrcomic.util.g.d;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = " UN_WAITING ON_RESPONSE ";
        r3 = r3.append(r4);
        r4 = r5.g;
        r3 = r3.append(r4);
        r3 = r3.toString();
        com.qrcomic.util.g.b(r1, r2, r3);
    L_0x010a:
        r1 = r5.e;
        r1.unlock();
        r1 = com.qrcomic.util.g.a();
        if (r1 == 0) goto L_0x0120;
    L_0x0115:
        r1 = "qqcomic.downloader.QRComicReadTask";
        r2 = com.qrcomic.util.g.d;
        r3 = "QRComicReadTask cancelHttpReq notify signal";
        com.qrcomic.util.g.b(r1, r2, r3);
    L_0x0120:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qrcomic.downloader.i.l():void");
    }

    public /* bridge */ /* synthetic */ void c(int i) {
        super.c(i);
    }

    public /* bridge */ /* synthetic */ String i() {
        return super.i();
    }

    public i(long j, long j2, ComicSectionPicInfo comicSectionPicInfo, j jVar) {
        if (j < 0 || comicSectionPicInfo == null || jVar == null) {
            throw new IllegalArgumentException("create QRComicReadTask agruments error taskType=" + this.a + ",queueSeq=" + j + ",picInfo=" + (comicSectionPicInfo != null ? comicSectionPicInfo.toString() : "null"));
        }
        this.a = 300;
        super.b(j);
        super.c(j2);
        this.g = comicSectionPicInfo;
        this.d.add(new WeakReference(jVar));
        this.h = 1;
        this.c = com.qrcomic.manager.b.a().b().h;
    }

    public void run() {
        if (g.a()) {
            g.b("qqcomic.downloader.QRComicReadTask", g.d, "QRComicReadTask run currentThread=" + Thread.currentThread().getName() + ",taskKey=" + i());
        }
        try {
            com.qrcomic.downloader.a.a b;
            this.e.lock();
            if (g.b()) {
                g.b("qqcomic.downloader.QRComicReadTask", g.b, " LOCK " + this.g);
            }
            this.k = SystemClock.elapsedRealtime();
            com.qrcomic.downloader.a.a aVar = null;
            com.qrcomic.downloader.a.b a = com.qrcomic.downloader.a.b.a();
            if (a != null) {
                aVar = a.a(this.g);
            }
            if (aVar == null) {
                b = e.a().b(this.g);
            } else {
                b = aVar;
            }
            if (b != null && b.a != null) {
                b a2 = a(b.a, b.b, 1);
                if (a2 == null || a2.e() == null) {
                    a(101, "bitmap decode error");
                } else {
                    d b2 = d.b();
                    a aVar2 = new a(this.g.picUrl, j(), k());
                    aVar2.a(this.g);
                    b2.a(aVar2, a2);
                    a(b.a.length, a2);
                }
            } else if (TextUtils.isEmpty(this.g.picUrl)) {
                a(100, "picInfo url is null and can't download");
            } else if (f.b(com.qrcomic.manager.b.a().b().b())) {
                this.l = true;
                super.a(this.g.picUrl);
                if (g.b()) {
                    g.b("qqcomic.downloader.QRComicReadTask", g.b, " WAITING " + this.g);
                }
                this.j.await();
            } else {
                a(105, "not connect network");
            }
            if (b != null) {
                b.a();
            }
            this.e.unlock();
            if (g.b()) {
                g.b("qqcomic.downloader.QRComicReadTask", g.b, " UNLOCK " + this.g);
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            d(4);
            a(102, "QRComicReadTask running throw exception," + e.getMessage());
            this.e.unlock();
            if (g.b()) {
                g.b("qqcomic.downloader.QRComicReadTask", g.b, " UNLOCK " + this.g);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            a(102, "QRComicReadTask running throw exception," + e2.getMessage());
            this.e.unlock();
            if (g.b()) {
                g.b("qqcomic.downloader.QRComicReadTask", g.b, " UNLOCK " + this.g);
            }
        } catch (Throwable th) {
            this.e.unlock();
            if (g.b()) {
                g.b("qqcomic.downloader.QRComicReadTask", g.b, " UNLOCK " + this.g);
            }
        }
        if (g.a()) {
            g.b("qqcomic.downloader.QRComicReadTask", g.d, "QRComicReadTask run end currentThread=" + Thread.currentThread().getName() + ",taskKey=" + i());
        }
    }

    public void a(com.qrcomic.downloader.c.b.a aVar) {
        String str;
        Exception e;
        Throwable th;
        c cVar;
        c cVar2 = null;
        if (g.a()) {
            String str2 = "qqcomic.downloader.QRComicReadTask";
            String str3 = g.d;
            StringBuilder append = new StringBuilder().append("QRComicReadTask onResp currentThread=").append(Thread.currentThread().getName()).append(",resp result=").append(aVar != null ? Integer.valueOf(aVar.c) : "null").append(",mPicInfo.picUrl=");
            if (this.g != null) {
                str = this.g.picUrl;
            } else {
                str = "null";
            }
            g.b(str2, str3, append.append(str).toString());
        }
        try {
            int i;
            this.e.lock();
            str = "";
            if (aVar != null) {
                int size;
                if (aVar.d.c instanceof c) {
                    cVar2 = aVar.d.c;
                    try {
                        size = cVar2.size();
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            e.printStackTrace();
                            a(102, "QRComicReadTask onResp exception msg=" + e.getMessage());
                            if (g.a()) {
                                g.b("qqcomic.downloader.QRComicReadTask", g.d, "QRComicReadTask onResp notify signal");
                            }
                            if (cVar2 != null) {
                                try {
                                    cVar2.close();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                    return;
                                }
                            }
                            if (g.b()) {
                                g.b("qqcomic.downloader.QRComicReadTask", g.b, " UN_WAITING ON_RESPONSE " + this.g);
                            }
                            this.j.signalAll();
                            if (g.b()) {
                                g.b("qqcomic.downloader.QRComicReadTask", g.b, " UNLOCK ON_RESPONSE " + this.g);
                            }
                            this.e.unlock();
                            return;
                        } catch (Throwable th2) {
                            th = th2;
                            cVar = cVar2;
                            if (g.a()) {
                                g.b("qqcomic.downloader.QRComicReadTask", g.d, "QRComicReadTask onResp notify signal");
                            }
                            if (cVar != null) {
                                try {
                                    cVar.close();
                                } catch (Exception e4) {
                                    e4.printStackTrace();
                                    throw th;
                                }
                            }
                            if (g.b()) {
                                g.b("qqcomic.downloader.QRComicReadTask", g.b, " UN_WAITING ON_RESPONSE " + this.g);
                            }
                            this.j.signalAll();
                            if (g.b()) {
                                g.b("qqcomic.downloader.QRComicReadTask", g.b, " UNLOCK ON_RESPONSE " + this.g);
                            }
                            this.e.unlock();
                            throw th;
                        }
                    }
                }
                size = 0;
                String str4 = aVar.a;
                int i2 = aVar.c;
                i2 = aVar.b;
                i = size;
                cVar = cVar2;
            } else {
                i = 0;
                cVar = null;
            }
            if (aVar != null) {
                try {
                    if (aVar.c == o.a && cVar != null && i > 0) {
                        if (g.a()) {
                            g.b("qqcomic.downloader.QRComicReadTask", g.d, "mPicInfo.picUrl=" + (this.g != null ? this.g.picUrl : "null") + "mOutStream.getBufData().toString()=" + cVar.a().toString());
                        }
                        b a = a(cVar.a(), i, 1);
                        if (a == null || a.e() == null || this.g == null) {
                            a(101, "bitmap decode error");
                            if (g.a()) {
                                g.b("qqcomic.downloader.QRComicReadTask", g.d, "QRComicReadTask onResp notify signal");
                            }
                            if (cVar != null) {
                                try {
                                    cVar.close();
                                } catch (Exception e32) {
                                    e32.printStackTrace();
                                    return;
                                }
                            }
                            if (g.b()) {
                                g.b("qqcomic.downloader.QRComicReadTask", g.b, " UN_WAITING ON_RESPONSE " + this.g);
                            }
                            this.j.signalAll();
                            if (g.b()) {
                                g.b("qqcomic.downloader.QRComicReadTask", g.b, " UNLOCK ON_RESPONSE " + this.g);
                            }
                            this.e.unlock();
                        }
                        d b = d.b();
                        a aVar2 = new a(this.g.picUrl, j(), k());
                        aVar2.a(this.g);
                        b.a(aVar2, a);
                        a(i, a);
                        final com.qrcomic.downloader.a.a aVar3 = new com.qrcomic.downloader.a.a(i);
                        aVar3.a(cVar.a(), 0, i);
                        j.a().a(new com.qrcomic.a.d(this) {
                            final /* synthetic */ i b;

                            public void run() {
                                com.qrcomic.downloader.a.b a = com.qrcomic.downloader.a.b.a();
                                if (a != null) {
                                    a.a(this.b.g, aVar3);
                                }
                                aVar3.a();
                            }
                        }, Integer.MAX_VALUE, null, false);
                        if (g.a()) {
                            g.b("qqcomic.downloader.QRComicReadTask", g.d, "QRComicReadTask onResp notify signal");
                        }
                        if (cVar != null) {
                            cVar.close();
                        }
                        if (g.b()) {
                            g.b("qqcomic.downloader.QRComicReadTask", g.b, " UN_WAITING ON_RESPONSE " + this.g);
                        }
                        this.j.signalAll();
                        if (g.b()) {
                            g.b("qqcomic.downloader.QRComicReadTask", g.b, " UNLOCK ON_RESPONSE " + this.g);
                        }
                        this.e.unlock();
                    }
                } catch (Exception e5) {
                    e32 = e5;
                    cVar2 = cVar;
                    e32.printStackTrace();
                    a(102, "QRComicReadTask onResp exception msg=" + e32.getMessage());
                    if (g.a()) {
                        g.b("qqcomic.downloader.QRComicReadTask", g.d, "QRComicReadTask onResp notify signal");
                    }
                    if (cVar2 != null) {
                        cVar2.close();
                    }
                    if (g.b()) {
                        g.b("qqcomic.downloader.QRComicReadTask", g.b, " UN_WAITING ON_RESPONSE " + this.g);
                    }
                    this.j.signalAll();
                    if (g.b()) {
                        g.b("qqcomic.downloader.QRComicReadTask", g.b, " UNLOCK ON_RESPONSE " + this.g);
                    }
                    this.e.unlock();
                    return;
                } catch (Throwable th3) {
                    th = th3;
                    if (g.a()) {
                        g.b("qqcomic.downloader.QRComicReadTask", g.d, "QRComicReadTask onResp notify signal");
                    }
                    if (cVar != null) {
                        cVar.close();
                    }
                    if (g.b()) {
                        g.b("qqcomic.downloader.QRComicReadTask", g.b, " UN_WAITING ON_RESPONSE " + this.g);
                    }
                    this.j.signalAll();
                    if (g.b()) {
                        g.b("qqcomic.downloader.QRComicReadTask", g.b, " UNLOCK ON_RESPONSE " + this.g);
                    }
                    this.e.unlock();
                    throw th;
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("download resp error");
            if (aVar != null) {
                stringBuilder.append(",errCode=" + aVar.c);
                stringBuilder.append(",errMsg=" + aVar.a);
                stringBuilder.append(",httpCode=" + aVar.b);
            }
            a(103, stringBuilder.toString());
            if (g.a()) {
                g.b("qqcomic.downloader.QRComicReadTask", g.d, "QRComicReadTask onResp notify signal");
            }
            if (cVar != null) {
                cVar.close();
            }
            if (g.b()) {
                g.b("qqcomic.downloader.QRComicReadTask", g.b, " UN_WAITING ON_RESPONSE " + this.g);
            }
            this.j.signalAll();
            if (g.b()) {
                g.b("qqcomic.downloader.QRComicReadTask", g.b, " UNLOCK ON_RESPONSE " + this.g);
            }
            this.e.unlock();
        } catch (Throwable th4) {
            th = th4;
            cVar = null;
            if (g.a()) {
                g.b("qqcomic.downloader.QRComicReadTask", g.d, "QRComicReadTask onResp notify signal");
            }
            if (cVar != null) {
                cVar.close();
            }
            if (g.b()) {
                g.b("qqcomic.downloader.QRComicReadTask", g.b, " UN_WAITING ON_RESPONSE " + this.g);
            }
            this.j.signalAll();
            if (g.b()) {
                g.b("qqcomic.downloader.QRComicReadTask", g.b, " UNLOCK ON_RESPONSE " + this.g);
            }
            this.e.unlock();
            throw th;
        }
    }

    public void a(int i, String str) {
        try {
            String str2;
            if (g.a()) {
                g.b("qqcomic.downloader.QRComicReadTask", g.d, "QRComicReadTask onReadTaskError errCode=" + i + ",errMsg=" + str);
            }
            com.qrcomic.d.b c = d.b().c();
            final HashSet hashSet = this.d;
            final ComicSectionPicInfo comicSectionPicInfo = this.g;
            final int i2 = i;
            final String str3 = str;
            c.a(new Runnable(this) {
                final /* synthetic */ i e;

                public void run() {
                    if (hashSet != null) {
                        Iterator it = hashSet.iterator();
                        while (it.hasNext()) {
                            WeakReference weakReference = (WeakReference) it.next();
                            if (weakReference != null) {
                                j jVar = (j) weakReference.get();
                                if (jVar != null) {
                                    jVar.a(comicSectionPicInfo, i2, str3);
                                }
                            }
                        }
                    }
                }
            });
            c(3);
            if (f.a(com.qrcomic.manager.b.a().b().b())) {
                str2 = "1";
            } else {
                str2 = "0";
            }
            if (this.l) {
                str2 = "1";
            } else {
                str2 = "0";
            }
        } catch (Exception e) {
            if (g.a()) {
                g.b("qqcomic.downloader.QRComicReadTask", g.d, "QRComicReadTask onReadTaskError Exception msg=" + e.getMessage());
            }
            e.printStackTrace();
        }
    }

    public void a(int i, b bVar) {
        try {
            String str;
            if (g.a()) {
                g.b("qqcomic.downloader.QRComicReadTask", g.d, "QRComicReadTask onReadTaskSuccess len=" + i);
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long j = 0;
            if (elapsedRealtime > this.k) {
                j = (((long) i) / (elapsedRealtime - this.k)) * 1000;
            }
            com.qrcomic.d.b c = d.b().c();
            final HashSet hashSet = this.d;
            final ComicSectionPicInfo comicSectionPicInfo = this.g;
            final b bVar2 = bVar;
            final int i2 = i;
            c.a(new Runnable(this) {
                final /* synthetic */ i f;

                public void run() {
                    comicSectionPicInfo.bitmap = bVar2.e();
                    if (hashSet != null) {
                        Iterator it = hashSet.iterator();
                        while (it.hasNext()) {
                            WeakReference weakReference = (WeakReference) it.next();
                            j jVar;
                            if (comicSectionPicInfo.bitmap != null) {
                                jVar = (j) weakReference.get();
                                if (jVar != null) {
                                    jVar.a(comicSectionPicInfo, (long) i2, j);
                                }
                            } else {
                                jVar = (j) weakReference.get();
                                if (jVar != null) {
                                    jVar.a(comicSectionPicInfo, 106, "bitmap is null,may be memcache is full");
                                }
                            }
                        }
                    }
                }
            });
            c(3);
            if (f.a(com.qrcomic.manager.b.a().b().b())) {
                str = "1";
            } else {
                str = "0";
            }
            if (this.l) {
                str = "1";
            } else {
                str = "0";
            }
        } catch (Exception e) {
            if (g.a()) {
                g.b("qqcomic.downloader.QRComicReadTask", g.d, "QRComicReadTask onReadTaskSuccess Exception msg=" + e.getMessage());
            }
            e.printStackTrace();
        }
    }

    @TargetApi(11)
    private b a(byte[] bArr, int i, int i2) {
        float f = 1.0f;
        try {
            Options options = new Options();
            if (h.e == 0) {
                if (VERSION.SDK_INT < 11 || VERSION.SDK_INT > 16) {
                    options.inPreferredConfig = Config.RGB_565;
                } else {
                    options.inPreferredConfig = Config.ARGB_4444;
                }
                if (g.a()) {
                    g.b("qqcomic.downloader.QRComicReadTask", g.d, "Use 16-bit to decode.");
                }
            } else if (g.a()) {
                g.b("qqcomic.downloader.QRComicReadTask", g.d, "Use 32-bit to decode.");
            }
            float f2 = (float) this.g.width;
            float f3 = (float) this.g.height;
            int[] iArr = new int[2];
            h b = com.qrcomic.manager.b.a().b();
            if (b == null || b.b() == null) {
                f2 = 1.0f;
            } else {
                iArr = com.qrcomic.util.c.d.a(b.b());
                if (f2 / ((float) iArr[0]) != 0.0f) {
                    f = f2 / ((float) iArr[0]);
                }
                f2 = f;
            }
            if (f2 != 0.0f) {
                options.inScaled = true;
                options.inSampleSize = (int) Math.ceil((double) f2);
            }
            b a = CompactBitmapFactory.a(bArr, 0, i, options);
            if (g.a() && a != null) {
                g.a("qqcomic.downloader.QRComicReadTask", g.d, " 图片缩放完成。。。。。缩放倍数 ： " + f2 + " 图片宽高：" + a.b() + "：" + a.c());
            }
            if (!(a == null || a.e() == null)) {
                return a;
            }
        } catch (OutOfMemoryError e) {
            if (this.c != null) {
                e.printStackTrace();
                long d = d(i2);
                if (g.a()) {
                    long a2 = this.c.a();
                    int b2 = this.c.b();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("decode bitmap oom error,clear cache and call system gc,try times=" + i2);
                    stringBuilder.append(",memMaxSize=" + (d / BaseConstants.MEGA) + "MB");
                    stringBuilder.append(",currentMemSize=" + (a2 / BaseConstants.MEGA) + "MB");
                    stringBuilder.append(",currentBitmapNum=" + b2);
                    g.b("qqcomic.downloader.QRComicReadTask", g.d, stringBuilder.toString());
                }
                if (i2 < 3) {
                    try {
                        Thread.sleep((long) (i2 * 100));
                        i2++;
                        return a(bArr, i, i2);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        if (g.a()) {
            g.b("qqcomic.downloader.QRComicReadTask", g.d, "end decode bitmap error,try times=" + i2);
        }
        return null;
    }

    private long d(int i) {
        if (g.a()) {
            g.a("qqcomic.downloader.QRComicReadTask", g.d, "trim memory开始回收内存 time=" + i + " percent = " + ((5 - i) / 10));
        }
        long g = this.c.g();
        this.c.a(Math.max((((long) (5 - i)) * g) / 10, 4194304));
        System.gc();
        return g;
    }

    protected void a(com.qrcomic.downloader.c.b.c cVar) {
        super.a(cVar);
        cVar.h.put("dt", "1");
        if (this.g != null && !TextUtils.isEmpty(this.g.preloadLocation)) {
            cVar.h.put("preloadLocation", this.g.preloadLocation);
            if (g.a()) {
                g.a("qqcomic.downloader.QRComicReadTask", g.d, "预加载图片位置 : " + this.g.preloadLocation);
            }
        }
    }

    public void b(WeakReference<j> weakReference) {
        if (weakReference != null) {
            Set<WeakReference> synchronizedSet = Collections.synchronizedSet(this.d);
            synchronized (synchronizedSet) {
                Object obj;
                j jVar = (j) weakReference.get();
                for (WeakReference weakReference2 : synchronizedSet) {
                    if (weakReference2 != null) {
                        j jVar2 = (j) weakReference2.get();
                        if (!(jVar2 == null || jVar == null || !jVar2.equals(jVar))) {
                            obj = 1;
                            break;
                        }
                    }
                }
                obj = null;
                if (obj == null && jVar != null) {
                    this.d.add(weakReference);
                }
            }
        }
    }

    public void a(long j, long j2, boolean z) {
    }
}
