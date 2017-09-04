package com.tencent.qalsdk.service;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.IBinder;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.qalsdk.base.remote.IBaseService.Stub;
import com.tencent.qalsdk.core.NetConnInfoCenter;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qalsdk.sdk.v;
import com.tencent.qalsdk.util.QLog;
import java.util.HashSet;
import qalsdk.d;

public class QalService extends Service {
    public static String appChannel = null;
    public static String appVersion = null;
    public static Context context = null;
    public static j core = j.a();
    public static volatile boolean inited = false;
    static HashSet<Integer> invalidUids = new HashSet();
    static d msfServiceReqHandler = new d();
    static f msfServiceRespHandler = null;
    static HashSet<Integer> passedUids = new HashSet();
    private static String proccessName = null;
    public static volatile boolean sIsCreatedByAutoBoot = false;
    public static final String tag = "QalService";
    private Stub binder = new i(this);
    private a mConnection = null;

    private class a implements ServiceConnection {
        final /* synthetic */ QalService a;

        private a(QalService qalService) {
            this.a = qalService;
        }

        public void onServiceDisconnected(ComponentName componentName) {
            QLog.i(QalService.tag, "Assist onServiceDisconnected");
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            QLog.i(QalService.tag, "Assist onServiceConnected");
            Service a = ((com.tencent.qalsdk.service.QalAssistService.a) iBinder).a();
            if (a != null) {
                this.a.startForeground(com.tencent.qalsdk.base.a.bm, new Notification());
                a.startForeground(com.tencent.qalsdk.base.a.bm, new Notification());
                a.stopForeground(true);
            }
            this.a.unbindService(this.a.mConnection);
            this.a.mConnection = null;
        }
    }

    public IBinder onBind(Intent intent) {
        String str = null;
        try {
            str = intent.getStringExtra(v.c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        QLog.d(tag, 1, "service onBind by :" + str);
        if (d.t()) {
            d.a(false);
        }
        return this.binder;
    }

    public boolean onUnbind(Intent intent) {
        QLog.d(tag, 2, "serivce onUnbind by :" + intent.getStringExtra(v.c));
        return super.onUnbind(intent);
    }

    public static boolean isSamePackage(Context context, int i, String str) {
        if (invalidUids.contains(Integer.valueOf(i))) {
            if (QLog.isColorLevel()) {
                QLog.d(tag, 2, "found invalid uid call " + i);
            }
            return false;
        } else if (passedUids.contains(Integer.valueOf(i))) {
            return true;
        } else {
            int i2;
            boolean z;
            String str2;
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(i);
            String packageName = context.getPackageName();
            for (String equals : packagesForUid) {
                if (equals.equals(packageName)) {
                    if (QLog.isColorLevel()) {
                        QLog.d(tag, 2, "found accountSyncRequest from the same packeName application,");
                    }
                    passedUids.add(Integer.valueOf(i));
                    z = false;
                    if (z) {
                        return true;
                    }
                    invalidUids.add(Integer.valueOf(i));
                    str2 = "";
                    if (packagesForUid != null) {
                        i2 = 0;
                        while (i2 < packagesForUid.length) {
                            i2++;
                            str2 = str2 + " " + packagesForUid[i2] + VoiceWakeuperAidl.PARAMS_SEPARATE;
                        }
                    }
                    if (QLog.isColorLevel()) {
                        QLog.d(tag, 2, "found invalid uid call " + str2);
                    }
                    return false;
                }
            }
            z = true;
            if (z) {
                return true;
            }
            invalidUids.add(Integer.valueOf(i));
            str2 = "";
            if (packagesForUid != null) {
                i2 = 0;
                while (i2 < packagesForUid.length) {
                    i2++;
                    str2 = str2 + " " + packagesForUid[i2] + VoiceWakeuperAidl.PARAMS_SEPARATE;
                }
            }
            if (QLog.isColorLevel()) {
                QLog.d(tag, 2, "found invalid uid call " + str2);
            }
            return false;
        }
    }

    public void onCreate() {
        super.onCreate();
        try {
            context = this;
            QLog.d(tag, 1, "serivce onCreate");
            serviceInit(this, sIsCreatedByAutoBoot);
            if (QLog.isColorLevel()) {
                QLog.d(tag, 2, "serivce onCreate... autoBoot[" + sIsCreatedByAutoBoot + "]");
            }
            sIsCreatedByAutoBoot = false;
            startForegroundCompat();
        } catch (RuntimeException e) {
            QLog.e(tag, 1, "serivce onCreate exception:" + e.getMessage());
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        QLog.d(tag, 1, "serivce onStart");
        return super.onStartCommand(intent, i, i2);
    }

    public void onDestroy() {
        try {
            QLog.i(tag, "service onDestroy");
            context.unregisterReceiver(j.a().q);
        } catch (Throwable e) {
            QLog.d(tag, 1, "unregisterReceiver failed. " + e, e);
        }
        try {
            context.unregisterReceiver(NetConnInfoCenter.impl.k);
            QLog.i(tag, "unregisterReceiver impl rr");
        } catch (Throwable e2) {
            QLog.d(tag, 1, "unregisterReceiver  rr failed. " + e2, e2);
        }
        try {
            context.unregisterReceiver(j.a().e);
            QLog.i(tag, "unregisterReceiver pushManager ");
        } catch (Throwable e22) {
            QLog.d(tag, 1, "unregisterReceiver pushManager failed. " + e22, e22);
        }
        stopForegroundCompat();
        super.onDestroy();
        System.exit(0);
    }

    public static synchronized void serviceInit(Context context, boolean z) {
        synchronized (QalService.class) {
            if (!inited) {
                QLog.d(tag, "serviceInit init");
                proccessName = MsfSdkUtils.getProcessName(context);
                QLog.init(context);
                core.a(context, z);
                c.a(context, core);
                msfServiceRespHandler = new f(core);
                msfServiceRespHandler.setName("MsfServiceRespHandler");
                msfServiceRespHandler.start();
                inited = true;
            }
        }
    }

    public static j getCore() {
        return core;
    }

    public static String getProccessName() {
        return proccessName;
    }

    private void startForegroundCompat() {
        try {
            if (VERSION.SDK_INT < 18) {
                startForeground(com.tencent.qalsdk.base.a.bm, new Notification());
                return;
            }
            if (this.mConnection == null) {
                this.mConnection = new a();
            }
            bindService(new Intent(this, QalAssistService.class), this.mConnection, 1);
        } catch (Exception e) {
            QLog.e(tag, e.getMessage());
        }
    }

    private void stopForegroundCompat() {
        try {
            if (VERSION.SDK_INT < 18) {
                stopForeground(true);
            }
        } catch (Exception e) {
        }
    }
}
