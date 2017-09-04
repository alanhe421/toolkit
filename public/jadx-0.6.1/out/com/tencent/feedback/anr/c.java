package com.tencent.feedback.anr;

import android.content.Context;
import android.os.FileObserver;
import android.os.Process;
import com.tencent.feedback.common.e;
import com.tencent.feedback.eup.CrashReport;
import com.tencent.feedback.eup.CrashStrategyBean;

/* compiled from: RQDSRC */
public class c extends FileObserver {
    private static c b = null;
    private Context a;

    private c(Context context) {
        super("/data/anr/", 8);
        com.tencent.feedback.common.c.a(context).E();
        Process.myPid();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
        }
        this.a = context;
    }

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (b == null) {
                if (context != null) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                }
                b = new c(context);
            }
            cVar = b;
        }
        return cVar;
    }

    public void startWatching() {
        super.startWatching();
        e.a("start watching", new Object[0]);
    }

    public void stopWatching() {
        synchronized (c.class) {
            b = null;
        }
        super.stopWatching();
        e.a("stop watching", new Object[0]);
    }

    public void onEvent(int i, String str) {
        e.c("received event %d %s", new Object[]{Integer.valueOf(i), str});
        String str2 = "/data/anr/" + str;
        if (str2.contains("trace")) {
            CrashStrategyBean crashRuntimeStrategy = CrashReport.getCrashRuntimeStrategy();
            if (crashRuntimeStrategy == null) {
                e.d("magic! no crash stategy,no anr return ?", new Object[0]);
                return;
            } else if (crashRuntimeStrategy.isOpenANR()) {
                b.a(this.a).a(str2);
                return;
            } else {
                e.a("close anr!", new Object[0]);
                return;
            }
        }
        e.c("not anr file %s", new Object[]{str2});
    }
}
