package com.iflytek.cloud;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.iflytek.speech.SpeechComponent;
import com.iflytek.speech.UtilityConfig;
import com.tencent.android.tpush.common.Constants;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class e extends com.iflytek.cloud.a.a.a {
    private static e c = null;
    protected com.iflytek.cloud.a.a.a.a a = com.iflytek.cloud.a.a.a.a.AUTO;
    private ArrayList<SpeechComponent> d = new ArrayList();
    private int e = -1;
    private Context f = null;
    private boolean g = false;
    private a h = null;

    private class a extends BroadcastReceiver {
        final /* synthetic */ e a;

        private a(e eVar) {
            this.a = eVar;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String dataString = intent.getDataString();
            String concat = String.valueOf("package:").concat(UtilityConfig.COMPONENT_PKG);
            String str = "android.intent.action.PACKAGE_REMOVED";
            String str2 = "android.intent.action.PACKAGE_REPLACED";
            if (("android.intent.action.PACKAGE_ADDED".equals(action) || str.equals(action) || str2.equals(action)) && concat.equals(dataString) && e.a() != null) {
                e.a().c();
            }
        }
    }

    private e(Context context, String str) {
        this.f = context.getApplicationContext();
        super.a("params", str);
        a("params", str);
        this.a = com.iflytek.cloud.a.a.a.a.PLUS;
        f();
        g();
        try {
            com.iflytek.common.a.a(context, "appid", this.b.d("appid"));
            com.iflytek.common.a.a(context);
            com.iflytek.common.a.a(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized e a() {
        e eVar;
        synchronized (e.class) {
            eVar = c;
        }
        return eVar;
    }

    public static synchronized e a(Context context, String str) {
        e eVar;
        synchronized (e.class) {
            if (c == null) {
                com.iflytek.cloud.b.a aVar = new com.iflytek.cloud.b.a();
                aVar.b(str);
                if (aVar.a("force_login", false) || a(context.getApplicationContext())) {
                    c = new e(context, str);
                } else {
                    com.iflytek.cloud.a.b.a.a.b("init failed, please call this method in your main process!");
                    c = null;
                }
            }
            eVar = c;
        }
        return eVar;
    }

    private static boolean a(Context context) {
        try {
            int myPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME);
            if (activityManager != null) {
                for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                    if (runningAppProcessInfo.pid == myPid) {
                        com.iflytek.cloud.a.b.a.a.a("process name:" + runningAppProcessInfo.processName);
                        if (context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).packageName.equals(runningAppProcessInfo.processName)) {
                            com.iflytek.cloud.a.b.a.a.a("process name:" + runningAppProcessInfo.processName + "is own process");
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void c(String str) {
        if (!TextUtils.isEmpty(str)) {
            PackageManager packageManager = this.f.getPackageManager();
            Intent intent = new Intent(str);
            intent.setPackage(UtilityConfig.COMPONENT_PKG);
            List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, Opcodes.SHL_INT_LIT8);
            if (queryIntentServices != null && queryIntentServices.size() > 0) {
                for (ResolveInfo resolveInfo : queryIntentServices) {
                    SpeechComponent d = d(resolveInfo.serviceInfo.packageName);
                    if (d != null) {
                        try {
                            for (String addEngine : resolveInfo.serviceInfo.metaData.getString(UtilityConfig.METADATA_KEY_ENGINE_TYPE).split(",")) {
                                d.addEngine(addEngine);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private SpeechComponent d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Object obj;
        SpeechComponent speechComponent;
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            if (str.equals(((SpeechComponent) it.next()).getPackageName())) {
                obj = 1;
                break;
            }
        }
        obj = null;
        if (obj == null) {
            speechComponent = new SpeechComponent(str);
            this.d.add(speechComponent);
        } else {
            speechComponent = null;
        }
        return speechComponent;
    }

    private void f() {
        if (c()) {
            c(UtilityConfig.ACTION_SPEECH_RECOGNIZER);
            c(UtilityConfig.ACTION_SPEECH_SYNTHESIZER);
            c(UtilityConfig.ACTION_SPEECH_UNDERSTANDER);
            c(UtilityConfig.ACTION_TEXT_UNDERSTANDER);
            c(UtilityConfig.ACTION_SPEECH_WAKEUP);
        }
    }

    private void g() {
        this.h = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addDataScheme("package");
        this.f.registerReceiver(this.h, intentFilter);
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.b.e(str)) {
            return super.a(str);
        }
        if (!str.equals("tts") && !str.equals("asr") && !str.equals("all") && !str.equals("ivw")) {
            return null;
        }
        try {
            return b(str);
        } catch (Exception e) {
            return "{ret:20004}";
        }
    }

    public boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        super.a(str, str2);
        return true;
    }

    public String b(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String str2 = "";
        if (!c()) {
            jSONObject.put("ret", 21001);
            return jSONObject.toString();
        } else if (e() < 97) {
            jSONObject.put("ret", 20018);
            return jSONObject.toString();
        } else if (Constants.ERRORCODE_UNKNOWN > e() || e() > 11000) {
            Object string;
            Cursor query = this.f.getContentResolver().query(Uri.parse("content://com.iflytek.vflynote.providers.LocalResourceProvider"), null, str, null, null);
            int columnIndex = query.getColumnIndex("tag_rescontent");
            if (query == null || !query.moveToFirst()) {
                String str3 = str2;
            } else {
                string = query.getString(columnIndex);
                Log.v("SpeechUtility", string);
            }
            if (query != null) {
                query.close();
            }
            if (TextUtils.isEmpty(string)) {
                jSONObject.put("ret", 20004);
                return jSONObject.toString();
            }
            jSONObject.put("ret", 0);
            jSONObject.put("result", new JSONObject(string));
            return jSONObject.toString();
        } else {
            jSONObject.put("ret", 20020);
            return jSONObject.toString();
        }
    }

    protected boolean b() {
        try {
            return this.f.getPackageManager().getPackageInfo(UtilityConfig.COMPONENT_PKG, 0) != null;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public boolean c() {
        boolean z = false;
        int i = -1;
        try {
            PackageInfo packageInfo = this.f.getPackageManager().getPackageInfo(UtilityConfig.COMPONENT_PKG, 0);
            if (packageInfo != null) {
                z = true;
                i = packageInfo.versionCode;
            }
        } catch (NameNotFoundException e) {
        }
        if (!(z == this.g && this.e == i)) {
            this.g = z;
            this.e = i;
            if (b.a() != null) {
                b.a().a(this.f);
            }
            if (c.a() != null) {
                c.a().a(this.f);
            }
            if (d.a() != null) {
                d.a().a(this.f);
            }
            if (g.a() != null) {
                g.a().a(this.f);
            }
        }
        return z;
    }

    public com.iflytek.cloud.a.a.a.a d() {
        return this.a;
    }

    public int e() {
        if (this.e < 0) {
            try {
                PackageInfo packageInfo = this.f.getPackageManager().getPackageInfo(UtilityConfig.COMPONENT_PKG, 0);
                if (packageInfo != null) {
                    this.e = packageInfo.versionCode;
                }
            } catch (NameNotFoundException e) {
            }
        }
        return this.e;
    }
}
