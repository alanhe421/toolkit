package com.tencent.widget;

public interface AbsListView$OnScrollListener {
    public static final int SCROLL_STATE_FLING = 2;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_TOUCH_SCROLL = 1;

    void onScroll(AbsListView absListView, int i, int i2, int i3);

    void onScrollStateChanged(AbsListView absListView, int i);
}
