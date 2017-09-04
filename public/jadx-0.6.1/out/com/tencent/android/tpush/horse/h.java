package com.tencent.android.tpush.horse;

import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.horse.data.OptStrategyList;
import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.d.f;
import com.tencent.android.tpush.service.m;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ProGuard */
class h implements Runnable {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public void run() {
        synchronized (this) {
            if (XGPushConfig.enableDebug) {
                a.c(Constants.HorseLogTag, "Action ->  createOptimalSocketChannel run");
            }
            if (q.i().b() || f.i().b()) {
                a.c(Constants.HorseLogTag, ">> horse task running");
            } else {
                String str = "";
                try {
                    str = f.h(m.e());
                    OptStrategyList optStrategyList = CacheManager.getOptStrategyList(m.e(), str);
                    StrategyItem e = optStrategyList.e();
                    if (e.d() == 1 || e == null || e.a(optStrategyList.g())) {
                        g.a(this.a, str);
                        return;
                    }
                    g.a(this.a, System.currentTimeMillis());
                    if (e.d() == 0) {
                        if (XGPushConfig.enableDebug) {
                            a.c(Constants.HorseLogTag, "Using the optStrategyItem" + e.toString());
                        }
                        g.a(this.a, true);
                        List arrayList = new ArrayList();
                        e.a(0);
                        arrayList.add(e);
                        q.i().a(g.a(this.a));
                        q.i().a(arrayList);
                        q.i().g();
                    } else {
                        if (XGPushConfig.enableDebug) {
                            a.d(Constants.HorseLogTag, "Using Http chanel http:" + e.toString());
                        }
                        n nVar = new n();
                        nVar.a(e);
                        if (nVar.a().isConnected() && g.b(this.a) != null) {
                            g.b(this.a).a(nVar.a(), e);
                            return;
                        }
                    }
                } catch (Throwable e2) {
                    a.c(Constants.HorseLogTag, "createOptimalSocketChannel error", e2);
                    g.a(this.a, str);
                } catch (Throwable e22) {
                    a.c(Constants.HorseLogTag, "createOptimalSocketChannel error", e22);
                    this.a.b();
                } catch (Throwable e222) {
                    a.c(Constants.HorseLogTag, "createOptimalSocketChannel error", e222);
                    this.a.b();
                }
            }
        }
    }
}
