package com.qrcomic.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RadioButton;
import b.a.a.a.a.a.d;
import com.qrcomic.screenshot.a.b;

public class SegmentRadioButton extends RadioButton {
    private Bitmap a;
    private int b = 0;
    private int c = b.a(2.0f);
    private volatile boolean d = false;
    private Bitmap e;

    public SegmentRadioButton(Context context) {
        super(context);
    }

    public SegmentRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean getRedHotStatus() {
        return this.d;
    }

    public void setRedHotStatus(boolean z) {
        if (this.d != z) {
            this.d = z;
            invalidate();
        }
    }

    private Bitmap a(Context context) {
        if (this.a == null || this.a.isRecycled()) {
            try {
                this.a = BitmapFactory.decodeResource(context.getResources(), d.skin_tips_dot);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
        return this.a;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.e = a(getContext());
        int a = (this.e == null || this.e.isRecycled()) ? b.a(9.0f) : this.e.getWidth();
        this.b = (i - a) - b.a(3.0f);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.d) {
            this.e = a(getContext());
            if (this.e != null && !this.e.isRecycled()) {
                canvas.drawBitmap(this.e, (float) this.b, (float) this.c, null);
            }
        }
    }
}
