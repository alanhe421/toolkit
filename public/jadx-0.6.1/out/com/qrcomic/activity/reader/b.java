package com.qrcomic.activity.reader;

import android.os.Process;
import com.qrcomic.a.h;
import com.qrcomic.activity.reader.a.a;
import com.qrcomic.e.c;
import com.qrcomic.entity.f;
import com.qrcomic.entity.k;
import com.qrcomic.util.g;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: QRComicSectionAutoPayingThread */
public class b extends Thread {
    private BlockingQueue<String> a;
    private final a b;
    private final a c;
    private final com.qrcomic.e.b d;
    private final h e;
    private volatile boolean f = false;
    private c g = new c(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void d(Object obj) {
            if (g.a()) {
                g.a("QRComicSectionAutoPayingThread", g.d, "自动购买成功。。。。 :" + this.e);
            }
            if (obj != null && (obj instanceof k) && this.a.b != null) {
                this.a.b.a((k) obj);
                List list = ((k) obj).d;
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        String str = (String) list.get(i);
                        synchronized (this) {
                            if (!this.a.a.contains(str)) {
                                try {
                                    this.a.a.remove(str);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }

        public void e(Object obj) {
            if (g.a()) {
                g.a("QRComicSectionAutoPayingThread", g.d, "自动购买失败。。。。 :" + this.e);
            }
            if (!(obj == null || !(obj instanceof com.qrcomic.e.b.a) || this.a.b == null)) {
                this.a.b.a((com.qrcomic.e.b.a) obj);
            }
            this.a.b();
        }
    };

    public b(a aVar, a aVar2) {
        this.b = aVar2;
        this.c = aVar;
        this.a = new LinkedBlockingQueue();
        this.e = com.qrcomic.manager.b.a().b();
        this.e.a(this.g, false);
        this.d = (com.qrcomic.e.b) this.e.b(1);
    }

    public void a() {
        this.f = true;
        this.e.b(this.g);
        interrupt();
    }

    public void a(String str) {
        if (g.a()) {
            g.a("QRComicSectionAutoPayingThread", g.d, "QRComicSectionAutoPayingThread addTask :" + str);
        }
        synchronized (this) {
            if (!this.a.contains(str)) {
                try {
                    this.a.put(str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void run() {
        Process.setThreadPriority(10);
        while (!this.f) {
            try {
                String str = (String) this.a.take();
                if (!this.c.y.contains(str)) {
                    b(str);
                }
            } catch (InterruptedException e) {
                if (this.f) {
                    break;
                }
            }
        }
        if (g.a()) {
            g.a("QRComicSectionAutoPayingThread", g.d, " 购买线程停了， 停了， 停了");
        }
    }

    private void b(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        this.d.a(this.c.n, arrayList, 2);
    }

    public void b() {
        for (String b : this.a) {
            f b2 = this.c.b(b);
            if (b2 != null) {
                b2.t = 3;
            }
        }
        this.a.clear();
    }
}
