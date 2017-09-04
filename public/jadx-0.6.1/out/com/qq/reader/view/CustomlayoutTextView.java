package com.qq.reader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;
import com.qq.reader.common.utils.ao;
import com.qq.reader.readengine.fileparse.a;
import com.qq.reader.readengine.fileparse.c;
import com.qq.reader.readengine.kernel.e;

public class CustomlayoutTextView extends TextView {
    c a;
    private boolean b = true;

    public CustomlayoutTextView(Context context) {
        super(context);
    }

    public CustomlayoutTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomlayoutTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CustomlayoutTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setTextindent(boolean z) {
        this.b = z;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        TextPaint paint = getPaint();
        this.a = new a();
        this.a.f = true;
        this.a.g = getText().toString();
        ao.i();
        com.qq.reader.readengine.a.c.a(this.a, (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), Integer.MAX_VALUE, paint, this.b);
        ao.i();
        float f = 0.0f;
        for (e e : this.a.j()) {
            f = e.e() + f;
        }
        setMeasuredDimension(getMeasuredWidth(), Math.max((int) ((((float) getPaddingTop()) + f) + ((float) getPaddingBottom())), getMeasuredHeight()));
    }

    public void onDraw(Canvas canvas) {
        int currentTextColor = getCurrentTextColor();
        float paddingLeft = (float) getPaddingLeft();
        float paddingTop = (float) getPaddingTop();
        getPaint().setColor(currentTextColor);
        int size = this.a.j().size();
        float ascent = getPaint().ascent();
        float f = paddingTop;
        for (currentTextColor = 0; currentTextColor < size; currentTextColor++) {
            String d = this.a.d(currentTextColor);
            float[] e = this.a.e(currentTextColor);
            float[] fArr = new float[e.length];
            for (int i = 0; i < e.length; i += 2) {
                fArr[i] = e[i] + paddingLeft;
                fArr[i + 1] = f - ascent;
            }
            canvas.drawPosText(d, fArr, getPaint());
            f += ((e) this.a.j().get(currentTextColor)).e();
        }
    }
}
