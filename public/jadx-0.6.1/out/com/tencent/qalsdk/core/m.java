package com.tencent.qalsdk.core;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import com.tencent.android.tpush.common.Constants;
import com.tencent.qalsdk.base.CloseConnReason;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.sdk.MsfCommand;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qalsdk.sdk.v;
import com.tencent.qalsdk.util.QLog;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicBoolean;
import qalsdk.aj;
import qalsdk.d;
import qalsdk.f;

/* compiled from: NetConnInfoCenterImpl */
public class m {
    static String a = "MSF.C.NetConnInfoCenter";
    public static j b = null;
    public static final String c = "recordSysTimeKey";
    static boolean d = false;
    static String e = "";
    static String f = "";
    private static int m = 0;
    private static String n = null;
    private static String o = null;
    private static AtomicBoolean p = new AtomicBoolean(false);
    private static final String q = "servetTimeDiff";
    private static long r = -1;
    private static long s = 0;
    private static AtomicBoolean t = new AtomicBoolean();
    private static long u = 0;
    private static final byte v = (byte) 0;
    private static final byte w = (byte) 1;
    private static int x = 0;
    private long A = 0;
    int g;
    int h;
    int i;
    boolean j;
    public b k = new b();
    boolean l = true;
    private long y = 0;
    private int z = 0;

    /* compiled from: NetConnInfoCenterImpl */
    private class a extends PhoneStateListener {
        final /* synthetic */ m a;

