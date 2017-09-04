package com.qq.reader.common.monitor;

import android.content.Context;
import android.os.Process;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;

/* compiled from: CrashHandler */
public class b implements UncaughtExceptionHandler {
    private static b b;
    private UncaughtExceptionHandler a;

    private b() {
    }

    public static b a() {
        if (b == null) {
            b = new b();
        }
        return b;
    }

    public void a(Context context) {
        this.a = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (this.a != null) {
            a(th);
            this.a.uncaughtException(thread, th);
            return;
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            j.c("CrashHandler--------> InterruptedException : " + e.toString());
        }
        Process.killProcess(Process.myPid());
        System.exit(10);
    }

    public static void a(Throwable th) {
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        String obj = stringWriter.toString();
        printWriter.close();
        j.c("CrashHandler--------> error : " + th.toString() + "  \n trace : " + obj);
    }
}
