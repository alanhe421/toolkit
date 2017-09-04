package com.tencent.beacon.event;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.android.tpush.common.Constants;
import com.tencent.beacon.b.a.b;
import com.tencent.beacon.b.a.c;
import com.tencent.beacon.b.f;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ProGuard */
public final class a {
    private static String g = "INSTALL_UPLOADED_DENGTA";
    private static String h = "USEAPP_UPLOADED_DENGTA";
    private Context a;
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;
    private int e = 180;
    private String f = "N";
    private List<b> i = null;
    private Runnable j = new Runnable(this) {
        private /* synthetic */ a a;

        {
            this.a = r1;
        }

        public final void run() {
            a.a(this.a);
        }
    };
    private Runnable k = new Runnable(this) {
        private /* synthetic */ a a;

        {
            this.a = r1;
        }

        public final void run() {
            a.b(this.a);
        }
    };
    private Runnable l = new Runnable(this) {
        private /* synthetic */ a a;

        {
            this.a = r1;
        }

        public final void run() {
            a.c(this.a);
        }
    };

    static /* synthetic */ void a(a aVar) {
        List c = aVar.c(aVar.d);
        if (c != null) {
            int size = c.size();
            if (size > 0) {
                Map hashMap = new HashMap(4);
                f.a(aVar.a);
                hashMap.put("A33", f.l(aVar.a));
                hashMap.put("A58", aVar.f);
                StringBuilder stringBuilder = new StringBuilder();
                int i = (size + 49) / 50;
                int i2;
                if (i > 1) {
                    int i3 = 0;
                    while (i3 < i) {
                        for (i2 = 0; i2 < size; i2++) {
                            if (((i2 + 1) + 49) / 50 == i3 + 1) {
                                stringBuilder.append((String) c.get(i2)).append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                            }
                            if (i2 + 1 == (i2 + 1) * 50) {
                                break;
                            }
                        }
                        hashMap.put("A83", stringBuilder.toString());
                        if (UserAction.onUserAction("rqd_install_apps", true, 0, 0, hashMap, true) && i3 == 0) {
                            com.tencent.beacon.b.b.a(aVar.a, g, com.tencent.beacon.net.a.d());
                        }
                        stringBuilder.delete(0, stringBuilder.length());
                        i3++;
                    }
                    return;
                }
                for (i2 = 0; i2 < size; i2++) {
                    if (i2 == size - 1) {
                        stringBuilder.append((String) c.get(i2));
                    } else {
                        stringBuilder.append((String) c.get(i2)).append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                    }
                }
                hashMap.put("A83", stringBuilder.toString());
                if (UserAction.onUserAction("rqd_install_apps", true, 0, 0, hashMap, true)) {
                    com.tencent.beacon.b.b.a(aVar.a, g, com.tencent.beacon.net.a.d());
                }
            }
        }
    }

    static /* synthetic */ void b(a aVar) {
        aVar.i = aVar.b(aVar.d);
        StringBuilder stringBuilder;
        Map hashMap;
        if (com.tencent.beacon.net.a.d().equals(com.tencent.beacon.b.b.b(aVar.a, h, ""))) {
            List<b> a = c.a(aVar.a);
            if (a != null) {
                stringBuilder = new StringBuilder();
                for (b bVar : a) {
                    stringBuilder.append(bVar.b + "," + bVar.c + "," + (bVar.d ? "Y" : "N")).append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                    bVar.e = true;
                }
                hashMap = new HashMap(4);
                f.a(aVar.a);
                hashMap.put("A33", f.l(aVar.a));
                hashMap.put("A58", aVar.f);
                hashMap.put("A84", stringBuilder.toString());
                if (UserAction.onUserAction("rqd_use_apps", true, 0, 0, hashMap, true)) {
                    c.b(aVar.a);
                    return;
                }
                return;
            }
            return;
        }
        c.c(aVar.a);
        if (aVar.i != null) {
            stringBuilder = new StringBuilder();
            for (b bVar2 : aVar.i) {
                stringBuilder.append(bVar2.b + "," + bVar2.c + "," + (bVar2.d ? "Y" : "N")).append(VoiceWakeuperAidl.PARAMS_SEPARATE);
            }
            hashMap = new HashMap(4);
            f.a(aVar.a);
            hashMap.put("A33", f.l(aVar.a));
            hashMap.put("A58", aVar.f);
            hashMap.put("A84", stringBuilder.toString());
            if (UserAction.onUserAction("rqd_use_apps", true, 0, 0, hashMap, true)) {
                com.tencent.beacon.b.b.a(aVar.a, h, com.tencent.beacon.net.a.d());
            }
            c.a(aVar.a, aVar.i, true);
        }
    }

