package com.tencent.android.tpush.horse;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.service.channel.protocol.TpnsRedirectReq;
import com.tencent.android.tpush.service.d.f;
import com.tencent.android.tpush.service.m;

/* compiled from: ProGuard */
class c extends Thread {
    protected o a = new d(this);
    final /* synthetic */ a b;
    private n c;
    private int d;
    private StrategyItem e;

    public c(a aVar, int i) {
        this.b = aVar;
        this.d = i;
    }

    public n a() {
        return this.c;
    }

    public void run() {
        while (this.b.b.size() > 0) {
            try {
                this.e = (StrategyItem) this.b.b.remove();
                JceStruct tpnsRedirectReq = new TpnsRedirectReq();
                tpnsRedirectReq.network = f.f(m.e());
                tpnsRedirectReq.op = f.g(m.e());
                this.c = new n();
                this.c.a(this.a);
                try {
                    a.c("HorseThread", " HorseThread:" + getClass().getSimpleName() + Thread.currentThread() + "current NetworkType:" + tpnsRedirectReq.network + ",strategyItem:" + this.e);
                    this.c.a(this.e);
                    this.c.a(tpnsRedirectReq);
                    this.c.b();
                } catch (Throwable th) {
                    a.c("HorseThread", "HorseThread error", th);
                }
            } catch (Throwable th2) {
                a.c("HorseThread", "Can not get strateItem from strategyItems>>", th2);
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    a.h(Constants.HorseLogTag, e.toString());
                }
            }
        }
    }
}
