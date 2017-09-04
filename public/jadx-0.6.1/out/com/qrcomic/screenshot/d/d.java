package com.qrcomic.screenshot.d;

import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import com.qrcomic.screenshot.b.a;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: QRDoodleUtil */
public class d {
    static Comparator<a> a = new Comparator<a>() {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((a) obj, (a) obj2);
        }

        public int a(a aVar, a aVar2) {
            long b = aVar.b() - aVar2.b();
            if (b > 0) {
                return 1;
            }
            if (b < 0) {
                return -1;
            }
            return 0;
        }
    };

    public static boolean a(List<a> list) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        Collections.sort(list, a);
        return true;
    }

    public static boolean a(float f, float f2) {
        if (Math.abs(f - f2) < 1.0E-4f) {
            return true;
        }
        return false;
    }

    public static boolean a(RectF rectF, RectF rectF2) {
        if (rectF != null && rectF2 != null && a(rectF.left, rectF2.left) && a(rectF.top, rectF2.top) && a(rectF.right, rectF2.right) && a(rectF.bottom, rectF2.bottom)) {
            return true;
        }
        return false;
    }

    public static boolean a(PointF pointF, PointF pointF2) {
        if (pointF == null || pointF2 == null || !a(pointF.x, pointF2.x) || !a(pointF.y, pointF2.y)) {
            return false;
        }
        return true;
    }

    public static float a(MotionEvent motionEvent, PointF pointF) {
        return a(motionEvent.getX(), motionEvent.getY(), pointF.x, pointF.y);
    }

    public static float b(PointF pointF, PointF pointF2) {
        return a(pointF.x, pointF.y, pointF2.x, pointF2.y);
    }

    public static float a(float f, float f2, float f3, float f4) {
        float f5 = f - f3;
        float f6 = f2 - f4;
        return (float) Math.sqrt((double) ((f5 * f5) + (f6 * f6)));
    }

    public static float c(PointF pointF, PointF pointF2) {
        float f = pointF.x - pointF2.x;
        float f2 = pointF.y - pointF2.y;
        float toDegrees = (float) Math.toDegrees(Math.asin((double) (f2 / b(pointF, pointF2))));
        if (!Float.isNaN(toDegrees)) {
            if (f2 >= 0.0f && f >= 0.0f) {
                return toDegrees;
            }
            if (f2 >= 0.0f && f <= 0.0f) {
                return 180.0f - toDegrees;
            }
            if (f2 <= 0.0f && f >= 0.0f) {
                return toDegrees;
            }
            if (f2 <= 0.0f && f <= 0.0f) {
                return -180.0f - toDegrees;
            }
        }
        return 0.0f;
    }
}
