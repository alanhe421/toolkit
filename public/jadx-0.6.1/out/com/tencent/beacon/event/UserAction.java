package com.tencent.beacon.event;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import com.tencent.av.config.ConfigBaseParser;
import com.tencent.beacon.b.b;
import com.tencent.beacon.b.b.d;
import com.tencent.beacon.b.b.e;
import com.tencent.beacon.b.c;
import com.tencent.beacon.b.f;
import com.tencent.beacon.b.g;
import com.tencent.beacon.b.j;
import com.tencent.beacon.e.a;
import com.tencent.beacon.upload.InitHandleListener;
import com.tencent.beacon.upload.UploadHandleListener;
import com.tencent.beacon.upload.h;
import com.tencent.upload.log.trace.TracerConfig;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ProGuard */
public class UserAction {
    protected static Map<String, String> a = null;
    private static Context b = null;
    private static String c = "";
    private static String d = "10000";
    private static String e = "";
    private static Runnable f = new Runnable() {
        public final void run() {
            a.b(" db events to up on app call", new Object[0]);
            try {
                o.d(false);
            } catch (Throwable th) {
                a.a(th);
            }
        }
    };

    public static void initUserAction(Context context) {
        a(context, null, true, 0, null);
    }

    public static void initUserAction(Context context, boolean z) {
        a(context, null, z, 0, null);
    }

    public static void initUserAction(Context context, boolean z, long j) {
        a(context, null, z, j, null);
    }

    public static void initUserAction(Context context, boolean z, long j, InitHandleListener initHandleListener) {
        a(context, null, z, j, initHandleListener);
    }

    public static void initUserAction(Context context, boolean z, long j, UploadHandleListener uploadHandleListener) {
        a(context, uploadHandleListener, z, j, null);
    }

    @TargetApi(14)
    private static void a(Context context, UploadHandleListener uploadHandleListener, boolean z, long j, InitHandleListener initHandleListener) {
        if (context == null) {
            a.c(" the context is null! init beacon sdk failed!", new Object[0]);
            return;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            b = applicationContext;
        } else {
            b = context;
        }
        if (j > 0) {
            if (j > TracerConfig.LOG_FLUSH_DURATION) {
                j = TracerConfig.LOG_FLUSH_DURATION;
            }
            d.a(j);
        }
        long time = new Date().getTime();
        if (Integer.valueOf(VERSION.SDK).intValue() >= 14) {
            ((Application) b).registerActivityLifecycleCallbacks(new g());
        }
        c.a().a(new com.tencent.beacon.b.a(b));
        a.a("API Level: %s", VERSION.SDK);
        a.a("initUserAction t1:" + (new Date().getTime() - time), new Object[0]);
        final h a = o.a(b, z);
        o a2 = o.a(b, a, uploadHandleListener, initHandleListener);
        if (a2 != null) {
            a2.a(true);
        }
        c.a().a(new Runnable() {
            public final void run() {
                com.tencent.beacon.net.a.a("com.tencent.beacon.net.SpeedMonitorModule", "getInstance", new Class[]{Context.class, Object.class}, new Object[]{UserAction.b, a});
            }
        });
        a.a("initUserAction t2:" + (new Date().getTime() - time), new Object[0]);
    }

    @Deprecated
    public static void setNetSpeedMonitorUsable(boolean z) {
        a.c(" SpeedMonitorModule is Deprecated !", new Object[0]);
    }

    public static void setUserActionUsable(boolean z) {
        o d = o.d();
        if (d != null) {
            d.a(z);
        } else {
            a.c(" UserActionRecord.getInstance is null, please initUserAction first!", new Object[0]);
        }
    }

    public static void setUploadMode(boolean z) {
        o d = o.d();
        if (d != null) {
            d.b(z);
        } else {
            a.c(" UserActionRecord.getInstance is null, please initUserAction first!", new Object[0]);
        }
    }

    public static void setAPPVersion(String str) {
        if (str != null && !str.trim().equals("")) {
            b.a(str);
        }
    }

