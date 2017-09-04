package com.qq.reader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.qq.reader.ReaderApplication;
import com.tencent.feedback.proguard.R;

public class QRImageView extends ImageView {
    private Rect a = new Rect();
    private Paint b = new Paint();
    private Drawable c;
    private float d;
    private float e;

    public QRImageView(Context context) {
        super(context);
        a();
    }

    public QRImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.b.setColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.common_cover_bound_stroke_color));
        this.b.setStrokeWidth(1.2f);
        this.b.setStyle(Style.STROKE);
        this.d = getResources().getDimension(R.dimen.common_cover_shadow_width);
        this.e = getResources().getDimension(R.dimen.common_cover_shadow_height);
        this.c = getContext().getResources().getDrawable(R.drawable.common_cover_shadow);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.a.set(0, 0, getWidth(), getHeight());
        this.c.setBounds(0, 0, (int) (((float) (i3 - i)) + (this.d * 2.0f)), (int) (((float) (i4 - i2)) + (this.e * 2.0f)));
    }

    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(-this.d, -this.e);
        this.c.draw(canvas);
        canvas.restore();
        super.onDraw(canvas);
        canvas.drawRect(this.a, this.b);
    }

    public void setBorderColor(int i) {
        if (this.b != null) {
            this.b.setColor(i);
        }
    }

    public void setBorderWidth(float f) {
        if (this.b != null) {
            this.b.setStrokeWidth(f);
        }
    }

    public void setShadowDrawable(Drawable drawable) {
        this.c = drawable;
    }

    public void setShadowWidthAndHeight(int i, int i2) {
        this.d = (float) i;
        this.e = (float) i2;
    }
}
