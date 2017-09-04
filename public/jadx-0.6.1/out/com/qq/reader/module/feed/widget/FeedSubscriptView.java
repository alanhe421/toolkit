package com.qq.reader.module.feed.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.TextView;

public class FeedSubscriptView extends TextView {
    private Paint a = new Paint();
    private Paint b;
    private Path c;
    private Path d;

    public FeedSubscriptView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a.setColor(-10706458);
        this.a.setStrokeCap(Cap.ROUND);
        this.a.setAntiAlias(true);
        this.c = new Path();
        this.b = new Paint();
        this.b.setColor(getTextColors().getDefaultColor());
        this.b.setStrokeCap(Cap.ROUND);
        this.b.setTextSize(getTextSize());
        this.b.setAntiAlias(true);
        this.d = new Path();
        setLayerType(1, null);
    }

    public void setConceptBgColor(int i) {
        this.a.setColor(i);
        postInvalidate();
    }

    protected void onDraw(Canvas canvas) {
        this.c.moveTo(0.0f, 0.0f);
        this.c.lineTo((float) getWidth(), 0.0f);
        this.c.lineTo((float) getWidth(), (float) getHeight());
        this.c.lineTo(0.0f, 0.0f);
        canvas.drawPath(this.c, this.a);
        this.d.moveTo(((float) getMeasuredWidth()) * 0.4f, 0.0f);
        this.d.lineTo((float) getMeasuredWidth(), ((float) getMeasuredHeight()) * 0.6f);
        canvas.drawTextOnPath(getText().toString(), this.d, getTextSize() / 2.0f, getTextSize() / 2.0f, this.b);
    }
}
