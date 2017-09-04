package com.qq.reader.module.readpage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.ImageView;

public class FpsView extends ImageView {
    private long a = -1;
    private int b;
    private int c;
    private final Paint d = new Paint();

    public FpsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d.setColor(-1);
        this.d.setAntiAlias(true);
    }

    public void draw(Canvas canvas) {
        if (this.a == -1) {
            this.a = SystemClock.elapsedRealtime();
            this.b = 0;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = elapsedRealtime - this.a;
        super.draw(canvas);
        this.d.setColor(-65536);
        canvas.drawText(this.c + " fps", 20.0f, 20.0f, this.d);
        if (j > 1000) {
            this.a = elapsedRealtime;
            this.c = this.b;
            this.b = 0;
        }
        this.b++;
    }
}
