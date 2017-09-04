package com.qq.reader.plugin.tts;

import com.qq.reader.plugin.tts.model.b;
import com.qq.reader.plugin.tts.model.d;
import com.tencent.android.tpush.common.Constants;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/* compiled from: SentenceQueue */
public class k implements f {
    private e a;
    private c b;
    private BlockingQueue<d> c = new ArrayBlockingQueue(Constants.ERRORCODE_UNKNOWN);

    public void a(c cVar) {
        this.b = cVar;
        this.b.a((f) this);
    }

    public void a(e eVar) {
        this.a = eVar;
    }

    public int c() {
        return this.b.a();
    }

    public int d() {
        return this.b.b();
    }

    public b b() {
        b bVar = new b();
        if (g() != 0 || this.a == null) {
            try {
                bVar.a((d) this.c.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            bVar.a(1);
        }
        return bVar;
    }

    public void a(d dVar) {
        try {
            if (dVar.a() == 0) {
                this.c.put(dVar);
            } else {
                this.c.put(dVar);
            }
            if (this.a != null && !this.a.isDataSatisfied() && f()) {
                this.a.prepared();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean f() {
        if (1 <= this.c.size()) {
            return true;
        }
        return false;
    }

    public void a() {
        this.c.clear();
        this.b = null;
        this.a = null;
    }

    public int g() {
        return this.c.size();
    }

    public void b(d dVar) {
        if (this.b != null) {
            this.b.a(dVar);
        }
    }

    public boolean e() {
        if (this.b != null) {
            return this.b.d();
        }
        return false;
    }
}
