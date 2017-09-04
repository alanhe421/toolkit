package com.tencent.widget;

import android.view.View;

public interface OverScrollViewListener {
    public static final int OVERSCROLL_POSITION_BOTTOM = 1;
    public static final int OVERSCROLL_POSITION_TOP = 0;

    void onNotCompleteVisable(int i, View view, ListView listView);

    void onViewCompleteVisable(int i, View view, ListView listView);

    boolean onViewCompleteVisableAndReleased(int i, View view, ListView listView);

    void onViewNotCompleteVisableAndReleased(int i, View view, ListView listView);
}
