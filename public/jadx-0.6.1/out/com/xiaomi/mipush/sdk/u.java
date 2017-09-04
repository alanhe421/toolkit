package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.b.a.a;
import com.xiaomi.channel.commonutils.android.b;
import com.xiaomi.push.service.ar;
import com.xiaomi.push.service.au;
import com.xiaomi.xmpush.thrift.k;
import com.xiaomi.xmpush.thrift.m;
import java.util.ArrayList;
import java.util.Iterator;

public class u {
    private final int a = -1;
    private final double b = 0.0d;
    private a c;
    private Context d;

    public u(Context context) {
        this.d = context;
        a();
    }

    private void a() {
        this.c = new a(this.d);
    }

    public static boolean a(Context context) {
        return !context.getSharedPreferences("mipush_extra", 0).getBoolean("geo_need_refresh", true) ? false : au.a(context);
    }

    public static void b(Context context) {
        ArrayList a = ar.a(context).a();
        if (a != null) {
            u uVar = new u(context);
            Iterator it = a.iterator();
            while (it.hasNext()) {
                k kVar = (k) it.next();
                if (b.g(context, kVar.g())) {
                    uVar.a(kVar);
                }
            }
            context.getSharedPreferences("mipush_extra", 0).edit().putBoolean("geo_need_refresh", false).commit();
        }
    }

    public void a(String str) {
        this.c.a(this.d, "com.xiaomi.xmsf", str);
    }

    public boolean a(k kVar) {
        if (kVar == null) {
            return false;
        }
        if (kVar.m() != null && kVar.o() > 0.0d) {
            m m = kVar.m();
            this.c.a(this.d, m.c(), m.a(), (float) kVar.o(), -1, "com.xiaomi.xmsf", kVar.a(), kVar.s().name());
        }
        return true;
    }
}
