package com.qrcomic.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class QRRelativeLayout extends RelativeLayout {
    private int a;
    private boolean b = true;
    private int c = 3;

    public QRRelativeLayout(Context context) {
        super(context);
    }

    public QRRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public QRRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setDownloadSelectMode() {
        this.a = 1;
    }

    public void setDownloadManagerMode() {
        this.a = 2;
    }

    public void setIsCanSelect(boolean z) {
        this.b = z;
    }

    public void setLookMode(int i) {
        this.c = i;
        if (1 == this.c) {
            setBackgroundDrawable(new ColorDrawable(-855310));
        } else if (2 == this.c) {
            setBackgroundColor(0);
        } else {
            setBackgroundDrawable(null);
        }
    }

    public void draw(Canvas canvas) {
        switch (this.a) {
            case 1:
                if (this.b) {
                    super.draw(canvas);
                    return;
                }
                canvas.saveLayerAlpha(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), 102, 31);
                super.draw(canvas);
                canvas.restore();
                return;
            case 2:
                switch (this.c) {
                    case 2:
                        canvas.saveLayerAlpha(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), 128, 31);
                        super.draw(canvas);
                        canvas.restore();
                        return;
                    default:
                        super.draw(canvas);
                        return;
                }
            default:
                super.draw(canvas);
                return;
        }
    }
}
