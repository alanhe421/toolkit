package com.qq.reader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.qq.reader.common.monitor.debug.c;
import com.tencent.feedback.proguard.R;

public class StrokeLinearLayout extends LinearLayout {
    Paint a;
    int b;
    Point[] c;
    Point[] d;
    Point[] e;
    Point[] f;
    Point[] g;
    private int h;

    public StrokeLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = getResources().getColor(R.color.readpage_vertical_divider_color);
        this.a = new Paint();
        this.b = 30;
        this.c = new Point[4];
        this.d = new Point[2];
        this.e = new Point[2];
        this.f = new Point[2];
        this.g = new Point[2];
        b();
    }

    public StrokeLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = getResources().getColor(R.color.readpage_vertical_divider_color);
        this.a = new Paint();
        this.b = 30;
        this.c = new Point[4];
        this.d = new Point[2];
        this.e = new Point[2];
        this.f = new Point[2];
        this.g = new Point[2];
        b();
    }

    public StrokeLinearLayout(Context context) {
        super(context);
        this.h = getResources().getColor(R.color.readpage_vertical_divider_color);
        this.a = new Paint();
        this.b = 30;
        this.c = new Point[4];
        this.d = new Point[2];
        this.e = new Point[2];
        this.f = new Point[2];
        this.g = new Point[2];
        b();
    }

    private void b() {
        this.a.setColor(this.h);
        this.a.setStrokeWidth(1.0f);
        this.a.setStyle(Style.FILL_AND_STROKE);
        this.a.setAntiAlias(true);
        setBackgroundColor(0);
    }

    public void setStrokeColor(int i) {
        c.a("endpager", "setStrokeColor " + i);
        this.h = i;
        this.a.setColor(this.h);
        invalidate();
    }

    public void setStrokeWidth(float f) {
        this.a.setStrokeWidth(f);
    }

    public void setRadius(int i) {
        this.b = i;
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        onDraw(canvas);
    }

    protected void onDraw(Canvas canvas) {
        a(canvas);
    }

    void a() {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int right = getRight();
        int strokeWidth = (int) this.a.getStrokeWidth();
        c.a("draw", "left " + 0 + " top " + 0 + " right " + right + " bottom " + getBottom() + " strokewidth  " + strokeWidth);
        this.d[0] = new Point((this.b + 0) + strokeWidth, 0 + strokeWidth);
        this.d[1] = new Point(((0 + measuredWidth) - this.b) - strokeWidth, 0 + strokeWidth);
        this.e[0] = new Point((0 + measuredWidth) - strokeWidth, (this.b + 0) + strokeWidth);
        this.e[1] = new Point((0 + measuredWidth) - strokeWidth, ((0 + measuredHeight) - this.b) - strokeWidth);
        this.f[0] = new Point((this.b + 0) + strokeWidth, (0 + measuredHeight) - strokeWidth);
        this.f[1] = new Point(((0 + measuredWidth) - this.b) - strokeWidth, (0 + measuredHeight) - strokeWidth);
        this.g[0] = new Point(0 + strokeWidth, (this.b + 0) + strokeWidth);
        this.g[1] = new Point(0 + strokeWidth, ((0 + measuredHeight) - this.b) - strokeWidth);
        this.c[0] = new Point((this.b + 0) + strokeWidth, (0 + strokeWidth) + this.b);
        this.c[1] = new Point(((0 + measuredWidth) - strokeWidth) - this.b, (0 + strokeWidth) + this.b);
        this.c[2] = new Point(((measuredWidth + 0) - strokeWidth) - this.b, ((0 + measuredHeight) - this.b) - strokeWidth);
        this.c[3] = new Point((0 + strokeWidth) + this.b, ((measuredHeight + 0) - this.b) - strokeWidth);
    }

    void a(Canvas canvas) {
        int i = 0;
        a();
        a(this.d[0], this.d[1], canvas);
        a(this.e[0], this.e[1], canvas);
        a(this.f[0], this.f[1], canvas);
        a(this.g[0], this.g[1], canvas);
        while (i < this.c.length) {
            a(this.c[i], canvas, i);
            i++;
        }
    }

    private void a(Point point, Canvas canvas, int i) {
        canvas.drawArc(new RectF((float) (point.x - this.b), (float) (point.y - this.b), (float) (point.x + this.b), (float) (point.y + this.b)), (float) (((i * 90) + 180) % 360), 90.0f, false, this.a);
    }

    private void a(Point point, Point point2, Canvas canvas) {
        canvas.drawLine((float) point.x, (float) point.y, (float) point2.x, (float) point2.y, this.a);
    }
}
