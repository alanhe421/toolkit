package com.tencent.mid.util;

import android.util.Log;

public final class f {
    private String a = "default";
    private boolean b = true;
    private int c = 2;

    public f(String str) {
        this.a = str;
    }

    private String b() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (!stackTraceElement.isNativeMethod() && !stackTraceElement.getClassName().equals(Thread.class.getName()) && !stackTraceElement.getClassName().equals(getClass().getName())) {
                return "[" + Thread.currentThread().getName() + "(" + Thread.currentThread().getId() + "): " + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + "]";
            }
        }
        return null;
    }

    public void a(Exception exception) {
        if (this.c <= 6) {
            StringBuffer stringBuffer = new StringBuffer();
            String b = b();
            StackTraceElement[] stackTrace = exception.getStackTrace();
            if (b != null) {
                stringBuffer.append(b + " - " + exception + "\r\n");
            } else {
                stringBuffer.append(exception + "\r\n");
            }
            if (stackTrace != null && stackTrace.length > 0) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    if (stackTraceElement != null) {
                        stringBuffer.append("[ " + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + " ]\r\n");
                    }
                }
            }
            Log.e(this.a, stringBuffer.toString());
        }
    }

    public void a(Object obj) {
        if (this.c <= 4) {
            String b = b();
            Log.i(this.a, b == null ? obj.toString() : b + " - " + obj);
        }
    }

    public void a(boolean z) {
    }

    public boolean a() {
        return this.b;
    }

    public void b(Exception exception) {
        if (a()) {
            a(exception);
        }
    }

    public void b(Object obj) {
        if (a()) {
            a(obj);
        }
    }

    public void c(Object obj) {
        if (this.c <= 5) {
            String b = b();
            Log.w(this.a, b == null ? obj.toString() : b + " - " + obj);
        }
    }

    public void d(Object obj) {
        if (a()) {
            c(obj);
        }
    }

    public void e(Object obj) {
        if (this.c <= 6) {
            String b = b();
            Log.e(this.a, b == null ? obj.toString() : b + " - " + obj);
        }
    }

    public void f(Object obj) {
        if (a()) {
            e(obj);
        }
    }

    public void g(Object obj) {
        if (this.c <= 3) {
            String b = b();
            Log.d(this.a, b == null ? obj.toString() : b + " - " + obj);
        }
    }

    public void h(Object obj) {
        if (a()) {
            g(obj);
        }
    }
}