    static /* synthetic */ void c(a aVar) {
        List<b> b = aVar.b(aVar.d);
        if (aVar.i != null) {
            Collection arrayList = new ArrayList();
            for (b bVar : b) {
                if (!aVar.i.contains(bVar)) {
                    arrayList.add(bVar);
                }
            }
            if (arrayList.size() > 0) {
                c.a(aVar.a, arrayList, false);
                aVar.i.addAll(arrayList);
            }
        }
    }

    public a(Context context, boolean z, boolean z2, boolean z3, int i) {
        this.a = context;
        this.c = z;
        this.b = z2;
        this.d = z3;
        this.e = i;
        n a = n.a();
        if (a != null) {
            this.f = a.r();
        }
        if (this.c) {
            String b = com.tencent.beacon.b.b.b(this.a, g, "");
            if ("".equals(b) || !com.tencent.beacon.net.a.d().equals(b)) {
                com.tencent.beacon.b.c.a().a(this.j, BuglyBroadcastRecevier.UPLOADLIMITED);
            } else {
                com.tencent.beacon.e.a.a("installApp list has been uploaded today! " + b, new Object[0]);
            }
        }
        if (this.b) {
            com.tencent.beacon.b.c.a().a(this.k, BuglyBroadcastRecevier.UPLOADLIMITED);
            a(true);
        }
    }

    public final void a(boolean z) {
        if (z) {
            com.tencent.beacon.b.c.a().a(110, this.l, (long) (this.e * 1000), (long) (this.e * 1000));
        } else {
            com.tencent.beacon.b.c.a().a(110);
        }
    }

    private List<b> b(boolean z) {
        if (this.a == null) {
            return null;
        }
        List<b> list;
        try {
            ActivityManager activityManager = (ActivityManager) this.a.getSystemService(Constants.FLAG_ACTIVITY_NAME);
            if (activityManager == null) {
                return null;
            }
            List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null || runningAppProcesses.size() <= 0) {
                list = null;
                return list;
            }
            List<b> arrayList = new ArrayList();
            try {
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    boolean z2 = runningAppProcessInfo.processName.startsWith("android") || runningAppProcessInfo.processName.startsWith("com.android.") || runningAppProcessInfo.uid < Constants.ERRORCODE_UNKNOWN;
                    if (!z2 || z) {
                        b bVar = new b();
                        bVar.b = runningAppProcessInfo.processName;
                        bVar.c = runningAppProcessInfo.pid;
                        bVar.d = z2;
                        arrayList.add(bVar);
                    }
                }
                list = arrayList;
            } catch (Exception e) {
                list = arrayList;
            }
            return list;
        } catch (Exception e2) {
            list = null;
        }
    }

    @SuppressLint({"NewApi"})
    private List<String> c(boolean z) {
        if (this.a == null) {
            return null;
        }
        try {
            PackageManager packageManager = this.a.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
            if (installedPackages == null || installedPackages.size() <= 0) {
                return null;
            }
            List<String> arrayList = new ArrayList();
            try {
                for (PackageInfo packageInfo : installedPackages) {
                    Object obj = (packageInfo.applicationInfo.flags & 1) == 0 ? 1 : null;
                    if (z || obj != null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(",");
                        stringBuilder.append(packageInfo.packageName).append(",");
                        stringBuilder.append(packageInfo.versionName).append(",");
                        stringBuilder.append(packageInfo.versionCode).append(",");
                        if (Integer.parseInt(VERSION.SDK) < 9) {
                            stringBuilder.append(",");
                            stringBuilder.append(",");
                        } else {
                            stringBuilder.append(packageInfo.firstInstallTime).append(",");
                            stringBuilder.append(packageInfo.lastUpdateTime).append(",");
                        }
                        if (obj != null) {
                            stringBuilder.append("N");
                        } else {
                            stringBuilder.append("Y");
                        }
                        arrayList.add(stringBuilder.toString());
                    }
                }
                return arrayList;
            } catch (Exception e) {
                return arrayList;
            }
        } catch (Exception e2) {
            return null;
        }
    }
}
