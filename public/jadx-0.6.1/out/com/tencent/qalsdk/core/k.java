package com.tencent.qalsdk.core;

import android.content.Context;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.tencent.mid.api.MidEntity;
import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qalsdk.sdk.v;
import com.tencent.qalsdk.service.QalService;
import com.tencent.qalsdk.util.QLog;
import java.io.File;
import java.util.Properties;

/* compiled from: MsfCoreUtil */
public class k {
    public static final String a = "MSF.C.Util";
    private static String b = "";
    private static String c = "";
    private static String d = "";
    private static String e = "";

    public static FromServiceMsg a(ToServiceMsg toServiceMsg) {
        FromServiceMsg fromServiceMsg = new FromServiceMsg(toServiceMsg.getUin(), toServiceMsg.getServiceCmd());
        fromServiceMsg.setAppId(toServiceMsg.getAppId());
        fromServiceMsg.setAppSeq(toServiceMsg.getAppSeq());
        fromServiceMsg.setRequestSsoSeq(toServiceMsg.getRequestSsoSeq());
        fromServiceMsg.setMsfCommand(toServiceMsg.getMsfCommand());
        fromServiceMsg.addAttribute(v.c, toServiceMsg.getAttribute(v.c));
        fromServiceMsg.addAttribute(a.aL, Long.valueOf(System.currentTimeMillis()));
        return fromServiceMsg;
    }

    public static void a(Context context) {
        String str = null;
        try {
            if (VERSION.SDK_INT < 23 || context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                c = telephonyManager.getSubscriberId();
                str = telephonyManager.getDeviceId();
            } else {
                c = null;
            }
            if (str != null && str.length() > 0) {
                b = str;
                a(str);
                QLog.i(a, 2, "read sys imei:" + b);
            }
        } catch (Throwable e) {
            QLog.d(a, 1, "read sys imei error " + e, e);
        }
        if (b == null || b.length() == 0) {
            b = e();
            QLog.i(a, 2, "load imei:" + b);
        }
    }

    public static String a() {
        return b;
    }

    public static String b() {
        return c;
    }

    public static String c() {
        return d;
    }

    public static String d() {
        return e;
    }

