package com.qq.reader.view.scrollcover;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;

class FancyCoverFlowItemWrapper extends ViewGroup {
    private float a;
    private boolean b = false;
    private float c;
    private int d;
    private float e;
    private ColorMatrix f;
    private Paint g;
    private Bitmap h;
    private Canvas i;
    private int j;

    public FancyCoverFlowItemWrapper(Context context) {
        super(context);
        b();
    }

    public FancyCoverFlowItemWrapper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public FancyCoverFlowItemWrapper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void b() {
        this.g = new Paint();
        this.f = new ColorMatrix();
        b(1.0f);
    }

    void a(boolean z) {
        if (z != this.b) {
            this.b = z;
            setLayerType(z ? 1 : 2, null);
            c();
        }
    }

    void a(float f) {
        if (f != this.c) {
            this.c = f;
            c();
        }
    }

    void a(int i) {
        if (i != this.d) {
            this.d = i;
            c();
        }
    }

    void b(int i) {
        this.j = i;
    }

    public int a() {
        return this.j;
    }

    public void b(float f) {
        if (f != this.a) {
            this.a = f;
            this.f.setSaturation(f);
            this.g.setColorFilter(new ColorMatrixColorFilter(this.f));
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        c();
        if (this.b) {
            setMeasuredDimension((int) (((float) getMeasuredWidth()) * this.e), getMeasuredHeight());
        }
    }

    @SuppressLint({"DrawAllocation"})
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            if (!(this.h != null && this.h.getWidth() == measuredWidth && this.h.getHeight() == measuredHeight)) {
                this.h = Bitmap.createBitmap(measuredWidth, measuredHeight, Config.ARGB_8888);
                this.i = new Canvas(this.h);
            }
            View childAt = getChildAt(0);
            int measuredWidth2 = (measuredWidth - childAt.getMeasuredWidth()) / 2;
            childAt.layout(measuredWidth2, 0, measuredWidth - measuredWidth2, childAt.getMeasuredHeight());
        }
    }

    @TargetApi(11)
    private void c() {
        float f = 1.0f;
        if (getChildAt(0) != null) {
            int measuredHeight = getMeasuredHeight();
            if (this.b) {
                f = (((1.0f - this.c) * ((float) measuredHeight)) - ((float) this.d)) / ((float) measuredHeight);
            }
            this.e = f;
            int i = (int) (this.e * ((float) measuredHeight));
            measuredHeight = (int) (this.e * ((float) getMeasuredWidth()));
            i = MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE);
            getChildAt(0).measure(MeasureSpec.makeMeasureSpec(measuredHeight, Integer.MIN_VALUE), i);
        }
    }
}
