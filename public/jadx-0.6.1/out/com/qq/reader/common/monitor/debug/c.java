package com.qq.reader.common.monitor.debug;

import com.qq.reader.common.monitor.debug.b.a;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: Logger */
public class c {
    public static ExecutorService a = Executors.newSingleThreadExecutor();
    private static b b;

    public static synchronized void a(b bVar) {
        synchronized (c.class) {
            b = bVar;
        }
    }

    public static a a() {
        return new a();
    }

    public static void a(String str, String str2) {
    }

    public static void b(String str, String str2) {
    }

    public static void c(String str, String str2) {
    }

    public static void d(String str, String str2) {
    }

    public static void a(String str, String str2, boolean z) {
    }

    public static void b(String str, String str2, boolean z) {
    }

    public static void e(String str, String str2) {
    }

    public static void c(String str, String str2, boolean z) {
    }

    public static String b() {
        Map allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return "Thread all stackTraces is error StackTraceElement Maps == null ";
        }
        StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) allStackTraces.get(Thread.currentThread());
        if (stackTraceElementArr == null) {
            return "Thread all stackTraces is error StackTraceElement == null ";
        }
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < stackTraceElementArr.length; i++) {
            if (i >= 5) {
                stringBuffer.append(stackTraceElementArr[i].toString()).append("\n");
            }
        }
        return stringBuffer.toString();
    }
}
