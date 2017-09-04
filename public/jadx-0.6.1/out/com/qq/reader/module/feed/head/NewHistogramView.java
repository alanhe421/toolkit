package com.qq.reader.module.feed.head;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.qq.reader.common.c.b;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewHistogramView extends View implements Runnable {
    float a = getResources().getDisplayMetrics().density;
    long b;
    long c;
    private Canvas d;
    private Paint e = new Paint();
    private Paint f = new Paint();
    private List<Float> g = new ArrayList();
    private float h;
    private float i;
    private float j;
    private int k;
    private Handler l = new Handler();
    private Random m = new Random();
    private int n;

    public NewHistogramView(Context context) {
        super(context);
        e();
    }

    public NewHistogramView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        e();
    }

    public NewHistogramView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        e();
    }

    private float a(float f) {
        return (this.a * f) + 0.5f;
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        this.j = (float) size2;
        d();
        setMeasuredDimension(size, size2);
    }

    private void d() {
        this.e.setARGB(255, 255, 255, 255);
        this.f.setARGB(102, 255, 255, 255);
        this.h = a(23.0f);
        this.i = a(31.0f);
        this.c = System.currentTimeMillis();
    }

    private void e() {
        this.g.clear();
        if (this.m.nextInt() % 2 == 0) {
            this.g.add(Float.valueOf(a(20.0f)));
            this.g.add(Float.valueOf(a(45.0f)));
            this.g.add(Float.valueOf(a(60.0f)));
            this.g.add(Float.valueOf(a(35.0f)));
        } else {
            this.g.add(Float.valueOf(a(35.0f)));
            this.g.add(Float.valueOf(a(60.0f)));
            this.g.add(Float.valueOf(a(45.0f)));
            this.g.add(Float.valueOf(a(20.0f)));
        }
        int size = this.g.size();
        this.k = 0;
        for (int i = 0; i < size; i++) {
            if (((Float) this.g.get(this.k)).floatValue() <= ((Float) this.g.get(i)).floatValue()) {
                this.k = i;
            }
        }
        this.n = 0;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.d = canvas;
        a();
    }

    void a() {
        if (this.n < 52) {
            a(0);
            a(1);
            a(2);
            a(3);
            this.n++;
            return;
        }
        a(0);
        a(1);
        a(2);
        a(3);
        this.l.removeCallbacks(this);
    }

    private void a(int i) {
        float f;
        float f2 = this.i * ((float) i);
        if (this.n < i * 4) {
            f = 0.0f;
        } else if (this.n < i * 4 || this.n > (i * 4) + 40) {
            f = ((Float) this.g.get(i)).floatValue() * b(40);
        } else {
            f = ((Float) this.g.get(i)).floatValue() * b(this.n - (i * 4));
        }
        if (this.k == i) {
            this.d.drawRect(f2, this.j - f, this.h + f2, this.j, this.e);
        } else {
            this.d.drawRect(f2, this.j - f, this.h + f2, this.j, this.f);
        }
    }

    private float b(int i) {
        if (i < 30) {
            return (float) (((Math.cos(((double) ((((float) i) / 30.0f) + 1.0f)) * 3.141592653589793d) / 2.0d) + 0.5d) * 1.2000000476837158d);
        }
        return (float) (1.2d - ((double) (((float) (i - 30)) / 50.0f)));
    }

    public void run() {
        invalidate();
        if (this.n < 52) {
            this.b = (25 + this.c) - System.currentTimeMillis();
            this.c = System.currentTimeMillis();
            if (this.b > 0) {
                this.l.postDelayed(this, this.b);
            } else {
                this.l.postDelayed(this, 0);
            }
        }
    }

    public void b() {
        if (b.c == 0) {
            e();
        }
        this.l.post(this);
    }

    public void c() {
        if (this.l != null) {
            this.l.removeCallbacksAndMessages(null);
        }
    }
}
