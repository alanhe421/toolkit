package com.qq.reader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import com.tencent.feedback.proguard.R;

/* compiled from: PageHeaderView */
public class s {
    private Context a;
    private Paint b;
    private String c = "";
    private int d;
    private int e;
    private boolean f = true;
    private float g = 0.0f;

    public s(Context context) {
        this.a = context;
        this.d = context.getResources().getDimensionPixelOffset(R.dimen.common_dp_12);
        if (this.e == 0) {
            this.e = context.getResources().getDimensionPixelOffset(R.dimen.common_dp_20);
        }
        a();
        this.d = (int) (((float) this.d) - this.g);
    }

    private void a() {
        this.b = new TextPaint(1);
        this.b.setTextSize((float) this.a.getResources().getDimensionPixelOffset(R.dimen.text_size_class_3));
        this.g = this.b.ascent();
    }

    public void a(int i) {
        this.b.setColor(i);
    }

    public void a(String str) {
        this.c = str;
    }

    public void a(Canvas canvas) {
        if (this.f) {
            canvas.drawText(this.c, (float) this.e, (float) this.d, this.b);
        }
    }
}
