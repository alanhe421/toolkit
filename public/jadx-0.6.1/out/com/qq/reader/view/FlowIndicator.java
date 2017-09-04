package com.qq.reader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

public class FlowIndicator extends View {
    private int a;
    private int b;
    private float c;
    private float d;
    private float e;
    private int f;
    private int g;

    public FlowIndicator(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public FlowIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 0;
        this.b = 3;
        this.c = 10.0f;
        this.d = 10.0f;
        this.e = 0.0f;
        this.f = -12303292;
        this.g = -1;
        setBackgroundColor(0);
    }

    public FlowIndicator(Context context) {
        this(context, null);
    }

    public void setSize(int i) {
        this.b = i;
    }

    public void setCurrent(int i) {
        this.a = i;
        invalidate();
    }

    public void setRadius(int i) {
        this.c = (float) i;
    }

    public void setSpace(int i) {
        this.d = (float) i;
    }

    public void setSelectedColor(int i) {
        this.f = i;
    }

    public void setUnSelectedColor(int i) {
        this.g = i;
    }

    protected void onMeasure(int i, int i2) {
        MeasureSpec.getMode(i2);
        super.onMeasure(i, (getPaddingBottom() + getPaddingTop()) + MeasureSpec.makeMeasureSpec((int) (this.c * 2.0f), 1073741824));
    }

    protected void onDraw(Canvas canvas) {
        if (this.b > 1) {
            Paint paint = new Paint();
            paint.setColor(-16776961);
            paint.setAntiAlias(true);
            super.onDraw(canvas);
            float width = (((float) getWidth()) - (((this.c * 2.0f) * ((float) this.b)) + (this.d * ((float) (this.b - 1))))) / 2.0f;
            int height = (int) (((float) (((getHeight() - getPaddingBottom()) - getPaddingTop()) - getPaddingBottom())) + this.c);
            if (height < 0) {
                height = (int) this.c;
            }
            for (int i = 0; i < this.b; i++) {
                paint.setColor(this.g);
                canvas.drawCircle((this.c + width) + (((float) i) * ((this.d + this.c) + this.c)), (float) height, this.c, paint);
            }
            paint.setColor(this.f);
            float f = this.e;
            if (f > 0.0f && this.a >= this.b - 1) {
                f = 0.0f;
            }
            if (f < 0.0f && this.a <= 0) {
                f = 0.0f;
            }
            canvas.drawCircle(((f + ((float) this.a)) * ((this.d + this.c) + this.c)) + (this.c + width), (float) height, this.c, paint);
        }
    }
}
