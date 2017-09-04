package com.xiaomi.mipush.sdk;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.e;
import com.xiaomi.channel.commonutils.g.d;
import com.xiaomi.xmpush.thrift.af;
import java.util.HashMap;
import org.apache.thrift.a;

final class z implements Runnable {
    z() {
    }

    public void run() {
        if (e.c(c.a) != null) {
            a afVar = new af();
            afVar.b(q.a(c.a).c());
            afVar.c("client_info_update");
            afVar.a(c.a());
            afVar.a(new HashMap());
            Object a = d.a(e.c(c.a));
            Object e = e.e(c.a);
            if (!TextUtils.isEmpty(e)) {
                a = a + "," + e;
            }
            afVar.i().put("imei_md5", a);
            int b = e.b();
            if (b >= 0) {
                afVar.i().put("space_id", Integer.toString(b));
            }
            al.a(c.a).a(afVar, com.xiaomi.xmpush.thrift.a.Notification, false, null);
        }
    }
}
