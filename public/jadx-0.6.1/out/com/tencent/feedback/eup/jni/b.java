package com.tencent.feedback.eup.jni;

import android.content.Context;
import android.util.Log;
import com.tencent.av.config.ConfigBaseParser;
import com.tencent.feedback.common.a;
import com.tencent.feedback.common.c;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.feedback.eup.CrashHandleListener;
import com.tencent.feedback.eup.CrashReport;
import com.tencent.feedback.eup.e;
import com.tencent.feedback.eup.f;
import java.util.Map;

/* compiled from: RQDSRC */
public final class b implements NativeExceptionHandler {
    private static b b;
    private Context a;
    private String c;

    private b(Context context) {
        this.a = context;
    }

    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4) {
        handleNativeException(i, i2, j, j2, str, str2, str3, str4, -1234567890, "", -1, -1, -1, "", ConfigBaseParser.DEFAULT_VALUE);
    }

    public static synchronized b a(Context context) {
        b bVar;
        synchronized (b.class) {
            if (b == null) {
                b = new b(context);
            }
            bVar = b;
        }
        return bVar;
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            bVar = b;
        }
        return bVar;
    }

    private synchronized String b() {
        return this.c;
    }

    public final synchronized void a(String str) {
        this.c = str;
    }

    protected static e a(Context context, long j, String str, String str2, String str3, String str4, int i, String str5, int i2, String str6, byte[] bArr, String str7, String str8) {
        String str9;
        if (str3 == null) {
            str9 = null;
        } else {
            int indexOf = str3.indexOf("java.lang.Thread.getStackTrace");
            if (indexOf < 0) {
                str9 = str3;
            } else {
                int indexOf2 = str3.indexOf("\n", indexOf);
                if (indexOf2 < 0) {
                    str9 = str3;
                } else {
                    String substring = str3.substring(0, indexOf);
                    str9 = substring + str3.substring(indexOf2);
                }
            }
        }
        c a = c.a(context);
        String E = a.E();
        String name = Thread.currentThread().getName();
        e a2 = com.tencent.feedback.eup.b.a(context, a.g(), a.o(), a.j(), a.y(), E, name, str2, str, str6, str9, j, str7, bArr);
        if (a2 == null) {
            return null;
        }
        if (i > 0) {
            a2.a(a2.e() + "(" + str5 + ")");
            a2.o("kernel");
        } else {
            a2.o(str5);
            if (i2 > 0) {
                if (!str.equalsIgnoreCase("SIGABRT")) {
                    a2.n(a.a(i2));
                }
            }
            a2.n(new StringBuilder(ConfigBaseParser.DEFAULT_VALUE).append(i2).toString());
        }
        com.tencent.feedback.common.e.a("etype:%s,sType:%s,sPN:%s", a2.e(), a2.A(), a2.z());
        a2.a((byte) 2);
        a2.h(str4);
        a2.p(str8);
        return a2;
    }

    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        CrashHandleListener crashHandleListener;
        com.tencent.feedback.common.e.b("rqdp{  na eup p:} %d , t:%d , exT:%d ,exTMS: %d, exTP:%s ,exADD:%s ,parsed exSTA:%s, TMB:%s , si_code:%d , si_CodeType:%s , sPid:%d ,sUid:%d,siErr:%d,siErrMsg:%s,naVersion:%s", Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(j), Long.valueOf(j2), str, str2, str3, str4, Integer.valueOf(i3), str5, Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6), str6, str7);
        Log.e("eup", "native crash happen:" + str);
        Log.e("eup", str3);
        boolean a = com.tencent.feedback.anr.b.a(this.a).a();
        String str8 = a ? "This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!" : "";
        if (a) {
            Log.e("eup", "This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!");
        }
        byte[] bArr = null;
        String str9 = null;
        long j3 = (1000 * j) + (j2 / 1000);
        f l = f.l();
        if (l == null) {
            com.tencent.feedback.common.e.c("rqdp{  instance == null}", new Object[0]);
            crashHandleListener = null;
        } else {
            crashHandleListener = l.r();
        }
        if (crashHandleListener != null) {
            try {
                com.tencent.feedback.common.e.b("your crhanlde start", new Object[0]);
                crashHandleListener.onCrashHandleStart(true);
            } catch (Throwable th) {
                com.tencent.feedback.common.e.d("on native hanlde start throw %s", th.toString());
                if (!com.tencent.feedback.common.e.a(th)) {
                    th.printStackTrace();
                }
            }
            try {
                com.tencent.feedback.common.e.b("your crdata", new Object[0]);
                bArr = crashHandleListener.getCrashExtraData(true, str, str2, str3, i3, j3);
            } catch (Throwable th2) {
                com.tencent.feedback.common.e.d("get extra data error %s", th2.toString());
                if (!com.tencent.feedback.common.e.a(th2)) {
                    th2.printStackTrace();
                }
            }
            try {
                com.tencent.feedback.common.e.b("your crmsg", new Object[0]);
                str9 = crashHandleListener.getCrashExtraMessage(true, str, str2, str3, i3, j3);
            } catch (Throwable th22) {
                com.tencent.feedback.common.e.d("get extra msg error %s", th22.toString());
                if (!com.tencent.feedback.common.e.a(th22)) {
                    th22.printStackTrace();
                }
            }
        }
        e a2 = a(this.a, j3, str, str2, str3, str4, i3, str5, i4, str8, bArr, str9, str7);
        if (a2 == null) {
            com.tencent.feedback.common.e.c("rqdp{  cr eup msg fail!}", new Object[0]);
            return;
        }
        boolean onCrashSaving;
        com.tencent.feedback.eup.c a3;
        try {
            com.tencent.feedback.common.e.c("rqdp{  get other stack}", new Object[0]);
            Map b = com.tencent.feedback.proguard.a.b();
            if (b != null) {
                a2.C().putAll(b);
            }
        } catch (Throwable th222) {
            com.tencent.feedback.common.e.d("get all threads stack fail", new Object[0]);
            if (!com.tencent.feedback.common.e.a(th222)) {
                th222.printStackTrace();
            }
        }
        if (crashHandleListener != null) {
            com.tencent.feedback.common.e.b("your ask2save", new Object[0]);
            try {
                onCrashSaving = crashHandleListener.onCrashSaving(true, str, str2, str3, i3, j3, a2.k(), a2.D(), a2.v());
            } catch (Throwable th2222) {
                com.tencent.feedback.common.e.d("on Crash Saving error %s", th2222.toString());
                if (!com.tencent.feedback.common.e.a(th2222)) {
                    th2222.printStackTrace();
                }
            }
            BuglyBroadcastRecevier.brocastCrash(this.a, a2);
            if (onCrashSaving) {
                com.tencent.feedback.common.e.c("the eup no need to save!", new Object[0]);
            } else {
                try {
                    a3 = com.tencent.feedback.eup.c.a(this.a);
                    if (a3 != null) {
                        onCrashSaving = a3.a(a2, CrashReport.getCrashRuntimeStrategy());
                        com.tencent.feedback.common.e.b("rqdp{  eup save} %b", Boolean.valueOf(onCrashSaving));
                        c.a(b());
                    }
                } catch (Throwable th22222) {
                    com.tencent.feedback.common.e.d("your crash handle happen error %s", th22222.toString());
                    if (!com.tencent.feedback.common.e.a(th22222)) {
                        th22222.printStackTrace();
                    }
                }
            }
            if (crashHandleListener != null) {
                try {
                    com.tencent.feedback.common.e.b("your crhanlde end", new Object[0]);
                    crashHandleListener.onCrashHandleEnd(true);
                } catch (Throwable th3) {
                    com.tencent.feedback.common.e.d("on native hanlde end throw %s", th3.toString());
                    if (!com.tencent.feedback.common.e.a(th3)) {
                        th3.printStackTrace();
                        return;
                    }
                    return;
                }
            }
        }
        onCrashSaving = true;
        BuglyBroadcastRecevier.brocastCrash(this.a, a2);
        if (onCrashSaving) {
            com.tencent.feedback.common.e.c("the eup no need to save!", new Object[0]);
        } else {
            a3 = com.tencent.feedback.eup.c.a(this.a);
            if (a3 != null) {
                onCrashSaving = a3.a(a2, CrashReport.getCrashRuntimeStrategy());
                com.tencent.feedback.common.e.b("rqdp{  eup save} %b", Boolean.valueOf(onCrashSaving));
                c.a(b());
            }
        }
        if (crashHandleListener != null) {
            com.tencent.feedback.common.e.b("your crhanlde end", new Object[0]);
            crashHandleListener.onCrashHandleEnd(true);
        }
    }
}
