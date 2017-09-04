package com.qq.reader.widget.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.util.AttributeSet;
import com.qq.reader.c.b;
import com.tencent.smtt.sdk.WebView;

public class NumberCenterCircleProgressBar extends CircleProgressBar {
    private Paint f;
    private Rect g;
    private boolean h;

    public NumberCenterCircleProgressBar(Context context) {
        super(context);
    }

    public NumberCenterCircleProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void a(Context context, AttributeSet attributeSet) {
        super.a(context, attributeSet);
        this.f = new Paint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.CircleProgressBar_CenterNumber);
        int color = obtainStyledAttributes.getColor(1, WebView.NIGHT_MODE_COLOR);
        this.f.setTextSize((float) obtainStyledAttributes.getDimensionPixelSize(0, 20));
        this.f.setColor(color);
        if (obtainStyledAttributes != null) {
            obtainStyledAttributes.recycle();
        }
        this.f.setAntiAlias(true);
        this.g = new Rect();
    }

    protected void onDraw(Canvas canvas) {
        if (!this.h) {
            super.onDraw(canvas);
        } else if (this.d != null) {
            this.d.save();
            this.d.drawColor(0, Mode.CLEAR);
            f(this.d);
            canvas.drawBitmap(this.c, 0.0f, 0.0f, this.b);
        }
    }

    public void f(Canvas canvas) {
        String valueOf = String.valueOf(getCurrentProgress() + "\"");
        this.f.getTextBounds(valueOf, 0, valueOf.length(), this.g);
        canvas.drawText(valueOf, (float) ((canvas.getWidth() - this.g.width()) / 2), (float) ((canvas.getHeight() / 2) + (this.g.height() / 2)), this.f);
    }

    public void setProgress(int i) {
        super.setProgress(i);
        this.h = false;
    }

    public void setMaxProgress(int i) {
        if (i > 0) {
            this.a = i;
        }
    }
}
