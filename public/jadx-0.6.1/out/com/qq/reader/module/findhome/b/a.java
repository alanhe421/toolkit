package com.qq.reader.module.findhome.b;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.g;
import android.support.v7.widget.RecyclerView.p;
import android.view.View;

/* compiled from: FindHomeRecycleDivider */
public class a extends g {
    private static final int[] e = new int[]{16843284};
    private Paint a;
    private Drawable b;
    private int c;
    private int d;

    public a(Context context, int i) {
        this.c = 2;
        if (i == 1 || i == 0) {
            this.d = i;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(e);
            this.b = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            return;
        }
        throw new IllegalArgumentException("请输入正确的参数！");
    }

    public a(Context context, int i, int i2, int i3) {
        this(context, i);
        this.c = i2;
        this.a = new Paint(1);
        this.a.setColor(i3);
        this.a.setStyle(Style.FILL);
    }

    public void a(Rect rect, View view, RecyclerView recyclerView, p pVar) {
        super.a(rect, view, recyclerView, pVar);
        if (this.d == 1) {
            rect.set(0, 0, this.c, 0);
        } else {
            rect.set(0, 0, 0, this.c);
        }
    }

    public void a(Canvas canvas, RecyclerView recyclerView, p pVar) {
        super.a(canvas, recyclerView, pVar);
        if (this.d == 1) {
            d(canvas, recyclerView);
        } else {
            c(canvas, recyclerView);
        }
    }

    private void c(Canvas canvas, RecyclerView recyclerView) {
        int paddingLeft = recyclerView.getPaddingLeft();
        int measuredWidth = recyclerView.getMeasuredWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int bottom = layoutParams.bottomMargin + childAt.getBottom();
            int i2 = bottom + this.c;
            if (this.b != null) {
                this.b.setBounds(paddingLeft, bottom, measuredWidth, i2);
                this.b.draw(canvas);
            }
            if (this.a != null) {
                canvas.drawRect((float) paddingLeft, (float) bottom, (float) measuredWidth, (float) i2, this.a);
            }
        }
    }

    private void d(Canvas canvas, RecyclerView recyclerView) {
        int paddingTop = recyclerView.getPaddingTop();
        int measuredHeight = recyclerView.getMeasuredHeight() - recyclerView.getPaddingBottom();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int right = layoutParams.rightMargin + childAt.getRight();
            int i2 = right + this.c;
            if (this.b != null) {
                this.b.setBounds(right, paddingTop, i2, measuredHeight);
                this.b.draw(canvas);
            }
            if (this.a != null) {
                canvas.drawRect((float) right, (float) paddingTop, (float) i2, (float) measuredHeight, this.a);
            }
        }
    }
}
