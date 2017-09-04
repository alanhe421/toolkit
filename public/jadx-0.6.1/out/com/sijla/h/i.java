package com.sijla.h;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.sijla.common.a;
import com.sijla.d.c;
import com.sijla.j.a.d;
import com.sijla.j.b;
import com.sijla.j.f;
import com.sijla.j.g;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class i implements a {
    JSONObject a;
    private Context b;
    private boolean c;

    public i(Context context, JSONObject jSONObject) {
        this(context, jSONObject, false);
    }

    public i(Context context, JSONObject jSONObject, boolean z) {
        this.a = new JSONObject();
        this.b = context;
        if (jSONObject != null) {
            this.a = jSONObject;
        }
        this.c = z;
    }

    public void a() {
        a("onScreenOn");
    }

    public void b() {
        f.a("UploadDataFunner.onScreenOff");
        a("onScreenOff");
    }

    public void run() {
        a("run");
    }

    public static List<File> a(Context context, String str) {
        List arrayList = new ArrayList();
        if (!b.a(str)) {
            File file = new File(str);
            if (file != null) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    String name;
                    Map hashMap = new HashMap();
                    for (File file2 : listFiles) {
                        if (file2 != null && file2.exists()) {
                            name = file2.getName();
                            if (file2.isDirectory() || !name.contains(".csv")) {
                                com.sijla.j.a.b.a(file2);
                                f.a(name + " is Dir ");
                            } else if (!name.endsWith(".lock")) {
                                try {
                                    String substring = name.substring(0, name.indexOf(".csv"));
                                    StringBuffer stringBuffer = new StringBuffer();
                                    if (hashMap.containsKey(substring)) {
                                        stringBuffer.append((StringBuffer) hashMap.get(substring));
                                    }
                                    stringBuffer.append(b.a(d.b(new FileInputStream(file2))));
                                    hashMap.put(substring, stringBuffer);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                file2.delete();
                            }
                        }
                    }
                    for (String name2 : hashMap.keySet()) {
                        File a = com.sijla.j.a.b.a(b.j() + (name2 + ".csv" + "-" + System.currentTimeMillis()), b.e(((StringBuffer) hashMap.get(name2)).toString()));
                        if (a != null && a.exists()) {
                            arrayList.add(a);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public static void a(Context context, JSONObject jSONObject) {
        String j = b.j();
        a(context, j);
        a(context, j, true);
        j = b.k();
        a(context, j);
        a(context, j, false);
    }

    private static void a(Context context, String str, boolean z) {
        int i = 0;
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null && listFiles.length != 0) {
            Map hashMap = new HashMap();
            for (File file : listFiles) {
                if (file != null && file.exists() && file.isFile()) {
                    hashMap.put(file.getName(), file);
                }
            }
            if (hashMap != null) {
                try {
                    JSONArray jSONArray;
                    JSONArray optJSONArray = c.a.optJSONArray("truthurls");
                    if (optJSONArray == null || optJSONArray.length() == 0) {
                        optJSONArray = new JSONArray();
                        optJSONArray.put("http://www.qchannel01.cn/center/ard");
                        jSONArray = optJSONArray;
                    } else {
                        jSONArray = optJSONArray;
                    }
                    String a = a(context, z);
                    boolean z2 = false;
                    while (i < jSONArray.length()) {
                        String optString = jSONArray.optString(i, "http://www.qchannel01.cn/center/ard");
                        com.sijla.common.c cVar = new com.sijla.common.c();
                        f.a("uploadDataUseFiles");
                        com.sijla.common.c a2 = g.a(optString + a, new JSONObject(), hashMap);
                        Log.d("qlog", "STATUS:" + a2.b());
                        if (a2.b()) {
                            if (!z2) {
                                z2 = true;
                            }
                            if (c.a.optInt("repeatReportTruth", 0) == 0) {
                                f.a("repeatReportTruth=0,break");
                                break;
                            }
                            f.a("repeatReportTruth=1");
                        }
                        i++;
                    }
                    a(str, z2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String a(Context context, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?appkey=");
        stringBuilder.append(b.p(context));
        stringBuilder.append("&uid=");
        stringBuilder.append(com.sijla.j.i.b(context));
        stringBuilder.append("&sdk=");
        stringBuilder.append(170425);
        stringBuilder.append("&did=");
        stringBuilder.append(com.sijla.j.a.a.i(context));
        stringBuilder.append(z ? "&type=3" : "");
        return stringBuilder.toString();
    }

    protected static void a(String str, boolean z) {
        int i = 0;
        try {
            File[] listFiles = new File(str).listFiles();
            if (listFiles != null) {
                int optInt = c.a.optInt("truthsize", 10);
                File file;
                if (z || listFiles.length > optInt) {
                    optInt = listFiles.length;
                    while (i < optInt) {
                        file = listFiles[i];
                        if (file != null && file.exists()) {
                            com.sijla.j.a.b.a(file);
                            f.a(ShareConstants.RES_DEL_TITLE + file.getName());
                        }
                        i++;
                    }
                    return;
                }
                optInt = listFiles.length;
                while (i < optInt) {
                    file = listFiles[i];
                    if (!(file == null || !file.exists() || file.getName().endsWith(".lock"))) {
                        file.renameTo(new File(file.getAbsolutePath() + ".lock"));
                        file.delete();
                        f.a("rename:" + file.getName());
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(String str) {
        if (!com.sijla.j.a.a.b(this.b)) {
            return;
        }
        if (this.c || f()) {
            f.a("UploadDataFunner.executeUploadData from:" + str);
            a(this.b, this.a);
        }
    }

    private boolean f() {
        return b.a(this.b, "lastPostTime", this.a.optLong("timepost", 3600));
    }

    public void c() {
        com.sijla.c.c.a(new 1(this));
    }

    public void d() {
    }

    public void e() {
        com.sijla.c.c.a(new 2(this));
    }

    public void a(Intent intent) {
        com.sijla.c.c.a(new 3(this));
    }
}
