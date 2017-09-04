package com.tencent.theme;

import android.graphics.Paint;
import android.graphics.drawable.Drawable.ConstantState;

public abstract class BaseConstantState extends ConstantState {
    public static final int BITMAP = 0;
    static final int INDEX_DENSITY = 2;
    static final int INDEX_HEIGHT = 1;
    static final int INDEX_WIDTH = 0;
    public static final int SCALE_BITMAP = 1;
    static final Paint sColorPaint = new Paint();
    static final Paint sPaint = new Paint();
    int bitmapType = 0;
    boolean hasProblem;
    int[] mImageSizeWhenOOM;
    SkinData skinData;

    static {
        sPaint.setColor(-65536);
        sPaint.setStrokeWidth(4.0f);
        sColorPaint.setColor(1358888960);
    }

    public static int scaleFromDensity(int i, int i2, int i3) {
        return (i2 == 0 || i3 == 0 || i2 == i3) ? i : ((i * i3) + (i2 >> 1)) / i2;
    }
}
