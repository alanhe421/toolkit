package com.sijla.common;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import com.sijla.a.a;
import com.sijla.callback.QtCallBack;
import com.sijla.h.d;
import com.sijla.h.e;
import com.sijla.h.g;
import com.sijla.j.c;
import com.sijla.j.f;
import com.sijla.j.h;
import com.sijla.j.i;
import com.sijla.j.j;
import com.tencent.mid.api.MidEntity;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    public static String a;
    public static String b;
    private static String c = "notset";
    private static a d = null;
    private static QtCallBack e;
    private static c f;

    public static String a() {
        return c;
    }

    public static void a(String str) {
        c = str;
    }

    public static void a(final Context context, final String str, boolean z) {
        if (z) {
            try {
                f.c("appKey = " + com.sijla.j.b.p(context) + " try start growthService");
                com.sijla.c.c.a(new Runnable() {
                    public void run() {
                        try {
                            com.sijla.d.a.a(context);
                            b.a = "qd=" + i.b(context);
                            b.b = "dd=" + com.sijla.j.a.c.a(com.sijla.j.a.a.i(context));
                            new com.sijla.h.i(context, com.sijla.d.c.a).run();
                            b.a(context);
                            b.a(context, str);
                            if (b.b()) {
                                Log.i("qm", "ss");
                                b.c(context);
                                return;
                            }
                            f.a("TruthService Off");
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static boolean b() {
        f.a("TruthService " + "on");
        return true;
    }

    public static synchronized TreeMap<String, Integer> a(Context context) {
        TreeMap<String, Integer> treeMap;
        Object obj = null;
        synchronized (b.class) {
            try {
                treeMap = new TreeMap();
                String e = e(context);
                File file = new File(e);
                JSONArray jSONArray = new JSONArray();
                List arrayList = new ArrayList();
                if (file.exists()) {
                    JSONArray d = com.sijla.j.b.d(com.sijla.j.a.b.a(e));
                    for (int i = 0; i < d.length(); i++) {
                        JSONObject jSONObject = d.getJSONObject(i);
                        String string = jSONObject.getString("appid");
                        int i2 = jSONObject.getInt("sdkver");
                        if (string.equals(context.getPackageName())) {
                            obj = 1;
                            jSONObject.put("sdkver", 170425);
                        }
                        if (com.sijla.j.a.a.e(context, string) && !arrayList.contains(string)) {
                            jSONArray.put(jSONObject);
                            treeMap.put(string, Integer.valueOf(i2));
                            arrayList.add(string);
                        }
                    }
                }
                if (obj == null) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("sdkver", 170425);
                    jSONObject2.put("appid", context.getPackageName());
                    jSONArray.put(jSONObject2);
                    treeMap.put(context.getPackageName(), Integer.valueOf(170425));
                }
                com.sijla.j.a.b.a(com.sijla.j.b.a(jSONArray), e, false);
            } catch (JSONException e2) {
                e2.printStackTrace();
                treeMap = null;
            }
        }
        return treeMap;
    }

    public static String b(Context context) {
        String str = "";
        TreeMap a = a(context);
        List c = com.sijla.j.a.a.c();
        if (c == null || a == null) {
            return str;
        }
        TreeMap treeMap = new TreeMap();
        String str2 = str;
        for (int i = 0; i < c.size(); i++) {
            str2 = (String) c.get(i);
            if (a.containsKey(str2)) {
                treeMap.put(a.get(str2), str2);
            }
        }
        for (Integer intValue : treeMap.keySet()) {
            int intValue2 = intValue.intValue();
            str2 = (String) treeMap.get(Integer.valueOf(intValue2));
            f.a("app = " + str2 + " sdkver = " + intValue2);
        }
        return str2;
    }

    private static String e(Context context) {
        return com.sijla.j.b.b(context) + ".app/app";
    }

    public static void c(Context context) {
        try {
            Class x = com.sijla.j.a.a.x(context);
            if (x != null) {
                h.a(context, x);
                h.a(context, 30, x);
                context.startService(new Intent(context, x));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void f(Context context) {
        try {
            if (((Boolean) j.b(context, "isFirstRun", Boolean.valueOf(true))).booleanValue()) {
                b(context, com.sijla.d.c.a);
                j.a(context, "FirstRunTime", Long.valueOf(com.sijla.j.b.g()));
                j.a(context, "isFirstRun", Boolean.valueOf(false));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static List<a> d(Context context) {
        f(context);
        List<a> arrayList = new ArrayList();
        try {
            if (1 == com.sijla.d.c.a.optInt("apk", 1)) {
                arrayList.add(new com.sijla.h.b(context));
            }
            arrayList.add(new com.sijla.h.i(context, com.sijla.d.c.a));
            arrayList.add(new com.sijla.h.j(context, com.sijla.d.c.a));
            arrayList.add(new com.sijla.h.f(context, com.sijla.d.c.a));
            if (1 == com.sijla.d.c.a.optLong("location", 1) && com.sijla.j.b.e(context) && com.sijla.j.a.a.f(context, "android.permission.ACCESS_FINE_LOCATION") && com.sijla.j.b.a(context, "bdloctime", com.sijla.d.c.a.optLong("locitl", 3600))) {
                arrayList.add(new e(context));
            } else {
                try {
                    f.c("Not bd ");
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            String str;
            if (1 == com.sijla.d.c.a.optLong("location", 1) && com.sijla.j.b.g(context) && com.sijla.j.a.a.f(context, "android.permission.ACCESS_FINE_LOCATION") && com.sijla.j.b.a(context, "gdloctime", com.sijla.d.c.a.optLong("locitl", 3600))) {
                arrayList.add(new g(context));
                if (1 == com.sijla.d.c.a.optInt("session", 0)) {
                    str = Build.MANUFACTURER;
                    if (VERSION.SDK_INT >= 24) {
                        Log.d("qlog", "Build.VERSION.SDK_INT=" + VERSION.SDK_INT);
                    } else {
                        arrayList.add(new d(context));
                    }
                }
                return arrayList;
            }
            f.c("Not gd ");
            if (1 == com.sijla.d.c.a.optInt("session", 0)) {
                str = Build.MANUFACTURER;
                if (VERSION.SDK_INT >= 24) {
                    arrayList.add(new d(context));
                } else {
                    Log.d("qlog", "Build.VERSION.SDK_INT=" + VERSION.SDK_INT);
                }
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(final Context context, final String str) {
        com.sijla.c.c.a(new Runnable() {
            public void run() {
                f.a("QMHelper.growth");
                if (!com.sijla.j.b.a(str)) {
                    j.a(context, "QTChannel", str);
                }
                if (com.sijla.j.b.a((String) j.b(context, "firstChannel", ""))) {
                    j.a(context, "firstChannel", str);
                }
                com.sijla.i.c.a().a(context, com.sijla.d.c.a, b.e);
                com.sijla.i.c.a().a(context, com.sijla.d.c.a);
            }
        });
    }

    private static void b(final Context context, final JSONObject jSONObject) {
        try {
            com.sijla.c.c.a(new Runnable() {
                public void run() {
                    b.c(context, jSONObject);
                    com.sijla.j.b.k(context);
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void c(Context context, JSONObject jSONObject) {
        try {
            f.a("post qiddid ....");
            JSONObject jSONObject2 = new JSONObject();
            String[] m = com.sijla.j.b.m();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(MidEntity.TAG_TIMESTAMPS, m[1]);
            jSONObject3.put("time", com.sijla.j.b.a());
            jSONObject3.put("qid", i.b(context));
            jSONObject3.put(MidEntity.TAG_IMEI, com.sijla.j.a.a.i(context));
            jSONObject3.put("mode", Build.MODEL);
            jSONObject3.put("appkey", com.sijla.j.b.p(context));
            jSONObject3.put("sdkver", 170425);
            jSONObject2.put("ln", "qidimei");
            jSONObject2.put("s1", m[1]);
            jSONObject2.put("s2", com.sijla.f.b.a(m[0], jSONObject3.toString()));
            com.sijla.j.g.a(jSONObject.optString("sdk_init_url", "http://www.qmlog.cn/n/init/"), jSONObject2, new com.sijla.j.g.a() {
                public void a(String str, JSONObject jSONObject) {
                    f.a("post qiddid success:" + jSONObject);
                }

                public void a(String str) {
                }
            }, true);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(QtCallBack qtCallBack) {
        e = qtCallBack;
    }

    public static void b(Context context, String str) {
        if (context != null) {
            com.sijla.j.a.a.g(context, str);
        }
    }

    public static String c() {
        return HBS.e + "";
    }

    public static String d() {
        return (com.sijla.j.b.g() - HBS.a) + "";
    }

    public static void a(c cVar) {
        if (cVar == null) {
            f = new c();
        } else {
            f = cVar;
        }
    }

    public static c e() {
        if (f == null) {
            f = new c();
        }
        return f;
    }
}
