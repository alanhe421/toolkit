package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.android.h;
import com.xiaomi.channel.commonutils.c.g.a;
import com.xiaomi.push.service.az;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.p;
import java.util.HashMap;
import java.util.Map;

public class o extends a {
    private Context a;

    public o(Context context) {
        this.a = context;
    }

    public int a() {
        return 13;
    }

    public void run() {
        org.apache.thrift.a afVar = new af(c.a(), false);
        q a = q.a(this.a);
        afVar.c(p.A.T);
        afVar.b(a.c());
        afVar.d(this.a.getPackageName());
        Map hashMap = new HashMap();
        h.a(hashMap, "miid", az.a(this.a).c());
        afVar.h = hashMap;
        al.a(this.a).a(afVar, com.xiaomi.xmpush.thrift.a.i, true, null);
    }
}
