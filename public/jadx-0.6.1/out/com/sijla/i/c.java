package com.sijla.i;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Log;
import com.sijla.an.Order;
import com.sijla.bean.TruthInfo;
import com.sijla.callback.QtCallBack;
import com.sijla.common.d;
import com.sijla.j.a.a;
import com.sijla.j.b;
import com.sijla.j.e;
import com.sijla.j.f;
import com.sijla.j.i;
import com.tencent.android.tpush.common.Constants;
import com.tencent.mid.api.MidEntity;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

public class c {
    private static c a;
    private Timer b;
    private TimerTask c;
    private boolean d = false;
    private boolean e = false;
    private long f;
    private long g = b.g();
    private a h = null;

    public static c a() {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    a = new c();
                }
            }
        }
        return a;
    }

    public void a(final Context context, JSONObject jSONObject, final QtCallBack qtCallBack) {
        if (a.a(context) && a(context, context.getPackageName())) {
            if (this.b == null) {
                this.b = new Timer();
            }
            if (this.c == null) {
                this.c = new TimerTask(this) {
                    final /* synthetic */ c c;

                    public void run() {
                        boolean z = false;
                        try {
                            Context applicationContext = context.getApplicationContext();
                            c cVar = this.c;
                            if (a.a(applicationContext) && c.a(applicationContext, applicationContext.getPackageName())) {
                                z = true;
                            }
                            cVar.e = z;
                            if (!this.c.e && this.c.b != null && this.c.c != null) {
                                this.c.f = (System.currentTimeMillis() / 1000) - this.c.f;
                                if (this.c.f > 0) {
                                    Log.i("qm", "App onPause");
                                    f.a("QTSESSION STOP，DUR:" + this.c.f + " STOP—TIME:" + b.a());
                                    c.b(applicationContext, this.c.f, qtCallBack);
                                }
                                this.c.d = false;
                                this.c.b.cancel();
                                this.c.b = null;
                                this.c.c.cancel();
                                this.c.c = null;
                                this.c.f = 0;
                            }
                        } catch (Throwable th) {
                        }
                    }
                };
            }
            if (!this.d) {
                try {
                    this.d = true;
                    this.b.schedule(this.c, 0, 1000);
                    this.f = System.currentTimeMillis() / 1000;
                    Log.i("qm", "App onResume");
                    b(context, jSONObject, qtCallBack);
                } catch (Throwable th) {
                    this.d = false;
                    this.f = 0;
                    if (this.b != null) {
                        this.b.cancel();
                        this.b = null;
                    }
                    if (this.c != null) {
                        this.c.cancel();
                        this.c = null;
                    }
                }
            }
        }
    }

    private void b(final Context context, final JSONObject jSONObject, final QtCallBack qtCallBack) {
        com.sijla.c.c.a(new Runnable(this) {
            final /* synthetic */ c d;

            public void run() {
                f.a("whenHostAppOpen，OPEN—TIME:" + b.a());
                c.b(context, 0, qtCallBack);
                d.a(context, jSONObject);
            }
        });
    }

    private static void b(Context context, long j, QtCallBack qtCallBack) {
        JSONObject a = a(context.getApplicationContext(), j);
        f.c("qt report " + (0 == j ? "onresume " : "onpause"));
        b.a(context, a, qtCallBack);
    }

    private static JSONObject a(Context context, long j) {
        JSONObject jSONObject;
        Exception e;
        try {
            String str = b.g() + "";
            String substring = com.sijla.j.a.c.a(str).substring(0, 8);
            jSONObject = new JSONObject();
            try {
                jSONObject.put("dur", b.b(substring, j + ""));
                jSONObject.put("pv", b.b(substring, "170425"));
                jSONObject.put("dtype", b.b(substring, "qt"));
                jSONObject.put(MidEntity.TAG_TIMESTAMPS, b.b(substring, b.g() + ""));
                jSONObject.put("appkey", b.b(substring, b.p(context)));
                jSONObject.put("channel", b.b(substring, b.m(context)));
                jSONObject.put("uuid", b.b(substring, i.a(context)));
                jSONObject.put("nt", b.b(substring, a.e(context)));
                jSONObject.put("nuid", b.b(substring, i.e(context)));
                jSONObject.put("adr", b.b(substring, b.i(context)));
                jSONObject.put("did", b.b(substring, a.i(context)));
                jSONObject.put("mid", b.b(substring, i.b(context)));
                jSONObject.put("uid3", b.b(substring, com.sijla.common.b.a()));
                jSONObject.put("inschannel", b.b(substring, b.n(context)));
                jSONObject.put("e", "1");
                jSONObject.put("s1", str);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return jSONObject;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            jSONObject = null;
            e = exception;
            e.printStackTrace();
            return jSONObject;
        }
        return jSONObject;
    }

    public static boolean a(Context context, String str) {
        try {
            if (a.f(context, "android.permission.GET_TASKS")) {
                List runningTasks = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningTasks(1);
                if (!(runningTasks == null || runningTasks.isEmpty())) {
                    return ((RunningTaskInfo) runningTasks.get(0)).topActivity.getPackageName().equals(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void a(final Context context, final JSONObject jSONObject) {
        com.sijla.c.c.a(new Runnable(this) {
            final /* synthetic */ c c;

            public void run() {
                int i;
                String str;
                int i2;
                Exception e;
                String string;
                Context applicationContext = context.getApplicationContext();
                SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("QT", 0);
                Editor edit = sharedPreferences.edit();
                String str2 = "1";
                TruthInfo a = b.a(applicationContext, jSONObject);
                TruthInfo truthInfo = new TruthInfo();
                String[] order = ((Order) TruthInfo.class.getAnnotation(Order.class)).order();
                List a2 = b.a(applicationContext);
                List<File> arrayList = new ArrayList();
                for (i = 0; i < a2.size(); i++) {
                    File file = new File((String) a2.get(i));
                    if (file.exists()) {
                        arrayList.add(file);
                    }
                }
                if (arrayList.isEmpty()) {
                    CharSequence string2 = sharedPreferences.getString("appver", "");
                    if (TextUtils.isEmpty(string2)) {
                        f.a("NOT FOUND ANY FILE");
                        str = "4";
                    } else if (TextUtils.isEmpty(string2) || !sharedPreferences.getString("QTIME", "").equals("")) {
                        f.a("NOT FOUND ANY FILE BUT FOUND INAPP");
                        str = str2;
                    } else {
                        f.a("FOUND OLDSDK");
                        str = "2";
                    }
                } else {
                    i = 1;
                    for (File file2 : arrayList) {
                        file2.getAbsolutePath();
                        try {
                            Object a3 = b.a(com.sijla.j.a.d.b(new FileInputStream(file2)));
                            if (!TextUtils.isEmpty(a3)) {
                                String[] split = a3.split("\t");
                                if (split != null) {
                                    int i3 = 0;
                                    while (i3 < split.length) {
                                        String str3 = order[i3];
                                        if ("status".equals(str3) || MidEntity.TAG_TIMESTAMPS.equals(str3) || "addr".equals(str3) || "channel".equals(str3) || "wifimac".equals(str3)) {
                                            i2 = i;
                                        } else if ("uid3".equals(str3)) {
                                            i2 = i;
                                        } else {
                                            str2 = split[i3];
                                            str = (String) e.a(a, str3);
                                            e.a(truthInfo, str3, str2);
                                            boolean z = str2.equals(str) || b.a(str);
                                            i2 = i & z;
                                            if (z) {
                                                continue;
                                            } else {
                                                try {
                                                    f.a("FIELD " + str3 + " VS STATUS:" + z);
                                                } catch (Exception e2) {
                                                    e = e2;
                                                }
                                            }
                                        }
                                        i3++;
                                        i = i2;
                                    }
                                    if (i != 0) {
                                        break;
                                    }
                                }
                            }
                            i2 = i;
                        } catch (Exception e3) {
                            Exception exception = e3;
                            i2 = i;
                            e = exception;
                            e.printStackTrace();
                            i = i2;
                        }
                        i = i2;
                    }
                    if (i != 0) {
                        str = "1";
                    } else {
                        str = "3";
                    }
                    string = sharedPreferences.getString("appver", "");
                    if (!string.equals(a.a(applicationContext.getPackageName(), applicationContext)) && !"".equals(string)) {
                        str = "2";
                    } else if (truthInfo.getAppver().equals(a.getAppver()) && "".equals(string)) {
                        str = "5";
                    }
                }
                b.a(applicationContext, a);
                string = a.a(applicationContext.getPackageName(), applicationContext);
                edit.putString("QTIME", b.g() + "");
                edit.putString("appver", string);
                edit.commit();
                f.c("QT_STATUS:" + str);
                if (!str.equals("1")) {
                    a.setStatus(str);
                    b.a(applicationContext, jSONObject, a);
                }
            }
        });
    }
}
