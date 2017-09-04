package com.qq.reader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.tencent.feedback.proguard.R;

public class PageHeader extends View {
    private Context a;
    private Paint b;
    private String c = "";
    private int d;
    private int e;
    private int f;
    private int g;
    private boolean h = true;
    private float i = 0.0f;

    public PageHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        this.d = context.getResources().getDimensionPixelOffset(R.dimen.common_dp_12);
        if (this.e == 0) {
            this.e = getResources().getDimensionPixelOffset(R.dimen.common_dp_20);
        }
        a();
        this.d = (int) (((float) this.d) - this.i);
    }

    private void a() {
        this.b = new TextPaint(1);
        this.b.setTextSize((float) this.a.getResources().getDimensionPixelOffset(R.dimen.text_size_class_3));
        this.i = this.b.ascent();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.g = MeasureSpec.getSize(i);
        this.f = MeasureSpec.getSize(i2);
        setMeasuredDimension(this.g, this.f);
    }

    public void setColor(int i) {
        this.b.setColor(i);
    }

    public void setChapterName(String str) {
        this.c = str;
    }

    protected void onDraw(Canvas canvas) {
        if (this.h) {
            super.onDraw(canvas);
            canvas.drawText(this.c, (float) this.e, (float) this.d, this.b);
        }
    }

    public void setShow(boolean z) {
        this.h = z;
    }
}