    public static void a(String str) {
        File file = new File(j.a().i);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.exists()) {
                Properties loadConfig = MsfSdkUtils.loadConfig(j.a().i);
                loadConfig.put(MidEntity.TAG_IMEI, str);
                MsfSdkUtils.saveConfig(j.a().i, loadConfig);
                return;
            }
            QLog.d(a, 1, "can not create imei file");
        } catch (Throwable e) {
            QLog.d(a, 1, "save sys imei error", e);
        }
    }

    private static String f() {
        String string = Secure.getString(QalService.context.getContentResolver(), "android_id");
        QLog.i(a, "ANDROID_ID:" + string);
        if (string == null || !string.equals("9774d56d682e549c")) {
            return string;
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String e() {
        /*
        r5 = 1;
        r0 = 0;
        r1 = new java.io.File;
        r2 = com.tencent.qalsdk.core.j.a();
        r2 = r2.i;
        r1.<init>(r2);
        r2 = r1.exists();	 Catch:{ Exception -> 0x004d }
        if (r2 == 0) goto L_0x0044;
    L_0x0013:
        r0 = "MSF.C.Util";
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004d }
        r2.<init>();	 Catch:{ Exception -> 0x004d }
        r3 = "file exit:";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x004d }
        r3 = com.tencent.qalsdk.core.j.a();	 Catch:{ Exception -> 0x004d }
        r3 = r3.i;	 Catch:{ Exception -> 0x004d }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x004d }
        r2 = r2.toString();	 Catch:{ Exception -> 0x004d }
        com.tencent.qalsdk.util.QLog.i(r0, r2);	 Catch:{ Exception -> 0x004d }
        r0 = com.tencent.qalsdk.core.j.a();	 Catch:{ Exception -> 0x004d }
        r0 = r0.i;	 Catch:{ Exception -> 0x004d }
        r0 = com.tencent.qalsdk.sdk.MsfSdkUtils.loadConfig(r0);	 Catch:{ Exception -> 0x004d }
        r2 = "imei";
        r0 = r0.getProperty(r2);	 Catch:{ Exception -> 0x004d }
    L_0x0044:
        if (r0 == 0) goto L_0x0057;
    L_0x0046:
        r2 = r0.length();	 Catch:{ Exception -> 0x004d }
        if (r2 <= 0) goto L_0x0057;
    L_0x004c:
        return r0;
    L_0x004d:
        r0 = move-exception;
        r2 = "MSF.C.Util";
        r3 = "load sys imei error";
        com.tencent.qalsdk.util.QLog.d(r2, r5, r3, r0);
    L_0x0057:
        r0 = f();
        if (r0 != 0) goto L_0x007c;
    L_0x005d:
        r2 = new java.lang.StringBuffer;
        r2.<init>();
        r0 = 0;
    L_0x0063:
        r3 = 15;
        if (r0 >= r3) goto L_0x0078;
    L_0x0067:
        r3 = new java.util.Random;
        r3.<init>();
        r4 = 10;
        r3 = r3.nextInt(r4);
        r2.append(r3);
        r0 = r0 + 1;
        goto L_0x0063;
    L_0x0078:
        r0 = r2.toString();
    L_0x007c:
        r2 = r1.exists();	 Catch:{ Exception -> 0x00c0 }
        if (r2 != 0) goto L_0x0085;
    L_0x0082:
        r1.createNewFile();	 Catch:{ Exception -> 0x00c0 }
    L_0x0085:
        r1 = r1.exists();	 Catch:{ Exception -> 0x00c0 }
        if (r1 == 0) goto L_0x00cb;
    L_0x008b:
        r1 = com.tencent.qalsdk.core.j.a();	 Catch:{ Exception -> 0x00c0 }
        r1 = r1.i;	 Catch:{ Exception -> 0x00c0 }
        r1 = com.tencent.qalsdk.sdk.MsfSdkUtils.loadConfig(r1);	 Catch:{ Exception -> 0x00c0 }
        r2 = "imei";
        r1.put(r2, r0);	 Catch:{ Exception -> 0x00c0 }
        r2 = com.tencent.qalsdk.core.j.a();	 Catch:{ Exception -> 0x00c0 }
        r2 = r2.i;	 Catch:{ Exception -> 0x00c0 }
        com.tencent.qalsdk.sdk.MsfSdkUtils.saveConfig(r2, r1);	 Catch:{ Exception -> 0x00c0 }
        r1 = "MSF.C.Util";
        r2 = 2;
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00c0 }
        r3.<init>();	 Catch:{ Exception -> 0x00c0 }
        r4 = "write imei ";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00c0 }
        r3 = r3.append(r0);	 Catch:{ Exception -> 0x00c0 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x00c0 }
        com.tencent.qalsdk.util.QLog.i(r1, r2, r3);	 Catch:{ Exception -> 0x00c0 }
        goto L_0x004c;
    L_0x00c0:
        r1 = move-exception;
        r2 = "MSF.C.Util";
        r3 = "load imei error";
        com.tencent.qalsdk.util.QLog.d(r2, r5, r3, r1);
        goto L_0x004c;
    L_0x00cb:
        r1 = com.tencent.qalsdk.util.QLog.isColorLevel();	 Catch:{ Exception -> 0x00c0 }
        if (r1 == 0) goto L_0x004c;
    L_0x00d1:
        r1 = "MSF.C.Util";
        r2 = 2;
        r3 = "can not create imei file";
        com.tencent.qalsdk.util.QLog.d(r1, r2, r3);	 Catch:{ Exception -> 0x00c0 }
        goto L_0x004c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.qalsdk.core.k.e():java.lang.String");
    }
}
