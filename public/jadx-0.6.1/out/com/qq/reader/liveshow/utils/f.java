package com.qq.reader.liveshow.utils;

import android.content.Context;
import android.os.Process;
import java.lang.Thread.UncaughtExceptionHandler;

/* compiled from: CrashHandler */
public class f implements UncaughtExceptionHandler {
    private static f a = new f();
    private UncaughtExceptionHandler b;
    private Context c;

    private f() {
    }

    public static f a() {
        return a;
    }

    public void a(Context context) {
        this.b = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.c = context.getApplicationContext();
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (this.b != null) {
            this.b.uncaughtException(thread, th);
        } else {
            Process.killProcess(Process.myPid());
        }
    }
}
