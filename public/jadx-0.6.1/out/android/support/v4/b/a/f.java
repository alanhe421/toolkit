package android.support.v4.b.a;

import android.graphics.drawable.Drawable;
import android.util.Log;
import java.lang.reflect.Method;

/* compiled from: DrawableCompatJellybeanMr1 */
class f {
    private static Method a;
    private static boolean b;

    public static void a(Drawable drawable, int i) {
        if (!b) {
            try {
                a = Drawable.class.getDeclaredMethod("setLayoutDirection", new Class[]{Integer.TYPE});
                a.setAccessible(true);
            } catch (Throwable e) {
                Log.i("DrawableCompatJellybeanMr1", "Failed to retrieve setLayoutDirection(int) method", e);
            }
            b = true;
        }
        if (a != null) {
            try {
                a.invoke(drawable, new Object[]{Integer.valueOf(i)});
            } catch (Throwable e2) {
                Log.i("DrawableCompatJellybeanMr1", "Failed to invoke setLayoutDirection(int) via reflection", e2);
                a = null;
            }
        }
    }
}
