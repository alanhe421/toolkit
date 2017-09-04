package com.qrcomic.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

public class QRComicIOSProgressBar extends View {
    DisplayMetrics a;
    private Paint b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private RectF i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private boolean v;
    private int w;

    public QRComicIOSProgressBar(Context context) {
        this(context, null);
    }

    public QRComicIOSProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public QRComicIOSProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = new RectF();
        this.t = 100;
        this.v = true;
        this.b = new Paint();
        this.b.setAntiAlias(true);
        this.a = context.getResources().getDisplayMetrics();
        this.f = (int) (TypedValue.applyDimension(1, 2.5f, this.a) + 0.5f);
        this.w = (int) (TypedValue.applyDimension(1, 1.5f, this.a) + 0.5f);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = this.v ? -344569 : -4473925;
        a();
        canvas.save();
        this.b.setStyle(Style.FILL);
        this.b.setColor(268435455);
        canvas.drawCircle((float) this.g, (float) this.h, (float) this.d, this.b);
        this.b.setStyle(Style.STROKE);
        this.b.setColor(i);
        this.b.setStrokeWidth((float) this.w);
        canvas.drawCircle((float) this.g, (float) this.h, (float) this.d, this.b);
        this.b.setStrokeWidth((float) this.f);
        canvas.drawArc(this.i, -90.0f, (float) ((this.u * 360) / this.t), false, this.b);
        this.b.setColor(-344569);
        this.b.setStrokeWidth((float) this.w);
        if (this.v) {
            canvas.drawLine((float) this.j, (float) this.l, (float) this.j, (float) this.m, this.b);
            canvas.drawLine((float) this.k, (float) this.l, (float) this.k, (float) this.m, this.b);
        } else {
            canvas.drawLine((float) (this.n + 1), (float) this.r, (float) this.o, (float) this.s, this.b);
            canvas.drawLine((float) (this.p - 1), (float) this.r, (float) this.o, (float) this.s, this.b);
            canvas.drawLine((float) this.o, (float) this.q, (float) this.o, (float) this.s, this.b);
            canvas.drawLine((float) this.n, (float) this.s, (float) this.p, (float) this.s, this.b);
        }
        canvas.restore();
    }

    public synchronized int getMax() {
        return this.t;
    }

    public synchronized void setMax(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("the max progress must be greater than 0.");
        }
        this.t = i;
    }

    public synchronized int getProgress() {
        return this.u;
    }

    public synchronized void setProgress(int i) {
        this.u = Math.max(0, Math.min(i, this.t));
        postInvalidate();
    }

    public synchronized void setRunningState(boolean z) {
        this.v = z;
        postInvalidate();
    }

    private void a() {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        this.c = (getWidth() - (paddingLeft * 2)) / 2;
        this.g = this.c + paddingLeft;
        this.h = this.c + paddingTop;
        this.d = this.c - this.w;
        this.e = this.d - (this.f / 2);
        RectF rectF = this.i;
        float f = (float) (this.c - this.e);
        this.i.top = f;
        rectF.left = f;
        rectF = this.i;
        f = (float) (this.c + this.e);
        this.i.bottom = f;
        rectF.right = f;
        this.i.offset((float) paddingLeft, (float) paddingTop);
        int i = this.d - this.f;
        this.j = (this.c - (i / 3)) + paddingLeft;
        this.k = (this.c + (i / 3)) + paddingLeft;
        this.l = (this.c - (i / 2)) + paddingTop;
        this.m = (this.c + (i / 2)) + paddingTop;
        this.n = ((int) (((double) this.c) - (((double) i) * 0.4d))) + paddingLeft;
        this.o = this.c + paddingLeft;
        this.p = paddingLeft + ((int) (((double) this.c) + (((double) i) * 0.4d)));
        this.q = this.l;
        this.r = (this.c - (i / 10)) + paddingTop;
        this.s = this.m - (i / 10);
    }
}
