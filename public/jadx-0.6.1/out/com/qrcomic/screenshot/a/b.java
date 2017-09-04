package com.qrcomic.screenshot.a;

import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/* compiled from: QRDoodleConstant */
public class b {
    public static final int a = a(3.0f);
    public static final int b = a(30.0f);
    public static final int c = a(20.0f);
    public static final int d = a(1.0f);
    public static int e = a(160.0f);
    public static int f = a(117.8f);
    public static int g = a(14.0f);
    public static int h = a(15.0f);
    public static int i = a(25.2f);
    public static int j = (e - (i * 2));
    public static int k = (f - (i * 2));
    private static int l = 0;
    private static int m = 0;
    private static float n = 0.0f;
    private static DisplayMetrics o;

    public static DisplayMetrics a() {
        if (o == null) {
            synchronized (b.class) {
                if (o == null) {
                    Display defaultDisplay = ((WindowManager) com.qrcomic.manager.b.a().b().b().getSystemService("window")).getDefaultDisplay();
                    o = new DisplayMetrics();
                    defaultDisplay.getMetrics(o);
                }
            }
        }
        return o;
    }

    public static int a(float f) {
        return (int) ((a().density * f) + 0.5f);
    }

    public static float b(float f) {
        return a().density * f;
    }

    public static int a(Bitmap bitmap) {
        if (bitmap == null) {
            return e;
        }
        return bitmap.getWidth();
    }

    public static int b(Bitmap bitmap) {
        if (bitmap == null) {
            return f;
        }
        return bitmap.getHeight();
    }
}
