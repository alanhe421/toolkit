package android.support.v4.view;

import android.view.View;
import java.lang.reflect.Field;

/* compiled from: ViewCompatBase */
class aa {
    private static Field a;
    private static boolean b;
    private static Field c;
    private static boolean d;

    static int a(View view) {
        if (!b) {
            try {
                a = View.class.getDeclaredField("mMinWidth");
                a.setAccessible(true);
            } catch (NoSuchFieldException e) {
            }
            b = true;
        }
        if (a != null) {
            try {
                return ((Integer) a.get(view)).intValue();
            } catch (Exception e2) {
            }
        }
        return 0;
    }

    static int b(View view) {
        if (!d) {
            try {
                c = View.class.getDeclaredField("mMinHeight");
                c.setAccessible(true);
            } catch (NoSuchFieldException e) {
            }
            d = true;
        }
        if (c != null) {
            try {
                return ((Integer) c.get(view)).intValue();
            } catch (Exception e2) {
            }
        }
        return 0;
    }
}
