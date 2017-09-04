package com.qq.reader.module.readpage;

import android.content.Context;
import android.graphics.Paint;
import android.util.DisplayMetrics;

/* compiled from: LayoutDesigner */
public class g {
    public static boolean a = false;
    public static double b = -1.0d;
    public static double c = -1.0d;
    public static double d = -1.0d;
    public static int e = -1;

    private static double a(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.sqrt(Math.pow((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi), 2.0d) + Math.pow((double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi), 2.0d));
    }

    public static float a(Context context, Paint paint) {
        if (b < 0.0d) {
            b = a(context);
        }
        float textSize = paint.getTextSize();
        return (((b < 4.3d ? 1.35f : 1.5f) * textSize) - paint.descent()) / textSize;
    }
}
