package com.qq.reader.liveshow.model.filter;

import com.qq.reader.liveshow.model.filter.queue.impl.MessageBlockingQueue;
import com.qq.reader.liveshow.utils.SxbLog;
import java.util.Random;

/* compiled from: MessagePool */
public class a {
    public a a;
    private Thread b;
    private com.qq.reader.liveshow.model.filter.queue.a c;
    private com.qq.reader.liveshow.model.filter.a.a d;
    private boolean e = false;
    private android.support.v4.util.a<Long, Boolean> f = null;
    private String g;
    private final Object h = new Object();

    /* compiled from: MessagePool */
    public interface a {
        void a(b bVar);
    }

    /* compiled from: MessagePool */
    public interface b {
        long a();

        void a(long j);
    }

    /* compiled from: MessagePool */
    private class c implements Runnable {
        final /* synthetic */ a a;
        private b b;

        private c(a aVar) {
            this.a = aVar;
            this.b = null;
        }

        public void run() {
            Thread.currentThread().setName(this.a.g + " Thread");
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    b bVar;
                    SxbLog.c("TestMessagePool", "pool try get task");
                    if (this.b != null) {
                        b bVar2 = this.b;
                        this.b = null;
                        bVar = bVar2;
                    } else {
                        bVar = this.a.c.get();
                    }
                    if (this.a.e) {
                        synchronized (this.a.h) {
                            long e = this.a.c();
                            if (e >= 0) {
                                try {
                                    this.a.f.put(Long.valueOf(e), Boolean.valueOf(false));
                                    bVar.a(e);
                                    SxbLog.c("TestMessagePool", this.a.g + "-----pool take entityKey = " + bVar.a());
                                } catch (Exception e2) {
                                    SxbLog.c("TestMessagePool", e2.toString());
                                }
                            } else {
                                SxbLog.c("TestMessagePool", this.a.g + "-----pool wait entityKey = " + bVar.a());
                                this.a.h.wait();
                                this.b = bVar;
                            }
                        }
                    }
                    if (!(this.a.a == null || bVar == null)) {
                        SxbLog.c("TestMessagePool", "pool mCallBack.onShow");
                        SxbLog.c("TestMessagePool", this.a.g + "==================show" + bVar.toString());
                        this.a.a.a(bVar);
                    }
                    this.a.d.a(this.a.b);
                } catch (Exception e22) {
                    SxbLog.c("TestMessagePool", "InnerQueue is interrupted for shutting down." + e22);
                    SxbLog.c("TestMessagePool", "InnerQueue thread is terminated.");
                    return;
                } catch (Throwable th) {
                    SxbLog.c("TestMessagePool", "InnerQueue thread is terminated.");
                }
            }
            SxbLog.c("TestMessagePool", "--------------isInterrupted--------------");
            SxbLog.c("TestMessagePool", "InnerQueue thread is terminated.");
        }
    }

    public a(String str) {
        if (str == null || str.length() == 0) {
            str = "Default";
        }
        this.g = str;
    }

    public a a(com.qq.reader.liveshow.model.filter.queue.a aVar) {
        this.c = aVar;
        return this;
    }

    public a a(com.qq.reader.liveshow.model.filter.a.a aVar) {
        this.d = aVar;
        return this;
    }

    public a a(int i) {
        if (this.f == null) {
            this.f = new android.support.v4.util.a(i);
            long abs = Math.abs(new Random((long) getClass().hashCode()).nextLong()) / 2;
            for (int i2 = 0; i2 < i; i2++) {
                this.f.put(Long.valueOf(abs), Boolean.valueOf(true));
                abs++;
            }
            this.e = true;
            SxbLog.c("TestMessagePool", "pool set channelCount =  " + i);
        }
        return this;
    }

    private synchronized void b() {
        if (this.c == null) {
            this.c = new MessageBlockingQueue();
        }
        if (this.d == null) {
            this.d = new com.qq.reader.liveshow.model.filter.a.a.a(200);
        }
    }

    public void a(a aVar) {
        b();
        this.a = aVar;
        if (this.b == null) {
            this.b = new Thread(new c());
            this.b.start();
            SxbLog.c("TestMessagePool", "pool register and start");
        }
    }

    public void a() {
        if (this.b != null) {
            this.b.interrupt();
            this.b = null;
        }
        if (this.c != null) {
            this.c.clear();
        }
        if (this.d != null) {
            this.d = null;
        }
        this.a = null;
        SxbLog.c("TestMessagePool", "pool unregister and clear");
    }

    public void a(b bVar) {
        if (this.b != null && bVar != null && this.e) {
            synchronized (this.h) {
                long a = bVar.a();
                if (!((Boolean) this.f.get(Long.valueOf(a))).booleanValue()) {
                    this.f.put(Long.valueOf(a), Boolean.valueOf(true));
                    SxbLog.c("TestMessagePool", "-----pool notify entityKey = " + a);
                    this.h.notify();
                }
            }
        }
    }

    private long c() {
        for (int i = 0; i < this.f.size(); i++) {
            if (((Boolean) this.f.c(i)).booleanValue()) {
                return ((Long) this.f.b(i)).longValue();
            }
        }
        return -1;
    }

    public void b(b bVar) {
        if (this.b != null && bVar != null) {
            SxbLog.c("TestMessagePool", this.g + "==================add" + bVar.toString());
            SxbLog.c("TestMessagePool", this.g + "-----pool add entityKey = " + bVar.a());
            this.c.add(bVar);
        }
    }

    public void c(b bVar) {
        if (this.b != null) {
            SxbLog.c("TestMessagePool", this.g + "==================addImmediate" + bVar.toString());
            SxbLog.c("TestMessagePool", this.g + "-----pool add entityKey = " + bVar.a());
            this.c.addToFirst(bVar);
        }
    }
}
