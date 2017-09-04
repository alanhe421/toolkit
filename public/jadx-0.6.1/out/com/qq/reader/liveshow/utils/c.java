package com.qq.reader.liveshow.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import com.qq.reader.liveshow.model.b;

/* compiled from: CenterImageSpan */
public class c extends ImageSpan {
    private Context a;

    public c(Context context, Bitmap bitmap) {
        super(context, bitmap);
        this.a = context;
    }

    public c(Context context, Drawable drawable, int i) {
        super(drawable, i);
        this.a = context;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        return b.a(this.a);
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        Drawable drawable = getDrawable();
        canvas.save();
        canvas.translate(f, (float) ((((i5 - i3) - drawable.getBounds().bottom) / 2) + i3));
        drawable.draw(canvas);
        canvas.restore();
    }
}
