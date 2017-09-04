package android.support.v4.b.a;

import android.graphics.drawable.Drawable;

/* compiled from: DrawableCompatKitKat */
class g {
    public static boolean a(Drawable drawable) {
        return drawable.isAutoMirrored();
    }

    public static Drawable b(Drawable drawable) {
        if (drawable instanceof l) {
            return drawable;
        }
        return new l(drawable);
    }
}
