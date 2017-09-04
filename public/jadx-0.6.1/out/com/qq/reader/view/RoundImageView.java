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
import android.widget.ImageView;

public class RoundImageView extends ImageView {
    private int a = 15;
    private Paint b;
    private Paint c;
    private float d = 0.0f;
    private int e;
    private int f;

    public RoundImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    public RoundImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public RoundImageView(Context context) {
        super(context);
        a(context, null);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.b = new Paint();
        this.b.setColor(-1);
        this.b.setAntiAlias(true);
        this.b.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
        this.c = new Paint();
        this.c.setXfermode(null);
    }

    public void setRadius(float f) {
        this.d = f;
    }

    public void setType(int i) {
        this.a = i;
    }

    public void draw(Canvas canvas) {
        if (this.d > 0.0f) {
            this.e = getScrollX();
            this.f = getScrollY();
            Bitmap createBitmap = Bitmap.createBitmap(this.e + getWidth(), this.f + getHeight(), Config.ARGB_8888);
            if (createBitmap != null) {
                Canvas canvas2 = new Canvas(createBitmap);
                super.draw(canvas2);
                if ((this.a & 1) != 0) {
                    a(canvas2);
                }
                if ((this.a & 2) != 0) {
                    d(canvas2);
                }
                if ((this.a & 4) != 0) {
                    b(canvas2);
                }
                if ((this.a & 8) != 0) {
                    c(canvas2);
                }
                canvas.drawBitmap(createBitmap, 0.0f, 0.0f, this.c);
                createBitmap.recycle();
                return;
            }
            super.draw(canvas);
            return;
        }
        super.draw(canvas);
    }

    private void a(Canvas canvas) {
        Path path = new Path();
        path.moveTo((float) this.e, this.d);
        path.lineTo((float) this.e, (float) this.f);
        path.lineTo(this.d, (float) this.f);
        path.arcTo(new RectF((float) this.e, (float) this.f, ((float) this.e) + (this.d * 2.0f), ((float) this.f) + (this.d * 2.0f)), -90.0f, -90.0f);
        path.close();
        canvas.drawPath(path, this.b);
    }

    private void b(Canvas canvas) {
        Path path = new Path();
        path.moveTo((float) this.e, ((float) (this.f + getHeight())) - this.d);
        path.lineTo((float) this.e, (float) (this.f + getHeight()));
        path.lineTo(((float) this.e) + this.d, (float) (this.f + getHeight()));
        path.arcTo(new RectF((float) this.e, ((float) (this.f + getHeight())) - (this.d * 2.0f), ((float) this.e) + (this.d * 2.0f), (float) (this.f + getHeight())), 90.0f, 90.0f);
        path.close();
        canvas.drawPath(path, this.b);
    }

    private void c(Canvas canvas) {
        Path path = new Path();
        path.moveTo(((float) (this.e + getWidth())) - this.d, (float) (this.f + getHeight()));
        path.lineTo((float) (this.e + getWidth()), (float) (this.f + getHeight()));
        path.lineTo((float) (this.e + getWidth()), ((float) (this.f + getHeight())) - this.d);
        path.arcTo(new RectF(((float) (this.e + getWidth())) - (this.d * 2.0f), ((float) (this.f + getHeight())) - (this.d * 2.0f), (float) (this.e + getWidth()), (float) (this.f + getHeight())), 0.0f, 90.0f);
        path.close();
        canvas.drawPath(path, this.b);
    }

    private void d(Canvas canvas) {
        Path path = new Path();
        path.moveTo((float) (this.e + getWidth()), ((float) this.f) + this.d);
        path.lineTo((float) (this.e + getWidth()), (float) this.f);
        path.lineTo(((float) (this.e + getWidth())) - this.d, (float) this.f);
        path.arcTo(new RectF(((float) (this.e + getWidth())) - (this.d * 2.0f), (float) this.f, (float) (this.e + getWidth()), ((float) this.f) + (this.d * 2.0f)), -90.0f, 90.0f);
        path.close();
        canvas.drawPath(path, this.b);
    }
}
