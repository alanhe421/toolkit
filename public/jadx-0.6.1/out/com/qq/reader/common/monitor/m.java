package com.qq.reader.common.monitor;

/* compiled from: TtsLog */
public class m {
    private static long a = 0;
    private static long b = 0;
    private static long c = 0;
    private static long d = 0;

    public static void a() {
        d = System.currentTimeMillis();
    }

    public static void b() {
        if (d > 0) {
            b += System.currentTimeMillis() - d;
        }
        d = 0;
    }

    public static void c() {
        c = System.currentTimeMillis();
    }

    public static void d() {
        if (c > 0) {
            a += System.currentTimeMillis() - c;
        }
        c = 0;
    }

    public static void e() {
        a = 0;
        b = 0;
        c = 0;
        d = 0;
    }

    public static long f() {
        return a;
    }

    public static long g() {
        return b;
    }
}
