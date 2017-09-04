package com.tencent.android.tpush.service;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.text.TextUtils;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.stat.f;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class w {
    private static final String a = w.class.getSimpleName();
    private static volatile w c = null;
    private Context b = null;
    private boolean d = true;
    private Handler e = null;
    private volatile boolean f = false;
    private long g = 0;

    private w(Context context) {
        this.b = context.getApplicationContext();
        this.d = g();
        HandlerThread handlerThread = new HandlerThread(w.class.getName());
        handlerThread.start();
        this.e = new Handler(handlerThread.getLooper());
    }

    public static w a(Context context) {
        if (c == null) {
            synchronized (w.class) {
                if (c == null) {
                    c = new w(context);
                }
            }
        }
        return c;
    }

    private String c() {
        int myPid = Process.myPid();
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.b.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    private boolean a(String str) {
        boolean z;
        List<RunningServiceInfo> runningServices = ((ActivityManager) this.b.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningServices(Integer.MAX_VALUE);
        if (runningServices != null && runningServices.size() > 0) {
            for (RunningServiceInfo runningServiceInfo : runningServices) {
                if (runningServiceInfo.service.getPackageName().equals(str)) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        a.d(a, str + " is running=" + z);
        return z;
    }

    private void d() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (Build.MODEL != null) {
                jSONObject.put("md", Build.MODEL);
            }
            jSONObject.put("osVer", String.valueOf(VERSION.SDK_INT));
            jSONObject.put("mf", Build.MANUFACTURER);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        f.b(this.b).a(new com.tencent.android.tpush.stat.event.a(this.b, "PullYYB", jSONObject, "AVF34P44NJT5"), new x(this));
    }

    private void a(String str, String str2) {
        if (!this.f) {
            this.f = true;
            a.d(a, str + "/" + str2);
            if (b(str)) {
                a.d(a, str + " is installed.");
                if (!a(str)) {
                    try {
                        String str3 = "am startservice -n " + str + "/" + str2;
                        int waitFor = Runtime.getRuntime().exec(str3).waitFor();
                        a.d(a, "exec cmd:" + str3 + ",exitValud:" + waitFor);
                        if (waitFor != 0) {
                            str3 = "am startservice --user 0 -n " + str + "/" + str2;
                            Process exec = Runtime.getRuntime().exec(str3);
                            exec.waitFor();
                            a.d(a, "exec cmd:" + str3 + ",exitValud:" + exec.exitValue());
                        }
                        Intent intent = new Intent();
                        intent.setAction("com.tencent.android.qqdownloader.SDKService");
                        intent.putExtra("from_where", "xg");
                        intent.setClassName(str, str2);
                        this.b.startService(intent);
                        Thread.sleep(1000);
                        if (a(str)) {
                            d();
                        }
                    } catch (Throwable th) {
                        a.b(a, "monitorAppService error.", th);
                    }
                }
            }
            this.f = false;
        }
    }

    private void e() {
        a("com.tencent.android.qqdownloader", "com.tencent.assistant.sdk.SDKSupportService");
    }

    private void f() {
        XGPushManager.startPushService(this.b);
        XGWatchdog.getInstance(this.b).startWatchdog();
    }

    private boolean b(String str) {
        try {
            this.b.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean g() {
        Object c = c();
        if (TextUtils.isEmpty(c) || !c.contains("xg_service")) {
            a.d(a, "not xg_service");
            return false;
        }
        a.d(a, "is xg_service");
        return true;
    }

    public void a() {
        if (this.e != null) {
            this.e.post(new y(this));
        }
    }
}
