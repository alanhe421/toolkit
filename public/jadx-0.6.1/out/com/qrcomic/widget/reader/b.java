package com.qrcomic.widget.reader;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.os.Build.VERSION;
import java.lang.reflect.Array;

/* compiled from: QRBitmapDrawable */
public class b extends Drawable implements Callback {
    private Paint a = new Paint();
    private BitmapDrawable b;
    private Bitmap c;
    private Bitmap[][] d;
    private int e;
    private int f;
    private float g = 1.0f;
    private float h = 1.0f;
    private Rect i = new Rect();
    private Rect j = new Rect();

    public b(Resources resources, Bitmap bitmap) {
        if (bitmap != null) {
            this.b = new BitmapDrawable(resources, bitmap);
            this.b.setTargetDensity(bitmap.getDensity());
            this.b.setCallback(this);
            this.c = this.b.getBitmap();
            this.a = this.b.getPaint();
        }
    }

    public int getIntrinsicWidth() {
        if (this.b != null) {
            return this.b.getIntrinsicWidth();
        }
        return 0;
    }

    public int getIntrinsicHeight() {
        if (this.b != null) {
            return this.b.getIntrinsicHeight();
        }
        return 0;
    }

    public void setDither(boolean z) {
        if (this.b != null) {
            this.b.setDither(z);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        if (this.b != null) {
            return this.b.setVisible(z, z2);
        }
        return false;
    }

    @TargetApi(14)
    public void draw(Canvas canvas) {
        try {
            if (this.c == null) {
                return;
            }
            if (VERSION.SDK_INT >= 14) {
                int maximumBitmapWidth = canvas.getMaximumBitmapWidth();
                int maximumBitmapHeight = canvas.getMaximumBitmapHeight();
                if (!(this.e == maximumBitmapWidth && this.f == maximumBitmapHeight)) {
                    this.e = maximumBitmapWidth;
                    this.f = maximumBitmapHeight;
                    if (!(maximumBitmapWidth == 0 || maximumBitmapHeight == 0)) {
                        a();
                    }
                }
                a(canvas);
            } else if (this.b != null) {
                this.b.draw(canvas);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a() {
        int i = 1;
        Bitmap bitmap = this.c;
        if (bitmap != null) {
            int i2 = this.e;
            int i3 = this.f;
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width > i2 || height > i3) {
                int i4;
                int i5 = width > i2 ? i2 : width;
                if (height > i3) {
                    i4 = i3;
                } else {
                    i4 = height;
                }
                int i6 = width % i2;
                int i7 = height % i3;
                width /= i2;
                if (i6 > 0) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                int i8 = width + i2;
                i3 = height / i3;
                if (i7 <= 0) {
                    i = 0;
                }
                int i9 = i3 + i;
                this.d = (Bitmap[][]) Array.newInstance(Bitmap.class, new int[]{i9, i8});
                width = 0;
                int i10 = 0;
                while (width < i9) {
                    this.d[width] = new Bitmap[i8];
                    i2 = 0;
                    i = 0;
                    while (i2 < i8) {
                        Bitmap[] bitmapArr = this.d[width];
                        if (i8 - 1 == i2) {
                            height = i6;
                        } else {
                            height = i5;
                        }
                        bitmapArr[i2] = Bitmap.createBitmap(bitmap, i, i10, height, i9 + -1 == width ? i7 : i4);
                        i2++;
                        i += i5;
                    }
                    width++;
                    i10 += i4;
                }
                return;
            }
            this.d = new Bitmap[1][];
            this.d[0] = new Bitmap[1];
            this.d[0][0] = bitmap;
        }
    }

    private void a(Canvas canvas) throws RuntimeException {
        Bitmap[][] bitmapArr = this.d;
        if (bitmapArr != null) {
            copyBounds(this.i);
            int i = this.i.left;
            int i2 = this.i.top;
            for (i = 0; i < bitmapArr.length; i++) {
                int height = (int) ((((float) bitmapArr[i][0].getHeight()) * this.h) + 0.5f);
                int i3 = this.i.left;
                for (int i4 = 0; i4 < bitmapArr[i].length; i4++) {
                    Bitmap bitmap = bitmapArr[i][i4];
                    if (bitmap != null) {
                        int width = (int) ((((float) bitmapArr[i][i4].getWidth()) * this.g) + 0.5f);
                        this.j.set(i3, i2, i3 + width, i2 + height);
                        canvas.drawBitmap(bitmap, null, this.j, this.a);
                        i3 += width;
                    }
                }
                i2 += height;
            }
        }
    }

    public void setAlpha(int i) {
        if (this.b != null) {
            this.b.setAlpha(i);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.b != null) {
            this.b.setColorFilter(colorFilter);
        }
    }

    public int getOpacity() {
        if (this.b != null) {
            return this.b.getOpacity();
        }
        return 0;
    }

    @TargetApi(11)
    public void invalidateDrawable(Drawable drawable) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(drawable);
        }
    }

    @TargetApi(11)
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(drawable, runnable, j);
        }
    }

    @TargetApi(11)
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(drawable, runnable);
        }
    }

    protected void onBoundsChange(Rect rect) {
        if (this.b != null) {
            this.b.setBounds(rect);
        }
        if (this.c != null) {
            this.g = ((float) rect.width()) / ((float) this.c.getWidth());
            this.h = ((float) rect.height()) / ((float) this.c.getHeight());
        }
    }
}
