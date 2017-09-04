package com.tencent.qalsdk.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import com.tencent.qalsdk.base.CloseConnReason;

public class NetConnInfoCenter extends BroadcastReceiver {
    public static String RDMREPORT_INTENT = "com.tencent.mobileqq.rdm.report";
    public static final String TAG = NetConnInfoCenter.class.getSimpleName();
    public static m impl = new m();
    private static boolean sHasBooted = false;
    public static long servetTimeSecondInterv = 0;
    public static volatile int socketConnState = 0;

    public static long getServerTime() {
        return (System.currentTimeMillis() / 1000) + servetTimeSecondInterv;
    }

    public static long getServerTimeMillis() {
        return System.currentTimeMillis() + (servetTimeSecondInterv * 1000);
    }

    public static void init(j jVar) {
        m.a(jVar);
        impl.a(jVar.u);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r7, android.content.Intent r8) {
        /*
        r6 = this;
        r0 = 1;
        r4 = 2;
        if (r8 != 0) goto L_0x0013;
    L_0x0004:
        r0 = com.tencent.qalsdk.util.QLog.isColorLevel();
        if (r0 == 0) goto L_0x0012;
    L_0x000a:
        r0 = com.tencent.qalsdk.core.m.a;
        r1 = "receive broadcast intent == null return";
        com.tencent.qalsdk.util.QLog.d(r0, r4, r1);
    L_0x0012:
        return;
    L_0x0013:
        r1 = com.tencent.qalsdk.util.QLog.isColorLevel();
        if (r1 == 0) goto L_0x0032;
    L_0x0019:
        r1 = com.tencent.qalsdk.core.m.a;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "receive broadcast ";
        r2 = r2.append(r3);
        r2 = r2.append(r8);
        r2 = r2.toString();
        com.tencent.qalsdk.util.QLog.d(r1, r4, r2);
    L_0x0032:
        r1 = r8.getAction();
        if (r1 != 0) goto L_0x0047;
    L_0x0038:
        r0 = com.tencent.qalsdk.util.QLog.isColorLevel();
        if (r0 == 0) goto L_0x0012;
    L_0x003e:
        r0 = com.tencent.qalsdk.core.m.a;
        r1 = "receive broadcast intent.getAction() == null return";
        com.tencent.qalsdk.util.QLog.d(r0, r4, r1);
        goto L_0x0012;
    L_0x0047:
        r1 = 0;
        r2 = sHasBooted;
        if (r2 != 0) goto L_0x016a;
    L_0x004c:
        sHasBooted = r0;
        r2 = android.os.SystemClock.elapsedRealtime();
        r4 = 300000; // 0x493e0 float:4.2039E-40 double:1.482197E-318;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 >= 0) goto L_0x016a;
    L_0x0059:
        r1 = com.tencent.qalsdk.service.QalService.inited;
        if (r1 != 0) goto L_0x0091;
    L_0x005d:
        r1 = com.tencent.qalsdk.core.m.a;	 Catch:{ RuntimeException -> 0x00e2 }
        r2 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x00e2 }
        r2.<init>();	 Catch:{ RuntimeException -> 0x00e2 }
        r3 = "receive broadcast, boot qalservice|";
        r2 = r2.append(r3);	 Catch:{ RuntimeException -> 0x00e2 }
        r2 = r2.append(r0);	 Catch:{ RuntimeException -> 0x00e2 }
        r3 = ":";
        r2 = r2.append(r3);	 Catch:{ RuntimeException -> 0x00e2 }
        r3 = r8.getAction();	 Catch:{ RuntimeException -> 0x00e2 }
        r2 = r2.append(r3);	 Catch:{ RuntimeException -> 0x00e2 }
        r2 = r2.toString();	 Catch:{ RuntimeException -> 0x00e2 }
        com.tencent.qalsdk.util.QLog.e(r1, r2);	 Catch:{ RuntimeException -> 0x00e2 }
        com.tencent.qalsdk.service.QalService.sIsCreatedByAutoBoot = r0;	 Catch:{ RuntimeException -> 0x00e2 }
        r1 = new android.content.Intent;	 Catch:{ RuntimeException -> 0x00e2 }
        r2 = com.tencent.qalsdk.service.QalService.class;
        r1.<init>(r7, r2);	 Catch:{ RuntimeException -> 0x00e2 }
        r7.startService(r1);	 Catch:{ RuntimeException -> 0x00e2 }
    L_0x0091:
        r1 = r8.getAction();	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        r2 = "android.net.conn.CONNECTIVITY_CHANGE";
        r1 = r1.equals(r2);	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        if (r1 == 0) goto L_0x0101;
    L_0x009e:
        r0 = "networkInfo";
        r0 = r8.getParcelableExtra(r0);	 Catch:{ Exception -> 0x00af }
        r0 = (android.net.NetworkInfo) r0;	 Catch:{ Exception -> 0x00af }
        r1 = impl;	 Catch:{ Exception -> 0x00af }
        r2 = 1;
        r1.a(r7, r0, r2);	 Catch:{ Exception -> 0x00af }
        goto L_0x0012;
    L_0x00af:
        r0 = move-exception;
        r1 = com.tencent.qalsdk.util.QLog.isColorLevel();	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        if (r1 == 0) goto L_0x0012;
    L_0x00b6:
        r1 = com.tencent.qalsdk.core.m.a;	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        r2 = 2;
        r3 = r0.toString();	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        com.tencent.qalsdk.util.QLog.d(r1, r2, r3, r0);	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        goto L_0x0012;
    L_0x00c2:
        r0 = move-exception;
        r1 = com.tencent.qalsdk.core.m.a;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "onReceive native not find";
        r2 = r2.append(r3);
        r0 = r0.getMessage();
        r0 = r2.append(r0);
        r0 = r0.toString();
        com.tencent.qalsdk.util.QLog.e(r1, r0);
        goto L_0x0012;
    L_0x00e2:
        r1 = move-exception;
        r2 = com.tencent.qalsdk.core.m.a;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "startService exception:";
        r3 = r3.append(r4);
        r1 = r1.getMessage();
        r1 = r3.append(r1);
        r1 = r1.toString();
        com.tencent.qalsdk.util.QLog.e(r2, r1);
        goto L_0x0091;
    L_0x0101:
        r1 = r8.getAction();	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        r2 = "android.intent.action.TIME_SET";
        r1 = r1.equals(r2);	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        if (r1 != 0) goto L_0x0128;
    L_0x010e:
        r1 = r8.getAction();	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        r2 = "android.intent.action.DATE_CHANGED";
        r1 = r1.equals(r2);	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        if (r1 != 0) goto L_0x0128;
    L_0x011b:
        r1 = r8.getAction();	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        r2 = "android.intent.action.TIMEZONE_CHANGED";
        r1 = r1.equals(r2);	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        if (r1 == 0) goto L_0x0153;
    L_0x0128:
        r0 = com.tencent.qalsdk.util.QLog.isColorLevel();	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        if (r0 == 0) goto L_0x014c;
    L_0x012e:
        r0 = com.tencent.qalsdk.core.m.a;	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        r1 = 2;
        r2 = new java.lang.StringBuilder;	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        r2.<init>();	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        r3 = "recv broadcast ";
        r2 = r2.append(r3);	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        r3 = r8.getAction();	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        r2 = r2.append(r3);	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        r2 = r2.toString();	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        com.tencent.qalsdk.util.QLog.d(r0, r1, r2);	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
    L_0x014c:
        r0 = impl;	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        r0.o();	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        goto L_0x0012;
    L_0x0153:
        if (r0 == 0) goto L_0x0012;
    L_0x0155:
        r0 = 1;
        qalsdk.d.a(r0);	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        r0 = com.tencent.qalsdk.util.QLog.isColorLevel();	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        if (r0 == 0) goto L_0x0012;
    L_0x015f:
        r0 = com.tencent.qalsdk.core.m.a;	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        r1 = 2;
        r2 = "set StatReporter.needReportBooting true";
        com.tencent.qalsdk.util.QLog.d(r0, r1, r2);	 Catch:{ UnsatisfiedLinkError -> 0x00c2 }
        goto L_0x0012;
    L_0x016a:
        r0 = r1;
        goto L_0x0059;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.qalsdk.core.NetConnInfoCenter.onReceive(android.content.Context, android.content.Intent):void");
    }

    public static String getSignalStrengthsLog() {
        return " SignalStrengths wifi:" + getWifiStrength() + " gsm:" + getGsmStrength() + " cdma:" + getCdmaStrength() + " net:" + m.n();
    }

    public static void checkConnInfo(Context context) {
        impl.a(context, null);
    }

    public static void checkConnInfo(Context context, boolean z) {
        impl.a(context, null, z);
    }

    public static void onConnOpened(String str, String str2) {
        impl.a(str, str2);
    }

    public static void onRecvFirstResp() {
        impl.i();
    }

    public static void onOepnConnAllFailed() {
        impl.g();
    }

    public static void onConnClosed(CloseConnReason closeConnReason) {
        impl.a(closeConnReason);
    }

    public static void handleGetServerTimeResp(long j) {
        impl.a(j);
    }

    public static int getWifiStrength() {
        if (impl.g == 0 && m.e()) {
            impl.q();
        }
        return impl.g;
    }

    public static int getCdmaStrength() {
        return impl.i;
    }

    public static int getGsmStrength() {
        return impl.h;
    }

    public static boolean isGSM() {
        return impl.j;
    }
}
