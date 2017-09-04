package com.tencent.feedback.eup;

import android.content.Context;
import com.tencent.feedback.common.c;
import com.tencent.feedback.common.e;
import com.tencent.feedback.common.f;
import com.tencent.feedback.eup.jni.NativeExceptionHandler;
import com.tencent.feedback.eup.jni.NativeExceptionUpload;
import com.tencent.feedback.eup.jni.b;
import com.tencent.feedback.eup.jni.d;
import com.tencent.feedback.proguard.a;
import com.tencent.feedback.proguard.o;
import com.tencent.feedback.proguard.p;
import com.tencent.feedback.proguard.t;
import com.tencent.feedback.proguard.u;
import com.tencent.feedback.proguard.y;
import com.tencent.feedback.upload.AbstractUploadDatas;
import com.tencent.feedback.upload.UploadHandleListener;
import com.tencent.tesla.soload.SoLoadCore;
import com.tencent.upload.log.trace.TracerConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: RQDSRC */
public class CrashReport {
    public static void initCrashReport(Context context) {
        initCrashReport(context, null, null, true, null);
    }

    public static void initCrashReport(Context context, boolean z) {
        f.a(context, "10000", false, f.a(context, z), null, null, null);
    }

    public static void initCrashReport(Context context, CrashHandleListener crashHandleListener, UploadHandleListener uploadHandleListener, boolean z, CrashStrategyBean crashStrategyBean) {
        f.a(context, "10000", false, f.a(context, z), uploadHandleListener, crashHandleListener, crashStrategyBean);
    }

    public static void initCrashReport(Context context, CrashHandleListener crashHandleListener, UploadHandleListener uploadHandleListener, boolean z, CrashStrategyBean crashStrategyBean, long j) {
        if (j > 0) {
            if (j > TracerConfig.LOG_FLUSH_DURATION) {
                j = TracerConfig.LOG_FLUSH_DURATION;
            }
            u.a(j);
        }
        f.a(context, null, false, f.a(context, z), uploadHandleListener, crashHandleListener, crashStrategyBean);
    }

    public static void initNativeCrashReport(Context context, String str, boolean z) {
        initNativeCrashReport(context, str, z, null, null);
    }

    public static void initNativeCrashReport(Context context, String str, boolean z, List<File> list) {
        initNativeCrashReport(context, str, z, list, null);
    }

    public static void initNativeCrashReport(Context context, String str, boolean z, List<File> list, File file) {
        if (b.a() != null) {
            e.a("already inited Native", new Object[0]);
            return;
        }
        String str2;
        if (file != null) {
            if (NativeExceptionUpload.loadRQDNativeLib(file)) {
                e.a("load lib sucess from specify!", new Object[0]);
            } else {
                e.d("load lib fail %s close native return!", file.getAbsoluteFile());
                return;
            }
        } else if (NativeExceptionUpload.loadRQDNativeLib()) {
            e.a("load lib sucess default!", new Object[0]);
        } else {
            e.d("load lib fail default close native return!", new Object[0]);
            return;
        }
        NativeExceptionHandler a = b.a(context);
        a.a(str);
        NativeExceptionUpload.setmHandler(a);
        if (file != null) {
            if (list == null) {
                list = new ArrayList();
            }
            list.add(file);
        }
        if (context == null || str == null) {
            e.c("rqdp{  nreg param!}", new Object[0]);
        } else {
            CrashStrategyBean s = f.l().s();
            Context context2 = context;
            String str3 = str;
            t.a(context).a(new d(context2, str3, a.c() - ((long) (((s.getRecordOverDays() * 24) * 3600) * 1000)), s.getMaxStoredNum(), "tomb_", ".txt"));
            e.a("add clean task to query listener", new Object[0]);
            str2 = "/data/data/" + context.getPackageName() + SoLoadCore.PATH_LIB;
            if (c.a(context).A() == null) {
                e.b("no setted SO , query so!", new Object[0]);
                com.tencent.feedback.common.b.b().a(new com.tencent.feedback.eup.jni.a(context, str2, list));
            }
        }
        com.tencent.feedback.common.d.a(context);
        str2 = com.tencent.feedback.common.d.d();
        com.tencent.feedback.common.d.a(context);
        NativeExceptionUpload.registEUP(str, str2, Integer.parseInt(com.tencent.feedback.common.d.c()));
        NativeExceptionUpload.enableNativeEUP(true);
        if (z) {
            NativeExceptionUpload.setNativeLogMode(3);
        } else {
            NativeExceptionUpload.setNativeLogMode(5);
        }
    }

