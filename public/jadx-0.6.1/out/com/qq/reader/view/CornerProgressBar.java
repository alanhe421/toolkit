package com.qq.reader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import com.tencent.feedback.proguard.R;

public class CornerProgressBar extends ProgressBar {
    private int a = 0;
    private final Rect b = new Rect();
    private Rect c = new Rect();
    private Drawable d;

    public CornerProgressBar(Context context) {
        super(context);
        a(context);
    }

    public CornerProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public CornerProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.d = context.getResources().getDrawable(R.drawable.endpage_progressbar_style);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.b.left = getPaddingLeft();
        this.b.top = getPaddingTop();
        this.b.right = ((i3 - i) - getPaddingLeft()) - getPaddingRight();
        this.b.bottom = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
    }

    public synchronized void setProgress(int i) {
        this.a = i;
        invalidate();
    }

    protected synchronized void onDraw(Canvas canvas) {
        this.c.set(this.b);
        this.c.right = (this.b.width() * this.a) / 100;
        LayerDrawable layerDrawable = (LayerDrawable) this.d;
        Drawable findDrawableByLayerId = layerDrawable.findDrawableByLayerId(16908288);
        Drawable findDrawableByLayerId2 = layerDrawable.findDrawableByLayerId(16908301);
        if (findDrawableByLayerId != null) {
            findDrawableByLayerId.setBounds(this.b);
            findDrawableByLayerId.draw(canvas);
        }
        if (findDrawableByLayerId2 != null) {
            findDrawableByLayerId2.setBounds(this.c);
            findDrawableByLayerId2.draw(canvas);
        }
    }
}
