package com.tencent.widget;

import android.view.View;
import android.view.ViewConfiguration;

class AbsListView$PositionScroller implements Runnable {
    private static final int MOVE_DOWN_BOUND = 3;
    private static final int MOVE_DOWN_POS = 1;
    private static final int MOVE_OFFSET = 5;
    private static final int MOVE_UP_BOUND = 4;
    private static final int MOVE_UP_POS = 2;
    private static final int SCROLL_DURATION = 400;
    private int mBoundPos;
    private final int mExtraScroll;
    private int mLastSeenPos;
    private int mMode;
    private int mOffsetFromTop;
    private int mScrollDuration;
    private int mTargetPos;
    final /* synthetic */ AbsListView this$0;

    AbsListView$PositionScroller(AbsListView absListView) {
        this.this$0 = absListView;
        this.mExtraScroll = ViewConfiguration.get(absListView.getContext().getApplicationContext()).getScaledFadingEdgeLength();
    }

    void start(int i) {
        stop();
        int i2 = this.this$0.mFirstPosition;
        int childCount = (this.this$0.getChildCount() + i2) - 1;
        if (i <= i2) {
            i2 = (i2 - i) + 1;
            this.mMode = 2;
        } else if (i >= childCount) {
            i2 = (i - childCount) + 1;
            this.mMode = 1;
        } else {
            return;
        }
        if (i2 > 0) {
            this.mScrollDuration = 400 / i2;
        } else {
            this.mScrollDuration = 400;
        }
        this.mTargetPos = i;
        this.mBoundPos = -1;
        this.mLastSeenPos = -1;
        this.this$0.post(this);
    }

    void start(int i, int i2) {
        stop();
        if (i2 == -1) {
            start(i);
            return;
        }
        int i3 = this.this$0.mFirstPosition;
        int childCount = (this.this$0.getChildCount() + i3) - 1;
        if (i <= i3) {
            int i4 = childCount - i2;
            if (i4 >= 1) {
                childCount = (i3 - i) + 1;
                i3 = i4 - 1;
                if (i3 < childCount) {
                    this.mMode = 4;
                } else {
                    this.mMode = 2;
                    i3 = childCount;
                }
            } else {
                return;
            }
        } else if (i >= childCount) {
            i3 = i2 - i3;
            if (i3 >= 1) {
                childCount = (i - childCount) + 1;
                i3--;
                if (i3 < childCount) {
                    this.mMode = 3;
                } else {
                    this.mMode = 1;
                    i3 = childCount;
                }
            } else {
                return;
            }
        } else {
            return;
        }
        if (i3 > 0) {
            this.mScrollDuration = 400 / i3;
        } else {
            this.mScrollDuration = 400;
        }
        this.mTargetPos = i;
        this.mBoundPos = i2;
        this.mLastSeenPos = -1;
        this.this$0.post(this);
    }

    void startWithOffset(int i, int i2) {
        startWithOffset(i, i2, 400);
    }

    void startWithOffset(int i, int i2, int i3) {
        stop();
        this.mTargetPos = i;
        this.mOffsetFromTop = i2;
        this.mBoundPos = -1;
        this.mLastSeenPos = -1;
        this.mMode = 5;
        int i4 = this.this$0.mFirstPosition;
        int childCount = this.this$0.getChildCount();
        int i5 = (i4 + childCount) - 1;
        if (i < i4) {
            i4 -= i;
        } else if (i > i5) {
            i4 = i - i5;
        } else {
            this.this$0.smoothScrollBy(this.this$0.getChildAt(i - i4).getTop() - i2, i3);
            return;
        }
        this.mScrollDuration = (int) (((float) i3) / (((float) i4) / ((float) childCount)));
        this.mLastSeenPos = -1;
        this.this$0.post(this);
    }

    void stop() {
        this.this$0.removeCallbacks(this);
        if (AbsListView.access$2100(this.this$0) != null) {
            AbsListView.access$2100(this.this$0).stop();
        }
    }

