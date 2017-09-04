package com.tencent.widget;

import java.lang.reflect.Method;

public class TraceUtils {
    public static final boolean USE_TRACE = false;
    private static Method sMethodBegin;
    private static Method sMethodEnd;

    public static void traceBegin(String str) {
    }

    public static void traceEnd() {
    }
}
