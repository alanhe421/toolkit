package com.qq.reader.widget.titler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;

/* compiled from: GrayBgEnableHelper */
public class a implements b {
    private View a;
    private Drawable[] b = new Drawable[2];
    private int c;
    private boolean d = false;
    private int e;
    private int f;
    private int g;

    public a(View view, Context context) {
        this.a = view;
        this.b[0] = context.getResources().getDrawable(R.drawable.gray_btn_oval_drawable);
        this.b[1] = context.getResources().getDrawable(R.drawable.gray_btn_oval_pressed_drawable);
        this.g = ao.a(33.0f);
        this.b[0].setBounds(0, 0, this.g, this.g);
        this.b[1].setBounds(0, 0, this.g, this.g);
    }

    public void a(Canvas canvas, int i, int i2) {
        if (this.d) {
            if (!(this.e == i && this.f == i2)) {
                this.e = i;
                this.f = i2;
            }
            int i3 = (i - this.g) / 2;
            canvas.save();
            canvas.translate((float) i3, (float) i3);
            this.b[this.c].draw(canvas);
            canvas.restore();
        }
    }

    public void a(MotionEvent motionEvent) {
        if (this.d) {
            int i = this.c;
            switch (motionEvent.getAction() & 255) {
                case 0:
                    this.c = 1;
                    break;
                case 1:
                case 3:
                    this.c = 0;
                    break;
                case 2:
                    this.c = 1;
                    break;
            }
            if (i != this.c) {
                this.a.invalidate();
            }
        }
    }

    public void setEnable(boolean z) {
        if (this.d != z) {
            this.a.invalidate();
        }
        this.d = z;
    }
}
