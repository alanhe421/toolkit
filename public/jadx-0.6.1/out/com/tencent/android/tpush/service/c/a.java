package com.tencent.android.tpush.service.c;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.stat.StatReportStrategy;
import com.tencent.android.tpush.stat.c;
import com.tencent.android.tpush.stat.h;
import java.util.ArrayList;
import java.util.Properties;

/* compiled from: ProGuard */
public class a {
    private static Context a = null;

    public static void a(Context context) {
        c.b(true);
        c.a(StatReportStrategy.BATCH);
        h.b(context);
        h.e(context);
        a = context.getApplicationContext();
    }

    public static void a() {
        h.a(a, -1);
    }

    public static void a(ArrayList arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            try {
                h.a(a, arrayList);
            } catch (Throwable th) {
                com.tencent.android.tpush.a.a.c("XgStat", "reportSrvAck", th);
            }
        }
    }

    public static void a(Intent intent) {
        if (intent != null) {
            try {
                long longExtra = intent.getLongExtra("type", 0);
                long longExtra2 = intent.getLongExtra(MessageKey.MSG_BUSI_MSG_ID, 0);
                long longExtra3 = intent.getLongExtra(MessageKey.MSG_CREATE_TIMESTAMPS, 0);
                long longExtra4 = intent.getLongExtra(MessageKey.MSG_ID, 0);
                long longExtra5 = intent.getLongExtra("accId", 0);
                Properties properties = new Properties();
                properties.setProperty("type", "" + longExtra);
                properties.setProperty(MessageKey.MSG_BUSI_MSG_ID, "" + longExtra2);
                properties.setProperty(MessageKey.MSG_ID, "" + longExtra4);
                h.a(a, "SdkAck", properties, longExtra5, longExtra3);
            } catch (Throwable th) {
                com.tencent.android.tpush.a.a.c("XgStat", "reportSDKAck", th);
            }
        }
    }

    public static void b(ArrayList arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            com.tencent.android.tpush.a.a.h("XgStat", "ServiceStat reportAck 15 with null list ");
            return;
        }
        try {
            h.b(a, arrayList);
        } catch (Throwable th) {
            com.tencent.android.tpush.a.a.c("XgStat", "reportAck", th);
        }
    }

    public static void c(ArrayList arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            try {
                h.c(a, arrayList);
            } catch (Throwable th) {
                com.tencent.android.tpush.a.a.c("XgStat", "reportNotifactionClickedOrClear", th);
            }
        }
    }
}
