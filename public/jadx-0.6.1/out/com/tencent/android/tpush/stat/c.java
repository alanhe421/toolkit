package com.tencent.android.tpush.stat;

import android.content.Context;
import com.tencent.android.tpush.service.a.a;
import com.tencent.android.tpush.stat.a.e;
import com.tencent.android.tpush.stat.a.f;
import com.tencent.android.tpush.stat.a.g;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class c {
    static d a = new d(2);
    static d b = new d(1);
    static String c = "__HIBERNATE__";
    static volatile String d = "pingma.qq.com:80";
    private static f e = e.b();
    private static StatReportStrategy f = StatReportStrategy.APP_LAUNCH;
    private static boolean g = false;
    private static boolean h = true;
    private static volatile String i = "http://pingma.qq.com:80/mstat/report";
    private static boolean j = false;
    private static short k = (short) 6;

    public static StatReportStrategy a() {
        return f;
    }

    public static void a(StatReportStrategy statReportStrategy) {
        f = statReportStrategy;
        if (b()) {
            e.h("Change to statSendStrategy: " + statReportStrategy);
        }
    }

    public static boolean b() {
        return g;
    }

    public static boolean c() {
        return h && a.a(h.a(null)).B == 1;
    }

    public static void a(boolean z) {
        h = z;
        if (!z) {
            e.c("!!!!!!MTA StatService has been disabled!!!!!!");
        }
    }

    static void a(Context context, JSONObject jSONObject) {
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (str.equalsIgnoreCase(Integer.toString(b.a))) {
                    a(context, b, jSONObject.getJSONObject(str));
                } else if (str.equalsIgnoreCase(Integer.toString(a.a))) {
                    a(context, a, jSONObject.getJSONObject(str));
                }
            }
        } catch (Throwable e) {
            e.b(e);
        }
    }

    static void a(Context context, d dVar, JSONObject jSONObject) {
        Object obj = null;
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                Object obj2;
                String str = (String) keys.next();
                if (str.equalsIgnoreCase("v")) {
                    int i = jSONObject.getInt(str);
                    if (dVar.d != i) {
                        obj2 = 1;
                    } else {
                        obj2 = obj;
                    }
                    dVar.d = i;
                } else if (str.equalsIgnoreCase("c")) {
                    str = jSONObject.getString("c");
                    if (str.length() > 0) {
                        dVar.b = new JSONObject(str);
                    }
                    obj2 = obj;
                } else {
                    if (str.equalsIgnoreCase("m")) {
                        dVar.c = jSONObject.getString("m");
                    }
                    obj2 = obj;
                }
                obj = obj2;
            }
            if (obj == 1 && dVar.a == b.a) {
                a(dVar.b);
                b(dVar.b);
            }
            a(context, dVar);
        } catch (Throwable e) {
            e.b(e);
        } catch (Throwable e2) {
            e.b(e2);
        }
    }

    static void a(JSONObject jSONObject) {
        try {
            StatReportStrategy a = StatReportStrategy.a(jSONObject.getInt("rs"));
            if (a != null) {
                a(a);
            }
        } catch (JSONException e) {
            if (b()) {
                e.b((Object) "rs not found.");
            }
        }
    }

    static void a(Context context, d dVar) {
        if (dVar.a == b.a) {
            b = dVar;
            a(b.b);
        } else if (dVar.a == a.a) {
            a = dVar;
        }
    }

    static void b(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.length() != 0) {
            try {
                String string = jSONObject.getString(c);
                if (b()) {
                    e.h("hibernateVer:" + string + ", current version:" + "2.0.6");
                }
                long a = e.a(string);
                if (e.a("2.0.6") <= a) {
                    a(a);
                }
            } catch (JSONException e) {
                e.h("__HIBERNATE__ not found.");
            }
        }
    }

    static void a(long j) {
        g.b(f.a(), c, j);
        a(false);
        e.c("MTA is disable for current SDK version");
    }

    public static String d() {
        return i;
    }

    public static boolean e() {
        return j;
    }

    public static void b(boolean z) {
        j = z;
    }

    public static short f() {
        return k;
    }
}
