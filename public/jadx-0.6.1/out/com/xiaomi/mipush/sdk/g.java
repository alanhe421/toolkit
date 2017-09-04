package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.xiaomi.push.service.k;
import com.xiaomi.xmpush.thrift.f;
import java.util.ArrayList;

public class g {
    public static StackTraceElement[] a;

    public static void a() {
        try {
            a = (StackTraceElement[]) Thread.getAllStackTraces().get(Thread.currentThread());
        } catch (Throwable th) {
        }
    }

    public static void a(Context context) {
        com.xiaomi.channel.commonutils.c.g.a(context).a(new p(context), 20);
    }

    private static String b(int i) {
        int i2 = 4;
        if (a == null || a.length <= 4) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        while (i2 < a.length && i2 < i + 4) {
            try {
                arrayList.add(a[i2].toString());
                i2++;
            } catch (Exception e) {
                return "";
            }
        }
        return arrayList.toString();
    }

    private static boolean d(Context context) {
        k a = k.a(context);
        if (!a.a(f.AggregationSdkMonitorSwitch.a(), false)) {
            return false;
        }
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_upload_call_stack_timestamp", 0)) >= ((long) Math.max(60, a.a(f.AggregationSdkMonitorFrequency.a(), 86400)));
    }

    private static void e(Context context) {
        Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.putLong("last_upload_call_stack_timestamp", System.currentTimeMillis());
        edit.commit();
    }
}
