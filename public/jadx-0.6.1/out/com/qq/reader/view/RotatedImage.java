package com.qq.reader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RotatedImage extends ImageView {
    private float a = 0.0f;
    private Handler b = new Handler(this) {
        final /* synthetic */ RotatedImage a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            this.a.a();
        }
    };
    private boolean c = false;

    public RotatedImage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public RotatedImage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RotatedImage(Context context) {
        super(context);
    }

    private void a() {
        if (this.c) {
            this.a += 15.0f;
            invalidate();
            this.b.sendMessageDelayed(this.b.obtainMessage(), 12);
        }
    }

    protected void onDraw(Canvas canvas) {
        canvas.save();
        if (this.a > 360.0f) {
            this.a = 0.0f;
        }
        canvas.rotate(this.a, ((float) getMeasuredWidth()) / 2.0f, ((float) getMeasuredHeight()) / 2.0f);
        super.onDraw(canvas);
        canvas.restore();
    }
}
