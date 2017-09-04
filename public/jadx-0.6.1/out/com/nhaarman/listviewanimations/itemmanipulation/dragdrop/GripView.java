package com.nhaarman.listviewanimations.itemmanipulation.dragdrop;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;

public class GripView extends View {
    private static final int[] a = new int[]{16843173};
    private final Paint b;
    private float c;
    private float d;
    private int e;
    private int f;

    public GripView(Context context) {
        this(context, null);
    }

    public GripView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GripView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 2;
        this.b = new Paint(1);
        int color = getResources().getColor(17170432);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a);
            color = obtainStyledAttributes.getColor(0, color);
            obtainStyledAttributes.recycle();
        }
        this.b.setColor(color);
        this.c = (float) ((int) TypedValue.applyDimension(1, 2.0f, context.getResources().getDisplayMetrics()));
    }

    public void setColor(int i) {
        this.b.setColor(getResources().getColor(i));
    }

    public void setDotSizeRadiusPx(float f) {
        this.c = f;
    }

    public void setColumnCount(int i) {
        this.e = i;
        requestLayout();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f = (int) (((float) ((i2 - getPaddingTop()) - getPaddingBottom())) / (this.c * 4.0f));
        this.d = ((((float) i2) - ((((float) this.f) * this.c) * 2.0f)) - ((((float) (this.f - 1)) * this.c) * 2.0f)) / 2.0f;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(MeasureSpec.makeMeasureSpec((getPaddingLeft() + getPaddingRight()) + ((int) (((float) this.e) * ((this.c * 4.0f) - 2.0f))), 1073741824), i2);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < this.e; i++) {
            float paddingLeft = ((((float) (i * 2)) * this.c) * 2.0f) + ((float) getPaddingLeft());
            for (int i2 = 0; i2 < this.f; i2++) {
                canvas.drawCircle(this.c + paddingLeft, (this.d + ((((float) (i2 * 2)) * this.c) * 2.0f)) + this.c, this.c, this.b);
            }
        }
    }
}
