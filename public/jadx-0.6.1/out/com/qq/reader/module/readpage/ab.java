package com.qq.reader.module.readpage;

import android.view.View;

/* compiled from: TouchArea */
public class ab {
    public static int a(View view, float f, float f2) {
        return a(f, f2, view.getWidth(), view.getHeight());
    }

    public static int a(float f, float f2, int i, int i2) {
        int i3 = i2 / 3;
        int i4 = i / 3;
        if (f >= ((float) (i4 << 1))) {
            return 0;
        }
        if (f < ((float) i4)) {
            return 1;
        }
        if (f2 >= ((float) (i3 << 1))) {
            return 0;
        }
        if (f2 < ((float) i3)) {
            return 1;
        }
        return 2;
    }

    public static boolean b(View view, float f, float f2) {
        int width = view.getWidth();
        int i = width / 3;
        if (f < ((float) i) || f >= ((float) (width - i))) {
            return false;
        }
        return true;
    }
}
