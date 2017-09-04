package com.qq.reader.module.feed.head;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import com.qq.reader.common.c.b;
import com.qq.reader.common.monitor.debug.c;

public class RisingNumberView extends View implements Runnable {
    private static int i = 30;
    private static int j = 1500;
    private static int k = (j / i);
    float a = getResources().getDisplayMetrics().density;
    long b;
    long c;
    private int d;
    private int e;
    private Canvas f;
    private Paint g = new Paint();
    private Paint h = new Paint();
    private long l;
    private long m = 0;
    private String n = "";
    private float o;
    private float p;
    private Handler q = new Handler();
    private int r;

    public RisingNumberView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public RisingNumberView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RisingNumberView(Context context) {
        super(context);
    }

    protected void onMeasure(int i, int i2) {
        this.h.setAntiAlias(true);
        this.h.setColor(-1);
        this.h.setTextSize(a((float) (this.l == 0 ? 20 : 13)));
        this.g.setAntiAlias(true);
        this.g.setColor(-1);
        this.g.setTextSize(a(30.0f));
        this.e = (int) ((this.g.measureText(this.l + "") + this.h.measureText(this.n)) + a(2.0f));
        this.d = (int) (((this.g.descent() - this.g.ascent()) + a(4.0f)) + a(1.0f));
        this.o = (((float) this.d) - this.g.descent()) - a(1.0f);
        this.p = this.g.measureText(this.l + "") + a(2.0f);
        setMeasuredDimension(this.e, this.d);
    }

    public void setNumber(long j) {
        this.l = j;
    }

    public void setText(String str) {
        if (str != null) {
            this.n = str;
        }
    }

    private float a(float f) {
        return (this.a * f) + 0.5f;
    }

    private void d() {
        this.m = 0;
        this.r = 0;
        if (b.d == 0) {
            i = 30;
            j = 1500;
            k = j / i;
        } else if (b.d == 1) {
            i = 40;
            j = 2200;
            k = j / i;
        } else if (b.d == 2) {
            i = 50;
            j = 2800;
            k = j / i;
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f = canvas;
        a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        try {
            if (this.q != null) {
                this.q.removeCallbacks(this);
            }
        } catch (Throwable th) {
            c.e("RisingNumberView", th.getMessage());
        }
    }

    void a() {
        if (this.l == 0) {
            this.f.drawText(this.n, 0.0f, this.o, this.h);
        } else if (this.r < k) {
            this.f.drawText(this.m + "", getNumberPosX(), this.o, this.g);
            this.f.drawText(this.n, this.p + 0.0f, this.o, this.h);
            this.m = (long) (((float) this.l) * a(this.r));
            this.r++;
        } else {
            this.f.drawText(this.l + "", 0.0f, this.o, this.g);
            this.f.drawText(this.n, this.p, this.o, this.h);
            this.q.removeCallbacks(this);
        }
    }

    private float getNumberPosX() {
        if (String.valueOf(this.m).length() == String.valueOf(this.l).length()) {
            return 0.0f;
        }
        return this.g.measureText(this.l + "") - this.g.measureText(this.m + "");
    }

    private float a(int i) {
        return (float) (1.0d - Math.pow((double) (1.0f - (((float) i) / ((float) k))), 4.0d));
    }

    public void b() {
        if (b.c == 0) {
            d();
        }
        this.q.post(this);
    }

    public void c() {
        if (this.q != null) {
            this.q.removeCallbacksAndMessages(null);
        }
    }

    public void run() {
        invalidate();
        if (this.m < this.l) {
            this.b = (((long) i) + this.c) - System.currentTimeMillis();
            this.c = System.currentTimeMillis();
            this.q.postDelayed(this, this.b);
        }
    }
}
