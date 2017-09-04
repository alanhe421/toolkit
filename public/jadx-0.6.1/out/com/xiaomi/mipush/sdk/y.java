package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.service.module.c;
import com.xiaomi.push.service.module.d;
import com.xiaomi.push.service.module.e;

final class y implements Runnable {
    y() {
    }

    public void run() {
        c a = d.a(c.a).a(e.a);
        if (a != null) {
            try {
                a.a().loadClass("com.xiaomi.push.mpcd.MpcdPlugin").getMethod("main", new Class[]{Context.class}).invoke(null, new Object[]{c.a});
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.b.c.a("plugin load fail");
            }
        }
    }
}
