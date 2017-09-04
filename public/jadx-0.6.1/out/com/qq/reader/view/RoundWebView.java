package com.qq.reader.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;

public class RoundWebView extends FixedWebView {
    private Paint a;
    private Paint b;
    private float c = 0.0f;
    private int d;
    private int e;

    public RoundWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public RoundWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public RoundWebView(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.a = new Paint();
        this.a.setColor(-1);
        this.a.setAntiAlias(true);
        this.a.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
        this.b = new Paint();
        this.b.setXfermode(null);
    }

    public void setRadius(float f) {
        this.c = f;
    }

    public void draw(Canvas canvas) {
        if (this.c > 0.0f) {
            this.d = getScrollX();
            this.e = getScrollY();
            Bitmap createBitmap = Bitmap.createBitmap(this.d + getWidth(), this.e + getHeight(), Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap);
            super.draw(canvas2);
            a(canvas2);
            d(canvas2);
            b(canvas2);
            c(canvas2);
            canvas.drawBitmap(createBitmap, 0.0f, 0.0f, this.b);
            createBitmap.recycle();
            return;
        }
        super.draw(canvas);
    }

    private void a(Canvas canvas) {
        Path path = new Path();
        path.moveTo((float) this.d, this.c);
        path.lineTo((float) this.d, (float) this.e);
        path.lineTo(this.c, (float) this.e);
        path.arcTo(new RectF((float) this.d, (float) this.e, ((float) this.d) + (this.c * 2.0f), ((float) this.e) + (this.c * 2.0f)), -90.0f, -90.0f);
        path.close();
        canvas.drawPath(path, this.a);
    }

    private void b(Canvas canvas) {
        Path path = new Path();
        path.moveTo((float) this.d, ((float) (this.e + getHeight())) - this.c);
        path.lineTo((float) this.d, (float) (this.e + getHeight()));
        path.lineTo(((float) this.d) + this.c, (float) (this.e + getHeight()));
        path.arcTo(new RectF((float) this.d, ((float) (this.e + getHeight())) - (this.c * 2.0f), ((float) this.d) + (this.c * 2.0f), (float) (this.e + getHeight())), 90.0f, 90.0f);
        path.close();
        canvas.drawPath(path, this.a);
    }

    private void c(Canvas canvas) {
        Path path = new Path();
        path.moveTo(((float) (this.d + getWidth())) - this.c, (float) (this.e + getHeight()));
        path.lineTo((float) (this.d + getWidth()), (float) (this.e + getHeight()));
        path.lineTo((float) (this.d + getWidth()), ((float) (this.e + getHeight())) - this.c);
        path.arcTo(new RectF(((float) (this.d + getWidth())) - (this.c * 2.0f), ((float) (this.e + getHeight())) - (this.c * 2.0f), (float) (this.d + getWidth()), (float) (this.e + getHeight())), 0.0f, 90.0f);
        path.close();
        canvas.drawPath(path, this.a);
    }

    private void d(Canvas canvas) {
        Path path = new Path();
        path.moveTo((float) (this.d + getWidth()), ((float) this.e) + this.c);
        path.lineTo((float) (this.d + getWidth()), (float) this.e);
        path.lineTo(((float) (this.d + getWidth())) - this.c, (float) this.e);
        path.arcTo(new RectF(((float) (this.d + getWidth())) - (this.c * 2.0f), (float) this.e, (float) (this.d + getWidth()), ((float) this.e) + (this.c * 2.0f)), -90.0f, 90.0f);
        path.close();
        canvas.drawPath(path, this.a);
    }
}
