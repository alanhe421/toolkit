package com.tencent.feedback.eup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import com.tencent.feedback.common.a;
import com.tencent.feedback.common.c;
import com.tencent.feedback.common.e;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: RQDSRC */
public class BuglyBroadcastRecevier extends BroadcastReceiver {
    public static final String ACTION_ENCRY_KEY = "feedback";
    public static final int ACTION_ENCRY_TYPE = 1;
    public static final String ACTION_PROCESS_CRASHED = "com.tencent.feedback.A02";
    public static final String ACTION_PROCESS_LAUNCHED = "com.tencent.feedback.A01";
    public static final String BUNDLE_APPINFO = "com.tencent.feedback.B01";
    public static final String BUNDLE_CRASHINFO = "com.tencent.feedback.B02";
    public static final String PARAM_AVAIL_RAM = "com.tencent.feedback.P07";
    public static final String PARAM_AVAIL_ROM = "com.tencent.feedback.P13";
    public static final String PARAM_AVAIL_SDCARD = "com.tencent.feedback.P06";
    public static final String PARAM_CRASH_TYPE = "com.tencent.feedback.P05";
    public static final String PARAM_ENCRY_PKG_NAME = "com.tencent.feedback.P12";
    public static final String PARAM_EXP_MESSAGE = "com.tencent.feedback.P09";
    public static final String PARAM_EXP_STACK = "com.tencent.feedback.P010";
    public static final String PARAM_EXP_TYPE_NAME = "com.tencent.feedback.P08";
    public static final String PARAM_IS_FRONT_PROCESS = "com.tencent.feedback.P03";
    public static final String PARAM_PKG_NAME = "com.tencent.feedback.P01";
    public static final String PARAM_PROCESS_NAME = "com.tencent.feedback.P02";
    public static final String PARAM_SERVER_KEY_VALUE = "com.tencent.feedback.P17";
    public static final String PARAM_SERVER_SCENE_TAG = "com.tencent.feedback.P15";
    public static final String PARAM_SURVIVAL_TIME = "com.tencent.feedback.P011";
    public static final String PARAM_THREAD_NAME = "com.tencent.feedback.P04";
    public static final String PARAM_USER_KEY_VALUE = "com.tencent.feedback.P16";
    public static final String PARAM_USER_SCENE_TAG = "com.tencent.feedback.P14";
    public static final long UPLOADLIMITED = 60000;
    private static BuglyBroadcastRecevier instance = null;
    private String lastAPN;
    private Context mContext;
    private IntentFilter mFilter;
    private final ProcessMoniterHandler monierHandler;

    public static synchronized BuglyBroadcastRecevier getInstance() {
        BuglyBroadcastRecevier buglyBroadcastRecevier;
        synchronized (BuglyBroadcastRecevier.class) {
            if (instance == null) {
                instance = new BuglyBroadcastRecevier();
            }
            buglyBroadcastRecevier = instance;
        }
        return buglyBroadcastRecevier;
    }

    public BuglyBroadcastRecevier() {
        this.mFilter = new IntentFilter();
        this.monierHandler = null;
    }