    public static void setUserID(String str) {
        a.a(" setUserID:" + str, new Object[0]);
        if (str != null && str.trim().length() > 0 && !"10000".equals(str)) {
            Object trim = str.replace('|', '_').trim();
            if (com.tencent.beacon.net.a.c((String) trim)) {
                if (trim.length() < 5) {
                    a.c(" userID is invalid!! userID is too short, length < 5!", new Object[0]);
                }
                if (trim.length() > 128) {
                    trim = trim.substring(0, 128);
                }
            } else {
                a.c("userID is invalid!! userID should be ASCII code in 32-126! userID:" + str, new Object[0]);
                trim = "10000";
            }
            if (!"10000".equals(trim)) {
                d = str;
            }
        }
    }

    public static String getUserID() {
        return d;
    }

    public static void setQQ(String str) {
        if (str != null) {
            try {
                if (Long.parseLong(str) > TracerConfig.LOG_FLUSH_DURATION) {
                    c = str;
                    return;
                }
                return;
            } catch (Exception e) {
                a.c(" setQQ: set qq is not available !", new Object[0]);
                return;
            }
        }
        a.c(" setQQ: set qq is null !", new Object[0]);
    }

    public static String getQQ() {
        return c;
    }

    public static void setAdditionalInfo(Map<String, String> map) {
        if (map != null && map.size() <= 20) {
            Map hashMap = new HashMap();
            a = hashMap;
            hashMap.putAll(map);
        }
    }

    public static void setAppkey(String str) {
        if (str != null && !str.equals("")) {
            e = str;
        }
    }

    public static String getAppkey() {
        return e;
    }

    public static boolean loginEvent(boolean z, long j, Map<String, String> map) {
        if (b != null) {
            f.a(b);
            map.put("A33", f.l(b));
        }
        return o.a("rqd_wgLogin", z, j, (Map) map);
    }

    public static void setAppKey(Context context, String str) throws Exception {
        a.a(" setAppKey:" + str, new Object[0]);
        if (context == null) {
            a.c(" the context is null! setAppKey failed!", new Object[0]);
            return;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            b = applicationContext;
        } else {
            b = context;
        }
        if (str == null || str.trim().length() <= 0 || str.trim().length() >= 20) {
            a.c(" setAppKey: appkey is null or not available!", new Object[0]);
            throw new RuntimeException("appkey is null or not available! please check it!");
        }
        com.tencent.beacon.b.d m = com.tencent.beacon.b.d.m();
        if (m == null) {
            com.tencent.beacon.b.d.a(b);
            m = com.tencent.beacon.b.d.m();
        } else {
            m.d(str);
        }
        if (m != null) {
            m.d(str);
        }
    }

    public static void setChannelID(String str) {
        com.tencent.beacon.b.d m = com.tencent.beacon.b.d.m();
        if (m == null) {
            com.tencent.beacon.b.d.a(b);
            m = com.tencent.beacon.b.d.m();
        }
        if (m == null) {
            a.d("please set the channelID after call initUserAction!", new Object[0]);
        } else {
            m.c(com.tencent.beacon.net.a.b(str));
        }
    }

    public static void setInitChannelPath(Context context, String str) throws Exception {
        if (b == null) {
            b.c(context.getApplicationContext(), str);
        } else {
            a.d("please set the channel path before call initUserAction!", new Object[0]);
            throw new RuntimeException("please set the channel path before call initUserAction!");
        }
    }

    public static void doUploadRecords() {
        c.a().a(f);
    }

    public static void onAppExited() {
        o.e();
    }

    public static void flushObjectsToDB(boolean z) {
        o.c(z);
    }

    public static String getCloudParas(String str) {
        Map d = e.a().d();
        if (d != null) {
            return (String) d.get(str);
        }
        return null;
    }

    public static boolean onUserAction(String str, boolean z, long j, long j2, Map<String, String> map, boolean z2) {
        return onUserAction(str, z, j, j2, map, z2, false);
    }

