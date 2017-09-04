package android.support.v4.b.a;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;

/* compiled from: DrawableCompatLollipop */
class h {
    public static void a(Drawable drawable, ColorStateList colorStateList) {
        if (drawable instanceof m) {
            d.a(drawable, colorStateList);
        } else {
            drawable.setTintList(colorStateList);
        }
    }

    public static Drawable a(Drawable drawable) {
        if ((drawable instanceof GradientDrawable) || (drawable instanceof DrawableContainer)) {
            return new m(drawable);
        }
        return drawable;
    }
}
