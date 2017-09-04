package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tencent.android.tpush.common.Constants;
import com.tencent.open.SocialConstants;
import com.xiaomi.channel.commonutils.android.e;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.c.a;
import com.xiaomi.channel.commonutils.d.b;
import com.xiaomi.channel.commonutils.g.d;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

public class bd {
    private static bc a;
    private static a b;

    public static synchronized bc a(Context context) {
        bc bcVar = null;
        synchronized (bd.class) {
            if (a != null) {
                bcVar = a;
            } else {
                SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_account", 0);
                Object string = sharedPreferences.getString("uuid", null);
                Object string2 = sharedPreferences.getString(Constants.FLAG_TOKEN, null);
                Object string3 = sharedPreferences.getString("security", null);
                String string4 = sharedPreferences.getString("app_id", null);
                String string5 = sharedPreferences.getString("app_token", null);
                String string6 = sharedPreferences.getString("package_name", null);
                Object string7 = sharedPreferences.getString("device_id", null);
                int i = sharedPreferences.getInt("env_type", 1);
                if (!TextUtils.isEmpty(string7) && string7.startsWith("a-")) {
                    string7 = e.h(context);
                    sharedPreferences.edit().putString("device_id", string7).commit();
                }
                if (!(TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3))) {
                    CharSequence h = e.h(context);
                    if ("com.xiaomi.xmsf".equals(context.getPackageName()) || TextUtils.isEmpty(h) || TextUtils.isEmpty(r8) || r8.equals(h)) {
                        a = new bc(string, string2, string3, string4, string5, string6, i);
                        bcVar = a;
                    } else {
                        c.d("erase the old account.");
                        b(context);
                    }
                }
            }
        }
        return bcVar;
    }

    public static synchronized bc a(Context context, String str, String str2, String str3) {
        bc bcVar = null;
        synchronized (bd.class) {
            PackageInfo packageInfo;
            Map treeMap = new TreeMap();
            treeMap.put("devid", e.a(context));
            String str4 = c(context) ? "1000271" : str2;
            String str5 = c(context) ? "420100086271" : str3;
            String str6 = c(context) ? "com.xiaomi.xmsf" : str;
            treeMap.put("appid", str4);
            treeMap.put("apptoken", str5);
            try {
                packageInfo = context.getPackageManager().getPackageInfo(str6, 16384);
            } catch (Throwable e) {
                c.a(e);
                packageInfo = null;
            }
            treeMap.put("appversion", packageInfo != null ? String.valueOf(packageInfo.versionCode) : "0");
            treeMap.put("sdkversion", Integer.toString(27));
            treeMap.put("packagename", str6);
            treeMap.put("model", Build.MODEL);
            Object a = d.a(e.c(context));
            Object e2 = e.e(context);
            if (!TextUtils.isEmpty(e2)) {
                a = a + "," + e2;
            }
            treeMap.put("imei_md5", a);
            treeMap.put("os", VERSION.RELEASE + "-" + VERSION.INCREMENTAL);
            int b = e.b();
            if (b >= 0) {
                treeMap.put("space_id", Integer.toString(b));
            }
            CharSequence a2 = d.a(e.j(context));
            if (!TextUtils.isEmpty(a2)) {
                treeMap.put("mac_address", a2);
            }
            treeMap.put("android_id", e.b(context));
            b a3 = com.xiaomi.channel.commonutils.d.d.a(context, a(), treeMap);
            String str7 = "";
            if (a3 != null) {
                str7 = a3.a();
            }
            if (!TextUtils.isEmpty(str7)) {
                JSONObject jSONObject = new JSONObject(str7);
                if (jSONObject.getInt("code") == 0) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                    bcVar = new bc(jSONObject2.getString("userId") + "@xiaomi.com/an" + d.a(6), jSONObject2.getString(Constants.FLAG_TOKEN), jSONObject2.getString("ssecurity"), str4, str5, str6, a.c());
                    a(context, bcVar);
                    a = bcVar;
                } else {
                    bg.a(context, jSONObject.getInt("code"), jSONObject.optString(SocialConstants.PARAM_COMMENT));
                    c.a(str7);
                }
            }
        }
        return bcVar;
    }

    public static String a() {
        if (a.b()) {
            return "http://" + com.xiaomi.smack.b.c + ":9085" + "/pass/register";
        }
        return "https://" + (a.a() ? "sandbox.xmpush.xiaomi.com" : "register.xmpush.xiaomi.com") + "/pass/register";
    }

    public static void a(Context context, bc bcVar) {
        Editor edit = context.getSharedPreferences("mipush_account", 0).edit();
        edit.putString("uuid", bcVar.a);
        edit.putString("security", bcVar.c);
        edit.putString(Constants.FLAG_TOKEN, bcVar.b);
        edit.putString("app_id", bcVar.d);
        edit.putString("package_name", bcVar.f);
        edit.putString("app_token", bcVar.e);
        edit.putString("device_id", e.h(context));
        edit.putInt("env_type", bcVar.g);
        edit.commit();
        b();
    }

    public static void b() {
        if (b != null) {
            b.a();
        }
    }

    public static void b(Context context) {
        context.getSharedPreferences("mipush_account", 0).edit().clear().commit();
        a = null;
        b();
    }

    private static boolean c(Context context) {
        return context.getPackageName().equals("com.xiaomi.xmsf");
    }
}