    public static CrashStrategyBean getCrashRuntimeStrategy() {
        try {
            return f.l().s();
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static void setUserId(Context context, String str) {
        c.a(context).a(str);
        y a = y.a();
        if (a != null) {
            e.b("record uidstate async", new Object[0]);
            a.c();
        }
    }

    public static boolean addPlugin(Context context, String str, String str2, String str3) {
        if (str == null || str2 == null) {
            return false;
        }
        c a = c.a(context);
        if (str3 == null) {
            str3 = "";
        }
        return a.a(str, str2, str3);
    }

    public static void removePlugin(Context context, String str) {
        if (str != null) {
            c.a(context).f(str);
        }
    }

    public static int countExceptionDatas(Context context) {
        if (!f.o()) {
            return -1;
        }
        f l = f.l();
        if (l != null) {
            return l.g();
        }
        e.c("rqdp{  instance == null}", new Object[0]);
        return -1;
    }

    public static byte[] getExceptionDatas(Context context) {
        AbstractUploadDatas m = f.m();
        if (m == null) {
            return null;
        }
        byte[] a = m.a(true);
        if (a == null) {
            return a;
        }
        m.done(true);
        return a;
    }

    public static boolean doUploadExceptionDatas() {
        return f.n();
    }

    public static int countStoredRecord(Context context) {
        return b.b(context);
    }

    public static boolean handleCatchException(Thread thread, Throwable th, String str, byte[] bArr) {
        return f.a(thread, th, str, bArr);
    }

    public static void setCrashReportAble(boolean z) {
        f l = f.l();
        if (l != null) {
            l.a(z);
        }
    }

    public static void setNativeCrashReportAble(boolean z) {
        NativeExceptionUpload.enableNativeEUP(z);
    }

    public static long getSDKTotalConsume(Context context, boolean z) {
        p b = f.b(context);
        if (b == null) {
            return -1;
        }
        if (z) {
            return b.e;
        }
        return b.e + b.d;
    }

    public static void clearSDKTotalConsume(Context context) {
        f.c(context);
    }

    public static void setLogAble(boolean z, boolean z2) {
        e.a(z ? new e.a() : null);
        if (z) {
            e.c("'setLogAble(boolean)' is true , so running in debug model , close it when you release!", new Object[0]);
        }
    }

    public static void setThreadPoolService(ScheduledExecutorService scheduledExecutorService) {
        com.tencent.feedback.common.b.a(com.tencent.feedback.common.b.a(scheduledExecutorService));
    }

    public static void setDeviceId(Context context, String str) {
        c.a(context).b(str);
    }

    public static void setCountryName(Context context, String str) {
        c.a(context).g(str);
    }

    public static void setAPKSHa1(Context context, String str) {
        c.a(context).d(str);
        e.b("set sha1 %s", str);
    }

    public static void setSOFile(Context context, List<SOFile> list) {
        if (list != null) {
            List arrayList = new ArrayList();
            c a = c.a(context);
            for (SOFile sOFile : list) {
                o oVar = new o();
                oVar.a(sOFile.fileName);
                oVar.c(sOFile.arch);
                oVar.b(sOFile.sha1);
                e.b("setsofile %s %s %s", oVar.a(), oVar.f(), oVar.d());
                arrayList.add(oVar);
            }
            if (arrayList.size() > 0) {
                a.a(arrayList);
                e.b("setted so count %d", Integer.valueOf(arrayList.size()));
            }
        }
    }

    public static void setProductVersion(Context context, String str) {
        c.a(context).h(str);
        e.b("setted version %s", str);
    }

    public static void setDeviceRooted(Context context, boolean z) {
        c.a(context).a(z);
        e.b("setted isRT %s", z);
    }

    public static void setDengta_AppKey(Context context, String str) {
        c.a(context).e(str);
        e.b("setted beacon appkey %s", str);
    }

    public static void setProductID(Context context, String str) {
        c.a(context).i(str);
        e.b("setted ProductID %s", str);
    }

    public static void setUserSceneTag(Context context, int i) {
        if (context == null) {
            e.c("setTag args context should not be null", new Object[0]);
        } else if (i <= 0) {
            e.c("setTag args tagId should > 0", new Object[0]);
        } else {
            c.a(context).a(i);
        }
    }

    public static int getUserSceneTagId(Context context) {
        if (context != null) {
            return c.a(context).F();
        }
        e.c("get user scene tag context is null", new Object[0]);
        return -1;
    }

    public static String getUserData(Context context, String str) {
        if (context == null) {
            return null;
        }
        Object obj = (str == null || str.trim().length() <= 0) ? 1 : null;
        if (obj == null) {
            return c.a(context).k(str);
        }
        return null;
    }

    public static void putUserData(Context context, String str, String str2) {
        if (context == null) {
            e.c("putUserData args context should not be null", new Object[0]);
            return;
        }
        int i;
        if (str == null || str.trim().length() <= 0) {
            i = 1;
        } else {
            i = 0;
        }
        if (i != 0) {
            e.c("putUserData args key should not be null", new Object[0]);
        } else if (str.matches("[a-zA-Z[0-9]]+")) {
            String str3 = str2;
            if (str3.length() > 200) {
                e.c("user data value length over limit %d , has been cutted!", Integer.valueOf(200));
                str3 = str3.substring(0, 200);
            }
            c a = c.a(context);
            if (a.J().contains(str)) {
                c.a(context).a(str, str3);
                e.b("replace KV %s %s", str, str3);
            } else if (a.I() >= 10) {
                e.c("user data size is over limit %d , will drop this new key %s", Integer.valueOf(10), str);
            } else if (str.length() > 50) {
                e.c("user data key length over limit %d , will drop this new key %s", Integer.valueOf(50), str);
            } else {
                c.a(context).a(str, str3);
                e.b("added KV %s %s", str, str3);
            }
        } else {
            e.c("putUserData args key should match [a-zA-Z[0-9]]+  {" + str + "}", new Object[0]);
        }
    }

    public static String removeUserData(Context context, String str) {
        int i = 0;
        if (context == null) {
            e.c("removeUserData args context should not be null", new Object[0]);
        }
        if (str == null || str.trim().length() <= 0) {
            i = 1;
        }
        if (i != 0) {
            return null;
        }
        return c.a(context).j(str);
    }

    public static Set<String> getAllUserDataKeys(Context context) {
        if (context == null) {
            e.c("getAllUserDataKeys args context should not be null", new Object[0]);
        }
        return c.a(context).J();
    }

    public static int getUserDatasSize(Context context) {
        if (context == null) {
            e.c("getUserDatasSize args context should not be null", new Object[0]);
        }
        return c.a(context).I();
    }
}
