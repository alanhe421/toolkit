package jp.wasabeef.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import com.bumptech.glide.g;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.f;

public class RoundedCornersTransformation implements f<Bitmap> {
    private c a;
    private int b;
    private int c;
    private int d;
    private CornerType e;

    public enum CornerType {
        ALL,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        OTHER_TOP_LEFT,
        OTHER_TOP_RIGHT,
        OTHER_BOTTOM_LEFT,
        OTHER_BOTTOM_RIGHT,
        DIAGONAL_FROM_TOP_LEFT,
        DIAGONAL_FROM_TOP_RIGHT
    }

    public RoundedCornersTransformation(Context context, int i, int i2) {
        this(context, i, i2, CornerType.ALL);
    }

    public RoundedCornersTransformation(Context context, int i, int i2, CornerType cornerType) {
        this(g.a(context).a(), i, i2, cornerType);
    }

    public RoundedCornersTransformation(c cVar, int i, int i2, CornerType cornerType) {
        this.a = cVar;
        this.b = i;
        this.c = this.b * 2;
        this.d = i2;
        this.e = cornerType;
    }

    public i<Bitmap> a(i<Bitmap> iVar, int i, int i2) {
        Bitmap bitmap = (Bitmap) iVar.b();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap a = this.a.a(width, height, Config.ARGB_8888);
        if (a == null) {
            a = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(a);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP));
        a(canvas, paint, (float) width, (float) height);
        return com.bumptech.glide.load.resource.bitmap.c.a(a, this.a);
    }

    private void a(Canvas canvas, Paint paint, float f, float f2) {
        float f3 = f - ((float) this.d);
        float f4 = f2 - ((float) this.d);
        switch (this.e) {
            case ALL:
                canvas.drawRoundRect(new RectF((float) this.d, (float) this.d, f3, f4), (float) this.b, (float) this.b, paint);
                return;
            case TOP_LEFT:
                b(canvas, paint, f3, f4);
                return;
            case TOP_RIGHT:
                c(canvas, paint, f3, f4);
                return;
            case BOTTOM_LEFT:
                d(canvas, paint, f3, f4);
                return;
            case BOTTOM_RIGHT:
                e(canvas, paint, f3, f4);
                return;
            case TOP:
                f(canvas, paint, f3, f4);
                return;
            case BOTTOM:
                g(canvas, paint, f3, f4);
                return;
            case LEFT:
                h(canvas, paint, f3, f4);
                return;
            case RIGHT:
                i(canvas, paint, f3, f4);
                return;
            case OTHER_TOP_LEFT:
                j(canvas, paint, f3, f4);
                return;
            case OTHER_TOP_RIGHT:
                k(canvas, paint, f3, f4);
                return;
            case OTHER_BOTTOM_LEFT:
                l(canvas, paint, f3, f4);
                return;
            case OTHER_BOTTOM_RIGHT:
                m(canvas, paint, f3, f4);
                return;
            case DIAGONAL_FROM_TOP_LEFT:
                n(canvas, paint, f3, f4);
                return;
            case DIAGONAL_FROM_TOP_RIGHT:
                o(canvas, paint, f3, f4);
                return;
            default:
                canvas.drawRoundRect(new RectF((float) this.d, (float) this.d, f3, f4), (float) this.b, (float) this.b, paint);
                return;
        }
    }

    private void b(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.d, (float) this.d, (float) (this.d + this.c), (float) (this.d + this.c)), (float) this.b, (float) this.b, paint);
        canvas.drawRect(new RectF((float) this.d, (float) (this.d + this.b), (float) (this.d + this.b), f2), paint);
        canvas.drawRect(new RectF((float) (this.d + this.b), (float) this.d, f, f2), paint);
    }

    private void c(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - ((float) this.c), (float) this.d, f, (float) (this.d + this.c)), (float) this.b, (float) this.b, paint);
        canvas.drawRect(new RectF((float) this.d, (float) this.d, f - ((float) this.b), f2), paint);
        canvas.drawRect(new RectF(f - ((float) this.b), (float) (this.d + this.b), f, f2), paint);
    }

    private void d(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.d, f2 - ((float) this.c), (float) (this.d + this.c), f2), (float) this.b, (float) this.b, paint);
        canvas.drawRect(new RectF((float) this.d, (float) this.d, (float) (this.d + this.c), f2 - ((float) this.b)), paint);
        canvas.drawRect(new RectF((float) (this.d + this.b), (float) this.d, f, f2), paint);
    }

    private void e(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - ((float) this.c), f2 - ((float) this.c), f, f2), (float) this.b, (float) this.b, paint);
        canvas.drawRect(new RectF((float) this.d, (float) this.d, f - ((float) this.b), f2), paint);
        canvas.drawRect(new RectF(f - ((float) this.b), (float) this.d, f, f2 - ((float) this.b)), paint);
    }

    private void f(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.d, (float) this.d, f, (float) (this.d + this.c)), (float) this.b, (float) this.b, paint);
        canvas.drawRect(new RectF((float) this.d, (float) (this.d + this.b), f, f2), paint);
    }

    private void g(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.d, f2 - ((float) this.c), f, f2), (float) this.b, (float) this.b, paint);
        canvas.drawRect(new RectF((float) this.d, (float) this.d, f, f2 - ((float) this.b)), paint);
    }

    private void h(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.d, (float) this.d, (float) (this.d + this.c), f2), (float) this.b, (float) this.b, paint);
        canvas.drawRect(new RectF((float) (this.d + this.b), (float) this.d, f, f2), paint);
    }

    private void i(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - ((float) this.c), (float) this.d, f, f2), (float) this.b, (float) this.b, paint);
        canvas.drawRect(new RectF((float) this.d, (float) this.d, f - ((float) this.b), f2), paint);
    }

    private void j(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.d, f2 - ((float) this.c), f, f2), (float) this.b, (float) this.b, paint);
        canvas.drawRoundRect(new RectF(f - ((float) this.c), (float) this.d, f, f2), (float) this.b, (float) this.b, paint);
        canvas.drawRect(new RectF((float) this.d, (float) this.d, f - ((float) this.b), f2 - ((float) this.b)), paint);
    }

    private void k(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.d, (float) this.d, (float) (this.d + this.c), f2), (float) this.b, (float) this.b, paint);
        canvas.drawRoundRect(new RectF((float) this.d, f2 - ((float) this.c), f, f2), (float) this.b, (float) this.b, paint);
        canvas.drawRect(new RectF((float) (this.d + this.b), (float) this.d, f, f2 - ((float) this.b)), paint);
    }

    private void l(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.d, (float) this.d, f, (float) (this.d + this.c)), (float) this.b, (float) this.b, paint);
        canvas.drawRoundRect(new RectF(f - ((float) this.c), (float) this.d, f, f2), (float) this.b, (float) this.b, paint);
        canvas.drawRect(new RectF((float) this.d, (float) (this.d + this.b), f - ((float) this.b), f2), paint);
    }

    private void m(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.d, (float) this.d, f, (float) (this.d + this.c)), (float) this.b, (float) this.b, paint);
        canvas.drawRoundRect(new RectF((float) this.d, (float) this.d, (float) (this.d + this.c), f2), (float) this.b, (float) this.b, paint);
        canvas.drawRect(new RectF((float) (this.d + this.b), (float) (this.d + this.b), f, f2), paint);
    }

    private void n(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.d, (float) this.d, (float) (this.d + this.c), (float) (this.d + this.c)), (float) this.b, (float) this.b, paint);
        canvas.drawRoundRect(new RectF(f - ((float) this.c), f2 - ((float) this.c), f, f2), (float) this.b, (float) this.b, paint);
        canvas.drawRect(new RectF((float) this.d, (float) (this.d + this.b), f - ((float) this.c), f2), paint);
        canvas.drawRect(new RectF((float) (this.d + this.c), (float) this.d, f, f2 - ((float) this.b)), paint);
    }

    private void o(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - ((float) this.c), (float) this.d, f, (float) (this.d + this.c)), (float) this.b, (float) this.b, paint);
        canvas.drawRoundRect(new RectF((float) this.d, f2 - ((float) this.c), (float) (this.d + this.c), f2), (float) this.b, (float) this.b, paint);
        canvas.drawRect(new RectF((float) this.d, (float) this.d, f - ((float) this.b), f2 - ((float) this.b)), paint);
        canvas.drawRect(new RectF((float) (this.d + this.b), (float) (this.d + this.b), f, f2), paint);
    }

    public String a() {
        return "RoundedTransformation(radius=" + this.b + ", margin=" + this.d + ", diameter=" + this.c + ", cornerType=" + this.e.name() + ")";
    }
}
