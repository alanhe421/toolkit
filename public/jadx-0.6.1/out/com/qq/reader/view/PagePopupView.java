package com.qq.reader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.qq.reader.readengine.fileparse.a;
import com.qq.reader.readengine.fileparse.c;
import java.util.ArrayList;

public class PagePopupView extends View {
    Context a;
    TextPaint b;
    int c;
    private String d = null;
    private boolean e = false;
    private c f = null;
    private ArrayList<Float> g = new ArrayList();

    public PagePopupView(Context context) {
        super(context);
        this.a = context;
        a();
    }

    public PagePopupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        a();
    }

    private void a() {
        this.f = new a();
        this.b = new TextPaint();
        this.b.setAntiAlias(true);
        this.b.setDither(false);
        this.b.setTypeface(Typeface.SANS_SERIF);
        this.b.setTextSize(18.0f);
        this.b.setColor(-11584207);
        this.b.setTextAlign(Align.LEFT);
        this.b.setTextAlign(Align.CENTER);
    }

    protected TextPaint getPopViewTextPaint() {
        return this.b;
    }

    public void draw(Canvas canvas) {
        onDraw(canvas);
    }

    protected void onDraw(Canvas canvas) {
        float paddingLeft = (float) getPaddingLeft();
        float paddingTop = (float) getPaddingTop();
        for (int i = 0; i < this.f.k(); i++) {
            String d = this.f.d(i);
            float[] e = this.f.e(i);
            float[] fArr = new float[e.length];
            for (int i2 = 0; i2 < e.length; i2 += 2) {
                fArr[i2] = e[i2] + paddingLeft;
                fArr[i2 + 1] = paddingTop;
            }
            canvas.drawPosText(d, fArr, this.b);
            paddingTop += getRenderLineHeight();
        }
    }

    public void setText(String str) {
        this.d = str;
        this.f.g = this.d;
        this.e = false;
    }

    protected void onMeasure(int i, int i2) {
        if (!this.e) {
            this.g = com.qq.reader.readengine.a.c.a(this.f, this.c, 0, this.b, false);
            this.e = true;
        }
        setMeasuredDimension(MeasureSpec.getSize(i), ((int) getRenderLineHeight()) * this.f.k());
    }

    public float getRenderLineHeight() {
        return getTextPaintLineHeight() + this.b.descent();
    }

    public float getTextPaintLineHeight() {
        return (this.b.getTextSize() * 120.0f) / 100.0f;
    }

    public void setMaxWidth(int i) {
        this.c = i;
    }
}
