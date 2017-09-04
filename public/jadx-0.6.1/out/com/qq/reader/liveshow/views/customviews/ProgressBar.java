package com.qq.reader.liveshow.views.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.View;

public class ProgressBar extends View {
    private double a;
    private int b = -16731258;
    private int c = -23519;
    private int d = -16731258;
    private final float e = 2.0f;
    private float f = 1.0f;
    private float g = 0.0f;
    private LinearGradient h = null;

    public ProgressBar(Context context) {
        super(context);
        a(context);
    }

    public ProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.f = 2.0f;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.a <= 100.0d) {
            Rect clipBounds = canvas.getClipBounds();
            Paint paint = new Paint();
            RectF rectF = new RectF(clipBounds);
            paint.setAntiAlias(true);
            float f = (float) ((((double) (clipBounds.right - clipBounds.left)) * this.a) / 100.0d);
            rectF.right = f;
            if (this.h == null || this.g != f) {
                this.h = new LinearGradient((float) clipBounds.left, (float) clipBounds.top, f, (float) clipBounds.bottom, new int[]{this.c, this.b}, null, TileMode.REPEAT);
                this.g = f;
            }
            paint.setShader(this.h);
            canvas.drawRoundRect(rectF, this.f, this.f, paint);
            canvas.save();
            canvas.restore();
        }
    }

    public void setProgress(double d) {
        this.a = d;
        postInvalidate();
    }

    public void setColor(int i) {
        this.d = i;
    }
}
