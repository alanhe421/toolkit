package com.qq.reader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.qq.reader.common.utils.ao;
import com.qq.reader.readengine.kernel.b;
import com.tencent.feedback.proguard.R;

public class BatterView extends View {
    Context a;
    Paint b;
    Paint c;
    Paint d;
    Paint e;
    int f;
    int g;
    int h;
    int i;
    int j;
    int k;
    int l = 0;
    int m;
    int n = 0;
    int o = 0;
    public int p;
    private String q;
    private String r;
    private String s;
    private int t = 5;
    private int u = 0;
    private b v;
    private boolean w = true;

    public BatterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        this.t = context.getResources().getDimensionPixelOffset(R.dimen.common_dp_2);
        this.g = b(context.getResources().getDimensionPixelOffset(R.dimen.common_dp_11)) - this.t;
        this.h = this.g - context.getResources().getDimensionPixelOffset(R.dimen.common_dp_2);
        this.p = context.getResources().getDimensionPixelOffset(R.dimen.common_dp_1);
        this.k = this.h / 2;
        this.i = (((this.h * 11) / 7) * 9) / 10;
        this.j = ((this.h * 11) / 7) - this.i;
        if (this.n == 0) {
            this.n = context.getResources().getDimensionPixelOffset(R.dimen.common_dp_20);
        }
        this.o = context.getResources().getDimensionPixelOffset(R.dimen.common_dp_20);
    }

    public void a(b bVar) {
        this.v = bVar;
    }

    public void setType(int i) {
        this.u = i;
    }

    public void setColor(int i) {
        if (this.b == null) {
            this.b = new Paint(1);
            this.b.setStyle(Style.STROKE);
            this.c = new Paint(1);
            this.c.setStyle(Style.FILL);
            this.d = new Paint(1);
            this.d.setStyle(Style.FILL);
        }
        this.b.setColor(i);
        this.c.setColor(i);
        this.d.setColor(i);
        this.e.setColor(i);
    }

    public void setValue(int i) {
        if (i > 100) {
            this.f = a(i);
        } else {
            this.f = i;
        }
    }

    private int a(int i) {
        if (i > 100) {
            return a(i / 10);
        }
        return i;
    }

    public void draw(Canvas canvas) {
        onDraw(canvas);
    }

    protected void onDraw(Canvas canvas) {
        if (this.w) {
            int i = this.o;
            int i2 = this.g - this.h;
            canvas.drawRect((float) i, (float) i2, (float) (this.i + i), (float) this.h, this.b);
            int i3 = i + 2;
            canvas.drawRect((float) (i + 2), (float) (i2 + 2), (float) (i3 + (((((this.i + i) - 2) - i3) * this.f) / 100)), (float) (this.h - 2), this.c);
            canvas.drawRect((float) (this.i + i), (float) (((this.h - this.k) / 2) + i2), (float) ((this.i + i) + this.j), (float) (this.h - ((this.h - this.k) / 2)), this.d);
            FontMetrics fontMetrics = this.e.getFontMetrics();
            i3 = fontMetrics.ascent != 0.0f ? (int) fontMetrics.ascent : 0;
            canvas.drawText(" " + this.q, (float) ((this.i + i) + this.j), (float) ((i2 - i3) - this.t), this.e);
            this.s = this.v.i();
            if (this.u != 1) {
                canvas.drawText(this.s, (((float) this.l) - this.e.measureText(this.s)) / 2.0f, (float) ((i2 - i3) - this.t), this.e);
            }
            this.r = ao.a(this.v.g().doubleValue());
            canvas.drawText(this.r, (float) ((this.l - this.n) - ((int) this.e.measureText(this.r))), (float) ((i2 - i3) - this.t), this.e);
            super.onDraw(canvas);
        }
    }

    protected void onMeasure(int i, int i2) {
        this.l = MeasureSpec.getSize(i);
        this.m = MeasureSpec.getSize(i2);
        setMeasuredDimension(this.l, (this.h + this.t) + this.p);
    }

    private int b(int i) {
        this.e = new TextPaint(1);
        this.e.setTextSize((float) i);
        FontMetrics fontMetrics = this.e.getFontMetrics();
        return (int) Math.ceil((double) (fontMetrics.descent - fontMetrics.ascent));
    }

    public void setTime(CharSequence charSequence) {
        if (charSequence != null) {
            this.q = charSequence.toString();
        }
    }

    public void setPercent(String str) {
        if (str != null) {
            this.r = str;
        }
    }

    public void setRealPage(String str) {
        if (str != null) {
            this.s = str;
        }
    }

    public void setShow(boolean z) {
        this.w = z;
    }
}
