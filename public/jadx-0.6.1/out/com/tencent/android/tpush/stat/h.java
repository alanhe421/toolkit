package com.tencent.android.tpush.stat;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.HandlerThread;
import com.tencent.android.tpush.stat.a.e;
import com.tencent.android.tpush.stat.a.f;
import com.tencent.android.tpush.stat.a.g;
import com.tencent.android.tpush.stat.event.b;
import com.tencent.android.tpush.stat.event.d;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class h {
    static volatile long a = 0;
    private static volatile Handler b = null;
    private static volatile int c = 0;
    private static f d = e.b();
    private static UncaughtExceptionHandler e = null;
    private static Context f = null;
    private static String g = null;
    private static volatile SharedPreferences h = null;

    public static Context a(Context context) {
        return context != null ? context : f;
    }

    public static void b(Context context) {
        if (context != null) {
            f = context.getApplicationContext();
        }
    }

    static boolean c(Context context) {
        long a = g.a(context, c.c, 0);
        long a2 = e.a("2.0.6");
        boolean z = true;
        if (a2 <= a) {
            d.e("MTA is disable for current version:" + a2 + ",wakeup version:" + a);
            z = false;
        }
        c.a(z);
        return z;
    }

    static boolean a(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    static synchronized void d(Context context) {
        synchronized (h.class) {
            if (context != null) {
                if (b == null && c(context)) {
                    Context applicationContext = context.getApplicationContext();
                    f = applicationContext;
                    HandlerThread handlerThread = new HandlerThread("XgStat");
                    handlerThread.start();
                    b = new Handler(handlerThread.getLooper());
                    b.post(new i(applicationContext));
                }
            }
        }
    }

    public static Handler e(Context context) {
        if (b == null) {
            synchronized (h.class) {
                if (b == null) {
                    try {
                        d(context);
                    } catch (Throwable th) {
                        d.a(th);
                        c.a(false);
                    }
                }
            }
        }
        return b;
    }

    static JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (c.b.d != 0) {
                jSONObject2.put("v", c.b.d);
            }
            jSONObject.put(Integer.toString(c.b.a), jSONObject2);
            jSONObject2 = new JSONObject();
            if (c.a.d != 0) {
                jSONObject2.put("v", c.a.d);
            }
            jSONObject.put(Integer.toString(c.a.a), jSONObject2);
        } catch (Throwable e) {
            d.b(e);
        }
        return jSONObject;
    }

    static void a(Context context, long j) {
        a(new com.tencent.android.tpush.stat.event.e(context, c, a(), j));
    }

    static int b(Context context, long j) {
        Object obj = 1;
        long currentTimeMillis = System.currentTimeMillis();
        if (a == 0) {
            a = g.a(f, "_INTER_MTA_NEXT_DAY", 0);
        }
        if (c != 0 && currentTimeMillis < a) {
            obj = null;
        }
        if (obj != null) {
            c = e.a();
            a = e.c();
            g.b(f, "_INTER_MTA_NEXT_DAY", a);
            a(context, j);
        }
        return c;
    }

    public static void a(Context context, String str, Properties properties, long j, long j2) {
        if (c.c()) {
            Context a = a(context);
            if (a == null) {
                d.e("The Context of StatService.trackCustomEvent() can not be null!");
            } else if (a(str)) {
                d.e("The event_id of StatService.trackCustomEvent() can not be null or empty.");
            } else {
                b bVar = new b(str, null, properties);
                if (e(a) != null) {
                    b.post(new l(a, j, bVar, j2));
                }
            }
        }
    }

    public static void a(Context context, ArrayList arrayList) {
        if (c.c()) {
            Context a = a(context);
            if (a == null) {
                d.e("The Context of StatService.trackCustomEvent() can not be null!");
            } else if (arrayList == null || arrayList.size() == 0) {
                d.e("The reportList of StatService.trackCustomEvent() can not be null or empty.");
            } else if (e(a) != null) {
                b.post(new m(arrayList, a));
            }
        }
    }

    public static void b(Context context, ArrayList arrayList) {
        if (c.c()) {
            Context a = a(context);
            if (a == null) {
                d.e("The Context of StatService.trackCustomEvent() can not be null!");
            } else if (arrayList == null || arrayList.size() == 0) {
                d.e("The reportList of StatService.trackCustomEvent() can not be null or empty.");
            } else if (e(a) != null) {
                b.post(new n(arrayList, a));
            }
        }
    }

    public static void c(Context context, ArrayList arrayList) {
        if (c.c()) {
            Context a = a(context);
            if (a == null) {
                d.e("The Context of StatService.trackCustomEvent() can not be null!");
            } else if (arrayList == null || arrayList.size() == 0) {
                d.e("The reportList of StatService.trackCustomEvent() can not be null or empty.");
            } else if (e(a) != null) {
                b.post(new o(arrayList, a));
            }
        }
    }

    public static void a(Context context, int i) {
        if (c.c()) {
            if (c.b()) {
                d.b("commitEvents, maxNumber=" + i);
            }
            Context a = a(context);
            if (a == null) {
                d.e("The Context of StatService.commitEvents() can not be null!");
            } else if (i < -1 || i == 0) {
                d.e("The maxNumber of StatService.commitEvents() should be -1 or bigger than 0.");
            } else if (a.a(a).c() && e(a) != null) {
                b.post(new p());
            }
        }
    }

    static void a(List list) {
        d.h("sentEventList size:" + list.size());
        if (a.a(f).c()) {
            f.b(f).b(list, new q(list));
        } else {
            b(list);
        }
    }

    static void a(d dVar) {
        d.h("send Event:" + dVar);
        if (a.a(f).c()) {
            f.b(f).a(dVar, new j(dVar));
            return;
        }
        b(Arrays.asList(new d[]{dVar}));
    }

    static synchronized void b(List list) {
        synchronized (h.class) {
            if (list != null) {
                try {
                    if (h != null) {
                        d.h("store event size:" + list.size());
                        Editor edit = h.edit();
                        for (Object obj : list) {
                            edit.putLong(obj.toString(), System.currentTimeMillis());
                        }
                        edit.commit();
                    }
                } catch (Throwable e) {
                    d.b(e);
                }
            }
        }
        return;
    }

    static synchronized void c(List list) {
        synchronized (h.class) {
            if (list != null) {
                try {
                    if (h != null) {
                        d.h("delete event size:" + list.size());
                        Editor edit = h.edit();
                        for (Object obj : list) {
                            edit.remove(obj.toString());
                        }
                        edit.commit();
                    }
                } catch (Throwable e) {
                    d.b(e);
                }
            }
        }
        return;
    }

    static synchronized void d(List list) {
        synchronized (h.class) {
            if (list != null) {
                try {
                    if (h != null) {
                        Editor edit = h.edit();
                        for (Object obj : list) {
                            String obj2 = obj.toString();
                            short s = h.getInt(obj2, 1);
                            if (s <= (short) 0 || s > c.f()) {
                                edit.remove(obj2);
                            } else {
                                edit.putInt(obj2, s + 1);
                            }
                        }
                        edit.commit();
                    }
                } catch (Throwable e) {
                    d.b(e);
                }
            }
        }
        return;
    }

    static void b() {
        if (h != null) {
            Map all = h.getAll();
            if (all != null && all.size() > 0) {
                List arrayList = new ArrayList(10);
                for (Entry entry : all.entrySet()) {
                    if (arrayList.size() == 10) {
                        f.b(f).b(arrayList, new k(arrayList));
                    }
                    arrayList.clear();
                }
            }
        }
    }
}
