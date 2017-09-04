package com.qq.reader.readengine.a;

import android.graphics.Paint;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;

/* compiled from: LayoutSetting */
public class a {
    public static float a = 1.5f;
    public static float b = 1.8f;
    public static float c = 4.0f;
    private static boolean d = true;

    public static float a(Paint paint) {
        return (paint.descent() - paint.ascent()) * a;
    }

    public static float b(Paint paint) {
        return (paint.descent() - paint.ascent()) * 1.2f;
    }

    public static float c(Paint paint) {
        return (paint.descent() - paint.ascent()) * b;
    }

    public static float d(Paint paint) {
        return (paint.descent() - paint.ascent()) * c;
    }

    public static float e(Paint paint) {
        float textSize = paint.getTextSize();
        paint.setTextSize(d.F(ReaderApplication.getApplicationImp()));
        float descent = (paint.descent() - paint.ascent()) * a;
        paint.setTextSize(textSize);
        return descent;
    }

    public static void a(boolean z) {
        d = z;
    }

    public static boolean a() {
        return d;
    }

    public static void b() {
        int G = d.G(ReaderApplication.getApplicationImp());
        if (G == 0) {
            a = 1.2f;
            b = 1.4f;
            c = 3.0f;
        } else if (G == 1) {
            a = 1.4f;
            b = 1.8f;
            c = 4.0f;
        } else {
            a = 2.0f;
            b = 2.0f;
            c = 5.0f;
        }
    }
}