    public BuglyBroadcastRecevier(ProcessMoniterHandler processMoniterHandler) {
        this.mFilter = new IntentFilter();
        this.monierHandler = processMoniterHandler;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.mContext != null) {
            this.mContext.unregisterReceiver(this);
        }
    }

    public synchronized void addFilter(String str) {
        if (!this.mFilter.hasAction(str)) {
            this.mFilter.addAction(str);
        }
        e.b("add action %s", str);
    }

    public synchronized void regist(Context context) {
        e.a("regis BC", new Object[0]);
        context.registerReceiver(this, this.mFilter);
        this.mContext = context;
    }

    public synchronized void unregist(Context context) {
        e.a("unregis BC", new Object[0]);
        context.unregisterReceiver(this);
        this.mContext = context;
    }

    public final void onReceive(Context context, Intent intent) {
        if (!processConnectedBroadCast(context, intent)) {
            CrashStrategyBean crashRuntimeStrategy = CrashReport.getCrashRuntimeStrategy();
            if (crashRuntimeStrategy == null) {
                e.d("magic! no crash stategy,no notify return ?", new Object[0]);
            } else if (!crashRuntimeStrategy.isReceiveBroadcast()) {
                e.a("close proc receiver!", new Object[0]);
            } else if (!processOtherProcessLaunched(context, intent) && processOtherProcessCrashed(context, intent)) {
            }
        }
    }

    protected final synchronized boolean processConnectedBroadCast(Context context, Intent intent) {
        return false;
    }

    protected static void brocastProcessLaunch(Context context) {
        if (context != null) {
            try {
                CrashStrategyBean crashRuntimeStrategy = CrashReport.getCrashRuntimeStrategy();
                if (crashRuntimeStrategy == null) {
                    e.d("magic! no crash stategy,no notify return ?", new Object[0]);
                } else if (crashRuntimeStrategy.isBroadcast()) {
                    e.a("notify launch !", new Object[0]);
                    String b = a.b(context);
                    byte[] a = com.tencent.feedback.proguard.a.a(b.getBytes("utf8"), 1, ACTION_ENCRY_KEY);
                    Intent intent = new Intent(ACTION_PROCESS_LAUNCHED);
                    intent.putExtra(PARAM_ENCRY_PKG_NAME, a);
                    Bundle bundle = new Bundle();
                    bundle.putString(PARAM_PKG_NAME, b);
                    bundle.putString(PARAM_PROCESS_NAME, a.a(Process.myPid()));
                    bundle.putBoolean(PARAM_IS_FRONT_PROCESS, a.g(context));
                    intent.putExtra(BUNDLE_APPINFO, bundle);
                    context.sendBroadcast(intent);
                } else {
                    e.a("close brocast!", new Object[0]);
                }
            } catch (Throwable th) {
                if (!e.a(th)) {
                    Log.w("eup", "something error " + th.getClass().getName());
                }
            }
        }
    }

    public static void brocastCrash(Context context, e eVar) {
        if (context != null && eVar != null) {
            try {
                CrashStrategyBean crashRuntimeStrategy = CrashReport.getCrashRuntimeStrategy();
                if (crashRuntimeStrategy == null) {
                    e.d("magic! no crash stategy,no notify return ?", new Object[0]);
                } else if (crashRuntimeStrategy.isBroadcast()) {
                    e.a("notify crash !", new Object[0]);
                    String b = a.b(context);
                    byte[] a = com.tencent.feedback.proguard.a.a(b.getBytes("utf8"), 1, ACTION_ENCRY_KEY);
                    Intent intent = new Intent(ACTION_PROCESS_CRASHED);
                    intent.putExtra(PARAM_ENCRY_PKG_NAME, a);
                    Bundle bundle = new Bundle();
                    bundle.putString(PARAM_PKG_NAME, b);
                    bundle.putString(PARAM_PROCESS_NAME, eVar.q());
                    bundle.putString(PARAM_THREAD_NAME, eVar.r());
                    bundle.putByte(PARAM_CRASH_TYPE, eVar.P());
                    bundle.putLong(PARAM_AVAIL_ROM, eVar.G());
                    bundle.putLong(PARAM_AVAIL_SDCARD, eVar.H());
                    bundle.putLong(PARAM_AVAIL_RAM, eVar.F());
                    bundle.putString(PARAM_EXP_TYPE_NAME, eVar.e());
                    bundle.putString(PARAM_EXP_MESSAGE, eVar.f());
                    bundle.putString(PARAM_EXP_STACK, eVar.h());
                    bundle.putBoolean(PARAM_IS_FRONT_PROCESS, a.g(context));
                    bundle.putLong(PARAM_SURVIVAL_TIME, eVar.Q());
                    bundle.putInt(PARAM_USER_SCENE_TAG, eVar.R());
                    bundle.putInt(PARAM_SERVER_SCENE_TAG, eVar.S());
                    bundle.putBundle(PARAM_USER_KEY_VALUE, getBundleFromMap(eVar.T()));
                    bundle.putBundle(PARAM_SERVER_KEY_VALUE, getBundleFromMap(eVar.U()));
                    intent.putExtra(BUNDLE_CRASHINFO, bundle);
                    context.sendBroadcast(intent);
                } else {
                    e.a("close brocast!", new Object[0]);
                }
            } catch (Throwable th) {
                if (!e.a(th)) {
                    Log.w("eup", "something error " + th.getClass().getName());
                }
            }
        }
    }

    private static Bundle getBundleFromMap(Map<String, String> map) {
        Bundle bundle = new Bundle();
        if (map != null && map.size() > 0) {
            for (Entry entry : map.entrySet()) {
                bundle.putString((String) entry.getKey(), (String) entry.getValue());
            }
        }
        return bundle;
    }

    protected final synchronized boolean processOtherProcessLaunched(Context context, Intent intent) {
        boolean z = true;
        synchronized (this) {
            if (!(context == null || intent == null)) {
                if (intent.getAction().equals(ACTION_PROCESS_LAUNCHED)) {
                    if (this.monierHandler == null) {
                        e.a("no moniter handler", new Object[0]);
                    } else {
                        try {
                            String packageName = context.getPackageName();
                            String E = c.a(context).E();
                            Object byteArrayExtra = intent.getByteArrayExtra(PARAM_ENCRY_PKG_NAME);
                            Bundle bundleExtra = intent.getBundleExtra(BUNDLE_APPINFO);
                            if (byteArrayExtra == null || bundleExtra == null) {
                                e.c("args fail other proc launch %s %s", byteArrayExtra, bundleExtra);
                            } else {
                                String str = new String(com.tencent.feedback.proguard.a.b(byteArrayExtra, 1, ACTION_ENCRY_KEY), "utf8");
                                if (!str.equals(bundleExtra.getString(PARAM_PKG_NAME))) {
                                    e.c("args fail other proc launch inner %s %s", str, bundleExtra.getString(PARAM_PKG_NAME));
                                } else if ((str + bundleExtra.getString(PARAM_PROCESS_NAME)).equals(packageName + E)) {
                                    e.a("current proc not need to notify", new Object[0]);
                                } else {
                                    e.a("notify other app lau %s", str);
                                    this.monierHandler.onOtherAppProcessLaunched(str, bundleExtra.getString(PARAM_PROCESS_NAME), bundleExtra.getBoolean(PARAM_IS_FRONT_PROCESS), bundleExtra);
                                    e.a("notify other app lau %s end", str);
                                }
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            z = false;
        }
        return z;
    }

    public final synchronized boolean processOtherProcessCrashed(Context context, Intent intent) {
        boolean z;
        if (!(context == null || intent == null)) {
            if (intent.getAction().equals(ACTION_PROCESS_CRASHED)) {
                if (this.monierHandler == null) {
                    e.a("no moniter handler", new Object[0]);
                    z = true;
                } else {
                    try {
                        String packageName = context.getPackageName();
                        String E = c.a(context).E();
                        Object byteArrayExtra = intent.getByteArrayExtra(PARAM_ENCRY_PKG_NAME);
                        Bundle bundleExtra = intent.getBundleExtra(BUNDLE_CRASHINFO);
                        if (byteArrayExtra == null || bundleExtra == null) {
                            e.c("args fail other proc cra %s %s", byteArrayExtra, bundleExtra);
                            z = true;
                        } else {
                            String str = new String(com.tencent.feedback.proguard.a.b(byteArrayExtra, 1, ACTION_ENCRY_KEY), "utf8");
                            if (str.equals(bundleExtra.getString(PARAM_PKG_NAME))) {
                                if ((str + bundleExtra.getString(PARAM_PROCESS_NAME)).equals(packageName + E)) {
                                    e.a("current proc not need to notify", new Object[0]);
                                    z = true;
                                } else {
                                    e.a("notify other app cra %s", str);
                                    this.monierHandler.onOtherAppProcessCrash(str, bundleExtra.getString(PARAM_PROCESS_NAME), bundleExtra.getString(PARAM_THREAD_NAME), bundleExtra.getByte(PARAM_CRASH_TYPE), bundleExtra.getLong(PARAM_AVAIL_ROM), bundleExtra.getLong(PARAM_AVAIL_SDCARD), bundleExtra.getLong(PARAM_AVAIL_RAM), bundleExtra.getString(PARAM_EXP_TYPE_NAME), bundleExtra.getString(PARAM_EXP_MESSAGE), bundleExtra.getString(PARAM_EXP_STACK), bundleExtra.getBoolean(PARAM_IS_FRONT_PROCESS), bundleExtra.getLong(PARAM_SURVIVAL_TIME), bundleExtra);
                                    e.a("notify other app cra %s end", str);
                                    z = true;
                                }
                            } else {
                                Object[] objArr = new Object[2];
                                objArr[0] = str;
                                objArr[1] = bundleExtra.getString(PARAM_PKG_NAME);
                                e.c("args fail other proc cra inner %s %s", objArr);
                                z = true;
                            }
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        z = false;
        return z;
    }
}
