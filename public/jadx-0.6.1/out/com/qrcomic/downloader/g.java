package com.qrcomic.downloader;

import android.os.SystemClock;
import com.qrcomic.downloader.a.e;
import com.qrcomic.downloader.c.b.a;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.o;
import com.qrcomic.f.c;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: QRComicOfflineTask */
public class g extends h {
    public static AtomicBoolean c = new AtomicBoolean(false);
    public static AtomicBoolean d = new AtomicBoolean(false);
    public long e = 0;
    private ReentrantLock j = new ReentrantLock();
    private Condition k = this.j.newCondition();
    private long l;

    public /* bridge */ /* synthetic */ void c(int i) {
        super.c(i);
    }

    public /* bridge */ /* synthetic */ String i() {
        return super.i();
    }

    public g(long j, long j2, ComicSectionPicInfo comicSectionPicInfo, x xVar) {
        if (j < 0 || comicSectionPicInfo == null || xVar == null) {
            throw new IllegalArgumentException("create QRComicOfflineTask agruments error taskType=" + this.a + ",queueSeq=" + j);
        }
        this.a = 200;
        super.b(j);
        super.c(j2);
        this.g = comicSectionPicInfo;
        this.b.add(new WeakReference(xVar));
        this.h = 1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r6 = this;
        r0 = com.qrcomic.util.g.a();
        if (r0 == 0) goto L_0x0039;
    L_0x0006:
        r0 = "QRComicOfflineTask";
        r1 = com.qrcomic.util.g.d;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "QRComicOfflineTask run currentThread=";
        r2 = r2.append(r3);
        r3 = java.lang.Thread.currentThread();
        r3 = r3.getName();
        r2 = r2.append(r3);
        r3 = ",taskKey=";
        r2 = r2.append(r3);
        r3 = r6.i();
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.qrcomic.util.g.b(r0, r1, r2);
    L_0x0039:
        r0 = r6.j;	 Catch:{ Exception -> 0x0080 }
        r0.lock();	 Catch:{ Exception -> 0x0080 }
        r0 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Exception -> 0x0080 }
        r6.l = r0;	 Catch:{ Exception -> 0x0080 }
        r1 = com.qrcomic.downloader.a.e.a();	 Catch:{ Exception -> 0x0080 }
        r0 = r6.g;	 Catch:{ Exception -> 0x0080 }
        r2 = r1.a(r0);	 Catch:{ Exception -> 0x0080 }
        r4 = 0;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 <= 0) goto L_0x005d;
    L_0x0054:
        r6.d(r2);	 Catch:{ Exception -> 0x0080 }
    L_0x0057:
        r0 = r6.j;
        r0.unlock();
    L_0x005c:
        return;
    L_0x005d:
        r0 = 0;
        r2 = com.qrcomic.downloader.a.b.a();	 Catch:{ Exception -> 0x0080 }
        if (r2 == 0) goto L_0x006a;
    L_0x0064:
        r0 = r6.g;	 Catch:{ Exception -> 0x0080 }
        r0 = r2.a(r0);	 Catch:{ Exception -> 0x0080 }
    L_0x006a:
        if (r0 == 0) goto L_0x00b7;
    L_0x006c:
        r2 = r0.a;	 Catch:{ Exception -> 0x0080 }
        if (r2 == 0) goto L_0x00b7;
    L_0x0070:
        r2 = r6.g;	 Catch:{ Exception -> 0x0080 }
        r1 = r1.a(r2, r0);	 Catch:{ Exception -> 0x0080 }
        if (r1 == 0) goto L_0x00a7;
    L_0x0078:
        r0 = r0.a;	 Catch:{ Exception -> 0x0080 }
        r0 = r0.length;	 Catch:{ Exception -> 0x0080 }
        r0 = (long) r0;	 Catch:{ Exception -> 0x0080 }
        r6.d(r0);	 Catch:{ Exception -> 0x0080 }
        goto L_0x0057;
    L_0x0080:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00b0 }
        r1 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b0 }
        r2.<init>();	 Catch:{ all -> 0x00b0 }
        r3 = "task running throw exception,";
        r2 = r2.append(r3);	 Catch:{ all -> 0x00b0 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x00b0 }
        r0 = r2.append(r0);	 Catch:{ all -> 0x00b0 }
        r0 = r0.toString();	 Catch:{ all -> 0x00b0 }
        r6.a(r1, r0);	 Catch:{ all -> 0x00b0 }
        r0 = r6.j;
        r0.unlock();
        goto L_0x005c;
    L_0x00a7:
        r0 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        r1 = "save to sdcard error";
        r6.a(r0, r1);	 Catch:{ Exception -> 0x0080 }
        goto L_0x0057;
    L_0x00b0:
        r0 = move-exception;
        r1 = r6.j;
        r1.unlock();
        throw r0;
    L_0x00b7:
        r0 = r6.g;	 Catch:{ Exception -> 0x0080 }
        r0 = r0.picUrl;	 Catch:{ Exception -> 0x0080 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0080 }
        if (r0 != 0) goto L_0x0111;
    L_0x00c1:
        r0 = com.qrcomic.manager.b.a();	 Catch:{ Exception -> 0x0080 }
        r0 = r0.b();	 Catch:{ Exception -> 0x0080 }
        r1 = r0.b();	 Catch:{ Exception -> 0x0080 }
        r1 = com.qrcomic.util.f.b(r1);	 Catch:{ Exception -> 0x0080 }
        if (r1 == 0) goto L_0x0107;
    L_0x00d3:
        r1 = c;	 Catch:{ Exception -> 0x0080 }
        r1 = r1.get();	 Catch:{ Exception -> 0x0080 }
        if (r1 != 0) goto L_0x00e5;
    L_0x00db:
        r0 = r0.b();	 Catch:{ Exception -> 0x0080 }
        r0 = com.qrcomic.util.f.c(r0);	 Catch:{ Exception -> 0x0080 }
        if (r0 == 0) goto L_0x00f3;
    L_0x00e5:
        r0 = r6.g;	 Catch:{ Exception -> 0x0080 }
        r0 = r0.picUrl;	 Catch:{ Exception -> 0x0080 }
        super.a(r0);	 Catch:{ Exception -> 0x0080 }
        r0 = r6.k;	 Catch:{ Exception -> 0x0080 }
        r0.await();	 Catch:{ Exception -> 0x0080 }
        goto L_0x0057;
    L_0x00f3:
        r0 = d;	 Catch:{ Exception -> 0x0080 }
        r1 = 1;
        r0 = r0.getAndSet(r1);	 Catch:{ Exception -> 0x0080 }
        if (r0 != 0) goto L_0x0057;
    L_0x00fc:
        r0 = com.qrcomic.downloader.d.b();	 Catch:{ Exception -> 0x0080 }
        r1 = 100;
        r0.a(r1);	 Catch:{ Exception -> 0x0080 }
        goto L_0x0057;
    L_0x0107:
        r0 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        r1 = "not connect network";
        r6.a(r0, r1);	 Catch:{ Exception -> 0x0080 }
        goto L_0x0057;
    L_0x0111:
        r0 = 100;
        r1 = "picInfo url is null and can't download";
        r6.a(r0, r1);	 Catch:{ Exception -> 0x0080 }
        goto L_0x0057;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qrcomic.downloader.g.run():void");
    }

    public void a(a aVar) {
        int i = 0;
        if (com.qrcomic.util.g.a()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("QRComicOfflineTask onResp currentThread=" + Thread.currentThread().getName());
            if (aVar != null) {
                stringBuilder.append(",resp result=" + aVar.c);
            } else {
                stringBuilder.append(",resp null");
            }
            com.qrcomic.util.g.b("QRComicOfflineTask", com.qrcomic.util.g.d, stringBuilder.toString());
        }
        c cVar = null;
        try {
            this.j.lock();
            String str = "";
            if (aVar != null) {
                if (aVar.d != null) {
                    cVar = aVar.d.c;
                    i = cVar.size();
                }
                str = aVar.a;
                int i2 = aVar.c;
                i2 = aVar.c;
                i2 = aVar.b;
            }
            if (aVar == null || aVar.c != o.a || cVar == null || i <= 0) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("download resp error");
                if (aVar != null) {
                    stringBuilder2.append(",errCode=" + aVar.c);
                    stringBuilder2.append(",errMsg=" + aVar.a);
                    stringBuilder2.append(",httpCode=" + aVar.b);
                } else {
                    stringBuilder2.append(",resp is null");
                }
                if (aVar == null || aVar.c != a.a.c) {
                    a(103, stringBuilder2.toString());
                }
            } else {
                com.qrcomic.downloader.a.a aVar2 = new com.qrcomic.downloader.a.a(i);
                aVar2.a(cVar.a(), 0, i);
                boolean a = e.a().a(this.g, aVar2);
                aVar2.a();
                if (a) {
                    d((long) i);
                } else {
                    a(104, "save to sdcard error");
                }
            }
            if (com.qrcomic.util.g.a()) {
                com.qrcomic.util.g.b("QRComicOfflineTask", com.qrcomic.util.g.d, "QRComicOfflineTask onResp notify signal");
            }
            if (cVar != null) {
                try {
                    cVar.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            this.k.signalAll();
            this.j.unlock();
        } catch (Exception e2) {
            e2.printStackTrace();
            a(102, "onResp exception msg=" + e2.getMessage());
            if (com.qrcomic.util.g.a()) {
                com.qrcomic.util.g.b("QRComicOfflineTask", com.qrcomic.util.g.d, "QRComicOfflineTask onResp notify signal");
            }
            if (cVar != null) {
                try {
                    cVar.close();
                } catch (Exception e22) {
                    e22.printStackTrace();
                    return;
                }
            }
            this.k.signalAll();
            this.j.unlock();
        } catch (Throwable th) {
            if (com.qrcomic.util.g.a()) {
                com.qrcomic.util.g.b("QRComicOfflineTask", com.qrcomic.util.g.d, "QRComicOfflineTask onResp notify signal");
            }
            if (cVar != null) {
                try {
                    cVar.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            this.k.signalAll();
            this.j.unlock();
        }
    }

    public void d(long j) {
        try {
            if (com.qrcomic.util.g.a()) {
                com.qrcomic.util.g.b("QRComicOfflineTask", com.qrcomic.util.g.d, "QRComicOfflineTask onOfflineTaskSuccess");
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime > this.l) {
                this.e = (j / (elapsedRealtime - this.l)) * 1000;
            }
            if (this.b != null) {
                synchronized (Collections.synchronizedSet(this.b)) {
                    Iterator it = this.b.iterator();
                    while (it.hasNext()) {
                        WeakReference weakReference = (WeakReference) it.next();
                        if (weakReference != null) {
                            x xVar = (x) weakReference.get();
                            if (xVar != null) {
                                xVar.b(this);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (com.qrcomic.util.g.a()) {
                com.qrcomic.util.g.b("QRComicOfflineTask", com.qrcomic.util.g.d, "QRComicOfflineTask onOfflineTaskSuccess Exception msg=" + e.getMessage());
            }
            e.printStackTrace();
        }
    }

    public void a(int i, String str) {
        if (com.qrcomic.util.g.a()) {
            com.qrcomic.util.g.b("QRComicOfflineTask", com.qrcomic.util.g.d, "QRComicOfflineTask onOfflineTaskError errCode=" + i + ",errMsg=" + str);
        }
        try {
            if (this.b != null) {
                Set<WeakReference> synchronizedSet = Collections.synchronizedSet(this.b);
                synchronized (synchronizedSet) {
                    for (WeakReference weakReference : synchronizedSet) {
                        if (weakReference != null) {
                            x xVar = (x) weakReference.get();
                            if (xVar != null) {
                                xVar.a(this, i, str);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (com.qrcomic.util.g.a()) {
                com.qrcomic.util.g.b("QRComicOfflineTask", com.qrcomic.util.g.d, "QRComicOfflineTask onOfflineTaskError Exception msg=" + e.getMessage());
            }
            e.printStackTrace();
        }
    }

    protected void a(com.qrcomic.downloader.c.b.c cVar) {
        super.a(cVar);
        cVar.h.put("dt", "2");
    }

    public void l() {
        if (com.qrcomic.util.g.a()) {
            com.qrcomic.util.g.b("QRComicOfflineTask", com.qrcomic.util.g.d, "QRComicOfflineTask cancelHttpReq");
        }
        try {
            this.j.lock();
            super.l();
        } catch (Exception e) {
            e.printStackTrace();
            a(102, "cancelHttpReq exception msg=" + e.getMessage());
        } finally {
            if (com.qrcomic.util.g.a()) {
                com.qrcomic.util.g.b("QRComicOfflineTask", com.qrcomic.util.g.d, "QRComicOfflineTask cancelHttpReq notify signal");
            }
            this.k.signalAll();
            this.j.unlock();
        }
    }

    public void a(long j, long j2, boolean z) {
    }

    public static void a(boolean z) {
        c.set(z);
    }
}
