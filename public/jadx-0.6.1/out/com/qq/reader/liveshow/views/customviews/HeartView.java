package com.qq.reader.liveshow.views.customviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.qq.reader.liveshow.a.d;

public class HeartView extends ImageView {
    private static final Paint a = new Paint(3);
    private static Bitmap d;
    private static Bitmap e;
    private static final Canvas f = new Canvas();
    private int b = d.heart0;
    private int c = d.heart1;

    public HeartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HeartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public HeartView(Context context) {
        super(context);
    }

    public void setDrawable(int i) {
        setImageDrawable(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), i)));
    }

    public void setColor(int i) {
        setImageDrawable(new BitmapDrawable(getResources(), a(i)));
    }

    public void setColorAndDrawables(int i, int i2, int i3) {
        if (i2 != this.b) {
            d = null;
        }
        if (i3 != this.c) {
            e = null;
        }
        this.b = i2;
        this.c = i3;
        setColor(i);
    }

    public Bitmap a(int i) {
        if (d == null) {
            d = BitmapFactory.decodeResource(getResources(), this.b);
        }
        if (e == null) {
            e = BitmapFactory.decodeResource(getResources(), this.c);
        }
        Bitmap bitmap = d;
        Bitmap bitmap2 = e;
        Bitmap a = a(bitmap2.getWidth(), bitmap2.getHeight());
        if (a == null) {
            return null;
        }
        Canvas canvas = f;
        canvas.setBitmap(a);
        Paint paint = a;
        canvas.drawBitmap(bitmap2, 0.0f, 0.0f, paint);
        paint.setColorFilter(new PorterDuffColorFilter(i, Mode.SRC_ATOP));
        canvas.drawBitmap(bitmap, ((float) (bitmap2.getWidth() - bitmap.getWidth())) / 2.0f, ((float) (bitmap2.getHeight() - bitmap.getHeight())) / 2.0f, paint);
        paint.setColorFilter(null);
        canvas.setBitmap(null);
        return a;
    }

    private static Bitmap a(int i, int i2) {
        try {
            return Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }
}
