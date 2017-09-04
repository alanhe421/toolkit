package com.qrcomic.a;

import android.os.HandlerThread;
import com.qrcomic.d.b;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/* compiled from: QRThreadManager */
public class j {
    private static j h;
    private BlockingDeque<d> a = new LinkedBlockingDeque();
    private c b = new c(4, 10, 300, TimeUnit.SECONDS, new PriorityBlockingQueue());
    private c c = new c(4, 10, 300, TimeUnit.SECONDS, new PriorityBlockingQueue());
    private Thread d;
    private final Object e = new Object();
    private b f;
    private HandlerThread g = new HandlerThread("thread-manager-background-handler");

    /* compiled from: QRThreadManager */
    private static class a extends d {
        private a() {
        }

        public void run() {
        }
    }

    private j() {
        this.g.start();
        this.f = new b(this.g.getLooper());
        this.d = new Thread(this, "ThreadQueue") {
            final /* synthetic */ j a;

            public void run() {
                while (!this.a.d.isInterrupted() && this.a.d.isAlive()) {
                    try {
                        d dVar = (d) this.a.a.take();
                        if (!this.a.d(dVar)) {
                            this.a.c(dVar);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        try {
                            this.a.b.awaitTermination(100000, TimeUnit.SECONDS);
                            this.a.g.quit();
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        };
        this.d.start();
    }

    private void c(d dVar) {
        d dVar2;
        if (dVar.g() == 2) {
            synchronized (this.e) {
                for (Runnable runnable : this.b.getQueue()) {
                    dVar2 = (d) runnable;
                    int e = dVar2.e();
                    if (e > 1) {
                        dVar2.a(e - 1);
                    }
                }
                this.b.execute(dVar);
            }
        } else if (dVar.g() != 0 && dVar.g() == 1) {
            for (Runnable runnable2 : this.c.getQueue()) {
                dVar2 = (d) runnable2;
                int e2 = dVar2.e();
                if (e2 > 1) {
                    dVar2.a(e2 - 1);
                }
            }
            this.c.execute(dVar);
        }
    }

    private boolean d(d dVar) {
        if (dVar == null) {
            return true;
        }
        for (Runnable runnable : this.b.getQueue()) {
            if ((runnable instanceof d) && runnable.equals(dVar)) {
                return true;
            }
        }
        return false;
    }

    public static j a() {
        if (h == null) {
            synchronized (j.class) {
                if (h == null) {
                    h = new j();
                }
            }
        }
        return h;
    }

    public synchronized void a(d dVar) {
        this.a.add(dVar);
        this.a.add(new a());
    }

    public static b b() {
        return a().f;
    }

    public void a(d dVar, int i, b bVar, boolean z) {
        dVar.a(i);
        dVar.a(bVar);
        this.a.add(dVar);
        this.a.add(new a());
    }

    public static void b(d dVar) {
        dVar.b(1);
        a().a.add(dVar);
        a().a.add(new a());
    }

    public void a(d dVar, b bVar) {
        dVar.a(bVar);
        dVar.a(4);
        this.a.add(dVar);
    }

    public void a(d dVar, b bVar, boolean z) {
        dVar.a(bVar);
        dVar.a(4);
        this.a.add(dVar);
    }
}
