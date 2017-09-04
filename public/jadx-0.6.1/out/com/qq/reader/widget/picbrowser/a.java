package com.qq.reader.widget.picbrowser;

import android.view.View;

/* compiled from: ViewUtils */
public class a {
    public static void a(View view, View view2, int[] iArr) {
        if (iArr != null && iArr.length >= 2) {
            int left;
            int i = 0;
            int i2 = 0;
            while (view.getParent() != null) {
                left = view.getLeft() + i2;
                i2 = view.getTop() + i;
                if (view.getParent() == view2) {
                    iArr[0] = left;
                    iArr[1] = i2;
                    if (iArr.length >= 4) {
                        iArr[2] = view.getMeasuredWidth();
                        iArr[3] = view.getMeasuredHeight();
                    }
                    if (view2 == null) {
                        iArr[0] = left;
                        iArr[1] = i2;
                    }
                }
                try {
                    View view3 = (View) view.getParent();
                    if (iArr.length >= 4) {
                        iArr[2] = view3.getMeasuredWidth();
                        iArr[3] = view3.getMeasuredHeight();
                    }
                    view = view3;
                    i = i2;
                    i2 = left;
                } catch (ClassCastException e) {
                    e.printStackTrace();
                }
            }
            left = i2;
            i2 = i;
            if (view2 == null) {
                iArr[0] = left;
                iArr[1] = i2;
            }
        }
    }
}
