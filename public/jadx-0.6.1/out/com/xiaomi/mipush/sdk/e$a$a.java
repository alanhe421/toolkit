package com.xiaomi.mipush.sdk;

import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.mipush.sdk.e.a;
import com.xiaomi.push.service.y;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.e;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class e$a$a {
    public final ArrayList<e> a = new ArrayList();
    final /* synthetic */ a b;
    private ScheduledThreadPoolExecutor c = new ScheduledThreadPoolExecutor(1);
    private ScheduledFuture<?> d;
    private final Runnable e = new ae(this);

    public e$a$a(a aVar) {
        this.b = aVar;
    }

    private void a() {
        if (this.d == null) {
            this.d = this.c.scheduleAtFixedRate(this.e, 1000, 1000, TimeUnit.MILLISECONDS);
        }
    }

    private void b() {
        e eVar = (e) this.a.remove(0);
        for (af afVar : y.a(Arrays.asList(new e[]{eVar}), a.a(this.b).getPackageName(), q.a(a.a(this.b)).c(), 30720)) {
            c.c("MiTinyDataClient Send item by PushServiceClient.sendMessage(XmActionNotification)." + eVar.toString());
            al.a(a.a(this.b)).a(afVar, com.xiaomi.xmpush.thrift.a.i, true, null);
        }
    }

    public void a(e eVar) {
        this.c.execute(new ad(this, eVar));
    }
}
