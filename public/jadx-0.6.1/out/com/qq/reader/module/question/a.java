package com.qq.reader.module.question;

import android.os.Build;
import android.os.Build.VERSION;

/* compiled from: AudioManagerParameter */
public class a {
    public int a;
    public int b;
    public boolean c;

    public a(int i, int i2, boolean z) {
        this.a = i;
        this.b = i2;
        this.c = z;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public static a d() {
        if (q()) {
            return new a(0, 0, false);
        }
        if (h()) {
            return new a(0, 0, false);
        }
        if (i()) {
            return new a(0, 0, false);
        }
        if (j()) {
            return new a(0, 0, false);
        }
        if (k()) {
            return new a(0, 0, false);
        }
        if (m()) {
            return new a(0, 0, false);
        }
        if (l()) {
            return new a(0, 0, false);
        }
        if (n()) {
            return new a(2, 0, false);
        }
        if (o()) {
            return new a(2, 0, false);
        }
        if (p()) {
            return new a(3, 3, false);
        }
        if (r()) {
            return new a(0, 0, false);
        }
        return new a(3, 0, false);
    }

    public static a e() {
        if (g()) {
            return new a(0, 0, true);
        }
        return new a(0, 3, true);
    }

    private static boolean g() {
        String str = Build.MODEL;
        boolean z = (Build.MANUFACTURER.equalsIgnoreCase("Samsung") || str.contains("MI 2") || str.contains("Nexus 4")) && VERSION.SDK_INT >= 11;
        if (z) {
            return true;
        }
        if (str.contains("SCH-I699") && VERSION.SDK_INT == 10) {
            return true;
        }
        return false;
    }

    private static boolean h() {
        return Build.MODEL.contains("HUAWEI Y 200T") && VERSION.SDK_INT <= 10;
    }

    private static boolean i() {
        return Build.MODEL.contains("Lenovo A278t") && VERSION.SDK_INT <= 10;
    }

    private static boolean j() {
        return Build.MODEL.contains("ZTE-T U960s") && VERSION.SDK_INT <= 10;
    }

    private static boolean k() {
        return Build.MODEL.contains("5910");
    }

    private static boolean l() {
        return Build.MODEL.contains("Lenovo K900");
    }

    private static boolean m() {
        return Build.MODEL.contains("V926");
    }

    private static boolean n() {
        return Build.MODEL.contains("ZTE N881E");
    }

    private static boolean o() {
        return Build.MODEL.contains("LNV-Lenovo S870e");
    }

    private static boolean p() {
        return Build.MODEL.contains("Coolpad 5891Q");
    }

    private static boolean q() {
        return Build.MODEL.contains("ME860") && VERSION.SDK_INT <= 10;
    }

    private static boolean r() {
        return Build.MODEL.contains("GT-S7500");
    }

    public static boolean f() {
        return t() || s();
    }

    private static boolean s() {
        return Build.MODEL.contains("Lenovo A750");
    }

    private static boolean t() {
        return Build.MANUFACTURER.equalsIgnoreCase("OPPO") && Build.MODEL.contains("X907");
    }
}
