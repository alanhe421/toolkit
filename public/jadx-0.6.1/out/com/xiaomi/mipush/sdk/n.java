package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.channel.commonutils.android.e;
import com.xiaomi.channel.commonutils.android.h;
import com.xiaomi.channel.commonutils.c.g;
import com.xiaomi.push.service.az;
import com.xiaomi.push.service.az.a;
import com.xiaomi.push.service.k;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.f;
import com.xiaomi.xmpush.thrift.p;
import java.util.HashMap;
import java.util.Map;

public class n implements a {
    public n(Context context) {
        az.a(context).a((a) this);
    }

    private void b(String str, Context context) {
        org.apache.thrift.a afVar = new af();
        afVar.c(p.ClientMIIDUpdate.T);
        afVar.b(q.a(context).c());
        afVar.a(c.a());
        Map hashMap = new HashMap();
        h.a(hashMap, "miid", str);
        afVar.a(hashMap);
        int b = e.b();
        if (b >= 0) {
            afVar.i().put("space_id", Integer.toString(b));
        }
        al.a(context).a(afVar, com.xiaomi.xmpush.thrift.a.Notification, true, null);
    }

    public void a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        long j = sharedPreferences.getLong("last_sync_miid_time", -1);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        int a = k.a(context).a(f.SyncMIIDFrequency.a(), 21600);
        if (j == -1) {
            sharedPreferences.edit().putLong("last_sync_miid_time", currentTimeMillis).commit();
        } else if (Math.abs(currentTimeMillis - j) > ((long) a)) {
            g.a(context).a(new o(context), a);
            sharedPreferences.edit().putLong("last_sync_miid_time", currentTimeMillis).commit();
        }
    }

    public void a(String str, Context context) {
        b(str, context);
    }
}
