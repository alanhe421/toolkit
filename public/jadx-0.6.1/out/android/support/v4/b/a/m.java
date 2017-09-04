package android.support.v4.b.a;

import android.content.res.Resources.Theme;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* compiled from: DrawableWrapperLollipop */
class m extends l {
    m(Drawable drawable) {
        super(drawable);
    }

    public void setHotspot(float f, float f2) {
        this.b.setHotspot(f, f2);
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        this.b.setHotspotBounds(i, i2, i3, i4);
    }

    public void getOutline(Outline outline) {
        this.b.getOutline(outline);
    }

    public void applyTheme(Theme theme) {
        this.b.applyTheme(theme);
    }

    public boolean canApplyTheme() {
        return this.b.canApplyTheme();
    }

    public Rect getDirtyBounds() {
        return this.b.getDirtyBounds();
    }
}
