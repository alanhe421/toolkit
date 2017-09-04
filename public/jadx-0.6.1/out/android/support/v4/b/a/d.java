package android.support.v4.b.a;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

/* compiled from: DrawableCompatBase */
class d {
    public static void a(Drawable drawable, ColorStateList colorStateList) {
        if (drawable instanceof i) {
            ((i) drawable).setTintList(colorStateList);
        }
    }

    public static Drawable a(Drawable drawable) {
        if (drawable instanceof j) {
            return drawable;
        }
        return new j(drawable);
    }
}
