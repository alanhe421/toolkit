package com.qq.reader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;
import com.qq.reader.appconfig.a.d;
import com.tencent.feedback.proguard.R;

public class CustomCircle extends TextView {
    float a;
    private Paint b = new Paint();
    private Paint c;

    public CustomCircle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b.setAntiAlias(true);
        this.c = new Paint();
        this.c.setAntiAlias(true);
        this.c.setColor(-10844493);
        this.a = (float) context.getResources().getDimensionPixelOffset(R.dimen.common_dp_2);
    }

    protected void onDraw(Canvas canvas) {
        if (isSelected()) {
            canvas.drawCircle((float) (getMeasuredWidth() / 2), (float) (getMeasuredHeight() / 2), (float) (getMeasuredHeight() / 2), this.c);
            canvas.drawCircle((float) (getMeasuredWidth() / 2), (float) (getMeasuredHeight() / 2), ((float) (getMeasuredHeight() / 2)) - this.a, this.b);
        } else {
            canvas.drawCircle((float) (getMeasuredWidth() / 2), (float) (getMeasuredHeight() / 2), (float) (getMeasuredHeight() / 2), this.b);
        }
        super.onDraw(canvas);
    }

    public void setCustomColor() {
        if (d.m) {
            setCustomBgColor(d.l);
            setText("长按编辑");
        } else {
            setCustomTextColor(getResources().getColor(R.color.text_color_c102));
            setText("自定义");
        }
        postInvalidate();
    }

    public void setCustomBgColor(int i) {
        this.b.setColor(i);
    }

    public void setCustomTextColor(int i) {
        setTextColor(i);
    }
}
