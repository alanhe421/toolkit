package com.qq.reader.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListAdapter;
import com.qq.reader.view.pullupdownlist.XListView;
import com.tencent.feedback.proguard.R;

public class PinnedHeaderListView extends XListView {
    private a b;
    private View c;
    private View d;
    private boolean e;
    private int f;
    private int g;

    public interface a {
        int a(int i);

        void a(View view, int i, int i2);
    }

    public PinnedHeaderListView(Context context) {
        super(context);
    }

    public PinnedHeaderListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PinnedHeaderListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setPinnedHeaderView(View view) {
        this.c = view;
        this.d = view.findViewById(R.id.bottom_shadow);
        if (this.c != null) {
            setFadingEdgeLength(0);
        }
        requestLayout();
    }

    public void setAdapter(ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
        this.b = (a) listAdapter;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.c != null) {
            measureChild(this.c, i, i2);
            this.f = this.c.getMeasuredWidth();
            this.g = this.c.getMeasuredHeight();
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.c != null) {
            this.c.layout(0, 0, this.f, this.g);
            a(getFirstVisiblePosition());
        }
    }

    public void a(int i) {
        int i2 = 255;
        if (this.c != null && this.b != null) {
            int i3;
            switch (this.b.a(i)) {
                case 0:
                    this.e = false;
                    return;
                case 1:
                    i3 = this.g;
                    this.b.a(this.c, i, 255);
                    if (this.c.getTop() != 0) {
                        this.c.layout(0, 0, this.f, i3);
                    } else {
                        this.c.layout(0, 0, this.f, this.d.getMeasuredHeight() + i3);
                    }
                    this.e = true;
                    return;
                case 2:
                    View childAt = getChildAt(0);
                    int bottom = childAt.getBottom();
                    childAt.getHeight();
                    int height = this.c.getHeight();
                    if (bottom < height - this.d.getHeight()) {
                        i3 = this.d.getHeight() + (bottom - height);
                        i2 = ((height + i3) * 255) / height;
                    } else {
                        i3 = 0;
                    }
                    this.b.a(this.c, i, i2);
                    if (this.c.getTop() != i3) {
                        this.c.layout(0, i3, this.f, this.g + i3);
                    }
                    this.e = true;
                    return;
                default:
                    return;
            }
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.e) {
            drawChild(canvas, this.c, getDrawingTime());
        }
    }
}