    public static boolean onUserAction(String str, boolean z, long j, long j2, Map<String, String> map, boolean z2, boolean z3) {
        if (str == null || "".equals(str.trim())) {
            a.c("param eventName is null or \"\", please check it, return false! ", new Object[0]);
            return false;
        }
        String trim = str.replace('|', '_').trim();
        if (trim.length() == 0) {
            a.c("eventName is invalid!! eventName length == 0!", new Object[0]);
            trim = null;
        } else if (!com.tencent.beacon.net.a.c(trim)) {
            a.c("eventName is invalid!! eventName should be ASCII code in 32-126! eventName:" + str, new Object[0]);
            trim = null;
        } else if (trim.length() > 128) {
            a.c("eventName is invalid!! eventName length should be less than 128! eventName:" + str, new Object[0]);
            trim = trim.substring(0, 128);
        }
        if (trim == null) {
            return false;
        }
        return o.a(trim, z, j, j2, map, z2, z3);
    }

    public static boolean testSpeedIp(List<String> list) {
        String str = "com.tencent.beacon.net.SpeedMonitorModule";
        Object a = com.tencent.beacon.net.a.a(str, "getInstance", new Class[0], new Object[0]);
        if (a == null) {
            return false;
        }
        return ((Boolean) com.tencent.beacon.net.a.a(str, "testSpeedIp", a, new Class[]{List.class}, new Object[]{list})).booleanValue();
    }

    public static boolean testSpeedDomain(List<String> list) {
        String str = "com.tencent.beacon.net.SpeedMonitorModule";
        Object a = com.tencent.beacon.net.a.a(str, "getInstance", new Class[0], new Object[0]);
        if (a == null) {
            return false;
        }
        return ((Boolean) com.tencent.beacon.net.a.a(str, "testSpeedDomain", a, new Class[]{List.class}, new Object[]{list})).booleanValue();
    }

    @Deprecated
    public static boolean heartbeatEvent() {
        a.c(" heartbeatEvent is Deprecated !", new Object[0]);
        return true;
    }

    public static void closeUseInfoEvent() {
        o d = o.d();
        if (d != null) {
            d.f();
        }
    }

    @Deprecated
    public static void setAutoLaunchEventUsable(boolean z) {
    }

    public static long getSDKTotalConsume(Context context, boolean z) {
        com.tencent.beacon.b.a.f b = com.tencent.beacon.b.h.b(context.getApplicationContext());
        if (b == null) {
            return -1;
        }
        if (z) {
            return b.e;
        }
        return b.e + b.d;
    }

    public static void clearSDKTotalConsume(Context context) {
        com.tencent.beacon.b.h.d(context.getApplicationContext());
    }

    public static void clearAppTotalConsume(Context context) {
        com.tencent.beacon.b.h.e(context.getApplicationContext());
    }

    public static void setLogAble(boolean z, boolean z2) {
        a.a = z;
        a.b = z2;
    }

    public static g getUserActionRuntimeStrategy() {
        try {
            return o.d().j();
        } catch (Throwable th) {
            a.a(th);
            return null;
        }
    }

    public static String getQIMEI() {
        if (b != null && o.d() != null) {
            return j.a(b).a();
        }
        a.d("please initUserAction first!", new Object[0]);
        String str = "";
        if (b == null) {
            return str;
        }
        try {
            f.a(b);
            str = f.b(b);
            if (!"".equals(str)) {
                return str;
            }
            f.a(b);
            return f.d(b);
        } catch (Exception e) {
            return str;
        }
    }

    public static String getSDKVersion() {
        return "2.4.5";
    }

    public static String getGatewayIP() {
        com.tencent.beacon.b.d m = com.tencent.beacon.b.d.m();
        if (m != null) {
            return m.g();
        }
        return ConfigBaseParser.DEFAULT_VALUE;
    }

    public static String getNetWorkType() {
        if (b == null) {
            a.d("please initUserAction first!", new Object[0]);
            return ConfigBaseParser.DEFAULT_VALUE;
        }
        f.a(b);
        return f.l(b);
    }

    public static String getAPN() {
        if (b != null) {
            return f.p(b);
        }
        a.d("please initUserAction first!", new Object[0]);
        return ConfigBaseParser.DEFAULT_VALUE;
    }

    public static void onSplashEvent() {
        c a = c.a();
        if (b == null || a == null) {
            a.d("please initUserAction first!", new Object[0]);
        } else {
            a.a(new Runnable() {
                public final void run() {
                    new com.tencent.beacon.net.a(UserAction.b).b();
                }
            });
        }
    }
}
