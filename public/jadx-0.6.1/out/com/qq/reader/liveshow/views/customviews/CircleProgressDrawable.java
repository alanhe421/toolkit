package com.qq.reader.liveshow.views.customviews;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

public class CircleProgressDrawable extends Drawable {
    private Paint a;
    private Paint b;
    private RectF c;
    private float d = 0.0f;

    public CircleProgressDrawable() {
        a();
    }

    private void a() {
        this.c = new RectF();
        this.a = new Paint();
        this.a.setStyle(Style.FILL);
        this.a.setAntiAlias(true);
        this.a.setColor(-65536);
        this.b = new Paint();
        this.b.setStyle(Style.FILL);
        this.b.setAntiAlias(true);
        this.b.setColor(-65536);
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        this.c.set((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom);
        canvas.drawArc(this.c, 0.0f, 360.0f, true, this.b);
        canvas.drawArc(this.c, 270.0f, this.d, true, this.a);
    }

    public void setAlpha(int i) {
        this.a.setAlpha(i);
        this.b.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        this.b.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        switch (this.a.getColor() >>> 24) {
            case 0:
                return -2;
            case 255:
                return -1;
            default:
                return -3;
        }
    }

    public float getProgress() {
        return this.d / 360.0f;
    }

    public void setProgress(float f) {
        this.d = 360.0f * f;
        invalidateSelf();
    }

    public void setColor(int i) {
        this.a.setColor(i);
    }

    public void setBackGroundColor(int i) {
        this.b.setColor(i);
    }
}
