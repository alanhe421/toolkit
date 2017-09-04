package com.qq.reader.module.readpage.voteview;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import com.qq.reader.common.utils.ao;

public class RoundBorderDrawable extends BitmapDrawable {
    private int a;
    private int b;
    private int c;
    private Bitmap d;
    private Paint e;
    private Paint f;

    public RoundBorderDrawable(Bitmap bitmap, int i, int i2, int i3, boolean z) {
        this(bitmap, i, i2, i3, 0, 0, z);
    }

    public RoundBorderDrawable(Bitmap bitmap, int i, int i2, int i3, int i4, int i5, boolean z) {
        this.d = bitmap;
        if (z) {
            this.a = i;
            this.b = i3;
            this.c = i5;
        } else {
            this.a = ao.a((float) i);
            this.b = ao.a((float) i3);
            this.c = ao.a((float) i5);
        }
        if (i3 != 0) {
            this.e = new Paint();
            this.e.setAntiAlias(true);
            this.e.setFilterBitmap(true);
            this.e.setDither(true);
            this.e.setColor(i2);
            this.e.setStyle(Style.FILL);
        }
        if (i5 != 0) {
            this.f = new Paint();
            this.f.setAntiAlias(true);
            this.f.setFilterBitmap(true);
            this.f.setDither(true);
            this.f.setColor(i4);
            this.f.setStyle(Style.FILL);
        }
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int i = bounds.left;
        int i2 = bounds.top;
        if (this.d != null) {
            if (this.c != 0) {
                canvas.drawCircle((float) (this.a + i), (float) (this.a + i2), (float) this.a, this.f);
            }
            if (this.b != 0) {
                canvas.drawCircle((float) (this.a + i), (float) (this.a + i2), (float) (this.a - this.c), this.e);
            }
            canvas.drawBitmap(a(this.d, (this.a - this.b) - this.c), (float) (i + (this.b + this.c)), (float) (i2 + (this.b + this.c)), null);
        }
    }

    public int getOpacity() {
        return -3;
    }

    public Bitmap a(Bitmap bitmap, int i) {
        int i2 = i * 2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (height > width) {
            bitmap = Bitmap.createBitmap(bitmap, 0, (height - width) / 2, width, width);
        } else if (height < width) {
            bitmap = Bitmap.createBitmap(bitmap, (width - height) / 2, 0, height, height);
        }
        if (!(bitmap.getWidth() == i2 && bitmap.getHeight() == i2)) {
            bitmap = Bitmap.createScaledBitmap(bitmap, i2, i2, true);
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle((float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2), (float) (bitmap.getWidth() / 2), paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }
}
