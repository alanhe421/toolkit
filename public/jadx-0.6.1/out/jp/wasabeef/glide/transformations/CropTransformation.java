package jp.wasabeef.glide.transformations;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.RectF;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.f;

public class CropTransformation implements f<Bitmap> {
    private c a;
    private int b;
    private int c;
    private CropType d;

    public enum CropType {
        TOP,
        CENTER,
        BOTTOM
    }

    public i<Bitmap> a(i<Bitmap> iVar, int i, int i2) {
        Bitmap createBitmap;
        Bitmap bitmap = (Bitmap) iVar.b();
        this.b = this.b == 0 ? bitmap.getWidth() : this.b;
        this.c = this.c == 0 ? bitmap.getHeight() : this.c;
        Config config = bitmap.getConfig() != null ? bitmap.getConfig() : Config.ARGB_8888;
        Bitmap a = this.a.a(this.b, this.c, config);
        if (a == null) {
            createBitmap = Bitmap.createBitmap(this.b, this.c, config);
        } else {
            createBitmap = a;
        }
        float max = Math.max(((float) this.b) / ((float) bitmap.getWidth()), ((float) this.c) / ((float) bitmap.getHeight()));
        float width = ((float) bitmap.getWidth()) * max;
        max *= (float) bitmap.getHeight();
        float f = (((float) this.b) - width) / 2.0f;
        float a2 = a(max);
        new Canvas(createBitmap).drawBitmap(bitmap, null, new RectF(f, a2, width + f, max + a2), null);
        return com.bumptech.glide.load.resource.bitmap.c.a(createBitmap, this.a);
    }

    public String a() {
        return "CropTransformation(width=" + this.b + ", height=" + this.c + ", cropType=" + this.d + ")";
    }

    private float a(float f) {
        switch (this.d) {
            case CENTER:
                return (((float) this.c) - f) / 2.0f;
            case BOTTOM:
                return ((float) this.c) - f;
            default:
                return 0.0f;
        }
    }
}
