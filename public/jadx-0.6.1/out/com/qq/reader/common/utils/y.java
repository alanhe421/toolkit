package com.qq.reader.common.utils;

import android.view.View;
import android.widget.AbsListView;
import java.util.Hashtable;

/* compiled from: QuickReturnUtils */
public class y {
    private static Hashtable<Integer, Integer> a = new Hashtable();

    public static int a(AbsListView absListView) {
        int i = 0;
        View childAt = absListView.getChildAt(0);
        if (childAt != null) {
            int firstVisiblePosition = absListView.getFirstVisiblePosition();
            int i2 = -childAt.getTop();
            a.put(Integer.valueOf(absListView.getFirstVisiblePosition()), Integer.valueOf(childAt.getHeight()));
            if (i2 < 0) {
                i2 = 0;
            }
            int i3 = 0;
            i = i2;
            while (i3 < firstVisiblePosition) {
                i2 = a.get(Integer.valueOf(i3)) != null ? ((Integer) a.get(Integer.valueOf(i3))).intValue() + i : i;
                i3++;
                i = i2;
            }
        }
        return i;
    }

    public static void a() {
        a.clear();
    }
}
