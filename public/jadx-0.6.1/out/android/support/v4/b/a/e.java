package android.support.v4.b.a;

import android.graphics.drawable.Drawable;

/* compiled from: DrawableCompatHoneycomb */
class e {
    public static Drawable a(Drawable drawable) {
        if (drawable instanceof k) {
            return drawable;
        }
        return new k(drawable);
    }
}
