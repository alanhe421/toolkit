package com.qq.reader.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class SearchSwitcherLayout extends ViewGroup {
    SparseArray<List<View>> a = new SparseArray();
    SparseArray<Integer> b = new SparseArray();
    int c;
    boolean d = false;
    boolean e = true;
    a f;

    public interface a {
        void a(int i);
    }

    public SearchSwitcherLayout(Context context) {
        super(context);
    }

    public SearchSwitcherLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SearchSwitcherLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.e) {
            int size = this.a.size();
            int paddingLeft = getPaddingLeft();
            int i5 = 0;
            int i6 = 0;
            while (i5 < size) {
                List list = (List) this.a.get(i5);
                if (list != null) {
                    int i7 = paddingLeft;
                    for (paddingLeft = 0; paddingLeft < list.size(); paddingLeft++) {
                        View view = (View) list.get(paddingLeft);
                        int measuredWidth = view.getMeasuredWidth();
                        view.layout(i7, i6, i7 + measuredWidth, view.getMeasuredHeight() + i6);
                        i7 += measuredWidth;
                    }
                }
                int intValue = i6 + (this.b.get(i5) == null ? 0 : ((Integer) this.b.get(i5)).intValue());
                paddingLeft = getPaddingLeft();
                i5++;
                i6 = intValue;
            }
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.e = true;
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 0;
        this.a.clear();
        this.b.clear();
        if (this.c != getChildCount()) {
            this.e = true;
        }
        this.c = getChildCount();
        super.onMeasure(i, i2);
        int size = (MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        measureChildren(i, i2);
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < getChildCount()) {
            View childAt = getChildAt(i4);
            int measuredWidth = childAt.getMeasuredWidth();
            int max = Math.max(i5, childAt.getMeasuredHeight());
            if (i6 + measuredWidth <= size) {
                i6 += measuredWidth;
                i5 = i3;
            } else {
                i5 = i3 + 1;
                i6 = measuredWidth;
            }
            this.b.put(i5, Integer.valueOf(max));
            List list = (List) this.a.get(i5);
            if (list == null) {
                list = new ArrayList();
                this.a.put(i5, list);
            }
            list.add(childAt);
            i4++;
            i3 = i5;
            i5 = max;
        }
        if (this.f != null) {
            this.f.a(i3 + 1);
        }
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(getTotalHeight(), 1073741824));
    }

    private int getTotalHeight() {
        int size = this.b.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int intValue;
            if (this.b.get(i) != null) {
                intValue = ((Integer) this.b.get(i)).intValue() + i2;
            } else {
                intValue = i2;
            }
            if (this.d) {
                return intValue;
            }
            i++;
            i2 = intValue;
        }
        return i2;
    }

    public void setFold(boolean z, boolean z2) {
        this.d = z;
        if (z2) {
            requestLayout();
        }
    }

    public void setLinesListener(a aVar) {
        this.f = aVar;
    }
}
