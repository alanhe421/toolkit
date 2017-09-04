package com.qq.reader.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import com.qq.reader.c.b;

public class RoundProgressView extends ImageView {
    private DrawFilter a;
    private Paint b;
    private int c;
    private int d;
    private int e;
    private int f;
    private float g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;

    public RoundProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public RoundProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.b = new Paint();
        this.a = new PaintFlagsDrawFilter(0, 3);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.RoundProgressBar);
        this.c = obtainStyledAttributes.getColor(0, -65536);
        this.d = obtainStyledAttributes.getColor(1, -65536);
        this.e = obtainStyledAttributes.getColor(2, -16711936);
        this.f = obtainStyledAttributes.getColor(3, -16711936);
        this.g = obtainStyledAttributes.getDimension(4, 5.0f);
        this.h = obtainStyledAttributes.getInteger(7, 100);
        this.j = obtainStyledAttributes.getInt(9, 0);
        this.k = this.c;
        this.l = this.e;
        obtainStyledAttributes.recycle();
    }

    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(this.a);
        int width;
        if (this.j == 1) {
            width = getWidth() / 2;
            this.b.setColor(this.l);
            this.b.setStyle(Style.FILL);
            this.b.setAntiAlias(true);
            canvas.drawCircle((float) width, (float) width, (float) width, this.b);
        } else {
            width = getWidth() / 2;
            int i = (int) (((float) width) - (this.g / 2.0f));
            this.b.setColor(this.k);
            this.b.setStyle(Style.STROKE);
            this.b.setStrokeWidth(this.g);
            this.b.setAntiAlias(true);
            canvas.drawCircle((float) width, (float) width, (float) i, this.b);
            this.b.setStrokeWidth(this.g);
            this.b.setColor(this.l);
            RectF rectF = new RectF((float) (width - i), (float) (width - i), (float) (width + i), (float) (width + i));
            this.b.setStyle(Style.STROKE);
            canvas.drawArc(rectF, -90.0f, (float) ((this.i * 360) / this.h), false, this.b);
        }
        super.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.l = this.f;
                this.k = this.d;
                postInvalidate();
                break;
            case 1:
            case 3:
                this.l = this.e;
                this.k = this.c;
                postInvalidate();
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setStyle(int i) {
        this.j = i;
        postInvalidate();
    }

    public synchronized int getMax() {
        return this.h;
    }

    public synchronized void setMax(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.h = i;
    }

    public synchronized int getProgress() {
        return this.i;
    }

    public synchronized void setProgress(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (i > this.h) {
            i = this.h;
        }
        if (i <= this.h) {
            this.i = i;
            postInvalidate();
        }
    }

    public void setProgressByPercent(float f) {
        setProgress((int) (((float) getMax()) * f));
    }
}
