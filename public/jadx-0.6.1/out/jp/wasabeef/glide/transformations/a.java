package jp.wasabeef.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import com.bumptech.glide.g;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.f;

/* compiled from: CropCircleTransformation */
public class a implements f<Bitmap> {
    private c a;

    public a(Context context) {
        this(g.a(context).a());
    }

    public a(c cVar) {
        this.a = cVar;
    }

    public i<Bitmap> a(i<Bitmap> iVar, int i, int i2) {
        Bitmap bitmap = (Bitmap) iVar.b();
        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
        int width = (bitmap.getWidth() - min) / 2;
        int height = (bitmap.getHeight() - min) / 2;
        Bitmap a = this.a.a(min, min, Config.ARGB_8888);
        if (a == null) {
            a = Bitmap.createBitmap(min, min, Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(a);
        Paint paint = new Paint();
        Shader bitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        if (!(width == 0 && height == 0)) {
            Matrix matrix = new Matrix();
            matrix.setTranslate((float) (-width), (float) (-height));
            bitmapShader.setLocalMatrix(matrix);
        }
        paint.setShader(bitmapShader);
        paint.setAntiAlias(true);
        float f = ((float) min) / 2.0f;
        canvas.drawCircle(f, f, f, paint);
        return com.bumptech.glide.load.resource.bitmap.c.a(a, this.a);
    }

    public String a() {
        return "CropCircleTransformation()";
    }
}
