package com.qq.reader.module.bookstore.search;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.WebView;

public class HotWordsView extends ViewGroup {
    private int a = 10;
    private int b = 7;
    private int c = 10;
    private int d = 14;
    private int e = 20;
    private int f = 18;
    private int g = WebView.NIGHT_MODE_COLOR;
    private int h = WebView.NIGHT_MODE_COLOR;
    private int i = 14;
    private int j = 14;
    private int k = 14;
    private int l = 17;
    private final int m = 10;
    private int n = 0;
    private int o = 0;
    private int p = 2;

    public HotWordsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public HotWordsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public HotWordsView(Context context) {
        super(context);
        a();
    }

    public int getWordCount() {
        return this.n;
    }

    public void setNext(int i) {
        this.o = i;
    }

    public int getNext() {
        int i = (this.o - 10) + this.n;
        return i > 0 ? i : 0;
    }

    private void a() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.f = (int) (getResources().getDimension(R.dimen.text_big_size) / getResources().getDisplayMetrics().density);
        this.g = getResources().getColor(R.color.textcolor_black);
        this.h = getResources().getColor(R.color.textcolor_red);
        this.a = (int) TypedValue.applyDimension(1, (float) this.a, displayMetrics);
        this.b = (int) TypedValue.applyDimension(1, (float) this.b, displayMetrics);
        this.c = (int) TypedValue.applyDimension(1, (float) this.c, displayMetrics);
        this.d = (int) TypedValue.applyDimension(1, (float) this.d, displayMetrics);
        this.e = (int) TypedValue.applyDimension(1, (float) this.e, displayMetrics);
        this.i = (int) TypedValue.applyDimension(1, (float) this.i, displayMetrics);
        this.j = (int) TypedValue.applyDimension(1, (float) this.j, displayMetrics);
        this.k = (int) TypedValue.applyDimension(1, (float) this.k, displayMetrics);
        this.l = (int) TypedValue.applyDimension(1, (float) this.l, displayMetrics);
        this.n = 0;
        this.o = 0;
    }

    public void setMaxRaw(int i) {
        if (i >= 0) {
            this.p = i;
        }
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        MeasureSpec.getSize(i2);
        int childCount = getChildCount();
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
            return;
        }
        int i3 = 1;
        int i4 = this.i;
        this.n = childCount;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            childAt.measure(MeasureSpec.makeMeasureSpec((size - this.i) - this.j, Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(0, 0));
            int measuredWidth = childAt.getMeasuredWidth();
            i4 += this.c + measuredWidth;
            if (i4 > size - this.j) {
                i3++;
                if (i3 > this.p) {
                    i3 = this.p;
                    this.n = i5;
                    break;
                }
                i4 = (this.i + measuredWidth) + this.c;
                if (i4 - this.c > size) {
                    i3++;
                    if (i3 > this.p) {
                        i3 = this.p;
                        this.n = i5;
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        this.e = getChildAt(0).getMeasuredHeight();
        setMeasuredDimension(size, ((this.e * i3) + ((i3 - 1) * this.d)) + (this.k + this.l));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width = getWidth();
        int childCount = getChildCount();
        int i5 = this.i;
        int i6 = this.i;
        i6 = this.k;
        int i7 = i5;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (i8 >= this.n) {
                childAt.setVisibility(8);
            } else {
                int measuredWidth = childAt.getMeasuredWidth();
                i5 = (this.c + measuredWidth) + i7;
                if (i5 > width) {
                    i7 = this.i;
                    i5 = (this.i + measuredWidth) + this.c;
                    i6 += this.e + this.d;
                }
                childAt.layout(i7, i6, i5 - this.c, this.e + i6);
                i7 = i5;
            }
        }
    }
}
