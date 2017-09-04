package com.xiaomi.push.service.receivers;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.d.d;
import com.xiaomi.mipush.sdk.ag;
import com.xiaomi.mipush.sdk.al;
import com.xiaomi.mipush.sdk.q;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NetworkStatusReceiver extends BroadcastReceiver {
    private static int a = 1;
    private static int b = 1;
    private static int c = 2;
    private static BlockingQueue<Runnable> d = new LinkedBlockingQueue();
    private static ThreadPoolExecutor e = new ThreadPoolExecutor(a, b, (long) c, TimeUnit.SECONDS, d);
    private static boolean f = false;
    private boolean g;

    public NetworkStatusReceiver() {
        this.g = false;
        this.g = true;
    }

    public NetworkStatusReceiver(Object obj) {
        this.g = false;
        f = true;
    }

    private void a(Context context) {
        if (!(al.a(context).c() || !q.a(context).i() || q.a(context).m())) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(context, "com.xiaomi.push.service.XMPushService"));
                intent.setAction("com.xiaomi.push.network_status_changed");
                context.startService(intent);
            } catch (Throwable e) {
                c.a(e);
            }
        }
        if (d.d(context) && al.a(context).g()) {
            al.a(context).d();
        }
        if (d.d(context)) {
            if ("disable_syncing".equals(ag.a(context).a())) {
                com.xiaomi.mipush.sdk.c.h(context);
            }
            if ("enable_syncing".equals(ag.a(context).a())) {
                com.xiaomi.mipush.sdk.c.i(context);
            }
        }
    }

    public static boolean a() {
        return f;
    }

    public void onReceive(Context context, Intent intent) {
        if (!this.g) {
            e.execute(new a(this, context));
        }
    }
}
