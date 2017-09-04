package com.qq.reader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.qq.reader.appconfig.a.d;
import com.tencent.feedback.proguard.R;

public class StyleItemView extends View {
    Context a;
    Paint b;
    Paint c;
    Paint d;
    TextPaint e;
    float f;
    private int g = 0;
    private int h = 0;
    private final int i = 1;
    private int j;
    private int k;
    private int l;
    private int m = 0;
    private float n;
    private final int o = 2;
    private String p = new String();

    public StyleItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = context;
        this.f = (float) context.getResources().getDimensionPixelOffset(R.dimen.common_dp_2);
    }

    public StyleItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        this.f = (float) context.getResources().getDimensionPixelOffset(R.dimen.common_dp_2);
    }

    public StyleItemView(Context context) {
        super(context);
        this.a = context;
        this.f = (float) context.getResources().getDimensionPixelOffset(R.dimen.common_dp_2);
    }

    protected void onDraw(Canvas canvas) {
        a();
        canvas.drawRoundRect(new RectF(this.f, this.f, ((float) this.g) - this.f, ((float) this.h) - this.f), 6.0f, 6.0f, this.b);
        canvas.drawRoundRect(new RectF(this.f + 1.0f, this.f + 1.0f, ((float) (this.g - 1)) - this.f, ((float) (this.h - 1)) - this.f), 6.0f, 6.0f, this.c);
        Canvas canvas2 = canvas;
        canvas2.drawLine(getPlusHorizontalStartX(), getPlusHorizontalStartY(), ((float) this.m) + getPlusHorizontalStartX(), getPlusHorizontalStartY(), this.d);
        canvas2 = canvas;
        canvas2.drawLine(getPlusVerticalStartX(), getPlusVerticalStartY(), getPlusVerticalStartX(), ((float) this.m) + getPlusVerticalStartY(), this.d);
        canvas.drawText(this.p, getStrStartX() + 1.0f, getStrStartY(), this.e);
    }

    private void a() {
        if (this.m == 0) {
            this.k = (this.g - 2) / 3;
            this.m = (this.g - 2) - (this.k * 2);
            if (this.m % 2 != 0) {
                this.m++;
            }
            this.l = ((int) ((((float) (this.h - 2)) - this.n) - ((float) this.m))) / 2;
            this.j = (this.h - 2) / 10;
            if (this.j % 2 != 0) {
                this.j++;
            }
            this.d.setStrokeWidth((float) this.j);
        }
    }

    private float getPlusHorizontalStartX() {
        return (float) (this.k + 1);
    }

    private float getPlusHorizontalStartY() {
        return (float) (((this.m / 2) + this.l) + 1);
    }

    private float getPlusVerticalStartX() {
        return getPlusHorizontalStartX() + ((float) (this.m / 2));
    }

    private float getPlusVerticalStartY() {
        return (float) (this.l + 1);
    }

    private float getStrStartX() {
        return ((((float) this.g) - (((float) this.p.length()) * this.n)) / 2.0f) - 2.0f;
    }

    private float getStrStartY() {
        return (float) (this.h - this.l);
    }

    protected void onMeasure(int i, int i2) {
        this.g = MeasureSpec.getSize(i);
        this.h = MeasureSpec.getSize(i2);
        setMeasuredDimension(this.g, this.h);
        b();
        if (d.m) {
            this.c.setColor(d.l);
            this.e.setColor(d.k);
            this.d.setColor(d.k);
            this.p = "长按编辑";
        } else {
            this.c.setColor(-986896);
            this.e.setColor(-11316397);
            this.d.setColor(-11316397);
            this.p = "编辑";
        }
        invalidate();
    }

    private void b() {
        if (this.c == null) {
            this.b = new Paint(1);
            this.b.setColor(-1);
            this.b.setStyle(Style.STROKE);
            this.b.setStrokeWidth(6.0f);
            this.c = new Paint(1);
            this.c.setStyle(Style.FILL);
            this.e = new TextPaint(1);
            this.e.setDither(false);
            this.e.setTypeface(Typeface.SERIF);
            this.e.setTextAlign(Align.LEFT);
            c();
            this.d = new Paint(1);
            this.d.setStyle(Style.STROKE);
        }
    }

    private void c() {
        int i = 10;
        while (i < 20) {
            this.e.setTextSize((float) i);
            this.n = this.e.measureText("我");
            if (this.n < ((float) ((this.g - 2) / 5))) {
                i++;
            } else {
                return;
            }
        }
    }
}
