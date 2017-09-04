package com.qq.reader.widget.cloudtag;

import java.util.ArrayList;
import java.util.Collections;

/* compiled from: IsIntersectantUtil */
public class b {
    public static float a(float[] fArr, float[] fArr2, float f, float f2, float f3, float f4, float f5, float f6) {
        return a(fArr[0], fArr[1], fArr[2], fArr2[0], fArr2[1], f, f2, f3, f4, f5, f6);
    }

    public static float a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11) {
        int i;
        Object arrayList = new ArrayList();
        float f12 = f - f4;
        float f13 = f + f4;
        float f14 = f2 + f5;
        float f15 = f2 - f5;
        float[] a = a(f3, f6, f7, f8, f9, f10, f11);
        if (!a(f6, f7, f8, f9, f10, f11, a[0], a[1], a[2]) || a[0] < f12 || a[0] > f13 || a[1] < f15 || a[1] > f14) {
            i = 0;
        } else {
            arrayList.add(Float.valueOf(a(f6, f7, f8, a[0], a[1], a[2])));
            i = 1;
        }
        if (i < 1) {
            return Float.POSITIVE_INFINITY;
        }
        Collections.sort(arrayList);
        return ((Float) arrayList.get(0)).floatValue();
    }

    public static boolean a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        if (a(f, f4, f7) && a(f2, f5, f8) && a(f3, f6, f9)) {
            return true;
        }
        return false;
    }

    public static boolean a(float f, float f2, float f3) {
        if (f > f2) {
            if (f3 < f2 || f3 > f) {
                return false;
            }
            return true;
        } else if (f3 < f || f3 > f2) {
            return false;
        } else {
            return true;
        }
    }

    public static float[] a(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        return new float[]{(((f - f4) * (f5 - f2)) / (f7 - f4)) + f2, (((f - f4) * (f6 - f3)) / (f7 - f4)) + f3, f};
    }

    public static float a(float f, float f2, float f3, float f4, float f5, float f6) {
        return (float) Math.sqrt((double) ((((f4 - f) * (f4 - f)) + ((f5 - f2) * (f5 - f2))) + ((f6 - f3) * (f6 - f3))));
    }
}
