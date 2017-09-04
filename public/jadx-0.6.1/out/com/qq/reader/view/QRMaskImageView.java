package com.qq.reader.view;

import android.content.Context;
import android.graphics.ColorMatrixColorFilter;
import android.util.AttributeSet;

public class QRMaskImageView extends QRImageView {
    public final float[] a = new float[]{1.0f, 0.0f, 0.0f, 0.0f, -50.0f, 0.0f, 1.0f, 0.0f, 0.0f, -50.0f, 0.0f, 0.0f, 1.0f, 0.0f, -50.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    public final float[] b = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    private boolean c = true;

    public QRMaskImageView(Context context) {
        super(context);
    }

    public QRMaskImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setPressed(boolean z) {
        a(z);
        super.setPressed(z);
    }

    private void a(boolean z) {
        if (!this.c) {
            return;
        }
        if (z) {
            setDrawingCacheEnabled(true);
            setColorFilter(new ColorMatrixColorFilter(this.a));
            a(this.a);
            return;
        }
        setColorFilter(new ColorMatrixColorFilter(this.b));
        a(this.b);
    }

    private void a(float[] fArr) {
        if (getBackground() != null) {
            getBackground().setColorFilter(new ColorMatrixColorFilter(fArr));
        }
        if (getDrawable() != null) {
            getDrawable().setColorFilter(new ColorMatrixColorFilter(fArr));
        }
    }
}
