package com.qq.reader.plugin.tts;

import com.qq.reader.plugin.tts.model.a;
import com.qq.reader.plugin.tts.model.d;
import com.qq.reader.readengine.kernel.b;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: SentenceProducer */
public class j extends c {
    boolean a = false;
    private BlockingQueue<a> b = new LinkedBlockingQueue();
    private i c;
    private f d;
    private b e;
    private int f = 0;
    private Object g = new Object();

    public void a(f fVar) {
        this.d = fVar;
    }

    public void c() {
        this.b.clear();
        interrupt();
        this.c = null;
    }

    public void a(b bVar) {
        this.e = bVar;
    }

    public void run() {
        try {
            a aVar = (a) this.b.take();
            if (aVar != null) {
                do {
                    this.a = true;
                    this.c.a(aVar, this.d);
                    this.a = false;
                    aVar = (a) this.b.take();
                } while (aVar != null);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void a(a aVar) {
        try {
            synchronized (this.g) {
                if (aVar == null) {
                    return;
                }
                this.f++;
                aVar.a(this.f);
                this.b.put(aVar);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void b(int i) {
        d dVar = new d();
        dVar.a(i);
        this.d.a(dVar);
    }

    public void a(int i) {
        switch (i) {
            case 1:
                this.c = new b();
                return;
            case 2:
                this.c = new t();
                return;
            case 4:
                this.c = new s();
                return;
            default:
                this.c = new r();
                return;
        }
    }

    public int a() {
        this.e.q();
        return 0;
    }

    public int b() {
        try {
            this.e.r();
        } catch (Exception e) {
        }
        return 0;
    }

    public void a(d dVar) {
        this.e.a(dVar);
    }

    public boolean d() {
        try {
            if (this.e.d().t().getBookNetId() == 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
}
