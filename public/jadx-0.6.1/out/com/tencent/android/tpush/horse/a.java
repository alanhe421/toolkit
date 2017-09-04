package com.tencent.android.tpush.horse;

import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.horse.data.StrategyItem;
import java.lang.Thread.State;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: ProGuard */
public abstract class a {
    private static final Object a = new Object();
    private LinkedBlockingQueue b = new LinkedBlockingQueue();
    private ConcurrentHashMap c = new ConcurrentHashMap();
    private b d;
    private AtomicInteger e = new AtomicInteger(0);
    private volatile boolean f = false;

    public abstract void e();

    public abstract void f();

    public void a() {
        this.e.set(0);
    }

    public boolean b() {
        return this.e.get() > 0;
    }

    public boolean c() {
        return this.f;
    }

    public LinkedBlockingQueue d() {
        return this.b;
    }

    public void g() {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.a.a.c("BaseTask", "startTask() with strategyItems size = " + this.b.size());
        }
        int i = 0;
        while (i < 2) {
            try {
                if (this.c.get(Integer.valueOf(i)) == null || ((c) this.c.get(Integer.valueOf(i))).getState() == State.TERMINATED) {
                    c cVar = new c(this, i);
                    this.c.put(Integer.valueOf(i), cVar);
                    cVar.start();
                    i++;
                } else {
                    if (!((c) this.c.get(Integer.valueOf(i))).isAlive()) {
                        ((c) this.c.get(Integer.valueOf(i))).start();
                    }
                    i++;
                }
            } catch (Throwable e) {
                this.c.remove(Integer.valueOf(i));
                com.tencent.android.tpush.a.a.c(Constants.HorseLogTag, "startTask", e);
            }
        }
    }

    synchronized void a(List list) {
        if (list != null) {
            if (1 <= list.size()) {
                this.b.clear();
                this.f = false;
                a();
                for (StrategyItem strategyItem : list) {
                    if (!this.b.contains(strategyItem)) {
                        this.b.add(strategyItem);
                        this.e.incrementAndGet();
                    }
                }
            }
        }
        if (!(this.d == null || b())) {
            this.d.a(null);
        }
    }

    public void a(int i) {
        try {
            if (!this.c.isEmpty()) {
                c cVar;
                for (Integer intValue : this.c.keySet()) {
                    int intValue2 = intValue.intValue();
                    if (intValue2 != i) {
                        cVar = (c) this.c.get(Integer.valueOf(intValue2));
                        if (!(cVar == null || cVar.a() == null)) {
                            cVar.a().c();
                        }
                    }
                }
                cVar = (c) this.c.remove(Integer.valueOf(i));
                if (cVar != null) {
                    cVar.interrupt();
                }
            }
        } catch (Throwable e) {
            com.tencent.android.tpush.a.a.c("BaseTask", "stopOtherHorse", e);
        }
    }

    public void a(b bVar) {
        this.d = bVar;
    }
}
