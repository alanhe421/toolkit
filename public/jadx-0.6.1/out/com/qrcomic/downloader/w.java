package com.qrcomic.downloader;

import android.text.TextUtils;
import com.qrcomic.downloader.a.a;
import com.qrcomic.util.g;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: QRComicTaskDispatcherPool */
public class w extends ThreadPoolExecutor {
    public static final int a = (e + 1);
    private static final int e = Runtime.getRuntime().availableProcessors();
    public ConcurrentHashMap<String, a> b;
    public ConcurrentHashMap<String, a> c;
    private final String d;
    private AtomicLong f;
    private ReentrantLock g;

    public w() {
        this(a, 40);
    }

    public w(int i, int i2) {
        super(i, i, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(i2, new a()), new ThreadFactory() {
            private final AtomicInteger a = new AtomicInteger(1);
            private final ThreadGroup b = new ThreadGroup("VipComicTaskDispatcherGroup");

            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(this.b, runnable, "VipComicTaskDispatcherThread-" + this.a.getAndIncrement(), 0);
                thread.setDaemon(false);
                thread.setPriority(5);
                return thread;
            }
        });
        this.d = "QRComicTaskDispatcherPool";
        this.f = new AtomicLong(0);
        this.b = new ConcurrentHashMap();
        this.c = new ConcurrentHashMap();
        this.g = new ReentrantLock();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute(java.lang.Runnable r5) {
        /*
        r4 = this;
        r0 = r4.g;	 Catch:{ Exception -> 0x0036 }
        r0.lock();	 Catch:{ Exception -> 0x0036 }
        r0 = r5 instanceof com.qrcomic.downloader.a;	 Catch:{ Exception -> 0x0036 }
        if (r0 == 0) goto L_0x0063;
    L_0x0009:
        r5 = (com.qrcomic.downloader.a) r5;	 Catch:{ Exception -> 0x0036 }
        if (r5 == 0) goto L_0x0017;
    L_0x000d:
        r0 = r5.i();	 Catch:{ Exception -> 0x0036 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0036 }
        if (r0 == 0) goto L_0x0044;
    L_0x0017:
        r1 = new java.lang.IllegalArgumentException;	 Catch:{ Exception -> 0x0036 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0036 }
        r0.<init>();	 Catch:{ Exception -> 0x0036 }
        r2 = "QRComicAbstractTask";
        r2 = r0.append(r2);	 Catch:{ Exception -> 0x0036 }
        if (r5 != 0) goto L_0x0040;
    L_0x0027:
        r0 = "is null";
    L_0x002a:
        r0 = r2.append(r0);	 Catch:{ Exception -> 0x0036 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0036 }
        r1.<init>(r0);	 Catch:{ Exception -> 0x0036 }
        throw r1;	 Catch:{ Exception -> 0x0036 }
    L_0x0036:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0072 }
        r0 = r4.g;
        r0.unlock();
    L_0x003f:
        return;
    L_0x0040:
        r0 = "'s taskKey is null";
        goto L_0x002a;
    L_0x0044:
        r0 = r4.c;	 Catch:{ Exception -> 0x0036 }
        r1 = r5.i();	 Catch:{ Exception -> 0x0036 }
        r0.put(r1, r5);	 Catch:{ Exception -> 0x0036 }
        r0 = r5.h();	 Catch:{ Exception -> 0x0036 }
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 != 0) goto L_0x005e;
    L_0x0057:
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0036 }
        r5.a(r0);	 Catch:{ Exception -> 0x0036 }
    L_0x005e:
        r0 = r5.a;	 Catch:{ Exception -> 0x006d }
        switch(r0) {
            case 100: goto L_0x007f;
            case 200: goto L_0x0069;
            case 300: goto L_0x0079;
            default: goto L_0x0063;
        };
    L_0x0063:
        r0 = r4.g;
        r0.unlock();
        goto L_0x003f;
    L_0x0069:
        r4.b(r5);	 Catch:{ Exception -> 0x006d }
        goto L_0x0063;
    L_0x006d:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Exception -> 0x0036 }
        goto L_0x0063;
    L_0x0072:
        r0 = move-exception;
        r1 = r4.g;
        r1.unlock();
        throw r0;
    L_0x0079:
        r5 = (com.qrcomic.downloader.i) r5;	 Catch:{ Exception -> 0x006d }
        r4.a(r5);	 Catch:{ Exception -> 0x006d }
        goto L_0x0063;
    L_0x007f:
        r4.c(r5);	 Catch:{ Exception -> 0x006d }
        goto L_0x0063;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qrcomic.downloader.w.execute(java.lang.Runnable):void");
    }

    private void a(i iVar) {
        int i = 10;
        try {
            i iVar2;
            Iterator it;
            WeakReference weakReference;
            long currentTimeMillis = System.currentTimeMillis();
            String i2 = iVar.i();
            if (g.a()) {
                g.b("QRComicTaskDispatcherPool", g.d, "addExecute QRComicReadTask: " + iVar.i() + ",queueSeq=" + iVar.j() + ",index=" + iVar.k());
            }
            if (this.b.containsKey(i2)) {
                iVar2 = (i) this.b.get(i2);
                if (currentTimeMillis - iVar2.h() > 201000) {
                    it = iVar2.d.iterator();
                    while (it.hasNext()) {
                        weakReference = (WeakReference) it.next();
                        if (weakReference != null) {
                            iVar.b(weakReference);
                        }
                    }
                    iVar2.l();
                    b(iVar);
                } else {
                    it = iVar.d.iterator();
                    while (it.hasNext()) {
                        weakReference = (WeakReference) it.next();
                        if (weakReference != null) {
                            iVar2.b(weakReference);
                        }
                    }
                }
            } else if (getQueue().contains(iVar)) {
                iVar2 = (i) a((a) iVar);
                if (iVar2 != null) {
                    it = iVar2.d.iterator();
                    while (it.hasNext()) {
                        weakReference = (WeakReference) it.next();
                        if (weakReference != null) {
                            iVar.d.add(weakReference);
                        }
                    }
                    super.remove(iVar2);
                }
                super.execute(iVar);
            } else {
                b(iVar);
            }
            c(i2);
            if (this.f.get() > 10) {
                Object[] toArray = getQueue().toArray();
                if (toArray != null && toArray.length > 10) {
                    Arrays.sort(toArray, new a());
                    while (i < toArray.length) {
                        Object obj = toArray[i];
                        if (obj instanceof i) {
                            iVar2 = (i) obj;
                            it = iVar2.d.iterator();
                            while (it.hasNext()) {
                                weakReference = (WeakReference) it.next();
                                if (weakReference != null) {
                                    j jVar = (j) weakReference.get();
                                    if (jVar != null) {
                                        jVar.a(iVar2.g, "Read Task Queue Full");
                                    }
                                }
                            }
                            iVar2.l();
                            super.remove(iVar2);
                            currentTimeMillis = this.f.decrementAndGet();
                            this.c.remove(iVar2.i());
                            if (g.a()) {
                                g.b("QRComicTaskDispatcherPool", g.d, "Queue Read Task Full remove old task key=" + iVar2.i() + ",current queue read task size=" + currentTimeMillis);
                            }
                        }
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void c(String str) {
        try {
            for (Entry value : this.b.entrySet()) {
                a aVar = (a) value.getValue();
                if (!aVar.i().equals(str) && (aVar instanceof i)) {
                    if (System.currentTimeMillis() - aVar.h() > 201000) {
                        aVar.l();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b(i iVar) {
        super.execute(iVar);
        this.f.incrementAndGet();
    }

    private void b(a aVar) {
        try {
            String i = aVar.i();
            Iterator it;
            if (this.b.containsKey(i)) {
                a aVar2 = (a) this.b.get(i);
                it = aVar.b.iterator();
                while (it.hasNext()) {
                    WeakReference weakReference = (WeakReference) it.next();
                    if (weakReference != null) {
                        aVar2.a(weakReference);
                    }
                }
                if (g.a()) {
                    g.b("QRComicTaskDispatcherPool", g.d, "addExecute QRComicOfflineTask:" + aVar.i() + ",queueSeq=" + aVar.j() + ",index=" + aVar.k() + ",in flight map addListener");
                }
            } else if (getQueue().contains(aVar)) {
                Runnable a = a(aVar);
                if (a != null) {
                    it = a.b.iterator();
                    while (it.hasNext()) {
                        WeakReference weakReference2 = (WeakReference) it.next();
                        if (weakReference2 != null) {
                            aVar.b.add(weakReference2);
                        }
                    }
                    super.remove(a);
                    if (g.a()) {
                        g.b("QRComicTaskDispatcherPool", g.d, "addExecute QRComicOfflineTask:" + aVar.i() + ",queueSeq=" + aVar.j() + ",index=" + aVar.k() + ",in queue addListener");
                    }
                }
                super.execute(aVar);
            } else {
                super.execute(aVar);
                if (g.a()) {
                    g.b("QRComicTaskDispatcherPool", g.d, "addExecute QRComicOfflineTask:" + aVar.i() + ",queueSeq=" + aVar.j() + ",index=" + aVar.k());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void c(a aVar) {
        try {
            String i = aVar.i();
            Iterator it;
            if (this.b.containsKey(i)) {
                a aVar2 = (a) this.b.get(i);
                it = aVar.b.iterator();
                while (it.hasNext()) {
                    WeakReference weakReference = (WeakReference) it.next();
                    if (weakReference != null) {
                        aVar2.a(weakReference);
                    }
                }
                if (g.a()) {
                    g.b("QRComicTaskDispatcherPool", g.d, "addExecute VipComicVideoTask:" + aVar.i() + ",queueSeq=" + aVar.j() + ",index=" + aVar.k() + ",in flight map addListener");
                }
            } else if (getQueue().contains(aVar)) {
                Runnable a = a(aVar);
                if (a != null) {
                    it = a.b.iterator();
                    while (it.hasNext()) {
                        WeakReference weakReference2 = (WeakReference) it.next();
                        if (weakReference2 != null) {
                            aVar.b.add(weakReference2);
                        }
                    }
                    super.remove(a);
                    if (g.a()) {
                        g.b("QRComicTaskDispatcherPool", g.d, "addExecute VipComicVideoTask:" + aVar.i() + ",queueSeq=" + aVar.j() + ",index=" + aVar.k() + ",in queue addListener");
                    }
                }
                super.execute(aVar);
            } else {
                super.execute(aVar);
                if (g.a()) {
                    g.b("QRComicTaskDispatcherPool", g.d, "addExecute VipComicVideoTask:" + aVar.i() + ",queueSeq=" + aVar.j() + ",index=" + aVar.k());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public a a(a aVar) {
        try {
            CharSequence i = aVar.i();
            if (!TextUtils.isEmpty(i) && this.c.containsKey(i)) {
                for (a aVar2 : getQueue()) {
                    if (aVar2 != null && aVar2.equals(aVar)) {
                        return aVar2;
                    }
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean a(String str) {
        try {
            if (!TextUtils.isEmpty(str) && this.c.containsKey(str)) {
                remove((a) this.c.get(str));
                this.c.remove(str);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void b(String str) {
        try {
            if (this.b.containsKey(str)) {
                a aVar = (a) this.b.get(str);
                if (aVar != null) {
                    remove(aVar);
                    aVar.l();
                    this.b.remove(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a() {
        try {
            i iVar;
            if (!(this.c == null || this.c.isEmpty())) {
                for (a aVar : this.c.values()) {
                    if (aVar instanceof i) {
                        iVar = (i) aVar;
                        remove(iVar);
                        this.c.remove(iVar.i());
                    }
                }
            }
            if (this.b != null && !this.b.isEmpty()) {
                for (a aVar2 : this.b.values()) {
                    if (aVar2 instanceof i) {
                        iVar = (i) aVar2;
                        remove(iVar);
                        this.b.remove(iVar.i());
                        iVar.l();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void beforeExecute(Thread thread, Runnable runnable) {
        try {
            if (runnable instanceof a) {
                a aVar = (a) runnable;
                if (aVar.a == 300) {
                    this.f.decrementAndGet();
                }
                this.b.put(aVar.i(), aVar);
                if (g.a()) {
                    g.b("QRComicTaskDispatcherPool", g.d, "beforeExecute QRComicAbstractTask:" + aVar.i() + ",queueSeq=" + aVar.j() + ",index=" + aVar.k());
                }
            }
            super.beforeExecute(thread, runnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void afterExecute(Runnable runnable, Throwable th) {
        try {
            if (runnable instanceof a) {
                a aVar = (a) runnable;
                String i = aVar.i();
                this.b.remove(i);
                this.c.remove(i);
                if (g.a()) {
                    g.b("QRComicTaskDispatcherPool", g.d, "afterExecute QRComicAbstractTask key=" + aVar.i() + ",queueSeq=" + aVar.j() + ",index=" + aVar.k());
                }
            }
            super.afterExecute(runnable, th);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
