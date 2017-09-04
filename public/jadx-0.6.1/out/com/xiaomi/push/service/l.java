package com.xiaomi.push.service;

import android.util.Pair;
import com.xiaomi.channel.commonutils.c.b;
import com.xiaomi.xmpush.thrift.ad;
import com.xiaomi.xmpush.thrift.ae;
import com.xiaomi.xmpush.thrift.g;
import com.xiaomi.xmpush.thrift.h;
import com.xiaomi.xmpush.thrift.o;
import com.xiaomi.xmpush.thrift.q;
import java.util.ArrayList;
import java.util.List;

public class l {
    public static int a(k kVar, g gVar) {
        int i = 0;
        String a = a(gVar);
        switch (m.a[gVar.ordinal()]) {
            case 1:
                i = 1;
                break;
        }
        return kVar.a.getInt(a, i);
    }

    private static String a(g gVar) {
        return "oc_version_" + gVar.a();
    }

    private static List<Pair<Integer, Object>> a(List<q> list, boolean z) {
        if (b.a(list)) {
            return null;
        }
        List<Pair<Integer, Object>> arrayList = new ArrayList();
        for (q qVar : list) {
            int a = qVar.a();
            h a2 = h.a(qVar.c());
            if (a2 != null) {
                if (z && qVar.c) {
                    arrayList.add(new Pair(Integer.valueOf(a), null));
                } else {
                    Object obj;
                    Pair pair;
                    switch (m.b[a2.ordinal()]) {
                        case 1:
                            pair = new Pair(Integer.valueOf(a), Integer.valueOf(qVar.f()));
                            break;
                        case 2:
                            pair = new Pair(Integer.valueOf(a), Long.valueOf(qVar.h()));
                            break;
                        case 3:
                            pair = new Pair(Integer.valueOf(a), qVar.j());
                            break;
                        case 4:
                            pair = new Pair(Integer.valueOf(a), Boolean.valueOf(qVar.l()));
                            break;
                        default:
                            obj = null;
                            break;
                    }
                    arrayList.add(obj);
                }
            }
        }
        return arrayList;
    }

    public static void a(k kVar, ad adVar) {
        kVar.b(a(adVar.a(), true));
    }

    public static void a(k kVar, ae aeVar) {
        for (o oVar : aeVar.a()) {
            if (oVar.a() > a(kVar, oVar.d())) {
                a(kVar, oVar.d(), oVar.a());
                kVar.a(a(oVar.b, false));
            }
        }
    }

    public static void a(k kVar, g gVar, int i) {
        kVar.a.edit().putInt(a(gVar), i).commit();
    }
}
