package com.tencent.feedback.anr;

import android.content.Context;
import com.tencent.feedback.common.b;
import com.tencent.feedback.common.c;
import com.tencent.feedback.common.e;
import com.tencent.feedback.eup.CrashReport;
import com.tencent.feedback.eup.CrashStrategyBean;

/* compiled from: RQDSRC */
public class ANRReport {
    public static void startANRMonitor(Context context) {
        c.a(context).startWatching();
    }

    public static void stopANRMonitor() {
        if (c.a(null) != null) {
            c.a(null).stopWatching();
        }
    }

    public static void uploadANRInfoAsync(Context context, int i, String str, String str2, String str3, long j) {
        if (i <= 0 || str == null || j <= 0) {
            e.d("anr args unright pid, procName ,anrTime should not be null", new Object[0]);
            return;
        }
        final Context context2 = context;
        final int i2 = i;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        final long j2 = j;
        b.b().a(new Runnable() {
            public final void run() {
                ANRReport.uploadANRInfo(context2, i2, str4, str5, str6, j2);
            }
        });
    }

    public static boolean uploadANRInfo(Context context, int i, String str, String str2, String str3, long j) {
        c a = c.a(context);
        if (a == null) {
            e.d("commonInfo is null not init ?", new Object[0]);
            return false;
        }
        CrashStrategyBean crashRuntimeStrategy = CrashReport.getCrashRuntimeStrategy();
        if (crashRuntimeStrategy == null) {
            e.d("crash strategy null,not init?", new Object[0]);
            return false;
        }
        com.tencent.feedback.eup.e a2 = com.tencent.feedback.eup.b.a(context, a.g(), a.o(), a.j(), a.y(), str, "main", "", "ANR_RQD_EXCEPTION", "", str2, j, str3, null);
        a2.a((byte) 3);
        boolean a3 = com.tencent.feedback.eup.c.a(context).a(a2, crashRuntimeStrategy);
        e.b("sha1:%s %d", new Object[]{a2.o(), Integer.valueOf(a2.m())});
        e.b("handle anr %b", new Object[]{Boolean.valueOf(a3)});
        return a3;
    }
}