    public void run() {
        int i = 0;
        if (this.this$0.mTouchMode == 4 || this.mLastSeenPos == -1) {
            int height = this.this$0.getHeight();
            int i2 = this.this$0.mFirstPosition;
            View childAt;
            int height2;
            int i3;
            switch (this.mMode) {
                case 1:
                    i = this.this$0.getChildCount() - 1;
                    i2 += i;
                    if (i < 0) {
                        return;
                    }
                    if (i2 == this.mLastSeenPos) {
                        this.this$0.post(this);
                        return;
                    }
                    childAt = this.this$0.getChildAt(i);
                    height2 = childAt.getHeight();
                    height -= childAt.getTop();
                    if (i2 < this.this$0.mItemCount - 1) {
                        i = this.mExtraScroll;
                    } else {
                        i = this.this$0.mListPadding.bottom;
                    }
                    this.this$0.smoothScrollBy(i + (height2 - height), this.mScrollDuration);
                    this.mLastSeenPos = i2;
                    if (i2 < this.mTargetPos) {
                        this.this$0.post(this);
                        return;
                    }
                    return;
                case 2:
                    if (i2 == this.mLastSeenPos) {
                        this.this$0.post(this);
                        return;
                    }
                    childAt = this.this$0.getChildAt(0);
                    if (childAt != null) {
                        height = childAt.getTop();
                        if (i2 > 0) {
                            i = this.mExtraScroll;
                        } else {
                            i = this.this$0.mListPadding.top;
                        }
                        this.this$0.smoothScrollBy(height - i, this.mScrollDuration);
                        this.mLastSeenPos = i2;
                        if (i2 > this.mTargetPos) {
                            this.this$0.post(this);
                            return;
                        }
                        return;
                    }
                    return;
                case 3:
                    height = this.this$0.getChildCount();
                    if (i2 != this.mBoundPos && height > 1 && height + i2 < this.this$0.mItemCount) {
                        height = i2 + 1;
                        if (height == this.mLastSeenPos) {
                            this.this$0.post(this);
                            return;
                        }
                        View childAt2 = this.this$0.getChildAt(1);
                        height2 = childAt2.getHeight();
                        i2 = childAt2.getTop();
                        i3 = this.mExtraScroll;
                        if (height < this.mBoundPos) {
                            this.this$0.smoothScrollBy(Math.max(0, (i2 + height2) - i3), this.mScrollDuration);
                            this.mLastSeenPos = height;
                            this.this$0.post(this);
                            return;
                        } else if (i2 > i3) {
                            this.this$0.smoothScrollBy(i2 - i3, this.mScrollDuration);
                            return;
                        } else {
                            return;
                        }
                    }
                    return;
                case 4:
                    i = this.this$0.getChildCount() - 2;
                    if (i >= 0) {
                        i2 += i;
                        if (i2 == this.mLastSeenPos) {
                            this.this$0.post(this);
                            return;
                        }
                        childAt = this.this$0.getChildAt(i);
                        height2 = childAt.getHeight();
                        i = childAt.getTop();
                        i3 = height - i;
                        this.mLastSeenPos = i2;
                        if (i2 > this.mBoundPos) {
                            this.this$0.smoothScrollBy(-(i3 - this.mExtraScroll), this.mScrollDuration);
                            this.this$0.post(this);
                            return;
                        }
                        height -= this.mExtraScroll;
                        i += height2;
                        if (height > i) {
                            this.this$0.smoothScrollBy(-(height - i), this.mScrollDuration);
                            return;
                        }
                        return;
                    }
                    return;
                case 5:
                    if (this.mLastSeenPos == i2) {
                        this.this$0.post(this);
                        return;
                    }
                    this.mLastSeenPos = i2;
                    height = this.this$0.getChildCount();
                    height2 = this.mTargetPos;
                    i3 = (i2 + height) - 1;
                    if (height2 < i2) {
                        i = (i2 - height2) + 1;
                    } else if (height2 > i3) {
                        i = height2 - i3;
                    }
                    float min = Math.min(Math.abs(((float) i) / ((float) height)), 1.0f);
                    if (height2 < i2) {
                        this.this$0.smoothScrollBy((int) (min * ((float) (-this.this$0.getHeight()))), this.mScrollDuration);
                        this.this$0.post(this);
                        return;
                    } else if (height2 > i3) {
                        this.this$0.smoothScrollBy((int) (min * ((float) this.this$0.getHeight())), this.mScrollDuration);
                        this.this$0.post(this);
                        return;
                    } else {
                        i = this.this$0.getChildAt(height2 - i2).getTop() - this.mOffsetFromTop;
                        this.this$0.smoothScrollBy(i, Math.abs((int) (((float) this.mScrollDuration) * (((float) i) / ((float) this.this$0.getHeight())))));
                        return;
                    }
                default:
                    return;
            }
        }
    }
}
