package qalsdk;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.core.c;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.core.l;
import com.tencent.qalsdk.core.m;
import com.tencent.qalsdk.service.QalService;
import com.tencent.qalsdk.util.QLog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: SsoListManager */
public class f {
    public static final String a = "MSF.C.SsoListManager";
    private static final String l = "__loginSdk_ssoWifilist";
    private static final String m = "__loginSdk_ssoMobilelist";
    private static final String n = "__loginSdk_ssolist";
    private static final String o = "wifiused";
    private static final int p = 10;
    private static final String q = "wifiname";
    private static final String r = "wifitime";
    private static final String s = "wifiUsedCount";
    CopyOnWriteArrayList<c> b;
    CopyOnWriteArrayList<c> c;
    CopyOnWriteArrayList<c> d;
    CopyOnWriteArrayList<c> e;
    CopyOnWriteArrayList<c> f;
    CopyOnWriteArrayList<c> g;
    j h;
    private CopyOnWriteArrayList<c> i = new CopyOnWriteArrayList();
    private CopyOnWriteArrayList<c> j = new CopyOnWriteArrayList();
    private ConcurrentHashMap<String, Object> k = new ConcurrentHashMap();

    public f(j jVar) {
        this.h = jVar;
    }

    public void a() {
        this.b = new CopyOnWriteArrayList();
        this.c = new CopyOnWriteArrayList();
        this.b.add(c.a("socket://openmsf.3g.qq.com:443#00000:0:1"));
        this.b.add(c.a("socket://59.37.116.121:8080#00000:0:1"));
        this.b.add(c.a("socket://111.30.131.47:14000#00000:0:1"));
        this.b.add(c.a("socket://140.207.69.75:80#00000:0:1"));
        this.b.add(c.a("socket://123.151.152.102:80#00000:0:1"));
        this.b.add(c.a("socket://183.232.126.117:443#00000:0:1"));
        this.d = new CopyOnWriteArrayList();
        this.e = new CopyOnWriteArrayList();
        this.f = new CopyOnWriteArrayList();
        this.g = new CopyOnWriteArrayList();
        this.d.add(c.a("socket://183.232.126.117:14000#46000_46002:0:1"));
        this.d.add(c.a("socket://111.30.131.47:80#46000_46002:0:1"));
        this.d.add(c.a("socket://openmsf.3g.qq.com:8080#46000_46002:0:1"));
        this.d.add(c.a("socket://117.135.172.198:443#46000_46002:0:1"));
        this.d.add(c.a("socket://59.37.116.121:14000#46000_46002:0:1"));
        this.e.add(c.a("socket://163.177.56.123:14000#46001:0:1"));
        this.e.add(c.a("socket://61.135.157.230:80#46001:0:1"));
        this.e.add(c.a("socket://openmsf.3g.qq.com:8080#46001:0:1"));
        this.e.add(c.a("socket://140.207.69.75:443#46001:0:1"));
        this.e.add(c.a("socket://59.37.116.121:14000#46001:0:1"));
        this.f.add(c.a("socket://59.37.116.121:14000#46003:0:1"));
        this.f.add(c.a("socket://123.151.152.102:80#46003:0:1"));
        this.f.add(c.a("socket://openmsf.3g.qq.com:8080#46003:0:1"));
        this.f.add(c.a("socket://101.226.68.116:443#46003:0:1"));
        this.f.add(c.a("socket://163.177.56.123:14000#46003:0:1"));
        this.g.add(c.a("socket://203.205.151.207:14000#00000:0:1"));
        this.g.add(c.a("socket://openmsf.3g.qq.com:8080#00000:0:1"));
        this.g.add(c.a("socket://59.37.116.121:80#00000:0:1"));
        j();
        f();
        i();
        a(d());
    }

