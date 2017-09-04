package com.qq.reader.common.monitor;

import android.content.Context;
import com.qq.reader.appconfig.a.d;
import java.util.Calendar;

/* compiled from: MTAStatTool */
public class g {
    public static void a(Context context, String str) {
    }

    public static void b(Context context, String str) {
        try {
            int aM = d.aM(context);
            int i = Calendar.getInstance().get(6);
            if (i != aM) {
                i.a("event_startup2", null, context);
                d.e = true;
                d.C(context, i);
                if ("readerpage".equals(str)) {
                    i.a("event_first_open_page", true, 0, 0, null, false, false, context);
                } else {
                    i.a("event_first_open_page", false, 0, 0, null, false, false, context);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Context context) {
    }

    public static void c(Context context, String str) {
    }

    public static void a(Context context, int i) {
    }

    public static void b(Context context) {
    }
}
