package com.sijla.d;

import android.content.Context;
import android.os.Build.VERSION;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.sijla.j.b;
import com.sijla.j.d;
import com.sijla.j.f;
import com.sijla.j.g;
import com.sijla.j.i;
import com.sijla.j.j;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    public static void a(Context context) {
        if (b.a(context, "ntycfg_check", (long) c.a.optInt("notifycfgitl", 1800))) {
            b(context);
        }
        try {
            String str = b.c(context) + "config/config";
            File file = new File(str);
            File file2 = new File(str + ".gz");
            if (file.exists() && file.isFile()) {
                long lastModified = file.lastModified();
                int optInt = c.a.optInt("cfgitl", 28800);
                lastModified = Math.abs((System.currentTimeMillis() / 1000) - (lastModified / 1000));
                f.a("config duration = " + lastModified + " interval = " + optInt);
                if (lastModified > ((long) optInt)) {
                    a(context, file2);
                }
            } else {
                j.a(context, "cfgver", "");
                a(context, file2);
            }
            b(context, file);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void b(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appid", context.getPackageName());
            jSONObject.put("appver", com.sijla.j.a.a.f(context));
            jSONObject.put("did", com.sijla.j.a.a.i(context));
            jSONObject.put("qid", i.b(context));
            jSONObject.put("sdkver", 170425);
            String optString = c.a.optString("notifycfgurl", "");
            if (!b.a(optString)) {
                g.a(optString, jSONObject, new 1(context), true);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(Context context, File file) {
        String optString = c.a.optString("cfgurl", "http://www.qmlog.cn/n/dpz/");
        if (b.a(optString)) {
            optString = "http://www.qmlog.cn/n/dpz/";
        }
        File a = new b(context, optString, file.getAbsolutePath()).a();
        if (a != null && a.exists() && a.isFile()) {
            try {
                if (d.a(a)) {
                    f.a("ungzip config success");
                } else {
                    f.d("ungzip config fail");
                }
            } catch (Exception e) {
                e.printStackTrace();
                f.d("ungzip config expection:" + e.getMessage());
            }
        }
    }

    public static void b(Context context, File file) {
        int i = 0;
        if (file == null || !file.exists() || file.isDirectory()) {
            f.d("miss config file use defalut configs or last configs");
            return;
        }
        try {
            String trim = com.sijla.j.a.b.c(file).trim();
            if (!b.a(trim)) {
                JSONObject c = b.c(trim);
                if (c != null) {
                    int i2;
                    c.h = c.getString(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION);
                    f.a("configver = " + c.h);
                    j.a(context, "cfgver", c.h);
                    JSONObject jSONObject = c.getJSONObject("base");
                    if (jSONObject != null) {
                        c.a = jSONObject;
                    }
                    JSONArray jSONArray = c.getJSONArray("lg");
                    if (jSONArray != null) {
                        c.d.clear();
                        for (i2 = 0; i2 < jSONArray.length(); i2++) {
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                            if (jSONObject2 != null) {
                                c.d.add(jSONObject2);
                                f.a("lgtaskConfig:" + jSONObject2.toString());
                            }
                        }
                    }
                    jSONArray = c.getJSONArray("lgapps");
                    if (jSONArray != null && jSONArray.length() > 0) {
                        c.f.clear();
                        for (i2 = 0; i2 < jSONArray.length(); i2++) {
                            String string = jSONArray.getString(i2);
                            c.f.add(string);
                            f.a("lgapp=" + string);
                        }
                    }
                    if (VERSION.SDK_INT < 20) {
                        jSONArray = c.getJSONArray("pages");
                        if (jSONArray != null && jSONArray.length() > 0) {
                            c.c.clear();
                            for (i2 = 0; i2 < jSONArray.length(); i2++) {
                                c.c.add(jSONArray.getString(i2));
                            }
                        }
                    }
                    JSONArray jSONArray2 = c.getJSONArray("apk");
                    if (jSONArray2 != null) {
                        c.g.clear();
                        while (i < jSONArray2.length()) {
                            c = jSONArray2.getJSONObject(i);
                            String string2 = c.getString("k");
                            String string3 = c.getString("v");
                            c.g.add(new String[]{string2, string3});
                            i++;
                        }
                    }
                }
                f.c("Config Load Success From File !!");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
