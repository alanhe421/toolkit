package com.qq.reader.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.util.ArrayList;
import java.util.List;

public class InterruptView extends ImageView {
    private List<l> a = new ArrayList();
    private a b;

    public interface a {
        void a(int i, int i2);
    }

    public InterruptView(Context context) {
        super(context);
        setPadding(0, 0, 0, 0);
        setScaleType(ScaleType.FIT_XY);
        setLayoutParams(new LayoutParams(-1, -1));
    }

    protected void onDetachedFromWindow() {
        this.a.clear();
        super.onDetachedFromWindow();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        switch (motionEvent.getAction()) {
            case 1:
                a(rawX, rawY);
                return true;
            default:
                return super.onTouchEvent(motionEvent);
        }
    }

    private void a(int i, int i2) {
        if (this.b != null) {
            this.b.a(i, i2);
        }
    }

    public void setInterruptListener(a aVar) {
        this.b = aVar;
    }
}