    public static synchronized String b() {
        String str;
        synchronized (f.class) {
            ConnectivityManager connectivityManager = (ConnectivityManager) j.a().u.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null || activeNetworkInfo.getType() != 0) {
                    str = null;
                } else {
                    if (QLog.isColorLevel()) {
                        QLog.d(a, 2, "connect to mobile network " + activeNetworkInfo.getSubtypeName());
                    }
                    str = activeNetworkInfo.getExtraInfo();
                    if (QLog.isColorLevel()) {
                        QLog.d(a, 2, "current apn:" + str);
                    }
                }
            } else {
                str = null;
            }
        }
        return str;
    }

    public static synchronized int c() {
        int i;
        synchronized (f.class) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) j.a().u.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                i = 100;
            } else {
                if (activeNetworkInfo.getType() == 0) {
                    i = activeNetworkInfo.getSubtype();
                    if (i == 7) {
                        i = 107;
                    } else if (i == 4) {
                        i = 104;
                    } else if (i == 2) {
                        i = 102;
                    } else if (i == 14) {
                        i = 114;
                    } else if (i == 5) {
                        i = 105;
                    } else if (i == 6) {
                        i = 106;
                    } else if (i == 12) {
                        i = 112;
                    } else if (i == 1) {
                        i = 101;
                    } else if (i == 8) {
                        i = 108;
                    } else if (i == 10) {
                        i = 110;
                    } else if (i == 15) {
                        i = a.cd;
                    } else if (i == 9) {
                        i = 109;
                    } else if (i == 11) {
                        i = 111;
                    } else if (i == 13) {
                        i = 113;
                    } else if (i == 3) {
                        i = 103;
                    }
                }
                i = 100;
            }
        }
        return i;
    }

    public static synchronized String d() {
        String str;
        synchronized (f.class) {
            WifiInfo connectionInfo = ((WifiManager) j.a().u.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo.getSSID() == null) {
                str = null;
            } else {
                str = connectionInfo.getSSID().replaceAll("\"", "");
                if (str.equals("<unknown ssid>")) {
                    str = null;
                }
            }
        }
        return str;
    }

    public static synchronized aq e() {
        aq aqVar;
        synchronized (f.class) {
            aq aqVar2 = new aq();
            aqVar2.a = "wifi";
            WifiInfo connectionInfo = ((WifiManager) j.a().u.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo == null) {
                aqVar = aqVar2;
            } else {
                if (!(connectionInfo.getSupplicantState() == null || connectionInfo.getSupplicantState().toString() == null)) {
                    aqVar2.b = connectionInfo.getSupplicantState().toString();
                }
                if (connectionInfo.getSSID() != null) {
                    aqVar2.c = connectionInfo.getSSID().replaceAll("\"", "");
                }
                if (connectionInfo.getBSSID() != null) {
                    aqVar2.d = connectionInfo.getBSSID();
                }
                aqVar2.e = connectionInfo.getRssi();
                aqVar = aqVar2;
            }
        }
        return aqVar;
    }

    public synchronized void f() {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.h.u.getSharedPreferences(o, 0);
            int i = sharedPreferences.getInt(s, 0);
            for (int i2 = 0; i2 < i; i2++) {
                String string = sharedPreferences.getString(q + i2, "");
                long j = sharedPreferences.getLong(r + i2, 0);
                if (string != null && j > 0) {
                    this.k.put(string, Long.valueOf(j));
                    if (QLog.isColorLevel()) {
                        QLog.d(a, 2, "get a ssid " + string + " time = " + j);
                    }
                }
            }
        }
    }

    public synchronized void a(String str) {
        if (str != null) {
            int size;
            long currentTimeMillis;
            String str2;
            SharedPreferences sharedPreferences = this.h.u.getSharedPreferences(o, 0);
            long currentTimeMillis2 = System.currentTimeMillis();
            if (this.k.containsKey(str)) {
                this.k.put(str, Long.valueOf(currentTimeMillis2));
                if (QLog.isColorLevel()) {
                    QLog.d(a, 2, "find ssid: " + str + " update time = " + currentTimeMillis2);
                }
            } else {
                size = this.k.size();
                currentTimeMillis = System.currentTimeMillis();
                String str3 = null;
                if (size >= 10) {
                    if (QLog.isColorLevel()) {
                        QLog.d(a, 2, "too much ssid need to delete one find Least Recently Used now...");
                    }
                    for (Entry entry : this.k.entrySet()) {
                        if (entry != null) {
                            long j;
                            long longValue = ((Long) entry.getValue()).longValue();
                            if (longValue < currentTimeMillis) {
                                str2 = (String) entry.getKey();
                                j = longValue;
                            } else {
                                str2 = str3;
                                j = currentTimeMillis;
                            }
                            currentTimeMillis = j;
                            str3 = str2;
                        }
                    }
                    if (str3 != null) {
                        if (QLog.isColorLevel()) {
                            QLog.d(a, 2, "Least Recently Used ssid find delete now: " + str3);
                        }
                        this.k.remove(str3);
                        l.a().n_removeConfig("__loginSdk_ssoWifilist_" + str3);
                    }
                }
                this.k.put(str, Long.valueOf(System.currentTimeMillis()));
            }
            Editor edit = sharedPreferences.edit();
            edit.putInt(s, this.k.size());
            int i = 0;
            for (Entry entry2 : this.k.entrySet()) {
                if (entry2 != null) {
                    currentTimeMillis = ((Long) entry2.getValue()).longValue();
                    str2 = (String) entry2.getKey();
                    if (str2 != null) {
                        edit.putString(q + i, str2);
                        edit.putLong(r + i, currentTimeMillis);
                        size = i + 1;
                    } else {
                        size = i;
                    }
                    i = size;
                }
            }
            edit.commit();
        }
    }

    public synchronized void g() {
        Collection arrayList = new ArrayList();
        String b = b();
        if (b != null) {
            String str = "__loginSdk_ssoMobilelist_" + b;
            String config = l.a().getConfig(str);
            Object obj = null;
            if (config == null || config.length() == 0) {
                b = l.a().getConfig(n);
                if (QLog.isColorLevel()) {
                    QLog.d(a, 2, "loadSsoStoreForCurrentAPN get a new apn, ssoStore from __loginSdk_ssolist " + b);
                }
                if (b == null || b.length() <= 0) {
                    if (QLog.isColorLevel()) {
                        QLog.d(a, 2, "loadSsoStoreForCurrentAPN a new apn get from defaultMobileSso mobile: " + str + " " + this.c);
                    }
                    arrayList.addAll(this.c);
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    a(b, arrayList2);
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        c cVar = (c) it.next();
                        if (cVar.h.indexOf(c.c) <= -1) {
                            arrayList.add(cVar);
                        } else {
                            cVar.a(true);
                        }
                    }
                }
                obj = 1;
            } else {
                if (QLog.isColorLevel()) {
                    QLog.d(a, 2, "a old apn loadSsoStoreForCurrentAPN mobile: " + str + " " + config);
                }
                if (config != null && config.length() > 0) {
                    a(config, arrayList);
                }
            }
            if (arrayList.size() > 0) {
                this.j.clear();
                this.j.addAll(arrayList);
            }
            if (obj != null) {
                StringBuffer stringBuffer = new StringBuffer();
                Iterator it2 = this.j.iterator();
                while (it2.hasNext()) {
                    stringBuffer.append(((c) it2.next()).toString() + VoiceWakeuperAidl.PARAMS_SEPARATE);
                }
                l.a().n_setConfig(str, stringBuffer.toString());
            }
            j();
        }
    }

    public synchronized void h() {
        Object obj = null;
        synchronized (this) {
            Collection arrayList = new ArrayList();
            String d = d();
            if (d != null) {
                Iterator it;
                String str = "__loginSdk_ssoWifilist_" + d;
                String config = l.a().getConfig(str);
                if (config == null || config.length() == 0) {
                    String config2 = l.a().getConfig(n);
                    if (QLog.isColorLevel()) {
                        QLog.d(a, 2, "loadSsoStoreForCurrentSSID get a new ssid, ssoStore from __loginSdk_ssolist" + config2);
                    }
                    if (config2 == null || config2.length() <= 0) {
                        if (QLog.isColorLevel()) {
                            QLog.d(a, 2, "loadSsoStoreForCurrentSSID a new ssid get from defaultWifiSso wifi: " + str + this.b);
                        }
                        arrayList.addAll(this.b);
                    } else {
                        ArrayList arrayList2 = new ArrayList();
                        a(config2, arrayList2);
                        it = arrayList2.iterator();
                        while (it.hasNext()) {
                            c cVar = (c) it.next();
                            if (cVar.h.indexOf(c.c) > -1) {
                                arrayList.add(cVar);
                            } else {
                                cVar.a(false);
                            }
                        }
                    }
                    obj = 1;
                } else {
                    if (QLog.isColorLevel()) {
                        QLog.d(a, 2, "a old ssid loadSsoStoreForCurrentSSID wifi: " + str + config);
                    }
                    if (config != null && config.length() > 0) {
                        a(config, arrayList);
                    }
                }
                if (arrayList.size() > 0) {
                    this.i.clear();
                    this.i.addAll(arrayList);
                }
                if (obj != null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    it = this.i.iterator();
                    while (it.hasNext()) {
                        stringBuffer.append(((c) it.next()).toString() + VoiceWakeuperAidl.PARAMS_SEPARATE);
                    }
                    l.a().n_setConfig(str, stringBuffer.toString());
                }
                a(d);
            }
        }
    }

    public synchronized void i() {
        Collection arrayList = new ArrayList();
        Collection arrayList2 = new ArrayList();
        String str = l;
        String d = d();
        if (d != null) {
            d = (str + "_") + d;
        } else {
            d = str;
        }
        String config = l.a().getConfig(d);
        str = m;
        String b = b();
        if (b != null) {
            str = str + "_" + b;
        }
        b = l.a().getConfig(str);
        if ((config == null || config.length() == 0) && (b == null || b.length() == 0)) {
            str = l.a().getConfig(n);
            if (QLog.isColorLevel()) {
                QLog.d(a, 2, "load ssoStore " + str);
            }
            if (str != null && str.length() > 0) {
                ArrayList arrayList3 = new ArrayList();
                a(str, arrayList3);
                Iterator it = arrayList3.iterator();
                while (it.hasNext()) {
                    c cVar = (c) it.next();
                    if (cVar.h.indexOf(c.c) > -1) {
                        cVar.a(true);
                        arrayList.add(cVar);
                    } else {
                        cVar.a(false);
                        arrayList2.add(cVar);
                    }
                }
            }
        } else {
            if (QLog.isColorLevel()) {
                QLog.d(a, 2, "load wifiStore wifi: " + d + ":" + config);
                QLog.d(a, 2, "load mobileStore " + str + ":" + b);
            }
            if (config != null && config.length() > 0) {
                a(config, arrayList);
            }
            if (b != null && b.length() > 0) {
                a(b, arrayList2);
            }
        }
        this.i.addAll(arrayList);
        this.j.addAll(arrayList2);
    }

    public synchronized void j() {
        String subscriberId;
        if (VERSION.SDK_INT < 23 || QalService.context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
            subscriberId = ((TelephonyManager) this.h.u.getSystemService("phone")).getSubscriberId();
        } else {
            subscriberId = null;
        }
        if (subscriberId == null || subscriberId.length() < 5) {
            this.c.addAll(this.b);
        } else {
            this.c.clear();
            Boolean.valueOf(true);
            int length = subscriberId.length();
            Boolean valueOf = Boolean.valueOf((length >= 3 ? subscriberId.substring(0, 3) : "").equals("460"));
            String substring = length >= 5 ? subscriberId.substring(0, 5) : "";
            if (!valueOf.booleanValue()) {
                this.c.addAll(this.g);
            } else if (substring.equals("46001") || substring.equals("46006") || substring.equals("46010")) {
                this.c.addAll(this.e);
            } else if (substring.equals("46003") || substring.equals("46005") || substring.equals("46011")) {
                this.c.addAll(this.f);
            } else {
                this.c.addAll(this.d);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(java.lang.String r9, java.util.ArrayList<com.tencent.qalsdk.core.c> r10) {
        /*
        r8 = this;
        r0 = 0;
        monitor-enter(r8);
        if (r9 == 0) goto L_0x000a;
    L_0x0004:
        r1 = r9.length();	 Catch:{ all -> 0x0053 }
        if (r1 != 0) goto L_0x000d;
    L_0x000a:
        r9 = "";
    L_0x000d:
        r1 = ";";
        r1 = r9.split(r1);	 Catch:{ all -> 0x0053 }
        r2 = r1.length;	 Catch:{ all -> 0x0053 }
    L_0x0015:
        if (r0 >= r2) goto L_0x0076;
    L_0x0017:
        r3 = r1[r0];	 Catch:{ all -> 0x0053 }
        r4 = r3.length();	 Catch:{ all -> 0x0053 }
        if (r4 <= 0) goto L_0x0043;
    L_0x001f:
        r3 = com.tencent.qalsdk.core.c.a(r3);	 Catch:{ all -> 0x0053 }
        if (r3 == 0) goto L_0x0046;
    L_0x0025:
        r4 = r3.c();	 Catch:{ all -> 0x0053 }
        r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x0053 }
        if (r4 != 0) goto L_0x0046;
    L_0x002f:
        r4 = "null";
        r5 = r3.c();	 Catch:{ all -> 0x0053 }
        r4 = r4.equals(r5);	 Catch:{ all -> 0x0053 }
        if (r4 != 0) goto L_0x0046;
    L_0x003c:
        r4 = 0;
        r3.a(r4);	 Catch:{ all -> 0x0053 }
        r10.add(r3);	 Catch:{ all -> 0x0053 }
    L_0x0043:
        r0 = r0 + 1;
        goto L_0x0015;
    L_0x0046:
        if (r3 != 0) goto L_0x0056;
    L_0x0048:
        r3 = "MSF.C.SsoListManager";
        r4 = 1;
        r5 = "found invalid endpoint: null";
        com.tencent.qalsdk.util.QLog.d(r3, r4, r5);	 Catch:{ all -> 0x0053 }
        goto L_0x0043;
    L_0x0053:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
    L_0x0056:
        r4 = "MSF.C.SsoListManager";
        r5 = 1;
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0053 }
        r6.<init>();	 Catch:{ all -> 0x0053 }
        r7 = "found invalid endpoint: ";
        r6 = r6.append(r7);	 Catch:{ all -> 0x0053 }
        r3 = r3.toString();	 Catch:{ all -> 0x0053 }
        r3 = r6.append(r3);	 Catch:{ all -> 0x0053 }
        r3 = r3.toString();	 Catch:{ all -> 0x0053 }
        com.tencent.qalsdk.util.QLog.d(r4, r5, r3);	 Catch:{ all -> 0x0053 }
        goto L_0x0043;
    L_0x0076:
        monitor-exit(r8);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: qalsdk.f.a(java.lang.String, java.util.ArrayList):void");
    }

    public synchronized void a(ArrayList<c> arrayList, boolean z, boolean z2) {
        if (this.h.d.a.p().equals("") && !a.at) {
            this.i.clear();
            StringBuffer stringBuffer = new StringBuffer();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (cVar != null && !TextUtils.isEmpty(cVar.c()) && !"null".equals(cVar.c())) {
                    stringBuffer.append(cVar.toString() + VoiceWakeuperAidl.PARAMS_SEPARATE);
                    this.i.add(cVar);
                } else if (cVar == null) {
                    QLog.d(a, 1, "found invalid wifi endpoint: null");
                } else {
                    QLog.d(a, 1, "found invalid wifi endpoint: " + cVar.toString());
                }
            }
            String stringBuffer2 = stringBuffer.toString();
            String d = d();
            if (d != null) {
                l.a().n_setConfig("__loginSdk_ssoWifilist_" + d, stringBuffer2);
            }
        } else if (QLog.isDevelopLevel()) {
            QLog.d(a, 4, "debug mode, skip store sso ");
        }
    }

    public synchronized void b(ArrayList<c> arrayList, boolean z, boolean z2) {
        if (this.h.d.a.p().equals("") && !a.at) {
            this.j.clear();
            StringBuffer stringBuffer = new StringBuffer();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (cVar != null && !TextUtils.isEmpty(cVar.c()) && !"null".equals(cVar.c())) {
                    cVar.a(false);
                    stringBuffer.append(cVar.toString() + VoiceWakeuperAidl.PARAMS_SEPARATE);
                    this.j.add(cVar);
                } else if (cVar == null) {
                    QLog.d(a, 1, "found invalid mobile endpoint: null");
                } else {
                    QLog.d(a, 1, "found invalid mobile endpoint: " + cVar.toString());
                }
            }
            String stringBuffer2 = stringBuffer.toString();
            String b = b();
            if (b != null) {
                b = "__loginSdk_ssoMobilelist_" + b;
                l.a().n_setConfig(b, stringBuffer2);
                if (z2) {
                    QLog.d(a, 1, "save push mobilesso for " + b + " " + stringBuffer2);
                } else {
                    QLog.d(a, 1, "save http mobilesso for " + b + " " + stringBuffer2);
                }
            }
        } else if (QLog.isDevelopLevel()) {
            QLog.d(a, 4, "debug mode, skip store sso ");
        }
    }

    public synchronized void a(ArrayList<c> arrayList) {
        if (arrayList != null) {
            if (arrayList.size() != 0) {
                this.i.clear();
                this.j.clear();
                this.b.clear();
                this.c.clear();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    this.j.add((c) it.next());
                }
                it = arrayList.iterator();
                while (it.hasNext()) {
                    this.i.add((c) it.next());
                }
            }
        }
        if (QLog.isColorLevel()) {
            QLog.w(a, 2, "setTestSso serviceLists is null...");
        }
        throw new RuntimeException("setTestSso serviceLists is null...");
    }

    public void a(FromServiceMsg fromServiceMsg) {
        if (QLog.isColorLevel()) {
            QLog.d(a, 2, "onRecvSsoHelloPush from:" + fromServiceMsg);
        }
        try {
            ToServiceMsg toServiceMsg = new ToServiceMsg("", fromServiceMsg.getUin(), a.aD);
            toServiceMsg.putWupBuffer(fromServiceMsg.getWupBuffer());
            toServiceMsg.setAppId(this.h.i());
            toServiceMsg.setNeedCallback(false);
            toServiceMsg.setTimeout(30000);
            toServiceMsg.setRequestSsoSeq(fromServiceMsg.getRequestSsoSeq());
            try {
                this.h.a(toServiceMsg);
            } catch (Throwable e) {
                if (QLog.isColorLevel()) {
                    QLog.i(a, 2, "send sso hello push resp error " + e, e);
                }
            }
        } catch (Throwable e2) {
            if (QLog.isColorLevel()) {
                QLog.i(a, 2, "onRecvSsoHelloPush error " + e2, e2);
            }
        }
    }

    public CopyOnWriteArrayList<c> k() {
        if (this.i.size() == 0) {
            return this.b;
        }
        return this.i;
    }

    public CopyOnWriteArrayList<c> l() {
        if (this.j.size() == 0) {
            return this.c;
        }
        return this.j;
    }

    public synchronized void a(c cVar) {
        if (m.e()) {
            if (!b(cVar)) {
                c(cVar);
            }
        } else if (!m.f()) {
            b(cVar);
            c(cVar);
        } else if (!c(cVar)) {
            b(cVar);
        }
    }

    private boolean b(c cVar) {
        if (this.i.remove(cVar)) {
            this.i.add(cVar);
            QLog.d(a, 1, " set " + cVar + " at wifiList last.");
            return true;
        }
        QLog.d(a, 1, " not found " + cVar + " at wifiList.");
        return false;
    }

    private boolean c(c cVar) {
        if (this.j.remove(cVar)) {
            this.j.add(cVar);
            QLog.d(a, 1, " set " + cVar + " at mobileList last.");
            return true;
        }
        QLog.d(a, 1, " not found " + cVar + " at mobileList.");
        return false;
    }
}