        private a(m mVar) {
            this.a = mVar;
        }

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            this.a.i = signalStrength.getCdmaDbm();
            int gsmSignalStrength = signalStrength.getGsmSignalStrength();
            if (gsmSignalStrength == 99) {
                gsmSignalStrength = -3;
            }
            this.a.h = gsmSignalStrength;
            this.a.j = signalStrength.isGsm();
            this.a.q();
        }
    }

    /* compiled from: NetConnInfoCenterImpl */
    private class b extends BroadcastReceiver {
        final /* synthetic */ m a;

        private b(m mVar) {
            this.a = mVar;
        }

        public void onReceive(Context context, Intent intent) {
            this.a.q();
        }
    }

    static void a(j jVar) {
        b = jVar;
        NetConnInfoCenter.servetTimeSecondInterv = b.u.getSharedPreferences(a, 0).getLong(q, 0);
    }

    public static void a() {
        u = System.currentTimeMillis();
        t.set(true);
    }

    private static void r() {
        t.set(false);
        u = 0;
    }

    private static String c(int i) {
        switch (i) {
            case 0:
                return "NONE";
            case 1:
                return "MOBILE";
            case 2:
                return "WiFi";
            case 3:
                return "WiFi";
            default:
                return "UNKNOWN";
        }
    }

    public synchronized void a(int i) {
        Object obj = null;
        synchronized (this) {
            String d = f.d();
            String b = f.b();
            if (m == i) {
                if (QLog.isColorLevel()) {
                    QLog.d(a, 2, "found repeat net event , now is " + b() + " now:" + i + " last:" + m);
                }
                if (d != null && (n == null || !n.equals(d))) {
                    if (QLog.isColorLevel()) {
                        QLog.d(a, 2, "new ssid :  " + d + " old ssid: " + n);
                    }
                    n = d;
                    if (b != null) {
                        b.g.h();
                        s();
                    }
                }
                if (b != null && (o == null || !o.equals(b))) {
                    if (QLog.isColorLevel()) {
                        QLog.d(f.a, 2, "Mobile APN changed, load sso list new apn :  " + b + " old apn: " + o);
                    }
                    o = b;
                    if (b != null) {
                        b.g.g();
                    }
                }
            } else {
                if (i > 0) {
                    a(true);
                    if (t.get()) {
                        r();
                    }
                } else {
                    a(false);
                }
                QLog.d(a, 1, "netchange " + c(m) + " to " + c(i));
                q();
                o.b("");
                o.b(0);
                int i2;
                if (i == 1) {
                    if (!(m == 0 || m == -2 || (m != 2 && m != 3))) {
                        if (b != null && (o == null || !o.equals(b))) {
                            if (QLog.isColorLevel()) {
                                QLog.d(f.a, 2, "WIFI to Mobile load mobile sso list new apn :  " + b + " old apn: " + o);
                            }
                            o = b;
                            if (b != null) {
                                b.g.g();
                            }
                        }
                        i2 = 1;
                    }
                } else if (i != 2 && i != 3) {
                    i2 = 1;
                } else if (!(m == 0 || m == -2 || m != 1)) {
                    if (d != null && (n == null || !n.equals(d))) {
                        if (QLog.isColorLevel()) {
                            QLog.d(a, 2, "new ssid :  " + d + " old ssid: " + n);
                        }
                        n = d;
                        if (b != null) {
                            b.g.h();
                        }
                    }
                    i2 = 1;
                }
                m = i;
                s();
                if (obj != null) {
                    t();
                }
                if (!(i <= 0 || b == null || b.e == null)) {
                    b.e.c();
                }
            }
        }
    }

    private void s() {
        if (e()) {
            if (b != null && b.d != null) {
                b.d.a.g.a(n);
            }
        } else if (b != null && b.d != null) {
            b.d.a.g.a();
        }
    }

    private void t() {
        if (b != null && b.d != null) {
            b.d.a(CloseConnReason.netChange);
        }
    }

    public static boolean b() {
        return p.get();
    }

    public static int c() {
        if (e()) {
            return 2;
        }
        if (f()) {
            return 1;
        }
        return 0;
    }

    public static boolean d() {
        return e() || f();
    }

    public static boolean e() {
        return m == 2 || m == 3;
    }

    public static boolean f() {
        return m == 1;
    }

    public void g() {
        NetConnInfoCenter.socketConnState = 3;
        FromServiceMsg fromServiceMsg = new FromServiceMsg(b.i(), j.f(), "0", com.tencent.qalsdk.base.a.af);
        fromServiceMsg.setMsgSuccess();
        fromServiceMsg.setRequestSsoSeq(j.f());
        fromServiceMsg.setMsfCommand(MsfCommand.onOepnConnAllFailed);
        if (b.e.b()) {
            fromServiceMsg.addAttribute(v.m, Integer.valueOf(1));
        }
        MsfSdkUtils.addFromMsgProcessName(v.n, fromServiceMsg);
        b.a(null, fromServiceMsg);
        b.d.a.g.b();
    }

    public static String h() {
        return n;
    }

    public void i() {
        if (e()) {
            b.d.a.g.b(n);
        }
        NetConnInfoCenter.socketConnState = 4;
        FromServiceMsg fromServiceMsg = new FromServiceMsg(b.i(), j.f(), "0", com.tencent.qalsdk.base.a.ae);
        fromServiceMsg.setMsgSuccess();
        fromServiceMsg.setRequestSsoSeq(j.f());
        fromServiceMsg.setMsfCommand(MsfCommand.onReceFirstResp);
        if (b.e.b()) {
            fromServiceMsg.addAttribute(v.m, Integer.valueOf(1));
        }
        MsfSdkUtils.addFromMsgProcessName(v.n, fromServiceMsg);
        b.a(null, fromServiceMsg);
    }

    public void a(String str, String str2) {
        NetConnInfoCenter.socketConnState = 2;
        b.e.d();
        FromServiceMsg fromServiceMsg = new FromServiceMsg(b.i(), j.f(), "0", com.tencent.qalsdk.base.a.ad);
        fromServiceMsg.setMsgSuccess();
        fromServiceMsg.setRequestSsoSeq(j.f());
        fromServiceMsg.setMsfCommand(MsfCommand.onConnOpened);
        fromServiceMsg.addAttribute(v.k, str);
        fromServiceMsg.addAttribute(v.l, str2);
        if (b.e.b()) {
            fromServiceMsg.addAttribute(v.m, Integer.valueOf(1));
        }
        MsfSdkUtils.addFromMsgProcessName(v.n, fromServiceMsg);
        b.a(null, fromServiceMsg);
    }

    public void a(CloseConnReason closeConnReason) {
        NetConnInfoCenter.socketConnState = 1;
        b.e.a(closeConnReason);
        FromServiceMsg fromServiceMsg = new FromServiceMsg(b.i(), j.f(), "0", com.tencent.qalsdk.base.a.Z);
        fromServiceMsg.setMsgSuccess();
        fromServiceMsg.setMsfCommand(MsfCommand.onConnClosed);
        MsfSdkUtils.addFromMsgProcessName(v.n, fromServiceMsg);
        b.a(null, fromServiceMsg);
    }

    public static void a(boolean z) {
        p.set(z);
        if (QLog.isColorLevel()) {
            QLog.d(a, 2, "setNetSupport " + p.get());
        }
    }

    public void a(Context context, NetworkInfo networkInfo) {
        a(context, networkInfo, false);
    }

    public void a(Context context, NetworkInfo networkInfo, boolean z) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        a(connectivityManager.getActiveNetworkInfo(), networkInfo);
        try {
            NetworkInfo networkInfo2;
            NetworkInfo networkInfo3 = connectivityManager.getNetworkInfo(0);
            if (networkInfo3 == null) {
                networkInfo2 = connectivityManager.getNetworkInfo(50);
            } else {
                networkInfo2 = networkInfo3;
            }
            if (networkInfo2 != null) {
                e = networkInfo2.getExtraInfo();
            }
        } catch (Exception e) {
            QLog.d(a, 1, "get currentAPN error " + e);
        }
    }

    public void a(NetworkInfo networkInfo, NetworkInfo networkInfo2) {
        if (networkInfo != null) {
            String typeName = networkInfo.getTypeName();
            try {
                f = networkInfo.getSubtypeName();
            } catch (Exception e) {
                QLog.d(a, 1, "get subtypeName error " + e);
            }
            if (!typeName.toLowerCase().contains("mobile_mms")) {
                QLog.d(a, 1, "currentAPN:" + e + ". received networkInfo: " + networkInfo.getDetailedState() + " :" + networkInfo + ". extra NetworkInfo: " + networkInfo2);
                if (networkInfo.isAvailable() && networkInfo.getDetailedState() == DetailedState.CONNECTED) {
                    if (1 == networkInfo.getType()) {
                        u();
                        return;
                    } else if (a(networkInfo)) {
                        w();
                        b(networkInfo);
                        return;
                    } else {
                        return;
                    }
                } else if (networkInfo.getDetailedState() != DetailedState.DISCONNECTED && networkInfo.getDetailedState() != DetailedState.FAILED && networkInfo.getDetailedState() != DetailedState.IDLE && networkInfo.getDetailedState() != DetailedState.SUSPENDED) {
                    return;
                } else {
                    if (1 == networkInfo.getType()) {
                        w();
                        return;
                    } else if (a(networkInfo)) {
                        v();
                        return;
                    } else {
                        return;
                    }
                }
            }
            return;
        }
        if (QLog.isColorLevel()) {
            QLog.d(a, 2, "currentAPN:" + e + ". active NetworkInfo: " + networkInfo + ". extra NetworkInfo: " + networkInfo2);
        }
        if (networkInfo2 == null) {
            return;
        }
        if (networkInfo2.getDetailedState() != DetailedState.DISCONNECTED && networkInfo2.getDetailedState() != DetailedState.FAILED && networkInfo2.getDetailedState() != DetailedState.IDLE && networkInfo2.getDetailedState() != DetailedState.SUSPENDED) {
            return;
        }
        if (1 == networkInfo2.getType()) {
            w();
        } else if (a(networkInfo2)) {
            v();
        }
    }

    public static int j() {
        return x;
    }

    private void b(NetworkInfo networkInfo) {
        if (networkInfo != null) {
            x = networkInfo.getSubtype();
        } else {
            try {
                x = ((TelephonyManager) b.u.getSystemService("phone")).getNetworkType();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        a(m | 1);
    }

    private void u() {
        WifiInfo connectionInfo = ((WifiManager) b.u.getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo != null) {
            int ipAddress = connectionInfo.getIpAddress();
            if (QLog.isColorLevel()) {
                QLog.d(a, 2, "wifiConnected localAddress " + ipAddress + "(" + b(ipAddress).getHostAddress() + "), " + connectionInfo.toString());
            }
        }
        a(m | 2);
    }

    private void v() {
        a(m & -2);
    }

    private void w() {
        a(m & -3);
    }

    public static boolean k() {
        return d;
    }

    public static void b(boolean z) {
        d = z;
    }

    public static String l() {
        return e;
    }

    public static String m() {
        return f;
    }

    public static int n() {
        if (e()) {
            return m;
        }
        if (f()) {
            return j() + Constants.ERRORCODE_UNKNOWN;
        }
        return 0;
    }

    public static InetAddress b(int i) {
        try {
            return InetAddress.getByAddress(new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)});
        } catch (UnknownHostException e) {
            throw new AssertionError();
        }
    }

    public static boolean a(NetworkInfo networkInfo) {
        return networkInfo.getType() == 0 || 50 == networkInfo.getType();
    }

    public static void a(String str) {
        try {
            if (aj.m) {
                Intent intent = new Intent(str);
                String str2 = aj.n;
                int b = b.b(str2);
                intent.putExtra("uin", str2);
                intent.putExtra("istatus", b);
                intent.putExtra("gatewayip", o.m());
                ((AlarmManager) b.u.getSystemService("alarm")).set(0, System.currentTimeMillis() + d.s(), PendingIntent.getBroadcast(b.u, 0, intent, 0));
                QLog.d(a, 1, "send bootAction for QQ " + str2);
                aj.m = false;
                return;
            }
            QLog.d(a, 1, "not need send bootAction for QQ");
        } catch (Exception e) {
            QLog.d(a, 1, "send bootAction for QQ error " + e);
        }
    }

    public void o() {
        System.currentTimeMillis();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.A <= 0 || elapsedRealtime - this.A > d.i()) {
            if (this.z < 10) {
                y();
                this.z++;
                this.A = elapsedRealtime;
            } else if (QLog.isColorLevel()) {
                QLog.d(a, 2, "also send checkTimeMsg " + this.z);
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (this.y == 0 || elapsedRealtime - this.y > 600000) {
                this.y = elapsedRealtime;
                this.z = 0;
            }
            r = currentTimeMillis;
            if (l.a() != null) {
                l.a().n_setConfig(c, String.valueOf(currentTimeMillis));
            }
        } else if (QLog.isColorLevel()) {
            QLog.d(a, 2, "quit to checkTimeMsg too frequency.");
        }
    }

    public void p() {
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (0 != s && elapsedRealtime > s + d.l()) {
                s = elapsedRealtime;
                if (-1 == r && l.a() != null) {
                    String config = l.a().getConfig(c);
                    if (config != null) {
                        try {
                            r = Long.parseLong(config);
                        } catch (Exception e) {
                            if (QLog.isColorLevel()) {
                                QLog.d(a, 2, "get lastCheckTime catch Exception " + e);
                            }
                        }
                    }
                }
                elapsedRealtime = System.currentTimeMillis();
                if (-1 == r) {
                    r = elapsedRealtime;
                    if (l.a() != null) {
                        l.a().n_setConfig(c, String.valueOf(r));
                    }
                } else if (((double) elapsedRealtime) > ((double) (r + d.j())) + (Math.random() * ((double) d.k()))) {
                    o();
                }
            } else if (0 == s) {
                s = elapsedRealtime;
            }
        } catch (Exception e2) {
            if (QLog.isColorLevel()) {
                QLog.d(a, 2, "checkRecordTime catch Exception " + e2);
            }
        }
    }

    private void x() {
        if (b == null && QLog.isDevelopLevel()) {
            QLog.d(a, 4, "msfCore not inited. can not send checkServerTimeMsg.");
        }
    }

    private void y() {
        if (b != null) {
            ToServiceMsg toServiceMsg = new ToServiceMsg("", "0", com.tencent.qalsdk.base.a.ah);
            toServiceMsg.setMsfCommand(MsfCommand.getServerTime);
            toServiceMsg.setAppId(b.d.j());
            toServiceMsg.setTimeout(com.tencent.qalsdk.base.a.ap);
            toServiceMsg.setRequestSsoSeq(j.f());
            toServiceMsg.addAttribute(com.tencent.qalsdk.base.a.ao, Boolean.valueOf(true));
            toServiceMsg.putWupBuffer(MsfSdkUtils.convertInt2Bytes(4));
            b.d.b(toServiceMsg);
        } else if (QLog.isDevelopLevel()) {
            QLog.d(a, 4, "msfCore not inited. can not send checkServerTimeMsg.");
        }
    }

    public void a(long j) {
        if (QLog.isDevelopLevel()) {
            QLog.e(a, 4, "handleGetServerTimeResp servertime is " + j);
        }
        NetConnInfoCenter.servetTimeSecondInterv = j - (System.currentTimeMillis() / 1000);
        Editor edit = b.u.getSharedPreferences(a, 0).edit();
        edit.putLong(q, NetConnInfoCenter.servetTimeSecondInterv);
        edit.commit();
        if (QLog.isColorLevel()) {
            QLog.d(a, 2, "set serverTime is " + b.n.format(Long.valueOf(System.currentTimeMillis() + (NetConnInfoCenter.servetTimeSecondInterv * 1000))));
        }
        j.a(v.n, b.d.j());
    }

    public void a(Context context) {
        ((TelephonyManager) context.getSystemService("phone")).listen(new a(), 256);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.RSSI_CHANGED");
        context.registerReceiver(this.k, intentFilter);
    }

    synchronized void q() {
        if (this.l) {
            try {
                WifiManager wifiManager = (WifiManager) b.u.getSystemService("wifi");
                if (wifiManager != null) {
                    WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                    if (((connectionInfo.getBSSID() != null ? 1 : 0) & (connectionInfo != null ? 1 : 0)) != 0) {
                        this.g = WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 32);
                    }
                }
            } catch (Throwable e) {
                QLog.d(a, 1, "check WifiState error " + e, e);
                this.l = false;
            }
        } else {
            this.g = 0;
        }
    }
}
