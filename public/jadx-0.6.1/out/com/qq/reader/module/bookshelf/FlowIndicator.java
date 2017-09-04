package com.qq.reader.module.bookshelf;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.tencent.feedback.proguard.R;

public class FlowIndicator extends View {
    private int a;
    private int b;
    private float c;
    private float d;
    private float e;

    public FlowIndicator(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public FlowIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 0;
        this.b = 0;
        this.c = 6.0f;
        this.d = 10.0f;
        this.e = 0.0f;
        this.d = (float) context.getResources().getDimensionPixelOffset(R.dimen.common_dp_7);
        this.c = (float) context.getResources().getDimensionPixelOffset(R.dimen.common_dp_3);
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

    protected void onDraw(Canvas canvas) {
        if (this.b != 1) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            super.onDraw(canvas);
            float width = ((float) getWidth()) - (((this.c * 2.0f) * ((float) this.b)) + (this.d * ((float) (this.b - 1))));
            for (int i = 0; i < this.b; i++) {
                paint.setColor(-1);
                paint.setAlpha(102);
                canvas.drawCircle((((((float) getPaddingLeft()) + width) - ((float) getPaddingRight())) + this.c) + (((float) i) * ((this.d + this.c) + this.c)), (float) (getHeight() / 2), this.c, paint);
            }
            float paddingLeft = ((((((float) getPaddingLeft()) + width) - ((float) getPaddingRight())) + (((float) this.a) * ((this.d + this.c) + this.c))) + this.c) + (this.e * (this.d + this.c));
            paint.setColor(-1);
            canvas.drawCircle(paddingLeft, (float) (getHeight() / 2), this.c, paint);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }
}
