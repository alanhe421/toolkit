package com.qq.reader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;

public class GuideShadowView extends View {
    private Paint a;
    private Paint b;
    private List<k> c;
    private k d;

    public GuideShadowView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public GuideShadowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GuideShadowView(Context context) {
        super(context);
        this.a = new Paint();
        this.a.setAntiAlias(true);
        this.a.setColor(-1291845632);
        this.b = new Paint();
        this.b.setAntiAlias(true);
        this.b.setColor(-1291845632);
    }

    public void setHighLightRect(k kVar) {
        this.d = kVar;
    }

    public void setHighLightRect(List<k> list) {
        this.c = list;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.d != null) {
            Path path = new Path();
            RectF rectF = new RectF(this.d.a);
            if (this.d.b == 2) {
                path.moveTo(rectF.left, rectF.top);
                path.lineTo(rectF.left, rectF.bottom);
                path.lineTo((rectF.right + rectF.left) / 2.0f, rectF.bottom);
                path.arcTo(rectF, 90.0f, 180.0f, false);
                path.arcTo(rectF, 270.0f, 180.0f, false);
                path.lineTo(rectF.right, rectF.bottom);
                path.lineTo(rectF.right, rectF.top);
                path.lineTo((rectF.right + rectF.left) / 2.0f, rectF.top);
                canvas.drawPath(path, this.b);
            }
            Rect rect = new Rect(0, 0, getRight(), this.d.a.top);
            Rect rect2 = new Rect(0, this.d.a.top, this.d.a.left, this.d.a.bottom);
            Rect rect3 = new Rect(this.d.a.right, this.d.a.top, getRight(), this.d.a.bottom);
            Rect rect4 = new Rect(0, this.d.a.bottom, getRight(), getBottom());
            canvas.drawRect(rect, this.a);
            canvas.drawRect(rect2, this.a);
            canvas.drawRect(rect3, this.a);
            canvas.drawRect(rect4, this.a);
            return;
        }
        canvas.drawRect(new Rect(getLeft(), getTop(), getRight(), getBottom()), this.a);
    }
}
