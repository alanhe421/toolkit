package com.sijla.i;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.sijla.bean.TruthInfo;
import com.sijla.callback.QtCallBack;
import com.sijla.d.c;
import com.sijla.j.a.a;
import com.sijla.j.f;
import com.sijla.j.g;
import com.sijla.j.i;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class b {
    public static String a = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "qt/data/");

    public static TruthInfo a(Context context, JSONObject jSONObject) {
        return a(context, jSONObject, "0");
    }

    public static TruthInfo a(Context context, JSONObject jSONObject, String str) {
        Object obj = 1;
        TruthInfo truthInfo = new TruthInfo();
        try {
            truthInfo.setAppkey(com.sijla.j.b.p(context));
            truthInfo.setStatus(str);
            String packageName = context.getPackageName();
            truthInfo.setAppid(packageName);
            truthInfo.setAppver(a.a(packageName, context));
            truthInfo.setChannel(com.sijla.j.b.m(context));
            truthInfo.setUuid(i.b(context));
            truthInfo.setQuid(i.d(context));
            truthInfo.setImei(a.i(context));
            truthInfo.setImsi(a.m(context));
            if (1 != jSONObject.optInt("phnum", 1)) {
                obj = null;
            }
            truthInfo.setPn(obj != null ? a.l(context) : "");
            truthInfo.setSimSerial(a.t(context));
            truthInfo.setCpuSerial(a.e());
            truthInfo.setCpuCore(a.h() + "");
            truthInfo.setWifimac(a.q(context));
            truthInfo.setRoserial(a.g());
            truthInfo.setBlumac(com.sijla.j.b.l(context));
            truthInfo.setBrand(Build.MANUFACTURER);
            truthInfo.setMode(Build.MANUFACTURER + " " + Build.MODEL);
            truthInfo.setRam(a.f());
            truthInfo.setRom(a.w(context));
            truthInfo.setOsver(VERSION.RELEASE);
            truthInfo.setScrSize(a.p(context));
            truthInfo.setResolution(a.o(context));
            truthInfo.setRoot(a.b() ? "1" : "0");
            truthInfo.setAndroidId(Secure.getString(context.getContentResolver(), "android_id"));
            truthInfo.setMno(a.n(context));
            truthInfo.setAddr(com.sijla.j.b.i(context));
            truthInfo.setCuid(i.e(context));
            truthInfo.setUid3(com.sijla.common.b.a());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return truthInfo;
    }

    public static String a(Context context, String str, String str2) {
        String str3 = "";
        try {
            if (!TextUtils.isEmpty(str)) {
                str3 = a + str2;
                byte[] a = a(str);
                if (a != null && a.length > 0) {
                    com.sijla.j.a.b.a(str3, a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str3;
    }

    private static byte[] a(String str) {
        return com.sijla.j.b.e(str);
    }

    public static void a(Context context, JSONObject jSONObject, TruthInfo truthInfo) {
        if (a.b(context)) {
            String str = "qt.csv." + System.currentTimeMillis() + ".txt";
            String truthInfo2 = truthInfo.toString();
            f.a("report:" + truthInfo2);
            a(context, truthInfo2, str);
            File file = new File(a);
            if (file != null && file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    Map hashMap = new HashMap();
                    for (int i = 0; i < listFiles.length; i++) {
                        hashMap.put(listFiles[i].getName(), listFiles[i]);
                    }
                    str = "?appkey=" + com.sijla.j.b.p(context) + "&uid=" + i.b(context) + "&sdk=" + 170425 + "&did=" + a.i(context) + "&type=3";
                    String optString = jSONObject.optString("urltruth", "http://www.qchannel01.cn/center/adj");
                    String optString2 = jSONObject.optString("urldata2");
                    JSONObject jSONObject2 = new JSONObject();
                    if (!(g.a(optString + str, jSONObject2, hashMap).b() || com.sijla.j.b.a(optString2))) {
                        g.a(optString2 + str, jSONObject2, hashMap);
                    }
                    com.sijla.j.a.b.a(file);
                }
            }
        }
    }

    public static void a(Context context, JSONObject jSONObject, QtCallBack qtCallBack) {
        a(context, jSONObject, qtCallBack, false);
    }

    public static void a(Context context, JSONObject jSONObject, QtCallBack qtCallBack, boolean z) {
        boolean z2 = true;
        if (jSONObject != null) {
            JSONArray jSONArray;
            JSONArray jSONArray2 = null;
            try {
                jSONArray2 = c.a.optJSONArray("growthurls");
                f.a("growthurl size1:" + jSONArray2.length());
                jSONArray = jSONArray2;
            } catch (Throwable th) {
                th.printStackTrace();
                jSONArray = jSONArray2;
            }
            if (jSONArray == null || jSONArray.length() == 0) {
                jSONArray = new JSONArray();
                jSONArray.put("http://www.qchannel01.cn/center/adj");
            }
            f.a("growthurl size2:" + jSONArray.length());
            String a = a(context, z);
            boolean z3 = false;
            for (int i = 0; i < jSONArray.length(); i++) {
                if (g.a(jSONArray.optString(i, "http://www.qchannel01.cn/center/adj") + a, jSONObject)) {
                    if (!z3) {
                        a(context, qtCallBack, true);
                    }
                    if (c.a.optInt("repeatReportGrowth", 0) == 0) {
                        f.a("repeatReportGrowth=0,break");
                        break;
                    } else {
                        f.a("repeatReportGrowth=1");
                        z3 = true;
                    }
                }
            }
            z2 = z3;
            if (!z2) {
                a(context, qtCallBack, false);
                b(context, jSONObject);
            }
        }
    }

    private static String a(Context context, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?ak=");
        stringBuilder.append(com.sijla.j.b.p(context));
        stringBuilder.append("&uid=");
        stringBuilder.append(i.a(context));
        stringBuilder.append("&offline=");
        stringBuilder.append(z ? "1" : "0");
        return stringBuilder.toString();
    }

    private static void a(Context context, QtCallBack qtCallBack, boolean z) {
        if (qtCallBack != null) {
            try {
                String a = a.a(context.getPackageName(), context);
                String m = com.sijla.j.b.m(context);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("qmuid", i.a(context));
                jSONObject.put("selfuid", com.sijla.common.b.a());
                jSONObject.put("appver", a);
                jSONObject.put("currentChannel", m);
                jSONObject.put("installChannel", com.sijla.j.b.n(context));
                jSONObject.put("uploadStatus", z ? "success" : "fail");
                qtCallBack.uploadCallBack(jSONObject);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static void b(Context context, JSONObject jSONObject) {
    }
}
