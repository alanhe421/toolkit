package com.sijla.h;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.sijla.c.c;
import com.sijla.j.a.a;
import com.sijla.j.b;
import com.sijla.j.f;
import com.sijla.j.g;
import com.sijla.j.i;
import com.tencent.mid.api.MidEntity;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

public class h {
    public static void a(final Context context) {
        c.a(new Runnable() {
            public void run() {
                h.b(context);
            }
        }, (long) (com.sijla.d.c.a.optInt("lgdelay", 5) * 1000));
    }

    public static void b(Context context) {
        if (b.a(context, "lgitl", (long) com.sijla.d.c.a.optInt("lgitl", 300))) {
            try {
                if (com.sijla.d.c.d == null || com.sijla.d.c.d.isEmpty()) {
                    f.d("SDKConfig.lgConfigList Empyt");
                    return;
                }
                for (int i = 0; i < com.sijla.d.c.d.size(); i++) {
                    a(context, (JSONObject) com.sijla.d.c.d.get(i));
                }
            } catch (Throwable th) {
                th.printStackTrace();
                f.b(th.toString());
            }
        }
    }

    public static void a(final Context context, final JSONObject jSONObject) {
        c.a(new Runnable() {
            public void run() {
                try {
                    String optString = jSONObject.optString("pkg");
                    String optString2 = jSONObject.optString("action");
                    String optString3 = jSONObject.optString("clsname");
                    String optString4 = jSONObject.optString("extrakey", "qtsrc");
                    String optString5 = jSONObject.optString("extravalue", "qt");
                    String optString6 = jSONObject.optString("appver");
                    String optString7 = jSONObject.optString("channel");
                    if (b.a(optString)) {
                        f.a("pkg isEmpty");
                    } else if (!b.a(optString6) && !optString6.equals(a.f(context))) {
                    } else {
                        if (!b.a(optString7) && !optString7.equals(b.m(context))) {
                            return;
                        }
                        if (a.e(context, optString)) {
                            Intent intent = b.a(optString2) ? new Intent() : new Intent(optString2);
                            ComponentName componentName = new ComponentName(optString, optString3);
                            f.c("start  pkg = " + optString + " cname = " + optString3);
                            intent.setComponent(componentName);
                            intent.putExtra(optString4, optString5);
                            context.startService(intent);
                            h.a(context, com.sijla.d.c.a, componentName, h.a(context, optString, optString3) ? "1" : "0");
                            return;
                        }
                        f.a("pkg = " + optString + " cname = " + optString3 + " have not install ");
                    }
                } catch (Throwable th) {
                    f.b("error:" + jSONObject.toString());
                }
            }
        });
    }

    public static boolean a(Context context, String str, String str2) {
        try {
            for (RunningAppProcessInfo runningAppProcessInfo : a.g(context).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.contains(str + ":" + str2) && (!runningAppProcessInfo.processName.equals(str + ":QS") || !runningAppProcessInfo.processName.equals(str + ":ajmd"))) {
                    f.b("checkServiceRunning:" + runningAppProcessInfo.processName);
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            Throwable th2 = th;
            boolean z = false;
            th2.printStackTrace();
            return z;
        }
    }

    public static void a(Context context, JSONObject jSONObject, ComponentName componentName, String str) {
        try {
            String str2 = b.g() + "";
            String substring = com.sijla.j.a.c.a(str2).substring(0, 8);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("appkey", b.p(context));
            jSONObject2.put("quid", i.b(context));
            jSONObject2.put("did", a.i(context));
            jSONObject2.put("channel", b.m(context));
            jSONObject2.put("appid", componentName.getPackageName());
            jSONObject2.put("appver", a.a(componentName.getPackageName(), context));
            jSONObject2.put("sdkver", 170425);
            jSONObject2.put("mode", Build.MODEL);
            jSONObject2.put(MidEntity.TAG_TIMESTAMPS, str2);
            jSONObject2.put(SocialConstants.PARAM_SEND_MSG, str);
            f.a("lgprint:" + jSONObject2.toString());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("s1", str2);
            jSONObject3.put("appid", context.getPackageName());
            jSONObject3.put("s2", com.sijla.f.b.a(substring, jSONObject2.toString()));
            jSONObject3.put("ln", "lg");
            str2 = jSONObject.optString("lgdata", "http://www.qmlog.cn/n/mlog/");
            if (!b.a(str2)) {
                g.a(str2, jSONObject3, new g.a() {
                    public void a(String str, JSONObject jSONObject) {
                        f.a("upload lgdata(" + str + ")success res:" + jSONObject.toString());
                    }

                    public void a(String str) {
                        f.a("upload lgdata(" + str2 + ")error res:" + str);
                    }
                }, true);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            f.b("save lg error:" + th.getMessage());
        }
    }
}
